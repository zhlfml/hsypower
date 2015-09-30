package com.hsypower.epct.web.filter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.hsypower.epct.utils.FileUtil;
import com.hsypower.epct.utils.ServletResponseUtil;
import com.hsypower.epct.utils.Validator;

public class Jsp2HtmlFilter implements Filter {

	public void init(FilterConfig config) throws ServletException {
		context = config.getServletContext();
		contextRealPath = context.getRealPath("/");
		String minute = config.getInitParameter("minute");
		String encode = config.getInitParameter("charset");
		if (Validator.isNotNull(minute)) {
			keepTime = Long.valueOf(minute) * MINUTE;
		}
		if (Validator.isNotNull(encode)) {
			encoding = encode;
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		StringBuilder filePath = new StringBuilder(contextRealPath)
				.append(File.separator).append(FOLDER).append(File.separator)
				.append(httpRequest.getServletPath()).append(SUFFIX);

		byte[] bytes = null;
		File file = new File(filePath.toString());

		if (file.exists()
				&& file.lastModified() + keepTime > System.currentTimeMillis()) {
			bytes = FileUtil.getBytes(file);
		} else {
			FileUtil.touch(file);
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			FileChannel fc = raf.getChannel();
			FileLock fl = null;
			try {
				fl = fc.lock();
				if (fl.isValid()) {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					CacheResponseWrapper cacheResponse = new CacheResponseWrapper(
							httpResponse, baos);
					chain.doFilter(request, cacheResponse);

					bytes = baos.toByteArray();
					raf.write(bytes);
					raf.setLength(bytes.length);

					fl.release();
					fc.close();
					raf.close();
				}
			} catch (OverlappingFileLockException e) {
				while (true) {
					try {
						fl = fc.tryLock();
						if (fl.isValid()) {
							bytes = new byte[(int) raf.length()];
							raf.readFully(bytes);

							fl.release();
							fc.close();
							raf.close();

							break;
						}
					} catch (OverlappingFileLockException e1) {
						try {
							Thread.sleep(150);
						} catch (InterruptedException e2) {
							// Ignore
						}
					}
				}
			}
		}

		ServletResponseUtil.write(httpResponse, bytes);
	}

	public void destroy() {
		context = null;
	}

	private class CacheResponseWrapper extends HttpServletResponseWrapper {
		private CacheOutputStream cacheOutputStream;

		private PrintWriter printWriter;

		public CacheResponseWrapper(HttpServletResponse reponse,
				ByteArrayOutputStream baos) {
			super(reponse);
			cacheOutputStream = new CacheOutputStream(baos);
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			return cacheOutputStream;
		}

		@Override
		public PrintWriter getWriter() throws IOException {
			if (printWriter == null) {
				printWriter = new PrintWriter(new OutputStreamWriter(
						cacheOutputStream, encoding), true);
			}

			return printWriter;
		}

		protected byte[] getBytes() throws IOException {
			if (cacheOutputStream != null) {
				return cacheOutputStream.getBytes();
			}

			return null;
		}
	}

	private class CacheOutputStream extends ServletOutputStream {
		private ByteArrayOutputStream baos;

		public CacheOutputStream(ByteArrayOutputStream baos) {
			this.baos = baos;
		}

		@Override
		public void write(int b) throws IOException {
			baos.write(b);
		}

		@Override
		public void write(byte[] b, int off, int len) throws IOException {
			baos.write(b, off, len);
		}

		protected byte[] getBytes() {
			return baos.toByteArray();
		}
	}

	private ServletContext context = null;
	private String contextRealPath = null;
	private String encoding = "UTF-8";
	private final static String FOLDER = "static";
	private final static String SUFFIX = ".shtml";
	private final static int MINUTE = 60 * 1000;
	private long keepTime = 100L * 365 * 24 * 60 * MINUTE; // default keep time
															// is one century
}

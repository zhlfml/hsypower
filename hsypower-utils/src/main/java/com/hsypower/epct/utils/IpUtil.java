package com.hsypower.epct.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {

	/**
	 * @param request
	 *            IP
	 * @return IP Address
	 */
	public static String getRemoteIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 思路 1.扫描网卡接口
	 * 
	 * @return local ip
	 * @throws SocketException
	 */
	public static String getLocalIp() {
		String localip = null;// 本地IP，如果没有配置外网IP则返回它
		String netip = null;// 外网IP
		Enumeration<NetworkInterface> netInterfaces = null;
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			return null;
		}
		InetAddress ip = null;
		boolean finded = false;// 是否找到外网IP
		while (netInterfaces.hasMoreElements() && !finded) {
			NetworkInterface ni = netInterfaces.nextElement();
			Enumeration<InetAddress> address = ni.getInetAddresses();
			while (address.hasMoreElements()) {
				ip = address.nextElement();
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {// 外网IP
					netip = ip.getHostAddress();
					finded = true;
					break;
				} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
					localip = ip.getHostAddress();
				}
			}
		}
		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}

	/**
	 * 思路 2.访问公网资源，由公网中特定程序捕获IP。
	 * 
	 * @return local ip
	 */
	public static String getLocalIp2() {
		// 输入流
		InputStream in = null;
		// 到外网提供者的Http连接
		HttpURLConnection httpConn = null;
		try {
			// 打开连接
			URL url = new URL("http://www.whereismyip.com/");
			httpConn = (HttpURLConnection) url.openConnection();
			// 连接设置
			HttpURLConnection.setFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
			// 获取连接的输入流
			in = httpConn.getInputStream();
			byte[] bytes = new byte[1024];// 此大小可根据实际情况调整
			// 读取到数组中
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length
					&& (numRead = in.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}

			return parse(new String(bytes, "UTF-8"));
		} catch (Exception e) {
			// ignore
		} finally {
			try {
				in.close();
				httpConn.disconnect();
			} catch (Exception ex) {
				// ignore
			}
		}

		return null;
	}

	/**
	 * 使用正则表达式解析返回的HTML文本,得到本机外网地址
	 * 
	 * @param html
	 */
	private static String parse(String html) {
		Pattern pattern = Pattern.compile(
				"(\\d{1,3})[.](\\d{1,3})[.](\\d{1,3})[.](\\d{1,3})",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(html);
		while (matcher.find()) {
			return matcher.group(0);
		}

		return null;
	}

}

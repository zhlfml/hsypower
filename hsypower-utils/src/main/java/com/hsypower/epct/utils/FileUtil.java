package com.hsypower.epct.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileUtil {
    
    public static String read(String file) throws IOException {
        return read(new File(file));
    }
    
    public static String read(File file) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader in = new BufferedReader(new FileReader(file));
        
        String s;
        while ((s = in.readLine()) != null) {
            sb.append(s);
            sb.append("\n");
        }
        in.close();
        
        return sb.toString();
    }
    
    public static void write(String file, String text) throws IOException {
        write(new File(file), text);
    }
    
    public static void write(File file, String text) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8")));
        
        out.print(text);
        out.close();
    }
    
    public static File[] listDirs(File file) {
        List<File> dirs = new ArrayList<File>();

        File[] fileArray = file.listFiles();

        for (int i = 0; (fileArray != null) && (i < fileArray.length); i++) {
            if (fileArray[i].isDirectory()) {
                dirs.add(fileArray[i]);
            }
        }

        return dirs.toArray(new File[dirs.size()]);
    }
    
    public static String getShortFileName(String fullFileName) {
        int pos = fullFileName.lastIndexOf(File.separator);

        String shortFileName =
            fullFileName.substring(pos + 1, fullFileName.length());

        return shortFileName;
    }
    
    public static String getShortFileName(File file) {
        return getShortFileName(file.getAbsolutePath());
    }
    
    public static String getParent(String fullFileName) {
        int pos = fullFileName.lastIndexOf(File.separator);

        String parentFileName = fullFileName.substring(0, pos + 1);

        return parentFileName;
    }
    
    public static String getParentFileName(File file) {
        return getParent(file.getAbsolutePath());
    }
    
    public static byte[] getBytes(File file) throws IOException {
		if ((file == null) || !file.exists()) {
			return null;
		}

		RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

		byte[] bytes = new byte[(int)randomAccessFile.length()];

		randomAccessFile.readFully(bytes);

		randomAccessFile.close();

		return bytes;
	}
    
    /**
     *  删除整个目录，包括子目录及文件
     *  
     * @param dir 被删除的目录
     */
    public static void rmdir(String dir) {
        rmdir(new File(dir));
    }
    
    /**
     *  删除整个目录，包括子目录及文件
     *  
     * @param dir 被删除的目录
     */
    public static void rmdir(File dir) {
        if (dir.exists()) {
            if (dir.isDirectory()) {
                File[] filelist = dir.listFiles();
                for (File subfile : filelist) {
                	rmdir(subfile.getAbsolutePath());
                }
            }
            dir.delete();
        }
    }
    
    public static boolean mkdir(String dir) {
    	return mkdir(new File(dir));
    }
    
    public static boolean mkdir(File dir) {
    	return dir.exists() || dir.mkdirs();
    }
    
    public static boolean touch(String file) throws IOException {
    	return touch(new File(file));
    }
    
    public static boolean touch(File file) throws IOException {
    	return file.exists() || (mkdir(file.getParent()) && file.createNewFile());
    }
    
    /**
     * test file whether contain word 
     *
     * @param regex key word
     * @return true if find word in file
     */
    public static boolean contains(String file, String regex) throws IOException {
    	return contains(new File(file), regex);
    }
    
    /**
     * test file whether contain word 
     * 
     * @param regex key word
     * @return true if find word in file
     */
    public static boolean contains(File file, String regex) throws IOException {
    	
    	boolean contain = false;
        BufferedReader in = new BufferedReader(new FileReader(file));
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        
        String s;
        while ((s = in.readLine()) != null) {
        	Matcher m = pattern.matcher(s.trim());
        	if (m.find()) {
        		contain = true;
        		break;
        	}
        }
        in.close();
        
        return contain;
    }
}

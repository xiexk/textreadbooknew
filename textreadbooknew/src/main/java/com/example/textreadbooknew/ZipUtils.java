package com.example.textreadbooknew;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

public class ZipUtils {

	private static final String CHINESE_CHARSET = "GBK";
	private static final int CACHE_SIZE = 1024;

	public static void zip(String sourceFolder, String zipFilePath) throws Exception {
		OutputStream out = new FileOutputStream(zipFilePath);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		ZipOutputStream zos = new ZipOutputStream(bos);
		zos.setEncoding(CHINESE_CHARSET);
		File file = new File(sourceFolder);
		String basePath = null;
		if (file.isDirectory()) {
			basePath = file.getPath();
		} else {
			basePath = file.getParent();
		}
		zipFile(file, basePath, zos);
		zos.closeEntry();
		zos.close();
		bos.close();
		out.close();
	}

	private static void zipFile(File parentFile, String basePath, ZipOutputStream zos) throws Exception {
		File[] files = new File[0];
		if (parentFile.isDirectory()) {
			files = parentFile.listFiles();
		} else {
			files = new File[1];
			files[0] = parentFile;
		}
		String pathName;
		InputStream is;
		BufferedInputStream bis;
		byte[] cache = new byte[CACHE_SIZE];
		for (File file : files) {
			if (file.isDirectory()) {
				pathName = file.getPath().substring(basePath.length() + 1) + "/";
				zos.putNextEntry(new ZipEntry(pathName));
				zipFile(file, basePath, zos);
			} else {
				pathName = file.getPath().substring(basePath.length() + 1);
				is = new FileInputStream(file);
				bis = new BufferedInputStream(is);
				zos.putNextEntry(new ZipEntry(pathName));
				int nRead = 0;
				while ((nRead = bis.read(cache, 0, CACHE_SIZE)) != -1) {
					zos.write(cache, 0, nRead);
				}
				bis.close();
				is.close();
			}
		}
	}

	public static boolean unZip(String zipFilePath, String destDir, String bookId) throws Exception {
		try {

			ZipFile zipFile = new ZipFile(zipFilePath, CHINESE_CHARSET);
			Enumeration<?> emu = zipFile.getEntries();
			BufferedInputStream bis;
			FileOutputStream fos;
			BufferedOutputStream bos;
			File file, parentFile;
			ZipEntry entry;
			byte[] cache = new byte[CACHE_SIZE];
			while (emu.hasMoreElements()) {
				entry = (ZipEntry) emu.nextElement();
				String content_path = entry.getName();
				if (content_path.startsWith("\\") || content_path.startsWith("/")) {
					content_path = content_path.substring(1, content_path.length());
				}
				if (entry.isDirectory()) {
					boolean isMake = new File(destDir + content_path).mkdirs();
					continue;
				}
				bis = new BufferedInputStream(zipFile.getInputStream(entry));
				file = new File(destDir + content_path);
				parentFile = file.getParentFile();
				if (parentFile != null && (!parentFile.exists())) {
					boolean isMake = parentFile.mkdirs();
				}
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos, CACHE_SIZE);
				int nRead = 0;
				while ((nRead = bis.read(cache, 0, CACHE_SIZE)) != -1) {
					fos.write(cache, 0, nRead);
				}
				bos.flush();
				bos.close();
				fos.close();
				bis.close();
			}
			zipFile.close();
			new File(zipFilePath).delete();
			return true;
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean unZip(String zipFilePath, String destDir) {
		try {
			ZipFile zipFile = new ZipFile(zipFilePath, CHINESE_CHARSET);
			Enumeration<?> emu = zipFile.getEntries();
			BufferedInputStream bis;
			FileOutputStream fos;
			BufferedOutputStream bos;
			File file, parentFile;
			ZipEntry entry;
			byte[] cache = new byte[CACHE_SIZE];
			while (emu.hasMoreElements()) {
				entry = (ZipEntry) emu.nextElement();
				String content_path = entry.getName();
				if (content_path.startsWith("\\") || content_path.startsWith("/")) {
					content_path = content_path.substring(1, content_path.length());
				}
				if (entry.isDirectory()) {
					boolean isMake = new File(destDir + content_path).mkdirs();
					continue;
				}
				bis = new BufferedInputStream(zipFile.getInputStream(entry));
				file = new File(destDir + content_path);
				parentFile = file.getParentFile();
				if (parentFile != null && (!parentFile.exists())) {
					boolean isMake = parentFile.mkdirs();
				}
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos, CACHE_SIZE);
				int nRead = 0;
				while ((nRead = bis.read(cache, 0, CACHE_SIZE)) != -1) {
					fos.write(cache, 0, nRead);
				}
				bos.flush();
				bos.close();
				fos.close();
				bis.close();
			}
			zipFile.close();
			return true;
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
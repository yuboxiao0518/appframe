package com.primeton.appframe.common.utils;

import java.net.MalformedURLException;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

public class SMBUtil {
	private static String ip="10.200.20.25";
	private static String path="/BaiduYunDownload/";
	private static String passWord="ftp";
	private static String userName="1234";
	public static void queryFile() {
		String url="smb://"+ip+path;
		NtlmPasswordAuthentication authentication = new NtlmPasswordAuthentication(ip, userName, passWord);
		try {
			SmbFile smbFile = new SmbFile(url,authentication);
			SmbFile[] listFiles = smbFile.listFiles();
			for(SmbFile listFile:listFiles) {
				String name = listFile.getName();
				System.out.println("fileName:"+name);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		queryFile();
	}

}

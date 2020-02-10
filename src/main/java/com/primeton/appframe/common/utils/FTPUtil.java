package com.primeton.appframe.common.utils;

import java.io.IOException;
import java.net.SocketException;
import java.net.URL;
import java.text.ParseException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import ch.ethz.ssh2.Connection;

public class FTPUtil {
	private Connection conn;
	private String ip="10.200.23.20";
	private String userName="ftp";
	private String passWord="1234";
	
	@SuppressWarnings("deprecation")
	public static void connect() throws SocketException, IOException, ParseException {
		// 连接
		String time="2017-12-14";
		String[] split = time.split("-");
		String year=split[0];
		String month=split[1];
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("10.200.23.20",21);
		ftpClient.login("ftp", "1234");
		ftpClient.setControlEncoding("GBK");
//		ftpClient.setFileTransferMode(FTP.ASCII_FILE_TYPE);
		FTPFile[] listFiles2 = ftpClient.listFiles(year+"/"+month);
		for(FTPFile listFile:listFiles2){
			String name = listFile.getName();
//			System.out.println(name);
		}
		FTPFile[] listFiles = ftpClient.listFiles();
		for(FTPFile listFile:listFiles) {
			String name = listFile.getName();
			System.out.println(name);
		}
	}
	public static void connect2() throws IOException {
	}

	public Boolean login() {
		// TODO 自动生成的方法存根
		Boolean flag = false;
		try {
			//"192.168.171.128"
			//http://192.168.171.128:8081/
			conn = new Connection(ip);
			conn.connect();
			flag = conn.authenticateWithPassword(userName, passWord);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}
	public static void main(String[] args) {
		try {
			connect();
//			FTPUtil ftpUtil = new FTPUtil();
//			Boolean login = ftpUtil.login();
//			System.out.println(login);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}

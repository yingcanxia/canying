package com.cust.easyutil;

import java.io.InputStream;
import java.net.URL;

public class ClassLoaderTool {
	static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	public ClassLoaderTool() {
	}
	public static String getClassFolder(){
		URL url=classLoader.getResource(".");
		return url.getPath().substring(1);
	}
	/**
	 * ��ȡclassĿ¼����Դ�ļ�
	 * @param path classĿ¼�µ���Դ�ļ������Ŀ¼
	 * @return ��
	 */
	public static InputStream getResourceAsStream(String path){
		return classLoader.getResourceAsStream(path);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(ClassLoaderTool.getClassFolder());
	}

}

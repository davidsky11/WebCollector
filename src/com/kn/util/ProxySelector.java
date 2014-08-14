package com.kn.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.Scanner;

public class ProxySelector {

	// 设置本地JVM的网络默认配置
	public void setLocalProxy() {
		Properties prop = System.getProperties();

		prop.setProperty("http.proxyHost", "10.191.131.19");
		prop.setProperty("http.proxyPort", "3128");
		prop.setProperty("http.nonProxyHosts", "localhost|10.203.*");

		prop.setProperty("https.proxyHost", "10.191.131.19");
		prop.setProperty("https.proxyPort", "3128");

		prop.setProperty("ftp.proxyHost", "10.191.131.19");
		prop.setProperty("ftp.proxyPort", "3128");
		prop.setProperty("ftp.nonProxyHosts", "localhost|10.203.*");

		prop.setProperty("socks.proxyHost", "10.191.131.19");
		prop.setProperty("socks.proxyPort", "3128");

	}

	// 清除proxy设置
	public void removeLocalProxy() {

		Properties prop = System.getProperties();

		prop.remove("http.proxyHost");
		prop.remove("http.proxyPort");
		prop.remove("http.nonProxyHosts");

		prop.remove("https.proxyHost");
		prop.remove("https.proxyPort");

		prop.remove("ftps.proxyHost");
		prop.remove("ftps.proxyPort");
		prop.remove("ftp.nonProxyHosts");

		prop.remove("socks.proxyHost");
		prop.remove("socks.proxyPort");
	}

	// 测试HTTP访问
	public void showHttpProxy() throws MalformedURLException, IOException {
		URL url = new URL("http://www.cnblogs.com/longwu/archive/2011/12/24/2300110.html");
		// 直接打开连接，但系统会调用刚设置的HTTP代理服务器
		URLConnection conn = url.openConnection(); // ①
		Scanner scan = new Scanner(conn.getInputStream());
		// 读取远程主机的内容
		while (scan.hasNextLine()) {
			System.out.println(scan.nextLine());
		}
	}

	public static void main(String[] args) throws IOException {
		ProxySelector test = new ProxySelector();
		test.setLocalProxy();
		test.showHttpProxy();
		test.removeLocalProxy();
	}

}

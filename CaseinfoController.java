package com.neusoft.lemis.rest.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neusoft.lemis.rest.service.CaseinfoService;
import com.neusoft.lemis.rest.utils.ProxyUtil;
import com.neusoft.lemis.rest.utils.SslUtil;

@Controller
public class CaseinfoController {
	
	@Autowired
	private CaseinfoService caseinfoService;
	
	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String getCaseInfo(@RequestBody String reqParam) {
		return caseinfoService.getCaseInfo(reqParam);
	}
	
	public static void main(String[] args) {
//		String urladd = "https://devsaasapi.odrcloud.cn/gzzc/meeting/addMeeting";
		String urladd = "https://devsaasapi.odrcloud.cn/gzzc/user/userLoginByMobile";
		HttpURLConnection connection = null;
		OutputStream dataout = null;
		BufferedReader reader = null;
		String line = null;

		try {
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("dl-proxy.neusoft.com", 8080));
			Authenticator.setDefault(new ProxyUtil("liu_yuxuan", "lyx@1994114"));
			URL url = new URL(urladd);
			SslUtil.ignoreSsl();
			connection = (HttpURLConnection) url.openConnection(proxy);// 根据URL生成HttpURLConnection
			connection.setDoOutput(true);// 设置是否向connection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true,默认情况下是false
			connection.setDoInput(true); // 设置是否从connection读入，默认情况下是true;
			connection.setRequestMethod("POST");// 设置请求方式为post,默认GET请求
			connection.setUseCaches(false);// post请求不能使用缓存设为false
			connection.setConnectTimeout(5000);// 连接主机的超时时间
			connection.setReadTimeout(5000);// 从主机读取数据的超时时间
			connection.setInstanceFollowRedirects(true);// 设置该HttpURLConnection实例是否自动执行重定向
			connection.setRequestProperty("connection", "Keep-Alive");// 连接复用
			connection.setRequestProperty("charset", "utf-8");

			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("cType", "PC");
			connection.setRequestProperty("appName", "gzzc");
			connection.connect();// 建立TCP连接,getOutputStream会隐含的进行connect,所以此处可以不要

			dataout = new DataOutputStream(connection.getOutputStream());// 创建输入输出流,用于往连接里面输出携带的参数
//			List list = new ArrayList();
//			list.add("{'userName' : '张三', 'mobilePhone' : '12345678901'}");
//			String body = "[{\"name\":\"会议1\",\"orderTime\":\"2019-12-09\",\"meetingType\":\"1\",\"orderType\":\"1\",\"caseId\":\"\",\"meetingUserList\":\"" + list + "\",\"caseId\":\"\"}]";
			String body = "{mobilePhone: '15960210993', password: 'Aa88888888'}";
			dataout.write(body.getBytes());
			dataout.flush();
			dataout.close();

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 发送http请求
				StringBuilder result = new StringBuilder();
				// 循环读取流
				while ((line = reader.readLine()) != null) {
					result.append(line).append(System.getProperty("line.separator"));//
				}
				System.out.println(result.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			connection.disconnect();
		}
	}
}

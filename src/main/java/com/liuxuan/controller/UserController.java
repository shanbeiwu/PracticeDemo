package com.liuxuan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuxuan.pojo.User;


@Controller
@RequestMapping("/json")
public class UserController{
	User user = null;
	/**
	 * ʹ��@autowiredҲ���ԣ�@autowirtedĬ�ϰ�������װ��
	 * @resourceĬ�ϰ�����װ�䣬���Ҳ�����ƥ���bean�Żᰴ������װ��
	 */
	
	/**
	 * ���Բ�ѯ
	 * @param id
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/hello", produces = "application/json;charset=UTF-8")
	public List<User> hello() {
		List<User> list = new ArrayList<User>();
		user = new User();
		user.setId(1);
		user.setName("ɽ����");
		user.setPassword("123");
		list.add(user);
		JSONArray jsarr = JSONArray.fromObject(list);
		System.out.println("this is json end");
		return jsarr;
	}
	
	/**
	 * �˷�����ȡǰ̨�����id��name��password
	 * �����ػ�ȡ���
	 * @param setuser
	 * @return jsonarray
	 */
	@ResponseBody
	@RequestMapping(value = "/set", produces = "application/json;charset=UTF-8", method = {RequestMethod.POST})
	public JSONArray setsum(@RequestBody List<User> setuser) {
		List<String> list=new ArrayList<String>();
		String str  = "are you sure success ?";
		for(int i = 0; i < setuser.size(); i++) {
			user = setuser.get(i);
		}
		System.out.println(user.toString());
		list.add(str);
		JSONArray json = JSONArray.fromObject(list);
		return json;
	}
	

		
	
}

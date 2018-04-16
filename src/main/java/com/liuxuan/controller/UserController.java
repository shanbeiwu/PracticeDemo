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
	 * 使用@autowired也可以，@autowirted默认按照类型装配
	 * @resource默认按名称装配，当找不到与匹配的bean才会按照类型装配
	 */
	
	/**
	 * 测试查询
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
		user.setName("山贝戊");
		user.setPassword("123");
		list.add(user);
		JSONArray jsarr = JSONArray.fromObject(list);
		System.out.println("this is json end");
		return jsarr;
	}
	
	/**
	 * 此方法获取前台出入的id，name，password
	 * 并返回获取结果
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

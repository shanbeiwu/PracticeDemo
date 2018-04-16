package com.liuxuan.pojo;
/**
 * 
 * @author liuyuxuan
 * 此类为pojo类，用来定义属性以及属性类型
 * 在这里定义了三个属性，int ID，String name,String password
 * 分别对应数据库中ID，应户名，密码
 * 并且构造好了set，get方法以及全参数的构造器和无参构造器
 * 并重写了tostring方法
 *
 */
public class User {
    private Integer id;

    private String name;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

	@Override
	public String toString() {
		return "User [id为：" + id + ", 用户名为：" + name + ", 密码为：" + password
				+ "]";
	}
    
}
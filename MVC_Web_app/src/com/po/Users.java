package com.po;

import java.sql.Date;

/*
 * 用户类
 * */
public class Users {

	private String usernum;//工号
	private String password;//密码
	private String username;//姓名
	private String sex;//性别
	private String diplomas;//学历
	private String position;//职位
	private String salary;//月薪
	private Date birthday;//出生年月
	private Date workday;//入职时间
	
	public Users(String usernum,String password,String username,String sex,
			String diplomas,String position,String salary,Date birthday,Date workday) {
		this.usernum=usernum;
		this.password=password;
		this.username=username;
		this.sex=sex;
		this.diplomas=diplomas;
		this.position=position;
		this.salary=salary;
		this.birthday=birthday;
		this.workday=workday;
	}
	//仅有一个工号的构造方法：
	public Users(String usernum) {
		this.usernum=usernum;
	}
	//仅有工号和密码的构造方法：
	public Users(String usernum,String password) {
		this.usernum=usernum;
		this.password=password;
	}
	public String getUsernum() {
		return usernum;
	}
	public void setUsernum(String usernum) {
		this.usernum = usernum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDiplomas() {
		return diplomas;
	}
	public void setDiplomas(String diplomas) {
		this.diplomas = diplomas;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getWorkday() {
		return workday;
	}
	public void setWorkday(Date workday) {
		this.workday = workday;
	}
	
}

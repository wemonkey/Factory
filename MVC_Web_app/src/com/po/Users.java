package com.po;

import java.sql.Date;

/*
 * �û���
 * */
public class Users {

	private String usernum;//����
	private String password;//����
	private String username;//����
	private String sex;//�Ա�
	private String diplomas;//ѧ��
	private String position;//ְλ
	private String salary;//��н
	private Date birthday;//��������
	private Date workday;//��ְʱ��
	
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
	//����һ�����ŵĹ��췽����
	public Users(String usernum) {
		this.usernum=usernum;
	}
	//���й��ź�����Ĺ��췽����
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

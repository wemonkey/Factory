package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UsersDAO;
import com.po.Users;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String usernum = "";//工号
		String password = "";//密码
		String username ="";//姓名
		String sex = "";//性别
		String diplomas = "" ;//学历
		String position =  "";//职位
		String salary = "";//月薪
		String birthday = "";//出生年月
		String workday = "";//入职日期
		
		request.setCharacterEncoding("utf-8");//防止中文乱码
		
		usernum = request.getParameter("usernum");//接收用户输入的工号
		password = request.getParameter("password");//接收用户输入的密码
		username = request.getParameter("username");//接收用户输入的姓名
		sex = request.getParameter("sex");//接收用户输入的性别
		diplomas = request.getParameter("diplomas");//接收用户输入的学历
		position = request.getParameter("position");//接收用户输入的职位
		salary = request.getParameter("salary");//接收用户输入的月薪
		birthday = request.getParameter("birthday");//接收用户输入的出生年月
		workday = request.getParameter("workday");//接收用户输入的入职日期
		java.sql.Date birthday_Date=Date.valueOf(birthday);//转化为SQL date类型
		java.sql.Date workday_Date=Date.valueOf(workday);//转化为SQL date类型
		
		Users user = new Users(usernum , password ,username ,sex , diplomas , position , salary , birthday_Date , workday_Date);
		UsersDAO userdao = new UsersDAO();
//		try {
//			userdao.Modify(user);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			boolean flag=userdao.AddStaff(user);
			if(flag==false){//增加失败
				request.setAttribute("error", "addError");
			}
			else {//增加成功
				request.setAttribute("error", "noAddError");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		request.getRequestDispatcher("Main_Page.jsp").forward(request, response);
		//response.sendRedirect("Main_Page.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

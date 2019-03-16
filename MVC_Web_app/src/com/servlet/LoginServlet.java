package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UsersDAO;
import com.po.Users;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String U_usernum = "";//存储用户输入的用户名
		String U_password = "";//存储用户输入的密码
		boolean flag=false;

		request.setCharacterEncoding("utf-8");//防止中文乱码
		request.setCharacterEncoding("UTF-8");
		String CONTENT_TYPE = "text/html; charset=UTF-8";
		response.setContentType(CONTENT_TYPE);

		U_usernum = request.getParameter("usernum");//接受传入的用户输入的用户名
		U_password = request.getParameter("password");//接受传入的用户输入的密码
		System.out.println("用户输入："+U_usernum+U_password);//测试代码
		
		Users user = new Users(U_usernum,U_password);
		UsersDAO userdao = new UsersDAO();

		try {
			flag = userdao.IsLogined(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (flag==true) {
			//登陆成功,跳转到主页面,session中的loginUser保存用户工号
			request.getSession().setAttribute("loginUser", U_usernum);
			request.getSession().setAttribute("password", U_password);
			request.getRequestDispatcher("Main_Page.jsp").forward(request, response);
		} else {
			//密码不能匹配,返回登录界面并提示
			PrintWriter out = response.getWriter() ;
			out.print("<script language=\"JavaScript\">\r\n" + 
					"				history.back();\r\n" + 
					"				alert(\"登陆失败\");\r\n" + 
					"				</script>");
		}
	}
}

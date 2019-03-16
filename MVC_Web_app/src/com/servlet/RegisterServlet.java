package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UsersDAO;
import com.po.Users;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String U_username = "";//用户输入的用户名
		String U_password1 = "";//用户输入的第一次密码
		String U_password2 = "";//用户输入的第二次密码
		boolean Ps_flag=false;

		request.setCharacterEncoding("utf-8");//防止中文乱码
		request.setCharacterEncoding("UTF-8");
		String CONTENT_TYPE = "text/html; charset=UTF-8";
		response.setContentType(CONTENT_TYPE);

		U_username = request.getParameter("usernum2");//接受传入的用户输入的用户名
		U_password1 = request.getParameter("Password1");//接受传入的用户输入的第一次密码
		U_password2 = request.getParameter("Password2");//接受传入的用户输入的第二次密码
		System.out.println("用户输入注册信息"+U_username+" "+U_password1);//测试代码
		PrintWriter out = response.getWriter() ;
		if (!U_password1.equals(U_password2)) {
			//两次密码不一致,返回页面并提示
			out.print("<script language=\"JavaScript\">\r\n" + 
					"			history.back();\r\n" + 
					"			alert(\"注册失败,请确认两次密码一致\");\r\n" + 
					"			</script>");

		} else {
			Users user = new Users(U_username,U_password1);
			UsersDAO userdao = new UsersDAO();
			try {
				Ps_flag = userdao.register(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			if (Ps_flag ==true ) {
				System.out.println("新员工信息添加成功");
				out.print("<script type=\"text/javascript\">  \r\n" + 
						"			         window.location=\"index.jsp\";  \r\n" + 
						"			         alert(\"注册成功\");  \r\n" + 
						"			         </script>  ");           
			} else {
				//存在异常输入时,用于返回注册页面
				out.print("<script language=\"JavaScript\">\r\n" + 
						"					history.back();\r\n" + 
						"					alert(\"注册失败,请重新注册\");\r\n" + 
						"					</script>");

			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

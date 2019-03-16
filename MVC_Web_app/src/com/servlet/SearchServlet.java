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
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String S_usernum = request.getParameter("S_usernum");
		//request.setAttribute("S_usernum", S_usernum);

		request.setCharacterEncoding("utf-8");//防止中文乱码
		request.setCharacterEncoding("UTF-8");
		String CONTENT_TYPE = "text/html; charset=UTF-8";
		response.setContentType(CONTENT_TYPE);
		
		Users user =new Users(S_usernum);
		UsersDAO userdao = new UsersDAO();
		Users user2;
		try {
			user2 = userdao.SearchStaff(user);
			if(user2==null){
				PrintWriter out = response.getWriter() ;
				out.print("<script language=\"JavaScript\">\r\n" + 
						"					history.back();\r\n" + 
						"					alert(\"没有此号员工！\");\r\n" + 
						"					</script>");
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				request.setCharacterEncoding("utf-8");//防止中文乱码

				request.setAttribute("user2", user2);
				request.getRequestDispatcher("SearchStaff.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

package com.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UsersDAO;
import com.po.Users;

/**
 * Servlet implementation class ShowStaffInfoServlet
 */
@WebServlet("/ShowStaffInfoServlet")
public class ShowStaffInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowStaffInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String M_usernum = "";//接收Main_Page传送的工号;
		request.setCharacterEncoding("utf-8");//防止中文乱码
		M_usernum = request.getParameter("usernum");//接收查询的工号
		Users user =new Users(M_usernum);
		UsersDAO userdao = new UsersDAO();
		try {
			Users user2 = userdao.SearchStaff(user);//查询已有信息
			request.setAttribute("user2", user2);//将查到的信息放到request域里
			request.getRequestDispatcher("ShowStaffInfo.jsp").forward(request, response);//请求转发
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

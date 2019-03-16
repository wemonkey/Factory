<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="com.po.Users"%>
<%@ page import="com.dao.UsersDAO"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.lang.*"%>
<%@page import="com.mysql.jdbc.Driver"%>
<%@page import="java.sql.Date"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>员工管理页面</title>
    <link rel="stylesheet" href="css/mainStyle.css" type="text/css" media="all">
  </head>
  
  <body>
	<%
		//判断是否增加成功：
		String errorStr = (String) request.getAttribute("error");
		if(errorStr!=null){
			if (errorStr.equals("addError")){
				out.print("<script language=\"JavaScript\">\r\n" + 
						"			history.back();\r\n" + 
						"			alert(\"该工号已被使用,请重新添加\");\r\n" + 
						"			</script>");
			}
			else if(errorStr.equals("noAddError")){
				out.print("<script language=\"JavaScript\">\r\n" + 
						"			alert(\"增加成功\");\r\n" + 
						"			</script>");
			}
			else if(errorStr.equals("nodelteError")){
				out.print("<script language=\"JavaScript\">\r\n" + 
						"			alert(\"删除成功\");\r\n" + 
						"			</script>");
			}
			else if(errorStr.equals("noModifyError")){
				out.print("<script language=\"JavaScript\">\r\n" + 
						"			alert(\"修改成功\");\r\n" + 
						"			</script>");
			}
		}
		
	%>
	<h1>员工管理系统</h1>
    <hr>
	<%
			String loginUser = "";
   			String password = "";
			if (session.getAttribute("loginUser") != null) {
				loginUser = session.getAttribute("loginUser").toString();
			}
			if (session.getAttribute("password") != null) {
				password = session.getAttribute("password").toString();
			}
	%>
	<h3>欢迎<%=loginUser %>登录员工管理系统!</h3><br></font>
	<!-- 查询和添加按钮 -->
	<div class="Search_Add">
		<%
			if (loginUser.equals("admin") && password.equals("admin")) {
		%>
		<form action="AddStaff.jsp" method="post">
			<input type="submit"  value="添加员工" class="Add_Button">
		</form>
		<%
			} else {
			}
		%>
		<form action="SearchServlet" method="post">
			<input type="submit"  value="查询员工" class="Search_Button"> 
			<input type="text" name="S_usernum" placeholder="请输入查询的工号" required maxLength="8">
		</form>	
	</div>


	<!-- 登录成功的员工的单条信息 :-->
	<div class="StaffInfo_One">
		<%
			String S_usernum = loginUser;

			String U_usernum = "";//工号
			String U_password = "";//密码
			String U_username = "";//姓名
			String U_sex = "";//性别
			String U_diplomas = "";//学历
			String U_position = "";//职位
			String U_salary = "";//月薪
			Date U_birthday = null;//出生年月
			Date U_workday = null;//入职日期
	
			request.setCharacterEncoding("utf-8");//防止中文乱码
			
			Users user = new Users(S_usernum);
			UsersDAO userdao = new UsersDAO();
			Users user2 = userdao.SearchStaff(user);

			U_usernum = user2.getUsernum();//工号
			U_password = user2.getPassword();//密码
			U_username = user2.getUsername();//姓名
			U_sex = user2.getSex();//性别
			U_diplomas = user2.getDiplomas();//学历
			U_position = user2.getPosition();//职位
			U_salary = user2.getSalary();//月薪
			U_birthday = user2.getBirthday();//出生年月
			U_workday = user2.getWorkday();//入职日期
		%>
		<h3>您好,您的信息为:</h3>
		<table id="staff_table" border="1">
			<tr>
				<td>工号</td>
				<td>姓名</td>
				<td>性别</td>
				<td>学历</td>
				<td>职位</td>
				<td>月薪</td>
				<td>出生年月</td>
				<td>入职时间</td>
				<td>修改操作</td>
			</tr>
			<tr>
				<td><%=U_usernum%></td>
				<td><%=U_username%></td>
				<td><%=U_sex%></td>
				<td><%=U_diplomas%></td>
				<td><%=U_position%></td>
				<td><%=U_salary%></td>
				<td><%=U_birthday%></td>
				<td><%=U_workday%></td>
				<td><a href="ModifySearchServlet?usernum=<%=U_usernum%>">修改</a></td>	
			</tr>
		</table>
	</div>



	<!-- 所有员工信息表: -->
	<div class="StaffInfo_All">
		<h3>所有员工信息为:</h3>
		<table border="1">
			<tr>
				<td>工号</td>
				<td>姓名</td>
				<td>性别</td>
				<td>学历</td>
				<td>职位</td>
				<td>月薪</td>
				<td>出生年月</td>
				<td>入职时间</td>
				<td>修改操作</td>
				<td>删除操作</td>
			</tr>
			<%
				//通过循环,来动态建立表格
				UsersDAO dao = new UsersDAO();
				List<Users> list = dao.ReadStaffList();
				for (Users tl : list) {
			%>
				<tr>
					<td><a href="ShowStaffInfoServlet?usernum=<%=tl.getUsernum()%>"><%=tl.getUsernum()%></a></td>
					<td><%=tl.getUsername()%></td>
					<td><%=tl.getSex()%></td>
					<td><%=tl.getDiplomas()%></td>
					<td><%=tl.getPosition()%></td>
					<td><%=tl.getSalary()%></td>
					<td><%=tl.getBirthday()%></td>
					<td><%=tl.getWorkday()%></td>
				<%
				if (loginUser.equals("admin") && password.equals("admin")) {
				%>
					
					<script type="text/javascript">
						function confirmDel(usernum) {
							if (confirm("确认删除员工"+usernum+"吗？")) {
								window.location.href = 'DeleteServlet?usernum='+usernum;
							}
						}
					</script>
					<td><a href="ModifySearchServlet?usernum=<%=tl.getUsernum()%>">修改</a></td>		
					<td><a onclick='confirmDel(<%=tl.getUsernum()%>)'>删除</a></td>
					
				<%
				} else {
				%>
					<td>无法操作</td>
					<td>无法操作</td>
				<% 
				}
				%>
				</tr>
			<%
			}
			%>
		</table>
	</div>
</body>
</html>

<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,com.po.Users"%>
<%@page import="java.sql.Date,com.dao.UsersDAO"%>
<%@page import="java.lang.*"%>
<%@page import="com.mysql.jdbc.Driver"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/addStyle.css" type="text/css" media="all">
<title>修改员工信息</title>
</head>
<body>
	<%
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");//防止中文乱码

		Users user2 = (Users) request.getAttribute("user2");//查询已有信息
	%>
	<div class="Add_Container">
		<div class="register w3layouts agileits">
			<h2>员工信息详情</h2>
			<form>
				<input type="text"  value="工号：<%=user2.getUsernum() %>"disabled> 
				<input type="text" value="姓名：<%=user2.getUsername() %>" disabled> 
				<label class="Input_class">
				&nbsp;<span style="color:white">性别:</span> 
				<%if(user2.getSex().equals("男")){
				%>
					<input type="radio" value="男"  checked/><span style="color:white">男</span>
					<input type="radio" value="女 " /><span style="color:white">女</span> 
				<%
				}
				else{
			 	%>
					<input type="radio" value="男"  /><span style="color:white">男</span>
					<input type="radio" value="女 " checked/><span style="color:white">女</span>  
				<% 
				}
				%>
				</label>
				<!-- <hr style="width: 98%; float: left; background-color: #CCC;" /> -->
				
				<input type="text" value="学历：<%=user2.getDiplomas() %>" disabled> 
				<input type="text" value="职位：<%=user2.getPosition() %>" disabled> 
				<input type="text" value="月薪：<%=user2.getSalary() %>" disabled> 
				<label class="Input_class"> 
					<span style="color:white">出生年月:</span> 
					<input type="date" value=<%=user2.getBirthday() %> disabled>
				</label>
				<br>		
				<label class="Input_class"> 
					<span style="color:white">入职日期:</span> 
					<input type="date" value=<%=user2.getWorkday() %> disabled>
				</label> 
				<br>
				<div class="send-button w3layouts agileits">
					<input type="button" value="返回首页" onclick="window.location.href='Main_Page.jsp'">
				</div>
				
			</form>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
</body>
</html>
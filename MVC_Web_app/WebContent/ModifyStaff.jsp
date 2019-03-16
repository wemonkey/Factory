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
			<h2>修改员工信息</h2>
			<form action="ModifyServlet" method="post">
				<input type="text" Name="usernum"  maxLength="8" value=<%=user2.getUsernum() %> readonly="readonly"> 
				<input type="text" Name="password"  placeholder="密码" required maxLength="16" value=<%=user2.getPassword() %>>
				<input type="text" Name="username" placeholder="姓名" required maxLength="20"  value=<%=user2.getUsername() %>> 
				<label class="Input_class">
				&nbsp;<span style="color:white">性别:</span> 
				<%if(user2.getSex().equals("男")){
				%>
					<input name="sex" type="radio" value="男"  checked/><span style="color:white">男</span>
					<input name="sex" type="radio" value="女 " /><span style="color:white">女</span> 
				<%
				}
				else{
			 	%>
					<input name="sex" type="radio" value="男"  /><span style="color:white">男</span>
					<input name="sex" type="radio" value="女 " checked/><span style="color:white">女</span>  
				<% 
				}
				%>
				</label>
				<!-- <hr style="width: 98%; float: left; background-color: #CCC;" /> -->
				
				<input type="text" Name="diplomas"  placeholder="学历" required maxLength="20" value=<%=user2.getDiplomas() %>> 
				<input type="text" Name="position" placeholder="职位" required maxLength="20" value=<%=user2.getPosition() %>> 
				<input type="text" Name="salary" placeholder="月薪" required maxLength="20" value=<%=user2.getSalary() %>> 
				<label class="Input_class"> 
				<span style="color:white">出生年月:</span> 
				<input type="date"  name="birthday" value=<%=user2.getBirthday() %>>
				</label>
				<br>		
				<label class="Input_class"> 
				<span style="color:white">入职日期:</span> 
				<input type="date" name="workday" value=<%=user2.getWorkday() %>>
				</label> 
				<br>
				<div class="send-button w3layouts agileits">
					<input type="submit" value="确认修改">
				</div>
			</form>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
</body>
</html>
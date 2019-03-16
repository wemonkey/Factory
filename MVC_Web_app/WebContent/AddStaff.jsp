<%@ page language="java" import="java.util.*,java.net.*"
	contentType="text/html; charset=utf-8"%>
<%@page import="java.sql.*,com.po.Users"%>
<%@page import="java.util.*,com.dao.UsersDAO"%>
<%@page import="java.lang.*"%>
<%@page import="com.mysql.jdbc.Driver"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	UsersDAO userdao = new UsersDAO();
	userdao.CreateStaff();
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>添加员工信息</title>

<!-- Meta-Tags -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<!-- //Meta-Tags -->

<!-- Style -->
<link rel="stylesheet" href="css/addStyle.css" type="text/css" media="all">
</head>
<body>
<!-- container w3layouts agileits;register w3layouts agileits -->
	<div class="Add_Container">
		<div class="register w3layouts agileits">
			<h2>添加员工信息</h2>
			<form action="AddServlet" method="post">
				<input type="text" Name="usernum" placeholder="工号(8位)" required maxLength="8" onkeyup='this.value=this.value.replace(/[^1-9]*$/,"")' onblur='this.value=this.value.replace(/^[0]+[0-9]*$/gi,"")'> 
				<input type="password" Name="password" placeholder="密码" required maxLength="16">
				<input type="text" Name="username" placeholder="姓名" required maxLength="20"> 
				<label class="Input_class">
				&nbsp;<span style="color:white">性别:</span> 
				<input name="sex" type="radio" value="男"  checked/><span style="color:white">男</span>
				<input name="sex" type="radio" value="女 " /><span style="color:white">女 </span>
				</label> 
				<input type="text" Name="diplomas" placeholder="学历" required maxLength="20"> 
				<input type="text" Name="position" placeholder="职位" required maxLength="20"> 
				<input type="text" Name="salary" placeholder="月薪" required maxLength="20"> 
				<label class="Input_class"> 
				<span style="color:white">出生年月:</span> 
				<input type="date" name="birthday" value="1990-01-01">
				</label> 
				<br>		
				<br>
				<label class="Input_class"> 
				<span style="color:white">入职日期:</span> 
				<input type="date" name="workday" value="2008-01-01">
				</label> 
				<br>
				<br>
				<div class="send-button w3layouts agileits">
					<input type="submit" value="确认添加">
				</div>
			</form>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>

	</div>

</body>
<!-- //Body -->

</html>
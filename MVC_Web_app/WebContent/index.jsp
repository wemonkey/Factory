<%@ page language="java" import="java.util.*,java.net.*"
	contentType="text/html; charset=utf-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.lang.*,com.dao.UsersDAO"%>
<%@page import="com.mysql.jdbc.Driver"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%!//定义数据库连接对象
	public static final String driver = "com.mysql.jdbc.Driver";
	//定义数据库地址
	public static final String url = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false"; //数据库名
	public static final String username = "root";
	public static final String password = "000000"; //改成自己的root密码
%>
<%
	Connection conn = null;
%>
<%
	//连接数据库建表,用于保存登录的用户名与密码
	UsersDAO userdao = new UsersDAO();
	userdao.CreateStaff();
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>系统登录页面</title>

<!-- Meta-Tags -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="application/x-javascript">
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<!-- //Meta-Tags -->

<!-- Style -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
</head>
<body>

	<h1>用户登录</h1>
	<div class="container w3layouts agileits">

		<div class="login w3layouts agileits">
			<h2>登 录</h2>
			<form action="LoginServlet" method="post">
				<input type="text" name="usernum" placeholder="请输入八位工号" required
					 maxLength="8"> <input type="password"
					name="password" placeholder="密码" required maxLength="16">

				<ul class="tick w3layouts agileits">
					<li><br><br></li>
				</ul>
				<div class="send-button w3layouts agileits">
					<input type="submit" value="登 录">
				</div>
			</form>
			<div class="clear"></div>
		</div>

		<div class="register w3layouts agileits">
			<h2>员工注册</h2>
			<form action="RegisterServlet" method="post">
				<input type="text" Name="usernum2" placeholder="请输入八位工号" required maxLength="8" onkeyup='this.value=this.value.replace(/[^1-9]*$/,"")' onblur='this.value=this.value.replace(/^[0]+[0-9]*$/gi,"")'> 
				<input type="password" Name="Password1" placeholder="密码" required maxLength="16">
				<input type="password" Name="Password2" placeholder="确认密码" required maxLength="16">
				<div class="send-button w3layouts agileits">
					<input type="submit" value="确认注册">
				</div>
			</form>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>

	</div>

</body>
<!-- //Body -->

</html>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@page import="java.sql.*,com.po.Users"%>
<%@page import="java.sql.Date,com.dao.UsersDAO"%>
<%@page import="java.util.*"%>
<%@page import="java.lang.*"%>
<%@page import="com.mysql.jdbc.Driver"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>员工查询页面</title>
    <link rel="stylesheet" href="css/mainStyle.css" type="text/css" media="all">
  </head>
  
  <body>
    <h1>员工管理系统</h1>
    <hr>
    <%
			String loginUser = "";
			String passwordx = "";
			if (session.getAttribute("loginUser") != null) {
				loginUser = session.getAttribute("loginUser").toString();
			}
			if (session.getAttribute("password") != null) {
				passwordx = session.getAttribute("password").toString();
			}
	%>
  <font style="color:white"><h3>欢迎<%=loginUser %>登录员工管理系统!</h3><br></font>

			<%
				//String S_usernum = request.getParameter("S_usernum");
				//request.setAttribute("S_usernum", S_usernum);
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				request.setCharacterEncoding("utf-8");//防止中文乱码
				Users user2=(Users)request.getAttribute("user2");
				
			%>

			<div class="Search_Add">
				<div>
				<input type="button" class="Add_Button" value="返回首页" onclick="window.location.href='Main_Page.jsp'">
				</div>
				<div>
				<form action="SearchServlet" method="post">
				<input type="submit" value="查询员工"  class="Search_Button"> 
				<input type="text" name="S_usernum"placeholder="请输入查询的工号" required maxLength="8" >
				</form>
				</div>
			</div>
			<div  class=StaffInfo_One>
				<table id="student_table" border="1">
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
					<tr>
						<td><%=user2.getUsernum()%></td>
						<td><%=user2.getUsername()%></td>
						<td><%=user2.getSex()%></td>
						<td><%=user2.getDiplomas()%></td>
						<td><%=user2.getPosition()%></td>
						<td><%=user2.getSalary()%></td>
						<td><%=user2.getBirthday()%></td>
						<td><%=user2.getWorkday()%></td>
						<% 
						if(loginUser.equals("admin")&&passwordx.equals("admin")){
					%>
						<td><a href="ModifySearchServlet?usernum=<%=user2.getUsernum()%>">修改</a></td>
						<script type="text/javascript">
							function confirmDel(usernum) {
								if (confirm("确定删除吗？")) {
									window.location.href = 'DeleteServlet?usernum='
											+ usernum;
								}
							}
						</script>
						<td><a onclick='confirmDel(<%=user2.getUsernum()%>)'>删除</a></td>
						
				<%
					} else {
				%>
						<td>
						无法操作 
						</td>
						<td>
						无法操作
						</td>
					<% 
						}
					%>
					</tr>
				</table>
			</div>
  </body>
</html>

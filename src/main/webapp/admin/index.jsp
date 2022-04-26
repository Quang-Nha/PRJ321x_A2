<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/PRJ321x_A2/css/index.css">
</head>
<body>
	<%
  //nếu chư đăng nhập thì không có session 
  //và action != dologin thì chuyển sang trang LoginServlet.java
  String action = (String)session.getAttribute("action");
  if(action == null) {
	  response.sendRedirect(response.encodeUrl(request.getContextPath() + "/login"));
  }else if(!action.equals("dologin")) {
	  response.sendRedirect(response.encodeUrl(request.getContextPath() + "/login"));
  }
  %>

	<!-- account -->
	<div class="account">
		<pre>&#9705;     &#128110;  Welcome <%= session.getAttribute("username") %></pre>

		<!-- đăng xuất chuyển sang trang LogoutServlet.java -->
		<a
			href="<%= response.encodeUrl(request.getContextPath() + "/logout") %>">Logout</a>
	</div>

	<!-- cột trái -->
	<div class="column column_left">
		<h3>2021 TEAM</h3>
		<hr>
		<p>
			<a href="#">DashBoard</a>
		</p>
		<p>
			<a href="#">Staff Manager</a>
		</p>
	</div>

	<!-- cột phải -->
	<div class="column column_right">
		<div class="fakeimg">
			<img src="/PRJ321x_A2/media/background.jpg" alt="background">
		</div>

		<!-- bảng -->
		<table>
			<caption>Members of the team</caption>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>StudenID</th>
				<th>Class</th>
			</tr>
			<tr>
				<td>1</td>
				<td>Member 1</td>
				<td>Member code 1</td>
				<td>Class 1</td>
			</tr>
			<tr>
				<td>2</td>
				<td>Member 2</td>
				<td>Member code 2</td>
				<td>Class 2</td>
			</tr>

			</div>
</body>
</html>
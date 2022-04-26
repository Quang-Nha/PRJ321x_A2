<%@page import="model.CookieUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/login.css">
</head>

<body>
	<%
  //nếu session chưa mất tức chưa logout điều hướng sang trang quản trị
  String action = (String)session.getAttribute("action");
  if(action != null && action.equals("dologin")){
	  response.sendRedirect(response.encodeUrl(request.getContextPath() + "/login"));
  }
  %>
	<% 
  CookieUtils cooUtils = new CookieUtils();
  %>

	<!-- cột trái -->
	<div class="column column_left">
		<form action="/PRJ321x_A2/login" method="post" class="content_left">
			<h1>Sing in</h1>
			<!-- tạo tham số ẩn để xác nhận đã gửi form -->
			<input type="hidden" name="action" value="dologin">

			<!-- gán usename là cookie gửi lên từ server -->
			<input type="text" id="username" name="username"
				placeholder="Enter Username"
				value="<%= cooUtils.get("username", request) %>" required> <input
				type="password" id="password" name="password"
				placeholder="Enter password" required>

			<div class="Remember">
				<input type="checkbox" id="remember" name="remember" value="1">
				<label for="remember">Remember me</label>
			</div>

			<p class="forgot">
				<a href="#">Forgot your password?</a>
			</p>

			<input type="submit" value="LOGIN">

			<!-- hiển thị thông báo lỗi đăng nhập -->
			<% 
      //nếu session error = null thì cho bằng rỗng
      if(session.getAttribute("error") == null) {
    	  session.setAttribute("error", "");
      }
    	  
      %>
			<div style="color: #ff0000"><%= session.getAttribute("error") %></div>

		</form>

	</div>

	<!-- cột phải -->
	<div class="column column_right">
		<div class="content_right">
			<h1>Welcome Back!</h1>
			<p>To keep connected with us please login with your personal info
			</p>
		</div>
	</div>

</body>

</html>
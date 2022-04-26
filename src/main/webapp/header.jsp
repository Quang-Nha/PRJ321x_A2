<!-- header -->
<div class="header">

	<div class="left_header">
		<h2>PRJ321x</h2>
		<p>Welcome to my Wedsite</p>
	</div>

	<form class="right_header">
		<a href="#">Categories &#9660;</a> <input type="text" name="search"
			placeholder="What are you looking for?">
	</form>

</div>

<!-- thanh điều hướng -->
<ul class="topnav">
	<li><a href="#">Home</a></li>

	<!-- trình đơn thả xuống -->
	<li class="dropdown"><a class="dropbtn">Products</a>
		<div class="dropdown-content">
			<a href="#">android</a> <a href="#">iphone</a> <a href="#">other</a>
		</div></li>

	<li><a href="#">About us</a></li>

	<!-- link sang trang LoginServlet.java -->
	<li class="last"><a
		href="<%= response.encodeUrl(request.getContextPath() + "/login") %>">Login</a>
	</li>
</ul>
package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.CookieUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("deprecation")

	// hàm xử lý với các request không gửi từ form
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html:;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();

		// lấy tham số "action"
		String action = (String) session.getAttribute("action");

		// nếu "action" không null và = dologin tức là session chưa mất
		// thì chuyển sang trang quản trị
		if (action != null && action.equals("dologin")) {
			response.sendRedirect(response.encodeUrl(request.getContextPath() + "/admin/index.jsp"));
		} else {
			// không thì tạo session báo lỗi, lấy các cookie và quay về trang login
			session.setAttribute("error", "");

			CookieUtils cookieUtils = new CookieUtils();

			// lấy giá trị cookie với tham số username
			// nếu chưa gửi form value sẽ = ""
			String value = cookieUtils.get("username", request);
			// gửi cookie lên client
			cookieUtils.add("username", value, 10, response);

			response.sendRedirect(response.encodeUrl(request.getContextPath() + "/login.jsp"));
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	//hàm xử lý request từ form
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html:;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			//lấy các tham số từ form
			String action = request.getParameter("action");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String remember = request.getParameter("remember");

			//nếu các param quan trọng null quay lại trang login
			if (action == null || username == null || password == null) {
				response.sendRedirect(response.encodeUrl(request.getContextPath() + "/login.jsp"));
			}

			//thêm vào đối tượng Account với các tham số trên
			Account account = new Account();
			account.setUsername(username);
			account.setPassword(password);

			//tạo cookie lưu username là rỗng
			Cookie cookieName = new Cookie("username", "");
			cookieName.setMaxAge(600);
			
			//nếu param remember = 1 là yêu cầu lưu username
			//thì gán lại cookie có param username = tham số username từ form gửi
			if (remember != null && remember.equals("1")) {
				cookieName = new Cookie("username", username);
			}
			response.addCookie(cookieName);//gửi cookie

			HttpSession session = request.getSession();
			
			//gọi hàm xác thực username, password trong object account đã được gán
			//nếu hàm return true thì so sánh tiếp không thì tạo session báo lỗi
			//và quay lại trang login
			if (account.validate()) {
				
				//dùng ServletContext lấy các tham số init từ file xml 
				ServletContext context = getServletContext();
				String us = context.getInitParameter("username");
				String pw = context.getInitParameter("password");
				
				//nếu các param từ form và param từ init trùng khớp thì
				//lưu các param cần thiết vào session để xác định trạng thái đăng nhập
				//rồi chuyển sang trang quản trị
				if (account.getUsername().equalsIgnoreCase(us) && account.getPassword().equals(pw)) {
					session.setAttribute("username", username);
					session.setAttribute("action", action);
					response.sendRedirect(response.encodeUrl(request.getContextPath() + "/admin/index.jsp"));
					return;
				} else {
					//nếu không khớp thì tạo session báo lỗi và quay lại trang login
					session.setAttribute("error", "wrong username or password");
					response.sendRedirect(response.encodeUrl(request.getContextPath() + "/login.jsp"));
					return;
				}

			} else {
				session.setAttribute("error", "invalid syntax");
				response.sendRedirect(response.encodeUrl(request.getContextPath() + "/login.jsp"));
				return;
			}

		} catch (NullPointerException e) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} catch (Exception ex) {
			response.getWriter().println(ex);
		}

	}

}

package model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	
	//hàm lấy giá trị cookie nếu có tham số name giống nhau trong mảng các cookie
	//nếu không có tham số này tức không còn cookie hoặc chưa gửi form thì trả về ""
	public static String get(String name, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}

		return "";
	}

	//hàm thêm 1 cookie với tham số, giá trị và thời gian tồn tại
	public static Cookie add(String name, String value, int mins, HttpServletResponse response) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(60 * mins);
		response.addCookie(cookie);

		return cookie;
	}
}

package com.example.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.example.entity.User;

@Component
public class LoginFilter implements Filter {
	final String LOGIN = "/login";
	final String SIGNUP = "/signup";
	final String CSS = ".css";

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		List<String> errorList = new ArrayList<>();
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		HttpServletResponse httpResponse = (HttpServletResponse) res;

		String currentPath = httpRequest.getServletPath();

		// ログインユーザーの情報があるか、あるいは公開されたページへのアクセスであれば許可
		if (isLogined(httpRequest, errorList) ||
				(currentPath.startsWith(LOGIN)
						|| currentPath.startsWith(SIGNUP)
						|| currentPath.endsWith(CSS))) {
			chain.doFilter(req, res);
			return;
		}

		httpRequest.getSession().setAttribute("errorList", errorList);
		httpResponse.sendRedirect("/login");
	}

	private boolean isLogined(HttpServletRequest httpRequest, List<String> errorList) {
		HttpSession session = httpRequest.getSession();
		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			errorList.add("ログインしてください");
			return false;
		}
		return true;

	}
}
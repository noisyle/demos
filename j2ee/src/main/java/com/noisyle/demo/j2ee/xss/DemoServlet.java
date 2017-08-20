package com.noisyle.demo.j2ee.xss;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = -2616778759139448381L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("<html><body>"+request.getParameter("param")+"<br /><button type='button' onclick='window.history.back()'>Back</button></body></html>");
	}
	
}

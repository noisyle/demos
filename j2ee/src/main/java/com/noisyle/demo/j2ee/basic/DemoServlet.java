package com.noisyle.demo.j2ee.basic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = -53556380040847524L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream in = DemoServlet.class.getClassLoader().getResourceAsStream("test.properties");
		Properties p = new Properties();
		p.load(in);
		in.close();
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/html; charset=UTF-8");
		resp.getWriter().write("<!doctype html><html>");
		resp.getWriter().write("getRealPath from Listener: "+DemoListener.getRealPath());
		resp.getWriter().write("<br />");
		resp.getWriter().write("getRealPath from Servlet: "+req.getServletContext().getRealPath("/"));
		resp.getWriter().write("<br />");
		resp.getWriter().write("getResource(): "+DemoServlet.class.getClassLoader().getResource("test.properties").toString());
		resp.getWriter().write("<br />");
		resp.getWriter().write("File Content: "+p.getProperty("text"));
		resp.getWriter().write("</html>");
	}
}

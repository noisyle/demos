package com.noisyle.demo.j2ee.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = -53556380040847524L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream in = TestServlet.class.getClassLoader().getResourceAsStream("test.properties");
		Properties p = new Properties();
		p.load(in);
		in.close();
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/html; charset=UTF-8");
		resp.getWriter().write("<!doctype html><html>");
		resp.getWriter().write("realpath1: "+TestListener.getRealPath());
		resp.getWriter().write("realpath2: "+req.getServletContext().getRealPath("/"));
		resp.getWriter().write("<br />");
		resp.getWriter().write("uri: "+TestServlet.class.getClassLoader().getResource("test.properties").toString());
		resp.getWriter().write("<br />");
		resp.getWriter().write("content: "+p.getProperty("text"));
		resp.getWriter().write("</html>");
	}
}

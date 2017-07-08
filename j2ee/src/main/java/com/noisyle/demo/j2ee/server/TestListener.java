package com.noisyle.demo.j2ee.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TestListener implements ServletContextListener {
	
	private static String realPath = null;

	public static String getRealPath() {
		return realPath;
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		realPath = sce.getServletContext().getRealPath("/");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}

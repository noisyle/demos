package com.noisyle.demo.j2ee.basic;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DemoListener implements ServletContextListener {
	
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

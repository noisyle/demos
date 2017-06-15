package com.noisyle.demo.freemarkerdemo.api;

import java.io.IOException;
import java.io.Writer;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerUtil {
	private static Configuration configuration;
	
	static {
		configuration = new Configuration();
		ClassTemplateLoader loader = new ClassTemplateLoader(FreeMarkerUtil.class, "/ftl");
        configuration.setTemplateLoader(loader);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

	public static void process(String ftl, Object param, Writer out) {
        Template template;
		try {
			template = configuration.getTemplate(ftl);
			template.process(param, out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
	
	public static Configuration getConfiguration() {
		return configuration;
	}

}

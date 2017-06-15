package com.noisyle.demo.freemarkerdemo.api;

import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerUtilTest {

	@Test
	public void test() throws Exception {
		Configuration configuration = new Configuration();
		ClassTemplateLoader loader = new ClassTemplateLoader(FreeMarkerUtil.class, "/ftl");
        configuration.setTemplateLoader(loader);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Template template = configuration.getTemplate("systemStatus.ftl");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "2");
        template.process(map, new OutputStreamWriter(System.out));
	}

}

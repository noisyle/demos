package com.noisyle.demo.freemarker.api;

import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class FreeMarkerUtilTest {

	@Test
	public void testProcess() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "world");
		FreeMarkerUtil.process("hello.ftl", map, new OutputStreamWriter(System.out));
	}

}

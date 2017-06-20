package com.noisyle.demo.restdemo.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class DemoController {
	private Logger logger = LoggerFactory.getLogger(DemoController.class);

	@RequestMapping(value = "/hello", method = RequestMethod.GET, headers = "CustomKey=CustomValue")
	public @ResponseBody Object getWithCustomHeader(HttpServletRequest request) {
		logger.debug(request.getRequestURI());
		Map<String, Object> response = new HashMap<>();
		response.put("code", 0);
		response.put("message", "Hello World!");
		return response;
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "No Custom Header")
	public @ResponseBody Object get(HttpServletRequest request) {
		logger.debug(request.getRequestURI());
		return new HelloResponse(HttpStatus.FORBIDDEN.value(), "No Custom Header");
	}

	@XmlRootElement(name = "HelloResponse")
	public static class HelloResponse {
		// 将HelloResponse定义为内部类时，为了能被jaxb正常序列化，需要定义为静态内部类
		long code;
		String message;

		public HelloResponse() {
			// 为了能被jaxb正常序列化，需要提供无参的构造函数
		}

		public HelloResponse(long code, String message) {
			this.code = code;
			this.message = message;
		}

		public long getCode() {
			return code;
		}

		public void setCode(long code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}

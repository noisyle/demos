package com.noisyle.demo.j2ee.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class DemoTag extends BodyTagSupport {
	private static final long serialVersionUID = -2616778759139448381L;

	private String value;

	@Override
	public int doStartTag() throws JspException {
		JspWriter writer = this.pageContext.getOut();
		try {
			writer.write("<span>Hello, ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	@Override
	public int doEndTag() throws JspException {
		JspWriter writer = this.pageContext.getOut();
		try {
			writer.write(this.bodyContent.getString());
			if (value != null) {
				writer.write(" (value=" + value + ")");
			}
			writer.write("!</span>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}

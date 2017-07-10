package com.noisyle.demo.itextpdf;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.pdf.BaseFont;

public class PDFConverter {
	final private static Logger logger = LoggerFactory.getLogger(PDFConverter.class);
	
	public static void convertFile2PDf(String templatepath, String output) {
		try {
			InputStream in = PDFConverter.class.getClassLoader().getResourceAsStream(templatepath);
			String template = IOUtils.toString(in, Charset.forName("utf-8"));
			in.close();
			logger.debug(template);

			ITextRenderer renderer = new ITextRenderer();
			renderer.getFontResolver().addFont(PDFConverter.class.getResource("/fonts/ARIALUNI.TTF").getPath(),
					BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			renderer.setDocumentFromString(template);
			renderer.layout();
			FileOutputStream out = new FileOutputStream(output);
			renderer.createPDF(out);
			out.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("test start");
		PDFConverter.convertFile2PDf("template1.html", "d:\\test1.pdf");
		System.out.println("test end");
		
	}
}

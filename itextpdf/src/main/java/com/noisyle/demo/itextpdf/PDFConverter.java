package com.noisyle.demo.itextpdf;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class PDFConverter {
	final private static Logger logger = LoggerFactory.getLogger(PDFConverter.class);

	final private static String JSON = "{\"rows\":[{\"balance\":\"900,000.00\",\"ROWNUM1\":1,\"expiryDate\":\"2015-02-16\",\"userId\":74,\"userName\":\"MGMT_VIEW\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":2,\"expiryDate\":\"2015-02-16\",\"userId\":0,\"userName\":\"SYS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":3,\"expiryDate\":\"2015-02-16\",\"userId\":5,\"userName\":\"SYSTEM\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":4,\"expiryDate\":\"2015-02-16\",\"userId\":30,\"userName\":\"DBSNMP\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":5,\"expiryDate\":\"2015-02-16\",\"userId\":72,\"userName\":\"SYSMAN\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":6,\"expiryDate\":\"2015-02-16\",\"userId\":9,\"userName\":\"OUTLN\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":7,\"expiryDate\":\"2015-02-16\",\"userId\":75,\"userName\":\"FLOWS_FILES\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":8,\"expiryDate\":\"2015-02-16\",\"userId\":57,\"userName\":\"MDSYS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":9,\"expiryDate\":\"2015-02-16\",\"userId\":53,\"userName\":\"ORDSYS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":10,\"expiryDate\":\"2015-02-16\",\"userId\":42,\"userName\":\"EXFSYS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":11,\"expiryDate\":\"2015-02-16\",\"userId\":32,\"userName\":\"WMSYS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":12,\"expiryDate\":\"2015-02-16\",\"userId\":31,\"userName\":\"APPQOSSYS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":13,\"expiryDate\":\"2015-02-16\",\"userId\":78,\"userName\":\"APEX_030200\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":14,\"expiryDate\":\"2015-02-16\",\"userId\":83,\"userName\":\"OWBSYS_AUDIT\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":15,\"expiryDate\":\"2015-02-16\",\"userId\":54,\"userName\":\"ORDDATA\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":16,\"expiryDate\":\"2015-02-16\",\"userId\":43,\"userName\":\"CTXSYS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":17,\"expiryDate\":\"2015-02-16\",\"userId\":46,\"userName\":\"ANONYMOUS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":18,\"expiryDate\":\"2015-02-16\",\"userId\":45,\"userName\":\"XDB\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":19,\"expiryDate\":\"2015-02-16\",\"userId\":55,\"userName\":\"ORDPLUGINS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":20,\"expiryDate\":\"2015-02-16\",\"userId\":79,\"userName\":\"OWBSYS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":21,\"expiryDate\":\"2015-02-16\",\"userId\":56,\"userName\":\"SI_INFORMTN_SCHEMA\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":22,\"expiryDate\":\"2015-02-16\",\"userId\":61,\"userName\":\"OLAPSYS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":23,\"expiryDate\":\"2015-02-16\",\"userId\":84,\"userName\":\"SCOTT\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":24,\"expiryDate\":\"2015-02-16\",\"userId\":21,\"userName\":\"ORACLE_OCM\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":25,\"expiryDate\":\"2015-02-16\",\"userId\":2147483638,\"userName\":\"XS$NULL\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":26,\"expiryDate\":\"2015-02-16\",\"userId\":90,\"userName\":\"BI\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":27,\"expiryDate\":\"2015-02-16\",\"userId\":89,\"userName\":\"PM\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":28,\"expiryDate\":\"2015-02-16\",\"userId\":65,\"userName\":\"MDDATA\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":29,\"expiryDate\":\"2015-02-16\",\"userId\":87,\"userName\":\"IX\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":30,\"expiryDate\":\"2015-02-16\",\"userId\":88,\"userName\":\"SH\"}],\"total\":30}";

	private static Configuration cfg;

	static {
		cfg = new Configuration(Configuration.VERSION_2_3_23);
		ClassTemplateLoader loader = new ClassTemplateLoader(PDFConverter.class, "/ftl");
		cfg.setTemplateLoader(loader);
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
	}

	private static String renderHTML(String ftl, Object param) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
        Template template;
		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(out));
			template = cfg.getTemplate(ftl);
			template.process(param, writer);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return out.toString();
	}

	private static void convertHtml2PDF(String html, OutputStream out) throws DocumentException, IOException {
		ITextRenderer renderer = new ITextRenderer();
		renderer.getFontResolver().addFont(PDFConverter.class.getResource("/fonts/ARIALUNI.TTF").getPath(),
				BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		renderer.setDocumentFromString(html);
		renderer.layout();
		renderer.createPDF(out);
	}

	public static void main(String[] args) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		TableData data = mapper.readValue(JSON, TableData.class);
		logger.debug(mapper.writeValueAsString(data));
        
		Date printtime = new Date();
		Map<String, Object> param = new HashMap<>();
		param.put("printtime", printtime);
		param.put("total", data.getTotal());
		param.put("rows", data.getRows());
		param.put("footer", data.getFooter());
		String html = renderHTML("table.ftl", param);
		logger.debug(html);

		OutputStream out = new FileOutputStream("d:\\test"+printtime.getTime()+".pdf");
		try {
			convertHtml2PDF(html, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}

class TableData {
	private int total;
	private List<Map<String, Object>> rows;
	private List<Map<String, Object>> footer;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Map<String, Object>> getRows() {
		return rows;
	}
	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
	}
	public List<Map<String, Object>> getFooter() {
		return footer;
	}
	public void setFooter(List<Map<String, Object>> footer) {
		this.footer = footer;
	}
	
	@Override
	public String toString() {
		return String.format("total:%d rows:%s", total, rows.size());
	}
}

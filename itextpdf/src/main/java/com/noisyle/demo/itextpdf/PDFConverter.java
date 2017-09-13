package com.noisyle.demo.itextpdf;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
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

	private static Configuration cfg;

	static {
		cfg = new Configuration(Configuration.VERSION_2_3_23);
		ClassTemplateLoader loader = new ClassTemplateLoader(PDFConverter.class, "/ftl");
		cfg.setTemplateLoader(loader);
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
	}

	private static void renderHTML(String ftl, Object param, OutputStream out) throws TemplateException, IOException {
		Writer writer = new BufferedWriter(new OutputStreamWriter(out));
		Template template = cfg.getTemplate(ftl);
		template.process(param, writer);
	}

	private static void convertHTML2PDF(String html, OutputStream out) throws DocumentException, IOException {
		ITextRenderer renderer = new ITextRenderer();
		renderer.getFontResolver().addFont(PDFConverter.class.getResource("/fonts/ARIALUNI.TTF").getPath(),
				BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		renderer.setDocumentFromString(html);
		renderer.layout();
		renderer.createPDF(out);
	}
	
	private static List<Map<String, Object>> getFlatTitle(List<List<Map<String, Object>>> titles) {
		// TODO compatible with multi-level headers
		return titles != null && titles.size() > 0 ? titles.get(titles.size() - 1) : null;
	}
	
	private static List<TableData> pagination(TableData tableData, int pageSize) {
		List<TableData> tables = new LinkedList<TableData>();
		TableData tmp = null;
		for (int i = 0; i * pageSize < tableData.getRows().size(); i++) {
			tmp = new TableData();
			tmp.setTotal(tableData.getTotal());
			tmp.setRows(tableData.getRows().subList(i * pageSize,
					(i + 1) * pageSize < tableData.getRows().size() ? (i + 1) * pageSize : tableData.getRows().size()));
			tmp.setFooter(tableData.getFooter());
			tables.add(tmp);
		}
		return tables;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		int pageSize = 10;
		String _table_name = "表格名称";
		String _title = "[[{\"field\":\"userId\",\"sortable\":true,\"width\":\"100\",\"title\":\"用户ID\",\"boxWidth\":92,\"cellClass\":\"datagrid-cell-c1-userId\",\"cellSelector\":\"div.datagrid-cell-c1-userId\"},{\"field\":\"userName\",\"sortable\":true,\"width\":\"300\",\"title\":\"用户名称\",\"boxWidth\":292,\"cellClass\":\"datagrid-cell-c1-userName\",\"cellSelector\":\"div.datagrid-cell-c1-userName\"},{\"field\":\"expiryDate\",\"sortable\":true,\"width\":\"200\",\"title\":\"格式化时间\",\"boxWidth\":192,\"cellClass\":\"datagrid-cell-c1-expiryDate\",\"cellSelector\":\"div.datagrid-cell-c1-expiryDate\"},{\"field\":\"balance\",\"sortable\":true,\"width\":\"200\",\"title\":\"格式化金额\",\"boxWidth\":192,\"cellClass\":\"datagrid-cell-c1-balance\",\"cellSelector\":\"div.datagrid-cell-c1-balance\"}]]";
		String _data = "{\"rows\":[{\"balance\":\"900,000.00\",\"ROWNUM1\":1,\"expiryDate\":\"2015-02-16\",\"userId\":74,\"userName\":\"MGMT_VIEW\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":2,\"expiryDate\":\"2015-02-16\",\"userId\":0,\"userName\":\"SYS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":3,\"expiryDate\":\"2015-02-16\",\"userId\":5,\"userName\":\"SYSTEM\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":4,\"expiryDate\":\"2015-02-16\",\"userId\":30,\"userName\":\"DBSNMP\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":5,\"expiryDate\":\"2015-02-16\",\"userId\":72,\"userName\":\"SYSMAN\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":6,\"expiryDate\":\"2015-02-16\",\"userId\":9,\"userName\":\"OUTLN\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":7,\"expiryDate\":\"2015-02-16\",\"userId\":75,\"userName\":\"FLOWS_FILES\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":8,\"expiryDate\":\"2015-02-16\",\"userId\":57,\"userName\":\"MDSYS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":9,\"expiryDate\":\"2015-02-16\",\"userId\":53,\"userName\":\"ORDSYS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":10,\"expiryDate\":\"2015-02-16\",\"userId\":42,\"userName\":\"EXFSYS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":11,\"expiryDate\":\"2015-02-16\",\"userId\":32,\"userName\":\"WMSYS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":12,\"expiryDate\":\"2015-02-16\",\"userId\":31,\"userName\":\"APPQOSSYS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":13,\"expiryDate\":\"2015-02-16\",\"userId\":78,\"userName\":\"APEX_030200\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":14,\"expiryDate\":\"2015-02-16\",\"userId\":83,\"userName\":\"OWBSYS_AUDIT\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":15,\"expiryDate\":\"2015-02-16\",\"userId\":54,\"userName\":\"ORDDATA\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":16,\"expiryDate\":\"2015-02-16\",\"userId\":43,\"userName\":\"CTXSYS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":17,\"expiryDate\":\"2015-02-16\",\"userId\":46,\"userName\":\"ANONYMOUS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":18,\"expiryDate\":\"2015-02-16\",\"userId\":45,\"userName\":\"XDB\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":19,\"expiryDate\":\"2015-02-16\",\"userId\":55,\"userName\":\"ORDPLUGINS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":20,\"expiryDate\":\"2015-02-16\",\"userId\":79,\"userName\":\"OWBSYS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":21,\"expiryDate\":\"2015-02-16\",\"userId\":56,\"userName\":\"SI_INFORMTN_SCHEMA\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":22,\"expiryDate\":\"2015-02-16\",\"userId\":61,\"userName\":\"OLAPSYS\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":23,\"expiryDate\":\"2015-02-16\",\"userId\":84,\"userName\":\"SCOTT\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":24,\"expiryDate\":\"2015-02-16\",\"userId\":21,\"userName\":\"ORACLE_OCM\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":26,\"expiryDate\":\"2015-02-16\",\"userId\":90,\"userName\":\"BI\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":27,\"expiryDate\":\"2015-02-16\",\"userId\":89,\"userName\":\"PM\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":28,\"expiryDate\":\"2015-02-16\",\"userId\":65,\"userName\":\"MDDATA\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":29,\"expiryDate\":\"2015-02-16\",\"userId\":87,\"userName\":\"IX\"},{\"balance\":\"900,000.00\",\"ROWNUM1\":30,\"expiryDate\":\"2015-02-16\",\"userId\":88,\"userName\":\"SH\"}],\"total\":29}";

		ObjectMapper mapper = new ObjectMapper();
		TableData data = mapper.readValue(_data, TableData.class);
		List<List<Map<String, Object>>> titles = mapper.readValue(_title, List.class);
		logger.debug(mapper.writeValueAsString(data));
		logger.debug(mapper.writeValueAsString(getFlatTitle(titles)));

		Date printtime = new Date();
		Map<String, Object> param = new HashMap<>();
		param.put("table_name", _table_name);
		param.put("print_time", printtime);
		param.put("title", titles);
		param.put("flat_title", getFlatTitle(titles));
		param.put("table_datas", pagination(data, pageSize));
		ByteArrayOutputStream out_template = new ByteArrayOutputStream();
		try {
			renderHTML("table.ftl", param, out_template);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out_template != null) {
				out_template.close();
			}
		}
		String html = out_template.toString();
		logger.debug(html);

		OutputStream out_html = new FileOutputStream("d:\\test" + printtime.getTime() + ".html");
		IOUtils.write(html, out_html, StandardCharsets.UTF_8);
		
		OutputStream out_pdf = new FileOutputStream("d:\\test" + printtime.getTime() + ".pdf");
		try {
			convertHTML2PDF(html, out_pdf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out_html != null) {
				out_html.close();
			}
		}
	}
}

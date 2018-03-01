package com.chinanx.springboot.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/")
    public String hello(Map<String, Object> map) {
        map.put("timestamp", new Date().getTime());
        return "hello";
    }

    @RequestMapping("/docx2pdf")
    public ResponseEntity<Resource> docx2pdf() throws IOException {
        String sourceName = "demo.docx";
        String targetName = "demo_docx.pdf";

        Resource file = new ClassPathResource(sourceName);

        XWPFDocument document = new XWPFDocument(file.getInputStream());
        PdfOptions options = PdfOptions.create();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfConverter.getInstance().convert(document, out, options);

        Resource body = new ByteArrayResource(out.toByteArray());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", targetName);
        headers.setContentLength(body.contentLength());
//        HttpStatus status = HttpStatus.CREATED;
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<org.springframework.core.io.Resource>(body, headers, status);
    }
}

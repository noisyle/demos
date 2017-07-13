package com.noisyle.demo.lucene.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.noisyle.demo.lucene.repository.CnapsRepository;
import com.noisyle.demo.lucene.service.SearchService;

@RestController
public class SearchController {
	@Autowired
	private CnapsRepository cnapsRepository;
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value="/hello")
	public Object hello(@RequestParam(required=false) String q) {
		if(q==null){
			return new HashMap<String, String>(){
				private static final long serialVersionUID = 1L;
				{
					this.put("text", "Hello world!");
				}
			};
		} else {
			return cnapsRepository.findByBankNameIgnoreCaseContaining(q);
		}
	}
	
	@RequestMapping(value="/search")
	public Object search(@RequestParam(required=false) String q) {
		if(q==null){
			return new HashMap<String, String>(){
				private static final long serialVersionUID = 1L;
				{
					this.put("error", "请输入查询条件!");
				}
			};
		} else {
			return searchService.searchCnaps(q);
		}
	}

}

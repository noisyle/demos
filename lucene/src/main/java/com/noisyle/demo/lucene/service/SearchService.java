package com.noisyle.demo.lucene.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;
import com.noisyle.demo.lucene.model.Cnaps;
import com.noisyle.demo.lucene.repository.CnapsRepository;

@Service
public class SearchService implements InitializingBean {
	@Autowired
	private CnapsRepository repository;
	private Directory directory;
	private Analyzer analyzer;
	private final Version version = Version.LUCENE_43;

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public void afterPropertiesSet() throws Exception {
		directory = new RAMDirectory();
		analyzer = new MMSegAnalyzer();
	}
	
	@Scheduled(fixedRate=60000)
	public void update() {
		try {
			updateDirectory();
		} catch (Exception e) {
			logger.error("更新Cnaps索引失败", e);
		}
	}
	
	private void updateDirectory() throws Exception {
		logger.info("Start update index");
		Date start_time = new Date();
		
		List<Cnaps> cnapsList = (List<Cnaps>) repository.findAll();
		Date end_time = new Date();
		logger.debug("Find "+cnapsList.size()+" records in "+(end_time.getTime()-start_time.getTime())+" million seconds.");
		Directory new_directory = new RAMDirectory();
		IndexWriter writer = new IndexWriter(new_directory, new IndexWriterConfig(version, analyzer));
		for (Cnaps cnaps : cnapsList) {
			Document doc = new Document();
			doc.add(new TextField("s_bankname", cnaps.getBankName(), Field.Store.YES));
			doc.add(new StringField("s_bankcode", cnaps.getBankCode(), Field.Store.YES));
			doc.add(new LongField("n_id", cnaps.getId(), Field.Store.YES));
			writer.addDocument(doc);
		}
		writer.close();
		directory = new_directory;
		
		Date end_time1 = new Date();
		logger.info("Index updated in "+(end_time1.getTime()-start_time.getTime())+" million seconds.");
	}

	public List<Cnaps> searchCnaps(String q) {
		List<Cnaps> result = new LinkedList<Cnaps>();
		try {
			IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(directory));
			QueryParser parser = new QueryParser(version, "s_bankname", analyzer);
			Query query = parser.parse(q);

			TopDocs topDocs = searcher.search(query, 20);
			ScoreDoc[] hits = topDocs.scoreDocs;
			logger.debug("Find "+hits.length+" records with keyword【"+q+"】");
			
			for (int i = 0; i < hits.length; i++) {
				Document hitDoc = searcher.doc(hits[i].doc);
				Cnaps cnaps = new Cnaps();
				cnaps.setBankName(hitDoc.get("s_bankname"));
				cnaps.setBankCode(hitDoc.get("s_bankcode"));
				cnaps.setId(Long.valueOf(hitDoc.get("n_id")));
				result.add(cnaps);
			}
		} catch (Exception e) {
			logger.error("查询失败", e);
		}
		return result;
	}
}

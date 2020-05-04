package com.app.processor;

import com.app.connection.ConnectionManager;
import com.app.file.FileWriting;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static com.app.file.FileWriting.createFolderInDisk;
import static com.app.utils.Constants.ABSTRACT_KEY;
import static com.app.utils.Constants.ARTICLE_KEY;
import static com.app.utils.Constants.EMPTY;
import static com.app.utils.Constants.LINK_ATTR;
import static com.app.utils.Constants.LINK_CCS_DEF;
import static com.app.utils.Constants.URL_PATH;

@Service
public class MonthlyDocumentProcessor {

	private Logger logger = LoggerFactory.getLogger(MonthlyDocumentProcessor.class);

	private ConnectionManager connectionProcessor;
	
	public MonthlyDocumentProcessor(ConnectionManager connectionProcessor) {
		this.connectionProcessor = connectionProcessor;
	}
	
	public void process(Document document, String folderToSave){
		logger.info("Processando documentos de: "+folderToSave);

		createFolderInDisk(folderToSave);
		getAllLinksInPage(document).forEach(link ->{
			processLink(link.attr(LINK_ATTR), folderToSave);
		});
    }

    private Stream<Element> getAllLinksInPage(Document document){
		return document.select(LINK_CCS_DEF).stream();
	}

	private void processLink(String url, String folder){
		if(url.contains(ARTICLE_KEY)){
			connectionProcessor.getByUrl(url)
				.ifPresent(document -> {
					new ArticleProcessor(document, folder+"/" ,url.replace(URL_PATH, EMPTY))
							.process();
				});
		}
		if(url.contains(ABSTRACT_KEY)){
			connectionProcessor.getByUrl(url)
				.ifPresent(document -> {
					new AbstractProcessor(document, folder+"/" ,url.replace(URL_PATH, EMPTY))
							.process();
				});
		}
	}

}

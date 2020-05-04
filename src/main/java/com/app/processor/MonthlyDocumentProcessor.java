package com.app.processor;

import com.app.connection.ConnectionManager;
import com.app.file.FileWriting;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.TextNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.app.utils.Constants.EMPTY;
import static com.app.utils.Constants.LINK_ATTR;
import static com.app.utils.Constants.LINK_CCS_DEF;
import static com.app.utils.Constants.URL_PATH;

@Service
public class MonthlyDocumentProcessor {

	private Logger logger = LoggerFactory.getLogger(MonthlyDocumentProcessor.class);

	private ConnectionManager connectionProcessor;

	private Set<String> linksToProcess;
	
	public MonthlyDocumentProcessor(ConnectionManager connectionProcessor) {
		this.connectionProcessor = connectionProcessor;

		linksToProcess = new HashSet<>();
		linksToProcess.add("texto em Português");
		linksToProcess.add(" Inglês");
	}
	
	public void process(Document document, String folder){
		logger.info("Processando Doc"+folder);
		FileWriting.createFolder(folder);
		document.select(LINK_CCS_DEF).stream()
			.forEach(link ->{
				link.childNodes().forEach(child ->{
					if(child instanceof TextNode){
						processTextNode((TextNode) child, folder);
					}
				});
		});
    }

    private void processTextNode(TextNode textNode, String folder){
		if(linksToProcess.contains(textNode.text())) {
			String articleUrl = textNode.parentNode().attr(LINK_ATTR);
			if(articleUrl.contains("sci_arttext") || articleUrl.contains("sci_abstract")){
				connectionProcessor
						.getByUrl(articleUrl)
						.ifPresent(document -> {
							new ArticleProcessor(document, folder+"/" ,articleUrl.replace(URL_PATH,EMPTY))
									.process();
						});
			}
		}
	}


	
}

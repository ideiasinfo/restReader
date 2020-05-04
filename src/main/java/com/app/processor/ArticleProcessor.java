package com.app.processor;

import com.app.file.FileWriting;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import java.util.Optional;
import java.util.function.Function;

import static com.app.utils.Constants.BACK_MAP;
import static com.app.utils.Constants.BODY_MAP;
import static com.app.utils.Constants.EMPTY;
import static com.app.utils.Constants.FRONT_MAP;
import static com.app.utils.Constants.LINE_SEPARATOR;
import static com.app.utils.Constants.RESOURCES_PATH;
import static com.app.utils.Constants.TXT_EXTENSION;

public class ArticleProcessor {

    public static final String ARTICLE_FRONT_BR = "article-front";
    public static final String ARTICLE_BODY_BR = "article-body";
    public static final String ARTICLE_BACK_BR = "article-back";

    public static final String ARTICLE_FRONT_US = "s1-front";
    public static final String ARTICLE_BODY_US = "s1-body";
    public static final String ARTICLE_BACK_US = "s1-back";

    public static final String EDITORIAL_FRONT_BR = "S01-front";
    public static final String EDITORIAL_BODY_BR = "S01-body";
    public static final String EDITORIAL_BACK_BR = "S01-back";

    private Document document;
    private String name;
    private String folder;

    public ArticleProcessor(Document document, String folder, String name){
        this.document = document;
        this.name = name;
        this.folder = folder;
    }

    public void process(){
        StringBuffer stringBuffer = new StringBuffer();

        NodeProcessor frontProcessor = new NodeProcessor(FRONT_MAP);
        stringBuffer.append(processIfExistElementId(ARTICLE_FRONT_BR, frontProcessor));
        stringBuffer.append(processIfExistElementId(ARTICLE_FRONT_US, frontProcessor));
        stringBuffer.append(processIfExistElementId(EDITORIAL_FRONT_BR, frontProcessor));
        stringBuffer.append(LINE_SEPARATOR);

        NodeProcessor bodyProcessor = new NodeProcessor(BODY_MAP);
        stringBuffer.append(processIfExistElementId(ARTICLE_BODY_BR, bodyProcessor));
        stringBuffer.append(processIfExistElementId(ARTICLE_BODY_US, bodyProcessor));
        stringBuffer.append(processIfExistElementId(EDITORIAL_BODY_BR, bodyProcessor));
        stringBuffer.append(LINE_SEPARATOR);

        NodeProcessor backProcessor = new NodeProcessor(BACK_MAP);
        stringBuffer.append(processIfExistElementId(ARTICLE_BACK_BR,
                node -> {
                    if(!node.childNodes().isEmpty()) return backProcessor.processRootNode(node.childNode(0));
                    else return EMPTY;
                }));
        stringBuffer.append(processIfExistElementId(ARTICLE_BACK_US,
                node -> {
                    if(!node.childNodes().isEmpty()) return backProcessor.processRootNode(node.childNode(0));
                    else return EMPTY;
                }));
        stringBuffer.append(processIfExistElementId(EDITORIAL_BACK_BR,
                node -> {
                    if(!node.childNodes().isEmpty()) return backProcessor.processRootNode(node.childNode(0));
                    else return EMPTY;
                }));

        String fileName = RESOURCES_PATH+folder+name+TXT_EXTENSION;
        FileWriting.writeUsingFileWriter(fileName, stringBuffer.toString());
    }

    private String processIfExistElementId(String elementId, NodeProcessor processor){
        return processIfExistElementId(elementId, node -> processor.processRootNode(node));
    }

    private String processIfExistElementId(String elementId, Function<Node,String> function){
        return getNodeByElementId(elementId)
                .map(function)
                .orElse("");
    }

    private Optional<Node> getNodeByElementId(String elementId){
        return Optional.ofNullable(document.getElementById(elementId));
    }

}

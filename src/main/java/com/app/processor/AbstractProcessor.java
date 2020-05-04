package com.app.processor;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import java.util.Optional;

import static com.app.file.FileWriting.writeStringIntoText;
import static com.app.utils.Constants.BODY_MAP;
import static com.app.utils.Constants.RESOURCES_PATH;
import static com.app.utils.Constants.TXT_EXTENSION;

public class AbstractProcessor {

    public static final String CONTAINER_TAG = "container";

    public static final String CONTENT_TAG = "content";

    public static final String INDEX_PT = "index,pt";
    public static final String INDEX_EN = "index,en";
    public static final String INDEX_ES = "index,es";

    private Document document;
    private String name;
    private String folder;

    public AbstractProcessor(Document document, String folder, String name){
        this.document = document;
        this.name = name;
        this.folder = folder;
    }

    public void process(){
        StringBuffer stringBuffer = new StringBuffer();

        getNodeByClass(document.body(), CONTAINER_TAG).ifPresent(containerNode ->{
            getNodeByClass(containerNode, CONTENT_TAG).ifPresent(contetNode -> {
                getNodeByClass(contetNode, INDEX_PT).ifPresent(indexNode ->{
                    stringBuffer.append(new NodeProcessor(BODY_MAP).processRootNode(indexNode));
                });
                getNodeByClass(contetNode, INDEX_EN).ifPresent(indexNode ->{
                    stringBuffer.append(new NodeProcessor(BODY_MAP).processRootNode(indexNode));
                });
                getNodeByClass(contetNode, INDEX_ES).ifPresent(indexNode ->{
                    stringBuffer.append(new NodeProcessor(BODY_MAP).processRootNode(indexNode));
                });
            });
        });

        String fileName = RESOURCES_PATH+folder+name+TXT_EXTENSION;
        writeStringIntoText(fileName, stringBuffer.toString());
    }

    private Optional<Node> getNodeByClass(Node rootNode, String className){
        return rootNode.childNodes()
                    .stream()
                    .filter(childNode -> childNode.attr("class").equals(className))
                    .findFirst();
    }

}

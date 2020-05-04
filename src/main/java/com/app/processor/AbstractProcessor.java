package com.app.processor;

import com.app.file.FileWriting;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import java.util.Optional;

import static com.app.utils.Constants.BODY_MAP;
import static com.app.utils.Constants.RESOURCES_PATH;
import static com.app.utils.Constants.TXT_EXTENSION;

public class AbstractProcessor {

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

        Node node = document.body().childNode(0);

        getNodeByClass(node,"content").ifPresent(contetNode -> {
            getNodeByClass(contetNode, "index,en").ifPresent(indexNode ->{
                NodeProcessor bodyProcessor = new NodeProcessor(BODY_MAP);
                stringBuffer.append(bodyProcessor.processRootNode(indexNode));
            });
        });

        String fileName = RESOURCES_PATH+folder+name+TXT_EXTENSION;
        FileWriting.writeUsingFileWriter(fileName, stringBuffer.toString());
    }

    private Optional<Node> getNodeByClass(Node rootNode, String className){
        return rootNode.childNodes()
                    .stream()
                    .filter(childNode -> childNode.attr("class").equals(className))
                    .findFirst();
    }

}

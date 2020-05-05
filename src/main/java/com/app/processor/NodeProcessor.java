package com.app.processor;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.app.utils.Constants.EMPTY;
import static com.app.utils.Constants.LINE_SEPARATOR;

public class NodeProcessor{

    private Map<String,String> tagMapping;

    public NodeProcessor(Map<String,String> tagMapping){
        this.tagMapping = tagMapping;
    }

    public String processRootNode(Node node){
        return processNode(node, child ->{
            String childText = processChild(child);
            if(!childText.equals(EMPTY)){
                return childText+LINE_SEPARATOR;
            }
            return EMPTY;
        });
    }

    private String processNode(Node node){
        return processNode(node, this::processChild);
    }

    private String processNode(Node node, Function<Node,String> runChild){
        return node.childNodes().stream()
                .map(runChild)
                .collect(Collectors.joining());
    }

    private String processChild(Node child){
        if(child instanceof TextNode) return processTextNode((TextNode)child);
        if(child instanceof Element) return processNode(child);
        return EMPTY;
    }

    private String processTextNode(TextNode textNode){
        String param = tagMapping.get(getParentTag(textNode));
        if(param != null) return textNode.text() + param;
        return EMPTY;
    }

    private String getParentTag(Node node){
        if(node.parentNode() instanceof Element){
            Element element = (Element)node.parentNode();
            return element.tag().getName();
        }
        return EMPTY;
    }

}

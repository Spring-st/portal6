package com.aof.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XMLUtil {
    private Document document = null;

    public Document getDocument() {
        return this.document;
    }

    public XMLUtil() {
        this.document = DocumentHelper.createDocument();
    }

    public Element addRoot(String rootName) {
        Element root = this.document.addElement(rootName);
        return root;
    }

    public Element addNode(Element parentElement, String elementName) {
        Element node = parentElement.addElement(elementName);
        return node;
    }

    public void addAttribute(Element thisElement, String attributeName, String attributeValue) {
        thisElement.addAttribute(attributeName, attributeValue);
    }

    public void addAttributes(Element thisElement, String[] attributeNames, String[] attributeValues) {
        for (int i = 0; i < attributeNames.length; i++)
            thisElement.addAttribute(attributeNames[i], attributeValues[i]);
    }

    public void addText(Element thisElement, String text) {
        thisElement.addText(text);
    }

    public String getXML() {
        return this.document.asXML().substring(39);
    }
}

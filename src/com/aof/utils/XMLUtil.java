/*    */ package com.aof.utils;
/*    */ 
/*    */ import org.dom4j.Document;
/*    */ import org.dom4j.DocumentHelper;
/*    */ import org.dom4j.Element;
/*    */ 
/*    */ public class XMLUtil {
/*  8 */   private Document document = null;
/*    */   
/*    */   public Document getDocument() {
/* 11 */     return this.document;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public XMLUtil() {
/* 17 */     this.document = DocumentHelper.createDocument();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Element addRoot(String rootName) {
/* 26 */     Element root = this.document.addElement(rootName);
/* 27 */     return root;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Element addNode(Element parentElement, String elementName) {
/* 37 */     Element node = parentElement.addElement(elementName);
/* 38 */     return node;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addAttribute(Element thisElement, String attributeName, String attributeValue) {
/* 49 */     thisElement.addAttribute(attributeName, attributeValue);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addAttributes(Element thisElement, String[] attributeNames, String[] attributeValues) {
/* 59 */     for (int i = 0; i < attributeNames.length; i++) {
/* 60 */       thisElement.addAttribute(attributeNames[i], attributeValues[i]);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addText(Element thisElement, String text) {
/* 70 */     thisElement.addText(text);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getXML() {
/* 79 */     return this.document.asXML().substring(39);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/utils/XMLUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
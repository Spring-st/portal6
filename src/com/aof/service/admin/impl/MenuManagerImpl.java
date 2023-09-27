/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.MenuDAO;
/*     */ import com.aof.model.admin.Menu;
/*     */ import com.aof.model.admin.query.MenuQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.MenuManager;
/*     */ import com.shcnc.utils.XmlBuilder;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MenuManagerImpl
/*     */   extends BaseManager
/*     */   implements MenuManager
/*     */ {
/*     */   private MenuDAO dao;
/*     */   
/*     */   public void setMenuDAO(MenuDAO dao) {
/*  49 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Menu getMenu(Integer id) {
/*  58 */     return this.dao.getMenu(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Menu saveMenu(Menu menu) {
/*  67 */     return this.dao.saveMenu(menu);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeMenu(Integer id) {
/*  76 */     this.dao.removeMenu(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMenuListCount(Map conditions) {
/*  85 */     return this.dao.getMenuListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getMenuList(Map conditions, int pageNo, int pageSize, MenuQueryOrder order, boolean descend) {
/*  95 */     return this.dao.getMenuList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMenuXml(Locale locale) {
/*     */     DocumentBuilder builder;
/* 107 */     Set<Menu> rootSet = new TreeSet(Menu.MENU_COMPARATOR);
/*     */     
/* 109 */     for (Iterator<Menu> itor = this.dao.getMenuList(null, 0, -1, null, false).iterator(); itor.hasNext(); ) {
/* 110 */       Menu m = itor.next();
/* 111 */       Menu pm = m.getParentMenu();
/* 112 */       if (pm == null) {
/* 113 */         rootSet.add(m); continue;
/*     */       } 
/* 115 */       pm.addChild(m);
/*     */     } 
/*     */ 
/*     */     
/* 119 */     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/*     */     
/*     */     try {
/* 122 */       builder = factory.newDocumentBuilder();
/* 123 */     } catch (ParserConfigurationException e) {
/* 124 */       throw new RuntimeException(e);
/*     */     } 
/* 126 */     Document doc = builder.newDocument();
/*     */     
/* 128 */     Element tree = doc.createElement("tree");
/*     */     
/* 130 */     buildTree(rootSet, doc, tree, locale);
/* 131 */     doc.appendChild(tree);
/* 132 */     XmlBuilder xmlBuilder = new XmlBuilder();
/* 133 */     xmlBuilder.setXmlHeader("");
/* 134 */     return xmlBuilder.printDOMTree(doc);
/*     */   }
/*     */   
/*     */   private void buildTree(Set roots, Document doc, Node parent, Locale locale) {
/* 138 */     for (Iterator<Menu> itor = roots.iterator(); itor.hasNext(); ) {
/* 139 */       Element e = null;
/* 140 */       Menu m = itor.next();
/* 141 */       Integer id = m.getId();
/* 142 */       Set children = m.getChildren();
/* 143 */       if (children == null || children.isEmpty()) {
/* 144 */         e = doc.createElement("leaf");
/*     */       } else {
/* 146 */         e = doc.createElement("branch");
/* 147 */         buildTree(children, doc, e, locale);
/*     */       } 
/* 149 */       e.setAttribute("id", id.toString());
/* 150 */       e.setAttribute("desc", Locale.ENGLISH.equals(locale) ? m.getName() : m.getSecondName());
/* 151 */       parent.appendChild(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/MenuManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
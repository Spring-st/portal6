/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.DepartmentDAO;
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.query.DepartmentQueryCondition;
/*     */ import com.aof.model.admin.query.DepartmentQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.DepartmentManager;
/*     */ import com.shcnc.utils.XmlBuilder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class DepartmentManagerImpl
/*     */   extends BaseManager
/*     */   implements DepartmentManager
/*     */ {
/*     */   private DepartmentDAO dao;
/*     */   
/*     */   public void setDepartmentDAO(DepartmentDAO dao) {
/*  48 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Department getDepartment(Integer id) {
/*  55 */     return this.dao.getDepartment(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Department saveDepartment(Department department) {
/*  62 */     department = this.dao.saveDepartment(department);
/*  63 */     EnabledDisabled enabled = department.getEnabled();
/*     */     
/*  65 */     if (EnabledDisabled.ENABLED.equals(enabled)) {
/*     */ 
/*     */ 
/*     */       
/*  69 */       Department d = department.getParentDepartment();
/*  70 */       if (d != null) {
/*  71 */         d = this.dao.getDepartment(d.getId());
/*  72 */         while (d != null && !EnabledDisabled.ENABLED.equals(d.getEnabled())) {
/*  73 */           d.setEnabled(EnabledDisabled.ENABLED);
/*  74 */           this.dao.saveDepartment(d);
/*  75 */           d = d.getParentDepartment();
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  82 */       Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  83 */       conditions.put(DepartmentQueryCondition.SITE_EQ, department.getSite().getId());
/*  84 */       conditions.put(DepartmentQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/*  85 */       List departmentList = this.dao.getDepartmentList(conditions, 0, -1, null, false);
/*  86 */       HashMap<Object, Object> parentChildrenMap = new HashMap<Object, Object>();
/*  87 */       Iterator<Department> itor = departmentList.iterator();
/*  88 */       while (itor.hasNext()) {
/*  89 */         Department d = itor.next();
/*  90 */         Department pd = d.getParentDepartment();
/*  91 */         if (pd != null) {
/*  92 */           Integer pid = pd.getId();
/*  93 */           List<Department> childList = (List)parentChildrenMap.get(pid);
/*  94 */           if (childList == null) {
/*  95 */             childList = new ArrayList();
/*  96 */             parentChildrenMap.put(pid, childList);
/*     */           } 
/*  98 */           childList.add(d);
/*     */         } 
/*     */       } 
/* 101 */       setChildrenDisabled(department, parentChildrenMap);
/*     */     } 
/*     */     
/* 104 */     return department;
/*     */   }
/*     */   
/*     */   private void setChildrenDisabled(Department parent, Map parentChildrenMap) {
/* 108 */     List<Department> children = (List)parentChildrenMap.get(parent.getId());
/* 109 */     if (children == null)
/* 110 */       return;  for (int i = 0; i < children.size(); i++) {
/* 111 */       Department child = children.get(i);
/* 112 */       if (!EnabledDisabled.DISABLED.equals(child.getEnabled())) {
/* 113 */         child.setEnabled(EnabledDisabled.DISABLED);
/* 114 */         this.dao.saveDepartment(child);
/* 115 */         setChildrenDisabled(child, parentChildrenMap);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeDepartment(Integer id) {
/* 123 */     this.dao.removeDepartment(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDepartmentListCount(Map conditions) {
/* 130 */     return this.dao.getDepartmentListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getDepartmentList(Map conditions, int pageNo, int pageSize, DepartmentQueryOrder order, boolean descend) {
/* 138 */     return this.dao.getDepartmentList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDepartmentXmlBySite(Integer siteId) {
/*     */     DocumentBuilder builder;
/* 145 */     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/*     */     
/*     */     try {
/* 148 */       builder = factory.newDocumentBuilder();
/* 149 */     } catch (ParserConfigurationException e) {
/* 150 */       throw new RuntimeException(e);
/*     */     } 
/* 152 */     Document doc = builder.newDocument();
/*     */     
/* 154 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 155 */     conditions.put(DepartmentQueryCondition.SITE_EQ, siteId);
/* 156 */     List<Department> departmentList = this.dao.getDepartmentList(conditions, 0, -1, null, false);
/* 157 */     List<Department> rootList = new ArrayList();
/* 158 */     HashMap<Object, Object> departmentItems = new HashMap<Object, Object>();
/* 159 */     Iterator<Department> itor = departmentList.iterator();
/* 160 */     while (itor.hasNext()) {
/* 161 */       Department d = itor.next();
/* 162 */       departmentItems.put(d.getId(), d);
/*     */     } 
/* 164 */     itor = departmentList.iterator();
/* 165 */     while (itor.hasNext()) {
/* 166 */       Department d = itor.next();
/* 167 */       d = (Department)departmentItems.get(d.getId());
/* 168 */       Department pd = d.getParentDepartment();
/* 169 */       if (pd == null) {
/* 170 */         rootList.add(d); continue;
/*     */       } 
/* 172 */       pd = (Department)departmentItems.get(pd.getId());
/* 173 */       pd.addChild(d);
/*     */     } 
/*     */     
/* 176 */     Element tree = doc.createElement("tree");
/*     */     
/* 178 */     buildTree(rootList, doc, tree);
/* 179 */     doc.appendChild(tree);
/* 180 */     XmlBuilder xmlBuilder = new XmlBuilder();
/* 181 */     xmlBuilder.setXmlHeader("");
/* 182 */     return xmlBuilder.printDOMTree(doc);
/*     */   }
/*     */   
/*     */   private void buildTree(List<Department> roots, Document doc, Node parent) {
/* 186 */     int len = roots.size();
/* 187 */     for (int i = 0; i < len; i++) {
/* 188 */       Element e = null;
/* 189 */       Department d = roots.get(i);
/* 190 */       Integer id = d.getId();
/* 191 */       List children = d.getChildren();
/* 192 */       if (children == null || children.isEmpty()) {
/* 193 */         e = doc.createElement("leaf");
/*     */       } else {
/* 195 */         e = doc.createElement("branch");
/* 196 */         buildTree(children, doc, e);
/*     */       } 
/* 198 */       e.setAttribute("id", id.toString());
/* 199 */       e.setAttribute("desc", d.getName());
/* 200 */       parent.appendChild(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillDepartment(Site site, boolean onlyEnabled) {
/* 208 */     List<Site> l = new ArrayList();
/* 209 */     l.add(site);
/* 210 */     fillDepartment(l, onlyEnabled);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillDepartment(List siteList, boolean onlyEnabled) {
/* 217 */     this.dao.fillSiteDepartment(siteList, onlyEnabled);
/* 218 */     for (Iterator<Site> iter = siteList.iterator(); iter.hasNext(); ) {
/* 219 */       Site site = iter.next();
/* 220 */       site.setDepartments(buildList(site.getDepartments()));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void buildList(List<Department> resultList, List<Department> rootList, HashMap departmentItems, int indent) {
/* 225 */     if (rootList == null)
/*     */       return; 
/* 227 */     String strIndent = getIndenetString(indent);
/* 228 */     for (int i = 0; i < rootList.size(); i++) {
/* 229 */       Department d = rootList.get(i);
/* 230 */       d.setIndentName(String.valueOf(strIndent) + d.getName());
/* 231 */       resultList.add(d);
/* 232 */       List l = (List)departmentItems.get(d.getId());
/* 233 */       buildList(resultList, l, departmentItems, indent + 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getIndenetString(int indent) {
/* 238 */     StringBuffer space = new StringBuffer();
/* 239 */     while (indent-- > 0)
/* 240 */       space.append("��"); 
/* 241 */     return space.toString();
/*     */   }
/*     */   
/*     */   private List buildList(List departmentList) {
/* 245 */     List<Department> rootList = new ArrayList();
/* 246 */     HashMap<Object, Object> departmentItems = new HashMap<Object, Object>();
/* 247 */     Iterator<Department> itor = departmentList.iterator();
/* 248 */     while (itor.hasNext()) {
/* 249 */       Department d = itor.next();
/* 250 */       Department pd = d.getParentDepartment();
/* 251 */       if (pd == null) {
/* 252 */         rootList.add(d); continue;
/*     */       } 
/* 254 */       List<Department> l = (List)departmentItems.get(pd.getId());
/* 255 */       if (l == null)
/* 256 */         l = new ArrayList(); 
/* 257 */       l.add(d);
/* 258 */       departmentItems.put(pd.getId(), l);
/*     */     } 
/*     */     
/* 261 */     List resultList = new ArrayList();
/* 262 */     buildList(resultList, rootList, departmentItems, 0);
/* 263 */     return resultList;
/*     */   }
/*     */   
/*     */   public List getEnabledDepartmentTreeOfSite(Site site) {
/* 267 */     List<Site> siteList = new ArrayList();
/* 268 */     siteList.add(site);
/* 269 */     fillDepartment(siteList, true);
/* 270 */     return site.getDepartments();
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/DepartmentManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.shcnc.struts.taglib.page;
/*     */ 
/*     */ import com.aof.model.query.AutoArrayList;
/*     */ import com.shcnc.struts.form.ComboBox;
/*     */ import com.shcnc.struts.form.QueryForm;
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.jsp.JspException;
/*     */ import org.apache.commons.beanutils.ConvertUtils;
/*     */ import org.apache.commons.beanutils.DynaBean;
/*     */ import org.apache.commons.beanutils.DynaProperty;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.apache.struts.taglib.html.FormTag;
/*     */ import org.apache.struts.util.RequestUtils;
/*     */ import org.apache.struts.util.ResponseUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BasicFormTag
/*     */   extends FormTag
/*     */ {
/*     */   public int doStartTag() throws JspException {
/*  30 */     lookup();
/*     */     
/*  32 */     StringBuffer results = new StringBuffer();
/*     */     
/*  34 */     results.append(renderFormStartElement());
/*     */     
/*  36 */     ResponseUtils.write(this.pageContext, results.toString());
/*     */     
/*  38 */     this.pageContext.setAttribute("org.apache.struts.taglib.html.FORM", this, 2);
/*     */     
/*  40 */     initFormBean();
/*     */     
/*  42 */     renderHiddenFields();
/*  43 */     renderGotoPage();
/*  44 */     renderExport();
/*  45 */     renderChangeOrder();
/*  46 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderChangeOrder() throws JspException {
/*  52 */     ResponseUtils.write(this.pageContext, "<script>");
/*  53 */     ResponseUtils.write(this.pageContext, "function changeOrder(aOrder,aDesc) {");
/*  54 */     ResponseUtils.write(this.pageContext, "with(document.");
/*  55 */     ResponseUtils.write(this.pageContext, this.beanName);
/*  56 */     ResponseUtils.write(this.pageContext, "_again){");
/*     */     
/*  58 */     ResponseUtils.write(this.pageContext, "pageNo.value=\"0\";");
/*  59 */     ResponseUtils.write(this.pageContext, "count.value=\"\";");
/*     */     
/*  61 */     ResponseUtils.write(this.pageContext, "order.value=aOrder;");
/*  62 */     ResponseUtils.write(this.pageContext, "descend.value=aDesc;");
/*     */     
/*  64 */     ResponseUtils.write(this.pageContext, "window.setTimeout(\"document.");
/*  65 */     ResponseUtils.write(this.pageContext, this.beanName);
/*  66 */     ResponseUtils.write(this.pageContext, "_again.submit();\", 5);");
/*     */     
/*  68 */     ResponseUtils.write(this.pageContext, "}}");
/*  69 */     ResponseUtils.write(this.pageContext, "</script>");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderExport() throws JspException {
/*  75 */     ResponseUtils.write(this.pageContext, "<script>");
/*  76 */     ResponseUtils.write(this.pageContext, "function exportTo(exportType) {");
/*     */     
/*  78 */     ResponseUtils.write(this.pageContext, "document.");
/*  79 */     ResponseUtils.write(this.pageContext, this.beanName);
/*  80 */     ResponseUtils.write(this.pageContext, "_again.exportType.value=exportType;");
/*     */     
/*  82 */     ResponseUtils.write(this.pageContext, "document.");
/*  83 */     ResponseUtils.write(this.pageContext, this.beanName);
/*  84 */     ResponseUtils.write(this.pageContext, "_again.target='export';");
/*     */     
/*  86 */     ResponseUtils.write(this.pageContext, "window.setTimeout(\"document.");
/*  87 */     ResponseUtils.write(this.pageContext, this.beanName);
/*  88 */     ResponseUtils.write(this.pageContext, "_again.submit();\", 5);");
/*     */     
/*  90 */     ResponseUtils.write(this.pageContext, "window.setTimeout(\"");
/*  91 */     ResponseUtils.write(this.pageContext, "document." + this.beanName + "_again.exportType.value='';");
/*  92 */     ResponseUtils.write(this.pageContext, "document." + this.beanName + "_again.target='';");
/*  93 */     ResponseUtils.write(this.pageContext, "\",25);");
/*     */     
/*  95 */     ResponseUtils.write(this.pageContext, "}");
/*  96 */     ResponseUtils.write(this.pageContext, "</script>");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderGotoPage() throws JspException {
/* 102 */     ResponseUtils.write(this.pageContext, "<script>");
/* 103 */     ResponseUtils.write(this.pageContext, "function gotoPage(aPageNo) {");
/* 104 */     ResponseUtils.write(this.pageContext, "document.");
/* 105 */     ResponseUtils.write(this.pageContext, this.beanName);
/* 106 */     ResponseUtils.write(this.pageContext, "_again.pageNo.value=\"\"+aPageNo;");
/* 107 */     ResponseUtils.write(this.pageContext, "window.setTimeout(\"document.");
/* 108 */     ResponseUtils.write(this.pageContext, this.beanName);
/* 109 */     ResponseUtils.write(this.pageContext, "_again.submit();\", 5);");
/* 110 */     ResponseUtils.write(this.pageContext, "}");
/* 111 */     ResponseUtils.write(this.pageContext, "</script>");
/*     */   }
/*     */   
/*     */   protected ArrayList keysToSkip() {
/* 115 */     ArrayList<String> keysToSkip = new ArrayList();
/* 116 */     keysToSkip.add("class");
/* 117 */     keysToSkip.add("servletWrapper");
/* 118 */     keysToSkip.add("multipartRequestHandler");
/* 119 */     keysToSkip.add("validatorResults");
/* 120 */     keysToSkip.add("bean");
/* 121 */     keysToSkip.add("");
/* 122 */     return keysToSkip;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderHiddenFields() throws JspException {
/* 128 */     QueryForm form = (QueryForm)this.pageContext.getAttribute("org.apache.struts.taglib.html.BEAN", 2);
/*     */     try {
/* 130 */       Map<String, Object> map = new HashMap();
/*     */       
/* 132 */       if (form instanceof DynaBean) {
/*     */         
/* 134 */         DynaBean db = (DynaBean)form;
/* 135 */         DynaProperty[] properties = db.getDynaClass().getDynaProperties();
/* 136 */         for (int i = 0; i < properties.length; i++) {
/* 137 */           String name = properties[i].getName();
/* 138 */           Object value = PropertyUtils.getProperty(form, name);
/* 139 */           if (value != null) {
/* 140 */             map.put(name, ConvertUtils.convert(value));
/*     */           }
/*     */         } 
/*     */       } else {
/*     */         
/* 145 */         PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(form);
/* 146 */         for (int i = 0; i < descriptors.length; i++) {
/* 147 */           String name = descriptors[i].getName();
/* 148 */           if (descriptors[i].getReadMethod() != null && descriptors[i].getWriteMethod() != null) {
/* 149 */             Object value = PropertyUtils.getProperty(form, name);
/* 150 */             if (value != null) {
/* 151 */               if (value instanceof ComboBox) {
/* 152 */                 value = ((ComboBox)value).getValue();
/* 153 */                 if (value != null) {
/* 154 */                   name = String.valueOf(name) + ".value";
/*     */                 }
/*     */               }
/* 157 */               else if (descriptors[i].getPropertyType().getName().equals("com.aof.model.query.AutoArrayList")) {
/* 158 */                 AutoArrayList list = (AutoArrayList)value;
/* 159 */                 for (int j = 0; j < list.size(); j++) {
/* 160 */                   map.put(String.valueOf(name) + "[" + j + "]", list.get(j));
/*     */                 }
/* 162 */               } else if (descriptors[i].getPropertyType().getName().equals("[Ljava.lang.String;")) {
/* 163 */                 System.out.println(String.valueOf(descriptors[i].getName()) + "|" + descriptors[i].getPropertyType().getName());
/*     */               } else {
/* 165 */                 map.put(name, ConvertUtils.convert(value));
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 172 */       processSkip(map);
/* 173 */       Iterator<String> itor = map.keySet().iterator();
/* 174 */       while (itor.hasNext())
/*     */       {
/* 176 */         String key = itor.next();
/* 177 */         Object value = map.get(key);
/* 178 */         ResponseUtils.write(this.pageContext, "<input type=\"hidden\" name=\"");
/* 179 */         ResponseUtils.write(this.pageContext, key);
/* 180 */         ResponseUtils.write(this.pageContext, "\" value=\"");
/* 181 */         ResponseUtils.write(this.pageContext, (value == null) ? "" : value.toString());
/* 182 */         ResponseUtils.write(this.pageContext, "\"/>");
/*     */       }
/*     */     
/* 185 */     } catch (IllegalAccessException e) {
/* 186 */       throw new JspException(
/* 187 */           "error when describing " + this.beanName + ":" + e.toString());
/* 188 */     } catch (InvocationTargetException e) {
/* 189 */       throw new JspException(
/* 190 */           "error when describing " + this.beanName + ":" + e.toString());
/* 191 */     } catch (NoSuchMethodException e) {
/* 192 */       throw new JspException(
/* 193 */           "error when describing " + this.beanName + ":" + e.toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void processSkip(Map map) {
/* 199 */     List keysToSkip = keysToSkip();
/* 200 */     Iterator itor = keysToSkip.iterator();
/* 201 */     while (itor.hasNext()) {
/*     */       
/* 203 */       Object key = itor.next();
/* 204 */       map.remove(key);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected String renderFormStartElement() {
/* 210 */     HttpServletResponse response = 
/* 211 */       (HttpServletResponse)this.pageContext.getResponse();
/*     */     
/* 213 */     StringBuffer results = new StringBuffer("<form");
/* 214 */     results.append(" name=\"");
/* 215 */     results.append(this.beanName);
/* 216 */     results.append("_again\"");
/* 217 */     results.append(" method=\"");
/* 218 */     results.append((this.method == null) ? "post" : this.method);
/* 219 */     results.append("\" action=\"");
/* 220 */     results.append(
/* 221 */         response.encodeURL(
/* 222 */           RequestUtils.getActionMappingURL(this.action, this.pageContext)));
/*     */     
/* 224 */     results.append("\"");
/*     */     
/* 226 */     if (this.styleClass != null) {
/* 227 */       results.append(" class=\"");
/* 228 */       results.append(this.styleClass);
/* 229 */       results.append("\"");
/*     */     } 
/* 231 */     if (this.enctype != null) {
/* 232 */       results.append(" enctype=\"");
/* 233 */       results.append(this.enctype);
/* 234 */       results.append("\"");
/*     */     } 
/* 236 */     if (this.onreset != null) {
/* 237 */       results.append(" onreset=\"");
/* 238 */       results.append(this.onreset);
/* 239 */       results.append("\"");
/*     */     } 
/* 241 */     if (this.onsubmit != null) {
/* 242 */       results.append(" onsubmit=\"");
/* 243 */       results.append(this.onsubmit);
/* 244 */       results.append("\"");
/*     */     } 
/* 246 */     if (this.style != null) {
/* 247 */       results.append(" style=\"");
/* 248 */       results.append(this.style);
/* 249 */       results.append("\"");
/*     */     } 
/* 251 */     if (this.styleId != null) {
/* 252 */       results.append(" id=\"");
/* 253 */       results.append(this.styleId);
/* 254 */       results.append("\"");
/*     */     } 
/* 256 */     if (this.target != null) {
/* 257 */       results.append(" target=\"");
/* 258 */       results.append(this.target);
/* 259 */       results.append("\"");
/*     */     } 
/* 261 */     results.append(">");
/* 262 */     return results.toString();
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/shcnc/struts/taglib/page/BasicFormTag.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
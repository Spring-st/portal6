/*     */ package com.shcnc.struts.taglib.page;
/*     */ 
/*     */ import com.shcnc.struts.form.QueryForm;
/*     */ import java.io.IOException;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.servlet.jsp.JspException;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.apache.struts.util.RequestUtils;
/*     */ 
/*     */ public class SelectPageTag
/*     */   extends TagSupport
/*     */ {
/*  16 */   private String format = null;
/*     */   
/*  18 */   private String style = null;
/*     */   
/*  20 */   private String styleClass = null;
/*     */   
/*     */   private boolean resource = false;
/*     */   
/*     */   private boolean useCombo = true;
/*     */   
/*  26 */   private int useComboLimit = 0;
/*     */ 
/*     */   
/*     */   public String getFormat() {
/*  30 */     return this.format;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFormat(String format) {
/*  35 */     this.format = format;
/*     */   }
/*     */   
/*     */   public int doStartTag() throws JspException {
/*     */     try {
/*  40 */       QueryForm form = (QueryForm)this.pageContext.getAttribute("org.apache.struts.taglib.html.BEAN", 2);
/*  41 */       int pageCount = form.getPageCount();
/*  42 */       int pageNo = Integer.parseInt(form.getPageNo());
/*  43 */       PageUtils.checkPageNo(pageNo, pageCount);
/*  44 */       renderSelectElement(this.pageContext.getOut(), form);
/*  45 */     } catch (IOException ioe) {
/*  46 */       throw new JspTagException("Error:IOException while writing to the user");
/*     */     } 
/*  48 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderSelectElement(JspWriter out, QueryForm form) throws IOException {
/*  53 */     int pageCount = form.getPageCount();
/*  54 */     int pageNo = form.getPageNoAsInt();
/*  55 */     int pageSize = form.getPageSizeAsInt();
/*  56 */     if (this.useCombo && (this.useComboLimit == 0 || pageCount <= this.useComboLimit)) {
/*  57 */       out.print("<select");
/*     */       
/*  59 */       out.print(" name=\"");
/*  60 */       out.print("comboPage");
/*  61 */       out.print("\" ");
/*  62 */       if (this.style != null) {
/*  63 */         out.print("style=\"");
/*  64 */         out.print(this.style);
/*  65 */         out.print("\" ");
/*     */       } 
/*  67 */       if (this.styleClass != null) {
/*  68 */         out.print("class=\"");
/*  69 */         out.print(this.styleClass);
/*  70 */         out.print("\" ");
/*     */       } 
/*     */       
/*  73 */       out.print(" onchange=\"setPage(this.options[this.value].value)\"");
/*     */       
/*  75 */       out.print(">");
/*     */       
/*  77 */       Integer[] pages = { Integer.valueOf(15), Integer.valueOf(50), Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(500), Integer.valueOf(1000) }; byte b; int i; Integer[] arrayOfInteger1;
/*  78 */       for (i = (arrayOfInteger1 = pages).length, b = 0; b < i; ) { Integer integer = arrayOfInteger1[b];
/*  79 */         out.print("<option value=\"");
/*  80 */         out.print(integer);
/*  81 */         out.print("\" ");
/*  82 */         if (pageSize == integer.intValue()) {
/*  83 */           out.print("selected ");
/*     */         }
/*     */         
/*  86 */         out.print(" >");
/*  87 */         out.print(integer);
/*  88 */         out.print("</option>"); b++; }
/*     */       
/*  90 */       out.print("<option value=\"");
/*  91 */       out.print(-1);
/*  92 */       out.print("\" ");
/*  93 */       if (pageSize == -1) {
/*  94 */         out.print("selected ");
/*     */       }
/*  96 */       out.print(" >");
/*  97 */       out.print("ALL");
/*  98 */       out.print("</option>");
/*     */ 
/*     */       
/* 101 */       out.print("</select>");
/*     */     } else {
/* 103 */       int rangeStart = 0;
/* 104 */       int rangeEnd = 3;
/* 105 */       int index = outputRange(out, rangeStart, rangeEnd, pageNo, pageCount);
/* 106 */       if (index >= pageCount) {
/*     */         return;
/*     */       }
/* 109 */       if (pageNo - 1 > index) {
/* 110 */         out.print(". . . ");
/* 111 */         index = outputRange(out, pageNo - 1, pageNo + 2, pageNo, pageCount);
/*     */       } else {
/* 113 */         index = outputRange(out, index, min(pageNo + 2, index + 3), pageNo, pageCount);
/*     */       } 
/* 115 */       if (pageCount - 3 > index) {
/* 116 */         out.print(". . . ");
/* 117 */         index = outputRange(out, pageCount - 3, pageCount, pageNo, pageCount);
/*     */       } else {
/* 119 */         index = outputRange(out, index, pageCount, pageNo, pageCount);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private int min(int v1, int v2) {
/* 125 */     return (v1 < v2) ? v1 : v2;
/*     */   }
/*     */ 
/*     */   
/*     */   private int outputRange(JspWriter out, int rangeStart, int rangeEnd, int currentPageNo, int pageCount) throws IOException {
/* 130 */     for (int index = rangeStart; index < rangeEnd && index < pageCount; index++) {
/* 131 */       outputLink(out, index, currentPageNo);
/* 132 */       out.print(" ");
/*     */     } 
/* 134 */     return 1;
/*     */   }
/*     */   
/*     */   private void outputLink(JspWriter out, int pageNo, int currentPageNo) throws IOException {
/* 138 */     if (pageNo == currentPageNo) {
/* 139 */       out.print(pageNo + 1);
/*     */     } else {
/* 141 */       out.print("<a href='javascript:gotoPage(");
/* 142 */       out.print(pageNo);
/* 143 */       out.print(")'>");
/* 144 */       out.print(pageNo + 1);
/* 145 */       out.print("</a>");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String format(int i) {
/* 151 */     if (this.format == null || this.format.trim().equals("")) {
/* 152 */       return String.valueOf(i);
/*     */     }
/* 154 */     String localFormat = null;
/* 155 */     if (this.resource) {
/*     */       
/*     */       try {
/* 158 */         localFormat = RequestUtils.message(this.pageContext, "org.apache.struts.action.MESSAGE", "org.apache.struts.action.LOCALE", this.format);
/* 159 */       } catch (JspException e) {
/* 160 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/* 164 */       localFormat = this.format;
/*     */     } 
/*     */     
/* 167 */     Pattern pattern = Pattern.compile("%s");
/* 168 */     Matcher matcher = pattern.matcher(localFormat);
/* 169 */     String retVal = matcher.replaceAll(String.valueOf(i));
/*     */     
/* 171 */     String s = null;
/*     */     
/* 173 */     int i_100 = i - i / 100 * 100;
/* 174 */     if (i_100 == 11 || i_100 == 12 || i_100 == 13)
/* 175 */     { s = "th"; }
/*     */     else
/* 177 */     { int i_10 = i_100 - i_100 / 10 * 10;
/* 178 */       switch (i_10)
/*     */       { case 1:
/* 180 */           s = "st";
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
/* 192 */           pattern = Pattern.compile("%t");
/* 193 */           matcher = pattern.matcher(retVal);
/* 194 */           return matcher.replaceAll(String.valueOf(String.valueOf(i)) + s);case 2: s = "nd"; pattern = Pattern.compile("%t"); matcher = pattern.matcher(retVal); return matcher.replaceAll(String.valueOf(String.valueOf(i)) + s);case 3: s = "rd"; pattern = Pattern.compile("%t"); matcher = pattern.matcher(retVal); return matcher.replaceAll(String.valueOf(String.valueOf(i)) + s); }  s = "th"; }  pattern = Pattern.compile("%t"); matcher = pattern.matcher(retVal); return matcher.replaceAll(String.valueOf(String.valueOf(i)) + s);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStyle() {
/* 199 */     return this.style;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStyle(String style) {
/* 204 */     this.style = style;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStyleClass() {
/* 209 */     return this.styleClass;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStyleClass(String styleClass) {
/* 214 */     this.styleClass = styleClass;
/*     */   }
/*     */   
/*     */   public void release() {
/* 218 */     this.format = null;
/* 219 */     this.style = null;
/* 220 */     this.styleClass = null;
/* 221 */     this.resource = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isResource() {
/* 226 */     return this.resource;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setResource(boolean resource) {
/* 231 */     this.resource = resource;
/*     */   }
/*     */   
/*     */   public boolean isUseCombo() {
/* 235 */     return this.useCombo;
/*     */   }
/*     */   
/*     */   public void setUseCombo(boolean useCombo) {
/* 239 */     this.useCombo = useCombo;
/*     */   }
/*     */   
/*     */   public int getUseComboLimit() {
/* 243 */     return this.useComboLimit;
/*     */   }
/*     */   
/*     */   public void setUseComboLimit(int useComboLimit) {
/* 247 */     this.useComboLimit = useComboLimit;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/shcnc/struts/taglib/page/SelectPageTag.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
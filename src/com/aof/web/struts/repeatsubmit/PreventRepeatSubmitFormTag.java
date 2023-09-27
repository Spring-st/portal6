/*    */ package com.aof.web.struts.repeatsubmit;
/*    */ 
/*    */ import javax.servlet.jsp.JspException;
/*    */ import org.apache.struts.taglib.html.FormTag;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PreventRepeatSubmitFormTag
/*    */   extends FormTag
/*    */ {
/*    */   protected void lookup() throws JspException {
/* 18 */     super.lookup();
/* 19 */     if (this.mapping instanceof com.aof.web.struts.action.SecureActionMapping)
/*    */     {
/* 21 */       if (this.mapping.getScope().equals("session")) {
/*    */         
/* 23 */         this.beanName = this.mapping.getName();
/* 24 */         this.beanScope = "request";
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected String renderToken() {
/* 32 */     StringBuffer results = new StringBuffer();
/*    */     
/* 34 */     String token = (String)this.pageContext.getRequest().getAttribute("org.apache.struts.action.TOKEN");
/*    */     
/* 36 */     if (token != null) {
/* 37 */       results.append("<div><input type=\"hidden\" name=\"");
/* 38 */       results.append("org.apache.struts.taglib.html.TOKEN");
/* 39 */       results.append("\" value=\"");
/* 40 */       results.append(token);
/* 41 */       results.append("\" />");
/* 42 */       results.append("</div>");
/*    */     } 
/*    */     
/* 45 */     return results.toString();
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/repeatsubmit/PreventRepeatSubmitFormTag.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
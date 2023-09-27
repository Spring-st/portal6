/*    */ package com.aof.web.struts.form;
/*    */ 
/*    */ import com.aof.model.query.AutoArrayList;
/*    */ import com.shcnc.struts.form.BaseQueryForm;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.struts.action.ActionMapping;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BaseSessionQueryForm
/*    */   extends BaseQueryForm
/*    */ {
/*    */   public String getValidationKey(ActionMapping mapping, HttpServletRequest request) {
/* 15 */     return mapping.getName();
/*    */   }
/*    */   
/*    */   public void reset(ActionMapping mapping, HttpServletRequest request) {
/* 19 */     if (!(mapping instanceof com.aof.web.struts.action.SecureActionMapping)) throw new RuntimeException("mapping is not org.apache.struts.action.ActionMapping"); 
/* 20 */     if (!mapping.getScope().equals("session")) throw new RuntimeException("scope is not session"); 
/* 21 */     request.setAttribute(mapping.getName(), this);
/* 22 */     String pageNo = getPageNo();
/* 23 */     boolean desc = isDescend();
/* 24 */     super.reset(mapping, request);
/* 25 */     setPageNo(pageNo);
/* 26 */     setDescend(desc);
/* 27 */     setExportType("");
/*    */   }
/*    */   
/* 30 */   private AutoArrayList field = new AutoArrayList(String.class);
/* 31 */   private AutoArrayList fieldCondition = new AutoArrayList(String.class);
/* 32 */   private AutoArrayList fieldValue = new AutoArrayList(String.class);
/*    */   public AutoArrayList getField() {
/* 34 */     return this.field;
/*    */   }
/*    */   
/*    */   public void setField(AutoArrayList field) {
/* 38 */     this.field = field;
/*    */   }
/*    */ 
/*    */   
/*    */   public AutoArrayList getFieldCondition() {
/* 43 */     return this.fieldCondition;
/*    */   }
/*    */   
/*    */   public void setFieldCondition(AutoArrayList fieldCondition) {
/* 47 */     this.fieldCondition = fieldCondition;
/*    */   }
/*    */   
/*    */   public AutoArrayList getFieldValue() {
/* 51 */     return this.fieldValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFieldValue(AutoArrayList fieldValue) {
/* 56 */     this.fieldValue = fieldValue;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/BaseSessionQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
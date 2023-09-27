/*    */ package com.aof.web.struts.form;
/*    */ 
/*    */ import com.aof.model.query.AutoArrayList;
/*    */ import com.shcnc.struts.form.BeanQueryForm;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.struts.action.ActionMapping;
/*    */ 
/*    */ 
/*    */ public class BeanSessionQueryForm
/*    */   extends BeanQueryForm
/*    */ {
/*    */   public String getValidationKey(ActionMapping mapping, HttpServletRequest request) {
/* 13 */     return mapping.getName();
/*    */   }
/*    */   
/*    */   public void reset(ActionMapping mapping, HttpServletRequest request) {
/* 17 */     if (!(mapping instanceof com.aof.web.struts.action.SecureActionMapping)) throw new RuntimeException("mapping is not org.apache.struts.action.ActionMapping"); 
/* 18 */     if (!mapping.getScope().equals("session")) throw new RuntimeException("scope is not session"); 
/* 19 */     request.setAttribute(mapping.getName(), this);
/* 20 */     String pageNo = getPageNo();
/* 21 */     boolean desc = isDescend();
/* 22 */     super.reset(mapping, request);
/* 23 */     setPageNo(pageNo);
/* 24 */     setDescend(desc);
/* 25 */     setExportType("");
/* 26 */     System.out.println(request.getParameter("field"));
/*    */     
/* 28 */     System.out.println(request.getParameter("fieldCondition"));
/* 29 */     System.out.println(request.getParameter("fieldValue"));
/*    */   }
/*    */   
/* 32 */   private AutoArrayList field = new AutoArrayList(String.class);
/* 33 */   private AutoArrayList fieldCondition = new AutoArrayList(String.class);
/* 34 */   private AutoArrayList fieldValue = new AutoArrayList(String.class);
/*    */   public AutoArrayList getField() {
/* 36 */     return this.field;
/*    */   }
/*    */   
/*    */   public void setField(AutoArrayList field) {
/* 40 */     this.field = field;
/*    */   }
/*    */   
/*    */   public AutoArrayList getFieldCondition() {
/* 44 */     return this.fieldCondition;
/*    */   }
/*    */   
/*    */   public void setFieldCondition(AutoArrayList fieldCondition) {
/* 48 */     this.fieldCondition = fieldCondition;
/*    */   }
/*    */   
/*    */   public AutoArrayList getFieldValue() {
/* 52 */     return this.fieldValue;
/*    */   }
/*    */   
/*    */   public void setFieldValue(AutoArrayList fieldValue) {
/* 56 */     this.fieldValue = fieldValue;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/BeanSessionQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
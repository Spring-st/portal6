/*    */ package com.aof.web.struts.form.basicDataView;
/*    */ 
/*    */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*    */ 
/*    */ public class BasicDataViewQueryForm
/*    */   extends BaseSessionQueryForm {
/*    */   private String height;
/*    */   
/*    */   public String getHeight() {
/* 10 */     return this.height;
/*    */   } private String eq; private String low;
/*    */   public void setHeight(String height) {
/* 13 */     this.height = height;
/*    */   }
/*    */   public String getEq() {
/* 16 */     return this.eq;
/*    */   }
/*    */   public void setEq(String eq) {
/* 19 */     this.eq = eq;
/*    */   }
/*    */   public String getLow() {
/* 22 */     return this.low;
/*    */   }
/*    */   public void setLow(String low) {
/* 25 */     this.low = low;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/basicDataView/BasicDataViewQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
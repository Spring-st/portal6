/*    */ package com.aof.web.struts.form.product;
/*    */ 
/*    */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*    */ 
/*    */ public class SalesOrderItemQueryForm
/*    */   extends BaseSessionQueryForm {
/*    */   private String soipNumber;
/*    */   private String selectSoipitemId;
/*    */   private String itemNumber;
/*    */   private String dueDate;
/*    */   private String status;
/*    */   
/*    */   public String getStatus() {
/* 14 */     return this.status;
/*    */   }
/*    */   public void setStatus(String status) {
/* 17 */     this.status = status;
/*    */   }
/*    */   public String getDueDate() {
/* 20 */     return this.dueDate;
/*    */   }
/*    */   public void setDueDate(String dueDate) {
/* 23 */     this.dueDate = dueDate;
/*    */   }
/*    */   public String getItemNumber() {
/* 26 */     return this.itemNumber;
/*    */   }
/*    */   public void setItemNumber(String itemNumber) {
/* 29 */     this.itemNumber = itemNumber;
/*    */   }
/*    */   public String getSoipNumber() {
/* 32 */     return this.soipNumber;
/*    */   }
/*    */   public void setSoipNumber(String soipNumber) {
/* 35 */     this.soipNumber = soipNumber;
/*    */   }
/*    */   public String getSelectSoipitemId() {
/* 38 */     return this.selectSoipitemId;
/*    */   }
/*    */   public void setSelectSoipitemId(String selectSoipitemId) {
/* 41 */     this.selectSoipitemId = selectSoipitemId;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/product/SalesOrderItemQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
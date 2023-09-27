/*    */ package com.aof.web.struts.form.po;
/*    */ 
/*    */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*    */ 
/*    */ 
/*    */ public class ProductOutStorageQueryForm
/*    */   extends BaseSessionQueryForm
/*    */ {
/*    */   private String id;
/*    */   private String saiheCode;
/*    */   private String hncCode;
/*    */   private String desc;
/*    */   
/*    */   public String getId() {
/* 15 */     return this.id;
/*    */   } private String qty; private String outDate; private String deliverDate; private String status;
/*    */   public void setId(String id) {
/* 18 */     this.id = id;
/*    */   }
/*    */   public String getHncCode() {
/* 21 */     return this.hncCode;
/*    */   }
/*    */   public void setHncCode(String hncCode) {
/* 24 */     this.hncCode = hncCode;
/*    */   }
/*    */   public String getDesc() {
/* 27 */     return this.desc;
/*    */   }
/*    */   public void setDesc(String desc) {
/* 30 */     this.desc = desc;
/*    */   }
/*    */   public String getQty() {
/* 33 */     return this.qty;
/*    */   }
/*    */   public void setQty(String qty) {
/* 36 */     this.qty = qty;
/*    */   }
/*    */   public String getOutDate() {
/* 39 */     return this.outDate;
/*    */   }
/*    */   public void setOutDate(String outDate) {
/* 42 */     this.outDate = outDate;
/*    */   }
/*    */   public String getSaiheCode() {
/* 45 */     return this.saiheCode;
/*    */   }
/*    */   public void setSaiheCode(String saiheCode) {
/* 48 */     this.saiheCode = saiheCode;
/*    */   }
/*    */   public String getDeliverDate() {
/* 51 */     return this.deliverDate;
/*    */   }
/*    */   public void setDeliverDate(String deliverDate) {
/* 54 */     this.deliverDate = deliverDate;
/*    */   }
/*    */   public String getStatus() {
/* 57 */     return this.status;
/*    */   }
/*    */   public void setStatus(String status) {
/* 60 */     this.status = status;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/po/ProductOutStorageQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
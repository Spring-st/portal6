/*    */ package com.aof.web.struts.form.basic;
/*    */ 
/*    */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScanLogQueryForm
/*    */   extends BaseSessionQueryForm
/*    */ {
/*    */   private String id;
/*    */   private String type;
/*    */   private String describe;
/*    */   private String userId;
/*    */   private String startDate;
/*    */   private String endDate;
/*    */   private String siteId;
/*    */   
/*    */   public String getSiteId() {
/* 28 */     return this.siteId;
/*    */   }
/*    */   
/*    */   public void setSiteId(String siteId) {
/* 32 */     this.siteId = siteId;
/*    */   }
/*    */   
/*    */   public String getId() {
/* 36 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(String id) {
/* 40 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getType() {
/* 44 */     return this.type;
/*    */   }
/*    */   
/*    */   public void setType(String type) {
/* 48 */     this.type = type;
/*    */   }
/*    */   
/*    */   public String getDescribe() {
/* 52 */     return this.describe;
/*    */   }
/*    */   
/*    */   public void setDescribe(String describe) {
/* 56 */     this.describe = describe;
/*    */   }
/*    */   
/*    */   public String getUserId() {
/* 60 */     return this.userId;
/*    */   }
/*    */   
/*    */   public void setUserId(String userId) {
/* 64 */     this.userId = userId;
/*    */   }
/*    */   
/*    */   public String getStartDate() {
/* 68 */     return this.startDate;
/*    */   }
/*    */   
/*    */   public void setStartDate(String startDate) {
/* 72 */     this.startDate = startDate;
/*    */   }
/*    */   
/*    */   public String getEndDate() {
/* 76 */     return this.endDate;
/*    */   }
/*    */   
/*    */   public void setEndDate(String endDate) {
/* 80 */     this.endDate = endDate;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/basic/ScanLogQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
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
/*    */ public class InventoryTempQueryForm
/*    */   extends BaseSessionQueryForm
/*    */ {
/*    */   private String id;
/*    */   private String date;
/*    */   private String part;
/*    */   private String describe1;
/*    */   private String describe2;
/*    */   private String location;
/*    */   
/*    */   public String getLocation() {
/* 26 */     return this.location;
/*    */   }
/*    */   
/*    */   public void setLocation(String location) {
/* 30 */     this.location = location;
/*    */   }
/*    */   
/*    */   public String getDescribe1() {
/* 34 */     return this.describe1;
/*    */   }
/*    */   
/*    */   public void setDescribe1(String describe1) {
/* 38 */     this.describe1 = describe1;
/*    */   }
/*    */   
/*    */   public String getDescribe2() {
/* 42 */     return this.describe2;
/*    */   }
/*    */   
/*    */   public void setDescribe2(String describe2) {
/* 46 */     this.describe2 = describe2;
/*    */   }
/*    */   
/*    */   public String getDate() {
/* 50 */     return this.date;
/*    */   }
/*    */   
/*    */   public void setDate(String date) {
/* 54 */     this.date = date;
/*    */   }
/*    */   
/*    */   public String getPart() {
/* 58 */     return this.part;
/*    */   }
/*    */   
/*    */   public void setPart(String part) {
/* 62 */     this.part = part;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getId() {
/* 69 */     return this.id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setId(String id) {
/* 77 */     this.id = id;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/basic/InventoryTempQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
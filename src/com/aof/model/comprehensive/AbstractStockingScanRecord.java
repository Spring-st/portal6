/*    */ package com.aof.model.comprehensive;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
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
/*    */ 
/*    */ public abstract class AbstractStockingScanRecord
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private String stocking;
/*    */   private String location;
/*    */   private String lotSer;
/*    */   private String userId;
/*    */   private Date date;
/*    */   
/*    */   public String getUserId() {
/* 29 */     return this.userId;
/*    */   }
/*    */   
/*    */   public void setUserId(String userId) {
/* 33 */     this.userId = userId;
/*    */   }
/*    */   
/*    */   public Date getDate() {
/* 37 */     return this.date;
/*    */   }
/*    */   
/*    */   public void setDate(Date date) {
/* 41 */     this.date = date;
/*    */   }
/*    */   
/*    */   public Integer getId() {
/* 45 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Integer id) {
/* 49 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getStocking() {
/* 53 */     return this.stocking;
/*    */   }
/*    */   
/*    */   public void setStocking(String stocking) {
/* 57 */     this.stocking = stocking;
/*    */   }
/*    */   
/*    */   public String getLocation() {
/* 61 */     return this.location;
/*    */   }
/*    */   
/*    */   public void setLocation(String location) {
/* 65 */     this.location = location;
/*    */   }
/*    */   
/*    */   public String getLotSer() {
/* 69 */     return this.lotSer;
/*    */   }
/*    */   
/*    */   public void setLotSer(String lotSer) {
/* 73 */     this.lotSer = lotSer;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractStockingScanRecord() {}
/*    */   
/*    */   public AbstractStockingScanRecord(Integer id) {
/* 80 */     setId(id);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/comprehensive/AbstractStockingScanRecord.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
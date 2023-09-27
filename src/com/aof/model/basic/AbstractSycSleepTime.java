/*    */ package com.aof.model.basic;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractSycSleepTime
/*    */   implements Serializable
/*    */ {
/* 11 */   private int hashValue = 0;
/*    */   
/*    */   private Integer id;
/*    */   private String type;
/*    */   private String sleepTime;
/*    */   
/*    */   public Integer getId() {
/* 18 */     return this.id;
/*    */   }
/*    */   public void setId(Integer id) {
/* 21 */     this.id = id;
/*    */   }
/*    */   public String getType() {
/* 24 */     return this.type;
/*    */   }
/*    */   public void setType(String type) {
/* 27 */     this.type = type;
/*    */   }
/*    */   public String getSleepTime() {
/* 30 */     return this.sleepTime;
/*    */   }
/*    */   public void setSleepTime(String sleepTime) {
/* 33 */     this.sleepTime = sleepTime;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractSycSleepTime.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
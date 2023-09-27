/*    */ package com.aof.model.business;
/*    */ 
/*    */ import com.aof.model.admin.User;
/*    */ import java.io.Serializable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BasePurchaser
/*    */   implements Serializable
/*    */ {
/* 25 */   private int hashValue = 0;
/*    */ 
/*    */   
/*    */   private Integer id;
/*    */ 
/*    */   
/*    */   private User purchaser;
/*    */ 
/*    */ 
/*    */   
/*    */   public BasePurchaser() {}
/*    */ 
/*    */   
/*    */   public BasePurchaser(Integer id) {
/* 39 */     setId(id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Integer getId() {
/* 46 */     return this.id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setId(Integer id) {
/* 54 */     this.hashValue = 0;
/* 55 */     this.id = id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public User getPurchaser() {
/* 62 */     return this.purchaser;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPurchaser(User purchaser) {
/* 70 */     this.purchaser = purchaser;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract boolean equals(Object paramObject);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 89 */     if (this.hashValue == 0) {
/* 90 */       int result = 17;
/* 91 */       int idValue = (getId() == null) ? 0 : getId().hashCode();
/* 92 */       result = result * 37 + idValue;
/* 93 */       this.hashValue = result;
/*    */     } 
/* 95 */     return this.hashValue;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/BasePurchaser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
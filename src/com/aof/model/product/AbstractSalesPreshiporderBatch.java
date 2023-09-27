/*    */ package com.aof.model.product;
/*    */ 
/*    */ import com.aof.model.metadata.EnabledDisabled;
/*    */ import com.aof.model.metadata.YesNo;
/*    */ import com.aof.model.po.Box;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractSalesPreshiporderBatch
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private SalesPreshiporderItem spsoitemId;
/*    */   private Box box;
/*    */   private YesNo status;
/*    */   private EnabledDisabled enabled;
/*    */   
/*    */   public EnabledDisabled getEnabled() {
/* 21 */     return this.enabled;
/*    */   }
/*    */   
/*    */   public void setEnabled(EnabledDisabled enabled) {
/* 25 */     this.enabled = enabled;
/*    */   }
/*    */   
/*    */   public SalesPreshiporderItem getSpsoitemId() {
/* 29 */     return this.spsoitemId;
/*    */   }
/*    */   
/*    */   public void setSpsoitemId(SalesPreshiporderItem spsoitemId) {
/* 33 */     this.spsoitemId = spsoitemId;
/*    */   }
/*    */   
/*    */   public Box getBox() {
/* 37 */     return this.box;
/*    */   }
/*    */   
/*    */   public void setBox(Box box) {
/* 41 */     this.box = box;
/*    */   }
/*    */   
/*    */   public YesNo getStatus() {
/* 45 */     return this.status;
/*    */   }
/*    */   
/*    */   public void setStatus(YesNo status) {
/* 49 */     this.status = status;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractSalesPreshiporderBatch() {}
/*    */   
/*    */   public AbstractSalesPreshiporderBatch(Integer id) {
/* 56 */     this.id = id;
/*    */   }
/*    */   
/*    */   public Integer getId() {
/* 60 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Integer id) {
/* 64 */     this.id = id;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/AbstractSalesPreshiporderBatch.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
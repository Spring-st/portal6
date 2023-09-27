/*    */ package com.aof.model.basic;
/*    */ 
/*    */ import com.aof.model.metadata.EnabledDisabled;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class AbstractCostCenter
/*    */   implements Serializable {
/*  8 */   private int hashValue = 0;
/*    */   private Integer id;
/*    */   private String code;
/*    */   private String description;
/*    */   private EnabledDisabled enabled;
/*    */   
/*    */   public Integer getId() {
/* 15 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Integer id) {
/* 19 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 23 */     return this.description;
/*    */   }
/*    */   
/*    */   public void setDescription(String description) {
/* 27 */     this.description = description;
/*    */   }
/*    */   
/*    */   public EnabledDisabled getEnabled() {
/* 31 */     return this.enabled;
/*    */   }
/*    */   
/*    */   public void setEnabled(EnabledDisabled enabled) {
/* 35 */     this.enabled = enabled;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCostCenter() {}
/*    */   
/*    */   public AbstractCostCenter(Integer id) {
/* 42 */     setId(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object rhs) {
/* 47 */     if (rhs == null)
/* 48 */       return false; 
/* 49 */     if (this == rhs)
/* 50 */       return true; 
/* 51 */     if (!(rhs instanceof CostCenter))
/* 52 */       return false; 
/* 53 */     CostCenter that = (CostCenter)rhs;
/* 54 */     if (getCode() != null)
/* 55 */       return getCode().equals(that.getCode()); 
/* 56 */     return (that.getCode() == null);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 61 */     if (this.hashValue == 0) {
/* 62 */       int result = 17;
/* 63 */       int purCateIdValue = (getCode() == null) ? 0 : getCode()
/* 64 */         .hashCode();
/* 65 */       result = result * 37 + purCateIdValue;
/* 66 */       this.hashValue = result;
/*    */     } 
/* 68 */     return this.hashValue;
/*    */   }
/*    */   
/*    */   public String getCode() {
/* 72 */     return this.code;
/*    */   }
/*    */   
/*    */   public void setCode(String code) {
/* 76 */     this.code = code;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractCostCenter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
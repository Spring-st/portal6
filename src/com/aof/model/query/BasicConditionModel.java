/*    */ package com.aof.model.query;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BasicConditionModel
/*    */ {
/*    */   private Integer index;
/*    */   private String field;
/*    */   private BasicConditionType type;
/*    */   private Object value;
/* 12 */   private Boolean isCustom = Boolean.valueOf(false);
/*    */   public BasicConditionModel(String field, BasicConditionType type, Object value) {
/* 14 */     this.field = field;
/* 15 */     this.type = type;
/* 16 */     this.value = value;
/*    */   }
/*    */   
/*    */   public String getField() {
/* 20 */     return this.field;
/*    */   }
/*    */   public void setField(String field) {
/* 23 */     this.field = field;
/*    */   }
/*    */   public BasicConditionType getType() {
/* 26 */     return this.type;
/*    */   }
/*    */   public void setType(BasicConditionType type) {
/* 29 */     this.type = type;
/*    */   }
/*    */   public Object getValue() {
/* 32 */     return this.value;
/*    */   }
/*    */   public void setValue(Object value) {
/* 35 */     this.value = value;
/*    */   }
/*    */   
/*    */   public Boolean getIsCustom() {
/* 39 */     return this.isCustom;
/*    */   }
/*    */   
/*    */   public void setIsCustom(Boolean isCustom) {
/* 43 */     this.isCustom = isCustom;
/*    */   }
/*    */   
/*    */   public Integer getIndex() {
/* 47 */     return this.index;
/*    */   }
/*    */   
/*    */   public void setIndex(Integer index) {
/* 51 */     this.index = index;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/query/BasicConditionModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
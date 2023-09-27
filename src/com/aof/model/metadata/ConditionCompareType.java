/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConditionCompareType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 13 */   public static final ConditionCompareType LESS_THAN = new ConditionCompareType(1, "<", MetadataType.CONDITION_COMPARE_TYPE);
/* 14 */   public static final ConditionCompareType LESS_OR_EQUAL = new ConditionCompareType(2, "<=", MetadataType.CONDITION_COMPARE_TYPE);
/* 15 */   public static final ConditionCompareType EQUAL = new ConditionCompareType(3, "=", MetadataType.CONDITION_COMPARE_TYPE);
/* 16 */   public static final ConditionCompareType GREATER_OR_EQUAL = new ConditionCompareType(4, ">=", MetadataType.CONDITION_COMPARE_TYPE);
/* 17 */   public static final ConditionCompareType GREATER_THAN = new ConditionCompareType(5, ">", MetadataType.CONDITION_COMPARE_TYPE);
/*    */   
/*    */   static {
/* 20 */     LESS_THAN.engineComparePassCondition = 1;
/* 21 */     LESS_OR_EQUAL.engineComparePassCondition = 2;
/* 22 */     EQUAL.engineComparePassCondition = 5;
/* 23 */     GREATER_OR_EQUAL.engineComparePassCondition = 3;
/* 24 */     GREATER_THAN.engineComparePassCondition = 4;
/*    */   }
/*    */   private int engineComparePassCondition;
/*    */   
/*    */   public ConditionCompareType() {}
/*    */   
/*    */   private ConditionCompareType(int metadataId, String defaultLabel, MetadataType type) {
/* 31 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEngineComparePassCondition() {
/* 40 */     return this.engineComparePassCondition;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/ConditionCompareType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
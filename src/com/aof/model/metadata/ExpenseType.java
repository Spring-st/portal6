/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExpenseType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final ExpenseType TRAVEL = new ExpenseType(1, "Travel", MetadataType.EXPENSE_TYPE);
/* 12 */   public static final ExpenseType OTHER = new ExpenseType(2, "Other", MetadataType.EXPENSE_TYPE);
/*    */ 
/*    */   
/*    */   public ExpenseType() {}
/*    */   
/*    */   private ExpenseType(int metadataId, String defaultLabel, MetadataType type) {
/* 18 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/ExpenseType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
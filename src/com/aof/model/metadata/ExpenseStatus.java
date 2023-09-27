/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExpenseStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final ExpenseStatus DRAFT = new ExpenseStatus(1, "Draft", MetadataType.EXPENSE_STATUS);
/* 13 */   public static final ExpenseStatus PENDING = new ExpenseStatus(2, "Pending", MetadataType.EXPENSE_STATUS);
/* 14 */   public static final ExpenseStatus APPROVED = new ExpenseStatus(3, "Approved", MetadataType.EXPENSE_STATUS);
/* 15 */   public static final ExpenseStatus REJECTED = new ExpenseStatus(4, "Rejected", MetadataType.EXPENSE_STATUS);
/* 16 */   public static final ExpenseStatus CONFIRMED = new ExpenseStatus(5, "Confirmed", MetadataType.EXPENSE_STATUS);
/* 17 */   public static final ExpenseStatus CLAIMED = new ExpenseStatus(6, "Claimed", MetadataType.EXPENSE_STATUS);
/*    */ 
/*    */   
/*    */   public ExpenseStatus() {}
/*    */ 
/*    */   
/*    */   private ExpenseStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 24 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/ExpenseStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
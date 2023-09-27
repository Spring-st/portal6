/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BudgetType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final BudgetType CAPEX = new BudgetType(1, "Capex", MetadataType.BUDGET_TYPE);
/* 12 */   public static final BudgetType OTHER = new BudgetType(2, "Other", MetadataType.BUDGET_TYPE);
/*    */ 
/*    */   
/*    */   public BudgetType() {}
/*    */   
/*    */   private BudgetType(int metadataId, String defaultLabel, MetadataType type) {
/* 18 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/BudgetType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
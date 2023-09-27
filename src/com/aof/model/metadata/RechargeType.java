/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RechargeType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final RechargeType CUSTOMER = new RechargeType(1, "Customer", MetadataType.RECHARGE_TYPE);
/* 12 */   public static final RechargeType ENTITY = new RechargeType(2, "Entity", MetadataType.RECHARGE_TYPE);
/* 13 */   public static final RechargeType PERSON = new RechargeType(3, "Person", MetadataType.RECHARGE_TYPE);
/*    */ 
/*    */   
/*    */   public RechargeType() {}
/*    */ 
/*    */   
/*    */   private RechargeType(int metadataId, String defaultLabel, MetadataType type) {
/* 20 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/RechargeType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
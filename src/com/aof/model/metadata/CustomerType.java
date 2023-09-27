/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomerType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final CustomerType CUSTOMER = new CustomerType(1, "Customer", MetadataType.CUSTOMER_TYPE);
/* 12 */   public static final CustomerType ENTITY = new CustomerType(2, "Entity", MetadataType.CUSTOMER_TYPE);
/*    */   
/*    */   static {
/* 15 */     CUSTOMER.rechargeType = RechargeType.CUSTOMER;
/* 16 */     ENTITY.rechargeType = RechargeType.ENTITY;
/*    */   }
/*    */   private RechargeType rechargeType;
/*    */   
/*    */   public CustomerType() {}
/*    */   
/*    */   private CustomerType(int metadataId, String defaultLabel, MetadataType type) {
/* 23 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RechargeType getRechargeType() {
/* 32 */     return this.rechargeType;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/CustomerType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ public class InventoryMovingQueryCondition
/*    */   extends Enum
/*    */ {
/*  9 */   public static final InventoryMovingQueryCondition ID_EQ = new InventoryMovingQueryCondition("id_eq");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   public static final InventoryMovingQueryCondition PROVINCE_ID_EQ = new InventoryMovingQueryCondition("province_id_eq");
/*    */ 
/*    */ 
/*    */   
/* 21 */   public static final InventoryMovingQueryCondition ENGNAME_LIKE = new InventoryMovingQueryCondition("engName_like");
/*    */   
/* 23 */   public static final InventoryMovingQueryCondition CHNNAME_LIKE = new InventoryMovingQueryCondition("chnName_like");
/*    */   
/* 25 */   public static final InventoryMovingQueryCondition ENABLED_EQ = new InventoryMovingQueryCondition("enabled_eq");
/*    */   
/*    */   protected InventoryMovingQueryCondition(String value) {
/* 28 */     super(value);
/*    */   }
/*    */   
/*    */   public static InventoryMovingQueryCondition getEnum(String value) {
/* 32 */     return (InventoryMovingQueryCondition)getEnum(InventoryMovingQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/InventoryMovingQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
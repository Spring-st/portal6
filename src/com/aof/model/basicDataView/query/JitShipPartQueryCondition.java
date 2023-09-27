/*    */ package com.aof.model.basicDataView.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class JitShipPartQueryCondition
/*    */   extends Enum {
/*  7 */   public static final JitShipPartQueryCondition PART_ID_EQ = new JitShipPartQueryCondition("part_id_eq");
/*  8 */   public static final JitShipPartQueryCondition PART_FREEZE_STATUS_EQ = new JitShipPartQueryCondition("part_freeze_status_eq");
/*  9 */   public static final JitShipPartQueryCondition PART_PRODUCTCLASS_EQ = new JitShipPartQueryCondition("part_productclass_eq");
/* 10 */   public static final JitShipPartQueryCondition PART_ENABLED_EQ = new JitShipPartQueryCondition("part_enabled_eq");
/* 11 */   public static final JitShipPartQueryCondition PART_VEND_EQ = new JitShipPartQueryCondition("part_vend_eq");
/* 12 */   public static final JitShipPartQueryCondition QTY_GT = new JitShipPartQueryCondition("qty_gt");
/*    */ 
/*    */   
/* 15 */   public static final JitShipPartQueryCondition highQty = new JitShipPartQueryCondition("highQty");
/*    */   
/* 17 */   public static final JitShipPartQueryCondition lowQty = new JitShipPartQueryCondition("lowQty");
/* 18 */   public static final JitShipPartQueryCondition securityQty = new JitShipPartQueryCondition("securityQty");
/* 19 */   public static final JitShipPartQueryCondition currentQty = new JitShipPartQueryCondition("currentQty");
/*    */   protected JitShipPartQueryCondition(String value) {
/* 21 */     super(value);
/*    */   }
/*    */   
/*    */   public static JitShipPartQueryCondition getEnum(String value) {
/* 25 */     return (JitShipPartQueryCondition)getEnum(JitShipPartQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basicDataView/query/JitShipPartQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.aof.model.comprehensive.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class BoxAdjustmentQueryOrder
/*    */   extends Enum {
/*  7 */   public static final BoxAdjustmentQueryOrder ID = new BoxAdjustmentQueryOrder("id");
/*  8 */   public static final BoxAdjustmentQueryOrder ENGNAME = new BoxAdjustmentQueryOrder("engName");
/*  9 */   public static final BoxAdjustmentQueryOrder CHNNAME = new BoxAdjustmentQueryOrder("chnName");
/* 10 */   public static final BoxAdjustmentQueryOrder ENABLED = new BoxAdjustmentQueryOrder("enabled");
/* 11 */   public static final BoxAdjustmentQueryOrder BOX_ID = new BoxAdjustmentQueryOrder("box_id");
/*    */   
/*    */   protected BoxAdjustmentQueryOrder(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   
/*    */   public static BoxAdjustmentQueryOrder getEnum(String value) {
/* 18 */     return (BoxAdjustmentQueryOrder)getEnum(BoxAdjustmentQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/comprehensive/query/BoxAdjustmentQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
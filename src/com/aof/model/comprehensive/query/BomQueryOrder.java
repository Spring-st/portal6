/*    */ package com.aof.model.comprehensive.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class BomQueryOrder
/*    */   extends Enum {
/*  7 */   public static final BomQueryOrder ID = new BomQueryOrder("id");
/*  8 */   public static final BomQueryOrder ENGNAME = new BomQueryOrder("engName");
/*  9 */   public static final BomQueryOrder CHNNAME = new BomQueryOrder("chnName");
/* 10 */   public static final BomQueryOrder ENABLED = new BomQueryOrder("enabled");
/*    */   
/*    */   protected BomQueryOrder(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static BomQueryOrder getEnum(String value) {
/* 17 */     return (BomQueryOrder)getEnum(BomQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/comprehensive/query/BomQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
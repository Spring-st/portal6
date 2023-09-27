/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class WmsPartQueryOrder
/*    */   extends Enum {
/*  7 */   public static final WmsPartQueryOrder ID = new WmsPartQueryOrder("id");
/*  8 */   public static final WmsPartQueryOrder ENGNAME = new WmsPartQueryOrder("engName");
/*  9 */   public static final WmsPartQueryOrder CHNNAME = new WmsPartQueryOrder("chnName");
/* 10 */   public static final WmsPartQueryOrder ENABLED = new WmsPartQueryOrder("enabled");
/*    */   
/*    */   protected WmsPartQueryOrder(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static WmsPartQueryOrder getEnum(String value) {
/* 17 */     return (WmsPartQueryOrder)getEnum(WmsPartQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/WmsPartQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
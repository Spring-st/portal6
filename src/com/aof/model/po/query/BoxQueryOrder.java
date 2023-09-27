/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class BoxQueryOrder
/*    */   extends Enum {
/*  7 */   public static final BoxQueryOrder ID = new BoxQueryOrder("id");
/*  8 */   public static final BoxQueryOrder ENGNAME = new BoxQueryOrder("engName");
/*  9 */   public static final BoxQueryOrder CHNNAME = new BoxQueryOrder("chnName");
/* 10 */   public static final BoxQueryOrder ENABLED = new BoxQueryOrder("enabled");
/* 11 */   public static final BoxQueryOrder INDATE_LINE = new BoxQueryOrder("outDate");
/* 12 */   public static final BoxQueryOrder PART_ID = new BoxQueryOrder("part_id");
/*    */   
/*    */   protected BoxQueryOrder(String value) {
/* 15 */     super(value);
/*    */   }
/*    */   
/*    */   public static BoxQueryOrder getEnum(String value) {
/* 19 */     return (BoxQueryOrder)getEnum(BoxQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/BoxQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
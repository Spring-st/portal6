/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class WmsToolQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final WmsToolQueryCondition ID_EQ = new WmsToolQueryCondition("id_eq");
/*  9 */   public static final WmsToolQueryCondition CODE_EQ = new WmsToolQueryCondition("code_eq");
/* 10 */   public static final WmsToolQueryCondition SEQ_EQ = new WmsToolQueryCondition("seq_eq");
/* 11 */   public static final WmsToolQueryCondition ISPRINT_EQ = new WmsToolQueryCondition("isPrint_eq");
/* 12 */   public static final WmsToolQueryCondition STATUS_EQ = new WmsToolQueryCondition("status_eq");
/*    */   
/*    */   protected WmsToolQueryCondition(String value) {
/* 15 */     super(value);
/*    */   }
/*    */   
/*    */   public static WmsToolQueryCondition getEnum(String value) {
/* 19 */     return (WmsToolQueryCondition)getEnum(WmsToolQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/WmsToolQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
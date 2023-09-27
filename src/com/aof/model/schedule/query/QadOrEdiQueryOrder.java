/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class QadOrEdiQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final QadOrEdiQueryOrder ID = new QadOrEdiQueryOrder("id");
/*    */   
/*    */   protected QadOrEdiQueryOrder(String name) {
/* 11 */     super(name);
/*    */   }
/*    */   
/*    */   public static QadOrEdiQueryOrder getEnum(String value) {
/* 15 */     return (QadOrEdiQueryOrder)getEnum(QadOrEdiQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/QadOrEdiQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ReportEntersSellsSavesQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final ReportEntersSellsSavesQueryOrder ID = new ReportEntersSellsSavesQueryOrder("id");
/*    */ 
/*    */   
/* 11 */   public static final ReportEntersSellsSavesQueryOrder ENABLED = new ReportEntersSellsSavesQueryOrder("enabled");
/*    */   
/*    */   protected ReportEntersSellsSavesQueryOrder(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   
/*    */   public static ReportEntersSellsSavesQueryOrder getEnum(String value) {
/* 18 */     return (ReportEntersSellsSavesQueryOrder)getEnum(ReportEntersSellsSavesQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/ReportEntersSellsSavesQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
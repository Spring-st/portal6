/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ public class ScanLogQueryCondition
/*    */   extends Enum
/*    */ {
/*  9 */   public static final ScanLogQueryCondition ID_EQ = new ScanLogQueryCondition("id_eq");
/*    */   
/* 11 */   public static final ScanLogQueryCondition TYPE_EQ = new ScanLogQueryCondition("type_eq");
/*    */   
/* 13 */   public static final ScanLogQueryCondition DESCRIBE_EQ = new ScanLogQueryCondition("describe_eq");
/*    */   
/* 15 */   public static final ScanLogQueryCondition STARTDATE_EQ = new ScanLogQueryCondition("startDate_eq");
/*    */   
/* 17 */   public static final ScanLogQueryCondition ENDDATE_EQ = new ScanLogQueryCondition("endDate_eq");
/*    */   
/*    */   protected ScanLogQueryCondition(String value) {
/* 20 */     super(value);
/*    */   }
/*    */   
/*    */   public static ScanLogQueryCondition getEnum(String value) {
/* 24 */     return (ScanLogQueryCondition)getEnum(ScanLogQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/ScanLogQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
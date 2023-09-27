/*    */ package com.aof.model.basicDataView.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class LocationPartNumberQueryCondition
/*    */   extends Enum {
/*  7 */   public static final LocationPartNumberQueryCondition NUMBER_QT = new LocationPartNumberQueryCondition("locationpartnumber_number_qt");
/*    */   
/*    */   protected LocationPartNumberQueryCondition(String value) {
/* 10 */     super(value);
/*    */   }
/*    */   
/*    */   public static LocationPartNumberQueryCondition getEnum(String value) {
/* 14 */     return (LocationPartNumberQueryCondition)getEnum(LocationPartNumberQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basicDataView/query/LocationPartNumberQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
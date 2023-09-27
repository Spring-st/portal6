/*    */ package com.aof.model.basicDataView.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class LocationPartNumberQueryOrder
/*    */   extends Enum {
/*  7 */   public static final LocationPartNumberQueryOrder LOCATION_ID = new LocationPartNumberQueryOrder("location");
/*  8 */   public static final LocationPartNumberQueryOrder PART_ID = new LocationPartNumberQueryOrder("part");
/*  9 */   public static final LocationPartNumberQueryOrder NUMBER = new LocationPartNumberQueryOrder("number");
/* 10 */   public static final LocationPartNumberQueryOrder PARTQTY = new LocationPartNumberQueryOrder("partQty");
/* 11 */   public static final LocationPartNumberQueryOrder OCCUPIEDNUMBER = new LocationPartNumberQueryOrder("occupiedNumber");
/*    */   
/*    */   protected LocationPartNumberQueryOrder(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   
/*    */   public static LocationPartNumberQueryOrder getEnum(String value) {
/* 18 */     return (LocationPartNumberQueryOrder)getEnum(LocationPartNumberQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basicDataView/query/LocationPartNumberQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
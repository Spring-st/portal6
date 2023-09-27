/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class HotelContractQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final HotelContractQueryOrder ID = new HotelContractQueryOrder("id");
/*    */ 
/*    */   
/* 11 */   public static final HotelContractQueryOrder FILENAME = new HotelContractQueryOrder("fileName");
/* 12 */   public static final HotelContractQueryOrder DESCRIPTION = new HotelContractQueryOrder("description");
/* 13 */   public static final HotelContractQueryOrder FILECONTENT = new HotelContractQueryOrder("fileContent");
/* 14 */   public static final HotelContractQueryOrder UPLOADDATE = new HotelContractQueryOrder("uploadDate");
/*    */   
/*    */   protected HotelContractQueryOrder(String value) {
/* 17 */     super(value);
/*    */   }
/*    */   
/*    */   public static HotelContractQueryOrder getEnum(String value) {
/* 21 */     return (HotelContractQueryOrder)getEnum(HotelContractQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/HotelContractQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
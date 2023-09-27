/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ public class HotelContractQueryCondition
/*    */   extends Enum
/*    */ {
/*  9 */   public static final HotelContractQueryCondition ID_EQ = new HotelContractQueryCondition("id_eq");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   public static final HotelContractQueryCondition HOTEL_ID_EQ = new HotelContractQueryCondition("hotel_id_eq");
/*    */ 
/*    */ 
/*    */   
/* 21 */   public static final HotelContractQueryCondition FILENAME_LIKE = new HotelContractQueryCondition("fileName_like");
/*    */   
/* 23 */   public static final HotelContractQueryCondition DESCRIPTION_LIKE = new HotelContractQueryCondition("description_like");
/*    */   
/* 25 */   public static final HotelContractQueryCondition FILECONTENT_EQ = new HotelContractQueryCondition("fileContent_eq");
/*    */   
/* 27 */   public static final HotelContractQueryCondition UPLOADDATE_EQ = new HotelContractQueryCondition("uploadDate_eq");
/*    */   
/*    */   protected HotelContractQueryCondition(String value) {
/* 30 */     super(value);
/*    */   }
/*    */   
/*    */   public static HotelContractQueryCondition getEnum(String value) {
/* 34 */     return (HotelContractQueryCondition)getEnum(HotelContractQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/HotelContractQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class HotelQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final HotelQueryOrder ID = new HotelQueryOrder("id");
/*    */ 
/*    */   
/* 11 */   public static final HotelQueryOrder NAME = new HotelQueryOrder("name");
/* 12 */   public static final HotelQueryOrder ADDRESS = new HotelQueryOrder("address");
/* 13 */   public static final HotelQueryOrder DESCRIPTION = new HotelQueryOrder("description");
/* 14 */   public static final HotelQueryOrder TELEPHONE = new HotelQueryOrder("telephone");
/* 15 */   public static final HotelQueryOrder FAX = new HotelQueryOrder("fax");
/* 16 */   public static final HotelQueryOrder LEVEL = new HotelQueryOrder("level");
/* 17 */   public static final HotelQueryOrder CONTRACTSTARTDATE = new HotelQueryOrder("contractStartDate");
/* 18 */   public static final HotelQueryOrder CONTRACTEXPIREDATE = new HotelQueryOrder("contractExpireDate");
/* 19 */   public static final HotelQueryOrder DRAFT = new HotelQueryOrder("draft");
/* 20 */   public static final HotelQueryOrder ENABLED = new HotelQueryOrder("enabled");
/*    */   
/* 22 */   public static final HotelQueryOrder COUNTRY_ENG = new HotelQueryOrder("country_eng");
/* 23 */   public static final HotelQueryOrder CITY_ENG = new HotelQueryOrder("city_eng");
/* 24 */   public static final HotelQueryOrder PROVINCE_ENG = new HotelQueryOrder("province_eng");
/*    */   
/* 26 */   public static final HotelQueryOrder COUNTRY_CHN = new HotelQueryOrder("country_chn");
/* 27 */   public static final HotelQueryOrder CITY_CHN = new HotelQueryOrder("city_chn");
/* 28 */   public static final HotelQueryOrder PROVINCE_CHN = new HotelQueryOrder("province_chn");
/*    */   
/* 30 */   public static final HotelQueryOrder SITE = new HotelQueryOrder("site");
/* 31 */   public static final HotelQueryOrder PROMOTESTATUS = new HotelQueryOrder("promoteStatus");
/*    */   
/*    */   protected HotelQueryOrder(String value) {
/* 34 */     super(value);
/*    */   }
/*    */   
/*    */   public static HotelQueryOrder getEnum(String value) {
/* 38 */     return (HotelQueryOrder)getEnum(HotelQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/HotelQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
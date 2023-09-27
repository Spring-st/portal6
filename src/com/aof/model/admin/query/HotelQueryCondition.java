/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ public class HotelQueryCondition
/*    */   extends Enum
/*    */ {
/*  9 */   public static final HotelQueryCondition ID_EQ = new HotelQueryCondition("id_eq");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   public static final HotelQueryCondition SITE_ID_EQ = new HotelQueryCondition("site_id_eq");
/*    */   
/* 19 */   public static final HotelQueryCondition CURRENCY_CODE_EQ = new HotelQueryCondition("currency_code_eq");
/*    */   
/* 21 */   public static final HotelQueryCondition CITY_ID_EQ = new HotelQueryCondition("city_id_eq");
/*    */   
/* 23 */   public static final HotelQueryCondition PROVINCE_ID_EQ = new HotelQueryCondition("province_id_eq");
/*    */ 
/*    */   
/* 26 */   public static final HotelQueryCondition COUNTRY_ID_EQ = new HotelQueryCondition("country_id_eq");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public static final HotelQueryCondition NAME_LIKE = new HotelQueryCondition("name_like");
/*    */   
/* 33 */   public static final HotelQueryCondition ADDRESS_LIKE = new HotelQueryCondition("address_like");
/*    */   
/* 35 */   public static final HotelQueryCondition DESCRIPTION_LIKE = new HotelQueryCondition("description_like");
/*    */   
/* 37 */   public static final HotelQueryCondition TELEPHONE_LIKE = new HotelQueryCondition("telephone_like");
/*    */   
/* 39 */   public static final HotelQueryCondition FAX_LIKE = new HotelQueryCondition("fax_like");
/*    */   
/* 41 */   public static final HotelQueryCondition LEVEL_EQ = new HotelQueryCondition("level_eq");
/*    */   
/* 43 */   public static final HotelQueryCondition CONTRACTSTARTDATE_EQ = new HotelQueryCondition("contractStartDate_eq");
/*    */   
/* 45 */   public static final HotelQueryCondition CONTRACTEXPIREDATE_EQ = new HotelQueryCondition("contractExpireDate_eq");
/*    */   
/* 47 */   public static final HotelQueryCondition DRAFT_EQ = new HotelQueryCondition("draft_eq");
/*    */   
/* 49 */   public static final HotelQueryCondition ENABLED_EQ = new HotelQueryCondition("enabled_eq");
/*    */ 
/*    */   
/* 52 */   public static final HotelQueryCondition PROMOTESTATUS_NOTEQ = new HotelQueryCondition("promoteStatus_notEq");
/*    */ 
/*    */   
/* 55 */   public static final HotelQueryCondition PROMOTESTATUS_EQ = new HotelQueryCondition("promoteStatus_eq");
/*    */   
/*    */   protected HotelQueryCondition(String value) {
/* 58 */     super(value);
/*    */   }
/*    */   
/*    */   public static HotelQueryCondition getEnum(String value) {
/* 62 */     return (HotelQueryCondition)getEnum(HotelQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/HotelQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
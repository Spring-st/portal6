/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ public class CityQueryCondition
/*    */   extends Enum
/*    */ {
/*  9 */   public static final CityQueryCondition ID_EQ = new CityQueryCondition("id_eq");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   public static final CityQueryCondition PROVINCE_ID_EQ = new CityQueryCondition("province_id_eq");
/*    */ 
/*    */ 
/*    */   
/* 21 */   public static final CityQueryCondition ENGNAME_LIKE = new CityQueryCondition("engName_like");
/*    */   
/* 23 */   public static final CityQueryCondition CHNNAME_LIKE = new CityQueryCondition("chnName_like");
/*    */   
/* 25 */   public static final CityQueryCondition ENABLED_EQ = new CityQueryCondition("enabled_eq");
/*    */   
/*    */   protected CityQueryCondition(String value) {
/* 28 */     super(value);
/*    */   }
/*    */   
/*    */   public static CityQueryCondition getEnum(String value) {
/* 32 */     return (CityQueryCondition)getEnum(CityQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/CityQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
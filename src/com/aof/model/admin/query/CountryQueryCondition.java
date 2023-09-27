/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class CountryQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final CountryQueryCondition ID_EQ = new CountryQueryCondition(
/*  9 */       "id_eq");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   public static final CountryQueryCondition SHORTNAME_LIKE = new CountryQueryCondition(
/* 19 */       "shortName_like");
/*    */   
/* 21 */   public static final CountryQueryCondition ENGNAME_LIKE = new CountryQueryCondition(
/* 22 */       "engName_like");
/*    */   
/* 24 */   public static final CountryQueryCondition CHNNAME_LIKE = new CountryQueryCondition(
/* 25 */       "chnName_like");
/*    */   
/* 27 */   public static final CountryQueryCondition ENABLED_EQ = new CountryQueryCondition(
/* 28 */       "enabled_eq");
/*    */ 
/*    */ 
/*    */   
/*    */   protected CountryQueryCondition(String value) {
/* 33 */     super(value);
/*    */   }
/*    */   
/*    */   public static CountryQueryCondition getEnum(String value) {
/* 37 */     return (CountryQueryCondition)getEnum(CountryQueryCondition.class, 
/* 38 */         value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/CountryQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
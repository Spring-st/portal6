/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProvinceQueryCondition
/*    */   extends Enum
/*    */ {
/* 15 */   public static final ProvinceQueryCondition ID_EQ = new ProvinceQueryCondition("id_eq");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   public static final ProvinceQueryCondition COUNTRY_ID_EQ = new ProvinceQueryCondition("country_id_eq");
/*    */ 
/*    */ 
/*    */   
/* 27 */   public static final ProvinceQueryCondition ENGNAME_LIKE = new ProvinceQueryCondition("engName_like");
/*    */   
/* 29 */   public static final ProvinceQueryCondition CHNNAME_LIKE = new ProvinceQueryCondition("chnName_like");
/*    */   
/* 31 */   public static final ProvinceQueryCondition ENABLED_EQ = new ProvinceQueryCondition("enabled_eq");
/*    */   
/*    */   protected ProvinceQueryCondition(String value) {
/* 34 */     super(value);
/*    */   }
/*    */   
/*    */   public static ProvinceQueryCondition getEnum(String value) {
/* 38 */     return (ProvinceQueryCondition)getEnum(ProvinceQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/ProvinceQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
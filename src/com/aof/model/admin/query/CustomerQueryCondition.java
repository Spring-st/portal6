/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomerQueryCondition
/*    */   extends Enum
/*    */ {
/* 13 */   public static final CustomerQueryCondition CODE_EQ = new CustomerQueryCondition("code_eq");
/* 14 */   public static final CustomerQueryCondition CODE_LIKE = new CustomerQueryCondition("code_like");
/* 15 */   public static final CustomerQueryCondition DESCRIPTION_LIKE = new CustomerQueryCondition("description_like");
/* 16 */   public static final CustomerQueryCondition SITE_ID_EQ = new CustomerQueryCondition("site_id_eq");
/* 17 */   public static final CustomerQueryCondition TYPE_EQ = new CustomerQueryCondition("type_eq");
/* 18 */   public static final CustomerQueryCondition ENABLED_EQ = new CustomerQueryCondition("enabled_eq");
/*    */   
/*    */   protected CustomerQueryCondition(String value) {
/* 21 */     super(value);
/*    */   }
/*    */   
/*    */   public static CustomerQueryCondition getEnum(String value) {
/* 25 */     return (CustomerQueryCondition)getEnum(CustomerQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/CustomerQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
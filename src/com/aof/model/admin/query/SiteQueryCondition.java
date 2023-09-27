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
/*    */ public class SiteQueryCondition
/*    */   extends Enum
/*    */ {
/* 14 */   public static final SiteQueryCondition NAME_LIKE = new SiteQueryCondition("name_like");
/* 15 */   public static final SiteQueryCondition ENABLED_EQ = new SiteQueryCondition("enabled_eq");
/* 16 */   public static final SiteQueryCondition NAME_EQ = new SiteQueryCondition("name_eq");
/*    */   
/*    */   protected SiteQueryCondition(String value) {
/* 19 */     super(value);
/*    */   }
/*    */   
/*    */   public static SiteQueryCondition getEnum(String value) {
/* 23 */     return (SiteQueryCondition)getEnum(SiteQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/SiteQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
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
/*    */ public class SiteQueryOrder
/*    */   extends Enum
/*    */ {
/* 14 */   public static final SiteQueryOrder NAME = new SiteQueryOrder("name");
/* 15 */   public static final SiteQueryOrder ENABLED = new SiteQueryOrder("enabled");
/*    */   
/*    */   protected SiteQueryOrder(String value) {
/* 18 */     super(value);
/*    */   }
/*    */   
/*    */   public static SiteQueryOrder getEnum(String value) {
/* 22 */     return (SiteQueryOrder)getEnum(SiteQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/SiteQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
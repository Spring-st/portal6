/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ public class TravelGroupQueryCondition
/*    */   extends Enum
/*    */ {
/*  9 */   public static final TravelGroupQueryCondition ID_EQ = new TravelGroupQueryCondition("id_eq");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   public static final TravelGroupQueryCondition SITE_ID_EQ = new TravelGroupQueryCondition("site_id_eq");
/*    */ 
/*    */ 
/*    */   
/* 21 */   public static final TravelGroupQueryCondition NAME_LIKE = new TravelGroupQueryCondition("name_like");
/*    */   
/* 23 */   public static final TravelGroupQueryCondition ENABLED_EQ = new TravelGroupQueryCondition("enabled_eq");
/*    */   
/*    */   protected TravelGroupQueryCondition(String value) {
/* 26 */     super(value);
/*    */   }
/*    */   
/*    */   public static TravelGroupQueryCondition getEnum(String value) {
/* 30 */     return (TravelGroupQueryCondition)getEnum(TravelGroupQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/TravelGroupQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
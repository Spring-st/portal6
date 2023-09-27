/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class CityQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final CityQueryOrder ID = new CityQueryOrder("id");
/*    */ 
/*    */   
/* 11 */   public static final CityQueryOrder ENGNAME = new CityQueryOrder("engName");
/* 12 */   public static final CityQueryOrder CHNNAME = new CityQueryOrder("chnName");
/* 13 */   public static final CityQueryOrder ENABLED = new CityQueryOrder("enabled");
/*    */   
/*    */   protected CityQueryOrder(String value) {
/* 16 */     super(value);
/*    */   }
/*    */   
/*    */   public static CityQueryOrder getEnum(String value) {
/* 20 */     return (CityQueryOrder)getEnum(CityQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/CityQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
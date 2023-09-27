/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CountryQueryOrder
/*    */   extends Enum
/*    */ {
/* 12 */   public static final CountryQueryOrder ID = new CountryQueryOrder("id");
/*    */ 
/*    */   
/* 15 */   public static final CountryQueryOrder SHORTNAME = new CountryQueryOrder("shortName");
/* 16 */   public static final CountryQueryOrder ENGNAME = new CountryQueryOrder("engName");
/* 17 */   public static final CountryQueryOrder CHNNAME = new CountryQueryOrder("chnName");
/* 18 */   public static final CountryQueryOrder ENABLED = new CountryQueryOrder("enabled");
/*    */   
/*    */   protected CountryQueryOrder(String value) {
/* 21 */     super(value);
/*    */   }
/*    */   
/*    */   public static CountryQueryOrder getEnum(String value) {
/* 25 */     return (CountryQueryOrder)getEnum(CountryQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/CountryQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
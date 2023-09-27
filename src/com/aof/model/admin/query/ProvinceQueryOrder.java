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
/*    */ public class ProvinceQueryOrder
/*    */   extends Enum
/*    */ {
/* 14 */   public static final ProvinceQueryOrder ID = new ProvinceQueryOrder("id");
/*    */ 
/*    */   
/* 17 */   public static final ProvinceQueryOrder ENGNAME = new ProvinceQueryOrder("engName");
/* 18 */   public static final ProvinceQueryOrder CHNNAME = new ProvinceQueryOrder("chnName");
/* 19 */   public static final ProvinceQueryOrder ENABLED = new ProvinceQueryOrder("enabled");
/*    */   
/*    */   protected ProvinceQueryOrder(String value) {
/* 22 */     super(value);
/*    */   }
/*    */   
/*    */   public static ProvinceQueryOrder getEnum(String value) {
/* 26 */     return (ProvinceQueryOrder)getEnum(ProvinceQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/ProvinceQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
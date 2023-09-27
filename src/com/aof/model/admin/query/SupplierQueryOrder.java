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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SupplierQueryOrder
/*    */   extends Enum
/*    */ {
/* 20 */   public static final SupplierQueryOrder ID = new SupplierQueryOrder("id");
/*    */   
/* 22 */   public static final SupplierQueryOrder CODE = new SupplierQueryOrder("code");
/* 23 */   public static final SupplierQueryOrder NAME = new SupplierQueryOrder("name");
/* 24 */   public static final SupplierQueryOrder CITYENGNAME = new SupplierQueryOrder("cityEngName");
/* 25 */   public static final SupplierQueryOrder CITYCHNNAME = new SupplierQueryOrder("cityChnName");
/* 26 */   public static final SupplierQueryOrder ENABLED = new SupplierQueryOrder("enabled");
/* 27 */   public static final SupplierQueryOrder DRAFT = new SupplierQueryOrder("draft");
/* 28 */   public static final SupplierQueryOrder PROMOTE_STATUS = new SupplierQueryOrder("promoteStatus");
/*    */ 
/*    */   
/*    */   protected SupplierQueryOrder(String value) {
/* 32 */     super(value);
/*    */   }
/*    */   
/*    */   public static SupplierQueryOrder getEnum(String value) {
/* 36 */     return (SupplierQueryOrder)getEnum(SupplierQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/SupplierQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProduceBuckleMaterialQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final ProduceBuckleMaterialQueryOrder ID = new ProduceBuckleMaterialQueryOrder("id");
/*    */ 
/*    */   
/*    */   protected ProduceBuckleMaterialQueryOrder(String value) {
/* 12 */     super(value);
/*    */   }
/*    */   
/*    */   public static ProduceBuckleMaterialQueryOrder getEnum(String value) {
/* 16 */     return (ProduceBuckleMaterialQueryOrder)getEnum(ProduceBuckleMaterialQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/ProduceBuckleMaterialQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
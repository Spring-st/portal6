/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProductionplanningQueryOrder
/*    */   extends Enum {
/*  7 */   public static final ProductionplanningQueryOrder id = new ProductionplanningQueryOrder("id");
/*  8 */   public static final ProductionplanningQueryOrder productionPlanningNumber = new ProductionplanningQueryOrder("productionPlanningNumber");
/*  9 */   public static final ProductionplanningQueryOrder uploadDate = new ProductionplanningQueryOrder("uploadDate");
/* 10 */   public static final ProductionplanningQueryOrder uploadUser = new ProductionplanningQueryOrder("uploadUser");
/* 11 */   public static final ProductionplanningQueryOrder uploadFileName = new ProductionplanningQueryOrder("uploadFileName");
/*    */   protected ProductionplanningQueryOrder(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   public static ProductionplanningQueryOrder getProductionplanningQueryOrder(String value) {
/* 16 */     return (ProductionplanningQueryOrder)getEnum(ProductionplanningQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/ProductionplanningQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
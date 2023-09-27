/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProductionplanningQueryCondition
/*    */   extends Enum {
/*  7 */   public static final ProductionplanningQueryCondition id_EQ = new ProductionplanningQueryCondition("id_eq");
/*  8 */   public static final ProductionplanningQueryCondition productionPlanningNumber_EQ = new ProductionplanningQueryCondition("productionPlanningNumber_eq");
/*  9 */   public static final ProductionplanningQueryCondition uploadDate_EQ = new ProductionplanningQueryCondition("uploadDate_eq");
/* 10 */   public static final ProductionplanningQueryCondition uploadUser_EQ = new ProductionplanningQueryCondition("uploadUser_eq");
/* 11 */   public static final ProductionplanningQueryCondition uploadFileName_EQ = new ProductionplanningQueryCondition("uploadFileName_eq");
/* 12 */   public static final ProductionplanningQueryCondition productionPlanningNumber_Like = new ProductionplanningQueryCondition("productionPlanningNumber_like");
/*    */   protected ProductionplanningQueryCondition(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   public static ProductionplanningQueryCondition getProductionplanningQueryCondition(String value) {
/* 17 */     return (ProductionplanningQueryCondition)getEnum(ProductionplanningQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/ProductionplanningQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
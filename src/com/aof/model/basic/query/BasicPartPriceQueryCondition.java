/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class BasicPartPriceQueryCondition
/*    */   extends Enum {
/*  7 */   public static final BasicPartPriceQueryCondition ID_EQ = new BasicPartPriceQueryCondition("id_eq");
/*  8 */   public static final BasicPartPriceQueryCondition CUSTOMER_EQ = new BasicPartPriceQueryCondition("customer_eq");
/*  9 */   public static final BasicPartPriceQueryCondition PARTID_EQ = new BasicPartPriceQueryCondition("partid_eq");
/* 10 */   public static final BasicPartPriceQueryCondition STARTDATE_LE = new BasicPartPriceQueryCondition("startdate_le");
/* 11 */   public static final BasicPartPriceQueryCondition STARTDATE_GE = new BasicPartPriceQueryCondition("startdate_ge");
/* 12 */   public static final BasicPartPriceQueryCondition EXPIREDATE_LE = new BasicPartPriceQueryCondition("expiredate_le");
/* 13 */   public static final BasicPartPriceQueryCondition EXPIREDATE_GE = new BasicPartPriceQueryCondition("expiredate_ge");
/*    */   
/*    */   protected BasicPartPriceQueryCondition(String name) {
/* 16 */     super(name);
/*    */   }
/*    */   
/*    */   public static BasicPartPriceQueryCondition getEnum(String value) {
/* 20 */     return (BasicPartPriceQueryCondition)getEnum(BasicPartPriceQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/BasicPartPriceQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
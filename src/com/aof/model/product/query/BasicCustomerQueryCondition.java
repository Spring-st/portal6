/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class BasicCustomerQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final BasicCustomerQueryCondition ID_EQ = new BasicCustomerQueryCondition("id_eq");
/*  9 */   public static final BasicCustomerQueryCondition ENABLED_EQ = new BasicCustomerQueryCondition("enabled_eq");
/* 10 */   public static final BasicCustomerQueryCondition CODE_EQ = new BasicCustomerQueryCondition("code_eq");
/* 11 */   public static final BasicCustomerQueryCondition EDITID_NE = new BasicCustomerQueryCondition("editid_ne");
/* 12 */   public static final BasicCustomerQueryCondition SITEID_ID_EQ = new BasicCustomerQueryCondition("siteid_id_eq");
/*    */   protected BasicCustomerQueryCondition(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   
/*    */   public static BasicCustomerQueryCondition getEnum(String value) {
/* 18 */     return (BasicCustomerQueryCondition)getEnum(BasicCustomerQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/BasicCustomerQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
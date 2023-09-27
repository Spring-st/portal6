/*    */ package com.aof.model.comprehensive.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class BomQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final BomQueryCondition ID_EQ = new BomQueryCondition("id_eq");
/*  9 */   public static final BomQueryCondition ID_BEGINWITH = new BomQueryCondition("id_beginwith");
/* 10 */   public static final BomQueryCondition DESCRIBE_EQ = new BomQueryCondition("describe_eq");
/* 11 */   public static final BomQueryCondition ENABLED_EQ = new BomQueryCondition("enabled_eq");
/* 12 */   public static final BomQueryCondition CODE_EQ = new BomQueryCondition("code_eq");
/* 13 */   public static final BomQueryCondition DELEIVER_EQ = new BomQueryCondition("deliver_eq");
/* 14 */   public static final BomQueryCondition DEATETIME_EQ = new BomQueryCondition("datetime_eq");
/* 15 */   public static final BomQueryCondition ENDTIME_EQ = new BomQueryCondition("endtime_eq");
/* 16 */   public static final BomQueryCondition STATUS_EQ = new BomQueryCondition("status_eq");
/* 17 */   public static final BomQueryCondition USER_EQ = new BomQueryCondition("user_eq");
/* 18 */   public static final BomQueryCondition TYPE_EQ = new BomQueryCondition("type_eq");
/* 19 */   public static final BomQueryCondition PRODUCT_NO_EQ = new BomQueryCondition("product_no_eq");
/* 20 */   public static final BomQueryCondition CHILD_PART_EQ = new BomQueryCondition("child_part_eq");
/* 21 */   public static final BomQueryCondition FATHER_PART_EQ = new BomQueryCondition("father_part_eq");
/*    */   
/*    */   protected BomQueryCondition(String value) {
/* 24 */     super(value);
/*    */   }
/*    */   
/*    */   public static BomQueryCondition getEnum(String value) {
/* 28 */     return (BomQueryCondition)getEnum(BomQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/comprehensive/query/BomQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
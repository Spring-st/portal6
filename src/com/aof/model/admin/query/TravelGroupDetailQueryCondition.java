/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TravelGroupDetailQueryCondition
/*    */   extends Enum
/*    */ {
/* 13 */   public static final TravelGroupDetailQueryCondition EXPENSESUBCATEGORY_ID_EQ = new TravelGroupDetailQueryCondition("expenseSubCategory_id_eq");
/*    */   
/* 15 */   public static final TravelGroupDetailQueryCondition TRAVELGROUP_ID_EQ = new TravelGroupDetailQueryCondition("travelGroup_id_eq");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 21 */   public static final TravelGroupDetailQueryCondition AMOUNTLIMIT_EQ = new TravelGroupDetailQueryCondition("amountLimit_eq");
/*    */   
/* 23 */   public static final TravelGroupDetailQueryCondition EXPENSESUBCATEGORY_ISHOTEL_EQ = new TravelGroupDetailQueryCondition("expenseSubCategory_ishote_eq");
/*    */   
/*    */   protected TravelGroupDetailQueryCondition(String value) {
/* 26 */     super(value);
/*    */   }
/*    */   
/*    */   public static TravelGroupDetailQueryCondition getEnum(String value) {
/* 30 */     return (TravelGroupDetailQueryCondition)getEnum(TravelGroupDetailQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/TravelGroupDetailQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
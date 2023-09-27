/*    */ package com.aof.model.admin;
/*    */ 
/*    */ import com.aof.model.metadata.YesNo;
/*    */ import java.io.Serializable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExpenseSubCategory
/*    */   extends AbstractExpenseSubCategory
/*    */   implements Serializable
/*    */ {
/*    */   public ExpenseSubCategory() {
/* 26 */     setIsHotel(YesNo.NO);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ExpenseSubCategory(Integer id) {
/* 35 */     super(id);
/* 36 */     setIsHotel(YesNo.NO);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/ExpenseSubCategory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
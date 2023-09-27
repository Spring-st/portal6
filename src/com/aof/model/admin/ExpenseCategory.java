/*    */ package com.aof.model.admin;
/*    */ 
/*    */ import com.aof.model.metadata.ExpenseType;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ public class ExpenseCategory
/*    */   extends AbstractExpenseCategory
/*    */   implements Serializable
/*    */ {
/*    */   private List enabledExpenseSubCategoryList;
/*    */   
/*    */   public ExpenseCategory() {
/* 28 */     setType(ExpenseType.OTHER);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ExpenseCategory(Integer id) {
/* 37 */     super(id);
/* 38 */     setType(ExpenseType.OTHER);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getEnabledExpenseSubCategoryList() {
/* 45 */     return this.enabledExpenseSubCategoryList;
/*    */   }
/*    */   
/*    */   public void setEnabledExpenseSubCategoryList(List enabledExpenseSubCategoryList) {
/* 49 */     this.enabledExpenseSubCategoryList = enabledExpenseSubCategoryList;
/*    */   }
/*    */   
/*    */   public void addEnabledExpenseSubCategory(ExpenseSubCategory esc) {
/* 53 */     if (this.enabledExpenseSubCategoryList == null)
/* 54 */       this.enabledExpenseSubCategoryList = new ArrayList(); 
/* 55 */     this.enabledExpenseSubCategoryList.add(esc);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/ExpenseCategory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
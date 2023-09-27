/*    */ package com.aof.model.admin;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PurchaseCategory
/*    */   extends AbstractPurchaseCategory
/*    */   implements Serializable
/*    */ {
/*    */   private List enabledPurchaseSubCategoryList;
/*    */   
/*    */   public PurchaseCategory() {}
/*    */   
/*    */   public PurchaseCategory(Integer id) {
/* 34 */     super(id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getEnabledPurchaseSubCategoryList() {
/* 44 */     return this.enabledPurchaseSubCategoryList;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setEnabledPurchaseSubCategoryList(List enabledPurchaseSubCategoryList) {
/* 51 */     this.enabledPurchaseSubCategoryList = enabledPurchaseSubCategoryList;
/*    */   }
/*    */   
/*    */   public void addEnabledPurchaseSubCategory(PurchaseSubCategory psc) {
/* 55 */     if (this.enabledPurchaseSubCategoryList == null) {
/* 56 */       this.enabledPurchaseSubCategoryList = new ArrayList();
/* 57 */       this.enabledPurchaseSubCategoryList.add(psc);
/* 58 */     } else if (!this.enabledPurchaseSubCategoryList.contains(psc)) {
/* 59 */       this.enabledPurchaseSubCategoryList.add(psc);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/PurchaseCategory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
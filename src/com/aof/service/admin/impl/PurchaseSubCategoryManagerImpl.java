/*    */ package com.aof.service.admin.impl;
/*    */ 
/*    */ import com.aof.dao.admin.PurchaseSubCategoryDAO;
/*    */ import com.aof.model.admin.PurchaseCategory;
/*    */ import com.aof.model.admin.PurchaseSubCategory;
/*    */ import com.aof.model.admin.query.PurchaseSubCategoryQueryCondition;
/*    */ import com.aof.model.admin.query.PurchaseSubCategoryQueryOrder;
/*    */ import com.aof.model.metadata.EnabledDisabled;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.admin.PurchaseSubCategoryManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
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
/*    */ public class PurchaseSubCategoryManagerImpl
/*    */   extends BaseManager
/*    */   implements PurchaseSubCategoryManager
/*    */ {
/*    */   private PurchaseSubCategoryDAO dao;
/*    */   
/*    */   public void setPurchaseSubCategoryDAO(PurchaseSubCategoryDAO dao) {
/* 36 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PurchaseSubCategory getPurchaseSubCategory(Integer id) {
/* 43 */     return this.dao.getPurchaseSubCategory(id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PurchaseSubCategory updatePurchaseSubCategory(PurchaseSubCategory function) {
/* 50 */     return this.dao.updatePurchaseSubCategory(function);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PurchaseSubCategory insertPurchaseSubCategory(PurchaseSubCategory function) {
/* 57 */     return this.dao.insertPurchaseSubCategory(function);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPurchaseSubCategoryListCount(Map conditions) {
/* 65 */     return this.dao.getPurchaseSubCategoryListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getPurchaseSubCategoryList(Map conditions, int pageNo, int pageSize, PurchaseSubCategoryQueryOrder order, boolean descend) {
/* 72 */     return this.dao.getPurchaseSubCategoryList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getEnabledPurchaseSubCategoryOfPurchaseCategory(PurchaseCategory category) {
/* 79 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 80 */     conds.put(PurchaseSubCategoryQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 81 */     conds.put(PurchaseSubCategoryQueryCondition.PURCHASECATEGORY_ID_EQ, category.getId());
/* 82 */     return getPurchaseSubCategoryList(conds, 0, -1, PurchaseSubCategoryQueryOrder.DESCRIPTION, false);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/PurchaseSubCategoryManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
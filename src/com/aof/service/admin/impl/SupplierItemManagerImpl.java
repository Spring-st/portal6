/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.SupplierItemDAO;
/*     */ import com.aof.model.admin.PurchaseSubCategory;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.admin.SupplierItem;
/*     */ import com.aof.model.admin.query.SupplierItemQueryCondition;
/*     */ import com.aof.model.admin.query.SupplierItemQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.SupplierItemManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SupplierItemManagerImpl
/*     */   extends BaseManager
/*     */   implements SupplierItemManager
/*     */ {
/*     */   private SupplierItemDAO dao;
/*     */   
/*     */   public void setSupplierItemDAO(SupplierItemDAO dao) {
/*  35 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public SupplierItem getSupplierItem(Integer id) {
/*  39 */     return this.dao.getSupplierItem(id);
/*     */   }
/*     */   
/*     */   public SupplierItem updateSupplierItem(SupplierItem function) {
/*  43 */     return this.dao.updateSupplierItem(function);
/*     */   }
/*     */   
/*     */   public SupplierItem insertSupplierItem(SupplierItem function) {
/*  47 */     return this.dao.insertSupplierItem(function);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSupplierItemListCount(Map conditions) {
/*  52 */     return this.dao.getSupplierItemListCount(conditions);
/*     */   }
/*     */   
/*     */   public List getSupplierItemList(Map conditions, int pageNo, int pageSize, SupplierItemQueryOrder order, boolean descend) {
/*  56 */     return this.dao.getSupplierItemList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getSupplierItemListBySupplier(Supplier supplier) {
/*  61 */     return this.dao.getSupplierItemListBySupplier(supplier);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getSupplierItemListBySupplierPurchaseSubCategoryId(Integer supplierId, Integer purchaseSubCategoryId) {
/*  66 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  67 */     conditions.put(SupplierItemQueryCondition.SUPPLIER_ID_EQ, supplierId);
/*  68 */     conditions.put(SupplierItemQueryCondition.PURCHASESUBCATEGORY_ID_EQ, purchaseSubCategoryId);
/*  69 */     return this.dao.getSupplierItemList(conditions, 0, -1, null, false);
/*     */   }
/*     */   
/*     */   public List getSupplierItemListGroupByPurchaseSubCategory(Supplier supplier) {
/*  73 */     List itemList = this.dao.getSupplierItemListBySupplier(supplier);
/*  74 */     return getSupplierItemListGroupByPurchaseSubCategory(itemList);
/*     */   }
/*     */   
/*     */   private List getSupplierItemListGroupByPurchaseSubCategory(List itemList) {
/*  78 */     List<List<SupplierItem>> result = new ArrayList();
/*  79 */     if (itemList.size() > 0) {
/*  80 */       Iterator<SupplierItem> itor = itemList.iterator();
/*  81 */       Integer lastSubCategoryId = new Integer(-1);
/*  82 */       List<SupplierItem> items = null;
/*  83 */       while (itor.hasNext()) {
/*  84 */         SupplierItem item = itor.next();
/*  85 */         if (!lastSubCategoryId.equals(item.getPurchaseSubCategory().getId())) {
/*  86 */           items = new ArrayList();
/*  87 */           items.add(item);
/*  88 */           lastSubCategoryId = item.getPurchaseSubCategory().getId();
/*  89 */           result.add(items); continue;
/*     */         } 
/*  91 */         items.add(item);
/*     */       } 
/*     */     } 
/*     */     
/*  95 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeSupplierItem(Integer id) {
/* 100 */     this.dao.removeSupplierItem(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getSupplierItemListConflictWithPromoteBySupplierGroupByPurchaseSubCategory(Supplier supplier) {
/* 107 */     List itemList = this.dao.getSupplierItemListConflictWithPromoteBySupplier(supplier);
/* 108 */     return getSupplierItemListGroupByPurchaseSubCategory(itemList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void changePurchaseSubCategoryBySupplierAndPurchaseSubCategory(Integer supplierId, Integer purchaseSubCategoryId, PurchaseSubCategory destPurchaseSubCategory) {
/* 115 */     this.dao.changePurchaseSubCategoryBySupplierAndPurchaseSubCategory(supplierId, purchaseSubCategoryId, destPurchaseSubCategory);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/SupplierItemManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
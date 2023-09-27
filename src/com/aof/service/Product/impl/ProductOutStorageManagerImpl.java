/*    */ package com.aof.service.Product.impl;
/*    */ 
/*    */ import com.aof.dao.product.ProductOutStorageDAO;
/*    */ import com.aof.model.product.ProductOutStorage;
/*    */ import com.aof.model.product.query.ProductOutStorageQueryOrder;
/*    */ import com.aof.service.Product.ProductOutStorageManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ProductOutStorageManagerImpl
/*    */   implements ProductOutStorageManager
/*    */ {
/*    */   private ProductOutStorageDAO dao;
/*    */   
/*    */   public ProductOutStorageDAO getDao() {
/* 16 */     return this.dao;
/*    */   }
/*    */   
/*    */   public void setDao(ProductOutStorageDAO dao) {
/* 20 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public ProductOutStorage getProductOutStorage(Integer id) {
/* 24 */     return this.dao.getProductOutStorage(id);
/*    */   }
/*    */   
/*    */   public ProductOutStorage updateProductOutStorage(ProductOutStorage entity) {
/* 28 */     return this.dao.updateProductOutStorage(entity);
/*    */   }
/*    */   
/*    */   public ProductOutStorage insertProductOutStorage(ProductOutStorage entity) {
/* 32 */     return this.dao.insertProductOutStorage(entity);
/*    */   }
/*    */   
/*    */   public void deleteProductOutStorage(ProductOutStorage entity) {
/* 36 */     this.dao.deleteProductOutStorage(entity);
/*    */   }
/*    */   
/*    */   public int getProductOutStorageListCount(Map conditions) {
/* 40 */     return this.dao.getProductOutStorageListCount(conditions);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getProductOutStorageList(Map conditions, int pageNo, int pageSize, ProductOutStorageQueryOrder order, boolean descend) {
/* 45 */     return this.dao.getProductOutStorageList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/impl/ProductOutStorageManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
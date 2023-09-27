/*    */ package com.aof.service.Product.impl;
/*    */ 
/*    */ import com.aof.dao.product.ProductGolineDAO;
/*    */ import com.aof.model.product.ProductGoline;
/*    */ import com.aof.model.product.query.ProductGolineQueryOrder;
/*    */ import com.aof.service.Product.ProductGolineManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ProductGolineManagerImpl
/*    */   implements ProductGolineManager
/*    */ {
/*    */   private ProductGolineDAO dao;
/*    */   
/*    */   public ProductGolineDAO getDao() {
/* 16 */     return this.dao;
/*    */   }
/*    */   
/*    */   public void setDao(ProductGolineDAO dao) {
/* 20 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public ProductGoline getProductGoline(Integer id) {
/* 24 */     return this.dao.getProductGoline(id);
/*    */   }
/*    */   
/*    */   public ProductGoline updateProductGoline(ProductGoline entity) {
/* 28 */     return this.dao.updateProductGoline(entity);
/*    */   }
/*    */   
/*    */   public ProductGoline insertProductGoline(ProductGoline entity) {
/* 32 */     return this.dao.insertProductGoline(entity);
/*    */   }
/*    */   
/*    */   public void deleteProductGoline(ProductGoline entity) {
/* 36 */     this.dao.deleteProductGoline(entity);
/*    */   }
/*    */   
/*    */   public int getProductGolineListCount(Map conditions) {
/* 40 */     return this.dao.getProductGolineListCount(conditions);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getProductGolineList(Map conditions, int pageNo, int pageSize, ProductGolineQueryOrder order, boolean descend) {
/* 45 */     return this.dao.getProductGolineList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */   
/*    */   public List getTotalNumberList() {
/* 49 */     return this.dao.getTotalNumberList();
/*    */   }
/*    */ 
/*    */   
/*    */   public List getHncCodeList() {
/* 54 */     return this.dao.getHncCodeList();
/*    */   }
/*    */   
/*    */   public int getProductGolineListCountAjax(Map conditions) {
/* 58 */     return this.dao.getProductGolineListCountAjax(conditions);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/impl/ProductGolineManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
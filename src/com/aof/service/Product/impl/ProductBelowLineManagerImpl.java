/*    */ package com.aof.service.Product.impl;
/*    */ 
/*    */ import com.aof.dao.product.ProductBelowLineDAO;
/*    */ import com.aof.model.product.ProductBelowLine;
/*    */ import com.aof.model.product.query.ProductBelowLineQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.Product.ProductBelowLineManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ProductBelowLineManagerImpl
/*    */   extends BaseManager
/*    */   implements ProductBelowLineManager {
/*    */   private ProductBelowLineDAO dao;
/*    */   
/*    */   public void setDao(ProductBelowLineDAO dao) {
/* 17 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   
/*    */   public ProductBelowLine getProductBelowLine(Integer id) {
/* 22 */     return this.dao.getProductBelowLine(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getProductBelowLineListCount(Map conditions) {
/* 27 */     return this.dao.getProductBelowLineListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getProductBelowLineList(Map conditions, int pageNo, int pageSize, ProductBelowLineQueryOrder order, boolean descend) {
/* 33 */     return this.dao.getProductBelowLineList(conditions, pageNo, pageSize, order, 
/* 34 */         descend);
/*    */   }
/*    */ 
/*    */   
/*    */   public ProductBelowLine insertProductBelowLine(ProductBelowLine city) {
/* 39 */     return this.dao.insertProductBelowLine(city);
/*    */   }
/*    */ 
/*    */   
/*    */   public ProductBelowLine updateProductBelowLine(ProductBelowLine city) {
/* 44 */     return this.dao.updateProductBelowLine(city);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/impl/ProductBelowLineManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
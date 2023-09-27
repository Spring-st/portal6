/*    */ package com.aof.service.Product.impl;
/*    */ 
/*    */ import com.aof.dao.product.ProductBelowlineCasadeDAO;
/*    */ import com.aof.model.product.ProductBelowlineCasade;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.Product.ProductBelowlineCasadeManager;
/*    */ 
/*    */ public class ProductBelowlineCasadeManagerImpl
/*    */   extends BaseManager
/*    */   implements ProductBelowlineCasadeManager {
/*    */   private ProductBelowlineCasadeDAO productBelowlineCasadeDAO;
/*    */   
/*    */   public void setProductBelowlineCasadeDAO(ProductBelowlineCasadeDAO productBelowlineCasadeDAO) {
/* 14 */     this.productBelowlineCasadeDAO = productBelowlineCasadeDAO;
/*    */   }
/*    */   
/*    */   public void insert(ProductBelowlineCasade casade) {
/* 18 */     this.productBelowlineCasadeDAO.insert(casade);
/*    */   }
/*    */ 
/*    */   
/*    */   public void delete(ProductBelowlineCasade casade) {
/* 23 */     this.productBelowlineCasadeDAO.delete(casade);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(ProductBelowlineCasade casade) {
/* 28 */     this.productBelowlineCasadeDAO.update(casade);
/*    */   }
/*    */   
/*    */   public ProductBelowlineCasade getProductBelowlineCasadeByProduct(Integer id) {
/* 32 */     return this.productBelowlineCasadeDAO.getProductBelowlineCasadeByProduct(id);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/impl/ProductBelowlineCasadeManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
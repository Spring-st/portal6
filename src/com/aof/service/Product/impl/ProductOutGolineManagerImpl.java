/*    */ package com.aof.service.Product.impl;
/*    */ 
/*    */ import com.aof.dao.product.ProductOutGolineDAO;
/*    */ import com.aof.model.sync.query.ProductOutGolineQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.Product.ProductOutGolineManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProductOutGolineManagerImpl
/*    */   extends BaseManager
/*    */   implements ProductOutGolineManager
/*    */ {
/*    */   private ProductOutGolineDAO dao;
/*    */   
/*    */   public ProductOutGolineDAO getDao() {
/* 19 */     return this.dao;
/*    */   }
/*    */   
/*    */   public void setDao(ProductOutGolineDAO dao) {
/* 23 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public int getProductOutGolineListCount(Map conditions) {
/* 27 */     return this.dao.getProductOutGolineListCount(conditions);
/*    */   }
/*    */   
/*    */   public List getProductOutGolineList(Map conditions, int pageNo, int pageSize, ProductOutGolineQueryOrder order, boolean descend) {
/* 31 */     return this.dao.getProductOutGolineList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/impl/ProductOutGolineManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
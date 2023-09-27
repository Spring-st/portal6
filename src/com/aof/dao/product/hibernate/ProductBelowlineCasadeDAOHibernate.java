/*    */ package com.aof.dao.product.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.product.ProductBelowlineCasadeDAO;
/*    */ import com.aof.model.product.ProductBelowlineCasade;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ProductBelowlineCasadeDAOHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements ProductBelowlineCasadeDAO
/*    */ {
/*    */   public void insert(ProductBelowlineCasade casade) {
/* 13 */     getHibernateTemplate().save(casade);
/*    */   }
/*    */   
/*    */   public void delete(ProductBelowlineCasade casade) {
/* 17 */     getHibernateTemplate().delete(casade);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(ProductBelowlineCasade casade) {
/* 22 */     getHibernateTemplate().update(casade);
/*    */   }
/*    */   
/*    */   public ProductBelowlineCasade getProductBelowlineCasadeByProduct(Integer id) {
/* 26 */     String hql = "from ProductBelowlineCasade p where p.product.id=" + id;
/* 27 */     List<ProductBelowlineCasade> list = getHibernateTemplate().find(hql);
/* 28 */     return list.get(0);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/hibernate/ProductBelowlineCasadeDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
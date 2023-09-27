/*     */ package com.aof.dao.product.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.product.ProductDAO;
/*     */ import com.aof.model.product.Product;
/*     */ import com.aof.model.product.ProductBelowLine;
/*     */ import com.aof.model.product.query.ProductQueryCondition;
/*     */ import com.aof.model.product.query.ProductQueryOrder;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
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
/*     */ public class ProductDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements ProductDAO
/*     */ {
/*  30 */   private Log log = LogFactory.getLog(ProductDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from Product produce";
/*     */   
/*     */   private static final String SQL = "from Product produce";
/*     */ 
/*     */   
/*     */   public Product getProduct(Integer id) {
/*  38 */     if (id == null) {
/*  39 */       if (this.log.isDebugEnabled())
/*  40 */         this.log.debug("try to get Product with null id"); 
/*  41 */       return null;
/*     */     } 
/*  43 */     return (Product)getHibernateTemplate().get(Product.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Product updateProduct(Product sr) {
/*  53 */     Integer id = sr.getId();
/*  54 */     if (id == null) {
/*  55 */       throw new RuntimeException("cannot save a sr with null id");
/*     */     }
/*  57 */     Product oldProduct = getProduct(id);
/*  58 */     if (oldProduct != null) {
/*     */       try {
/*  60 */         PropertyUtils.copyProperties(oldProduct, sr);
/*  61 */       } catch (Exception e) {
/*  62 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  64 */       getHibernateTemplate().update(oldProduct);
/*  65 */       return oldProduct;
/*     */     } 
/*  67 */     throw new RuntimeException("sr not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Product insertProduct(Product sr) {
/*  78 */     getHibernateTemplate().save(sr);
/*  79 */     return sr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*     */       {
/*  88 */         ProductQueryCondition.ID_EQ, "produce.id = ?" }
/*     */     };
/*  90 */   private static final Object[][] QUERY_ORDERS = new Object[][] { { ProductQueryOrder.ID, 
/*  91 */         "produce.id" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getProductListCount(Map conditions) {
/*  99 */     return getListCount(conditions, "select count(*) from Product produce", QUERY_CONDITIONS);
/*     */   }
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
/*     */   public List getProductList(Map conditions, int pageNo, int pageSize, ProductQueryOrder order, boolean descend) {
/* 112 */     return getList(conditions, pageNo, pageSize, order, descend, "from Product produce", 
/* 113 */         QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */   
/*     */   public List<ProductBelowLine> getAllProductBelowLines() {
/* 117 */     String hql = "from ProductBelowLine";
/* 118 */     return getHibernateTemplate().find(hql);
/*     */   }
/*     */   
/*     */   public void delete(Product product) {
/* 122 */     getHibernateTemplate().delete(product);
/*     */   }
/*     */   
/*     */   public List<ProductBelowLine> getBelowLineByStorage(String stroage) {
/* 126 */     String hql = "from ProductBelowLine p where p.location.code=?";
/* 127 */     List<ProductBelowLine> list = getHibernateTemplate().find(hql, stroage);
/* 128 */     return list;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/hibernate/ProductDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
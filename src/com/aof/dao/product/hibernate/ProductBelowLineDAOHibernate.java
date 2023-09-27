/*     */ package com.aof.dao.product.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.product.ProductBelowLineDAO;
/*     */ import com.aof.model.product.ProductBelowLine;
/*     */ import com.aof.model.product.query.ProductBelowLineQueryCondition;
/*     */ import com.aof.model.product.query.ProductBelowLineQueryOrder;
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
/*     */ public class ProductBelowLineDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements ProductBelowLineDAO
/*     */ {
/*  29 */   private Log log = LogFactory.getLog(ProductBelowLineDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from ProductBelowLine produce";
/*     */   
/*     */   private static final String SQL = "from ProductBelowLine produce";
/*     */ 
/*     */   
/*     */   public ProductBelowLine getProductBelowLine(Integer id) {
/*  37 */     if (id == null) {
/*  38 */       if (this.log.isDebugEnabled())
/*  39 */         this.log.debug("try to get ProductBelowLine with null id"); 
/*  40 */       return null;
/*     */     } 
/*  42 */     return (ProductBelowLine)getHibernateTemplate().get(ProductBelowLine.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProductBelowLine updateProductBelowLine(ProductBelowLine sr) {
/*  51 */     Integer id = sr.getId();
/*  52 */     if (id == null) {
/*  53 */       throw new RuntimeException("cannot save a sr with null id");
/*     */     }
/*  55 */     ProductBelowLine oldProductBelowLine = getProductBelowLine(id);
/*  56 */     if (oldProductBelowLine != null) {
/*     */       try {
/*  58 */         PropertyUtils.copyProperties(oldProductBelowLine, sr);
/*  59 */       } catch (Exception e) {
/*  60 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  62 */       getHibernateTemplate().update(oldProductBelowLine);
/*  63 */       return oldProductBelowLine;
/*     */     } 
/*  65 */     throw new RuntimeException("sr not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProductBelowLine insertProductBelowLine(ProductBelowLine sr) {
/*  75 */     getHibernateTemplate().save(sr);
/*  76 */     return sr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*     */       {
/*  85 */         ProductBelowLineQueryCondition.ID_EQ, "produce.id = ?"
/*     */       }
/*     */     };
/*  88 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  89 */       { ProductBelowLineQueryOrder.ID, "produce.id" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getProductBelowLineListCount(Map conditions) {
/*  98 */     return getListCount(conditions, "select count(*) from ProductBelowLine produce", QUERY_CONDITIONS);
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
/*     */   public List getProductBelowLineList(Map conditions, int pageNo, int pageSize, ProductBelowLineQueryOrder order, boolean descend) {
/* 111 */     return getList(conditions, pageNo, pageSize, order, descend, "from ProductBelowLine produce", 
/* 112 */         QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/hibernate/ProductBelowLineDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
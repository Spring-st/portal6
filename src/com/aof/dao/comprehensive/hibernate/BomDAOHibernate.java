/*     */ package com.aof.dao.comprehensive.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.comprehensive.BomDAO;
/*     */ import com.aof.model.comprehensive.Bom;
/*     */ import com.aof.model.comprehensive.query.BomQueryCondition;
/*     */ import com.aof.model.comprehensive.query.BomQueryOrder;
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
/*     */ public class BomDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements BomDAO
/*     */ {
/*  29 */   private Log log = LogFactory.getLog(BomDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from Bom ba";
/*     */   
/*     */   private static final String SQL = "from Bom ba";
/*     */ 
/*     */   
/*     */   public Bom getBom(Integer id) {
/*  37 */     if (id == null) {
/*  38 */       if (this.log.isDebugEnabled())
/*  39 */         this.log.debug("try to get Bom with null id"); 
/*  40 */       return null;
/*     */     } 
/*  42 */     return (Bom)getHibernateTemplate().get(Bom.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bom updateBom(Bom ct) {
/*  53 */     Integer id = ct.getId();
/*  54 */     if (id == null) {
/*  55 */       throw new RuntimeException("cannot save a Bom with null id");
/*     */     }
/*  57 */     Bom oldBom = getBom(id);
/*  58 */     if (oldBom != null) {
/*     */       try {
/*  60 */         PropertyUtils.copyProperties(oldBom, ct);
/*  61 */       } catch (Exception e) {
/*  62 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/*  64 */       getHibernateTemplate().update(oldBom);
/*  65 */       return oldBom;
/*     */     } 
/*  67 */     throw new RuntimeException("Bom not found");
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
/*     */   public Bom insertBom(Bom ct) {
/*  79 */     getHibernateTemplate().save(ct);
/*  80 */     return ct;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  88 */       { BomQueryCondition.ID_EQ, "ba.id = ?"
/*  89 */       }, { BomQueryCondition.PRODUCT_NO_EQ, "ba.product_no.id = ?"
/*  90 */       }, { BomQueryCondition.CHILD_PART_EQ, "ba.child_part.id = ?"
/*  91 */       }, { BomQueryCondition.FATHER_PART_EQ, "ba.father_part.id = ?" }
/*     */     };
/*     */ 
/*     */   
/*  95 */   private static final Object[][] QUERY_ORDERS = new Object[][] { { BomQueryOrder.ID, "ba.id" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBomListCount(Map conditions) {
/* 104 */     return getListCount(conditions, "select count(*) from Bom ba", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getBomList(Map conditions, int pageNo, int pageSize, BomQueryOrder order, boolean descend) {
/* 115 */     return getList(conditions, pageNo, pageSize, order, descend, "from Bom ba", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/comprehensive/hibernate/BomDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
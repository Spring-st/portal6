/*     */ package com.aof.dao.po.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.po.ProduceBuckleMaterialDAO;
/*     */ import com.aof.model.comprehensive.ProduceBuckleMaterial;
/*     */ import com.aof.model.po.query.ProduceBuckleMaterialQueryCondition;
/*     */ import com.aof.model.po.query.ProduceBuckleMaterialQueryOrder;
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
/*     */ public class ProduceBuckleMaterialDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements ProduceBuckleMaterialDAO
/*     */ {
/*  29 */   private Log log = LogFactory.getLog(ProduceBuckleMaterialDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from ProduceBuckleMaterial pbm";
/*     */   
/*     */   private static final String SQL = "from ProduceBuckleMaterial pbm";
/*     */ 
/*     */   
/*     */   public ProduceBuckleMaterial getProduceBuckleMaterial(Integer id) {
/*  37 */     if (id == null) {
/*  38 */       if (this.log.isDebugEnabled())
/*  39 */         this.log.debug("try to get ProduceBuckleMaterial with null id"); 
/*  40 */       return null;
/*     */     } 
/*  42 */     return (ProduceBuckleMaterial)getHibernateTemplate().get(ProduceBuckleMaterial.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProduceBuckleMaterial updateProduceBuckleMaterial(ProduceBuckleMaterial material) {
/*  51 */     Integer id = material.getId();
/*  52 */     if (id == null) {
/*  53 */       throw new RuntimeException("cannot save a material with null id");
/*     */     }
/*  55 */     ProduceBuckleMaterial oldProduceBuckleMaterial = getProduceBuckleMaterial(id);
/*  56 */     if (oldProduceBuckleMaterial != null) {
/*     */       try {
/*  58 */         PropertyUtils.copyProperties(oldProduceBuckleMaterial, material);
/*  59 */       } catch (Exception e) {
/*  60 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/*  62 */       getHibernateTemplate().update(oldProduceBuckleMaterial);
/*  63 */       return oldProduceBuckleMaterial;
/*     */     } 
/*  65 */     throw new RuntimeException("material not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProduceBuckleMaterial insertProduceBuckleMaterial(ProduceBuckleMaterial material) {
/*  76 */     getHibernateTemplate().save(material);
/*  77 */     getHibernateTemplate().flush();
/*  78 */     return material;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  86 */       { ProduceBuckleMaterialQueryCondition.ID_EQ, "pbm.id = ?" }
/*     */     };
/*     */ 
/*     */   
/*  90 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  91 */       { ProduceBuckleMaterialQueryOrder.ID, "pbm.id" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getProduceBuckleMaterialListCount(Map conditions) {
/* 100 */     return getListCount(conditions, "select count(*) from ProduceBuckleMaterial pbm", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getProduceBuckleMaterialList(Map conditions, int pageNo, int pageSize, ProduceBuckleMaterialQueryOrder order, boolean descend) {
/* 108 */     return getList(conditions, pageNo, pageSize, order, descend, "from ProduceBuckleMaterial pbm", 
/* 109 */         QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */   
/*     */   public Integer getnextGrowth() {
/* 113 */     String sql = "select max(pbm.growth) from ProduceBuckleMaterial pbm";
/* 114 */     return getHibernateTemplate().find(sql).get(0);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/hibernate/ProduceBuckleMaterialDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
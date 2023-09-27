/*     */ package com.aof.dao.po.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.po.ProduceRejectedMaterialDAO;
/*     */ import com.aof.model.po.ProduceRejectedMaterial;
/*     */ import com.aof.model.po.query.ProduceRejectedMaterialQueryCondition;
/*     */ import com.aof.model.po.query.ProduceRejectedMaterialQueryOrder;
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
/*     */ public class ProduceRejectedMaterialDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements ProduceRejectedMaterialDAO
/*     */ {
/*  28 */   private Log log = LogFactory.getLog(ProduceRejectedMaterialDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from ProduceRejectedMaterial prm";
/*     */   
/*     */   private static final String SQL = "from ProduceRejectedMaterial prm";
/*     */ 
/*     */   
/*     */   public ProduceRejectedMaterial getProduceRejectedMaterial(Integer id) {
/*  36 */     if (id == null) {
/*  37 */       if (this.log.isDebugEnabled())
/*  38 */         this.log.debug("try to get ProduceRejectedMaterial with null id"); 
/*  39 */       return null;
/*     */     } 
/*  41 */     return (ProduceRejectedMaterial)getHibernateTemplate().get(ProduceRejectedMaterial.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProduceRejectedMaterial updateProduceRejectedMaterial(ProduceRejectedMaterial ct) {
/*  50 */     Integer id = ct.getId();
/*  51 */     if (id == null) {
/*  52 */       throw new RuntimeException("cannot save a ct with null id");
/*     */     }
/*  54 */     ProduceRejectedMaterial oldProduceRejectedMaterial = getProduceRejectedMaterial(id);
/*  55 */     if (oldProduceRejectedMaterial != null) {
/*     */       try {
/*  57 */         PropertyUtils.copyProperties(oldProduceRejectedMaterial, ct);
/*  58 */       } catch (Exception e) {
/*  59 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/*  61 */       getHibernateTemplate().update(oldProduceRejectedMaterial);
/*  62 */       return oldProduceRejectedMaterial;
/*     */     } 
/*  64 */     throw new RuntimeException("ct not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public ProduceRejectedMaterial insertProduceRejectedMaterial(ProduceRejectedMaterial ct) {
/*  69 */     getHibernateTemplate().save(ct);
/*  70 */     return ct;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  78 */       { ProduceRejectedMaterialQueryCondition.ID_EQ, "prm.id = ?"
/*  79 */       }, { ProduceRejectedMaterialQueryCondition.TYPE_EQ, "prm.type = ?" }
/*     */     };
/*     */ 
/*     */   
/*  83 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  84 */       { ProduceRejectedMaterialQueryOrder.ID, "prm.id" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getProduceRejectedMaterialListCount(Map conditions) {
/*  93 */     return getListCount(conditions, "select count(*) from ProduceRejectedMaterial prm", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getProduceRejectedMaterialList(Map conditions, int pageNo, int pageSize, ProduceRejectedMaterialQueryOrder order, boolean descend) {
/* 100 */     return getList(conditions, pageNo, pageSize, order, descend, "from ProduceRejectedMaterial prm", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/hibernate/ProduceRejectedMaterialDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
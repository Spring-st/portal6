/*     */ package com.aof.dao.basic.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.basic.CostCenterDAO;
/*     */ import com.aof.model.basic.CostCenter;
/*     */ import com.aof.model.basic.query.CostCenterQueryCondition;
/*     */ import com.aof.model.basic.query.CostCenterQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.po.query.PurchaseOrderReceiptsQueryCondition;
/*     */ import java.util.HashMap;
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
/*     */ 
/*     */ public class CostCenterDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements CostCenterDAO
/*     */ {
/*  33 */   private Log log = LogFactory.getLog(CostCenterDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from CostCenter costCenter";
/*     */   
/*     */   private static final String SQL = "from CostCenter costCenter";
/*     */ 
/*     */   
/*     */   public CostCenter getCostCenter(Integer id) {
/*  41 */     if (id == null) {
/*  42 */       if (this.log.isDebugEnabled())
/*  43 */         this.log.debug("try to get CostCenter with null id"); 
/*  44 */       return null;
/*     */     } 
/*  46 */     return (CostCenter)getHibernateTemplate().get(CostCenter.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CostCenter updateCostCenter(CostCenter costCenter) {
/*  55 */     Integer id = costCenter.getId();
/*  56 */     if (id == null) {
/*  57 */       throw new RuntimeException("cannot save a costCenter with null id");
/*     */     }
/*  59 */     CostCenter oldCostCenter = getCostCenter(id);
/*  60 */     if (oldCostCenter != null) {
/*     */       try {
/*  62 */         PropertyUtils.copyProperties(oldCostCenter, costCenter);
/*  63 */       } catch (Exception e) {
/*  64 */         throw new RuntimeException("copy error:" + e);
/*     */       } 
/*  66 */       getHibernateTemplate().update(oldCostCenter);
/*  67 */       return oldCostCenter;
/*     */     } 
/*  69 */     throw new RuntimeException("costCenter not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CostCenter insertCostCenter(CostCenter costCenter) {
/*  79 */     getHibernateTemplate().save(costCenter);
/*  80 */     return costCenter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  88 */       { CostCenterQueryCondition.ID_EQ, "costCenter.id = ?" }
/*     */     };
/*     */   
/*  91 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  92 */       { CostCenterQueryOrder.ID, "costCenter.id"
/*  93 */       }, { CostCenterQueryOrder.ENABLED, "costCenter.enabled" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCostCenterListCount(Map conditions) {
/* 102 */     return getListCount(conditions, "select count(*) from CostCenter costCenter", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getCostCenterList(Map conditions, int pageNo, int pageSize, CostCenterQueryOrder order, boolean descend) {
/* 112 */     return getList(conditions, pageNo, pageSize, order, descend, "from CostCenter costCenter", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledCostCenterList() {
/* 121 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 122 */     conds.put(CostCenterQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 123 */     return getCostCenterList(conds, 0, -1, CostCenterQueryOrder.ID, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteCostCenter(CostCenter costCenter) {
/* 128 */     getHibernateTemplate().delete(costCenter);
/*     */   }
/*     */   
/*     */   public String getMaxCostCenterCodeBeginWith(String prefix) {
/* 132 */     String sql = "select max(po.code) from CostCenter po";
/* 133 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 134 */     conds.put(PurchaseOrderReceiptsQueryCondition.ID_BEGINWITH, prefix);
/* 135 */     List<String> l = getList(conds, 0, -1, null, false, sql, QUERY_CONDITIONS, 
/* 136 */         QUERY_ORDERS);
/* 137 */     if (l.isEmpty()) {
/* 138 */       return null;
/*     */     }
/* 140 */     return l.get(0);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/hibernate/CostCenterDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
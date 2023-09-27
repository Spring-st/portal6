/*     */ package com.aof.dao.kpi.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.kpi.KPISummaryDAO;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.kpi.KPIExpenseCategorySummary;
/*     */ import com.aof.model.kpi.KPIPurchaseCategorySummary;
/*     */ import com.aof.model.kpi.KPISummary;
/*     */ import com.aof.model.kpi.query.KPISummaryQueryCondition;
/*     */ import com.aof.model.kpi.query.KPISummaryQueryOrder;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.Hibernate;
/*     */ import net.sf.hibernate.type.Type;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class KPISummaryDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements KPISummaryDAO {
/*  22 */   private Log log = LogFactory.getLog(KPISummaryDAOHibernate.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String SQL_TOP5_EXPENSE_CATEGORY = "select kecs.expenseCategory.description, sum(kecs.expenseRequestCreatedToday) from KPIExpenseCategorySummary as kecs where kecs.site.id = ? group by kecs.expenseCategory.description order by sum(kecs.expenseRequestCreatedToday) DESC ";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String SQL_TOP5_PURCHASE_CATEGORY = "select kpcs.purchaseCategory.description, sum(kpcs.purchaseRequestCreatedToday) from KPIPurchaseCategorySummary as kpcs where kpcs.site.id = ? group by kpcs.purchaseCategory.description order by sum(kpcs.purchaseRequestCreatedToday) DESC ";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String SQL = "from KPISummary kpiSummary";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  43 */       { KPISummaryQueryCondition.ID_EQ, "kpiSummary.id = ?"
/*  44 */       }, { KPISummaryQueryCondition.SUMMARYDATE_EQ, "kpiSummary.summaryDate = ?"
/*  45 */       }, { KPISummaryQueryCondition.SUMMARYDATE_GE, "kpiSummary.summaryDate >= ?"
/*  46 */       }, { KPISummaryQueryCondition.SUMMARYDATE_LE, "kpiSummary.summaryDate <= ?" }
/*     */     };
/*     */   
/*  49 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  50 */       { KPISummaryQueryOrder.ID, "kpiSummary.id"
/*  51 */       }, { KPISummaryQueryOrder.SUMMARYDATE, "kpiSummary.summaryDate"
/*  52 */       }, { KPISummaryQueryOrder.LOGINUSERCOUNT, "kpiSummary.loginUserCount"
/*  53 */       }, { KPISummaryQueryOrder.CLOSEDREQUESTNUMTODAY, "kpiSummary.closedRequestNumToday"
/*  54 */       }, { KPISummaryQueryOrder.AVGREQUESTCLOSEDDAYS, "kpiSummary.avgRequestClosedDays"
/*  55 */       }, { KPISummaryQueryOrder.CREATEDREQUESTNUMTODAY, "kpiSummary.createdRequestNumToday" }
/*     */     };
/*     */   
/*     */   public KPISummary getKPISummary(Integer id) {
/*  59 */     if (id == null) {
/*  60 */       if (this.log.isDebugEnabled())
/*  61 */         this.log.debug("try to get KPISummary with null id"); 
/*  62 */       return null;
/*     */     } 
/*  64 */     return (KPISummary)getHibernateTemplate().get(KPISummary.class, id);
/*     */   }
/*     */   
/*     */   public KPISummary insertKPISummary(KPISummary kpiSummary) {
/*  68 */     getHibernateTemplate().save(kpiSummary);
/*  69 */     return kpiSummary;
/*     */   }
/*     */   
/*     */   public KPISummary updateKPISummary(KPISummary kpiSummary) {
/*  73 */     Integer id = kpiSummary.getId();
/*  74 */     if (id == null) {
/*  75 */       throw new RuntimeException("cannot save a KPISummary with null id");
/*     */     }
/*  77 */     KPISummary oldKPISummary = getKPISummary(id);
/*  78 */     if (oldKPISummary != null) {
/*     */       try {
/*  80 */         PropertyUtils.copyProperties(oldKPISummary, kpiSummary);
/*  81 */       } catch (Exception e) {
/*  82 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  84 */       getHibernateTemplate().update(oldKPISummary);
/*  85 */       return oldKPISummary;
/*     */     } 
/*  87 */     throw new RuntimeException("KPISummary not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteKPISummary(KPISummary kpiSummary) {
/*  92 */     getHibernateTemplate().delete(kpiSummary);
/*     */   }
/*     */   
/*     */   public KPIPurchaseCategorySummary getKPIPurchaseCategorySummary(Integer id) {
/*  96 */     if (id == null) {
/*  97 */       if (this.log.isDebugEnabled())
/*  98 */         this.log.debug("try to get KPIPurchaseCategorySummary with null id"); 
/*  99 */       return null;
/*     */     } 
/* 101 */     return (KPIPurchaseCategorySummary)getHibernateTemplate().get(KPIPurchaseCategorySummary.class, id);
/*     */   }
/*     */   
/*     */   public KPIPurchaseCategorySummary insertKPIPurchaseCategorySummary(KPIPurchaseCategorySummary kpiPurchaseCategorySummary) {
/* 105 */     getHibernateTemplate().save(kpiPurchaseCategorySummary);
/* 106 */     return kpiPurchaseCategorySummary;
/*     */   }
/*     */   
/*     */   public KPIPurchaseCategorySummary updateKPIPurchaseCategorySummary(KPIPurchaseCategorySummary kpiPurchaseCategorySummary) {
/* 110 */     Integer id = kpiPurchaseCategorySummary.getId();
/* 111 */     if (id == null) {
/* 112 */       throw new RuntimeException("cannot save a KPIPurchaseCategorySummary with null id");
/*     */     }
/* 114 */     KPIPurchaseCategorySummary oldKPIPurchaseCategorySummary = getKPIPurchaseCategorySummary(id);
/* 115 */     if (oldKPIPurchaseCategorySummary != null) {
/*     */       try {
/* 117 */         PropertyUtils.copyProperties(oldKPIPurchaseCategorySummary, kpiPurchaseCategorySummary);
/* 118 */       } catch (Exception e) {
/* 119 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/* 121 */       getHibernateTemplate().update(oldKPIPurchaseCategorySummary);
/* 122 */       return oldKPIPurchaseCategorySummary;
/*     */     } 
/* 124 */     throw new RuntimeException("KPIPurchaseCategorySummary not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteKPIPurchaseCategorySummary(KPIPurchaseCategorySummary kpiPurchaseCategorySummary) {
/* 129 */     getHibernateTemplate().delete(kpiPurchaseCategorySummary);
/*     */   }
/*     */   
/*     */   public KPIExpenseCategorySummary getKPIExpenseCategorySummary(Integer id) {
/* 133 */     if (id == null) {
/* 134 */       if (this.log.isDebugEnabled())
/* 135 */         this.log.debug("try to get KPIExpenseCategorySummary with null id"); 
/* 136 */       return null;
/*     */     } 
/* 138 */     return (KPIExpenseCategorySummary)getHibernateTemplate().get(KPIExpenseCategorySummary.class, id);
/*     */   }
/*     */   
/*     */   public KPIExpenseCategorySummary insertKPIExpenseCategorySummary(KPIExpenseCategorySummary kpiExpenseCategorySummary) {
/* 142 */     getHibernateTemplate().save(kpiExpenseCategorySummary);
/* 143 */     return kpiExpenseCategorySummary;
/*     */   }
/*     */   
/*     */   public KPIExpenseCategorySummary updateKPIExpenseCategorySummary(KPIExpenseCategorySummary kpiExpenseCategorySummary) {
/* 147 */     Integer id = kpiExpenseCategorySummary.getId();
/* 148 */     if (id == null) {
/* 149 */       throw new RuntimeException("cannot save a KPIExpenseCategorySummary with null id");
/*     */     }
/* 151 */     KPIExpenseCategorySummary oldKPIExpenseCategorySummary = getKPIExpenseCategorySummary(id);
/* 152 */     if (oldKPIExpenseCategorySummary != null) {
/*     */       try {
/* 154 */         PropertyUtils.copyProperties(oldKPIExpenseCategorySummary, kpiExpenseCategorySummary);
/* 155 */       } catch (Exception e) {
/* 156 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/* 158 */       getHibernateTemplate().update(oldKPIExpenseCategorySummary);
/* 159 */       return oldKPIExpenseCategorySummary;
/*     */     } 
/* 161 */     throw new RuntimeException("KPIExpenseCategorySummary not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteKPIExpenseCategorySummary(KPIExpenseCategorySummary kpiExpenseCategorySummary) {
/* 166 */     getHibernateTemplate().delete(kpiExpenseCategorySummary);
/*     */   }
/*     */   
/*     */   public List getKPISummaryList(Map conditions, int pageNo, int pageSize, KPISummaryQueryOrder order, boolean descend) {
/* 170 */     return getList(conditions, pageNo, pageSize, order, descend, "from KPISummary kpiSummary", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */   
/*     */   public List getTop5KPIExpenseCategorySummary(Site site) {
/* 174 */     return getHibernateTemplate().find("select kecs.expenseCategory.description, sum(kecs.expenseRequestCreatedToday) from KPIExpenseCategorySummary as kecs where kecs.site.id = ? group by kecs.expenseCategory.description order by sum(kecs.expenseRequestCreatedToday) DESC ", site.getId(), (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public List getTop5KPIPurchaseCategorySummary(Site site) {
/* 178 */     return getHibernateTemplate().find("select kpcs.purchaseCategory.description, sum(kpcs.purchaseRequestCreatedToday) from KPIPurchaseCategorySummary as kpcs where kpcs.site.id = ? group by kpcs.purchaseCategory.description order by sum(kpcs.purchaseRequestCreatedToday) DESC ", site.getId(), (Type)Hibernate.INTEGER);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/kpi/hibernate/KPISummaryDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
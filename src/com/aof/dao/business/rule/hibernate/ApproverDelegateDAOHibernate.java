/*     */ package com.aof.dao.business.rule.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.business.rule.ApproverDelegateDAO;
/*     */ import com.aof.model.business.rule.ApproverDelegate;
/*     */ import com.aof.model.business.rule.query.ApproverDelegateQueryCondition;
/*     */ import com.aof.model.business.rule.query.ApproverDelegateQueryOrder;
/*     */ import com.aof.model.metadata.ApproverDelegateType;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.Hibernate;
/*     */ import net.sf.hibernate.type.Type;
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
/*     */ public class ApproverDelegateDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements ApproverDelegateDAO
/*     */ {
/*  34 */   private Log log = LogFactory.getLog(ApproverDelegateDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from ApproverDelegate approverDelegate";
/*     */   private static final String SQL = "from ApproverDelegate approverDelegate";
/*     */   
/*     */   public ApproverDelegate getApproverDelegate(Integer id) {
/*  40 */     if (id == null) {
/*  41 */       if (this.log.isDebugEnabled())
/*  42 */         this.log.debug("try to get ApproverDelegate with null id"); 
/*  43 */       return null;
/*     */     } 
/*  45 */     return (ApproverDelegate)getHibernateTemplate().get(ApproverDelegate.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ApproverDelegate updateApproverDelegate(ApproverDelegate approverDelegate) {
/*  52 */     Integer id = approverDelegate.getId();
/*  53 */     if (id == null) {
/*  54 */       throw new RuntimeException("cannot save a approverDelegate with null id");
/*     */     }
/*  56 */     ApproverDelegate oldApproverDelegate = getApproverDelegate(id);
/*  57 */     if (oldApproverDelegate != null) {
/*     */       try {
/*  59 */         PropertyUtils.copyProperties(oldApproverDelegate, approverDelegate);
/*  60 */       } catch (Exception e) {
/*  61 */         throw new RuntimeException(e);
/*     */       } 
/*  63 */       getHibernateTemplate().update(oldApproverDelegate);
/*  64 */       return oldApproverDelegate;
/*     */     } 
/*  66 */     throw new RuntimeException("approverDelegate not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ApproverDelegate insertApproverDelegate(ApproverDelegate approverDelegate) {
/*  74 */     getHibernateTemplate().save(approverDelegate);
/*  75 */     return approverDelegate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*     */       {
/*  84 */         ApproverDelegateQueryCondition.ID_EQ, "approverDelegate.id = ?"
/*  85 */       }, { ApproverDelegateQueryCondition.ORIGINALAPPROVER_ID_EQ, "approverDelegate.originalApprover.id = ?"
/*  86 */       }, { ApproverDelegateQueryCondition.DELEGATEAPPROVER_ID_EQ, "approverDelegate.delegateApprover.id = ?"
/*  87 */       }, { ApproverDelegateQueryCondition.TYPE_EQ, "approverDelegate.type = ?"
/*  88 */       }, { ApproverDelegateQueryCondition.FROMDATE_EQ, "approverDelegate.fromDate = ?"
/*  89 */       }, { ApproverDelegateQueryCondition.TODATE_EQ, "approverDelegate.toDate = ?"
/*  90 */       }, { ApproverDelegateQueryCondition.FROMDATE_GE, "approverDelegate.fromDate >= ?"
/*  91 */       }, { ApproverDelegateQueryCondition.FROMDATE_LT, "approverDelegate.fromDate < ?"
/*  92 */       }, { ApproverDelegateQueryCondition.TODATE_GE, "approverDelegate.toDate >= ?"
/*  93 */       }, { ApproverDelegateQueryCondition.TODATE_LT, "approverDelegate.toDate < ?" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 100 */       { ApproverDelegateQueryOrder.ID, "approverDelegate.id"
/* 101 */       }, { ApproverDelegateQueryOrder.TYPE, "approverDelegate.type"
/* 102 */       }, { ApproverDelegateQueryOrder.FROMDATE, "approverDelegate.fromDate"
/* 103 */       }, { ApproverDelegateQueryOrder.TODATE, "approverDelegate.toDate"
/* 104 */       }, { ApproverDelegateQueryOrder.DELEGATEAPPROVER_NAME, "approverDelegate.delegateApprover.name" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getApproverDelegateListCount(Map conditions) {
/* 111 */     return getListCount(conditions, "select count(*) from ApproverDelegate approverDelegate", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getApproverDelegateList(Map conditions, int pageNo, int pageSize, ApproverDelegateQueryOrder order, boolean descend) {
/* 119 */     return getList(conditions, pageNo, pageSize, order, descend, "from ApproverDelegate approverDelegate", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDelegateApprover(ApproverDelegateType approverDelegateType, Integer originalApproverId, Integer delegateApproverId) {
/* 126 */     long today = getTodayTimeMillis();
/* 127 */     List result = getHibernateTemplate().find(
/* 128 */         "from ApproverDelegate ad where ad.type=? and ad.originalApprover.id=? and ad.delegateApprover.id=?  and ((ad.fromDate is null or ad.fromDate<?) and (ad.toDate is null or ad.toDate>=?))", 
/*     */         
/* 130 */         new Object[] { approverDelegateType.getEnumCode(), originalApproverId, 
/* 131 */           delegateApproverId, new Date(today + 86400000L), new Date(today)
/* 132 */         }, new Type[] { (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER, (Type)Hibernate.DATE, (Type)Hibernate.DATE });
/* 133 */     return (result.size() > 0);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/business/rule/hibernate/ApproverDelegateDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
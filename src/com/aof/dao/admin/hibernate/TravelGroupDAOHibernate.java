/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.TravelGroupDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.TravelGroup;
/*     */ import com.aof.model.admin.query.TravelGroupQueryCondition;
/*     */ import com.aof.model.admin.query.TravelGroupQueryOrder;
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
/*     */ public class TravelGroupDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements TravelGroupDAO
/*     */ {
/*  30 */   private Log log = LogFactory.getLog(TravelGroupDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from TravelGroup travelGroup";
/*     */   
/*     */   private static final String SQL = "from TravelGroup travelGroup";
/*     */   
/*     */   public TravelGroup getTravelGroup(Integer id) {
/*  37 */     if (id == null) {
/*  38 */       if (this.log.isDebugEnabled()) this.log.debug("try to get TravelGroup with null id"); 
/*  39 */       return null;
/*     */     } 
/*  41 */     return (TravelGroup)getHibernateTemplate().get(TravelGroup.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TravelGroup updateTravelGroup(TravelGroup travelGroup) {
/*  48 */     Integer id = travelGroup.getId();
/*  49 */     if (id == null) {
/*  50 */       throw new RuntimeException("cannot save a travelGroup with null id");
/*     */     }
/*  52 */     TravelGroup oldTravelGroup = getTravelGroup(id);
/*  53 */     if (oldTravelGroup != null) {
/*     */       try {
/*  55 */         PropertyUtils.copyProperties(oldTravelGroup, travelGroup);
/*     */       }
/*  57 */       catch (Exception e) {
/*     */         
/*  59 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  61 */       getHibernateTemplate().update(oldTravelGroup);
/*  62 */       return oldTravelGroup;
/*     */     } 
/*     */ 
/*     */     
/*  66 */     throw new RuntimeException("travelGroup not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TravelGroup insertTravelGroup(TravelGroup travelGroup) {
/*  74 */     getHibernateTemplate().save(travelGroup);
/*  75 */     return travelGroup;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*     */       {
/*  84 */         TravelGroupQueryCondition.ID_EQ, "travelGroup.id = ?"
/*     */ 
/*     */       
/*     */       },
/*     */       
/*     */       {
/*     */         
/*  91 */         TravelGroupQueryCondition.SITE_ID_EQ, "travelGroup.site.id = ?"
/*     */       
/*     */       },
/*  94 */       { TravelGroupQueryCondition.NAME_LIKE, "travelGroup.name like ?", new LikeConvert()
/*  95 */       }, { TravelGroupQueryCondition.ENABLED_EQ, "travelGroup.enabled = ?" }
/*     */     };
/*     */   
/*  98 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*     */       {
/* 100 */         TravelGroupQueryOrder.ID, "travelGroup.id"
/*     */       
/*     */       },
/* 103 */       { TravelGroupQueryOrder.NAME, "travelGroup.name"
/* 104 */       }, { TravelGroupQueryOrder.ENABLED, "travelGroup.enabled" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTravelGroupListCount(Map conditions) {
/* 111 */     return getListCount(conditions, "select count(*) from TravelGroup travelGroup", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getTravelGroupList(Map conditions, int pageNo, int pageSize, TravelGroupQueryOrder order, boolean descend) {
/* 118 */     return getList(conditions, pageNo, pageSize, order, descend, "from TravelGroup travelGroup", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/TravelGroupDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.aof.dao.po.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.po.PoHighLineDAO;
/*     */ import com.aof.model.po.PoHighLineTwo;
/*     */ import com.aof.model.po.query.PoHighLineTwoQueryCondition;
/*     */ import com.aof.model.po.query.PoHighLineTwoQueryOrder;
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
/*     */ public class PoHighLineDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements PoHighLineDAO
/*     */ {
/*  27 */   private Log log = LogFactory.getLog(PoHighLineDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from PoHighLineOne poOne";
/*     */   
/*     */   private static final String SQL = "from PoHighLineOne poOne";
/*     */ 
/*     */   
/*     */   public PoHighLineTwo getPoHighLineTwo(Integer id) {
/*  35 */     if (id == null) {
/*  36 */       if (this.log.isDebugEnabled())
/*  37 */         this.log.debug("try to get PoHighLineTwo with null id"); 
/*  38 */       return null;
/*     */     } 
/*  40 */     return (PoHighLineTwo)getHibernateTemplate().get(PoHighLineTwo.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoHighLineTwo updatePoHighLineTwo(PoHighLineTwo poHighLineTwo) {
/*  49 */     Integer id = poHighLineTwo.getId();
/*  50 */     if (id == null) {
/*  51 */       throw new RuntimeException("cannot save a PoHighLineTwo with null id");
/*     */     }
/*  53 */     PoHighLineTwo oldPoHighLineTwo = getPoHighLineTwo(id);
/*  54 */     if (oldPoHighLineTwo != null) {
/*     */       try {
/*  56 */         PropertyUtils.copyProperties(oldPoHighLineTwo, poHighLineTwo);
/*  57 */       } catch (Exception e) {
/*  58 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/*  60 */       getHibernateTemplate().update(oldPoHighLineTwo);
/*  61 */       return oldPoHighLineTwo;
/*     */     } 
/*  63 */     throw new RuntimeException("PoHighLineTwo not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoHighLineTwo insertPoHighLineTwo(PoHighLineTwo poHighLineTwo) {
/*  73 */     getHibernateTemplate().save(poHighLineTwo);
/*  74 */     getHibernateTemplate().flush();
/*  75 */     return poHighLineTwo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  83 */       { PoHighLineTwoQueryCondition.ID_EQ, "poOne.id = ?" }
/*     */     };
/*     */   
/*  86 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  87 */       { PoHighLineTwoQueryOrder.ID, "poOne.id" }
/*     */     };
/*     */   
/*     */   private static final String SQL_COUNT_TWO = "select count(*) from PoHighLineTwo poTwo";
/*     */   
/*     */   private static final String SQL_TWO = "from PoHighLineTwo poTwo";
/*     */ 
/*     */   
/*     */   public int getPoHighLineTwoListCount(Map conditions) {
/*  96 */     return getListCount(conditions, "select count(*) from PoHighLineTwo poTwo", QUERY_CONDITIONS_TWO);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPoHighLineTwoList(Map conditions, int pageNo, int pageSize, PoHighLineTwoQueryOrder order, boolean descend) {
/* 102 */     return getList(conditions, pageNo, pageSize, order, descend, "from PoHighLineTwo poTwo", QUERY_CONDITIONS_TWO, QUERY_ORDERS_TWO);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   private static final Object[][] QUERY_CONDITIONS_TWO = new Object[][] {
/* 110 */       { PoHighLineTwoQueryCondition.ID_EQ, "poTwo.id = ?" }
/*     */     };
/*     */   
/* 113 */   private static final Object[][] QUERY_ORDERS_TWO = new Object[][] {
/* 114 */       { PoHighLineTwoQueryOrder.ID, "poTwo.id" } };
/*     */   private static final String SQL_COUNT_BELOW = "select count(*) from PoBelowLine pbl";
/*     */   
/*     */   public int getPoHighLineOneListCount(Map conditions) {
/* 118 */     return getListCount(conditions, "select count(*) from PoHighLineOne poOne", QUERY_CONDITIONS);
/*     */   }
/*     */   
/*     */   private static final String SQL_BELOW = "from PoHighLineTwo pbl";
/*     */   
/*     */   public List getPoHighLineOneList(Map conditions, int pageNo, int pageSize, PoHighLineTwoQueryOrder order, boolean descend) {
/* 124 */     return getList(conditions, pageNo, pageSize, order, descend, "from PoHighLineOne poOne", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   private static final Object[][] QUERY_CONDITIONS_BELOW = new Object[][] {
/* 132 */       { PoHighLineTwoQueryCondition.ID_EQ, "pbl.id = ?" }
/*     */     };
/*     */   
/* 135 */   private static final Object[][] QUERY_ORDERS_BELOW = new Object[][] {
/* 136 */       { PoHighLineTwoQueryOrder.ID, "pbl.id" }
/*     */     };
/*     */   
/*     */   public int getPoHighLineBelowListCount(Map conditions) {
/* 140 */     return getListCount(conditions, "select count(*) from PoBelowLine pbl", QUERY_CONDITIONS_BELOW);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPoHighLineBelowList(Map conditions, int pageNo, int pageSize, PoHighLineTwoQueryOrder order, boolean descend) {
/* 146 */     return getList(conditions, pageNo, pageSize, order, descend, "from PoHighLineTwo pbl", QUERY_CONDITIONS_BELOW, QUERY_ORDERS_BELOW);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/hibernate/PoHighLineDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.aof.dao.basic.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.basic.BasicPartLocationDAO;
/*     */ import com.aof.model.basic.BasicPartLocation;
/*     */ import com.aof.model.basic.query.BasicPartLocationQueryCondition;
/*     */ import com.aof.model.basic.query.BasicPartLocationQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
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
/*     */ public class BasicPartLocationDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements BasicPartLocationDAO
/*     */ {
/*  31 */   private Log log = LogFactory.getLog(BasicPartLocationDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from BasicPartLocation br";
/*     */   
/*     */   private static final String SQL = "from BasicPartLocation br";
/*     */ 
/*     */   
/*     */   public BasicPartLocation getBasicPartLocation(Integer id) {
/*  39 */     if (id == null) {
/*  40 */       if (this.log.isDebugEnabled())
/*  41 */         this.log.debug("try to get BasicPartLocation with null id"); 
/*  42 */       return null;
/*     */     } 
/*  44 */     return (BasicPartLocation)getHibernateTemplate().get(BasicPartLocation.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicPartLocation updateBasicPartLocation(BasicPartLocation sr) {
/*  53 */     Integer id = sr.getId();
/*  54 */     if (id == null) {
/*  55 */       throw new RuntimeException("cannot save a sr with null id");
/*     */     }
/*  57 */     BasicPartLocation oldBasicPartLocation = getBasicPartLocation(id);
/*  58 */     if (oldBasicPartLocation != null) {
/*     */       try {
/*  60 */         PropertyUtils.copyProperties(oldBasicPartLocation, sr);
/*  61 */       } catch (Exception e) {
/*  62 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  64 */       getHibernateTemplate().update(oldBasicPartLocation);
/*  65 */       return oldBasicPartLocation;
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
/*     */   public BasicPartLocation insertBasicPartLocation(BasicPartLocation sr) {
/*  77 */     getHibernateTemplate().save(sr);
/*  78 */     return sr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  86 */       { BasicPartLocationQueryCondition.ID_EQ, "br.id = ?" }
/*     */     };
/*     */ 
/*     */   
/*  90 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  91 */       { BasicPartLocationQueryOrder.ID, "br.id"
/*  92 */       }, { BasicPartLocationQueryOrder.ENABLED, "br.status" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBasicPartLocationListCount(Map conditions) {
/* 101 */     return getListCount(conditions, "select count(*) from BasicPartLocation br", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getBasicPartLocationList(Map conditions, int pageNo, int pageSize, BasicPartLocationQueryOrder order, boolean descend) {
/* 111 */     return getList(conditions, pageNo, pageSize, order, descend, "from BasicPartLocation br", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledBasicPartLocationList() {
/* 120 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 121 */     conds.put(BasicPartLocationQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 122 */     return getBasicPartLocationList(conds, 0, -1, BasicPartLocationQueryOrder.ID, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteBasicPartLocation(BasicPartLocation sr) {
/* 127 */     getHibernateTemplate().delete(sr);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/hibernate/BasicPartLocationDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
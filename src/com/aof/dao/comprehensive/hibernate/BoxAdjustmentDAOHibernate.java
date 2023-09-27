/*     */ package com.aof.dao.comprehensive.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.comprehensive.BoxAdjustmentDAO;
/*     */ import com.aof.dao.convert.QueryParameterConvert;
/*     */ import com.aof.model.comprehensive.BoxAdjustment;
/*     */ import com.aof.model.comprehensive.BoxAdjustmentBox;
/*     */ import com.aof.model.comprehensive.query.BoxAdjustmentQueryCondition;
/*     */ import com.aof.model.comprehensive.query.BoxAdjustmentQueryOrder;
/*     */ import java.util.HashMap;
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
/*     */ public class BoxAdjustmentDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements BoxAdjustmentDAO
/*     */ {
/*  33 */   private Log log = LogFactory.getLog(BoxAdjustmentDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from BoxAdjustment ba";
/*     */   
/*     */   private static final String SQL = "from BoxAdjustment ba";
/*     */ 
/*     */   
/*     */   public BoxAdjustment getBoxAdjustment(Integer id) {
/*  41 */     if (id == null) {
/*  42 */       if (this.log.isDebugEnabled())
/*  43 */         this.log.debug("try to get BoxAdjustment with null id"); 
/*  44 */       return null;
/*     */     } 
/*  46 */     return (BoxAdjustment)getHibernateTemplate().get(BoxAdjustment.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoxAdjustment updateBoxAdjustment(BoxAdjustment ct) {
/*  55 */     Integer id = ct.getId();
/*  56 */     if (id == null) {
/*  57 */       throw new RuntimeException("cannot save a BoxAdjustment with null id");
/*     */     }
/*  59 */     BoxAdjustment oldBoxAdjustment = getBoxAdjustment(id);
/*  60 */     if (oldBoxAdjustment != null) {
/*     */       try {
/*  62 */         PropertyUtils.copyProperties(oldBoxAdjustment, ct);
/*  63 */       } catch (Exception e) {
/*  64 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/*  66 */       getHibernateTemplate().update(oldBoxAdjustment);
/*  67 */       return oldBoxAdjustment;
/*     */     } 
/*  69 */     throw new RuntimeException("BoxAdjustment not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoxAdjustment insertBoxAdjustment(BoxAdjustment ct) {
/*  79 */     getHibernateTemplate().save(ct);
/*  80 */     return ct;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  88 */       { BoxAdjustmentQueryCondition.ID_EQ, "ba.id = ?"
/*  89 */       }, { BoxAdjustmentQueryCondition.CODE_EQ, "ba.code = ?"
/*  90 */       }, { BoxAdjustmentQueryCondition.ENDTIME_EQ, "ba.date <= ?"
/*  91 */       }, { BoxAdjustmentQueryCondition.STATUS_EQ, "ba.status = ?"
/*  92 */       }, { BoxAdjustmentQueryCondition.USER_EQ, "ba.userName.id = ?"
/*  93 */       }, { BoxAdjustmentQueryCondition.ID_BEGINWITH, 
/*  94 */         "po.code like ?", new QueryParameterConvert() {
/*     */           public Object convert(Object src) {
/*  96 */             return src + "%";
/*     */           }
/*     */         } }
/*     */     };
/*     */   
/* 101 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 102 */       { BoxAdjustmentQueryOrder.ID, "ba.id" }
/*     */     };
/*     */ 
/*     */   
/*     */   private static final String SQL_COUNT2 = "select count(*) from BoxAdjustmentBox bab";
/*     */   
/*     */   private static final String SQL2 = "from BoxAdjustmentBox bab";
/*     */   
/* 110 */   private static final Object[][] QUERY_CONDITIONS2 = new Object[][] {
/* 111 */       { BoxAdjustmentQueryCondition.ID_EQ, "bab.id = ?" }
/*     */     };
/*     */   
/* 114 */   private static final Object[][] QUERY_ORDERS2 = new Object[][] {
/* 115 */       { BoxAdjustmentQueryOrder.ID, "bab.id" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBoxAdjustmentListCount(Map conditions) {
/* 125 */     return getListCount(conditions, "select count(*) from BoxAdjustment ba", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getBoxAdjustmentList(Map conditions, int pageNo, int pageSize, BoxAdjustmentQueryOrder order, boolean descend) {
/* 135 */     return getList(conditions, pageNo, pageSize, order, descend, "from BoxAdjustment ba", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */   
/*     */   public String getMaxBoxAdjustmentIdBeginWith(String prefix) {
/* 139 */     String sql = "select max(ba.code) from BoxAdjustment ba";
/* 140 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 141 */     conds.put(BoxAdjustmentQueryCondition.ID_BEGINWITH, prefix);
/* 142 */     List<String> l = getList(conds, 0, -1, null, false, sql, QUERY_CONDITIONS, QUERY_ORDERS);
/* 143 */     if (l.isEmpty()) {
/* 144 */       return null;
/*     */     }
/* 146 */     return l.get(0);
/*     */   }
/*     */   
/*     */   public List<BoxAdjustmentBox> getBoxAdjustmentBoxByMain(Integer id) {
/* 150 */     String sql = "from BoxAdjustmentBox bab where bab.box_adjustment_id.id = ? ";
/* 151 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public int getBoxAdjustmentBoxListCount(Map conditions) {
/* 155 */     return getListCount(conditions, "select count(*) from BoxAdjustmentBox bab", QUERY_CONDITIONS2);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getBoxAdjustmentBoxList(Map conditions, int pageNo, int pageSize, BoxAdjustmentQueryOrder order, boolean descend) {
/* 160 */     return getList(conditions, pageNo, pageSize, order, descend, "from BoxAdjustmentBox bab", QUERY_CONDITIONS2, QUERY_ORDERS2);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/comprehensive/hibernate/BoxAdjustmentDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
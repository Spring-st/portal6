/*     */ package com.aof.dao.basic.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.basic.WmsPartDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.basic.query.WmsPartQueryCondition;
/*     */ import com.aof.model.basic.query.WmsPartQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
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
/*     */ 
/*     */ public class WmsPartDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements WmsPartDAO
/*     */ {
/*  34 */   private Log log = LogFactory.getLog(WmsPartDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from WmsPart srt";
/*     */   
/*     */   private static final String SQL = "from WmsPart srt";
/*     */ 
/*     */   
/*     */   public WmsPart getWmsPart(String id) {
/*  42 */     if (id == null) {
/*  43 */       if (this.log.isDebugEnabled())
/*  44 */         this.log.debug("try to get WmsPart with null id"); 
/*  45 */       return null;
/*     */     } 
/*  47 */     return (WmsPart)getHibernateTemplate().get(WmsPart.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WmsPart updateWmsPart(WmsPart srt) {
/*  56 */     String id = srt.getId();
/*  57 */     if (id == null) {
/*  58 */       throw new RuntimeException("cannot save a srt with null id");
/*     */     }
/*  60 */     WmsPart oldWmsPart = getWmsPart(id);
/*  61 */     if (oldWmsPart != null) {
/*     */       try {
/*  63 */         PropertyUtils.copyProperties(oldWmsPart, srt);
/*  64 */       } catch (Exception e) {
/*  65 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  67 */       getHibernateTemplate().update(oldWmsPart);
/*  68 */       return oldWmsPart;
/*     */     } 
/*  70 */     throw new RuntimeException("srt not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WmsPart insertWmsPart(WmsPart srt) {
/*  80 */     getHibernateTemplate().save(srt);
/*  81 */     return srt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  89 */       { WmsPartQueryCondition.ID_EQ, "srt.id like ?", new LikeConvert()
/*  90 */       }, { WmsPartQueryCondition.NAME_EQ, "srt.name = ?"
/*  91 */       }, { WmsPartQueryCondition.ENABLED_EQ, "srt.enabled = ?"
/*  92 */       }, { WmsPartQueryCondition.QUALITYPE, "srt.productClass = ?"
/*  93 */       }, { WmsPartQueryCondition.IDEQ, "srt.id = ?"
/*  94 */       }, { WmsPartQueryCondition.FREEZE_STATUS_EQ, "srt.freeze_status = ?"
/*  95 */       }, { WmsPartQueryCondition.PRODUCTCLASS_EQ, "srt.productClass = ?"
/*  96 */       }, { WmsPartQueryCondition.SUPPLIER_EQ, "srt.vend =?"
/*  97 */       }, { WmsPartQueryCondition.ASN_EQ, " srt.productSpecifications = ?" }
/*     */     };
/*     */   
/* 100 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 101 */       { WmsPartQueryOrder.ID, "srt.id"
/* 102 */       }, { WmsPartQueryOrder.ENABLED, "srt.enabled" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWmsPartListCount(Map conditions) {
/* 111 */     return getListCount(conditions, "select count(*) from WmsPart srt", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getWmsPartList(Map conditions, int pageNo, int pageSize, WmsPartQueryOrder order, boolean descend) {
/* 121 */     return getList(conditions, pageNo, pageSize, order, descend, "from WmsPart srt", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledWmsPartList() {
/* 130 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 131 */     conds.put(WmsPartQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 132 */     return getWmsPartList(conds, 0, -1, WmsPartQueryOrder.ID, false);
/*     */   }
/*     */   
/*     */   public void deleteWmsPart(WmsPart srt) {
/* 136 */     getHibernateTemplate().delete(srt);
/*     */   }
/*     */   
/*     */   public boolean isExits(String workCenter, String partCode) {
/* 140 */     String HQL = "from WmsPart wp where wp.workCenter='" + workCenter + "' and wp.id='" + partCode + "'";
/* 141 */     List<WmsPart> list = getHibernateTemplate().find(HQL);
/* 142 */     return (list.size() > 0);
/*     */   }
/*     */   
/*     */   public boolean validatePartIsFreeze(String part) {
/* 146 */     String sql = "from WmsPart part where part.id = ? and part.freeze_status = 1";
/* 147 */     List list = getHibernateTemplate().find(sql, part, (Type)Hibernate.STRING);
/* 148 */     if (list == null || list.isEmpty()) {
/* 149 */       return false;
/*     */     }
/* 151 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/hibernate/WmsPartDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
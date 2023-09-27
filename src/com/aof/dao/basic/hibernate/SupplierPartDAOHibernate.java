/*     */ package com.aof.dao.basic.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.basic.SupplierPartDAO;
/*     */ import com.aof.model.basic.SupplierPart;
/*     */ import com.aof.model.basic.query.SupplierPartQueryCondition;
/*     */ import com.aof.model.basic.query.SupplierPartQueryOrder;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.Hibernate;
/*     */ import net.sf.hibernate.type.Type;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.springframework.orm.hibernate.HibernateTemplate;
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
/*     */ 
/*     */ public class SupplierPartDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements SupplierPartDAO
/*     */ {
/*  34 */   private Log log = LogFactory.getLog(SupplierPartDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from SupplierPart ud";
/*     */   private static final String SQL = "from SupplierPart ud";
/*     */   
/*     */   public SupplierPart getSupplierPart(Integer id) {
/*  40 */     if (id == null) {
/*  41 */       if (this.log.isDebugEnabled())
/*  42 */         this.log.debug("try to get supplierPart with null id"); 
/*  43 */       return null;
/*     */     } 
/*  45 */     return (SupplierPart)getHibernateTemplate().get(SupplierPart.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SupplierPart saveSupplierPart(SupplierPart ud) {
/*  53 */     HibernateTemplate template = getHibernateTemplate();
/*  54 */     template.save(ud);
/*  55 */     template.flush();
/*  56 */     template.evict(ud);
/*  57 */     return getSupplierPart(ud.getId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SupplierPart updateSupplierPart(SupplierPart ud) {
/*  64 */     HibernateTemplate template = getHibernateTemplate();
/*  65 */     template.update(ud);
/*  66 */     template.flush();
/*     */ 
/*     */     
/*  69 */     return getSupplierPart(ud.getId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeSupplierPart(SupplierPart ur) {
/*  76 */     getHibernateTemplate().delete(ur);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  84 */       { SupplierPartQueryCondition.ID_EQ, "ud.id = ?"
/*  85 */       }, { SupplierPartQueryCondition.PART_ID_EQ, "ud.partId.id = ?"
/*  86 */       }, { SupplierPartQueryCondition.SUPPLIER_ID_EQ, "ud.supplierId.id = ?"
/*  87 */       }, { SupplierPartQueryCondition.SUPPLIER_PART_EQ, "ud.supplierPart = ?" }
/*     */     };
/*     */   
/*  90 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  91 */       { SupplierPartQueryOrder.ID, "ud.id" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSupplierPartListCount(Map conditions) {
/*  99 */     return getListCount(conditions, "select count(*) from SupplierPart ud", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getSupplierPartList(Map conditions, int pageNo, int pageSize, SupplierPartQueryOrder order, boolean descend) {
/* 106 */     return getList(conditions, pageNo, pageSize, order, descend, "from SupplierPart ud", 
/* 107 */         QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List supplierPart(Integer seq) {
/* 117 */     String hql = "from SupplierPart u where u.seq >" + seq;
/* 118 */     return getHibernateTemplate().find(hql);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SupplierPart supplierPart(Integer supplier, String partId) {
/* 124 */     String hql = "from SupplierPart u where u.supplierId.id=" + supplier + " and u.partId.id='" + partId + "'";
/* 125 */     List<SupplierPart> list = getHibernateTemplate().find(hql);
/* 126 */     if (list.size() == 0) {
/* 127 */       return null;
/*     */     }
/* 129 */     return list.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BigDecimal getSupplierPartReturnIsRqc(String supp, String part) {
/* 135 */     String sql = "from SupplierPart sup where sup.supplierId.code=? and sup.partId.id = ?";
/* 136 */     Object[] param = { supp, part };
/* 137 */     List<SupplierPart> list = getHibernateTemplate().find(sql, param);
/* 138 */     if (list == null || list.isEmpty()) {
/* 139 */       return new BigDecimal(0);
/*     */     }
/* 141 */     return ((SupplierPart)list.get(0)).getSampling_rate();
/*     */   }
/*     */   
/*     */   public List<SupplierPart> getSupplierPart(String partId) {
/* 145 */     String hql = "from SupplierPart sp where sp.partId.id=?";
/* 146 */     return getHibernateTemplate().find(hql, partId, (Type)Hibernate.STRING);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/hibernate/SupplierPartDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
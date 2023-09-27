/*     */ package com.aof.dao.po.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.dao.convert.QueryParameterConvert;
/*     */ import com.aof.dao.po.PurchaseOrderDAO;
/*     */ import com.aof.model.po.PurchaseOrder;
/*     */ import com.aof.model.po.PurchaseOrderDetial;
/*     */ import com.aof.model.po.query.PurchaseOrderQueryCondition;
/*     */ import com.aof.model.po.query.PurchaseOrderQueryOrder;
/*     */ import java.util.Date;
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
/*     */ public class PurchaseOrderDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements PurchaseOrderDAO
/*     */ {
/*  35 */   private Log log = LogFactory.getLog(PurchaseOrderDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from PurchaseOrder po";
/*     */   
/*     */   private static final String SQL = "from PurchaseOrder po";
/*     */ 
/*     */   
/*     */   public PurchaseOrder getPurchaseOrder(String id) {
/*  43 */     if (id == null) {
/*  44 */       if (this.log.isDebugEnabled())
/*  45 */         this.log.debug("try to get PurchaseOrder with null id"); 
/*  46 */       return null;
/*     */     } 
/*  48 */     return (PurchaseOrder)getHibernateTemplate().get(PurchaseOrder.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrder updatePurchaseOrder(PurchaseOrder ct) {
/*  57 */     String id = ct.getPo_number();
/*  58 */     if (id == null) {
/*  59 */       throw new RuntimeException("cannot save a ct with null id");
/*     */     }
/*  61 */     PurchaseOrder oldPurchaseOrder = getPurchaseOrder(id);
/*  62 */     if (oldPurchaseOrder != null) {
/*     */       try {
/*  64 */         PropertyUtils.copyProperties(oldPurchaseOrder, ct);
/*  65 */       } catch (Exception e) {
/*  66 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/*  68 */       getHibernateTemplate().update(oldPurchaseOrder);
/*  69 */       return oldPurchaseOrder;
/*     */     } 
/*  71 */     throw new RuntimeException("ct not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public PurchaseOrder insertPurchaseOrder(PurchaseOrder ct) {
/*  76 */     getHibernateTemplate().save(ct);
/*  77 */     return ct;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  85 */       { PurchaseOrderQueryCondition.ID_EQ, "po.id = ?"
/*  86 */       }, { PurchaseOrderQueryCondition.CODE_EQ, "po.poOrder like ?", new LikeConvert()
/*  87 */       }, { PurchaseOrderQueryCondition.SITE_EQ, "po.site = ?"
/*  88 */       }, { PurchaseOrderQueryCondition.DEPARTMENT_EQ, "po.department = ?"
/*  89 */       }, { PurchaseOrderQueryCondition.STARTPODATE_EQ, "po.poDate >= ?"
/*  90 */       }, { PurchaseOrderQueryCondition.ENDPODATE_EQ, "po.poDate <= ?"
/*  91 */       }, { PurchaseOrderQueryCondition.SUPPLIER_EQ, "po.supplier.name like ?", new LikeConvert()
/*  92 */       }, { PurchaseOrderQueryCondition.SUPPLIERCODE_EQ, "po.supplier.code like ?", new LikeConvert()
/*  93 */       }, { PurchaseOrderQueryCondition.STATUS_EQ, "po.status = ?"
/*  94 */       }, { PurchaseOrderQueryCondition.ID_BEGINWITH, 
/*  95 */         "po.id like ?", new QueryParameterConvert() {
/*     */           public Object convert(Object src) {
/*  97 */             return src + "%";
/*     */           }
/*     */         } }
/*     */     };
/*     */   
/* 102 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 103 */       { PurchaseOrderQueryOrder.ID, "po.id" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPurchaseOrderListCount(Map conditions) {
/* 112 */     return getListCount(conditions, "select count(*) from PurchaseOrder po", QUERY_CONDITIONS);
/*     */   }
/*     */   public String getLastPoApplicationCode() {
/* 115 */     String result = getHibernateTemplate().find("select max(ta.id) from PurchaseOrder po where po.id like 'CA%'").get(0);
/*     */     
/* 117 */     return ((result == null) ? "CA000000" : (String.valueOf(result) + "000000")).substring(0, 8);
/*     */   }
/*     */   
/*     */   public String getMaxPoApplicationIdBeginWith(String prefix) {
/* 121 */     String sql = "select max(po.id) from PurchaseOrder po";
/* 122 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 123 */     conds.put(PurchaseOrderQueryCondition.ID_BEGINWITH, prefix);
/* 124 */     List<String> l = getList(conds, 0, -1, null, false, sql, QUERY_CONDITIONS, 
/* 125 */         QUERY_ORDERS);
/* 126 */     if (l.isEmpty()) {
/* 127 */       return null;
/*     */     }
/* 129 */     return l.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPurchaseOrderList(Map conditions, int pageNo, int pageSize, PurchaseOrderQueryOrder order, boolean descend) {
/* 137 */     return getList(conditions, pageNo, pageSize, order, descend, "from PurchaseOrder po", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledPurchaseOrderList() {
/* 146 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 147 */     return getPurchaseOrderList(conds, 0, -1, PurchaseOrderQueryOrder.ID, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deletePurchaseOrder(PurchaseOrder po) {
/* 152 */     getHibernateTemplate().delete(po);
/*     */   }
/*     */   
/*     */   public List<PurchaseOrderDetial> getPurchaseOrderItemListByOrderId(String id) {
/* 156 */     String sql = "from PurchaseOrderDetial poi where poi.poNumber.po_number = ?";
/* 157 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.STRING);
/*     */   }
/*     */   
/*     */   public List getPurchaseOrderInspectionPendingItemListByPoipId(String poipId, String partId) {
/* 161 */     String sql = "from PurchaseOrderInspectionPendingItem poipitem where poipitem.poip_number.poip_number = ? and poipitem.itemNumber.id = ?";
/* 162 */     Object[] params = { poipId, partId };
/* 163 */     return getHibernateTemplate().find(sql, params);
/*     */   }
/*     */   
/*     */   public List<PurchaseOrderDetial> getPurchaseOrderItem(String line, String wmspart, Date dueDate) {
/* 167 */     String sql = "from PurchaseOrderDetial item where item.line=? and item.itemNumber.id=? and item.dueDate = ?";
/* 168 */     Object[] parmet = { line, wmspart, dueDate };
/* 169 */     return getHibernateTemplate().find(sql, parmet);
/*     */   }
/*     */   
/*     */   public PurchaseOrderDetial getPurchaseOrderDetial(Integer id) {
/* 173 */     if (id == null) {
/* 174 */       if (this.log.isDebugEnabled())
/* 175 */         this.log.debug("try to get PurchaseOrderDetial with null id"); 
/* 176 */       return null;
/*     */     } 
/* 178 */     return (PurchaseOrderDetial)getHibernateTemplate().get(PurchaseOrderDetial.class, id);
/*     */   }
/*     */ 
/*     */   
/*     */   public PurchaseOrderDetial updatePurchaseOrderDetial(PurchaseOrderDetial detial) {
/* 183 */     Integer id = detial.getId();
/* 184 */     if (id == null) {
/* 185 */       throw new RuntimeException("cannot save a PurchaseOrderDetial with null id");
/*     */     }
/* 187 */     PurchaseOrderDetial oldPurchaseOrder = getPurchaseOrderDetial(id);
/* 188 */     if (oldPurchaseOrder != null) {
/*     */       try {
/* 190 */         PropertyUtils.copyProperties(oldPurchaseOrder, detial);
/* 191 */       } catch (Exception e) {
/* 192 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/* 194 */       getHibernateTemplate().update(oldPurchaseOrder);
/* 195 */       return oldPurchaseOrder;
/*     */     } 
/* 197 */     throw new RuntimeException("ct not found");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/hibernate/PurchaseOrderDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
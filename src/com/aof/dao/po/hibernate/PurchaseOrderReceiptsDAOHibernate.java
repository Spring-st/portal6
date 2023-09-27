/*     */ package com.aof.dao.po.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.convert.QueryParameterConvert;
/*     */ import com.aof.dao.po.PurchaseOrderReceiptsDAO;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.po.PurchaseOrderInspectionPendingItem;
/*     */ import com.aof.model.po.PurchaseOrderReceipts;
/*     */ import com.aof.model.po.PurchaseOrderReceiptsDetial;
/*     */ import com.aof.model.po.query.PurchaseOrderReceiptsQueryCondition;
/*     */ import com.aof.model.po.query.PurchaseOrderReceiptsQueryOrder;
/*     */ import java.math.BigDecimal;
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
/*     */ public class PurchaseOrderReceiptsDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements PurchaseOrderReceiptsDAO
/*     */ {
/*  33 */   private Log log = LogFactory.getLog(PurchaseOrderReceiptsDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from PurchaseOrderReceipts por";
/*     */   
/*     */   private static final String SQL = "from PurchaseOrderReceipts por";
/*     */ 
/*     */   
/*     */   public PurchaseOrderReceipts getPurchaseOrderReceipts(Integer id) {
/*  41 */     if (id == null) {
/*  42 */       if (this.log.isDebugEnabled())
/*  43 */         this.log.debug("try to get PurchaseOrderReceipts with null id"); 
/*  44 */       return null;
/*     */     } 
/*  46 */     return (PurchaseOrderReceipts)getHibernateTemplate().get(PurchaseOrderReceipts.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderReceipts updatePurchaseOrderReceipts(PurchaseOrderReceipts ct) {
/*  55 */     Integer id = ct.getId();
/*  56 */     if (id == null) {
/*  57 */       throw new RuntimeException("cannot save a ct with null id");
/*     */     }
/*  59 */     PurchaseOrderReceipts oldPurchaseOrderReceipts = getPurchaseOrderReceipts(id);
/*  60 */     if (oldPurchaseOrderReceipts != null) {
/*     */       try {
/*  62 */         PropertyUtils.copyProperties(oldPurchaseOrderReceipts, ct);
/*  63 */       } catch (Exception e) {
/*  64 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/*  66 */       getHibernateTemplate().update(oldPurchaseOrderReceipts);
/*  67 */       return oldPurchaseOrderReceipts;
/*     */     } 
/*  69 */     throw new RuntimeException("ct not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderReceipts insertPurchaseOrderReceipts(PurchaseOrderReceipts ct) {
/*  79 */     getHibernateTemplate().save(ct);
/*  80 */     return ct;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  88 */       { PurchaseOrderReceiptsQueryCondition.ID_EQ, "por.id = ?"
/*  89 */       }, { PurchaseOrderReceiptsQueryCondition.ID_BEGINWITH, 
/*  90 */         "por.id like ?", new QueryParameterConvert() {
/*     */           public Object convert(Object src) {
/*  92 */             return src + "%";
/*     */           }
/*     */         } }
/*     */     };
/*     */   
/*  97 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  98 */       { PurchaseOrderReceiptsQueryOrder.ID, "por.id" }
/*     */     };
/*     */   
/*     */   private static final String SQL_COUNT_ITEM = "select count(*) from PurchaseOrderReceiptsDetial pord";
/*     */   
/*     */   private static final String SQL_ITEM = "from PurchaseOrderReceiptsDetial pord";
/*     */ 
/*     */   
/*     */   public int getPurchaseOrderReceiptsListCount(Map conditions) {
/* 107 */     return getListCount(conditions, "select count(*) from PurchaseOrderReceipts por", QUERY_CONDITIONS);
/*     */   }
/*     */   public String getLastPoApplicationCode() {
/* 110 */     String result = getHibernateTemplate()
/* 111 */       .find("select max(ta.id) from PurchaseOrderReceipts por where por.id like 'CA%'").get(0);
/* 112 */     return ((result == null) ? "CA000000" : (String.valueOf(result) + "000000")).substring(0, 8);
/*     */   }
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
/*     */   public List getPurchaseOrderReceiptsList(Map conditions, int pageNo, int pageSize, PurchaseOrderReceiptsQueryOrder order, boolean descend) {
/* 125 */     return getList(conditions, pageNo, pageSize, order, descend, "from PurchaseOrderReceipts por", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledPurchaseOrderReceiptsList() {
/* 134 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 135 */     return getPurchaseOrderReceiptsList(conds, 0, -1, PurchaseOrderReceiptsQueryOrder.ID, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deletePurchaseOrderReceipts(PurchaseOrderReceipts po) {
/* 140 */     getHibernateTemplate().delete(po);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   private static final Object[][] QUERY_CONDITIONS_ITEM = new Object[][] {
/* 148 */       { PurchaseOrderReceiptsQueryCondition.ID_EQ, "pord.id = ?"
/* 149 */       }, { PurchaseOrderReceiptsQueryCondition.ID_BEGINWITH, 
/* 150 */         "po.id like ?", new QueryParameterConvert() {
/*     */           public Object convert(Object src) {
/* 152 */             return src + "%";
/*     */           }
/*     */         } }
/*     */     };
/*     */   
/* 157 */   private static final Object[][] QUERY_ORDERS_ITEM = new Object[][] {
/* 158 */       { PurchaseOrderReceiptsQueryOrder.ID, "pord.id" }
/*     */     };
/*     */ 
/*     */   
/*     */   public List getPurchaseOrderReceiptsItemList(Map condtions, int pageNo, int pageSize, PurchaseOrderReceiptsQueryOrder order, boolean descend) {
/* 163 */     return getList(condtions, pageNo, pageSize, order, descend, "from PurchaseOrderReceiptsDetial pord", QUERY_CONDITIONS_ITEM, QUERY_ORDERS_ITEM);
/*     */   }
/*     */   
/*     */   public int getPurchaseOrderReceiptsItemListCount(Map condtions) {
/* 167 */     return getListCount(condtions, "select count(*) from PurchaseOrderReceiptsDetial pord", QUERY_CONDITIONS_ITEM);
/*     */   }
/*     */   
/*     */   public PurchaseOrderReceipts getPurchaseOrderReceiptsByPoip(String id) {
/* 171 */     String sql = "from PurchaseOrderReceipts por where por.poip_number.poip_number = ?";
/* 172 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.STRING).get(0);
/*     */   }
/*     */   
/*     */   public PurchaseOrderReceiptsDetial getPurchaseOrderReceiptsDetial(Integer id) {
/* 176 */     if (id == null) {
/* 177 */       if (this.log.isDebugEnabled())
/* 178 */         this.log.debug("try to get PurchaseOrderReceiptsDetial with null id"); 
/* 179 */       return null;
/*     */     } 
/* 181 */     return (PurchaseOrderReceiptsDetial)getHibernateTemplate().get(PurchaseOrderReceiptsDetial.class, id);
/*     */   }
/*     */ 
/*     */   
/*     */   public PurchaseOrderReceiptsDetial insertPurchaseOrderReceiptsDetial(PurchaseOrderReceiptsDetial po) {
/* 186 */     getHibernateTemplate().save(po);
/* 187 */     return po;
/*     */   }
/*     */ 
/*     */   
/*     */   public PurchaseOrderReceiptsDetial updatePurchaseOrderReceiptsDetial(PurchaseOrderReceiptsDetial po) {
/* 192 */     Integer id = po.getId();
/* 193 */     if (id == null) {
/* 194 */       throw new RuntimeException("cannot save a PurchaseOrderReceiptsDetial with null id");
/*     */     }
/* 196 */     PurchaseOrderReceiptsDetial oldPurchaseOrderReceipts = getPurchaseOrderReceiptsDetial(id);
/* 197 */     if (oldPurchaseOrderReceipts != null) {
/*     */       try {
/* 199 */         PropertyUtils.copyProperties(oldPurchaseOrderReceipts, po);
/* 200 */       } catch (Exception e) {
/* 201 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/* 203 */       getHibernateTemplate().update(oldPurchaseOrderReceipts);
/* 204 */       return oldPurchaseOrderReceipts;
/*     */     } 
/* 206 */     throw new RuntimeException("PurchaseOrderReceiptsDetial not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMaxPoReceiptsBeginWith(String prefix) {
/* 211 */     String sql = "select max(por.id) from PurchaseOrderReceipts por";
/* 212 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 213 */     conds.put(PurchaseOrderReceiptsQueryCondition.ID_BEGINWITH, prefix);
/* 214 */     List<String> l = getList(conds, 0, -1, null, false, sql, QUERY_CONDITIONS, QUERY_ORDERS);
/*     */     
/* 216 */     if (l.isEmpty()) {
/* 217 */       return null;
/*     */     }
/* 219 */     return l.get(0);
/*     */   }
/*     */   
/*     */   public List<Box> getBoxList(Integer id) {
/* 223 */     String sql = "from Box box where box.psoItem.poipItem.id in (select item.id from PurchaseOrderInspectionPendingItem item where item.id = ?)";
/* 224 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean validateReceivingEndByCondimentSingle(Integer id, BigDecimal palnQty) {
/* 229 */     String sql = "select pcs.number from PurchaseOrderCondimentSingle pcs where pcs.po_detial_id.id=" + id + " ";
/* 230 */     List<BigDecimal> list = getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/* 231 */     if (list.size() > 0 && list.get(0) != null) {
/* 232 */       BigDecimal qty = list.get(0);
/* 233 */       if (qty.compareTo(palnQty) == 0 || qty.compareTo(palnQty) == 1) {
/* 234 */         return true;
/*     */       }
/*     */     } 
/* 237 */     return false;
/*     */   }
/*     */   
/*     */   public boolean validateReceivingEndByPoip(Integer id, BigDecimal palnQty) {
/* 241 */     String sql = "from PurchaseOrderInspectionPendingItem item where item.id = ? ";
/* 242 */     List<PurchaseOrderInspectionPendingItem> list = getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/* 243 */     if (list.size() > 0 && list.get(0) != null) {
/* 244 */       PurchaseOrderInspectionPendingItem item = list.get(0);
/* 245 */       BigDecimal qty = item.getReceiptQty();
/* 246 */       if (qty.compareTo(palnQty) == 0 || qty.compareTo(palnQty) == 1) {
/* 247 */         return true;
/*     */       }
/*     */     } 
/* 250 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/hibernate/PurchaseOrderReceiptsDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
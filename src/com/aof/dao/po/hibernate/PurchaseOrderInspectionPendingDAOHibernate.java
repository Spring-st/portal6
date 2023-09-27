/*     */ package com.aof.dao.po.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.dao.convert.QueryParameterConvert;
/*     */ import com.aof.dao.convert.QuerySQLConvert;
/*     */ import com.aof.dao.po.PurchaseOrderInspectionPendingDAO;
/*     */ import com.aof.model.po.PurchaseOrderInspectionPending;
/*     */ import com.aof.model.po.PurchaseOrderInspectionPendingItem;
/*     */ import com.aof.model.po.query.PurchaseOrderInspectionPendingQueryCondition;
/*     */ import com.aof.model.po.query.PurchaseOrderInspectionPendingQueryOrder;
/*     */ import com.aof.model.po.query.PurchaseOrderReceiptsQueryCondition;
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
/*     */ 
/*     */ public class PurchaseOrderInspectionPendingDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements PurchaseOrderInspectionPendingDAO
/*     */ {
/*  38 */   private Log log = LogFactory.getLog(PurchaseOrderInspectionPendingDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from PurchaseOrderInspectionPending po";
/*     */   
/*     */   private static final String SQL = "from PurchaseOrderInspectionPending po";
/*     */ 
/*     */   
/*     */   public PurchaseOrderInspectionPending getPurchaseOrderInspectionPending(String id) {
/*  46 */     if (id == null) {
/*  47 */       if (this.log.isDebugEnabled())
/*  48 */         this.log.debug("try to get PurchaseOrderInspectionPending with null id"); 
/*  49 */       return null;
/*     */     } 
/*  51 */     return (PurchaseOrderInspectionPending)getHibernateTemplate().get(PurchaseOrderInspectionPending.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderInspectionPending updatePurchaseOrderInspectionPending(PurchaseOrderInspectionPending ct) {
/*  60 */     String id = ct.getPoip_number();
/*  61 */     if (id == null) {
/*  62 */       throw new RuntimeException("cannot save a ct with null id");
/*     */     }
/*  64 */     PurchaseOrderInspectionPending oldPurchaseOrderInspectionPending = getPurchaseOrderInspectionPending(id);
/*  65 */     if (oldPurchaseOrderInspectionPending != null) {
/*     */       try {
/*  67 */         PropertyUtils.copyProperties(oldPurchaseOrderInspectionPending, ct);
/*  68 */       } catch (Exception e) {
/*  69 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  71 */       getHibernateTemplate().update(oldPurchaseOrderInspectionPending);
/*  72 */       return oldPurchaseOrderInspectionPending;
/*     */     } 
/*  74 */     throw new RuntimeException("ct not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderInspectionPending insertPurchaseOrderInspectionPending(PurchaseOrderInspectionPending ct) {
/*  84 */     getHibernateTemplate().save(ct);
/*  85 */     return ct;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] { 
/*  93 */       { PurchaseOrderInspectionPendingQueryCondition.ID_EQ, "po.id = ?"
/*  94 */       }, { PurchaseOrderInspectionPendingQueryCondition.PO_ID_EQ, "po.po_number.poOrder like ?", new LikeConvert()
/*  95 */       }, { PurchaseOrderInspectionPendingQueryCondition.SITE_EQ, "po.site = ?"
/*  96 */       }, { PurchaseOrderInspectionPendingQueryCondition.DEPARTMENT_EQ, "po.department = ?"
/*  97 */       }, { PurchaseOrderInspectionPendingQueryCondition.STARTPODATE_EQ, "po.po_number.createDate >= ?"
/*  98 */       }, { PurchaseOrderInspectionPendingQueryCondition.ENDPODATE_EQ, "po.po_number.createDate <=?"
/*  99 */       }, { PurchaseOrderInspectionPendingQueryCondition.SUPPLIER_ID_EQ, "po.supplier.id = ?"
/* 100 */       }, { PurchaseOrderInspectionPendingQueryCondition.SUPPLIER_EQ, "po.supplier.name like ?", new LikeConvert()
/* 101 */       }, { PurchaseOrderInspectionPendingQueryCondition.SUPPLIERCODE_EQ, "po.supplier.code like ?", new LikeConvert()
/* 102 */       }, { PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ, "po.status = ?" }, 
/* 103 */       { PurchaseOrderInspectionPendingQueryCondition.ISPRINT_EQ, "po.isPrint = ?"
/* 104 */       }, { PurchaseOrderInspectionPendingQueryCondition.ISCONFIRMPO_EQ, "po.ifConfirmPO = ?"
/* 105 */       }, { PurchaseOrderInspectionPendingQueryCondition.PONUMBER_EQ, "po.po_number.poOrder like ?", new LikeConvert()
/* 106 */       }, { PurchaseOrderInspectionPendingQueryCondition.ID_BEGINWITH, 
/* 107 */         "po.id like ?", new QueryParameterConvert() {
/*     */           public Object convert(Object src) {
/* 109 */             return src + "%";
/*     */           }
/*     */         } } };
/*     */ 
/*     */   
/* 114 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 115 */       { PurchaseOrderInspectionPendingQueryOrder.ID, "po.id"
/* 116 */       }, { PurchaseOrderInspectionPendingQueryOrder.STATUS, "po.status" }
/*     */     };
/*     */   
/*     */   private static final String SQL_COUNT_ITEM = "select count(*) from PurchaseOrderInspectionPendingItem poitem";
/*     */   
/*     */   private static final String SQL_ITEM = "from PurchaseOrderInspectionPendingItem poitem";
/*     */ 
/*     */   
/*     */   public int getPurchaseOrderInspectionPendingListCount(Map conditions) {
/* 125 */     return getListCount(conditions, "select count(*) from PurchaseOrderInspectionPending po", QUERY_CONDITIONS);
/*     */   }
/*     */   public String getLastPoApplicationCode() {
/* 128 */     String result = getHibernateTemplate()
/* 129 */       .find(
/* 130 */         "select max(ta.id) from PurchaseOrderInspectionPending po where po.id like 'CA%'")
/* 131 */       .get(0);
/* 132 */     return ((result == null) ? "CA000000" : (String.valueOf(result) + "000000"))
/* 133 */       .substring(0, 8);
/*     */   }
/*     */   
/*     */   public String getMaxPoApplicationIdBeginWith(String prefix) {
/* 137 */     String sql = "select max(po.id) from PurchaseOrderInspectionPending po";
/* 138 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 139 */     conds.put(PurchaseOrderInspectionPendingQueryCondition.ID_BEGINWITH, prefix);
/* 140 */     List<String> l = getList(conds, 0, -1, null, false, sql, QUERY_CONDITIONS, 
/* 141 */         QUERY_ORDERS);
/* 142 */     if (l.isEmpty()) {
/* 143 */       return null;
/*     */     }
/* 145 */     return l.get(0);
/*     */   }
/*     */   public String getMaxPONumber() {
/* 148 */     String sql = "select max(po.id) from PurchaseOrderInspectionPending po";
/* 149 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 150 */     List<String> l = getList(conds, 0, -1, null, false, sql, QUERY_CONDITIONS, QUERY_ORDERS);
/* 151 */     if (l.isEmpty()) {
/* 152 */       return null;
/*     */     }
/* 154 */     return l.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPurchaseOrderInspectionPendingList(Map conditions, int pageNo, int pageSize, PurchaseOrderInspectionPendingQueryOrder order, boolean descend) {
/* 163 */     return getList(conditions, pageNo, pageSize, order, descend, "from PurchaseOrderInspectionPending po", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledPurchaseOrderInspectionPendingList() {
/* 172 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 173 */     return getPurchaseOrderInspectionPendingList(conds, 0, -1, PurchaseOrderInspectionPendingQueryOrder.ID, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deletePurchaseOrderInspectionPending(PurchaseOrderInspectionPending po) {
/* 178 */     getHibernateTemplate().delete(po);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 185 */   private static final Object[][] QUERY_CONDITIONS_ITEM = new Object[][] { 
/* 186 */       { PurchaseOrderInspectionPendingQueryCondition.ID_EQ, "poitem.id = ?"
/* 187 */       }, { PurchaseOrderInspectionPendingQueryCondition.SITE_EQ, "poitem.poip_number.site = ?"
/* 188 */       }, { PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ, "poitem.poip_number.status = ?"
/* 189 */       }, { PurchaseOrderInspectionPendingQueryCondition.PONUMBER_EQ, "poitem.poip_number.po_number.poOrder like ?", new LikeConvert()
/* 190 */       }, { PurchaseOrderInspectionPendingQueryCondition.ITEMNUMBER_EQ, "poitem.itemNumber like ?", new LikeConvert()
/* 191 */       }, { PurchaseOrderInspectionPendingQueryCondition.SUPPLIERITEM_EQ, "poitem.supplierItemNumber = ?"
/* 192 */       }, { PurchaseOrderInspectionPendingQueryCondition.UM_EQ, "poitem.um = ?"
/* 193 */       }, { PurchaseOrderInspectionPendingQueryCondition.DUEDATE_EQ, "poitem.dueDate = ?"
/* 194 */       }, { PurchaseOrderInspectionPendingQueryCondition.CREATEDATE_EQ, "poitem.poip_number.createDate = ?"
/* 195 */       }, { PurchaseOrderInspectionPendingQueryCondition.RECEIPTQTY_EQ, "poitem.receiptQty = ?" }, 
/* 196 */       { PurchaseOrderInspectionPendingQueryCondition.RECEIVDATE_EQ, "poitem.receivingDate = ?"
/* 197 */       }, { PurchaseOrderInspectionPendingQueryCondition.SUPPLIER_ID_EQ, "poitem.poip_number.supplier.id = ?"
/* 198 */       }, { PurchaseOrderInspectionPendingQueryCondition.qtyOpen_DT, "poitem.qtyOpen > ?"
/* 199 */       }, { PurchaseOrderInspectionPendingQueryCondition.ITEMSTATUS_EQ, "poitem.status = ?"
/* 200 */       }, { PurchaseOrderInspectionPendingQueryCondition.NOTINITEMSTATUS_EQ, "poitem.status not in (?, ?)"
/* 201 */       }, { PurchaseOrderInspectionPendingQueryCondition.CREATETYPE_EQ, "poitem.poip_number.createType = ? "
/* 202 */       }, { PurchaseOrderInspectionPendingQueryCondition.ID_IN, "poitem.id in ", new QuerySQLConvert() {
/*     */           public Object convert(StringBuffer sql, Object parameter) {
/* 204 */             if (parameter != null && parameter instanceof Object[]) {
/* 205 */               Object[] finalParameter = (Object[])parameter;
/* 206 */               if (finalParameter.length > 0) {
/* 207 */                 sql.append("(?");
/* 208 */                 for (int i = 1; i < finalParameter.length; i++) {
/* 209 */                   sql.append(",?");
/*     */                 }
/* 211 */                 sql.append(")");
/*     */               } else {
/* 213 */                 return finalParameter;
/*     */               } 
/*     */             } else {
/* 216 */               return parameter;
/*     */             } 
/* 218 */             return parameter;
/*     */           }
/*     */         }
/*     */       },
/* 222 */       { PurchaseOrderInspectionPendingQueryCondition.ID_BEGINWITH, 
/* 223 */         "poitem.id like ?", new QueryParameterConvert() {
/*     */           public Object convert(Object src) {
/* 225 */             return src + "%";
/*     */           }
/*     */         }
/*     */       },
/* 229 */       { PurchaseOrderInspectionPendingQueryCondition.LOTSER_ITEM_EQ, "poitem.lotSer.id = ?"
/* 230 */       }, { PurchaseOrderReceiptsQueryCondition.ID_BEGINWITH, 
/* 231 */         "poitem.id like ?", new QueryParameterConvert() {
/*     */           public Object convert(Object src) {
/* 233 */             return src + "%";
/*     */           }
/*     */         } }, 
/* 236 */       { PurchaseOrderInspectionPendingQueryCondition.SITE_ID_IN, "poitem.poip_number.site.id in ", new QuerySQLConvert() {
/*     */           public Object convert(StringBuffer sql, Object finalParameters) {
/* 238 */             if (finalParameters != null && finalParameters instanceof Object[]) {
/* 239 */               Object[] parametersList = (Object[])finalParameters;
/* 240 */               if (parametersList.length > 0) {
/* 241 */                 sql.append("(?");
/* 242 */                 for (int i = 1; i < parametersList.length; i++) {
/* 243 */                   sql.append(",?");
/*     */                 }
/* 245 */                 sql.append(")");
/*     */               } else {
/* 247 */                 return parametersList;
/*     */               } 
/*     */             } else {
/* 250 */               return finalParameters;
/*     */             } 
/* 252 */             return finalParameters;
/*     */           }
/*     */         } } };
/*     */ 
/*     */   
/* 257 */   private static final Object[][] QUERY_ORDERS_ITEM = new Object[][] {
/* 258 */       { PurchaseOrderInspectionPendingQueryOrder.ID, "poitem.id"
/* 259 */       }, { PurchaseOrderInspectionPendingQueryOrder.STATUS, "poitem.status" }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPurchaseOrderInspectionPendingItemList(Map conditions, int pageNo, int pageSize, PurchaseOrderInspectionPendingQueryOrder order, boolean descend) {
/* 265 */     String sql = "from PurchaseOrderInspectionPendingItem po where po.qtyOpen > 0";
/* 266 */     return getList(conditions, pageNo, pageSize, order, descend, "from PurchaseOrderInspectionPendingItem poitem", QUERY_CONDITIONS_ITEM, QUERY_ORDERS_ITEM);
/*     */   }
/*     */   
/*     */   public int getPurchaseOrderInspectionPendingItemListCount(Map conditions) {
/* 270 */     return getListCount(conditions, "select count(*) from PurchaseOrderInspectionPendingItem poitem", QUERY_CONDITIONS_ITEM);
/*     */   }
/*     */   
/*     */   public List<PurchaseOrderInspectionPendingItem> getPurchaseOrderInspectionPendingItemListByReceiving(String poid) {
/* 274 */     String sql = "from PurchaseOrderInspectionPendingItem poi where poi.poip_number.poip_number = ? and poi.isReceiving = 1";
/* 275 */     return getHibernateTemplate().find(sql, poid, (Type)Hibernate.STRING);
/*     */   }
/*     */   
/*     */   public String getMaxPoApplicationIdBeginWithReceipts(String prefix) {
/* 279 */     String sql = "select max(po.id) from PurchaseOrderReceipts po";
/* 280 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 281 */     conds.put(PurchaseOrderReceiptsQueryCondition.ID_BEGINWITH, prefix);
/* 282 */     List<String> l = getList(conds, 0, -1, null, false, sql, QUERY_CONDITIONS, 
/* 283 */         QUERY_ORDERS);
/* 284 */     if (l.isEmpty()) {
/* 285 */       return null;
/*     */     }
/* 287 */     return l.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getBoxList(Integer poItemid, String boxNumber) {
/* 292 */     String sql = "from Box po where po.poItem.id = ? and po.number = ? ";
/* 293 */     Object[] params = { poItemid, boxNumber };
/* 294 */     return getHibernateTemplate().find(sql, params);
/*     */   }
/*     */   
/*     */   public List getPurchaseOrderInspectionPendingItemListByPoipId(String poipId, String partId) {
/* 298 */     String sql = "from PurchaseOrderInspectionPendingItem poipitem where poipitem.poip_number.poip_number = ? and poipitem.itemNumber.id = ?";
/* 299 */     Object[] params = { poipId, partId };
/* 300 */     return getHibernateTemplate().find(sql, params);
/*     */   }
/*     */ 
/*     */   
/*     */   public PurchaseOrderInspectionPendingItem updatePurchaseOrderInspectionPendingItem3(PurchaseOrderInspectionPendingItem poipItem) {
/* 305 */     Integer id = poipItem.getId();
/* 306 */     if (id == null) {
/* 307 */       throw new RuntimeException(
/* 308 */           "cannot save a poipItem with null id");
/*     */     }
/* 310 */     PurchaseOrderInspectionPendingItem oldPoipItem = getPoipItem(id);
/* 311 */     if (oldPoipItem != null) {
/*     */       try {
/* 313 */         PropertyUtils.copyProperties(oldPoipItem, poipItem);
/* 314 */       } catch (Exception e) {
/* 315 */         throw new RuntimeException(e);
/*     */       } 
/* 317 */       getHibernateTemplate().update(oldPoipItem);
/* 318 */       return oldPoipItem;
/*     */     } 
/* 320 */     throw new RuntimeException("oldPoipItem not found");
/*     */   }
/*     */   
/*     */   public PurchaseOrderInspectionPendingItem updatePurchaseOrderInspectionPendingItem2(PurchaseOrderInspectionPendingItem poi) {
/* 324 */     Integer id = poi.getId();
/* 325 */     if (id == null) {
/* 326 */       throw new RuntimeException("cannot save a poipItem with null id");
/*     */     }
/* 328 */     PurchaseOrderInspectionPendingItem oldPoipItem = getPoipItem(id);
/* 329 */     if (oldPoipItem != null) {
/*     */       try {
/* 331 */         PropertyUtils.copyProperties(oldPoipItem, poi);
/* 332 */       } catch (Exception e) {
/* 333 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/* 335 */       getHibernateTemplate().update(oldPoipItem);
/* 336 */       return oldPoipItem;
/*     */     } 
/* 338 */     throw new RuntimeException("srt not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public PurchaseOrderInspectionPendingItem getPoipItem(Integer id) {
/* 343 */     if (id == null) {
/* 344 */       if (this.log.isDebugEnabled())
/* 345 */         this.log.debug("try to get Inventory with null id"); 
/* 346 */       return null;
/*     */     } 
/* 348 */     return (PurchaseOrderInspectionPendingItem)getHibernateTemplate().get(PurchaseOrderInspectionPendingItem.class, id);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/hibernate/PurchaseOrderInspectionPendingDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.aof.dao.po.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.dao.po.PortalShipOrderDAO;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.po.PortalShipOrder;
/*     */ import com.aof.model.po.PortalShipOrderItem;
/*     */ import com.aof.model.po.query.PortalShipOrderQueryCondition;
/*     */ import com.aof.model.po.query.PortalShipOrderQueryOrder;
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
/*     */ public class PortalShipOrderDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements PortalShipOrderDAO
/*     */ {
/*  33 */   private Log log = LogFactory.getLog(PortalShipOrderDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from PortalShipOrder po";
/*     */   
/*     */   private static final String SQL = "from PortalShipOrder po";
/*     */ 
/*     */   
/*     */   public PortalShipOrder getPortalShipOrder(Integer id) {
/*  41 */     if (id == null) {
/*  42 */       if (this.log.isDebugEnabled())
/*  43 */         this.log.debug("try to get PortalShipOrder with null id"); 
/*  44 */       return null;
/*     */     } 
/*  46 */     return (PortalShipOrder)getHibernateTemplate().get(PortalShipOrder.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortalShipOrder updatePortalShipOrder(PortalShipOrder portalShipOrder) {
/*  55 */     Integer id = portalShipOrder.getId();
/*  56 */     if (id == null) {
/*  57 */       throw new RuntimeException("cannot save a portalShipOrder with null id");
/*     */     }
/*  59 */     PortalShipOrder oldPortalShipOrder = getPortalShipOrder(id);
/*  60 */     if (oldPortalShipOrder != null) {
/*     */       try {
/*  62 */         PropertyUtils.copyProperties(oldPortalShipOrder, portalShipOrder);
/*  63 */       } catch (Exception e) {
/*  64 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/*  66 */       getHibernateTemplate().update(oldPortalShipOrder);
/*  67 */       getHibernateTemplate().flush();
/*  68 */       return oldPortalShipOrder;
/*     */     } 
/*  70 */     throw new RuntimeException("portalShipOrder not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortalShipOrder insertPortalShipOrder(PortalShipOrder portalShipOrder) {
/*  80 */     getHibernateTemplate().save(portalShipOrder);
/*  81 */     getHibernateTemplate().flush();
/*  82 */     return portalShipOrder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] { 
/*  90 */       { PortalShipOrderQueryCondition.ID_EQ, "po.id = ?"
/*  91 */       }, { PortalShipOrderQueryCondition.SITE_EQ, "po.site = ?"
/*  92 */       }, { PortalShipOrderQueryCondition.CODE_EQ, "po.code like ?", new LikeConvert()
/*  93 */       }, { PortalShipOrderQueryCondition.DEPARTMENT_EQ, "po.department = ?"
/*  94 */       }, { PortalShipOrderQueryCondition.CREATEDATE_GE, "po.createDate >= ?"
/*  95 */       }, { PortalShipOrderQueryCondition.CREATEDATE_LT, "po.createDate <= ?"
/*  96 */       }, { PortalShipOrderQueryCondition.ENABLED_EQ, "po.enabled = ?"
/*  97 */       }, { PortalShipOrderQueryCondition.APPROVEREQUESTID_EQ, "po.approveRequestId = ?"
/*  98 */       }, { PortalShipOrderQueryCondition.PORTALSHIPORDER_STATUS_EQ, "po.status = ?"
/*  99 */       }, { PortalShipOrderQueryCondition.PO_CODE_EQ, "po.id in (select item.portalShipOrder.id from PortalShipOrderItem item where item.poipItem.poip_number.po_number.po_number=?)" }, 
/* 100 */       { PortalShipOrderQueryCondition.PART_CODE_EQ, "po.id in (select item.portalShipOrder.id from PortalShipOrderItem item where item.poipItem.itemNumber.id=?)"
/* 101 */       }, { PortalShipOrderQueryCondition.PART_ID_EQ, "po.id in (select item.portalShipOrder.id from PortalShipOrderItem item where item.part.id=?)"
/* 102 */       }, { PortalShipOrderQueryCondition.TYPE_EQ, "po.type = ?"
/* 103 */       }, { PortalShipOrderQueryCondition.CREATETYPE_EQ, "po.createType = ?"
/* 104 */       }, { PortalShipOrderQueryCondition.SUPPLIER_ID_EQ, "po.supplier.id = ?"
/* 105 */       }, { PortalShipOrderQueryCondition.SYNC_STATUS_EQ, " po.isSync = ?"
/* 106 */       }, { PortalShipOrderQueryCondition.CREATEDATE_EQ, " po.createDate = ?"
/* 107 */       }, { PortalShipOrderQueryCondition.ARRIVALDATE_EQ, " po.arrivalDate = ?" } };
/*     */ 
/*     */   
/* 110 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 111 */       { PortalShipOrderQueryOrder.ID, "po.id"
/* 112 */       }, { PortalShipOrderQueryOrder.CREATEDATE, "po.enabled asc,po.createDate"
/* 113 */       }, { PortalShipOrderQueryOrder.ARRIVALDATE, "po.arrivalDate" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPortalShipOrderListCount(Map conditions) {
/* 122 */     return getListCount(conditions, "select count(*) from PortalShipOrder po", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getPortalShipOrderList(Map conditions, int pageNo, int pageSize, PortalShipOrderQueryOrder order, boolean descend) {
/* 127 */     return getList(conditions, pageNo, pageSize, order, descend, "from PortalShipOrder po", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */   
/*     */   public List getEnabledPortalShipOrderList() {
/* 131 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 132 */     return getPortalShipOrderList(conds, 0, -1, PortalShipOrderQueryOrder.ID, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<PortalShipOrderItem> getPortalShipOrderItemListByOrderId(Integer id) {
/* 137 */     String hql = "from PortalShipOrderItem item where item.portalShipOrder.id=? ";
/* 138 */     return getHibernateTemplate().find(hql, id);
/*     */   }
/*     */   public List<Box> getBoxByShipOrderId(Integer id) {
/* 141 */     String hql = "from Box box where box.psoItem.portalShipOrder.id=? ";
/* 142 */     return getHibernateTemplate().find(hql, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<PortalShipOrderItem> getAllShipOrderItem() {
/* 149 */     String hql = "from PortalShipOrderItem";
/* 150 */     return getHibernateTemplate().find(hql);
/*     */   }
/*     */   public void deletePortalShipOrder(PortalShipOrder portalShipOrder) {
/* 153 */     getHibernateTemplate().delete(portalShipOrder);
/*     */   }
/*     */   public void deletePurchaseOrderBox(Integer id) {
/* 156 */     String hql = "from Box box where box.psoItem.id=" + id;
/* 157 */     getHibernateTemplate().delete(hql);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deletePortalShipOrderItem(PortalShipOrderItem portalShipOrderItem) {
/* 162 */     getHibernateTemplate().delete(portalShipOrderItem);
/*     */   }
/*     */   public PortalShipOrderItem getPortalShipOrderItem(Integer id) {
/* 165 */     return (PortalShipOrderItem)getHibernateTemplate().get(PortalShipOrderItem.class, id);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/hibernate/PortalShipOrderDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.aof.dao.po.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.dao.po.PortalShipOrderItemDAO;
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
/*     */ public class PortalShipOrderItemDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements PortalShipOrderItemDAO
/*     */ {
/*  31 */   private Log log = LogFactory.getLog(PortalShipOrderItemDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from PortalShipOrderItem psoi";
/*     */   
/*     */   private static final String SQL = "from PortalShipOrderItem psoi";
/*     */ 
/*     */   
/*     */   public PortalShipOrderItem getPortalShipOrderItem(Integer id) {
/*  39 */     if (id == null) {
/*  40 */       if (this.log.isDebugEnabled())
/*  41 */         this.log.debug("try to get PortalShipOrderItemItem with null id"); 
/*  42 */       return null;
/*     */     } 
/*  44 */     return (PortalShipOrderItem)getHibernateTemplate().get(PortalShipOrderItem.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortalShipOrderItem updatePortalShipOrderItem(PortalShipOrderItem item) {
/*  53 */     Integer id = item.getId();
/*  54 */     if (id == null) {
/*  55 */       throw new RuntimeException("cannot save a PortalShipOrderItemItem with null id");
/*     */     }
/*  57 */     PortalShipOrderItem oldPortalShipOrderItem = getPortalShipOrderItem(id);
/*  58 */     if (oldPortalShipOrderItem != null) {
/*     */       try {
/*  60 */         PropertyUtils.copyProperties(oldPortalShipOrderItem, item);
/*  61 */       } catch (Exception e) {
/*  62 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/*  64 */       getHibernateTemplate().update(oldPortalShipOrderItem);
/*  65 */       return oldPortalShipOrderItem;
/*     */     } 
/*  67 */     throw new RuntimeException("PortalShipOrderItem not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortalShipOrderItem insertPortalShipOrderItem(PortalShipOrderItem portalShipOrderItem) {
/*  77 */     getHibernateTemplate().save(portalShipOrderItem);
/*  78 */     getHibernateTemplate().flush();
/*  79 */     return portalShipOrderItem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  87 */       { PortalShipOrderQueryCondition.ID_EQ, "psoi.id = ?"
/*  88 */       }, { PortalShipOrderQueryCondition.SITE_EQ, "psoi.site = ?"
/*  89 */       }, { PortalShipOrderQueryCondition.STATUS_EQ, "psoi.status = ?"
/*  90 */       }, { PortalShipOrderQueryCondition.STATUS_CONFIRM_EQ, "psoi.status_confirm = ?"
/*  91 */       }, { PortalShipOrderQueryCondition.CODE_EQ, "psoi.code like ?", new LikeConvert()
/*  92 */       }, { PortalShipOrderQueryCondition.PO_NUMBER_EQ, "psoi.poipItem.poip_number.po_number = ?"
/*  93 */       }, { PortalShipOrderQueryCondition.PART_CODE_EQ, "psoi.poipItem.itemNumber.id = ?"
/*  94 */       }, { PortalShipOrderQueryCondition.SYNC_STATUS_EQ, "psoi.isSync = ?"
/*  95 */       }, { PortalShipOrderQueryCondition.SO_ID_EQ, "psoi.portalShipOrder.id= ? "
/*  96 */       }, { PortalShipOrderQueryCondition.PO_ITEM_EQ, "psoi.poipItem.id= ? " }
/*     */     };
/*     */   
/*  99 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 100 */       { PortalShipOrderQueryOrder.ID, "psoi.id" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPortalShipOrderItemListCount(Map conditions) {
/* 109 */     return getListCount(conditions, "select count(*) from PortalShipOrderItem psoi", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getPortalShipOrderItemList(Map conditions, int pageNo, int pageSize, PortalShipOrderQueryOrder order, boolean descend) {
/* 114 */     return getList(conditions, pageNo, pageSize, order, descend, "from PortalShipOrderItem psoi", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */   
/*     */   public List getEnabledPortalShipOrderItemList() {
/* 118 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 119 */     return getPortalShipOrderItemList(conds, 0, -1, PortalShipOrderQueryOrder.ID, false);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/hibernate/PortalShipOrderItemDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
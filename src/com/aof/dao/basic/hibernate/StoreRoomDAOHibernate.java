/*     */ package com.aof.dao.basic.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.basic.StoreRoomDAO;
/*     */ import com.aof.model.basic.StoreRoom;
/*     */ import com.aof.model.basic.query.StoreRoomQueryCondition;
/*     */ import com.aof.model.basic.query.StoreRoomQueryOrder;
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
/*     */ public class StoreRoomDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements StoreRoomDAO
/*     */ {
/*  31 */   private Log log = LogFactory.getLog(StoreRoomDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from StoreRoom sr";
/*     */   
/*     */   private static final String SQL = "from StoreRoom sr";
/*     */ 
/*     */   
/*     */   public StoreRoom getStoreRoom(Integer id) {
/*  39 */     if (id == null) {
/*  40 */       if (this.log.isDebugEnabled())
/*  41 */         this.log.debug("try to get StoreRoom with null id"); 
/*  42 */       return null;
/*     */     } 
/*  44 */     return (StoreRoom)getHibernateTemplate().get(StoreRoom.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StoreRoom updateStoreRoom(StoreRoom sr) {
/*  53 */     Integer id = sr.getId();
/*  54 */     if (id == null) {
/*  55 */       throw new RuntimeException("cannot save a sr with null id");
/*     */     }
/*  57 */     StoreRoom oldStoreRoom = getStoreRoom(id);
/*  58 */     if (oldStoreRoom != null) {
/*     */       try {
/*  60 */         PropertyUtils.copyProperties(oldStoreRoom, sr);
/*  61 */       } catch (Exception e) {
/*  62 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  64 */       getHibernateTemplate().update(oldStoreRoom);
/*  65 */       return oldStoreRoom;
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
/*     */   public StoreRoom insertStoreRoom(StoreRoom sr) {
/*  77 */     getHibernateTemplate().save(sr);
/*  78 */     return sr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  86 */       { StoreRoomQueryCondition.ID_EQ, "sr.id = ?"
/*  87 */       }, { StoreRoomQueryCondition.TYPE_EQ, "sr.type = ?"
/*  88 */       }, { StoreRoomQueryCondition.ADDRESS_EQ, "sr.address = ?"
/*  89 */       }, { StoreRoomQueryCondition.ENABLED_EQ, "sr.status = ?"
/*  90 */       }, { StoreRoomQueryCondition.SITE_EQ, "sr.site.id = ?" }
/*     */     };
/*     */ 
/*     */   
/*  94 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  95 */       { StoreRoomQueryOrder.ID, "sr.id"
/*  96 */       }, { StoreRoomQueryOrder.ENABLED, "sr.status" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStoreRoomListCount(Map conditions) {
/* 105 */     return getListCount(conditions, "select count(*) from StoreRoom sr", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getStoreRoomList(Map conditions, int pageNo, int pageSize, StoreRoomQueryOrder order, boolean descend) {
/* 115 */     return getList(conditions, pageNo, pageSize, order, descend, "from StoreRoom sr", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledStoreRoomList() {
/* 124 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 125 */     conds.put(StoreRoomQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 126 */     return getStoreRoomList(conds, 0, -1, StoreRoomQueryOrder.ID, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteStoreRoom(StoreRoom sr) {
/* 131 */     getHibernateTemplate().delete(sr);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/hibernate/StoreRoomDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.aof.dao.plantWarehouse.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.convert.QueryParameterConvert;
/*     */ import com.aof.dao.plantWarehouse.WmsPlanToGoOutDAO;
/*     */ import com.aof.dao.plantWarehouse.WmsUWDAO;
/*     */ import com.aof.model.plantWarehouse.WmsPlanToGoOut;
/*     */ import com.aof.model.plantWarehouse.WmsPlanToGoOutItem;
/*     */ import com.aof.model.plantWarehouse.WmsPlanToGoOutSub;
/*     */ import com.aof.model.plantWarehouse.query.WmsPlanToGoOutQueryCondition;
/*     */ import com.aof.model.plantWarehouse.query.WmsPlanToGoOutQueryOrder;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.Hibernate;
/*     */ import net.sf.hibernate.type.Type;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class WmsPlanToGoOutDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements WmsPlanToGoOutDAO {
/*  24 */   private Log log = LogFactory.getLog(WmsUWDAO.class);
/*     */   
/*     */   public WmsPlanToGoOut getWmsPlanToGoOut(Integer id) {
/*  27 */     if (id == null) {
/*  28 */       if (this.log.isDebugEnabled())
/*  29 */         this.log.debug("try to get WmsPlanToGoOut with null id"); 
/*  30 */       return null;
/*     */     } 
/*  32 */     WmsPlanToGoOut s = (WmsPlanToGoOut)getHibernateTemplate().get(WmsPlanToGoOut.class, id);
/*  33 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from WmsPlanToGoOut pwom";
/*     */   private static final String SQL = "from WmsPlanToGoOut pwom";
/*     */   
/*  40 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  41 */       { WmsPlanToGoOutQueryCondition.ID_EQ, "pwom.id = ?"
/*  42 */       }, { WmsPlanToGoOutQueryCondition.ID_BEGINWITH, 
/*  43 */         "pwom.code like ?", new QueryParameterConvert() {
/*     */           public Object convert(Object src) {
/*  45 */             return src + "%";
/*     */           }
/*     */         } }
/*     */     };
/*     */   
/*  50 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  51 */       { WmsPlanToGoOutQueryOrder.ID, "pwom.id" }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWmsPlanToGoOutListCount(Map conditions) {
/*  57 */     return getListCount(conditions, "select count(*) from WmsPlanToGoOut pwom", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getWmsPlanToGoOutList(Map conditions, int pageNo, int pageSize, WmsPlanToGoOutQueryOrder order, boolean descend) {
/*  64 */     return getList(conditions, pageNo, pageSize, order, descend, "from WmsPlanToGoOut pwom", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WmsPlanToGoOut insertWmsPlanToGoOut(WmsPlanToGoOut pwom) {
/*  70 */     getHibernateTemplate().save(pwom);
/*  71 */     return pwom;
/*     */   }
/*     */ 
/*     */   
/*     */   public WmsPlanToGoOut updateWmsPlanToGoOut(WmsPlanToGoOut pwom) {
/*  76 */     Integer id = pwom.getId();
/*  77 */     if (id == null) {
/*  78 */       throw new RuntimeException("cannot save a ct with null id");
/*     */     }
/*  80 */     WmsPlanToGoOut oldProduceInStorage = getWmsPlanToGoOut(id);
/*  81 */     if (oldProduceInStorage != null) {
/*     */       try {
/*  83 */         PropertyUtils.copyProperties(oldProduceInStorage, pwom);
/*  84 */       } catch (Exception e) {
/*  85 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/*  87 */       getHibernateTemplate().update(oldProduceInStorage);
/*  88 */       return oldProduceInStorage;
/*     */     } 
/*  90 */     throw new RuntimeException("ct not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMaxPoApplicationIdBeginWith(String prefix) {
/*  95 */     String sql = "select max(pwom.code) from WmsPlanToGoOut pwom";
/*  96 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/*  97 */     conds.put(WmsPlanToGoOutQueryCondition.ID_BEGINWITH, prefix);
/*  98 */     List<String> l = getList(conds, 0, -1, null, false, sql, QUERY_CONDITIONS, QUERY_ORDERS);
/*  99 */     if (l.isEmpty()) {
/* 100 */       return null;
/*     */     }
/* 102 */     return l.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public WmsPlanToGoOutItem getWmsPlanToGoOutItem(int id) {
/* 107 */     return (WmsPlanToGoOutItem)getHibernateTemplate().get(WmsPlanToGoOutItem.class, Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WmsPlanToGoOutItem insertWmsPlanToGoOutItem(WmsPlanToGoOutItem pwom) {
/* 113 */     getHibernateTemplate().save(pwom);
/* 114 */     return pwom;
/*     */   }
/*     */ 
/*     */   
/*     */   public WmsPlanToGoOutItem updateWmsPlanToGoOutItem(WmsPlanToGoOutItem pwom) {
/* 119 */     Integer id = pwom.getId();
/* 120 */     if (id == null) {
/* 121 */       throw new RuntimeException("cannot save a ct with null id");
/*     */     }
/* 123 */     WmsPlanToGoOutItem oldProduceInStorage = getWmsPlanToGoOutItem(id.intValue());
/* 124 */     if (oldProduceInStorage != null) {
/*     */       try {
/* 126 */         PropertyUtils.copyProperties(oldProduceInStorage, pwom);
/* 127 */       } catch (Exception e) {
/* 128 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/* 130 */       getHibernateTemplate().update(oldProduceInStorage);
/* 131 */       return oldProduceInStorage;
/*     */     } 
/* 133 */     throw new RuntimeException("ct not found");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteWmsPlanToGoOutItem(WmsPlanToGoOutItem item) {
/* 139 */     getHibernateTemplate().delete(item);
/*     */   }
/*     */   
/*     */   public List<WmsPlanToGoOutItem> getWmsPlanToGoOutItemByMain(Integer id) {
/* 143 */     String sql = "from WmsPlanToGoOutItem item where item.unplanned_outbound_id.id = ?";
/* 144 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public List<WmsPlanToGoOutItem> getWmsPlanToGoOutItemByBox(Integer id) {
/* 148 */     String sql = "from WmsPlanToGoOutItem item where item.box.id = ?";
/* 149 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<WmsPlanToGoOutItem> getWmsPlanToGoOutItemByBoxLotser(String lotser) {
/* 155 */     String sql = "from WmsPlanToGoOutItem item where item.box.id =  (select box.id from PoIpiBox box where box.lotSer.id=?)";
/*     */     
/* 157 */     return getHibernateTemplate().find(sql, lotser, (Type)Hibernate.STRING);
/*     */   }
/*     */   
/*     */   public void deleteWmsPlanToGoOut(WmsPlanToGoOut goOut) {
/* 161 */     getHibernateTemplate().delete(goOut);
/*     */   }
/*     */   
/*     */   public WmsPlanToGoOut getWmsPlanToGoOutByCode(String code) {
/* 165 */     String sql = "from WmsPlanToGoOut wp where wp.code = ?";
/* 166 */     List<WmsPlanToGoOut> list = getHibernateTemplate().find(sql, code, (Type)Hibernate.STRING);
/* 167 */     if (list == null || list.isEmpty()) {
/* 168 */       return null;
/*     */     }
/* 170 */     return list.get(0);
/*     */   }
/*     */   
/*     */   public WmsPlanToGoOutSub insertWmsPlanToGoOutSub(WmsPlanToGoOutSub sub) {
/* 174 */     getHibernateTemplate().save(sub);
/* 175 */     return sub;
/*     */   }
/*     */   
/*     */   public List<WmsPlanToGoOutSub> getWmsPlanToGoOutSubByItem(Integer id) {
/* 179 */     String sql = "from WmsPlanToGoOutSub sub where sub.unplanned_outbound_detial_id.id = ?";
/* 180 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public List<WmsPlanToGoOutSub> getWmsPlanToGoOutSubByMain(Integer id) {
/* 184 */     String sql = "from WmsPlanToGoOutSub sub where sub.unplanned_outbound_detial_id.unplanned_outbound_id.id = ?";
/* 185 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/plantWarehouse/hibernate/WmsPlanToGoOutDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.aof.dao.plantWarehouse.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.convert.QueryParameterConvert;
/*     */ import com.aof.dao.plantWarehouse.WmsUWDAO;
/*     */ import com.aof.model.plantWarehouse.WmsUnplannedWarehousing;
/*     */ import com.aof.model.plantWarehouse.WmsUnplannedWarehousingItem;
/*     */ import com.aof.model.plantWarehouse.query.WmsUnplannedWarehousingQueryCondition;
/*     */ import com.aof.model.plantWarehouse.query.WmsUnplannedWarehousingQueryOrder;
/*     */ import java.sql.SQLException;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.Hibernate;
/*     */ import net.sf.hibernate.HibernateException;
/*     */ import net.sf.hibernate.Query;
/*     */ import net.sf.hibernate.Session;
/*     */ import net.sf.hibernate.type.Type;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.springframework.orm.hibernate.HibernateCallback;
/*     */ 
/*     */ public class WmsUWDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements WmsUWDAO {
/*  27 */   private Log log = LogFactory.getLog(WmsUWDAO.class); private static final String SQL_COUNT = "select count(*) from WmsUnplannedWarehousing pwom"; private static final String SQL = "from WmsUnplannedWarehousing pwom";
/*     */   
/*     */   public WmsUnplannedWarehousing getWmsUnplannedWarehousing(Integer id) {
/*  30 */     if (id == null) {
/*  31 */       if (this.log.isDebugEnabled())
/*  32 */         this.log.debug("try to get WmsUnplannedWarehousing with null id"); 
/*  33 */       return null;
/*     */     } 
/*  35 */     WmsUnplannedWarehousing s = (WmsUnplannedWarehousing)getHibernateTemplate()
/*  36 */       .get(WmsUnplannedWarehousing.class, id);
/*  37 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  45 */       { WmsUnplannedWarehousingQueryCondition.ID_EQ, "pwom.id = ?"
/*  46 */       }, { WmsUnplannedWarehousingQueryCondition.ID_BEGINWITH, 
/*  47 */         "pwom.code like ?", new QueryParameterConvert() {
/*     */           public Object convert(Object src) {
/*  49 */             return src + "%";
/*     */           }
/*     */         } }
/*     */     };
/*     */ 
/*     */   
/*  55 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  56 */       { WmsUnplannedWarehousingQueryOrder.ID, "pwom.id" }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWmsUnplannedWarehousingListCount(Map conditions) {
/*  62 */     return getListCount(conditions, "select count(*) from WmsUnplannedWarehousing pwom", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getWmsUnplannedWarehousingList(Map conditions, int pageNo, int pageSize, WmsUnplannedWarehousingQueryOrder order, boolean descend) {
/*  69 */     return getList(conditions, pageNo, pageSize, order, descend, "from WmsUnplannedWarehousing pwom", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WmsUnplannedWarehousing insertWmsUnplannedWarehousing(WmsUnplannedWarehousing pwom) {
/*  75 */     getHibernateTemplate().save(pwom);
/*  76 */     return pwom;
/*     */   }
/*     */ 
/*     */   
/*     */   public WmsUnplannedWarehousing updateWmsUnplannedWarehousing(WmsUnplannedWarehousing pwom) {
/*  81 */     Integer id = pwom.getId();
/*  82 */     if (id == null) {
/*  83 */       throw new RuntimeException("cannot save a ct with null id");
/*     */     }
/*  85 */     WmsUnplannedWarehousing oldProduceInStorage = getWmsUnplannedWarehousing(id);
/*  86 */     if (oldProduceInStorage != null) {
/*     */       try {
/*  88 */         PropertyUtils.copyProperties(oldProduceInStorage, pwom);
/*  89 */       } catch (Exception e) {
/*  90 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/*  92 */       getHibernateTemplate().update(oldProduceInStorage);
/*  93 */       return oldProduceInStorage;
/*     */     } 
/*  95 */     throw new RuntimeException("ct not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMaxPoApplicationIdBeginWith(String prefix) {
/* 100 */     String sql = "select max(pwom.code) from WmsUnplannedWarehousing pwom";
/* 101 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 102 */     conds.put(WmsUnplannedWarehousingQueryCondition.ID_BEGINWITH, prefix);
/* 103 */     List<String> l = getList(conds, 0, -1, null, false, sql, QUERY_CONDITIONS, QUERY_ORDERS);
/* 104 */     if (l.isEmpty()) {
/* 105 */       return null;
/*     */     }
/* 107 */     return l.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public WmsUnplannedWarehousingItem getWmsUnplannedWarehousingItem(int id) {
/* 112 */     return (WmsUnplannedWarehousingItem)getHibernateTemplate().get(WmsUnplannedWarehousingItem.class, Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WmsUnplannedWarehousingItem insertWmsUnplannedWarehousingItem(WmsUnplannedWarehousingItem pwom) {
/* 118 */     getHibernateTemplate().save(pwom);
/* 119 */     return pwom;
/*     */   }
/*     */ 
/*     */   
/*     */   public WmsUnplannedWarehousingItem updateWmsUnplannedWarehousingItem(WmsUnplannedWarehousingItem pwom) {
/* 124 */     Integer id = pwom.getId();
/* 125 */     if (id == null) {
/* 126 */       throw new RuntimeException("cannot save a ct with null id");
/*     */     }
/* 128 */     WmsUnplannedWarehousingItem oldProduceInStorage = getWmsUnplannedWarehousingItem(id.intValue());
/* 129 */     if (oldProduceInStorage != null) {
/*     */       try {
/* 131 */         PropertyUtils.copyProperties(oldProduceInStorage, pwom);
/* 132 */       } catch (Exception e) {
/* 133 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/* 135 */       getHibernateTemplate().update(oldProduceInStorage);
/* 136 */       return oldProduceInStorage;
/*     */     } 
/* 138 */     throw new RuntimeException("ct not found");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<WmsUnplannedWarehousingItem> getWmsUnplannedWarehousingItemByMain(Integer id) {
/* 144 */     String sql = "from WmsUnplannedWarehousingItem item where item.unplanned_warehousing_id.id = ?";
/* 145 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteWmsUWItem(WmsUnplannedWarehousingItem item) {
/* 150 */     getHibernateTemplate().delete(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer wmsUwIsPrintFinishByItem(Integer id) {
/* 155 */     return null;
/*     */   }
/*     */   
/*     */   public Integer getWmsUWItemIsPrintByMain(final Integer id) {
/* 159 */     String sql = "from WmsUnplannedWarehousingItem item where item.unplanned.id = ? and item.isPrint=1";
/* 160 */     return (Integer)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException
/*     */           {
/* 164 */             Query query = session.createQuery("from WmsUnplannedWarehousingItem item where item.unplanned.id = ? and item.isPrint=1");
/* 165 */             query.setInteger(0, id.intValue());
/* 166 */             List list = query.list();
/*     */ 
/*     */ 
/*     */             
/*     */             try {  }
/* 171 */             catch (Exception e)
/* 172 */             { e.getStackTrace(); }
/*     */             finally
/* 174 */             { if (session != null && session.isOpen())
/* 175 */                 session.close();  }  if (session != null && session.isOpen()) session.close();
/*     */ 
/*     */ 
/*     */             
/* 179 */             return Integer.valueOf(list.size());
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public Integer getWmsUnplannedWarehousingByBox(final Integer id) {
/* 185 */     String sql = "from PoIpiBox box where box.wmsUWItem.unplanned.id = ? and box.isEnabled=0";
/*     */     
/* 187 */     return (Integer)getHibernateTemplate().execute(
/* 188 */         new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException
/*     */           {
/* 192 */             Query query = session.createQuery("from PoIpiBox box where box.wmsUWItem.unplanned.id = ? and box.isEnabled=0");
/* 193 */             query.setInteger(0, id.intValue());
/* 194 */             List list = query.list();
/*     */ 
/*     */ 
/*     */             
/*     */             try {  }
/* 199 */             catch (Exception e)
/* 200 */             { e.getStackTrace(); }
/*     */             finally
/* 202 */             { if (session != null && session.isOpen())
/* 203 */                 session.close();  }  if (session != null && session.isOpen()) session.close();
/*     */ 
/*     */             
/* 206 */             return Integer.valueOf(list.size());
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public Integer getWmsUnplannedWarehousingByBoxAll(final Integer id) {
/* 212 */     String sql = "from PoIpiBox box where box.wmsUWItem.unplanned.id = ? and box.isEnabled=0 and box.isPutIntStorage=0";
/*     */     
/* 214 */     return (Integer)getHibernateTemplate().execute(
/* 215 */         new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException
/*     */           {
/* 219 */             Query query = session.createQuery("from PoIpiBox box where box.wmsUWItem.unplanned.id = ? and box.isEnabled=0 and box.isPutIntStorage=0");
/* 220 */             query.setInteger(0, id.intValue());
/* 221 */             List list = query.list();
/*     */ 
/*     */ 
/*     */             
/*     */             try {  }
/* 226 */             catch (Exception e)
/* 227 */             { e.getStackTrace(); }
/*     */             finally
/* 229 */             { if (session != null && session.isOpen())
/* 230 */                 session.close();  }  if (session != null && session.isOpen()) session.close();
/*     */ 
/*     */ 
/*     */             
/* 234 */             return Integer.valueOf(list.size());
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public WmsUnplannedWarehousing getWmsUnplannedWarehousingByWorkOrderBox(Integer id) {
/* 241 */     String hql = "from WmsUnplannedWarehousing uw where uw.id in (select item.unplanned.id from WmsUnplannedWarehousingItem item where item.workOrderBox.id = ?)";
/* 242 */     List<WmsUnplannedWarehousing> list = getHibernateTemplate().find(hql, id, (Type)Hibernate.INTEGER);
/* 243 */     if (list == null || list.isEmpty()) {
/* 244 */       return null;
/*     */     }
/* 246 */     return list.get(0);
/*     */   }
/*     */   
/*     */   public Integer getWmsUnplannedWarehousingByProduceWorkOrderBox(final Integer id) {
/* 250 */     String sql = "from WmsUnplannedWarehousingItem item where item.unplanned.id = ?";
/*     */     
/* 252 */     return (Integer)getHibernateTemplate().execute(
/* 253 */         new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException
/*     */           {
/* 257 */             Query query = session.createQuery("from WmsUnplannedWarehousingItem item where item.unplanned.id = ?");
/* 258 */             query.setInteger(0, id.intValue());
/* 259 */             List list = query.list();
/*     */ 
/*     */ 
/*     */             
/*     */             try {  }
/* 264 */             catch (Exception e)
/* 265 */             { e.getStackTrace(); }
/*     */             finally
/* 267 */             { if (session != null && session.isOpen())
/* 268 */                 session.close();  }  if (session != null && session.isOpen()) session.close();
/*     */ 
/*     */ 
/*     */             
/* 272 */             return Integer.valueOf(list.size());
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public Integer getWmsUnplannedWarehousingByProduceWorkOrderBoxAll(final Integer id) {
/* 278 */     String sql = "from WmsUnplannedWarehousingItem item where item.unplanned.id = ? and item.workOrderBox.enabled = 0 and item.workOrderBox.isInStorage = 0";
/*     */     
/* 280 */     return (Integer)getHibernateTemplate().execute(
/* 281 */         new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException
/*     */           {
/* 285 */             Query query = session.createQuery("from WmsUnplannedWarehousingItem item where item.unplanned.id = ? and item.workOrderBox.enabled = 0 and item.workOrderBox.isInStorage = 0");
/* 286 */             query.setInteger(0, id.intValue());
/* 287 */             List list = query.list();
/*     */ 
/*     */ 
/*     */             
/*     */             try {  }
/* 292 */             catch (Exception e)
/* 293 */             { e.getStackTrace(); }
/*     */             finally
/* 295 */             { if (session != null && session.isOpen())
/* 296 */                 session.close();  }  if (session != null && session.isOpen()) session.close();
/*     */ 
/*     */             
/* 299 */             return Integer.valueOf(list.size());
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteWmsUW(WmsUnplannedWarehousing warehousing) {
/* 306 */     getHibernateTemplate().delete(warehousing);
/*     */   }
/*     */   
/*     */   public List<WmsUnplannedWarehousingItem> getBoxByWmsUnplannedWarehousing(Integer id) {
/* 310 */     String sql = "from WmsUnplannedWarehousingItem item where item.unplanned_warehousing_id.id = ?";
/*     */     
/* 312 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/plantWarehouse/hibernate/WmsUWDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
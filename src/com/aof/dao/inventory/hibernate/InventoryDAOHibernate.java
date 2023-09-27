/*     */ package com.aof.dao.inventory.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.dao.convert.QuerySQLConvert;
/*     */ import com.aof.dao.inventory.InventoryDAO;
/*     */ import com.aof.model.basicDataView.query.LocationPartNumberQueryCondition;
/*     */ import com.aof.model.basicDataView.query.LocationPartNumberQueryOrder;
/*     */ import com.aof.model.inventory.Inventory;
/*     */ import com.aof.model.inventory.InventoryDetial;
/*     */ import com.aof.model.inventory.InventoryRecord;
/*     */ import com.aof.model.inventory.InventoryTotal;
/*     */ import com.aof.model.inventory.query.InventoryQueryCondition;
/*     */ import com.aof.model.inventory.query.InventoryQueryOrder;
/*     */ import com.shcnc.hibernate.CompositeQuery;
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
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
/*     */ public class InventoryDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements InventoryDAO
/*     */ {
/*  48 */   private Log log = LogFactory.getLog(InventoryDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from Inventory it";
/*     */   
/*     */   private static final String SQL = "from Inventory it";
/*     */   
/*  54 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  55 */       { InventoryQueryCondition.ID_EQ, "it.id = ?"
/*  56 */       }, { InventoryQueryCondition.STATUS_EQ, "it.box.status = ?"
/*  57 */       }, { InventoryQueryCondition.TYPE_EQ, "it.box.type = ?"
/*  58 */       }, { InventoryQueryCondition.PART_TYPE_EQ, "it.part.productClass = ?" }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*  63 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  64 */       { InventoryQueryOrder.ID, "it.id" } }; private static final String SQL_COUNT_REPORT = "select count(*) from InventoryTotal it,InventoryDetial item where it.id = item.inventory_total_id.id";
/*     */   private static final String SQL_REPORT = "from InventoryTotal it,InventoryDetial item where it.id = item.inventory_total_id.id";
/*     */   
/*     */   public Inventory getInventory(Integer id) {
/*  68 */     if (id == null) {
/*  69 */       if (this.log.isDebugEnabled())
/*  70 */         this.log.debug("try to get Inventory with null id"); 
/*  71 */       return null;
/*     */     } 
/*  73 */     return (Inventory)getHibernateTemplate().get(Inventory.class, id);
/*     */   }
/*     */   private static final String SQL_ITEM_COUNT = "select count(*) from InventoryRecord srt "; private static final String SQL_ITEM = "from InventoryRecord srt ";
/*     */   public int getInventoryListCount(Map conditions) {
/*  77 */     return getListCount(conditions, "select count(*) from Inventory it", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getInventoryList(Map conditions, int pageNo, int pageSize, InventoryQueryOrder order, boolean descend) {
/*  82 */     return getList(conditions, pageNo, pageSize, order, descend, "from Inventory it", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */   
/*     */   public List<Object> getInventoryListsum(Map conditions) {
/*  86 */     List<Object> result = new ArrayList();
/*     */     try {
/*  88 */       CompositeQuery query = new CompositeQuery("select produceScanRecord.box.wmsPart.id,sum(produceScanRecord.count)  from ProduceScanRecord produceScanRecord group by produceScanRecord.box.wmsPart.id", 
/*  89 */           "", getSession());
/*  90 */       if (conditions != null) appendConditions(query, conditions, QUERY_CONDITIONS); 
/*  91 */       result = query.list();
/*     */     }
/*  93 */     catch (Exception e) {
/*  94 */       e.fillInStackTrace();
/*     */     } 
/*  96 */     return result;
/*     */   }
/*     */   
/*     */   public Inventory insertInventory(Inventory inventory) {
/* 100 */     getHibernateTemplate().save(inventory);
/* 101 */     return inventory;
/*     */   }
/*     */   
/*     */   public Inventory updateInventory(Inventory inventory) {
/* 105 */     Integer id = inventory.getId();
/* 106 */     if (id == null) {
/* 107 */       throw new RuntimeException("cannot save a inventory with null id");
/*     */     }
/* 109 */     Inventory oldInventory = getInventory(id);
/* 110 */     if (oldInventory != null) {
/*     */       try {
/* 112 */         PropertyUtils.copyProperties(oldInventory, inventory);
/* 113 */       } catch (Exception e) {
/* 114 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/* 116 */       getHibernateTemplate().update(oldInventory);
/* 117 */       return oldInventory;
/*     */     } 
/* 119 */     throw new RuntimeException("srt not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteInventory(Inventory inventory) {
/* 124 */     getHibernateTemplate().delete(inventory);
/*     */   }
/*     */   
/*     */   public InventoryRecord insertInventoryRecord(InventoryRecord inventoryRecord) {
/* 128 */     getHibernateTemplate().save(inventoryRecord);
/* 129 */     return inventoryRecord;
/*     */   }
/*     */   
/*     */   public InventoryRecord getInventoryRecordByInventoryId(Integer InventoryId) {
/* 133 */     String sql = "from InventoryRecord ir where ir.inventoryId.id = ?";
/* 134 */     return (InventoryRecord)getHibernateTemplate().find(sql, InventoryId, (Type)Hibernate.INTEGER);
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
/* 145 */   private static final Object[][] QUERY_ITEM_CONDITIONS = new Object[0][];
/*     */ 
/*     */ 
/*     */   
/* 149 */   private static final Object[][] QUERY_ITEM_ORDERS = new Object[0][]; private static final String SQL_ITEM_SUMMARY_PART = "select srt.inventoryId.partCode.id from InventoryRecord srt   group by srt.inventoryId.partCode.id"; private static final String SQL_COUNT_DETIAL = "select count(*) from InventoryDetial ind";
/*     */   private static final String SQL_DETIAL = "from InventoryDetial ind";
/*     */   private static final String SQL_COUNT_DETIAL_PART = "select count(*) from InventoryDetial ind where ind.id in (select max(ind.id) from InventoryDetial ind group by ind.part.id) ";
/*     */   private static final String SQL_DETIAL_PART = "from InventoryDetial ind where ind.id in (select max(ind.id) from InventoryDetial ind group by ind.part.id) ";
/*     */   
/*     */   public int getInventoryReportListCount(Map conditions) {
/* 155 */     return getListCount(conditions, "select count(*) from InventoryTotal it,InventoryDetial item where it.id = item.inventory_total_id.id", QUERY_ITEM_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getInventoryReportList(Map conditions, int pageNo, int pageSize, InventoryQueryOrder order, boolean descend) {
/* 161 */     return getList(conditions, pageNo, pageSize, order, descend, "from InventoryTotal it,InventoryDetial item where it.id = item.inventory_total_id.id", QUERY_CONDITIONS, QUERY_ITEM_ORDERS);
/*     */   }
/*     */   
/*     */   public InventoryRecord getInventoryRecord(Integer id) {
/* 165 */     if (id == null) {
/* 166 */       if (this.log.isDebugEnabled())
/* 167 */         this.log.debug("try to get Inventory with null id"); 
/* 168 */       return null;
/*     */     } 
/* 170 */     return (InventoryRecord)getHibernateTemplate().get(InventoryRecord.class, id);
/*     */   }
/*     */   
/*     */   public List<Inventory> getInventoryByPart(String part) {
/* 174 */     String sql = "from Inventory it where it.partCode.id = ?";
/* 175 */     return getHibernateTemplate().find(sql, part, (Type)Hibernate.STRING);
/*     */   }
/*     */   
/*     */   public List<Inventory> getInventoryByPart(String part, Integer site) {
/* 179 */     String sql = "from Inventory it where it.partCode.id = ? and it.storeroomId.site= ?";
/* 180 */     Object[] parmart = { part, site };
/* 181 */     return getHibernateTemplate().find(sql, parmart);
/*     */   }
/*     */   
/*     */   public List<Inventory> getInventoryByPart(String part, Integer site, Integer n) {
/* 185 */     String sql = "from Inventory it where it.partCode.id = ? and it.storeroomId.site= ? and it.storeroomId.type=" + n;
/* 186 */     Object[] parmart = { part, site };
/* 187 */     return getHibernateTemplate().find(sql, parmart);
/*     */   }
/*     */   public List<Inventory> getInventoryByRoomAndPart(Integer room, String part) {
/* 190 */     String sql = "from Inventory it where it.storeroomId.type.id = ? and it.partCode.id = ?";
/* 191 */     Object[] parmart = { room, part };
/* 192 */     return getHibernateTemplate().find(sql, parmart);
/*     */   }
/*     */   
/*     */   public BigDecimal getInventoryRecord(Date startDate, Date endDate, String part) {
/* 196 */     String sql = "select sum(it.number)from InventoryRecord it where it.inventoryId.partCode.id = ? and it.date in(?,?) ";
/* 197 */     Object[] parmart = { part, startDate, endDate };
/* 198 */     List<BigDecimal> list = getHibernateTemplate().find(sql, parmart);
/* 199 */     if (list == null || list.size() == 0) {
/* 200 */       return new BigDecimal(0);
/*     */     }
/* 202 */     BigDecimal num = list.get(0);
/* 203 */     if (num == null) {
/* 204 */       return new BigDecimal(0);
/*     */     }
/* 206 */     return list.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Inventory getInventoryByPartIdAndLocation(String id) {
/* 213 */     String sql = "from Inventory inv where inv.partCode.id = ? and inv.storeroomId.id in(39,40)";
/* 214 */     List<Inventory> list = getHibernateTemplate().find(sql, id, (Type)Hibernate.STRING);
/* 215 */     if (list == null || list.isEmpty()) {
/* 216 */       return null;
/*     */     }
/* 218 */     return list.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getInventoryRecordReturnPeriodInitialValue(String str, String strPart) {
/* 224 */     String[] arrays = str.split(";");
/* 225 */     String sql = "from InventoryRecord ir where ";
/* 226 */     String sqlSum = ""; byte b; int i; String[] arrayOfString1;
/* 227 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String array = arrayOfString1[b];
/* 228 */       String[] locAndPart = array.split(",");
/* 229 */       String location = locAndPart[0];
/* 230 */       String part = locAndPart[1];
/*     */       
/* 232 */       String sqlLoc = " ir.location = ";
/* 233 */       String sqlPart = " ir.wmsPart = ";
/* 234 */       sqlLoc = String.valueOf(sqlLoc) + location + "or ";
/* 235 */       sqlPart = String.valueOf(sqlPart) + part + " ";
/* 236 */       sqlSum = String.valueOf(sqlSum) + sqlLoc + sqlPart; b++; }
/*     */     
/* 238 */     sql = String.valueOf(sql) + sqlSum;
/*     */     
/* 240 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryRecord getInventoryRecordByLocationAndPart(final String partId, final String location) {
/* 245 */     String sql = "from InventoryRecord ir where ir.inventoryId.partCode.id = ? and ir.location.code = ? order by ir.date desc";
/* 246 */     return (InventoryRecord)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException
/*     */           {
/* 250 */             Query query = session.createQuery("from InventoryRecord ir where ir.inventoryId.partCode.id = ? and ir.location.code = ? order by ir.date desc");
/* 251 */             query.setString(0, partId);
/* 252 */             query.setString(1, location);
/* 253 */             query.setFetchSize(0);
/* 254 */             query.setMaxResults(1);
/*     */             
/* 256 */             List list = query.list();
/* 257 */             if (list == null || list.isEmpty()) {
/* 258 */               return null;
/*     */             }
/* 260 */             return list.get(0);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public InventoryRecord getInventoryRecordByLocationAndPartBalance(final String partId, final String location) {
/* 266 */     String sql = "from InventoryRecord ir where ir.inventoryId.partCode.id = ? and ir.location.code = ? order by ir.date";
/* 267 */     return (InventoryRecord)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException
/*     */           {
/* 271 */             Query query = session.createQuery("from InventoryRecord ir where ir.inventoryId.partCode.id = ? and ir.location.code = ? order by ir.date");
/* 272 */             query.setString(0, partId);
/* 273 */             query.setString(1, location);
/* 274 */             query.setFetchSize(0);
/* 275 */             query.setMaxResults(1);
/*     */             
/* 277 */             List list = query.list();
/*     */ 
/*     */ 
/*     */             
/*     */             try {  }
/* 282 */             catch (Exception e)
/* 283 */             { e.getStackTrace(); }
/*     */             finally
/* 285 */             { if (session != null && session.isOpen())
/* 286 */                 session.close();  }  if (session != null && session.isOpen()) session.close();
/*     */ 
/*     */ 
/*     */             
/* 290 */             return list.get(0);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InventoryRecord getInventoryRecordByLocationAndPart(final String partId) {
/* 300 */     String sql = "from InventoryRecord ir where ir.inventoryId.partCode.id = ?  order by ir.date";
/* 301 */     return (InventoryRecord)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException
/*     */           {
/* 305 */             Query query = session.createQuery("from InventoryRecord ir where ir.inventoryId.partCode.id = ?  order by ir.date");
/* 306 */             query.setString(0, partId);
/* 307 */             query.setFetchSize(0);
/* 308 */             query.setMaxResults(1);
/*     */             
/* 310 */             List list = query.list();
/* 311 */             if (list == null || list.isEmpty()) {
/* 312 */               return null;
/*     */             }
/* 314 */             return list.get(0);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public InventoryRecord getInventoryRecordByLocationAndPartBalance(final String partId) {
/* 320 */     String sql = "from InventoryRecord ir where ir.inventoryId.partCode.id = ?  order by ir.date";
/* 321 */     return (InventoryRecord)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException
/*     */           {
/* 325 */             Query query = session.createQuery("from InventoryRecord ir where ir.inventoryId.partCode.id = ?  order by ir.date");
/* 326 */             query.setString(0, partId);
/* 327 */             query.setFetchSize(0);
/* 328 */             query.setMaxResults(1);
/*     */             
/* 330 */             List list = query.list();
/*     */ 
/*     */ 
/*     */             
/*     */             try {  }
/* 335 */             catch (Exception e)
/* 336 */             { e.getStackTrace(); }
/*     */             finally
/* 338 */             { if (session != null && session.isOpen())
/* 339 */                 session.close();  }  if (session != null && session.isOpen()) session.close();
/*     */ 
/*     */ 
/*     */             
/* 343 */             return list.get(0);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public BigDecimal getInventoryBySemiFinished() {
/* 349 */     String sql = "select sum(item.count) from InventoryItem item where item.location.storeroom_id.type.id = 22";
/* 350 */     List<Object> objs = getHibernateTemplate().find(sql);
/* 351 */     if (objs != null || objs.isEmpty()) {
/* 352 */       return (BigDecimal)objs.get(0);
/*     */     }
/* 354 */     return new BigDecimal(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal getInventoryByPowderStorage() {
/* 359 */     String sql = "select sum(item.count) from InventoryItem item where item.location.code like 'SA%' ";
/* 360 */     List<Object> objs = getHibernateTemplate().find(sql);
/* 361 */     if (objs != null || objs.isEmpty()) {
/* 362 */       return (BigDecimal)objs.get(0);
/*     */     }
/* 364 */     return new BigDecimal(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryDetial getInventoryDetial(Integer id) {
/* 369 */     if (id == null) {
/* 370 */       if (this.log.isDebugEnabled())
/* 371 */         this.log.debug("try to get InventoryDetial with null id"); 
/* 372 */       return null;
/*     */     } 
/* 374 */     return (InventoryDetial)getHibernateTemplate().get(InventoryDetial.class, id);
/*     */   }
/*     */   
/*     */   public InventoryDetial insertInventoryDetial(InventoryDetial detial) {
/* 378 */     getHibernateTemplate().save(detial);
/* 379 */     return detial;
/*     */   }
/*     */   
/*     */   public InventoryDetial updateInventoryDetial(InventoryDetial detial) {
/* 383 */     Integer id = detial.getId();
/* 384 */     if (id == null) {
/* 385 */       throw new RuntimeException("cannot save a detial with null id");
/*     */     }
/* 387 */     InventoryDetial oldInventory = getInventoryDetial(id);
/* 388 */     if (oldInventory != null) {
/*     */       try {
/* 390 */         PropertyUtils.copyProperties(oldInventory, detial);
/* 391 */       } catch (Exception e) {
/* 392 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/* 394 */       getHibernateTemplate().update(oldInventory);
/* 395 */       return oldInventory;
/*     */     } 
/* 397 */     throw new RuntimeException("srt not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryTotal getInventoryTotal(Integer id) {
/* 402 */     if (id == null) {
/* 403 */       if (this.log.isDebugEnabled())
/* 404 */         this.log.debug("try to get InventoryTotal with null id"); 
/* 405 */       return null;
/*     */     } 
/* 407 */     return (InventoryTotal)getHibernateTemplate().get(
/* 408 */         InventoryTotal.class, id);
/*     */   }
/*     */   
/*     */   public InventoryTotal insertInventoryTotal(InventoryTotal inventoryTotal) {
/* 412 */     getHibernateTemplate().save(inventoryTotal);
/* 413 */     return inventoryTotal;
/*     */   }
/*     */   
/*     */   public InventoryTotal updateInventoryTotal(InventoryTotal inventoryTotal) {
/* 417 */     Integer id = inventoryTotal.getId();
/* 418 */     if (id == null) {
/* 419 */       throw new RuntimeException("cannot save a inventory with null id");
/*     */     }
/* 421 */     InventoryTotal oldInventory = getInventoryTotal(id);
/* 422 */     if (oldInventory != null) {
/*     */       try {
/* 424 */         PropertyUtils.copyProperties(oldInventory, inventoryTotal);
/* 425 */       } catch (Exception e) {
/* 426 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/* 428 */       getHibernateTemplate().update(oldInventory);
/* 429 */       return oldInventory;
/*     */     } 
/* 431 */     throw new RuntimeException("srt not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryDetial getInventoryTotalByPart(String part) {
/* 436 */     String sql = "from InventoryDetial item where item.part.id = ?";
/* 437 */     List<InventoryDetial> list = getHibernateTemplate().find(sql, part, (Type)Hibernate.STRING);
/* 438 */     if (list == null || list.isEmpty()) {
/* 439 */       return null;
/*     */     }
/* 441 */     return list.get(0);
/*     */   }
/*     */   
/*     */   public List test() {
/* 445 */     String sql = "from InventoryTotal it,InventoryDetial item where it.id = item.inventory_total_id.id";
/* 446 */     List list = getHibernateTemplate().find(sql);
/* 447 */     return list;
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
/*     */   
/* 460 */   private static final Object[][] QUERY_CONDITIONS_DETIAL = new Object[][] {
/* 461 */       { InventoryQueryCondition.ID_EQ, "ind.id = ?"
/* 462 */       }, { InventoryQueryCondition.DETAIL_STORAGE_EQ, "ind.location.id = ?"
/* 463 */       }, { InventoryQueryCondition.DETAIL_PART_EQ, "ind.part.id = ?"
/* 464 */       }, { InventoryQueryCondition.NUMBER_NE, "ind.number <> ?"
/* 465 */       }, { InventoryQueryCondition.NUMBER_GE, "ind.number > ?"
/* 466 */       }, { InventoryQueryCondition.DETAIL_STORAGE_ID_EQ, "ind.location.basic_storeroom_id.type = ?"
/* 467 */       }, { InventoryQueryCondition.PART_EQ, "ind.part.id = ?"
/* 468 */       }, { InventoryQueryCondition.PART_ID_TYPE_LIKE, "ind.part.id like ?", new LikeConvert()
/* 469 */       }, { InventoryQueryCondition.LOCATION_ID_IN, "ind.location.id in ", new QuerySQLConvert() {
/*     */           public Object convert(StringBuffer sql, Object parameter) {
/* 471 */             if (parameter != null && parameter instanceof Object[]) {
/* 472 */               Object[] finalParameter = (Object[])parameter;
/* 473 */               if (finalParameter.length > 0) {
/* 474 */                 sql.append("(?");
/* 475 */                 for (int i = 1; i < finalParameter.length; i++) {
/* 476 */                   sql.append(",?");
/*     */                 }
/* 478 */                 sql.append(")");
/*     */               } else {
/* 480 */                 return finalParameter;
/*     */               } 
/*     */             } else {
/* 483 */               return parameter;
/*     */             } 
/* 485 */             return parameter;
/*     */           }
/*     */         } }
/*     */     };
/*     */   
/* 490 */   private static final Object[][] QUERY_ORDERS_DETIAL = new Object[][] {
/* 491 */       { InventoryQueryOrder.ID, "ind.id"
/* 492 */       }, { InventoryQueryOrder.PART_ID, "ind.part.id" } };
/*     */   private static final String SQL_COUNT_TOTAL = "select count(*) from InventoryTotal ivt";
/*     */   private static final String SQL_TOTAL = "from InventoryTotal ivt";
/*     */   
/*     */   public int getInventoryDetialListCount(Map conditions) {
/* 497 */     return getListCount(conditions, "select count(*) from InventoryDetial ind", QUERY_CONDITIONS_DETIAL);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getInventoryDetialList(Map conditions, int pageNo, int pageSize, InventoryQueryOrder order, boolean descend) {
/* 503 */     return getList(conditions, pageNo, pageSize, order, descend, "from InventoryDetial ind", QUERY_CONDITIONS_DETIAL, QUERY_ORDERS_DETIAL);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getInventoryDetialByPartList(Map conditions, int pageNo, int pageSize, InventoryQueryOrder order, boolean descend) {
/* 509 */     return getList(conditions, pageNo, pageSize, order, descend, "from InventoryDetial ind where ind.id in (select max(ind.id) from InventoryDetial ind group by ind.part.id) ", QUERY_CONDITIONS_DETIAL, QUERY_ORDERS_DETIAL);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInventoryDetialByPartListCount(Map conditions) {
/* 514 */     return getListCount(conditions, "select count(*) from InventoryDetial ind where ind.id in (select max(ind.id) from InventoryDetial ind group by ind.part.id) ", QUERY_CONDITIONS_DETIAL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 520 */   private static final Object[][] QUERY_CONDITIONS_TOTAL = new Object[][] {
/* 521 */       { InventoryQueryCondition.ID_EQ, "ivt.id = ?" }
/*     */     };
/*     */   
/* 524 */   private static final Object[][] QUERY_ORDERS_TOTAL = new Object[][] {
/* 525 */       { InventoryQueryOrder.ID, "ivt.id" } };
/*     */   private static final String SQL_COUNT_LOCATIONPARTNUMBER = "select count(*) from LocationPartNumber lpn";
/*     */   private static final String SQL_LOCATIONPARTNUMBER = "from LocationPartNumber lpn";
/*     */   
/*     */   public int getInventoryTotalList(Map conditions) {
/* 530 */     return getListCount(conditions, "select count(*) from InventoryTotal ivt", QUERY_CONDITIONS_TOTAL);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getInventoryTotalList(Map conditions, int pageNo, int pageSize, InventoryQueryOrder order, boolean descend) {
/* 536 */     return getList(conditions, pageNo, pageSize, order, descend, "from InventoryTotal ivt", QUERY_CONDITIONS_TOTAL, QUERY_ORDERS_TOTAL);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InventoryDetial getInventoryDetialByPartAndLocation(String partId, Integer locationId) {
/* 542 */     String hql = "from  InventoryDetial i where i.location.id=? and i.part.id=?";
/* 543 */     Object[] params = { locationId, partId };
/* 544 */     List<InventoryDetial> list = getHibernateTemplate().find(hql, params);
/* 545 */     if (list == null || list.isEmpty()) {
/* 546 */       return null;
/*     */     }
/* 548 */     return list.get(0);
/*     */   }
/*     */   public List getBoxSumCount(String pratId) {
/* 551 */     String sql = "select sum(ind.number) from InventoryDetial ind where ind.part.id = '" + pratId + "'";
/* 552 */     List listsCount = getHibernateTemplate().find(sql);
/* 553 */     return listsCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 559 */   private static final Object[][] QUERY_CONDITIONS_LOCATIONPARTNUMBER = new Object[][] {
/* 560 */       { LocationPartNumberQueryCondition.NUMBER_QT, "lpn.number > ?" }
/*     */     };
/*     */   
/* 563 */   private static final Object[][] QUERY_ORDERS_LOCATIONPARTNUMBER = new Object[][] {
/* 564 */       { LocationPartNumberQueryOrder.LOCATION_ID, "lpn.location.id"
/* 565 */       }, { LocationPartNumberQueryOrder.PART_ID, "lpn.part.id"
/* 566 */       }, { LocationPartNumberQueryOrder.NUMBER, "lpn.number"
/* 567 */       }, { LocationPartNumberQueryOrder.PARTQTY, "lpn.partQty"
/* 568 */       }, { LocationPartNumberQueryOrder.OCCUPIEDNUMBER, "lpn.occupiedNumber" } };
/*     */   
/*     */   public int getLocationPartNumberListCount(Map conditions) {
/* 571 */     return getListCount(conditions, "select count(*) from LocationPartNumber lpn", QUERY_ORDERS_LOCATIONPARTNUMBER);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getLocationPartNumberList(Map conditions, int pageNo, int pageSize, LocationPartNumberQueryOrder order, boolean descend) {
/* 576 */     return getList(conditions, pageNo, pageSize, order, descend, "from LocationPartNumber lpn", QUERY_CONDITIONS_LOCATIONPARTNUMBER, QUERY_ORDERS_LOCATIONPARTNUMBER);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/inventory/hibernate/InventoryDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
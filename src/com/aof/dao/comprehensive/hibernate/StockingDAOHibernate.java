/*     */ package com.aof.dao.comprehensive.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.comprehensive.StockingDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.dao.convert.QueryParameterConvert;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.comprehensive.Stocking;
/*     */ import com.aof.model.comprehensive.StockingDetial;
/*     */ import com.aof.model.comprehensive.StockingRecord;
/*     */ import com.aof.model.comprehensive.StockingScanRecord;
/*     */ import com.aof.model.comprehensive.StockingSweepRecord;
/*     */ import com.aof.model.comprehensive.query.StockingDetialQueryCondition;
/*     */ import com.aof.model.comprehensive.query.StockingDetialQueryOrder;
/*     */ import com.aof.model.comprehensive.query.StockingQueryCondition;
/*     */ import com.aof.model.comprehensive.query.StockingQueryOrder;
/*     */ import com.aof.model.comprehensive.query.StockingScanRecordQueryCondition;
/*     */ import com.aof.model.comprehensive.query.StockingScanRecordQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.SQLException;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.Hibernate;
/*     */ import net.sf.hibernate.HibernateException;
/*     */ import net.sf.hibernate.Session;
/*     */ import net.sf.hibernate.Transaction;
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
/*     */ public class StockingDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements StockingDAO
/*     */ {
/*  52 */   private Log log = LogFactory.getLog(StockingDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from Stocking sk";
/*     */   
/*     */   private static final String SQL = "from Stocking sk";
/*     */ 
/*     */   
/*     */   public Stocking getStocking(Integer id) {
/*  60 */     if (id == null) {
/*  61 */       if (this.log.isDebugEnabled())
/*  62 */         this.log.debug("try to get Stocking with null id"); 
/*  63 */       return null;
/*     */     } 
/*  65 */     return (Stocking)getHibernateTemplate().get(Stocking.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Stocking updateStocking(Stocking ct) {
/*  74 */     Integer id = ct.getId();
/*  75 */     if (id == null) {
/*  76 */       throw new RuntimeException("cannot save a ct with null id");
/*     */     }
/*  78 */     Stocking oldStocking = getStocking(id);
/*  79 */     if (oldStocking != null) {
/*     */       try {
/*  81 */         PropertyUtils.copyProperties(oldStocking, ct);
/*  82 */       } catch (Exception e) {
/*  83 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/*  85 */       getHibernateTemplate().update(oldStocking);
/*  86 */       return oldStocking;
/*     */     } 
/*  88 */     throw new RuntimeException("ct not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Stocking insertStocking(Stocking ct) {
/*  98 */     getHibernateTemplate().save(ct);
/*  99 */     return ct;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 107 */       { StockingQueryCondition.ID_EQ, "sk.id = ?"
/* 108 */       }, { StockingQueryCondition.DESCRIBE_EQ, "sk.describe = ?"
/* 109 */       }, { StockingQueryCondition.ENABLED_EQ, "sk.enabled = ?"
/* 110 */       }, { StockingQueryCondition.STATUS_EQ, "sk.status = ?"
/* 111 */       }, { StockingQueryCondition.CODE_EQ, "sk.code like ?", new LikeConvert()
/* 112 */       }, { StockingQueryCondition.ID_BEGINWITH, 
/* 113 */         "sk.code like ?", new QueryParameterConvert() {
/*     */           public Object convert(Object src) {
/* 115 */             return src + "%";
/*     */           }
/*     */         } }
/*     */     };
/*     */   
/* 120 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 121 */       { StockingQueryOrder.ID, "sk.id" }
/*     */     };
/*     */   
/*     */   private static final String ITEM_SQL_COUNT = "select count(*) from StockingDetial skd";
/*     */   
/*     */   private static final String ITEM_SQL = "from StockingDetial skd";
/*     */ 
/*     */   
/*     */   public int getStockingListCount(Map conditions) {
/* 130 */     return getListCount(conditions, "select count(*) from Stocking sk", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getStockingList(Map conditions, int pageNo, int pageSize, StockingQueryOrder order, boolean descend) {
/* 140 */     return getList(conditions, pageNo, pageSize, order, descend, "from Stocking sk", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledStockingList() {
/* 149 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 150 */     conds.put(StockingQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 151 */     return getStockingList(conds, 0, -1, StockingQueryOrder.ID, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteStocking(Stocking ct) {
/* 156 */     getHibernateTemplate().delete(ct);
/*     */   }
/*     */   
/*     */   public String getMaxStockingIdBeginWith(String prefix) {
/* 160 */     String sql = "select max(sk.code) from Stocking sk";
/* 161 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 162 */     conds.put(StockingQueryCondition.ID_BEGINWITH, prefix);
/*     */     
/* 164 */     List<String> l = getList(conds, 0, -1, null, false, sql, QUERY_CONDITIONS, QUERY_ORDERS);
/* 165 */     if (l.isEmpty()) {
/* 166 */       return null;
/*     */     }
/* 168 */     return l.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   private static final Object[][] ITEM_QUERY_CONDITIONS = new Object[][] {
/* 178 */       { StockingDetialQueryCondition.ID, "skd.id = ?"
/* 179 */       }, { StockingDetialQueryCondition.STOCKING_ID_EQ, "skd.stocking.id = ?"
/* 180 */       }, { StockingDetialQueryCondition.LOCATION_ID_EQ, "skd.location.id = ?"
/* 181 */       }, { StockingDetialQueryCondition.PART_IS_NOT_NULL, "skd.part.id is not null " }
/*     */     };
/*     */   
/* 184 */   private static final Object[][] ITEM_QUERY_ORDERS = new Object[][] {
/* 185 */       { StockingDetialQueryOrder.ID, "skd.id" } };
/*     */   private static final String SQL_COUNT_Record = "select count(*) from StockingRecord sr";
/*     */   private static final String SQL_Record = "from StockingRecord sr";
/*     */   
/*     */   public List getStockingDetialList(Map conditions, int pageNo, int pageSize, StockingDetialQueryOrder order, boolean descend) {
/* 190 */     return getList(conditions, pageNo, pageSize, order, descend, "from StockingDetial skd", ITEM_QUERY_CONDITIONS, ITEM_QUERY_ORDERS);
/*     */   }
/*     */   
/*     */   public int getStockingDetialListCount(Map conditions) {
/* 194 */     return getListCount(conditions, "select count(*) from StockingDetial skd", ITEM_QUERY_CONDITIONS);
/*     */   }
/*     */   
/*     */   public StockingDetial updateStockingDetial(StockingDetial stockingDetial) {
/* 198 */     Integer id = stockingDetial.getId();
/* 199 */     if (id == null) {
/* 200 */       throw new RuntimeException("cannot save a StockingDetial with null id");
/*     */     }
/* 202 */     StockingDetial oldStocking = getStockingDetial(id);
/* 203 */     if (oldStocking != null) {
/*     */       try {
/* 205 */         PropertyUtils.copyProperties(oldStocking, stockingDetial);
/* 206 */       } catch (Exception e) {
/* 207 */         throw new RuntimeException("copy error!" + e);
/*     */       } 
/* 209 */       getHibernateTemplate().update(oldStocking);
/* 210 */       return oldStocking;
/*     */     } 
/* 212 */     throw new RuntimeException("StockingDetial not found");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public StockingDetial getStockingDetial(Integer id) {
/* 218 */     return (StockingDetial)getHibernateTemplate().get(
/* 219 */         StockingDetial.class, id);
/*     */   }
/*     */   
/*     */   public List<StockingDetial> getStockingByInventory(Integer id) {
/* 223 */     String sql = "from StockingDetial ws where ws.inventory.id = ?";
/* 224 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public StockingSweepRecord insertStockingSweepRecord(StockingSweepRecord sweepRecord) {
/* 230 */     getHibernateTemplate().save(sweepRecord);
/* 231 */     return sweepRecord;
/*     */   }
/*     */   
/*     */   public int getStockingSweepRecordByLotSer(String lotSer, String locationId, String stockCode) {
/* 235 */     String sql = "from StockingSweepRecord ws where ws.lotSet = ? and ws.location = ? and ws.StockingDetial.stocking.code=?";
/* 236 */     Object[] param = { lotSer, locationId, stockCode };
/* 237 */     List list = getHibernateTemplate().find(sql, param);
/* 238 */     if (list == null || list.isEmpty()) {
/* 239 */       return 0;
/*     */     }
/* 241 */     return 1;
/*     */   }
/*     */   public StockingSweepRecord getStockingSweepRecordByBoxLotSer(String lotSer, String locationId, String stockCode) {
/* 244 */     String sql = "from StockingSweepRecord ws where ws.box.lot = ? and ws.location.code = ? and ws.stocking_detail_id.stocking.code=?";
/* 245 */     Object[] param = { lotSer, locationId, stockCode };
/* 246 */     List<StockingSweepRecord> list = getHibernateTemplate().find(sql, param);
/* 247 */     if (list == null || list.isEmpty()) {
/* 248 */       return null;
/*     */     }
/* 250 */     return list.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<StockingDetial> getStockingByStocking(String code) {
/* 255 */     String sql = "from StockingDetial item where item.stocking.code = ?";
/* 256 */     return getHibernateTemplate().find(sql, code, (Type)Hibernate.STRING);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<StockingDetial> getStockingByMain(Integer id) {
/* 261 */     String sql = "from StockingDetial item where item.stocking.id = ? order by item.location.id";
/* 262 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/*     */   }
/*     */ 
/*     */   
/*     */   public StockingDetial getStockingDetialByLocation(String code, String stockCode) {
/* 267 */     String sql = "from StockingDetial item where item.location.code = ? and item.stocking.code=? ";
/* 268 */     Object[] param = { code, stockCode };
/* 269 */     List<StockingDetial> list = getHibernateTemplate().find(sql, param);
/* 270 */     if (list == null || list.isEmpty()) {
/* 271 */       return null;
/*     */     }
/* 273 */     return list.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<StockingDetial> getStockingDetialByLocationList(String code, String stockCode) {
/* 278 */     String sql = "from StockingDetial item where item.location.code = ? and item.stocking.code=?  ";
/* 279 */     Object[] param = { code, stockCode };
/* 280 */     List<StockingDetial> list = getHibernateTemplate().find(sql, param);
/* 281 */     if (list == null || list.isEmpty()) {
/* 282 */       return null;
/*     */     }
/* 284 */     return list;
/*     */   }
/*     */   
/*     */   public StockingSweepRecord getStockingSweepRecordByBox(Integer id) {
/* 288 */     String sql = "from StockingSweepRecord rec where rec.box.id = ?";
/* 289 */     List<StockingSweepRecord> list = getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/* 290 */     if (list == null || list.isEmpty()) {
/* 291 */       return null;
/*     */     }
/* 293 */     return list.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public StockingSweepRecord getStockingSweepRecordByLotser(String lotser, Integer id) {
/* 298 */     String sql = "from StockingSweepRecord rec where rec.box.lot.id = ? and rec.stocking_detail_id.stocking.id = ? ";
/* 299 */     Object[] parmert = { lotser, id };
/* 300 */     List<StockingSweepRecord> list = getHibernateTemplate().find(sql, parmert);
/* 301 */     if (list == null || list.isEmpty()) {
/* 302 */       return null;
/*     */     }
/*     */     
/* 305 */     return list.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<StockingSweepRecord> getStockingSweepRecordByItem(Integer id) {
/* 310 */     String sql = "from StockingSweepRecord wsr where wsr.StockingDetial.id = ?";
/* 311 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public List<Object[]> getPoIpiBoxByStockingReturnObject(Integer id) {
/* 315 */     String sql = "select box.wmsPart, box.location.code, box.count, box.lotSer.id  from PoIpiBox box where box.location.id in (select item.location.id from StockingDetial item where item.stocking.id = ?) and box.isPutIntStorage = 0 and box.isPickingOutboundFinish = 1 and box.isEnabled = 0 and box.lotSer.id in(select wsr.lotSet from StockingSweepRecord wsr  where wsr.StockingDetial.id in (select item.id from StockingDetial item where item.stocking.id = ?))";
/*     */ 
/*     */ 
/*     */     
/* 319 */     Object[] parmart = { id, id };
/* 320 */     return getHibernateTemplate().find(sql, parmart);
/*     */   }
/*     */   
/*     */   public List<StockingSweepRecord> getStockingByPoIpiBoxReturnObject(Integer id) {
/* 324 */     String sql = "from StockingSweepRecord wsr where wsr.StockingDetial.id in (select item.id from StockingDetial item where item.stocking.id = ?) and wsr.lotSet not in (select box.lotSer.id from PoIpiBox box where box.location.id in (select item.location.id from StockingDetial item where item.stocking.id = ?) and box.isPutIntStorage = 0 and box.isPickingOutboundFinish = 1 and box.isEnabled = 0)";
/*     */ 
/*     */     
/* 327 */     Object[] parmart = { id, id };
/* 328 */     return getHibernateTemplate().find(sql, parmart);
/*     */   }
/*     */   
/*     */   public List<Object[]> getStockingByInventoryLosses(Integer id) {
/* 332 */     String sql = "select box.wmsPart, box.location.code, box.count, box.lotSer.id  from PoIpiBox box where box.location.id in (select item.location.id from StockingDetial item where item.stocking.id = ?) and box.isPutIntStorage = 0 and box.isPickingOutboundFinish = 1 and box.isEnabled = 0 and box.lotSer.id not in(select wsr.lotSet from StockingSweepRecord wsr  where wsr.StockingDetial.id in (select item.id from StockingDetial item where item.stocking.id = ?))";
/*     */ 
/*     */ 
/*     */     
/* 336 */     Object[] parmart = { id, id };
/* 337 */     return getHibernateTemplate().find(sql, parmart);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<StockingSweepRecord> getStockingSweepRecordByMain(Integer id) {
/* 342 */     String sql = "from StockingSweepRecord wsr where wsr.StockingDetial.id in (select item.id from StockingDetial item where item.stocking.id = ?)";
/* 343 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public StockingRecord getStockingRecord(Integer id) {
/* 347 */     if (id == null) {
/* 348 */       if (this.log.isDebugEnabled())
/* 349 */         this.log.debug("try to get StockingRecord with null id"); 
/* 350 */       return null;
/*     */     } 
/* 352 */     return (StockingRecord)getHibernateTemplate().get(StockingRecord.class, id);
/*     */   }
/*     */   
/*     */   public StockingRecord insertStockingRecord(StockingRecord record) {
/* 356 */     getHibernateTemplate().save(record);
/* 357 */     return record;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<StockingRecord> getStockingDifferenceByStockingRecord(Integer id) {
/* 362 */     String sql = "from StockingRecord record where record.stocking_id.id = ?  and record.differences<>0";
/* 363 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/*     */   }
/*     */ 
/*     */   
/*     */   public StockingDetial getStockingDetialByLocationByPart(String code, String part, String stockCode) {
/* 368 */     String sql = "from StockingDetial item where item.location.code = ? and item.part.id=? and item.stocking.code=? and item.stocking.status=0  ";
/* 369 */     Object[] param = { code, part, stockCode };
/* 370 */     List<StockingDetial> list = getHibernateTemplate().find(sql, param);
/* 371 */     if (list == null || list.isEmpty()) {
/* 372 */       return null;
/*     */     }
/* 374 */     return list.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<StockingDetial> getWmsStockingByStocking(String id) {
/* 379 */     String sql = "from StockingDetial item where item.stocking.code = ?";
/* 380 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.STRING);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 388 */   private static final Object[][] QUERY_CONDITIONS_Record = new Object[][] {
/* 389 */       { StockingQueryCondition.ID_EQ, "sr.id = ?"
/* 390 */       }, { StockingQueryCondition.STOCKING_ID_EQ, "sr.stocking_id.id = ?"
/* 391 */       }, { StockingQueryCondition.BOX_LOT_ID_EQ, "sr.box.id = ?"
/* 392 */       }, { StockingQueryCondition.TYPE_IS_NOT_NULL, "sr.type is not null"
/* 393 */       }, { StockingQueryCondition.STOCKINGRECORD_TYPE_EQ, "sr.type = ?"
/* 394 */       }, { StockingQueryCondition.STOCKINGRECORD_STATUS_NOT_EQ, "sr.status is null or sr.status <> ?"
/* 395 */       }, { StockingQueryCondition.STOCKINGRECORD_TYPE_EQ, "sr.type = ?"
/* 396 */       }, { StockingQueryCondition.STOCKINGRECORD_DIFFERENCES_GT, "sr.differences > ?"
/* 397 */       }, { StockingQueryCondition.STOCKINGRECORD_DIFFERENCES_LT, "sr.differences < ?" }
/*     */     };
/*     */   
/* 400 */   private static final Object[][] QUERY_ORDERS_Record = new Object[][] {
/* 401 */       { StockingQueryOrder.BOX_LOT_ID, "sr.box.lot.id"
/* 402 */       }, { StockingQueryOrder.ID, "sr.id"
/* 403 */       }, { StockingQueryOrder.LOCATION_ID, "sr.box.location.id" } };
/*     */   private static final String SQL_SCANRECORD_COUNT = "select count(*) from StockingScanRecord ssr";
/*     */   private static final String SQL_SCANRECORD = "from StockingScanRecord ssr";
/*     */   
/*     */   public int getStockingRecordListCount(Map conditions) {
/* 408 */     return getListCount(conditions, "select count(*) from StockingRecord sr", QUERY_CONDITIONS_Record);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getStockingRecordList(Map conditions, int pageNo, int pageSize, StockingQueryOrder order, boolean descend) {
/* 414 */     return getList(conditions, pageNo, pageSize, order, descend, "from StockingRecord sr", QUERY_CONDITIONS_Record, QUERY_ORDERS_Record);
/*     */   }
/*     */ 
/*     */   
/*     */   public StockingRecord getStockingRecordByPlan(String lot, String location, String order) {
/* 419 */     String sql = "from StockingRecord record where record.box.lot.id = ? and record.location.code =? and record.stocking_id.code=? and record.stocking_id.status=0  ";
/* 420 */     Object[] param = { lot, location, order };
/* 421 */     List<StockingRecord> list = getHibernateTemplate().find(sql, param);
/* 422 */     if (list == null || list.isEmpty()) {
/* 423 */       return null;
/*     */     }
/* 425 */     return list.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public Stocking getStockingByCode(String code) {
/* 430 */     String sql = "from Stocking stocking where stocking.code = ? ";
/* 431 */     List<Stocking> list = getHibernateTemplate().find(sql, code, (Type)Hibernate.STRING);
/* 432 */     if (list == null || list.isEmpty()) {
/* 433 */       return null;
/*     */     }
/* 435 */     return list.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public StockingSweepRecord getStockingSweepRecordBylotSer(String lot, String location, String order) {
/* 441 */     String sql = "from StockingSweepRecord wsr where wsr.box.lot.id=? and wsr.stocking_detail_id.location.code=? and wsr.stocking_detail_id.stocking.code=? ";
/* 442 */     Object[] param = { lot, location, order };
/* 443 */     List<StockingSweepRecord> list = getHibernateTemplate().find(sql, param);
/* 444 */     if (list == null || list.isEmpty()) {
/* 445 */       return null;
/*     */     }
/* 447 */     return list.get(0);
/*     */   }
/*     */   
/*     */   public StockingSweepRecord getStockingSweepRecordBylotSer(String lot, String order) {
/* 451 */     String sql = "from StockingSweepRecord wsr where wsr.box.lot.id=? and wsr.stocking_detail_id.stocking.code=? ";
/* 452 */     Object[] param = { lot, order };
/* 453 */     List<StockingSweepRecord> list = getHibernateTemplate().find(sql, param);
/* 454 */     if (list == null || list.isEmpty()) {
/* 455 */       return null;
/*     */     }
/* 457 */     return list.get(0);
/*     */   }
/*     */   
/*     */   public int updateStockingIsSync(final Integer stockingId) {
/* 461 */     return ((Integer)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 464 */             int sum = 0;
/*     */             try {
/* 466 */               Transaction tx = session.beginTransaction();
/* 467 */               Connection con = session.connection();
/* 468 */               PreparedStatement stmt = con.prepareStatement("update stocking_record set is_sync=1where stocking_id= '" + stockingId + "'");
/* 469 */               sum = stmt.executeUpdate();
/* 470 */               tx.commit();
/* 471 */             } catch (Exception e) {
/* 472 */               e.getStackTrace();
/* 473 */               e.getMessage();
/*     */             } finally {
/* 475 */               if (session != null && session.isOpen()) {
/* 476 */                 session.close();
/*     */               }
/*     */             } 
/* 479 */             return Integer.valueOf(sum);
/*     */           }
/*     */         })).intValue();
/*     */   }
/*     */   
/*     */   public void deleteStockingDetial(StockingDetial city) {
/* 485 */     getHibernateTemplate().delete(city);
/*     */   }
/*     */ 
/*     */   
/*     */   public int deleteStockingSweepRecordByAll(final Integer stockingId) {
/* 490 */     return ((Integer)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 493 */             int sum = 0;
/*     */             try {
/* 495 */               Transaction tx = session.beginTransaction();
/* 496 */               Connection con = session.connection();
/* 497 */               PreparedStatement stmt = con.prepareStatement("delete from stocking_sweepRecord where stocking_detail_id in (select id from stocking_detail where stocking = '" + stockingId + "')");
/* 498 */               sum = stmt.executeUpdate();
/* 499 */               tx.commit();
/* 500 */             } catch (Exception e) {
/* 501 */               e.getStackTrace();
/* 502 */               e.getMessage();
/*     */             } finally {
/* 504 */               if (session != null && session.isOpen()) {
/* 505 */                 session.close();
/*     */               }
/*     */             } 
/* 508 */             return Integer.valueOf(sum);
/*     */           }
/*     */         })).intValue();
/*     */   }
/*     */   
/*     */   public int deleteStockingDetialByAll(final Integer stockingId) {
/* 514 */     return ((Integer)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 517 */             int sum = 0;
/*     */             try {
/* 519 */               Transaction tx = session.beginTransaction();
/* 520 */               Connection con = session.connection();
/* 521 */               PreparedStatement stmt = con.prepareStatement("delete from stocking_detail where stocking ='" + stockingId + "'");
/* 522 */               sum = stmt.executeUpdate();
/* 523 */               tx.commit();
/* 524 */             } catch (Exception e) {
/* 525 */               e.getStackTrace();
/* 526 */               e.getMessage();
/*     */             } finally {
/* 528 */               if (session != null && session.isOpen()) {
/* 529 */                 session.close();
/*     */               }
/*     */             } 
/* 532 */             return Integer.valueOf(sum);
/*     */           }
/*     */         })).intValue();
/*     */   }
/*     */   
/*     */   public int deleteLocationByAll(final Integer stockingId) {
/* 538 */     return ((Integer)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 541 */             int sum = 0;
/*     */             try {
/* 543 */               Transaction tx = session.beginTransaction();
/* 544 */               Connection con = session.connection();
/*     */               
/* 546 */               PreparedStatement stmt = con.prepareStatement("update basic_storage_location set freeae_status=1 where id in (select location from stocking_detail where stocking = '" + stockingId + "')");
/* 547 */               sum = stmt.executeUpdate();
/* 548 */               tx.commit();
/* 549 */             } catch (Exception e) {
/* 550 */               e.getStackTrace();
/* 551 */               e.getMessage();
/*     */             } finally {
/* 553 */               if (session != null && session.isOpen()) {
/* 554 */                 session.close();
/*     */               }
/*     */             } 
/* 557 */             return Integer.valueOf(sum);
/*     */           }
/*     */         })).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal getInventoryDetialByPlanSum(Integer id) {
/* 564 */     String sql = "select sum(item.plan_sum_qty) from StockingDetial item where item.stocking.id = '" + id + "'";
/* 565 */     List<BigDecimal> listsCount = getHibernateTemplate().find(sql);
/* 566 */     return listsCount.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public StockingRecord updateStockingRecord(StockingRecord record) {
/* 571 */     Integer id = record.getId();
/* 572 */     if (id == null) {
/* 573 */       throw new RuntimeException("cannot save a ct with null id");
/*     */     }
/* 575 */     StockingRecord oldRecord = getStockingRecord(id);
/* 576 */     if (oldRecord != null) {
/*     */       try {
/* 578 */         PropertyUtils.copyProperties(oldRecord, record);
/* 579 */       } catch (Exception e) {
/* 580 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/* 582 */       getHibernateTemplate().update(oldRecord);
/* 583 */       return oldRecord;
/*     */     } 
/* 585 */     throw new RuntimeException("record not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer insertWmsStockingItemAllList(final Stocking wmsStocking) {
/* 590 */     return (Integer)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 593 */             int sum = 0;
/*     */             try {
/* 595 */               Transaction tx = session.beginTransaction();
/* 596 */               Connection con = session.connection();
/* 597 */               String sql = "INSERT INTO stocking_detail(stocking,location,part,plan_sum_qty,actual_sum_qty,plan_num,actual_num)SELECT s='" + 
/* 598 */                 wmsStocking.getId() + "',location,part,sumNumber ,0,count,0 FROM location_part_no a,basic_storage_location b where a.location=b.id and b.freeae_status=1 and b.is_enabled=0";
/*     */               
/* 600 */               PreparedStatement stmt = con.prepareStatement(sql);
/*     */               
/* 602 */               sum = stmt.executeUpdate();
/* 603 */               tx.commit();
/* 604 */             } catch (Exception e) {
/* 605 */               e.getStackTrace();
/* 606 */               e.getMessage();
/*     */             } finally {
/* 608 */               if (session != null && session.isOpen()) {
/* 609 */                 session.close();
/*     */               }
/*     */             } 
/* 612 */             return Integer.valueOf(sum);
/*     */           }
/*     */         });
/*     */   }
/*     */   public Integer insertWmsStockingItemList(final Stocking wmsStocking, final String[] locatins) {
/* 617 */     return (Integer)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 620 */             int sum = 0;
/*     */             try {
/* 622 */               Transaction tx = session.beginTransaction();
/* 623 */               Connection con = session.connection();
/* 624 */               String sql = "INSERT INTO stocking_detail(stocking,location,part,plan_sum_qty,actual_sum_qty,plan_num,actual_num)SELECT s='" + 
/* 625 */                 wmsStocking.getId() + "',location,part,sumNumber ,0,count,0 FROM location_part_no a,basic_storage_location b where a.location=b.id and b.freeae_status=1 and b.is_enabled=0 and a.location in ("; byte b; int i; String[] arrayOfString;
/* 626 */               for (i = (arrayOfString = locatins).length, b = 0; b < i; ) { String s = arrayOfString[b];
/* 627 */                 sql = String.valueOf(sql) + "'";
/* 628 */                 sql = String.valueOf(sql) + s;
/* 629 */                 sql = String.valueOf(sql) + "',"; b++; }
/*     */               
/* 631 */               sql = sql.substring(0, sql.length() - 1);
/* 632 */               sql = String.valueOf(sql) + ")";
/*     */               
/* 634 */               PreparedStatement stmt = con.prepareStatement(sql);
/*     */               
/* 636 */               sum = stmt.executeUpdate();
/* 637 */               tx.commit();
/* 638 */             } catch (Exception e) {
/* 639 */               e.getStackTrace();
/* 640 */               e.getMessage();
/*     */             } finally {
/* 642 */               if (session != null && session.isOpen()) {
/* 643 */                 session.close();
/*     */               }
/*     */             } 
/* 646 */             return Integer.valueOf(sum);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public Integer insertWmsStockingItemNotBylocationList(final Stocking wmsStocking, final List<StorageLocation> locationList) {
/* 652 */     return (Integer)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 655 */             int sum = 0;
/*     */             try {
/* 657 */               Transaction tx = session.beginTransaction();
/* 658 */               Connection con = session.connection();
/* 659 */               StringBuffer sqlBuffer = new StringBuffer();
/*     */               
/* 661 */               sqlBuffer.append("INSERT stocking_detail (stocking,location,plan_sum_qty,actual_sum_qty,plan_num,actual_num) SELECT stocking=" + wmsStocking.getId() + ",id,plan_sum_qty=0,actual_sum_qty=0,plan_num=0,actual_num=0 from  basic_storage_location WHERE  id in(select a.id from basic_storage_location a  where a.id in (");
/* 662 */               for (int i = 0; i < locationList.size(); i++) {
/* 663 */                 if (i == locationList.size() - 1) {
/* 664 */                   sqlBuffer.append(((StorageLocation)locationList.get(i)).getId());
/*     */                 } else {
/* 666 */                   sqlBuffer.append(((StorageLocation)locationList.get(i)).getId());
/* 667 */                   sqlBuffer.append(",");
/*     */                 } 
/*     */               } 
/* 670 */               sqlBuffer.append(") ) and id not in (select b.location from stocking_detail b where stocking=" + wmsStocking.getId() + " )");
/*     */               
/* 672 */               PreparedStatement stmt = con.prepareStatement(sqlBuffer.toString());
/*     */               
/* 674 */               sum = stmt.executeUpdate();
/* 675 */               tx.commit();
/* 676 */             } catch (Exception e) {
/* 677 */               e.getStackTrace();
/* 678 */               e.getMessage();
/*     */             } finally {
/* 680 */               if (session != null && session.isOpen()) {
/* 681 */                 session.close();
/*     */               }
/*     */             } 
/* 684 */             return Integer.valueOf(sum);
/*     */           }
/*     */         });
/*     */   }
/*     */   public Integer updateLocationFreeaeByStocking(final Integer wmsStockingId) {
/* 689 */     return (Integer)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 692 */             int sum = 0;
/*     */             try {
/* 694 */               Transaction tx = session.beginTransaction();
/* 695 */               Connection con = session.connection();
/*     */               
/* 697 */               PreparedStatement stmt = con.prepareStatement("update basic_storage_location set freeae_status=0 where id in (select location from stocking_detail where stocking = '" + wmsStockingId + "')");
/* 698 */               sum = stmt.executeUpdate();
/* 699 */               tx.commit();
/* 700 */             } catch (Exception e) {
/* 701 */               e.getStackTrace();
/* 702 */               e.getMessage();
/*     */             } finally {
/* 704 */               if (session != null && session.isOpen()) {
/* 705 */                 session.close();
/*     */               }
/*     */             } 
/* 708 */             return Integer.valueOf(sum);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public List<StockingScanRecord> getStockingScanRecordByList(String stockingCode) {
/* 715 */     String sql = "from StockingScanRecord ssr where ssr.stocking = ?";
/* 716 */     return getHibernateTemplate().find(sql, stockingCode, (Type)Hibernate.STRING);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 723 */   private static final Object[][] QUERY_SCANRECORD_CONDITIONS = new Object[][] {
/* 724 */       { StockingScanRecordQueryCondition.ID_EQ, "ssr.id = ?"
/* 725 */       }, { StockingScanRecordQueryCondition.STOCKING_EQ, "ssr.stocking = ?" }
/*     */     };
/*     */   
/* 728 */   private static final Object[][] QUERY_SCANRECORD_ORDERS = new Object[][] {
/* 729 */       { StockingScanRecordQueryOrder.ID, "ssr.id" }
/*     */     };
/*     */   
/*     */   public int getStockingScanRecordListCount(Map conditions) {
/* 733 */     return getListCount(conditions, "select count(*) from StockingScanRecord ssr", QUERY_SCANRECORD_CONDITIONS);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getStockingScanRecordList(Map conditions, int pageNo, int pageSize, StockingScanRecordQueryOrder order, boolean descend) {
/* 738 */     return getList(conditions, pageNo, pageSize, order, descend, "from StockingScanRecord ssr", QUERY_SCANRECORD_CONDITIONS, QUERY_SCANRECORD_ORDERS);
/*     */   }
/*     */   
/*     */   public Integer updateStockingRecordStatus(final StockingRecord record, final Integer status) {
/* 742 */     return (Integer)getHibernateTemplate().execute(new HibernateCallback() {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 744 */             int sum = 0;
/*     */             try {
/* 746 */               Transaction tx = session.beginTransaction();
/* 747 */               Connection con = session.connection();
/*     */               
/* 749 */               PreparedStatement stmt = con.prepareStatement("update stocking_record set handle_status='" + status + "'" + " where stocking_id='" + record.getStocking_id().getId() + "' and  box='" + record.getBox().getId() + "'");
/* 750 */               sum = stmt.executeUpdate();
/* 751 */               tx.commit();
/* 752 */             } catch (Exception e) {
/* 753 */               e.getStackTrace();
/* 754 */               e.getMessage();
/*     */             } finally {
/* 756 */               if (session != null && session.isOpen()) {
/* 757 */                 session.close();
/*     */               }
/*     */             } 
/* 760 */             return Integer.valueOf(sum);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal getInventoryDetialByActualSum(Integer id) {
/* 767 */     String sql = "select sum(item.actual_sum_qty) from StockingDetial item where item.stocking.id = '" + id + "'";
/* 768 */     List<BigDecimal> listsCount = getHibernateTemplate().find(sql);
/* 769 */     return listsCount.get(0);
/*     */   }
/*     */   
/*     */   public BigDecimal getInventoryDetialByDifferencesSum(Integer id) {
/* 773 */     String sql = "select sum(sr.differences) from StockingRecord sr where sr.stocking_id.id = '" + id + "'";
/* 774 */     List<BigDecimal> listsCount = getHibernateTemplate().find(sql);
/* 775 */     return listsCount.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer insertWmsStockingItemNotBylocationList(final Stocking wmsStocking, final String[] locationList) {
/* 781 */     return (Integer)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 784 */             int sum = 0;
/*     */             try {
/* 786 */               Transaction tx = session.beginTransaction();
/* 787 */               Connection con = session.connection();
/* 788 */               StringBuffer sqlBuffer = new StringBuffer();
/* 789 */               sqlBuffer.append("INSERT stocking_detail (stocking,location,plan_sum_qty,actual_sum_qty,plan_num,actual_num) SELECT stocking=" + wmsStocking.getId() + ",id,plan_sum_qty=0,actual_sum_qty=0,plan_num=0,actual_num=0 from  basic_storage_location WHERE  id in(select a.id from basic_storage_location a  where a.id in (");
/* 790 */               for (int i = 0; i < locationList.length; i++) {
/* 791 */                 if (i == locationList.length - 1) {
/* 792 */                   sqlBuffer.append(locationList[i]);
/*     */                 } else {
/* 794 */                   sqlBuffer.append(locationList[i]);
/* 795 */                   sqlBuffer.append(",");
/*     */                 } 
/*     */               } 
/* 798 */               sqlBuffer.append(") ) and id not in (select b.location from stocking_detail b where stocking=" + wmsStocking.getId() + " )");
/*     */               
/* 800 */               PreparedStatement stmt = con.prepareStatement(sqlBuffer.toString());
/*     */               
/* 802 */               sum = stmt.executeUpdate();
/* 803 */               tx.commit();
/* 804 */             } catch (Exception e) {
/* 805 */               e.getStackTrace();
/* 806 */               e.getMessage();
/*     */             } finally {
/* 808 */               if (session != null && session.isOpen()) {
/* 809 */                 session.close();
/*     */               }
/*     */             } 
/* 812 */             return Integer.valueOf(sum);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer insertWmsStockingItemListByPart(final Stocking wmsStocking, final String[] parts) {
/* 820 */     return (Integer)getHibernateTemplate().execute(new HibernateCallback() {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 822 */             int sum = 0;
/*     */             try {
/* 824 */               Transaction tx = session.beginTransaction();
/* 825 */               Connection con = session.connection();
/* 826 */               String sql = "INSERT INTO stocking_detail(stocking,part,plan_sum_qty,actual_sum_qty,plan_num,actual_num)SELECT s='" + 
/* 827 */                 wmsStocking.getId() + "',part,sumNumber ,0,count,0 FROM sk_part_sumNumber where part in ("; byte b; int i; String[] arrayOfString;
/* 828 */               for (i = (arrayOfString = parts).length, b = 0; b < i; ) { String s = arrayOfString[b];
/* 829 */                 sql = String.valueOf(sql) + "'";
/* 830 */                 sql = String.valueOf(sql) + s;
/* 831 */                 sql = String.valueOf(sql) + "',"; b++; }
/*     */               
/* 833 */               sql = sql.substring(0, sql.length() - 1);
/* 834 */               sql = String.valueOf(sql) + ")";
/*     */               
/* 836 */               PreparedStatement stmt = con.prepareStatement(sql);
/*     */               
/* 838 */               sum = stmt.executeUpdate();
/* 839 */               tx.commit();
/* 840 */             } catch (Exception e) {
/* 841 */               e.getStackTrace();
/* 842 */               e.getMessage();
/*     */             } finally {
/* 844 */               if (session != null && session.isOpen()) {
/* 845 */                 session.close();
/*     */               }
/*     */             } 
/* 848 */             return Integer.valueOf(sum);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public StockingScanRecord getStockingScanRecordByLotser(String lotser, Integer id) {
/* 855 */     Stocking stocking = getStocking(id);
/* 856 */     if (stocking != null) {
/* 857 */       String sql = "from StockingScanRecord rec where rec.lotSer = ? and rec.stocking = ? ";
/* 858 */       Object[] parmert = { lotser, stocking.getCode() };
/* 859 */       List<StockingScanRecord> list = getHibernateTemplate().find(sql, parmert);
/* 860 */       if (list == null || list.isEmpty()) {
/* 861 */         return null;
/*     */       }
/* 863 */       return list.get(0);
/*     */     } 
/* 865 */     return null;
/*     */   }
/*     */   
/*     */   public StockingDetial getStockingDetialByPart(String part, String stockCode) {
/* 869 */     String sql = "from StockingDetial item where item.part.id = ? and item.stocking.code=? ";
/* 870 */     Object[] param = { part, stockCode };
/* 871 */     List<StockingDetial> list = getHibernateTemplate().find(sql, param);
/* 872 */     if (list == null || list.isEmpty()) {
/* 873 */       return null;
/*     */     }
/* 875 */     return list.get(0);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/comprehensive/hibernate/StockingDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
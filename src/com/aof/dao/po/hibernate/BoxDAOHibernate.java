/*     */ package com.aof.dao.po.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.dao.po.BoxDAO;
/*     */ import com.aof.model.basic.BadReasons;
/*     */ import com.aof.model.basicDataView.query.PoPartSumNumberQueryOrder;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.po.PurchaseOrderRqcUnqualified;
/*     */ import com.aof.model.po.WmsLot;
/*     */ import com.aof.model.po.query.BoxQueryCondition;
/*     */ import com.aof.model.po.query.BoxQueryOrder;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.RoundingMode;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BoxDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements BoxDAO
/*     */ {
/*  41 */   private Log log = LogFactory.getLog(BoxDAOHibernate.class); private static final String SQL_COUNT_ITEM = "select pb.part.id from Box pb group by pb.part.id"; private static final String SQL_ITEM = "select pb.part.id, sum(pb.number) from Box pb group by pb.part.id";
/*     */   
/*     */   public Box getBox(Integer id) {
/*  44 */     if (id == null) {
/*  45 */       if (this.log.isDebugEnabled())
/*  46 */         this.log.debug("try to get Box with null id"); 
/*  47 */       return null;
/*     */     } 
/*  49 */     return (Box)getHibernateTemplate().get(Box.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from Box pb";
/*     */ 
/*     */   
/*     */   private static final String SQL = "from Box pb";
/*     */   
/*     */   private static final String SQL_SUMCOUNT = "select sum(pb.count) from Box pb";
/*     */   
/*  61 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] { 
/*  62 */       { BoxQueryCondition.ID_EQ, "pb.id = ?"
/*  63 */       }, { BoxQueryCondition.LOTSER_EQ, "pb.lot.id like ?", new LikeConvert()
/*  64 */       }, { BoxQueryCondition.TYPE_EQ, "pb.type = ?"
/*  65 */       }, { BoxQueryCondition.ISENABLED_EQ, "pb.enabled <> ?"
/*  66 */       }, { BoxQueryCondition.SINGLE_EQ, "pb.single = ?"
/*  67 */       }, { BoxQueryCondition.STATUS_EQ, "pb.status = ?"
/*  68 */       }, { BoxQueryCondition.STA_GT, "pb.status > ?"
/*  69 */       }, { BoxQueryCondition.STATUS_NOT_EQ, "pb.status <> ?"
/*  70 */       }, { BoxQueryCondition.STATUS_RQC_EQ, "pb.status_rqc = ?"
/*  71 */       }, { BoxQueryCondition.LOCATION_EQ, "pb.location.basic_storeroom_id.type = ?" }, 
/*  72 */       { BoxQueryCondition.FREEZE_EQ, "pb.status_freeze = ?"
/*  73 */       }, { BoxQueryCondition.PART_TYPE_EQ, "pb.part.type = ?"
/*  74 */       }, { BoxQueryCondition.STATUS_RQC_NOT_EQ, "pb.status_rqc <> ?"
/*  75 */       }, { BoxQueryCondition.ISENABLED_NOT_EQ, "pb.enabled = ?"
/*  76 */       }, { BoxQueryCondition.PART_ID_EQ, "pb.part.id = ?"
/*  77 */       }, { BoxQueryCondition.ISNOTZERO, "pb.number <> 0"
/*  78 */       }, { BoxQueryCondition.LOCATION_NOTNULL, "pb.location is not null"
/*  79 */       }, { BoxQueryCondition.LOCATION_STORE_ROOM_TYPE_EQ, "pb.location.basic_storeroom_id.type = ?"
/*  80 */       }, { BoxQueryCondition.LOCATION_NOT_EQ, "pb.location.code <> ?"
/*  81 */       }, { BoxQueryCondition.NUMBER_GT, "pb.number > ?" }, 
/*  82 */       { BoxQueryCondition.LOCATION_ID_EQ, "pb.location.id = ?"
/*  83 */       }, { BoxQueryCondition.LOCATION_TYPE_OR_RQC_EQ, "pb.location.basic_storeroom_id.type = ? or pb.status_rqc = ?"
/*  84 */       }, { BoxQueryCondition.ID_NOT_IN, "pb.id not in (select bacth.box.id from SalesPreshiporderBatch bacth where bacth.enabled=0) "
/*  85 */       }, { BoxQueryCondition.PART_ID_IN, "pb.part.id in (select item.part.id from WmsPlanToGoOutItem item where item.unplanned_outbound_id.id = ?) "
/*  86 */       }, { BoxQueryCondition.SYNC_STATUS_EQ, "pb.isSync= ? "
/*  87 */       }, { BoxQueryCondition.SHIPORDER_ITEM_ID_EQ, "pb.psoItem.id= ? " } };
/*     */ 
/*     */   
/*  90 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  91 */       { BoxQueryOrder.ID, "pb.id"
/*  92 */       }, { BoxQueryOrder.INDATE_LINE, "pb.in_date_line"
/*  93 */       }, { BoxQueryOrder.PART_ID, "pb.part.id" } }; private static final String SQL_COUNT_PoPartSumNumber = "select count(*) from PoPartSumNumber pps";
/*     */   private static final String SQL_PoPartSumNumber = "from PoPartSumNumber pps";
/*     */   
/*     */   public int getBoxListCount(Map conditions) {
/*  97 */     return getListCount(conditions, "select count(*) from Box pb", QUERY_CONDITIONS);
/*     */   }
/*     */   
/*     */   public List getBoxList(Map conditions, int pageNo, int pageSize, BoxQueryOrder order, boolean descend) {
/* 101 */     return getList(conditions, pageNo, pageSize, order, descend, "from Box pb", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   private static final Object[][] QUERY_CONDITIONS_PoPartSumNumber = new Object[0][];
/*     */ 
/*     */ 
/*     */   
/* 112 */   private static final Object[][] QUERY_ORDERS_PoPartSumNumber = new Object[][] {
/* 113 */       { PoPartSumNumberQueryOrder.PART_ID, "pps.part.id"
/* 114 */       }, { PoPartSumNumberQueryOrder.PART_YEARFROM1, "pps.part.yearFrom1" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBoxItemListCount(Map conditions) {
/* 121 */     return getListCount(conditions, "select count(*) from PoPartSumNumber pps", QUERY_CONDITIONS_PoPartSumNumber);
/*     */   }
/*     */   
/*     */   public List getBoxItemList(Map conditions, int pageNo, int pageSize, PoPartSumNumberQueryOrder order, boolean descend) {
/* 125 */     return getList(conditions, pageNo, pageSize, order, descend, "from PoPartSumNumber pps", QUERY_CONDITIONS_PoPartSumNumber, QUERY_ORDERS_PoPartSumNumber);
/*     */   }
/*     */   
/*     */   public Integer getBoxByPoipItem(final int id) {
/* 129 */     String sql = "select count(*) from Box pb where pb.poritem.poip_item_id.id = ? and pb.isPickingOutboundFinish = 1 ";
/*     */     
/* 131 */     return (Integer)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException
/*     */           {
/* 135 */             Query query = session.createQuery("select count(*) from Box pb where pb.poritem.poip_item_id.id = ? and pb.isPickingOutboundFinish = 1 ");
/* 136 */             query.setInteger(0, id);
/* 137 */             List<Integer> list = query.list();
/* 138 */             Integer num = list.get(0);
/*     */             
/* 140 */             try { if (num.intValue() == 0) {
/* 141 */                 return null;
/*     */               
/*     */               }
/*     */                }
/*     */             
/* 146 */             catch (Exception e)
/* 147 */             { e.getStackTrace(); }
/*     */             finally
/* 149 */             { if (session != null && session.isOpen())
/* 150 */                 session.close();  }  if (session != null && session.isOpen()) session.close();
/*     */ 
/*     */             
/* 153 */             return num;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public List<Box> getBoxByChildItem(int id) {
/* 159 */     String sql = "from Box pb where pb.childItem.id = ?";
/* 160 */     return getHibernateTemplate().find(sql, Integer.valueOf(id), (Type)Hibernate.INTEGER);
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer getPwoBoxByPurchaseOrderReceipts(final Integer id) {
/* 165 */     String sql = "select count(*) from Box box where box.poritem.id = ? and box.isReceipt = 1 and box.isEnabled=0";
/* 166 */     return (Integer)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException
/*     */           {
/* 170 */             Query query = session.createQuery("select count(*) from Box box where box.poritem.id = ? and box.isReceipt = 1 and box.isEnabled=0");
/* 171 */             query.setInteger(0, id.intValue());
/* 172 */             List<Integer> list = query.list();
/* 173 */             if (list == null || list.isEmpty()) {
/* 174 */               return null;
/*     */             }
/* 176 */             Integer num = list.get(0);
/* 177 */             if (num.intValue() == 0) {
/* 178 */               return null;
/*     */             }
/* 180 */             return num;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteBoxByWmsUWItem(Box box) {
/* 187 */     WmsLot lot = box.getLot();
/* 188 */     getHibernateTemplate().delete(box);
/* 189 */     getHibernateTemplate().delete(lot);
/*     */   }
/*     */   
/*     */   public List<Box> getBoxBylotSer(String lotSer) {
/* 193 */     String sql = "from Box box where box.lot.id = ?";
/* 194 */     return getHibernateTemplate().find(sql, lotSer, (Type)Hibernate.STRING);
/*     */   }
/*     */   
/*     */   public Box getBoxBylotSer2(String lotSer) {
/* 198 */     List<Box> list = null;
/*     */     try {
/* 200 */       String sql = "from Box box where box.lot.id = ?";
/* 201 */       list = getHibernateTemplate().find(sql, lotSer, 
/* 202 */           (Type)Hibernate.STRING);
/* 203 */     } catch (Exception e) {
/* 204 */       return null;
/*     */     } 
/*     */     
/* 207 */     if (list == null || list.isEmpty()) {
/* 208 */       return null;
/*     */     }
/* 210 */     return list.get(0);
/*     */   }
/*     */   
/*     */   public Box updateBox(Box box) {
/* 214 */     Integer id = box.getId();
/* 215 */     if (id == null) {
/* 216 */       throw new RuntimeException("cannot save a ct with null id");
/*     */     }
/* 218 */     Box oldProduceWorkOrderBox = getBox(id);
/* 219 */     if (oldProduceWorkOrderBox != null) {
/*     */       try {
/* 221 */         PropertyUtils.copyProperties(oldProduceWorkOrderBox, box);
/* 222 */       } catch (Exception e) {
/* 223 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/* 225 */       getHibernateTemplate().update(oldProduceWorkOrderBox);
/* 226 */       return oldProduceWorkOrderBox;
/*     */     } 
/* 228 */     throw new RuntimeException("Box not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Box> getBoxByLocation(String location) {
/* 233 */     String sql = "from Box box where box.location.id = ? and box.isPutIntStorage=0 and box.isPickingOutboundFinish=1 and box.isEnabled=0";
/* 234 */     return getHibernateTemplate().find(sql, location, (Type)Hibernate.STRING);
/*     */   }
/*     */   
/*     */   public List<Box> getBoxByLocationAndPart(String location, String part) {
/* 238 */     String sql = "from Box box where box.location.id = ? and box.part.id = ?  and box.isPutIntStorage=0 and box.isPickingOutboundFinish=1 and box.isEnabled=0";
/*     */     
/* 240 */     Object[] paratm = { location, part };
/* 241 */     return getHibernateTemplate().find(sql, paratm);
/*     */   }
/*     */   
/*     */   public List<Box> getBoxByPoritem(Integer id) {
/* 245 */     String sql = "from Box box where box.poritem.id = ?";
/* 246 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public List<Box> getBoxByReceiptsItem(Integer id) {
/* 250 */     String sql = "from Box box where box.poritem.id = ?";
/* 251 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public List<Box> getBoxByLocationAndIsPutIntStorage(Integer id) {
/* 255 */     String sql = "from Box box  where box.isPutIntStorage=0 and box.isPickingOutboundFinish=1 and box.isEnabled=0 and box.location.id = ?";
/* 256 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public List exportBoxByDate(final Date startDate, final Date endDate) {
/* 260 */     String sql = "select box.location.code, box.lotSer.id, box.blanketMark, box.part.id, box.count, box.date from Box box  where box.isPutIntStorage=0 and box.isPickingOutboundFinish=1 and box.isEnabled=0 and box.date >= ? and box.date <= ? ";
/*     */ 
/*     */ 
/*     */     
/* 264 */     return (List)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException
/*     */           {
/* 268 */             Query query = session.createQuery("select box.location.code, box.lotSer.id, box.blanketMark, box.part.id, box.count, box.date from Box box  where box.isPutIntStorage=0 and box.isPickingOutboundFinish=1 and box.isEnabled=0 and box.date >= ? and box.date <= ? ");
/* 269 */             query.setDate(0, startDate);
/* 270 */             query.setDate(1, endDate);
/* 271 */             List list = query.list();
/*     */ 
/*     */ 
/*     */             
/*     */             try {  }
/* 276 */             catch (Exception e)
/* 277 */             { e.getStackTrace(); }
/*     */             finally
/* 279 */             { if (session != null && session.isOpen())
/* 280 */                 session.close();  }  if (session != null && session.isOpen()) session.close();
/*     */ 
/*     */             
/* 283 */             return list;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public List exportBoxByendDate(final Date endDate) {
/* 289 */     String sql = "select box.location.code, box.lotSer.id, box.blanketMark, box.part.id, box.count, box.date from Box box  where box.isPutIntStorage=0 and box.isPickingOutboundFinish=1 and box.isEnabled=0 and box.date <= ? ";
/*     */ 
/*     */ 
/*     */     
/* 293 */     return (List)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException
/*     */           {
/* 297 */             Query query = session.createQuery("select box.location.code, box.lotSer.id, box.blanketMark, box.part.id, box.count, box.date from Box box  where box.isPutIntStorage=0 and box.isPickingOutboundFinish=1 and box.isEnabled=0 and box.date <= ? ");
/* 298 */             query.setDate(0, endDate);
/* 299 */             List list = query.list();
/*     */ 
/*     */ 
/*     */             
/*     */             try {  }
/* 304 */             catch (Exception e)
/* 305 */             { e.getStackTrace(); }
/*     */             finally
/* 307 */             { if (session != null && session.isOpen())
/* 308 */                 session.close();  }  if (session != null && session.isOpen()) session.close();
/*     */ 
/*     */             
/* 311 */             return list;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public Integer getBoxListByReceiptIsAllPrint(final String id) {
/* 317 */     String sql = "from Box box where box.poritem.id in (select item.id from PurchaseOrderReceiptsItem item where item.por_number.por_number = ?) and box.isPrint=1";
/* 318 */     return (Integer)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException
/*     */           {
/* 322 */             Query query = session.createQuery("from Box box where box.poritem.id in (select item.id from PurchaseOrderReceiptsItem item where item.por_number.por_number = ?) and box.isPrint=1");
/* 323 */             query.setString(0, id);
/* 324 */             List list = query.list();
/*     */ 
/*     */ 
/*     */             
/*     */             try {  }
/* 329 */             catch (Exception e)
/* 330 */             { e.getStackTrace(); }
/*     */             finally
/* 332 */             { if (session != null && session.isOpen())
/* 333 */                 session.close();  }  if (session != null && session.isOpen()) session.close();
/*     */ 
/*     */             
/* 336 */             return Integer.valueOf(list.size());
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public List<Object[]> getBoxLocationNumberByGroupBy() {
/* 342 */     String sql = "select count(*), box.location.code from Box box where box.location.id in ( select sl.id from StorageLocation sl where sl.code like 'A1%' or sl.code like 'A2%' or sl.code like 'A3%' or sl.code like 'A4%' or sl.code like 'A5%' or sl.code like 'A6%' or sl.code like 'A7%' or sl.code like 'A8%' or sl.code like 'A9%' or sl.code like 'AA%' or sl.code like 'AB%' or sl.code like 'AC%' or sl.code like 'AD%' )and box.isPutIntStorage=0 and box.isPickingOutboundFinish=1 and box.isEnabled=0 group by box.location.code ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 348 */     return getHibernateTemplate().find(sql);
/*     */   }
/*     */   
/*     */   public List<Map> getBoxLocationNumberByGroupByCountLocationType() {
/* 352 */     String sql = "select count(*), box.location.code from Box box where box.location.id in ( select sl.id from StorageLocation sl where sl.code like 'A1%' or sl.code like 'A2%' or sl.code like 'A3%' or sl.code like 'A4%' or sl.code like 'A5%' or sl.code like 'A6%' or sl.code like 'A7%' or sl.code like 'A8%' or sl.code like 'A9%' or sl.code like 'AA%' or sl.code like 'AB%' or sl.code like 'AC%' or sl.code like 'AD%' )and box.isPutIntStorage=0 and box.isPickingOutboundFinish=1 and box.isEnabled=0 group by box.location.code ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 359 */     List<Object[]> listObject = getHibernateTemplate().find(sql);
/* 360 */     List<Map> list = new ArrayList<Map>();
/* 361 */     if (listObject == null || listObject.isEmpty()) {
/* 362 */       return null;
/*     */     }
/* 364 */     Integer numA1 = Integer.valueOf(0), countA1 = Integer.valueOf(0);
/* 365 */     Integer numA2 = Integer.valueOf(0), countA2 = Integer.valueOf(0);
/* 366 */     Integer numA3 = Integer.valueOf(0), countA3 = Integer.valueOf(0);
/* 367 */     Integer numA4 = Integer.valueOf(0), countA4 = Integer.valueOf(0);
/* 368 */     Integer numA5 = Integer.valueOf(0), countA5 = Integer.valueOf(0);
/* 369 */     Integer numA6 = Integer.valueOf(0), countA6 = Integer.valueOf(0);
/* 370 */     Integer numA7 = Integer.valueOf(0), countA7 = Integer.valueOf(0);
/* 371 */     Integer numA8 = Integer.valueOf(0), countA8 = Integer.valueOf(0);
/* 372 */     Integer numA9 = Integer.valueOf(0), countA9 = Integer.valueOf(0);
/* 373 */     Integer numAA = Integer.valueOf(0), countAA = Integer.valueOf(0);
/* 374 */     Integer numAB = Integer.valueOf(0), countAB = Integer.valueOf(0);
/* 375 */     Integer numAC = Integer.valueOf(0), countAC = Integer.valueOf(0);
/* 376 */     Integer numAD = Integer.valueOf(0), countAD = Integer.valueOf(0);
/*     */ 
/*     */ 
/*     */     
/* 380 */     for (Object[] object : listObject) {
/* 381 */       Integer number = (Integer)object[0];
/* 382 */       String code = (String)object[1];
/* 383 */       String cdoeDispose = code.substring(0, 2);
/*     */       
/* 385 */       if (cdoeDispose.equalsIgnoreCase("A1")) {
/* 386 */         numA1 = Integer.valueOf(numA1.intValue() + number.intValue());
/* 387 */         countA1 = Integer.valueOf(countA1.intValue() + 1); continue;
/* 388 */       }  if (cdoeDispose.equalsIgnoreCase("A2")) {
/* 389 */         numA2 = Integer.valueOf(numA2.intValue() + number.intValue());
/* 390 */         countA2 = Integer.valueOf(countA2.intValue() + 1); continue;
/* 391 */       }  if (cdoeDispose.equalsIgnoreCase("A3")) {
/* 392 */         numA3 = Integer.valueOf(numA3.intValue() + number.intValue());
/* 393 */         countA3 = Integer.valueOf(countA3.intValue() + 1); continue;
/* 394 */       }  if (cdoeDispose.equalsIgnoreCase("A4")) {
/* 395 */         numA4 = Integer.valueOf(numA4.intValue() + number.intValue());
/* 396 */         countA4 = Integer.valueOf(countA4.intValue() + 1); continue;
/* 397 */       }  if (cdoeDispose.equalsIgnoreCase("A5")) {
/* 398 */         numA5 = Integer.valueOf(numA5.intValue() + number.intValue());
/* 399 */         countA5 = Integer.valueOf(countA5.intValue() + 1); continue;
/* 400 */       }  if (cdoeDispose.equalsIgnoreCase("A6")) {
/* 401 */         numA6 = Integer.valueOf(numA6.intValue() + number.intValue());
/* 402 */         countA6 = Integer.valueOf(countA6.intValue() + 1); continue;
/* 403 */       }  if (cdoeDispose.equalsIgnoreCase("A7")) {
/* 404 */         numA7 = Integer.valueOf(numA7.intValue() + number.intValue());
/* 405 */         countA7 = Integer.valueOf(countA7.intValue() + 1); continue;
/* 406 */       }  if (cdoeDispose.equalsIgnoreCase("A8")) {
/* 407 */         numA8 = Integer.valueOf(numA8.intValue() + number.intValue());
/* 408 */         countA8 = Integer.valueOf(countA8.intValue() + 1); continue;
/* 409 */       }  if (cdoeDispose.equalsIgnoreCase("A9")) {
/* 410 */         numA9 = Integer.valueOf(numA9.intValue() + number.intValue());
/* 411 */         countA9 = Integer.valueOf(countA9.intValue() + 1); continue;
/* 412 */       }  if (cdoeDispose.equalsIgnoreCase("AA")) {
/* 413 */         numAA = Integer.valueOf(numAA.intValue() + number.intValue());
/* 414 */         countAA = Integer.valueOf(countAA.intValue() + 1); continue;
/* 415 */       }  if (cdoeDispose.equalsIgnoreCase("AB")) {
/* 416 */         numAB = Integer.valueOf(numAB.intValue() + number.intValue());
/* 417 */         countAB = Integer.valueOf(countAB.intValue() + 1); continue;
/* 418 */       }  if (cdoeDispose.equalsIgnoreCase("AC")) {
/* 419 */         numAC = Integer.valueOf(numAC.intValue() + number.intValue());
/* 420 */         countAC = Integer.valueOf(countAC.intValue() + 1); continue;
/* 421 */       }  if (cdoeDispose.equalsIgnoreCase("AD")) {
/* 422 */         numAD = Integer.valueOf(numAD.intValue() + number.intValue());
/* 423 */         countAD = Integer.valueOf(countAD.intValue() + 1);
/*     */       } 
/*     */     } 
/* 426 */     BigDecimal dividend = new BigDecimal(0);
/* 427 */     BigDecimal returnValue = new BigDecimal(0);
/*     */     
/* 429 */     Map<Object, Object> map = new HashMap<Object, Object>();
/* 430 */     map = new HashMap<Object, Object>();
/* 431 */     dividend = (new BigDecimal(countA1.intValue())).multiply(new BigDecimal(8));
/* 432 */     map.put("code", "A1");
/* 433 */     map.put("amount", (new BigDecimal(numA1.intValue())).divide(dividend, 4, RoundingMode.DOWN));
/* 434 */     list.add(map);
/*     */     
/* 436 */     map = new HashMap<Object, Object>();
/* 437 */     dividend = (new BigDecimal(countA2.intValue())).multiply(new BigDecimal(8));
/* 438 */     map.put("code", "A2");
/* 439 */     map.put("amount", (new BigDecimal(numA2.intValue())).divide(dividend, 4, RoundingMode.DOWN));
/* 440 */     list.add(map);
/*     */     
/* 442 */     map = new HashMap<Object, Object>();
/* 443 */     dividend = (new BigDecimal(countA3.intValue())).multiply(new BigDecimal(8));
/* 444 */     map.put("code", "A3");
/* 445 */     map.put("amount", (new BigDecimal(numA3.intValue())).divide(dividend, 4, RoundingMode.DOWN));
/* 446 */     list.add(map);
/*     */     
/* 448 */     map = new HashMap<Object, Object>();
/* 449 */     dividend = (new BigDecimal(countA4.intValue())).multiply(new BigDecimal(8));
/* 450 */     map.put("code", "A4");
/* 451 */     map.put("amount", (new BigDecimal(numA4.intValue())).divide(dividend, 4, RoundingMode.DOWN));
/* 452 */     list.add(map);
/*     */     
/* 454 */     map = new HashMap<Object, Object>();
/* 455 */     dividend = (new BigDecimal(countA5.intValue())).multiply(new BigDecimal(8));
/* 456 */     map.put("code", "A5");
/* 457 */     map.put("amount", (new BigDecimal(numA5.intValue())).divide(dividend, 4, RoundingMode.DOWN));
/* 458 */     list.add(map);
/*     */     
/* 460 */     map = new HashMap<Object, Object>();
/* 461 */     dividend = (new BigDecimal(countA6.intValue())).multiply(new BigDecimal(8));
/* 462 */     map.put("code", "A6");
/* 463 */     map.put("amount", (new BigDecimal(numA6.intValue())).divide(dividend, 4, RoundingMode.DOWN));
/* 464 */     list.add(map);
/*     */     
/* 466 */     map = new HashMap<Object, Object>();
/* 467 */     dividend = (new BigDecimal(countA7.intValue())).multiply(new BigDecimal(8));
/* 468 */     map.put("code", "A7");
/* 469 */     map.put("amount", (new BigDecimal(numA7.intValue())).divide(dividend, 4, RoundingMode.DOWN));
/* 470 */     list.add(map);
/*     */     
/* 472 */     map = new HashMap<Object, Object>();
/* 473 */     dividend = (new BigDecimal(countA8.intValue())).multiply(new BigDecimal(8));
/* 474 */     map.put("code", "A8");
/* 475 */     map.put("amount", (new BigDecimal(numA8.intValue())).divide(dividend, 4, RoundingMode.DOWN));
/* 476 */     list.add(map);
/*     */     
/* 478 */     map = new HashMap<Object, Object>();
/* 479 */     dividend = (new BigDecimal(countA9.intValue())).multiply(new BigDecimal(8));
/* 480 */     map.put("code", "A9");
/* 481 */     map.put("amount", (new BigDecimal(numA9.intValue())).divide(dividend, 4, RoundingMode.DOWN));
/* 482 */     list.add(map);
/*     */     
/* 484 */     map = new HashMap<Object, Object>();
/* 485 */     if (countAA.intValue() != 0) {
/* 486 */       dividend = (new BigDecimal(countAA.intValue())).multiply(new BigDecimal(8));
/* 487 */       returnValue = (new BigDecimal(numAA.intValue())).divide(dividend, 4, RoundingMode.DOWN);
/*     */     } else {
/* 489 */       returnValue = new BigDecimal(0);
/*     */     } 
/* 491 */     map.put("code", "AA");
/* 492 */     map.put("amount", returnValue);
/* 493 */     list.add(map);
/*     */     
/* 495 */     map = new HashMap<Object, Object>();
/* 496 */     dividend = (new BigDecimal(countAB.intValue())).multiply(new BigDecimal(8));
/* 497 */     map.put("code", "AB");
/* 498 */     map.put("amount", (new BigDecimal(numAB.intValue())).divide(dividend, 4, RoundingMode.DOWN));
/* 499 */     list.add(map);
/*     */     
/* 501 */     map = new HashMap<Object, Object>();
/* 502 */     dividend = (new BigDecimal(countAC.intValue())).multiply(new BigDecimal(8));
/* 503 */     map.put("code", "AC");
/* 504 */     map.put("amount", (new BigDecimal(numAC.intValue())).divide(dividend, 4, RoundingMode.DOWN));
/* 505 */     list.add(map);
/*     */     
/* 507 */     map = new HashMap<Object, Object>();
/* 508 */     dividend = (new BigDecimal(countAD.intValue())).multiply(new BigDecimal(8));
/* 509 */     map.put("code", "AD");
/* 510 */     map.put("amount", (new BigDecimal(countAD.intValue())).divide(dividend, 4, RoundingMode.DOWN));
/* 511 */     list.add(map);
/*     */     
/* 513 */     return list;
/*     */   }
/*     */   
/*     */   public List<Object[]> getBoxByLoation(String code) {
/* 517 */     String sql = "select count(*),box.location.code from Box box where box.location.code like '" + code + "%' group by box.location.code";
/* 518 */     return getHibernateTemplate().find(sql);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Box> getBoxByRqcItemAndPart(Integer id, String partId) {
/* 523 */     String sql = "from Box box where box.rqcitem.id = ? and box.part.id = ?";
/* 524 */     Object[] param = { id, partId };
/* 525 */     return getHibernateTemplate().find(sql, param);
/*     */   }
/*     */   
/*     */   public List getBomByBox(String part) {
/* 529 */     String sql = "from Bom bom where bom.product_no.id = ? ";
/* 530 */     return getHibernateTemplate().find(sql, part, (Type)Hibernate.STRING);
/*     */   }
/*     */   
/*     */   public List<Box> getBoxByLocation(String location, String part) {
/* 534 */     String sql = "from Box box where box.status=3 and box.location.id=? and box.part.id=? and box.enabled=0 ";
/* 535 */     Object[] params = { location, part };
/* 536 */     return getHibernateTemplate().find(sql, params);
/*     */   }
/*     */   public List getBoxSumCount(Integer location, String pratId) {
/* 539 */     String sql = "select sum(box1.number) from Box box1 where box1.location.id = " + location + " and box1.part.id = '" + pratId + "' and box1.enabled=0 and box1.status=4";
/* 540 */     List listsCount = getHibernateTemplate().find(sql);
/* 541 */     return listsCount;
/*     */   }
/*     */   
/*     */   public BadReasons getBadReasons(String lotSerId) {
/* 545 */     String sql = " from PurchaseOrderRqcUnqualified poRqcunqualified where poRqcunqualified.rqc_box_id.lot.id = '" + lotSerId + "' order by poRqcunqualified.id desc";
/* 546 */     List<PurchaseOrderRqcUnqualified> list = getHibernateTemplate().find(sql);
/* 547 */     BadReasons reasons = null;
/* 548 */     if (list.size() > 0) {
/* 549 */       reasons = ((PurchaseOrderRqcUnqualified)list.get(0)).getReasons();
/*     */     }
/* 551 */     return reasons;
/*     */   }
/*     */   
/*     */   public List<PurchaseOrderRqcUnqualified> getPurchaseOrderRqcUnqualifiedList(Integer boxId) {
/* 555 */     String sql = " from PurchaseOrderRqcUnqualified poRqcunqualified where poRqcunqualified.id in(select max(unqualified.id) from PurchaseOrderRqcUnqualified unqualified where unqualified.rqc_box_id.id = '" + boxId + "' group by unqualified.reasons.id)  order by poRqcunqualified.id ";
/* 556 */     return getHibernateTemplate().find(sql);
/*     */   }
/*     */   
/*     */   public List<Box> getBoxByPart(String part) {
/* 560 */     String sql = "from Box box where box.status=3 and box.part.id=? and box.enabled=0 ";
/* 561 */     Object[] params = { part };
/* 562 */     return getHibernateTemplate().find(sql, params);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/hibernate/BoxDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
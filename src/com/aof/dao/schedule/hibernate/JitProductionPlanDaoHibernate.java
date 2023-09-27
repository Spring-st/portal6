/*     */ package com.aof.dao.schedule.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.schedule.JitProductionPlanDao;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.schedule.EdiProduction;
/*     */ import com.aof.model.schedule.JitProductionPlan;
/*     */ import com.aof.model.schedule.query.JitProductionPlanQueryCondition;
/*     */ import com.aof.model.schedule.query.JitProductionPlanQueryOrder;
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.HibernateException;
/*     */ import net.sf.hibernate.Session;
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
/*     */ public class JitProductionPlanDaoHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements JitProductionPlanDao
/*     */ {
/*  40 */   private Log log = LogFactory.getLog(JitProductionPlanDaoHibernate.class); private static final String SQL_COUNT = "select count(*) from JitProductionPlan entity";
/*     */   public JitProductionPlan save(JitProductionPlan entity) {
/*  42 */     getHibernateTemplate().save(entity);
/*  43 */     return entity;
/*     */   }
/*     */   private static final String SQL = "from JitProductionPlan entity";
/*     */   public JitProductionPlan getJitProductionPlan(Integer id) {
/*  47 */     if (id == null && 
/*  48 */       this.log.isDebugEnabled()) {
/*  49 */       this.log.debug("get JitProductionPlan with null id!");
/*  50 */       return null;
/*     */     } 
/*     */     
/*  53 */     return (JitProductionPlan)getHibernateTemplate().get(JitProductionPlan.class, id);
/*     */   }
/*     */   
/*     */   public void delete(JitProductionPlan entity) {
/*  57 */     getHibernateTemplate().delete(entity);
/*     */   }
/*     */   
/*     */   public JitProductionPlan update(JitProductionPlan entity) {
/*  61 */     if (entity.getId() == null) {
/*  62 */       throw new RuntimeException("update JitProductionPlan with null id!");
/*     */     }
/*  64 */     JitProductionPlan oldEntity = getJitProductionPlan(entity.getId());
/*  65 */     if (oldEntity != null) {
/*     */       try {
/*  67 */         PropertyUtils.copyProperties(oldEntity, entity);
/*  68 */       } catch (Exception e) {
/*  69 */         throw new RuntimeException("copy error with MappingCsutomer" + e);
/*     */       } 
/*  71 */       getHibernateTemplate().update(oldEntity);
/*  72 */       return oldEntity;
/*     */     } 
/*  74 */     throw new RuntimeException("MappinCustomer not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  83 */       { JitProductionPlanQueryCondition.ID_EQ, "entity.id = ?"
/*  84 */       }, { JitProductionPlanQueryCondition.TYPE_EQ, "entity.type = ?"
/*  85 */       }, { JitProductionPlanQueryCondition.PRODUCTCLASS_NOT_EQ, "entity.childPart.productClass != ? or entity.childPart.productClass is null"
/*  86 */       }, { JitProductionPlanQueryCondition.PRODUCTCLASS_EQ, "entity.childPart.productClass = ? "
/*  87 */       }, { JitProductionPlanQueryCondition.PRODUCTION_ID_EQ, "entity.productionId.id = ?"
/*  88 */       }, { JitProductionPlanQueryCondition.DATE_GE, "entity.date >= ?"
/*  89 */       }, { JitProductionPlanQueryCondition.DATE_EQ, "entity.date = ?"
/*  90 */       }, { JitProductionPlanQueryCondition.QTY_GT, "entity.qty > ?"
/*  91 */       }, { JitProductionPlanQueryCondition.PARTID_VEND_EQ, "entity.childPart.vend = ?" }
/*     */     };
/*     */   
/*  94 */   private static final Object[][] QUERY_ORDERS = new Object[][] { 
/*  95 */       { JitProductionPlanQueryOrder.ID, "entity.id"
/*  96 */       }, { JitProductionPlanQueryOrder.DATE, "entity.date"
/*  97 */       }, { JitProductionPlanQueryOrder.CURRENTQTY, "entity.currentQty"
/*  98 */       }, { JitProductionPlanQueryOrder.HOUR2DEMANDQTY, "entity.hour2DemandQty"
/*  99 */       }, { JitProductionPlanQueryOrder.HOUR4DEMANDQTY, "entity.hour4DemandQty"
/* 100 */       }, { JitProductionPlanQueryOrder.HOUR6DEMANDQTY, "entity.hour6DemandQty"
/* 101 */       }, { JitProductionPlanQueryOrder.HOUR8DEMANDQTY, "entity.hour8DemandQty"
/* 102 */       }, { JitProductionPlanQueryOrder.HOUR10DEMANDQTY, "entity.hour10DemandQty"
/* 103 */       }, { JitProductionPlanQueryOrder.HOUR12DEMANDQTY, "entity.hour12DemandQty"
/* 104 */       }, { JitProductionPlanQueryOrder.HOUR14DEMANDQTY, "entity.hour14DemandQty" }, 
/* 105 */       { JitProductionPlanQueryOrder.HOUR16DEMANDQTY, "entity.hour16DemandQty"
/* 106 */       }, { JitProductionPlanQueryOrder.HOUR18DEMANDQTY, "entity.hour18DemandQty"
/* 107 */       }, { JitProductionPlanQueryOrder.HOUR20DEMANDQTY, "entity.hour20DemandQty"
/* 108 */       }, { JitProductionPlanQueryOrder.HOUR22DEMANDQTY, "entity.hour22DemandQty"
/* 109 */       }, { JitProductionPlanQueryOrder.HOUR24DEMANDQTY, "entity.hour24DemandQty" } };
/*     */ 
/*     */   
/*     */   public Integer getListCount(Map conditions) {
/* 113 */     return Integer.valueOf(getListCount(conditions, "select count(*) from JitProductionPlan entity", QUERY_CONDITIONS));
/*     */   }
/*     */ 
/*     */   
/*     */   public List<JitProductionPlan> getList(Map conditions, int pageNum, int pageSize, JitProductionPlanQueryOrder order, boolean descend) {
/* 118 */     return getList(conditions, pageNum, pageSize, order, descend, "from JitProductionPlan entity", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/* 120 */   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHHmmss");
/* 121 */   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
/*     */ 
/*     */   
/*     */   public List<JitProductionPlan> DismantlingBom(final EdiProduction ediProduction, final WmsPart part, final BigDecimal qty) {
/* 125 */     return (List<JitProductionPlan>)getHibernateTemplate().execute(new HibernateCallback() {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 127 */             List<JitProductionPlan> items = new ArrayList<JitProductionPlan>();
/* 128 */             PreparedStatement ps = null;
/* 129 */             ResultSet rs = null;
/*     */             try {
/* 131 */               Connection conn = session.connection();
/* 132 */               ps = conn.prepareStatement("select * from basic_bom where father_part= '" + part.getId() + "' ");
/* 133 */               rs = ps.executeQuery();
/*     */               
/* 135 */               while (rs.next()) {
/* 136 */                 JitProductionPlan item = new JitProductionPlan();
/* 137 */                 item.setProductionId(ediProduction);
/* 138 */                 item.setType(ediProduction.getType());
/* 139 */                 item.setStatus(Integer.valueOf(1));
/* 140 */                 item.setFatherPart(part);
/* 141 */                 item.setChildPart((WmsPart)JitProductionPlanDaoHibernate.this.getHibernateTemplate().get(WmsPart.class, rs.getString("child_part")));
/* 142 */                 BigDecimal unitQty = (rs.getBigDecimal("unit_qty") == null) ? new BigDecimal(0) : rs.getBigDecimal("unit_qty");
/* 143 */                 item.setQty(unitQty.multiply(qty));
/*     */                 
/* 145 */                 Calendar calendar = Calendar.getInstance();
/*     */                 
/* 147 */                 calendar.setTime(ediProduction.getTaskTime());
/* 148 */                 Integer hour = Integer.valueOf(calendar.get(11));
/* 149 */                 if (hour.intValue() < 6) {
/* 150 */                   Calendar calendar1 = Calendar.getInstance();
/* 151 */                   calendar1.setTime(ediProduction.getTaskTime());
/* 152 */                   calendar1.add(5, -1);
/* 153 */                   item.setDate(calendar1.getTime());
/*     */                 } else {
/* 155 */                   item.setDate(ediProduction.getTaskTime());
/*     */                 } 
/* 157 */                 if (hour.intValue() >= 0 && hour.intValue() < 2) {
/* 158 */                   item.setHour2DemandQty(unitQty.multiply(qty));
/* 159 */                 } else if (hour.intValue() >= 2 && hour.intValue() < 4) {
/* 160 */                   item.setHour4DemandQty(unitQty.multiply(qty));
/* 161 */                 } else if (hour.intValue() >= 4 && hour.intValue() < 6) {
/* 162 */                   item.setHour6DemandQty(unitQty.multiply(qty));
/* 163 */                 } else if (hour.intValue() >= 6 && hour.intValue() < 8) {
/* 164 */                   item.setHour8DemandQty(unitQty.multiply(qty));
/* 165 */                 } else if (hour.intValue() >= 8 && hour.intValue() < 10) {
/* 166 */                   item.setHour10DemandQty(unitQty.multiply(qty));
/* 167 */                 } else if (hour.intValue() >= 10 && hour.intValue() < 12) {
/* 168 */                   item.setHour12DemandQty(unitQty.multiply(qty));
/* 169 */                 } else if (hour.intValue() >= 12 && hour.intValue() < 14) {
/* 170 */                   item.setHour14DemandQty(unitQty.multiply(qty));
/* 171 */                 } else if (hour.intValue() >= 14 && hour.intValue() < 16) {
/* 172 */                   item.setHour16DemandQty(unitQty.multiply(qty));
/* 173 */                 } else if (hour.intValue() >= 16 && hour.intValue() < 18) {
/* 174 */                   item.setHour18DemandQty(unitQty.multiply(qty));
/* 175 */                 } else if (hour.intValue() >= 18 && hour.intValue() < 20) {
/* 176 */                   item.setHour20DemandQty(unitQty.multiply(qty));
/* 177 */                 } else if (hour.intValue() >= 20 && hour.intValue() < 22) {
/* 178 */                   item.setHour22DemandQty(unitQty.multiply(qty));
/* 179 */                 } else if (hour.intValue() >= 22) {
/* 180 */                   item.setHour24DemandQty(unitQty.multiply(qty));
/*     */                 } 
/* 182 */                 List<JitProductionPlan> jitProductionPlanList = JitProductionPlanDaoHibernate.this.DismantlingBom(ediProduction, item.getChildPart(), item.getQty());
/* 183 */                 if (jitProductionPlanList.size() > 0) {
/* 184 */                   item.setSign(Integer.valueOf(1));
/* 185 */                   item.setJitProductionPlanList(jitProductionPlanList);
/*     */                 } else {
/* 187 */                   item.setSign(Integer.valueOf(0));
/*     */                 } 
/* 189 */                 items.add(item);
/*     */               } 
/* 191 */             } catch (Exception e) {
/* 192 */               System.out.println(e.getMessage());
/*     */             } finally {
/* 194 */               if (rs != null)
/* 195 */                 rs.close(); 
/* 196 */               if (ps != null)
/* 197 */                 ps.close(); 
/* 198 */               if (session != null && session.isOpen()) {
/* 199 */                 session.close();
/*     */               }
/* 201 */               rs = null;
/* 202 */               ps = null;
/*     */             } 
/* 204 */             return items;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public List<Map> computeCombinePlan(final String str) {
/* 210 */     return (List<Map>)getHibernateTemplate().execute(new HibernateCallback() {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 212 */             List<Map> items = new ArrayList<Map>();
/* 213 */             PreparedStatement ps = null;
/* 214 */             ResultSet rs = null;
/*     */             try {
/* 216 */               Connection conn = session.connection();
/* 217 */               ps = conn.prepareStatement("SELECT MAX(a.id) AS id, a.child_part AS part, SUM(ISNULL(a.qty, 0)) AS qty, AVG(ISNULL(a.current_qty, 0)) AS current_qty, AVG(ISNULL(b.high_qty, 0)) AS high_qty, AVG(ISNULL(b.low_qty, 0)) AS low_qty FROM portalv6_jit_production_plan AS a INNER JOIN basic_part AS b ON a.child_part = b.id WHERE a.id IN (" + 
/*     */ 
/*     */ 
/*     */                   
/* 221 */                   str + ")GROUP BY a.child_part ");
/* 222 */               rs = ps.executeQuery();
/*     */               
/* 224 */               while (rs.next()) {
/* 225 */                 Map<Object, Object> item = new HashMap<Object, Object>();
/* 226 */                 String id = rs.getString("id");
/* 227 */                 String part = rs.getString("part");
/* 228 */                 BigDecimal qty = rs.getBigDecimal("qty");
/* 229 */                 BigDecimal currentQty = rs.getBigDecimal("current_qty");
/* 230 */                 BigDecimal highQty = rs.getBigDecimal("high_qty");
/* 231 */                 BigDecimal lowQty = rs.getBigDecimal("low_qty");
/* 232 */                 item.put(id, id);
/* 233 */                 item.put("part", part);
/* 234 */                 item.put("qty", qty);
/* 235 */                 item.put("currentQty", currentQty);
/* 236 */                 item.put("highQty", highQty);
/* 237 */                 item.put("lowQty", lowQty);
/* 238 */                 if (qty.compareTo(highQty) != 1 && qty.compareTo(lowQty) != -1) {
/* 239 */                   item.put("remark", "");
/* 240 */                 } else if (qty.compareTo(highQty) == 1) {
/* 241 */                   item.put("remark", "不能高于高储数量");
/* 242 */                 } else if (qty.compareTo(lowQty) == -1) {
/* 243 */                   item.put("remark", "不能低于低储数量");
/*     */                 } 
/* 245 */                 items.add(item);
/*     */               } 
/* 247 */             } catch (Exception e) {
/* 248 */               System.out.println(e.getMessage());
/*     */             } finally {
/* 250 */               if (rs != null)
/* 251 */                 rs.close(); 
/* 252 */               if (ps != null)
/* 253 */                 ps.close(); 
/* 254 */               if (session != null && session.isOpen()) {
/* 255 */                 session.close();
/*     */               }
/* 257 */               rs = null;
/* 258 */               ps = null;
/*     */             } 
/* 260 */             return items;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, BigDecimal> getPartNeedQtyByBom(final EdiProduction ediProduction, final WmsPart part, final BigDecimal qty) {
/* 267 */     return (Map<String, BigDecimal>)getHibernateTemplate().execute(new HibernateCallback() {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 269 */             Map<String, BigDecimal> items = new LinkedHashMap<String, BigDecimal>();
/* 270 */             PreparedStatement ps = null;
/* 271 */             ResultSet rs = null;
/*     */             
/*     */             try {
/* 274 */               Connection conn = session.connection();
/* 275 */               ps = conn.prepareStatement("select * from basic_bom where father_part= '" + part.getId() + "' ");
/* 276 */               rs = ps.executeQuery();
/* 277 */               while (rs.next()) {
/* 278 */                 StringBuffer sb = new StringBuffer();
/* 279 */                 WmsPart parts = (WmsPart)JitProductionPlanDaoHibernate.this.getHibernateTemplate().get(WmsPart.class, rs.getString("child_part"));
/* 280 */                 sb.append(parts.getId());
/* 281 */                 BigDecimal unitQty = (rs.getBigDecimal("unit_qty") == null) ? new BigDecimal(0) : rs.getBigDecimal("unit_qty");
/* 282 */                 BigDecimal qtys = unitQty.multiply(qty);
/*     */                 
/* 284 */                 Calendar calendar = Calendar.getInstance();
/* 285 */                 calendar.setTime(JitProductionPlanDaoHibernate.this.sdf.parse(String.valueOf(JitProductionPlanDaoHibernate.this.sdf1.format(ediProduction.getTaskDate())) + ediProduction.getTime().toString()));
/* 286 */                 Integer hour = Integer.valueOf(calendar.get(11));
/* 287 */                 if (hour.intValue() < 6) {
/* 288 */                   calendar.set(5, calendar.get(5) - 1);
/* 289 */                   sb.append("+");
/* 290 */                   sb.append(JitProductionPlanDaoHibernate.this.sdf1.format(calendar.getTime()));
/*     */                 } else {
/* 292 */                   sb.append("+");
/* 293 */                   sb.append(JitProductionPlanDaoHibernate.this.sdf1.format(calendar.getTime()));
/*     */                 } 
/* 295 */                 if (hour.intValue() >= 0 && hour.intValue() < 2) {
/* 296 */                   sb.append("+");
/* 297 */                   sb.append(2);
/* 298 */                 } else if (hour.intValue() >= 2 && hour.intValue() < 4) {
/* 299 */                   sb.append("+");
/* 300 */                   sb.append(4);
/* 301 */                 } else if (hour.intValue() >= 4 && hour.intValue() < 6) {
/* 302 */                   sb.append("+");
/* 303 */                   sb.append(6);
/* 304 */                 } else if (hour.intValue() >= 6 && hour.intValue() < 8) {
/* 305 */                   sb.append("+");
/* 306 */                   sb.append(8);
/* 307 */                 } else if (hour.intValue() >= 8 && hour.intValue() < 10) {
/* 308 */                   sb.append("+");
/* 309 */                   sb.append(10);
/* 310 */                 } else if (hour.intValue() >= 10 && hour.intValue() < 12) {
/* 311 */                   sb.append("+");
/* 312 */                   sb.append(12);
/* 313 */                 } else if (hour.intValue() >= 12 && hour.intValue() < 14) {
/* 314 */                   sb.append("+");
/* 315 */                   sb.append(14);
/* 316 */                 } else if (hour.intValue() >= 14 && hour.intValue() < 16) {
/* 317 */                   sb.append("+");
/* 318 */                   sb.append(16);
/* 319 */                 } else if (hour.intValue() >= 16 && hour.intValue() < 18) {
/* 320 */                   sb.append("+");
/* 321 */                   sb.append(18);
/* 322 */                 } else if (hour.intValue() >= 18 && hour.intValue() < 20) {
/* 323 */                   sb.append("+");
/* 324 */                   sb.append(20);
/* 325 */                 } else if (hour.intValue() >= 20 && hour.intValue() < 22) {
/* 326 */                   sb.append("+");
/* 327 */                   sb.append(22);
/* 328 */                 } else if (hour.intValue() >= 22) {
/* 329 */                   sb.append("+");
/* 330 */                   sb.append(24);
/*     */                 } 
/*     */                 
/* 333 */                 Map<String, BigDecimal> mapStr = JitProductionPlanDaoHibernate.this.getPartNeedQtyByBom(ediProduction, (WmsPart)JitProductionPlanDaoHibernate.this.getHibernateTemplate().get(WmsPart.class, rs.getString("child_part")), qtys);
/* 334 */                 if (mapStr.size() > 0) {
/* 335 */                   for (String str : mapStr.keySet()) {
/* 336 */                     if (items.containsKey(str)) {
/* 337 */                       BigDecimal qtya = items.get(str);
/* 338 */                       items.put(str, qtya.add(mapStr.get(str))); continue;
/*     */                     } 
/* 340 */                     items.put(str, mapStr.get(str));
/*     */                   } 
/*     */                   continue;
/*     */                 } 
/* 344 */                 items.put(sb.toString(), qtys);
/*     */               }
/*     */             
/* 347 */             } catch (Exception e) {
/* 348 */               System.out.println(e.getMessage());
/*     */             } finally {
/* 350 */               if (rs != null)
/* 351 */                 rs.close(); 
/* 352 */               if (ps != null)
/* 353 */                 ps.close(); 
/* 354 */               if (session != null && session.isOpen()) {
/* 355 */                 session.close();
/*     */               }
/* 357 */               rs = null;
/* 358 */               ps = null;
/*     */             } 
/* 360 */             return items;
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/schedule/hibernate/JitProductionPlanDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
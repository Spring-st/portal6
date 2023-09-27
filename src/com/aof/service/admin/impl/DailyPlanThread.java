/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import be.ibridge.kettle.core.exception.KettleException;
/*     */ import com.aof.dao.admin.SynBaseDAO;
/*     */ import com.aof.dao.sync.SyncDAO;
/*     */ import com.aof.model.basic.SycSleepTime;
/*     */ import com.aof.model.basic.SyncLog;
/*     */ import com.aof.model.schedule.ProductionDayPlan;
/*     */ import com.aof.model.schedule.ProductionDayPlanHistory;
/*     */ import com.aof.model.sync.shared.XxqadDailyPlanDet;
/*     */ import com.aof.service.basic.SycSleepTimeManager;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.List;
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
/*     */ 
/*     */ public class DailyPlanThread
/*     */   extends Thread
/*     */ {
/*  34 */   private Log log = LogFactory.getLog(DailyPlanThread.class);
/*     */   
/*     */   private SynBaseDAO dao;
/*     */   
/*     */   private SyncDAO daoShared;
/*     */   
/*     */   private SycSleepTimeManager sycSleepTimeManager;
/*     */   
/*     */   private String time;
/*     */ 
/*     */   
/*     */   public DailyPlanThread(SycSleepTimeManager sycSleepTimeManager, SynBaseDAO dao, SyncDAO daoShared, String time) {
/*  46 */     this.dao = dao;
/*  47 */     this.time = time;
/*  48 */     this.daoShared = daoShared;
/*  49 */     this.sycSleepTimeManager = sycSleepTimeManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     while (true) {
/*     */       try {
/*  56 */         SycSleepTime sycSleepTime = new SycSleepTime();
/*  57 */         sycSleepTime = this.sycSleepTimeManager.getSycSleepTime("日计划信息");
/*  58 */         if (sycSleepTime != null) {
/*  59 */           this.time = String.valueOf(Integer.valueOf(sycSleepTime
/*  60 */                 .getSleepTime()).intValue() * 1000 * 60);
/*     */         }
/*  62 */         sleep(Long.parseLong(this.time));
/*  63 */         Date date = new Date();
/*  64 */         SimpleDateFormat format = new SimpleDateFormat(
/*  65 */             "yyyy/MM/dd hh:mm:ss");
/*  66 */         System.out.println("日计划信息同步-------------------------1-" + 
/*  67 */             format.format(date));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  72 */         String beginSql = "select ctrl.xxqad_seq from QADCtrl ctrl  where ctrl.xxqad_portal=0 and ctrl.xxqad_table_qty > 0";
/*     */ 
/*     */         
/*  75 */         List<String> revList = this.daoShared
/*  76 */           .getObjectList("select  det.xxqad_dailyplan_rev  from XxqadDailyPlanDet det order by  det.xxqad_dailyplan_rev desc ");
/*     */ 
/*     */         
/*     */         try {
/*  80 */           xxqadDailyPlanDet(revList);
/*  81 */         } catch (KettleException e1) {
/*     */           
/*  83 */           e1.printStackTrace();
/*     */         } 
/*  85 */         System.out.println("-------------------- 日计划信息同步完成");
/*  86 */       } catch (InterruptedException e) {
/*  87 */         e.printStackTrace(); continue;
/*     */       } finally {
/*  89 */         System.gc();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void xxqadDailyPlanDet(List<String> sqlList) throws KettleException {
/*  97 */     if (sqlList != null && sqlList.size() > 0) {
/*  98 */       String sql = sqlList.get(0);
/*     */       try {
/* 100 */         List<XxqadDailyPlanDet> syncXxqadDailyPlanList = this.daoShared
/* 101 */           .getObjectList(" from XxqadDailyPlanDet p  where  (p.xxqad_dailyplan_portalread = 0 or p.xxqad_dailyplan_portalread is null) and p.xxqad_dailyplan_rev='" + 
/* 102 */             sql + "'  ");
/*     */         
/* 104 */         if (syncXxqadDailyPlanList.size() != 0) {
/* 105 */           insertProductionDayPlanHistory();
/*     */         }
/* 107 */         Boolean issyncok = Boolean.valueOf(true);
/* 108 */         for (XxqadDailyPlanDet shared : syncXxqadDailyPlanList) {
/*     */           try {
/* 110 */             insertXxqadDailyPlanDet(shared, this.dao, this.daoShared);
/*     */           
/*     */           }
/* 113 */           catch (Exception e) {
/* 114 */             insertSystemLog("RedMinuteSyncJob", 
/* 115 */                 "xxqadDailyPlanDetListSyncRead", 
/* 116 */                 e.getMessage(), "1");
/* 117 */             issyncok = Boolean.valueOf(false);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 133 */       catch (Exception e) {
/* 134 */         insertSystemLog("RedMinuteSyncJob", 
/* 135 */             "xxqadDailyPlanDetListSyncRead", e.getMessage(), "1");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void insertProductionDayPlanHistory() {
/* 141 */     String sqlPlan = " from ProductionDayPlan plan ";
/*     */     
/*     */     try {
/* 144 */       List<ProductionDayPlan> planList = this.dao.getObjectList(sqlPlan);
/* 145 */       ProductionDayPlanHistory history = null;
/* 146 */       for (ProductionDayPlan dayPlan : planList) {
/* 147 */         history = new ProductionDayPlanHistory();
/* 148 */         history.setAsnNo(dayPlan.getAsnNo());
/* 149 */         history.setVersion(dayPlan.getVersion());
/* 150 */         history.setCreatDate(dayPlan.getCreatDate());
/* 151 */         history.setCreatTime(dayPlan.getCreatTime());
/* 152 */         history.setDailyDate(dayPlan.getDailyDate());
/* 153 */         history.setCarkind(dayPlan.getCarkind());
/* 154 */         history.setPlanRem(dayPlan.getPlanRem());
/* 155 */         history.setPlanSum(dayPlan.getPlanSum());
/* 156 */         history.setPlanWeitou(dayPlan.getPlanWeitou());
/* 157 */         history.setPlanLco(dayPlan.getPlanLco());
/* 158 */         history.setPlanLcx(dayPlan.getPlanLcx());
/* 159 */         history.setPlanAll(dayPlan.getPlanAll());
/* 160 */         history.setPlanM1(dayPlan.getPlanM1());
/* 161 */         history.setPlanMouth(dayPlan.getPlanMouth());
/* 162 */         history.setPlanD1(dayPlan.getPlanD1());
/* 163 */         history.setPlanPbs(dayPlan.getPlanPbs());
/* 164 */         history.setPlanPrj(dayPlan.getPlanPrj());
/* 165 */         history.setPlanWbs(dayPlan.getPlanWbs());
/* 166 */         history.setPlanSum1(dayPlan.getPlanSum1());
/* 167 */         history.setDay1(dayPlan.getDay1());
/* 168 */         history.setDay2(dayPlan.getDay2());
/* 169 */         history.setDay3(dayPlan.getDay3());
/* 170 */         history.setDay4(dayPlan.getDay4());
/* 171 */         history.setDay5(dayPlan.getDay5());
/* 172 */         history.setDay6(dayPlan.getDay6());
/* 173 */         history.setDay7(dayPlan.getDay7());
/* 174 */         history.setDay8(dayPlan.getDay8());
/* 175 */         history.setDay9(dayPlan.getDay9());
/* 176 */         history.setDay10(dayPlan.getDay10());
/* 177 */         history.setDay11(dayPlan.getDay11());
/* 178 */         history.setDay12(dayPlan.getDay12());
/* 179 */         history.setDay13(dayPlan.getDay13());
/* 180 */         history.setDay14(dayPlan.getDay14());
/* 181 */         history.setDay15(dayPlan.getDay15());
/* 182 */         history.setDay16(dayPlan.getDay16());
/* 183 */         history.setDay17(dayPlan.getDay17());
/* 184 */         history.setDay18(dayPlan.getDay18());
/* 185 */         history.setDay19(dayPlan.getDay19());
/* 186 */         history.setDay20(dayPlan.getDay20());
/* 187 */         history.setDay21(dayPlan.getDay21());
/* 188 */         history.setDay22(dayPlan.getDay22());
/* 189 */         history.setDay23(dayPlan.getDay23());
/* 190 */         history.setDay24(dayPlan.getDay24());
/* 191 */         history.setDay25(dayPlan.getDay25());
/* 192 */         history.setDay26(dayPlan.getDay26());
/* 193 */         history.setDay27(dayPlan.getDay27());
/* 194 */         history.setDay28(dayPlan.getDay28());
/* 195 */         history.setDay29(dayPlan.getDay29());
/* 196 */         history.setDay30(dayPlan.getDay30());
/* 197 */         history.setDay31(dayPlan.getDay31());
/* 198 */         history.setDay32(dayPlan.getDay32());
/* 199 */         history.setDay33(dayPlan.getDay33());
/* 200 */         history.setDay34(dayPlan.getDay34());
/* 201 */         history.setDay35(dayPlan.getDay35());
/* 202 */         history.setDay36(dayPlan.getDay36());
/* 203 */         history.setDay37(dayPlan.getDay37());
/* 204 */         history.setDay38(dayPlan.getDay38());
/* 205 */         history.setDay39(dayPlan.getDay39());
/* 206 */         history.setDay40(dayPlan.getDay40());
/* 207 */         history.setDay41(dayPlan.getDay41());
/* 208 */         history.setDay42(dayPlan.getDay42());
/* 209 */         history.setDay43(dayPlan.getDay43());
/* 210 */         history.setDay44(dayPlan.getDay44());
/* 211 */         history.setDay45(dayPlan.getDay45());
/*     */         
/* 213 */         this.dao.saveObject(history);
/* 214 */         this.dao.removeObject(dayPlan);
/*     */       } 
/* 216 */       history = null;
/* 217 */     } catch (Exception e) {
/*     */       
/* 219 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void insertXxqadDailyPlanDet(XxqadDailyPlanDet plan, SynBaseDAO dao, SyncDAO daoShared) {
/*     */     try {
/* 227 */       ProductionDayPlan day = null;
/* 228 */       day = new ProductionDayPlan();
/* 229 */       day.setAsnNo(plan.getXxqad_dailyplan_asn());
/* 230 */       day.setVersion(plan.getXxqad_dailyplan_rev());
/* 231 */       day.setCreatDate(plan.getXxqad_dailyplan_creatdate());
/* 232 */       day.setCreatTime(plan.getXxqad_dailyplan_creattime());
/* 233 */       day.setDailyDate(plan.getXxqad_dailydate());
/* 234 */       day.setCarkind(plan.getXxqad_dailyplan_carkind());
/* 235 */       day.setPlanRem(plan.getXxqad_dailyplan_rem());
/* 236 */       day.setPlanSum(plan.getXxqad_dailyplan_sum());
/* 237 */       day.setPlanWeitou(plan.getXxqad_dailyplan_weitou());
/* 238 */       day.setPlanLco(plan.getXxqad_dailyplan_lco());
/* 239 */       day.setPlanLcx(plan.getXxqad_dailyplan_lcx());
/* 240 */       day.setPlanAll(plan.getXxqad_dailyplan_all());
/* 241 */       day.setPlanM1(plan.getXxqad_dailyplan_M1());
/* 242 */       day.setPlanMouth(plan.getXxqad_dailyplan_mouth());
/* 243 */       day.setPlanD1(plan.getXxqad_dailyplan_D1());
/* 244 */       day.setPlanPbs(plan.getXxqad_dailyplan_pbs());
/* 245 */       day.setPlanPrj(plan.getXxqad_dailyplan_prj());
/* 246 */       day.setPlanWbs(plan.getXxqad_dailyplan_wbs());
/* 247 */       day.setPlanSum1(plan.getXxqad_dailyplan_sum1());
/* 248 */       day.setDay1(plan.getXxqad_dailyplan_day1());
/* 249 */       day.setDay2(plan.getXxqad_dailyplan_day2());
/* 250 */       day.setDay3(plan.getXxqad_dailyplan_day3());
/* 251 */       day.setDay4(plan.getXxqad_dailyplan_day4());
/* 252 */       day.setDay5(plan.getXxqad_dailyplan_day5());
/* 253 */       day.setDay6(plan.getXxqad_dailyplan_day6());
/* 254 */       day.setDay7(plan.getXxqad_dailyplan_day7());
/* 255 */       day.setDay8(plan.getXxqad_dailyplan_day8());
/* 256 */       day.setDay9(plan.getXxqad_dailyplan_day9());
/* 257 */       day.setDay10(plan.getXxqad_dailyplan_day10());
/* 258 */       day.setDay11(plan.getXxqad_dailyplan_day11());
/* 259 */       day.setDay12(plan.getXxqad_dailyplan_day12());
/* 260 */       day.setDay13(plan.getXxqad_dailyplan_day13());
/* 261 */       day.setDay14(plan.getXxqad_dailyplan_day14());
/* 262 */       day.setDay15(plan.getXxqad_dailyplan_day15());
/* 263 */       day.setDay16(plan.getXxqad_dailyplan_day16());
/* 264 */       day.setDay17(plan.getXxqad_dailyplan_day17());
/* 265 */       day.setDay18(plan.getXxqad_dailyplan_day18());
/* 266 */       day.setDay19(plan.getXxqad_dailyplan_day19());
/* 267 */       day.setDay20(plan.getXxqad_dailyplan_day20());
/* 268 */       day.setDay21(plan.getXxqad_dailyplan_day21());
/* 269 */       day.setDay22(plan.getXxqad_dailyplan_day22());
/* 270 */       day.setDay23(plan.getXxqad_dailyplan_day23());
/* 271 */       day.setDay24(plan.getXxqad_dailyplan_day24());
/* 272 */       day.setDay25(plan.getXxqad_dailyplan_day25());
/* 273 */       day.setDay26(plan.getXxqad_dailyplan_day26());
/* 274 */       day.setDay27(plan.getXxqad_dailyplan_day27());
/* 275 */       day.setDay28(plan.getXxqad_dailyplan_day28());
/* 276 */       day.setDay29(plan.getXxqad_dailyplan_day29());
/* 277 */       day.setDay30(plan.getXxqad_dailyplan_day30());
/* 278 */       day.setDay31(plan.getXxqad_dailyplan_day31());
/* 279 */       day.setDay32(plan.getXxqad_dailyplan_day32());
/* 280 */       day.setDay33(plan.getXxqad_dailyplan_day33());
/* 281 */       day.setDay34(plan.getXxqad_dailyplan_day34());
/* 282 */       day.setDay35(plan.getXxqad_dailyplan_day35());
/* 283 */       day.setDay36(plan.getXxqad_dailyplan_day36());
/* 284 */       day.setDay37(plan.getXxqad_dailyplan_day37());
/* 285 */       day.setDay38(plan.getXxqad_dailyplan_day38());
/* 286 */       day.setDay39(plan.getXxqad_dailyplan_day39());
/* 287 */       day.setDay40(plan.getXxqad_dailyplan_day40());
/* 288 */       day.setDay41(plan.getXxqad_dailyplan_day41());
/* 289 */       day.setDay42(plan.getXxqad_dailyplan_day42());
/* 290 */       day.setDay43(plan.getXxqad_dailyplan_day43());
/* 291 */       day.setDay44(plan.getXxqad_dailyplan_day44());
/* 292 */       day.setDay45(plan.getXxqad_dailyplan_day45());
/* 293 */       dao.saveObject(day);
/* 294 */       plan.setXxqad_dailyplan_portalread("1");
/* 295 */       daoShared.updateObject(plan);
/* 296 */       dao.commit();
/* 297 */       daoShared.commit();
/* 298 */       day = null;
/* 299 */     } catch (Exception e) {
/*     */       
/* 301 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void insertSystemLog(String action, String content, String sync_describe, String syncResults) {
/* 308 */     SyncLog log = new SyncLog();
/* 309 */     log.setSync_date(new Date());
/* 310 */     log.setSync_content(content);
/* 311 */     log.setSync_describe(sync_describe);
/* 312 */     log.setSync_object(action);
/* 313 */     log.setSync_results(syncResults);
/* 314 */     this.dao.saveObject(log);
/*     */   }
/*     */   
/*     */   public SynBaseDAO getDao() {
/* 318 */     return this.dao;
/*     */   }
/*     */   
/*     */   public void setDao(SynBaseDAO dao) {
/* 322 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public SyncDAO getDaoShared() {
/* 326 */     return this.daoShared;
/*     */   }
/*     */   
/*     */   public void setDaoShared(SyncDAO daoShared) {
/* 330 */     this.daoShared = daoShared;
/*     */   }
/*     */   
/*     */   public String getTime() {
/* 334 */     return this.time;
/*     */   }
/*     */   
/*     */   public void setTime(String time) {
/* 338 */     this.time = time;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/DailyPlanThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
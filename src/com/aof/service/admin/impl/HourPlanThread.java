/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import be.ibridge.kettle.core.exception.KettleException;
/*     */ import com.aof.dao.admin.SynBaseDAO;
/*     */ import com.aof.dao.sync.SyncDAO;
/*     */ import com.aof.model.basic.SycSleepTime;
/*     */ import com.aof.model.basic.SyncLog;
/*     */ import com.aof.model.schedule.Production72HourPlan;
/*     */ import com.aof.model.schedule.Production72HourPlanHistory;
/*     */ import com.aof.model.sync.shared.XxqadHourPlanDet;
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
/*     */ public class HourPlanThread
/*     */   extends Thread
/*     */ {
/*  34 */   private Log log = LogFactory.getLog(HourPlanThread.class);
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
/*     */   public HourPlanThread(SycSleepTimeManager sycSleepTimeManager, SynBaseDAO dao, SyncDAO daoShared, String time) {
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
/*  57 */         sycSleepTime = this.sycSleepTimeManager
/*  58 */           .getSycSleepTime("72小时预计需求报表信息");
/*  59 */         if (sycSleepTime != null) {
/*  60 */           this.time = String.valueOf(Integer.valueOf(sycSleepTime
/*  61 */                 .getSleepTime()).intValue() * 1000 * 60);
/*     */         }
/*  63 */         sleep(Long.parseLong(this.time));
/*  64 */         Date date = new Date();
/*  65 */         SimpleDateFormat format = new SimpleDateFormat(
/*  66 */             "yyyy/MM/dd hh:mm:ss");
/*  67 */         System.out.println("72小时预计需求报表信息同步-------------------------1-" + 
/*  68 */             format.format(date));
/*     */         
/*  70 */         List<String> hourList = this.daoShared
/*  71 */           .getObjectList("select  det.xxqad_hourplan_rev  from XxqadHourPlanDet det order by  det.xxqad_hourplan_rev desc ");
/*     */         try {
/*  73 */           xxqad72HourPlanDet(hourList);
/*  74 */         } catch (Exception e1) {
/*  75 */           e1.printStackTrace();
/*     */         } 
/*  77 */         System.out.println("-------------------- 72小时预计需求报表信息同步完成");
/*  78 */       } catch (InterruptedException e) {
/*  79 */         e.printStackTrace(); continue;
/*     */       } finally {
/*  81 */         System.gc();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void xxqad72HourPlanDet(List<String> sqlList) throws KettleException {
/*  92 */     if (sqlList != null && sqlList.size() > 0) {
/*  93 */       String sql = sqlList.get(0);
/*     */       try {
/*  95 */         List<XxqadHourPlanDet> syncXxqadHourPlanList = this.daoShared
/*  96 */           .getObjectList(" from XxqadHourPlanDet p  where  (p.xxqad_hourplan_portalread = 0 or p.xxqad_hourplan_portalread is null) and p.xxqad_hourplan_rev='" + 
/*  97 */             sql + "'  ");
/*     */         
/*  99 */         if (syncXxqadHourPlanList.size() != 0) {
/* 100 */           insertProduction72HourPlanHistory();
/*     */         }
/* 102 */         Boolean issyncok = Boolean.valueOf(true);
/* 103 */         for (XxqadHourPlanDet shared : syncXxqadHourPlanList) {
/*     */           try {
/* 105 */             insertXxqad72HourPlanDet(shared, this.dao, this.daoShared);
/*     */           
/*     */           }
/* 108 */           catch (Exception e) {
/* 109 */             insertSystemLog("RedMinuteSyncJob", 
/* 110 */                 "xxqad72HourPlanDetListSyncRead", 
/* 111 */                 e.getMessage(), "1");
/* 112 */             issyncok = Boolean.valueOf(false);
/*     */           } 
/*     */         } 
/* 115 */       } catch (Exception e) {
/* 116 */         insertSystemLog("RedMinuteSyncJob", 
/* 117 */             "xxqad72HourPlanDetListSyncRead", e.getMessage(), "1");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void insertProduction72HourPlanHistory() {
/* 123 */     String sqlPlan = " from Production72HourPlan  ";
/*     */     
/*     */     try {
/* 126 */       List<Production72HourPlan> planList = this.dao.getObjectList(sqlPlan);
/* 127 */       for (Production72HourPlan hourPlan : planList) {
/* 128 */         Production72HourPlanHistory history = new Production72HourPlanHistory();
/* 129 */         history.setVersion(hourPlan.getVersion());
/* 130 */         history.setCreatdate(hourPlan.getCreatdate());
/* 131 */         history.setCreattime(hourPlan.getCreattime());
/* 132 */         history.setCarkind(hourPlan.getCarkind());
/* 133 */         history.setAsn(hourPlan.getAsn());
/* 134 */         history.setM1(hourPlan.getM1());
/* 135 */         history.setMouth(hourPlan.getMouth());
/* 136 */         history.setD1(hourPlan.getD1());
/* 137 */         history.setPbs(hourPlan.getPbs());
/* 138 */         history.setPrj(hourPlan.getPrj());
/* 139 */         history.setWbs(hourPlan.getWbs());
/* 140 */         history.setSum1(hourPlan.getSum1());
/* 141 */         history.setHourplan_day1(hourPlan.getHourplan_day1());
/* 142 */         history.setHourplan_day2(hourPlan.getHourplan_day2());
/* 143 */         history.setHourplan_day3(hourPlan.getHourplan_day3());
/* 144 */         history.setHourplan_day4(hourPlan.getHourplan_day4());
/* 145 */         history.setHourplan_day5(hourPlan.getHourplan_day5());
/* 146 */         history.setHourplan_day6(hourPlan.getHourplan_day6());
/* 147 */         history.setHourplan_day7(hourPlan.getHourplan_day7());
/* 148 */         history.setHourplan_day8(hourPlan.getHourplan_day8());
/* 149 */         history.setHourplan_day9(hourPlan.getHourplan_day9());
/* 150 */         history.setHourplan_day10(hourPlan.getHourplan_day10());
/* 151 */         history.setHourplan_day11(hourPlan.getHourplan_day11());
/* 152 */         history.setHourplan_day12(hourPlan.getHourplan_day12());
/* 153 */         history.setHourplan_day13(hourPlan.getHourplan_day13());
/* 154 */         history.setHourplan_day14(hourPlan.getHourplan_day14());
/* 155 */         history.setHourplan_day15(hourPlan.getHourplan_day15());
/* 156 */         history.setHourplan_day16(hourPlan.getHourplan_day16());
/* 157 */         history.setHourplan_day17(hourPlan.getHourplan_day17());
/* 158 */         history.setHourplan_day18(hourPlan.getHourplan_day18());
/* 159 */         history.setHourplan_day19(hourPlan.getHourplan_day19());
/* 160 */         history.setHourplan_day20(hourPlan.getHourplan_day20());
/* 161 */         history.setHourplan_day21(hourPlan.getHourplan_day21());
/* 162 */         history.setHourplan_day22(hourPlan.getHourplan_day22());
/* 163 */         history.setHourplan_day23(hourPlan.getHourplan_day23());
/* 164 */         history.setHourplan_day24(hourPlan.getHourplan_day24());
/* 165 */         history.setHourplan_day25(hourPlan.getHourplan_day25());
/* 166 */         history.setHourplan_day26(hourPlan.getHourplan_day26());
/* 167 */         history.setHourplan_day27(hourPlan.getHourplan_day27());
/* 168 */         history.setHourplan_day28(hourPlan.getHourplan_day28());
/* 169 */         history.setHourplan_day29(hourPlan.getHourplan_day29());
/* 170 */         history.setHourplan_day30(hourPlan.getHourplan_day30());
/* 171 */         history.setHourplan_day31(hourPlan.getHourplan_day31());
/* 172 */         history.setHourplan_day32(hourPlan.getHourplan_day32());
/* 173 */         history.setHourplan_day33(hourPlan.getHourplan_day33());
/* 174 */         history.setHourplan_day34(hourPlan.getHourplan_day34());
/* 175 */         history.setHourplan_day35(hourPlan.getHourplan_day35());
/* 176 */         history.setHourplan_day36(hourPlan.getHourplan_day36());
/* 177 */         history.setHourplan_day37(hourPlan.getHourplan_day37());
/* 178 */         history.setHourplan_day38(hourPlan.getHourplan_day38());
/* 179 */         history.setHourplan_day39(hourPlan.getHourplan_day39());
/* 180 */         history.setHourplan_day40(hourPlan.getHourplan_day40());
/* 181 */         history.setHourplan_day41(hourPlan.getHourplan_day41());
/* 182 */         history.setHourplan_day42(hourPlan.getHourplan_day42());
/* 183 */         history.setHourplan_day43(hourPlan.getHourplan_day43());
/* 184 */         history.setHourplan_day44(hourPlan.getHourplan_day44());
/* 185 */         history.setHourplan_day45(hourPlan.getHourplan_day45());
/* 186 */         history.setHourplan_day46(hourPlan.getHourplan_day46());
/* 187 */         history.setHourplan_day47(hourPlan.getHourplan_day47());
/* 188 */         history.setHourplan_day48(hourPlan.getHourplan_day48());
/* 189 */         history.setHourplan_day49(hourPlan.getHourplan_day49());
/* 190 */         history.setHourplan_day50(hourPlan.getHourplan_day50());
/* 191 */         history.setHourplan_day51(hourPlan.getHourplan_day51());
/* 192 */         history.setHourplan_day52(hourPlan.getHourplan_day52());
/* 193 */         history.setHourplan_day53(hourPlan.getHourplan_day53());
/* 194 */         history.setHourplan_day54(hourPlan.getHourplan_day54());
/* 195 */         history.setHourplan_day55(hourPlan.getHourplan_day55());
/* 196 */         history.setHourplan_day56(hourPlan.getHourplan_day56());
/* 197 */         history.setHourplan_day57(hourPlan.getHourplan_day57());
/* 198 */         history.setHourplan_day58(hourPlan.getHourplan_day58());
/* 199 */         history.setHourplan_day59(hourPlan.getHourplan_day59());
/* 200 */         history.setHourplan_day60(hourPlan.getHourplan_day60());
/* 201 */         history.setHourplan_day61(hourPlan.getHourplan_day61());
/* 202 */         history.setHourplan_day62(hourPlan.getHourplan_day62());
/* 203 */         history.setHourplan_day63(hourPlan.getHourplan_day63());
/* 204 */         history.setHourplan_day64(hourPlan.getHourplan_day64());
/* 205 */         history.setHourplan_day65(hourPlan.getHourplan_day65());
/* 206 */         history.setRem(hourPlan.getRem());
/* 207 */         history.setSum(hourPlan.getSum());
/* 208 */         history.setWeitou(hourPlan.getWeitou());
/* 209 */         history.setLco(hourPlan.getLco());
/* 210 */         history.setLcx(hourPlan.getLcx());
/* 211 */         history.setHourAll(hourPlan.getHourAll());
/* 212 */         history.setXxqad_hourdate(hourPlan.getXxqad_hourdate());
/* 213 */         history.setRmks(hourPlan.getRmks());
/* 214 */         history.setCreatedt(hourPlan.getCreatedt());
/* 215 */         history.setCreateur(hourPlan.getCreateur());
/* 216 */         history.setUpdatedt(hourPlan.getUpdatedt());
/* 217 */         history.setUpdateur(hourPlan.getUpdateur());
/*     */         
/* 219 */         this.dao.saveObject(history);
/* 220 */         this.dao.removeObject(hourPlan);
/*     */       } 
/* 222 */     } catch (Exception e) {
/*     */       
/* 224 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void insertXxqad72HourPlanDet(XxqadHourPlanDet hourPlan, SynBaseDAO dao, SyncDAO daoShared) {
/*     */     try {
/* 232 */       Production72HourPlan hour = new Production72HourPlan();
/* 233 */       hour.setVersion(hourPlan.getXxqad_hourplan_rev());
/* 234 */       hour.setCreatdate(hourPlan.getXxqad_hourplan_creatdate());
/* 235 */       hour.setCreattime(hourPlan.getXxqad_hourplan_creattime());
/* 236 */       hour.setCarkind(hourPlan.getXxqad_hourplan_carkind());
/* 237 */       hour.setAsn(hourPlan.getXxqad_hourplan_asn());
/* 238 */       hour.setM1(hourPlan.getXxqad_hourplan_M1());
/* 239 */       hour.setMouth(hourPlan.getXxqad_hourplan_mouth());
/* 240 */       hour.setD1(hourPlan.getXxqad_hourplan_D1());
/* 241 */       hour.setPbs(hourPlan.getXxqad_hourplan_pbs());
/* 242 */       hour.setPrj(hourPlan.getXxqad_hourplan_prj());
/* 243 */       hour.setWbs(hourPlan.getXxqad_hourplan_wbs());
/* 244 */       hour.setSum1(hourPlan.getXxqad_hourplan_sum1());
/* 245 */       hour.setHourplan_day1(hourPlan.getXxqad_hourplan_day1());
/* 246 */       hour.setHourplan_day2(hourPlan.getXxqad_hourplan_day2());
/* 247 */       hour.setHourplan_day3(hourPlan.getXxqad_hourplan_day3());
/* 248 */       hour.setHourplan_day4(hourPlan.getXxqad_hourplan_day4());
/* 249 */       hour.setHourplan_day5(hourPlan.getXxqad_hourplan_day5());
/* 250 */       hour.setHourplan_day6(hourPlan.getXxqad_hourplan_day6());
/* 251 */       hour.setHourplan_day7(hourPlan.getXxqad_hourplan_day7());
/* 252 */       hour.setHourplan_day8(hourPlan.getXxqad_hourplan_day8());
/* 253 */       hour.setHourplan_day9(hourPlan.getXxqad_hourplan_day9());
/* 254 */       hour.setHourplan_day10(hourPlan.getXxqad_hourplan_day10());
/* 255 */       hour.setHourplan_day11(hourPlan.getXxqad_hourplan_day11());
/* 256 */       hour.setHourplan_day12(hourPlan.getXxqad_hourplan_day12());
/* 257 */       hour.setHourplan_day13(hourPlan.getXxqad_hourplan_day13());
/* 258 */       hour.setHourplan_day14(hourPlan.getXxqad_hourplan_day14());
/* 259 */       hour.setHourplan_day15(hourPlan.getXxqad_hourplan_day15());
/* 260 */       hour.setHourplan_day16(hourPlan.getXxqad_hourplan_day16());
/* 261 */       hour.setHourplan_day17(hourPlan.getXxqad_hourplan_day17());
/* 262 */       hour.setHourplan_day18(hourPlan.getXxqad_hourplan_day18());
/* 263 */       hour.setHourplan_day19(hourPlan.getXxqad_hourplan_day19());
/* 264 */       hour.setHourplan_day20(hourPlan.getXxqad_hourplan_day20());
/* 265 */       hour.setHourplan_day21(hourPlan.getXxqad_hourplan_day21());
/* 266 */       hour.setHourplan_day22(hourPlan.getXxqad_hourplan_day22());
/* 267 */       hour.setHourplan_day23(hourPlan.getXxqad_hourplan_day23());
/* 268 */       hour.setHourplan_day24(hourPlan.getXxqad_hourplan_day24());
/* 269 */       hour.setHourplan_day25(hourPlan.getXxqad_hourplan_day25());
/* 270 */       hour.setHourplan_day26(hourPlan.getXxqad_hourplan_day26());
/* 271 */       hour.setHourplan_day27(hourPlan.getXxqad_hourplan_day27());
/* 272 */       hour.setHourplan_day28(hourPlan.getXxqad_hourplan_day28());
/* 273 */       hour.setHourplan_day29(hourPlan.getXxqad_hourplan_day29());
/* 274 */       hour.setHourplan_day30(hourPlan.getXxqad_hourplan_day30());
/* 275 */       hour.setHourplan_day31(hourPlan.getXxqad_hourplan_day31());
/* 276 */       hour.setHourplan_day32(hourPlan.getXxqad_hourplan_day32());
/* 277 */       hour.setHourplan_day33(hourPlan.getXxqad_hourplan_day33());
/* 278 */       hour.setHourplan_day34(hourPlan.getXxqad_hourplan_day34());
/* 279 */       hour.setHourplan_day35(hourPlan.getXxqad_hourplan_day35());
/* 280 */       hour.setHourplan_day36(hourPlan.getXxqad_hourplan_day36());
/* 281 */       hour.setHourplan_day37(hourPlan.getXxqad_hourplan_day37());
/* 282 */       hour.setHourplan_day38(hourPlan.getXxqad_hourplan_day38());
/* 283 */       hour.setHourplan_day39(hourPlan.getXxqad_hourplan_day39());
/* 284 */       hour.setHourplan_day40(hourPlan.getXxqad_hourplan_day40());
/* 285 */       hour.setHourplan_day41(hourPlan.getXxqad_hourplan_day41());
/* 286 */       hour.setHourplan_day42(hourPlan.getXxqad_hourplan_day42());
/* 287 */       hour.setHourplan_day43(hourPlan.getXxqad_hourplan_day43());
/* 288 */       hour.setHourplan_day44(hourPlan.getXxqad_hourplan_day44());
/* 289 */       hour.setHourplan_day45(hourPlan.getXxqad_hourplan_day45());
/* 290 */       hour.setHourplan_day46(hourPlan.getXxqad_hourplan_day46());
/* 291 */       hour.setHourplan_day47(hourPlan.getXxqad_hourplan_day47());
/* 292 */       hour.setHourplan_day48(hourPlan.getXxqad_hourplan_day48());
/* 293 */       hour.setHourplan_day49(hourPlan.getXxqad_hourplan_day49());
/* 294 */       hour.setHourplan_day50(hourPlan.getXxqad_hourplan_day50());
/* 295 */       hour.setHourplan_day51(hourPlan.getXxqad_hourplan_day51());
/* 296 */       hour.setHourplan_day52(hourPlan.getXxqad_hourplan_day52());
/* 297 */       hour.setHourplan_day53(hourPlan.getXxqad_hourplan_day53());
/* 298 */       hour.setHourplan_day54(hourPlan.getXxqad_hourplan_day54());
/* 299 */       hour.setHourplan_day55(hourPlan.getXxqad_hourplan_day55());
/* 300 */       hour.setHourplan_day56(hourPlan.getXxqad_hourplan_day56());
/* 301 */       hour.setHourplan_day57(hourPlan.getXxqad_hourplan_day57());
/* 302 */       hour.setHourplan_day58(hourPlan.getXxqad_hourplan_day58());
/* 303 */       hour.setHourplan_day59(hourPlan.getXxqad_hourplan_day59());
/* 304 */       hour.setHourplan_day60(hourPlan.getXxqad_hourplan_day60());
/* 305 */       hour.setHourplan_day61(hourPlan.getXxqad_hourplan_day61());
/* 306 */       hour.setHourplan_day62(hourPlan.getXxqad_hourplan_day62());
/* 307 */       hour.setHourplan_day63(hourPlan.getXxqad_hourplan_day63());
/* 308 */       hour.setHourplan_day64(hourPlan.getXxqad_hourplan_day64());
/* 309 */       hour.setHourplan_day65(hourPlan.getXxqad_hourplan_day65());
/* 310 */       hour.setRem(hourPlan.getXxqad_hourplan_rem());
/* 311 */       hour.setSum(hourPlan.getXxqad_hourplan_sum());
/* 312 */       hour.setWeitou(hourPlan.getXxqad_hourplan_weitou());
/* 313 */       hour.setLco(hourPlan.getXxqad_hourplan_lco());
/* 314 */       hour.setLcx(hourPlan.getXxqad_hourplan_lcx());
/* 315 */       hour.setHourAll(hourPlan.getXxqad_hourplan_all());
/* 316 */       hour.setXxqad_hourdate(hourPlan.getXxqad_hourdate());
/* 317 */       hour.setRmks(hourPlan.getXxqad_hourplan_rmks());
/* 318 */       hour.setCreatedt(hourPlan.getXxqad_hourplan_createdt());
/* 319 */       hour.setCreateur(hourPlan.getXxqad_hourplan_createur());
/* 320 */       hour.setUpdatedt(hourPlan.getXxqad_hourplan_updatedt());
/* 321 */       hour.setUpdateur(hourPlan.getXxqad_hourplan_updateur());
/* 322 */       dao.saveObject(hour);
/* 323 */       hourPlan.setXxqad_hourplan_portalread("1");
/* 324 */       daoShared.updateObject(hourPlan);
/* 325 */       dao.commit();
/* 326 */       daoShared.commit();
/* 327 */       hour = null;
/* 328 */     } catch (Exception e) {
/*     */       
/* 330 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void insertSystemLog(String action, String content, String sync_describe, String syncResults) {
/* 337 */     SyncLog log = new SyncLog();
/* 338 */     log.setSync_date(new Date());
/* 339 */     log.setSync_content(content);
/* 340 */     log.setSync_describe(sync_describe);
/* 341 */     log.setSync_object(action);
/* 342 */     log.setSync_results(syncResults);
/* 343 */     this.dao.saveObject(log);
/*     */   }
/*     */   
/*     */   public SynBaseDAO getDao() {
/* 347 */     return this.dao;
/*     */   }
/*     */   
/*     */   public void setDao(SynBaseDAO dao) {
/* 351 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public SyncDAO getDaoShared() {
/* 355 */     return this.daoShared;
/*     */   }
/*     */   
/*     */   public void setDaoShared(SyncDAO daoShared) {
/* 359 */     this.daoShared = daoShared;
/*     */   }
/*     */   
/*     */   public String getTime() {
/* 363 */     return this.time;
/*     */   }
/*     */   
/*     */   public void setTime(String time) {
/* 367 */     this.time = time;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/HourPlanThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
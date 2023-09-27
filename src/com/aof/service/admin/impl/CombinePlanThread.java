/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.basic.WmsPartDAO;
/*     */ import com.aof.model.basic.SycSleepTime;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.schedule.JitProductionPlan;
/*     */ import com.aof.model.schedule.JitProductionPlanHistory;
/*     */ import com.aof.model.schedule.ProjectedInventory;
/*     */ import com.aof.model.schedule.query.JitProductionPlanQueryCondition;
/*     */ import com.aof.model.schedule.query.ProjectedInventoryQueryCondition;
/*     */ import com.aof.service.basic.SycSleepTimeManager;
/*     */ import com.aof.service.schedule.JitProductionPlanHistoryManager;
/*     */ import com.aof.service.schedule.JitProductionPlanManager;
/*     */ import com.aof.service.schedule.ProjectedInventoryManager;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CombinePlanThread
/*     */   extends Thread
/*     */ {
/*     */   private JitProductionPlanManager jitProductionPlanManager;
/*     */   private WmsPartDAO wmsPartdao;
/*     */   private JitProductionPlanHistoryManager jitProductionPlanHistoryManager;
/*     */   private ProjectedInventoryManager projectedInventoryManager;
/*     */   private SycSleepTimeManager sycSleepTimeManager;
/*     */   private String time;
/*     */   
/*     */   public SycSleepTimeManager getSycSleepTimeManager() {
/*  54 */     return this.sycSleepTimeManager;
/*     */   }
/*     */   
/*     */   public void setSycSleepTimeManager(SycSleepTimeManager sycSleepTimeManager) {
/*  58 */     this.sycSleepTimeManager = sycSleepTimeManager;
/*     */   }
/*     */   
/*     */   public JitProductionPlanManager getJitProductionPlanManager() {
/*  62 */     return this.jitProductionPlanManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJitProductionPlanManager(JitProductionPlanManager jitProductionPlanManager) {
/*  67 */     this.jitProductionPlanManager = jitProductionPlanManager;
/*     */   }
/*     */   
/*     */   public CombinePlanThread(SycSleepTimeManager sycSleepTimeManager, String time, JitProductionPlanManager jitProductionPlanManager, WmsPartDAO wmsPartdao, JitProductionPlanHistoryManager jitProductionPlanHistoryManager, ProjectedInventoryManager projectedInventoryManager) {
/*  71 */     this.sycSleepTimeManager = sycSleepTimeManager;
/*  72 */     this.time = time;
/*  73 */     this.jitProductionPlanManager = jitProductionPlanManager;
/*  74 */     this.wmsPartdao = wmsPartdao;
/*  75 */     this.jitProductionPlanHistoryManager = jitProductionPlanHistoryManager;
/*  76 */     this.projectedInventoryManager = projectedInventoryManager;
/*     */   }
/*     */   
/*     */   public void run() {
/*     */     while (true) {
/*     */       try {
/*  82 */         SycSleepTime sycSleepTime = new SycSleepTime();
/*  83 */         sycSleepTime = this.sycSleepTimeManager.getSycSleepTime("Jit物料计划自动合并");
/*  84 */         if (sycSleepTime != null) {
/*  85 */           this.time = String.valueOf(Integer.valueOf(sycSleepTime.getSleepTime()).intValue() * 1000 * 60);
/*     */         }
/*  87 */         sleep(Long.parseLong(this.time));
/*  88 */         Date date = new Date();
/*  89 */         SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
/*  90 */         System.out.println("Jit物料计划自动合并开始-------------------------1-" + format.format(date));
/*     */         
/*     */         try {
/*  93 */           combinePlan();
/*  94 */         } catch (Exception e1) {
/*  95 */           e1.printStackTrace();
/*     */         } 
/*  97 */         System.out.println("-------------------- Jit物料计划自动合并完成");
/*  98 */       } catch (InterruptedException e) {
/*  99 */         e.printStackTrace(); continue;
/*     */       } finally {
/* 101 */         System.gc();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void combinePlan() {
/* 107 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 108 */     conditions.put(JitProductionPlanQueryCondition.PRODUCTCLASS_EQ, "JIT");
/* 109 */     List<JitProductionPlan> planList = this.jitProductionPlanManager.getList(conditions, 0, -1, null, false);
/*     */     try {
/* 111 */       Map<String, JitProductionPlan> map = new LinkedHashMap<String, JitProductionPlan>();
/* 112 */       JitProductionPlanHistory history = null;
/* 113 */       BigDecimal big = null;
/* 114 */       JitProductionPlan plan = null;
/* 115 */       for (JitProductionPlan jitProductionPlan1 : planList) {
/* 116 */         String date = String.valueOf(jitProductionPlan1.getChildPart().getId()) + jitProductionPlan1.getDate().toString();
/* 117 */         if (map.containsKey(date)) {
/*     */           
/* 119 */           JitProductionPlan jit = map.get(date);
/* 120 */           WmsPart part = this.wmsPartdao.getWmsPart(jitProductionPlan1.getChildPart().getId());
/* 121 */           jit.setChildPart(part);
/* 122 */           jit.setDate(jitProductionPlan1.getDate());
/* 123 */           if (jitProductionPlan1.getQty() != null) {
/* 124 */             big = new BigDecimal(0);
/* 125 */             if (jit.getQty() == null) {
/* 126 */               jit.setQty(big.add(jitProductionPlan1.getQty()));
/*     */             } else {
/* 128 */               jit.setQty(jit.getQty().add(jitProductionPlan1.getQty()));
/*     */             } 
/*     */           } 
/* 131 */           if (jitProductionPlan1.getHour2DemandQty() != null) {
/* 132 */             big = new BigDecimal(0);
/* 133 */             if (jit.getHour2DemandQty() == null) {
/* 134 */               jit.setHour2DemandQty(big.add(jitProductionPlan1.getHour2DemandQty()));
/*     */             } else {
/* 136 */               jit.setHour2DemandQty(jit.getHour2DemandQty().add(jitProductionPlan1.getHour2DemandQty()));
/*     */             } 
/*     */           } 
/* 139 */           if (jitProductionPlan1.getHour4DemandQty() != null) {
/* 140 */             big = new BigDecimal(0);
/* 141 */             if (jit.getHour4DemandQty() == null) {
/* 142 */               jit.setHour4DemandQty(big.add(jitProductionPlan1.getHour4DemandQty()));
/*     */             } else {
/* 144 */               jit.setHour4DemandQty(jit.getHour4DemandQty().add(jitProductionPlan1.getHour4DemandQty()));
/*     */             } 
/*     */           } 
/* 147 */           if (jitProductionPlan1.getHour6DemandQty() != null) {
/* 148 */             big = new BigDecimal(0);
/* 149 */             if (jit.getHour6DemandQty() == null) {
/* 150 */               jit.setHour6DemandQty(big.add(jitProductionPlan1.getHour6DemandQty()));
/*     */             } else {
/* 152 */               jit.setHour6DemandQty(jit.getHour6DemandQty().add(jitProductionPlan1.getHour6DemandQty()));
/*     */             } 
/*     */           } 
/* 155 */           if (jitProductionPlan1.getHour8DemandQty() != null) {
/* 156 */             big = new BigDecimal(0);
/* 157 */             if (jit.getHour8DemandQty() == null) {
/* 158 */               jit.setHour8DemandQty(big.add(jitProductionPlan1.getHour8DemandQty()));
/*     */             } else {
/* 160 */               jit.setHour8DemandQty(jit.getHour8DemandQty().add(jitProductionPlan1.getHour8DemandQty()));
/*     */             } 
/*     */           } 
/* 163 */           if (jitProductionPlan1.getHour10DemandQty() != null) {
/* 164 */             big = new BigDecimal(0);
/* 165 */             if (jit.getHour10DemandQty() == null) {
/* 166 */               jit.setHour10DemandQty(big.add(jitProductionPlan1.getHour10DemandQty()));
/*     */             } else {
/* 168 */               jit.setHour10DemandQty(jit.getHour10DemandQty().add(jitProductionPlan1.getHour10DemandQty()));
/*     */             } 
/*     */           } 
/* 171 */           if (jitProductionPlan1.getHour12DemandQty() != null) {
/* 172 */             big = new BigDecimal(0);
/* 173 */             if (jit.getHour12DemandQty() == null) {
/* 174 */               jit.setHour12DemandQty(big.add(jitProductionPlan1.getHour12DemandQty()));
/*     */             } else {
/* 176 */               jit.setHour12DemandQty(jit.getHour12DemandQty().add(jitProductionPlan1.getHour12DemandQty()));
/*     */             } 
/*     */           } 
/*     */           
/* 180 */           if (jitProductionPlan1.getHour14DemandQty() != null) {
/* 181 */             big = new BigDecimal(0);
/* 182 */             if (jit.getHour14DemandQty() == null) {
/* 183 */               jit.setHour14DemandQty(big.add(jitProductionPlan1.getHour14DemandQty()));
/*     */             } else {
/* 185 */               jit.setHour14DemandQty(jit.getHour14DemandQty().add(jitProductionPlan1.getHour14DemandQty()));
/*     */             } 
/*     */           } 
/* 188 */           if (jitProductionPlan1.getHour16DemandQty() != null) {
/* 189 */             big = new BigDecimal(0);
/* 190 */             if (jit.getHour16DemandQty() == null) {
/* 191 */               jit.setHour16DemandQty(big.add(jitProductionPlan1.getHour16DemandQty()));
/*     */             } else {
/* 193 */               jit.setHour16DemandQty(jit.getHour16DemandQty().add(jitProductionPlan1.getHour16DemandQty()));
/*     */             } 
/*     */           } 
/* 196 */           if (jitProductionPlan1.getHour18DemandQty() != null) {
/* 197 */             big = new BigDecimal(0);
/* 198 */             if (jit.getHour18DemandQty() == null) {
/* 199 */               jit.setHour18DemandQty(big.add(jitProductionPlan1.getHour18DemandQty()));
/*     */             } else {
/* 201 */               jit.setHour18DemandQty(jit.getHour18DemandQty().add(jitProductionPlan1.getHour18DemandQty()));
/*     */             } 
/*     */           } 
/* 204 */           if (jitProductionPlan1.getHour20DemandQty() != null) {
/* 205 */             big = new BigDecimal(0);
/* 206 */             if (jit.getHour20DemandQty() == null) {
/* 207 */               jit.setHour20DemandQty(big.add(jitProductionPlan1.getHour20DemandQty()));
/*     */             } else {
/* 209 */               jit.setHour20DemandQty(jit.getHour20DemandQty().add(jitProductionPlan1.getHour20DemandQty()));
/*     */             } 
/*     */           } 
/* 212 */           if (jitProductionPlan1.getHour22DemandQty() != null) {
/* 213 */             big = new BigDecimal(0);
/* 214 */             if (jit.getHour22DemandQty() == null) {
/* 215 */               jit.setHour22DemandQty(big.add(jitProductionPlan1.getHour22DemandQty()));
/*     */             } else {
/* 217 */               jit.setHour22DemandQty(jit.getHour22DemandQty().add(jitProductionPlan1.getHour22DemandQty()));
/*     */             } 
/*     */           } 
/* 220 */           if (jitProductionPlan1.getHour24DemandQty() != null) {
/* 221 */             big = new BigDecimal(0);
/* 222 */             if (jit.getHour24DemandQty() == null) {
/* 223 */               jit.setHour24DemandQty(big.add(jitProductionPlan1.getHour24DemandQty()));
/*     */             } else {
/* 225 */               jit.setHour24DemandQty(jit.getHour24DemandQty().add(jitProductionPlan1.getHour24DemandQty()));
/*     */             } 
/*     */           } 
/* 228 */           map.put(date, jit);
/*     */         } else {
/* 230 */           plan = new JitProductionPlan();
/* 231 */           plan.setProductionId(jitProductionPlan1.getProductionId());
/* 232 */           plan.setFatherPart(jitProductionPlan1.getFatherPart());
/* 233 */           plan.setChildPart(jitProductionPlan1.getChildPart());
/* 234 */           plan.setQty(jitProductionPlan1.getQty());
/* 235 */           plan.setType(jitProductionPlan1.getType());
/* 236 */           plan.setStatus(jitProductionPlan1.getStatus());
/* 237 */           plan.setCurrentQty(jitProductionPlan1.getCurrentQty());
/* 238 */           plan.setHour2DemandQty(jitProductionPlan1.getHour2DemandQty());
/* 239 */           plan.setHour4DemandQty(jitProductionPlan1.getHour4DemandQty());
/* 240 */           plan.setHour6DemandQty(jitProductionPlan1.getHour6DemandQty());
/* 241 */           plan.setHour8DemandQty(jitProductionPlan1.getHour8DemandQty());
/* 242 */           plan.setHour10DemandQty(jitProductionPlan1.getHour10DemandQty());
/* 243 */           plan.setHour12DemandQty(jitProductionPlan1.getHour12DemandQty());
/* 244 */           plan.setHour14DemandQty(jitProductionPlan1.getHour14DemandQty());
/* 245 */           plan.setHour16DemandQty(jitProductionPlan1.getHour16DemandQty());
/* 246 */           plan.setHour18DemandQty(jitProductionPlan1.getHour18DemandQty());
/* 247 */           plan.setHour20DemandQty(jitProductionPlan1.getHour20DemandQty());
/* 248 */           plan.setHour22DemandQty(jitProductionPlan1.getHour22DemandQty());
/* 249 */           plan.setHour24DemandQty(jitProductionPlan1.getHour24DemandQty());
/* 250 */           plan.setDate(jitProductionPlan1.getDate());
/* 251 */           map.put(date, plan);
/*     */         } 
/*     */         
/* 254 */         history = new JitProductionPlanHistory();
/* 255 */         history.setProductionId(jitProductionPlan1.getProductionId());
/* 256 */         history.setFatherPart(jitProductionPlan1.getFatherPart());
/* 257 */         history.setChildPart(jitProductionPlan1.getChildPart());
/* 258 */         history.setQty(jitProductionPlan1.getQty());
/* 259 */         history.setType(jitProductionPlan1.getType());
/* 260 */         history.setStatus(Integer.valueOf(2));
/* 261 */         history.setCurrentQty(jitProductionPlan1.getCurrentQty());
/* 262 */         history.setHour2DemandQty(jitProductionPlan1.getHour2DemandQty());
/* 263 */         history.setHour4DemandQty(jitProductionPlan1.getHour4DemandQty());
/* 264 */         history.setHour6DemandQty(jitProductionPlan1.getHour6DemandQty());
/* 265 */         history.setHour8DemandQty(jitProductionPlan1.getHour8DemandQty());
/* 266 */         history.setHour10DemandQty(jitProductionPlan1.getHour10DemandQty());
/* 267 */         history.setHour12DemandQty(jitProductionPlan1.getHour12DemandQty());
/* 268 */         history.setHour14DemandQty(jitProductionPlan1.getHour14DemandQty());
/* 269 */         history.setHour16DemandQty(jitProductionPlan1.getHour16DemandQty());
/* 270 */         history.setHour18DemandQty(jitProductionPlan1.getHour18DemandQty());
/* 271 */         history.setHour20DemandQty(jitProductionPlan1.getHour20DemandQty());
/* 272 */         history.setHour22DemandQty(jitProductionPlan1.getHour22DemandQty());
/* 273 */         history.setHour24DemandQty(jitProductionPlan1.getHour24DemandQty());
/* 274 */         history.setDate(jitProductionPlan1.getDate());
/* 275 */         history.setProduction_plan_id(jitProductionPlan1);
/* 276 */         this.jitProductionPlanHistoryManager.save(history);
/*     */       } 
/* 278 */       JitProductionPlan p = null;
/* 279 */       for (String partId : map.keySet()) {
/* 280 */         p = new JitProductionPlan();
/* 281 */         p = map.get(partId);
/* 282 */         Map<Object, Object> currentList = new HashMap<Object, Object>();
/* 283 */         currentList.put(ProjectedInventoryQueryCondition.PART_ID_EQ, p.getChildPart().getId());
/* 284 */         List<ProjectedInventory> toryList = this.projectedInventoryManager.getList(currentList, 0, -1, null, false);
/* 285 */         if (toryList.size() == 0) {
/* 286 */           p.setCurrentQty(null);
/*     */         } else {
/* 288 */           p.setCurrentQty(((ProjectedInventory)toryList.get(0)).getCurrentQty());
/*     */         } 
/* 290 */         p.setProductionId(null);
/* 291 */         p.setStatus(Integer.valueOf(1));
/* 292 */         this.jitProductionPlanManager.save(p);
/*     */       } 
/*     */ 
/*     */       
/* 296 */       JitProductionPlan jitProductionPlan = null;
/* 297 */       for (int i = 0; i < planList.size(); i++) {
/* 298 */         jitProductionPlan = planList.get(i);
/* 299 */         this.jitProductionPlanManager.delete(jitProductionPlan);
/*     */       } 
/* 301 */       p = null;
/* 302 */       map = null;
/* 303 */       big = null;
/* 304 */       history = null;
/* 305 */       plan = null;
/* 306 */       jitProductionPlan = null;
/* 307 */       planList = null;
/* 308 */     } catch (Exception e) {
/* 309 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public JitProductionPlanHistoryManager getJitProductionPlanHistoryManager() {
/* 314 */     return this.jitProductionPlanHistoryManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJitProductionPlanHistoryManager(JitProductionPlanHistoryManager jitProductionPlanHistoryManager) {
/* 319 */     this.jitProductionPlanHistoryManager = jitProductionPlanHistoryManager;
/*     */   }
/*     */   
/*     */   public ProjectedInventoryManager getProjectedInventoryManager() {
/* 323 */     return this.projectedInventoryManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProjectedInventoryManager(ProjectedInventoryManager projectedInventoryManager) {
/* 328 */     this.projectedInventoryManager = projectedInventoryManager;
/*     */   }
/*     */   
/*     */   public WmsPartDAO getWmsPartdao() {
/* 332 */     return this.wmsPartdao;
/*     */   }
/*     */   
/*     */   public void setWmsPartdao(WmsPartDAO wmsPartdao) {
/* 336 */     this.wmsPartdao = wmsPartdao;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/CombinePlanThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
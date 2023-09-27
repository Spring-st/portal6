/*     */ package com.aof.service.schedule.impl;
/*     */ 
/*     */ import com.aof.dao.basic.SupplierPartDAO;
/*     */ import com.aof.dao.basic.WmsPartDAO;
/*     */ import com.aof.dao.po.PurchaseOrderInspectionPendingDAO;
/*     */ import com.aof.dao.schedule.JitProductionPlanDao;
/*     */ import com.aof.dao.schedule.JitProductionPlanHistoryDao;
/*     */ import com.aof.dao.schedule.ProjectedInventoryDao;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.schedule.EdiProduction;
/*     */ import com.aof.model.schedule.JitProductionPlan;
/*     */ import com.aof.model.schedule.JitProductionPlanHistory;
/*     */ import com.aof.model.schedule.ProjectedInventory;
/*     */ import com.aof.model.schedule.query.JitProductionPlanQueryOrder;
/*     */ import com.aof.model.schedule.query.ProjectedInventoryQueryCondition;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.schedule.JitProductionPlanManager;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.DecimalFormat;
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
/*     */ public class JitProductionPlanManagerImpl
/*     */   extends BaseManager
/*     */   implements JitProductionPlanManager
/*     */ {
/*     */   private JitProductionPlanDao dao;
/*     */   private WmsPartDAO wmsPartdao;
/*     */   private SupplierPartDAO supplierPartdao;
/*     */   private PurchaseOrderInspectionPendingDAO purchaseOrderInspectionPendingdao;
/*     */   private JitProductionPlanHistoryDao jitProductionPlanHistoryDao;
/*     */   private ProjectedInventoryDao projectedInventoryDao;
/*     */   
/*     */   public JitProductionPlan save(JitProductionPlan entity) {
/*  50 */     return this.dao.save(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public JitProductionPlan getJitProductionPlan(Integer id) {
/*  55 */     return this.dao.getJitProductionPlan(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public void delete(JitProductionPlan entity) {
/*  60 */     this.dao.delete(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public JitProductionPlan update(JitProductionPlan entity) {
/*  65 */     return this.dao.update(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer getListCount(Map conditions) {
/*  70 */     return this.dao.getListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<JitProductionPlan> getList(Map conditions, int pageNum, int pageSize, JitProductionPlanQueryOrder order, boolean descend) {
/*  76 */     return this.dao.getList(conditions, pageNum, pageSize, order, descend);
/*     */   }
/*     */   
/*     */   public void setDao(JitProductionPlanDao dao) {
/*  80 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public JitProductionPlanDao getDao() {
/*  84 */     return this.dao;
/*     */   }
/*     */   
/*     */   public void DismantlingBom(EdiProduction ediProduction, WmsPart part, BigDecimal qty) {
/*  88 */     List<JitProductionPlan> jitProductionPlanList = this.dao.DismantlingBom(ediProduction, part, qty);
/*  89 */     insertJitProductionPlan(jitProductionPlanList);
/*     */   }
/*     */   
/*     */   public void insertJitProductionPlan(List<JitProductionPlan> jitProductionPlanList) {
/*  93 */     for (JitProductionPlan jitProductionPlan : jitProductionPlanList) {
/*  94 */       if (jitProductionPlan.getSign().intValue() == 0) {
/*  95 */         this.dao.save(jitProductionPlan); continue;
/*     */       } 
/*  97 */       insertJitProductionPlan(jitProductionPlan.getJitProductionPlanList());
/*     */     } 
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void combinePlan(List<JitProductionPlan> planList) {
/*     */     try {
/* 116 */       Map<String, JitProductionPlan> map = new LinkedHashMap<String, JitProductionPlan>();
/* 117 */       JitProductionPlanHistory history = null;
/* 118 */       for (JitProductionPlan jitProductionPlan : planList) {
/* 119 */         String date = String.valueOf(jitProductionPlan.getChildPart().getId()) + jitProductionPlan.getDate().toString();
/* 120 */         if (map.containsKey(date)) {
/*     */           
/* 122 */           JitProductionPlan jit = map.get(date);
/* 123 */           WmsPart part = this.wmsPartdao.getWmsPart(jitProductionPlan.getChildPart().getId());
/* 124 */           jit.setChildPart(part);
/* 125 */           jit.setDate(jitProductionPlan.getDate());
/* 126 */           if (jitProductionPlan.getQty() != null) {
/* 127 */             BigDecimal big = new BigDecimal(0);
/* 128 */             if (jit.getQty() == null) {
/* 129 */               jit.setQty(big.add(jitProductionPlan.getQty()));
/*     */             } else {
/* 131 */               jit.setQty(jit.getQty().add(jitProductionPlan.getQty()));
/*     */             } 
/*     */           } 
/* 134 */           if (jitProductionPlan.getHour2DemandQty() != null) {
/* 135 */             BigDecimal big = new BigDecimal(0);
/* 136 */             if (jit.getHour2DemandQty() == null) {
/* 137 */               jit.setHour2DemandQty(big.add(jitProductionPlan.getHour2DemandQty()));
/*     */             } else {
/* 139 */               jit.setHour2DemandQty(jit.getHour2DemandQty().add(jitProductionPlan.getHour2DemandQty()));
/*     */             } 
/*     */           } 
/* 142 */           if (jitProductionPlan.getHour4DemandQty() != null) {
/* 143 */             BigDecimal big = new BigDecimal(0);
/* 144 */             if (jit.getHour4DemandQty() == null) {
/* 145 */               jit.setHour4DemandQty(big.add(jitProductionPlan.getHour4DemandQty()));
/*     */             } else {
/* 147 */               jit.setHour4DemandQty(jit.getHour4DemandQty().add(jitProductionPlan.getHour4DemandQty()));
/*     */             } 
/*     */           } 
/* 150 */           if (jitProductionPlan.getHour6DemandQty() != null) {
/* 151 */             BigDecimal big = new BigDecimal(0);
/* 152 */             if (jit.getHour6DemandQty() == null) {
/* 153 */               jit.setHour6DemandQty(big.add(jitProductionPlan.getHour6DemandQty()));
/*     */             } else {
/* 155 */               jit.setHour6DemandQty(jit.getHour6DemandQty().add(jitProductionPlan.getHour6DemandQty()));
/*     */             } 
/*     */           } 
/* 158 */           if (jitProductionPlan.getHour8DemandQty() != null) {
/* 159 */             BigDecimal big = new BigDecimal(0);
/* 160 */             if (jit.getHour8DemandQty() == null) {
/* 161 */               jit.setHour8DemandQty(big.add(jitProductionPlan.getHour8DemandQty()));
/*     */             } else {
/* 163 */               jit.setHour8DemandQty(jit.getHour8DemandQty().add(jitProductionPlan.getHour8DemandQty()));
/*     */             } 
/*     */           } 
/* 166 */           if (jitProductionPlan.getHour10DemandQty() != null) {
/* 167 */             BigDecimal big = new BigDecimal(0);
/* 168 */             if (jit.getHour10DemandQty() == null) {
/* 169 */               jit.setHour10DemandQty(big.add(jitProductionPlan.getHour10DemandQty()));
/*     */             } else {
/* 171 */               jit.setHour10DemandQty(jit.getHour10DemandQty().add(jitProductionPlan.getHour10DemandQty()));
/*     */             } 
/*     */           } 
/* 174 */           if (jitProductionPlan.getHour12DemandQty() != null) {
/* 175 */             BigDecimal big = new BigDecimal(0);
/* 176 */             if (jit.getHour12DemandQty() == null) {
/* 177 */               jit.setHour12DemandQty(big.add(jitProductionPlan.getHour12DemandQty()));
/*     */             } else {
/* 179 */               jit.setHour12DemandQty(jit.getHour12DemandQty().add(jitProductionPlan.getHour12DemandQty()));
/*     */             } 
/*     */           } 
/*     */           
/* 183 */           if (jitProductionPlan.getHour14DemandQty() != null) {
/* 184 */             BigDecimal big = new BigDecimal(0);
/* 185 */             if (jit.getHour14DemandQty() == null) {
/* 186 */               jit.setHour14DemandQty(big.add(jitProductionPlan.getHour14DemandQty()));
/*     */             } else {
/* 188 */               jit.setHour14DemandQty(jit.getHour14DemandQty().add(jitProductionPlan.getHour14DemandQty()));
/*     */             } 
/*     */           } 
/* 191 */           if (jitProductionPlan.getHour16DemandQty() != null) {
/* 192 */             BigDecimal big = new BigDecimal(0);
/* 193 */             if (jit.getHour16DemandQty() == null) {
/* 194 */               jit.setHour16DemandQty(big.add(jitProductionPlan.getHour16DemandQty()));
/*     */             } else {
/* 196 */               jit.setHour16DemandQty(jit.getHour16DemandQty().add(jitProductionPlan.getHour16DemandQty()));
/*     */             } 
/*     */           } 
/* 199 */           if (jitProductionPlan.getHour18DemandQty() != null) {
/* 200 */             BigDecimal big = new BigDecimal(0);
/* 201 */             if (jit.getHour18DemandQty() == null) {
/* 202 */               jit.setHour18DemandQty(big.add(jitProductionPlan.getHour18DemandQty()));
/*     */             } else {
/* 204 */               jit.setHour18DemandQty(jit.getHour18DemandQty().add(jitProductionPlan.getHour18DemandQty()));
/*     */             } 
/*     */           } 
/* 207 */           if (jitProductionPlan.getHour20DemandQty() != null) {
/* 208 */             BigDecimal big = new BigDecimal(0);
/* 209 */             if (jit.getHour20DemandQty() == null) {
/* 210 */               jit.setHour20DemandQty(big.add(jitProductionPlan.getHour20DemandQty()));
/*     */             } else {
/* 212 */               jit.setHour20DemandQty(jit.getHour20DemandQty().add(jitProductionPlan.getHour20DemandQty()));
/*     */             } 
/*     */           } 
/* 215 */           if (jitProductionPlan.getHour22DemandQty() != null) {
/* 216 */             BigDecimal big = new BigDecimal(0);
/* 217 */             if (jit.getHour22DemandQty() == null) {
/* 218 */               jit.setHour22DemandQty(big.add(jitProductionPlan.getHour22DemandQty()));
/*     */             } else {
/* 220 */               jit.setHour22DemandQty(jit.getHour22DemandQty().add(jitProductionPlan.getHour22DemandQty()));
/*     */             } 
/*     */           } 
/* 223 */           if (jitProductionPlan.getHour24DemandQty() != null) {
/* 224 */             BigDecimal big = new BigDecimal(0);
/* 225 */             if (jit.getHour24DemandQty() == null) {
/* 226 */               jit.setHour24DemandQty(big.add(jitProductionPlan.getHour24DemandQty()));
/*     */             } else {
/* 228 */               jit.setHour24DemandQty(jit.getHour24DemandQty().add(jitProductionPlan.getHour24DemandQty()));
/*     */             } 
/*     */           } 
/* 231 */           map.put(date, jit);
/*     */         } else {
/* 233 */           JitProductionPlan plan = new JitProductionPlan();
/* 234 */           plan.setProductionId(jitProductionPlan.getProductionId());
/* 235 */           plan.setFatherPart(jitProductionPlan.getFatherPart());
/* 236 */           plan.setChildPart(jitProductionPlan.getChildPart());
/* 237 */           plan.setQty(jitProductionPlan.getQty());
/* 238 */           plan.setType(jitProductionPlan.getType());
/* 239 */           plan.setStatus(jitProductionPlan.getStatus());
/* 240 */           plan.setCurrentQty(jitProductionPlan.getCurrentQty());
/* 241 */           plan.setHour2DemandQty(jitProductionPlan.getHour2DemandQty());
/* 242 */           plan.setHour4DemandQty(jitProductionPlan.getHour4DemandQty());
/* 243 */           plan.setHour6DemandQty(jitProductionPlan.getHour6DemandQty());
/* 244 */           plan.setHour8DemandQty(jitProductionPlan.getHour8DemandQty());
/* 245 */           plan.setHour10DemandQty(jitProductionPlan.getHour10DemandQty());
/* 246 */           plan.setHour12DemandQty(jitProductionPlan.getHour12DemandQty());
/* 247 */           plan.setHour14DemandQty(jitProductionPlan.getHour14DemandQty());
/* 248 */           plan.setHour16DemandQty(jitProductionPlan.getHour16DemandQty());
/* 249 */           plan.setHour18DemandQty(jitProductionPlan.getHour18DemandQty());
/* 250 */           plan.setHour20DemandQty(jitProductionPlan.getHour20DemandQty());
/* 251 */           plan.setHour22DemandQty(jitProductionPlan.getHour22DemandQty());
/* 252 */           plan.setHour24DemandQty(jitProductionPlan.getHour24DemandQty());
/* 253 */           plan.setDate(jitProductionPlan.getDate());
/* 254 */           map.put(date, plan);
/*     */         } 
/*     */         
/* 257 */         history = new JitProductionPlanHistory();
/* 258 */         history.setProductionId(jitProductionPlan.getProductionId());
/* 259 */         history.setFatherPart(jitProductionPlan.getFatherPart());
/* 260 */         history.setChildPart(jitProductionPlan.getChildPart());
/* 261 */         history.setQty(jitProductionPlan.getQty());
/* 262 */         history.setType(jitProductionPlan.getType());
/* 263 */         history.setStatus(Integer.valueOf(2));
/* 264 */         history.setCurrentQty(jitProductionPlan.getCurrentQty());
/* 265 */         history.setHour2DemandQty(jitProductionPlan.getHour2DemandQty());
/* 266 */         history.setHour4DemandQty(jitProductionPlan.getHour4DemandQty());
/* 267 */         history.setHour6DemandQty(jitProductionPlan.getHour6DemandQty());
/* 268 */         history.setHour8DemandQty(jitProductionPlan.getHour8DemandQty());
/* 269 */         history.setHour10DemandQty(jitProductionPlan.getHour10DemandQty());
/* 270 */         history.setHour12DemandQty(jitProductionPlan.getHour12DemandQty());
/* 271 */         history.setHour14DemandQty(jitProductionPlan.getHour14DemandQty());
/* 272 */         history.setHour16DemandQty(jitProductionPlan.getHour16DemandQty());
/* 273 */         history.setHour18DemandQty(jitProductionPlan.getHour18DemandQty());
/* 274 */         history.setHour20DemandQty(jitProductionPlan.getHour20DemandQty());
/* 275 */         history.setHour22DemandQty(jitProductionPlan.getHour22DemandQty());
/* 276 */         history.setHour24DemandQty(jitProductionPlan.getHour24DemandQty());
/* 277 */         history.setDate(jitProductionPlan.getDate());
/* 278 */         history.setProduction_plan_id(jitProductionPlan);
/* 279 */         this.jitProductionPlanHistoryDao.save(history);
/*     */       } 
/* 281 */       for (String partId : map.keySet()) {
/* 282 */         JitProductionPlan p = new JitProductionPlan();
/* 283 */         p = map.get(partId);
/* 284 */         Map<Object, Object> currentList = new HashMap<Object, Object>();
/* 285 */         currentList.put(ProjectedInventoryQueryCondition.PART_ID_EQ, p.getChildPart().getId());
/* 286 */         List<ProjectedInventory> toryList = this.projectedInventoryDao.getList(currentList, 0, -1, null, false);
/* 287 */         if (toryList.size() == 0) {
/* 288 */           p.setCurrentQty(null);
/*     */         } else {
/* 290 */           p.setCurrentQty(((ProjectedInventory)toryList.get(0)).getCurrentQty());
/*     */         } 
/* 292 */         p.setProductionId(null);
/* 293 */         p.setStatus(Integer.valueOf(1));
/* 294 */         save(p);
/*     */       } 
/*     */ 
/*     */       
/* 298 */       for (int i = 0; i < planList.size(); i++) {
/* 299 */         JitProductionPlan plan = planList.get(i);
/* 300 */         delete(plan);
/*     */       } 
/* 302 */     } catch (Exception e) {
/*     */       
/* 304 */       e.printStackTrace();
/*     */     } 
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
/*     */ 
/*     */ 
/*     */   
/*     */   private String getMaxPurchaseOrderInspectionPending() {
/* 343 */     StringBuffer sb = new StringBuffer((new SimpleDateFormat("yyMMdd")).format(new Date()));
/* 344 */     List<String> soMaxList = this.dao.getObjectList("select max(po.poip_number) from PurchaseOrderInspectionPending po where po.poip_number like '" + 
/* 345 */         sb.toString() + "%'");
/* 346 */     Integer serialNumber = Integer.valueOf(0);
/* 347 */     if (soMaxList != null && soMaxList.size() != 0 && soMaxList.get(0) != null && !((String)soMaxList.get(0)).equals("null") && !((String)soMaxList.get(0)).equals("NULL")) {
/* 348 */       String soMax = soMaxList.get(0);
/* 349 */       serialNumber = Integer.valueOf(Integer.parseInt(soMax.substring(soMax.length() - 4, soMax.length())));
/*     */     } 
/* 351 */     DecimalFormat df = new DecimalFormat("0000");
/* 352 */     String serialNumbers = df.format((serialNumber.intValue() + 1));
/* 353 */     sb.append(serialNumbers);
/* 354 */     return sb.toString();
/*     */   }
/*     */   public WmsPartDAO getWmsPartdao() {
/* 357 */     return this.wmsPartdao;
/*     */   }
/*     */   
/*     */   public void setWmsPartdao(WmsPartDAO wmsPartdao) {
/* 361 */     this.wmsPartdao = wmsPartdao;
/*     */   }
/*     */   
/*     */   public SupplierPartDAO getSupplierPartdao() {
/* 365 */     return this.supplierPartdao;
/*     */   }
/*     */   
/*     */   public void setSupplierPartdao(SupplierPartDAO supplierPartdao) {
/* 369 */     this.supplierPartdao = supplierPartdao;
/*     */   }
/*     */   
/*     */   public PurchaseOrderInspectionPendingDAO getPurchaseOrderInspectionPendingdao() {
/* 373 */     return this.purchaseOrderInspectionPendingdao;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPurchaseOrderInspectionPendingdao(PurchaseOrderInspectionPendingDAO purchaseOrderInspectionPendingdao) {
/* 378 */     this.purchaseOrderInspectionPendingdao = purchaseOrderInspectionPendingdao;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Map> computeCombinePlan(String str) {
/* 383 */     return this.dao.computeCombinePlan(str);
/*     */   }
/*     */   
/*     */   public JitProductionPlanHistoryDao getJitProductionPlanHistoryDao() {
/* 387 */     return this.jitProductionPlanHistoryDao;
/*     */   }
/*     */   
/*     */   public void setJitProductionPlanHistoryDao(JitProductionPlanHistoryDao jitProductionPlanHistoryDao) {
/* 391 */     this.jitProductionPlanHistoryDao = jitProductionPlanHistoryDao;
/*     */   }
/*     */   
/*     */   public ProjectedInventoryDao getProjectedInventoryDao() {
/* 395 */     return this.projectedInventoryDao;
/*     */   }
/*     */   
/*     */   public void setProjectedInventoryDao(ProjectedInventoryDao projectedInventoryDao) {
/* 399 */     this.projectedInventoryDao = projectedInventoryDao;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, BigDecimal> getPartNeedQtyByBom(EdiProduction ediProduction, WmsPart part, BigDecimal qty) {
/* 404 */     return this.dao.getPartNeedQtyByBom(ediProduction, part, qty);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/schedule/impl/JitProductionPlanManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.aof.service.basic.impl;
/*     */ 
/*     */ import com.aof.dao.basic.ReportEntersSellsSavesDAO;
/*     */ import com.aof.model.basic.ReportEntersSellsSaves;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.basic.query.ReportEntersSellsSavesQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.basic.ReportEntersSellsSavesManager;
/*     */ import com.aof.service.basic.WmsPartManager;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ReportEntersSellsSavesManagerImpl
/*     */   extends BaseManager
/*     */   implements ReportEntersSellsSavesManager
/*     */ {
/*     */   private ReportEntersSellsSavesDAO dao;
/*     */   private WmsPartManager wmsPartManager;
/*     */   
/*     */   public void setWmsPartManager(WmsPartManager wmsPartManager) {
/*  25 */     this.wmsPartManager = wmsPartManager;
/*     */   }
/*     */   
/*     */   public void setDao(ReportEntersSellsSavesDAO dao) {
/*  29 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   
/*     */   public ReportEntersSellsSaves getReportEntersSellsSaves(Integer id) {
/*  34 */     return this.dao.getReportEntersSellsSaves(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getReportEntersSellsSavesListCount(Map conditions) {
/*  39 */     return this.dao.getReportEntersSellsSavesListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getReportEntersSellsSavesList(Map conditions, int pageNo, int pageSize, ReportEntersSellsSavesQueryOrder order, boolean descend) {
/*  46 */     return this.dao.getReportEntersSellsSavesList(conditions, pageNo, pageSize, 
/*  47 */         order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ReportEntersSellsSaves insertReportEntersSellsSaves(ReportEntersSellsSaves city) {
/*  53 */     return this.dao.insertReportEntersSellsSaves(city);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ReportEntersSellsSaves updateReportEntersSellsSaves(ReportEntersSellsSaves city) {
/*  59 */     return this.dao.updateReportEntersSellsSaves(city);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getEnabledReportEntersSellsSavesList() {
/*  64 */     return this.dao.getEnabledReportEntersSellsSavesList();
/*     */   }
/*     */ 
/*     */   
/*     */   public ReportEntersSellsSaves getReportEntersSellsSaves(String id) {
/*  69 */     return this.dao.getReportEntersSellsSaves(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean insertReportEntersSellsSaves(String mouth) {
/*  76 */     Date start_date = new Date();
/*  77 */     start_date.setDate(1);
/*  78 */     start_date.setMonth(Integer.parseInt(mouth) - 1);
/*     */     
/*  80 */     Date end_date = new Date();
/*  81 */     end_date.setMonth(Integer.parseInt(mouth) - 1);
/*  82 */     Calendar cal = Calendar.getInstance();
/*  83 */     cal.setTime(end_date);
/*     */     while (true) {
/*  85 */       cal.add(5, 1);
/*     */       
/*  87 */       if (cal.get(5) == 1) {
/*  88 */         cal.add(5, -1);
/*  89 */         end_date = cal.getTime();
/*     */         
/*  91 */         SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
/*     */ 
/*     */         
/*  94 */         List<WmsPart> parts = this.wmsPartManager.getEnabledWmsPartList();
/*     */ 
/*     */         
/*  97 */         BigDecimal initial_qty = new BigDecimal(0);
/*     */         
/*  99 */         BigDecimal balance_qty = new BigDecimal(0);
/*     */         
/* 101 */         BigDecimal sumIn = new BigDecimal(0);
/* 102 */         BigDecimal sumOut = new BigDecimal(0);
/*     */         
/* 104 */         for (WmsPart part : parts) {
/*     */ 
/*     */           
/* 107 */           initial_qty = new BigDecimal(0);
/*     */           
/* 109 */           balance_qty = new BigDecimal(0);
/*     */           
/* 111 */           sumIn = new BigDecimal(0);
/* 112 */           sumOut = new BigDecimal(0);
/*     */ 
/*     */           
/* 115 */           String sqlInit = "from ReportEntersSellsSaves res where res.part.id='" + part.getId() + "' and res.month='" + mouth + "' order by res.end_date desc ";
/* 116 */           List<ReportEntersSellsSaves> list = this.dao.getObjectList(sqlInit);
/*     */           
/* 118 */           String sqlbalanceIn = "select sum(ir.qty) from InventoryRecord ir where ir.part='" + part.getId() + "' and ir.date >= '" + format.format(start_date) + "' and ir.date <= '" + format.format(end_date) + "' and ir.type=1 ";
/* 119 */           String sqlbalanceOut = "select sum(ir.qty) from InventoryRecord ir where ir.part='" + part.getId() + "' and ir.date >= '" + format.format(start_date) + "' and ir.date <= '" + format.format(end_date) + "' and ir.type=2 ";
/*     */           
/* 121 */           List<Object> in = this.dao.getObjectList(sqlbalanceIn);
/* 122 */           if (in.size() > 0 && in.get(0) != null) {
/* 123 */             sumIn = (BigDecimal)in.get(0);
/*     */           }
/*     */           
/* 126 */           List<Object> out = this.dao.getObjectList(sqlbalanceOut);
/* 127 */           if (out.size() > 0 && out.get(0) != null) {
/* 128 */             sumOut = (BigDecimal)in.get(0);
/*     */           }
/*     */ 
/*     */           
/* 132 */           boolean sign = true;
/* 133 */           if (list.size() > 0) {
/*     */             
/* 135 */             ReportEntersSellsSaves sellsSaves = list.get(0);
/* 136 */             Integer oldmouth = sellsSaves.getMonth();
/* 137 */             if (oldmouth.toString().equals(mouth)) {
/* 138 */               sign = false;
/* 139 */               String sqlInit2 = "from ReportEntersSellsSaves res where res.part.id='" + part.getId() + "' and res.month = '" + mouth + "' and res.start_date < '" + format.format(start_date) + "' order by res.end_date desc ";
/* 140 */               List<ReportEntersSellsSaves> list2 = this.dao.getObjectList(sqlInit2);
/* 141 */               if (list2.size() > 0 && list2.get(0) != null) {
/* 142 */                 initial_qty = ((ReportEntersSellsSaves)list2.get(0)).getBalance_qty();
/*     */               }
/*     */             } else {
/* 145 */               initial_qty = sellsSaves.getBalance_qty();
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 150 */           balance_qty = sumIn.subtract(sumOut).add(initial_qty);
/*     */           
/* 152 */           if (sign) {
/* 153 */             insertRes(initial_qty, part, balance_qty, mouth, start_date, end_date); continue;
/*     */           } 
/* 155 */           updateRes(initial_qty, part, balance_qty, mouth, start_date, end_date, list.get(0));
/*     */         } 
/*     */ 
/*     */         
/* 159 */         return false;
/*     */       } 
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
/*     */   private ReportEntersSellsSaves insertRes(BigDecimal initial_qty, WmsPart part, BigDecimal balance_qty, String mouth, Date start_date, Date end_date) {
/* 174 */     ReportEntersSellsSaves saves = new ReportEntersSellsSaves();
/* 175 */     saves.setInitial_qty(initial_qty);
/* 176 */     saves.setPart(part);
/* 177 */     saves.setStart_date(start_date);
/* 178 */     saves.setEnd_date(end_date);
/* 179 */     saves.setBalance_qty(balance_qty);
/* 180 */     saves.setMonth(Integer.valueOf(Integer.parseInt(mouth)));
/*     */     
/* 182 */     this.dao.insertReportEntersSellsSaves(saves);
/* 183 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ReportEntersSellsSaves updateRes(BigDecimal initial_qty, WmsPart part, BigDecimal balance_qty, String mouth, Date start_date, Date end_date, ReportEntersSellsSaves saves) {
/* 189 */     saves.setInitial_qty(initial_qty);
/* 190 */     saves.setPart(part);
/* 191 */     saves.setStart_date(start_date);
/* 192 */     saves.setEnd_date(end_date);
/* 193 */     saves.setBalance_qty(balance_qty);
/* 194 */     saves.setMonth(Integer.valueOf(Integer.parseInt(mouth)));
/*     */     
/* 196 */     this.dao.updateObject(saves);
/* 197 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/impl/ReportEntersSellsSavesManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
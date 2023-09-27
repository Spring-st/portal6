/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import be.ibridge.kettle.core.exception.KettleException;
/*     */ import com.aof.dao.admin.SynBaseDAO;
/*     */ import com.aof.dao.sync.SyncDAO;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.basic.SycSleepTime;
/*     */ import com.aof.model.basic.SyncLog;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.PurchaseOrderStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.PurchaseOrderInspectionPending;
/*     */ import com.aof.model.po.PurchaseOrderInspectionPendingItem;
/*     */ import com.aof.model.sync.shared.QADCtrl;
/*     */ import com.aof.model.sync.shared.QADPurchaseOrder;
/*     */ import com.aof.service.basic.SycSleepTimeManager;
/*     */ import java.math.BigDecimal;
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
/*     */ public class PoThread
/*     */   extends Thread
/*     */ {
/*  41 */   private Log log = LogFactory.getLog(PoThread.class);
/*     */   
/*     */   private SynBaseDAO dao;
/*     */   
/*     */   private SyncDAO daoShared;
/*     */   
/*     */   private String time;
/*     */   
/*     */   private SycSleepTimeManager sycSleepTimeManager;
/*     */ 
/*     */   
/*     */   public PoThread(SycSleepTimeManager sycSleepTimeManager, SynBaseDAO dao, SyncDAO daoShared, String time) {
/*  53 */     this.sycSleepTimeManager = sycSleepTimeManager;
/*  54 */     this.dao = dao;
/*  55 */     this.time = time;
/*  56 */     this.daoShared = daoShared;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     while (true) {
/*     */       try {
/*  63 */         SycSleepTime sycSleepTime = new SycSleepTime();
/*  64 */         sycSleepTime = this.sycSleepTimeManager.getSycSleepTime("采购单信息");
/*  65 */         if (sycSleepTime != null) {
/*  66 */           this.time = String.valueOf(Integer.valueOf(sycSleepTime
/*  67 */                 .getSleepTime()).intValue() * 1000 * 60);
/*     */         }
/*  69 */         sleep(Long.parseLong(this.time));
/*  70 */         Date date = new Date();
/*  71 */         SimpleDateFormat format = new SimpleDateFormat(
/*  72 */             "yyyy/MM/dd hh:mm:ss");
/*  73 */         System.out.println("采购单信息同步-------------------------1-" + 
/*  74 */             format.format(date));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  79 */         String beginSql = "select ctrl.xxqad_seq from QADCtrl ctrl  where ctrl.xxqad_portal=0 and ctrl.xxqad_table_qty > 0";
/*     */ 
/*     */         
/*  82 */         List<String> poList = this.daoShared
/*  83 */           .getObjectList(String.valueOf(beginSql) + 
/*  84 */             " and  ctrl.xxqad_table='xxqad_pod_det' group by ctrl.xxqad_seq");
/*     */         
/*     */         try {
/*  87 */           poSyncRead(poList);
/*  88 */         } catch (KettleException e1) {
/*     */           
/*  90 */           e1.printStackTrace();
/*     */         } 
/*  92 */         System.out.println("--------------------采购单信息同步完成");
/*  93 */       } catch (InterruptedException e) {
/*  94 */         e.printStackTrace(); continue;
/*     */       } finally {
/*  96 */         System.gc();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void poSyncRead(List<String> sqlList) throws KettleException {
/* 104 */     for (String sql : sqlList) {
/*     */       try {
/* 106 */         List<QADPurchaseOrder> syncPartList = this.daoShared
/* 107 */           .getObjectList("from QADPurchaseOrder po where po.xxqad_pod_seq='" + 
/* 108 */             sql + "' and po.xxqad_pod_portalread=0");
/* 109 */         Boolean issyncok = Boolean.valueOf(true);
/* 110 */         for (QADPurchaseOrder shared : syncPartList) {
/*     */           try {
/* 112 */             insertPo(shared, this.dao, this.daoShared);
/* 113 */           } catch (Exception e) {
/* 114 */             insertSystemLog("QADPurchaseOrder", "poSyncRead", 
/* 115 */                 e.getMessage(), "1");
/* 116 */             issyncok = Boolean.valueOf(false);
/*     */           } 
/*     */         } 
/* 119 */         syncPartList = null;
/* 120 */         if (issyncok.booleanValue()) {
/*     */           
/* 122 */           List<QADCtrl> ctrlList = this.daoShared
/* 123 */             .getObjectList("from QADCtrl ctrl where ctrl.xxqad_seq='" + 
/* 124 */               sql + "' and ctrl.xxqad_portal=0");
/* 125 */           for (QADCtrl ctrl : ctrlList) {
/* 126 */             ctrl.setXxqad_portal("1");
/* 127 */             this.daoShared.updateObject(ctrl);
/*     */           } 
/*     */         } 
/* 130 */       } catch (Exception e) {
/* 131 */         insertSystemLog("RedMinuteSyncJob", "poSyncRead", 
/* 132 */             e.getMessage(), "1");
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
/*     */   protected void insertPo(QADPurchaseOrder po, SynBaseDAO dao, SyncDAO daoShared) {
/* 167 */     List<Supplier> supplierList = dao
/* 168 */       .getObjectList("from Supplier supplier where supplier.code='" + 
/* 169 */         po.getXxqad_pod_vend() + "'");
/* 170 */     if (supplierList == null || supplierList.size() != 1)
/*     */     {
/* 172 */       po.setXxqad_pod_rmks("supplier.inexistence:" + po.getXxqad_pod_vend());
/*     */     }
/*     */     
/* 175 */     WmsPart wmsPart = (WmsPart)dao
/* 176 */       .getObject(WmsPart.class, po.getXxqad_pod_part());
/*     */ 
/*     */     
/* 179 */     if (wmsPart != null) {
/* 180 */       String sql = "from PurchaseOrderInspectionPending po where po.po_number='" + 
/* 181 */         po.getXxqad_pod_nbr() + "' ";
/* 182 */       List<PurchaseOrderInspectionPending> list = dao.getObjectList(sql);
/* 183 */       PurchaseOrderInspectionPending pending = null;
/* 184 */       if (list.size() == 0) {
/* 185 */         pending = new PurchaseOrderInspectionPending();
/* 186 */         pending.setPoip_number(po.getXxqad_pod_nbr());
/* 187 */         pending.setCreateDate(new Date());
/* 188 */         pending.setCreateType(Integer.valueOf(2));
/* 189 */         pending.setIsPrint(YesNo.YES);
/* 190 */         pending.setPo_number(po.getXxqad_pod_nbr());
/* 191 */         pending.setSite(new Site(Integer.valueOf(2)));
/* 192 */         pending.setSupplier(supplierList.get(0));
/* 193 */         pending.setBuyer(po.getXxqad_pod_buyer());
/* 194 */         pending.setBuyer_phone(po.getXxqad_pod_buyer_phone());
/*     */         
/* 196 */         if (po.getXxqad_pod_status().equals("1")) {
/* 197 */           pending.setStatus(PurchaseOrderStatus.CLOSE);
/* 198 */         } else if (po.getXxqad_pod_status().equals("0")) {
/* 199 */           pending.setStatus(PurchaseOrderStatus.WAIT);
/*     */         } 
/*     */         
/* 202 */         dao.saveObject(pending);
/*     */       } else {
/* 204 */         pending = list.get(0);
/*     */       } 
/*     */ 
/*     */       
/* 208 */       List<PurchaseOrderInspectionPendingItem> itemList = dao
/* 209 */         .getObjectList("from PurchaseOrderInspectionPendingItem item where item.line='" + 
/* 210 */           po.getXxqad_pod_line() + 
/* 211 */           "' and item.itemNumber.id='" + 
/* 212 */           po.getXxqad_pod_part() + 
/* 213 */           "' and item.poip_number.id='" + 
/* 214 */           pending.getId() + "'");
/*     */ 
/*     */       
/* 217 */       if (itemList.size() == 0) {
/* 218 */         PurchaseOrderInspectionPendingItem item = new PurchaseOrderInspectionPendingItem();
/* 219 */         item.setPoip_number(pending);
/* 220 */         item.setLine(po.getXxqad_pod_line().toString());
/* 221 */         item.setItemNumber(wmsPart);
/* 222 */         item.setQty(po.getXxqad_pod_qty_ord());
/* 223 */         item.setQtyOpen(po.getXxqad_pod_qty_ord());
/* 224 */         item.setDueDate(po.getXxqad_pod_due_date());
/* 225 */         item.setUm(po.getXxqad_pod_loc_um());
/* 226 */         item.setRum(po.getXxqad_pod_um());
/* 227 */         item.setConversion_ratio(po.getXxqad_pod_um_conv());
/* 228 */         item.setAd_attn(po.getXxqad_pod_attn());
/* 229 */         item.setPo_ship(po.getXxqad_pod_ship());
/* 230 */         item.setVd_promo(po.getXxqad_pod_made());
/* 231 */         item.setFactory(po.getXxqad_pod_site());
/* 232 */         if (po.getXxqad_pod_confirm() != null && po.getXxqad_pod_confirm().equals("")) {
/* 233 */           item.setPo_confirm(Integer.getInteger(po.getXxqad_pod_confirm()));
/*     */         }
/* 235 */         item.setPo_domain(po.getXxqad_pod_domain());
/* 236 */         item.setReceiptQty(new BigDecimal(0));
/* 237 */         item.setQty_std(po.getXxqad_pod_qty_std());
/* 238 */         item.setCapacity(po.getXxqad_pod_qty_std());
/* 239 */         item.setIsViewItem(YesNo.NO);
/* 240 */         item.setIsPrintLabels(YesNo.NO);
/* 241 */         item.setIsReceiving(YesNo.NO);
/* 242 */         item.setIsIqc(YesNo.NO);
/* 243 */         item.setReceiptQty(new BigDecimal(0));
/* 244 */         item.setInventoryNumber(new BigDecimal(0));
/*     */         
/* 246 */         dao.saveObject(item);
/*     */       }
/* 248 */       else if (po.getXxqad_pod_status().equals("1")) {
/* 249 */         PurchaseOrderInspectionPendingItem item = itemList.get(0);
/* 250 */         if (item.getQtyOpen().compareTo(item.getQty()) == 0) {
/* 251 */           dao.removeObject(item);
/*     */         }
/*     */       } else {
/* 254 */         PurchaseOrderInspectionPendingItem item = itemList.get(0);
/* 255 */         Boolean isupdate = Boolean.valueOf(true);
/* 256 */         if (po.getXxqad_pod_qty_ord().doubleValue() < item.getQty()
/* 257 */           .doubleValue()) {
/* 258 */           if (item.getQty().doubleValue() - po.getXxqad_pod_qty_ord()
/* 259 */             .doubleValue() < item.getQty().doubleValue() - item
/* 260 */             .getQtyOpen().doubleValue())
/*     */           {
/* 262 */             isupdate = Boolean.valueOf(false);
/*     */           }
/*     */         }
/* 265 */         if (isupdate.booleanValue()) {
/*     */           
/* 267 */           BigDecimal overQty = item.getQty().subtract(
/* 268 */               item.getQtyOpen());
/* 269 */           item.setQty(po.getXxqad_pod_qty_ord());
/* 270 */           if (po.getXxqad_pod_qty_ord().compareTo(item.getQty()) == 1) {
/* 271 */             item.setQtyOpen(po.getXxqad_pod_qty_ord().subtract(overQty));
/*     */           } else {
/* 273 */             item.setQtyOpen(po.getXxqad_pod_qty_ord().subtract(overQty));
/*     */           } 
/* 275 */           item.setConversion_ratio(po.getXxqad_pod_um_conv());
/* 276 */           item.setQty_std(po.getXxqad_pod_qty_std());
/* 277 */           dao.updateObject(item);
/* 278 */           item.getPoip_number().setStatus(
/* 279 */               PurchaseOrderStatus.WAIT);
/* 280 */           dao.updateObject(item.getPoip_number());
/*     */         } 
/*     */       } 
/*     */       
/* 284 */       po.setXxqad_pod_portalread("1");
/* 285 */       daoShared.updateObject(po);
/* 286 */       dao.commit();
/* 287 */       daoShared.commit();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void insertSystemLog(String action, String content, String sync_describe, String syncResults) {
/* 294 */     SyncLog log = new SyncLog();
/* 295 */     log.setSync_date(new Date());
/* 296 */     log.setSync_content(content);
/* 297 */     log.setSync_describe(sync_describe);
/* 298 */     log.setSync_object(action);
/* 299 */     log.setSync_results(syncResults);
/* 300 */     this.dao.saveObject(log);
/*     */   }
/*     */   
/*     */   public SynBaseDAO getDao() {
/* 304 */     return this.dao;
/*     */   }
/*     */   
/*     */   public void setDao(SynBaseDAO dao) {
/* 308 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public SyncDAO getDaoShared() {
/* 312 */     return this.daoShared;
/*     */   }
/*     */   
/*     */   public void setDaoShared(SyncDAO daoShared) {
/* 316 */     this.daoShared = daoShared;
/*     */   }
/*     */   
/*     */   public String getTime() {
/* 320 */     return this.time;
/*     */   }
/*     */   
/*     */   public void setTime(String time) {
/* 324 */     this.time = time;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/PoThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
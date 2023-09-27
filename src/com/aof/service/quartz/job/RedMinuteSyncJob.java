/*      */ package com.aof.service.quartz.job;
/*      */ 
/*      */ import be.ibridge.kettle.core.exception.KettleException;
/*      */ import com.aof.dao.admin.SynBaseDAO;
/*      */ import com.aof.dao.schedule.JitProductionPlanDao;
/*      */ import com.aof.dao.sync.SyncDAO;
/*      */ import com.aof.model.basic.SyncLog;
/*      */ import com.aof.model.basic.WmsPart;
/*      */ import com.aof.model.comprehensive.Bom;
/*      */ import com.aof.model.metadata.EnabledDisabled;
/*      */ import com.aof.model.po.PortalShipOrder;
/*      */ import com.aof.model.po.PortalShipOrderItem;
/*      */ import com.aof.model.product.ProductGoline;
/*      */ import com.aof.model.schedule.EdiProduction;
/*      */ import com.aof.model.schedule.EdiProductionErrorLog;
/*      */ import com.aof.model.schedule.JitProductionPlan;
/*      */ import com.aof.model.schedule.NjitNpoPlan;
/*      */ import com.aof.model.schedule.Njitnpoplanhistory;
/*      */ import com.aof.model.schedule.Production72HourPlan;
/*      */ import com.aof.model.schedule.Production72HourPlanHistory;
/*      */ import com.aof.model.schedule.ProductionDayPlan;
/*      */ import com.aof.model.schedule.ProductionDayPlanHistory;
/*      */ import com.aof.model.schedule.QadOrEdi;
/*      */ import com.aof.model.sync.shared.MesSeatType;
/*      */ import com.aof.model.sync.shared.ProductOutGoline;
/*      */ import com.aof.model.sync.shared.QADAdMstr;
/*      */ import com.aof.model.sync.shared.QADBom;
/*      */ import com.aof.model.sync.shared.QADBomInfo;
/*      */ import com.aof.model.sync.shared.QADCtrl;
/*      */ import com.aof.model.sync.shared.QADCustomerWmsPart;
/*      */ import com.aof.model.sync.shared.QADDailyProductionSchedule;
/*      */ import com.aof.model.sync.shared.QADPcMstr;
/*      */ import com.aof.model.sync.shared.QADPurchaseOrder;
/*      */ import com.aof.model.sync.shared.QADStorage;
/*      */ import com.aof.model.sync.shared.QADSupplier;
/*      */ import com.aof.model.sync.shared.QADSupplierWmsPart;
/*      */ import com.aof.model.sync.shared.QADWmsPart;
/*      */ import com.aof.model.sync.shared.XbmwoDet;
/*      */ import com.aof.model.sync.shared.XbqtyDet;
/*      */ import com.aof.model.sync.shared.XxqadDailyPlanDet;
/*      */ import com.aof.model.sync.shared.XxqadHourPlanDet;
/*      */ import com.aof.model.sync.shared.XxqadMrpDet;
/*      */ import com.aof.model.sync.shared.XxqadXxptMstr;
/*      */ import com.aof.model.sync.shared.Xxqadidddet;
/*      */ import java.math.BigDecimal;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RedMinuteSyncJob
/*      */   extends BasicRed
/*      */ {
/*      */   private SynBaseDAO dao;
/*      */   private SyncDAO daoShared;
/*   61 */   private String siteDomainCode = "YA01";
/*      */   
/*      */   public void setDao(SynBaseDAO dao) {
/*   64 */     this.dao = dao;
/*      */   }
/*      */   private JitProductionPlanDao jitProductionPlanDao;
/*      */   public void setDaoShared(SyncDAO daoShared) {
/*   68 */     this.daoShared = daoShared;
/*      */   }
/*      */   
/*      */   public void setJitProductionPlanDao(JitProductionPlanDao jitProductionPlanDao) {
/*   72 */     this.jitProductionPlanDao = jitProductionPlanDao;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void test() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTypeWhere(String site, String domain) {
/*   86 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   public void startSyn() {
/*      */     try {
/*   92 */       insertSystemLog("RedMinuteSyncJob", "startSync", "", "0");
/*      */       
/*   94 */       Date date = new Date();
/*   95 */       SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
/*   96 */       System.out.println("partList-------------------------1-" + format.format(date));
/*      */ 
/*      */ 
/*      */       
/*  100 */       String beginSql = "select ctrl.xxqad_seq from QADCtrl ctrl  where ctrl.xxqad_portal=0 and ctrl.xxqad_table_qty > 0";
/*      */       
/*  102 */       List<String> partList = this.daoShared.getObjectList(String.valueOf(beginSql) + 
/*  103 */           " and  ctrl.xxqad_table='xxqad_pt_mstr' group by ctrl.xxqad_seq");
/*      */       
/*  105 */       wmsPartSyncRead(partList);
/*  106 */       System.out.println("--------------------partList");
/*      */ 
/*      */       
/*  109 */       List<String> suppList = this.daoShared.getObjectList(String.valueOf(beginSql) + 
/*  110 */           " and  ctrl.xxqad_table='xxqad_vd_mstr' group by ctrl.xxqad_seq");
/*  111 */       supplierSyncRead(suppList);
/*  112 */       System.out.println("--------------------suppList");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  127 */       List<String> poList = this.daoShared.getObjectList(String.valueOf(beginSql) + 
/*  128 */           " and  ctrl.xxqad_table='xxqad_pod_det' group by ctrl.xxqad_seq");
/*  129 */       poSyncRead(poList);
/*  130 */       System.out.println("--------------------poList");
/*      */ 
/*      */       
/*  133 */       List<String> bomList = this.daoShared.getObjectList(String.valueOf(beginSql) + 
/*  134 */           " and  ctrl.xxqad_table='xxqad_ps_mstr' group by ctrl.xxqad_seq");
/*  135 */       bomSyncRead(bomList);
/*  136 */       System.out.println("--------------------bomList");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  175 */       List<String> xbmwoDetList = this.daoShared.getObjectList(String.valueOf(beginSql) + 
/*  176 */           " and  ctrl.xxqad_table='xbmwo_det' group by ctrl.xxqad_seq");
/*  177 */       xbmwoDetListSyncRead(xbmwoDetList);
/*  178 */       System.out.println("-------------------- xbmwoDetList");
/*      */ 
/*      */       
/*  181 */       List<String> revList = this.daoShared.getObjectList("select  det.xxqad_dailyplan_rev  from XxqadDailyPlanDet det order by  det.xxqad_dailyplan_rev desc ");
/*  182 */       xxqadDailyPlanDet(revList);
/*  183 */       System.out.println("-------------------- xxqadDailyPlanDetList");
/*      */ 
/*      */       
/*  186 */       List<String> xxqadMrpDetList = this.daoShared.getObjectList("select xmdet.xxqadMrpRev from XxqadMrpDet xmdet where xmdet.xxqadMrpPortalread='0'  group by xmdet.xxqadMrpRev order by xmdet.xxqadMrpRev asc ");
/*      */       
/*  188 */       xxqadMrpDetSyncRead(xxqadMrpDetList);
/*  189 */       System.out.println("-------------------- xxqadMrpDetSyncRead");
/*      */ 
/*      */       
/*  192 */       List<String> xxqadidddetList = this.daoShared.getObjectList(String.valueOf(beginSql) + 
/*  193 */           " and  ctrl.xxqad_table='xxqad_ld_det' group by ctrl.xxqad_seq");
/*  194 */       xxqadidddetSyncRead(xxqadidddetList);
/*  195 */       System.out.println("-------------------- xxqadidddetList");
/*      */ 
/*      */       
/*  198 */       xxqadXxptMstr();
/*  199 */       System.out.println("-------------------- xxqadXxptMstr");
/*      */ 
/*      */       
/*  202 */       xbqtyDetSyncRead();
/*  203 */       System.out.println("-------------------- xbqtyDetList");
/*      */ 
/*      */       
/*  206 */       List<String> hourList = this.daoShared.getObjectList("select  det.xxqad_hourplan_rev  from XxqadHourPlanDet det order by  det.xxqad_hourplan_rev desc ");
/*  207 */       xxqad72HourPlanDet(hourList);
/*  208 */       System.out.println("-------------------- xxqad72HourPlanDetList");
/*      */       
/*  210 */       descompositionProduction();
/*  211 */       System.out.println("-------------------- 分解完成");
/*  212 */     } catch (Exception e) {
/*  213 */       insertSystemLog("RedMinuteSyncJob", "main", e.getMessage(), "1");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public List<ProductOutGoline> getProductOutGoline() {
/*  219 */     List<ProductOutGoline> ProductOutGolineList = this.daoShared
/*  220 */       .getObjectList("from ProductOutGoline pg where  pg.xxsh_worc_status=2");
/*  221 */     return ProductOutGolineList;
/*      */   }
/*      */   
/*      */   public void startProductOutGolineSyn() {
/*      */     try {
/*  226 */       worcMstrSyncRead();
/*  227 */       System.out.println("--------------------worcMstrList");
/*      */     }
/*  229 */     catch (Exception e) {
/*  230 */       insertSystemLog("RedMinuteSyncJob", "main", e.getMessage(), "1");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void wmsPartSyncRead(List<String> sqlList) throws KettleException {
/*  237 */     for (String sql : sqlList) {
/*      */ 
/*      */       
/*      */       try {
/*  241 */         List<QADWmsPart> syncPartList = this.daoShared.getObjectList("from QADWmsPart part where part.xxqadPtSeq='" + sql + 
/*  242 */             "' and part.xxqadPtPortalread=0");
/*      */         
/*  244 */         Boolean issyncok = Boolean.valueOf(true);
/*  245 */         for (QADWmsPart shared : syncPartList) {
/*      */           try {
/*  247 */             insertPart(shared, this.dao, this.daoShared);
/*      */ 
/*      */           
/*      */           }
/*  251 */           catch (Exception e) {
/*  252 */             insertSystemLog("DailySyncJob", "wmsPartSyncRead1", e.getMessage(), "1");
/*  253 */             issyncok = Boolean.valueOf(false);
/*      */           } 
/*      */         } 
/*      */         
/*  257 */         if (issyncok.booleanValue()) {
/*      */           
/*  259 */           List<QADCtrl> ctrlList = this.daoShared.getObjectList("from QADCtrl ctrl where ctrl.xxqad_seq='" + sql + 
/*  260 */               "' and ctrl.xxqad_portal=0");
/*  261 */           for (QADCtrl ctrl : ctrlList) {
/*  262 */             ctrl.setXxqad_portal("1");
/*  263 */             this.daoShared.updateObject(ctrl);
/*  264 */             this.daoShared.commit();
/*      */           } 
/*      */         } 
/*  267 */       } catch (Exception e) {
/*  268 */         insertSystemLog("RedMinuteSyncJob", "wmsPartSyncRead1", e.getMessage(), "1");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void supplierSyncRead(List<String> sqlList) {
/*      */     try {
/*  277 */       for (String sql : sqlList) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  282 */         List<QADSupplier> syncSupplierList = this.daoShared
/*  283 */           .getObjectList("from QADSupplier supplier where supplier.xxqad_vd_seq='" + sql + 
/*  284 */             "' and supplier.xxqad_vd_portalread = 0");
/*  285 */         Boolean issyncok = Boolean.valueOf(true);
/*  286 */         for (QADSupplier shared : syncSupplierList) {
/*      */           try {
/*  288 */             insertSupp(shared, this.dao, this.daoShared);
/*      */           }
/*  290 */           catch (Exception e) {
/*  291 */             issyncok = Boolean.valueOf(false);
/*  292 */             e.printStackTrace();
/*  293 */             insertSystemLog("RedMinuteSyncJob", "supplierSyncRead1", e.getMessage(), "1");
/*      */           } 
/*      */         } 
/*  296 */         if (issyncok.booleanValue()) {
/*      */           
/*  298 */           List<QADCtrl> ctrlList = this.daoShared.getObjectList("from QADCtrl ctrl where ctrl.xxqad_seq='" + sql + 
/*  299 */               "' and ctrl.xxqad_portal=0");
/*  300 */           for (QADCtrl ctrl : ctrlList) {
/*      */             
/*  302 */             ctrl.setXxqad_portal("1");
/*  303 */             this.daoShared.updateObject(ctrl);
/*  304 */             this.daoShared.commit();
/*      */           } 
/*      */         } 
/*      */       } 
/*  308 */     } catch (Exception e) {
/*      */       
/*  310 */       insertSystemLog("RedMinuteSyncJob", "supplierSyncRead", e.getMessage(), "1");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void suppPartSyncRead(List<String> sqlList) throws KettleException {
/*  317 */     for (String sql : sqlList) {
/*      */       try {
/*  319 */         List<QADSupplierWmsPart> syncPartList = this.daoShared
/*  320 */           .getObjectList("from QADSupplierWmsPart part where part.xxqad_vp_seq='" + sql + 
/*  321 */             "' and part.xxqad_vp_mesread=0" + getTypeWhere("xxqad_vp_site", "xxqad_vp_domain"));
/*      */         
/*  323 */         Boolean issyncok = Boolean.valueOf(true);
/*  324 */         for (QADSupplierWmsPart shared : syncPartList) {
/*      */           try {
/*  326 */             boolean bl = insertSuppPart(shared, this.dao);
/*  327 */             if (bl) {
/*  328 */               shared.setXxqad_vp_portalread(Integer.valueOf(1)); continue;
/*      */             } 
/*  330 */             issyncok = Boolean.valueOf(false);
/*      */           }
/*  332 */           catch (Exception e) {
/*  333 */             e.printStackTrace();
/*  334 */             insertSystemLog("RedMinuteSyncJob", "wmsPartSyncRead1", e.getMessage(), "1");
/*  335 */             issyncok = Boolean.valueOf(false);
/*      */           } 
/*      */         } 
/*      */         
/*  339 */         if (issyncok.booleanValue()) {
/*      */           
/*  341 */           List<QADCtrl> ctrlList = this.daoShared.getObjectList("from QADCtrl ctrl where ctrl.xxqad_seq='" + sql + 
/*  342 */               "' and ctrl.xxqad_mes=0");
/*  343 */           for (QADCtrl ctrl : ctrlList) {
/*  344 */             ctrl.setXxqad_portal("1");
/*  345 */             this.daoShared.updateObject(ctrl);
/*      */           } 
/*      */         } 
/*  348 */       } catch (Exception e) {
/*  349 */         insertSystemLog("RedMinuteSyncJob", "suppPartSyncRead", e.getMessage(), "1");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void locationSyncRead(List<String> sqlList) throws KettleException {
/*  357 */     for (String sql : sqlList) {
/*      */       try {
/*  359 */         List<QADStorage> syncPartList = this.daoShared.getObjectList("from QADStorage qs where qs.xxqad_loc_seq='" + sql + 
/*  360 */             "' and qs.xxqad_loc_mesread=0" + getTypeWhere("xxqad_loc_site", "xxqad_loc_domain"));
/*      */         
/*  362 */         Boolean issyncok = Boolean.valueOf(true);
/*  363 */         for (QADStorage shared : syncPartList) {
/*      */           try {
/*  365 */             insertLocation(shared, this.dao);
/*      */             
/*  367 */             shared.setXxqad_loc_mesread(Integer.valueOf(1));
/*  368 */           } catch (Exception e) {
/*  369 */             insertSystemLog("RedMinuteSyncJob", "locationSyncRead", e.getMessage(), "1");
/*  370 */             issyncok = Boolean.valueOf(false);
/*      */           } 
/*      */         } 
/*      */         
/*  374 */         if (issyncok.booleanValue()) {
/*      */           
/*  376 */           List<QADCtrl> ctrlList = this.daoShared.getObjectList("from QADCtrl ctrl where ctrl.xxqad_seq='" + sql + 
/*  377 */               "' and ctrl.xxqad_mes=0");
/*  378 */           for (QADCtrl ctrl : ctrlList) {
/*  379 */             ctrl.setXxqad_mes("1");
/*  380 */             this.daoShared.updateObject(ctrl);
/*      */           } 
/*      */         } 
/*  383 */       } catch (Exception e) {
/*  384 */         insertSystemLog("RedMinuteSyncJob", "locationSyncRead", e.getMessage(), "1");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void bomSyncRead(List<String> sqlList) throws KettleException {
/*  392 */     for (String sql : sqlList) {
/*      */ 
/*      */       
/*      */       try {
/*  396 */         List<QADBomInfo> syncBomList = this.daoShared.getObjectList("from QADBomInfo bom where bom.xxqad_ps_seq='" + sql + 
/*  397 */             "' and bom.xxqad_ps_portalread=0");
/*      */         
/*  399 */         Boolean issyncok = Boolean.valueOf(true);
/*  400 */         for (QADBomInfo shared : syncBomList) {
/*      */           try {
/*  402 */             boolean b1 = insertBom(shared, this.dao, this.daoShared);
/*  403 */             if (!b1) {
/*  404 */               issyncok = Boolean.valueOf(false);
/*      */             }
/*  406 */           } catch (Exception e) {
/*  407 */             insertSystemLog("RedMinuteSyncJob", "bomSyncRead", e.getMessage(), "1");
/*  408 */             issyncok = Boolean.valueOf(false);
/*      */           } 
/*      */         } 
/*      */         
/*  412 */         if (issyncok.booleanValue()) {
/*      */           
/*  414 */           List<QADCtrl> ctrlList = this.daoShared.getObjectList("from QADCtrl ctrl where ctrl.xxqad_seq='" + sql + 
/*  415 */               "' and ctrl.xxqad_portal=0");
/*  416 */           for (QADCtrl ctrl : ctrlList) {
/*  417 */             ctrl.setXxqad_portal("1");
/*  418 */             this.daoShared.updateObject(ctrl);
/*      */           } 
/*      */         } 
/*  421 */       } catch (Exception e) {
/*  422 */         insertSystemLog("RedMinuteSyncJob", "locationSyncRead", e.getMessage(), "1");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void worcMstrSyncRead() throws KettleException {
/*      */     try {
/*  431 */       List<ProductOutGoline> syncProductOutGolineList = this.daoShared
/*  432 */         .getObjectList("from ProductOutGoline pg where  pg.xxsh_worc_status=0");
/*  433 */       for (ProductOutGoline shared : syncProductOutGolineList) {
/*  434 */         String code = shared.getXxsh_worc_item().trim();
/*  435 */         Date date = new Date();
/*      */         
/*  437 */         List<ProductGoline> ProductGolineList = this.dao
/*  438 */           .getObjectList("from ProductGoline pg where  pg.shCode='" + code + "'");
/*      */         try {
/*  440 */           if (ProductGolineList.size() == 0) {
/*  441 */             insertProductOutGoline(shared, this.dao);
/*  442 */             shared.setXxsh_worc_status("1");
/*  443 */             Date date2 = new Date();
/*  444 */             shared.setXxsh_worc_up_date(new Date());
/*  445 */             this.daoShared.updateObject(shared); continue;
/*      */           } 
/*  447 */           shared.setXxsh_worc_status("2");
/*  448 */           Date date1 = new Date();
/*  449 */           shared.setXxsh_worc_up_date(new Date());
/*  450 */           shared.setXxsh_worc_rmks("赛赫条码重复");
/*  451 */           this.daoShared.updateObject(shared);
/*      */         }
/*  453 */         catch (Exception e) {
/*  454 */           System.out.println(e);
/*  455 */           insertSystemLog("RedMinuteSyncJob", "worcMstrSyncRead", e.getMessage(), "1");
/*  456 */           shared.setXxsh_worc_status("2");
/*  457 */           shared.setXxsh_worc_rmks("找不到总成或根据总成查找的数据有错");
/*  458 */           Date date1 = new Date();
/*  459 */           shared.setXxsh_worc_up_date(new Date());
/*      */           
/*  461 */           this.daoShared.updateObject(shared);
/*      */         } 
/*      */       } 
/*  464 */     } catch (Exception e) {
/*  465 */       insertSystemLog("RedMinuteSyncJob", "locationSyncRead", e.getMessage(), "1");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void poSyncRead(List<String> sqlList) throws KettleException {
/*  472 */     for (String sql : sqlList) {
/*      */ 
/*      */       
/*      */       try {
/*  476 */         List<QADPurchaseOrder> syncPartList = this.daoShared.getObjectList("from QADPurchaseOrder po where po.xxqad_pod_seq='" + 
/*  477 */             sql + "' and po.xxqad_pod_portalread=0");
/*      */         
/*  479 */         Boolean issyncok = Boolean.valueOf(true);
/*  480 */         for (QADPurchaseOrder shared : syncPartList) {
/*      */           try {
/*  482 */             insertPo(shared, this.dao, this.daoShared);
/*      */           }
/*  484 */           catch (Exception e) {
/*  485 */             insertSystemLog("QADPurchaseOrder", "poSyncRead", e.getMessage(), "1");
/*  486 */             issyncok = Boolean.valueOf(false);
/*      */           } 
/*      */         } 
/*      */         
/*  490 */         if (issyncok.booleanValue()) {
/*      */           
/*  492 */           List<QADCtrl> ctrlList = this.daoShared.getObjectList("from QADCtrl ctrl where ctrl.xxqad_seq='" + sql + 
/*  493 */               "' and ctrl.xxqad_portal=0");
/*  494 */           for (QADCtrl ctrl : ctrlList) {
/*  495 */             ctrl.setXxqad_portal("1");
/*  496 */             this.daoShared.updateObject(ctrl);
/*      */           } 
/*      */         } 
/*  499 */       } catch (Exception e) {
/*  500 */         insertSystemLog("RedMinuteSyncJob", "poSyncRead", e.getMessage(), "1");
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void insertSystemLog(String action, String content, String sync_describe, String syncResults) {
/*  506 */     SyncLog log = new SyncLog();
/*  507 */     log.setSync_date(new Date());
/*  508 */     log.setSync_content(content);
/*  509 */     log.setSync_describe(sync_describe);
/*  510 */     log.setSync_object(action);
/*  511 */     log.setSync_results(syncResults);
/*  512 */     this.dao.saveObject(log);
/*      */   }
/*      */ 
/*      */   
/*      */   private void saleOrderSyncRead(List<String> sqlList) throws KettleException {
/*  517 */     for (String sql : sqlList) {
/*      */       try {
/*  519 */         List<QADCustomerWmsPart> syncBomList = this.daoShared
/*  520 */           .getObjectList("from QADCustomerWmsPart part where part.xxqad_sche_seq='" + sql + 
/*  521 */             "' and part.xxqad_sche_mesread=0" + getTypeWhere("xxqad_sche_site", "xxqad_sche_domain"));
/*  522 */         Boolean issyncok = Boolean.valueOf(true);
/*  523 */         for (QADCustomerWmsPart shared : syncBomList) {
/*      */           try {
/*  525 */             boolean sign = insertSalesOrder(shared, this.dao);
/*      */             
/*  527 */             if (sign) { shared.setXxqad_sche_mesread("1"); }
/*  528 */             else { shared.setXxqad_sche_mesread("2"); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  534 */             this.daoShared.updateObject(shared);
/*  535 */           } catch (Exception e) {
/*  536 */             insertSystemLog("RedMinuteSyncJob", "bomSyncRead", e.getMessage(), "1");
/*  537 */             issyncok = Boolean.valueOf(false);
/*      */           } 
/*      */         } 
/*  540 */         if (issyncok.booleanValue()) {
/*  541 */           List<QADCtrl> ctrlList = this.daoShared.getObjectList("from QADCtrl ctrl where ctrl.xxqad_seq='" + sql + 
/*  542 */               "' and ctrl.xxqad_mes=0");
/*  543 */           for (QADCtrl ctrl : ctrlList) {
/*  544 */             ctrl.setXxqad_mes("1");
/*  545 */             this.daoShared.updateObject(ctrl);
/*      */           } 
/*      */         } 
/*  548 */       } catch (Exception e) {
/*  549 */         insertSystemLog("RedMinuteSyncJob", "saleOrderSyncRead", e.getMessage(), "1");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void dailyProductPlanSyncRead(List<String> sqlList) throws KettleException {
/*  557 */     for (String sql : sqlList) {
/*      */       try {
/*  559 */         List<QADDailyProductionSchedule> syncBomList = this.daoShared
/*  560 */           .getObjectList("from QADDailyProductionSchedule qdps where qdps.xxqad_wo_seq='" + sql + 
/*  561 */             "' and qdps.xxqad_wo_mesread=0" + getTypeWhere("xxqad_wo_site", "xxqad_wo_domain"));
/*  562 */         Boolean issyncok = Boolean.valueOf(true);
/*  563 */         for (QADDailyProductionSchedule shared : syncBomList) {
/*      */           try {
/*  565 */             insertDailyProductPlan(shared, this.dao);
/*  566 */             shared.setXxqad_wo_mesread("1");
/*  567 */             this.daoShared.updateObject(shared);
/*  568 */           } catch (Exception e) {
/*  569 */             insertSystemLog("RedMinuteSyncJob", "bomSyncRead", e.getMessage(), "1");
/*  570 */             issyncok = Boolean.valueOf(false);
/*      */           } 
/*      */         } 
/*  573 */         if (issyncok.booleanValue()) {
/*  574 */           List<QADCtrl> ctrlList = this.daoShared.getObjectList("from QADCtrl ctrl where ctrl.xxqad_seq='" + sql + 
/*  575 */               "' and ctrl.xxqad_mes=0");
/*  576 */           for (QADCtrl ctrl : ctrlList) {
/*  577 */             ctrl.setXxqad_mes("1");
/*  578 */             this.daoShared.updateObject(ctrl);
/*      */           } 
/*      */         } 
/*  581 */       } catch (Exception e) {
/*  582 */         insertSystemLog("RedMinuteSyncJob", "saleOrderSyncRead", e.getMessage(), "1");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void workOrderBomSyncRead(List<String> sqlList) throws KettleException {
/*  590 */     for (String sql : sqlList) {
/*      */       try {
/*  592 */         List<QADBom> syncBomList = this.daoShared.getObjectList("from QADBom bom where bom.xxqad_wod_seq='" + sql + 
/*  593 */             "' and bom.xxqad_wod_mesread=0" + getTypeWhere("xxqad_wod_site", "xxqad_wod_domain"));
/*  594 */         Boolean issyncok = Boolean.valueOf(true);
/*  595 */         for (QADBom shared : syncBomList) {
/*      */           try {
/*  597 */             insertWorkOrderBom(shared, this.dao);
/*  598 */             shared.setXxqad_wod_mesread("1");
/*  599 */             this.daoShared.updateObject(shared);
/*  600 */           } catch (Exception e) {
/*  601 */             insertSystemLog("RedMinuteSyncJob", "bomSyncRead", e.getMessage(), "1");
/*  602 */             issyncok = Boolean.valueOf(false);
/*      */           } 
/*      */         } 
/*  605 */         if (issyncok.booleanValue()) {
/*  606 */           List<QADCtrl> ctrlList = this.daoShared.getObjectList("from QADCtrl ctrl where ctrl.xxqad_seq='" + sql + 
/*  607 */               "' and ctrl.xxqad_mes=0");
/*  608 */           for (QADCtrl ctrl : ctrlList) {
/*  609 */             ctrl.setXxqad_mes("1");
/*  610 */             this.daoShared.updateObject(ctrl);
/*      */           } 
/*      */         } 
/*  613 */       } catch (Exception e) {
/*  614 */         insertSystemLog("RedMinuteSyncJob", "saleOrderSyncRead", e.getMessage(), "1");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void basicCustomerSyncRead(List<String> sqlList) throws KettleException {
/*  622 */     for (String sql : sqlList) {
/*      */       try {
/*  624 */         List<QADAdMstr> syncBomList = this.daoShared.getObjectList("from QADAdMstr ad where ad.xxqadAdSeq='" + sql + 
/*  625 */             "' and ad.xxqad_ad_mesread=0" + getTypeWhere("xxqad_ad_site", "xxqad_ad_domain"));
/*  626 */         Boolean issyncok = Boolean.valueOf(true);
/*  627 */         for (QADAdMstr shared : syncBomList) {
/*      */           try {
/*  629 */             insertBasicCustomer(shared, this.dao);
/*  630 */             shared.setXxqad_ad_mesread("1");
/*  631 */             this.daoShared.updateObject(shared);
/*  632 */           } catch (Exception e) {
/*  633 */             insertSystemLog("RedMinuteSyncJob", "bomSyncRead", e.getMessage(), "1");
/*  634 */             issyncok = Boolean.valueOf(false);
/*      */           } 
/*      */         } 
/*  637 */         if (issyncok.booleanValue()) {
/*  638 */           List<QADCtrl> ctrlList = this.daoShared.getObjectList("from QADCtrl ctrl where ctrl.xxqad_seq='" + sql + 
/*  639 */               "' and ctrl.xxqad_mes=0");
/*  640 */           for (QADCtrl ctrl : ctrlList) {
/*  641 */             ctrl.setXxqad_mes("1");
/*  642 */             this.daoShared.updateObject(ctrl);
/*      */           } 
/*      */         } 
/*  645 */       } catch (Exception e) {
/*  646 */         insertSystemLog("RedMinuteSyncJob", "saleOrderSyncRead", e.getMessage(), "1");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void basicPartPriceSyncRead(List<String> sqlList) throws KettleException {
/*  654 */     for (String sql : sqlList) {
/*      */       try {
/*  656 */         List<QADPcMstr> syncPcList = this.daoShared.getObjectList("from QADPcMstr pc where pc.xxqadPcSeq='" + sql + 
/*  657 */             "' and pc.xxqadPcMesread=0" + getTypeWhere("xxqad_pc_site", "xxqad_pc_domain"));
/*  658 */         Boolean issyncok = Boolean.valueOf(true);
/*  659 */         for (QADPcMstr shared : syncPcList) {
/*      */           try {
/*  661 */             insertBasicPartPrice(shared, this.dao);
/*  662 */             shared.setXxqadPcMesread("1");
/*  663 */             this.daoShared.updateObject(shared);
/*  664 */           } catch (Exception e) {
/*  665 */             insertSystemLog("RedMinuteSyncJob", "pcSyncRead", e.getMessage(), "1");
/*  666 */             issyncok = Boolean.valueOf(false);
/*      */           } 
/*      */         } 
/*  669 */         if (issyncok.booleanValue()) {
/*  670 */           List<QADCtrl> ctrlList = this.daoShared.getObjectList("from QADCtrl ctrl where ctrl.xxqad_seq='" + sql + 
/*  671 */               "' and ctrl.xxqad_mes=0");
/*  672 */           for (QADCtrl ctrl : ctrlList) {
/*  673 */             ctrl.setXxqad_mes("1");
/*  674 */             this.daoShared.updateObject(ctrl);
/*      */           } 
/*      */         } 
/*  677 */       } catch (Exception e) {
/*  678 */         insertSystemLog("RedMinuteSyncJob", "pcSyncRead", e.getMessage(), "1");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void xbmwoDetListSyncRead(List<String> sqlList) throws KettleException {
/*      */     try {
/*  687 */       List<XbmwoDet> syncXbmwoDetList = this.daoShared.getObjectList("from XbmwoDet xbmwodet where (xbmwodet.xbmwo_portalread=0 or xbmwodet.xbmwo_portalread is Null)");
/*      */ 
/*      */       
/*  690 */       for (XbmwoDet shared : syncXbmwoDetList) {
/*      */         try {
/*  692 */           insertXbmwoDet(shared, this.dao, this.daoShared);
/*      */         }
/*  694 */         catch (Exception e) {
/*  695 */           insertSystemLog("RedMinuteSyncJob", "xbmwoDetListSyncRead", e.getMessage(), "1");
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  709 */     catch (Exception e) {
/*  710 */       insertSystemLog("RedMinuteSyncJob", "xbmwoDetListSyncRead", e.getMessage(), "1");
/*  711 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void xxqadMrpDetSyncRead(List<String> sqlList) throws KettleException {
/*  718 */     for (String sql : sqlList) {
/*      */       
/*      */       try {
/*  721 */         List<XxqadMrpDet> syncXxqadMrpDetList = this.daoShared.getObjectList("from XxqadMrpDet xmdet where xmdet.xxqadMrpRev='" + sql + 
/*  722 */             "' and xmdet.xxqadMrpPortalread='0'");
/*      */         
/*  724 */         int a = 0;
/*  725 */         for (XxqadMrpDet shared : syncXxqadMrpDetList) {
/*      */           try {
/*  727 */             int i = insertXxqadMrpDet(shared, this.dao, this.daoShared);
/*  728 */             a += i;
/*      */           
/*      */           }
/*  731 */           catch (Exception e) {
/*  732 */             insertSystemLog("RedMinuteSyncJob", "xxqadMrpDetSyncRead", e.getMessage(), "1");
/*      */           } 
/*      */         } 
/*  735 */         if (a > 0) {
/*  736 */           this.dao.commit();
/*  737 */           this.daoShared.commit();
/*  738 */           deleteNjitNpoPlanDetList(sql);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  749 */       catch (Exception e) {
/*  750 */         insertSystemLog("RedMinuteSyncJob", "xxqadMrpDetSyncRead", e.getMessage(), "1");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void xxqadDailyPlanDet(List<String> sqlList) throws KettleException {
/*  757 */     if (sqlList != null && sqlList.size() > 0) {
/*  758 */       String sql = sqlList.get(0);
/*      */       try {
/*  760 */         List<XxqadDailyPlanDet> syncXxqadDailyPlanList = this.daoShared.getObjectList(" from XxqadDailyPlanDet p  where  (p.xxqad_dailyplan_portalread = 0 or p.xxqad_dailyplan_portalread is null) and p.xxqad_dailyplan_rev='" + sql + "'  ");
/*      */         
/*  762 */         if (syncXxqadDailyPlanList.size() != 0) {
/*  763 */           insertProductionDayPlanHistory();
/*      */         }
/*  765 */         Boolean issyncok = Boolean.valueOf(true);
/*  766 */         for (XxqadDailyPlanDet shared : syncXxqadDailyPlanList) {
/*      */           try {
/*  768 */             insertXxqadDailyPlanDet(shared, this.dao, this.daoShared);
/*      */           
/*      */           }
/*  771 */           catch (Exception e) {
/*  772 */             insertSystemLog("RedMinuteSyncJob", "xxqadDailyPlanDetListSyncRead", e.getMessage(), "1");
/*  773 */             issyncok = Boolean.valueOf(false);
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  787 */       catch (Exception e) {
/*  788 */         insertSystemLog("RedMinuteSyncJob", "xxqadDailyPlanDetListSyncRead", e.getMessage(), "1");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void xxqadXxptMstr() {
/*      */     try {
/*  800 */       List<XxqadXxptMstr> mstrList = this.daoShared.getObjectList(" from XxqadXxptMstr mstr where (mstr.xxqad_xxpt_portalread = 0 or mstr.xxqad_xxpt_portalread is null)");
/*  801 */       for (XxqadXxptMstr xxqadXxptMstr : mstrList)
/*      */       {
/*  803 */         WmsPart part = (WmsPart)this.dao.getObject(WmsPart.class, xxqadXxptMstr.getXxqad_xxpt_part());
/*      */         
/*  805 */         if (part == null) {
/*      */           
/*  807 */           System.out.println("ASN与总成对应中没有总成号：" + xxqadXxptMstr.getXxqad_xxpt_part());
/*      */           
/*      */           continue;
/*      */         } 
/*  811 */         List<QadOrEdi> qadOrEdiList = this.dao.getObjectList(" from QadOrEdi qad where qad.models='" + xxqadXxptMstr.getXxqad_xxpt_alc_code() + "' and qad.qadPart.id='" + xxqadXxptMstr.getXxqad_xxpt_part() + "'");
/*  812 */         List<MesSeatType> seatTypes = this.daoShared.getObjectList(" from MesSeatType mes where mes.alc='" + xxqadXxptMstr.getXxqad_xxpt_alc_code() + "'");
/*  813 */         if (qadOrEdiList.size() != 0) {
/*      */           
/*  815 */           QadOrEdi qad = qadOrEdiList.get(0);
/*  816 */           qad.setQty(xxqadXxptMstr.getXxqad_xxpt_qty());
/*  817 */           qad.setCreateDate(xxqadXxptMstr.getXxqad_xxpt_createdt());
/*  818 */           if (seatTypes.size() == 0) {
/*  819 */             qad.setDes(null);
/*      */           } else {
/*  821 */             qad.setDes(((MesSeatType)seatTypes.get(0)).getDes());
/*      */           } 
/*  823 */           this.dao.updateObject(qad);
/*      */         } else {
/*      */           
/*  826 */           QadOrEdi qad = new QadOrEdi();
/*  827 */           qad.setModels(xxqadXxptMstr.getXxqad_xxpt_alc_code());
/*  828 */           qad.setQadPart(part);
/*  829 */           qad.setQty(xxqadXxptMstr.getXxqad_xxpt_qty());
/*  830 */           qad.setCreateDate(xxqadXxptMstr.getXxqad_xxpt_createdt());
/*  831 */           if (seatTypes.size() == 0) {
/*  832 */             qad.setDes(null);
/*      */           } else {
/*  834 */             qad.setDes(((MesSeatType)seatTypes.get(0)).getDes());
/*      */           } 
/*  836 */           this.dao.saveObject(qad);
/*      */         } 
/*  838 */         xxqadXxptMstr.setXxqad_xxpt_portalread("1");
/*      */         
/*  840 */         this.daoShared.updateObject(xxqadXxptMstr);
/*  841 */         this.dao.commit();
/*  842 */         this.daoShared.commit();
/*      */       }
/*      */     
/*  845 */     } catch (Exception e) {
/*  846 */       insertSystemLog("RedMinuteSyncJob", "syncXxqadXxptMstr", e.getMessage(), "1");
/*      */       
/*  848 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void xxqadidddetSyncRead(List<String> sqlList) throws KettleException {
/*      */     try {
/*  858 */       List<Xxqadidddet> syncXxqadidddetList = this.daoShared.getObjectList("from Xxqadidddet det where ( det.xxqadLdPortalread = 0 or det.xxqadLdPortalread is null) order by det.xxqadLdId");
/*  859 */       Map<String, Map<String, Object>> map = new LinkedHashMap<String, Map<String, Object>>();
/*  860 */       for (Xxqadidddet shared : syncXxqadidddetList) {
/*  861 */         if (map.containsKey(shared.getXxqadLdPart())) {
/*  862 */           Map<String, Object> map1 = map.get(shared.getXxqadLdPart());
/*  863 */           if (map1.containsKey("qty")) {
/*  864 */             Integer qty = (Integer)map1.get("qty");
/*  865 */             map1.put("qty", Integer.valueOf(qty.intValue() + shared.getXxqadLdQtyOh().intValue()));
/*  866 */             map1.put("createDate", shared.getXxqadLdCreatedt());
/*      */           }  continue;
/*      */         } 
/*  869 */         Map<String, Object> mapo = new HashMap<String, Object>();
/*  870 */         mapo.put("qty", shared.getXxqadLdQtyOh());
/*  871 */         mapo.put("createDate", shared.getXxqadLdCreatedt());
/*  872 */         map.put(shared.getXxqadLdPart(), mapo);
/*      */       } 
/*      */       
/*  875 */       Boolean issyncok = Boolean.valueOf(true);
/*      */       
/*      */       try {
/*  878 */         for (String key : map.keySet()) {
/*  879 */           boolean sign = insertXxqadidddetListMap(key, map.get(key), this.dao);
/*  880 */           if (sign) {
/*  881 */             for (Xxqadidddet shared : syncXxqadidddetList) {
/*  882 */               if (shared.getXxqadLdPart().equals(key)) {
/*  883 */                 shared.setXxqadLdPortalread("1");
/*  884 */                 this.daoShared.updateObject(shared);
/*      */               } 
/*      */             }  continue;
/*      */           } 
/*  888 */           for (Xxqadidddet shared : syncXxqadidddetList) {
/*  889 */             if (shared.getXxqadLdPart().equals(key)) {
/*  890 */               shared.setXxqadLdPortalread("2");
/*  891 */               this.daoShared.updateObject(shared);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/*  896 */         this.dao.commit();
/*  897 */         this.daoShared.commit();
/*  898 */       } catch (Exception e) {
/*  899 */         insertSystemLog("RedMinuteSyncJob", "syncXxqadidddetList", e.getMessage(), "1");
/*  900 */         issyncok = Boolean.valueOf(false);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  912 */     catch (Exception e) {
/*  913 */       insertSystemLog("RedMinuteSyncJob", "syncXxqadidddetList", e.getMessage(), "1");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void xbqtyDetSyncRead() {
/*      */     try {
/*  923 */       List<XbqtyDet> xbqtyDetList = this.daoShared.getObjectList(" from XbqtyDet det  where (det.xbqty_rev01=0 or det.xbqty_rev01 is null)");
/*  924 */       for (XbqtyDet xbqtyDet : xbqtyDetList) {
/*  925 */         List<PortalShipOrderItem> orderItemList = this.dao.getObjectList(" from PortalShipOrderItem item  where item.portalShipOrder.code='" + xbqtyDet.getXbqty_nbr() + "' and item.part.id='" + xbqtyDet.getXbqty_part() + "'");
/*      */         
/*  927 */         if (orderItemList.size() == 0) {
/*  928 */           xbqtyDet.setXbqty_rev01("2");
/*  929 */           this.daoShared.updateObject(xbqtyDet);
/*      */           
/*      */           continue;
/*      */         } 
/*  933 */         for (PortalShipOrderItem portalShipOrderItem : orderItemList) {
/*      */           
/*  935 */           portalShipOrderItem.setActual_qty(xbqtyDet.getXbqty_qty_real());
/*  936 */           portalShipOrderItem.setReceived_qty(xbqtyDet.getXbqty_qty_arr());
/*  937 */           this.dao.updateObject(portalShipOrderItem);
/*      */           
/*  939 */           xbqtyDet.setXbqty_rev01("1");
/*  940 */           this.daoShared.updateObject(xbqtyDet);
/*      */         } 
/*  942 */         boolean f = true;
/*  943 */         List<PortalShipOrder> portalShipOrderList = this.dao.getObjectList("from PortalShipOrder pso where code='" + xbqtyDet.getXbqty_nbr() + "'");
/*  944 */         for (int i = 0; i < portalShipOrderList.size(); i++) {
/*  945 */           BigDecimal arrCount = new BigDecimal(0);
/*  946 */           BigDecimal realCount = new BigDecimal(0);
/*  947 */           BigDecimal arrNum = new BigDecimal(0);
/*      */           
/*  949 */           PortalShipOrder portalShipOrder = portalShipOrderList.get(i);
/*  950 */           List<PortalShipOrderItem> orderItemList1 = this.dao.getObjectList(" from PortalShipOrderItem item  where item.portalShipOrder.id='" + portalShipOrder.getId() + "'");
/*  951 */           for (int j = 0; j < orderItemList1.size(); j++) {
/*  952 */             PortalShipOrderItem portalShipOrderItem1 = orderItemList1.get(j);
/*  953 */             if (portalShipOrderItem1.getDeliveryNumber() != null) {
/*  954 */               arrCount = portalShipOrderItem1.getDeliveryNumber().add(arrCount);
/*      */             }
/*  956 */             if (portalShipOrderItem1.getActual_qty() != null) {
/*  957 */               realCount = portalShipOrderItem1.getActual_qty().add(realCount);
/*      */             }
/*      */ 
/*      */             
/*  961 */             arrNum = xbqtyDet.getXbqty_qty_arr().add(arrNum);
/*  962 */             int res = 0;
/*  963 */             if (portalShipOrderItem1.getReceived_qty() != null) {
/*  964 */               res = portalShipOrderItem1.getReceived_qty().compareTo(portalShipOrderItem1.getDeliveryNumber());
/*      */             }
/*  966 */             if (res != 0) {
/*  967 */               f = false;
/*      */             }
/*      */           } 
/*  970 */           if (f) {
/*  971 */             portalShipOrder.setEnabled(EnabledDisabled.DISABLED);
/*      */           }
/*  973 */           portalShipOrder.setRealDate(xbqtyDet.getXbqty_date_arr());
/*  974 */           portalShipOrder.setDifferenceCount(arrCount.subtract(realCount));
/*  975 */           this.dao.updateObject(portalShipOrder);
/*      */         } 
/*      */       } 
/*  978 */     } catch (Exception e) {
/*      */       
/*  980 */       insertSystemLog("RedMinuteSyncJob", "xbqtyDetList", e.getMessage(), "1");
/*      */       
/*  982 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void xxqad72HourPlanDet(List<String> sqlList) throws KettleException {
/*  992 */     if (sqlList != null && sqlList.size() > 0) {
/*  993 */       String sql = sqlList.get(0);
/*      */       try {
/*  995 */         List<XxqadHourPlanDet> syncXxqadHourPlanList = this.daoShared.getObjectList(" from XxqadHourPlanDet p  where  (p.xxqad_hourplan_portalread = 0 or p.xxqad_hourplan_portalread is null) and p.xxqad_hourplan_rev='" + sql + "'  ");
/*      */         
/*  997 */         if (syncXxqadHourPlanList.size() != 0) {
/*  998 */           insertProduction72HourPlanHistory();
/*      */         }
/* 1000 */         Boolean issyncok = Boolean.valueOf(true);
/* 1001 */         for (XxqadHourPlanDet shared : syncXxqadHourPlanList) {
/*      */           try {
/* 1003 */             insertXxqad72HourPlanDet(shared, this.dao, this.daoShared);
/*      */           
/*      */           }
/* 1006 */           catch (Exception e) {
/* 1007 */             insertSystemLog("RedMinuteSyncJob", "xxqad72HourPlanDetListSyncRead", e.getMessage(), "1");
/* 1008 */             issyncok = Boolean.valueOf(false);
/*      */           } 
/*      */         } 
/* 1011 */       } catch (Exception e) {
/* 1012 */         insertSystemLog("RedMinuteSyncJob", "xxqad72HourPlanDetListSyncRead", e.getMessage(), "1");
/*      */       } 
/*      */     } 
/*      */   }
/*      */   public void deleteNjitNpoPlanDetList(String str) {
/* 1017 */     List<NjitNpoPlan> njitNpoPlanList = this.dao.getObjectList("from NjitNpoPlan plan where plan.version !='" + str + "' or  plan.version is null");
/* 1018 */     for (NjitNpoPlan njitNpoPlan : njitNpoPlanList) {
/* 1019 */       Njitnpoplanhistory njitnpoplanhistory = new Njitnpoplanhistory(njitNpoPlan);
/* 1020 */       this.dao.saveObject(njitnpoplanhistory);
/* 1021 */       this.dao.removeObject(njitNpoPlan);
/*      */     } 
/* 1023 */     this.dao.commit();
/*      */   }
/*      */   public void insertProductionDayPlanHistory() {
/* 1026 */     String sqlPlan = " from ProductionDayPlan plan ";
/*      */     
/*      */     try {
/* 1029 */       List<ProductionDayPlan> planList = this.dao.getObjectList(sqlPlan);
/* 1030 */       for (ProductionDayPlan dayPlan : planList) {
/* 1031 */         ProductionDayPlanHistory history = new ProductionDayPlanHistory();
/* 1032 */         history.setAsnNo(dayPlan.getAsnNo());
/* 1033 */         history.setVersion(dayPlan.getVersion());
/* 1034 */         history.setCreatDate(dayPlan.getCreatDate());
/* 1035 */         history.setCreatTime(dayPlan.getCreatTime());
/* 1036 */         history.setDailyDate(dayPlan.getDailyDate());
/* 1037 */         history.setCarkind(dayPlan.getCarkind());
/* 1038 */         history.setPlanRem(dayPlan.getPlanRem());
/* 1039 */         history.setPlanSum(dayPlan.getPlanSum());
/* 1040 */         history.setPlanWeitou(dayPlan.getPlanWeitou());
/* 1041 */         history.setPlanLco(dayPlan.getPlanLco());
/* 1042 */         history.setPlanLcx(dayPlan.getPlanLcx());
/* 1043 */         history.setPlanAll(dayPlan.getPlanAll());
/* 1044 */         history.setPlanM1(dayPlan.getPlanM1());
/* 1045 */         history.setPlanMouth(dayPlan.getPlanMouth());
/* 1046 */         history.setPlanD1(dayPlan.getPlanD1());
/* 1047 */         history.setPlanPbs(dayPlan.getPlanPbs());
/* 1048 */         history.setPlanPrj(dayPlan.getPlanPrj());
/* 1049 */         history.setPlanWbs(dayPlan.getPlanWbs());
/* 1050 */         history.setPlanSum1(dayPlan.getPlanSum1());
/* 1051 */         history.setDay1(dayPlan.getDay1());
/* 1052 */         history.setDay2(dayPlan.getDay2());
/* 1053 */         history.setDay3(dayPlan.getDay3());
/* 1054 */         history.setDay4(dayPlan.getDay4());
/* 1055 */         history.setDay5(dayPlan.getDay5());
/* 1056 */         history.setDay6(dayPlan.getDay6());
/* 1057 */         history.setDay7(dayPlan.getDay7());
/* 1058 */         history.setDay8(dayPlan.getDay8());
/* 1059 */         history.setDay9(dayPlan.getDay9());
/* 1060 */         history.setDay10(dayPlan.getDay10());
/* 1061 */         history.setDay11(dayPlan.getDay11());
/* 1062 */         history.setDay12(dayPlan.getDay12());
/* 1063 */         history.setDay13(dayPlan.getDay13());
/* 1064 */         history.setDay14(dayPlan.getDay14());
/* 1065 */         history.setDay15(dayPlan.getDay15());
/* 1066 */         history.setDay16(dayPlan.getDay16());
/* 1067 */         history.setDay17(dayPlan.getDay17());
/* 1068 */         history.setDay18(dayPlan.getDay18());
/* 1069 */         history.setDay19(dayPlan.getDay19());
/* 1070 */         history.setDay20(dayPlan.getDay20());
/* 1071 */         history.setDay21(dayPlan.getDay21());
/* 1072 */         history.setDay22(dayPlan.getDay22());
/* 1073 */         history.setDay23(dayPlan.getDay23());
/* 1074 */         history.setDay24(dayPlan.getDay24());
/* 1075 */         history.setDay25(dayPlan.getDay25());
/* 1076 */         history.setDay26(dayPlan.getDay26());
/* 1077 */         history.setDay27(dayPlan.getDay27());
/* 1078 */         history.setDay28(dayPlan.getDay28());
/* 1079 */         history.setDay29(dayPlan.getDay29());
/* 1080 */         history.setDay30(dayPlan.getDay30());
/* 1081 */         history.setDay31(dayPlan.getDay31());
/* 1082 */         history.setDay32(dayPlan.getDay32());
/* 1083 */         history.setDay33(dayPlan.getDay33());
/* 1084 */         history.setDay34(dayPlan.getDay34());
/* 1085 */         history.setDay35(dayPlan.getDay35());
/* 1086 */         history.setDay36(dayPlan.getDay36());
/* 1087 */         history.setDay37(dayPlan.getDay37());
/* 1088 */         history.setDay38(dayPlan.getDay38());
/* 1089 */         history.setDay39(dayPlan.getDay39());
/* 1090 */         history.setDay40(dayPlan.getDay40());
/* 1091 */         history.setDay41(dayPlan.getDay41());
/* 1092 */         history.setDay42(dayPlan.getDay42());
/* 1093 */         history.setDay43(dayPlan.getDay43());
/* 1094 */         history.setDay44(dayPlan.getDay44());
/* 1095 */         history.setDay45(dayPlan.getDay45());
/*      */         
/* 1097 */         this.dao.saveObject(history);
/* 1098 */         this.dao.removeObject(dayPlan);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1168 */     catch (Exception e) {
/*      */       
/* 1170 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */   public void insertProduction72HourPlanHistory() {
/* 1174 */     String sqlPlan = " from Production72HourPlan  ";
/*      */     
/*      */     try {
/* 1177 */       List<Production72HourPlan> planList = this.dao.getObjectList(sqlPlan);
/* 1178 */       for (Production72HourPlan hourPlan : planList) {
/* 1179 */         Production72HourPlanHistory history = new Production72HourPlanHistory();
/* 1180 */         history.setVersion(hourPlan.getVersion());
/* 1181 */         history.setCreatdate(hourPlan.getCreatdate());
/* 1182 */         history.setCreattime(hourPlan.getCreattime());
/* 1183 */         history.setCarkind(hourPlan.getCarkind());
/* 1184 */         history.setAsn(hourPlan.getAsn());
/* 1185 */         history.setM1(hourPlan.getM1());
/* 1186 */         history.setMouth(hourPlan.getMouth());
/* 1187 */         history.setD1(hourPlan.getD1());
/* 1188 */         history.setPbs(hourPlan.getPbs());
/* 1189 */         history.setPrj(hourPlan.getPrj());
/* 1190 */         history.setWbs(hourPlan.getWbs());
/* 1191 */         history.setSum1(hourPlan.getSum1());
/* 1192 */         history.setHourplan_day1(hourPlan.getHourplan_day1());
/* 1193 */         history.setHourplan_day2(hourPlan.getHourplan_day2());
/* 1194 */         history.setHourplan_day3(hourPlan.getHourplan_day3());
/* 1195 */         history.setHourplan_day4(hourPlan.getHourplan_day4());
/* 1196 */         history.setHourplan_day5(hourPlan.getHourplan_day5());
/* 1197 */         history.setHourplan_day6(hourPlan.getHourplan_day6());
/* 1198 */         history.setHourplan_day7(hourPlan.getHourplan_day7());
/* 1199 */         history.setHourplan_day8(hourPlan.getHourplan_day8());
/* 1200 */         history.setHourplan_day9(hourPlan.getHourplan_day9());
/* 1201 */         history.setHourplan_day10(hourPlan.getHourplan_day10());
/* 1202 */         history.setHourplan_day11(hourPlan.getHourplan_day11());
/* 1203 */         history.setHourplan_day12(hourPlan.getHourplan_day12());
/* 1204 */         history.setHourplan_day13(hourPlan.getHourplan_day13());
/* 1205 */         history.setHourplan_day14(hourPlan.getHourplan_day14());
/* 1206 */         history.setHourplan_day15(hourPlan.getHourplan_day15());
/* 1207 */         history.setHourplan_day16(hourPlan.getHourplan_day16());
/* 1208 */         history.setHourplan_day17(hourPlan.getHourplan_day17());
/* 1209 */         history.setHourplan_day18(hourPlan.getHourplan_day18());
/* 1210 */         history.setHourplan_day19(hourPlan.getHourplan_day19());
/* 1211 */         history.setHourplan_day20(hourPlan.getHourplan_day20());
/* 1212 */         history.setHourplan_day21(hourPlan.getHourplan_day21());
/* 1213 */         history.setHourplan_day22(hourPlan.getHourplan_day22());
/* 1214 */         history.setHourplan_day23(hourPlan.getHourplan_day23());
/* 1215 */         history.setHourplan_day24(hourPlan.getHourplan_day24());
/* 1216 */         history.setHourplan_day25(hourPlan.getHourplan_day25());
/* 1217 */         history.setHourplan_day26(hourPlan.getHourplan_day26());
/* 1218 */         history.setHourplan_day27(hourPlan.getHourplan_day27());
/* 1219 */         history.setHourplan_day28(hourPlan.getHourplan_day28());
/* 1220 */         history.setHourplan_day29(hourPlan.getHourplan_day29());
/* 1221 */         history.setHourplan_day30(hourPlan.getHourplan_day30());
/* 1222 */         history.setHourplan_day31(hourPlan.getHourplan_day31());
/* 1223 */         history.setHourplan_day32(hourPlan.getHourplan_day32());
/* 1224 */         history.setHourplan_day33(hourPlan.getHourplan_day33());
/* 1225 */         history.setHourplan_day34(hourPlan.getHourplan_day34());
/* 1226 */         history.setHourplan_day35(hourPlan.getHourplan_day35());
/* 1227 */         history.setHourplan_day36(hourPlan.getHourplan_day36());
/* 1228 */         history.setHourplan_day37(hourPlan.getHourplan_day37());
/* 1229 */         history.setHourplan_day38(hourPlan.getHourplan_day38());
/* 1230 */         history.setHourplan_day39(hourPlan.getHourplan_day39());
/* 1231 */         history.setHourplan_day40(hourPlan.getHourplan_day40());
/* 1232 */         history.setHourplan_day41(hourPlan.getHourplan_day41());
/* 1233 */         history.setHourplan_day42(hourPlan.getHourplan_day42());
/* 1234 */         history.setHourplan_day43(hourPlan.getHourplan_day43());
/* 1235 */         history.setHourplan_day44(hourPlan.getHourplan_day44());
/* 1236 */         history.setHourplan_day45(hourPlan.getHourplan_day45());
/* 1237 */         history.setHourplan_day46(hourPlan.getHourplan_day46());
/* 1238 */         history.setHourplan_day47(hourPlan.getHourplan_day47());
/* 1239 */         history.setHourplan_day48(hourPlan.getHourplan_day48());
/* 1240 */         history.setHourplan_day49(hourPlan.getHourplan_day49());
/* 1241 */         history.setHourplan_day50(hourPlan.getHourplan_day50());
/* 1242 */         history.setHourplan_day51(hourPlan.getHourplan_day51());
/* 1243 */         history.setHourplan_day52(hourPlan.getHourplan_day52());
/* 1244 */         history.setHourplan_day53(hourPlan.getHourplan_day53());
/* 1245 */         history.setHourplan_day54(hourPlan.getHourplan_day54());
/* 1246 */         history.setHourplan_day55(hourPlan.getHourplan_day55());
/* 1247 */         history.setHourplan_day56(hourPlan.getHourplan_day56());
/* 1248 */         history.setHourplan_day57(hourPlan.getHourplan_day57());
/* 1249 */         history.setHourplan_day58(hourPlan.getHourplan_day58());
/* 1250 */         history.setHourplan_day59(hourPlan.getHourplan_day59());
/* 1251 */         history.setHourplan_day60(hourPlan.getHourplan_day60());
/* 1252 */         history.setHourplan_day61(hourPlan.getHourplan_day61());
/* 1253 */         history.setHourplan_day62(hourPlan.getHourplan_day62());
/* 1254 */         history.setHourplan_day63(hourPlan.getHourplan_day63());
/* 1255 */         history.setHourplan_day64(hourPlan.getHourplan_day64());
/* 1256 */         history.setHourplan_day65(hourPlan.getHourplan_day65());
/* 1257 */         history.setRem(hourPlan.getRem());
/* 1258 */         history.setSum(hourPlan.getSum());
/* 1259 */         history.setWeitou(hourPlan.getWeitou());
/* 1260 */         history.setLco(hourPlan.getLco());
/* 1261 */         history.setLcx(hourPlan.getLcx());
/* 1262 */         history.setHourAll(hourPlan.getHourAll());
/* 1263 */         history.setXxqad_hourdate(hourPlan.getXxqad_hourdate());
/* 1264 */         history.setRmks(hourPlan.getRmks());
/* 1265 */         history.setCreatedt(hourPlan.getCreatedt());
/* 1266 */         history.setCreateur(hourPlan.getCreateur());
/* 1267 */         history.setUpdatedt(hourPlan.getUpdatedt());
/* 1268 */         history.setUpdateur(hourPlan.getUpdateur());
/*      */         
/* 1270 */         this.dao.saveObject(history);
/* 1271 */         this.dao.removeObject(hourPlan);
/*      */       } 
/* 1273 */     } catch (Exception e) {
/*      */       
/* 1275 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void descompositionProduction() {
/* 1281 */     List<EdiProductionErrorLog> errorLogList = new ArrayList<EdiProductionErrorLog>();
/* 1282 */     EdiProductionErrorLog errorLog = null;
/* 1283 */     List<EdiProduction> ediProductionList = this.dao.getObjectList(" from EdiProduction entity where  entity.status!=1 and entity.enabled=0 and entity.type=1");
/* 1284 */     for (EdiProduction ediProduction : ediProductionList) {
/*      */       try {
/* 1286 */         List<QadOrEdi> qadEdiList = this.dao.getObjectList(" from QadOrEdi entity where entity.models ='" + ediProduction.getAsnNo() + "'");
/* 1287 */         if (qadEdiList.size() > 0) {
/* 1288 */           boolean sign = true;
/* 1289 */           for (QadOrEdi qadOrEdi : qadEdiList) {
/* 1290 */             List<Bom> bomList = this.dao.getObjectList(" from Bom ba where ba.father_part.id ='" + qadOrEdi.getQadPart().getId() + "'");
/* 1291 */             if (bomList == null || bomList.size() == 0) {
/* 1292 */               sign = false;
/* 1293 */               errorLog = new EdiProductionErrorLog();
/* 1294 */               errorLog.setAsnNo(ediProduction.getAsnNo());
/* 1295 */               errorLog.setNumber(ediProduction.getNumber());
/* 1296 */               errorLog.setModels(ediProduction.getModels());
/* 1297 */               errorLog.setDescribe(ediProduction.getDescribe());
/* 1298 */               errorLog.setProductlinecode(ediProduction.getProductlinecode());
/* 1299 */               errorLog.setShiftcode(ediProduction.getShiftcode());
/* 1300 */               errorLog.setStaffcode(ediProduction.getStaffcode());
/* 1301 */               errorLog.setTaskDate(ediProduction.getTaskDate());
/* 1302 */               errorLog.setTime(ediProduction.getTime());
/* 1303 */               errorLog.setSyncTime(ediProduction.getSyncTime());
/* 1304 */               errorLog.setQty(ediProduction.getQty());
/* 1305 */               errorLog.setType(ediProduction.getType());
/* 1306 */               errorLog.setUnit(ediProduction.getUnit());
/* 1307 */               errorLog.setErrorInfo("分解失败,在物料bom里面找不到父料号" + qadOrEdi.getQadPart().getId());
/* 1308 */               errorLogList.add(errorLog);
/*      */             } 
/*      */           } 
/* 1311 */           if (sign) {
/* 1312 */             for (QadOrEdi qadOrEdi : qadEdiList) {
/* 1313 */               List<Bom> bomList = this.dao.getObjectList(" from Bom ba where ba.father_part.id='" + qadOrEdi.getQadPart().getId() + "'");
/* 1314 */               if (bomList != null && bomList.size() > 0) {
/* 1315 */                 List<JitProductionPlan> jitProductionPlanList = this.jitProductionPlanDao.DismantlingBom(ediProduction, qadOrEdi.getQadPart(), new BigDecimal((ediProduction.getQty() == null) ? 0 : ediProduction.getQty().intValue()));
/* 1316 */                 insertJitProductionPlan(jitProductionPlanList);
/* 1317 */                 ediProduction.setStatus(Integer.valueOf(1));
/* 1318 */                 this.dao.updateObject(ediProduction);
/* 1319 */                 this.dao.commit();
/*      */               } 
/*      */             }  continue;
/*      */           } 
/* 1323 */           ediProduction.setStatus(Integer.valueOf(2));
/* 1324 */           this.dao.updateObject(ediProduction);
/* 1325 */           this.dao.commit();
/*      */           continue;
/*      */         } 
/* 1328 */         ediProduction.setStatus(Integer.valueOf(2));
/* 1329 */         this.dao.updateObject(ediProduction);
/* 1330 */         errorLog = new EdiProductionErrorLog();
/* 1331 */         errorLog.setAsnNo(ediProduction.getAsnNo());
/* 1332 */         errorLog.setNumber(ediProduction.getNumber());
/* 1333 */         errorLog.setModels(ediProduction.getModels());
/* 1334 */         errorLog.setDescribe(ediProduction.getDescribe());
/* 1335 */         errorLog.setProductlinecode(ediProduction.getProductlinecode());
/* 1336 */         errorLog.setShiftcode(ediProduction.getShiftcode());
/* 1337 */         errorLog.setStaffcode(ediProduction.getStaffcode());
/* 1338 */         errorLog.setTaskDate(ediProduction.getTaskDate());
/* 1339 */         errorLog.setTime(ediProduction.getTime());
/* 1340 */         errorLog.setSyncTime(ediProduction.getSyncTime());
/* 1341 */         errorLog.setQty(ediProduction.getQty());
/* 1342 */         errorLog.setType(ediProduction.getType());
/* 1343 */         errorLog.setUnit(ediProduction.getUnit());
/* 1344 */         errorLog.setErrorInfo("分解失败,ASN与总成对应关系找不到" + ediProduction.getAsnNo());
/* 1345 */         errorLogList.add(errorLog);
/*      */       }
/* 1347 */       catch (Exception e) {
/*      */         
/* 1349 */         e.printStackTrace();
/*      */       } 
/*      */     } 
/* 1352 */     if (errorLogList.size() != 0)
/* 1353 */       for (EdiProductionErrorLog ediProductionErrorLog : errorLogList) {
/* 1354 */         this.dao.saveObject(ediProductionErrorLog);
/* 1355 */         this.dao.commit();
/*      */       }  
/*      */   }
/*      */   
/*      */   public void insertJitProductionPlan(List<JitProductionPlan> jitProductionPlanList) {
/* 1360 */     for (JitProductionPlan jitProductionPlan : jitProductionPlanList) {
/* 1361 */       if (jitProductionPlan.getSign().intValue() == 0) {
/* 1362 */         this.dao.saveObject(jitProductionPlan);
/* 1363 */         this.dao.commit(); continue;
/*      */       } 
/* 1365 */       insertJitProductionPlan(jitProductionPlan.getJitProductionPlanList());
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/quartz/job/RedMinuteSyncJob.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
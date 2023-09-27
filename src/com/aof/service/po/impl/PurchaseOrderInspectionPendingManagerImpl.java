/*     */ package com.aof.service.po.impl;
/*     */ 
/*     */ import com.aof.dao.po.PurchaseOrderInspectionPendingDAO;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.basic.FPSMaterialPrice;
/*     */ import com.aof.model.basic.SupplierPart;
/*     */ import com.aof.model.metadata.CurrencyType;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.po.ProduceRejectedMaterial;
/*     */ import com.aof.model.po.PurchaseOrderInspectionPending;
/*     */ import com.aof.model.po.PurchaseOrderInspectionPendingItem;
/*     */ import com.aof.model.po.PurchaseOrderReceipts;
/*     */ import com.aof.model.po.PurchaseOrderReceiptsDetial;
/*     */ import com.aof.model.po.query.PurchaseOrderInspectionPendingQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.basic.FPSMaterialPriceManager;
/*     */ import com.aof.service.po.BoxManager;
/*     */ import com.aof.service.po.PurchaseOrderInspectionPendingManager;
/*     */ import com.aof.web.struts.action.ActionUtils2;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
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
/*     */ public class PurchaseOrderInspectionPendingManagerImpl
/*     */   extends BaseManager
/*     */   implements PurchaseOrderInspectionPendingManager
/*     */ {
/*     */   private PurchaseOrderInspectionPendingDAO dao;
/*     */   private BoxManager boxManager;
/*     */   private FPSMaterialPriceManager fpsMaterialPriceManager;
/*     */   
/*     */   public void setDao(PurchaseOrderInspectionPendingDAO dao) {
/*  52 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFpsMaterialPriceManager(FPSMaterialPriceManager fpsMaterialPriceManager) {
/*  57 */     this.fpsMaterialPriceManager = fpsMaterialPriceManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPurchaseOrderInspectionPendingDAO(PurchaseOrderInspectionPendingDAO dao) {
/*  64 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderInspectionPending getPurchaseOrderInspectionPending(String id) {
/*  71 */     return this.dao.getPurchaseOrderInspectionPending(id);
/*     */   }
/*     */   
/*     */   public void setBoxManager(BoxManager boxManager) {
/*  75 */     this.boxManager = boxManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderInspectionPending updatePurchaseOrderInspectionPending(PurchaseOrderInspectionPending po) {
/*  82 */     return this.dao.updatePurchaseOrderInspectionPending(po);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderInspectionPending insertPurchaseOrderInspectionPending(PurchaseOrderInspectionPending po) {
/*  89 */     po.setId(getLastCode(po.getSite(), po.getCreateDate()));
/*  90 */     if (po.getIsPrint() == null) {
/*  91 */       po.setIsPrint(YesNo.NO);
/*     */     }
/*  93 */     return this.dao.insertPurchaseOrderInspectionPending(po);
/*     */   }
/*     */   public PurchaseOrderInspectionPending insertPurchaseOrderInspectionPendingPONumber(PurchaseOrderInspectionPending po) {
/*  96 */     return this.dao.insertPurchaseOrderInspectionPending(po);
/*     */   }
/*     */   
/*     */   public PurchaseOrderInspectionPending insertPurchaseOrderInspectionPending(PurchaseOrderInspectionPending po, Date date) {
/* 100 */     po.setId(getLastCode(po.getSite(), date));
/* 101 */     if (po.getIsPrint() == null) {
/* 102 */       po.setIsPrint(YesNo.NO);
/*     */     }
/* 104 */     return this.dao.insertPurchaseOrderInspectionPending(po);
/*     */   }
/*     */   private String getLastCode(Site site, Date date) {
/* 107 */     StringBuffer sb = new StringBuffer("POI");
/* 108 */     String siteId = site.getId().toString();
/* 109 */     for (int i = 0; i < 3 - siteId.length(); i++)
/* 110 */       sb.append('0'); 
/* 111 */     sb.append(siteId);
/* 112 */     sb.append(StringUtils.right(ActionUtils2.get8CharsFromDate(date), 6));
/* 113 */     String prefix = sb.toString();
/* 114 */     String maxId = this.dao.getMaxPoApplicationIdBeginWith(prefix);
/*     */     
/* 116 */     int serialNo = 1;
/* 117 */     if (maxId != null) {
/*     */       
/* 119 */       Integer maxSN = ActionUtils2.parseInt(StringUtils.right(maxId, 5));
/* 120 */       if (maxSN == null) throw new RuntimeException("max serial no. is not digit"); 
/* 121 */       serialNo = maxSN.intValue() + 1;
/*     */     } 
/* 123 */     String sn = String.valueOf(serialNo);
/* 124 */     for (int j = 0; j < 5 - sn.length(); j++)
/* 125 */       sb.append('0'); 
/* 126 */     sb.append(sn);
/* 127 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPurchaseOrderInspectionPendingListCount(Map conditions) {
/* 134 */     return this.dao.getPurchaseOrderInspectionPendingListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPurchaseOrderInspectionPendingList(Map conditions, int pageNo, int pageSize, PurchaseOrderInspectionPendingQueryOrder order, boolean descend) {
/* 142 */     return this.dao.getPurchaseOrderInspectionPendingList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void promotePurchaseOrderInspectionPending(PurchaseOrderInspectionPending city) {
/* 149 */     updatePurchaseOrderInspectionPending(city);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean deletePurchaseOrderInspectionPending(PurchaseOrderInspectionPending city) {
/*     */     try {
/* 155 */       this.dao.deletePurchaseOrderInspectionPending(city);
/* 156 */       return true;
/*     */     }
/* 158 */     catch (Throwable t) {
/*     */       
/* 160 */       return false;
/*     */     } 
/*     */   }
/*     */   public List getPurchaseOrderInspectionPendingListEnabledAll() {
/* 164 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 165 */     return this.dao.getPurchaseOrderInspectionPendingList(conditions, -1, -1, null, true);
/*     */   }
/*     */   
/*     */   public void deletePurchaseOrderInspectionPendingItem(PurchaseOrderInspectionPendingItem poi) {
/* 169 */     this.dao.removeObject(poi);
/*     */   }
/*     */   
/*     */   public List getPurchaseOrderInspectionPendingItemList(String poid) {
/* 173 */     return this.dao.getObjectList("from PurchaseOrderInspectionPendingItem poi where poi.poip_number.poip_number='" + poid + "' order by poi.line");
/*     */   }
/*     */   
/*     */   public void insertPurchaseOrderInspectionPendingItem(PurchaseOrderInspectionPendingItem poi) {
/* 177 */     this.dao.saveObject(poi);
/*     */   }
/*     */   
/*     */   public void updatePurchaseOrderInspectionPendingItem(Object o) {
/* 181 */     this.dao.updateObject(o);
/*     */   }
/*     */   
/*     */   public PurchaseOrderInspectionPendingItem getPurchaseOrderInspectionPendingItem(Integer id) {
/* 185 */     return (PurchaseOrderInspectionPendingItem)this.dao.getObject(PurchaseOrderInspectionPendingItem.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPurchaseOrderInspectionPendingItemList(Map conditions, int pageNo, int pageSize, PurchaseOrderInspectionPendingQueryOrder order, boolean descend) {
/* 191 */     return this.dao.getPurchaseOrderInspectionPendingItemList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */   
/*     */   public int getPurchaseOrderInspectionPendingItemListCount(Map conditions) {
/* 195 */     return this.dao.getPurchaseOrderInspectionPendingItemListCount(conditions);
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertBox(Box poi) {
/* 200 */     poi.setNumber(poi.getNumber().setScale(4, 4));
/* 201 */     this.dao.saveObject(poi);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateBox(Box poi) {
/* 206 */     this.dao.updateObject(poi);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteBox(Box poi) {
/* 211 */     this.dao.removeObject(poi);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getBoxList(String poid) {
/* 217 */     return this.dao.getObjectList("from Box box where box.poritem.poip_item_id.id='" + poid + "'");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getBoxList(String lotser, String boxNumber, YesNo isReceipt) {
/* 223 */     return this.dao.getObjectList("from Box box where box.poItem.lotSer.id='" + lotser + "' and box.number='" + boxNumber + "' and box.isReceipt='" + isReceipt.getEnumCode() + "'");
/*     */   }
/*     */ 
/*     */   
/*     */   public Box getBox(Integer id) {
/* 228 */     return (Box)this.dao.getObject(Box.class, id);
/*     */   }
/*     */   
/*     */   public List getBoxListByReceipt(String poid) {
/* 232 */     return this.dao.getObjectList("from Box box where box.poritem.id='" + poid + "'");
/*     */   }
/*     */ 
/*     */   
/*     */   public List getBoxList(Integer poItemid, String boxNumber) {
/* 237 */     return this.dao.getBoxList(poItemid, boxNumber);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getPurchaseOrderInspectionPendingItemListByPoipId(String poipId, String partId) {
/* 242 */     return this.dao.getPurchaseOrderInspectionPendingItemListByPoipId(poipId, partId);
/*     */   }
/*     */ 
/*     */   
/*     */   public PurchaseOrderInspectionPendingItem updatePurchaseOrderInspectionPendingItem2(PurchaseOrderInspectionPendingItem poi) {
/* 247 */     this.dao.updateObject(poi);
/* 248 */     return poi;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderInspectionPendingItem updatePurchaseOrderInspectionPendingItem3(PurchaseOrderInspectionPendingItem poipItem) {
/* 254 */     return this.dao.updatePurchaseOrderInspectionPendingItem3(poipItem);
/*     */   }
/*     */   
/*     */   public List getBoxListByRQC(String poid) {
/* 258 */     return this.dao.getObjectList("from Box box where box.rqcitem.id='" + poid + "'");
/*     */   }
/*     */ 
/*     */   
/*     */   public List getBoxListByInbound(int qcId) {
/* 263 */     return this.dao.getObjectList("from Box box where box.poInboundItem.id='" + qcId + "' and (box.isReturn = 1 or box.isReturn is null)");
/*     */   }
/*     */ 
/*     */   
/*     */   public List getBoxListByInbound2(int qcId) {
/* 268 */     return this.dao.getObjectList("from Box box where box.poInboundItem.id='" + qcId + "' ");
/*     */   }
/*     */ 
/*     */   
/*     */   public List getBoxListByInbound2(String qcId) {
/* 273 */     return this.dao.getObjectList("from Box box where box.rqcitem.qc_number.qc_number='" + qcId + "' and box.quality is not null");
/*     */   }
/*     */ 
/*     */   
/*     */   public List getBoxListByReturn(String poReturnId) {
/* 278 */     return this.dao.getObjectList("from Box box where box.poReturnsItem.returnsId.rtvNbr ='" + poReturnId + "' and box.quality = 1");
/*     */   }
/*     */ 
/*     */   
/*     */   public List getBoxListByReturnItem(int poReturnItemId) {
/* 283 */     return this.dao.getObjectList("from Box box where box.poReturnsItem.id ='" + poReturnItemId + "' and box.quality = 1 and (box.concession = 1 or box.concession is null)");
/*     */   }
/*     */ 
/*     */   
/*     */   public List getBoxListByRQC2(String poid) {
/* 288 */     return this.dao.getObjectList("from Box box where box.rqcitem.id='" + poid + "' and box.quality = 1");
/*     */   }
/*     */ 
/*     */   
/*     */   public List getBoxListByRQCNotQC(String poid) {
/* 293 */     return this.dao.getObjectList("from Box box where box.rqcitem.id='" + poid + "' and box.isQuality = 1");
/*     */   }
/*     */ 
/*     */   
/*     */   public List<PurchaseOrderInspectionPendingItem> getPurchaseOrderInspectionPendingItemListByReceiving(String poid) {
/* 298 */     return this.dao.getPurchaseOrderInspectionPendingItemListByReceiving(poid);
/*     */   }
/*     */ 
/*     */   
/*     */   public void createPurchaseOrderReceiptsItemList(List<PurchaseOrderInspectionPendingItem> itemlist) {
/* 303 */     PurchaseOrderReceipts por = null;
/*     */     
/* 305 */     for (PurchaseOrderInspectionPendingItem item : itemlist) {
/* 306 */       BigDecimal waitQuantity = item.getWaitQuantityTemp();
/* 307 */       if (waitQuantity != null) {
/* 308 */         item.setQtyOpen(item.getQtyOpen().subtract(waitQuantity));
/* 309 */         item.setReceivingDate(null);
/* 310 */         item.setIsReceiving(YesNo.NO);
/* 311 */         updatePurchaseOrderInspectionPendingItem(item);
/*     */         
/* 313 */         updatePurchaseOrderInspectionPendingItem(item);
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
/* 326 */         PurchaseOrderReceiptsDetial pori = new PurchaseOrderReceiptsDetial();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 333 */         this.dao.saveObject(pori);
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
/*     */   public PurchaseOrderReceipts insertPurchaseOrderReceipts(PurchaseOrderReceipts po) {
/* 352 */     this.dao.saveObject(po);
/* 353 */     return (PurchaseOrderReceipts)this.dao.getObject(PurchaseOrderReceipts.class, po.getId());
/*     */   }
/*     */ 
/*     */   
/*     */   private String getLastCodeReceipts(Site site, Date date) {
/* 358 */     StringBuffer sb = new StringBuffer("DN");
/* 359 */     String siteId = site.getId().toString();
/* 360 */     for (int i = 0; i < 3 - siteId.length(); i++)
/* 361 */       sb.append('0'); 
/* 362 */     sb.append(siteId);
/* 363 */     sb.append(StringUtils.right(ActionUtils2.get8CharsFromDate(date), 6));
/* 364 */     String prefix = sb.toString();
/* 365 */     String maxId = this.dao.getMaxPoApplicationIdBeginWithReceipts(prefix);
/*     */     
/* 367 */     int serialNo = 1;
/* 368 */     if (maxId != null) {
/* 369 */       Integer maxSN = ActionUtils2.parseInt(StringUtils.right(maxId, 5));
/* 370 */       if (maxSN == null)
/* 371 */         throw new RuntimeException("max serial no. is not digit"); 
/* 372 */       serialNo = maxSN.intValue() + 1;
/*     */     } 
/* 374 */     String sn = String.valueOf(serialNo);
/* 375 */     for (int j = 0; j < 5 - sn.length(); j++)
/* 376 */       sb.append('0'); 
/* 377 */     sb.append(sn);
/* 378 */     return sb.toString();
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
/*     */   private void insertBox(Integer boxcount, Double surplusNumber, Double boxInteger, String poCode, String row, PurchaseOrderReceiptsDetial receiptsItem) {}
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
/*     */   public Boolean isclosePurchaseOrderInspectionPendingByItem(PurchaseOrderInspectionPending poip) {
/* 429 */     List<Integer> itemCount = this.dao.getObjectList("select count(*) from PurchaseOrderInspectionPendingItem item where item.poip_number.id='" + poip.getId() + "'");
/*     */     
/* 431 */     List<Integer> overItemCount = this.dao.getObjectList("select count(*) from PurchaseOrderInspectionPendingItem item where item.poip_number.id='" + poip.getId() + "' and item.qtyOpen=0");
/* 432 */     return Boolean.valueOf(itemCount.equals(overItemCount));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean isconfirmPurchaseOrderInspectionPendingByItem(PurchaseOrderInspectionPending poip) {
/* 439 */     List<Integer> itemCount = this.dao.getObjectList("select count(*) from PurchaseOrderInspectionPendingItem item where item.poip_number.id='" + poip.getId() + "'");
/* 440 */     List<Integer> overItemCount = this.dao.getObjectList("select count(*) from PurchaseOrderInspectionPendingItem item where item.poip_number.id='" + poip.getId() + "' and item.isViewItem=0");
/* 441 */     return Boolean.valueOf(itemCount.equals(overItemCount));
/*     */   }
/*     */   
/*     */   public void insertBox(String supplierCode, Date date, Integer boxCount, Integer BoxId) {
/* 445 */     DecimalFormat df = new DecimalFormat("0000");
/* 446 */     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
/* 447 */     String dates = dateFormat.format(date);
/*     */     
/* 449 */     List list = this.dao.getObjectList("select max(box.number) from Box box where box.lotSer.id like" +
/* 450 */         supplierCode + dates + " ");
/* 451 */     String maxnumber = "";
/* 452 */     if (list.size() == 0) {
/* 453 */       maxnumber = "0";
/*     */     }
/* 455 */     else if (list.get(0) == null) {
/* 456 */       maxnumber = "0";
/*     */     } else {
/* 458 */       maxnumber = list.get(0).toString();
/*     */     } 
/*     */ 
/*     */     
/* 462 */     Integer maxnumberinteger = Integer.valueOf(Integer.parseInt(maxnumber));
/* 463 */     maxnumberinteger = Integer.valueOf(maxnumberinteger.intValue() + 1);
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
/*     */   public List<BigDecimal> getBasicPOPartAmount(List<String> dateList, Integer siteId) {
/* 480 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
/* 481 */     SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd hh:mm");
/* 482 */     List<BigDecimal> amountList = new ArrayList<BigDecimal>();
/* 483 */     for (String date : dateList) {
/* 484 */       BigDecimal amount = new BigDecimal(0);
/* 485 */       Site site = (Site)this.dao.getObject(Site.class, siteId);
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
/* 498 */       amountList.add(amount);
/*     */     } 
/* 500 */     return amountList;
/*     */   }
/*     */   
/*     */   public List<BigDecimal> getBasicPOPartAmount(List<String> dateList) {
/* 504 */     List<BigDecimal> amountList = new ArrayList<BigDecimal>();
/* 505 */     for (String date : dateList) {
/* 506 */       BigDecimal amount = new BigDecimal(0);
/*     */       try {
/* 508 */         List<BigDecimal> amountSqlList = this.dao.getObjectList("select sum(po.qty) from PurchaseOrderInspectionPendingItem po where po.dueDate >='" + date + " 00:00'  and po.dueDate <='" + date + " 23:59'");
/* 509 */         if (amountSqlList.size() > 0 && amountSqlList.get(0) != null) {
/* 510 */           amount = amountSqlList.get(0);
/*     */         }
/* 512 */       } catch (Exception e) {
/* 513 */         e.fillInStackTrace();
/*     */       } 
/* 515 */       amountList.add(amount);
/*     */     } 
/* 517 */     return amountList;
/*     */   }
/*     */   
/*     */   public List<PurchaseOrderInspectionPendingItem> getPurchaseOrderInspectionPendingItemReport(List<PurchaseOrderInspectionPendingItem> itemList) {
/* 521 */     for (PurchaseOrderInspectionPendingItem item : itemList) {
/* 522 */       item.setReceiptQty(getBoxQtyAmount(item.getId(), "receivedNumber"));
/* 523 */       item.setInventoryNumber(getBoxQtyAmount(item.getId(), "inStorageNumber"));
/* 524 */       item.setReturnNumber(getBoxQtyAmount(item.getId(), "vetoQCnumber"));
/*     */     } 
/* 526 */     return itemList;
/*     */   }
/*     */   private BigDecimal getBoxQtyAmount(Integer itemid, String field) {
/* 529 */     BigDecimal orderNumber = new BigDecimal(0);
/* 530 */     String orderNumberSQL = "select sum(box." + field + ") from Box box where box.psoItem.poipItem.id='" + itemid + "' ";
/*     */     
/* 532 */     List<BigDecimal> orderNumberList = this.dao.getObjectList(orderNumberSQL);
/* 533 */     if (orderNumberList != null && orderNumberList.size() > 0 && orderNumberList.get(0) != null) {
/* 534 */       orderNumber = orderNumberList.get(0);
/*     */     }
/* 536 */     return orderNumber;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Map> getPurchaseOrderInspectionPendingItemAndSuppliers(String array) {
/* 541 */     String[] arrays = array.split(",");
/* 542 */     List<Map<Object, Object>> listMain = new ArrayList();
/* 543 */     Map<Object, Object> mapMain = new HashMap<Object, Object>(); byte b; int i; String[] arrayOfString1;
/* 544 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 545 */       mapMain = new HashMap<Object, Object>();
/* 546 */       PurchaseOrderInspectionPendingItem item = getPurchaseOrderInspectionPendingItem(Integer.valueOf(Integer.parseInt(id)));
/*     */       
/* 548 */       String sql = "from SupplierPart sp where sp.partId.id='" + item.getItemNumber().getId() + "'";
/* 549 */       List<SupplierPart> supplierParts = this.dao.getObjectList(sql);
/* 550 */       if (supplierParts.size() > 0) {
/* 551 */         if (supplierParts.size() > 1) {
/* 552 */           String sqlPoSum = "select sum(pos.amount) from PurchaseOrderSupplier pos where pos.poId.itemNumber.id = '" + item.getItemNumber().getId() + "' ";
/* 553 */           List<Object> listPosSum = this.dao.getObjectList(sqlPoSum);
/* 554 */           BigDecimal poSum = new BigDecimal(0);
/* 555 */           Map<Object, Object> map = new HashMap<Object, Object>();
/* 556 */           List<Map> listMap = new ArrayList<Map>();
/* 557 */           if (listPosSum.get(0) != null) {
/* 558 */             poSum = (BigDecimal)listPosSum.get(0);
/*     */             
/* 560 */             BigDecimal suppSum = new BigDecimal(0);
/* 561 */             for (SupplierPart supplierPart : supplierParts) {
/* 562 */               String sqlPos = "select sum(pos.amount) from PurchaseOrderSupplier pos where pos.supplier.id='" + supplierPart.getSupplierId().getId() + "'" + 
/* 563 */                 " and pos.poId.itemNumber.id = '" + item.getItemNumber().getId() + "' and pos.line = " + item.getLine() + " ";
/*     */               
/* 565 */               List<Object> list = this.dao.getObjectList(sqlPos);
/* 566 */               if (list.get(0) != null) {
/* 567 */                 suppSum = (BigDecimal)list.get(0);
/*     */               }
/* 569 */               BigDecimal proportion = validateBigDecimal(supplierPart.getProportion());
/* 570 */               String partId = supplierPart.getPartId().getId();
/* 571 */               Integer suppId = supplierPart.getSupplierId().getId();
/*     */ 
/*     */               
/* 574 */               BigDecimal shouldAmount = poSum.multiply(proportion).multiply(new BigDecimal(0.1D));
/*     */               
/* 576 */               if (shouldAmount.compareTo(suppSum) == 1) {
/* 577 */                 map = new HashMap<Object, Object>();
/* 578 */                 map.put("supplierPart", supplierPart);
/* 579 */                 listMap.add(0, map);
/*     */               } else {
/* 581 */                 map = new HashMap<Object, Object>();
/* 582 */                 map.put("supplierPart", supplierPart);
/* 583 */                 listMap.add(map);
/*     */               } 
/* 585 */               mapMain.put("proportion", proportion);
/* 586 */               mapMain.put("price", getSupplierPrice(partId, suppId));
/*     */             } 
/*     */           } else {
/*     */             
/* 590 */             for (SupplierPart supplierPart : supplierParts) {
/* 591 */               map = new HashMap<Object, Object>();
/* 592 */               map.put("supplierPart", supplierPart);
/* 593 */               listMap.add(map);
/*     */               
/* 595 */               mapMain.put("proportion", supplierPart.getProportion());
/* 596 */               mapMain.put("price", getSupplierPrice(supplierPart.getPartId().getId(), supplierPart.getSupplierId().getId()));
/*     */             } 
/*     */           } 
/*     */           
/* 600 */           mapMain.put("suppliers", listMap);
/*     */         } else {
/* 602 */           List<Map> listMap = new ArrayList<Map>();
/* 603 */           Map<Object, Object> map = new HashMap<Object, Object>();
/* 604 */           map.put("supplierPart", supplierParts.get(0));
/* 605 */           listMap.add(map);
/*     */           
/* 607 */           mapMain.put("suppliers", listMap);
/* 608 */           if (supplierParts.size() > 0) {
/* 609 */             mapMain.put("proportion", ((SupplierPart)supplierParts.get(0)).getProportion());
/*     */           } else {
/* 611 */             mapMain.put("proportion", Integer.valueOf(0));
/*     */           } 
/* 613 */           mapMain.put("price", getSupplierPrice(((SupplierPart)supplierParts.get(0)).getPartId().getId(), ((SupplierPart)supplierParts.get(0)).getSupplierId().getId()));
/*     */         } 
/*     */       }
/*     */       
/* 617 */       mapMain.put("item", item);
/* 618 */       listMain.add(mapMain);
/*     */       b++; }
/*     */     
/* 621 */     return (List)listMain;
/*     */   }
/*     */   
/*     */   public BigDecimal getSupplierPrice(String partId, Integer suppId) {
/* 625 */     BigDecimal decimal = new BigDecimal(0);
/* 626 */     FPSMaterialPrice price = this.fpsMaterialPriceManager.getFPSMaterialPriceByPartAndSupp(partId, suppId);
/* 627 */     if (price == null) {
/* 628 */       decimal = new BigDecimal(0);
/*     */     } else {
/* 630 */       decimal = price.getUnitPrice();
/*     */     } 
/* 632 */     return decimal;
/*     */   }
/*     */   
/*     */   public BigDecimal validateBigDecimal(BigDecimal decimal) {
/* 636 */     if (decimal == null) {
/* 637 */       decimal = new BigDecimal(0);
/*     */     }
/*     */     
/* 640 */     return decimal;
/*     */   }
/*     */   
/*     */   public void insertPurchaseOrderSupplier(String array) {
/* 644 */     String[] arrays = array.split(";"); byte b; int i; String[] arrayOfString1;
/* 645 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String str = arrayOfString1[b];
/* 646 */       String[] poAndStrings = str.split(",");
/* 647 */       String itemId = poAndStrings[0];
/* 648 */       String suppId = poAndStrings[1];
/*     */       
/* 650 */       String line = poAndStrings[2];
/*     */       
/* 652 */       PurchaseOrderInspectionPendingItem item = (PurchaseOrderInspectionPendingItem)this.dao.getObject(PurchaseOrderInspectionPendingItem.class, Integer.valueOf(Integer.parseInt(itemId)));
/* 653 */       Supplier supplier = (Supplier)this.dao.getObject(Supplier.class, Integer.valueOf(Integer.parseInt(suppId)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 663 */       item.getPoip_number().setSupplier(supplier);
/* 664 */       this.dao.updatePurchaseOrderInspectionPending(item.getPoip_number());
/*     */ 
/*     */       
/* 667 */       this.dao.updatePurchaseOrderInspectionPendingItem2(item);
/*     */       b++; }
/*     */   
/*     */   }
/*     */   public void updatePurchaseOrderInspectionPendingItemStatus(String array) {
/* 672 */     String[] arrays = array.split(","); byte b; int i; String[] arrayOfString1;
/* 673 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 674 */       PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem = getPurchaseOrderInspectionPendingItem(Integer.valueOf(Integer.parseInt(id)));
/*     */       b++; }
/*     */   
/*     */   }
/*     */   
/*     */   public List updatePurchaseOrderInspectionPendingItem(List<PurchaseOrderInspectionPendingItem> items) {
/* 680 */     List<PurchaseOrderInspectionPendingItem> newItems = new ArrayList<PurchaseOrderInspectionPendingItem>();
/* 681 */     for (PurchaseOrderInspectionPendingItem item : items) {
/* 682 */       String sql = "from FPSMaterialPrice fmp where fmp.wmsPart.id = '" + item.getItemNumber().getId() + "'" + 
/* 683 */         " and fmp.supplier.code='" + item.getPoip_number().getSupplier().getCode() + "' ";
/* 684 */       List<FPSMaterialPrice> list = this.dao.getObjectList(sql);
/*     */       
/* 686 */       if (list.size() > 0) {
/* 687 */         FPSMaterialPrice price = list.get(0);
/* 688 */         item.setPeric(price.getUnitPrice());
/* 689 */         item.setCurrencyType(price.getCurrencyType());
/*     */       } else {
/* 691 */         item.setPeric(new BigDecimal(0));
/* 692 */         item.setCurrencyType(CurrencyType.YUAN);
/*     */       } 
/*     */       
/* 695 */       newItems.add(item);
/*     */     } 
/*     */     
/* 698 */     return newItems;
/*     */   }
/*     */   
/*     */   public List updatePurchaseOrderInspectionPending(List<PurchaseOrderInspectionPending> list) {
/* 702 */     List<PurchaseOrderInspectionPending> listPo = new ArrayList<PurchaseOrderInspectionPending>();
/* 703 */     for (PurchaseOrderInspectionPending po : list) {
/* 704 */       String sqlQty = "select sum(item.qty) from PurchaseOrderInspectionPendingItem item where item.poip_number.poip_number='" + po.getPoip_number() + "' ";
/* 705 */       String sqlReceiptQty = "select sum(item.receiptQty) from PurchaseOrderInspectionPendingItem item where item.poip_number.poip_number='" + po.getPoip_number() + "' ";
/* 706 */       String sqlInventoryNumber = "select sum(item.inventoryNumber) from PurchaseOrderInspectionPendingItem item where item.poip_number.poip_number='" + po.getPoip_number() + "' ";
/*     */       
/* 708 */       List<Object> listQty = this.dao.getObjectList(sqlQty);
/* 709 */       List<Object> listReceiptQty = this.dao.getObjectList(sqlReceiptQty);
/* 710 */       List<Object> listInventoryNumber = this.dao.getObjectList(sqlInventoryNumber);
/*     */       
/* 712 */       if (listQty.size() > 0 && listQty.get(0) != null) {
/* 713 */         BigDecimal objQty = (BigDecimal)listQty.get(0);
/* 714 */         po.setQty(objQty);
/*     */       } 
/* 716 */       if (listReceiptQty.size() > 0 && listReceiptQty.get(0) != null) {
/* 717 */         BigDecimal objReceiptQty = (BigDecimal)listReceiptQty.get(0);
/* 718 */         po.setReceiptQty(objReceiptQty);
/*     */       } 
/* 720 */       if (listInventoryNumber.size() > 0 && listInventoryNumber.get(0) != null) {
/* 721 */         BigDecimal objInventoryNumber = (BigDecimal)listInventoryNumber.get(0);
/* 722 */         po.setInventoryNumber(objInventoryNumber);
/*     */       } 
/*     */       
/* 725 */       listPo.add(po);
/*     */     } 
/*     */     
/* 728 */     return listPo;
/*     */   }
/*     */   
/*     */   public PurchaseOrderInspectionPendingItem getPurchaseOrderInspectionPendingItem(PurchaseOrderInspectionPendingItem item) {
/* 732 */     item.setReceiptQty(getBoxQtyAmount(item.getId(), "receivedNumber"));
/* 733 */     item.setInventoryNumber(getBoxQtyAmount(item.getId(), "inStorageNumber"));
/* 734 */     item.setReturnNumber(getBoxQtyAmount(item.getId(), "vetoQCnumber"));
/*     */     
/* 736 */     return item;
/*     */   }
/*     */   
/*     */   public List<ProduceRejectedMaterial> getPurchaseOrderInspectionPendingItemByPrintReport(List<ProduceRejectedMaterial> itemList) {
/* 740 */     for (ProduceRejectedMaterial material : itemList) {
/* 741 */       material.getBox().getPsoItem().getPoipItem().setReceiptQty(getBoxQtyAmount(material.getBox().getPsoItem().getPoipItem().getId(), "receivedNumber"));
/* 742 */       material.getBox().getPsoItem().getPoipItem().setInventoryNumber(getBoxQtyAmount(material.getBox().getPsoItem().getPoipItem().getId(), "inStorageNumber"));
/* 743 */       material.getBox().getPsoItem().getPoipItem().setReturnNumber(getBoxQtyAmount(material.getBox().getPsoItem().getPoipItem().getId(), "vetoQCnumber"));
/*     */     } 
/* 745 */     return itemList;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMaxPONumber() {
/* 750 */     return this.dao.getMaxPONumber();
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/impl/PurchaseOrderInspectionPendingManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
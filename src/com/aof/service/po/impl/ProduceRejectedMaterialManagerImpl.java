/*     */ package com.aof.service.po.impl;
/*     */ 
/*     */ import com.aof.dao.basic.ScanLogDAO;
/*     */ import com.aof.dao.po.ProduceRejectedMaterialDAO;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.ScanLog;
/*     */ import com.aof.model.inventory.InventoryTransit;
/*     */ import com.aof.model.inventory.query.InventoryTransitQueryCondition;
/*     */ import com.aof.model.metadata.BoxStatus;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.InventoryType;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.po.PortalShipOrderItem;
/*     */ import com.aof.model.po.ProduceRejectedMaterial;
/*     */ import com.aof.model.po.PurchaseOrderCondimentSingle;
/*     */ import com.aof.model.po.PurchaseOrderInspectionPendingItem;
/*     */ import com.aof.model.po.query.PortalShipOrderQueryCondition;
/*     */ import com.aof.model.po.query.ProduceRejectedMaterialQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.inventory.InventoryManager;
/*     */ import com.aof.service.inventory.InventoryTransitManager;
/*     */ import com.aof.service.po.BoxManager;
/*     */ import com.aof.service.po.PortalShipOrderItemManager;
/*     */ import com.aof.service.po.ProduceRejectedMaterialManager;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProduceRejectedMaterialManagerImpl
/*     */   extends BaseManager
/*     */   implements ProduceRejectedMaterialManager
/*     */ {
/*     */   private ProduceRejectedMaterialDAO dao;
/*     */   private BoxManager boxManager;
/*     */   private InventoryManager inventoryManager;
/*     */   private InventoryTransitManager inventoryTransitManager;
/*     */   private PortalShipOrderItemManager portalShipOrderItemManager;
/*     */   private ScanLogDAO scanLogDAO;
/*     */   
/*     */   public void setScanLogDAO(ScanLogDAO scanLogDAO) {
/*  47 */     this.scanLogDAO = scanLogDAO;
/*     */   }
/*     */   
/*     */   public PortalShipOrderItemManager getPortalShipOrderItemManager() {
/*  51 */     return this.portalShipOrderItemManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPortalShipOrderItemManager(PortalShipOrderItemManager portalShipOrderItemManager) {
/*  56 */     this.portalShipOrderItemManager = portalShipOrderItemManager;
/*     */   }
/*     */   
/*     */   public InventoryTransitManager getInventoryTransitManager() {
/*  60 */     return this.inventoryTransitManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInventoryTransitManager(InventoryTransitManager inventoryTransitManager) {
/*  65 */     this.inventoryTransitManager = inventoryTransitManager;
/*     */   }
/*     */   
/*     */   public void setInventoryManager(InventoryManager inventoryManager) {
/*  69 */     this.inventoryManager = inventoryManager;
/*     */   }
/*     */   
/*     */   public void setBoxManager(BoxManager boxManager) {
/*  73 */     this.boxManager = boxManager;
/*     */   }
/*     */   
/*     */   public void setDao(ProduceRejectedMaterialDAO dao) {
/*  77 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   
/*     */   public ProduceRejectedMaterial getProduceRejectedMaterial(Integer id) {
/*  82 */     return this.dao.getProduceRejectedMaterial(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getProduceRejectedMaterialListCount(Map conditions) {
/*  87 */     return this.dao.getProduceRejectedMaterialListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getProduceRejectedMaterialList(Map conditions, int pageNo, int pageSize, ProduceRejectedMaterialQueryOrder order, boolean descend) {
/*  94 */     return this.dao.getProduceRejectedMaterialList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ProduceRejectedMaterial insertProduceRejectedMaterial(ProduceRejectedMaterial po) {
/* 100 */     return this.dao.insertProduceRejectedMaterial(po);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ProduceRejectedMaterial updateProduceRejectedMaterial(ProduceRejectedMaterial po) {
/* 106 */     return this.dao.updateProduceRejectedMaterial(po);
/*     */   }
/*     */   
/*     */   public String updateProduceRejectedMaterial(String array, User user) {
/* 110 */     String[] arrays = array.split(","); byte b; int i; String[] arrayOfString1;
/* 111 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 112 */       Box box = this.boxManager.getBox(Integer.valueOf(Integer.parseInt(id)));
/*     */       
/* 114 */       ScanLog scanLog = new ScanLog();
/* 115 */       scanLog.setDate(new Date());
/* 116 */       scanLog.setDescribe(box.getLot().getId());
/* 117 */       scanLog.setType(Integer.valueOf(41));
/* 118 */       if (user != null) {
/* 119 */         scanLog.setUserId(user);
/*     */       }
/* 121 */       this.scanLogDAO.insertScanLog(scanLog);
/*     */       
/*     */       try {
/* 124 */         boolean sign = false;
/*     */         
/* 126 */         PurchaseOrderCondimentSingle single = box.getSingle();
/* 127 */         PortalShipOrderItem orderItem = box.getPsoItem();
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
/* 146 */         if (single != null) {
/*     */           
/* 148 */           single.setNumber(single.getNumber().subtract(box.getNumber()));
/* 149 */           single.setDelivery_qty(single.getNumber());
/* 150 */           this.dao.updateObject(single);
/* 151 */           sign = true;
/* 152 */         } else if (single == null) {
/* 153 */           PurchaseOrderInspectionPendingItem item = box.getPsoItem().getPoipItem();
/*     */           
/* 155 */           if (item != null) {
/* 156 */             item.setReceiptQty(item.getReceiptQty().subtract(box.getNumber()));
/* 157 */             item.setQtyOpen(item.getQtyOpen().add(box.getNumber()));
/* 158 */             this.dao.updateObject(item);
/* 159 */             sign = true;
/*     */           } else {
/* 161 */             sign = false;
/*     */           } 
/* 163 */           PortalShipOrderItem portalShipOrderItem = box.getPsoItem();
/* 164 */           portalShipOrderItem.setDeliveryNumber(portalShipOrderItem.getDeliveryNumber().subtract(box.getNumber()));
/* 165 */           this.portalShipOrderItemManager.updatePortalShipOrderItem(portalShipOrderItem);
/*     */         } 
/*     */         
/* 168 */         if (sign) {
/*     */           
/* 170 */           this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.RETURN, Boolean.valueOf(true));
/*     */ 
/*     */           
/* 173 */           Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 174 */           conditions.put(InventoryTransitQueryCondition.PART_CODE_EQ, box.getPart().getId());
/* 175 */           List<InventoryTransit> inventoryTransitList = this.inventoryTransitManager.getInventoryTransitList(conditions, 0, -1, null, false);
/* 176 */           if (inventoryTransitList.size() > 0) {
/* 177 */             InventoryTransit inventoryTransit = inventoryTransitList.get(0);
/* 178 */             inventoryTransit.setQty(inventoryTransit.getQty().add(box.getNumber()));
/* 179 */             this.inventoryTransitManager.updateInventoryTransit(inventoryTransit);
/*     */           } 
/*     */ 
/*     */           
/* 183 */           if (single != null) {
/* 184 */             conditions.clear();
/* 185 */             conditions.put(PortalShipOrderQueryCondition.PO_NUMBER_EQ, box.getPo_number());
/* 186 */             conditions.put(PortalShipOrderQueryCondition.PART_CODE_EQ, box.getPart().getId());
/* 187 */             List<PortalShipOrderItem> portalShipOrderItemList = this.portalShipOrderItemManager.getPortalShipOrderItemList(conditions, 0, -1, null, false);
/* 188 */             if (portalShipOrderItemList.size() > 0) {
/* 189 */               PortalShipOrderItem portalShipOrderItem = portalShipOrderItemList.get(0);
/* 190 */               portalShipOrderItem.setAlready_season_qty(portalShipOrderItem.getAlready_season_qty().subtract(box.getNumber()));
/* 191 */               portalShipOrderItem.setStatus(YesNo.NO);
/* 192 */               this.portalShipOrderItemManager.updatePortalShipOrderItem(portalShipOrderItem);
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 197 */           ProduceRejectedMaterial material = new ProduceRejectedMaterial();
/* 198 */           material.setBox(box);
/* 199 */           material.setDate(new Date());
/* 200 */           material.setCreateUser(user);
/* 201 */           material.setQty(box.getNumber());
/* 202 */           material.setStatus(YesNo.YES);
/* 203 */           material.setType(Integer.valueOf(1));
/* 204 */           material.setLocation(box.getLocation());
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 209 */           this.dao.insertProduceRejectedMaterial(material);
/*     */ 
/*     */           
/* 212 */           box.setVetoQCnumber(box.getNumber());
/* 213 */           box.setReceivedNumber(new BigDecimal(0));
/* 214 */           box.setEnabled(EnabledDisabled.DISABLED);
/* 215 */           box.setLocation(null);
/* 216 */           box.setStatus(BoxStatus.Wait);
/* 217 */           this.dao.updateObject(box);
/*     */         } 
/* 219 */       } catch (Exception e) {
/* 220 */         e.printStackTrace();
/*     */       } 
/*     */       b++; }
/*     */     
/* 224 */     return "true";
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/impl/ProduceRejectedMaterialManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
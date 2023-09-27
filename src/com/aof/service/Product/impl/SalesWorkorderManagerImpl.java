/*     */ package com.aof.service.Product.impl;
/*     */ 
/*     */ import com.aof.dao.basic.ScanLogDAO;
/*     */ import com.aof.dao.product.SalesWorkorderDao;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.ScanLog;
/*     */ import com.aof.model.metadata.BoxStatus;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.InventoryType;
/*     */ import com.aof.model.metadata.SalesPreshiporderBatchStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.product.SalesPreshiporder;
/*     */ import com.aof.model.product.SalesPreshiporderItem;
/*     */ import com.aof.model.product.SalesWorkorder;
/*     */ import com.aof.model.product.query.SalesWorkorderQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.Product.SalesWorkorderManager;
/*     */ import com.aof.service.Properties;
/*     */ import com.aof.service.inventory.InventoryManager;
/*     */ import com.aof.service.po.BoxManager;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SalesWorkorderManagerImpl
/*     */   extends BaseManager
/*     */   implements SalesWorkorderManager
/*     */ {
/*     */   private SalesWorkorderDao dao;
/*     */   private ScanLogDAO scanLogDAO;
/*     */   private BoxManager boxManager;
/*     */   private InventoryManager inventoryManager;
/*     */   
/*     */   public void setInventoryManager(InventoryManager inventoryManager) {
/*  43 */     this.inventoryManager = inventoryManager;
/*     */   }
/*     */   
/*     */   public void setBoxManager(BoxManager boxManager) {
/*  47 */     this.boxManager = boxManager;
/*     */   }
/*     */   
/*     */   public void setScanLogDAO(ScanLogDAO scanLogDAO) {
/*  51 */     this.scanLogDAO = scanLogDAO;
/*     */   }
/*     */   
/*     */   public SalesWorkorderDao getDao() {
/*  55 */     return this.dao;
/*     */   }
/*     */   
/*     */   public void setDao(SalesWorkorderDao dao) {
/*  59 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public SalesWorkorder getById(Integer id) {
/*  63 */     return this.dao.getById(id);
/*     */   }
/*     */   
/*     */   public SalesWorkorder insert(SalesWorkorder salesWorkorder) {
/*  67 */     return this.dao.insert(salesWorkorder);
/*     */   }
/*     */   
/*     */   public void remove(SalesWorkorder salesWorkorder) {
/*  71 */     this.dao.remove(salesWorkorder);
/*     */   }
/*     */   
/*     */   public SalesWorkorder update(SalesWorkorder salesWorkorder) {
/*  75 */     return this.dao.update(salesWorkorder);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getListCount(Map conditions) {
/*  80 */     return this.dao.getListCount(conditions);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getList(Map conditions, int pageNo, int pageSize, SalesWorkorderQueryOrder order, boolean descend) {
/*  85 */     return this.dao.getList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningSalesDelivery(String lotSer, String order, String userId) {
/*  90 */     StringBuffer strBuffer = new StringBuffer();
/*     */     try {
/*  92 */       SalesPreshiporder salesPreshiporder = this.dao.getSalesPreshiporder(order);
/*  93 */       if (salesPreshiporder != null) {
/*  94 */         List<SalesPreshiporderItem> salesPreshiporderItemList = this.dao.getObjectList(" from SalesPreshiporderItem salesPreshiporderItem where salesPreshiporderItem.spsoId.code='" + order + "' ");
/*  95 */         for (SalesPreshiporderItem salesPreshiporderItem : salesPreshiporderItemList) {
/*  96 */           if (salesPreshiporderItem.getDeliverynumber().compareTo(salesPreshiporderItem.getStockQty()) == 1) {
/*  97 */             return String.valueOf(order) + "," + Properties.getPropertiesValye("scan.sync.error.salesPreshiporder.not.finish");
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 103 */         List<SalesWorkorder> salesWorkorderList = this.dao.getSalesWorkorder(order);
/* 104 */         if (salesWorkorderList != null) {
/* 105 */           Box box = this.boxManager.getBoxBylotSer2(lotSer);
/* 106 */           if (box != null) {
/* 107 */             if (box.getLocation().getFreeae_status() == YesNo.YES) {
/* 108 */               return String.valueOf(lotSer) + "," + box.getLocation().getCode() + ":" + Properties.getPropertiesValye("scan.sync.error.lotSerLocation.freeze");
/*     */             }
/*     */             
/* 111 */             String str = this.dao.getSalesWorkorderByLotStatus(order, lotSer);
/*     */             
/* 113 */             if (str.equals("no"))
/* 114 */               return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error.lotser.is.not.SalesWorkorder"); 
/* 115 */             if (str.equals("deliverybatch")) {
/* 116 */               return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error.lotser.deliverybatch");
/*     */             }
/* 118 */             SalesWorkorder salesWorkorder = this.dao.getSalesWorkorder(order, lotSer);
/*     */             
/* 120 */             if (salesWorkorder.getStatus1() == SalesPreshiporderBatchStatus.SALES_RETURN) {
/* 121 */               return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error.lotser.Sales.Return");
/*     */             }
/* 123 */             if (salesPreshiporder.getType().intValue() == 1) {
/* 124 */               boolean sign = false;
/* 125 */               List<SalesPreshiporderItem> itemList = this.dao.getObjectList(" from SalesPreshiporderItem salesPreshiporderItem where salesPreshiporderItem.spsoId.code='" + order + "' and salesPreshiporderItem.soipitemId.itemNumber.id='" + box.getPart().getId() + "'");
/* 126 */               for (SalesPreshiporderItem salesPreshiporderItem : itemList) {
/* 127 */                 if (salesPreshiporderItem.getShipQty().compareTo(salesPreshiporderItem.getStockQty()) == -1) {
/* 128 */                   BigDecimal shipQty = salesPreshiporderItem.getShipQty().add(box.getNumber());
/* 129 */                   salesPreshiporderItem.setShipQty(shipQty);
/* 130 */                   this.dao.updateObject(salesPreshiporderItem);
/* 131 */                   sign = true;
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 135 */               if (sign) {
/* 136 */                 ScanLog scanLog = new ScanLog();
/* 137 */                 scanLog.setDate(new Date());
/* 138 */                 scanLog.setDescribe(lotSer);
/* 139 */                 scanLog.setType(Integer.valueOf(4));
/* 140 */                 User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 141 */                 if (user != null) {
/* 142 */                   scanLog.setUserId(user);
/*     */                 }
/* 144 */                 this.scanLogDAO.insertScanLog(scanLog);
/* 145 */                 this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.RECEIVE_OUT, Boolean.valueOf(true));
/* 146 */                 salesWorkorder.setStatus1(SalesPreshiporderBatchStatus.IN_DELIVERYBATCH);
/* 147 */                 salesWorkorder.setOutDate(new Date());
/* 148 */                 this.dao.update(salesWorkorder);
/*     */                 
/* 150 */                 box.setEnabled(EnabledDisabled.DISABLED);
/* 151 */                 box.setStatus(BoxStatus.HASTHE);
/* 152 */                 box.setOut_date(new Date());
/* 153 */                 this.dao.updateObject(box);
/* 154 */                 strBuffer.append("ok");
/* 155 */                 strBuffer.append(",");
/* 156 */                 strBuffer.append(box.getLocation().getCode());
/*     */               } else {
/* 158 */                 return String.valueOf(box.getPart().getId()) + "," + Properties.getPropertiesValye("scan.sync.error.partId.is.not");
/*     */               } 
/*     */             } else {
/* 161 */               boolean sign = false;
/* 162 */               List<SalesPreshiporderItem> itemList = this.dao.getObjectList(" from SalesPreshiporderItem salesPreshiporderItem where salesPreshiporderItem.spsoId.code='" + order + "' and salesPreshiporderItem.customerPlanId.wmspart.id='" + box.getPart().getId() + "'");
/* 163 */               for (SalesPreshiporderItem salesPreshiporderItem : itemList) {
/* 164 */                 if (salesPreshiporderItem.getShipQty().compareTo(salesPreshiporderItem.getStockQty()) == -1) {
/* 165 */                   BigDecimal shipQty = salesPreshiporderItem.getShipQty().add(box.getNumber());
/* 166 */                   salesPreshiporderItem.setShipQty(shipQty);
/* 167 */                   this.dao.updateObject(salesPreshiporderItem);
/* 168 */                   sign = true;
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 172 */               if (sign) {
/* 173 */                 ScanLog scanLog = new ScanLog();
/* 174 */                 scanLog.setDate(new Date());
/* 175 */                 scanLog.setDescribe(lotSer);
/* 176 */                 scanLog.setType(Integer.valueOf(4));
/* 177 */                 User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 178 */                 if (user != null) {
/* 179 */                   scanLog.setUserId(user);
/*     */                 }
/* 181 */                 this.scanLogDAO.insertScanLog(scanLog);
/* 182 */                 this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.RECEIVE_OUT, Boolean.valueOf(true));
/* 183 */                 salesWorkorder.setStatus1(SalesPreshiporderBatchStatus.IN_DELIVERYBATCH);
/* 184 */                 salesWorkorder.setOutDate(new Date());
/* 185 */                 this.dao.update(salesWorkorder);
/*     */                 
/* 187 */                 box.setEnabled(EnabledDisabled.DISABLED);
/* 188 */                 box.setStatus(BoxStatus.HASTHE);
/* 189 */                 box.setOut_date(new Date());
/* 190 */                 this.dao.updateObject(box);
/* 191 */                 strBuffer.append("ok");
/* 192 */                 strBuffer.append(",");
/* 193 */                 strBuffer.append(box.getLocation().getCode());
/*     */               } else {
/* 195 */                 return String.valueOf(box.getPart().getId()) + "," + Properties.getPropertiesValye("scan.sync.error.partId.is.not");
/*     */               } 
/*     */             } 
/*     */           } else {
/* 199 */             return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error.lotser.is.not");
/*     */           } 
/*     */         } else {
/* 202 */           return String.valueOf(order) + "," + Properties.getPropertiesValye("scan.sync.error.salesPreshiporder.not.stock");
/*     */         } 
/*     */       } else {
/* 205 */         return String.valueOf(order) + "," + Properties.getPropertiesValye("scan.sync.error.salesPreshiporder.not");
/*     */       } 
/* 207 */     } catch (Exception e) {
/* 208 */       e.getStackTrace();
/* 209 */       return e.getMessage();
/*     */     } 
/* 211 */     return strBuffer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningSalesLoadingWithdraw(String lotSer, String order, String userId) {
/* 216 */     StringBuffer strBuffer = new StringBuffer();
/*     */     try {
/* 218 */       SalesPreshiporder salesPreshiporder = this.dao.getSalesPreshiporder(order);
/* 219 */       if (salesPreshiporder != null)
/*     */       
/*     */       { 
/*     */         
/* 223 */         if (salesPreshiporder.getShPrint() == YesNo.YES) {
/* 224 */           return String.valueOf(order) + "," + Properties.getPropertiesValye("scan.sync.error.salesWorkorder.is.ShPrint.not.Withdraw");
/*     */         }
/* 226 */         List<SalesWorkorder> salesWorkorderList = this.dao.getSalesWorkorder(order);
/* 227 */         if (salesWorkorderList != null)
/* 228 */         { Box box = this.boxManager.getBoxBylotSer2(lotSer);
/* 229 */           if (box != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 234 */             String str = this.dao.getSalesWorkorderByLotStatus(order, lotSer);
/*     */             
/* 236 */             if (str.equals("no")) {
/* 237 */               return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error.lotser.is.not.SalesWorkorder");
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 243 */             SalesWorkorder salesWorkorder = this.dao.getSalesWorkorder(order, lotSer);
/*     */             
/* 245 */             if (salesWorkorder.getStatus1() == SalesPreshiporderBatchStatus.SALES_RETURN) {
/* 246 */               return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error.lotser.Sales.Return");
/*     */             }
/* 248 */             if (salesPreshiporder.getType().intValue() == 1) {
/* 249 */               boolean sign = false;
/* 250 */               List<SalesPreshiporderItem> itemList = this.dao.getObjectList(" from SalesPreshiporderItem salesPreshiporderItem where salesPreshiporderItem.spsoId.code='" + order + "' and salesPreshiporderItem.soipitemId.itemNumber.id='" + box.getPart().getId() + "'");
/* 251 */               for (SalesPreshiporderItem salesPreshiporderItem : itemList) {
/* 252 */                 if (salesPreshiporderItem.getShipQty().compareTo(new BigDecimal(0)) == 1) {
/* 253 */                   BigDecimal shipQty = salesPreshiporderItem.getShipQty().subtract(box.getNumber());
/* 254 */                   salesPreshiporderItem.setShipQty(shipQty);
/* 255 */                   this.dao.updateObject(salesPreshiporderItem);
/* 256 */                   sign = true;
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 260 */               if (sign) {
/* 261 */                 ScanLog scanLog = new ScanLog();
/* 262 */                 scanLog.setDate(new Date());
/* 263 */                 scanLog.setDescribe(lotSer);
/* 264 */                 scanLog.setType(Integer.valueOf(43));
/* 265 */                 User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 266 */                 if (user != null) {
/* 267 */                   scanLog.setUserId(user);
/*     */                 }
/* 269 */                 this.scanLogDAO.insertScanLog(scanLog);
/* 270 */                 this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.COMMONIN, Boolean.valueOf(false));
/* 271 */                 salesWorkorder.setStatus1(SalesPreshiporderBatchStatus.ON_LOADINGBATCH);
/* 272 */                 this.dao.update(salesWorkorder);
/*     */                 
/* 274 */                 box.setEnabled(EnabledDisabled.ENABLED);
/* 275 */                 box.setStatus(BoxStatus.HASBEENINTO);
/*     */                 
/* 277 */                 this.dao.updateObject(box);
/* 278 */                 strBuffer.append("ok");
/* 279 */                 strBuffer.append(",");
/* 280 */                 strBuffer.append(box.getLocation().getCode());
/*     */               } else {
/* 282 */                 return String.valueOf(box.getPart().getId()) + "," + Properties.getPropertiesValye("scan.sync.error.partId.is.not.Withdraw");
/*     */               } 
/*     */             } else {
/* 285 */               boolean sign = false;
/* 286 */               List<SalesPreshiporderItem> itemList = this.dao.getObjectList(" from SalesPreshiporderItem salesPreshiporderItem where salesPreshiporderItem.spsoId.code='" + order + "' and salesPreshiporderItem.customerPlanId.wmspart.id='" + box.getPart().getId() + "'");
/* 287 */               for (SalesPreshiporderItem salesPreshiporderItem : itemList) {
/* 288 */                 if (salesPreshiporderItem.getShipQty().compareTo(new BigDecimal(0)) == 1) {
/* 289 */                   BigDecimal shipQty = salesPreshiporderItem.getShipQty().subtract(box.getNumber());
/* 290 */                   salesPreshiporderItem.setShipQty(shipQty);
/* 291 */                   this.dao.updateObject(salesPreshiporderItem);
/* 292 */                   sign = true;
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 296 */               if (sign) {
/* 297 */                 ScanLog scanLog = new ScanLog();
/* 298 */                 scanLog.setDate(new Date());
/* 299 */                 scanLog.setDescribe(lotSer);
/* 300 */                 scanLog.setType(Integer.valueOf(43));
/* 301 */                 User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 302 */                 if (user != null) {
/* 303 */                   scanLog.setUserId(user);
/*     */                 }
/* 305 */                 this.scanLogDAO.insertScanLog(scanLog);
/* 306 */                 this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.COMMONIN, Boolean.valueOf(false));
/* 307 */                 salesWorkorder.setStatus1(SalesPreshiporderBatchStatus.ON_LOADINGBATCH);
/* 308 */                 this.dao.update(salesWorkorder);
/*     */                 
/* 310 */                 box.setEnabled(EnabledDisabled.ENABLED);
/* 311 */                 box.setStatus(BoxStatus.HASBEENINTO);
/*     */                 
/* 313 */                 this.dao.updateObject(box);
/* 314 */                 strBuffer.append("ok");
/* 315 */                 strBuffer.append(",");
/* 316 */                 strBuffer.append(box.getLocation().getCode());
/*     */               } else {
/* 318 */                 return String.valueOf(box.getPart().getId()) + "," + Properties.getPropertiesValye("scan.sync.error.partId.is.not.Withdraw");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           }
/*     */           else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 358 */             return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error.lotser.is.not");
/*     */           }  }
/* 360 */         else { return String.valueOf(order) + "," + Properties.getPropertiesValye("scan.sync.error.salesPreshiporder.not.stock"); }  }
/* 361 */       else { return String.valueOf(order) + "," + Properties.getPropertiesValye("scan.sync.error.salesPreshiporder.inexistence"); } 
/* 362 */     } catch (Exception e) {
/* 363 */       e.getStackTrace();
/* 364 */       return e.getMessage();
/*     */     } 
/* 366 */     return strBuffer.toString();
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/impl/SalesWorkorderManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
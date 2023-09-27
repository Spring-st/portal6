/*     */ package com.aof.model.po;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractPurchaseManualCreateBarcode
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int id;
/*     */   private String part;
/*     */   private String partUnit;
/*     */   private String partName;
/*     */   private String model;
/*     */   private String supplierName;
/*     */   private Date serialDate;
/*     */   private String serialNumber;
/*     */   private Integer qty;
/*     */   private Integer printStatus;
/*     */   private String batchNo;
/*     */   
/*     */   public AbstractPurchaseManualCreateBarcode() {}
/*     */   
/*     */   public AbstractPurchaseManualCreateBarcode(Integer id) {
/*  27 */     this.id = id.intValue();
/*     */   }
/*     */   
/*     */   public int getId() {
/*  31 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*  35 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getPart() {
/*  39 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(String part) {
/*  43 */     this.part = part;
/*     */   }
/*     */   
/*     */   public String getPartUnit() {
/*  47 */     return this.partUnit;
/*     */   }
/*     */   
/*     */   public void setPartUnit(String partUnit) {
/*  51 */     this.partUnit = partUnit;
/*     */   }
/*     */   
/*     */   public String getPartName() {
/*  55 */     return this.partName;
/*     */   }
/*     */   
/*     */   public void setPartName(String partName) {
/*  59 */     this.partName = partName;
/*     */   }
/*     */   
/*     */   public String getSupplierName() {
/*  63 */     return this.supplierName;
/*     */   }
/*     */   
/*     */   public void setSupplierName(String supplierName) {
/*  67 */     this.supplierName = supplierName;
/*     */   }
/*     */   
/*     */   public Date getSerialDate() {
/*  71 */     return this.serialDate;
/*     */   }
/*     */   
/*     */   public void setSerialDate(Date serialDate) {
/*  75 */     this.serialDate = serialDate;
/*     */   }
/*     */   
/*     */   public String getSerialNumber() {
/*  79 */     return this.serialNumber;
/*     */   }
/*     */   
/*     */   public void setSerialNumber(String serialNumber) {
/*  83 */     this.serialNumber = serialNumber;
/*     */   }
/*     */   
/*     */   public Integer getQty() {
/*  87 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(Integer qty) {
/*  91 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public String getModel() {
/*  95 */     return this.model;
/*     */   }
/*     */   
/*     */   public void setModel(String model) {
/*  99 */     this.model = model;
/*     */   }
/*     */   
/*     */   public Integer getPrintStatus() {
/* 103 */     return this.printStatus;
/*     */   }
/*     */   
/*     */   public void setPrintStatus(Integer printStatus) {
/* 107 */     this.printStatus = printStatus;
/*     */   }
/*     */   
/*     */   public String getBatchNo() {
/* 111 */     return this.batchNo;
/*     */   }
/*     */   
/*     */   public void setBatchNo(String batchNo) {
/* 115 */     this.batchNo = batchNo;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractPurchaseManualCreateBarcode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.aof.model.product;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.SalesPreshiporderBatchStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.WmsLot;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSalesWorkorder
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private SalesPreshiporder shipId;
/*     */   private WmsLot lotSer;
/*     */   private WmsPart part;
/*     */   private StorageLocation location;
/*     */   private SalesPreshiporderBatchStatus status1;
/*     */   private Date scanDate;
/*     */   private User scanUser;
/*     */   private BigDecimal count;
/*     */   private YesNo isSync;
/*     */   private SalesPreshiporderItem shipItemId;
/*     */   private Date outDate;
/*     */   
/*     */   public Date getOutDate() {
/*  33 */     return this.outDate;
/*     */   }
/*     */   
/*     */   public void setOutDate(Date outDate) {
/*  37 */     this.outDate = outDate;
/*     */   }
/*     */   
/*     */   public SalesPreshiporderItem getShipItemId() {
/*  41 */     return this.shipItemId;
/*     */   }
/*     */   
/*     */   public void setShipItemId(SalesPreshiporderItem shipItemId) {
/*  45 */     this.shipItemId = shipItemId;
/*     */   }
/*     */   
/*     */   public BigDecimal getCount() {
/*  49 */     return this.count;
/*     */   }
/*     */   
/*     */   public void setCount(BigDecimal count) {
/*  53 */     this.count = count;
/*     */   }
/*     */   
/*     */   public YesNo getIsSync() {
/*  57 */     return this.isSync;
/*     */   }
/*     */   
/*     */   public void setIsSync(YesNo isSync) {
/*  61 */     this.isSync = isSync;
/*     */   }
/*     */   
/*     */   public SalesPreshiporder getShipId() {
/*  65 */     return this.shipId;
/*     */   }
/*     */   
/*     */   public void setShipId(SalesPreshiporder shipId) {
/*  69 */     this.shipId = shipId;
/*     */   }
/*     */   
/*     */   public WmsLot getLotSer() {
/*  73 */     return this.lotSer;
/*     */   }
/*     */   
/*     */   public void setLotSer(WmsLot lotSer) {
/*  77 */     this.lotSer = lotSer;
/*     */   }
/*     */   
/*     */   public WmsPart getPart() {
/*  81 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/*  85 */     this.part = part;
/*     */   }
/*     */   
/*     */   public StorageLocation getLocation() {
/*  89 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(StorageLocation location) {
/*  93 */     this.location = location;
/*     */   }
/*     */   
/*     */   public SalesPreshiporderBatchStatus getStatus1() {
/*  97 */     return this.status1;
/*     */   }
/*     */   
/*     */   public void setStatus1(SalesPreshiporderBatchStatus status1) {
/* 101 */     this.status1 = status1;
/*     */   }
/*     */   
/*     */   public Date getScanDate() {
/* 105 */     return this.scanDate;
/*     */   }
/*     */   
/*     */   public void setScanDate(Date scanDate) {
/* 109 */     this.scanDate = scanDate;
/*     */   }
/*     */   
/*     */   public User getScanUser() {
/* 113 */     return this.scanUser;
/*     */   }
/*     */   
/*     */   public void setScanUser(User scanUser) {
/* 117 */     this.scanUser = scanUser;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractSalesWorkorder() {}
/*     */   
/*     */   public AbstractSalesWorkorder(Integer id) {
/* 124 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 128 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 132 */     this.id = id;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/AbstractSalesWorkorder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
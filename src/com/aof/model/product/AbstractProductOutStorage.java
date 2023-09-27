/*     */ package com.aof.model.product;
/*     */ 
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.StoreRoom;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractProductOutStorage
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private String hncCode;
/*     */   private String saiheCode;
/*     */   private String description;
/*     */   private Integer qty;
/*     */   private Date outDate;
/*     */   private Date deliverDate;
/*     */   private int status;
/*     */   private int issync;
/*     */   private StoreRoom storeroom;
/*     */   private StorageLocation location;
/*     */   
/*     */   public StoreRoom getStoreroom() {
/*  29 */     return this.storeroom;
/*     */   }
/*     */   
/*     */   public void setStoreroom(StoreRoom storeroom) {
/*  33 */     this.storeroom = storeroom;
/*     */   }
/*     */   
/*     */   public int getIssync() {
/*  37 */     return this.issync;
/*     */   }
/*     */   
/*     */   public void setIssync(int issync) {
/*  41 */     this.issync = issync;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractProductOutStorage() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractProductOutStorage(Integer id) {
/*  54 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  60 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  64 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getHncCode() {
/*  68 */     return this.hncCode;
/*     */   }
/*     */   
/*     */   public void setHncCode(String hncCode) {
/*  72 */     this.hncCode = hncCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  77 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/*  81 */     this.description = description;
/*     */   }
/*     */   
/*     */   public Integer getQty() {
/*  85 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(Integer qty) {
/*  89 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public Date getOutDate() {
/*  93 */     return this.outDate;
/*     */   }
/*     */   
/*     */   public void setOutDate(Date outDate) {
/*  97 */     this.outDate = outDate;
/*     */   }
/*     */   
/*     */   public String getSaiheCode() {
/* 101 */     return this.saiheCode;
/*     */   }
/*     */   
/*     */   public void setSaiheCode(String saiheCode) {
/* 105 */     this.saiheCode = saiheCode;
/*     */   }
/*     */   
/*     */   public Date getDeliverDate() {
/* 109 */     return this.deliverDate;
/*     */   }
/*     */   
/*     */   public void setDeliverDate(Date deliverDate) {
/* 113 */     this.deliverDate = deliverDate;
/*     */   }
/*     */   
/*     */   public int getStatus() {
/* 117 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(int status) {
/* 121 */     this.status = status;
/*     */   }
/*     */   
/*     */   public StorageLocation getLocation() {
/* 125 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(StorageLocation location) {
/* 129 */     this.location = location;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/AbstractProductOutStorage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
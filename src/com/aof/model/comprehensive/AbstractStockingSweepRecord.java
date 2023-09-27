/*     */ package com.aof.model.comprehensive;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.po.Box;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
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
/*     */ public abstract class AbstractStockingSweepRecord
/*     */   implements Serializable
/*     */ {
/*  25 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private StockingDetial stocking_detail_id;
/*     */   private Box box;
/*     */   private StorageLocation location;
/*     */   private Date date;
/*     */   private BigDecimal qty;
/*     */   private User operation;
/*     */   
/*     */   public Date getDate() {
/*  35 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/*  39 */     this.date = date;
/*     */   }
/*     */   
/*     */   public StorageLocation getLocation() {
/*  43 */     return this.location;
/*     */   }
/*     */   
/*     */   public StockingDetial getStocking_detail_id() {
/*  47 */     return this.stocking_detail_id;
/*     */   }
/*     */   
/*     */   public void setStocking_detail_id(StockingDetial stocking_detail_id) {
/*  51 */     this.stocking_detail_id = stocking_detail_id;
/*     */   }
/*     */   
/*     */   public Box getBox() {
/*  55 */     return this.box;
/*     */   }
/*     */   
/*     */   public void setBox(Box box) {
/*  59 */     this.box = box;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/*  63 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/*  67 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public User getOperation() {
/*  71 */     return this.operation;
/*     */   }
/*     */   
/*     */   public void setOperation(User operation) {
/*  75 */     this.operation = operation;
/*     */   }
/*     */   
/*     */   public void setLocation(StorageLocation location) {
/*  79 */     this.location = location;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractStockingSweepRecord() {}
/*     */   
/*     */   public AbstractStockingSweepRecord(Integer id) {
/*  86 */     setId(id);
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  90 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  94 */     this.hashValue = 0;
/*  95 */     this.id = id;
/*     */   }
/*     */   
/*     */   public boolean equals(Object rhs) {
/*  99 */     if (rhs == null)
/* 100 */       return false; 
/* 101 */     if (this == rhs)
/* 102 */       return true; 
/* 103 */     if (!(rhs instanceof StockingSweepRecord))
/* 104 */       return false; 
/* 105 */     StockingSweepRecord that = (StockingSweepRecord)rhs;
/* 106 */     if (getId() != null)
/* 107 */       return getId().equals(that.getId()); 
/* 108 */     return (that.getId() == null);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 112 */     if (this.hashValue == 0) {
/* 113 */       int result = 17;
/* 114 */       int cityIdValue = (getId() == null) ? 0 : getId()
/* 115 */         .hashCode();
/* 116 */       result = result * 37 + cityIdValue;
/* 117 */       this.hashValue = result;
/*     */     } 
/* 119 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public int getHashValue() {
/* 123 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public void setHashValue(int hashValue) {
/* 127 */     this.hashValue = hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/comprehensive/AbstractStockingSweepRecord.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
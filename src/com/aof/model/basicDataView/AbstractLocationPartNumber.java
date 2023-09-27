/*     */ package com.aof.model.basicDataView;
/*     */ 
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
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
/*     */ public abstract class AbstractLocationPartNumber
/*     */   implements Serializable
/*     */ {
/*  20 */   private int hashValue = 0;
/*     */   private StorageLocation location;
/*     */   private WmsPart part;
/*     */   private BigDecimal number;
/*     */   private BigDecimal partQty;
/*     */   private BigDecimal occupiedNumber;
/*     */   
/*     */   public AbstractLocationPartNumber() {}
/*     */   
/*     */   public AbstractLocationPartNumber(StorageLocation id, WmsPart part) {
/*  30 */     setLocation(id);
/*  31 */     setPart(part);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object rhs) {
/*  41 */     if (rhs == null) return false; 
/*  42 */     if (this == rhs) return true; 
/*  43 */     if (!(rhs instanceof LocationPartNumber)) return false; 
/*  44 */     LocationPartNumber that = (LocationPartNumber)rhs;
/*  45 */     if (getLocation() == null || that.getLocation() == null) {
/*  46 */       return false;
/*     */     }
/*  48 */     if (!getPart().equals(that.getPart())) {
/*  49 */       return false;
/*     */     }
/*  51 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  62 */     if (this.hashValue == 0) {
/*  63 */       int result = 17;
/*  64 */       int tSiteValue = (getLocation() == null) ? 0 : getLocation().hashCode();
/*  65 */       result = result * 37 + tSiteValue;
/*  66 */       int tTypeValue = (getPart() == null) ? 0 : getPart().hashCode();
/*  67 */       result = result * 37 + tTypeValue;
/*  68 */       this.hashValue = result;
/*     */     } 
/*  70 */     return this.hashValue;
/*     */   }
/*     */   public int getHashValue() {
/*  73 */     return this.hashValue;
/*     */   }
/*     */   public void setHashValue(int hashValue) {
/*  76 */     this.hashValue = hashValue;
/*     */   }
/*     */   public StorageLocation getLocation() {
/*  79 */     return this.location;
/*     */   }
/*     */   public void setLocation(StorageLocation location) {
/*  82 */     this.location = location;
/*     */   }
/*     */   public WmsPart getPart() {
/*  85 */     return this.part;
/*     */   }
/*     */   public void setPart(WmsPart part) {
/*  88 */     this.part = part;
/*     */   }
/*     */   public BigDecimal getNumber() {
/*  91 */     return this.number;
/*     */   }
/*     */   public void setNumber(BigDecimal number) {
/*  94 */     this.number = number;
/*     */   }
/*     */   public BigDecimal getPartQty() {
/*  97 */     return this.partQty;
/*     */   }
/*     */   public void setPartQty(BigDecimal partQty) {
/* 100 */     this.partQty = partQty;
/*     */   }
/*     */   public BigDecimal getOccupiedNumber() {
/* 103 */     return this.occupiedNumber;
/*     */   }
/*     */   public void setOccupiedNumber(BigDecimal occupiedNumber) {
/* 106 */     this.occupiedNumber = occupiedNumber;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basicDataView/AbstractLocationPartNumber.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
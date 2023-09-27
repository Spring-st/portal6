/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
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
/*     */ public abstract class AbstractPrice
/*     */   implements Serializable
/*     */ {
/*  31 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */ 
/*     */   
/*     */   private String room;
/*     */ 
/*     */ 
/*     */   
/*     */   private BigDecimal price;
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer discount;
/*     */ 
/*     */ 
/*     */   
/*     */   private String description;
/*     */ 
/*     */ 
/*     */   
/*     */   private String breakfast;
/*     */ 
/*     */ 
/*     */   
/*     */   private String network;
/*     */ 
/*     */   
/*     */   private EnabledDisabled enabled;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPrice() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPrice(Integer id) {
/*  71 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  80 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  89 */     this.hashValue = 0;
/*  90 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/* 100 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 107 */     this.enabled = enabled;
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
/*     */   public String getRoom() {
/* 130 */     return this.room;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRoom(String room) {
/* 139 */     this.room = room;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BigDecimal getPrice() {
/* 148 */     return this.price;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPrice(BigDecimal price) {
/* 157 */     this.price = price;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getDiscount() {
/* 166 */     return this.discount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDiscount(Integer discount) {
/* 175 */     this.discount = discount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 184 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/* 193 */     this.description = description;
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
/*     */   public boolean equals(Object rhs) {
/* 205 */     if (rhs == null) return false; 
/* 206 */     if (this == rhs) return true; 
/* 207 */     if (!(rhs instanceof Price)) return false; 
/* 208 */     Price that = (Price)rhs;
/* 209 */     if (getId() != null) return getId().equals(that.getId()); 
/* 210 */     return (that.getId() == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 220 */     if (this.hashValue == 0) {
/*     */       
/* 222 */       int result = 17;
/* 223 */       int priceIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 224 */       result = result * 37 + priceIdValue;
/* 225 */       this.hashValue = result;
/*     */     } 
/* 227 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public String getBreakfast() {
/* 231 */     return this.breakfast;
/*     */   }
/*     */   
/*     */   public void setBreakfast(String breakfast) {
/* 235 */     this.breakfast = breakfast;
/*     */   }
/*     */   
/*     */   public String getNetwork() {
/* 239 */     return this.network;
/*     */   }
/*     */   
/*     */   public void setNetwork(String network) {
/* 243 */     this.network = network;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractPrice.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
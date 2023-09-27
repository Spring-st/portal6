/*     */ package com.aof.model.basic;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.StoreRoomType;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractStoreRoom
/*     */   implements Serializable
/*     */ {
/*  13 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private Site site;
/*     */   private String domain;
/*     */   private String code;
/*     */   private String name;
/*     */   private String address;
/*     */   private Date date;
/*     */   private BigDecimal largest_inventory;
/*     */   private EnabledDisabled status;
/*     */   private StoreRoomType type;
/*     */   private String remark;
/*     */   
/*     */   public Date getDate() {
/*  27 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/*  31 */     this.date = date;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  35 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  39 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/*  43 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/*  47 */     this.site = site;
/*     */   }
/*     */   
/*     */   public String getDomain() {
/*  51 */     return this.domain;
/*     */   }
/*     */   
/*     */   public void setDomain(String domain) {
/*  55 */     this.domain = domain;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  59 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  63 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  67 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  71 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getAddress() {
/*  75 */     return this.address;
/*     */   }
/*     */   
/*     */   public void setAddress(String address) {
/*  79 */     this.address = address;
/*     */   }
/*     */   
/*     */   public BigDecimal getLargest_inventory() {
/*  83 */     return this.largest_inventory;
/*     */   }
/*     */   
/*     */   public void setLargest_inventory(BigDecimal largest_inventory) {
/*  87 */     this.largest_inventory = largest_inventory;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getStatus() {
/*  91 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(EnabledDisabled status) {
/*  95 */     this.status = status;
/*     */   }
/*     */   
/*     */   public StoreRoomType getType() {
/*  99 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(StoreRoomType type) {
/* 103 */     this.type = type;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/* 107 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 111 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractStoreRoom() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractStoreRoom(Integer id) {
/* 123 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 134 */     if (rhs == null)
/* 135 */       return false; 
/* 136 */     if (this == rhs)
/* 137 */       return true; 
/* 138 */     if (!(rhs instanceof StoreRoom))
/* 139 */       return false; 
/* 140 */     StoreRoom that = (StoreRoom)rhs;
/* 141 */     if (getId() != null)
/* 142 */       return getId().equals(that.getId()); 
/* 143 */     return (that.getId() == null);
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
/* 154 */     if (this.hashValue == 0) {
/* 155 */       int result = 17;
/* 156 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 157 */       result = result * 37 + poIdValue;
/* 158 */       this.hashValue = result;
/*     */     } 
/* 160 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractStoreRoom.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
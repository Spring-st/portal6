/*     */ package com.aof.model.product;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.YesNo;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractProduct
/*     */   implements Serializable
/*     */ {
/*  31 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private Site site;
/*     */   private Date date;
/*     */   private StorageLocation location;
/*     */   private String domain;
/*     */   private BigDecimal qty;
/*     */   private User userId;
/*     */   private Box box;
/*     */   private YesNo isSync;
/*     */   private Date isSync_date;
/*     */   private WmsPart part;
/*     */   private String strDate;
/*     */   private YesNo isPrint;
/*     */   
/*     */   public WmsPart getPart() {
/*  47 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/*  51 */     this.part = part;
/*     */   }
/*     */   
/*     */   public String getStrDate() {
/*  55 */     return this.strDate;
/*     */   }
/*     */   
/*     */   public void setStrDate(String strDate) {
/*  59 */     this.strDate = strDate;
/*     */   }
/*     */   
/*     */   public YesNo getIsPrint() {
/*  63 */     return this.isPrint;
/*     */   }
/*     */   
/*     */   public void setIsPrint(YesNo isPrint) {
/*  67 */     this.isPrint = isPrint;
/*     */   }
/*     */   
/*     */   public YesNo getIsSync() {
/*  71 */     return this.isSync;
/*     */   }
/*     */   
/*     */   public void setIsSync(YesNo isSync) {
/*  75 */     this.isSync = isSync;
/*     */   }
/*     */   
/*     */   public Date getIsSync_date() {
/*  79 */     return this.isSync_date;
/*     */   }
/*     */   
/*     */   public void setIsSync_date(Date isSync_date) {
/*  83 */     this.isSync_date = isSync_date;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/*  87 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/*  91 */     this.site = site;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/*  95 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/*  99 */     this.date = date;
/*     */   }
/*     */   
/*     */   public StorageLocation getLocation() {
/* 103 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(StorageLocation location) {
/* 107 */     this.location = location;
/*     */   }
/*     */   
/*     */   public String getDomain() {
/* 111 */     return this.domain;
/*     */   }
/*     */   
/*     */   public void setDomain(String domain) {
/* 115 */     this.domain = domain;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/* 119 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/* 123 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public User getUserId() {
/* 127 */     return this.userId;
/*     */   }
/*     */   
/*     */   public void setUserId(User userId) {
/* 131 */     this.userId = userId;
/*     */   }
/*     */   
/*     */   public Box getBox() {
/* 135 */     return this.box;
/*     */   }
/*     */   
/*     */   public void setBox(Box box) {
/* 139 */     this.box = box;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 143 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 147 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractProduct() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractProduct(Integer id) {
/* 162 */     setId(id);
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
/* 173 */     if (rhs == null)
/* 174 */       return false; 
/* 175 */     if (this == rhs)
/* 176 */       return true; 
/* 177 */     if (!(rhs instanceof Product))
/* 178 */       return false; 
/* 179 */     Product that = (Product)rhs;
/* 180 */     if (getId() != null)
/* 181 */       return getId().equals(that.getId()); 
/* 182 */     return (that.getId() == null);
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
/* 193 */     if (this.hashValue == 0) {
/* 194 */       int result = 17;
/* 195 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 196 */       result = result * 37 + poIdValue;
/* 197 */       this.hashValue = result;
/*     */     } 
/* 199 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/AbstractProduct.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
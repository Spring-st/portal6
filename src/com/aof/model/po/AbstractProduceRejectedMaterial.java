/*     */ package com.aof.model.po;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.BadReasons;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.metadata.YesNo;
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
/*     */ 
/*     */ public abstract class AbstractProduceRejectedMaterial
/*     */   implements Serializable
/*     */ {
/*  31 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private Box box;
/*     */   private Date date;
/*     */   private String remark;
/*     */   private BigDecimal qty;
/*     */   private User createUser;
/*     */   private Site site;
/*     */   private YesNo status;
/*     */   private StorageLocation location;
/*     */   private YesNo isPrint;
/*     */   private BigDecimal returnNumber;
/*     */   private Integer type;
/*     */   private BadReasons reasons;
/*     */   
/*     */   public Integer getType() {
/*  47 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(Integer type) {
/*  51 */     this.type = type;
/*     */   }
/*     */   
/*     */   public BadReasons getReasons() {
/*  55 */     return this.reasons;
/*     */   }
/*     */   
/*     */   public void setReasons(BadReasons reasons) {
/*  59 */     this.reasons = reasons;
/*     */   }
/*     */   
/*     */   public BigDecimal getReturnNumber() {
/*  63 */     return this.returnNumber;
/*     */   }
/*     */   
/*     */   public void setReturnNumber(BigDecimal returnNumber) {
/*  67 */     this.returnNumber = returnNumber;
/*     */   }
/*     */   
/*     */   public YesNo getIsPrint() {
/*  71 */     return this.isPrint;
/*     */   }
/*     */   
/*     */   public void setIsPrint(YesNo isPrint) {
/*  75 */     this.isPrint = isPrint;
/*     */   }
/*     */   
/*     */   public StorageLocation getLocation() {
/*  79 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(StorageLocation location) {
/*  83 */     this.location = location;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  87 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  91 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Box getBox() {
/*  95 */     return this.box;
/*     */   }
/*     */   
/*     */   public void setBox(Box box) {
/*  99 */     this.box = box;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/* 103 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/* 107 */     this.date = date;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/* 111 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 115 */     this.remark = remark;
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
/*     */   public User getCreateUser() {
/* 127 */     return this.createUser;
/*     */   }
/*     */   
/*     */   public void setCreateUser(User createUser) {
/* 131 */     this.createUser = createUser;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/* 135 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/* 139 */     this.site = site;
/*     */   }
/*     */   
/*     */   public YesNo getStatus() {
/* 143 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(YesNo status) {
/* 147 */     this.status = status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractProduceRejectedMaterial() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractProduceRejectedMaterial(Integer id) {
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
/* 177 */     if (!(rhs instanceof ProduceRejectedMaterial))
/* 178 */       return false; 
/* 179 */     ProduceRejectedMaterial that = (ProduceRejectedMaterial)rhs;
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


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractProduceRejectedMaterial.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
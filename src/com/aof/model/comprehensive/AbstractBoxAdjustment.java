/*     */ package com.aof.model.comprehensive;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.metadata.BoxAdjustmentType;
/*     */ import com.aof.model.po.Box;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractBoxAdjustment
/*     */   implements Serializable {
/*  13 */   private int hashValue = 0;
/*     */   
/*     */   private Integer id;
/*     */   private Site site;
/*     */   private Box new_box_id;
/*     */   private User operation;
/*     */   private StorageLocation old_location;
/*     */   private String domain;
/*     */   private Date date;
/*     */   private BoxAdjustmentType type;
/*     */   private String remark;
/*     */   
/*     */   public Integer getId() {
/*  26 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  30 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getHashValue() {
/*  34 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public void setHashValue(int hashValue) {
/*  38 */     this.hashValue = hashValue;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/*  42 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/*  46 */     this.site = site;
/*     */   }
/*     */   
/*     */   public Box getNew_box_id() {
/*  50 */     return this.new_box_id;
/*     */   }
/*     */   
/*     */   public void setNew_box_id(Box new_box_id) {
/*  54 */     this.new_box_id = new_box_id;
/*     */   }
/*     */   
/*     */   public User getOperation() {
/*  58 */     return this.operation;
/*     */   }
/*     */   
/*     */   public void setOperation(User operation) {
/*  62 */     this.operation = operation;
/*     */   }
/*     */   
/*     */   public StorageLocation getOld_location() {
/*  66 */     return this.old_location;
/*     */   }
/*     */   
/*     */   public void setOld_location(StorageLocation old_location) {
/*  70 */     this.old_location = old_location;
/*     */   }
/*     */   
/*     */   public String getDomain() {
/*  74 */     return this.domain;
/*     */   }
/*     */   
/*     */   public void setDomain(String domain) {
/*  78 */     this.domain = domain;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/*  82 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/*  86 */     this.date = date;
/*     */   }
/*     */   
/*     */   public BoxAdjustmentType getType() {
/*  90 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(BoxAdjustmentType type) {
/*  94 */     this.type = type;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/*  98 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 102 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractBoxAdjustment() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractBoxAdjustment(Integer id) {
/* 114 */     setId(id);
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
/* 125 */     if (rhs == null)
/* 126 */       return false; 
/* 127 */     if (this == rhs)
/* 128 */       return true; 
/* 129 */     if (!(rhs instanceof BoxAdjustment))
/* 130 */       return false; 
/* 131 */     BoxAdjustment that = (BoxAdjustment)rhs;
/* 132 */     if (getId() != null)
/* 133 */       return getId().equals(that.getId()); 
/* 134 */     return (that.getId() == null);
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
/* 145 */     if (this.hashValue == 0) {
/* 146 */       int result = 17;
/* 147 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 148 */       result = result * 37 + poIdValue;
/* 149 */       this.hashValue = result;
/*     */     } 
/* 151 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/comprehensive/AbstractBoxAdjustment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
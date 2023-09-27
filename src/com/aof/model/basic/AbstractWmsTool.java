/*     */ package com.aof.model.basic;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractWmsTool
/*     */   implements Serializable {
/*  11 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private String code;
/*     */   private BigDecimal capacity;
/*     */   private BigDecimal qty;
/*     */   private EnabledDisabled enabled;
/*     */   private String remark;
/*     */   private Date date;
/*     */   private YesNo type;
/*     */   private WmsPart part;
/*     */   private Date in_date;
/*     */   private StorageLocation location;
/*     */   private YesNo status;
/*     */   
/*     */   public StorageLocation getLocation() {
/*  26 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(StorageLocation location) {
/*  30 */     this.location = location;
/*     */   }
/*     */   
/*     */   public Date getIn_date() {
/*  34 */     return this.in_date;
/*     */   }
/*     */   
/*     */   public void setIn_date(Date in_date) {
/*  38 */     this.in_date = in_date;
/*     */   }
/*     */   
/*     */   public WmsPart getPart() {
/*  42 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/*  46 */     this.part = part;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/*  50 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/*  54 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public YesNo getType() {
/*  58 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(YesNo type) {
/*  62 */     this.type = type;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/*  66 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/*  70 */     this.date = date;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  74 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  78 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  82 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  86 */     this.code = code;
/*     */   }
/*     */   
/*     */   public BigDecimal getCapacity() {
/*  90 */     return this.capacity;
/*     */   }
/*     */   
/*     */   public void setCapacity(BigDecimal capacity) {
/*  94 */     this.capacity = capacity;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/*  98 */     return this.enabled;
/*     */   }
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 102 */     this.enabled = enabled;
/*     */   }
/*     */   
/*     */   public YesNo getStatus() {
/* 106 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(YesNo status) {
/* 110 */     this.status = status;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/* 114 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 118 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractWmsTool() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractWmsTool(Integer id) {
/* 130 */     setId(id);
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
/* 141 */     if (rhs == null)
/* 142 */       return false; 
/* 143 */     if (this == rhs)
/* 144 */       return true; 
/* 145 */     if (!(rhs instanceof WmsTool))
/* 146 */       return false; 
/* 147 */     WmsTool that = (WmsTool)rhs;
/* 148 */     if (getId() != null)
/* 149 */       return getId().equals(that.getId()); 
/* 150 */     return (that.getId() == null);
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
/* 161 */     if (this.hashValue == 0) {
/* 162 */       int result = 17;
/* 163 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 164 */       result = result * 37 + poIdValue;
/* 165 */       this.hashValue = result;
/*     */     } 
/* 167 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractWmsTool.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
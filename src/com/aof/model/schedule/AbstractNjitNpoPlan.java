/*     */ package com.aof.model.schedule;
/*     */ 
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractNjitNpoPlan
/*     */   implements Serializable {
/*     */   private Integer id;
/*     */   private WmsPart partId;
/*     */   private String dataset;
/*     */   private String nbr;
/*     */   private String line;
/*     */   private Date relDate;
/*     */   private Integer time;
/*     */   private String type;
/*     */   private String detail;
/*     */   private String version;
/*     */   private BigDecimal qty;
/*     */   private String unit;
/*     */   private Date needDate;
/*     */   private Date createDate;
/*     */   private EnabledDisabled isEnabled;
/*     */   
/*     */   public Integer getId() {
/*  28 */     return this.id;
/*     */   } public AbstractNjitNpoPlan() {}
/*     */   public void setId(Integer id) {
/*  31 */     this.id = id;
/*     */   }
/*     */   public AbstractNjitNpoPlan(Integer id) {
/*  34 */     this.id = id;
/*     */   } public BigDecimal getQty() {
/*  36 */     return this.qty;
/*     */   }
/*     */   public void setQty(BigDecimal qty) {
/*  39 */     this.qty = qty;
/*     */   }
/*     */   public String getUnit() {
/*  42 */     return this.unit;
/*     */   }
/*     */   public void setUnit(String unit) {
/*  45 */     this.unit = unit;
/*     */   }
/*     */   public Date getNeedDate() {
/*  48 */     return this.needDate;
/*     */   }
/*     */   public void setNeedDate(Date needDate) {
/*  51 */     this.needDate = needDate;
/*     */   }
/*     */   public Date getCreateDate() {
/*  54 */     return this.createDate;
/*     */   }
/*     */   public void setCreateDate(Date createDate) {
/*  57 */     this.createDate = createDate;
/*     */   }
/*     */   public EnabledDisabled getIsEnabled() {
/*  60 */     return this.isEnabled;
/*     */   }
/*     */   public void setIsEnabled(EnabledDisabled isEnabled) {
/*  63 */     this.isEnabled = isEnabled;
/*     */   }
/*     */   public String getDataset() {
/*  66 */     return this.dataset;
/*     */   }
/*     */   public void setDataset(String dataset) {
/*  69 */     this.dataset = dataset;
/*     */   }
/*     */   public String getNbr() {
/*  72 */     return this.nbr;
/*     */   }
/*     */   public void setNbr(String nbr) {
/*  75 */     this.nbr = nbr;
/*     */   }
/*     */   public String getLine() {
/*  78 */     return this.line;
/*     */   }
/*     */   public void setLine(String line) {
/*  81 */     this.line = line;
/*     */   }
/*     */   public Date getRelDate() {
/*  84 */     return this.relDate;
/*     */   }
/*     */   public void setRelDate(Date relDate) {
/*  87 */     this.relDate = relDate;
/*     */   }
/*     */   public Integer getTime() {
/*  90 */     return this.time;
/*     */   }
/*     */   public void setTime(Integer time) {
/*  93 */     this.time = time;
/*     */   }
/*     */   public String getType() {
/*  96 */     return this.type;
/*     */   }
/*     */   public void setType(String type) {
/*  99 */     this.type = type;
/*     */   }
/*     */   public String getDetail() {
/* 102 */     return this.detail;
/*     */   }
/*     */   public void setDetail(String detail) {
/* 105 */     this.detail = detail;
/*     */   }
/*     */   public String getVersion() {
/* 108 */     return this.version;
/*     */   }
/*     */   public void setVersion(String version) {
/* 111 */     this.version = version;
/*     */   }
/*     */   public WmsPart getPartId() {
/* 114 */     return this.partId;
/*     */   }
/*     */   public void setPartId(WmsPart partId) {
/* 117 */     this.partId = partId;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/AbstractNjitNpoPlan.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
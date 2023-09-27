/*     */ package com.aof.model.schedule;
/*     */ 
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractNjitnpoplanhistory
/*     */   implements Serializable {
/*     */   private Integer id;
/*     */   private WmsPart partId;
/*     */   private BigDecimal qty;
/*     */   private String unit;
/*     */   private Date needDate;
/*     */   private Date createDate;
/*     */   private EnabledDisabled isEnabled;
/*     */   private String dataset;
/*     */   private String nbr;
/*     */   private String line;
/*     */   private Date reldate;
/*     */   private Integer time;
/*     */   private String type;
/*     */   private String detail;
/*     */   private String version;
/*     */   
/*     */   public Integer getId() {
/*  28 */     return this.id;
/*     */   } public AbstractNjitnpoplanhistory() {}
/*     */   public void setId(Integer id) {
/*  31 */     this.id = id;
/*     */   }
/*     */   public AbstractNjitnpoplanhistory(Integer id) {
/*  34 */     this.id = id;
/*     */   } public AbstractNjitnpoplanhistory(NjitNpoPlan njitNpoPlan) {
/*  36 */     this.id = null;
/*  37 */     this.partId = njitNpoPlan.getPartId();
/*  38 */     this.qty = njitNpoPlan.getQty();
/*  39 */     this.unit = njitNpoPlan.getUnit();
/*  40 */     this.needDate = njitNpoPlan.getNeedDate();
/*  41 */     this.createDate = njitNpoPlan.getCreateDate();
/*  42 */     this.isEnabled = njitNpoPlan.getIsEnabled();
/*  43 */     this.dataset = njitNpoPlan.getDataset();
/*  44 */     this.nbr = njitNpoPlan.getNbr();
/*  45 */     this.line = njitNpoPlan.getLine();
/*  46 */     this.reldate = njitNpoPlan.getRelDate();
/*  47 */     this.time = njitNpoPlan.getTime();
/*  48 */     this.type = njitNpoPlan.getType();
/*  49 */     this.detail = njitNpoPlan.getDetail();
/*  50 */     this.version = njitNpoPlan.getVersion();
/*     */   }
/*     */   public WmsPart getPartId() {
/*  53 */     return this.partId;
/*     */   }
/*     */   public void setPartId(WmsPart partId) {
/*  56 */     this.partId = partId;
/*     */   }
/*     */   public BigDecimal getQty() {
/*  59 */     return this.qty;
/*     */   }
/*     */   public void setQty(BigDecimal qty) {
/*  62 */     this.qty = qty;
/*     */   }
/*     */   public String getUnit() {
/*  65 */     return this.unit;
/*     */   }
/*     */   public void setUnit(String unit) {
/*  68 */     this.unit = unit;
/*     */   }
/*     */   public Date getNeedDate() {
/*  71 */     return this.needDate;
/*     */   }
/*     */   public void setNeedDate(Date needDate) {
/*  74 */     this.needDate = needDate;
/*     */   }
/*     */   public Date getCreateDate() {
/*  77 */     return this.createDate;
/*     */   }
/*     */   public void setCreateDate(Date createDate) {
/*  80 */     this.createDate = createDate;
/*     */   }
/*     */   public EnabledDisabled getIsEnabled() {
/*  83 */     return this.isEnabled;
/*     */   }
/*     */   public void setIsEnabled(EnabledDisabled isEnabled) {
/*  86 */     this.isEnabled = isEnabled;
/*     */   }
/*     */   public String getDataset() {
/*  89 */     return this.dataset;
/*     */   }
/*     */   public void setDataset(String dataset) {
/*  92 */     this.dataset = dataset;
/*     */   }
/*     */   public String getNbr() {
/*  95 */     return this.nbr;
/*     */   }
/*     */   public void setNbr(String nbr) {
/*  98 */     this.nbr = nbr;
/*     */   }
/*     */   public String getLine() {
/* 101 */     return this.line;
/*     */   }
/*     */   public void setLine(String line) {
/* 104 */     this.line = line;
/*     */   }
/*     */   public Date getReldate() {
/* 107 */     return this.reldate;
/*     */   }
/*     */   public void setReldate(Date reldate) {
/* 110 */     this.reldate = reldate;
/*     */   }
/*     */   public Integer getTime() {
/* 113 */     return this.time;
/*     */   }
/*     */   public void setTime(Integer time) {
/* 116 */     this.time = time;
/*     */   }
/*     */   public String getType() {
/* 119 */     return this.type;
/*     */   }
/*     */   public void setType(String type) {
/* 122 */     this.type = type;
/*     */   }
/*     */   public String getDetail() {
/* 125 */     return this.detail;
/*     */   }
/*     */   public void setDetail(String detail) {
/* 128 */     this.detail = detail;
/*     */   }
/*     */   public String getVersion() {
/* 131 */     return this.version;
/*     */   }
/*     */   public void setVersion(String version) {
/* 134 */     this.version = version;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/AbstractNjitnpoplanhistory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
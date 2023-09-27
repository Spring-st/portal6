/*     */ package com.aof.model.basic;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractBasicPartPrice implements Serializable {
/*     */   private Integer id;
/*     */   private String customer;
/*     */   private String sotaxc;
/*     */   private String partId;
/*     */   private String solist;
/*     */   private String pcDesc;
/*     */   private String curr;
/*     */   private String pcUm;
/*     */   private Date startDate;
/*     */   private Date expireDate;
/*     */   private String amtType;
/*     */   private BigDecimal amt;
/*     */   private String domain;
/*     */   private String site;
/*     */   private String rmks;
/*     */   private Date createDate;
/*     */   private String createUser;
/*     */   private Date updateDate;
/*     */   private String updateUser;
/*     */   private String seq;
/*     */   private String mtType;
/*     */   
/*     */   public Integer getId() {
/*  31 */     return this.id;
/*     */   }
/*     */   public void setId(Integer id) {
/*  34 */     this.id = id;
/*     */   }
/*     */   
/*     */   public AbstractBasicPartPrice() {}
/*     */   
/*     */   public AbstractBasicPartPrice(Integer id) {
/*  40 */     this.id = id;
/*     */   }
/*     */   public String getCustomer() {
/*  43 */     return this.customer;
/*     */   }
/*     */   public void setCustomer(String customer) {
/*  46 */     this.customer = customer;
/*     */   }
/*     */   public String getSotaxc() {
/*  49 */     return this.sotaxc;
/*     */   }
/*     */   public void setSotaxc(String sotaxc) {
/*  52 */     this.sotaxc = sotaxc;
/*     */   }
/*     */   public String getPartId() {
/*  55 */     return this.partId;
/*     */   }
/*     */   public void setPartId(String partId) {
/*  58 */     this.partId = partId;
/*     */   }
/*     */   public String getSolist() {
/*  61 */     return this.solist;
/*     */   }
/*     */   public void setSolist(String solist) {
/*  64 */     this.solist = solist;
/*     */   }
/*     */   public String getPcDesc() {
/*  67 */     return this.pcDesc;
/*     */   }
/*     */   public void setPcDesc(String pcDesc) {
/*  70 */     this.pcDesc = pcDesc;
/*     */   }
/*     */   public String getCurr() {
/*  73 */     return this.curr;
/*     */   }
/*     */   public void setCurr(String curr) {
/*  76 */     this.curr = curr;
/*     */   }
/*     */   public String getPcUm() {
/*  79 */     return this.pcUm;
/*     */   }
/*     */   public void setPcUm(String pcUm) {
/*  82 */     this.pcUm = pcUm;
/*     */   }
/*     */   public Date getStartDate() {
/*  85 */     return this.startDate;
/*     */   }
/*     */   public void setStartDate(Date startDate) {
/*  88 */     this.startDate = startDate;
/*     */   }
/*     */   public Date getExpireDate() {
/*  91 */     return this.expireDate;
/*     */   }
/*     */   public void setExpireDate(Date expireDate) {
/*  94 */     this.expireDate = expireDate;
/*     */   }
/*     */   public String getAmtType() {
/*  97 */     return this.amtType;
/*     */   }
/*     */   public void setAmtType(String amtType) {
/* 100 */     this.amtType = amtType;
/*     */   }
/*     */   public BigDecimal getAmt() {
/* 103 */     return this.amt;
/*     */   }
/*     */   public void setAmt(BigDecimal amt) {
/* 106 */     this.amt = amt;
/*     */   }
/*     */   public String getDomain() {
/* 109 */     return this.domain;
/*     */   }
/*     */   public void setDomain(String domain) {
/* 112 */     this.domain = domain;
/*     */   }
/*     */   public String getSite() {
/* 115 */     return this.site;
/*     */   }
/*     */   public void setSite(String site) {
/* 118 */     this.site = site;
/*     */   }
/*     */   public String getRmks() {
/* 121 */     return this.rmks;
/*     */   }
/*     */   public void setRmks(String rmks) {
/* 124 */     this.rmks = rmks;
/*     */   }
/*     */   public Date getCreateDate() {
/* 127 */     return this.createDate;
/*     */   }
/*     */   public void setCreateDate(Date createDate) {
/* 130 */     this.createDate = createDate;
/*     */   }
/*     */   public String getCreateUser() {
/* 133 */     return this.createUser;
/*     */   }
/*     */   public void setCreateUser(String createUser) {
/* 136 */     this.createUser = createUser;
/*     */   }
/*     */   public Date getUpdateDate() {
/* 139 */     return this.updateDate;
/*     */   }
/*     */   public void setUpdateDate(Date updateDate) {
/* 142 */     this.updateDate = updateDate;
/*     */   }
/*     */   public String getUpdateUser() {
/* 145 */     return this.updateUser;
/*     */   }
/*     */   public void setUpdateUser(String updateUser) {
/* 148 */     this.updateUser = updateUser;
/*     */   }
/*     */   public String getSeq() {
/* 151 */     return this.seq;
/*     */   }
/*     */   public void setSeq(String seq) {
/* 154 */     this.seq = seq;
/*     */   }
/*     */   public String getMtType() {
/* 157 */     return this.mtType;
/*     */   }
/*     */   public void setMtType(String mtType) {
/* 160 */     this.mtType = mtType;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractBasicPartPrice.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
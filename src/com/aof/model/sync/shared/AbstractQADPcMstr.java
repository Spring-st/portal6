/*     */ package com.aof.model.sync.shared;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractQADPcMstr implements Serializable {
/*     */   private Integer xxqadPcId;
/*     */   private String xxqadPcShipto;
/*     */   private String xxqadPcSotaxc;
/*     */   private String xxqadPcSopart;
/*     */   private String xxqadPcSolist;
/*     */   private String xxqadPcDesc;
/*     */   private String xxqadPcCurr;
/*     */   private String xxqadPcUm;
/*     */   private Date xxqadPcStart;
/*     */   private Date xxqadPcExpire;
/*     */   private String xxqadPcAmtType;
/*     */   private BigDecimal xxqadPcAmt;
/*     */   private String xxqadPcDomain;
/*     */   private String xxqadPcSite;
/*     */   private String xxqadPcQadread;
/*     */   private String xxqadPcMesread;
/*     */   private String xxqadPcPortalread;
/*     */   private String xxqadPcEdiread;
/*     */   private String xxqadPcRmks;
/*     */   private Date xxqadPcCreatedt;
/*     */   private String xxqadPcCreateur;
/*     */   private Date xxqadPcUpdatedt;
/*     */   private String xxqadPcUpdateur;
/*     */   private String xxqadPcSeq;
/*     */   
/*     */   public AbstractQADPcMstr() {}
/*     */   
/*     */   public AbstractQADPcMstr(Integer xxqadPcId) {
/*  36 */     this.xxqadPcId = xxqadPcId;
/*     */   }
/*     */   public Integer getXxqadPcId() {
/*  39 */     return this.xxqadPcId;
/*     */   }
/*     */   public void setXxqadPcId(Integer xxqadPcId) {
/*  42 */     this.xxqadPcId = xxqadPcId;
/*     */   }
/*     */   public String getXxqadPcShipto() {
/*  45 */     return this.xxqadPcShipto;
/*     */   }
/*     */   public void setXxqadPcShipto(String xxqadPcShipto) {
/*  48 */     this.xxqadPcShipto = xxqadPcShipto;
/*     */   }
/*     */   public String getXxqadPcSotaxc() {
/*  51 */     return this.xxqadPcSotaxc;
/*     */   }
/*     */   public void setXxqadPcSotaxc(String xxqadPcSotaxc) {
/*  54 */     this.xxqadPcSotaxc = xxqadPcSotaxc;
/*     */   }
/*     */   public String getXxqadPcSopart() {
/*  57 */     return this.xxqadPcSopart;
/*     */   }
/*     */   public void setXxqadPcSopart(String xxqadPcSopart) {
/*  60 */     this.xxqadPcSopart = xxqadPcSopart;
/*     */   }
/*     */   public String getXxqadPcSolist() {
/*  63 */     return this.xxqadPcSolist;
/*     */   }
/*     */   public void setXxqadPcSolist(String xxqadPcSolist) {
/*  66 */     this.xxqadPcSolist = xxqadPcSolist;
/*     */   }
/*     */   public String getXxqadPcDesc() {
/*  69 */     return this.xxqadPcDesc;
/*     */   }
/*     */   public void setXxqadPcDesc(String xxqadPcDesc) {
/*  72 */     this.xxqadPcDesc = xxqadPcDesc;
/*     */   }
/*     */   public String getXxqadPcCurr() {
/*  75 */     return this.xxqadPcCurr;
/*     */   }
/*     */   public void setXxqadPcCurr(String xxqadPcCurr) {
/*  78 */     this.xxqadPcCurr = xxqadPcCurr;
/*     */   }
/*     */   public String getXxqadPcUm() {
/*  81 */     return this.xxqadPcUm;
/*     */   }
/*     */   public void setXxqadPcUm(String xxqadPcUm) {
/*  84 */     this.xxqadPcUm = xxqadPcUm;
/*     */   }
/*     */   public Date getXxqadPcStart() {
/*  87 */     return this.xxqadPcStart;
/*     */   }
/*     */   public void setXxqadPcStart(Date xxqadPcStart) {
/*  90 */     this.xxqadPcStart = xxqadPcStart;
/*     */   }
/*     */   public Date getXxqadPcExpire() {
/*  93 */     return this.xxqadPcExpire;
/*     */   }
/*     */   public void setXxqadPcExpire(Date xxqadPcExpire) {
/*  96 */     this.xxqadPcExpire = xxqadPcExpire;
/*     */   }
/*     */   public String getXxqadPcAmtType() {
/*  99 */     return this.xxqadPcAmtType;
/*     */   }
/*     */   public void setXxqadPcAmtType(String xxqadPcAmtType) {
/* 102 */     this.xxqadPcAmtType = xxqadPcAmtType;
/*     */   }
/*     */   public BigDecimal getXxqadPcAmt() {
/* 105 */     return this.xxqadPcAmt;
/*     */   }
/*     */   public void setXxqadPcAmt(BigDecimal xxqadPcAmt) {
/* 108 */     this.xxqadPcAmt = xxqadPcAmt;
/*     */   }
/*     */   public String getXxqadPcDomain() {
/* 111 */     return this.xxqadPcDomain;
/*     */   }
/*     */   public void setXxqadPcDomain(String xxqadPcDomain) {
/* 114 */     this.xxqadPcDomain = xxqadPcDomain;
/*     */   }
/*     */   public String getXxqadPcSite() {
/* 117 */     return this.xxqadPcSite;
/*     */   }
/*     */   public void setXxqadPcSite(String xxqadPcSite) {
/* 120 */     this.xxqadPcSite = xxqadPcSite;
/*     */   }
/*     */   public String getXxqadPcQadread() {
/* 123 */     return this.xxqadPcQadread;
/*     */   }
/*     */   public void setXxqadPcQadread(String xxqadPcQadread) {
/* 126 */     this.xxqadPcQadread = xxqadPcQadread;
/*     */   }
/*     */   public String getXxqadPcMesread() {
/* 129 */     return this.xxqadPcMesread;
/*     */   }
/*     */   public void setXxqadPcMesread(String xxqadPcMesread) {
/* 132 */     this.xxqadPcMesread = xxqadPcMesread;
/*     */   }
/*     */   public String getXxqadPcPortalread() {
/* 135 */     return this.xxqadPcPortalread;
/*     */   }
/*     */   public void setXxqadPcPortalread(String xxqadPcPortalread) {
/* 138 */     this.xxqadPcPortalread = xxqadPcPortalread;
/*     */   }
/*     */   public String getXxqadPcEdiread() {
/* 141 */     return this.xxqadPcEdiread;
/*     */   }
/*     */   public void setXxqadPcEdiread(String xxqadPcEdiread) {
/* 144 */     this.xxqadPcEdiread = xxqadPcEdiread;
/*     */   }
/*     */   public String getXxqadPcRmks() {
/* 147 */     return this.xxqadPcRmks;
/*     */   }
/*     */   public void setXxqadPcRmks(String xxqadPcRmks) {
/* 150 */     this.xxqadPcRmks = xxqadPcRmks;
/*     */   }
/*     */   public Date getXxqadPcCreatedt() {
/* 153 */     return this.xxqadPcCreatedt;
/*     */   }
/*     */   public void setXxqadPcCreatedt(Date xxqadPcCreatedt) {
/* 156 */     this.xxqadPcCreatedt = xxqadPcCreatedt;
/*     */   }
/*     */   public String getXxqadPcCreateur() {
/* 159 */     return this.xxqadPcCreateur;
/*     */   }
/*     */   public void setXxqadPcCreateur(String xxqadPcCreateur) {
/* 162 */     this.xxqadPcCreateur = xxqadPcCreateur;
/*     */   }
/*     */   public Date getXxqadPcUpdatedt() {
/* 165 */     return this.xxqadPcUpdatedt;
/*     */   }
/*     */   public void setXxqadPcUpdatedt(Date xxqadPcUpdatedt) {
/* 168 */     this.xxqadPcUpdatedt = xxqadPcUpdatedt;
/*     */   }
/*     */   public String getXxqadPcUpdateur() {
/* 171 */     return this.xxqadPcUpdateur;
/*     */   }
/*     */   public void setXxqadPcUpdateur(String xxqadPcUpdateur) {
/* 174 */     this.xxqadPcUpdateur = xxqadPcUpdateur;
/*     */   }
/*     */   public String getXxqadPcSeq() {
/* 177 */     return this.xxqadPcSeq;
/*     */   }
/*     */   public void setXxqadPcSeq(String xxqadPcSeq) {
/* 180 */     this.xxqadPcSeq = xxqadPcSeq;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractQADPcMstr.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
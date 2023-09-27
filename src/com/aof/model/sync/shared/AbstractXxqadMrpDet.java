/*     */ package com.aof.model.sync.shared;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractXxqadMrpDet implements Serializable {
/*     */   private Integer xxqadMrpId;
/*     */   private String xxqadMrpDataset;
/*     */   private String xxqadMrpPart;
/*     */   private String xxqadMrpNbr;
/*     */   private String xxqadMrpLine;
/*     */   private Date xxqadMrpRelDate;
/*     */   private Date xxqadMrpDueDate;
/*     */   private BigDecimal xxqadMrpQty;
/*     */   private Integer xxqadMrpTime;
/*     */   private String xxqadMrpType;
/*     */   private String xxqadMrpSite;
/*     */   private String xxqadMrpDomain;
/*     */   private String xxqadMrpDetail;
/*     */   private String xxqadMrpMesread;
/*     */   private String xxqadMrpQadread;
/*     */   private String xxqadMrpPortalread;
/*     */   private String xxqadMrpEdiread;
/*     */   private String xxqadMrpRmks;
/*     */   private Date xxqadMrpCreatedt;
/*     */   private String xxqadMrpCreateur;
/*     */   private Date xxqadMrpUpdatedt;
/*     */   private String xxqadMrpUpdateur;
/*     */   private String xxqadMrpRev;
/*     */   
/*     */   public AbstractXxqadMrpDet() {}
/*     */   
/*     */   public AbstractXxqadMrpDet(Integer id) {
/*  34 */     this.xxqadMrpId = id;
/*     */   } public Integer getXxqadMrpId() {
/*  36 */     return this.xxqadMrpId;
/*     */   }
/*     */   public void setXxqadMrpId(Integer xxqadMrpId) {
/*  39 */     this.xxqadMrpId = xxqadMrpId;
/*     */   }
/*     */   public String getXxqadMrpPart() {
/*  42 */     return this.xxqadMrpPart;
/*     */   }
/*     */   public void setXxqadMrpPart(String xxqadMrpPart) {
/*  45 */     this.xxqadMrpPart = xxqadMrpPart;
/*     */   }
/*     */   public String getXxqadMrpNbr() {
/*  48 */     return this.xxqadMrpNbr;
/*     */   }
/*     */   public void setXxqadMrpNbr(String xxqadMrpNbr) {
/*  51 */     this.xxqadMrpNbr = xxqadMrpNbr;
/*     */   }
/*     */   public String getXxqadMrpLine() {
/*  54 */     return this.xxqadMrpLine;
/*     */   }
/*     */   public void setXxqadMrpLine(String xxqadMrpLine) {
/*  57 */     this.xxqadMrpLine = xxqadMrpLine;
/*     */   }
/*     */   public Date getXxqadMrpRelDate() {
/*  60 */     return this.xxqadMrpRelDate;
/*     */   }
/*     */   public void setXxqadMrpRelDate(Date xxqadMrpRelDate) {
/*  63 */     this.xxqadMrpRelDate = xxqadMrpRelDate;
/*     */   }
/*     */   public Date getXxqadMrpDueDate() {
/*  66 */     return this.xxqadMrpDueDate;
/*     */   }
/*     */   public void setXxqadMrpDueDate(Date xxqadMrpDueDate) {
/*  69 */     this.xxqadMrpDueDate = xxqadMrpDueDate;
/*     */   }
/*     */   public BigDecimal getXxqadMrpQty() {
/*  72 */     return this.xxqadMrpQty;
/*     */   }
/*     */   public void setXxqadMrpQty(BigDecimal xxqadMrpQty) {
/*  75 */     this.xxqadMrpQty = xxqadMrpQty;
/*     */   }
/*     */   public Integer getXxqadMrpTime() {
/*  78 */     return this.xxqadMrpTime;
/*     */   }
/*     */   public void setXxqadMrpTime(Integer xxqadMrpTime) {
/*  81 */     this.xxqadMrpTime = xxqadMrpTime;
/*     */   }
/*     */   public String getXxqadMrpType() {
/*  84 */     return this.xxqadMrpType;
/*     */   }
/*     */   public void setXxqadMrpType(String xxqadMrpType) {
/*  87 */     this.xxqadMrpType = xxqadMrpType;
/*     */   }
/*     */   public String getXxqadMrpSite() {
/*  90 */     return this.xxqadMrpSite;
/*     */   }
/*     */   public void setXxqadMrpSite(String xxqadMrpSite) {
/*  93 */     this.xxqadMrpSite = xxqadMrpSite;
/*     */   }
/*     */   public String getXxqadMrpDomain() {
/*  96 */     return this.xxqadMrpDomain;
/*     */   }
/*     */   public void setXxqadMrpDomain(String xxqadMrpDomain) {
/*  99 */     this.xxqadMrpDomain = xxqadMrpDomain;
/*     */   }
/*     */   public String getXxqadMrpDetail() {
/* 102 */     return this.xxqadMrpDetail;
/*     */   }
/*     */   public void setXxqadMrpDetail(String xxqadMrpDetail) {
/* 105 */     this.xxqadMrpDetail = xxqadMrpDetail;
/*     */   }
/*     */   public String getXxqadMrpMesread() {
/* 108 */     return this.xxqadMrpMesread;
/*     */   }
/*     */   public void setXxqadMrpMesread(String xxqadMrpMesread) {
/* 111 */     this.xxqadMrpMesread = xxqadMrpMesread;
/*     */   }
/*     */   public String getXxqadMrpQadread() {
/* 114 */     return this.xxqadMrpQadread;
/*     */   }
/*     */   public void setXxqadMrpQadread(String xxqadMrpQadread) {
/* 117 */     this.xxqadMrpQadread = xxqadMrpQadread;
/*     */   }
/*     */   public String getXxqadMrpPortalread() {
/* 120 */     return this.xxqadMrpPortalread;
/*     */   }
/*     */   public void setXxqadMrpPortalread(String xxqadMrpPortalread) {
/* 123 */     this.xxqadMrpPortalread = xxqadMrpPortalread;
/*     */   }
/*     */   public String getXxqadMrpEdiread() {
/* 126 */     return this.xxqadMrpEdiread;
/*     */   }
/*     */   public void setXxqadMrpEdiread(String xxqadMrpEdiread) {
/* 129 */     this.xxqadMrpEdiread = xxqadMrpEdiread;
/*     */   }
/*     */   public String getXxqadMrpRmks() {
/* 132 */     return this.xxqadMrpRmks;
/*     */   }
/*     */   public void setXxqadMrpRmks(String xxqadMrpRmks) {
/* 135 */     this.xxqadMrpRmks = xxqadMrpRmks;
/*     */   }
/*     */   public Date getXxqadMrpCreatedt() {
/* 138 */     return this.xxqadMrpCreatedt;
/*     */   }
/*     */   public void setXxqadMrpCreatedt(Date xxqadMrpCreatedt) {
/* 141 */     this.xxqadMrpCreatedt = xxqadMrpCreatedt;
/*     */   }
/*     */   public String getXxqadMrpCreateur() {
/* 144 */     return this.xxqadMrpCreateur;
/*     */   }
/*     */   public void setXxqadMrpCreateur(String xxqadMrpCreateur) {
/* 147 */     this.xxqadMrpCreateur = xxqadMrpCreateur;
/*     */   }
/*     */   public Date getXxqadMrpUpdatedt() {
/* 150 */     return this.xxqadMrpUpdatedt;
/*     */   }
/*     */   public void setXxqadMrpUpdatedt(Date xxqadMrpUpdatedt) {
/* 153 */     this.xxqadMrpUpdatedt = xxqadMrpUpdatedt;
/*     */   }
/*     */   public String getXxqadMrpUpdateur() {
/* 156 */     return this.xxqadMrpUpdateur;
/*     */   }
/*     */   public void setXxqadMrpUpdateur(String xxqadMrpUpdateur) {
/* 159 */     this.xxqadMrpUpdateur = xxqadMrpUpdateur;
/*     */   }
/*     */   public String getXxqadMrpRev() {
/* 162 */     return this.xxqadMrpRev;
/*     */   }
/*     */   public void setXxqadMrpRev(String xxqadMrpRev) {
/* 165 */     this.xxqadMrpRev = xxqadMrpRev;
/*     */   }
/*     */   public String getXxqadMrpDataset() {
/* 168 */     return this.xxqadMrpDataset;
/*     */   }
/*     */   public void setXxqadMrpDataset(String xxqadMrpDataset) {
/* 171 */     this.xxqadMrpDataset = xxqadMrpDataset;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractXxqadMrpDet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
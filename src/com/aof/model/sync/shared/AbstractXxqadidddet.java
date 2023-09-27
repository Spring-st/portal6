/*     */ package com.aof.model.sync.shared;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractXxqadidddet
/*     */   implements Serializable
/*     */ {
/*     */   private Integer xxqadLdId;
/*     */   private String xxqadLdSite;
/*     */   private String xxqadLdDomain;
/*     */   private String xxqadLdPart;
/*     */   private String xxqadLdLot;
/*     */   private String xxqadLdLoc;
/*     */   private Integer xxqadLdQtyOh;
/*     */   private String xxqadLdStatus;
/*     */   private String xxqadLdQadread;
/*     */   
/*     */   public AbstractXxqadidddet(Integer id) {
/*  26 */     this.xxqadLdId = id;
/*     */   } private String xxqadLdMesread; private String xxqadLdPortalread; private String xxqadLdEdiread; private Date xxqadLdCreatedt; private String xxqadLdCreateur; private Date xxqadLdUpdatedt; private String xxqadLdUpdateur; private String xxqadLdSeq; public AbstractXxqadidddet() {} public Integer getXxqadLdId() {
/*  28 */     return this.xxqadLdId;
/*     */   }
/*     */   public void setXxqadLdId(Integer xxqadLdId) {
/*  31 */     this.xxqadLdId = xxqadLdId;
/*     */   }
/*     */   public String getXxqadLdSite() {
/*  34 */     return this.xxqadLdSite;
/*     */   }
/*     */   public void setXxqadLdSite(String xxqadLdSite) {
/*  37 */     this.xxqadLdSite = xxqadLdSite;
/*     */   }
/*     */   public String getXxqadLdDomain() {
/*  40 */     return this.xxqadLdDomain;
/*     */   }
/*     */   public void setXxqadLdDomain(String xxqadLdDomain) {
/*  43 */     this.xxqadLdDomain = xxqadLdDomain;
/*     */   }
/*     */   public String getXxqadLdPart() {
/*  46 */     return this.xxqadLdPart;
/*     */   }
/*     */   public void setXxqadLdPart(String xxqadLdPart) {
/*  49 */     this.xxqadLdPart = xxqadLdPart;
/*     */   }
/*     */   public String getXxqadLdLot() {
/*  52 */     return this.xxqadLdLot;
/*     */   }
/*     */   public void setXxqadLdLot(String xxqadLdLot) {
/*  55 */     this.xxqadLdLot = xxqadLdLot;
/*     */   }
/*     */   public String getXxqadLdLoc() {
/*  58 */     return this.xxqadLdLoc;
/*     */   }
/*     */   public void setXxqadLdLoc(String xxqadLdLoc) {
/*  61 */     this.xxqadLdLoc = xxqadLdLoc;
/*     */   }
/*     */   public Integer getXxqadLdQtyOh() {
/*  64 */     return this.xxqadLdQtyOh;
/*     */   }
/*     */   public void setXxqadLdQtyOh(Integer xxqadLdQtyOh) {
/*  67 */     this.xxqadLdQtyOh = xxqadLdQtyOh;
/*     */   }
/*     */   public String getXxqadLdStatus() {
/*  70 */     return this.xxqadLdStatus;
/*     */   }
/*     */   public void setXxqadLdStatus(String xxqadLdStatus) {
/*  73 */     this.xxqadLdStatus = xxqadLdStatus;
/*     */   }
/*     */   public String getXxqadLdQadread() {
/*  76 */     return this.xxqadLdQadread;
/*     */   }
/*     */   public void setXxqadLdQadread(String xxqadLdQadread) {
/*  79 */     this.xxqadLdQadread = xxqadLdQadread;
/*     */   }
/*     */   public String getXxqadLdMesread() {
/*  82 */     return this.xxqadLdMesread;
/*     */   }
/*     */   public void setXxqadLdMesread(String xxqadLdMesread) {
/*  85 */     this.xxqadLdMesread = xxqadLdMesread;
/*     */   }
/*     */   public String getXxqadLdPortalread() {
/*  88 */     return this.xxqadLdPortalread;
/*     */   }
/*     */   public void setXxqadLdPortalread(String xxqadLdPortalread) {
/*  91 */     this.xxqadLdPortalread = xxqadLdPortalread;
/*     */   }
/*     */   public String getXxqadLdEdiread() {
/*  94 */     return this.xxqadLdEdiread;
/*     */   }
/*     */   public void setXxqadLdEdiread(String xxqadLdEdiread) {
/*  97 */     this.xxqadLdEdiread = xxqadLdEdiread;
/*     */   }
/*     */   public Date getXxqadLdCreatedt() {
/* 100 */     return this.xxqadLdCreatedt;
/*     */   }
/*     */   public void setXxqadLdCreatedt(Date xxqadLdCreatedt) {
/* 103 */     this.xxqadLdCreatedt = xxqadLdCreatedt;
/*     */   }
/*     */   public String getXxqadLdCreateur() {
/* 106 */     return this.xxqadLdCreateur;
/*     */   }
/*     */   public void setXxqadLdCreateur(String xxqadLdCreateur) {
/* 109 */     this.xxqadLdCreateur = xxqadLdCreateur;
/*     */   }
/*     */   public Date getXxqadLdUpdatedt() {
/* 112 */     return this.xxqadLdUpdatedt;
/*     */   }
/*     */   public void setXxqadLdUpdatedt(Date xxqadLdUpdatedt) {
/* 115 */     this.xxqadLdUpdatedt = xxqadLdUpdatedt;
/*     */   }
/*     */   public String getXxqadLdUpdateur() {
/* 118 */     return this.xxqadLdUpdateur;
/*     */   }
/*     */   public void setXxqadLdUpdateur(String xxqadLdUpdateur) {
/* 121 */     this.xxqadLdUpdateur = xxqadLdUpdateur;
/*     */   }
/*     */   public String getXxqadLdSeq() {
/* 124 */     return this.xxqadLdSeq;
/*     */   }
/*     */   public void setXxqadLdSeq(String xxqadLdSeq) {
/* 127 */     this.xxqadLdSeq = xxqadLdSeq;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractXxqadidddet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
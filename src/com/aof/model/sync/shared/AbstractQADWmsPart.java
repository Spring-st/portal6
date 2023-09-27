/*     */ package com.aof.model.sync.shared;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractQADWmsPart
/*     */   implements Serializable
/*     */ {
/*  15 */   private int hashValue = 0;
/*     */   
/*     */   private Integer xxqad_pt_id;
/*     */   private String xxqadPtPart;
/*     */   private String xxqadPtUm;
/*     */   private String xxqadPtDesc1;
/*     */   private String xxqadPtDesc2;
/*     */   private String xxqadPtDsgnGrp;
/*     */   private String xxqadPtPromo;
/*     */   private String xxqadPtPartType;
/*     */   private String xxqadPtProdLine;
/*     */   private String xxqadPtStatus;
/*     */   private Integer xxqadPtSize;
/*     */   private String xxqadPtGroup;
/*     */   private Integer xxqadPtShipWt;
/*     */   private Integer xxqadPtNetWt;
/*     */   private String xxqadPtVend;
/*     */   private String xxqadPtSite;
/*     */   private String xxqadPtDomain;
/*     */   private String xxqadPtQadread;
/*     */   private String xxqadPtMesread;
/*     */   private String xxqadPtPortalread;
/*     */   private String xxqadPtEdiread;
/*     */   private String xxqadPtRmks;
/*     */   private Date xxqadPtCreatedt;
/*     */   private String xxqadPtCreateur;
/*     */   private Date xxqadPtUpdatedt;
/*     */   private String xxqadPtUpdateur;
/*     */   private String xxqadPtSeq;
/*     */   private BigDecimal xxqadPtFrClass;
/*     */   private String xxqadPtDraw;
/*     */   private String xxqad_pt_drwg_loc;
/*     */   
/*     */   public AbstractQADWmsPart() {}
/*     */   
/*     */   public AbstractQADWmsPart(Integer id) {
/*  51 */     setXxqad_pt_id(id);
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
/*  62 */     if (rhs == null) return false; 
/*  63 */     if (this == rhs) return true; 
/*  64 */     if (!(rhs instanceof QADWmsPart)) return false; 
/*  65 */     QADWmsPart that = (QADWmsPart)rhs;
/*  66 */     if (getXxqad_pt_id() != null) return getXxqad_pt_id().equals(that.getXxqad_pt_id()); 
/*  67 */     return (that.getXxqad_pt_id() == null);
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
/*  78 */     if (this.hashValue == 0) {
/*  79 */       int result = 17;
/*  80 */       int poIdValue = (getXxqad_pt_id() == null) ? 0 : getXxqad_pt_id().hashCode();
/*  81 */       result = result * 37 + poIdValue;
/*  82 */       this.hashValue = result;
/*     */     } 
/*  84 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_pt_id() {
/*  88 */     return this.xxqad_pt_id;
/*     */   }
/*     */   
/*     */   public void setXxqad_pt_id(Integer xxqad_pt_id) {
/*  92 */     this.xxqad_pt_id = xxqad_pt_id;
/*     */   }
/*     */   
/*     */   public String getXxqadPtPart() {
/*  96 */     return this.xxqadPtPart;
/*     */   }
/*     */   
/*     */   public void setXxqadPtPart(String xxqadPtPart) {
/* 100 */     this.xxqadPtPart = xxqadPtPart;
/*     */   }
/*     */   
/*     */   public String getXxqadPtUm() {
/* 104 */     return this.xxqadPtUm;
/*     */   }
/*     */   
/*     */   public void setXxqadPtUm(String xxqadPtUm) {
/* 108 */     this.xxqadPtUm = xxqadPtUm;
/*     */   }
/*     */   
/*     */   public String getXxqadPtDesc1() {
/* 112 */     return this.xxqadPtDesc1;
/*     */   }
/*     */   
/*     */   public void setXxqadPtDesc1(String xxqadPtDesc1) {
/* 116 */     this.xxqadPtDesc1 = xxqadPtDesc1;
/*     */   }
/*     */   
/*     */   public String getXxqadPtDesc2() {
/* 120 */     return this.xxqadPtDesc2;
/*     */   }
/*     */   
/*     */   public void setXxqadPtDesc2(String xxqadPtDesc2) {
/* 124 */     this.xxqadPtDesc2 = xxqadPtDesc2;
/*     */   }
/*     */   
/*     */   public String getXxqadPtDsgnGrp() {
/* 128 */     return this.xxqadPtDsgnGrp;
/*     */   }
/*     */   
/*     */   public void setXxqadPtDsgnGrp(String xxqadPtDsgnGrp) {
/* 132 */     this.xxqadPtDsgnGrp = xxqadPtDsgnGrp;
/*     */   }
/*     */   
/*     */   public String getXxqadPtPromo() {
/* 136 */     return this.xxqadPtPromo;
/*     */   }
/*     */   
/*     */   public void setXxqadPtPromo(String xxqadPtPromo) {
/* 140 */     this.xxqadPtPromo = xxqadPtPromo;
/*     */   }
/*     */   
/*     */   public String getXxqadPtPartType() {
/* 144 */     return this.xxqadPtPartType;
/*     */   }
/*     */   
/*     */   public void setXxqadPtPartType(String xxqadPtPartType) {
/* 148 */     this.xxqadPtPartType = xxqadPtPartType;
/*     */   }
/*     */   
/*     */   public String getXxqadPtProdLine() {
/* 152 */     return this.xxqadPtProdLine;
/*     */   }
/*     */   
/*     */   public void setXxqadPtProdLine(String xxqadPtProdLine) {
/* 156 */     this.xxqadPtProdLine = xxqadPtProdLine;
/*     */   }
/*     */   
/*     */   public String getXxqadPtStatus() {
/* 160 */     return this.xxqadPtStatus;
/*     */   }
/*     */   
/*     */   public void setXxqadPtStatus(String xxqadPtStatus) {
/* 164 */     this.xxqadPtStatus = xxqadPtStatus;
/*     */   }
/*     */   
/*     */   public Integer getXxqadPtSize() {
/* 168 */     return this.xxqadPtSize;
/*     */   }
/*     */   
/*     */   public void setXxqadPtSize(Integer xxqadPtSize) {
/* 172 */     this.xxqadPtSize = xxqadPtSize;
/*     */   }
/*     */   
/*     */   public String getXxqadPtGroup() {
/* 176 */     return this.xxqadPtGroup;
/*     */   }
/*     */   
/*     */   public void setXxqadPtGroup(String xxqadPtGroup) {
/* 180 */     this.xxqadPtGroup = xxqadPtGroup;
/*     */   }
/*     */   
/*     */   public Integer getXxqadPtShipWt() {
/* 184 */     return this.xxqadPtShipWt;
/*     */   }
/*     */   
/*     */   public void setXxqadPtShipWt(Integer xxqadPtShipWt) {
/* 188 */     this.xxqadPtShipWt = xxqadPtShipWt;
/*     */   }
/*     */   
/*     */   public Integer getXxqadPtNetWt() {
/* 192 */     return this.xxqadPtNetWt;
/*     */   }
/*     */   
/*     */   public void setXxqadPtNetWt(Integer xxqadPtNetWt) {
/* 196 */     this.xxqadPtNetWt = xxqadPtNetWt;
/*     */   }
/*     */   
/*     */   public String getXxqadPtVend() {
/* 200 */     return this.xxqadPtVend;
/*     */   }
/*     */   
/*     */   public void setXxqadPtVend(String xxqadPtVend) {
/* 204 */     this.xxqadPtVend = xxqadPtVend;
/*     */   }
/*     */   
/*     */   public String getXxqadPtSite() {
/* 208 */     return this.xxqadPtSite;
/*     */   }
/*     */   
/*     */   public void setXxqadPtSite(String xxqadPtSite) {
/* 212 */     this.xxqadPtSite = xxqadPtSite;
/*     */   }
/*     */   
/*     */   public String getXxqadPtDomain() {
/* 216 */     return this.xxqadPtDomain;
/*     */   }
/*     */   
/*     */   public void setXxqadPtDomain(String xxqadPtDomain) {
/* 220 */     this.xxqadPtDomain = xxqadPtDomain;
/*     */   }
/*     */   
/*     */   public String getXxqadPtQadread() {
/* 224 */     return this.xxqadPtQadread;
/*     */   }
/*     */   
/*     */   public void setXxqadPtQadread(String xxqadPtQadread) {
/* 228 */     this.xxqadPtQadread = xxqadPtQadread;
/*     */   }
/*     */   
/*     */   public String getXxqadPtMesread() {
/* 232 */     return this.xxqadPtMesread;
/*     */   }
/*     */   
/*     */   public void setXxqadPtMesread(String xxqadPtMesread) {
/* 236 */     this.xxqadPtMesread = xxqadPtMesread;
/*     */   }
/*     */   
/*     */   public String getXxqadPtPortalread() {
/* 240 */     return this.xxqadPtPortalread;
/*     */   }
/*     */   
/*     */   public void setXxqadPtPortalread(String xxqadPtPortalread) {
/* 244 */     this.xxqadPtPortalread = xxqadPtPortalread;
/*     */   }
/*     */   
/*     */   public String getXxqadPtEdiread() {
/* 248 */     return this.xxqadPtEdiread;
/*     */   }
/*     */   
/*     */   public void setXxqadPtEdiread(String xxqadPtEdiread) {
/* 252 */     this.xxqadPtEdiread = xxqadPtEdiread;
/*     */   }
/*     */   
/*     */   public String getXxqadPtRmks() {
/* 256 */     return this.xxqadPtRmks;
/*     */   }
/*     */   
/*     */   public void setXxqadPtRmks(String xxqadPtRmks) {
/* 260 */     this.xxqadPtRmks = xxqadPtRmks;
/*     */   }
/*     */   
/*     */   public Date getXxqadPtCreatedt() {
/* 264 */     return this.xxqadPtCreatedt;
/*     */   }
/*     */   
/*     */   public void setXxqadPtCreatedt(Date xxqadPtCreatedt) {
/* 268 */     this.xxqadPtCreatedt = xxqadPtCreatedt;
/*     */   }
/*     */   
/*     */   public String getXxqadPtCreateur() {
/* 272 */     return this.xxqadPtCreateur;
/*     */   }
/*     */   
/*     */   public void setXxqadPtCreateur(String xxqadPtCreateur) {
/* 276 */     this.xxqadPtCreateur = xxqadPtCreateur;
/*     */   }
/*     */   
/*     */   public Date getXxqadPtUpdatedt() {
/* 280 */     return this.xxqadPtUpdatedt;
/*     */   }
/*     */   
/*     */   public void setXxqadPtUpdatedt(Date xxqadPtUpdatedt) {
/* 284 */     this.xxqadPtUpdatedt = xxqadPtUpdatedt;
/*     */   }
/*     */   
/*     */   public String getXxqadPtUpdateur() {
/* 288 */     return this.xxqadPtUpdateur;
/*     */   }
/*     */   
/*     */   public void setXxqadPtUpdateur(String xxqadPtUpdateur) {
/* 292 */     this.xxqadPtUpdateur = xxqadPtUpdateur;
/*     */   }
/*     */   
/*     */   public String getXxqadPtSeq() {
/* 296 */     return this.xxqadPtSeq;
/*     */   }
/*     */   
/*     */   public void setXxqadPtSeq(String xxqadPtSeq) {
/* 300 */     this.xxqadPtSeq = xxqadPtSeq;
/*     */   }
/*     */   
/*     */   public BigDecimal getXxqadPtFrClass() {
/* 304 */     return this.xxqadPtFrClass;
/*     */   }
/*     */   
/*     */   public void setXxqadPtFrClass(BigDecimal xxqadPtFrClass) {
/* 308 */     this.xxqadPtFrClass = xxqadPtFrClass;
/*     */   }
/*     */   
/*     */   public String getXxqadPtDraw() {
/* 312 */     return this.xxqadPtDraw;
/*     */   }
/*     */   
/*     */   public void setXxqadPtDraw(String xxqadPtDraw) {
/* 316 */     this.xxqadPtDraw = xxqadPtDraw;
/*     */   }
/*     */   
/*     */   public String getXxqad_pt_drwg_loc() {
/* 320 */     return this.xxqad_pt_drwg_loc;
/*     */   }
/*     */   
/*     */   public void setXxqad_pt_drwg_loc(String xxqad_pt_drwg_loc) {
/* 324 */     this.xxqad_pt_drwg_loc = xxqad_pt_drwg_loc;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractQADWmsPart.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
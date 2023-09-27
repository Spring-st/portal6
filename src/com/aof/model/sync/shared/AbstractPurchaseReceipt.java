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
/*     */ public abstract class AbstractPurchaseReceipt
/*     */   implements Serializable
/*     */ {
/*  15 */   private int hashValue = 0;
/*     */   
/*     */   private Integer xxmes_porc_id;
/*     */   
/*     */   private Integer xxmes_porc_date1;
/*     */   private Integer xxmes_porc_det_id;
/*     */   private String xxmes_porc_site;
/*     */   private String xxmes_porc_seq;
/*     */   private String xxmes_porc_txnbr;
/*     */   private String xxmes_porc_ponbr;
/*     */   private Integer xxmes_porc_poline;
/*     */   private String xxmes_porc_part;
/*     */   private Date xxmes_porc_date;
/*     */   private String xxmes_porc_boxcode;
/*     */   private BigDecimal xxmes_porc_qty_real;
/*     */   private String xxmes_porc_loc;
/*     */   private String xxmes_porc_poum;
/*     */   private String xxmes_porc_qc;
/*     */   private String xxmes_porc_potype;
/*     */   private String xxmes_porc_qadread;
/*     */   private String xxmes_porc_mesread;
/*     */   private String xxmes_porc_portalread;
/*     */   private String xxmes_porc_ediread;
/*     */   private String xxmes_porc_rmks;
/*     */   private Date xxmes_porc_createdt;
/*     */   private Integer xxmes_porc_createdt1;
/*     */   private String xxmes_porc_createur;
/*     */   private Date xxmes_porc_updatedt;
/*     */   private Integer xxmes_porc_updatedt1;
/*     */   private String xxmes_porc_updateur;
/*     */   private String xxmes_porc_domain;
/*     */   
/*     */   public String getXxmes_porc_domain() {
/*  48 */     return this.xxmes_porc_domain;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_domain(String xxmes_porc_domain) {
/*  52 */     this.xxmes_porc_domain = xxmes_porc_domain;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_porc_id() {
/*  56 */     return this.xxmes_porc_id;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_id(Integer xxmes_porc_id) {
/*  60 */     this.xxmes_porc_id = xxmes_porc_id;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_porc_date1() {
/*  64 */     return this.xxmes_porc_date1;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_date1(Integer xxmes_porc_date1) {
/*  68 */     this.xxmes_porc_date1 = xxmes_porc_date1;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_porc_det_id() {
/*  72 */     return this.xxmes_porc_det_id;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_det_id(Integer xxmes_porc_det_id) {
/*  76 */     this.xxmes_porc_det_id = xxmes_porc_det_id;
/*     */   }
/*     */   
/*     */   public String getXxmes_porc_site() {
/*  80 */     return this.xxmes_porc_site;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_site(String xxmes_porc_site) {
/*  84 */     this.xxmes_porc_site = xxmes_porc_site;
/*     */   }
/*     */   
/*     */   public String getXxmes_porc_seq() {
/*  88 */     return this.xxmes_porc_seq;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_seq(String xxmes_porc_seq) {
/*  92 */     this.xxmes_porc_seq = xxmes_porc_seq;
/*     */   }
/*     */   
/*     */   public String getXxmes_porc_txnbr() {
/*  96 */     return this.xxmes_porc_txnbr;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_txnbr(String xxmes_porc_txnbr) {
/* 100 */     this.xxmes_porc_txnbr = xxmes_porc_txnbr;
/*     */   }
/*     */   
/*     */   public String getXxmes_porc_ponbr() {
/* 104 */     return this.xxmes_porc_ponbr;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_ponbr(String xxmes_porc_ponbr) {
/* 108 */     this.xxmes_porc_ponbr = xxmes_porc_ponbr;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_porc_poline() {
/* 112 */     return this.xxmes_porc_poline;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_poline(Integer xxmes_porc_poline) {
/* 116 */     this.xxmes_porc_poline = xxmes_porc_poline;
/*     */   }
/*     */   
/*     */   public String getXxmes_porc_part() {
/* 120 */     return this.xxmes_porc_part;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_part(String xxmes_porc_part) {
/* 124 */     this.xxmes_porc_part = xxmes_porc_part;
/*     */   }
/*     */   
/*     */   public Date getXxmes_porc_date() {
/* 128 */     return this.xxmes_porc_date;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_date(Date xxmes_porc_date) {
/* 132 */     this.xxmes_porc_date = xxmes_porc_date;
/*     */   }
/*     */   
/*     */   public String getXxmes_porc_boxcode() {
/* 136 */     return this.xxmes_porc_boxcode;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_boxcode(String xxmes_porc_boxcode) {
/* 140 */     this.xxmes_porc_boxcode = xxmes_porc_boxcode;
/*     */   }
/*     */   
/*     */   public BigDecimal getXxmes_porc_qty_real() {
/* 144 */     return this.xxmes_porc_qty_real;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_qty_real(BigDecimal xxmes_porc_qty_real) {
/* 148 */     this.xxmes_porc_qty_real = xxmes_porc_qty_real;
/*     */   }
/*     */   
/*     */   public String getXxmes_porc_loc() {
/* 152 */     return this.xxmes_porc_loc;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_loc(String xxmes_porc_loc) {
/* 156 */     this.xxmes_porc_loc = xxmes_porc_loc;
/*     */   }
/*     */   
/*     */   public String getXxmes_porc_poum() {
/* 160 */     return this.xxmes_porc_poum;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_poum(String xxmes_porc_poum) {
/* 164 */     this.xxmes_porc_poum = xxmes_porc_poum;
/*     */   }
/*     */   
/*     */   public String getXxmes_porc_qc() {
/* 168 */     return this.xxmes_porc_qc;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_qc(String xxmes_porc_qc) {
/* 172 */     this.xxmes_porc_qc = xxmes_porc_qc;
/*     */   }
/*     */   
/*     */   public String getXxmes_porc_potype() {
/* 176 */     return this.xxmes_porc_potype;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_potype(String xxmes_porc_potype) {
/* 180 */     this.xxmes_porc_potype = xxmes_porc_potype;
/*     */   }
/*     */   
/*     */   public String getXxmes_porc_qadread() {
/* 184 */     return this.xxmes_porc_qadread;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_qadread(String xxmes_porc_qadread) {
/* 188 */     this.xxmes_porc_qadread = xxmes_porc_qadread;
/*     */   }
/*     */   
/*     */   public String getXxmes_porc_mesread() {
/* 192 */     return this.xxmes_porc_mesread;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_mesread(String xxmes_porc_mesread) {
/* 196 */     this.xxmes_porc_mesread = xxmes_porc_mesread;
/*     */   }
/*     */   
/*     */   public String getXxmes_porc_portalread() {
/* 200 */     return this.xxmes_porc_portalread;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_portalread(String xxmes_porc_portalread) {
/* 204 */     this.xxmes_porc_portalread = xxmes_porc_portalread;
/*     */   }
/*     */   
/*     */   public String getXxmes_porc_ediread() {
/* 208 */     return this.xxmes_porc_ediread;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_ediread(String xxmes_porc_ediread) {
/* 212 */     this.xxmes_porc_ediread = xxmes_porc_ediread;
/*     */   }
/*     */   
/*     */   public String getXxmes_porc_rmks() {
/* 216 */     return this.xxmes_porc_rmks;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_rmks(String xxmes_porc_rmks) {
/* 220 */     this.xxmes_porc_rmks = xxmes_porc_rmks;
/*     */   }
/*     */   
/*     */   public Date getXxmes_porc_createdt() {
/* 224 */     return this.xxmes_porc_createdt;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_createdt(Date xxmes_porc_createdt) {
/* 228 */     this.xxmes_porc_createdt = xxmes_porc_createdt;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_porc_createdt1() {
/* 232 */     return this.xxmes_porc_createdt1;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_createdt1(Integer xxmes_porc_createdt1) {
/* 236 */     this.xxmes_porc_createdt1 = xxmes_porc_createdt1;
/*     */   }
/*     */   
/*     */   public String getXxmes_porc_createur() {
/* 240 */     return this.xxmes_porc_createur;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_createur(String xxmes_porc_createur) {
/* 244 */     this.xxmes_porc_createur = xxmes_porc_createur;
/*     */   }
/*     */   
/*     */   public Date getXxmes_porc_updatedt() {
/* 248 */     return this.xxmes_porc_updatedt;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_updatedt(Date xxmes_porc_updatedt) {
/* 252 */     this.xxmes_porc_updatedt = xxmes_porc_updatedt;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_porc_updatedt1() {
/* 256 */     return this.xxmes_porc_updatedt1;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_updatedt1(Integer xxmes_porc_updatedt1) {
/* 260 */     this.xxmes_porc_updatedt1 = xxmes_porc_updatedt1;
/*     */   }
/*     */   
/*     */   public String getXxmes_porc_updateur() {
/* 264 */     return this.xxmes_porc_updateur;
/*     */   }
/*     */   
/*     */   public void setXxmes_porc_updateur(String xxmes_porc_updateur) {
/* 268 */     this.xxmes_porc_updateur = xxmes_porc_updateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractPurchaseReceipt() {}
/*     */ 
/*     */   
/*     */   public AbstractPurchaseReceipt(Integer xxmes_porc_id) {
/* 276 */     setXxmes_porc_id(xxmes_porc_id);
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
/* 287 */     if (rhs == null)
/* 288 */       return false; 
/* 289 */     if (this == rhs)
/* 290 */       return true; 
/* 291 */     if (!(rhs instanceof PurchaseReceipt))
/* 292 */       return false; 
/* 293 */     PurchaseReceipt that = (PurchaseReceipt)rhs;
/* 294 */     if (getXxmes_porc_id() != null)
/* 295 */       return getXxmes_porc_id().equals(that.getXxmes_porc_id()); 
/* 296 */     return (that.getXxmes_porc_id() == null);
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
/* 307 */     if (this.hashValue == 0) {
/* 308 */       int result = 17;
/* 309 */       int poIdValue = (getXxmes_porc_id() == null) ? 0 : 
/* 310 */         getXxmes_porc_id().hashCode();
/* 311 */       result = result * 37 + poIdValue;
/* 312 */       this.hashValue = result;
/*     */     } 
/* 314 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractPurchaseReceipt.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
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
/*     */ public abstract class AbstractInspectInstroage
/*     */   implements Serializable
/*     */ {
/*  14 */   private int hashValue = 0;
/*     */   
/*     */   private Integer xxmes_inrc_id;
/*     */   
/*     */   private Integer xxmes_inrc_date1;
/*     */   private Integer xxmes_inrc_det_id;
/*     */   private String xxmes_inrc_site;
/*     */   private String xxmes_inrc_seq;
/*     */   private String xxmes_inrc_txnbr;
/*     */   private String xxmes_inrc_ponbr;
/*     */   private Integer xxmes_inrc_poline;
/*     */   private String xxmes_inrc_part;
/*     */   private Date xxmes_inrc_date;
/*     */   private String xxmes_inrc_boxcode;
/*     */   private BigDecimal xxmes_inrc_qty_real;
/*     */   private String xxmes_inrc_loc;
/*     */   private String xxmes_inrc_loc_from;
/*     */   private String xxmes_inrc_poum;
/*     */   private String xxmes_inrc_potype;
/*     */   private String xxmes_inrc_qadread;
/*     */   private String xxmes_inrc_mesread;
/*     */   private String xxmes_inrc_portalrea;
/*     */   private String xxmes_inrc_ediread;
/*     */   private String xxmes_inrc_rmks;
/*     */   private Date xxmes_inrc_createdt;
/*     */   private Integer xxmes_inrc_createdt1;
/*     */   private String xxmes_inrc_createur;
/*     */   private Date xxmes_inrc_updatedt;
/*     */   private Integer xxmes_inrc_updatedt1;
/*     */   private String xxmes_inrc_updateur;
/*     */   private String xxmes_inrc_domain;
/*     */   
/*     */   public Integer getXxmes_inrc_id() {
/*  47 */     return this.xxmes_inrc_id;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_id(Integer xxmes_inrc_id) {
/*  51 */     this.xxmes_inrc_id = xxmes_inrc_id;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_inrc_date1() {
/*  55 */     return this.xxmes_inrc_date1;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_date1(Integer xxmes_inrc_date1) {
/*  59 */     this.xxmes_inrc_date1 = xxmes_inrc_date1;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_inrc_det_id() {
/*  63 */     return this.xxmes_inrc_det_id;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_det_id(Integer xxmes_inrc_det_id) {
/*  67 */     this.xxmes_inrc_det_id = xxmes_inrc_det_id;
/*     */   }
/*     */   
/*     */   public String getXxmes_inrc_site() {
/*  71 */     return this.xxmes_inrc_site;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_site(String xxmes_inrc_site) {
/*  75 */     this.xxmes_inrc_site = xxmes_inrc_site;
/*     */   }
/*     */   
/*     */   public String getXxmes_inrc_seq() {
/*  79 */     return this.xxmes_inrc_seq;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_seq(String xxmes_inrc_seq) {
/*  83 */     this.xxmes_inrc_seq = xxmes_inrc_seq;
/*     */   }
/*     */   
/*     */   public String getXxmes_inrc_txnbr() {
/*  87 */     return this.xxmes_inrc_txnbr;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_txnbr(String xxmes_inrc_txnbr) {
/*  91 */     this.xxmes_inrc_txnbr = xxmes_inrc_txnbr;
/*     */   }
/*     */   
/*     */   public String getXxmes_inrc_ponbr() {
/*  95 */     return this.xxmes_inrc_ponbr;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_ponbr(String xxmes_inrc_ponbr) {
/*  99 */     this.xxmes_inrc_ponbr = xxmes_inrc_ponbr;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_inrc_poline() {
/* 103 */     return this.xxmes_inrc_poline;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_poline(Integer xxmes_inrc_poline) {
/* 107 */     this.xxmes_inrc_poline = xxmes_inrc_poline;
/*     */   }
/*     */   
/*     */   public String getXxmes_inrc_part() {
/* 111 */     return this.xxmes_inrc_part;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_part(String xxmes_inrc_part) {
/* 115 */     this.xxmes_inrc_part = xxmes_inrc_part;
/*     */   }
/*     */   
/*     */   public Date getXxmes_inrc_date() {
/* 119 */     return this.xxmes_inrc_date;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_date(Date xxmes_inrc_date) {
/* 123 */     this.xxmes_inrc_date = xxmes_inrc_date;
/*     */   }
/*     */   
/*     */   public String getXxmes_inrc_boxcode() {
/* 127 */     return this.xxmes_inrc_boxcode;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_boxcode(String xxmes_inrc_boxcode) {
/* 131 */     this.xxmes_inrc_boxcode = xxmes_inrc_boxcode;
/*     */   }
/*     */   
/*     */   public BigDecimal getXxmes_inrc_qty_real() {
/* 135 */     return this.xxmes_inrc_qty_real;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_qty_real(BigDecimal xxmes_inrc_qty_real) {
/* 139 */     this.xxmes_inrc_qty_real = xxmes_inrc_qty_real;
/*     */   }
/*     */   
/*     */   public String getXxmes_inrc_loc() {
/* 143 */     return this.xxmes_inrc_loc;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_loc(String xxmes_inrc_loc) {
/* 147 */     this.xxmes_inrc_loc = xxmes_inrc_loc;
/*     */   }
/*     */   
/*     */   public String getXxmes_inrc_loc_from() {
/* 151 */     return this.xxmes_inrc_loc_from;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_loc_from(String xxmes_inrc_loc_from) {
/* 155 */     this.xxmes_inrc_loc_from = xxmes_inrc_loc_from;
/*     */   }
/*     */   
/*     */   public String getXxmes_inrc_poum() {
/* 159 */     return this.xxmes_inrc_poum;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_poum(String xxmes_inrc_poum) {
/* 163 */     this.xxmes_inrc_poum = xxmes_inrc_poum;
/*     */   }
/*     */   
/*     */   public String getXxmes_inrc_potype() {
/* 167 */     return this.xxmes_inrc_potype;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_potype(String xxmes_inrc_potype) {
/* 171 */     this.xxmes_inrc_potype = xxmes_inrc_potype;
/*     */   }
/*     */   
/*     */   public String getXxmes_inrc_qadread() {
/* 175 */     return this.xxmes_inrc_qadread;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_qadread(String xxmes_inrc_qadread) {
/* 179 */     this.xxmes_inrc_qadread = xxmes_inrc_qadread;
/*     */   }
/*     */   
/*     */   public String getXxmes_inrc_mesread() {
/* 183 */     return this.xxmes_inrc_mesread;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_mesread(String xxmes_inrc_mesread) {
/* 187 */     this.xxmes_inrc_mesread = xxmes_inrc_mesread;
/*     */   }
/*     */   
/*     */   public String getXxmes_inrc_portalrea() {
/* 191 */     return this.xxmes_inrc_portalrea;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_portalrea(String xxmes_inrc_portalrea) {
/* 195 */     this.xxmes_inrc_portalrea = xxmes_inrc_portalrea;
/*     */   }
/*     */   
/*     */   public String getXxmes_inrc_ediread() {
/* 199 */     return this.xxmes_inrc_ediread;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_ediread(String xxmes_inrc_ediread) {
/* 203 */     this.xxmes_inrc_ediread = xxmes_inrc_ediread;
/*     */   }
/*     */   
/*     */   public String getXxmes_inrc_rmks() {
/* 207 */     return this.xxmes_inrc_rmks;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_rmks(String xxmes_inrc_rmks) {
/* 211 */     this.xxmes_inrc_rmks = xxmes_inrc_rmks;
/*     */   }
/*     */   
/*     */   public Date getXxmes_inrc_createdt() {
/* 215 */     return this.xxmes_inrc_createdt;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_createdt(Date xxmes_inrc_createdt) {
/* 219 */     this.xxmes_inrc_createdt = xxmes_inrc_createdt;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_inrc_createdt1() {
/* 223 */     return this.xxmes_inrc_createdt1;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_createdt1(Integer xxmes_inrc_createdt1) {
/* 227 */     this.xxmes_inrc_createdt1 = xxmes_inrc_createdt1;
/*     */   }
/*     */   
/*     */   public String getXxmes_inrc_createur() {
/* 231 */     return this.xxmes_inrc_createur;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_createur(String xxmes_inrc_createur) {
/* 235 */     this.xxmes_inrc_createur = xxmes_inrc_createur;
/*     */   }
/*     */   
/*     */   public Date getXxmes_inrc_updatedt() {
/* 239 */     return this.xxmes_inrc_updatedt;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_updatedt(Date xxmes_inrc_updatedt) {
/* 243 */     this.xxmes_inrc_updatedt = xxmes_inrc_updatedt;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_inrc_updatedt1() {
/* 247 */     return this.xxmes_inrc_updatedt1;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_updatedt1(Integer xxmes_inrc_updatedt1) {
/* 251 */     this.xxmes_inrc_updatedt1 = xxmes_inrc_updatedt1;
/*     */   }
/*     */   
/*     */   public String getXxmes_inrc_updateur() {
/* 255 */     return this.xxmes_inrc_updateur;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_updateur(String xxmes_inrc_updateur) {
/* 259 */     this.xxmes_inrc_updateur = xxmes_inrc_updateur;
/*     */   }
/*     */   
/*     */   public String getXxmes_inrc_domain() {
/* 263 */     return this.xxmes_inrc_domain;
/*     */   }
/*     */   
/*     */   public void setXxmes_inrc_domain(String xxmes_inrc_domain) {
/* 267 */     this.xxmes_inrc_domain = xxmes_inrc_domain;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractInspectInstroage() {}
/*     */ 
/*     */   
/*     */   public AbstractInspectInstroage(Integer id) {
/* 275 */     setXxmes_inrc_id(id);
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
/* 286 */     if (rhs == null)
/* 287 */       return false; 
/* 288 */     if (this == rhs)
/* 289 */       return true; 
/* 290 */     if (!(rhs instanceof InspectInstroage))
/* 291 */       return false; 
/* 292 */     InspectInstroage that = (InspectInstroage)rhs;
/* 293 */     if (getXxmes_inrc_id() != null)
/* 294 */       return getXxmes_inrc_id().equals(that.getXxmes_inrc_id()); 
/* 295 */     return (that.getXxmes_inrc_id() == null);
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
/* 306 */     if (this.hashValue == 0) {
/* 307 */       int result = 17;
/* 308 */       int poIdValue = (getXxmes_inrc_id() == null) ? 0 : 
/* 309 */         getXxmes_inrc_id().hashCode();
/* 310 */       result = result * 37 + poIdValue;
/* 311 */       this.hashValue = result;
/*     */     } 
/* 313 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractInspectInstroage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
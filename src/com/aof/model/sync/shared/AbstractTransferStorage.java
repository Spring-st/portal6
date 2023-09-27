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
/*     */ public abstract class AbstractTransferStorage
/*     */   implements Serializable
/*     */ {
/*  14 */   private int hashValue = 0;
/*     */   
/*     */   private Integer xxmes_ic_box_id;
/*     */   
/*     */   private Integer xxmes_ic_det_id;
/*     */   
/*     */   private Integer xxmes_ic_date1;
/*     */   private String xxmes_ic_seq;
/*     */   private String xxmes_ic_box_code;
/*     */   private String xxmes_ic_part;
/*     */   private BigDecimal xxmes_ic_qty;
/*     */   private String xxmes_ic_loc_from;
/*     */   private String xxmes_ic_loc_to;
/*     */   private String xxmes_ic_nbr;
/*     */   private Date xxmes_ic_date;
/*     */   private String xxmes_ic_site;
/*     */   private String xxmes_ic_qadread;
/*     */   private String xxmes_ic_mesread;
/*     */   private String xxmes_ic_portalread;
/*     */   private String xxmes_ic_ediread;
/*     */   private String xxmes_ic_rmks;
/*     */   private Date xxmes_ic_createdt;
/*     */   private Integer xxmes_ic_createdt1;
/*     */   private String xxmes_ic_createur;
/*     */   private Date xxmes_ic_updatedt;
/*     */   private Integer xxmes_ic_updatedt1;
/*     */   private String xxmes_ic_updateur;
/*     */   private String xxmes_ic_domain;
/*     */   private String xxmes_ic_box_code_to;
/*     */   
/*     */   public Integer getXxmes_ic_box_id() {
/*  45 */     return this.xxmes_ic_box_id;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_box_id(Integer xxmes_ic_box_id) {
/*  49 */     this.xxmes_ic_box_id = xxmes_ic_box_id;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_ic_det_id() {
/*  53 */     return this.xxmes_ic_det_id;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_det_id(Integer xxmes_ic_det_id) {
/*  57 */     this.xxmes_ic_det_id = xxmes_ic_det_id;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_ic_date1() {
/*  61 */     return this.xxmes_ic_date1;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_date1(Integer xxmes_ic_date1) {
/*  65 */     this.xxmes_ic_date1 = xxmes_ic_date1;
/*     */   }
/*     */   
/*     */   public String getXxmes_ic_seq() {
/*  69 */     return this.xxmes_ic_seq;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_seq(String xxmes_ic_seq) {
/*  73 */     this.xxmes_ic_seq = xxmes_ic_seq;
/*     */   }
/*     */   
/*     */   public String getXxmes_ic_box_code() {
/*  77 */     return this.xxmes_ic_box_code;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_box_code(String xxmes_ic_box_code) {
/*  81 */     this.xxmes_ic_box_code = xxmes_ic_box_code;
/*     */   }
/*     */   
/*     */   public String getXxmes_ic_part() {
/*  85 */     return this.xxmes_ic_part;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_part(String xxmes_ic_part) {
/*  89 */     this.xxmes_ic_part = xxmes_ic_part;
/*     */   }
/*     */   
/*     */   public BigDecimal getXxmes_ic_qty() {
/*  93 */     return this.xxmes_ic_qty;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_qty(BigDecimal xxmes_ic_qty) {
/*  97 */     this.xxmes_ic_qty = xxmes_ic_qty;
/*     */   }
/*     */   
/*     */   public String getXxmes_ic_loc_from() {
/* 101 */     return this.xxmes_ic_loc_from;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_loc_from(String xxmes_ic_loc_from) {
/* 105 */     this.xxmes_ic_loc_from = xxmes_ic_loc_from;
/*     */   }
/*     */   
/*     */   public String getXxmes_ic_loc_to() {
/* 109 */     return this.xxmes_ic_loc_to;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_loc_to(String xxmes_ic_loc_to) {
/* 113 */     this.xxmes_ic_loc_to = xxmes_ic_loc_to;
/*     */   }
/*     */   
/*     */   public String getXxmes_ic_nbr() {
/* 117 */     return this.xxmes_ic_nbr;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_nbr(String xxmes_ic_nbr) {
/* 121 */     this.xxmes_ic_nbr = xxmes_ic_nbr;
/*     */   }
/*     */   
/*     */   public Date getXxmes_ic_date() {
/* 125 */     return this.xxmes_ic_date;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_date(Date xxmes_ic_date) {
/* 129 */     this.xxmes_ic_date = xxmes_ic_date;
/*     */   }
/*     */   
/*     */   public String getXxmes_ic_site() {
/* 133 */     return this.xxmes_ic_site;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_site(String xxmes_ic_site) {
/* 137 */     this.xxmes_ic_site = xxmes_ic_site;
/*     */   }
/*     */   
/*     */   public String getXxmes_ic_qadread() {
/* 141 */     return this.xxmes_ic_qadread;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_qadread(String xxmes_ic_qadread) {
/* 145 */     this.xxmes_ic_qadread = xxmes_ic_qadread;
/*     */   }
/*     */   
/*     */   public String getXxmes_ic_mesread() {
/* 149 */     return this.xxmes_ic_mesread;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_mesread(String xxmes_ic_mesread) {
/* 153 */     this.xxmes_ic_mesread = xxmes_ic_mesread;
/*     */   }
/*     */   
/*     */   public String getXxmes_ic_portalread() {
/* 157 */     return this.xxmes_ic_portalread;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_portalread(String xxmes_ic_portalread) {
/* 161 */     this.xxmes_ic_portalread = xxmes_ic_portalread;
/*     */   }
/*     */   
/*     */   public String getXxmes_ic_ediread() {
/* 165 */     return this.xxmes_ic_ediread;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_ediread(String xxmes_ic_ediread) {
/* 169 */     this.xxmes_ic_ediread = xxmes_ic_ediread;
/*     */   }
/*     */   
/*     */   public String getXxmes_ic_rmks() {
/* 173 */     return this.xxmes_ic_rmks;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_rmks(String xxmes_ic_rmks) {
/* 177 */     this.xxmes_ic_rmks = xxmes_ic_rmks;
/*     */   }
/*     */   
/*     */   public Date getXxmes_ic_createdt() {
/* 181 */     return this.xxmes_ic_createdt;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_createdt(Date xxmes_ic_createdt) {
/* 185 */     this.xxmes_ic_createdt = xxmes_ic_createdt;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_ic_createdt1() {
/* 189 */     return this.xxmes_ic_createdt1;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_createdt1(Integer xxmes_ic_createdt1) {
/* 193 */     this.xxmes_ic_createdt1 = xxmes_ic_createdt1;
/*     */   }
/*     */   
/*     */   public String getXxmes_ic_createur() {
/* 197 */     return this.xxmes_ic_createur;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_createur(String xxmes_ic_createur) {
/* 201 */     this.xxmes_ic_createur = xxmes_ic_createur;
/*     */   }
/*     */   
/*     */   public Date getXxmes_ic_updatedt() {
/* 205 */     return this.xxmes_ic_updatedt;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_updatedt(Date xxmes_ic_updatedt) {
/* 209 */     this.xxmes_ic_updatedt = xxmes_ic_updatedt;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_ic_updatedt1() {
/* 213 */     return this.xxmes_ic_updatedt1;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_updatedt1(Integer xxmes_ic_updatedt1) {
/* 217 */     this.xxmes_ic_updatedt1 = xxmes_ic_updatedt1;
/*     */   }
/*     */   
/*     */   public String getXxmes_ic_updateur() {
/* 221 */     return this.xxmes_ic_updateur;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_updateur(String xxmes_ic_updateur) {
/* 225 */     this.xxmes_ic_updateur = xxmes_ic_updateur;
/*     */   }
/*     */   
/*     */   public String getXxmes_ic_domain() {
/* 229 */     return this.xxmes_ic_domain;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_domain(String xxmes_ic_domain) {
/* 233 */     this.xxmes_ic_domain = xxmes_ic_domain;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractTransferStorage() {}
/*     */   
/*     */   public AbstractTransferStorage(Integer id) {
/* 240 */     setXxmes_ic_box_id(id);
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
/* 251 */     if (rhs == null)
/* 252 */       return false; 
/* 253 */     if (this == rhs)
/* 254 */       return true; 
/* 255 */     if (!(rhs instanceof TransferStorage))
/* 256 */       return false; 
/* 257 */     TransferStorage that = (TransferStorage)rhs;
/* 258 */     if (getXxmes_ic_box_id() != null)
/* 259 */       return getXxmes_ic_box_id().equals(that.getXxmes_ic_box_id()); 
/* 260 */     return (that.getXxmes_ic_box_id() == null);
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
/* 271 */     if (this.hashValue == 0) {
/* 272 */       int result = 17;
/* 273 */       int poIdValue = (getXxmes_ic_box_id() == null) ? 0 : 
/* 274 */         getXxmes_ic_box_id().hashCode();
/* 275 */       result = result * 37 + poIdValue;
/* 276 */       this.hashValue = result;
/*     */     } 
/* 278 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public String getXxmes_ic_box_code_to() {
/* 282 */     return this.xxmes_ic_box_code_to;
/*     */   }
/*     */   
/*     */   public void setXxmes_ic_box_code_to(String xxmes_ic_box_code_to) {
/* 286 */     this.xxmes_ic_box_code_to = xxmes_ic_box_code_to;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractTransferStorage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
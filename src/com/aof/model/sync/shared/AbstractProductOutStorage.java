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
/*     */ public abstract class AbstractProductOutStorage
/*     */   implements Serializable
/*     */ {
/*  14 */   private int hashValue = 0;
/*     */   
/*     */   private Integer xxmes_ship_id;
/*     */   
/*     */   private Integer xxmes_ship_date1;
/*     */   
/*     */   private Integer xxmes_ship_det_id;
/*     */   
/*     */   private Integer xxmes_ship_time1;
/*     */   private String xxmes_ship_seq;
/*     */   private String xxmes_ship_cust;
/*     */   private String xxmes_ship_shipto;
/*     */   private String xxmes_ship_part;
/*     */   private BigDecimal xxmes_ship_qty;
/*     */   private String xxmes_ship_loc;
/*     */   private Date xxmes_ship_date;
/*     */   private Date xxmes_ship_time;
/*     */   private String xxmes_ship_nbr;
/*     */   private String xxmes_ship_boxcode;
/*     */   private Integer xxmes_ship_line;
/*     */   private String xxmes_ship_type;
/*     */   private String xxmes_ship_site;
/*     */   private String xxmes_ship_qadread;
/*     */   private String xxmes_ship_mesread;
/*     */   private String xxmes_ship_portalread;
/*     */   private String xxmes_ship_ediread;
/*     */   private String xxmes_ship_rmks;
/*     */   private Date xxmes_ship_createdt;
/*     */   private Integer xxmes_ship_createdt1;
/*     */   private String xxmes_ship_createur;
/*     */   private Date xxmes_ship_updatedt;
/*     */   private Integer xxmes_ship_updatedt1;
/*     */   private String xxmes_ship_updateur;
/*     */   private String xxmes_ship_domain;
/*     */   
/*     */   public String getXxmes_ship_domain() {
/*  50 */     return this.xxmes_ship_domain;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_domain(String xxmes_ship_domain) {
/*  54 */     this.xxmes_ship_domain = xxmes_ship_domain;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_ship_id() {
/*  58 */     return this.xxmes_ship_id;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_id(Integer xxmes_ship_id) {
/*  62 */     this.xxmes_ship_id = xxmes_ship_id;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_ship_date1() {
/*  66 */     return this.xxmes_ship_date1;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_date1(Integer xxmes_ship_date1) {
/*  70 */     this.xxmes_ship_date1 = xxmes_ship_date1;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_ship_det_id() {
/*  74 */     return this.xxmes_ship_det_id;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_det_id(Integer xxmes_ship_det_id) {
/*  78 */     this.xxmes_ship_det_id = xxmes_ship_det_id;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_ship_time1() {
/*  82 */     return this.xxmes_ship_time1;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_time1(Integer xxmes_ship_time1) {
/*  86 */     this.xxmes_ship_time1 = xxmes_ship_time1;
/*     */   }
/*     */   
/*     */   public String getXxmes_ship_seq() {
/*  90 */     return this.xxmes_ship_seq;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_seq(String xxmes_ship_seq) {
/*  94 */     this.xxmes_ship_seq = xxmes_ship_seq;
/*     */   }
/*     */   
/*     */   public String getXxmes_ship_cust() {
/*  98 */     return this.xxmes_ship_cust;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_cust(String xxmes_ship_cust) {
/* 102 */     this.xxmes_ship_cust = xxmes_ship_cust;
/*     */   }
/*     */   
/*     */   public String getXxmes_ship_shipto() {
/* 106 */     return this.xxmes_ship_shipto;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_shipto(String xxmes_ship_shipto) {
/* 110 */     this.xxmes_ship_shipto = xxmes_ship_shipto;
/*     */   }
/*     */   
/*     */   public String getXxmes_ship_part() {
/* 114 */     return this.xxmes_ship_part;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_part(String xxmes_ship_part) {
/* 118 */     this.xxmes_ship_part = xxmes_ship_part;
/*     */   }
/*     */   
/*     */   public BigDecimal getXxmes_ship_qty() {
/* 122 */     return this.xxmes_ship_qty;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_qty(BigDecimal xxmes_ship_qty) {
/* 126 */     this.xxmes_ship_qty = xxmes_ship_qty;
/*     */   }
/*     */   
/*     */   public String getXxmes_ship_loc() {
/* 130 */     return this.xxmes_ship_loc;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_loc(String xxmes_ship_loc) {
/* 134 */     this.xxmes_ship_loc = xxmes_ship_loc;
/*     */   }
/*     */   
/*     */   public Date getXxmes_ship_date() {
/* 138 */     return this.xxmes_ship_date;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_date(Date xxmes_ship_date) {
/* 142 */     this.xxmes_ship_date = xxmes_ship_date;
/*     */   }
/*     */   
/*     */   public Date getXxmes_ship_time() {
/* 146 */     return this.xxmes_ship_time;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_time(Date xxmes_ship_time) {
/* 150 */     this.xxmes_ship_time = xxmes_ship_time;
/*     */   }
/*     */   
/*     */   public String getXxmes_ship_nbr() {
/* 154 */     return this.xxmes_ship_nbr;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_nbr(String xxmes_ship_nbr) {
/* 158 */     this.xxmes_ship_nbr = xxmes_ship_nbr;
/*     */   }
/*     */   
/*     */   public String getXxmes_ship_boxcode() {
/* 162 */     return this.xxmes_ship_boxcode;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_boxcode(String xxmes_ship_boxcode) {
/* 166 */     this.xxmes_ship_boxcode = xxmes_ship_boxcode;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_ship_line() {
/* 170 */     return this.xxmes_ship_line;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_line(Integer xxmes_ship_line) {
/* 174 */     this.xxmes_ship_line = xxmes_ship_line;
/*     */   }
/*     */   
/*     */   public String getXxmes_ship_type() {
/* 178 */     return this.xxmes_ship_type;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_type(String xxmes_ship_type) {
/* 182 */     this.xxmes_ship_type = xxmes_ship_type;
/*     */   }
/*     */   
/*     */   public String getXxmes_ship_site() {
/* 186 */     return this.xxmes_ship_site;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_site(String xxmes_ship_site) {
/* 190 */     this.xxmes_ship_site = xxmes_ship_site;
/*     */   }
/*     */   
/*     */   public String getXxmes_ship_qadread() {
/* 194 */     return this.xxmes_ship_qadread;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_qadread(String xxmes_ship_qadread) {
/* 198 */     this.xxmes_ship_qadread = xxmes_ship_qadread;
/*     */   }
/*     */   
/*     */   public String getXxmes_ship_mesread() {
/* 202 */     return this.xxmes_ship_mesread;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_mesread(String xxmes_ship_mesread) {
/* 206 */     this.xxmes_ship_mesread = xxmes_ship_mesread;
/*     */   }
/*     */   
/*     */   public String getXxmes_ship_portalread() {
/* 210 */     return this.xxmes_ship_portalread;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_portalread(String xxmes_ship_portalread) {
/* 214 */     this.xxmes_ship_portalread = xxmes_ship_portalread;
/*     */   }
/*     */   
/*     */   public String getXxmes_ship_ediread() {
/* 218 */     return this.xxmes_ship_ediread;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_ediread(String xxmes_ship_ediread) {
/* 222 */     this.xxmes_ship_ediread = xxmes_ship_ediread;
/*     */   }
/*     */   
/*     */   public String getXxmes_ship_rmks() {
/* 226 */     return this.xxmes_ship_rmks;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_rmks(String xxmes_ship_rmks) {
/* 230 */     this.xxmes_ship_rmks = xxmes_ship_rmks;
/*     */   }
/*     */   
/*     */   public Date getXxmes_ship_createdt() {
/* 234 */     return this.xxmes_ship_createdt;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_createdt(Date xxmes_ship_createdt) {
/* 238 */     this.xxmes_ship_createdt = xxmes_ship_createdt;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_ship_createdt1() {
/* 242 */     return this.xxmes_ship_createdt1;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_createdt1(Integer xxmes_ship_createdt1) {
/* 246 */     this.xxmes_ship_createdt1 = xxmes_ship_createdt1;
/*     */   }
/*     */   
/*     */   public String getXxmes_ship_createur() {
/* 250 */     return this.xxmes_ship_createur;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_createur(String xxmes_ship_createur) {
/* 254 */     this.xxmes_ship_createur = xxmes_ship_createur;
/*     */   }
/*     */   
/*     */   public Date getXxmes_ship_updatedt() {
/* 258 */     return this.xxmes_ship_updatedt;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_updatedt(Date xxmes_ship_updatedt) {
/* 262 */     this.xxmes_ship_updatedt = xxmes_ship_updatedt;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_ship_updatedt1() {
/* 266 */     return this.xxmes_ship_updatedt1;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_updatedt1(Integer xxmes_ship_updatedt1) {
/* 270 */     this.xxmes_ship_updatedt1 = xxmes_ship_updatedt1;
/*     */   }
/*     */   
/*     */   public String getXxmes_ship_updateur() {
/* 274 */     return this.xxmes_ship_updateur;
/*     */   }
/*     */   
/*     */   public void setXxmes_ship_updateur(String xxmes_ship_updateur) {
/* 278 */     this.xxmes_ship_updateur = xxmes_ship_updateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractProductOutStorage() {}
/*     */   
/*     */   public AbstractProductOutStorage(Integer id) {
/* 285 */     setXxmes_ship_id(id);
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
/* 296 */     if (rhs == null)
/* 297 */       return false; 
/* 298 */     if (this == rhs)
/* 299 */       return true; 
/* 300 */     if (!(rhs instanceof ProductOutStorage))
/* 301 */       return false; 
/* 302 */     ProductOutStorage that = (ProductOutStorage)rhs;
/* 303 */     if (getXxmes_ship_id() != null)
/* 304 */       return getXxmes_ship_id().equals(that.getXxmes_ship_id()); 
/* 305 */     return (that.getXxmes_ship_id() == null);
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
/* 316 */     if (this.hashValue == 0) {
/* 317 */       int result = 17;
/* 318 */       int poIdValue = (getXxmes_ship_id() == null) ? 0 : 
/* 319 */         getXxmes_ship_id().hashCode();
/* 320 */       result = result * 37 + poIdValue;
/* 321 */       this.hashValue = result;
/*     */     } 
/* 323 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractProductOutStorage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
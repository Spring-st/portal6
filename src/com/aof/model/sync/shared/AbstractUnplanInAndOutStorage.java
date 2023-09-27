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
/*     */ public class AbstractUnplanInAndOutStorage
/*     */   implements Serializable
/*     */ {
/*  14 */   private int hashValue = 0;
/*     */   
/*     */   private Integer xxmes_upiss_id;
/*     */   
/*     */   private String xxmes_upiss_char1;
/*     */   
/*     */   private String xxmes_upiss_char2;
/*     */   
/*     */   private Integer xxmes_upiss_date1;
/*     */   private Integer xxmes_upiss_time1;
/*     */   private Integer xxmes_upiss_det_id;
/*     */   private String xxmes_upiss_reason;
/*     */   private String xxmes_upiss_workcenter;
/*     */   private String xxmes_upiss_seq;
/*     */   private String xxmes_upiss_part;
/*     */   private BigDecimal xxmes_upiss_qty;
/*     */   private String xxmes_upiss_loc;
/*     */   private String xxmes_upiss_dir;
/*     */   private Date xxmes_upiss_date;
/*     */   private Date xxmes_upiss_time;
/*     */   private String xxmes_upiss_site;
/*     */   private String xxmes_upiss_qadread;
/*     */   private String xxmes_upiss_mesread;
/*     */   private String xxmes_upiss_portalread;
/*     */   private String xxmes_upiss_ediread;
/*     */   private String xxmes_upiss_rmks;
/*     */   private Date xxmes_upiss_createdt;
/*     */   private String xxmes_upiss_createdt1;
/*     */   private String xxmes_upiss_createur;
/*     */   private Date xxmes_upiss_updatedt;
/*     */   private String xxmes_upiss_updatedt1;
/*     */   private String xxmes_upiss_updateur;
/*     */   private String xxmes_upiss_domain;
/*     */   private String xxmes_upiss_boxcode;
/*     */   
/*     */   public Integer getXxmes_upiss_id() {
/*  50 */     return this.xxmes_upiss_id;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_id(Integer xxmes_upiss_id) {
/*  54 */     this.xxmes_upiss_id = xxmes_upiss_id;
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_char1() {
/*  58 */     return this.xxmes_upiss_char1;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_char1(String xxmes_upiss_char1) {
/*  62 */     this.xxmes_upiss_char1 = xxmes_upiss_char1;
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_char2() {
/*  66 */     return this.xxmes_upiss_char2;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_char2(String xxmes_upiss_char2) {
/*  70 */     this.xxmes_upiss_char2 = xxmes_upiss_char2;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_upiss_date1() {
/*  74 */     return this.xxmes_upiss_date1;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_date1(Integer xxmes_upiss_date1) {
/*  78 */     this.xxmes_upiss_date1 = xxmes_upiss_date1;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_upiss_time1() {
/*  82 */     return this.xxmes_upiss_time1;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_time1(Integer xxmes_upiss_time1) {
/*  86 */     this.xxmes_upiss_time1 = xxmes_upiss_time1;
/*     */   }
/*     */   
/*     */   public Integer getXxmes_upiss_det_id() {
/*  90 */     return this.xxmes_upiss_det_id;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_det_id(Integer xxmes_upiss_det_id) {
/*  94 */     this.xxmes_upiss_det_id = xxmes_upiss_det_id;
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_reason() {
/*  98 */     return this.xxmes_upiss_reason;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_reason(String xxmes_upiss_reason) {
/* 102 */     this.xxmes_upiss_reason = xxmes_upiss_reason;
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_workcenter() {
/* 106 */     return this.xxmes_upiss_workcenter;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_workcenter(String xxmes_upiss_workcenter) {
/* 110 */     this.xxmes_upiss_workcenter = xxmes_upiss_workcenter;
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_seq() {
/* 114 */     return this.xxmes_upiss_seq;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_seq(String xxmes_upiss_seq) {
/* 118 */     this.xxmes_upiss_seq = xxmes_upiss_seq;
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_part() {
/* 122 */     return this.xxmes_upiss_part;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_part(String xxmes_upiss_part) {
/* 126 */     this.xxmes_upiss_part = xxmes_upiss_part;
/*     */   }
/*     */   
/*     */   public BigDecimal getXxmes_upiss_qty() {
/* 130 */     return this.xxmes_upiss_qty;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_qty(BigDecimal xxmes_upiss_qty) {
/* 134 */     this.xxmes_upiss_qty = xxmes_upiss_qty;
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_loc() {
/* 138 */     return this.xxmes_upiss_loc;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_loc(String xxmes_upiss_loc) {
/* 142 */     this.xxmes_upiss_loc = xxmes_upiss_loc;
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_dir() {
/* 146 */     return this.xxmes_upiss_dir;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_dir(String xxmes_upiss_dir) {
/* 150 */     this.xxmes_upiss_dir = xxmes_upiss_dir;
/*     */   }
/*     */   
/*     */   public Date getXxmes_upiss_date() {
/* 154 */     return this.xxmes_upiss_date;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_date(Date xxmes_upiss_date) {
/* 158 */     this.xxmes_upiss_date = xxmes_upiss_date;
/*     */   }
/*     */   
/*     */   public Date getXxmes_upiss_time() {
/* 162 */     return this.xxmes_upiss_time;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_time(Date xxmes_upiss_time) {
/* 166 */     this.xxmes_upiss_time = xxmes_upiss_time;
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_site() {
/* 170 */     return this.xxmes_upiss_site;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_site(String xxmes_upiss_site) {
/* 174 */     this.xxmes_upiss_site = xxmes_upiss_site;
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_qadread() {
/* 178 */     return this.xxmes_upiss_qadread;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_qadread(String xxmes_upiss_qadread) {
/* 182 */     this.xxmes_upiss_qadread = xxmes_upiss_qadread;
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_mesread() {
/* 186 */     return this.xxmes_upiss_mesread;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_mesread(String xxmes_upiss_mesread) {
/* 190 */     this.xxmes_upiss_mesread = xxmes_upiss_mesread;
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_portalread() {
/* 194 */     return this.xxmes_upiss_portalread;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_portalread(String xxmes_upiss_portalread) {
/* 198 */     this.xxmes_upiss_portalread = xxmes_upiss_portalread;
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_ediread() {
/* 202 */     return this.xxmes_upiss_ediread;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_ediread(String xxmes_upiss_ediread) {
/* 206 */     this.xxmes_upiss_ediread = xxmes_upiss_ediread;
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_rmks() {
/* 210 */     return this.xxmes_upiss_rmks;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_rmks(String xxmes_upiss_rmks) {
/* 214 */     this.xxmes_upiss_rmks = xxmes_upiss_rmks;
/*     */   }
/*     */   
/*     */   public Date getXxmes_upiss_createdt() {
/* 218 */     return this.xxmes_upiss_createdt;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_createdt(Date xxmes_upiss_createdt) {
/* 222 */     this.xxmes_upiss_createdt = xxmes_upiss_createdt;
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_createdt1() {
/* 226 */     return this.xxmes_upiss_createdt1;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_createdt1(String xxmes_upiss_createdt1) {
/* 230 */     this.xxmes_upiss_createdt1 = xxmes_upiss_createdt1;
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_createur() {
/* 234 */     return this.xxmes_upiss_createur;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_createur(String xxmes_upiss_createur) {
/* 238 */     this.xxmes_upiss_createur = xxmes_upiss_createur;
/*     */   }
/*     */   
/*     */   public Date getXxmes_upiss_updatedt() {
/* 242 */     return this.xxmes_upiss_updatedt;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_updatedt(Date xxmes_upiss_updatedt) {
/* 246 */     this.xxmes_upiss_updatedt = xxmes_upiss_updatedt;
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_updatedt1() {
/* 250 */     return this.xxmes_upiss_updatedt1;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_updatedt1(String xxmes_upiss_updatedt1) {
/* 254 */     this.xxmes_upiss_updatedt1 = xxmes_upiss_updatedt1;
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_updateur() {
/* 258 */     return this.xxmes_upiss_updateur;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_updateur(String xxmes_upiss_updateur) {
/* 262 */     this.xxmes_upiss_updateur = xxmes_upiss_updateur;
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_domain() {
/* 266 */     return this.xxmes_upiss_domain;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_domain(String xxmes_upiss_domain) {
/* 270 */     this.xxmes_upiss_domain = xxmes_upiss_domain;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractUnplanInAndOutStorage() {}
/*     */ 
/*     */   
/*     */   public AbstractUnplanInAndOutStorage(Integer id) {
/* 278 */     setXxmes_upiss_id(id);
/*     */   }
/*     */   
/*     */   public String getXxmes_upiss_boxcode() {
/* 282 */     return this.xxmes_upiss_boxcode;
/*     */   }
/*     */   
/*     */   public void setXxmes_upiss_boxcode(String xxmes_upiss_boxcode) {
/* 286 */     this.xxmes_upiss_boxcode = xxmes_upiss_boxcode;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractUnplanInAndOutStorage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
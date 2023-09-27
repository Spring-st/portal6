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
/*     */ public abstract class AbstractWorkOrderBackFlushInStroage
/*     */   implements Serializable
/*     */ {
/*  15 */   private int hashValue = 0;
/*     */   
/*     */   private Integer shmes_worc_det_number;
/*     */   
/*     */   private Integer shmes_worc_det_id;
/*     */   
/*     */   private String shmes_worc_seq;
/*     */   
/*     */   private String shmes_rworc_id;
/*     */   
/*     */   private String shmes_worc_item;
/*     */   private String shmes_worc_loc;
/*     */   private BigDecimal shmes_worc_qty;
/*     */   private Date shmes_worc_date;
/*     */   private String shmes_worc_domain;
/*     */   private String shmes_worc_site;
/*     */   private String shmes_rworc_note;
/*     */   private String shmes_worc_qadread;
/*     */   private String shmes_worc_mesread;
/*     */   private String shmes_worc_portalread;
/*     */   private String shmes_worc_ediread;
/*     */   private String shmes_worc_rmks;
/*     */   private Date shmes_worc_createdt;
/*     */   private Integer shmes_worc_createdt1;
/*     */   private String shmes_worc_createur;
/*     */   private Date shmes_worc_updatedt;
/*     */   private Integer shmes_worc_updatedt1;
/*     */   private String shmes_worc_updateur;
/*     */   
/*     */   public Integer getShmes_worc_det_id() {
/*  45 */     return this.shmes_worc_det_id;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_det_id(Integer shmes_worc_det_id) {
/*  49 */     this.shmes_worc_det_id = shmes_worc_det_id;
/*     */   }
/*     */   
/*     */   public Integer getShmes_worc_det_number() {
/*  53 */     return this.shmes_worc_det_number;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_det_number(Integer shmes_worc_det_number) {
/*  57 */     this.shmes_worc_det_number = shmes_worc_det_number;
/*     */   }
/*     */   
/*     */   public String getShmes_worc_seq() {
/*  61 */     return this.shmes_worc_seq;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_seq(String shmes_worc_seq) {
/*  65 */     this.shmes_worc_seq = shmes_worc_seq;
/*     */   }
/*     */   
/*     */   public String getShmes_rworc_id() {
/*  69 */     return this.shmes_rworc_id;
/*     */   }
/*     */   
/*     */   public void setShmes_rworc_id(String shmes_rworc_id) {
/*  73 */     this.shmes_rworc_id = shmes_rworc_id;
/*     */   }
/*     */   
/*     */   public String getShmes_worc_item() {
/*  77 */     return this.shmes_worc_item;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_item(String shmes_worc_item) {
/*  81 */     this.shmes_worc_item = shmes_worc_item;
/*     */   }
/*     */   
/*     */   public String getShmes_worc_loc() {
/*  85 */     return this.shmes_worc_loc;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_loc(String shmes_worc_loc) {
/*  89 */     this.shmes_worc_loc = shmes_worc_loc;
/*     */   }
/*     */   
/*     */   public BigDecimal getShmes_worc_qty() {
/*  93 */     return this.shmes_worc_qty;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_qty(BigDecimal shmes_worc_qty) {
/*  97 */     this.shmes_worc_qty = shmes_worc_qty;
/*     */   }
/*     */   
/*     */   public Date getShmes_worc_date() {
/* 101 */     return this.shmes_worc_date;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_date(Date shmes_worc_date) {
/* 105 */     this.shmes_worc_date = shmes_worc_date;
/*     */   }
/*     */   
/*     */   public String getShmes_worc_domain() {
/* 109 */     return this.shmes_worc_domain;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_domain(String shmes_worc_domain) {
/* 113 */     this.shmes_worc_domain = shmes_worc_domain;
/*     */   }
/*     */   
/*     */   public String getShmes_worc_site() {
/* 117 */     return this.shmes_worc_site;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_site(String shmes_worc_site) {
/* 121 */     this.shmes_worc_site = shmes_worc_site;
/*     */   }
/*     */   
/*     */   public String getShmes_rworc_note() {
/* 125 */     return this.shmes_rworc_note;
/*     */   }
/*     */   
/*     */   public void setShmes_rworc_note(String shmes_rworc_note) {
/* 129 */     this.shmes_rworc_note = shmes_rworc_note;
/*     */   }
/*     */   
/*     */   public String getShmes_worc_qadread() {
/* 133 */     return this.shmes_worc_qadread;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_qadread(String shmes_worc_qadread) {
/* 137 */     this.shmes_worc_qadread = shmes_worc_qadread;
/*     */   }
/*     */   
/*     */   public String getShmes_worc_mesread() {
/* 141 */     return this.shmes_worc_mesread;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_mesread(String shmes_worc_mesread) {
/* 145 */     this.shmes_worc_mesread = shmes_worc_mesread;
/*     */   }
/*     */   
/*     */   public String getShmes_worc_portalread() {
/* 149 */     return this.shmes_worc_portalread;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_portalread(String shmes_worc_portalread) {
/* 153 */     this.shmes_worc_portalread = shmes_worc_portalread;
/*     */   }
/*     */   
/*     */   public String getShmes_worc_ediread() {
/* 157 */     return this.shmes_worc_ediread;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_ediread(String shmes_worc_ediread) {
/* 161 */     this.shmes_worc_ediread = shmes_worc_ediread;
/*     */   }
/*     */   
/*     */   public String getShmes_worc_rmks() {
/* 165 */     return this.shmes_worc_rmks;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_rmks(String shmes_worc_rmks) {
/* 169 */     this.shmes_worc_rmks = shmes_worc_rmks;
/*     */   }
/*     */   
/*     */   public Date getShmes_worc_createdt() {
/* 173 */     return this.shmes_worc_createdt;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_createdt(Date shmes_worc_createdt) {
/* 177 */     this.shmes_worc_createdt = shmes_worc_createdt;
/*     */   }
/*     */   
/*     */   public Integer getShmes_worc_createdt1() {
/* 181 */     return this.shmes_worc_createdt1;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_createdt1(Integer shmes_worc_createdt1) {
/* 185 */     this.shmes_worc_createdt1 = shmes_worc_createdt1;
/*     */   }
/*     */   
/*     */   public String getShmes_worc_createur() {
/* 189 */     return this.shmes_worc_createur;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_createur(String shmes_worc_createur) {
/* 193 */     this.shmes_worc_createur = shmes_worc_createur;
/*     */   }
/*     */   
/*     */   public Date getShmes_worc_updatedt() {
/* 197 */     return this.shmes_worc_updatedt;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_updatedt(Date shmes_worc_updatedt) {
/* 201 */     this.shmes_worc_updatedt = shmes_worc_updatedt;
/*     */   }
/*     */   
/*     */   public Integer getShmes_worc_updatedt1() {
/* 205 */     return this.shmes_worc_updatedt1;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_updatedt1(Integer shmes_worc_updatedt1) {
/* 209 */     this.shmes_worc_updatedt1 = shmes_worc_updatedt1;
/*     */   }
/*     */   
/*     */   public String getShmes_worc_updateur() {
/* 213 */     return this.shmes_worc_updateur;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_updateur(String shmes_worc_updateur) {
/* 217 */     this.shmes_worc_updateur = shmes_worc_updateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractWorkOrderBackFlushInStroage() {}
/*     */   
/*     */   public AbstractWorkOrderBackFlushInStroage(Integer shmes_worc_det_id) {
/* 224 */     setShmes_worc_det_id(shmes_worc_det_id);
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
/* 235 */     if (rhs == null)
/* 236 */       return false; 
/* 237 */     if (this == rhs)
/* 238 */       return true; 
/* 239 */     if (!(rhs instanceof WorkOrderBackFlushInStroage))
/* 240 */       return false; 
/* 241 */     WorkOrderBackFlushInStroage that = (WorkOrderBackFlushInStroage)rhs;
/* 242 */     if (getShmes_worc_det_id() != null)
/* 243 */       return getShmes_worc_det_id().equals(
/* 244 */           that.getShmes_worc_det_id()); 
/* 245 */     return (that.getShmes_worc_det_id() == null);
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
/* 256 */     if (this.hashValue == 0) {
/* 257 */       int result = 17;
/* 258 */       int poIdValue = (getShmes_worc_det_id() == null) ? 0 : 
/* 259 */         getShmes_worc_det_id().hashCode();
/* 260 */       result = result * 37 + poIdValue;
/* 261 */       this.hashValue = result;
/*     */     } 
/* 263 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractWorkOrderBackFlushInStroage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.aof.model.sync.shared;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractWorkOrderBuckleMaterial
/*     */ {
/*  13 */   private int hashValue = 0;
/*     */   
/*     */   private Integer shmes_wois_det_number;
/*     */   
/*     */   private Integer shmes_wois_det_id;
/*     */   
/*     */   private String shmes_wois_seq;
/*     */   
/*     */   private String shmes_wois_id;
/*     */   private String shmes_wois_item;
/*     */   private String shmes_wois_part;
/*     */   private String shmes_wois_loc;
/*     */   private BigDecimal shmes_wois_qty;
/*     */   private String shmes_wois_gw;
/*     */   private String shmes_wois_op;
/*     */   private Date shmes_wois_date;
/*     */   private String shmes_worc_domain;
/*     */   private String shmes_worc_site;
/*     */   private String shmes_wois_fk;
/*     */   private String shmes_wois_qadread;
/*     */   private String shmes_wois_mesread;
/*     */   private String shmes_wois_portalread;
/*     */   private String shmes_wois_ediread;
/*     */   private String shmes_wois_rmks;
/*     */   private Date shmes_wois_createdt;
/*     */   private Integer shmes_wois_createdt1;
/*     */   private String shmes_wois_createur;
/*     */   private Date shmes_wois_updatedt;
/*     */   private Integer shmes_wois_updatedt1;
/*     */   private String shmes_wois_updateur;
/*     */   
/*     */   public Integer getShmes_wois_det_number() {
/*  45 */     return this.shmes_wois_det_number;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_det_number(Integer shmes_wois_det_number) {
/*  49 */     this.shmes_wois_det_number = shmes_wois_det_number;
/*     */   }
/*     */   
/*     */   public Integer getShmes_wois_det_id() {
/*  53 */     return this.shmes_wois_det_id;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_det_id(Integer shmes_wois_det_id) {
/*  57 */     this.shmes_wois_det_id = shmes_wois_det_id;
/*     */   }
/*     */   
/*     */   public String getShmes_wois_seq() {
/*  61 */     return this.shmes_wois_seq;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_seq(String shmes_wois_seq) {
/*  65 */     this.shmes_wois_seq = shmes_wois_seq;
/*     */   }
/*     */   
/*     */   public String getShmes_wois_id() {
/*  69 */     return this.shmes_wois_id;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_id(String shmes_wois_id) {
/*  73 */     this.shmes_wois_id = shmes_wois_id;
/*     */   }
/*     */   
/*     */   public String getShmes_wois_item() {
/*  77 */     return this.shmes_wois_item;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_item(String shmes_wois_item) {
/*  81 */     this.shmes_wois_item = shmes_wois_item;
/*     */   }
/*     */   
/*     */   public String getShmes_wois_part() {
/*  85 */     return this.shmes_wois_part;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_part(String shmes_wois_part) {
/*  89 */     this.shmes_wois_part = shmes_wois_part;
/*     */   }
/*     */   
/*     */   public String getShmes_wois_loc() {
/*  93 */     return this.shmes_wois_loc;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_loc(String shmes_wois_loc) {
/*  97 */     this.shmes_wois_loc = shmes_wois_loc;
/*     */   }
/*     */   
/*     */   public BigDecimal getShmes_wois_qty() {
/* 101 */     return this.shmes_wois_qty;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_qty(BigDecimal shmes_wois_qty) {
/* 105 */     this.shmes_wois_qty = shmes_wois_qty;
/*     */   }
/*     */   
/*     */   public String getShmes_wois_gw() {
/* 109 */     return this.shmes_wois_gw;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_gw(String shmes_wois_gw) {
/* 113 */     this.shmes_wois_gw = shmes_wois_gw;
/*     */   }
/*     */   
/*     */   public String getShmes_wois_op() {
/* 117 */     return this.shmes_wois_op;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_op(String shmes_wois_op) {
/* 121 */     this.shmes_wois_op = shmes_wois_op;
/*     */   }
/*     */   
/*     */   public Date getShmes_wois_date() {
/* 125 */     return this.shmes_wois_date;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_date(Date shmes_wois_date) {
/* 129 */     this.shmes_wois_date = shmes_wois_date;
/*     */   }
/*     */   
/*     */   public String getShmes_worc_domain() {
/* 133 */     return this.shmes_worc_domain;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_domain(String shmes_worc_domain) {
/* 137 */     this.shmes_worc_domain = shmes_worc_domain;
/*     */   }
/*     */   
/*     */   public String getShmes_worc_site() {
/* 141 */     return this.shmes_worc_site;
/*     */   }
/*     */   
/*     */   public void setShmes_worc_site(String shmes_worc_site) {
/* 145 */     this.shmes_worc_site = shmes_worc_site;
/*     */   }
/*     */   
/*     */   public String getShmes_wois_fk() {
/* 149 */     return this.shmes_wois_fk;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_fk(String shmes_wois_fk) {
/* 153 */     this.shmes_wois_fk = shmes_wois_fk;
/*     */   }
/*     */   
/*     */   public String getShmes_wois_qadread() {
/* 157 */     return this.shmes_wois_qadread;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_qadread(String shmes_wois_qadread) {
/* 161 */     this.shmes_wois_qadread = shmes_wois_qadread;
/*     */   }
/*     */   
/*     */   public String getShmes_wois_mesread() {
/* 165 */     return this.shmes_wois_mesread;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_mesread(String shmes_wois_mesread) {
/* 169 */     this.shmes_wois_mesread = shmes_wois_mesread;
/*     */   }
/*     */   
/*     */   public String getShmes_wois_portalread() {
/* 173 */     return this.shmes_wois_portalread;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_portalread(String shmes_wois_portalread) {
/* 177 */     this.shmes_wois_portalread = shmes_wois_portalread;
/*     */   }
/*     */   
/*     */   public String getShmes_wois_ediread() {
/* 181 */     return this.shmes_wois_ediread;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_ediread(String shmes_wois_ediread) {
/* 185 */     this.shmes_wois_ediread = shmes_wois_ediread;
/*     */   }
/*     */   
/*     */   public String getShmes_wois_rmks() {
/* 189 */     return this.shmes_wois_rmks;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_rmks(String shmes_wois_rmks) {
/* 193 */     this.shmes_wois_rmks = shmes_wois_rmks;
/*     */   }
/*     */   
/*     */   public Date getShmes_wois_createdt() {
/* 197 */     return this.shmes_wois_createdt;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_createdt(Date shmes_wois_createdt) {
/* 201 */     this.shmes_wois_createdt = shmes_wois_createdt;
/*     */   }
/*     */   
/*     */   public Integer getShmes_wois_createdt1() {
/* 205 */     return this.shmes_wois_createdt1;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_createdt1(Integer shmes_wois_createdt1) {
/* 209 */     this.shmes_wois_createdt1 = shmes_wois_createdt1;
/*     */   }
/*     */   
/*     */   public String getShmes_wois_createur() {
/* 213 */     return this.shmes_wois_createur;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_createur(String shmes_wois_createur) {
/* 217 */     this.shmes_wois_createur = shmes_wois_createur;
/*     */   }
/*     */   
/*     */   public Date getShmes_wois_updatedt() {
/* 221 */     return this.shmes_wois_updatedt;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_updatedt(Date shmes_wois_updatedt) {
/* 225 */     this.shmes_wois_updatedt = shmes_wois_updatedt;
/*     */   }
/*     */   
/*     */   public Integer getShmes_wois_updatedt1() {
/* 229 */     return this.shmes_wois_updatedt1;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_updatedt1(Integer shmes_wois_updatedt1) {
/* 233 */     this.shmes_wois_updatedt1 = shmes_wois_updatedt1;
/*     */   }
/*     */   
/*     */   public String getShmes_wois_updateur() {
/* 237 */     return this.shmes_wois_updateur;
/*     */   }
/*     */   
/*     */   public void setShmes_wois_updateur(String shmes_wois_updateur) {
/* 241 */     this.shmes_wois_updateur = shmes_wois_updateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractWorkOrderBuckleMaterial() {}
/*     */ 
/*     */   
/*     */   public AbstractWorkOrderBuckleMaterial(Integer shmes_wois_seq) {
/* 249 */     setShmes_wois_det_id(shmes_wois_seq);
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
/* 260 */     if (rhs == null)
/* 261 */       return false; 
/* 262 */     if (this == rhs)
/* 263 */       return true; 
/* 264 */     if (!(rhs instanceof WorkOrderBuckleMaterial))
/* 265 */       return false; 
/* 266 */     WorkOrderBuckleMaterial that = (WorkOrderBuckleMaterial)rhs;
/* 267 */     if (getShmes_wois_det_id() != null)
/* 268 */       return getShmes_wois_det_id().equals(
/* 269 */           that.getShmes_wois_det_id()); 
/* 270 */     return (that.getShmes_wois_det_id() == null);
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
/* 281 */     if (this.hashValue == 0) {
/* 282 */       int result = 17;
/* 283 */       int poIdValue = (getShmes_wois_det_id() == null) ? 0 : 
/* 284 */         getShmes_wois_det_id().hashCode();
/* 285 */       result = result * 37 + poIdValue;
/* 286 */       this.hashValue = result;
/*     */     } 
/* 288 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractWorkOrderBuckleMaterial.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
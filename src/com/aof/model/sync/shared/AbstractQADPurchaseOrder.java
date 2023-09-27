/*     */ package com.aof.model.sync.shared;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractQADPurchaseOrder implements Serializable {
/*   8 */   private int hashValue = 0;
/*     */   
/*     */   private Integer xxqad_pod_id;
/*     */   
/*     */   private Integer xxqad_pod_due_date1;
/*     */   
/*     */   private String xxqad_pod_status;
/*     */   
/*     */   private String xxqad_pod_nbr;
/*     */   
/*     */   private Integer xxqad_pod_line;
/*     */   
/*     */   private String xxqad_pod_part;
/*     */   private String xxqad_pod_desc;
/*     */   private BigDecimal xxqad_pod_qty_ord;
/*     */   private BigDecimal xxqad_pod_qty_std;
/*     */   private Date xxqad_pod_due_date;
/*     */   private String xxqad_pod_loc_um;
/*     */   private String xxqad_pod_um;
/*     */   private BigDecimal xxqad_pod_um_conv;
/*     */   private String xxqad_pod_vend;
/*     */   private String xxqad_pod_name;
/*     */   private String xxqad_pod_attn;
/*     */   private String xxqad_pod_ship;
/*     */   private String xxqad_pod_made;
/*     */   private String xxqad_pod_site;
/*     */   private String xxqad_pod_confirm;
/*     */   private String xxqad_pod_domain;
/*     */   private String xxqad_pod_buyer;
/*     */   private String xxqad_pod_buyer_phone;
/*     */   private String xxqad_pod_qadread;
/*     */   private String xxqad_pod_mesread;
/*     */   private String xxqad_pod_portalread;
/*     */   private String xxqad_pod_ediread;
/*     */   private String xxqad_pod_rmks;
/*     */   private Date xxqad_pod_createdt;
/*     */   private Integer xxqad_pod_createdt1;
/*     */   private String xxqad_pod_createur;
/*     */   private Date xxqad_pod_updatedt;
/*     */   private Integer xxqad_pod_updatedt1;
/*     */   private String xxqad_pod_updateur;
/*     */   private String xxqad_pod_seq;
/*     */   
/*     */   public Integer getXxqad_pod_id() {
/*  52 */     return this.xxqad_pod_id;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_id(Integer xxqad_pod_id) {
/*  56 */     this.xxqad_pod_id = xxqad_pod_id;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_pod_due_date1() {
/*  60 */     return this.xxqad_pod_due_date1;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_due_date1(Integer xxqad_pod_due_date1) {
/*  64 */     this.xxqad_pod_due_date1 = xxqad_pod_due_date1;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_status() {
/*  68 */     return this.xxqad_pod_status;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_status(String xxqad_pod_status) {
/*  72 */     this.xxqad_pod_status = xxqad_pod_status;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_pod_createdt1() {
/*  76 */     return this.xxqad_pod_createdt1;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_createdt1(Integer xxqad_pod_createdt1) {
/*  80 */     this.xxqad_pod_createdt1 = xxqad_pod_createdt1;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_pod_updatedt1() {
/*  84 */     return this.xxqad_pod_updatedt1;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_updatedt1(Integer xxqad_pod_updatedt1) {
/*  88 */     this.xxqad_pod_updatedt1 = xxqad_pod_updatedt1;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_nbr() {
/*  92 */     return this.xxqad_pod_nbr;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_nbr(String xxqad_pod_nbr) {
/*  96 */     this.xxqad_pod_nbr = xxqad_pod_nbr;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_pod_line() {
/* 100 */     return this.xxqad_pod_line;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_line(Integer xxqad_pod_line) {
/* 104 */     this.xxqad_pod_line = xxqad_pod_line;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_part() {
/* 108 */     return this.xxqad_pod_part;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_part(String xxqad_pod_part) {
/* 112 */     this.xxqad_pod_part = xxqad_pod_part;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_desc() {
/* 116 */     return this.xxqad_pod_desc;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_desc(String xxqad_pod_desc) {
/* 120 */     this.xxqad_pod_desc = xxqad_pod_desc;
/*     */   }
/*     */   
/*     */   public BigDecimal getXxqad_pod_qty_ord() {
/* 124 */     return this.xxqad_pod_qty_ord;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_qty_ord(BigDecimal xxqad_pod_qty_ord) {
/* 128 */     this.xxqad_pod_qty_ord = xxqad_pod_qty_ord;
/*     */   }
/*     */   
/*     */   public BigDecimal getXxqad_pod_qty_std() {
/* 132 */     return this.xxqad_pod_qty_std;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_qty_std(BigDecimal xxqad_pod_qty_std) {
/* 136 */     this.xxqad_pod_qty_std = xxqad_pod_qty_std;
/*     */   }
/*     */   
/*     */   public Date getXxqad_pod_due_date() {
/* 140 */     return this.xxqad_pod_due_date;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_due_date(Date xxqad_pod_due_date) {
/* 144 */     this.xxqad_pod_due_date = xxqad_pod_due_date;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_loc_um() {
/* 148 */     return this.xxqad_pod_loc_um;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_loc_um(String xxqad_pod_loc_um) {
/* 152 */     this.xxqad_pod_loc_um = xxqad_pod_loc_um;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_um() {
/* 156 */     return this.xxqad_pod_um;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_um(String xxqad_pod_um) {
/* 160 */     this.xxqad_pod_um = xxqad_pod_um;
/*     */   }
/*     */   
/*     */   public BigDecimal getXxqad_pod_um_conv() {
/* 164 */     return this.xxqad_pod_um_conv;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_um_conv(BigDecimal xxqad_pod_um_conv) {
/* 168 */     this.xxqad_pod_um_conv = xxqad_pod_um_conv;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_vend() {
/* 172 */     return this.xxqad_pod_vend;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_vend(String xxqad_pod_vend) {
/* 176 */     this.xxqad_pod_vend = xxqad_pod_vend;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_name() {
/* 180 */     return this.xxqad_pod_name;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_name(String xxqad_pod_name) {
/* 184 */     this.xxqad_pod_name = xxqad_pod_name;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_attn() {
/* 188 */     return this.xxqad_pod_attn;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_attn(String xxqad_pod_attn) {
/* 192 */     this.xxqad_pod_attn = xxqad_pod_attn;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_ship() {
/* 196 */     return this.xxqad_pod_ship;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_ship(String xxqad_pod_ship) {
/* 200 */     this.xxqad_pod_ship = xxqad_pod_ship;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_made() {
/* 204 */     return this.xxqad_pod_made;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_made(String xxqad_pod_made) {
/* 208 */     this.xxqad_pod_made = xxqad_pod_made;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_site() {
/* 212 */     return this.xxqad_pod_site;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_site(String xxqad_pod_site) {
/* 216 */     this.xxqad_pod_site = xxqad_pod_site;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_confirm() {
/* 220 */     return this.xxqad_pod_confirm;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_confirm(String xxqad_pod_confirm) {
/* 224 */     this.xxqad_pod_confirm = xxqad_pod_confirm;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_domain() {
/* 228 */     return this.xxqad_pod_domain;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_domain(String xxqad_pod_domain) {
/* 232 */     this.xxqad_pod_domain = xxqad_pod_domain;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_buyer() {
/* 236 */     return this.xxqad_pod_buyer;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_buyer(String xxqad_pod_buyer) {
/* 240 */     this.xxqad_pod_buyer = xxqad_pod_buyer;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_buyer_phone() {
/* 244 */     return this.xxqad_pod_buyer_phone;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_buyer_phone(String xxqad_pod_buyer_phone) {
/* 248 */     this.xxqad_pod_buyer_phone = xxqad_pod_buyer_phone;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_rmks() {
/* 252 */     return this.xxqad_pod_rmks;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_rmks(String xxqad_pod_rmks) {
/* 256 */     this.xxqad_pod_rmks = xxqad_pod_rmks;
/*     */   }
/*     */   
/*     */   public Date getXxqad_pod_createdt() {
/* 260 */     return this.xxqad_pod_createdt;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_createdt(Date xxqad_pod_createdt) {
/* 264 */     this.xxqad_pod_createdt = xxqad_pod_createdt;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_createur() {
/* 268 */     return this.xxqad_pod_createur;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_createur(String xxqad_pod_createur) {
/* 272 */     this.xxqad_pod_createur = xxqad_pod_createur;
/*     */   }
/*     */   
/*     */   public Date getXxqad_pod_updatedt() {
/* 276 */     return this.xxqad_pod_updatedt;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_updatedt(Date xxqad_pod_updatedt) {
/* 280 */     this.xxqad_pod_updatedt = xxqad_pod_updatedt;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_updateur() {
/* 284 */     return this.xxqad_pod_updateur;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_updateur(String xxqad_pod_updateur) {
/* 288 */     this.xxqad_pod_updateur = xxqad_pod_updateur;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_seq() {
/* 292 */     return this.xxqad_pod_seq;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_seq(String xxqad_pod_seq) {
/* 296 */     this.xxqad_pod_seq = xxqad_pod_seq;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_qadread() {
/* 300 */     return this.xxqad_pod_qadread;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_qadread(String xxqad_pod_qadread) {
/* 304 */     this.xxqad_pod_qadread = xxqad_pod_qadread;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_mesread() {
/* 308 */     return this.xxqad_pod_mesread;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_mesread(String xxqad_pod_mesread) {
/* 312 */     this.xxqad_pod_mesread = xxqad_pod_mesread;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_portalread() {
/* 316 */     return this.xxqad_pod_portalread;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_portalread(String xxqad_pod_portalread) {
/* 320 */     this.xxqad_pod_portalread = xxqad_pod_portalread;
/*     */   }
/*     */   
/*     */   public String getXxqad_pod_ediread() {
/* 324 */     return this.xxqad_pod_ediread;
/*     */   }
/*     */   
/*     */   public void setXxqad_pod_ediread(String xxqad_pod_ediread) {
/* 328 */     this.xxqad_pod_ediread = xxqad_pod_ediread;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractQADPurchaseOrder() {}
/*     */ 
/*     */   
/*     */   public AbstractQADPurchaseOrder(Integer id) {
/* 337 */     setXxqad_pod_id(id);
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
/* 348 */     if (rhs == null)
/* 349 */       return false; 
/* 350 */     if (this == rhs)
/* 351 */       return true; 
/* 352 */     if (!(rhs instanceof QADPurchaseOrder))
/* 353 */       return false; 
/* 354 */     QADPurchaseOrder that = (QADPurchaseOrder)rhs;
/* 355 */     if (getXxqad_pod_id() != null)
/* 356 */       return getXxqad_pod_id().equals(that.getXxqad_pod_id()); 
/* 357 */     return (that.getXxqad_pod_id() == null);
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
/* 368 */     if (this.hashValue == 0) {
/* 369 */       int result = 17;
/* 370 */       int poIdValue = (getXxqad_pod_id() == null) ? 0 : 
/* 371 */         getXxqad_pod_id().hashCode();
/* 372 */       result = result * 37 + poIdValue;
/* 373 */       this.hashValue = result;
/*     */     } 
/* 375 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractQADPurchaseOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractQADDeliveryBill
/*     */ {
/*     */   private Integer xxptl_tx_id;
/*     */   private Integer xxptl_tx_date_due1;
/*     */   private Integer xxptl_tx_date_promise1;
/*     */   private Integer xxptl_tx_date_ship1;
/*     */   private Integer xxptl_tx_det_id;
/*     */   private String xxptl_tx_nbr;
/*     */   private String xxptl_tx_po_nbr;
/*     */   private String xxptl_tx_po_line;
/*     */   private String xxptl_tx_part;
/*     */   private BigDecimal xxptl_tx_qty_total;
/*     */   private BigDecimal xxptl_tx_qty_std;
/*     */   private BigDecimal xxptl_tx_qty_real;
/*     */   private Date xxptl_tx_date_ship;
/*     */   private Date xxptl_tx_date_Promise;
/*     */   private Date xxptl_tx_date_due;
/*     */   private String xxptl_tx_site;
/*     */   private String xxptl_tx_vend;
/*     */   private String xxptl_tx_boxcode;
/*     */   private String xxptl_tx_ware_class;
/*     */   private String xxptl_tx_ware;
/*     */   private String xxptl_tx_loc;
/*     */   private String xxptl_tx_po_um;
/*     */   private String xxptl_tx_loc_um;
/*     */   private BigDecimal xxptl_tx_conv;
/*     */   private String xxptl_tx_type;
/*     */   private String xxptl_tx_vend_batch;
/*     */   private String xxptl_tx_qadread;
/*     */   private String xxptl_tx_mesread;
/*     */   private String xxptl_tx_portalread;
/*     */   private String xxptl_tx_ediread;
/*     */   private String xxptl_tx_rmks;
/*     */   private Date xxptl_tx_createdt;
/*     */   private Integer xxptl_tx_createdt1;
/*     */   private String xxptl_tx_createur;
/*     */   private Date xxptl_tx_updatedt;
/*     */   private Date xxptl_tx_updatedt_1;
/*     */   private Integer xxptl_tx_updatedt1;
/*     */   private String xxptl_tx_updateur;
/*     */   private String xxptl_tx_seq;
/*     */   
/*     */   public Date getXxptl_tx_updatedt_1() {
/*  61 */     return this.xxptl_tx_updatedt_1;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_updatedt_1(Date xxptl_tx_updatedt_1) {
/*  65 */     this.xxptl_tx_updatedt_1 = xxptl_tx_updatedt_1;
/*     */   }
/*     */   
/*     */   public Integer getXxptl_tx_id() {
/*  69 */     return this.xxptl_tx_id;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_id(Integer xxptl_tx_id) {
/*  73 */     this.xxptl_tx_id = xxptl_tx_id;
/*     */   }
/*     */   
/*     */   public Integer getXxptl_tx_date_due1() {
/*  77 */     return this.xxptl_tx_date_due1;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_date_due1(Integer xxptl_tx_date_due1) {
/*  81 */     this.xxptl_tx_date_due1 = xxptl_tx_date_due1;
/*     */   }
/*     */   
/*     */   public Integer getXxptl_tx_date_promise1() {
/*  85 */     return this.xxptl_tx_date_promise1;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_date_promise1(Integer xxptl_tx_date_promise1) {
/*  89 */     this.xxptl_tx_date_promise1 = xxptl_tx_date_promise1;
/*     */   }
/*     */   
/*     */   public Integer getXxptl_tx_date_ship1() {
/*  93 */     return this.xxptl_tx_date_ship1;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_date_ship1(Integer xxptl_tx_date_ship1) {
/*  97 */     this.xxptl_tx_date_ship1 = xxptl_tx_date_ship1;
/*     */   }
/*     */   
/*     */   public Integer getXxptl_tx_det_id() {
/* 101 */     return this.xxptl_tx_det_id;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_det_id(Integer xxptl_tx_det_id) {
/* 105 */     this.xxptl_tx_det_id = xxptl_tx_det_id;
/*     */   }
/*     */   
/*     */   public Integer getXxptl_tx_createdt1() {
/* 109 */     return this.xxptl_tx_createdt1;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_createdt1(Integer xxptl_tx_createdt1) {
/* 113 */     this.xxptl_tx_createdt1 = xxptl_tx_createdt1;
/*     */   }
/*     */   
/*     */   public Integer getXxptl_tx_updatedt1() {
/* 117 */     return this.xxptl_tx_updatedt1;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_updatedt1(Integer xxptl_tx_updatedt1) {
/* 121 */     this.xxptl_tx_updatedt1 = xxptl_tx_updatedt1;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_nbr() {
/* 125 */     return this.xxptl_tx_nbr;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_nbr(String xxptl_tx_nbr) {
/* 129 */     this.xxptl_tx_nbr = xxptl_tx_nbr;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_po_nbr() {
/* 133 */     return this.xxptl_tx_po_nbr;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_po_nbr(String xxptl_tx_po_nbr) {
/* 137 */     this.xxptl_tx_po_nbr = xxptl_tx_po_nbr;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_po_line() {
/* 141 */     return this.xxptl_tx_po_line;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_po_line(String xxptl_tx_po_line) {
/* 145 */     this.xxptl_tx_po_line = xxptl_tx_po_line;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_part() {
/* 149 */     return this.xxptl_tx_part;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_part(String xxptl_tx_part) {
/* 153 */     this.xxptl_tx_part = xxptl_tx_part;
/*     */   }
/*     */   
/*     */   public BigDecimal getXxptl_tx_qty_total() {
/* 157 */     return this.xxptl_tx_qty_total;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_qty_total(BigDecimal xxptl_tx_qty_total) {
/* 161 */     this.xxptl_tx_qty_total = xxptl_tx_qty_total;
/*     */   }
/*     */   
/*     */   public BigDecimal getXxptl_tx_qty_std() {
/* 165 */     return this.xxptl_tx_qty_std;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_qty_std(BigDecimal xxptl_tx_qty_std) {
/* 169 */     this.xxptl_tx_qty_std = xxptl_tx_qty_std;
/*     */   }
/*     */   
/*     */   public BigDecimal getXxptl_tx_qty_real() {
/* 173 */     return this.xxptl_tx_qty_real;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_qty_real(BigDecimal xxptl_tx_qty_real) {
/* 177 */     this.xxptl_tx_qty_real = xxptl_tx_qty_real;
/*     */   }
/*     */   
/*     */   public Date getXxptl_tx_date_ship() {
/* 181 */     return this.xxptl_tx_date_ship;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_date_ship(Date xxptl_tx_date_ship) {
/* 185 */     this.xxptl_tx_date_ship = xxptl_tx_date_ship;
/*     */   }
/*     */   
/*     */   public Date getXxptl_tx_date_Promise() {
/* 189 */     return this.xxptl_tx_date_Promise;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_date_Promise(Date xxptl_tx_date_Promise) {
/* 193 */     this.xxptl_tx_date_Promise = xxptl_tx_date_Promise;
/*     */   }
/*     */   
/*     */   public Date getXxptl_tx_date_due() {
/* 197 */     return this.xxptl_tx_date_due;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_date_due(Date xxptl_tx_date_due) {
/* 201 */     this.xxptl_tx_date_due = xxptl_tx_date_due;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_site() {
/* 205 */     return this.xxptl_tx_site;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_site(String xxptl_tx_site) {
/* 209 */     this.xxptl_tx_site = xxptl_tx_site;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_vend() {
/* 213 */     return this.xxptl_tx_vend;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_vend(String xxptl_tx_vend) {
/* 217 */     this.xxptl_tx_vend = xxptl_tx_vend;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_boxcode() {
/* 221 */     return this.xxptl_tx_boxcode;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_boxcode(String xxptl_tx_boxcode) {
/* 225 */     this.xxptl_tx_boxcode = xxptl_tx_boxcode;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_ware_class() {
/* 229 */     return this.xxptl_tx_ware_class;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_ware_class(String xxptl_tx_ware_class) {
/* 233 */     this.xxptl_tx_ware_class = xxptl_tx_ware_class;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_ware() {
/* 237 */     return this.xxptl_tx_ware;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_ware(String xxptl_tx_ware) {
/* 241 */     this.xxptl_tx_ware = xxptl_tx_ware;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_loc() {
/* 245 */     return this.xxptl_tx_loc;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_loc(String xxptl_tx_loc) {
/* 249 */     this.xxptl_tx_loc = xxptl_tx_loc;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_po_um() {
/* 253 */     return this.xxptl_tx_po_um;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_po_um(String xxptl_tx_po_um) {
/* 257 */     this.xxptl_tx_po_um = xxptl_tx_po_um;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_loc_um() {
/* 261 */     return this.xxptl_tx_loc_um;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_loc_um(String xxptl_tx_loc_um) {
/* 265 */     this.xxptl_tx_loc_um = xxptl_tx_loc_um;
/*     */   }
/*     */   
/*     */   public BigDecimal getXxptl_tx_conv() {
/* 269 */     return this.xxptl_tx_conv;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_conv(BigDecimal xxptl_tx_conv) {
/* 273 */     this.xxptl_tx_conv = xxptl_tx_conv;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_type() {
/* 277 */     return this.xxptl_tx_type;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_type(String xxptl_tx_type) {
/* 281 */     this.xxptl_tx_type = xxptl_tx_type;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_vend_batch() {
/* 285 */     return this.xxptl_tx_vend_batch;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_vend_batch(String xxptl_tx_vend_batch) {
/* 289 */     this.xxptl_tx_vend_batch = xxptl_tx_vend_batch;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_qadread() {
/* 293 */     return this.xxptl_tx_qadread;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_qadread(String xxptl_tx_qadread) {
/* 297 */     this.xxptl_tx_qadread = xxptl_tx_qadread;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_mesread() {
/* 301 */     return this.xxptl_tx_mesread;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_mesread(String xxptl_tx_mesread) {
/* 305 */     this.xxptl_tx_mesread = xxptl_tx_mesread;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_portalread() {
/* 309 */     return this.xxptl_tx_portalread;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_portalread(String xxptl_tx_portalread) {
/* 313 */     this.xxptl_tx_portalread = xxptl_tx_portalread;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_ediread() {
/* 317 */     return this.xxptl_tx_ediread;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_ediread(String xxptl_tx_ediread) {
/* 321 */     this.xxptl_tx_ediread = xxptl_tx_ediread;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_rmks() {
/* 325 */     return this.xxptl_tx_rmks;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_rmks(String xxptl_tx_rmks) {
/* 329 */     this.xxptl_tx_rmks = xxptl_tx_rmks;
/*     */   }
/*     */   
/*     */   public Date getXxptl_tx_createdt() {
/* 333 */     return this.xxptl_tx_createdt;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_createdt(Date xxptl_tx_createdt) {
/* 337 */     this.xxptl_tx_createdt = xxptl_tx_createdt;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_createur() {
/* 341 */     return this.xxptl_tx_createur;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_createur(String xxptl_tx_createur) {
/* 345 */     this.xxptl_tx_createur = xxptl_tx_createur;
/*     */   }
/*     */   
/*     */   public Date getXxptl_tx_updatedt() {
/* 349 */     return this.xxptl_tx_updatedt;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_updatedt(Date xxptl_tx_updatedt) {
/* 353 */     this.xxptl_tx_updatedt = xxptl_tx_updatedt;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_updateur() {
/* 357 */     return this.xxptl_tx_updateur;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_updateur(String xxptl_tx_updateur) {
/* 361 */     this.xxptl_tx_updateur = xxptl_tx_updateur;
/*     */   }
/*     */   
/*     */   public String getXxptl_tx_seq() {
/* 365 */     return this.xxptl_tx_seq;
/*     */   }
/*     */   
/*     */   public void setXxptl_tx_seq(String xxptl_tx_seq) {
/* 369 */     this.xxptl_tx_seq = xxptl_tx_seq;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractQADDeliveryBill() {}
/*     */ 
/*     */   
/*     */   public AbstractQADDeliveryBill(String xxptl_tx_seq) {
/* 378 */     this.xxptl_tx_seq = xxptl_tx_seq;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractQADDeliveryBill.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
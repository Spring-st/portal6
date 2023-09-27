/*     */ package com.aof.model.sync.shared;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class AbstractXbipddDet
/*     */ {
/*     */   private String xbipdd_nbr;
/*     */   private Integer xbipdd_line;
/*     */   private String xbipdd_ponbr;
/*     */   private Integer xbipdd_poline;
/*     */   private String xbipdd_ctnbr;
/*     */   private String xbipdd_part;
/*     */   private String xbipdd_lot;
/*     */   private Integer xbipdd_qty;
/*     */   private String xbipdd_uf1;
/*     */   private String xbipdd_uf2;
/*     */   private String xbipdd_uf3;
/*     */   private String xbipdd_qadread;
/*     */   private String xbipdd_mesread;
/*     */   private String xbipdd_portalread;
/*     */   private String xbipdd_ediread;
/*     */   private Date xbipdd_createdt;
/*     */   private String xbipdd_createur;
/*     */   private Date xbipdd_updatedt;
/*     */   private String xbipdd_updateur;
/*     */   private Integer xbipdd_number;
/*     */   
/*     */   public AbstractXbipddDet() {}
/*     */   
/*     */   public AbstractXbipddDet(Integer id) {
/*  31 */     this.xbipdd_number = id;
/*     */   }
/*     */   public String getXbipdd_nbr() {
/*  34 */     return this.xbipdd_nbr;
/*     */   }
/*     */   public void setXbipdd_nbr(String xbipdd_nbr) {
/*  37 */     this.xbipdd_nbr = xbipdd_nbr;
/*     */   }
/*     */   public Integer getXbipdd_line() {
/*  40 */     return this.xbipdd_line;
/*     */   }
/*     */   public void setXbipdd_line(Integer xbipdd_line) {
/*  43 */     this.xbipdd_line = xbipdd_line;
/*     */   }
/*     */   public String getXbipdd_ponbr() {
/*  46 */     return this.xbipdd_ponbr;
/*     */   }
/*     */   public void setXbipdd_ponbr(String xbipdd_ponbr) {
/*  49 */     this.xbipdd_ponbr = xbipdd_ponbr;
/*     */   }
/*     */   public Integer getXbipdd_poline() {
/*  52 */     return this.xbipdd_poline;
/*     */   }
/*     */   public void setXbipdd_poline(Integer xbipdd_poline) {
/*  55 */     this.xbipdd_poline = xbipdd_poline;
/*     */   }
/*     */   public String getXbipdd_ctnbr() {
/*  58 */     return this.xbipdd_ctnbr;
/*     */   }
/*     */   public void setXbipdd_ctnbr(String xbipdd_ctnbr) {
/*  61 */     this.xbipdd_ctnbr = xbipdd_ctnbr;
/*     */   }
/*     */   public String getXbipdd_part() {
/*  64 */     return this.xbipdd_part;
/*     */   }
/*     */   public void setXbipdd_part(String xbipdd_part) {
/*  67 */     this.xbipdd_part = xbipdd_part;
/*     */   }
/*     */   public String getXbipdd_lot() {
/*  70 */     return this.xbipdd_lot;
/*     */   }
/*     */   public void setXbipdd_lot(String xbipdd_lot) {
/*  73 */     this.xbipdd_lot = xbipdd_lot;
/*     */   }
/*     */   public Integer getXbipdd_qty() {
/*  76 */     return this.xbipdd_qty;
/*     */   }
/*     */   public void setXbipdd_qty(Integer xbipdd_qty) {
/*  79 */     this.xbipdd_qty = xbipdd_qty;
/*     */   }
/*     */   public String getXbipdd_uf1() {
/*  82 */     return this.xbipdd_uf1;
/*     */   }
/*     */   public void setXbipdd_uf1(String xbipdd_uf1) {
/*  85 */     this.xbipdd_uf1 = xbipdd_uf1;
/*     */   }
/*     */   public String getXbipdd_uf2() {
/*  88 */     return this.xbipdd_uf2;
/*     */   }
/*     */   public void setXbipdd_uf2(String xbipdd_uf2) {
/*  91 */     this.xbipdd_uf2 = xbipdd_uf2;
/*     */   }
/*     */   public String getXbipdd_uf3() {
/*  94 */     return this.xbipdd_uf3;
/*     */   }
/*     */   public void setXbipdd_uf3(String xbipdd_uf3) {
/*  97 */     this.xbipdd_uf3 = xbipdd_uf3;
/*     */   }
/*     */   public String getXbipdd_qadread() {
/* 100 */     return this.xbipdd_qadread;
/*     */   }
/*     */   public void setXbipdd_qadread(String xbipdd_qadread) {
/* 103 */     this.xbipdd_qadread = xbipdd_qadread;
/*     */   }
/*     */   public String getXbipdd_mesread() {
/* 106 */     return this.xbipdd_mesread;
/*     */   }
/*     */   public void setXbipdd_mesread(String xbipdd_mesread) {
/* 109 */     this.xbipdd_mesread = xbipdd_mesread;
/*     */   }
/*     */   public String getXbipdd_portalread() {
/* 112 */     return this.xbipdd_portalread;
/*     */   }
/*     */   public void setXbipdd_portalread(String xbipdd_portalread) {
/* 115 */     this.xbipdd_portalread = xbipdd_portalread;
/*     */   }
/*     */   public String getXbipdd_ediread() {
/* 118 */     return this.xbipdd_ediread;
/*     */   }
/*     */   public void setXbipdd_ediread(String xbipdd_ediread) {
/* 121 */     this.xbipdd_ediread = xbipdd_ediread;
/*     */   }
/*     */   public Date getXbipdd_createdt() {
/* 124 */     return this.xbipdd_createdt;
/*     */   }
/*     */   public void setXbipdd_createdt(Date xbipdd_createdt) {
/* 127 */     this.xbipdd_createdt = xbipdd_createdt;
/*     */   }
/*     */   public String getXbipdd_createur() {
/* 130 */     return this.xbipdd_createur;
/*     */   }
/*     */   public void setXbipdd_createur(String xbipdd_createur) {
/* 133 */     this.xbipdd_createur = xbipdd_createur;
/*     */   }
/*     */   public Date getXbipdd_updatedt() {
/* 136 */     return this.xbipdd_updatedt;
/*     */   }
/*     */   public void setXbipdd_updatedt(Date xbipdd_updatedt) {
/* 139 */     this.xbipdd_updatedt = xbipdd_updatedt;
/*     */   }
/*     */   public String getXbipdd_updateur() {
/* 142 */     return this.xbipdd_updateur;
/*     */   }
/*     */   public void setXbipdd_updateur(String xbipdd_updateur) {
/* 145 */     this.xbipdd_updateur = xbipdd_updateur;
/*     */   }
/*     */   public Integer getXbipdd_number() {
/* 148 */     return this.xbipdd_number;
/*     */   }
/*     */   public void setXbipdd_number(Integer xbipdd_number) {
/* 151 */     this.xbipdd_number = xbipdd_number;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractXbipddDet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
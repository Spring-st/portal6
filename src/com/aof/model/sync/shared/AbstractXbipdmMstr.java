/*     */ package com.aof.model.sync.shared;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class AbstractXbipdmMstr {
/*     */   private String xbipdm_nbr;
/*     */   private String xbipdm_vend;
/*     */   private Date xbipdm_date;
/*     */   private String xbipdm_site;
/*     */   private String xbipdm_uf1;
/*     */   private String xbipdm_uf2;
/*     */   private String xbipdm_uf3;
/*     */   private String xbipdm_qadread;
/*     */   private String xbipdm_mesread;
/*     */   private String xbipdm_ediread;
/*     */   private String xbipdm_portalread;
/*     */   private Date xbipdm_createdt;
/*     */   private String xbipdm_createur;
/*     */   private Date xbipdm_updatedt;
/*     */   private String xbipdm_updateur;
/*     */   private Integer xbipdm_number;
/*     */   
/*     */   public AbstractXbipdmMstr() {}
/*     */   
/*     */   public AbstractXbipdmMstr(Integer id) {
/*  26 */     this.xbipdm_number = id;
/*     */   }
/*     */   public String getXbipdm_nbr() {
/*  29 */     return this.xbipdm_nbr;
/*     */   }
/*     */   public void setXbipdm_nbr(String xbipdm_nbr) {
/*  32 */     this.xbipdm_nbr = xbipdm_nbr;
/*     */   }
/*     */   public String getXbipdm_vend() {
/*  35 */     return this.xbipdm_vend;
/*     */   }
/*     */   public void setXbipdm_vend(String xbipdm_vend) {
/*  38 */     this.xbipdm_vend = xbipdm_vend;
/*     */   }
/*     */   public Date getXbipdm_date() {
/*  41 */     return this.xbipdm_date;
/*     */   }
/*     */   public void setXbipdm_date(Date xbipdm_date) {
/*  44 */     this.xbipdm_date = xbipdm_date;
/*     */   }
/*     */   public String getXbipdm_site() {
/*  47 */     return this.xbipdm_site;
/*     */   }
/*     */   public void setXbipdm_site(String xbipdm_site) {
/*  50 */     this.xbipdm_site = xbipdm_site;
/*     */   }
/*     */   public String getXbipdm_uf1() {
/*  53 */     return this.xbipdm_uf1;
/*     */   }
/*     */   public void setXbipdm_uf1(String xbipdm_uf1) {
/*  56 */     this.xbipdm_uf1 = xbipdm_uf1;
/*     */   }
/*     */   public String getXbipdm_uf2() {
/*  59 */     return this.xbipdm_uf2;
/*     */   }
/*     */   public void setXbipdm_uf2(String xbipdm_uf2) {
/*  62 */     this.xbipdm_uf2 = xbipdm_uf2;
/*     */   }
/*     */   public String getXbipdm_uf3() {
/*  65 */     return this.xbipdm_uf3;
/*     */   }
/*     */   public void setXbipdm_uf3(String xbipdm_uf3) {
/*  68 */     this.xbipdm_uf3 = xbipdm_uf3;
/*     */   }
/*     */   public String getXbipdm_qadread() {
/*  71 */     return this.xbipdm_qadread;
/*     */   }
/*     */   public void setXbipdm_qadread(String xbipdm_qadread) {
/*  74 */     this.xbipdm_qadread = xbipdm_qadread;
/*     */   }
/*     */   public String getXbipdm_mesread() {
/*  77 */     return this.xbipdm_mesread;
/*     */   }
/*     */   public void setXbipdm_mesread(String xbipdm_mesread) {
/*  80 */     this.xbipdm_mesread = xbipdm_mesread;
/*     */   }
/*     */   public String getXbipdm_ediread() {
/*  83 */     return this.xbipdm_ediread;
/*     */   }
/*     */   public void setXbipdm_ediread(String xbipdm_ediread) {
/*  86 */     this.xbipdm_ediread = xbipdm_ediread;
/*     */   }
/*     */   public String getXbipdm_portalread() {
/*  89 */     return this.xbipdm_portalread;
/*     */   }
/*     */   public void setXbipdm_portalread(String xbipdm_portalread) {
/*  92 */     this.xbipdm_portalread = xbipdm_portalread;
/*     */   }
/*     */   public Date getXbipdm_createdt() {
/*  95 */     return this.xbipdm_createdt;
/*     */   }
/*     */   public void setXbipdm_createdt(Date xbipdm_createdt) {
/*  98 */     this.xbipdm_createdt = xbipdm_createdt;
/*     */   }
/*     */   public String getXbipdm_createur() {
/* 101 */     return this.xbipdm_createur;
/*     */   }
/*     */   public void setXbipdm_createur(String xbipdm_createur) {
/* 104 */     this.xbipdm_createur = xbipdm_createur;
/*     */   }
/*     */   public Date getXbipdm_updatedt() {
/* 107 */     return this.xbipdm_updatedt;
/*     */   }
/*     */   public void setXbipdm_updatedt(Date xbipdm_updatedt) {
/* 110 */     this.xbipdm_updatedt = xbipdm_updatedt;
/*     */   }
/*     */   public String getXbipdm_updateur() {
/* 113 */     return this.xbipdm_updateur;
/*     */   }
/*     */   public void setXbipdm_updateur(String xbipdm_updateur) {
/* 116 */     this.xbipdm_updateur = xbipdm_updateur;
/*     */   }
/*     */   public Integer getXbipdm_number() {
/* 119 */     return this.xbipdm_number;
/*     */   }
/*     */   public void setXbipdm_number(Integer xbipdm_number) {
/* 122 */     this.xbipdm_number = xbipdm_number;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractXbipdmMstr.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
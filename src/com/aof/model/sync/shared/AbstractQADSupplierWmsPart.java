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
/*     */ public class AbstractQADSupplierWmsPart
/*     */   implements Serializable
/*     */ {
/*  14 */   private int hashValue = 0;
/*     */   
/*     */   private Integer xxqad_vp_id;
/*     */   
/*     */   private Integer xxqad_vp_ctrl_id;
/*     */   
/*     */   private String xxqad_vp_addr;
/*     */   private String xxqad_vp_part;
/*     */   private String xxqad_vp_site;
/*     */   private BigDecimal xxqad_vp_comment;
/*     */   private String xxqad_vp_domain;
/*     */   private Integer xxqad_vp_qadread;
/*     */   private Integer xxqad_vp_mesread;
/*     */   private Integer xxqad_vp_portalread;
/*     */   private Integer xxqad_vp_ediread;
/*     */   private String xxqad_vp_rmks;
/*     */   private Date xxqad_vp_createdt;
/*     */   private Integer xxqad_vp_createdt1;
/*     */   private String xxqad_vp_createur;
/*     */   private Date xxqad_vp_updatedt;
/*     */   private Integer xxqad_vp_updatedt1;
/*     */   private String xxqad_vp_updateur;
/*     */   private String xxqad_vp_seq;
/*     */   
/*     */   public Integer getXxqad_vp_id() {
/*  39 */     return this.xxqad_vp_id;
/*     */   }
/*     */   
/*     */   public void setXxqad_vp_id(Integer xxqad_vp_id) {
/*  43 */     this.xxqad_vp_id = xxqad_vp_id;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_vp_ctrl_id() {
/*  47 */     return this.xxqad_vp_ctrl_id;
/*     */   }
/*     */   
/*     */   public void setXxqad_vp_ctrl_id(Integer xxqad_vp_ctrl_id) {
/*  51 */     this.xxqad_vp_ctrl_id = xxqad_vp_ctrl_id;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_vp_createdt1() {
/*  55 */     return this.xxqad_vp_createdt1;
/*     */   }
/*     */   
/*     */   public void setXxqad_vp_createdt1(Integer xxqad_vp_createdt1) {
/*  59 */     this.xxqad_vp_createdt1 = xxqad_vp_createdt1;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_vp_updatedt1() {
/*  63 */     return this.xxqad_vp_updatedt1;
/*     */   }
/*     */   
/*     */   public void setXxqad_vp_updatedt1(Integer xxqad_vp_updatedt1) {
/*  67 */     this.xxqad_vp_updatedt1 = xxqad_vp_updatedt1;
/*     */   }
/*     */   
/*     */   public String getXxqad_vp_addr() {
/*  71 */     return this.xxqad_vp_addr;
/*     */   }
/*     */   
/*     */   public void setXxqad_vp_addr(String xxqad_vp_addr) {
/*  75 */     this.xxqad_vp_addr = xxqad_vp_addr;
/*     */   }
/*     */   
/*     */   public String getXxqad_vp_part() {
/*  79 */     return this.xxqad_vp_part;
/*     */   }
/*     */   
/*     */   public void setXxqad_vp_part(String xxqad_vp_part) {
/*  83 */     this.xxqad_vp_part = xxqad_vp_part;
/*     */   }
/*     */   
/*     */   public String getXxqad_vp_site() {
/*  87 */     return this.xxqad_vp_site;
/*     */   }
/*     */   
/*     */   public void setXxqad_vp_site(String xxqad_vp_site) {
/*  91 */     this.xxqad_vp_site = xxqad_vp_site;
/*     */   }
/*     */   
/*     */   public BigDecimal getXxqad_vp_comment() {
/*  95 */     return this.xxqad_vp_comment;
/*     */   }
/*     */   
/*     */   public void setXxqad_vp_comment(BigDecimal xxqad_vp_comment) {
/*  99 */     this.xxqad_vp_comment = xxqad_vp_comment;
/*     */   }
/*     */   
/*     */   public String getXxqad_vp_domain() {
/* 103 */     return this.xxqad_vp_domain;
/*     */   }
/*     */   
/*     */   public void setXxqad_vp_domain(String xxqad_vp_domain) {
/* 107 */     this.xxqad_vp_domain = xxqad_vp_domain;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_vp_qadread() {
/* 111 */     return this.xxqad_vp_qadread;
/*     */   }
/*     */   
/*     */   public void setXxqad_vp_qadread(Integer xxqad_vp_qadread) {
/* 115 */     this.xxqad_vp_qadread = xxqad_vp_qadread;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_vp_mesread() {
/* 119 */     return this.xxqad_vp_mesread;
/*     */   }
/*     */   
/*     */   public void setXxqad_vp_mesread(Integer xxqad_vp_mesread) {
/* 123 */     this.xxqad_vp_mesread = xxqad_vp_mesread;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_vp_portalread() {
/* 127 */     return this.xxqad_vp_portalread;
/*     */   }
/*     */   
/*     */   public void setXxqad_vp_portalread(Integer xxqad_vp_portalread) {
/* 131 */     this.xxqad_vp_portalread = xxqad_vp_portalread;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_vp_ediread() {
/* 135 */     return this.xxqad_vp_ediread;
/*     */   }
/*     */   
/*     */   public void setXxqad_vp_ediread(Integer xxqad_vp_ediread) {
/* 139 */     this.xxqad_vp_ediread = xxqad_vp_ediread;
/*     */   }
/*     */   
/*     */   public String getXxqad_vp_rmks() {
/* 143 */     return this.xxqad_vp_rmks;
/*     */   }
/*     */   
/*     */   public void setXxqad_vp_rmks(String xxqad_vp_rmks) {
/* 147 */     this.xxqad_vp_rmks = xxqad_vp_rmks;
/*     */   }
/*     */   
/*     */   public Date getXxqad_vp_createdt() {
/* 151 */     return this.xxqad_vp_createdt;
/*     */   }
/*     */   
/*     */   public void setXxqad_vp_createdt(Date xxqad_vp_createdt) {
/* 155 */     this.xxqad_vp_createdt = xxqad_vp_createdt;
/*     */   }
/*     */   
/*     */   public String getXxqad_vp_createur() {
/* 159 */     return this.xxqad_vp_createur;
/*     */   }
/*     */   
/*     */   public void setXxqad_vp_createur(String xxqad_vp_createur) {
/* 163 */     this.xxqad_vp_createur = xxqad_vp_createur;
/*     */   }
/*     */   
/*     */   public Date getXxqad_vp_updatedt() {
/* 167 */     return this.xxqad_vp_updatedt;
/*     */   }
/*     */   
/*     */   public void setXxqad_vp_updatedt(Date xxqad_vp_updatedt) {
/* 171 */     this.xxqad_vp_updatedt = xxqad_vp_updatedt;
/*     */   }
/*     */   
/*     */   public String getXxqad_vp_updateur() {
/* 175 */     return this.xxqad_vp_updateur;
/*     */   }
/*     */   
/*     */   public void setXxqad_vp_updateur(String xxqad_vp_updateur) {
/* 179 */     this.xxqad_vp_updateur = xxqad_vp_updateur;
/*     */   }
/*     */   
/*     */   public String getXxqad_vp_seq() {
/* 183 */     return this.xxqad_vp_seq;
/*     */   }
/*     */   
/*     */   public void setXxqad_vp_seq(String xxqad_vp_seq) {
/* 187 */     this.xxqad_vp_seq = xxqad_vp_seq;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractQADSupplierWmsPart() {}
/*     */ 
/*     */   
/*     */   public AbstractQADSupplierWmsPart(Integer id) {
/* 195 */     setXxqad_vp_id(id);
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
/* 206 */     if (rhs == null)
/* 207 */       return false; 
/* 208 */     if (this == rhs)
/* 209 */       return true; 
/* 210 */     if (!(rhs instanceof QADSupplierWmsPart))
/* 211 */       return false; 
/* 212 */     QADSupplierWmsPart that = (QADSupplierWmsPart)rhs;
/* 213 */     if (getXxqad_vp_id() != null)
/* 214 */       return getXxqad_vp_id().equals(that.getXxqad_vp_id()); 
/* 215 */     return (that.getXxqad_vp_id() == null);
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
/* 226 */     if (this.hashValue == 0) {
/* 227 */       int result = 17;
/* 228 */       int poIdValue = (getXxqad_vp_id() == null) ? 0 : 
/* 229 */         getXxqad_vp_id().hashCode();
/* 230 */       result = result * 37 + poIdValue;
/* 231 */       this.hashValue = result;
/*     */     } 
/* 233 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractQADSupplierWmsPart.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
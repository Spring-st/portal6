/*     */ package com.aof.model.sync.shared;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractQADSupplier implements Serializable {
/*   7 */   private int hashValue = 0;
/*     */   
/*     */   private Integer xxqad_vd_id;
/*     */   
/*     */   private Integer xxqad_vd_mstr_id;
/*     */   
/*     */   private String xxqad_vd_addr;
/*     */   private String xxqad_vd_name;
/*     */   private String xxqad_vd_line1;
/*     */   private String xxqad_vd_line2;
/*     */   private String xxqad_vd_line3;
/*     */   private String xxqad_vd_country;
/*     */   private String xxqad_vd_city;
/*     */   private String xxqad_vd_phone;
/*     */   private String xxqad_vd_fax;
/*     */   private String xxqad_vd_pst_id;
/*     */   private String xxqad_vd_attn;
/*     */   private String xxqad_vd_curr;
/*     */   private String xxqad_vd_active;
/*     */   private String xxqad_vd_promo;
/*     */   private String xxqad_vd_domain;
/*     */   private String xxqad_vd_site;
/*     */   private String xxqad_vd_qadread;
/*     */   private String xxqad_vd_mesread;
/*     */   private String xxqad_vd_portalread;
/*     */   private String xxqad_vd_ediread;
/*     */   private String xxqad_vd_rmks;
/*     */   private Date xxqad_vd_createdt;
/*     */   private Integer xxqad_vd_createdt1;
/*     */   private String xxqad_vd_createur;
/*     */   private Date xxqad_vd_updatedt;
/*     */   private Integer xxqad_vd_updatedt1;
/*     */   private String xxqad_vd_updateur;
/*     */   private String xxqad_vd_seq;
/*     */   
/*     */   public Integer getXxqad_vd_id() {
/*  43 */     return this.xxqad_vd_id;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_id(Integer xxqad_vd_id) {
/*  47 */     this.xxqad_vd_id = xxqad_vd_id;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_vd_mstr_id() {
/*  51 */     return this.xxqad_vd_mstr_id;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_mstr_id(Integer xxqad_vd_mstr_id) {
/*  55 */     this.xxqad_vd_mstr_id = xxqad_vd_mstr_id;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_vd_createdt1() {
/*  59 */     return this.xxqad_vd_createdt1;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_createdt1(Integer xxqad_vd_createdt1) {
/*  63 */     this.xxqad_vd_createdt1 = xxqad_vd_createdt1;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_vd_updatedt1() {
/*  67 */     return this.xxqad_vd_updatedt1;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_updatedt1(Integer xxqad_vd_updatedt1) {
/*  71 */     this.xxqad_vd_updatedt1 = xxqad_vd_updatedt1;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_addr() {
/*  75 */     return this.xxqad_vd_addr;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_addr(String xxqad_vd_addr) {
/*  79 */     this.xxqad_vd_addr = xxqad_vd_addr;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_name() {
/*  83 */     return this.xxqad_vd_name;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_name(String xxqad_vd_name) {
/*  87 */     this.xxqad_vd_name = xxqad_vd_name;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_line1() {
/*  91 */     return this.xxqad_vd_line1;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_line1(String xxqad_vd_line1) {
/*  95 */     this.xxqad_vd_line1 = xxqad_vd_line1;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_line2() {
/*  99 */     return this.xxqad_vd_line2;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_line2(String xxqad_vd_line2) {
/* 103 */     this.xxqad_vd_line2 = xxqad_vd_line2;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_line3() {
/* 107 */     return this.xxqad_vd_line3;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_line3(String xxqad_vd_line3) {
/* 111 */     this.xxqad_vd_line3 = xxqad_vd_line3;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_country() {
/* 115 */     return this.xxqad_vd_country;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_country(String xxqad_vd_country) {
/* 119 */     this.xxqad_vd_country = xxqad_vd_country;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_city() {
/* 123 */     return this.xxqad_vd_city;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_city(String xxqad_vd_city) {
/* 127 */     this.xxqad_vd_city = xxqad_vd_city;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_phone() {
/* 131 */     return this.xxqad_vd_phone;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_phone(String xxqad_vd_phone) {
/* 135 */     this.xxqad_vd_phone = xxqad_vd_phone;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_fax() {
/* 139 */     return this.xxqad_vd_fax;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_fax(String xxqad_vd_fax) {
/* 143 */     this.xxqad_vd_fax = xxqad_vd_fax;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_pst_id() {
/* 147 */     return this.xxqad_vd_pst_id;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_pst_id(String xxqad_vd_pst_id) {
/* 151 */     this.xxqad_vd_pst_id = xxqad_vd_pst_id;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_attn() {
/* 155 */     return this.xxqad_vd_attn;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_attn(String xxqad_vd_attn) {
/* 159 */     this.xxqad_vd_attn = xxqad_vd_attn;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_curr() {
/* 163 */     return this.xxqad_vd_curr;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_curr(String xxqad_vd_curr) {
/* 167 */     this.xxqad_vd_curr = xxqad_vd_curr;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_active() {
/* 171 */     return this.xxqad_vd_active;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_active(String xxqad_vd_active) {
/* 175 */     this.xxqad_vd_active = xxqad_vd_active;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_promo() {
/* 179 */     return this.xxqad_vd_promo;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_promo(String xxqad_vd_promo) {
/* 183 */     this.xxqad_vd_promo = xxqad_vd_promo;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_domain() {
/* 187 */     return this.xxqad_vd_domain;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_domain(String xxqad_vd_domain) {
/* 191 */     this.xxqad_vd_domain = xxqad_vd_domain;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_qadread() {
/* 195 */     return this.xxqad_vd_qadread;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_qadread(String xxqad_vd_qadread) {
/* 199 */     this.xxqad_vd_qadread = xxqad_vd_qadread;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_mesread() {
/* 203 */     return this.xxqad_vd_mesread;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_mesread(String xxqad_vd_mesread) {
/* 207 */     this.xxqad_vd_mesread = xxqad_vd_mesread;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_portalread() {
/* 211 */     return this.xxqad_vd_portalread;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_portalread(String xxqad_vd_portalread) {
/* 215 */     this.xxqad_vd_portalread = xxqad_vd_portalread;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_ediread() {
/* 219 */     return this.xxqad_vd_ediread;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_ediread(String xxqad_vd_ediread) {
/* 223 */     this.xxqad_vd_ediread = xxqad_vd_ediread;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_rmks() {
/* 227 */     return this.xxqad_vd_rmks;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_rmks(String xxqad_vd_rmks) {
/* 231 */     this.xxqad_vd_rmks = xxqad_vd_rmks;
/*     */   }
/*     */   
/*     */   public Date getXxqad_vd_createdt() {
/* 235 */     return this.xxqad_vd_createdt;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_createdt(Date xxqad_vd_createdt) {
/* 239 */     this.xxqad_vd_createdt = xxqad_vd_createdt;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_createur() {
/* 243 */     return this.xxqad_vd_createur;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_createur(String xxqad_vd_createur) {
/* 247 */     this.xxqad_vd_createur = xxqad_vd_createur;
/*     */   }
/*     */   
/*     */   public Date getXxqad_vd_updatedt() {
/* 251 */     return this.xxqad_vd_updatedt;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_updatedt(Date xxqad_vd_updatedt) {
/* 255 */     this.xxqad_vd_updatedt = xxqad_vd_updatedt;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_updateur() {
/* 259 */     return this.xxqad_vd_updateur;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_updateur(String xxqad_vd_updateur) {
/* 263 */     this.xxqad_vd_updateur = xxqad_vd_updateur;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_seq() {
/* 267 */     return this.xxqad_vd_seq;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_seq(String xxqad_vd_seq) {
/* 271 */     this.xxqad_vd_seq = xxqad_vd_seq;
/*     */   }
/*     */   
/*     */   public String getXxqad_vd_site() {
/* 275 */     return this.xxqad_vd_site;
/*     */   }
/*     */   
/*     */   public void setXxqad_vd_site(String xxqad_vd_site) {
/* 279 */     this.xxqad_vd_site = xxqad_vd_site;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractQADSupplier() {}
/*     */   
/*     */   public AbstractQADSupplier(Integer id) {
/* 286 */     setXxqad_vd_id(id);
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
/* 297 */     if (rhs == null)
/* 298 */       return false; 
/* 299 */     if (this == rhs)
/* 300 */       return true; 
/* 301 */     if (!(rhs instanceof QADSupplier))
/* 302 */       return false; 
/* 303 */     QADSupplier that = (QADSupplier)rhs;
/* 304 */     if (getXxqad_vd_id() != null)
/* 305 */       return getXxqad_vd_id().equals(that.getXxqad_vd_id()); 
/* 306 */     return (that.getXxqad_vd_id() == null);
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
/* 317 */     if (this.hashValue == 0) {
/* 318 */       int result = 17;
/* 319 */       int poIdValue = (getXxqad_vd_id() == null) ? 0 : 
/* 320 */         getXxqad_vd_id().hashCode();
/* 321 */       result = result * 37 + poIdValue;
/* 322 */       this.hashValue = result;
/*     */     } 
/* 324 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractQADSupplier.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
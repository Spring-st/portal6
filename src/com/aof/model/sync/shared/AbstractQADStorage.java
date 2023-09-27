/*     */ package com.aof.model.sync.shared;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractQADStorage implements Serializable {
/*   7 */   private int hashValue = 0;
/*     */   
/*     */   private Integer xxqad_loc_id;
/*     */   
/*     */   private Integer xxqad_loc_det_id;
/*     */   
/*     */   private String xxqad_loc_loc;
/*     */   
/*     */   private String xxqad_loc_desc;
/*     */   private String xxqad_loc_type;
/*     */   private String xxqad_loc_site;
/*     */   private String xxqad_loc_domain;
/*     */   private Integer xxqad_loc_qadread;
/*     */   private Integer xxqad_loc_mesread;
/*     */   private Integer xxqad_loc_portalread;
/*     */   private Integer xxqad_loc_ediread;
/*     */   private String xxqad_loc_rmks;
/*     */   private Date xxqad_loc_createdt;
/*     */   private Integer xxqad_loc_createdt1;
/*     */   private String xxqad_loc_createur;
/*     */   private Date xxqad_loc_updatedt;
/*     */   private Integer xxqad_loc_updatedt1;
/*     */   private String xxqad_loc_updateur;
/*     */   private String xxqad_loc_seq;
/*     */   
/*     */   public Integer getXxqad_loc_id() {
/*  33 */     return this.xxqad_loc_id;
/*     */   }
/*     */   
/*     */   public void setXxqad_loc_id(Integer xxqad_loc_id) {
/*  37 */     this.xxqad_loc_id = xxqad_loc_id;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_loc_det_id() {
/*  41 */     return this.xxqad_loc_det_id;
/*     */   }
/*     */   
/*     */   public void setXxqad_loc_det_id(Integer xxqad_loc_det_id) {
/*  45 */     this.xxqad_loc_det_id = xxqad_loc_det_id;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_loc_createdt1() {
/*  49 */     return this.xxqad_loc_createdt1;
/*     */   }
/*     */   
/*     */   public void setXxqad_loc_createdt1(Integer xxqad_loc_createdt1) {
/*  53 */     this.xxqad_loc_createdt1 = xxqad_loc_createdt1;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_loc_updatedt1() {
/*  57 */     return this.xxqad_loc_updatedt1;
/*     */   }
/*     */   
/*     */   public void setXxqad_loc_updatedt1(Integer xxqad_loc_updatedt1) {
/*  61 */     this.xxqad_loc_updatedt1 = xxqad_loc_updatedt1;
/*     */   }
/*     */   
/*     */   public String getXxqad_loc_loc() {
/*  65 */     return this.xxqad_loc_loc;
/*     */   }
/*     */   
/*     */   public void setXxqad_loc_loc(String xxqad_loc_loc) {
/*  69 */     this.xxqad_loc_loc = xxqad_loc_loc;
/*     */   }
/*     */   
/*     */   public String getXxqad_loc_desc() {
/*  73 */     return this.xxqad_loc_desc;
/*     */   }
/*     */   
/*     */   public void setXxqad_loc_desc(String xxqad_loc_desc) {
/*  77 */     this.xxqad_loc_desc = xxqad_loc_desc;
/*     */   }
/*     */   
/*     */   public String getXxqad_loc_type() {
/*  81 */     return this.xxqad_loc_type;
/*     */   }
/*     */   
/*     */   public void setXxqad_loc_type(String xxqad_loc_type) {
/*  85 */     this.xxqad_loc_type = xxqad_loc_type;
/*     */   }
/*     */   
/*     */   public String getXxqad_loc_site() {
/*  89 */     return this.xxqad_loc_site;
/*     */   }
/*     */   
/*     */   public void setXxqad_loc_site(String xxqad_loc_site) {
/*  93 */     this.xxqad_loc_site = xxqad_loc_site;
/*     */   }
/*     */   
/*     */   public String getXxqad_loc_domain() {
/*  97 */     return this.xxqad_loc_domain;
/*     */   }
/*     */   
/*     */   public void setXxqad_loc_domain(String xxqad_loc_domain) {
/* 101 */     this.xxqad_loc_domain = xxqad_loc_domain;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_loc_qadread() {
/* 105 */     return this.xxqad_loc_qadread;
/*     */   }
/*     */   
/*     */   public void setXxqad_loc_qadread(Integer xxqad_loc_qadread) {
/* 109 */     this.xxqad_loc_qadread = xxqad_loc_qadread;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_loc_mesread() {
/* 113 */     return this.xxqad_loc_mesread;
/*     */   }
/*     */   
/*     */   public void setXxqad_loc_mesread(Integer xxqad_loc_mesread) {
/* 117 */     this.xxqad_loc_mesread = xxqad_loc_mesread;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_loc_portalread() {
/* 121 */     return this.xxqad_loc_portalread;
/*     */   }
/*     */   
/*     */   public void setXxqad_loc_portalread(Integer xxqad_loc_portalread) {
/* 125 */     this.xxqad_loc_portalread = xxqad_loc_portalread;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_loc_ediread() {
/* 129 */     return this.xxqad_loc_ediread;
/*     */   }
/*     */   
/*     */   public void setXxqad_loc_ediread(Integer xxqad_loc_ediread) {
/* 133 */     this.xxqad_loc_ediread = xxqad_loc_ediread;
/*     */   }
/*     */   
/*     */   public String getXxqad_loc_rmks() {
/* 137 */     return this.xxqad_loc_rmks;
/*     */   }
/*     */   
/*     */   public void setXxqad_loc_rmks(String xxqad_loc_rmks) {
/* 141 */     this.xxqad_loc_rmks = xxqad_loc_rmks;
/*     */   }
/*     */   
/*     */   public Date getXxqad_loc_createdt() {
/* 145 */     return this.xxqad_loc_createdt;
/*     */   }
/*     */   
/*     */   public void setXxqad_loc_createdt(Date xxqad_loc_createdt) {
/* 149 */     this.xxqad_loc_createdt = xxqad_loc_createdt;
/*     */   }
/*     */   
/*     */   public String getXxqad_loc_createur() {
/* 153 */     return this.xxqad_loc_createur;
/*     */   }
/*     */   
/*     */   public void setXxqad_loc_createur(String xxqad_loc_createur) {
/* 157 */     this.xxqad_loc_createur = xxqad_loc_createur;
/*     */   }
/*     */   
/*     */   public Date getXxqad_loc_updatedt() {
/* 161 */     return this.xxqad_loc_updatedt;
/*     */   }
/*     */   
/*     */   public void setXxqad_loc_updatedt(Date xxqad_loc_updatedt) {
/* 165 */     this.xxqad_loc_updatedt = xxqad_loc_updatedt;
/*     */   }
/*     */   
/*     */   public String getXxqad_loc_updateur() {
/* 169 */     return this.xxqad_loc_updateur;
/*     */   }
/*     */   
/*     */   public void setXxqad_loc_updateur(String xxqad_loc_updateur) {
/* 173 */     this.xxqad_loc_updateur = xxqad_loc_updateur;
/*     */   }
/*     */   
/*     */   public String getXxqad_loc_seq() {
/* 177 */     return this.xxqad_loc_seq;
/*     */   }
/*     */   
/*     */   public void setXxqad_loc_seq(String xxqad_loc_seq) {
/* 181 */     this.xxqad_loc_seq = xxqad_loc_seq;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractQADStorage() {}
/*     */ 
/*     */   
/*     */   public AbstractQADStorage(Integer id) {
/* 189 */     setXxqad_loc_id(id);
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
/* 200 */     if (rhs == null)
/* 201 */       return false; 
/* 202 */     if (this == rhs)
/* 203 */       return true; 
/* 204 */     if (!(rhs instanceof QADStorage))
/* 205 */       return false; 
/* 206 */     QADStorage that = (QADStorage)rhs;
/* 207 */     if (getXxqad_loc_id() != null)
/* 208 */       return getXxqad_loc_id().equals(that.getXxqad_loc_id()); 
/* 209 */     return (that.getXxqad_loc_id() == null);
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
/* 220 */     if (this.hashValue == 0) {
/* 221 */       int result = 17;
/* 222 */       int poIdValue = (getXxqad_loc_id() == null) ? 0 : 
/* 223 */         getXxqad_loc_id().hashCode();
/* 224 */       result = result * 37 + poIdValue;
/* 225 */       this.hashValue = result;
/*     */     } 
/* 227 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractQADStorage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
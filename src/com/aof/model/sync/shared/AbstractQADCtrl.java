/*     */ package com.aof.model.sync.shared;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ 
/*     */ public abstract class AbstractQADCtrl implements Serializable {
/*   7 */   private int hashValue = 0;
/*     */   
/*     */   private Integer xxqad_id;
/*     */   
/*     */   private Integer xxqad_ctrl_id;
/*     */   
/*     */   private String xxqad_site;
/*     */   private String xxqad_seq;
/*     */   private String xxqad_table;
/*     */   private BigDecimal xxqad_table_qty;
/*     */   private String xxqad_qad;
/*     */   private String xxqad_mes;
/*     */   private String xxqad_portal;
/*     */   private String xxqad_edi;
/*     */   private String xxqad_domain;
/*     */   private String xxqad_rmks;
/*     */   
/*     */   public String getXxqad_qad() {
/*  25 */     return this.xxqad_qad;
/*     */   }
/*     */   
/*     */   public void setXxqad_qad(String xxqad_qad) {
/*  29 */     this.xxqad_qad = xxqad_qad;
/*     */   }
/*     */   
/*     */   public String getXxqad_mes() {
/*  33 */     return this.xxqad_mes;
/*     */   }
/*     */   
/*     */   public void setXxqad_mes(String xxqad_mes) {
/*  37 */     this.xxqad_mes = xxqad_mes;
/*     */   }
/*     */   
/*     */   public String getXxqad_portal() {
/*  41 */     return this.xxqad_portal;
/*     */   }
/*     */   
/*     */   public void setXxqad_portal(String xxqad_portal) {
/*  45 */     this.xxqad_portal = xxqad_portal;
/*     */   }
/*     */   
/*     */   public String getXxqad_edi() {
/*  49 */     return this.xxqad_edi;
/*     */   }
/*     */   
/*     */   public void setXxqad_edi(String xxqad_edi) {
/*  53 */     this.xxqad_edi = xxqad_edi;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_id() {
/*  57 */     return this.xxqad_id;
/*     */   }
/*     */   
/*     */   public void setXxqad_id(Integer xxqad_id) {
/*  61 */     this.xxqad_id = xxqad_id;
/*     */   }
/*     */   
/*     */   public Integer getXxqad_ctrl_id() {
/*  65 */     return this.xxqad_ctrl_id;
/*     */   }
/*     */   
/*     */   public void setXxqad_ctrl_id(Integer xxqad_ctrl_id) {
/*  69 */     this.xxqad_ctrl_id = xxqad_ctrl_id;
/*     */   }
/*     */   
/*     */   public String getXxqad_site() {
/*  73 */     return this.xxqad_site;
/*     */   }
/*     */   
/*     */   public void setXxqad_site(String xxqad_site) {
/*  77 */     this.xxqad_site = xxqad_site;
/*     */   }
/*     */   
/*     */   public String getXxqad_seq() {
/*  81 */     return this.xxqad_seq;
/*     */   }
/*     */   
/*     */   public void setXxqad_seq(String xxqad_seq) {
/*  85 */     this.xxqad_seq = xxqad_seq;
/*     */   }
/*     */   
/*     */   public String getXxqad_table() {
/*  89 */     return this.xxqad_table;
/*     */   }
/*     */   
/*     */   public void setXxqad_table(String xxqad_table) {
/*  93 */     this.xxqad_table = xxqad_table;
/*     */   }
/*     */   
/*     */   public BigDecimal getXxqad_table_qty() {
/*  97 */     return this.xxqad_table_qty;
/*     */   }
/*     */   
/*     */   public void setXxqad_table_qty(BigDecimal xxqad_table_qty) {
/* 101 */     this.xxqad_table_qty = xxqad_table_qty;
/*     */   }
/*     */   
/*     */   public String getXxqad_rmks() {
/* 105 */     return this.xxqad_rmks;
/*     */   }
/*     */   
/*     */   public void setXxqad_rmks(String xxqad_rmks) {
/* 109 */     this.xxqad_rmks = xxqad_rmks;
/*     */   }
/*     */   
/*     */   public String getXxqad_domain() {
/* 113 */     return this.xxqad_domain;
/*     */   }
/*     */   
/*     */   public void setXxqad_domain(String xxqad_domain) {
/* 117 */     this.xxqad_domain = xxqad_domain;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractQADCtrl() {}
/*     */   
/*     */   public AbstractQADCtrl(Integer id) {
/* 124 */     setXxqad_id(id);
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
/* 135 */     if (rhs == null)
/* 136 */       return false; 
/* 137 */     if (this == rhs)
/* 138 */       return true; 
/* 139 */     if (!(rhs instanceof QADCtrl))
/* 140 */       return false; 
/* 141 */     QADCtrl that = (QADCtrl)rhs;
/* 142 */     if (getXxqad_id() != null)
/* 143 */       return getXxqad_id().equals(that.getXxqad_id()); 
/* 144 */     return (that.getXxqad_id() == null);
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
/* 155 */     if (this.hashValue == 0) {
/* 156 */       int result = 17;
/* 157 */       int poIdValue = (getXxqad_id() == null) ? 0 : getXxqad_id()
/* 158 */         .hashCode();
/* 159 */       result = result * 37 + poIdValue;
/* 160 */       this.hashValue = result;
/*     */     } 
/* 162 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractQADCtrl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
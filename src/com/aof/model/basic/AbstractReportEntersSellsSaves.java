/*     */ package com.aof.model.basic;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractReportEntersSellsSaves implements Serializable {
/*   9 */   private int hashValue = 0;
/*     */   
/*     */   private Integer id;
/*     */   private WmsPart part;
/*     */   private Date start_date;
/*     */   private Date end_date;
/*     */   private BigDecimal initial_qty;
/*     */   private BigDecimal balance_qty;
/*     */   private User user;
/*     */   private Integer month;
/*     */   
/*     */   public Integer getMonth() {
/*  21 */     return this.month;
/*     */   }
/*     */   
/*     */   public void setMonth(Integer month) {
/*  25 */     this.month = month;
/*     */   }
/*     */   
/*     */   public WmsPart getPart() {
/*  29 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/*  33 */     this.part = part;
/*     */   }
/*     */   
/*     */   public Date getStart_date() {
/*  37 */     return this.start_date;
/*     */   }
/*     */   
/*     */   public void setStart_date(Date start_date) {
/*  41 */     this.start_date = start_date;
/*     */   }
/*     */   
/*     */   public Date getEnd_date() {
/*  45 */     return this.end_date;
/*     */   }
/*     */   
/*     */   public void setEnd_date(Date end_date) {
/*  49 */     this.end_date = end_date;
/*     */   }
/*     */   
/*     */   public BigDecimal getInitial_qty() {
/*  53 */     return this.initial_qty;
/*     */   }
/*     */   
/*     */   public void setInitial_qty(BigDecimal initial_qty) {
/*  57 */     this.initial_qty = initial_qty;
/*     */   }
/*     */   
/*     */   public BigDecimal getBalance_qty() {
/*  61 */     return this.balance_qty;
/*     */   }
/*     */   
/*     */   public void setBalance_qty(BigDecimal balance_qty) {
/*  65 */     this.balance_qty = balance_qty;
/*     */   }
/*     */   
/*     */   public User getUser() {
/*  69 */     return this.user;
/*     */   }
/*     */   
/*     */   public void setUser(User user) {
/*  73 */     this.user = user;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  77 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  81 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractReportEntersSellsSaves() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractReportEntersSellsSaves(Integer id) {
/*  93 */     setId(id);
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
/* 104 */     if (rhs == null)
/* 105 */       return false; 
/* 106 */     if (this == rhs)
/* 107 */       return true; 
/* 108 */     if (!(rhs instanceof ReportEntersSellsSaves))
/* 109 */       return false; 
/* 110 */     ReportEntersSellsSaves that = (ReportEntersSellsSaves)rhs;
/* 111 */     if (getId() != null)
/* 112 */       return getId().equals(that.getId()); 
/* 113 */     return (that.getId() == null);
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
/* 124 */     if (this.hashValue == 0) {
/* 125 */       int result = 17;
/* 126 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 127 */       result = result * 37 + poIdValue;
/* 128 */       this.hashValue = result;
/*     */     } 
/* 130 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractReportEntersSellsSaves.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
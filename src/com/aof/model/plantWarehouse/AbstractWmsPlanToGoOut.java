/*     */ package com.aof.model.plantWarehouse;
/*     */ 
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.UnplannedReasons;
/*     */ import com.aof.model.metadata.UnplannedOutboundStatus;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class AbstractWmsPlanToGoOut
/*     */ {
/*  13 */   private int hashValue = 0;
/*     */   
/*     */   private Integer id;
/*     */   private Site site;
/*     */   private User applicant;
/*     */   private Department department;
/*     */   private String code;
/*     */   private String domain;
/*     */   private Date date;
/*     */   private BigDecimal qty;
/*     */   private BigDecimal actual_qty;
/*     */   private UnplannedOutboundStatus status;
/*     */   private String remark;
/*     */   private UnplannedReasons reason_code;
/*     */   private String departmentCost;
/*     */   private String costAccount;
/*     */   
/*     */   public UnplannedReasons getReason_code() {
/*  31 */     return this.reason_code;
/*     */   }
/*     */   
/*     */   public void setReason_code(UnplannedReasons reason_code) {
/*  35 */     this.reason_code = reason_code;
/*     */   }
/*     */   
/*     */   public String getDepartmentCost() {
/*  39 */     return this.departmentCost;
/*     */   }
/*     */   
/*     */   public void setDepartmentCost(String departmentCost) {
/*  43 */     this.departmentCost = departmentCost;
/*     */   }
/*     */   
/*     */   public String getCostAccount() {
/*  47 */     return this.costAccount;
/*     */   }
/*     */   
/*     */   public void setCostAccount(String costAccount) {
/*  51 */     this.costAccount = costAccount;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/*  55 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/*  59 */     this.site = site;
/*     */   }
/*     */   
/*     */   public User getApplicant() {
/*  63 */     return this.applicant;
/*     */   }
/*     */   
/*     */   public void setApplicant(User applicant) {
/*  67 */     this.applicant = applicant;
/*     */   }
/*     */   
/*     */   public Department getDepartment() {
/*  71 */     return this.department;
/*     */   }
/*     */   
/*     */   public void setDepartment(Department department) {
/*  75 */     this.department = department;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  79 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  83 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getDomain() {
/*  87 */     return this.domain;
/*     */   }
/*     */   
/*     */   public void setDomain(String domain) {
/*  91 */     this.domain = domain;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/*  95 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/*  99 */     this.date = date;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/* 103 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/* 107 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public BigDecimal getActual_qty() {
/* 111 */     return this.actual_qty;
/*     */   }
/*     */   
/*     */   public void setActual_qty(BigDecimal actual_qty) {
/* 115 */     this.actual_qty = actual_qty;
/*     */   }
/*     */   
/*     */   public UnplannedOutboundStatus getStatus() {
/* 119 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(UnplannedOutboundStatus status) {
/* 123 */     this.status = status;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/* 127 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 131 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractWmsPlanToGoOut() {}
/*     */   
/*     */   public AbstractWmsPlanToGoOut(Integer id) {
/* 138 */     setId(id);
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 142 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 146 */     this.id = id;
/*     */   }
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 150 */     if (rhs == null)
/* 151 */       return false; 
/* 152 */     if (this == rhs)
/* 153 */       return true; 
/* 154 */     if (!(rhs instanceof WmsPlanToGoOut))
/* 155 */       return false; 
/* 156 */     WmsPlanToGoOut that = (WmsPlanToGoOut)rhs;
/* 157 */     if (getId() != null)
/* 158 */       return getId().equals(that.getId()); 
/* 159 */     return (that.getId() == null);
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
/* 170 */     if (this.hashValue == 0) {
/* 171 */       int result = 17;
/* 172 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 173 */       result = result * 37 + poIdValue;
/* 174 */       this.hashValue = result;
/*     */     } 
/* 176 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/plantWarehouse/AbstractWmsPlanToGoOut.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
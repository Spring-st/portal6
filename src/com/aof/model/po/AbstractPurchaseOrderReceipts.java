/*     */ package com.aof.model.po;
/*     */ 
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.metadata.PurchaseOrderReceiptsStatus;
/*     */ import java.io.Serializable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractPurchaseOrderReceipts
/*     */   implements Serializable
/*     */ {
/*  28 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private String code;
/*     */   private Site site;
/*     */   private Department department;
/*     */   private Date start_date;
/*     */   private Date end_date;
/*     */   private PurchaseOrderReceiptsStatus status;
/*     */   private BigDecimal plan_sum_number;
/*     */   private BigDecimal actual_sum_number;
/*     */   private String remark;
/*     */   
/*     */   public Integer getId() {
/*  41 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  45 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/*  49 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/*  53 */     this.site = site;
/*     */   }
/*     */   
/*     */   public Department getDepartment() {
/*  57 */     return this.department;
/*     */   }
/*     */   
/*     */   public void setDepartment(Department department) {
/*  61 */     this.department = department;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  65 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  69 */     this.code = code;
/*     */   }
/*     */   
/*     */   public Date getStart_date() {
/*  73 */     return this.start_date;
/*     */   }
/*     */   
/*     */   public void setStart_date(Date start_date) {
/*  77 */     this.start_date = start_date;
/*     */   }
/*     */   
/*     */   public Date getEnd_date() {
/*  81 */     return this.end_date;
/*     */   }
/*     */   
/*     */   public void setEnd_date(Date end_date) {
/*  85 */     this.end_date = end_date;
/*     */   }
/*     */   
/*     */   public PurchaseOrderReceiptsStatus getStatus() {
/*  89 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(PurchaseOrderReceiptsStatus status) {
/*  93 */     this.status = status;
/*     */   }
/*     */   
/*     */   public BigDecimal getPlan_sum_number() {
/*  97 */     return this.plan_sum_number;
/*     */   }
/*     */   
/*     */   public void setPlan_sum_number(BigDecimal plan_sum_number) {
/* 101 */     this.plan_sum_number = plan_sum_number;
/*     */   }
/*     */   
/*     */   public BigDecimal getActual_sum_number() {
/* 105 */     return this.actual_sum_number;
/*     */   }
/*     */   
/*     */   public void setActual_sum_number(BigDecimal actual_sum_number) {
/* 109 */     this.actual_sum_number = actual_sum_number;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/* 113 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 117 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderReceipts() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderReceipts(Integer id) {
/* 132 */     setId(id);
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
/* 143 */     if (rhs == null)
/* 144 */       return false; 
/* 145 */     if (this == rhs)
/* 146 */       return true; 
/* 147 */     if (!(rhs instanceof PurchaseOrderReceipts))
/* 148 */       return false; 
/* 149 */     PurchaseOrderReceipts that = (PurchaseOrderReceipts)rhs;
/* 150 */     if (getId() != null)
/* 151 */       return getId().equals(that.getId()); 
/* 152 */     return (that.getId() == null);
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
/* 163 */     if (this.hashValue == 0) {
/* 164 */       int result = 17;
/* 165 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 166 */       result = result * 37 + poIdValue;
/* 167 */       this.hashValue = result;
/*     */     } 
/* 169 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractPurchaseOrderReceipts.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.aof.model.comprehensive;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
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
/*     */ 
/*     */ public abstract class AbstractStocking
/*     */   implements Serializable
/*     */ {
/*  30 */   private int hashValue = 0;
/*     */   
/*     */   private Integer id;
/*     */   
/*     */   private Site site;
/*     */   
/*     */   private String domain;
/*     */   
/*     */   private String code;
/*     */   private User operation;
/*     */   private Date start_date;
/*     */   private Date end_date;
/*     */   private EnabledDisabled status;
/*     */   private String remark;
/*     */   private String name;
/*     */   private YesNo type;
/*     */   private BigDecimal plan_sumQty;
/*     */   private YesNo confirmStatus;
/*     */   private BigDecimal actual_sumQty;
/*     */   private BigDecimal differences_sumQty;
/*     */   private Integer inventoryType;
/*     */   
/*     */   public BigDecimal getActual_sumQty() {
/*  53 */     return this.actual_sumQty;
/*     */   }
/*     */   
/*     */   public void setActual_sumQty(BigDecimal actual_sumQty) {
/*  57 */     this.actual_sumQty = actual_sumQty;
/*     */   }
/*     */   
/*     */   public BigDecimal getDifferences_sumQty() {
/*  61 */     return this.differences_sumQty;
/*     */   }
/*     */   
/*     */   public void setDifferences_sumQty(BigDecimal differences_sumQty) {
/*  65 */     this.differences_sumQty = differences_sumQty;
/*     */   }
/*     */   
/*     */   public YesNo getConfirmStatus() {
/*  69 */     return this.confirmStatus;
/*     */   }
/*     */   
/*     */   public void setConfirmStatus(YesNo confirmStatus) {
/*  73 */     this.confirmStatus = confirmStatus;
/*     */   }
/*     */   
/*     */   public BigDecimal getPlan_sumQty() {
/*  77 */     return this.plan_sumQty;
/*     */   }
/*     */   
/*     */   public void setPlan_sumQty(BigDecimal plan_sumQty) {
/*  81 */     this.plan_sumQty = plan_sumQty;
/*     */   }
/*     */   
/*     */   public YesNo getType() {
/*  85 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(YesNo type) {
/*  89 */     this.type = type;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  93 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  97 */     this.name = name;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/* 101 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/* 105 */     this.site = site;
/*     */   }
/*     */   
/*     */   public String getDomain() {
/* 109 */     return this.domain;
/*     */   }
/*     */   
/*     */   public void setDomain(String domain) {
/* 113 */     this.domain = domain;
/*     */   }
/*     */   
/*     */   public User getOperation() {
/* 117 */     return this.operation;
/*     */   }
/*     */   
/*     */   public void setOperation(User operation) {
/* 121 */     this.operation = operation;
/*     */   }
/*     */   
/*     */   public Date getStart_date() {
/* 125 */     return this.start_date;
/*     */   }
/*     */   
/*     */   public void setStart_date(Date start_date) {
/* 129 */     this.start_date = start_date;
/*     */   }
/*     */   
/*     */   public Date getEnd_date() {
/* 133 */     return this.end_date;
/*     */   }
/*     */   
/*     */   public void setEnd_date(Date end_date) {
/* 137 */     this.end_date = end_date;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getStatus() {
/* 141 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(EnabledDisabled status) {
/* 145 */     this.status = status;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/* 149 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 153 */     this.remark = remark;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/* 157 */     this.code = code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractStocking() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractStocking(Integer id) {
/* 172 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/* 181 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/* 190 */     this.hashValue = 0;
/* 191 */     this.id = id;
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
/* 202 */     if (rhs == null)
/* 203 */       return false; 
/* 204 */     if (this == rhs)
/* 205 */       return true; 
/* 206 */     if (!(rhs instanceof Stocking))
/* 207 */       return false; 
/* 208 */     Stocking that = (Stocking)rhs;
/* 209 */     if (getId() != null)
/* 210 */       return getId().equals(that.getId()); 
/* 211 */     return (that.getId() == null);
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
/* 222 */     if (this.hashValue == 0) {
/* 223 */       int result = 17;
/* 224 */       int cityIdValue = (getId() == null) ? 0 : getId()
/* 225 */         .hashCode();
/* 226 */       result = result * 37 + cityIdValue;
/* 227 */       this.hashValue = result;
/*     */     } 
/* 229 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public String getCode() {
/* 233 */     return this.code;
/*     */   }
/*     */   
/*     */   public Integer getInventoryType() {
/* 237 */     return this.inventoryType;
/*     */   }
/*     */   
/*     */   public void setInventoryType(Integer inventoryType) {
/* 241 */     this.inventoryType = inventoryType;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/comprehensive/AbstractStocking.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
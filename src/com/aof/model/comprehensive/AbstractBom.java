/*     */ package com.aof.model.comprehensive;
/*     */ 
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractBom
/*     */   implements Serializable {
/*  10 */   private int hashValue = 0;
/*     */   
/*     */   private Integer id;
/*     */   private WmsPart product_no;
/*     */   private WmsPart father_part;
/*     */   private WmsPart child_part;
/*     */   private BigDecimal unit_qty;
/*     */   private String process;
/*     */   private String station;
/*     */   private String domain;
/*     */   private Date date;
/*     */   private String type;
/*     */   private Date start_date;
/*     */   private Date end_date;
/*     */   private String remark;
/*     */   
/*     */   public Date getDate() {
/*  27 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/*  31 */     this.date = date;
/*     */   }
/*     */   
/*     */   public String getDomain() {
/*  35 */     return this.domain;
/*     */   }
/*     */   
/*     */   public void setDomain(String domain) {
/*  39 */     this.domain = domain;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  43 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  47 */     this.id = id;
/*     */   }
/*     */   
/*     */   public WmsPart getProduct_no() {
/*  51 */     return this.product_no;
/*     */   }
/*     */   
/*     */   public void setProduct_no(WmsPart product_no) {
/*  55 */     this.product_no = product_no;
/*     */   }
/*     */   
/*     */   public WmsPart getFather_part() {
/*  59 */     return this.father_part;
/*     */   }
/*     */   
/*     */   public void setFather_part(WmsPart father_part) {
/*  63 */     this.father_part = father_part;
/*     */   }
/*     */   
/*     */   public WmsPart getChild_part() {
/*  67 */     return this.child_part;
/*     */   }
/*     */   
/*     */   public void setChild_part(WmsPart child_part) {
/*  71 */     this.child_part = child_part;
/*     */   }
/*     */   
/*     */   public BigDecimal getUnit_qty() {
/*  75 */     return this.unit_qty;
/*     */   }
/*     */   
/*     */   public void setUnit_qty(BigDecimal unit_qty) {
/*  79 */     this.unit_qty = unit_qty;
/*     */   }
/*     */   
/*     */   public String getProcess() {
/*  83 */     return this.process;
/*     */   }
/*     */   
/*     */   public void setProcess(String process) {
/*  87 */     this.process = process;
/*     */   }
/*     */   
/*     */   public String getStation() {
/*  91 */     return this.station;
/*     */   }
/*     */   
/*     */   public void setStation(String station) {
/*  95 */     this.station = station;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractBom() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractBom(Integer id) {
/* 107 */     setId(id);
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
/* 118 */     if (rhs == null)
/* 119 */       return false; 
/* 120 */     if (this == rhs)
/* 121 */       return true; 
/* 122 */     if (!(rhs instanceof Bom))
/* 123 */       return false; 
/* 124 */     Bom that = (Bom)rhs;
/* 125 */     if (getId() != null)
/* 126 */       return getId().equals(that.getId()); 
/* 127 */     return (that.getId() == null);
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
/* 138 */     if (this.hashValue == 0) {
/* 139 */       int result = 17;
/* 140 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 141 */       result = result * 37 + poIdValue;
/* 142 */       this.hashValue = result;
/*     */     } 
/* 144 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public String getType() {
/* 148 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(String type) {
/* 152 */     this.type = type;
/*     */   }
/*     */   
/*     */   public Date getStart_date() {
/* 156 */     return this.start_date;
/*     */   }
/*     */   
/*     */   public void setStart_date(Date start_date) {
/* 160 */     this.start_date = start_date;
/*     */   }
/*     */   
/*     */   public Date getEnd_date() {
/* 164 */     return this.end_date;
/*     */   }
/*     */   
/*     */   public void setEnd_date(Date end_date) {
/* 168 */     this.end_date = end_date;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/* 172 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 176 */     this.remark = remark;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/comprehensive/AbstractBom.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
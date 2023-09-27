/*     */ package com.aof.model.product;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.basic.WmsTool;
/*     */ import com.aof.model.metadata.ProductBelowLineStatus;
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
/*     */ public abstract class AbstractProductBelowLine
/*     */   implements Serializable
/*     */ {
/*  30 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private WmsTool tool;
/*     */   private WmsPart part;
/*     */   private User user;
/*     */   private StorageLocation location;
/*     */   private BigDecimal qty;
/*     */   private Date date;
/*     */   private ProductBelowLineStatus status;
/*     */   private String test1;
/*     */   private String test2;
/*     */   private String test3;
/*     */   
/*     */   public String getTest1() {
/*  44 */     return this.test1;
/*     */   }
/*     */   
/*     */   public void setTest1(String test1) {
/*  48 */     this.test1 = test1;
/*     */   }
/*     */   
/*     */   public String getTest2() {
/*  52 */     return this.test2;
/*     */   }
/*     */   
/*     */   public void setTest2(String test2) {
/*  56 */     this.test2 = test2;
/*     */   }
/*     */   
/*     */   public String getTest3() {
/*  60 */     return this.test3;
/*     */   }
/*     */   
/*     */   public void setTest3(String test3) {
/*  64 */     this.test3 = test3;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  68 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  72 */     this.id = id;
/*     */   }
/*     */   
/*     */   public WmsTool getTool() {
/*  76 */     return this.tool;
/*     */   }
/*     */   
/*     */   public void setTool(WmsTool tool) {
/*  80 */     this.tool = tool;
/*     */   }
/*     */   
/*     */   public WmsPart getPart() {
/*  84 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/*  88 */     this.part = part;
/*     */   }
/*     */   
/*     */   public User getUser() {
/*  92 */     return this.user;
/*     */   }
/*     */   
/*     */   public void setUser(User user) {
/*  96 */     this.user = user;
/*     */   }
/*     */   
/*     */   public StorageLocation getLocation() {
/* 100 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(StorageLocation location) {
/* 104 */     this.location = location;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/* 108 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/* 112 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/* 116 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/* 120 */     this.date = date;
/*     */   }
/*     */   
/*     */   public ProductBelowLineStatus getStatus() {
/* 124 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(ProductBelowLineStatus status) {
/* 128 */     this.status = status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractProductBelowLine() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractProductBelowLine(Integer id) {
/* 143 */     setId(id);
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
/* 154 */     if (rhs == null)
/* 155 */       return false; 
/* 156 */     if (this == rhs)
/* 157 */       return true; 
/* 158 */     if (!(rhs instanceof ProductBelowLine))
/* 159 */       return false; 
/* 160 */     ProductBelowLine that = (ProductBelowLine)rhs;
/* 161 */     if (getId() != null)
/* 162 */       return getId().equals(that.getId()); 
/* 163 */     return (that.getId() == null);
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
/* 174 */     if (this.hashValue == 0) {
/* 175 */       int result = 17;
/* 176 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 177 */       result = result * 37 + poIdValue;
/* 178 */       this.hashValue = result;
/*     */     } 
/* 180 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/AbstractProductBelowLine.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
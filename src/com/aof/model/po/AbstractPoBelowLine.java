/*     */ package com.aof.model.po;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.basic.WmsTool;
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
/*     */ public abstract class AbstractPoBelowLine
/*     */   implements Serializable
/*     */ {
/*  30 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private WmsTool tool;
/*     */   private User userId;
/*     */   private WmsPart part;
/*     */   private Date date;
/*     */   private YesNo status;
/*     */   private BigDecimal qty;
/*     */   private String test1;
/*     */   private String test2;
/*     */   
/*     */   public Integer getId() {
/*  42 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  46 */     this.id = id;
/*     */   }
/*     */   
/*     */   public WmsTool getTool() {
/*  50 */     return this.tool;
/*     */   }
/*     */   
/*     */   public void setTool(WmsTool tool) {
/*  54 */     this.tool = tool;
/*     */   }
/*     */   
/*     */   public User getUserId() {
/*  58 */     return this.userId;
/*     */   }
/*     */   
/*     */   public void setUserId(User userId) {
/*  62 */     this.userId = userId;
/*     */   }
/*     */   
/*     */   public WmsPart getPart() {
/*  66 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/*  70 */     this.part = part;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/*  74 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/*  78 */     this.date = date;
/*     */   }
/*     */   
/*     */   public YesNo getStatus() {
/*  82 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(YesNo status) {
/*  86 */     this.status = status;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/*  90 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/*  94 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public String getTest1() {
/*  98 */     return this.test1;
/*     */   }
/*     */   
/*     */   public void setTest1(String test1) {
/* 102 */     this.test1 = test1;
/*     */   }
/*     */   
/*     */   public String getTest2() {
/* 106 */     return this.test2;
/*     */   }
/*     */   
/*     */   public void setTest2(String test2) {
/* 110 */     this.test2 = test2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPoBelowLine() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPoBelowLine(Integer id) {
/* 125 */     setId(id);
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
/* 136 */     if (rhs == null)
/* 137 */       return false; 
/* 138 */     if (this == rhs)
/* 139 */       return true; 
/* 140 */     if (!(rhs instanceof PoBelowLine))
/* 141 */       return false; 
/* 142 */     PoBelowLine that = (PoBelowLine)rhs;
/* 143 */     if (getId() != null)
/* 144 */       return getId().equals(that.getId()); 
/* 145 */     return (that.getId() == null);
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
/* 156 */     if (this.hashValue == 0) {
/* 157 */       int result = 17;
/* 158 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 159 */       result = result * 37 + poIdValue;
/* 160 */       this.hashValue = result;
/*     */     } 
/* 162 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractPoBelowLine.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
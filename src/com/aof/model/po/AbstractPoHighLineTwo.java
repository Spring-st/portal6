/*     */ package com.aof.model.po;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.PoHighLineStatus;
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
/*     */ public abstract class AbstractPoHighLineTwo
/*     */   implements Serializable
/*     */ {
/*  30 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private Box box;
/*     */   private User userId;
/*     */   private WmsPart child_part;
/*     */   private Date date;
/*     */   private PoHighLineStatus status;
/*     */   private Integer serial_number;
/*     */   private YesNo is_finish;
/*     */   private BigDecimal qty;
/*     */   private BigDecimal already_qty;
/*     */   private String test3;
/*     */   
/*     */   public YesNo getIs_finish() {
/*  44 */     return this.is_finish;
/*     */   }
/*     */   
/*     */   public void setIs_finish(YesNo is_finish) {
/*  48 */     this.is_finish = is_finish;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  52 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  56 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Box getBox() {
/*  60 */     return this.box;
/*     */   }
/*     */   
/*     */   public void setBox(Box box) {
/*  64 */     this.box = box;
/*     */   }
/*     */   
/*     */   public User getUserId() {
/*  68 */     return this.userId;
/*     */   }
/*     */   
/*     */   public void setUserId(User userId) {
/*  72 */     this.userId = userId;
/*     */   }
/*     */   
/*     */   public WmsPart getChild_part() {
/*  76 */     return this.child_part;
/*     */   }
/*     */   
/*     */   public void setChild_part(WmsPart child_part) {
/*  80 */     this.child_part = child_part;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/*  84 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/*  88 */     this.date = date;
/*     */   }
/*     */   
/*     */   public PoHighLineStatus getStatus() {
/*  92 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(PoHighLineStatus status) {
/*  96 */     this.status = status;
/*     */   }
/*     */   
/*     */   public Integer getSerial_number() {
/* 100 */     return this.serial_number;
/*     */   }
/*     */   
/*     */   public void setSerial_number(Integer serial_number) {
/* 104 */     this.serial_number = serial_number;
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
/*     */   public BigDecimal getAlready_qty() {
/* 116 */     return this.already_qty;
/*     */   }
/*     */   
/*     */   public void setAlready_qty(BigDecimal already_qty) {
/* 120 */     this.already_qty = already_qty;
/*     */   }
/*     */   
/*     */   public String getTest3() {
/* 124 */     return this.test3;
/*     */   }
/*     */   
/*     */   public void setTest3(String test3) {
/* 128 */     this.test3 = test3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPoHighLineTwo() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPoHighLineTwo(Integer id) {
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
/* 158 */     if (!(rhs instanceof PoHighLineTwo))
/* 159 */       return false; 
/* 160 */     PoHighLineTwo that = (PoHighLineTwo)rhs;
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


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractPoHighLineTwo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
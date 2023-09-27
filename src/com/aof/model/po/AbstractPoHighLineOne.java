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
/*     */ public abstract class AbstractPoHighLineOne
/*     */   implements Serializable
/*     */ {
/*  30 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private Box box;
/*     */   private User userId;
/*     */   private WmsPart assembly_part;
/*     */   private WmsPart child_part;
/*     */   private Date date;
/*     */   private PoHighLineStatus status;
/*     */   private YesNo is_finish;
/*     */   private Integer serial_number;
/*     */   private BigDecimal qty;
/*     */   private BigDecimal already_qty;
/*     */   private String test3;
/*     */   
/*     */   public YesNo getIs_finish() {
/*  45 */     return this.is_finish;
/*     */   }
/*     */   
/*     */   public void setIs_finish(YesNo is_finish) {
/*  49 */     this.is_finish = is_finish;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  53 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  57 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Box getBox() {
/*  61 */     return this.box;
/*     */   }
/*     */   
/*     */   public void setBox(Box box) {
/*  65 */     this.box = box;
/*     */   }
/*     */   
/*     */   public User getUserId() {
/*  69 */     return this.userId;
/*     */   }
/*     */   
/*     */   public void setUserId(User userId) {
/*  73 */     this.userId = userId;
/*     */   }
/*     */   
/*     */   public WmsPart getAssembly_part() {
/*  77 */     return this.assembly_part;
/*     */   }
/*     */   
/*     */   public void setAssembly_part(WmsPart assembly_part) {
/*  81 */     this.assembly_part = assembly_part;
/*     */   }
/*     */   
/*     */   public WmsPart getChild_part() {
/*  85 */     return this.child_part;
/*     */   }
/*     */   
/*     */   public void setChild_part(WmsPart child_part) {
/*  89 */     this.child_part = child_part;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/*  93 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/*  97 */     this.date = date;
/*     */   }
/*     */   
/*     */   public PoHighLineStatus getStatus() {
/* 101 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(PoHighLineStatus status) {
/* 105 */     this.status = status;
/*     */   }
/*     */   
/*     */   public Integer getSerial_number() {
/* 109 */     return this.serial_number;
/*     */   }
/*     */   
/*     */   public void setSerial_number(Integer serial_number) {
/* 113 */     this.serial_number = serial_number;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/* 117 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/* 121 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public BigDecimal getAlready_qty() {
/* 125 */     return this.already_qty;
/*     */   }
/*     */   
/*     */   public void setAlready_qty(BigDecimal already_qty) {
/* 129 */     this.already_qty = already_qty;
/*     */   }
/*     */   
/*     */   public String getTest3() {
/* 133 */     return this.test3;
/*     */   }
/*     */   
/*     */   public void setTest3(String test3) {
/* 137 */     this.test3 = test3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPoHighLineOne() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPoHighLineOne(Integer id) {
/* 152 */     setId(id);
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
/* 163 */     if (rhs == null)
/* 164 */       return false; 
/* 165 */     if (this == rhs)
/* 166 */       return true; 
/* 167 */     if (!(rhs instanceof PoHighLineOne))
/* 168 */       return false; 
/* 169 */     PoHighLineOne that = (PoHighLineOne)rhs;
/* 170 */     if (getId() != null)
/* 171 */       return getId().equals(that.getId()); 
/* 172 */     return (that.getId() == null);
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
/* 183 */     if (this.hashValue == 0) {
/* 184 */       int result = 17;
/* 185 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 186 */       result = result * 37 + poIdValue;
/* 187 */       this.hashValue = result;
/*     */     } 
/* 189 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractPoHighLineOne.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
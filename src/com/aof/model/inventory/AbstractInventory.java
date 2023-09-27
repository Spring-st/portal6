/*     */ package com.aof.model.inventory;
/*     */ 
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.basic.WmsTool;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.po.Box;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractInventory
/*     */   implements Serializable {
/*  14 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private WmsPart part;
/*     */   private StorageLocation location;
/*     */   private Box box;
/*     */   private String address;
/*     */   private Date date;
/*     */   private BigDecimal qty;
/*     */   private String ref;
/*     */   private EnabledDisabled enabled;
/*     */   private WmsTool tool;
/*     */   
/*     */   public WmsTool getTool() {
/*  27 */     return this.tool;
/*     */   }
/*     */   
/*     */   public void setTool(WmsTool tool) {
/*  31 */     this.tool = tool;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/*  35 */     return this.enabled;
/*     */   }
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/*  39 */     this.enabled = enabled;
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
/*     */   public WmsPart getPart() {
/*  51 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/*  55 */     this.part = part;
/*     */   }
/*     */   
/*     */   public StorageLocation getLocation() {
/*  59 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(StorageLocation location) {
/*  63 */     this.location = location;
/*     */   }
/*     */   
/*     */   public Box getBox() {
/*  67 */     return this.box;
/*     */   }
/*     */   
/*     */   public void setBox(Box box) {
/*  71 */     this.box = box;
/*     */   }
/*     */   
/*     */   public String getAddress() {
/*  75 */     return this.address;
/*     */   }
/*     */   
/*     */   public void setAddress(String address) {
/*  79 */     this.address = address;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/*  83 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/*  87 */     this.date = date;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/*  91 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/*  95 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public String getRef() {
/*  99 */     return this.ref;
/*     */   }
/*     */   
/*     */   public void setRef(String ref) {
/* 103 */     this.ref = ref;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInventory() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInventory(Integer id) {
/* 115 */     setId(id);
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
/* 126 */     if (rhs == null)
/* 127 */       return false; 
/* 128 */     if (this == rhs)
/* 129 */       return true; 
/* 130 */     if (!(rhs instanceof Inventory))
/* 131 */       return false; 
/* 132 */     Inventory that = (Inventory)rhs;
/* 133 */     if (getId() != null)
/* 134 */       return getId().equals(that.getId()); 
/* 135 */     return (that.getId() == null);
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
/* 146 */     if (this.hashValue == 0) {
/* 147 */       int result = 17;
/* 148 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 149 */       result = result * 37 + poIdValue;
/* 150 */       this.hashValue = result;
/*     */     } 
/* 152 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/inventory/AbstractInventory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
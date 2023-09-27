/*     */ package com.aof.model.plantWarehouse;
/*     */ 
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import java.math.BigDecimal;
/*     */ 
/*     */ public class AbstractWmsPlanToGoOutItem
/*     */ {
/*   9 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private WmsPlanToGoOut unplanned_outbound_id;
/*     */   private WmsPart part;
/*     */   private BigDecimal qty;
/*     */   private BigDecimal actual_qty;
/*     */   private YesNo status;
/*     */   private String remark;
/*     */   
/*     */   public WmsPlanToGoOut getUnplanned_outbound_id() {
/*  19 */     return this.unplanned_outbound_id;
/*     */   }
/*     */   
/*     */   public void setUnplanned_outbound_id(WmsPlanToGoOut unplanned_outbound_id) {
/*  23 */     this.unplanned_outbound_id = unplanned_outbound_id;
/*     */   }
/*     */   
/*     */   public WmsPart getPart() {
/*  27 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/*  31 */     this.part = part;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/*  35 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/*  39 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public BigDecimal getActual_qty() {
/*  43 */     return this.actual_qty;
/*     */   }
/*     */   
/*     */   public void setActual_qty(BigDecimal actual_qty) {
/*  47 */     this.actual_qty = actual_qty;
/*     */   }
/*     */   
/*     */   public YesNo getStatus() {
/*  51 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(YesNo status) {
/*  55 */     this.status = status;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/*  59 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/*  63 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractWmsPlanToGoOutItem() {}
/*     */   
/*     */   public AbstractWmsPlanToGoOutItem(Integer id) {
/*  70 */     setId(id);
/*     */   }
/*     */   
/*     */   public int getHashValue() {
/*  74 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public void setHashValue(int hashValue) {
/*  78 */     this.hashValue = hashValue;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  82 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  86 */     this.id = id;
/*     */   }
/*     */   
/*     */   public boolean equals(Object rhs) {
/*  90 */     if (rhs == null)
/*  91 */       return false; 
/*  92 */     if (this == rhs)
/*  93 */       return true; 
/*  94 */     if (!(rhs instanceof WmsPlanToGoOutItem))
/*  95 */       return false; 
/*  96 */     WmsPlanToGoOutItem that = (WmsPlanToGoOutItem)rhs;
/*  97 */     if (getId() != null)
/*  98 */       return getId().equals(that.getId()); 
/*  99 */     return (that.getId() == null);
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
/* 110 */     if (this.hashValue == 0) {
/* 111 */       int result = 17;
/* 112 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 113 */       result = result * 37 + poIdValue;
/* 114 */       this.hashValue = result;
/*     */     } 
/* 116 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/plantWarehouse/AbstractWmsPlanToGoOutItem.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
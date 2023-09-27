/*     */ package com.aof.model.po;
/*     */ 
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
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
/*     */ 
/*     */ public abstract class AbstractPortalShipOrderItem
/*     */   implements Serializable
/*     */ {
/*  28 */   private Integer hashValue = Integer.valueOf(0);
/*     */   private Integer id;
/*     */   private PortalShipOrder portalShipOrder;
/*     */   private PurchaseOrderInspectionPendingItem poipItem;
/*     */   private BigDecimal deliveryNumber;
/*     */   private YesNo status;
/*     */   private BigDecimal already_season_qty;
/*     */   private YesNo status_confirm;
/*     */   private BigDecimal received_qty;
/*     */   private BigDecimal qty_std;
/*     */   private BigDecimal actual_qty;
/*     */   private YesNo printStatus;
/*     */   private WmsPart part;
/*     */   private Integer isSync;
/*     */   
/*     */   public YesNo getPrintStatus() {
/*  44 */     return this.printStatus;
/*     */   }
/*     */   
/*     */   public void setPrintStatus(YesNo printStatus) {
/*  48 */     this.printStatus = printStatus;
/*     */   }
/*     */   
/*     */   public BigDecimal getActual_qty() {
/*  52 */     return this.actual_qty;
/*     */   }
/*     */   
/*     */   public void setActual_qty(BigDecimal actual_qty) {
/*  56 */     this.actual_qty = actual_qty;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty_std() {
/*  60 */     return this.qty_std;
/*     */   }
/*     */   
/*     */   public void setQty_std(BigDecimal qty_std) {
/*  64 */     this.qty_std = qty_std;
/*     */   }
/*     */   
/*     */   public BigDecimal getReceived_qty() {
/*  68 */     return this.received_qty;
/*     */   }
/*     */   
/*     */   public void setReceived_qty(BigDecimal received_qty) {
/*  72 */     this.received_qty = received_qty;
/*     */   }
/*     */   
/*     */   public YesNo getStatus_confirm() {
/*  76 */     return this.status_confirm;
/*     */   }
/*     */   
/*     */   public void setStatus_confirm(YesNo status_confirm) {
/*  80 */     this.status_confirm = status_confirm;
/*     */   }
/*     */   
/*     */   public BigDecimal getAlready_season_qty() {
/*  84 */     return this.already_season_qty;
/*     */   }
/*     */   
/*     */   public void setAlready_season_qty(BigDecimal already_season_qty) {
/*  88 */     this.already_season_qty = already_season_qty;
/*     */   }
/*     */   
/*     */   public YesNo getStatus() {
/*  92 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(YesNo status) {
/*  96 */     this.status = status;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 100 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 104 */     this.id = id;
/*     */   }
/*     */   
/*     */   public PortalShipOrder getPortalShipOrder() {
/* 108 */     return this.portalShipOrder;
/*     */   }
/*     */   
/*     */   public void setPortalShipOrder(PortalShipOrder portalShipOrder) {
/* 112 */     this.portalShipOrder = portalShipOrder;
/*     */   }
/*     */   
/*     */   public PurchaseOrderInspectionPendingItem getPoipItem() {
/* 116 */     return this.poipItem;
/*     */   }
/*     */   
/*     */   public void setPoipItem(PurchaseOrderInspectionPendingItem poipItem) {
/* 120 */     this.poipItem = poipItem;
/*     */   }
/*     */   
/*     */   public BigDecimal getDeliveryNumber() {
/* 124 */     return this.deliveryNumber;
/*     */   }
/*     */   
/*     */   public void setDeliveryNumber(BigDecimal deliveryNumber) {
/* 128 */     this.deliveryNumber = deliveryNumber;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractPortalShipOrderItem() {}
/*     */   
/*     */   public AbstractPortalShipOrderItem(Integer id) {
/* 135 */     setId(id);
/*     */   }
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 139 */     if (rhs == null)
/* 140 */       return false; 
/* 141 */     if (this == rhs)
/* 142 */       return true; 
/* 143 */     if (!(rhs instanceof PortalShipOrderItem))
/* 144 */       return false; 
/* 145 */     PortalShipOrderItem that = (PortalShipOrderItem)rhs;
/* 146 */     if (getId() != null)
/* 147 */       return getId().equals(that.getId()); 
/* 148 */     return (that.getId() == null);
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
/* 159 */     if (this.hashValue.intValue() == 0) {
/* 160 */       int result = 17;
/* 161 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 162 */       result = result * 37 + poIdValue;
/* 163 */       this.hashValue = Integer.valueOf(result);
/*     */     } 
/* 165 */     return this.hashValue.intValue();
/*     */   }
/*     */   
/*     */   public WmsPart getPart() {
/* 169 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/* 173 */     this.part = part;
/*     */   }
/*     */   
/*     */   public Integer getIsSync() {
/* 177 */     return this.isSync;
/*     */   }
/*     */   
/*     */   public void setIsSync(Integer isSync) {
/* 181 */     this.isSync = isSync;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractPortalShipOrderItem.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
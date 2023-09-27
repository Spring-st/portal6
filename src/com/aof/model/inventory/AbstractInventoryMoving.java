/*     */ package com.aof.model.inventory;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.WmsLot;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractInventoryMoving
/*     */   implements Serializable {
/*  15 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private Site site;
/*     */   private StorageLocation old_location;
/*     */   private StorageLocation new_location;
/*     */   private WmsPart part;
/*     */   private String domain;
/*     */   private BigDecimal qty;
/*     */   private Date date;
/*     */   private YesNo is_sync;
/*     */   private Date is_sync_date;
/*     */   private String remark;
/*     */   private User user;
/*     */   private WmsLot lotSer;
/*     */   private WmsLot oldLotSer;
/*     */   
/*     */   public WmsLot getLotSer() {
/*  32 */     return this.lotSer;
/*     */   }
/*     */   
/*     */   public void setLotSer(WmsLot lotSer) {
/*  36 */     this.lotSer = lotSer;
/*     */   }
/*     */   
/*     */   public User getUser() {
/*  40 */     return this.user;
/*     */   }
/*     */   
/*     */   public void setUser(User user) {
/*  44 */     this.user = user;
/*     */   }
/*     */   
/*     */   public WmsPart getPart() {
/*  48 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/*  52 */     this.part = part;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  56 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  60 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/*  64 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/*  68 */     this.site = site;
/*     */   }
/*     */   
/*     */   public StorageLocation getOld_location() {
/*  72 */     return this.old_location;
/*     */   }
/*     */   
/*     */   public void setOld_location(StorageLocation old_location) {
/*  76 */     this.old_location = old_location;
/*     */   }
/*     */   
/*     */   public StorageLocation getNew_location() {
/*  80 */     return this.new_location;
/*     */   }
/*     */   
/*     */   public void setNew_location(StorageLocation new_location) {
/*  84 */     this.new_location = new_location;
/*     */   }
/*     */   
/*     */   public String getDomain() {
/*  88 */     return this.domain;
/*     */   }
/*     */   
/*     */   public void setDomain(String domain) {
/*  92 */     this.domain = domain;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/*  96 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/* 100 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/* 104 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/* 108 */     this.date = date;
/*     */   }
/*     */   
/*     */   public YesNo getIs_sync() {
/* 112 */     return this.is_sync;
/*     */   }
/*     */   
/*     */   public void setIs_sync(YesNo is_sync) {
/* 116 */     this.is_sync = is_sync;
/*     */   }
/*     */   
/*     */   public Date getIs_sync_date() {
/* 120 */     return this.is_sync_date;
/*     */   }
/*     */   
/*     */   public void setIs_sync_date(Date is_sync_date) {
/* 124 */     this.is_sync_date = is_sync_date;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/* 128 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 132 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInventoryMoving() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInventoryMoving(Integer id) {
/* 144 */     setId(id);
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
/* 155 */     if (rhs == null)
/* 156 */       return false; 
/* 157 */     if (this == rhs)
/* 158 */       return true; 
/* 159 */     if (!(rhs instanceof InventoryMoving))
/* 160 */       return false; 
/* 161 */     InventoryMoving that = (InventoryMoving)rhs;
/* 162 */     if (getId() != null)
/* 163 */       return getId().equals(that.getId()); 
/* 164 */     return (that.getId() == null);
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
/* 175 */     if (this.hashValue == 0) {
/* 176 */       int result = 17;
/* 177 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 178 */       result = result * 37 + poIdValue;
/* 179 */       this.hashValue = result;
/*     */     } 
/* 181 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public WmsLot getOldLotSer() {
/* 185 */     return this.oldLotSer;
/*     */   }
/*     */   
/*     */   public void setOldLotSer(WmsLot oldLotSer) {
/* 189 */     this.oldLotSer = oldLotSer;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/inventory/AbstractInventoryMoving.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
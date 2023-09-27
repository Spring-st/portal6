/*     */ package com.aof.model.comprehensive;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractProduceBuckleMaterial
/*     */   implements Serializable {
/*  13 */   private int hashValue = 0;
/*     */   
/*     */   private Integer id;
/*     */   private Site site;
/*     */   private String domain;
/*     */   private WmsPart part;
/*     */   private WmsPart assembly;
/*     */   private Bom bom_id;
/*     */   private StorageLocation location;
/*     */   private String code;
/*     */   private Date date;
/*     */   private BigDecimal qty;
/*     */   private YesNo is_sync;
/*     */   private Date is_sync_date;
/*     */   private String remark;
/*     */   private Integer growth;
/*     */   
/*     */   public Integer getGrowth() {
/*  31 */     return this.growth;
/*     */   }
/*     */   
/*     */   public void setGrowth(Integer growth) {
/*  35 */     this.growth = growth;
/*     */   }
/*     */   
/*     */   public WmsPart getAssembly() {
/*  39 */     return this.assembly;
/*     */   }
/*     */   
/*     */   public void setAssembly(WmsPart assembly) {
/*  43 */     this.assembly = assembly;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  47 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  51 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/*  55 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/*  59 */     this.site = site;
/*     */   }
/*     */   
/*     */   public WmsPart getPart() {
/*  63 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/*  67 */     this.part = part;
/*     */   }
/*     */   
/*     */   public Bom getBom_id() {
/*  71 */     return this.bom_id;
/*     */   }
/*     */   
/*     */   public void setBom_id(Bom bom_id) {
/*  75 */     this.bom_id = bom_id;
/*     */   }
/*     */   
/*     */   public StorageLocation getLocation() {
/*  79 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(StorageLocation location) {
/*  83 */     this.location = location;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  87 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  91 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getDomain() {
/*  95 */     return this.domain;
/*     */   }
/*     */   
/*     */   public void setDomain(String domain) {
/*  99 */     this.domain = domain;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/* 103 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/* 107 */     this.date = date;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/* 111 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/* 115 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public YesNo getIs_sync() {
/* 119 */     return this.is_sync;
/*     */   }
/*     */   
/*     */   public void setIs_sync(YesNo is_sync) {
/* 123 */     this.is_sync = is_sync;
/*     */   }
/*     */   
/*     */   public Date getIs_sync_date() {
/* 127 */     return this.is_sync_date;
/*     */   }
/*     */   
/*     */   public void setIs_sync_date(Date is_sync_date) {
/* 131 */     this.is_sync_date = is_sync_date;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/* 135 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 139 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractProduceBuckleMaterial() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractProduceBuckleMaterial(Integer id) {
/* 151 */     setId(id);
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
/* 162 */     if (rhs == null)
/* 163 */       return false; 
/* 164 */     if (this == rhs)
/* 165 */       return true; 
/* 166 */     if (!(rhs instanceof ProduceBuckleMaterial))
/* 167 */       return false; 
/* 168 */     ProduceBuckleMaterial that = (ProduceBuckleMaterial)rhs;
/* 169 */     if (getId() != null)
/* 170 */       return getId().equals(that.getId()); 
/* 171 */     return (that.getId() == null);
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
/* 182 */     if (this.hashValue == 0) {
/* 183 */       int result = 17;
/* 184 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 185 */       result = result * 37 + poIdValue;
/* 186 */       this.hashValue = result;
/*     */     } 
/* 188 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/comprehensive/AbstractProduceBuckleMaterial.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
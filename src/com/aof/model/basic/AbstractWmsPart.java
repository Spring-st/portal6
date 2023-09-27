/*     */ package com.aof.model.basic;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.PartType;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ 
/*     */ 
/*     */ public abstract class AbstractWmsPart
/*     */   implements Serializable
/*     */ {
/*  14 */   private int hashValue = 0;
/*     */   
/*     */   private String id;
/*     */   
/*     */   private Site site;
/*     */   private String domain;
/*     */   private String name;
/*     */   private String unit;
/*     */   private String describe1;
/*     */   private String describe2;
/*     */   private BigDecimal largest_inventory;
/*     */   private YesNo freeze_status;
/*     */   private String productClass;
/*     */   private String productLine;
/*     */   private Integer recommend_date;
/*     */   private EnabledDisabled enabled;
/*     */   private String date;
/*     */   private BigDecimal ord_mult;
/*     */   private String remark;
/*     */   private PartType type;
/*     */   private String partType;
/*     */   private String productSpecifications;
/*     */   private String vend;
/*     */   private BigDecimal highQty;
/*     */   private BigDecimal lowQty;
/*     */   private BigDecimal securityQty;
/*     */   private String drwgLoc;
/*     */   private String checked;
/*     */   private BasicPartPrice partPrice;
/*     */   private BigDecimal needQty;
/*     */   
/*     */   public String getChecked() {
/*  46 */     return this.checked;
/*     */   }
/*     */   
/*     */   public void setChecked(String checked) {
/*  50 */     this.checked = checked;
/*     */   }
/*     */   
/*     */   public PartType getType() {
/*  54 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(PartType type) {
/*  58 */     this.type = type;
/*     */   }
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
/*     */   public BigDecimal getOrd_mult() {
/*  71 */     return this.ord_mult;
/*     */   }
/*     */   
/*     */   public String getDate() {
/*  75 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(String date) {
/*  79 */     this.date = date;
/*     */   }
/*     */   
/*     */   public void setOrd_mult(BigDecimal ord_mult) {
/*  83 */     this.ord_mult = ord_mult;
/*     */   }
/*     */   
/*     */   public String getDomain() {
/*  87 */     return this.domain;
/*     */   }
/*     */   
/*     */   public void setDomain(String domain) {
/*  91 */     this.domain = domain;
/*     */   }
/*     */   
/*     */   public String getUnit() {
/*  95 */     return this.unit;
/*     */   }
/*     */   
/*     */   public void setUnit(String unit) {
/*  99 */     this.unit = unit;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/* 103 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/* 107 */     this.site = site;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 111 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 115 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getId() {
/* 119 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/* 123 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getDescribe1() {
/* 127 */     return this.describe1;
/*     */   }
/*     */   
/*     */   public void setDescribe1(String describe1) {
/* 131 */     this.describe1 = describe1;
/*     */   }
/*     */   
/*     */   public String getDescribe2() {
/* 135 */     return this.describe2;
/*     */   }
/*     */   
/*     */   public void setDescribe2(String describe2) {
/* 139 */     this.describe2 = describe2;
/*     */   }
/*     */   
/*     */   public BigDecimal getLargest_inventory() {
/* 143 */     return this.largest_inventory;
/*     */   }
/*     */   
/*     */   public void setLargest_inventory(BigDecimal largest_inventory) {
/* 147 */     this.largest_inventory = largest_inventory;
/*     */   }
/*     */   
/*     */   public YesNo getFreeze_status() {
/* 151 */     return this.freeze_status;
/*     */   }
/*     */   
/*     */   public void setFreeze_status(YesNo freeze_status) {
/* 155 */     this.freeze_status = freeze_status;
/*     */   }
/*     */   
/*     */   public String getProductClass() {
/* 159 */     return this.productClass;
/*     */   }
/*     */   
/*     */   public void setProductClass(String productClass) {
/* 163 */     this.productClass = productClass;
/*     */   }
/*     */   
/*     */   public Integer getRecommend_date() {
/* 167 */     return this.recommend_date;
/*     */   }
/*     */   
/*     */   public void setRecommend_date(Integer recommend_date) {
/* 171 */     this.recommend_date = recommend_date;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/* 175 */     return this.enabled;
/*     */   }
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 179 */     this.enabled = enabled;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/* 183 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 187 */     this.remark = remark;
/*     */   }
/*     */   
/*     */   public String getProductSpecifications() {
/* 191 */     return this.productSpecifications;
/*     */   }
/*     */   
/*     */   public void setProductSpecifications(String productSpecifications) {
/* 195 */     this.productSpecifications = productSpecifications;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractWmsPart() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractWmsPart(String id) {
/* 207 */     setId(id);
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
/* 218 */     if (rhs == null)
/* 219 */       return false; 
/* 220 */     if (this == rhs)
/* 221 */       return true; 
/* 222 */     if (!(rhs instanceof WmsPart))
/* 223 */       return false; 
/* 224 */     WmsPart that = (WmsPart)rhs;
/* 225 */     if (getId() != null)
/* 226 */       return getId().equals(that.getId()); 
/* 227 */     return (that.getId() == null);
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
/* 238 */     if (this.hashValue == 0) {
/* 239 */       int result = 17;
/* 240 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 241 */       result = result * 37 + poIdValue;
/* 242 */       this.hashValue = result;
/*     */     } 
/* 244 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public String getVend() {
/* 248 */     return this.vend;
/*     */   }
/*     */   
/*     */   public void setVend(String vend) {
/* 252 */     this.vend = vend;
/*     */   }
/*     */   
/*     */   public BasicPartPrice getPartPrice() {
/* 256 */     return this.partPrice;
/*     */   }
/*     */   
/*     */   public void setPartPrice(BasicPartPrice partPrice) {
/* 260 */     this.partPrice = partPrice;
/*     */   }
/*     */   
/*     */   public BigDecimal getHighQty() {
/* 264 */     return this.highQty;
/*     */   }
/*     */   
/*     */   public void setHighQty(BigDecimal highQty) {
/* 268 */     this.highQty = highQty;
/*     */   }
/*     */   
/*     */   public BigDecimal getLowQty() {
/* 272 */     return this.lowQty;
/*     */   }
/*     */   
/*     */   public void setLowQty(BigDecimal lowQty) {
/* 276 */     this.lowQty = lowQty;
/*     */   }
/*     */   
/*     */   public BigDecimal getNeedQty() {
/* 280 */     return this.needQty;
/*     */   }
/*     */   
/*     */   public void setNeedQty(BigDecimal needQty) {
/* 284 */     this.needQty = needQty;
/*     */   }
/*     */   
/*     */   public BigDecimal getSecurityQty() {
/* 288 */     return this.securityQty;
/*     */   }
/*     */   
/*     */   public void setSecurityQty(BigDecimal securityQty) {
/* 292 */     this.securityQty = securityQty;
/*     */   }
/*     */   
/*     */   public String getPartType() {
/* 296 */     return this.partType;
/*     */   }
/*     */   
/*     */   public void setPartType(String partType) {
/* 300 */     this.partType = partType;
/*     */   }
/*     */   
/*     */   public String getProductLine() {
/* 304 */     return this.productLine;
/*     */   }
/*     */   
/*     */   public void setProductLine(String productLine) {
/* 308 */     this.productLine = productLine;
/*     */   }
/*     */   
/*     */   public String getDrwgLoc() {
/* 312 */     return this.drwgLoc;
/*     */   }
/*     */   
/*     */   public void setDrwgLoc(String drwgLoc) {
/* 316 */     this.drwgLoc = drwgLoc;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractWmsPart.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.aof.model.basic;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ 
/*     */ public abstract class AbstractStorageLocation
/*     */   implements Serializable
/*     */ {
/*  12 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private StoreRoom basic_storeroom_id;
/*     */   private String code;
/*     */   private Site site;
/*     */   private String address;
/*     */   private String description;
/*     */   private BigDecimal max_inventory;
/*     */   private YesNo freeae_status;
/*     */   private YesNo recommend_status;
/*     */   private EnabledDisabled enabled;
/*     */   private String remark;
/*     */   private YesNo f_in_f_out_status;
/*  25 */   private BigDecimal number = new BigDecimal(0);
/*     */ 
/*     */   
/*     */   public BigDecimal getNumber() {
/*  29 */     return this.number;
/*     */   }
/*     */   
/*     */   public void setNumber(BigDecimal number) {
/*  33 */     this.number = number;
/*     */   }
/*     */   
/*     */   public YesNo getF_in_f_out_status() {
/*  37 */     return this.f_in_f_out_status;
/*     */   }
/*     */   
/*     */   public void setF_in_f_out_status(YesNo f_in_f_out_status) {
/*  41 */     this.f_in_f_out_status = f_in_f_out_status;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/*  45 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/*  49 */     this.site = site;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/*  53 */     return this.enabled;
/*     */   }
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/*  57 */     this.enabled = enabled;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  61 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  65 */     this.id = id;
/*     */   }
/*     */   
/*     */   public StoreRoom getBasic_storeroom_id() {
/*  69 */     return this.basic_storeroom_id;
/*     */   }
/*     */   
/*     */   public void setBasic_storeroom_id(StoreRoom basic_storeroom_id) {
/*  73 */     this.basic_storeroom_id = basic_storeroom_id;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  77 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  81 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getAddress() {
/*  85 */     return this.address;
/*     */   }
/*     */   
/*     */   public void setAddress(String address) {
/*  89 */     this.address = address;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/*  93 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/*  97 */     this.description = description;
/*     */   }
/*     */   
/*     */   public BigDecimal getMax_inventory() {
/* 101 */     return this.max_inventory;
/*     */   }
/*     */   
/*     */   public void setMax_inventory(BigDecimal max_inventory) {
/* 105 */     this.max_inventory = max_inventory;
/*     */   }
/*     */   
/*     */   public YesNo getFreeae_status() {
/* 109 */     return this.freeae_status;
/*     */   }
/*     */   
/*     */   public void setFreeae_status(YesNo freeae_status) {
/* 113 */     this.freeae_status = freeae_status;
/*     */   }
/*     */   
/*     */   public YesNo getRecommend_status() {
/* 117 */     return this.recommend_status;
/*     */   }
/*     */   
/*     */   public void setRecommend_status(YesNo recommend_status) {
/* 121 */     this.recommend_status = recommend_status;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/* 125 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 129 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractStorageLocation() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractStorageLocation(Integer id) {
/* 141 */     setId(id);
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
/* 152 */     if (rhs == null)
/* 153 */       return false; 
/* 154 */     if (this == rhs)
/* 155 */       return true; 
/* 156 */     if (!(rhs instanceof StorageLocation))
/* 157 */       return false; 
/* 158 */     StorageLocation that = (StorageLocation)rhs;
/* 159 */     if (getId() != null)
/* 160 */       return getId().equals(that.getId()); 
/* 161 */     return (that.getId() == null);
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
/* 172 */     if (this.hashValue == 0) {
/* 173 */       int result = 17;
/* 174 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 175 */       result = result * 37 + poIdValue;
/* 176 */       this.hashValue = result;
/*     */     } 
/* 178 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractStorageLocation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
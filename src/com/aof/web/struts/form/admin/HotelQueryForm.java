/*     */ package com.aof.web.struts.form.admin;
/*     */ 
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
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
/*     */ public class HotelQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private String id;
/*     */   private String site_id;
/*     */   private String currency_code;
/*     */   private String country_id;
/*     */   private String province_id;
/*     */   private String city_id;
/*     */   private String name;
/*     */   private String address;
/*     */   private String description;
/*     */   private String telephone;
/*     */   private String fax;
/*     */   private String level;
/*     */   private String contractStartDate;
/*     */   private String contractExpireDate;
/*     */   private String promoteStatus;
/*     */   private String enabled;
/*     */   
/*     */   public String getEnabled() {
/*  56 */     return this.enabled;
/*     */   }
/*     */   
/*     */   public void setEnabled(String enabled) {
/*  60 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAddress() {
/*  67 */     return this.address;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAddress(String address) {
/*  75 */     this.address = address;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCity_id() {
/*  82 */     return this.city_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCity_id(String city_id) {
/*  90 */     this.city_id = city_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContractExpireDate() {
/*  97 */     return this.contractExpireDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContractExpireDate(String contractExpireDate) {
/* 105 */     this.contractExpireDate = contractExpireDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContractStartDate() {
/* 112 */     return this.contractStartDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContractStartDate(String contractStartDate) {
/* 120 */     this.contractStartDate = contractStartDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCountry_id() {
/* 127 */     return this.country_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCountry_id(String country_id) {
/* 135 */     this.country_id = country_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCurrency_code() {
/* 142 */     return this.currency_code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrency_code(String currency_code) {
/* 150 */     this.currency_code = currency_code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 157 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/* 165 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFax() {
/* 172 */     return this.fax;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFax(String fax) {
/* 180 */     this.fax = fax;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/* 187 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(String id) {
/* 195 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLevel() {
/* 203 */     return this.level;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLevel(String level) {
/* 211 */     this.level = level;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 218 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 226 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProvince_id() {
/* 233 */     return this.province_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProvince_id(String province_id) {
/* 241 */     this.province_id = province_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSite_id() {
/* 248 */     return this.site_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite_id(String site_id) {
/* 256 */     this.site_id = site_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTelephone() {
/* 263 */     return this.telephone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTelephone(String telephone) {
/* 271 */     this.telephone = telephone;
/*     */   }
/*     */   
/*     */   public String getPromoteStatus() {
/* 275 */     return this.promoteStatus;
/*     */   }
/*     */   
/*     */   public void setPromoteStatus(String promoteStatus) {
/* 279 */     this.promoteStatus = promoteStatus;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/HotelQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
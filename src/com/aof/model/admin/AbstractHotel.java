/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.HotelLevel;
/*     */ import com.aof.model.metadata.HotelPromoteStatus;
/*     */ import java.io.Serializable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractHotel
/*     */   implements Serializable
/*     */ {
/*  34 */   private int hashValue = 0;
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */   
/*     */   private Currency currency;
/*     */ 
/*     */   
/*     */   private City city;
/*     */ 
/*     */   
/*     */   private Site site;
/*     */ 
/*     */   
/*     */   private String name;
/*     */ 
/*     */   
/*     */   private String address;
/*     */ 
/*     */   
/*     */   private String description;
/*     */ 
/*     */   
/*     */   private String telephone;
/*     */ 
/*     */   
/*     */   private String fax;
/*     */ 
/*     */   
/*     */   private HotelLevel level;
/*     */ 
/*     */   
/*     */   private Date contractStartDate;
/*     */ 
/*     */   
/*     */   private Date contractExpireDate;
/*     */ 
/*     */   
/*     */   private HotelPromoteStatus promoteStatus;
/*     */ 
/*     */   
/*     */   private String promoteMessage;
/*     */ 
/*     */   
/*     */   private Date promoteDate;
/*     */ 
/*     */   
/*     */   private EnabledDisabled enabled;
/*     */ 
/*     */   
/*     */   private Date emailDate;
/*     */   
/*  87 */   private int emailTimes = 0;
/*     */   
/*     */   private String email;
/*     */   
/*     */   private String contact;
/*     */   
/*     */   private String specialService;
/*     */   
/*     */   public String getContact() {
/*  96 */     return this.contact;
/*     */   }
/*     */   
/*     */   public void setContact(String contact) {
/* 100 */     this.contact = contact;
/*     */   }
/*     */   
/*     */   public String getEmail() {
/* 104 */     return this.email;
/*     */   }
/*     */   
/*     */   public void setEmail(String email) {
/* 108 */     this.email = email;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractHotel() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractHotel(Integer id) {
/* 124 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getEmailDate() {
/* 132 */     return this.emailDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEmailDate(Date emailDate) {
/* 139 */     this.emailDate = emailDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEmailTimes() {
/* 146 */     return this.emailTimes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEmailTimes(int emailTimes) {
/* 153 */     this.emailTimes = emailTimes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/* 162 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/* 171 */     this.hashValue = 0;
/* 172 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAddress() {
/* 181 */     return this.address;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAddress(String address) {
/* 190 */     this.address = address;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 200 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/* 209 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFax() {
/* 219 */     return this.fax;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFax(String fax) {
/* 228 */     this.fax = fax;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HotelPromoteStatus getPromoteStatus() {
/* 238 */     return this.promoteStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPromoteStatus(HotelPromoteStatus promoteStatus) {
/* 247 */     this.promoteStatus = promoteStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPromoteMessage() {
/* 256 */     return this.promoteMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPromoteMessage(String promoteMessage) {
/* 265 */     this.promoteMessage = promoteMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getPromoteDate() {
/* 274 */     return this.promoteDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPromoteDate(Date promoteDate) {
/* 283 */     this.promoteDate = promoteDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public City getCity() {
/* 290 */     return this.city;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCity(City city) {
/* 297 */     this.city = city;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getContractExpireDate() {
/* 304 */     return this.contractExpireDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContractExpireDate(Date contractExpireDate) {
/* 311 */     this.contractExpireDate = contractExpireDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getContractStartDate() {
/* 318 */     return this.contractStartDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContractStartDate(Date contractStartDate) {
/* 325 */     this.contractStartDate = contractStartDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Currency getCurrency() {
/* 332 */     return this.currency;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrency(Currency currency) {
/* 339 */     this.currency = currency;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/* 346 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 353 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HotelLevel getLevel() {
/* 360 */     return this.level;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLevel(HotelLevel level) {
/* 367 */     this.level = level;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 374 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 381 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site getSite() {
/* 388 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(Site site) {
/* 395 */     this.site = site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTelephone() {
/* 402 */     return this.telephone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTelephone(String telephone) {
/* 409 */     this.telephone = telephone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 419 */     if (rhs == null) return false; 
/* 420 */     if (this == rhs) return true; 
/* 421 */     if (!(rhs instanceof Hotel)) return false; 
/* 422 */     Hotel that = (Hotel)rhs;
/* 423 */     if (getId() != null) return getId().equals(that.getId()); 
/* 424 */     return (that.getId() == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 434 */     if (this.hashValue == 0) {
/*     */       
/* 436 */       int result = 17;
/* 437 */       int hotelIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 438 */       result = result * 37 + hotelIdValue;
/* 439 */       this.hashValue = result;
/*     */     } 
/* 441 */     return this.hashValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSpecialService() {
/* 448 */     return this.specialService;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpecialService(String specialService) {
/* 455 */     this.specialService = specialService;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractHotel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
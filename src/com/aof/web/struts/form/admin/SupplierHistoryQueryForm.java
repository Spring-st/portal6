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
/*     */ public class SupplierHistoryQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private String id;
/*     */   private String site_id;
/*     */   private String currency_code;
/*     */   private String country_id;
/*     */   private String city_id;
/*     */   private String name;
/*     */   private String address1;
/*     */   private String address3;
/*     */   private String address2;
/*     */   private String post;
/*     */   private String attention1;
/*     */   private String attention2;
/*     */   private String telephone1;
/*     */   private String telephone2;
/*     */   private String ext1;
/*     */   private String ext2;
/*     */   private String fax1;
/*     */   private String fax2;
/*     */   private String purchaseAccount;
/*     */   private String purchaseSubAccount;
/*     */   private String purchaseCostCenter;
/*     */   private String apAccount;
/*     */   private String apSubaccount;
/*     */   private String apCostCenter;
/*     */   private String bank;
/*     */   private String creditTerms;
/*     */   private String contact;
/*     */   private String contractStartDate;
/*     */   private String contractExpireDate;
/*     */   private String enabled;
/*     */   private String airTicket;
/*     */   private String promoteStatus;
/*     */   
/*     */   public String getAddress1() {
/*  88 */     return this.address1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAddress1(String address1) {
/*  95 */     this.address1 = address1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAddress2() {
/* 102 */     return this.address2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAddress2(String address2) {
/* 109 */     this.address2 = address2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAddress3() {
/* 116 */     return this.address3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAddress3(String address3) {
/* 123 */     this.address3 = address3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAirTicket() {
/* 130 */     return this.airTicket;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAirTicket(String airTicket) {
/* 137 */     this.airTicket = airTicket;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApAccount() {
/* 144 */     return this.apAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApAccount(String apAccount) {
/* 151 */     this.apAccount = apAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApCostCenter() {
/* 158 */     return this.apCostCenter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApCostCenter(String apCostCenter) {
/* 165 */     this.apCostCenter = apCostCenter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApSubaccount() {
/* 172 */     return this.apSubaccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApSubaccount(String apSubaccount) {
/* 179 */     this.apSubaccount = apSubaccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAttention1() {
/* 186 */     return this.attention1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttention1(String attention1) {
/* 193 */     this.attention1 = attention1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAttention2() {
/* 200 */     return this.attention2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttention2(String attention2) {
/* 207 */     this.attention2 = attention2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBank() {
/* 214 */     return this.bank;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBank(String bank) {
/* 221 */     this.bank = bank;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCity_id() {
/* 228 */     return this.city_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCity_id(String city_id) {
/* 235 */     this.city_id = city_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContact() {
/* 242 */     return this.contact;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContact(String contact) {
/* 249 */     this.contact = contact;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContractExpireDate() {
/* 256 */     return this.contractExpireDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContractExpireDate(String contractExpireDate) {
/* 263 */     this.contractExpireDate = contractExpireDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContractStartDate() {
/* 270 */     return this.contractStartDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContractStartDate(String contractStartDate) {
/* 277 */     this.contractStartDate = contractStartDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCountry_id() {
/* 284 */     return this.country_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCountry_id(String country_id) {
/* 291 */     this.country_id = country_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCreditTerms() {
/* 298 */     return this.creditTerms;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreditTerms(String creditTerms) {
/* 305 */     this.creditTerms = creditTerms;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCurrency_code() {
/* 312 */     return this.currency_code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrency_code(String currency_code) {
/* 319 */     this.currency_code = currency_code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEnabled() {
/* 326 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(String enabled) {
/* 333 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExt1() {
/* 340 */     return this.ext1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExt1(String ext1) {
/* 347 */     this.ext1 = ext1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExt2() {
/* 354 */     return this.ext2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExt2(String ext2) {
/* 361 */     this.ext2 = ext2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFax1() {
/* 368 */     return this.fax1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFax1(String fax1) {
/* 375 */     this.fax1 = fax1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFax2() {
/* 382 */     return this.fax2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFax2(String fax2) {
/* 389 */     this.fax2 = fax2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/* 396 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(String id) {
/* 403 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 410 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 417 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPost() {
/* 424 */     return this.post;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPost(String post) {
/* 431 */     this.post = post;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPromoteStatus() {
/* 438 */     return this.promoteStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPromoteStatus(String promoteStatus) {
/* 445 */     this.promoteStatus = promoteStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPurchaseAccount() {
/* 452 */     return this.purchaseAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPurchaseAccount(String purchaseAccount) {
/* 459 */     this.purchaseAccount = purchaseAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPurchaseCostCenter() {
/* 466 */     return this.purchaseCostCenter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPurchaseCostCenter(String purchaseCostCenter) {
/* 473 */     this.purchaseCostCenter = purchaseCostCenter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPurchaseSubAccount() {
/* 480 */     return this.purchaseSubAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPurchaseSubAccount(String purchaseSubAccount) {
/* 487 */     this.purchaseSubAccount = purchaseSubAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSite_id() {
/* 494 */     return this.site_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite_id(String site_id) {
/* 501 */     this.site_id = site_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTelephone1() {
/* 508 */     return this.telephone1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTelephone1(String telephone1) {
/* 515 */     this.telephone1 = telephone1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTelephone2() {
/* 522 */     return this.telephone2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTelephone2(String telephone2) {
/* 529 */     this.telephone2 = telephone2;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/SupplierHistoryQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
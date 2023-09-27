/*     */ package com.aof.web.struts.form.admin;
/*     */ 
/*     */ import com.aof.model.admin.query.SupplierQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SupplierQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private String id;
/*     */   private String code;
/*     */   private boolean confirmed;
/*     */   private String promoteStatus;
/*     */   private boolean includeGlobal;
/*     */   private boolean includeDisabled;
/*     */   private String confirmType;
/*     */   private String contractStatus;
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
/*     */   private String apSubAccount;
/*     */   private String apCostCenter;
/*     */   private String bank;
/*     */   private String creditTerms;
/*     */   private String contact;
/*     */   private String contractStartDate;
/*     */   private String contractExpireDate;
/*     */   private String enabled;
/*     */   private String draft;
/*     */   private String exportStatus;
/*     */   private String lastExportFile;
/*     */   private boolean confirmGlobal;
/*     */   
/*     */   public boolean isConfirmGlobal() {
/* 108 */     return this.confirmGlobal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConfirmGlobal(boolean confirmGlobal) {
/* 116 */     this.confirmGlobal = confirmGlobal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPromoteStatus() {
/* 123 */     return this.promoteStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPromoteStatus(String promoteStatus) {
/* 131 */     this.promoteStatus = promoteStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIncludeDisabled() {
/* 138 */     return this.includeDisabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIncludeDisabled(boolean includeDisabled) {
/* 146 */     this.includeDisabled = includeDisabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConfirmed() {
/* 153 */     return this.confirmed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConfirmed(boolean confirmed) {
/* 161 */     this.confirmed = confirmed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIncludeGlobal() {
/* 168 */     return this.includeGlobal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIncludeGlobal(boolean includeGlobal) {
/* 176 */     this.includeGlobal = includeGlobal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getConfirmType() {
/* 183 */     return this.confirmType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConfirmType(String confirmType) {
/* 191 */     this.confirmType = confirmType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContractStatus() {
/* 198 */     return this.contractStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContractStatus(String contractStatus) {
/* 206 */     this.contractStatus = contractStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAddress1() {
/* 213 */     return this.address1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAddress1(String address1) {
/* 220 */     this.address1 = address1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAddress2() {
/* 227 */     return this.address2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAddress2(String address2) {
/* 234 */     this.address2 = address2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAddress3() {
/* 241 */     return this.address3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAddress3(String address3) {
/* 248 */     this.address3 = address3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApAccount() {
/* 255 */     return this.apAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApAccount(String apAccount) {
/* 262 */     this.apAccount = apAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApCostCenter() {
/* 269 */     return this.apCostCenter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApCostCenter(String apCostCenter) {
/* 276 */     this.apCostCenter = apCostCenter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApSubAccount() {
/* 283 */     return this.apSubAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApSubAccount(String apSubAccount) {
/* 290 */     this.apSubAccount = apSubAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAttention1() {
/* 297 */     return this.attention1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttention1(String attention1) {
/* 304 */     this.attention1 = attention1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAttention2() {
/* 311 */     return this.attention2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttention2(String attention2) {
/* 318 */     this.attention2 = attention2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBank() {
/* 325 */     return this.bank;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBank(String bank) {
/* 332 */     this.bank = bank;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCity_id() {
/* 339 */     return this.city_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCity_id(String city_id) {
/* 346 */     this.city_id = city_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContact() {
/* 353 */     return this.contact;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContact(String contact) {
/* 360 */     this.contact = contact;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContractExpireDate() {
/* 367 */     return this.contractExpireDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContractExpireDate(String contractExpireDate) {
/* 374 */     this.contractExpireDate = contractExpireDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContractStartDate() {
/* 381 */     return this.contractStartDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContractStartDate(String contractStartDate) {
/* 388 */     this.contractStartDate = contractStartDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCountry_id() {
/* 395 */     return this.country_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCountry_id(String country_id) {
/* 402 */     this.country_id = country_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCreditTerms() {
/* 409 */     return this.creditTerms;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreditTerms(String creditTerms) {
/* 416 */     this.creditTerms = creditTerms;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCurrency_code() {
/* 423 */     return this.currency_code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrency_code(String currency_code) {
/* 430 */     this.currency_code = currency_code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDraft() {
/* 437 */     return this.draft;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDraft(String draft) {
/* 444 */     this.draft = draft;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEnabled() {
/* 451 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(String enabled) {
/* 458 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExportStatus() {
/* 465 */     return this.exportStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExportStatus(String exportStatus) {
/* 472 */     this.exportStatus = exportStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExt1() {
/* 479 */     return this.ext1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExt1(String ext1) {
/* 486 */     this.ext1 = ext1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExt2() {
/* 493 */     return this.ext2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExt2(String ext2) {
/* 500 */     this.ext2 = ext2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFax1() {
/* 507 */     return this.fax1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFax1(String fax1) {
/* 514 */     this.fax1 = fax1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFax2() {
/* 521 */     return this.fax2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFax2(String fax2) {
/* 528 */     this.fax2 = fax2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/* 535 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(String id) {
/* 542 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLastExportFile() {
/* 549 */     return this.lastExportFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLastExportFile(String lastExportFile) {
/* 556 */     this.lastExportFile = lastExportFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 563 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 570 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPost() {
/* 577 */     return this.post;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPost(String post) {
/* 584 */     this.post = post;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPurchaseAccount() {
/* 591 */     return this.purchaseAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPurchaseAccount(String purchaseAccount) {
/* 598 */     this.purchaseAccount = purchaseAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPurchaseCostCenter() {
/* 605 */     return this.purchaseCostCenter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPurchaseCostCenter(String purchaseCostCenter) {
/* 612 */     this.purchaseCostCenter = purchaseCostCenter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPurchaseSubAccount() {
/* 619 */     return this.purchaseSubAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPurchaseSubAccount(String purchaseSubAccount) {
/* 626 */     this.purchaseSubAccount = purchaseSubAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSite_id() {
/* 633 */     return this.site_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite_id(String site_id) {
/* 640 */     this.site_id = site_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTelephone1() {
/* 647 */     return this.telephone1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTelephone1(String telephone1) {
/* 654 */     this.telephone1 = telephone1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTelephone2() {
/* 661 */     return this.telephone2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTelephone2(String telephone2) {
/* 668 */     this.telephone2 = telephone2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCode() {
/* 675 */     return this.code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/* 682 */     this.code = code;
/*     */   }
/*     */ 
/*     */   
/*     */   public SupplierQueryForm() {
/* 687 */     setEnabled(EnabledDisabled.ENABLED.getEnumCode().toString());
/* 688 */     setOrder(SupplierQueryOrder.CODE.getName());
/* 689 */     setDescend(false);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/SupplierQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
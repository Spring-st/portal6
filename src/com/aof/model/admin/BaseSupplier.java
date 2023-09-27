/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.SupplierPromoteStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
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
/*     */ public abstract class BaseSupplier
/*     */ {
/*  30 */   private int hashValue = 0;
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */   
/*     */   private String code;
/*     */ 
/*     */   
/*     */   private Site site;
/*     */ 
/*     */   
/*     */   private Currency currency;
/*     */ 
/*     */   
/*     */   private String name;
/*     */ 
/*     */   
/*     */   private String address1;
/*     */ 
/*     */   
/*     */   private String address2;
/*     */ 
/*     */   
/*     */   private String address3;
/*     */ 
/*     */   
/*     */   private City city;
/*     */ 
/*     */   
/*     */   private String post;
/*     */ 
/*     */   
/*     */   private String attention1;
/*     */ 
/*     */   
/*     */   private String attention2;
/*     */ 
/*     */   
/*     */   private String telephone1;
/*     */ 
/*     */   
/*     */   private String ext1;
/*     */ 
/*     */   
/*     */   private String telephone2;
/*     */ 
/*     */   
/*     */   private String ext2;
/*     */ 
/*     */   
/*     */   private String fax1;
/*     */ 
/*     */   
/*     */   private String fax2;
/*     */ 
/*     */   
/*     */   private String purchaseAccount;
/*     */ 
/*     */   
/*     */   private String purchaseSubAccount;
/*     */ 
/*     */   
/*     */   private String purchaseCostCenter;
/*     */ 
/*     */   
/*     */   private String apAccount;
/*     */ 
/*     */   
/*     */   private String apSubAccount;
/*     */ 
/*     */   
/*     */   private String apCostCenter;
/*     */ 
/*     */   
/*     */   private String bank;
/*     */ 
/*     */   
/*     */   private String creditTerms;
/*     */ 
/*     */   
/*     */   private String contact;
/*     */ 
/*     */   
/*     */   private Date contractStartDate;
/*     */ 
/*     */   
/*     */   private Date contractExpireDate;
/*     */ 
/*     */   
/*     */   private EnabledDisabled enabled;
/*     */ 
/*     */   
/*     */   private YesNo airTicket;
/*     */ 
/*     */   
/*     */   private SupplierPromoteStatus promoteStatus;
/*     */ 
/*     */   
/*     */   private Date confirmDate;
/*     */ 
/*     */   
/*     */   private Date promoteDate;
/*     */ 
/*     */   
/*     */   private Country country;
/*     */ 
/*     */ 
/*     */   
/*     */   public BaseSupplier() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public BaseSupplier(Integer id) {
/* 144 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public YesNo getAirTicket() {
/* 151 */     return this.airTicket;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAirTicket(YesNo airTicket) {
/* 159 */     this.airTicket = airTicket;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SupplierPromoteStatus getPromoteStatus() {
/* 166 */     return this.promoteStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPromoteStatus(SupplierPromoteStatus promoteStatus) {
/* 174 */     this.promoteStatus = promoteStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAddress1() {
/* 181 */     return this.address1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAddress1(String address1) {
/* 189 */     this.address1 = address1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAddress2() {
/* 196 */     return this.address2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAddress2(String address2) {
/* 204 */     this.address2 = address2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAddress3() {
/* 211 */     return this.address3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAddress3(String address3) {
/* 219 */     this.address3 = address3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApAccount() {
/* 226 */     return this.apAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApAccount(String apAccount) {
/* 234 */     this.apAccount = apAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApCostCenter() {
/* 241 */     return this.apCostCenter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApCostCenter(String apCostCenter) {
/* 249 */     this.apCostCenter = apCostCenter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApSubAccount() {
/* 256 */     return this.apSubAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApSubAccount(String apSubaccount) {
/* 264 */     this.apSubAccount = apSubaccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAttention1() {
/* 271 */     return this.attention1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttention1(String attention1) {
/* 279 */     this.attention1 = attention1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAttention2() {
/* 286 */     return this.attention2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttention2(String attention2) {
/* 294 */     this.attention2 = attention2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBank() {
/* 301 */     return this.bank;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBank(String bank) {
/* 309 */     this.bank = bank;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public City getCity() {
/* 316 */     return this.city;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCity(City city) {
/* 324 */     this.city = city;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContact() {
/* 331 */     return this.contact;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContact(String contact) {
/* 339 */     this.contact = contact;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getContractExpireDate() {
/* 346 */     return this.contractExpireDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContractExpireDate(Date contractExpireDate) {
/* 354 */     this.contractExpireDate = contractExpireDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getContractStartDate() {
/* 361 */     return this.contractStartDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContractStartDate(Date contractStartDate) {
/* 369 */     this.contractStartDate = contractStartDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCreditTerms() {
/* 376 */     return this.creditTerms;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreditTerms(String creditTerms) {
/* 384 */     this.creditTerms = creditTerms;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Currency getCurrency() {
/* 391 */     return this.currency;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrency(Currency currency) {
/* 399 */     this.currency = currency;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/* 406 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 414 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExt1() {
/* 421 */     return this.ext1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExt1(String ext1) {
/* 429 */     this.ext1 = ext1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExt2() {
/* 436 */     return this.ext2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExt2(String ext2) {
/* 444 */     this.ext2 = ext2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFax1() {
/* 451 */     return this.fax1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFax1(String fax1) {
/* 459 */     this.fax1 = fax1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFax2() {
/* 466 */     return this.fax2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFax2(String fax2) {
/* 474 */     this.fax2 = fax2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/* 481 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/* 489 */     this.hashValue = 0;
/* 490 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCode() {
/* 497 */     return this.code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/* 504 */     this.code = code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 511 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 519 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPost() {
/* 526 */     return this.post;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPost(String post) {
/* 534 */     this.post = post;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPurchaseAccount() {
/* 541 */     return this.purchaseAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPurchaseAccount(String purchaseAccount) {
/* 549 */     this.purchaseAccount = purchaseAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPurchaseCostCenter() {
/* 556 */     return this.purchaseCostCenter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPurchaseCostCenter(String purchaseCostCenter) {
/* 564 */     this.purchaseCostCenter = purchaseCostCenter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPurchaseSubAccount() {
/* 571 */     return this.purchaseSubAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPurchaseSubAccount(String purchaseSubAccount) {
/* 579 */     this.purchaseSubAccount = purchaseSubAccount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site getSite() {
/* 586 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(Site site) {
/* 594 */     this.site = site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTelephone1() {
/* 601 */     return this.telephone1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTelephone1(String telephone1) {
/* 609 */     this.telephone1 = telephone1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTelephone2() {
/* 616 */     return this.telephone2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTelephone2(String telephone2) {
/* 624 */     this.telephone2 = telephone2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getConfirmDate() {
/* 631 */     return this.confirmDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConfirmDate(Date confirmDate) {
/* 638 */     this.confirmDate = confirmDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getPromoteDate() {
/* 646 */     return this.promoteDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPromoteDate(Date promoteDate) {
/* 653 */     this.promoteDate = promoteDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean equals(Object paramObject);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 672 */     if (this.hashValue == 0) {
/* 673 */       int result = 17;
/* 674 */       int idValue = (getId() == null) ? 0 : getId().hashCode();
/* 675 */       result = result * 37 + idValue;
/* 676 */       this.hashValue = result;
/*     */     } 
/* 678 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public void copySupplier(BaseSupplier copySupplier) {
/*     */     try {
/* 683 */       PropertyUtils.copyProperties(this, copySupplier);
/* 684 */     } catch (Exception e) {
/* 685 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contentEqual(Supplier that) {
/* 691 */     if (!getId().equals(that.getId())) return false; 
/* 692 */     if (getSite() != null)
/* 693 */     { if (!getSite().equals(that.getSite())) return false;
/*     */        }
/* 695 */     else if (that.getSite() != null) { return false; }
/*     */     
/* 697 */     if (getName() != null)
/* 698 */     { if (!getName().equals(that.getName())) return false;
/*     */        }
/* 700 */     else if (that.getName() != null) { return false; }
/*     */     
/* 702 */     if (getAddress1() != null)
/* 703 */     { if (!getAddress1().equals(that.getAddress1())) return false;
/*     */        }
/* 705 */     else if (that.getAddress1() != null) { return false; }
/*     */     
/* 707 */     if (getApAccount() != null)
/* 708 */     { if (!getApAccount().equals(that.getApAccount())) return false;
/*     */        }
/* 710 */     else if (that.getApAccount() != null) { return false; }
/*     */     
/* 712 */     if (getApCostCenter() != null)
/* 713 */     { if (!getApCostCenter().equals(that.getApCostCenter())) return false;
/*     */        }
/* 715 */     else if (that.getApCostCenter() != null) { return false; }
/*     */     
/* 717 */     if (getApSubAccount() != null)
/* 718 */     { if (!getApSubAccount().equals(that.getApSubAccount())) return false;
/*     */        }
/* 720 */     else if (that.getApSubAccount() != null) { return false; }
/*     */     
/* 722 */     if (getBank() != null)
/* 723 */     { if (!getBank().equals(that.getBank())) return false;
/*     */        }
/* 725 */     else if (that.getBank() != null) { return false; }
/*     */     
/* 727 */     if (getCity() != null)
/* 728 */     { if (!getCity().equals(that.getCity())) return false;
/*     */        }
/* 730 */     else if (that.getCity() != null) { return false; }
/*     */     
/* 732 */     if (getCountry() != null)
/* 733 */     { if (!getCountry().equals(that.getCountry())) return false;
/*     */        }
/* 735 */     else if (that.getCountry() != null) { return false; }
/*     */     
/* 737 */     if (getContact() != null)
/* 738 */     { if (!getContact().equals(that.getContact())) return false;
/*     */        }
/* 740 */     else if (that.getContact() != null) { return false; }
/*     */     
/* 742 */     if (getContractExpireDate() != null)
/* 743 */     { if (!getContractExpireDate().equals(that.getContractExpireDate())) return false;
/*     */        }
/* 745 */     else if (that.getContractExpireDate() != null) { return false; }
/*     */     
/* 747 */     if (getContractStartDate() != null)
/* 748 */     { if (!getContractStartDate().equals(that.getContractStartDate())) return false;
/*     */        }
/* 750 */     else if (that.getContractStartDate() != null) { return false; }
/*     */     
/* 752 */     if (getCurrency() != null)
/* 753 */     { if (!getCurrency().equals(that.getCurrency())) return false;
/*     */        }
/* 755 */     else if (that.getCurrency() != null) { return false; }
/*     */     
/* 757 */     if (getTelephone1() != null)
/* 758 */     { if (!getTelephone1().equals(that.getTelephone1())) return false;
/*     */        }
/* 760 */     else if (that.getTelephone1() != null) { return false; }
/*     */     
/* 762 */     if (getFax1() != null)
/* 763 */     { if (!getFax1().equals(that.getFax1())) return false;
/*     */        }
/* 765 */     else if (that.getFax1() != null) { return false; }
/*     */     
/* 767 */     if (getPost() != null)
/* 768 */     { if (!getPost().equals(that.getPost())) return false;
/*     */        }
/* 770 */     else if (that.getPost() != null) { return false; }
/*     */     
/* 772 */     if (getPurchaseAccount() != null)
/* 773 */     { if (!getPurchaseAccount().equals(that.getPurchaseAccount())) return false;
/*     */        }
/* 775 */     else if (that.getPurchaseAccount() != null) { return false; }
/*     */     
/* 777 */     if (getPurchaseCostCenter() != null)
/* 778 */     { if (!getPurchaseCostCenter().equals(that.getPurchaseCostCenter())) return false;
/*     */        }
/* 780 */     else if (that.getPurchaseCostCenter() != null) { return false; }
/*     */     
/* 782 */     if (getPurchaseSubAccount() != null)
/* 783 */     { if (!getPurchaseSubAccount().equals(that.getPurchaseSubAccount())) return false;
/*     */        }
/* 785 */     else if (that.getPurchaseSubAccount() != null) { return false; }
/*     */     
/* 787 */     if (getEnabled() != null)
/* 788 */     { if (!getEnabled().equals(that.getEnabled())) return false;
/*     */        }
/* 790 */     else if (that.getEnabled() != null) { return false; }
/*     */     
/* 792 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Country getCountry() {
/* 799 */     return this.country;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCountry(Country country) {
/* 806 */     this.country = country;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/BaseSupplier.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
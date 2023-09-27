/*     */ package com.aof.model.product;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ public abstract class AbstractBasicCustomer
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private String code;
/*     */   private String name1;
/*     */   private String name2;
/*     */   private String address;
/*     */   private String contacts;
/*     */   private String phone;
/*     */   private String email;
/*     */   private String product;
/*     */   private String remarks;
/*     */   private EnabledDisabled enabled;
/*     */   private String address2;
/*     */   private String address3;
/*     */   private String country;
/*     */   private String city;
/*     */   private String fax;
/*     */   private String postId;
/*     */   private String currencyType;
/*     */   private String domain;
/*     */   private String site;
/*     */   private String seqNo;
/*     */   
/*     */   public AbstractBasicCustomer() {}
/*     */   
/*     */   public AbstractBasicCustomer(Integer id) {
/*  35 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  39 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  43 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  47 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  51 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getName1() {
/*  55 */     return this.name1;
/*     */   }
/*     */   
/*     */   public void setName1(String name1) {
/*  59 */     this.name1 = name1;
/*     */   }
/*     */   
/*     */   public String getName2() {
/*  63 */     return this.name2;
/*     */   }
/*     */   
/*     */   public void setName2(String name2) {
/*  67 */     this.name2 = name2;
/*     */   }
/*     */   
/*     */   public String getAddress() {
/*  71 */     return this.address;
/*     */   }
/*     */   
/*     */   public void setAddress(String address) {
/*  75 */     this.address = address;
/*     */   }
/*     */   
/*     */   public String getContacts() {
/*  79 */     return this.contacts;
/*     */   }
/*     */   
/*     */   public void setContacts(String contacts) {
/*  83 */     this.contacts = contacts;
/*     */   }
/*     */   
/*     */   public String getPhone() {
/*  87 */     return this.phone;
/*     */   }
/*     */   
/*     */   public void setPhone(String phone) {
/*  91 */     this.phone = phone;
/*     */   }
/*     */   
/*     */   public String getEmail() {
/*  95 */     return this.email;
/*     */   }
/*     */   
/*     */   public void setEmail(String email) {
/*  99 */     this.email = email;
/*     */   }
/*     */   
/*     */   public String getProduct() {
/* 103 */     return this.product;
/*     */   }
/*     */   
/*     */   public void setProduct(String product) {
/* 107 */     this.product = product;
/*     */   }
/*     */   
/*     */   public String getRemarks() {
/* 111 */     return this.remarks;
/*     */   }
/*     */   
/*     */   public void setRemarks(String remarks) {
/* 115 */     this.remarks = remarks;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/* 119 */     return this.enabled;
/*     */   }
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 123 */     this.enabled = enabled;
/*     */   }
/*     */   
/*     */   public String getAddress2() {
/* 127 */     return this.address2;
/*     */   }
/*     */   
/*     */   public void setAddress2(String address2) {
/* 131 */     this.address2 = address2;
/*     */   }
/*     */   
/*     */   public String getAddress3() {
/* 135 */     return this.address3;
/*     */   }
/*     */   
/*     */   public void setAddress3(String address3) {
/* 139 */     this.address3 = address3;
/*     */   }
/*     */   
/*     */   public String getCountry() {
/* 143 */     return this.country;
/*     */   }
/*     */   
/*     */   public void setCountry(String country) {
/* 147 */     this.country = country;
/*     */   }
/*     */   
/*     */   public String getCity() {
/* 151 */     return this.city;
/*     */   }
/*     */   
/*     */   public void setCity(String city) {
/* 155 */     this.city = city;
/*     */   }
/*     */   
/*     */   public String getFax() {
/* 159 */     return this.fax;
/*     */   }
/*     */   
/*     */   public void setFax(String fax) {
/* 163 */     this.fax = fax;
/*     */   }
/*     */   
/*     */   public String getPostId() {
/* 167 */     return this.postId;
/*     */   }
/*     */   
/*     */   public void setPostId(String postId) {
/* 171 */     this.postId = postId;
/*     */   }
/*     */   
/*     */   public String getCurrencyType() {
/* 175 */     return this.currencyType;
/*     */   }
/*     */   
/*     */   public void setCurrencyType(String currencyType) {
/* 179 */     this.currencyType = currencyType;
/*     */   }
/*     */   
/*     */   public String getDomain() {
/* 183 */     return this.domain;
/*     */   }
/*     */   
/*     */   public void setDomain(String domain) {
/* 187 */     this.domain = domain;
/*     */   }
/*     */   
/*     */   public String getSite() {
/* 191 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(String site) {
/* 195 */     this.site = site;
/*     */   }
/*     */   
/*     */   public String getSeqNo() {
/* 199 */     return this.seqNo;
/*     */   }
/*     */   
/*     */   public void setSeqNo(String seqNo) {
/* 203 */     this.seqNo = seqNo;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/AbstractBasicCustomer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
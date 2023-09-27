/*     */ package com.aof.model.basic;
/*     */ 
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.product.BasicCustomer;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractCustomerreturns
/*     */   implements Serializable {
/*     */   private Integer id;
/*     */   private String returnNumber;
/*     */   private BasicCustomer basicCustomer;
/*     */   private String customerDescription;
/*     */   private String materialCode;
/*     */   private String materialName;
/*     */   private Integer returnQuantity;
/*     */   private String returnLocation;
/*     */   private Date returnDate;
/*     */   private String returnPerson;
/*     */   private String returnPersonContact;
/*     */   private Integer delStatus;
/*     */   private String returnReasons;
/*     */   private String returnStorage;
/*     */   private YesNo invoiceStatus;
/*     */   private String chanpinCode;
/*     */   private YesNo printStatus;
/*     */   
/*     */   public YesNo getPrintStatus() {
/*  29 */     return this.printStatus;
/*     */   }
/*     */   public void setPrintStatus(YesNo printStatus) {
/*  32 */     this.printStatus = printStatus;
/*     */   }
/*     */   public String getChanpinCode() {
/*  35 */     return this.chanpinCode;
/*     */   }
/*     */   public void setChanpinCode(String chanpinCode) {
/*  38 */     this.chanpinCode = chanpinCode;
/*     */   }
/*     */   public String getReturnNumber() {
/*  41 */     return this.returnNumber;
/*     */   }
/*     */   public void setReturnNumber(String returnNumber) {
/*  44 */     this.returnNumber = returnNumber;
/*     */   }
/*     */   public BasicCustomer getBasicCustomer() {
/*  47 */     return this.basicCustomer;
/*     */   }
/*     */   public void setBasicCustomer(BasicCustomer basicCustomer) {
/*  50 */     this.basicCustomer = basicCustomer;
/*     */   }
/*     */   public String getCustomerDescription() {
/*  53 */     return this.customerDescription;
/*     */   }
/*     */   public void setCustomerDescription(String customerDescription) {
/*  56 */     this.customerDescription = customerDescription;
/*     */   }
/*     */   public String getMaterialCode() {
/*  59 */     return this.materialCode;
/*     */   }
/*     */   public void setMaterialCode(String materialCode) {
/*  62 */     this.materialCode = materialCode;
/*     */   }
/*     */   public String getMaterialName() {
/*  65 */     return this.materialName;
/*     */   }
/*     */   public void setMaterialName(String materialName) {
/*  68 */     this.materialName = materialName;
/*     */   }
/*     */   public Integer getReturnQuantity() {
/*  71 */     return this.returnQuantity;
/*     */   }
/*     */   public void setReturnQuantity(Integer returnQuantity) {
/*  74 */     this.returnQuantity = returnQuantity;
/*     */   }
/*     */   public String getReturnLocation() {
/*  77 */     return this.returnLocation;
/*     */   }
/*     */   public void setReturnLocation(String returnLocation) {
/*  80 */     this.returnLocation = returnLocation;
/*     */   }
/*     */   public Date getReturnDate() {
/*  83 */     return this.returnDate;
/*     */   }
/*     */   public void setReturnDate(Date returnDate) {
/*  86 */     this.returnDate = returnDate;
/*     */   }
/*     */   public String getReturnPerson() {
/*  89 */     return this.returnPerson;
/*     */   }
/*     */   public void setReturnPerson(String returnPerson) {
/*  92 */     this.returnPerson = returnPerson;
/*     */   }
/*     */   public String getReturnPersonContact() {
/*  95 */     return this.returnPersonContact;
/*     */   }
/*     */   public void setReturnPersonContact(String returnPersonContact) {
/*  98 */     this.returnPersonContact = returnPersonContact;
/*     */   }
/*     */   public String getReturnReasons() {
/* 101 */     return this.returnReasons;
/*     */   }
/*     */   public void setReturnReasons(String returnReasons) {
/* 104 */     this.returnReasons = returnReasons;
/*     */   }
/*     */   public String getReturnStorage() {
/* 107 */     return this.returnStorage;
/*     */   }
/*     */   public void setReturnStorage(String returnStorage) {
/* 110 */     this.returnStorage = returnStorage;
/*     */   }
/*     */   public YesNo getInvoiceStatus() {
/* 113 */     return this.invoiceStatus;
/*     */   }
/*     */   public void setInvoiceStatus(YesNo invoiceStatus) {
/* 116 */     this.invoiceStatus = invoiceStatus;
/*     */   }
/*     */   public Integer getId() {
/* 119 */     return this.id;
/*     */   }
/*     */   public void setId(Integer id) {
/* 122 */     this.id = id;
/*     */   }
/*     */   public Integer getDelStatus() {
/* 125 */     return this.delStatus;
/*     */   }
/*     */   public void setDelStatus(Integer delStatus) {
/* 128 */     this.delStatus = delStatus;
/*     */   }
/*     */   public AbstractCustomerreturns(Integer id) {
/* 131 */     this.id = id;
/*     */   }
/*     */   
/*     */   public AbstractCustomerreturns() {}
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractCustomerreturns.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
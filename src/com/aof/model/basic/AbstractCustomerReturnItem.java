/*    */ package com.aof.model.basic;
/*    */ 
/*    */ import com.aof.model.metadata.YesNo;
/*    */ import com.aof.model.product.SalesWorkorder;
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public abstract class AbstractCustomerReturnItem
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private Customerreturns customerreturns;
/*    */   private String batchNumber;
/*    */   private WmsPart part;
/*    */   private String materialDescription;
/*    */   private String dpi;
/*    */   private Integer qty;
/*    */   private Date salesDeliveryDate;
/*    */   private String returnStorage;
/*    */   private SalesWorkorder workorderId;
/*    */   private YesNo isSync;
/*    */   
/*    */   public String getReturnStorage() {
/* 24 */     return this.returnStorage;
/*    */   }
/*    */   public void setReturnStorage(String returnStorage) {
/* 27 */     this.returnStorage = returnStorage;
/*    */   }
/*    */   public Integer getId() {
/* 30 */     return this.id;
/*    */   }
/*    */   public void setId(Integer id) {
/* 33 */     this.id = id;
/*    */   }
/*    */   public Customerreturns getCustomerreturns() {
/* 36 */     return this.customerreturns;
/*    */   }
/*    */   public void setCustomerreturns(Customerreturns customerreturns) {
/* 39 */     this.customerreturns = customerreturns;
/*    */   }
/*    */   public String getBatchNumber() {
/* 42 */     return this.batchNumber;
/*    */   }
/*    */   public void setBatchNumber(String batchNumber) {
/* 45 */     this.batchNumber = batchNumber;
/*    */   }
/*    */   public WmsPart getPart() {
/* 48 */     return this.part;
/*    */   }
/*    */   public void setPart(WmsPart part) {
/* 51 */     this.part = part;
/*    */   }
/*    */   public String getMaterialDescription() {
/* 54 */     return this.materialDescription;
/*    */   }
/*    */   public void setMaterialDescription(String materialDescription) {
/* 57 */     this.materialDescription = materialDescription;
/*    */   }
/*    */   
/*    */   public String getDpi() {
/* 61 */     return this.dpi;
/*    */   }
/*    */   public void setDpi(String dpi) {
/* 64 */     this.dpi = dpi;
/*    */   }
/*    */   public Integer getQty() {
/* 67 */     return this.qty;
/*    */   }
/*    */   public void setQty(Integer qty) {
/* 70 */     this.qty = qty;
/*    */   }
/*    */   public Date getSalesDeliveryDate() {
/* 73 */     return this.salesDeliveryDate;
/*    */   } public AbstractCustomerReturnItem() {}
/*    */   public void setSalesDeliveryDate(Date salesDeliveryDate) {
/* 76 */     this.salesDeliveryDate = salesDeliveryDate;
/*    */   }
/*    */   public AbstractCustomerReturnItem(Integer id) {
/* 79 */     this.id = id;
/*    */   } public SalesWorkorder getWorkorderId() {
/* 81 */     return this.workorderId;
/*    */   }
/*    */   public void setWorkorderId(SalesWorkorder workorderId) {
/* 84 */     this.workorderId = workorderId;
/*    */   }
/*    */   public YesNo getIsSync() {
/* 87 */     return this.isSync;
/*    */   }
/*    */   public void setIsSync(YesNo isSync) {
/* 90 */     this.isSync = isSync;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractCustomerReturnItem.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
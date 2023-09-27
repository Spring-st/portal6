/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.ContractStatus;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
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
/*     */ public class Supplier
/*     */   extends AbstractSupplier
/*     */   implements Serializable
/*     */ {
/*     */   private List items;
/*     */   
/*     */   public Supplier() {}
/*     */   
/*     */   public Supplier(Integer id) {
/*  37 */     super(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContractStatus getContractStatus() {
/*  46 */     Date today = new Date();
/*  47 */     Date contractStartDate = getContractStartDate();
/*  48 */     Date contractExpireDate = getContractExpireDate();
/*  49 */     if (contractStartDate == null) {
/*  50 */       if (contractExpireDate == null) {
/*  51 */         return ContractStatus.ACTIVED;
/*     */       }
/*  53 */       if (today.compareTo(contractExpireDate) < 0) {
/*  54 */         return ContractStatus.ACTIVED;
/*     */       }
/*  56 */       return ContractStatus.EXPIRED;
/*     */     } 
/*     */     
/*  59 */     if (contractExpireDate == null) {
/*  60 */       if (today.compareTo(contractStartDate) < 0) {
/*  61 */         return ContractStatus.NOT_ACTIVED;
/*     */       }
/*  63 */       return ContractStatus.ACTIVED;
/*     */     } 
/*  65 */     if (today.compareTo(contractStartDate) < 0) {
/*  66 */       return ContractStatus.NOT_ACTIVED;
/*     */     }
/*  68 */     if (today.compareTo(contractExpireDate) < 0) {
/*  69 */       return ContractStatus.ACTIVED;
/*     */     }
/*  71 */     return ContractStatus.EXPIRED;
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
/*     */   public List getItems() {
/*  83 */     return this.items;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItems(List items) {
/*  91 */     this.items = items;
/*     */   }
/*     */   
/*     */   public void addItem(SupplierItem si) {
/*  95 */     if (this.items == null) this.items = new ArrayList(); 
/*  96 */     this.items.add(si);
/*     */   }
/*     */   
/*     */   public void emailed(Date d) {
/* 100 */     setEmailDate(d);
/* 101 */     setEmailTimes(getEmailTimes() + 1);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/Supplier.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.aof.web.struts.form.basic;
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
/*     */ public class InventoryQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private String id;
/*     */   private String date;
/*     */   private String part;
/*     */   private String describe1;
/*     */   private String describe2;
/*     */   private String location;
/*     */   private String lot;
/*     */   private String startDate;
/*     */   private String endDate;
/*     */   private String supplier;
/*     */   
/*     */   public String getSupplier() {
/*  30 */     return this.supplier;
/*     */   }
/*     */   
/*     */   public void setSupplier(String supplier) {
/*  34 */     this.supplier = supplier;
/*     */   }
/*     */   
/*     */   public String getLot() {
/*  38 */     return this.lot;
/*     */   }
/*     */   
/*     */   public void setLot(String lot) {
/*  42 */     this.lot = lot;
/*     */   }
/*     */   
/*     */   public String getStartDate() {
/*  46 */     return this.startDate;
/*     */   }
/*     */   
/*     */   public void setStartDate(String startDate) {
/*  50 */     this.startDate = startDate;
/*     */   }
/*     */   
/*     */   public String getEndDate() {
/*  54 */     return this.endDate;
/*     */   }
/*     */   
/*     */   public void setEndDate(String endDate) {
/*  58 */     this.endDate = endDate;
/*     */   }
/*     */   
/*     */   public String getLocation() {
/*  62 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(String location) {
/*  66 */     this.location = location;
/*     */   }
/*     */   
/*     */   public String getDescribe1() {
/*  70 */     return this.describe1;
/*     */   }
/*     */   
/*     */   public void setDescribe1(String describe1) {
/*  74 */     this.describe1 = describe1;
/*     */   }
/*     */   
/*     */   public String getDescribe2() {
/*  78 */     return this.describe2;
/*     */   }
/*     */   
/*     */   public void setDescribe2(String describe2) {
/*  82 */     this.describe2 = describe2;
/*     */   }
/*     */   
/*     */   public String getDate() {
/*  86 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(String date) {
/*  90 */     this.date = date;
/*     */   }
/*     */   
/*     */   public String getPart() {
/*  94 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(String part) {
/*  98 */     this.part = part;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/* 105 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(String id) {
/* 113 */     this.id = id;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/basic/InventoryQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
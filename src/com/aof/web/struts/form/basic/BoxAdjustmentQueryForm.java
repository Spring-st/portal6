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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BoxAdjustmentQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private String id;
/*     */   private String siteId;
/*     */   private String describe;
/*     */   private String status;
/*     */   private String address;
/*     */   private String type;
/*     */   private String safetyInventory;
/*     */   private String maxInventory;
/*     */   
/*     */   public String getMaxInventory() {
/*  35 */     return this.maxInventory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxInventory(String maxInventory) {
/*  41 */     this.maxInventory = maxInventory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSafetyInventory() {
/*  47 */     return this.safetyInventory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSafetyInventory(String safetyInventory) {
/*  53 */     this.safetyInventory = safetyInventory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescribe() {
/*  59 */     return this.describe;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescribe(String describe) {
/*  65 */     this.describe = describe;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAddress() {
/*  72 */     return this.address;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAddress(String address) {
/*  78 */     this.address = address;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/*  84 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(String type) {
/*  90 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/*  96 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(String id) {
/* 102 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStatus() {
/* 108 */     return this.status;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStatus(String status) {
/* 114 */     this.status = status;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSiteId() {
/* 120 */     return this.siteId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSiteId(String siteId) {
/* 126 */     this.siteId = siteId;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/basic/BoxAdjustmentQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
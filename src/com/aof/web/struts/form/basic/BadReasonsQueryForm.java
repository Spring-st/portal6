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
/*     */ 
/*     */ 
/*     */ public class BadReasonsQueryForm
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
/*  37 */     return this.maxInventory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxInventory(String maxInventory) {
/*  43 */     this.maxInventory = maxInventory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSafetyInventory() {
/*  49 */     return this.safetyInventory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSafetyInventory(String safetyInventory) {
/*  55 */     this.safetyInventory = safetyInventory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescribe() {
/*  61 */     return this.describe;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescribe(String describe) {
/*  67 */     this.describe = describe;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAddress() {
/*  74 */     return this.address;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAddress(String address) {
/*  80 */     this.address = address;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/*  86 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(String type) {
/*  92 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/*  98 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(String id) {
/* 104 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStatus() {
/* 110 */     return this.status;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStatus(String status) {
/* 116 */     this.status = status;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSiteId() {
/* 122 */     return this.siteId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSiteId(String siteId) {
/* 128 */     this.siteId = siteId;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/basic/BadReasonsQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
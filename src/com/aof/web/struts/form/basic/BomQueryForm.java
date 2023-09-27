/*     */ package com.aof.web.struts.form.basic;
/*     */ 
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import java.util.Date;
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
/*     */ public class BomQueryForm
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
/*     */   private Date date;
/*     */   
/*     */   public Date getDate() {
/*  35 */     return this.date;
/*     */   }
/*     */   public void setDate(Date date) {
/*  38 */     this.date = date;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMaxInventory() {
/*  44 */     return this.maxInventory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxInventory(String maxInventory) {
/*  50 */     this.maxInventory = maxInventory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSafetyInventory() {
/*  56 */     return this.safetyInventory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSafetyInventory(String safetyInventory) {
/*  62 */     this.safetyInventory = safetyInventory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescribe() {
/*  68 */     return this.describe;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescribe(String describe) {
/*  74 */     this.describe = describe;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAddress() {
/*  81 */     return this.address;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAddress(String address) {
/*  87 */     this.address = address;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/*  93 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(String type) {
/*  99 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/* 105 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(String id) {
/* 111 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStatus() {
/* 117 */     return this.status;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStatus(String status) {
/* 123 */     this.status = status;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSiteId() {
/* 129 */     return this.siteId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSiteId(String siteId) {
/* 135 */     this.siteId = siteId;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/basic/BomQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
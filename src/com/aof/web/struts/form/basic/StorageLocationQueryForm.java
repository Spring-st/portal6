/*     */ package com.aof.web.struts.form.basic;
/*     */ 
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import org.apache.struts.upload.FormFile;
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
/*     */ public class StorageLocationQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private String id;
/*     */   private String siteId;
/*     */   private String describe;
/*     */   private String status;
/*     */   private String address;
/*     */   private String storeroom_id;
/*     */   private String site;
/*     */   private String code;
/*     */   private String codemany;
/*     */   private String stromType;
/*     */   private String notContain;
/*     */   private FormFile myFile;
/*     */   private String is_enabled;
/*     */   
/*     */   public FormFile getMyFile() {
/*  34 */     return this.myFile;
/*     */   }
/*     */   
/*     */   public void setMyFile(FormFile myFile) {
/*  38 */     this.myFile = myFile;
/*     */   }
/*     */   
/*     */   public String getNotContain() {
/*  42 */     return this.notContain;
/*     */   }
/*     */   
/*     */   public void setNotContain(String notContain) {
/*  46 */     this.notContain = notContain;
/*     */   }
/*     */   
/*     */   public String getStromType() {
/*  50 */     return this.stromType;
/*     */   }
/*     */   
/*     */   public void setStromType(String stromType) {
/*  54 */     this.stromType = stromType;
/*     */   }
/*     */   
/*     */   public String getCodemany() {
/*  58 */     return this.codemany;
/*     */   }
/*     */   
/*     */   public void setCodemany(String codemany) {
/*  62 */     this.codemany = codemany;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  66 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  70 */     this.code = code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSite() {
/*  79 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(String site) {
/*  87 */     this.site = site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIs_enabled() {
/*  94 */     return this.is_enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIs_enabled(String is_enabled) {
/* 102 */     this.is_enabled = is_enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescribe() {
/* 109 */     return this.describe;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescribe(String describe) {
/* 117 */     this.describe = describe;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAddress() {
/* 124 */     return this.address;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAddress(String address) {
/* 132 */     this.address = address;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStoreroom_id() {
/* 139 */     return this.storeroom_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStoreroom_id(String storeroom_id) {
/* 147 */     this.storeroom_id = storeroom_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/* 154 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(String id) {
/* 162 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStatus() {
/* 169 */     return this.status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStatus(String status) {
/* 177 */     this.status = status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSiteId() {
/* 184 */     return this.siteId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSiteId(String siteId) {
/* 192 */     this.siteId = siteId;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/basic/StorageLocationQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
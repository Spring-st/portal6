/*     */ package com.aof.web.struts.form.admin;
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
/*     */ public class HotelContractQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private String id;
/*     */   private String hotel_id;
/*     */   private String fileName;
/*     */   private String description;
/*     */   private String fileContent;
/*     */   private String uploadDate;
/*     */   
/*     */   public String getId() {
/*  28 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(String id) {
/*  36 */     this.id = id;
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
/*     */ 
/*     */   
/*     */   public String getHotel_id() {
/*  50 */     return this.hotel_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHotel_id(String hotel_id) {
/*  58 */     this.hotel_id = hotel_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFileName() {
/*  68 */     return this.fileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFileName(String fileName) {
/*  76 */     this.fileName = fileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  85 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/*  93 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFileContent() {
/* 102 */     return this.fileContent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFileContent(String fileContent) {
/* 110 */     this.fileContent = fileContent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUploadDate() {
/* 119 */     return this.uploadDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUploadDate(String uploadDate) {
/* 127 */     this.uploadDate = uploadDate;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/HotelContractQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
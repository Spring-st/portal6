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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WmsUnplannedWarehousingQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private String id;
/*     */   private String site;
/*     */   private String code;
/*     */   private String deliver;
/*     */   private String requistion;
/*     */   private String datetime;
/*     */   private String endtime;
/*     */   private String asnCode;
/*     */   private String status;
/*     */   private FormFile myFile;
/*     */   private String type;
/*     */   private String inDate;
/*     */   
/*     */   public String getInDate() {
/*  36 */     return this.inDate;
/*     */   }
/*     */   
/*     */   public void setInDate(String inDate) {
/*  40 */     this.inDate = inDate;
/*     */   }
/*     */   
/*     */   public String getType() {
/*  44 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(String type) {
/*  48 */     this.type = type;
/*     */   }
/*     */   
/*     */   public String getStatus() {
/*  52 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(String status) {
/*  56 */     this.status = status;
/*     */   }
/*     */   
/*     */   public FormFile getMyFile() {
/*  60 */     return this.myFile;
/*     */   }
/*     */   
/*     */   public void setMyFile(FormFile myFile) {
/*  64 */     this.myFile = myFile;
/*     */   }
/*     */   
/*     */   public String getId() {
/*  68 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  72 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getSite() {
/*  76 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(String site) {
/*  80 */     this.site = site;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  84 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  88 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getDeliver() {
/*  92 */     return this.deliver;
/*     */   }
/*     */   
/*     */   public void setDeliver(String deliver) {
/*  96 */     this.deliver = deliver;
/*     */   }
/*     */   
/*     */   public String getRequistion() {
/* 100 */     return this.requistion;
/*     */   }
/*     */   
/*     */   public void setRequistion(String requistion) {
/* 104 */     this.requistion = requistion;
/*     */   }
/*     */   
/*     */   public String getDatetime() {
/* 108 */     return this.datetime;
/*     */   }
/*     */   
/*     */   public void setDatetime(String datetime) {
/* 112 */     this.datetime = datetime;
/*     */   }
/*     */   
/*     */   public String getEndtime() {
/* 116 */     return this.endtime;
/*     */   }
/*     */   
/*     */   public void setEndtime(String endtime) {
/* 120 */     this.endtime = endtime;
/*     */   }
/*     */   
/*     */   public String getAsnCode() {
/* 124 */     return this.asnCode;
/*     */   }
/*     */   
/*     */   public void setAsnCode(String asnCode) {
/* 128 */     this.asnCode = asnCode;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/basic/WmsUnplannedWarehousingQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
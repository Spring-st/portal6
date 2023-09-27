/*    */ package com.aof.web.struts.form.basic;
/*    */ 
/*    */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*    */ import org.apache.struts.upload.FormFile;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WmsPlanToGoOutQueryForm
/*    */   extends BaseSessionQueryForm
/*    */ {
/*    */   private String id;
/*    */   private String site;
/*    */   private String code;
/*    */   private String deliver;
/*    */   private String requistion;
/*    */   private String datetime;
/*    */   private String endtime;
/*    */   private String asnCode;
/*    */   private String status;
/*    */   private FormFile myFile;
/*    */   private String type;
/*    */   
/*    */   public String getType() {
/* 35 */     return this.type;
/*    */   }
/*    */   public void setType(String type) {
/* 38 */     this.type = type;
/*    */   }
/*    */   public String getStatus() {
/* 41 */     return this.status;
/*    */   }
/*    */   public void setStatus(String status) {
/* 44 */     this.status = status;
/*    */   }
/*    */   public FormFile getMyFile() {
/* 47 */     return this.myFile;
/*    */   }
/*    */   public void setMyFile(FormFile myFile) {
/* 50 */     this.myFile = myFile;
/*    */   }
/*    */   public String getId() {
/* 53 */     return this.id;
/*    */   }
/*    */   public void setId(String id) {
/* 56 */     this.id = id;
/*    */   }
/*    */   public String getSite() {
/* 59 */     return this.site;
/*    */   }
/*    */   public void setSite(String site) {
/* 62 */     this.site = site;
/*    */   }
/*    */   public String getCode() {
/* 65 */     return this.code;
/*    */   }
/*    */   public void setCode(String code) {
/* 68 */     this.code = code;
/*    */   }
/*    */   public String getDeliver() {
/* 71 */     return this.deliver;
/*    */   }
/*    */   public void setDeliver(String deliver) {
/* 74 */     this.deliver = deliver;
/*    */   }
/*    */   public String getRequistion() {
/* 77 */     return this.requistion;
/*    */   }
/*    */   public void setRequistion(String requistion) {
/* 80 */     this.requistion = requistion;
/*    */   }
/*    */   public String getDatetime() {
/* 83 */     return this.datetime;
/*    */   }
/*    */   public void setDatetime(String datetime) {
/* 86 */     this.datetime = datetime;
/*    */   }
/*    */   public String getEndtime() {
/* 89 */     return this.endtime;
/*    */   }
/*    */   public void setEndtime(String endtime) {
/* 92 */     this.endtime = endtime;
/*    */   }
/*    */   public String getAsnCode() {
/* 95 */     return this.asnCode;
/*    */   }
/*    */   public void setAsnCode(String asnCode) {
/* 98 */     this.asnCode = asnCode;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/basic/WmsPlanToGoOutQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
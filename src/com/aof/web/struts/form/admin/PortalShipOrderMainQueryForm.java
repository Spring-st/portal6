/*     */ package com.aof.web.struts.form.admin;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
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
/*     */ public class PortalShipOrderMainQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private String id;
/*     */   private String status;
/*     */   private String site;
/*     */   private String code;
/*     */   private String createDate1;
/*     */   private String createDate2;
/*     */   private String department;
/*     */   private String enabled;
/*     */   private String remark;
/*     */   private String asnNo;
/*     */   private String isPrint;
/*     */   private String pocode;
/*     */   private String part_code;
/*     */   private String type;
/*     */   
/*     */   public PortalShipOrderMainQueryForm() {
/*  38 */     setEnabled(EnabledDisabled.ENABLED.getEnumCode().toString());
/*     */   }
/*     */   public String getId() {
/*  41 */     return this.id;
/*     */   }
/*     */   public void setId(String id) {
/*  44 */     this.id = id;
/*     */   }
/*     */   public String getStatus() {
/*  47 */     return this.status;
/*     */   }
/*     */   public void setStatus(String status) {
/*  50 */     this.status = status;
/*     */   }
/*     */   public String getSite() {
/*  53 */     return this.site;
/*     */   }
/*     */   public void setSite(String site) {
/*  56 */     this.site = site;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  60 */     return this.code;
/*     */   }
/*     */   public void setCode(String code) {
/*  63 */     this.code = code;
/*     */   }
/*     */   public String getCreateDate1() {
/*  66 */     return this.createDate1;
/*     */   }
/*     */   public void setCreateDate1(String createDate1) {
/*  69 */     this.createDate1 = createDate1;
/*     */   }
/*     */   public String getCreateDate2() {
/*  72 */     return this.createDate2;
/*     */   }
/*     */   public void setCreateDate2(String createDate2) {
/*  75 */     this.createDate2 = createDate2;
/*     */   }
/*     */   public String getDepartment() {
/*  78 */     return this.department;
/*     */   }
/*     */   public void setDepartment(String department) {
/*  81 */     this.department = department;
/*     */   }
/*     */   public String getEnabled() {
/*  84 */     return this.enabled;
/*     */   }
/*     */   public void setEnabled(String enabled) {
/*  87 */     this.enabled = enabled;
/*     */   }
/*     */   public String getRemark() {
/*  90 */     return this.remark;
/*     */   }
/*     */   public void setRemark(String remark) {
/*  93 */     this.remark = remark;
/*     */   }
/*     */   public String getAsnNo() {
/*  96 */     return this.asnNo;
/*     */   }
/*     */   public void setAsnNo(String asnNo) {
/*  99 */     this.asnNo = asnNo;
/*     */   }
/*     */   public String getIsPrint() {
/* 102 */     return this.isPrint;
/*     */   }
/*     */   public void setIsPrint(String isPrint) {
/* 105 */     this.isPrint = isPrint;
/*     */   }
/*     */   public String getPocode() {
/* 108 */     return this.pocode;
/*     */   }
/*     */   public void setPocode(String pocode) {
/* 111 */     this.pocode = pocode;
/*     */   }
/*     */   public String getPart_code() {
/* 114 */     return this.part_code;
/*     */   }
/*     */   public void setPart_code(String partCode) {
/* 117 */     this.part_code = partCode;
/*     */   }
/*     */   public String getType() {
/* 120 */     return this.type;
/*     */   }
/*     */   public void setType(String type) {
/* 123 */     this.type = type;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/PortalShipOrderMainQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
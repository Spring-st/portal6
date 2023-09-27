/*     */ package com.aof.model.po;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractProductionplanning
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int id;
/*     */   private String productionPlanningNumber;
/*     */   private Date uploadDate;
/*     */   private User uploadUser;
/*     */   private String uploadFileName;
/*     */   private String path;
/*     */   private String fileContent;
/*     */   private int fileContSize;
/*     */   private int num;
/*     */   
/*     */   public AbstractProductionplanning() {}
/*     */   
/*     */   public AbstractProductionplanning(Integer id) {
/*  26 */     this.id = id.intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  31 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*  35 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getProductionPlanningNumber() {
/*  40 */     return this.productionPlanningNumber;
/*     */   }
/*     */   
/*     */   public void setProductionPlanningNumber(String productionPlanningNumber) {
/*  44 */     this.productionPlanningNumber = productionPlanningNumber;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getUploadDate() {
/*  49 */     return this.uploadDate;
/*     */   }
/*     */   
/*     */   public void setUploadDate(Date uploadDate) {
/*  53 */     this.uploadDate = uploadDate;
/*     */   }
/*     */   
/*     */   public User getUploadUser() {
/*  57 */     return this.uploadUser;
/*     */   }
/*     */   
/*     */   public void setUploadUser(User uploadUser) {
/*  61 */     this.uploadUser = uploadUser;
/*     */   }
/*     */   
/*     */   public String getUploadFileName() {
/*  65 */     return this.uploadFileName;
/*     */   }
/*     */   
/*     */   public void setUploadFileName(String uploadFileName) {
/*  69 */     this.uploadFileName = uploadFileName;
/*     */   }
/*     */   
/*     */   public String getPath() {
/*  73 */     return this.path;
/*     */   }
/*     */   
/*     */   public void setPath(String path) {
/*  77 */     this.path = path;
/*     */   }
/*     */   
/*     */   public String getFileContent() {
/*  81 */     return this.fileContent;
/*     */   }
/*     */   
/*     */   public void setFileContent(String fileContent) {
/*  85 */     this.fileContent = fileContent;
/*     */   }
/*     */   
/*     */   public int getFileContSize() {
/*  89 */     return this.fileContSize;
/*     */   }
/*     */   
/*     */   public void setFileContSize(int fileContSize) {
/*  93 */     this.fileContSize = fileContSize;
/*     */   }
/*     */   
/*     */   public int getNum() {
/*  97 */     return this.num;
/*     */   }
/*     */   
/*     */   public void setNum(int num) {
/* 101 */     this.num = num;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractProductionplanning.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
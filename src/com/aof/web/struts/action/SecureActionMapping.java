/*     */ package com.aof.web.struts.action;
/*     */ 
/*     */ import com.shcnc.struts.action.CustomActionMapping;
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
/*     */ public class SecureActionMapping
/*     */   extends CustomActionMapping
/*     */ {
/*     */   private Integer functionId;
/*     */   private String functionDescription;
/*     */   private String level;
/*     */   private String functionType;
/*     */   
/*     */   public String getAttribute() {
/*  29 */     if (this.attribute == null) {
/*  30 */       if (getScope().equals("session")) {
/*     */         
/*  32 */         String path = getPath().trim();
/*  33 */         if (path.startsWith("/")) path = path.substring(1); 
/*  34 */         int index = path.indexOf(".do");
/*  35 */         if (index != -1) path = path.substring(0, index); 
/*  36 */         return String.valueOf(path) + "_" + this.name;
/*     */       } 
/*     */       
/*  39 */       return this.name;
/*     */     } 
/*  41 */     return this.attribute;
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
/*     */   public String getFunctionDescription() {
/*  53 */     return this.functionDescription;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFunctionDescription(String functionDescription) {
/*  60 */     this.functionDescription = functionDescription;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getFunctionId() {
/*  67 */     return this.functionId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFunctionId(Integer functionId) {
/*  74 */     this.functionId = functionId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFunctionType() {
/*  81 */     return this.functionType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFunctionType(String functionType) {
/*  88 */     this.functionType = functionType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLevel() {
/*  95 */     return this.level;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLevel(String level) {
/* 102 */     this.level = level;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/SecureActionMapping.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
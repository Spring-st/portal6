/*     */ package com.aof.model.basic;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractRecommendLocation
/*     */   implements Serializable {
/*   8 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private String code;
/*     */   private String description;
/*     */   private EnabledDisabled is_enabled;
/*     */   private String remark;
/*     */   
/*     */   public Integer getId() {
/*  16 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  20 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getHashValue() {
/*  24 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public void setHashValue(int hashValue) {
/*  28 */     this.hashValue = hashValue;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  32 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  36 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/*  40 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/*  44 */     this.description = description;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getIs_enabled() {
/*  48 */     return this.is_enabled;
/*     */   }
/*     */   
/*     */   public void setIs_enabled(EnabledDisabled is_enabled) {
/*  52 */     this.is_enabled = is_enabled;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/*  56 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/*  60 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractRecommendLocation() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractRecommendLocation(Integer id) {
/*  72 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object rhs) {
/*  83 */     if (rhs == null)
/*  84 */       return false; 
/*  85 */     if (this == rhs)
/*  86 */       return true; 
/*  87 */     if (!(rhs instanceof RecommendLocation))
/*  88 */       return false; 
/*  89 */     RecommendLocation that = (RecommendLocation)rhs;
/*  90 */     if (getId() != null)
/*  91 */       return getId().equals(that.getId()); 
/*  92 */     return (that.getId() == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 103 */     if (this.hashValue == 0) {
/* 104 */       int result = 17;
/* 105 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 106 */       result = result * 37 + poIdValue;
/* 107 */       this.hashValue = result;
/*     */     } 
/* 109 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractRecommendLocation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.aof.model.po;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import java.io.Serializable;
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
/*     */ public abstract class AbstractWmsLot
/*     */   implements Serializable
/*     */ {
/*  25 */   private int hashValue = 0;
/*     */   private String id;
/*     */   private EnabledDisabled enabled;
/*     */   
/*     */   public int getHashValue() {
/*  30 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public void setHashValue(int hashValue) {
/*  34 */     this.hashValue = hashValue;
/*     */   }
/*     */   
/*     */   public String getId() {
/*  38 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  42 */     this.id = id;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/*  46 */     return this.enabled;
/*     */   }
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/*  50 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractWmsLot() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractWmsLot(String id) {
/*  65 */     setId(id);
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
/*  76 */     if (rhs == null)
/*  77 */       return false; 
/*  78 */     if (this == rhs)
/*  79 */       return true; 
/*  80 */     if (!(rhs instanceof WmsLot))
/*  81 */       return false; 
/*  82 */     WmsLot that = (WmsLot)rhs;
/*  83 */     if (getId() != null)
/*  84 */       return getId().equals(that.getId()); 
/*  85 */     return (that.getId() == null);
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
/*  96 */     if (this.hashValue == 0) {
/*  97 */       int result = 17;
/*  98 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/*  99 */       result = result * 37 + poIdValue;
/* 100 */       this.hashValue = result;
/*     */     } 
/* 102 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractWmsLot.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSite
/*     */   implements Serializable
/*     */ {
/*  32 */   private int hashValue = 0;
/*     */   
/*     */   private Integer id;
/*     */   
/*     */   private String name;
/*     */   
/*     */   private String activity;
/*     */   
/*     */   private YesNo canRecharge;
/*     */   
/*     */   private Integer prApprovalRemind;
/*     */   
/*     */   private Integer prPrepareRemind;
/*     */   
/*     */   private Integer poApprovalRemind;
/*     */   
/*     */   private Integer poConfirmRemind;
/*     */   
/*     */   private Integer expApprovalRemind;
/*     */   
/*     */   private Integer expConfirmRemind;
/*     */   
/*     */   private Integer traApprovalRemind;
/*     */   
/*     */   private Integer traConfirmRemind;
/*     */   
/*     */   private Integer poReceiveRemind;
/*     */   
/*     */   private EnabledDisabled enabled;
/*     */   
/*     */   private City city;
/*     */   
/*     */   private Currency baseCurrency;
/*     */   
/*     */   private User manager;
/*     */   
/*     */   private Supplier supplier;
/*     */ 
/*     */   
/*     */   public Supplier getSupplier() {
/*  72 */     return this.supplier;
/*     */   }
/*     */   
/*     */   public void setSupplier(Supplier supplier) {
/*  76 */     this.supplier = supplier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSite() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSite(Integer id) {
/*  91 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/* 100 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/* 109 */     this.hashValue = 0;
/* 110 */     this.id = id;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/* 114 */     return this.enabled;
/*     */   }
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 118 */     this.enabled = enabled;
/*     */   }
/*     */   
/*     */   public Integer getExpApprovalRemind() {
/* 122 */     return this.expApprovalRemind;
/*     */   }
/*     */   
/*     */   public void setExpApprovalRemind(Integer expApprovalRemind) {
/* 126 */     this.expApprovalRemind = expApprovalRemind;
/*     */   }
/*     */   
/*     */   public Integer getExpConfirmRemind() {
/* 130 */     return this.expConfirmRemind;
/*     */   }
/*     */   
/*     */   public void setExpConfirmRemind(Integer expConfirmRemind) {
/* 134 */     this.expConfirmRemind = expConfirmRemind;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 138 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 142 */     this.name = name;
/*     */   }
/*     */   
/*     */   public Integer getPoApprovalRemind() {
/* 146 */     return this.poApprovalRemind;
/*     */   }
/*     */   
/*     */   public void setPoApprovalRemind(Integer poApprovalRemind) {
/* 150 */     this.poApprovalRemind = poApprovalRemind;
/*     */   }
/*     */   
/*     */   public Integer getPoConfirmRemind() {
/* 154 */     return this.poConfirmRemind;
/*     */   }
/*     */   
/*     */   public void setPoConfirmRemind(Integer poConfirmRemind) {
/* 158 */     this.poConfirmRemind = poConfirmRemind;
/*     */   }
/*     */   
/*     */   public Integer getPoReceiveRemind() {
/* 162 */     return this.poReceiveRemind;
/*     */   }
/*     */   
/*     */   public void setPoReceiveRemind(Integer poReceiveRemind) {
/* 166 */     this.poReceiveRemind = poReceiveRemind;
/*     */   }
/*     */   
/*     */   public Integer getPrApprovalRemind() {
/* 170 */     return this.prApprovalRemind;
/*     */   }
/*     */   
/*     */   public void setPrApprovalRemind(Integer prApprovalRemind) {
/* 174 */     this.prApprovalRemind = prApprovalRemind;
/*     */   }
/*     */   
/*     */   public Integer getPrPrepareRemind() {
/* 178 */     return this.prPrepareRemind;
/*     */   }
/*     */   
/*     */   public void setPrPrepareRemind(Integer prPrepareRemind) {
/* 182 */     this.prPrepareRemind = prPrepareRemind;
/*     */   }
/*     */   
/*     */   public YesNo getCanRecharge() {
/* 186 */     return this.canRecharge;
/*     */   }
/*     */   
/*     */   public void setCanRecharge(YesNo canRecharge) {
/* 190 */     this.canRecharge = canRecharge;
/*     */   }
/*     */   
/*     */   public Integer getTraApprovalRemind() {
/* 194 */     return this.traApprovalRemind;
/*     */   }
/*     */   
/*     */   public void setTraApprovalRemind(Integer traApprovalRemind) {
/* 198 */     this.traApprovalRemind = traApprovalRemind;
/*     */   }
/*     */   
/*     */   public Integer getTraConfirmRemind() {
/* 202 */     return this.traConfirmRemind;
/*     */   }
/*     */   
/*     */   public void setTraConfirmRemind(Integer traConfirmRemind) {
/* 206 */     this.traConfirmRemind = traConfirmRemind;
/*     */   }
/*     */   
/*     */   public City getCity() {
/* 210 */     return this.city;
/*     */   }
/*     */   
/*     */   public void setCity(City city) {
/* 214 */     this.city = city;
/*     */   }
/*     */   
/*     */   public Currency getBaseCurrency() {
/* 218 */     return this.baseCurrency;
/*     */   }
/*     */   
/*     */   public void setBaseCurrency(Currency baseCurrency) {
/* 222 */     this.baseCurrency = baseCurrency;
/*     */   }
/*     */   
/*     */   public User getManager() {
/* 226 */     return this.manager;
/*     */   }
/*     */   
/*     */   public void setManager(User manager) {
/* 230 */     this.manager = manager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getActivity() {
/* 237 */     return this.activity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActivity(String activity) {
/* 244 */     this.activity = activity;
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
/* 255 */     if (rhs == null)
/* 256 */       return false; 
/* 257 */     if (this == rhs)
/* 258 */       return true; 
/* 259 */     if (!(rhs instanceof Site))
/* 260 */       return false; 
/* 261 */     Site that = (Site)rhs;
/* 262 */     if (getId() != null)
/* 263 */       return getId().equals(that.getId()); 
/* 264 */     return (that.getId() == null);
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
/* 275 */     if (this.hashValue == 0) {
/* 276 */       int result = 17;
/* 277 */       int siteIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 278 */       result = result * 37 + siteIdValue;
/* 279 */       this.hashValue = result;
/*     */     } 
/* 281 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractSite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
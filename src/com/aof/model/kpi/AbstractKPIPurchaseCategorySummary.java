/*     */ package com.aof.model.kpi;
/*     */ 
/*     */ import com.aof.model.admin.PurchaseCategory;
/*     */ import com.aof.model.admin.Site;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractKPIPurchaseCategorySummary
/*     */   implements Serializable
/*     */ {
/*  11 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */   
/*     */   private KPISummary kpiSummary;
/*     */ 
/*     */   
/*     */   private Site site;
/*     */ 
/*     */   
/*     */   private Date summaryDate;
/*     */ 
/*     */   
/*     */   private PurchaseCategory purchaseCategory;
/*     */ 
/*     */   
/*     */   private int purchaseRequestCreatedToday;
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  35 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  42 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KPISummary getKpiSummary() {
/*  49 */     return this.kpiSummary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKpiSummary(KPISummary kpiSummary) {
/*  56 */     this.kpiSummary = kpiSummary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseCategory getPurchaseCategory() {
/*  63 */     return this.purchaseCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPurchaseCategory(PurchaseCategory purchaseCategory) {
/*  70 */     this.purchaseCategory = purchaseCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPurchaseRequestCreatedToday() {
/*  77 */     return this.purchaseRequestCreatedToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPurchaseRequestCreatedToday(int purchaseRequestCreatedToday) {
/*  84 */     this.purchaseRequestCreatedToday = purchaseRequestCreatedToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site getSite() {
/*  91 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(Site site) {
/*  98 */     this.site = site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getSummaryDate() {
/* 105 */     return this.summaryDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSummaryDate(Date summaryDate) {
/* 112 */     this.summaryDate = summaryDate;
/*     */   }
/*     */   
/*     */   public boolean equals(Object ks) {
/* 116 */     if (ks == null)
/* 117 */       return false; 
/* 118 */     if (this == ks)
/* 119 */       return true; 
/* 120 */     if (!(ks instanceof KPIPurchaseCategorySummary))
/* 121 */       return false; 
/* 122 */     KPIPurchaseCategorySummary that = (KPIPurchaseCategorySummary)ks;
/* 123 */     if (getId() != null)
/* 124 */       return getId().equals(that.getId()); 
/* 125 */     return (that.getId() == null);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 129 */     if (this.hashValue == 0) {
/* 130 */       int result = 17;
/* 131 */       int itemIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 132 */       result = result * 37 + itemIdValue;
/* 133 */       this.hashValue = result;
/*     */     } 
/* 135 */     return this.hashValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractKPIPurchaseCategorySummary() {}
/*     */ 
/*     */   
/*     */   public AbstractKPIPurchaseCategorySummary(Integer id) {
/* 144 */     setId(id);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/kpi/AbstractKPIPurchaseCategorySummary.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
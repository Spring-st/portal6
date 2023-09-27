/*     */ package com.aof.model.basicDataView;
/*     */ 
/*     */ import com.aof.model.schedule.EdiProduction;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
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
/*     */ public class EditProductionReport
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private EdiProduction ediProduction;
/*     */   private String asn_no;
/*     */   private Date task_date;
/*     */   private String quty;
/*     */   private String a;
/*     */   private String b;
/*     */   private String c;
/*     */   private String d;
/*     */   private String e;
/*     */   private String f;
/*     */   private String g;
/*     */   private String h;
/*     */   private String i;
/*     */   private String j;
/*     */   private String k;
/*     */   private String l;
/*     */   
/*     */   public int hashCode() {
/*  50 */     int prime = 31;
/*  51 */     int result = 1;
/*  52 */     result = 31 * result + ((this.ediProduction == null) ? 0 : this.ediProduction.hashCode());
/*     */     
/*  54 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  65 */     if (this == obj) {
/*  66 */       return true;
/*     */     }
/*  68 */     if (obj == null) {
/*  69 */       return false;
/*     */     }
/*  71 */     if (getClass() != obj.getClass()) {
/*  72 */       return false;
/*     */     }
/*  74 */     EditProductionReport other = (EditProductionReport)obj;
/*     */     
/*  76 */     if (this.ediProduction == null) {
/*  77 */       if (other.ediProduction != null) {
/*  78 */         return false;
/*     */       }
/*  80 */     } else if (!this.ediProduction.equals(other.ediProduction)) {
/*  81 */       return false;
/*     */     } 
/*     */     
/*  84 */     return true;
/*     */   }
/*     */   
/*     */   public String getAsn_no() {
/*  88 */     return this.asn_no;
/*     */   }
/*     */   public void setAsn_no(String asn_no) {
/*  91 */     this.asn_no = asn_no;
/*     */   }
/*     */   public Date getTask_date() {
/*  94 */     return this.task_date;
/*     */   }
/*     */   public void setTask_date(Date task_date) {
/*  97 */     this.task_date = task_date;
/*     */   }
/*     */   public String getQuty() {
/* 100 */     return this.quty;
/*     */   }
/*     */   public void setQuty(String quty) {
/* 103 */     this.quty = quty;
/*     */   }
/*     */   public String getA() {
/* 106 */     return this.a;
/*     */   }
/*     */   public void setA(String a) {
/* 109 */     this.a = a;
/*     */   }
/*     */   public String getB() {
/* 112 */     return this.b;
/*     */   }
/*     */   public void setB(String b) {
/* 115 */     this.b = b;
/*     */   }
/*     */   public String getC() {
/* 118 */     return this.c;
/*     */   }
/*     */   public void setC(String c) {
/* 121 */     this.c = c;
/*     */   }
/*     */   public String getD() {
/* 124 */     return this.d;
/*     */   }
/*     */   public void setD(String d) {
/* 127 */     this.d = d;
/*     */   }
/*     */   public String getE() {
/* 130 */     return this.e;
/*     */   }
/*     */   public void setE(String e) {
/* 133 */     this.e = e;
/*     */   }
/*     */   public String getF() {
/* 136 */     return this.f;
/*     */   }
/*     */   public void setF(String f) {
/* 139 */     this.f = f;
/*     */   }
/*     */   public String getG() {
/* 142 */     return this.g;
/*     */   }
/*     */   public void setG(String g) {
/* 145 */     this.g = g;
/*     */   }
/*     */   public String getH() {
/* 148 */     return this.h;
/*     */   }
/*     */   public void setH(String h) {
/* 151 */     this.h = h;
/*     */   }
/*     */   public String getI() {
/* 154 */     return this.i;
/*     */   }
/*     */   public void setI(String i) {
/* 157 */     this.i = i;
/*     */   }
/*     */   public String getJ() {
/* 160 */     return this.j;
/*     */   }
/*     */   public void setJ(String j) {
/* 163 */     this.j = j;
/*     */   }
/*     */   public String getK() {
/* 166 */     return this.k;
/*     */   }
/*     */   public void setK(String k) {
/* 169 */     this.k = k;
/*     */   }
/*     */   public String getL() {
/* 172 */     return this.l;
/*     */   }
/*     */   public void setL(String l) {
/* 175 */     this.l = l;
/*     */   }
/*     */   public EdiProduction getEdiProduction() {
/* 178 */     return this.ediProduction;
/*     */   }
/*     */   public void setEdiProduction(EdiProduction ediProduction) {
/* 181 */     this.ediProduction = ediProduction;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basicDataView/EditProductionReport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
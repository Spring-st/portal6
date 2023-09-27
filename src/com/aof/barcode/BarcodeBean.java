/*     */ package com.aof.barcode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BarcodeBean
/*     */ {
/*     */   private String type;
/*     */   private String msg;
/*  11 */   private int orientation = 90;
/*     */   
/*  13 */   private int resolution = 300;
/*     */ 
/*     */   
/*     */   private String height;
/*     */ 
/*     */   
/*     */   private String width;
/*     */ 
/*     */   
/*     */   private String module_width;
/*     */   
/*     */   private String wide_factor;
/*     */   
/*     */   private String quiet_zone;
/*     */   
/*     */   private String placement;
/*     */   
/*     */   private String pattern;
/*     */   
/*     */   private String font_size;
/*     */   
/*     */   private String font_name;
/*     */ 
/*     */   
/*     */   public int getOrientation() {
/*  38 */     return this.orientation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOrientation(int orientation) {
/*  45 */     this.orientation = orientation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getResolution() {
/*  52 */     return this.resolution;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResolution(int resolution) {
/*  59 */     this.resolution = resolution;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHeight() {
/*  66 */     return this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHeight(String height) {
/*  73 */     this.height = height;
/*     */   }
/*     */   
/*     */   public String getWidth() {
/*  77 */     return this.width;
/*     */   }
/*     */   public void setWidth(String width) {
/*  80 */     this.width = width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getModule_width() {
/*  87 */     return this.module_width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setModule_width(String module_width) {
/*  94 */     this.module_width = module_width;
/*     */   }
/*     */   public String getWide_factor() {
/*  97 */     return this.wide_factor;
/*     */   }
/*     */   public void setWide_factor(String wide_factor) {
/* 100 */     this.wide_factor = wide_factor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQuiet_zone() {
/* 107 */     return this.quiet_zone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQuiet_zone(String quiet_zone) {
/* 114 */     this.quiet_zone = quiet_zone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPlacement() {
/* 121 */     return this.placement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlacement(String placement) {
/* 128 */     this.placement = placement;
/*     */   }
/*     */   
/*     */   public String getPattern() {
/* 132 */     return this.pattern;
/*     */   }
/*     */   public void setPattern(String pattern) {
/* 135 */     this.pattern = pattern;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFont_size() {
/* 142 */     return this.font_size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFont_size(String font_size) {
/* 149 */     this.font_size = font_size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFont_name() {
/* 156 */     return this.font_name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFont_name(String font_name) {
/* 163 */     this.font_name = font_name;
/*     */   }
/*     */   public String getType() {
/* 166 */     return this.type;
/*     */   }
/*     */   public void setType(String type) {
/* 169 */     this.type = type;
/*     */   }
/*     */   public String getMsg() {
/* 172 */     return this.msg;
/*     */   }
/*     */   public void setMsg(String msg) {
/* 175 */     this.msg = msg;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/barcode/BarcodeBean.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
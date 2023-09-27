/*     */ package com.aof.model.admin;
/*     */ 
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
/*     */ public abstract class AbstractMenu
/*     */   implements Serializable
/*     */ {
/*  29 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */ 
/*     */   
/*     */   private Function function;
/*     */ 
/*     */ 
/*     */   
/*     */   private String name;
/*     */ 
/*     */ 
/*     */   
/*     */   private String description;
/*     */ 
/*     */   
/*     */   private String secondName;
/*     */ 
/*     */   
/*     */   private String secondDescription;
/*     */ 
/*     */   
/*     */   private String url;
/*     */ 
/*     */   
/*     */   private Menu parentMenu;
/*     */ 
/*     */   
/*     */   private String onClick;
/*     */ 
/*     */   
/*     */   private String onmouseOver;
/*     */ 
/*     */   
/*     */   private String onmouseDown;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractMenu() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractMenu(Integer menuId) {
/*  75 */     setId(menuId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  84 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  93 */     this.hashValue = 0;
/*  94 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 103 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 112 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSecondName() {
/* 121 */     return this.secondName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSecondName(String secondName) {
/* 130 */     this.secondName = secondName;
/*     */   }
/*     */   
/*     */   public Menu getParentMenu() {
/* 134 */     return this.parentMenu;
/*     */   }
/*     */   
/*     */   public void setParentMenu(Menu parentMenu) {
/* 138 */     this.parentMenu = parentMenu;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 147 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/* 156 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSecondDescription() {
/* 165 */     return this.secondDescription;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSecondDescription(String secondDescription) {
/* 174 */     this.secondDescription = secondDescription;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Function getFunction() {
/* 183 */     return this.function;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFunction(Function function) {
/* 192 */     this.function = function;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUrl() {
/* 201 */     return this.url;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUrl(String url) {
/* 210 */     this.url = url;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOnClick() {
/* 219 */     return this.onClick;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOnClick(String onClick) {
/* 228 */     this.onClick = onClick;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOnmouseOver() {
/* 237 */     return this.onmouseOver;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOnmouseOver(String onmouseOver) {
/* 246 */     this.onmouseOver = onmouseOver;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOnmouseDown() {
/* 255 */     return this.onmouseDown;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOnmouseDown(String onmouseDown) {
/* 264 */     this.onmouseDown = onmouseDown;
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
/* 275 */     if (rhs == null)
/* 276 */       return false; 
/* 277 */     if (this == rhs)
/* 278 */       return true; 
/* 279 */     if (!(rhs instanceof Menu))
/* 280 */       return false; 
/* 281 */     Menu that = (Menu)rhs;
/* 282 */     if (getId() != null)
/* 283 */       return getId().equals(that.getId()); 
/* 284 */     return (that.getId() == null);
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
/* 295 */     if (this.hashValue == 0) {
/* 296 */       int result = 17;
/* 297 */       int menuIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 298 */       result = result * 37 + menuIdValue;
/* 299 */       this.hashValue = result;
/*     */     } 
/* 301 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractMenu.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
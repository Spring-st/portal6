/*    */ package com.aof.web.struts.action;
/*    */ 
/*    */ import com.shcnc.struts.action.ExceptionHandler;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExceptionHandler
/*    */   extends ExceptionHandler
/*    */ {
/*    */   private Set setDialogActionPath;
/*    */   
/*    */   public ExceptionHandler() {
/* 25 */     this.setDialogActionPath = new HashSet();
/* 26 */     this.setDialogActionPath.add("/newUser");
/* 27 */     this.setDialogActionPath.add("/insertUser");
/* 28 */     this.setDialogActionPath.add("/editUser");
/* 29 */     this.setDialogActionPath.add("/updateUser");
/* 30 */     this.setDialogActionPath.add("/editFunction");
/* 31 */     this.setDialogActionPath.add("/updateFunction");
/* 32 */     this.setDialogActionPath.add("/newCurrency");
/* 33 */     this.setDialogActionPath.add("/insertCurrency");
/* 34 */     this.setDialogActionPath.add("/editCurrency");
/* 35 */     this.setDialogActionPath.add("/updateCurrency");
/* 36 */     this.setDialogActionPath.add("/newPurchaseCategory");
/* 37 */     this.setDialogActionPath.add("/insertPurchaseCategory");
/* 38 */     this.setDialogActionPath.add("/editPurchaseCategory");
/* 39 */     this.setDialogActionPath.add("/updatePurchaseCategory");
/* 40 */     this.setDialogActionPath.add("/newPurchaseSubCategory");
/* 41 */     this.setDialogActionPath.add("/insertPurchaseSubCategory");
/* 42 */     this.setDialogActionPath.add("/editPurchaseSubCategory");
/* 43 */     this.setDialogActionPath.add("/updatePurchaseSubCategory");
/* 44 */     this.setDialogActionPath.add("/newExchangeRate");
/* 45 */     this.setDialogActionPath.add("/insertExchangeRate");
/* 46 */     this.setDialogActionPath.add("/editExchangeRate");
/* 47 */     this.setDialogActionPath.add("/updateExchangeRate");
/* 48 */     this.setDialogActionPath.add("/selectSupplier");
/*    */   }
/*    */ 
/*    */   
/*    */   public Set getDialogActionPathSet() {
/* 53 */     return this.setDialogActionPath;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/ExceptionHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
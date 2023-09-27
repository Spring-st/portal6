/*    */ package com.aof.model.product;
/*    */ 
/*    */ public abstract class AbstractProductBelowlineCasade
/*    */ {
/*    */   private Integer id;
/*    */   
/*    */   public Product getProduct() {
/*  8 */     return this.product;
/*    */   } private Product product; private ProductBelowLine productBelowLine;
/*    */   public void setProduct(Product product) {
/* 11 */     this.product = product;
/*    */   }
/*    */   public ProductBelowLine getProductBelowLine() {
/* 14 */     return this.productBelowLine;
/*    */   }
/*    */   public void setProductBelowLine(ProductBelowLine productBelowLine) {
/* 17 */     this.productBelowLine = productBelowLine;
/*    */   }
/*    */   public Integer getId() {
/* 20 */     return this.id;
/*    */   }
/*    */   public void setId(Integer id) {
/* 23 */     this.id = id;
/*    */   }
/*    */   
/*    */   public AbstractProductBelowlineCasade(Integer id) {
/* 27 */     this.id = id;
/*    */   }
/*    */   
/*    */   public AbstractProductBelowlineCasade() {}
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/AbstractProductBelowlineCasade.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
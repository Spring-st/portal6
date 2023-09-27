/*    */ package com.aof.model.product;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class SalesPreshiporder
/*    */   extends AbstractSalesPreshiporder
/*    */ {
/*    */   private List<SalesPreshiporderItem> customerPlanList;
/*    */   
/*    */   public SalesPreshiporder() {}
/*    */   
/*    */   public SalesPreshiporder(Integer id) {
/* 13 */     super(id);
/*    */   }
/*    */   
/*    */   public List<SalesPreshiporderItem> getCustomerPlanList() {
/* 17 */     return this.customerPlanList;
/*    */   }
/*    */   
/*    */   public void setCustomerPlanList(List<SalesPreshiporderItem> customerPlanList) {
/* 21 */     this.customerPlanList = customerPlanList;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/SalesPreshiporder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
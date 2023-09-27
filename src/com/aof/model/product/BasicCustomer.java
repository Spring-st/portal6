/*    */ package com.aof.model.product;
/*    */ 
/*    */ import com.aof.model.admin.Site;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BasicCustomer
/*    */   extends AbstractBasicCustomer
/*    */   implements Serializable
/*    */ {
/*    */   private Site siteId;
/*    */   
/*    */   public BasicCustomer() {}
/*    */   
/*    */   public BasicCustomer(Integer id) {
/* 15 */     super(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public Site getSiteId() {
/* 20 */     return this.siteId;
/*    */   }
/*    */   
/*    */   public void setSiteId(Site siteId) {
/* 24 */     this.siteId = siteId;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/BasicCustomer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.aof.service.Product.impl;
/*    */ 
/*    */ import com.aof.dao.product.CustomerPlanDao;
/*    */ import com.aof.model.admin.User;
/*    */ import com.aof.model.product.CustomerPlan;
/*    */ import com.aof.model.product.query.CustomerPlanQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.Product.CustomerPlanManager;
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class CustomerPlanManagerImpl
/*    */   extends BaseManager
/*    */   implements CustomerPlanManager {
/*    */   private CustomerPlanDao dao;
/*    */   
/*    */   public CustomerPlanDao getDao() {
/* 21 */     return this.dao;
/*    */   }
/*    */   
/*    */   public void setDao(CustomerPlanDao dao) {
/* 25 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public CustomerPlan getById(Integer id) {
/* 29 */     return this.dao.getById(id);
/*    */   }
/*    */   
/*    */   public CustomerPlan insert(CustomerPlan customerPlan) {
/* 33 */     return this.dao.insert(customerPlan);
/*    */   }
/*    */   
/*    */   public void remove(CustomerPlan customerPlan) {
/* 37 */     this.dao.remove(customerPlan);
/*    */   }
/*    */   
/*    */   public CustomerPlan update(CustomerPlan customerPlan) {
/* 41 */     return this.dao.update(customerPlan);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getListCount(Map conditions) {
/* 46 */     return this.dao.getListCount(conditions);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getList(Map conditions, int pageNo, int pageSize, CustomerPlanQueryOrder order, boolean descend) {
/* 51 */     return this.dao.getList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */   
/*    */   public String getPlanNumbers(Date date, User requestor) {
/* 55 */     StringBuffer sb = new StringBuffer("XQ");
/*    */     
/* 57 */     StringBuffer siteIds = new StringBuffer("0000");
/* 58 */     String siteId = requestor.getPrimarySite().getId().toString();
/* 59 */     siteIds.append(siteId);
/* 60 */     String site = siteIds.substring(siteIds.length() - 4, siteIds.length());
/* 61 */     sb.append(site);
/* 62 */     String dateStr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
/* 63 */     sb.append(dateStr);
/* 64 */     List<String> soMaxList = this.dao.getObjectList("select max(customerPlan.planNumbers) from CustomerPlan customerPlan where customerPlan.planNumbers like '" + 
/* 65 */         sb.toString() + "%'");
/* 66 */     Integer serialNumber = Integer.valueOf(0);
/* 67 */     if (soMaxList != null && soMaxList.size() != 0 && soMaxList.get(0) != null && !((String)soMaxList.get(0)).equals("NULL") && !((String)soMaxList.get(0)).equals("NULL")) {
/* 68 */       String soMax = soMaxList.get(0);
/* 69 */       serialNumber = Integer.valueOf(Integer.parseInt(soMax.substring(soMax.length() - 4, soMax.length())));
/*    */     } 
/* 71 */     DecimalFormat df = new DecimalFormat("0000");
/* 72 */     String serialNumbers = df.format((serialNumber.intValue() + 1));
/* 73 */     sb.append(serialNumbers);
/* 74 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/impl/CustomerPlanManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
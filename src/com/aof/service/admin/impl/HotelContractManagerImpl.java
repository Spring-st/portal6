/*    */ package com.aof.service.admin.impl;
/*    */ 
/*    */ import com.aof.dao.admin.HotelContractDAO;
/*    */ import com.aof.model.admin.HotelContract;
/*    */ import com.aof.model.admin.query.HotelContractQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.admin.HotelContractManager;
/*    */ import java.io.InputStream;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import java.util.Map;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HotelContractManagerImpl
/*    */   extends BaseManager
/*    */   implements HotelContractManager
/*    */ {
/*    */   private HotelContractDAO dao;
/*    */   
/*    */   public void setHotelContractDAO(HotelContractDAO dao) {
/* 35 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public HotelContract getHotelContract(Integer id) {
/* 42 */     return this.dao.getHotelContract(id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public HotelContract updateHotelContract(HotelContract hotelContract) {
/* 49 */     return this.dao.updateHotelContract(hotelContract);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public HotelContract insertHotelContract(HotelContract hotelContract) {
/* 56 */     return this.dao.insertHotelContract(hotelContract);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getHotelContractListCount(Map conditions) {
/* 64 */     return this.dao.getHotelContractListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getHotelContractList(Map conditions, int pageNo, int pageSize, HotelContractQueryOrder order, boolean descend) {
/* 71 */     return this.dao.getHotelContractList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public HotelContract insertHotelContract(HotelContract hotelContract, InputStream inputStream) {
/* 78 */     hotelContract.setUploadDate(new Date());
/* 79 */     HotelContract hc = this.dao.insertHotelContract(hotelContract);
/* 80 */     this.dao.saveHotelContractContent(hc.getId(), inputStream);
/* 81 */     return hc;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InputStream getHotelContractContent(Integer id) {
/* 88 */     return this.dao.getHotelContractContent(id);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/HotelContractManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
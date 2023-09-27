/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.ExchangeRateDAO;
/*     */ import com.aof.dao.admin.HotelDAO;
/*     */ import com.aof.dao.admin.PriceDAO;
/*     */ import com.aof.model.admin.City;
/*     */ import com.aof.model.admin.Currency;
/*     */ import com.aof.model.admin.ExchangeRate;
/*     */ import com.aof.model.admin.Hotel;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.query.HotelQueryCondition;
/*     */ import com.aof.model.admin.query.HotelQueryOrder;
/*     */ import com.aof.model.admin.query.PriceQueryCondition;
/*     */ import com.aof.model.admin.query.PriceQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.HotelPromoteStatus;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.HotelManager;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ public class HotelManagerImpl
/*     */   extends BaseManager
/*     */   implements HotelManager
/*     */ {
/*     */   private HotelDAO dao;
/*     */   private PriceDAO priceDao;
/*     */   private ExchangeRateDAO exchangeRateDao;
/*     */   
/*     */   public void setHotelDAO(HotelDAO dao) {
/*  57 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hotel getHotel(Integer id) {
/*  64 */     return this.dao.getHotel(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hotel updateHotel(Hotel hotel) {
/*  71 */     return this.dao.updateHotel(hotel);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hotel insertHotel(Hotel hotel) {
/*  78 */     if (hotel.getSite() == null) {
/*  79 */       hotel.setPromoteStatus(HotelPromoteStatus.GLOBAL);
/*     */     } else {
/*  81 */       hotel.setPromoteStatus(HotelPromoteStatus.SITE);
/*  82 */     }  return this.dao.insertHotel(hotel);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHotelListCount(Map conditions) {
/*  89 */     return this.dao.getHotelListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getHotelList(Map conditions, int pageNo, int pageSize, HotelQueryOrder order, boolean descend) {
/*  97 */     return this.dao.getHotelList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hotel reponsePromote(Integer id) {
/* 104 */     Hotel h = getHotel(id);
/* 105 */     if (!h.getPromoteStatus().equals(HotelPromoteStatus.REQUEST)) {
/* 106 */       throw new RuntimeException("Promote Status error");
/*     */     }
/*     */     
/* 109 */     h.setPromoteStatus(HotelPromoteStatus.GLOBAL);
/* 110 */     h.setPromoteDate(new Date());
/* 111 */     updateHotel(h);
/* 112 */     return h;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hotel requestPromote(Integer id, String promoteMessage) {
/* 120 */     Hotel h = getHotel(id);
/*     */     
/* 122 */     if (!h.getPromoteStatus().equals(HotelPromoteStatus.SITE)) {
/* 123 */       throw new RuntimeException("Promote Status error");
/*     */     }
/* 125 */     h.setPromoteMessage(promoteMessage);
/* 126 */     h.setPromoteDate(new Date());
/* 127 */     h.setPromoteStatus(HotelPromoteStatus.REQUEST);
/* 128 */     updateHotel(h);
/* 129 */     return h;
/*     */   }
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
/*     */ 
/*     */ 
/*     */   
/*     */   private BigDecimal convert(BigDecimal price, Site site, Currency hotelCurrency) {
/* 201 */     if (site.getBaseCurrency().equals(hotelCurrency)) {
/* 202 */       return price;
/*     */     }
/* 204 */     ExchangeRate er = this.exchangeRateDao.getExchangeRate(hotelCurrency, site);
/* 205 */     if (er == null) {
/* 206 */       throw new RuntimeException("currency not found!");
/*     */     }
/* 208 */     return price.multiply(er.getExchangeRate());
/*     */   }
/*     */   
/*     */   private List getEnabledHotelList(City city) {
/* 212 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 213 */     conds.put(HotelQueryCondition.CITY_ID_EQ, city.getId());
/* 214 */     conds.put(HotelQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED
/* 215 */         .getEnumCode());
/* 216 */     return this.dao.getHotelList(conds, 0, -1, HotelQueryOrder.LEVEL, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPriceDAO(PriceDAO priceDao) {
/* 223 */     this.priceDao = priceDao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExchangeRateDAO(ExchangeRateDAO eDao) {
/* 230 */     this.exchangeRateDao = eDao;
/*     */   }
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
/*     */   private List getEnabledRoomList(City city) {
/* 250 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 251 */     conds.put(PriceQueryCondition.HOTEL_CITY_ID_EQ, city.getId());
/* 252 */     conds.put(PriceQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED
/* 253 */         .getEnumCode());
/* 254 */     List roomList = this.priceDao.getPriceList(conds, 0, -1, 
/* 255 */         PriceQueryOrder.PRICE, false);
/* 256 */     return roomList;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/HotelManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
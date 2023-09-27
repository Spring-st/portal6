/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.PriceDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.Price;
/*     */ import com.aof.model.admin.query.PriceQueryCondition;
/*     */ import com.aof.model.admin.query.PriceQueryOrder;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
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
/*     */ public class PriceDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements PriceDAO
/*     */ {
/*  30 */   private Log log = LogFactory.getLog(PriceDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from Price price";
/*     */   
/*     */   private static final String SQL = "from Price price";
/*     */   
/*     */   public Price getPrice(Integer id) {
/*  37 */     if (id == null) {
/*  38 */       if (this.log.isDebugEnabled()) this.log.debug("try to get Price with null id"); 
/*  39 */       return null;
/*     */     } 
/*  41 */     return (Price)getHibernateTemplate().get(Price.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Price updatePrice(Price price) {
/*  48 */     Integer id = price.getId();
/*  49 */     if (id == null) {
/*  50 */       throw new RuntimeException("cannot save a price with null id");
/*     */     }
/*  52 */     Price oldPrice = getPrice(id);
/*  53 */     if (oldPrice != null) {
/*     */       try {
/*  55 */         PropertyUtils.copyProperties(oldPrice, price);
/*     */       }
/*  57 */       catch (Exception e) {
/*     */         
/*  59 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  61 */       getHibernateTemplate().update(oldPrice);
/*  62 */       return oldPrice;
/*     */     } 
/*     */ 
/*     */     
/*  66 */     throw new RuntimeException("price not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Price insertPrice(Price price) {
/*  74 */     getHibernateTemplate().save(price);
/*  75 */     return price;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*     */       {
/*  84 */         PriceQueryCondition.ID_EQ, "price.id = ?"
/*     */ 
/*     */       
/*     */       },
/*     */       
/*     */       {
/*     */         
/*  91 */         PriceQueryCondition.HOTEL_ID_EQ, "price.hotel.id = ?"
/*     */       
/*     */       },
/*  94 */       { PriceQueryCondition.ROOM_LIKE, "price.room like ?", new LikeConvert()
/*  95 */       }, { PriceQueryCondition.PRICE_EQ, "price.price = ?"
/*  96 */       }, { PriceQueryCondition.DISCOUNT_EQ, "price.discount = ?"
/*  97 */       }, { PriceQueryCondition.DESCRIPTION_LIKE, "price.description like ?", new LikeConvert()
/*  98 */       }, { PriceQueryCondition.ENABLED_EQ, "price.enabled = ?"
/*  99 */       }, { PriceQueryCondition.HOTEL_CITY_ID_EQ, "price.hotel.city.id = ?" }
/*     */     };
/*     */   
/* 102 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*     */       {
/* 104 */         PriceQueryOrder.ID, "price.id"
/*     */       
/*     */       },
/* 107 */       { PriceQueryOrder.ROOM, "price.room"
/* 108 */       }, { PriceQueryOrder.PRICE, "price.price"
/* 109 */       }, { PriceQueryOrder.DISCOUNT, "price.discount"
/* 110 */       }, { PriceQueryOrder.DESCRIPTION, "price.description"
/* 111 */       }, { PriceQueryOrder.ENABLED, "price.enabled" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPriceListCount(Map conditions) {
/* 118 */     return getListCount(conditions, "select count(*) from Price price", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPriceList(Map conditions, int pageNo, int pageSize, PriceQueryOrder order, boolean descend) {
/* 125 */     return getList(conditions, pageNo, pageSize, order, descend, "from Price price", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/PriceDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
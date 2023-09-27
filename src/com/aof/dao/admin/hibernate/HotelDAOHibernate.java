/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.HotelDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.GlobalMailReminder;
/*     */ import com.aof.model.admin.Hotel;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.query.HotelQueryCondition;
/*     */ import com.aof.model.admin.query.HotelQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.HotelPromoteStatus;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.Hibernate;
/*     */ import net.sf.hibernate.type.Type;
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
/*     */ 
/*     */ public class HotelDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements HotelDAO
/*     */ {
/*  38 */   private Log log = LogFactory.getLog(HotelDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from Hotel hotel left join hotel.site join hotel.city join hotel.city.province join hotel.city.province.country";
/*     */   
/*     */   private static final String SQL = "select hotel from Hotel hotel left join hotel.site join hotel.city join hotel.city.province join hotel.city.province.country";
/*     */   
/*     */   public Hotel getHotel(Integer id) {
/*  45 */     if (id == null) {
/*  46 */       if (this.log.isDebugEnabled()) this.log.debug("try to get Hotel with null id"); 
/*  47 */       return null;
/*     */     } 
/*  49 */     return (Hotel)getHibernateTemplate().get(Hotel.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hotel updateHotel(Hotel hotel) {
/*  56 */     Integer id = hotel.getId();
/*  57 */     if (id == null) {
/*  58 */       throw new RuntimeException("cannot save a hotel with null id");
/*     */     }
/*  60 */     Hotel oldHotel = getHotel(id);
/*  61 */     if (oldHotel != null) {
/*     */       try {
/*  63 */         PropertyUtils.copyProperties(oldHotel, hotel);
/*     */       }
/*  65 */       catch (Exception e) {
/*     */         
/*  67 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  69 */       getHibernateTemplate().update(oldHotel);
/*     */ 
/*     */       
/*  72 */       return oldHotel;
/*     */     } 
/*     */ 
/*     */     
/*  76 */     throw new RuntimeException("hotel not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hotel insertHotel(Hotel hotel) {
/*  86 */     getHibernateTemplate().save(hotel);
/*  87 */     return hotel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*     */       
/*  96 */       { HotelQueryCondition.ID_EQ, "hotel.id = ?"
/*     */ 
/*     */       
/*     */       },
/*     */       
/*     */       {
/*     */         
/* 103 */         HotelQueryCondition.SITE_ID_EQ, "hotel.site.id = ?"
/* 104 */       }, { HotelQueryCondition.CURRENCY_CODE_EQ, "hotel.currency.code = ?"
/* 105 */       }, { HotelQueryCondition.CITY_ID_EQ, "hotel.city.id = ?"
/* 106 */       }, { HotelQueryCondition.PROVINCE_ID_EQ, "hotel.city.province.id = ?"
/* 107 */       }, { HotelQueryCondition.COUNTRY_ID_EQ, "hotel.city.province.country.id = ?"
/*     */       },
/*     */       {
/* 110 */         HotelQueryCondition.NAME_LIKE, "hotel.name like ?", new LikeConvert()
/* 111 */       }, { HotelQueryCondition.ADDRESS_LIKE, "hotel.address like ?", new LikeConvert()
/* 112 */       }, { HotelQueryCondition.DESCRIPTION_LIKE, "hotel.description like ?", new LikeConvert()
/* 113 */       }, { HotelQueryCondition.TELEPHONE_LIKE, "hotel.telephone like ?", new LikeConvert() }, 
/* 114 */       { HotelQueryCondition.FAX_LIKE, "hotel.fax like ?", new LikeConvert()
/* 115 */       }, { HotelQueryCondition.LEVEL_EQ, "hotel.level = ?"
/* 116 */       }, { HotelQueryCondition.CONTRACTSTARTDATE_EQ, "hotel.contractStartDate = ?"
/* 117 */       }, { HotelQueryCondition.CONTRACTEXPIREDATE_EQ, "hotel.contractExpireDate = ?"
/* 118 */       }, { HotelQueryCondition.DRAFT_EQ, "hotel.draft = ?"
/* 119 */       }, { HotelQueryCondition.ENABLED_EQ, "hotel.enabled = ?"
/* 120 */       }, { HotelQueryCondition.PROMOTESTATUS_NOTEQ, "hotel.promoteStatus <> ?"
/* 121 */       }, { HotelQueryCondition.PROMOTESTATUS_EQ, "hotel.promoteStatus = ?" }
/*     */     };
/*     */   
/* 124 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*     */       
/* 126 */       { HotelQueryOrder.ID, "hotel.id"
/*     */       },
/*     */       {
/* 129 */         HotelQueryOrder.NAME, "hotel.name"
/* 130 */       }, { HotelQueryOrder.ADDRESS, "hotel.address"
/* 131 */       }, { HotelQueryOrder.DESCRIPTION, "hotel.description"
/* 132 */       }, { HotelQueryOrder.TELEPHONE, "hotel.telephone"
/* 133 */       }, { HotelQueryOrder.FAX, "hotel.fax"
/* 134 */       }, { HotelQueryOrder.LEVEL, "hotel.level"
/* 135 */       }, { HotelQueryOrder.CONTRACTSTARTDATE, "hotel.contractStartDate"
/* 136 */       }, { HotelQueryOrder.CONTRACTEXPIREDATE, "hotel.contractExpireDate"
/* 137 */       }, { HotelQueryOrder.DRAFT, "hotel.draft" }, 
/* 138 */       { HotelQueryOrder.ENABLED, "hotel.enabled"
/* 139 */       }, { HotelQueryOrder.COUNTRY_ENG, "hotel.city.province.country.engName"
/* 140 */       }, { HotelQueryOrder.COUNTRY_CHN, "hotel.city.province.country.chnName"
/* 141 */       }, { HotelQueryOrder.CITY_ENG, "hotel.city.engName"
/* 142 */       }, { HotelQueryOrder.CITY_CHN, "hotel.city.chnName"
/* 143 */       }, { HotelQueryOrder.PROVINCE_ENG, "hotel.city.province.engName"
/* 144 */       }, { HotelQueryOrder.PROVINCE_CHN, "hotel.city.province.chnName"
/* 145 */       }, { HotelQueryOrder.SITE, "hotel.site.name"
/* 146 */       }, { HotelQueryOrder.PROMOTESTATUS, "hotel.promoteStatus" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHotelListCount(Map conditions) {
/* 154 */     return getListCount(conditions, "select count(*) from Hotel hotel left join hotel.site join hotel.city join hotel.city.province join hotel.city.province.country", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getHotelList(Map conditions, int pageNo, int pageSize, HotelQueryOrder order, boolean descend) {
/* 161 */     return getList(conditions, pageNo, pageSize, order, descend, "select hotel from Hotel hotel left join hotel.site join hotel.city join hotel.city.province join hotel.city.province.country", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */   
/*     */   public List getHotelMaintainerNotResponsedList(Site site, Date time, GlobalMailReminder gmr) {
/* 165 */     return getHibernateTemplate().find("from Hotel h where h.site.id=? and h.promoteStatus=? and h.promoteDate<=? and h.emailTimes<? and (h.emailDate is null or h.emailDate<=?) and h.enabled=?", 
/*     */         
/* 167 */         new Object[] { site.getId(), HotelPromoteStatus.REQUEST.getEnumCode(), 
/* 168 */           gmr.getResponseDate(time), new Integer(gmr.getMaxTime()), gmr.getEmailDate(time), 
/* 169 */           EnabledDisabled.ENABLED.getEnumCode()
/* 170 */         }, new Type[] { (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER, (Type)Hibernate.TIMESTAMP, (Type)Hibernate.INTEGER, (Type)Hibernate.TIMESTAMP, (Type)Hibernate.INTEGER });
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/HotelDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
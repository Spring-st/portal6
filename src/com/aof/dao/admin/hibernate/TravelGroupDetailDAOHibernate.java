/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.TravelGroupDetailDAO;
/*     */ import com.aof.model.admin.ExpenseSubCategory;
/*     */ import com.aof.model.admin.TravelGroup;
/*     */ import com.aof.model.admin.TravelGroupDetail;
/*     */ import com.aof.model.admin.query.TravelGroupDetailQueryCondition;
/*     */ import com.aof.model.admin.query.TravelGroupDetailQueryOrder;
/*     */ import java.io.Serializable;
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
/*     */ public class TravelGroupDetailDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements TravelGroupDetailDAO
/*     */ {
/*     */   private static final String SQL_COUNT = "select count(*) from TravelGroupDetail travelGroupDetail";
/*     */   private static final String SQL = "from TravelGroupDetail travelGroupDetail";
/*     */   
/*     */   public TravelGroupDetail getTravelGroupDetail(Integer travelGroup_id, Integer expenseSubCategroy_id) {
/*  31 */     TravelGroup tg = (TravelGroup)getHibernateTemplate().get(TravelGroup.class, travelGroup_id);
/*  32 */     ExpenseSubCategory esc = (ExpenseSubCategory)getHibernateTemplate().get(ExpenseSubCategory.class, expenseSubCategroy_id);
/*  33 */     TravelGroupDetail key = new TravelGroupDetail();
/*  34 */     key.setTravelGroup(tg);
/*  35 */     key.setExpenseSubCategory(esc);
/*  36 */     return (TravelGroupDetail)getHibernateTemplate().get(TravelGroupDetail.class, (Serializable)key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TravelGroupDetail updateTravelGroupDetail(TravelGroupDetail travelGroupDetail) {
/*  43 */     TravelGroupDetail oldTravelGroupDetail = getTravelGroupDetail(travelGroupDetail.getTravelGroup().getId(), 
/*  44 */         travelGroupDetail.getExpenseSubCategory().getId());
/*  45 */     if (oldTravelGroupDetail != null) {
/*  46 */       oldTravelGroupDetail.setAmountLimit(travelGroupDetail.getAmountLimit());
/*  47 */       oldTravelGroupDetail.setAbroadAmountLimit(travelGroupDetail.getAbroadAmountLimit());
/*  48 */       getHibernateTemplate().update(oldTravelGroupDetail);
/*  49 */       return oldTravelGroupDetail;
/*     */     } 
/*     */ 
/*     */     
/*  53 */     throw new RuntimeException("travelGroupDetail not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TravelGroupDetail insertTravelGroupDetail(TravelGroupDetail travelGroupDetail) {
/*  61 */     getHibernateTemplate().save(travelGroupDetail);
/*  62 */     return travelGroupDetail;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*     */ 
/*     */       
/*     */       {
/*     */ 
/*     */         
/*  75 */         TravelGroupDetailQueryCondition.EXPENSESUBCATEGORY_ID_EQ, "travelGroupDetail.expenseSubCategory.id = ?"
/*  76 */       }, { TravelGroupDetailQueryCondition.TRAVELGROUP_ID_EQ, "travelGroupDetail.travelGroup.id = ?"
/*  77 */       }, { TravelGroupDetailQueryCondition.EXPENSESUBCATEGORY_ISHOTEL_EQ, "travelGroupDetail.expenseSubCategory.isHotel = ?"
/*     */       
/*     */       },
/*     */       
/*     */       {
/*     */         
/*  83 */         TravelGroupDetailQueryCondition.AMOUNTLIMIT_EQ, "travelGroupDetail.amountLimit = ?"
/*     */       }
/*     */     };
/*  86 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*     */       
/*     */       {
/*     */         
/*  90 */         TravelGroupDetailQueryOrder.AMOUNTLIMIT, "travelGroupDetail.amountLimit"
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTravelGroupDetailListCount(Map conditions) {
/*  97 */     return getListCount(conditions, "select count(*) from TravelGroupDetail travelGroupDetail", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getTravelGroupDetailList(Map conditions, int pageNo, int pageSize, TravelGroupDetailQueryOrder order, boolean descend) {
/* 104 */     return getList(conditions, pageNo, pageSize, order, descend, "from TravelGroupDetail travelGroupDetail", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TravelGroupDetail insertOrUpdateTravelGroupDetail(TravelGroupDetail travelGroupDetail) {
/* 111 */     TravelGroupDetail oldTravelGroupDetail = getTravelGroupDetail(travelGroupDetail.getTravelGroup().getId(), 
/* 112 */         travelGroupDetail.getExpenseSubCategory().getId());
/* 113 */     if (oldTravelGroupDetail != null) {
/* 114 */       oldTravelGroupDetail.setAmountLimit(travelGroupDetail.getAmountLimit());
/* 115 */       oldTravelGroupDetail.setAbroadAmountLimit(travelGroupDetail.getAbroadAmountLimit());
/* 116 */       getHibernateTemplate().update(oldTravelGroupDetail);
/* 117 */       return oldTravelGroupDetail;
/*     */     } 
/*     */ 
/*     */     
/* 121 */     insertTravelGroupDetail(travelGroupDetail);
/* 122 */     return travelGroupDetail;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/TravelGroupDetailDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.TravelGroupDAO;
/*     */ import com.aof.dao.admin.TravelGroupDetailDAO;
/*     */ import com.aof.model.admin.ExpenseSubCategory;
/*     */ import com.aof.model.admin.TravelGroup;
/*     */ import com.aof.model.admin.TravelGroupDetail;
/*     */ import com.aof.model.admin.query.TravelGroupDetailQueryCondition;
/*     */ import com.aof.model.admin.query.TravelGroupQueryCondition;
/*     */ import com.aof.model.admin.query.TravelGroupQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.TravelGroupManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
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
/*     */ public class TravelGroupManagerImpl
/*     */   extends BaseManager
/*     */   implements TravelGroupManager
/*     */ {
/*     */   private TravelGroupDAO dao;
/*     */   private TravelGroupDetailDAO travelGroupDetailDAO;
/*     */   
/*     */   public TravelGroupDetailDAO getTravelGroupDetailDAO() {
/*  41 */     return this.travelGroupDetailDAO;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTravelGroupDetailDAO(TravelGroupDetailDAO travelGroupDetailDAO) {
/*  48 */     this.travelGroupDetailDAO = travelGroupDetailDAO;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTravelGroupDAO(TravelGroupDAO dao) {
/*  55 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TravelGroup getTravelGroup(Integer id) {
/*  62 */     return this.dao.getTravelGroup(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TravelGroup updateTravelGroup(TravelGroup function) {
/*  69 */     return this.dao.updateTravelGroup(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TravelGroup insertTravelGroup(TravelGroup function) {
/*  76 */     return this.dao.insertTravelGroup(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTravelGroupListCount(Map conditions) {
/*  84 */     return this.dao.getTravelGroupListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getTravelGroupList(Map conditions, int pageNo, int pageSize, TravelGroupQueryOrder order, boolean descend) {
/*  91 */     return this.dao.getTravelGroupList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertTravelGroup(TravelGroup travelGroup, List detailList) {
/*  98 */     insertTravelGroup(travelGroup);
/*  99 */     for (Iterator<TravelGroupDetail> iter = detailList.iterator(); iter.hasNext(); ) {
/* 100 */       TravelGroupDetail tgd = iter.next();
/* 101 */       this.travelGroupDetailDAO.insertTravelGroupDetail(tgd);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getDetailOf(TravelGroup travelGroup) {
/* 109 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 110 */     conds.put(TravelGroupDetailQueryCondition.TRAVELGROUP_ID_EQ, travelGroup.getId());
/* 111 */     return this.travelGroupDetailDAO.getTravelGroupDetailList(conds, 0, -1, null, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTravelGroup(TravelGroup travelGroup, List detailList) {
/* 118 */     updateTravelGroup(travelGroup);
/* 119 */     for (Iterator<TravelGroupDetail> iter = detailList.iterator(); iter.hasNext(); ) {
/* 120 */       TravelGroupDetail tgd = iter.next();
/* 121 */       this.travelGroupDetailDAO.insertOrUpdateTravelGroupDetail(tgd);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getAllEnabledTravelGroupListBySiteId(Integer siteId) {
/* 130 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 131 */     conditions.put(TravelGroupQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 132 */     conditions.put(TravelGroupQueryCondition.SITE_ID_EQ, siteId);
/* 133 */     return this.dao.getTravelGroupList(conditions, 0, -1, TravelGroupQueryOrder.NAME, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getAllEnabledTravelGroupListBySiteIdAndIncludeThis(Integer siteId, Integer id) {
/* 140 */     TravelGroup tg = this.dao.getTravelGroup(id);
/* 141 */     List<TravelGroup> l = getAllEnabledTravelGroupListBySiteId(siteId);
/* 142 */     if (tg == null) return l; 
/* 143 */     if (l.contains(tg)) return l; 
/* 144 */     for (int i = 0; i < l.size(); i++) {
/* 145 */       TravelGroup tg2 = l.get(i);
/* 146 */       if (tg2.getName().compareTo(tg.getName()) > 0) {
/* 147 */         l.add(i, tg);
/* 148 */         return l;
/*     */       } 
/*     */     } 
/* 151 */     l.add(tg);
/* 152 */     return l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TravelGroupDetail getHotelTravelGroupDetail(TravelGroup tg) {
/* 159 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 160 */     conds.put(TravelGroupDetailQueryCondition.TRAVELGROUP_ID_EQ, tg.getId());
/* 161 */     conds.put(TravelGroupDetailQueryCondition.EXPENSESUBCATEGORY_ISHOTEL_EQ, YesNo.YES.getEnumCode());
/* 162 */     List<TravelGroupDetail> l = this.travelGroupDetailDAO.getTravelGroupDetailList(conds, 0, -1, null, false);
/* 163 */     if (l.isEmpty())
/* 164 */       return null; 
/* 165 */     return l.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TravelGroupDetail getTravelGroupDetail(TravelGroup travelGroup, ExpenseSubCategory expenseSubCategory) {
/* 172 */     return this.travelGroupDetailDAO.getTravelGroupDetail(travelGroup.getId(), expenseSubCategory.getId());
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/TravelGroupManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
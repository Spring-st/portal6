/*    */ package com.aof.service.admin.impl;
/*    */ 
/*    */ import com.aof.dao.admin.CurrencyDAO;
/*    */ import com.aof.model.admin.Currency;
/*    */ import com.aof.model.admin.query.CurrencyQueryCondition;
/*    */ import com.aof.model.admin.query.CurrencyQueryOrder;
/*    */ import com.aof.model.metadata.EnabledDisabled;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.admin.CurrencyManager;
/*    */ import java.util.HashMap;
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
/*    */ public class CurrencyManagerImpl
/*    */   extends BaseManager
/*    */   implements CurrencyManager
/*    */ {
/*    */   private CurrencyDAO dao;
/*    */   
/*    */   public void setCurrencyDAO(CurrencyDAO dao) {
/* 35 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Currency getCurrency(String code) {
/* 42 */     return this.dao.getCurrency(code);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Currency insertCurrency(Currency currency) {
/* 49 */     return this.dao.insertCurrency(currency);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Currency updateCurrency(Currency currency) {
/* 56 */     return this.dao.updateCurrency(currency);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCurrencyListCount(Map conditions) {
/* 63 */     return this.dao.getCurrencyListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getCurrencyList(Map conditions, int pageNo, int pageSize, CurrencyQueryOrder order, boolean descend) {
/* 70 */     return this.dao.getCurrencyList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getAllEnabledCurrencyList() {
/* 77 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 78 */     conditions.put(CurrencyQueryCondition.STATUS_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 79 */     return this.dao.getCurrencyList(conditions, 0, -1, CurrencyQueryOrder.CODE, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getAllEnabledCurrencyListAndIncludeThis(String code) {
/* 86 */     Currency c = this.dao.getCurrency(code);
/* 87 */     List<Currency> l = getAllEnabledCurrencyList();
/* 88 */     if (c == null) return l; 
/* 89 */     if (l.contains(c)) return l; 
/* 90 */     for (int i = 0; i < l.size(); i++) {
/* 91 */       Currency c2 = l.get(i);
/* 92 */       if (c2.getCode().compareTo(c.getCode()) > 0) {
/* 93 */         l.add(i, c);
/* 94 */         return l;
/*    */       } 
/*    */     } 
/* 97 */     l.add(c);
/* 98 */     return l;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/CurrencyManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.aof.service.basic.impl;
/*     */ 
/*     */ import com.aof.dao.basic.CostCenterDAO;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.basic.CostCenter;
/*     */ import com.aof.model.basic.query.CostCenterQueryCondition;
/*     */ import com.aof.model.basic.query.CostCenterQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.basic.CostCenterManager;
/*     */ import com.aof.web.struts.action.ActionUtils;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
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
/*     */ public class CostCenterManagerImpl
/*     */   extends BaseManager
/*     */   implements CostCenterManager
/*     */ {
/*     */   private CostCenterDAO dao;
/*     */   
/*     */   public void setDao(CostCenterDAO dao) {
/*  40 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCostCenterDAO(CostCenterDAO dao) {
/*  47 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CostCenter getCostCenter(Integer id) {
/*  57 */     return this.dao.getCostCenter(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CostCenter updateCostCenter(CostCenter function) {
/*  68 */     return this.dao.updateCostCenter(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CostCenter insertCostCenter(CostCenter costCenter) {
/*  79 */     return this.dao.insertCostCenter(costCenter);
/*     */   }
/*     */   
/*     */   private String getLastCode(Site site, Date date) {
/*  83 */     StringBuffer sb = new StringBuffer("DN");
/*  84 */     String siteId = site.getId().toString();
/*  85 */     for (int i = 0; i < 3 - siteId.length(); i++)
/*  86 */       sb.append('0'); 
/*  87 */     sb.append(siteId);
/*  88 */     sb.append(StringUtils.right(ActionUtils.get8CharsFromDate(date), 6));
/*  89 */     String prefix = sb.toString();
/*  90 */     String maxId = this.dao.getMaxCostCenterCodeBeginWith(prefix);
/*     */     
/*  92 */     int serialNo = 1;
/*  93 */     if (maxId != null) {
/*  94 */       Integer maxSN = ActionUtils.parseInt(StringUtils.right(maxId, 5));
/*  95 */       if (maxSN == null)
/*  96 */         throw new RuntimeException("max serial no. is not digit"); 
/*  97 */       serialNo = maxSN.intValue() + 1;
/*     */     } 
/*  99 */     String sn = String.valueOf(serialNo);
/* 100 */     for (int j = 0; j < 5 - sn.length(); j++)
/* 101 */       sb.append('0'); 
/* 102 */     sb.append(sn);
/* 103 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCostCenterListCount(Map conditions) {
/* 114 */     return this.dao.getCostCenterListCount(conditions);
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
/*     */   public List getCostCenterList(Map conditions, int pageNo, int pageSize, CostCenterQueryOrder order, boolean descend) {
/* 126 */     return this.dao.getCostCenterList(conditions, pageNo, pageSize, order, 
/* 127 */         descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void promoteCostCenter(CostCenter costCenter) {
/* 138 */     updateCostCenter(costCenter);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean deleteCostCenter(CostCenter costCenter) {
/*     */     try {
/* 144 */       this.dao.deleteCostCenter(costCenter);
/* 145 */       return true;
/* 146 */     } catch (Throwable t) {
/* 147 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public List getCostCenterListEnabledAll() {
/* 152 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 153 */     conditions.put(CostCenterQueryCondition.ENABLED_EQ, 
/* 154 */         EnabledDisabled.ENABLED);
/* 155 */     return this.dao.getCostCenterList(conditions, -1, -1, null, true);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/impl/CostCenterManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
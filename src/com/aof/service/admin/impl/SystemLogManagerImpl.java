/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.UniversalDAO;
/*     */ import com.aof.dao.admin.SystemLogDAO;
/*     */ import com.aof.model.Loggable;
/*     */ import com.aof.model.admin.SystemLog;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.query.SystemLogQueryOrder;
/*     */ import com.aof.model.metadata.MetadataDetailEnum;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.SystemLogManager;
/*     */ import com.shcnc.utils.BeanHelper;
/*     */ import java.util.Collection;
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
/*     */ public class SystemLogManagerImpl
/*     */   extends BaseManager
/*     */   implements SystemLogManager
/*     */ {
/*     */   private SystemLogDAO dao;
/*     */   private UniversalDAO universalDAO;
/*     */   
/*     */   public void setSystemLogDAO(SystemLogDAO dao) {
/*  38 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public void setUniversalDAO(UniversalDAO universalDAO) {
/*  42 */     this.universalDAO = universalDAO;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SystemLog getSystemLog(Integer id) {
/*  49 */     return this.dao.getSystemLog(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SystemLog insertSystemLog(SystemLog systemLog) {
/*  56 */     return this.dao.insertSystemLog(systemLog);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSystemLogListCount(Map conditions) {
/*  63 */     return this.dao.getSystemLogListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getSystemLogList(Map conditions, int pageNo, int pageSize, SystemLogQueryOrder order, boolean descend) {
/*  70 */     return this.dao.getSystemLogList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateLog(Loggable oldTarget, Loggable newTarget, String action, User currentUser) {
/*  77 */     String[][] fieldInfos = newTarget.getLogFieldInfo(action);
/*  78 */     if (fieldInfos == null) {
/*  79 */       throw new RuntimeException("Action '" + action + "' not supported for log object '" + newTarget.getClass().getName() + "'");
/*     */     }
/*  81 */     if (fieldInfos.length == 0) {
/*  82 */       SystemLog systemLog = new SystemLog();
/*  83 */       systemLog.setAction(action);
/*  84 */       systemLog.setSite(newTarget.getLogSite());
/*  85 */       systemLog.setTarget(newTarget.getLogTargetName());
/*  86 */       systemLog.setTargetId(newTarget.getLogTargetId());
/*  87 */       systemLog.setUser(currentUser);
/*  88 */       insertSystemLog(systemLog);
/*     */     } else {
/*  90 */       for (int i = 0; i < fieldInfos.length; i++) {
/*  91 */         String[] fieldInfo = fieldInfos[i];
/*  92 */         String fieldName = fieldInfo[1];
/*  93 */         boolean logOldValue = false;
/*  94 */         Object oldValue = null;
/*  95 */         Object newValue = BeanHelper.getBeanPropertyValue(newTarget, fieldName);
/*  96 */         if (oldTarget != null) {
/*  97 */           oldValue = BeanHelper.getBeanPropertyValue(oldTarget, fieldName);
/*  98 */           if (oldValue == null) {
/*  99 */             if (newValue == null) {
/*     */               continue;
/*     */             }
/* 102 */             logOldValue = true;
/*     */           } else {
/* 104 */             if (newValue != null && 
/* 105 */               oldValue.equals(newValue)) {
/*     */               continue;
/*     */             }
/*     */             
/* 109 */             logOldValue = true;
/*     */           } 
/*     */         } 
/*     */         
/* 113 */         SystemLog systemLog = new SystemLog();
/* 114 */         systemLog.setAction(action);
/* 115 */         systemLog.setSite(newTarget.getLogSite());
/* 116 */         systemLog.setTarget(newTarget.getLogTargetName());
/* 117 */         systemLog.setTargetId(newTarget.getLogTargetId());
/* 118 */         systemLog.setUser(currentUser);
/*     */         
/* 120 */         StringBuffer content = new StringBuffer();
/* 121 */         content.append(fieldInfo[0]).append(':');
/*     */         
/* 123 */         String logPropertyName = fieldInfo[2];
/* 124 */         if (logOldValue) {
/* 125 */           if (oldValue instanceof Collection) {
/* 126 */             Iterator itor = ((Collection)oldValue).iterator(); while (true) {
/* 127 */               Object element = itor.next();
/* 128 */               appendToContent(content, element, logPropertyName, false);
/* 129 */               if (itor.hasNext()) {
/* 130 */                 content.append(',');
/*     */                 continue;
/*     */               } 
/*     */               break;
/*     */             } 
/*     */           } else {
/* 136 */             appendToContent(content, oldValue, logPropertyName, false);
/*     */           } 
/* 138 */           content.append(" -> ");
/*     */         } 
/* 140 */         if (newValue instanceof Collection) {
/* 141 */           Iterator itor = ((Collection)newValue).iterator(); while (true) {
/* 142 */             Object element = itor.next();
/* 143 */             appendToContent(content, element, logPropertyName, true);
/* 144 */             if (itor.hasNext()) {
/* 145 */               content.append(',');
/*     */               continue;
/*     */             } 
/*     */             break;
/*     */           } 
/*     */         } else {
/* 151 */           appendToContent(content, newValue, logPropertyName, true);
/*     */         } 
/* 153 */         systemLog.setContent(content.toString());
/* 154 */         insertSystemLog(systemLog);
/*     */         continue;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void appendToContent(StringBuffer content, Object element, String logPropertyName, boolean reload) {
/* 160 */     if (element == null) {
/* 161 */       content.append(element);
/*     */       return;
/*     */     } 
/* 164 */     Object logProperty = element;
/* 165 */     if (logPropertyName != null) {
/* 166 */       logProperty = BeanHelper.getBeanPropertyValue(element, logPropertyName);
/* 167 */       if (logProperty == null && reload) {
/* 168 */         this.universalDAO.refresh(element);
/* 169 */         logProperty = BeanHelper.getBeanPropertyValue(element, logPropertyName);
/*     */       } 
/*     */     } 
/* 172 */     if (logProperty instanceof MetadataDetailEnum) {
/* 173 */       content.append(((MetadataDetailEnum)logProperty).getEngShortDescription());
/*     */       return;
/*     */     } 
/* 176 */     content.append(logProperty);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/SystemLogManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
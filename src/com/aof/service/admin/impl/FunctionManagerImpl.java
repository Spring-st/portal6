/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.FunctionDAO;
/*     */ import com.aof.model.admin.Function;
/*     */ import com.aof.model.admin.query.FunctionQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.FunctionManager;
/*     */ import com.aof.web.struts.action.SecureActionMapping;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.struts.action.ActionMapping;
/*     */ import org.apache.struts.config.ActionConfig;
/*     */ import org.apache.struts.config.ModuleConfig;
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
/*     */ public class FunctionManagerImpl
/*     */   extends BaseManager
/*     */   implements FunctionManager
/*     */ {
/*  34 */   private Log log = LogFactory.getLog(FunctionManagerImpl.class);
/*     */   
/*     */   private FunctionDAO dao;
/*     */   
/*     */   private Map functions;
/*     */   
/*     */   private Map specialFunctions;
/*     */   
/*  42 */   private ModuleConfig lastConfig = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFunctionDAO(FunctionDAO dao) {
/*  50 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInitiated() {
/*  59 */     return (this.lastConfig != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initiate(ModuleConfig config) {
/*  68 */     if (this.lastConfig == config) {
/*     */       return;
/*     */     }
/*  71 */     synchronized (this) {
/*  72 */       if (this.lastConfig == config)
/*     */         return; 
/*  74 */       if (this.lastConfig != null)
/*  75 */         this.log.info("struts config changed, re-initiate"); 
/*  76 */       ActionConfig[] configs = config.findActionConfigs();
/*  77 */       if (this.functions == null) {
/*  78 */         this.functions = new HashMap<Object, Object>();
/*  79 */         this.specialFunctions = new HashMap<Object, Object>();
/*     */       } else {
/*  81 */         this.functions.clear();
/*  82 */         this.specialFunctions.clear();
/*     */       } 
/*  84 */       for (int i = 0; i < configs.length; i++) {
/*  85 */         if (configs[i] instanceof SecureActionMapping) {
/*  86 */           SecureActionMapping mapping = (SecureActionMapping)configs[i];
/*  87 */           Integer functionId = mapping.getFunctionId();
/*  88 */           if (functionId != null) {
/*     */             
/*  90 */             String description = mapping.getFunctionDescription();
/*  91 */             String level = mapping.getLevel();
/*  92 */             Function f = (Function)this.functions.get(functionId);
/*  93 */             if (f == null) {
/*  94 */               f = new Function(functionId);
/*  95 */               f.setDescription(description);
/*  96 */               f.setLevel(level);
/*  97 */               this.functions.put(functionId, f);
/*     */             } else {
/*  99 */               if (description != null) {
/* 100 */                 if (f.getDescription() != null) {
/* 101 */                   throw new RuntimeException("Description duplicate defined for function id " + functionId + " in action mapping '" + 
/* 102 */                       mapping.getPath() + "'. In struts config, each function id can only has one description definition.");
/*     */                 }
/* 104 */                 f.setDescription(description);
/*     */               } 
/* 106 */               if (level != null) {
/* 107 */                 if (f.getLevel() != null) {
/* 108 */                   throw new RuntimeException("Level duplicate defined for function id " + functionId + " in action mapping '" + mapping.getPath() + 
/* 109 */                       "'. In struts config, each function id can only has one level definition.");
/*     */                 }
/* 111 */                 f.setLevel(level);
/*     */               } 
/*     */             } 
/* 114 */             f.addActionMapping((ActionMapping)mapping);
/*     */             
/* 116 */             String functionType = mapping.getFunctionType();
/* 117 */             if (functionType != null) {
/* 118 */               Function f2 = this.specialFunctions.put(functionType, f);
/* 119 */               if (f2 != null && !f.equals(f2)) {
/* 120 */                 throw new RuntimeException("Type '" + functionType + "' duplicate defined.");
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 126 */       Iterator<Function> itor = this.functions.values().iterator();
/* 127 */       while (itor.hasNext()) {
/* 128 */         Function f = itor.next();
/* 129 */         if (f.getDescription() == null) {
/* 130 */           switch (f.getId().intValue()) {
/*     */             case 1:
/* 132 */               f.setDescription("Common Global Function");
/*     */               break;
/*     */             case 2:
/* 135 */               f.setDescription("Common Site Function");
/*     */               break;
/*     */             case 3:
/* 138 */               f.setDescription("Common Department Function");
/*     */               break;
/*     */             default:
/* 141 */               throw new RuntimeException("Description definition missing for function id " + f.getId() + 
/* 142 */                   ". In struts config, each function id must has one description definition.");
/*     */           } 
/*     */         }
/* 145 */         if (f.getLevel() == null) {
/* 146 */           switch (f.getId().toString().charAt(0)) {
/*     */             case '1':
/* 148 */               f.setLevel("g");
/*     */               break;
/*     */             case '2':
/* 151 */               f.setLevel("s");
/*     */               break;
/*     */             case '3':
/* 154 */               f.setLevel("d");
/*     */               break;
/*     */             default:
/* 157 */               throw new RuntimeException("Level definition missing for function id " + f.getId() + 
/* 158 */                   ". In struts config, each function id must has one level definition.");
/*     */           } 
/*     */         
/*     */         }
/* 162 */         Function f2 = this.dao.getFunction(f.getId());
/* 163 */         if (f2 == null) {
/* 164 */           f.setName(f.getDescription());
/* 165 */           this.dao.saveFunction(f); continue;
/*     */         } 
/* 167 */         f.setName(f2.getName());
/* 168 */         f.setDescription(f2.getDescription());
/*     */       } 
/*     */       
/* 171 */       this.lastConfig = config;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Function getFunction(String functionType) {
/* 181 */     if (this.lastConfig == null) {
/* 182 */       throw new RuntimeException("use function manager before it initiated.");
/*     */     }
/* 184 */     return (Function)this.specialFunctions.get(functionType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Function getFunction(Integer id) {
/* 193 */     if (this.lastConfig == null) {
/* 194 */       throw new RuntimeException("use function manager before it initiated.");
/*     */     }
/* 196 */     Function f = (Function)this.functions.get(id);
/* 197 */     if (f == null) {
/* 198 */       throw new RuntimeException("Try to load function with id '" + id + "', it's not found in current struts config.");
/*     */     }
/* 200 */     Function f2 = this.dao.getFunction(id);
/* 201 */     f2.setActionMappings(f.getActionMappings());
/* 202 */     f2.setLevel(f.getLevel());
/* 203 */     return f2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Function saveFunction(Function function) {
/* 212 */     if (this.lastConfig == null) {
/* 213 */       throw new RuntimeException("use function manager before it initiated.");
/*     */     }
/* 215 */     Integer id = function.getId();
/* 216 */     if (this.functions.get(id) == null) {
/* 217 */       throw new RuntimeException("try to save function with id '" + id + "', it's not found in current struts config.");
/*     */     }
/* 219 */     return this.dao.saveFunction(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFunctionListCount(Map conditions) {
/* 228 */     if (this.lastConfig == null) {
/* 229 */       throw new RuntimeException("use function manager before it initiated.");
/*     */     }
/* 231 */     return this.dao.getFunctionListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getFunctionList(Map conditions, int pageNo, int pageSize, FunctionQueryOrder order, boolean descend) {
/* 241 */     if (this.lastConfig == null) {
/* 242 */       throw new RuntimeException("use function manager before it initiated.");
/*     */     }
/* 244 */     List result = this.dao.getFunctionList(conditions, pageNo, pageSize, order, descend);
/* 245 */     Iterator<Function> itor = result.iterator();
/* 246 */     while (itor.hasNext()) {
/* 247 */       Function function = itor.next();
/* 248 */       Integer id = function.getId();
/* 249 */       Function f = (Function)this.functions.get(id);
/* 250 */       if (f == null) {
/* 251 */         itor.remove(); continue;
/*     */       } 
/* 253 */       function.setActionMappings(f.getActionMappings());
/* 254 */       function.setLevel(f.getLevel());
/*     */     } 
/*     */     
/* 257 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getAllFunction() {
/* 266 */     return getFunctionList(null, 0, -1, FunctionQueryOrder.NAME, false);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/FunctionManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
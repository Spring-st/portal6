/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.RoleDAO;
/*     */ import com.aof.model.admin.Function;
/*     */ import com.aof.model.admin.Role;
/*     */ import com.aof.model.admin.RoleFunction;
/*     */ import com.aof.model.admin.query.RoleQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.RoleManager;
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
/*     */ 
/*     */ 
/*     */ public class RoleManagerImpl
/*     */   extends BaseManager
/*     */   implements RoleManager
/*     */ {
/*     */   private RoleDAO dao;
/*     */   
/*     */   public void setRoleDAO(RoleDAO dao) {
/*  36 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Role getRole(Integer id) throws Exception {
/*  43 */     return this.dao.getRole(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Role saveRole(Role role) throws Exception {
/*  50 */     return this.dao.saveRole(role);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeRole(Integer id) throws Exception {
/*  57 */     this.dao.removeRole(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRoleListCount(Map conditions) throws Exception {
/*  64 */     return this.dao.getRoleListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getRoleList(Map conditions, int pageNo, int pageSize, RoleQueryOrder order, boolean descend) throws Exception {
/*  71 */     return this.dao.getRoleList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getAllRoleList() throws Exception {
/*  78 */     return this.dao.getRoleList(null, 0, -1, RoleQueryOrder.NAME, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getFunctionListByRole(Role role) throws Exception {
/*  85 */     return this.dao.getFunctionListByRole(role);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveFunctionListForRole(Role role, List<Function> functionList) throws Exception {
/*  92 */     Iterator<Function> itor = this.dao.getFunctionListByRole(role).iterator();
/*  93 */     while (itor.hasNext()) {
/*  94 */       Function f = itor.next();
/*  95 */       int i = functionList.indexOf(f);
/*  96 */       if (i == -1) {
/*  97 */         RoleFunction rf = new RoleFunction();
/*  98 */         rf.setRole(role);
/*  99 */         rf.setFunction(f);
/* 100 */         this.dao.removeRoleFunction(rf); continue;
/*     */       } 
/* 102 */       functionList.remove(i);
/*     */     } 
/*     */     
/* 105 */     itor = functionList.iterator();
/* 106 */     while (itor.hasNext()) {
/* 107 */       RoleFunction rf = new RoleFunction();
/* 108 */       rf = new RoleFunction();
/* 109 */       rf.setRole(role);
/* 110 */       rf.setFunction(itor.next());
/* 111 */       this.dao.saveRoleFunction(rf);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/RoleManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
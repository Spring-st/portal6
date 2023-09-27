/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.Menu;
/*     */ import com.aof.service.admin.FunctionManager;
/*     */ import com.aof.service.admin.MenuManager;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
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
/*     */ public class MenuAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  45 */     MenuManager mm = ServiceLocator.getMenuManager(request);
/*  46 */     request.setAttribute("X_MENUXML", mm.getMenuXml(getLocale(request)));
/*  47 */     return mapping.findForward("page");
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
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  60 */     MenuManager mm = ServiceLocator.getMenuManager(request);
/*  61 */     FunctionManager fm = ServiceLocator.getFunctionManager(request);
/*  62 */     Integer parentId = ActionUtils.parseInt(request.getParameter("parentId"));
/*  63 */     Menu parent = null;
/*  64 */     if (parentId != null) {
/*  65 */       parent = mm.getMenu(parentId);
/*  66 */       if (parent == null) throw new ActionException("menu.notFound", parentId); 
/*  67 */       request.setAttribute("X_PARENT", parent);
/*     */     } 
/*  69 */     if (!isBack(request)) {
/*  70 */       Menu m = new Menu();
/*  71 */       m.setParentMenu(parent);
/*  72 */       BeanForm menuForm = (BeanForm)getForm("/insertMenu", request);
/*  73 */       menuForm.populateToForm(m);
/*     */     } 
/*  75 */     List functionList = fm.getAllFunction();
/*  76 */     request.setAttribute("X_FUNCTIONLIST", functionList);
/*  77 */     return mapping.findForward("page");
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  90 */     MenuManager mm = ServiceLocator.getMenuManager(request);
/*  91 */     BeanForm menuForm = (BeanForm)form;
/*  92 */     Menu m = new Menu();
/*  93 */     menuForm.populateToBean(m);
/*  94 */     request.setAttribute("X_OBJECT", mm.saveMenu(m));
/*     */     
/*  96 */     return mapping.findForward("success");
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
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 109 */     FunctionManager fm = ServiceLocator.getFunctionManager(request);
/* 110 */     if (!isBack(request)) {
/* 111 */       Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 112 */       MenuManager mm = ServiceLocator.getMenuManager(request);
/* 113 */       Menu m = mm.getMenu(id);
/* 114 */       if (m == null) throw new ActionException("menu.notFound", id); 
/* 115 */       BeanForm menuForm = (BeanForm)getForm("/updateMenu", request);
/* 116 */       menuForm.populateToForm(m);
/*     */     } 
/* 118 */     List functionList = fm.getAllFunction();
/* 119 */     request.setAttribute("X_FUNCTIONLIST", functionList);
/* 120 */     return mapping.findForward("page");
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
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 133 */     MenuManager mm = ServiceLocator.getMenuManager(request);
/* 134 */     BeanForm menuForm = (BeanForm)form;
/* 135 */     Integer id = ActionUtils.parseInt((String)menuForm.get("id"));
/* 136 */     Menu m = mm.getMenu(id);
/* 137 */     if (m == null) throw new ActionException("menu.notFound", id); 
/* 138 */     menuForm.populateToBean(m);
/* 139 */     request.setAttribute("X_OBJECT", mm.saveMenu(m));
/*     */     
/* 141 */     return mapping.findForward("success");
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
/*     */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 154 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 155 */     MenuManager mm = ServiceLocator.getMenuManager(request);
/* 156 */     mm.removeMenu(id);
/* 157 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/MenuAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
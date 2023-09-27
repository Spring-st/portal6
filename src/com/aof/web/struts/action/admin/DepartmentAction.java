/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.admin.DepartmentManager;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.DepartmentQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.struts.form.ComboBox;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
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
/*     */ public class DepartmentAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  51 */     DepartmentQueryForm queryForm = (DepartmentQueryForm)form;
/*  52 */     ComboBox comboSite = queryForm.getSite();
/*  53 */     comboSite.setList(getAndCheckGrantedSiteList(request));
/*  54 */     DepartmentManager dm = ServiceLocator.getDepartmentManager(request);
/*  55 */     request.setAttribute("X_DEPARTMENTXML", dm.getDepartmentXmlBySite(ActionUtils.parseInt(comboSite.getValue())));
/*  56 */     return mapping.findForward("page");
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
/*  69 */     Site s = getAndCheckSite("siteId", request);
/*  70 */     if (!isBack(request)) {
/*  71 */       DepartmentManager dm = ServiceLocator.getDepartmentManager(request);
/*  72 */       Integer parentId = ActionUtils.parseInt(request.getParameter("parentId"));
/*  73 */       Department parent = null;
/*  74 */       if (parentId != null) {
/*  75 */         parent = dm.getDepartment(parentId);
/*  76 */         if (parent == null) throw new ActionException("department.notFound", parentId); 
/*     */       } 
/*  78 */       Department d = new Department();
/*  79 */       d.setSite(s);
/*  80 */       d.setParentDepartment(parent);
/*  81 */       BeanForm departmentForm = (BeanForm)getForm("/insertDepartment", request);
/*  82 */       departmentForm.populateToForm(d);
/*     */     } 
/*  84 */     prepareData(s, request, (Department)null);
/*  85 */     return mapping.findForward("page");
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
/*  98 */     DepartmentManager dm = ServiceLocator.getDepartmentManager(request);
/*  99 */     BeanForm departmentForm = (BeanForm)form;
/* 100 */     Department d = new Department();
/* 101 */     departmentForm.populateToBean(d);
/* 102 */     checkSite(d.getSite().getId(), request);
/* 103 */     request.setAttribute("X_OBJECT", dm.saveDepartment(d));
/*     */     
/* 105 */     return mapping.findForward("success");
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
/* 118 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 119 */     DepartmentManager dm = ServiceLocator.getDepartmentManager(request);
/* 120 */     Department d = dm.getDepartment(id);
/* 121 */     if (d == null) throw new ActionException("department.notFound", id); 
/* 122 */     Site s = d.getSite();
/* 123 */     checkSite(s, request);
/* 124 */     if (!isBack(request)) {
/* 125 */       BeanForm departmentForm = (BeanForm)getForm("/updateDepartment", request);
/* 126 */       departmentForm.populate(d, "to_form");
/*     */     } 
/* 128 */     prepareData(s, request, d);
/* 129 */     request.setAttribute("X_ENABLEDUSERLIST", ServiceLocator.getUserManager(request).getEnabledUserListOfDepartment(d));
/* 130 */     return mapping.findForward("page");
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
/* 143 */     DepartmentManager dm = ServiceLocator.getDepartmentManager(request);
/* 144 */     BeanForm departmentForm = (BeanForm)form;
/* 145 */     Integer id = ActionUtils.parseInt((String)departmentForm.get("id"));
/* 146 */     Department d = dm.getDepartment(id);
/* 147 */     if (d == null) throw new ActionException("department.notFound", id); 
/* 148 */     checkSite(d.getSite(), request);
/*     */ 
/*     */     
/* 151 */     departmentForm.populateToBean(d, null, new String[] { "site.id" });
/*     */     
/* 153 */     request.setAttribute("X_OBJECT", dm.saveDepartment(d));
/*     */     
/* 155 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private void prepareData(Site s, HttpServletRequest request, Department me) {
/* 159 */     DepartmentManager dm = ServiceLocator.getDepartmentManager(request);
/* 160 */     dm.fillDepartment(s, false);
/* 161 */     Set<Department> children = null;
/* 162 */     if (me != null) {
/* 163 */       children = new HashSet();
/* 164 */       children.add(me);
/*     */     } 
/* 166 */     for (Iterator<Department> itor = s.getDepartments().iterator(); itor.hasNext(); ) {
/* 167 */       Department d = itor.next();
/* 168 */       if (me != null) {
/* 169 */         if (d.equals(me)) {
/* 170 */           itor.remove();
/*     */           continue;
/*     */         } 
/* 173 */         Department pd = d.getParentDepartment();
/* 174 */         if (children.contains(pd)) {
/* 175 */           itor.remove();
/* 176 */           children.add(d);
/*     */           continue;
/*     */         } 
/*     */       } 
/* 180 */       d.setIndentName("��" + d.getIndentName());
/*     */     } 
/* 182 */     request.setAttribute("X_SITE", s);
/* 183 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/DepartmentAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
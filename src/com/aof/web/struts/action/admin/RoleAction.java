/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.Function;
/*     */ import com.aof.model.admin.Role;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.query.RoleQueryCondition;
/*     */ import com.aof.model.admin.query.RoleQueryOrder;
/*     */ import com.aof.model.metadata.MetadataDetailEnum;
/*     */ import com.aof.model.metadata.RoleType;
/*     */ import com.aof.service.admin.FunctionManager;
/*     */ import com.aof.service.admin.RoleManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.RoleQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
/*     */ import org.apache.struts.util.MessageResources;
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
/*     */ public class RoleAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  60 */     RoleManager rm = ServiceLocator.getRoleManager(request);
/*     */     
/*  62 */     RoleQueryForm queryForm = (RoleQueryForm)form;
/*  63 */     List<MetadataDetailEnum> l = PersistentEnum.getEnumList(RoleType.class);
/*  64 */     l.add(0, MetadataDetailEnum.DUMMY);
/*  65 */     queryForm.getType().setList(l);
/*     */     
/*  67 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/*  69 */     String exportType = queryForm.getExportType();
/*  70 */     if (exportType != null && exportType.length() > 0) {
/*  71 */       List data = rm.getRoleList(conditions, 0, -1, RoleQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  72 */       int index = SessionTempFile.createNewTempFile(request);
/*  73 */       String fileName = "role";
/*  74 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  77 */               MessageResources messages = RoleAction.this.getResources(request);
/*  78 */               row.add(messages.getMessage(RoleAction.this.getLocale(request), "role.name"));
/*  79 */               row.add(messages.getMessage(RoleAction.this.getLocale(request), "role.type"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  83 */               row.add(BeanUtils.getProperty(data, "name"));
/*  84 */               String locale = RoleAction.this.getCurrentUser(request).getLocale();
/*  85 */               if ("en".equals(locale)) {
/*  86 */                 row.add(BeanUtils.getProperty(data, "type.engShortDescription"));
/*     */               } else {
/*  88 */                 row.add(BeanUtils.getProperty(data, "type.chnShortDescription"));
/*     */               } 
/*     */             }
/*     */           });
/*  92 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/*  95 */     if (queryForm.isFirstInit()) {
/*  96 */       queryForm.init(rm.getRoleListCount(conditions));
/*     */     } else {
/*  98 */       queryForm.init();
/*     */     } 
/*     */     
/* 101 */     request.setAttribute("X_RESULTLIST", rm.getRoleList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), RoleQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend()));
/* 102 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(RoleQueryForm queryForm) {
/* 106 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/* 108 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 109 */     if (id != null) conditions.put(RoleQueryCondition.ID_EQ, id);
/*     */     
/* 111 */     String name = queryForm.getName();
/* 112 */     if (name != null) {
/* 113 */       name = name.trim();
/* 114 */       if (name.length() == 0) name = null; 
/*     */     } 
/* 116 */     if (name != null) conditions.put(RoleQueryCondition.NAME_LIKE, name);
/*     */     
/* 118 */     if (!queryForm.getType().isEmpty()) {
/* 119 */       Integer type = ActionUtils.parseInt(queryForm.getType().getValue());
/* 120 */       if (type != null && type.intValue() != -1) conditions.put(RoleQueryCondition.TYPE_EQ, type);
/*     */     
/*     */     } 
/* 123 */     return conditions;
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
/* 136 */     if (!isBack(request)) {
/* 137 */       Role r = new Role();
/* 138 */       BeanForm roleForm = (BeanForm)getForm("/insertRole", request);
/* 139 */       roleForm.populateToForm(r);
/*     */     } 
/* 141 */     request.setAttribute("X_ROLETYPELIST", PersistentEnum.getEnumList(RoleType.class));
/* 142 */     return mapping.findForward("page");
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
/* 155 */     RoleManager rm = ServiceLocator.getRoleManager(request);
/* 156 */     BeanForm roleForm = (BeanForm)form;
/* 157 */     Role r = new Role();
/* 158 */     roleForm.populateToBean(r);
/* 159 */     request.setAttribute("X_OBJECT", rm.saveRole(r));
/* 160 */     request.setAttribute("X_ROWPAGE", "role/row.jsp");
/*     */     
/* 162 */     return mapping.findForward("success");
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
/* 175 */     if (!isBack(request)) {
/* 176 */       Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 177 */       RoleManager rm = ServiceLocator.getRoleManager(request);
/* 178 */       Role r = rm.getRole(id);
/* 179 */       if (r == null) throw new ActionException("role.notFound", id); 
/* 180 */       BeanForm roleForm = (BeanForm)getForm("/updateRole", request);
/* 181 */       roleForm.populateToForm(r);
/*     */     } 
/* 183 */     request.setAttribute("X_ROLETYPELIST", PersistentEnum.getEnumList(RoleType.class));
/* 184 */     return mapping.findForward("page");
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
/* 197 */     RoleManager rm = ServiceLocator.getRoleManager(request);
/* 198 */     BeanForm roleForm = (BeanForm)form;
/* 199 */     Integer id = ActionUtils.parseInt((String)roleForm.get("id"));
/* 200 */     Role r = rm.getRole(id);
/* 201 */     if (r == null) throw new ActionException("role.notFound", id); 
/* 202 */     roleForm.populateToBean(r);
/* 203 */     request.setAttribute("X_OBJECT", rm.saveRole(r));
/* 204 */     request.setAttribute("X_ROWPAGE", "role/row.jsp");
/*     */     
/* 206 */     return mapping.findForward("success");
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
/*     */   public ActionForward grantFunction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 219 */     RoleManager rm = ServiceLocator.getRoleManager(request);
/* 220 */     FunctionManager fm = ServiceLocator.getFunctionManager(request);
/*     */     
/* 222 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 223 */     Role r = rm.getRole(id);
/* 224 */     if (r == null) throw new ActionException("role.notFound", id);
/*     */     
/* 226 */     if ("POST".equals(request.getMethod())) {
/* 227 */       String[] strFunctionIds = request.getParameterValues("granted");
/* 228 */       List<Function> functionList = new ArrayList();
/* 229 */       if (strFunctionIds != null)
/* 230 */         for (int i = 0; i < strFunctionIds.length; i++) {
/* 231 */           Integer functionId = ActionUtils.parseInt(strFunctionIds[i]);
/* 232 */           Function f = fm.getFunction(functionId);
/* 233 */           if (f != null) functionList.add(f);
/*     */         
/*     */         }  
/* 236 */       rm.saveFunctionListForRole(r, functionList);
/*     */     } 
/* 238 */     request.setAttribute("X_OBJECT", r);
/*     */     
/* 240 */     List<?> grantedFunction = rm.getFunctionListByRole(r);
/*     */     
/* 242 */     List availableFunction = fm.getAllFunction();
/* 243 */     availableFunction.removeAll(grantedFunction);
/* 244 */     request.setAttribute("X_GRANTEDFUNCTION", grantedFunction);
/* 245 */     request.setAttribute("X_AVAILABLEFUNCTION", availableFunction);
/* 246 */     return mapping.findForward("page");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/RoleAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
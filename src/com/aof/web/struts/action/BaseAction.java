/*     */ package com.aof.web.struts.action;
/*     */ 
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.Function;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.MetadataDetailEnum;
/*     */ import com.aof.model.po.query.BoxQueryCondition;
/*     */ import com.aof.model.query.AutoArrayList;
/*     */ import com.aof.model.query.BasicConditionModel;
/*     */ import com.aof.model.query.BasicConditionType;
/*     */ import com.aof.model.query.BasicQueryCondition;
/*     */ import com.aof.service.admin.DepartmentManager;
/*     */ import com.aof.service.admin.FunctionManager;
/*     */ import com.aof.service.admin.SiteManager;
/*     */ import com.aof.service.admin.UserManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.po.BoxQueryForm;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.BaseAction;
/*     */ import com.shcnc.utils.BeanHelper;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.commons.collections.FastHashMap;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
/*     */ import org.apache.struts.action.ActionMessage;
/*     */ import org.apache.struts.action.ActionMessages;
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
/*     */ public abstract class BaseAction
/*     */   extends BaseAction
/*     */ {
/*     */   protected Object getObjectFromRequest(String parameterName, Class clazz, String idName, HttpServletRequest request) throws Exception {
/*  71 */     String str_param_value = request.getParameter(parameterName);
/*  72 */     if (str_param_value == null)
/*  73 */       throw new ActionException("errors.parameter.notSet", parameterName); 
/*  74 */     Class idType = BeanHelper.getProperyType(clazz, idName);
/*  75 */     BeanLoader loader = ServiceLocator.getBeanLoader(request);
/*  76 */     Object o = null;
/*  77 */     Serializable idValue = null;
/*  78 */     if (idType.equals(String.class)) {
/*     */       
/*  80 */       idValue = str_param_value;
/*     */     }
/*  82 */     else if (idType.equals(Integer.class)) {
/*     */       
/*  84 */       idValue = ActionUtils.parseInt(str_param_value);
/*  85 */       if (idValue == null) throw new ActionException("errors.parameter.notInteger", parameterName); 
/*     */     } else {
/*  87 */       throw new RuntimeException("unsupported id type: " + idType);
/*     */     } 
/*  89 */     o = loader.load(clazz, idName, idValue);
/*  90 */     return o;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object getObjectFromRequestAndCheckExists(String parameterName, Class clazz, String idName, HttpServletRequest request) throws Exception {
/*  95 */     Object o = getObjectFromRequest(parameterName, clazz, idName, request);
/*  96 */     if (o == null)
/*  97 */       throw new ActionException(String.valueOf(getSimpleClassName(clazz)) + ".notFound", request.getParameter(parameterName)); 
/*  98 */     return o;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int intCheng(Object obj) {
/* 109 */     if (obj == null || obj == "") {
/* 110 */       return 0;
/*     */     }
/* 112 */     return Integer.parseInt(obj.toString());
/*     */   }
/*     */   
/*     */   private String getSimpleClassName(Class clazz) {
/* 116 */     String name = clazz.getName();
/* 117 */     int index = name.lastIndexOf('.');
/* 118 */     if (index != -1) name = name.substring(index + 1); 
/* 119 */     return StringUtils.uncapitalize(name);
/*     */   }
/* 121 */   public static final String ATTR_VERSION = "x_version"; public static final String ATTR_LANG = "x_lang"; public static final String LANG_CHN = "chn"; public static final String LANG_ENG = "eng"; protected static Log log = LogFactory.getLog(BaseAction.class); public static final String POSTFIX_GLOBAL = ""; public static final String POSTFIX_SITE = "_site"; public static final String POSTFIX_DEPARTMENT = "_department";
/*     */   public static final String FORWARD_PAGE = "page";
/*     */   
/*     */   protected String[] getParameterValues(String parameterName, HttpServletRequest request) {
/* 125 */     String[] retVal = request.getParameterValues(parameterName);
/* 126 */     if (retVal == null)
/* 127 */       return new String[0]; 
/* 128 */     return retVal;
/*     */   }
/*     */   public static final String FORWARD_SUCCESS = "success"; public static final String FORWARD_FAIL = "fail"; public static final String ATTR_RESULTLIST = "X_RESULTLIST"; public static final String ATTR_SITELIST = "x_siteList"; public static final String ATTR_ROWPAGE = "X_ROWPAGE";
/*     */   public static final String ATTR_OBJECT = "X_OBJECT";
/*     */   private static final String LOGIN_ACTION = "/login.do";
/*     */   private static final String ATTR_FUNCTION = "com.shcnc.struts.action.BaseAction.function";
/*     */   public static final String CONFIRM = "confirm";
/*     */   
/*     */   protected boolean isEnglish(HttpServletRequest request) {
/* 137 */     return getCurrentUser(request).getLocale().equals("en");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void postGlobalMessage(String messageKey, Object arg, HttpSession session) {
/* 142 */     ActionMessage message = (arg == null) ? new ActionMessage(messageKey) : new ActionMessage(messageKey, arg);
/* 143 */     ActionMessages messages = new ActionMessages();
/* 144 */     messages.add("org.apache.struts.action.GLOBAL_MESSAGE", message);
/* 145 */     saveMessages(session, messages);
/*     */   }
/*     */   
/*     */   protected void postGlobalMessage(String messageKey, HttpSession session) {
/* 149 */     postGlobalMessage(messageKey, (Object)null, session);
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
/*     */   protected User getCurrentUser(HttpServletRequest request) {
/* 173 */     User currentUser = (User)request.getSession().getAttribute("LOGIN_USER");
/* 174 */     return currentUser;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Function getFunction(HttpServletRequest request) {
/* 184 */     return (Function)request.getAttribute("com.shcnc.struts.action.BaseAction.function");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean hasFunction(HttpServletRequest request) {
/* 194 */     return (getFunction(request) != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isGlobal(HttpServletRequest request) {
/* 204 */     return getFunction(request).isGlobal();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSite(HttpServletRequest request) {
/* 214 */     return getFunction(request).isSite();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isDepartment(HttpServletRequest request) {
/* 224 */     return getFunction(request).isDepartment();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List getGrantedSiteDeparmentList(HttpServletRequest request) {
/* 235 */     UserManager um = ServiceLocator.getUserManager(request);
/* 236 */     return um.getGrantedSiteDeparmentList(getCurrentUser(request), getFunction(request));
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
/*     */   protected List getAndCheckGrantedSiteDeparmentList(HttpServletRequest request) {
/* 248 */     List list = getGrantedSiteDeparmentList(request);
/* 249 */     if (list.isEmpty()) {
/* 250 */       throw new ActionException("errors.noSiteGranted");
/*     */     }
/* 252 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List getGrantedSiteList(HttpServletRequest request) {
/* 262 */     if (!isDepartment(request)) {
/* 263 */       UserManager um = ServiceLocator.getUserManager(request);
/* 264 */       if (hasGlobalPower(request)) {
/* 265 */         SiteManager sm = ServiceLocator.getSiteManager(request);
/* 266 */         return sm.getAllEnabledSiteList();
/*     */       } 
/*     */       
/* 269 */       return um.getGrantedSiteList(getCurrentUser(request), getFunction(request));
/*     */     } 
/* 271 */     throw new RuntimeException("not with department level function");
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
/*     */   protected List getAndCheckGrantedSiteList(HttpServletRequest request) {
/* 283 */     List list = getGrantedSiteList(request);
/* 284 */     if (list.isEmpty()) {
/* 285 */       throw new ActionException("errors.noSiteGranted");
/*     */     }
/* 287 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkDepartment(Department department, HttpServletRequest request) {
/* 298 */     if (department.getEnabled().equals(EnabledDisabled.DISABLED)) {
/* 299 */       throw new ActionException("errors.department.disabled", department.getName());
/*     */     }
/*     */     
/* 302 */     if (department.getSite().getEnabled().equals(EnabledDisabled.DISABLED)) {
/* 303 */       throw new ActionException("errors.site.disabled", department.getSite().getName());
/*     */     }
/*     */     
/* 306 */     UserManager um = ServiceLocator.getUserManager(request);
/* 307 */     if (!um.hasDepartmentPower(department, getCurrentUser(request), getFunction(request))) {
/* 308 */       throw new ActionException("errors.noDepartmentPermission", department.getName());
/*     */     }
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
/*     */   protected Department getAndCheckDepartment(String paramterName, HttpServletRequest request) {
/* 322 */     return getAndCheckDepartment(ActionUtils.parseInt(request.getParameter(paramterName)), request);
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
/*     */ 
/*     */   
/*     */   protected Department getAndCheckDepartment(Integer departmentId, HttpServletRequest request) {
/* 337 */     DepartmentManager dm = ServiceLocator.getDepartmentManager(request);
/* 338 */     Department department = dm.getDepartment(departmentId);
/*     */     
/* 340 */     if (department == null) {
/* 341 */       throw new ActionException("department.notFound", department.getId());
/*     */     }
/* 343 */     checkDepartment(department, request);
/* 344 */     return department;
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
/*     */   protected void checkUser(User user, HttpServletRequest request) {
/* 356 */     UserManager um = ServiceLocator.getUserManager(request);
/* 357 */     boolean hasPower = um.hasUserPower(user, getCurrentUser(request), getFunction(request));
/* 358 */     if (!hasPower) {
/* 359 */       throw new ActionException("errors.noUserPermission", user.getName());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkUser(Integer userId, HttpServletRequest request) {
/* 371 */     getAndCheckUser(userId, request);
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
/*     */   protected User getAndCheckUser(Integer id, HttpServletRequest request) {
/* 384 */     UserManager um = ServiceLocator.getUserManager(request);
/* 385 */     User user = um.getUser(id);
/* 386 */     if (user == null)
/* 387 */       throw new ActionException("user.notFound"); 
/* 388 */     checkUser(user, request);
/* 389 */     return user;
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
/*     */   protected void checkSite(Site site, HttpServletRequest request) {
/* 401 */     if (site.getEnabled().equals(EnabledDisabled.DISABLED)) {
/* 402 */       throw new ActionException("errors.site.disabled", site.getName());
/*     */     }
/*     */     
/* 405 */     UserManager um = ServiceLocator.getUserManager(request);
/* 406 */     if (!um.hasSitePower(site, getCurrentUser(request), getFunction(request))) {
/* 407 */       throw new ActionException("errors.noSitePermission", site.getName());
/*     */     }
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
/*     */   protected Site getAndCheckSite(String parameterName, HttpServletRequest request) {
/* 421 */     return getAndCheckSite(ActionUtils.parseInt(request.getParameter(parameterName)), request);
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
/*     */   protected void checkSite(Integer site_id, HttpServletRequest request) {
/* 433 */     getAndCheckSite(site_id, request);
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
/*     */   
/*     */   protected Site getAndCheckSite(Integer site_id, HttpServletRequest request) {
/* 447 */     SiteManager sm = ServiceLocator.getSiteManager(request);
/* 448 */     Site site = sm.getSite(site_id);
/* 449 */     if (site == null) {
/* 450 */       throw new ActionException("site.notFound", site_id);
/*     */     }
/* 452 */     checkSite(site, request);
/* 453 */     return site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean hasGlobalPower(HttpServletRequest request) {
/* 464 */     UserManager um = ServiceLocator.getUserManager(request);
/* 465 */     return um.hasGlobalPower(getCurrentUser(request), getFunction(request));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkGlobalPower(HttpServletRequest request) {
/* 475 */     if (!hasGlobalPower(request)) {
/* 476 */       throw new ActionException("errors.noGlobalPermission");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Map getTokenMap(HttpSession session) {
/*     */     FastHashMap fastHashMap;
/* 487 */     Map tokenMap = (Map)session.getAttribute("com.shcnc.struts.PreventRepeatSubmitPlugIn.tokenmap");
/* 488 */     if (tokenMap == null) {
/* 489 */       fastHashMap = new FastHashMap();
/* 490 */       session.setAttribute("com.shcnc.struts.PreventRepeatSubmitPlugIn.tokenmap", fastHashMap);
/*     */     } 
/* 492 */     return (Map)fastHashMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void validateToken(ActionMapping mapping, HttpServletRequest request) {
/* 503 */     Map toMap = (Map)this.servlet.getServletContext().getAttribute("com.shcnc.struts.PreventRepeatSubmitPlugIn.tomap");
/* 504 */     if (toMap != null) {
/* 505 */       String toKey = (String)toMap.get(mapping.getPath());
/* 506 */       if (toKey != null) {
/* 507 */         Map tokenMap = getTokenMap(request.getSession());
/* 508 */         String saved = (String)tokenMap.remove(toKey);
/* 509 */         String token = request.getParameter("org.apache.struts.taglib.html.TOKEN");
/* 510 */         boolean invalid = true;
/* 511 */         if (token != null && saved != null && 
/* 512 */           token.equals(saved)) {
/* 513 */           invalid = false;
/*     */         }
/*     */ 
/*     */         
/* 517 */         if (invalid) {
/* 518 */           throw new ActionException("all.token.invalid");
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void saveToken(ActionMapping mapping, HttpServletRequest request) {
/* 531 */     Map fromMap = (Map)this.servlet.getServletContext().getAttribute("com.shcnc.struts.PreventRepeatSubmitPlugIn.frommap");
/* 532 */     if (fromMap != null) {
/* 533 */       String fromKey = (String)fromMap.get(mapping.getPath());
/*     */       
/* 535 */       if (fromKey != null) {
/* 536 */         Map<String, String> tokenMap = getTokenMap(request.getSession());
/* 537 */         String token = generateToken(request);
/* 538 */         tokenMap.put(fromKey, token);
/* 539 */         request.setAttribute("org.apache.struts.action.TOKEN", token);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 547 */     FunctionManager fm = ServiceLocator.getFunctionManager(request);
/* 548 */     fm.initiate(mapping.getModuleConfig());
/*     */     
/* 550 */     if (mapping instanceof SecureActionMapping) {
/*     */       
/* 552 */       User currentUser = getCurrentUser(request);
/* 553 */       if (currentUser == null) {
/* 554 */         if (((SecureActionMapping)mapping).isDialog())
/* 555 */           throw new ActionException("login.timeout"); 
/* 556 */         return new ActionForward("/login.do", true);
/*     */       } 
/*     */ 
/*     */       
/* 560 */       if (!isBack(request)) {
/* 561 */         validateToken(mapping, request);
/*     */       }
/*     */       
/* 564 */       Integer functionId = ((SecureActionMapping)mapping).getFunctionId();
/* 565 */       if (functionId != null) {
/* 566 */         Function function = fm.getFunction(functionId);
/* 567 */         System.out.println(function.getId());
/* 568 */         System.out.println(function.getName());
/* 569 */         System.out.println(function.getDescription());
/* 570 */         System.out.println(function.getLevel());
/* 571 */         System.out.println(function.isDepartment());
/* 572 */         if (function == null) {
/* 573 */           throw new ActionException("function.notFound", functionId);
/*     */         }
/* 575 */         request.setAttribute("com.shcnc.struts.action.BaseAction.function", function);
/*     */         
/* 577 */         if (isGlobal(request)) {
/* 578 */           checkGlobalPower(request);
/*     */         }
/* 580 */         putVersionPostfixToRequest(request);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 586 */     ActionForward forward = super.execute(mapping, form, request, response);
/*     */     
/* 588 */     saveToken(mapping, request);
/*     */     
/* 590 */     putLangPostfixToRequest(request);
/*     */     
/* 592 */     return forward;
/*     */   }
/*     */   
/*     */   private void putLangPostfixToRequest(HttpServletRequest request) {
/* 596 */     if (getCurrentUser(request) == null)
/*     */       return; 
/* 598 */     if (isEnglish(request)) {
/* 599 */       request.setAttribute("x_lang", "eng");
/*     */     } else {
/* 601 */       request.setAttribute("x_lang", "chn");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void putVersionPostfixToRequest(HttpServletRequest request) {
/* 610 */     if (isSite(request)) {
/* 611 */       request.setAttribute("x_version", "_site");
/* 612 */     } else if (isGlobal(request)) {
/* 613 */       request.setAttribute("x_version", "");
/* 614 */     } else if (isDepartment(request)) {
/* 615 */       request.setAttribute("x_version", "_department");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String getLocaleShortDescription(MetadataDetailEnum metadata, HttpServletRequest request) {
/* 620 */     if ("chn".equals(request.getAttribute("x_lang"))) {
/* 621 */       return metadata.getChnShortDescription();
/*     */     }
/* 623 */     return metadata.getEngShortDescription();
/*     */   }
/*     */   
/*     */   protected long getTodayTimeMillis() {
/* 627 */     Calendar c1 = Calendar.getInstance();
/* 628 */     Calendar c2 = Calendar.getInstance();
/* 629 */     c1.clear();
/* 630 */     c1.set(1, c2.get(1));
/* 631 */     c1.set(2, c2.get(2));
/* 632 */     c1.set(5, c2.get(5));
/* 633 */     return c1.getTimeInMillis();
/*     */   }
/*     */   
/*     */   protected ActionForward downloadAttachment(InputStream in, String fileName, HttpServletRequest request) throws Exception {
/* 637 */     int index = SessionTempFile.createNewTempFile(request);
/* 638 */     OutputStream out = new FileOutputStream(SessionTempFile.getTempFile(index, request));
/* 639 */     byte[] buf = new byte[1024];
/* 640 */     for (int i = in.read(buf); i > 0; i = in.read(buf)) {
/* 641 */       out.write(buf, 0, i);
/*     */     }
/* 643 */     out.close();
/* 644 */     return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8"), true);
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
/*     */ 
/*     */   
/*     */   protected Map constructQueryMap2(BoxQueryForm queryForm) {
/* 683 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 684 */     String number = queryForm.getNumber();
/* 685 */     String lotser = queryForm.getLotser();
/*     */     
/* 687 */     if (number != null && number.trim().length() != 0) {
/* 688 */       conditions.put(BoxQueryCondition.NUMBER_EQ, number);
/*     */     }
/*     */     
/* 691 */     if (lotser != null && lotser.trim().length() != 0) {
/* 692 */       conditions.put(BoxQueryCondition.LOTSER_EQ, lotser);
/*     */     }
/*     */     
/* 695 */     return conditions;
/*     */   }
/*     */   
/*     */   protected Map constructQueryMap3(BoxQueryForm queryForm) {
/* 699 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 700 */     String number = queryForm.getNumber();
/* 701 */     String lotser = queryForm.getLotser();
/*     */     
/* 703 */     if (number != null && number.trim().length() != 0) {
/* 704 */       conditions.put(BoxQueryCondition.NUMBER_EQ, number);
/*     */     }
/*     */     
/* 707 */     if (lotser != null && lotser.trim().length() != 0) {
/* 708 */       conditions.put(BoxQueryCondition.LOTSER_EQ, lotser);
/*     */     }
/*     */     
/* 711 */     return conditions;
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
/*     */ 
/*     */   
/*     */   protected Map putQuery(Map<Object, String> conditions, String from, Object object) {
/* 726 */     if (from != null && !from.equals("")) {
/* 727 */       conditions.put(object, from);
/*     */     }
/* 729 */     return conditions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getConditionAndOrder(BaseSessionQueryForm queryForm, Map<BasicQueryCondition, List<BasicConditionModel>> conditions, HttpServletRequest request) {
/* 735 */     List<BasicConditionModel> modelList = new ArrayList<BasicConditionModel>();
/* 736 */     List<BasicConditionModel> smodelList = new ArrayList<BasicConditionModel>();
/*     */     
/* 738 */     AutoArrayList<String> autoArrayList1 = queryForm.getField();
/* 739 */     AutoArrayList<String> autoArrayList2 = queryForm.getFieldValue();
/* 740 */     AutoArrayList<String> autoArrayList3 = queryForm.getFieldCondition();
/*     */     
/* 742 */     if (autoArrayList1.size() != autoArrayList2.size() && 
/* 743 */       autoArrayList1.size() != autoArrayList3.size() && 
/* 744 */       autoArrayList2.size() != autoArrayList3.size()) {
/* 745 */       throw new ActionException("query.fail1");
/*     */     }
/* 747 */     Boolean isQurey = Boolean.valueOf(false);
/* 748 */     for (int i = 0; i < autoArrayList1.size(); i++) {
/*     */ 
/*     */       
/* 751 */       String field = autoArrayList1.get(i);
/* 752 */       if (field != null && !field.equals("") && autoArrayList3 != null && !autoArrayList3.equals("") && autoArrayList2 != null && !autoArrayList2.equals("")) {
/* 753 */         isQurey = Boolean.valueOf(true);
/* 754 */         String condition = autoArrayList3.get(i);
/* 755 */         BasicConditionType type = BasicConditionType.getEnum(condition);
/* 756 */         if (type == null) {
/* 757 */           throw new ActionException("query.fail3");
/*     */         }
/*     */ 
/*     */         
/* 761 */         String fieldValue = autoArrayList2.get(i);
/* 762 */         if (fieldValue != null && fieldValue.trim().length() > 0) {
/* 763 */           BasicConditionModel model = new BasicConditionModel(field, 
/* 764 */               type, fieldValue);
/* 765 */           model.setIndex(Integer.valueOf(i));
/* 766 */           if (i > 19) {
/* 767 */             model.setIsCustom(Boolean.valueOf(true));
/*     */             
/* 769 */             smodelList.add(model);
/*     */           } 
/* 771 */           modelList.add(model);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 777 */     if (isQurey.booleanValue()) {
/* 778 */       conditions
/* 779 */         .put(BasicQueryCondition.BASIC_QUERY_CONDITION, modelList);
/*     */     }
/*     */     
/* 782 */     request.setAttribute("simQueryList", smodelList);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/BaseAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
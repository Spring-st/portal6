package com.aof.web.struts.action;

import com.aof.model.admin.Department;
import com.aof.model.admin.Function;
import com.aof.model.admin.Site;
import com.aof.model.admin.User;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.model.metadata.MetadataDetailEnum;
import com.aof.model.po.query.BoxQueryCondition;
import com.aof.model.query.AutoArrayList;
import com.aof.model.query.BasicConditionModel;
import com.aof.model.query.BasicConditionType;
import com.aof.model.query.BasicQueryCondition;
import com.aof.service.admin.DepartmentManager;
import com.aof.service.admin.FunctionManager;
import com.aof.service.admin.SiteManager;
import com.aof.service.admin.UserManager;
import com.aof.utils.SessionTempFile;
import com.aof.web.struts.form.BaseSessionQueryForm;
import com.aof.web.struts.form.po.BoxQueryForm;
import com.shcnc.struts.action.ActionException;
import com.shcnc.utils.BeanHelper;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections.FastHashMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public abstract class BaseAction  {
  protected Object getObjectFromRequest(String parameterName, Class clazz, String idName, HttpServletRequest request) throws Exception {
    String str_param_value = request.getParameter(parameterName);
    if (str_param_value == null)
      throw new ActionException("errors.parameter.notSet", parameterName);
    Class idType = BeanHelper.getProperyType(clazz, idName);
    BeanLoader loader = ServiceLocator.getBeanLoader(request);
    Object o = null;
    Serializable idValue = null;
    if (idType.equals(String.class)) {

      idValue = str_param_value;
    }
    else if (idType.equals(Integer.class)) {

      idValue = Integer.parseInt(str_param_value);
      if (idValue == null) throw new ActionException("errors.parameter.notInteger", parameterName);
    } else {
      throw new RuntimeException("unsupported id type: " + idType);
    }
    o = loader.load(clazz, idName, idValue);
    return o;
  }

  protected Object getObjectFromRequestAndCheckExists(String parameterName, Class clazz, String idName, HttpServletRequest request) throws Exception {
    Object o = getObjectFromRequest(parameterName, clazz, idName, request);
    if (o == null)
      throw new ActionException(String.valueOf(getSimpleClassName(clazz)) + ".notFound", request.getParameter(parameterName));
    return o;
  }

  protected int intCheng(Object obj) {
    if (obj == null || obj == "") {
      return 0;
    }
    return Integer.parseInt(obj.toString());
  }

  private String getSimpleClassName(Class clazz) {
    String name = clazz.getName();
    int index = name.lastIndexOf('.');
    if (index != -1) name = name.substring(index + 1);
    return StringUtils.uncapitalize(name);
  }
  public static final String ATTR_VERSION = "x_version";
  public static final String ATTR_LANG = "x_lang";
  public static final String LANG_CHN = "chn";
  public static final String LANG_ENG = "eng";
  protected static Log log = LogFactory.getLog(BaseAction.class);
  public static final String POSTFIX_GLOBAL = "";
  public static final String POSTFIX_SITE = "_site";
  public static final String POSTFIX_DEPARTMENT = "_department";
  public static final String FORWARD_PAGE = "page";

  protected String[] getParameterValues(String parameterName, HttpServletRequest request) {
    String[] retVal = request.getParameterValues(parameterName);
    if (retVal == null)
      return new String[0];
    return retVal;
  }
  public static final String FORWARD_SUCCESS = "success";
  public static final String FORWARD_FAIL = "fail";
  public static final String ATTR_RESULTLIST = "X_RESULTLIST";
  public static final String ATTR_SITELIST = "x_siteList";
  public static final String ATTR_ROWPAGE = "X_ROWPAGE";
  public static final String ATTR_OBJECT = "X_OBJECT";
  private static final String LOGIN_ACTION = "/login.do";
  private static final String ATTR_FUNCTION = "com.shcnc.struts.action.BaseAction.function";
  public static final String CONFIRM = "confirm";

  protected boolean isEnglish(HttpServletRequest request) {
    return getCurrentUser(request).getLocale().equals("en");
  }

  protected void postGlobalMessage(String messageKey, Object arg, HttpServletRequest session) {
    ActionMessage message = (arg == null) ? new ActionMessage(messageKey) : new ActionMessage(messageKey, arg);
    ActionMessages messages = new ActionMessages();
    messages.add("org.apache.struts.action.GLOBAL_MESSAGE", message);
    //todo st
    //saveMessages(session, messages);
  }

  protected void postGlobalMessage(String messageKey, HttpServletRequest session) {
    postGlobalMessage(messageKey, (Object)null, session);
  }





















  protected User getCurrentUser(HttpServletRequest request) {
    User currentUser = (User)request.getSession().getAttribute("LOGIN_USER");
    return currentUser;
  }







  protected Function getFunction(HttpServletRequest request) {
    return (Function)request.getAttribute("com.shcnc.struts.action.BaseAction.function");
  }







  protected boolean hasFunction(HttpServletRequest request) {
    return (getFunction(request) != null);
  }







  protected boolean isGlobal(HttpServletRequest request) {
    return getFunction(request).isGlobal();
  }







  protected boolean isSite(HttpServletRequest request) {
    return getFunction(request).isSite();
  }







  protected boolean isDepartment(HttpServletRequest request) {
    return getFunction(request).isDepartment();
  }








  protected List getGrantedSiteDeparmentList(HttpServletRequest request) {
    UserManager um = ServiceLocator.getUserManager(request);
    return um.getGrantedSiteDeparmentList(getCurrentUser(request), getFunction(request));
  }









  protected List getAndCheckGrantedSiteDeparmentList(HttpServletRequest request) {
    List list = getGrantedSiteDeparmentList(request);
    if (list.isEmpty()) {
      throw new ActionException("errors.noSiteGranted");
    }
    return list;
  }







  protected List getGrantedSiteList(HttpServletRequest request) {
    if (!isDepartment(request)) {
      UserManager um = ServiceLocator.getUserManager(request);
      if (hasGlobalPower(request)) {
        SiteManager sm = ServiceLocator.getSiteManager(request);
        return sm.getAllEnabledSiteList();
      }

      return um.getGrantedSiteList(getCurrentUser(request), getFunction(request));
    }
    throw new RuntimeException("not with department level function");
  }









  protected List getAndCheckGrantedSiteList(HttpServletRequest request) {
    List list = getGrantedSiteList(request);
    if (list.isEmpty()) {
      throw new ActionException("errors.noSiteGranted");
    }
    return list;
  }








  protected void checkDepartment(Department department, HttpServletRequest request) {
    if (department.getEnabled().equals(EnabledDisabled.DISABLED)) {
      throw new ActionException("errors.department.disabled", department.getName());
    }

    if (department.getSite().getEnabled().equals(EnabledDisabled.DISABLED)) {
      throw new ActionException("errors.site.disabled", department.getSite().getName());
    }

    UserManager um = ServiceLocator.getUserManager(request);
    if (!um.hasDepartmentPower(department, getCurrentUser(request), getFunction(request))) {
      throw new ActionException("errors.noDepartmentPermission", department.getName());
    }
  }










  protected Department getAndCheckDepartment(String paramterName, HttpServletRequest request) {
    return getAndCheckDepartment(Integer.parseInt(request.getParameter(paramterName)), request);
  }












  protected Department getAndCheckDepartment(Integer departmentId, HttpServletRequest request) {
    DepartmentManager dm = ServiceLocator.getDepartmentManager(request);
    Department department = dm.getDepartment(departmentId);

    if (department == null) {
      throw new ActionException("department.notFound", department.getId());
    }
    checkDepartment(department, request);
    return department;
  }









  protected void checkUser(User user, HttpServletRequest request) {
    UserManager um = ServiceLocator.getUserManager(request);
    boolean hasPower = um.hasUserPower(user, getCurrentUser(request), getFunction(request));
    if (!hasPower) {
      throw new ActionException("errors.noUserPermission", user.getName());
    }
  }








  protected void checkUser(Integer userId, HttpServletRequest request) {
    getAndCheckUser(userId, request);
  }










  protected User getAndCheckUser(Integer id, HttpServletRequest request) {
    UserManager um = ServiceLocator.getUserManager(request);
    User user = um.getUser(id);
    if (user == null)
      throw new ActionException("user.notFound");
    checkUser(user, request);
    return user;
  }









  protected void checkSite(Site site, HttpServletRequest request) {
    if (site.getEnabled().equals(EnabledDisabled.DISABLED)) {
      throw new ActionException("errors.site.disabled", site.getName());
    }

    UserManager um = ServiceLocator.getUserManager(request);
    if (!um.hasSitePower(site, getCurrentUser(request), getFunction(request))) {
      throw new ActionException("errors.noSitePermission", site.getName());
    }
  }










  protected Site getAndCheckSite(String parameterName, HttpServletRequest request) {
    return getAndCheckSite(Integer.parseInt(request.getParameter(parameterName)), request);
  }









  protected void checkSite(Integer site_id, HttpServletRequest request) {
    getAndCheckSite(site_id, request);
  }











  protected Site getAndCheckSite(Integer site_id, HttpServletRequest request) {
    SiteManager sm = ServiceLocator.getSiteManager(request);
    Site site = sm.getSite(site_id);
    if (site == null) {
      throw new ActionException("site.notFound", site_id);
    }
    checkSite(site, request);
    return site;
  }








  protected boolean hasGlobalPower(HttpServletRequest request) {
    UserManager um = ServiceLocator.getUserManager(request);
    return um.hasGlobalPower(getCurrentUser(request), getFunction(request));
  }







  protected void checkGlobalPower(HttpServletRequest request) {
    if (!hasGlobalPower(request)) {
      throw new ActionException("errors.noGlobalPermission");
    }
  }






  private Map getTokenMap(HttpSession session) {
    FastHashMap fastHashMap = null;
    Map tokenMap = (Map)session.getAttribute("com.shcnc.struts.PreventRepeatSubmitPlugIn.tokenmap");
    if (tokenMap == null) {
      fastHashMap = new FastHashMap();
      session.setAttribute("com.shcnc.struts.PreventRepeatSubmitPlugIn.tokenmap", fastHashMap);
    }
    return fastHashMap;
  }








//  private void validateToken(ActionMapping mapping, HttpServletRequest request) {
//    Map toMap = (Map)this.servlet.getServletContext().getAttribute("com.shcnc.struts.PreventRepeatSubmitPlugIn.tomap");
//    if (toMap != null) {
//      String toKey = (String)toMap.get(mapping.getPath());
//      if (toKey != null) {
//        Map tokenMap = getTokenMap(request.getSession());
//        String saved = (String)tokenMap.remove(toKey);
//        String token = request.getParameter("org.apache.struts.taglib.html.TOKEN");
//        boolean invalid = true;
//        if (token != null && saved != null &&
//                token.equals(saved)) {
//          invalid = false;
//        }
//
//
//        if (invalid) {
//          throw new ActionException("all.token.invalid");
//        }
//      }
//    }
//  }

  private void saveToken(ActionMapping mapping, HttpServletRequest request) {
//    Map fromMap = (Map)this.servlet.getServletContext().getAttribute("com.shcnc.struts.PreventRepeatSubmitPlugIn.frommap");
//    if (fromMap != null) {
//      String fromKey = (String)fromMap.get(mapping.getPath());
//
//      if (fromKey != null) {
//        Map<String, String> tokenMap = getTokenMap(request.getSession());
//        String token = generateToken(request);
//        tokenMap.put(fromKey, token);
//        request.setAttribute("org.apache.struts.action.TOKEN", token);
//      }
//    }
  }



//  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//    FunctionManager fm = ServiceLocator.getFunctionManager(request);
//    fm.initiate(mapping.getModuleConfig());
//
//    if (mapping instanceof SecureActionMapping) {
//
//      User currentUser = getCurrentUser(request);
//      if (currentUser == null) {
//        if (((SecureActionMapping)mapping).isDialog())
//          throw new ActionException("login.timeout");
//        return new ActionForward("/login.do", true);
//      }
//
//
//      if (!isBack(request)) {
//        validateToken(mapping, request);
//      }
//
//      Integer functionId = ((SecureActionMapping)mapping).getFunctionId();
//      if (functionId != null) {
//        Function function = fm.getFunction(functionId);
//        System.out.println(function.getId());
//        System.out.println(function.getName());
//        System.out.println(function.getDescription());
//        System.out.println(function.getLevel());
//        System.out.println(function.isDepartment());
//        if (function == null) {
//          throw new ActionException("function.notFound", functionId);
//        }
//        request.setAttribute("com.shcnc.struts.action.BaseAction.function", function);
//
//        if (isGlobal(request)) {
//          checkGlobalPower(request);
//        }
//        putVersionPostfixToRequest(request);
//      }
//    }
//
//
//
//    ActionForward forward = super.execute(mapping, form, request, response);
//
//    saveToken(mapping, request);
//
//    putLangPostfixToRequest(request);
//
//    return forward;
//  }

  private void putLangPostfixToRequest(HttpServletRequest request) {
    if (getCurrentUser(request) == null)
      return;
    if (isEnglish(request)) {
      request.setAttribute("x_lang", "eng");
    } else {
      request.setAttribute("x_lang", "chn");
    }
  }





  protected void putVersionPostfixToRequest(HttpServletRequest request) {
    if (isSite(request)) {
      request.setAttribute("x_version", "_site");
    } else if (isGlobal(request)) {
      request.setAttribute("x_version", "");
    } else if (isDepartment(request)) {
      request.setAttribute("x_version", "_department");
    }
  }

  protected String getLocaleShortDescription(MetadataDetailEnum metadata, HttpServletRequest request) {
    if ("chn".equals(request.getAttribute("x_lang"))) {
      return metadata.getChnShortDescription();
    }
    return metadata.getEngShortDescription();
  }

  protected long getTodayTimeMillis() {
    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    c1.clear();
    c1.set(1, c2.get(1));
    c1.set(2, c2.get(2));
    c1.set(5, c2.get(5));
    return c1.getTimeInMillis();
  }

  protected ActionForward downloadAttachment(InputStream in, String fileName, HttpServletRequest request) throws Exception {
    int index = SessionTempFile.createNewTempFile(request);
    OutputStream out = new FileOutputStream(SessionTempFile.getTempFile(index, request));
    byte[] buf = new byte[1024];
    for (int i = in.read(buf); i > 0; i = in.read(buf)) {
      out.write(buf, 0, i);
    }
    out.close();
    return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8"), true);
  }




































  protected Map constructQueryMap2(BoxQueryForm queryForm) {
    Map<Object, Object> conditions = new HashMap<Object, Object>();
    String number = queryForm.getNumber();
    String lotser = queryForm.getLotser();

    if (number != null && number.trim().length() != 0) {
      conditions.put(BoxQueryCondition.NUMBER_EQ, number);
    }

    if (lotser != null && lotser.trim().length() != 0) {
      conditions.put(BoxQueryCondition.LOTSER_EQ, lotser);
    }

    return conditions;
  }

  protected Map constructQueryMap3(BoxQueryForm queryForm) {
    Map<Object, Object> conditions = new HashMap<Object, Object>();
    String number = queryForm.getNumber();
    String lotser = queryForm.getLotser();

    if (number != null && number.trim().length() != 0) {
      conditions.put(BoxQueryCondition.NUMBER_EQ, number);
    }

    if (lotser != null && lotser.trim().length() != 0) {
      conditions.put(BoxQueryCondition.LOTSER_EQ, lotser);
    }

    return conditions;
  }












  protected Map putQuery(Map<Object, String> conditions, String from, Object object) {
    if (from != null && !from.equals("")) {
      conditions.put(object, from);
    }
    return conditions;
  }



  protected void getConditionAndOrder(BaseSessionQueryForm queryForm, Map<BasicQueryCondition, List<BasicConditionModel>> conditions, HttpServletRequest request) {
    List<BasicConditionModel> modelList = new ArrayList<BasicConditionModel>();
    List<BasicConditionModel> smodelList = new ArrayList<BasicConditionModel>();

    AutoArrayList autoArrayList1 = queryForm.getField();
    AutoArrayList autoArrayList2 = queryForm.getFieldValue();
    AutoArrayList autoArrayList3 = queryForm.getFieldCondition();

    if (autoArrayList1.size() != autoArrayList2.size() &&
            autoArrayList1.size() != autoArrayList3.size() &&
            autoArrayList2.size() != autoArrayList3.size()) {
      throw new ActionException("query.fail1");
    }
    Boolean isQurey = Boolean.valueOf(false);
    for (int i = 0; i < autoArrayList1.size(); i++) {
      String field = (String)autoArrayList1.get(i);
      if (field != null && !field.equals("") && autoArrayList3 != null && !autoArrayList3.equals("") && autoArrayList2 != null && !autoArrayList2.equals("")) {
        isQurey = Boolean.valueOf(true);
        String condition = (String)autoArrayList3.get(i);
        BasicConditionType type = BasicConditionType.getEnum(condition);
        if (type == null) {
          throw new ActionException("query.fail3");
        }


        String fieldValue = (String)autoArrayList2.get(i);
        if (fieldValue != null && fieldValue.trim().length() > 0) {
          BasicConditionModel model = new BasicConditionModel(field,
                  type, fieldValue);
          model.setIndex(Integer.valueOf(i));
          if (i > 19) {
            model.setIsCustom(Boolean.valueOf(true));

            smodelList.add(model);
          }
          modelList.add(model);
        }
      }
    }


    if (isQurey.booleanValue()) {
      conditions
              .put(BasicQueryCondition.BASIC_QUERY_CONDITION, modelList);
    }

    request.setAttribute("simQueryList", smodelList);
  }
}


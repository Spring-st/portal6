package com.aof.web.struts.action.business.rule;

import com.aof.model.admin.Department;
import com.aof.model.admin.Function;
import com.aof.model.admin.Site;
import com.aof.model.admin.User;
import com.aof.model.admin.query.UserQueryCondition;
import com.aof.model.admin.query.UserQueryOrder;
import com.aof.model.business.rule.ApproverDelegate;
import com.aof.model.business.rule.query.ApproverDelegateQueryCondition;
import com.aof.model.business.rule.query.ApproverDelegateQueryOrder;
import com.aof.model.business.rule.query.ApproverQueryCondition;
import com.aof.model.metadata.ApproverDelegateType;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.model.metadata.RuleType;
import com.aof.service.admin.EmailManager;
import com.aof.service.admin.FunctionManager;
import com.aof.service.admin.UserManager;
import com.aof.service.business.rule.ApproverDelegateManager;
import com.aof.utils.SessionTempFile;
import com.aof.web.struts.action.ActionUtils2;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.business.rule.ApproverDelegateQueryForm;
import com.shcnc.struts.action.ActionException;
import com.shcnc.struts.form.BeanForm;
import com.shcnc.struts.form.BeanQueryForm;
import com.shcnc.utils.BeanHelper;
import com.shcnc.utils.ExportUtil;
import com.shcnc.utils.Exportable;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

public class ApproverDelegateAction extends BaseAction2 {
    public ActionForward listApprover(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BeanQueryForm queryForm = (BeanQueryForm)form;
        ApproverQueryCondition cond = (ApproverQueryCondition)queryForm.newBean();
        List<Site> siteList = getAndCheckGrantedSiteDeparmentList(request);
        if (cond.getRuleType() == null) {
            cond.setRuleType(RuleType.CAPEX_APPROVAL_RULES);
            if (!isGlobal(request)) {
                Site firstSite = siteList.get(0);
                cond.setSiteId(firstSite.getId());
                Department firstDepartment = (Department) firstSite.getDepartments().get(0);
                cond.setDepartmentId(firstDepartment.getId());
            }
        }
        queryForm.populateToForm(cond);
        Map conds = constructApproverQueryMap(cond, request);
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(UserQueryOrder.NAME.getName());
            queryForm.setDescend(false);
        } else if (UserQueryOrder.getEnum(queryForm.getOrder()) == null) {
            throw new RuntimeException("order not found");
        }
        UserManager um = ServiceLocator.getUserManager(request);
        if (queryForm.isFirstInit()) {
            Integer pageSize = ActionUtils2.parseInt(queryForm.getPageSize());
            if (pageSize != null) {
                queryForm.init(um.getUserListCount(conds), pageSize.intValue());
            } else {
                queryForm.init(um.getUserListCount(conds));
            }
        } else {
            queryForm.init();
        }
        List result = um.getUserList(conds, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), UserQueryOrder.getEnum(queryForm.getOrder()), queryForm
                .isDescend());
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_siteList", siteList);
        putRuleTypeListToRequest(request);
        return mapping.findForward("page");
    }

    private Map constructApproverQueryMap(ApproverQueryCondition cond, HttpServletRequest request) {
        Map<Object, Object> conds = new HashMap<Object, Object>();
        conds.put(UserQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
        if (cond.getSiteId() != null)
            conds.put(UserQueryCondition.SITE_ID_EQ, cond.getSiteId());
        if (cond.getDepartmentId() != null)
            conds.put(UserQueryCondition.DEPARTMENT_ID_EQ, cond.getDepartmentId());
        if (cond.getName() != null)
            conds.put(UserQueryCondition.NAME_LIKE, cond.getName());
        FunctionManager fm = ServiceLocator.getFunctionManager(request);
        Function function = fm.getFunction(cond.getRuleType().getPrefixUrl());
        conds.put(UserQueryCondition.FUNCTION_ID_EQ, function);
        return conds;
    }

    private void putRuleTypeListToRequest(HttpServletRequest request) {
        List<RuleType> l = new ArrayList();
        l.add(RuleType.CAPEX_APPROVAL_RULES);
        l.add(RuleType.PR_APPROVAL_RULES);
        l.add(RuleType.PO_APPROVAL_RULES);
        l.add(RuleType.EXPENSE_APPROVAL_RULES);
        l.add(RuleType.TRAVEL_APPROVAL_RULES);
        request.setAttribute("x_ruleTypeList", l);
    }

    private boolean isSelf(HttpServletRequest request) {
        return isGlobal(request);
    }

    private RuleType getRuleTypeFromRequest(HttpServletRequest request) {
        Integer ruleTypeId = ActionUtils2.parseInt(request.getParameter("ruleType"));
        if (ruleTypeId == null)
            throw new ActionException("approverDelegate.ruleType.notSet");
        RuleType ruleType = (RuleType)RuleType.fromEnumCode(RuleType.class, ruleTypeId);
        if (ruleType == null)
            throw new ActionException("approverDelegate.ruleType.error");
        return ruleType;
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        processSelfPostfix(request);
        ApproverDelegateManager fm = ServiceLocator.getApproverDelegateManager(request);
        ApproverDelegateQueryForm queryForm = (ApproverDelegateQueryForm)form;
        User originalApprover = null;
        if (isSelf(request)) {
            queryForm.setOriginalApprover_id(getCurrentUser(request).getId().toString());
            originalApprover = getCurrentUser(request);
            List<ApproverDelegateType> typeList = ApproverDelegateType.getEnumList(ApproverDelegateType.class);
            request.setAttribute("x_typeList", typeList);
            if (StringUtils.isEmpty(queryForm.getType())) {
                ApproverDelegateType firstType = typeList.get(0);
                queryForm.setType(firstType.getEnumCode().toString());
            }
        } else {
            ApproverDelegateType type = null;
            if (queryForm.getType() == null) {
                RuleType ruleType = getRuleTypeFromRequest(request);
                type = ApproverDelegateType.getApproverDelegateTypeByRuleType(ruleType);
                if (type == null)
                    throw new ActionException("approverDelegate.ruleType.notApprove");
                queryForm.setType(type.getEnumCode().toString());
            } else {
                Integer typeId = ActionUtils2.parseInt(queryForm.getType());
                type = (ApproverDelegateType)ApproverDelegateType.fromEnumCode(ApproverDelegateType.class, typeId);
                if (type == null)
                    throw new RuntimeException("type error");
            }
            originalApprover = getAndCheckOriginalApproverFromRequest(request);
            request.setAttribute("x_type", type);
        }
        request.setAttribute("x_originalApprover", originalApprover);
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(ApproverDelegateQueryOrder.DELEGATEAPPROVER_NAME.getName());
            queryForm.setDescend(false);
            queryForm.setFromDate2(ActionUtils2.getTodayAsDisplayDate());
            queryForm.setToDate1(ActionUtils2.getTodayAsDisplayDate());
        } else if (ApproverDelegateQueryOrder.getEnum(queryForm.getOrder()) == null) {
            throw new RuntimeException("order error");
        }
        Map conditions = constructQueryMap(queryForm);
        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = fm.getApproverDelegateList(conditions, 0, -1, ApproverDelegateQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "approverDelegate";
            String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable() {
                public void exportHead(List row, HttpServletRequest request) throws Exception {
                    MessageResources messages = ApproverDelegateAction.this.getResources(request);
                    row.add(messages.getMessage(ApproverDelegateAction.this.getLocale(request), "approverDelegate.delegateApprover.id"));
                    row.add(messages.getMessage(ApproverDelegateAction.this.getLocale(request), "approverDelegate.fromDate"));
                    row.add(messages.getMessage(ApproverDelegateAction.this.getLocale(request), "approverDelegate.toDate"));
                }

                public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                    row.add(BeanHelper.getBeanPropertyValue(data, "delegateApprover.name"));
                    ApproverDelegate ad = (ApproverDelegate)data;
                    row.add(getDateString(ad.getFromDate()));
                    row.add(getDateString(ad.getToDate()));
                }

                private String getDateString(Date date) {
                    if (date == null)
                        return "";
                    return ActionUtils2.getDisplayDateFromDate(date);
                }
            });
            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getApproverDelegateListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getApproverDelegateList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                ApproverDelegateQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
        request.setAttribute("X_RESULTLIST", result);
        return mapping.findForward("page");
    }

    private Map constructQueryMap(ApproverDelegateQueryForm queryForm) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        Integer id = ActionUtils2.parseInt(queryForm.getId());
        if (id != null)
            conditions.put(ApproverDelegateQueryCondition.ID_EQ, id);
        Integer originalApprover_id = ActionUtils2.parseInt(queryForm.getOriginalApprover_id());
        if (originalApprover_id != null)
            conditions.put(ApproverDelegateQueryCondition.ORIGINALAPPROVER_ID_EQ, originalApprover_id);
        Integer delegateApprover_id = ActionUtils2.parseInt(queryForm.getDelegateApprover_id());
        if (delegateApprover_id != null)
            conditions.put(ApproverDelegateQueryCondition.DELEGATEAPPROVER_ID_EQ, delegateApprover_id);
        String type = queryForm.getType();
        if (type != null && type.trim().length() != 0)
            conditions.put(ApproverDelegateQueryCondition.TYPE_EQ, type);
        String fromDate = queryForm.getFromDate();
        if (fromDate != null && fromDate.trim().length() != 0)
            conditions.put(ApproverDelegateQueryCondition.FROMDATE_EQ, fromDate);
        String toDate = queryForm.getToDate();
        if (toDate != null && toDate.trim().length() != 0)
            conditions.put(ApproverDelegateQueryCondition.TODATE_EQ, toDate);
        if (StringUtils.isNotEmpty(queryForm.getFromDate1())) {
            Date d = ActionUtils2.getDateFromDisplayDate(queryForm.getFromDate1());
            conditions.put(ApproverDelegateQueryCondition.FROMDATE_GE, d);
        }
        if (StringUtils.isNotEmpty(queryForm.getFromDate2())) {
            Date d = ActionUtils2.getDateFromDisplayDate(queryForm.getFromDate2());
            conditions.put(ApproverDelegateQueryCondition.FROMDATE_LT, getNextDate(d));
        }
        if (StringUtils.isNotEmpty(queryForm.getToDate1())) {
            Date d = ActionUtils2.getDateFromDisplayDate(queryForm.getToDate1());
            conditions.put(ApproverDelegateQueryCondition.TODATE_GE, d);
        }
        if (StringUtils.isNotEmpty(queryForm.getToDate2())) {
            Date d = ActionUtils2.getDateFromDisplayDate(queryForm.getToDate2());
            conditions.put(ApproverDelegateQueryCondition.TODATE_LT, getNextDate(d));
        }
        return conditions;
    }

    private Date getNextDate(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(5, 1);
        return c.getTime();
    }

    private ApproverDelegate getApproverDelegateFromRequest(HttpServletRequest request) throws Exception {
        Integer id = ActionUtils2.parseInt(request.getParameter("id"));
        ApproverDelegateManager approverDelegateManager = ServiceLocator.getApproverDelegateManager(request);
        ApproverDelegate approverDelegate = approverDelegateManager.getApproverDelegate(id);
        if (approverDelegate == null)
            throw new ActionException("approverDelegate.notFound", id);
        return approverDelegate;
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        processSelfPostfix(request);
        ApproverDelegate approverDelegate = getApproverDelegateFromRequest(request);
        request.setAttribute("x_ad", approverDelegate);
        String today = ActionUtils2.getTodayAsDisplayDate();
        boolean fromDateBeforeToday = (ActionUtils2.getDisplayDateFromDate(approverDelegate.getFromDate()).compareTo(today) <= 0);
        boolean toDateBeforeToday = (ActionUtils2.getDisplayDateFromDate(approverDelegate.getToDate()).compareTo(today) <= 0);
        if (fromDateBeforeToday)
            request.setAttribute("x_fromBefore", Boolean.TRUE);
        if (toDateBeforeToday)
            request.setAttribute("x_toBefore", Boolean.TRUE);
        if (!isBack(request)) {
            if (isGlobal(request)) {
                if (!approverDelegate.getOriginalApprover().equals(getCurrentUser(request)))
                    throw new ActionException("approverDelegate.originalApprover.notSelf");
            } else {
                checkUser(approverDelegate.getOriginalApprover(), request);
                checkUser(approverDelegate.getDelegateApprover(), request);
            }
            BeanForm approverDelegateForm = (BeanForm)getForm("/updateApproverDelegate", request);
            approverDelegateForm.populate(approverDelegate, "to_form");
        }
        request.setAttribute("x_today", today);
        return mapping.findForward("page");
    }

    private User getAndCheckOriginalApproverFromRequest(HttpServletRequest request) throws Exception {
        User user = getOriginalApproverFromRequest(request);
        if (!isGlobal(request))
            checkUser(user, request);
        return user;
    }

    private void processSelfPostfix(HttpServletRequest request) {
        if (isGlobal(request))
            request.setAttribute("x_postfix", "_self");
    }

    public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        processSelfPostfix(request);
        if (!isBack(request)) {
            User originalApprover = null;
            if (isGlobal(request)) {
                originalApprover = getCurrentUser(request);
            } else {
                originalApprover = getAndCheckOriginalApproverFromRequest(request);
                checkUser(originalApprover, request);
            }
            ApproverDelegateType type = getApproverDelegateTypeFromRequest(request);
            ApproverDelegate approverDelegate = new ApproverDelegate();
            approverDelegate.setType(type);
            approverDelegate.setOriginalApprover(originalApprover);
            BeanForm approverDelegateForm = (BeanForm)getForm("/insertApproverDelegate", request);
            approverDelegateForm.populate(approverDelegate, "to_form");
        }
        request.setAttribute("x_today", ActionUtils2.getTodayAsDisplayDate());
        return mapping.findForward("page");
    }

    private ApproverDelegateType getApproverDelegateTypeFromRequest(HttpServletRequest request) {
        Integer id = ActionUtils2.parseInt(request.getParameter("type"));
        if (id == null)
            throw new ActionException("approverDelegate.type.notSet");
        ApproverDelegateType type = (ApproverDelegateType)ApproverDelegateType.fromEnumCode(ApproverDelegateType.class, id);
        if (type == null)
            throw new ActionException("approverDelegate.type.notFound", id);
        return type;
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        User oUser = getOriginalApproverFromRequest(request);
        User dUser = getDelegateApproverFromRequest(request);
        if (isGlobal(request)) {
            if (!oUser.equals(getCurrentUser(request)))
                throw new ActionException("approverDelegate.originalApprover.notSelf");
        } else {
            checkUser(oUser, request);
            checkUser(dUser, request);
        }
        if (oUser.equals(dUser))
            throw new ActionException("approverDelegate.select.notSelf");
        ApproverDelegate approverDelegate = getApproverDelegateFromRequest(request);
        String today = ActionUtils2.getTodayAsDisplayDate();
        boolean oldFromDateBeforeToday = (ActionUtils2.getDisplayDateFromDate(approverDelegate.getFromDate()).compareTo(today) <= 0);
        boolean oldToDateBeforeToday = (ActionUtils2.getDisplayDateFromDate(approverDelegate.getToDate()).compareTo(today) <= 0);
        if (oldFromDateBeforeToday && oldToDateBeforeToday)
            throw new RuntimeException("can't edit old");
        Date oldFromDate = approverDelegate.getFromDate();
        Date oldToDate = approverDelegate.getToDate();
        User oldDelegateApprover = approverDelegate.getDelegateApprover();
        BeanForm approverDelegateForm = (BeanForm)form;
        approverDelegateForm.populate(approverDelegate, "to_bean");
        approverDelegate.setOriginalApprover(oUser);
        approverDelegate.setDelegateApprover(dUser);
        String sFromDate = ActionUtils2.getDisplayDateFromDate(approverDelegate.getFromDate());
        String sToDate = ActionUtils2.getDisplayDateFromDate(approverDelegate.getToDate());
        if (sFromDate.compareTo(sToDate) > 0)
            throw new ActionException("approverDelegate.fromDateAfterToDate");
        if (!oldFromDateBeforeToday &&
                sFromDate.compareTo(today) <= 0)
            throw new ActionException("approverDelegate.fromDate.notAfterToday");
        if (!oldToDateBeforeToday &&
                sToDate.compareTo(today) <= 0)
            throw new ActionException("approverDelegate.toDate.notAfterToday");
        if (oldFromDateBeforeToday) {
            approverDelegate.setFromDate(oldFromDate);
            approverDelegate.setDelegateApprover(oldDelegateApprover);
        }
        if (oldToDateBeforeToday)
            approverDelegate.setToDate(oldToDate);
        ApproverDelegateManager approverDelegateManager = ServiceLocator.getApproverDelegateManager(request);
        Map<Object, Object> conds = new HashMap<Object, Object>();
        conds.put(ApproverDelegateQueryCondition.DELEGATEAPPROVER_ID_EQ, dUser.getId());
        conds.put(ApproverDelegateQueryCondition.ORIGINALAPPROVER_ID_EQ, oUser.getId());
        conds.put(ApproverDelegateQueryCondition.TYPE_EQ, approverDelegate.getType());
        List dList = approverDelegateManager.getApproverDelegateList(conds, 0, -1, ApproverDelegateQueryOrder.FROMDATE, false);
        for (Iterator<ApproverDelegate> iter = dList.iterator(); iter.hasNext(); ) {
            ApproverDelegate ad = iter.next();
            if (ad.equals(approverDelegate))
                continue;
            String oldSFromDate = ActionUtils2.getDisplayDateFromDate(ad.getFromDate());
            String oldSToDate = ActionUtils2.getDisplayDateFromDate(ad.getToDate());
            if (sToDate.compareTo(oldSFromDate) < 0)
                continue;
            if (sFromDate.compareTo(oldSToDate) > 0)
                continue;
            throw new ActionException("approverDelegate.date.overlap");
        }
        approverDelegateManager.updateApproverDelegate(approverDelegate);
        request.setAttribute("X_OBJECT", approverDelegateManager.getApproverDelegate(approverDelegate.getId()));
        request.setAttribute("X_ROWPAGE", "approverDelegate/row.jsp");
        EmailManager em = ServiceLocator.getEmailManager(request);
        Map<Object, Object> context = new HashMap<Object, Object>();
        context.put("x_ad", approverDelegate);
        em.insertEmail(approverDelegate.getDelegateApprover().getPrimarySite(), approverDelegate.getDelegateApprover().getEmail(),
                "Delegate.vm", context);
        return mapping.findForward("success");
    }

    private User getOriginalApproverFromRequest(HttpServletRequest request) throws Exception {
        Integer id = ActionUtils2.parseInt(request.getParameter("originalApprover_id"));
        if (id == null)
            throw new ActionException("approverDelegate.delegateApprover.notSet");
        UserManager um = ServiceLocator.getUserManager(request);
        User user = um.getUser(id);
        if (user == null)
            throw new ActionException("approverDelegate.delegateApprover.notFound");
        return user;
    }

    public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        User originalUser = null;
        User delegateApprover = null;
        if (isGlobal(request)) {
            originalUser = getCurrentUser(request);
            delegateApprover = getDelegateApproverFromRequest(request);
        } else {
            originalUser = getAndCheckOriginalApproverFromRequest(request);
            delegateApprover = getAndCheckDelegateApproverFromRequest(request);
        }
        if (originalUser.equals(delegateApprover))
            throw new ActionException("approverDelegate.select.notSelf");
        BeanForm approverDelegateForm = (BeanForm)form;
        ApproverDelegate approverDelegate = new ApproverDelegate();
        approverDelegateForm.populate(approverDelegate, "to_bean");
        approverDelegate.setOriginalApprover(originalUser);
        approverDelegate.setDelegateApprover(delegateApprover);
        String today = ActionUtils2.getTodayAsDisplayDate();
        String sFromDate = ActionUtils2.getDisplayDateFromDate(approverDelegate.getFromDate());
        String sToDate = ActionUtils2.getDisplayDateFromDate(approverDelegate.getToDate());
        if (sFromDate.compareTo(sToDate) > 0)
            throw new ActionException("approverDelegate.fromDateAfterToDate");
        if (sFromDate.compareTo(today) <= 0)
            throw new ActionException("approverDelegate.fromDate.notAfterToday");
        if (sToDate.compareTo(today) <= 0)
            throw new ActionException("approverDelegate.toDate.notAfterToday");
        ApproverDelegateManager approverDelegateManager = ServiceLocator.getApproverDelegateManager(request);
        Map<Object, Object> conds = new HashMap<Object, Object>();
        conds.put(ApproverDelegateQueryCondition.DELEGATEAPPROVER_ID_EQ, delegateApprover.getId());
        conds.put(ApproverDelegateQueryCondition.ORIGINALAPPROVER_ID_EQ, originalUser.getId());
        conds.put(ApproverDelegateQueryCondition.TYPE_EQ, approverDelegate.getType());
        List dList = approverDelegateManager.getApproverDelegateList(conds, 0, -1, ApproverDelegateQueryOrder.FROMDATE, false);
        for (Iterator<ApproverDelegate> iter = dList.iterator(); iter.hasNext(); ) {
            ApproverDelegate ad = iter.next();
            String oldSFromDate = ActionUtils2.getDisplayDateFromDate(ad.getFromDate());
            String oldSToDate = ActionUtils2.getDisplayDateFromDate(ad.getToDate());
            if (sToDate.compareTo(oldSFromDate) < 0)
                continue;
            if (sFromDate.compareTo(oldSToDate) > 0)
                continue;
            throw new ActionException("approverDelegate.date.overlap");
        }
        request.setAttribute("X_OBJECT", approverDelegateManager.insertApproverDelegate(approverDelegate));
        request.setAttribute("X_ROWPAGE", "approverDelegate/row.jsp");
        EmailManager em = ServiceLocator.getEmailManager(request);
        Map<Object, Object> context = new HashMap<Object, Object>();
        context.put("x_ad", approverDelegate);
        em.insertEmail(approverDelegate.getDelegateApprover().getPrimarySite(), approverDelegate.getDelegateApprover().getEmail(),
                "Delegate.vm", context);
        return mapping.findForward("success");
    }

    private User getDelegateApproverFromRequest(HttpServletRequest request) throws Exception {
        Integer id = ActionUtils2.parseInt(request.getParameter("delegateApprover_id"));
        if (id == null)
            throw new ActionException("approverDelegate.delegateApprover.notSet");
        UserManager um = ServiceLocator.getUserManager(request);
        User user = um.getUser(id);
        if (user == null)
            throw new ActionException("approverDelegate.delegateApprover.notFound");
        return user;
    }

    private User getAndCheckDelegateApproverFromRequest(HttpServletRequest request) throws Exception {
        User user = getDelegateApproverFromRequest(request);
        if (!isGlobal(request))
            checkUser(user, request);
        return user;
    }

    public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        processSelfPostfix(request);
        User originalApprover = getAndCheckOriginalApproverFromRequest(request);
        request.setAttribute("x_originalApprover", originalApprover);
        BeanQueryForm queryForm = (BeanQueryForm)form;
        ApproverQueryCondition cond = (ApproverQueryCondition)queryForm.newBean();
        if (cond.getRuleType() == null) {
            ApproverDelegateType type = getApproverDelegateTypeFromRequest(request);
            RuleType ruleType = ApproverDelegateType.getRuleTypeByApproverDelegateType(type);
            cond.setRuleType(ruleType);
        }
        ApproverDelegateType adt = ApproverDelegateType.getApproverDelegateTypeByRuleType(cond.getRuleType());
        if (adt == null)
            throw new ActionException("approverDelegate.rultType.notApprove");
        queryForm.populateToForm(cond);
        queryForm.setPageSize("5");
        return listApprover(mapping, form, request, response);
    }
}

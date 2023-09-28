package com.aof.web.struts.action.basic;

import com.aof.model.admin.Site;
import com.aof.model.basic.ExpensesCourse;
import com.aof.model.basic.query.ExpensesCourseQueryCondition;
import com.aof.model.basic.query.ExpensesCourseQueryOrder;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.service.basic.ExpensesCourseManager;
import com.aof.utils.SessionTempFile;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.basic.ExpensesCourseQueryForm;
import com.shcnc.hibernate.PersistentEnum;
import com.shcnc.struts.action.ActionException;
import com.shcnc.struts.action.ActionUtils;
import com.shcnc.struts.form.BeanForm;
import com.shcnc.utils.BeanHelper;
import com.shcnc.utils.ExportUtil;
import com.shcnc.utils.Exportable;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

public class ExpensesCourseAction extends BaseAction2 {
    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ExpensesCourseQueryForm queryForm = (ExpensesCourseQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(ExpensesCourseQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        ExpensesCourseManager fm = ServiceLocator.getExpensesCourseManager(request);
        Map conditions = constructQueryMap(queryForm);
        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = fm.getExpensesCourseList(conditions, 0, -1,
                    ExpensesCourseQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "expensesCourse";
            String suffix = ExportUtil.export(
                    exportType,
                    data,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable() {
                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources messages = ExpensesCourseAction.this.getResources(request);
                            row.add(messages.getMessage(ExpensesCourseAction.this.getLocale(request), "expensesCourse.id"));
                            row.add(messages.getMessage(ExpensesCourseAction.this.getLocale(request), "expensesCourse.code"));
                            row.add(messages.getMessage(ExpensesCourseAction.this.getLocale(request), "expensesCourse.description"));
                            row.add(messages.getMessage(ExpensesCourseAction.this.getLocale(request), "expensesCourse.type"));
                            row.add(messages.getMessage(ExpensesCourseAction.this.getLocale(request), "expensesCourse.currency"));
                            row.add(messages.getMessage(ExpensesCourseAction.this.getLocale(request), "expensesCourse.enabled.chnShortDescription"));
                        }

                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data, "id"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "code"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "description"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "type"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "currency"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "enabled.chnShortDescription"));
                        }
                    });
            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getExpensesCourseListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getExpensesCourseList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                ExpensesCourseQueryOrder.ID, queryForm.isDescend());
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(84));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ExpensesCourseQueryForm queryForm = (ExpensesCourseQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(ExpensesCourseQueryOrder.ID.getName());
            queryForm.setDescend(false);
            queryForm.setStatus("0");
        }
        ExpensesCourseManager fm = ServiceLocator.getExpensesCourseManager(request);
        Map conditions = constructQueryMap(queryForm);
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getExpensesCourseListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getExpensesCourseList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                ExpensesCourseQueryOrder.ID, queryForm.isDescend());
        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    private Map constructQueryMap(ExpensesCourseQueryForm queryForm) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        Integer id = ActionUtils.parseInt(queryForm.getId());
        if (id != null && !id.equals(""))
            conditions.put(ExpensesCourseQueryCondition.ID_EQ, id);
        return conditions;
    }

    private ExpensesCourse getExpensesCourse(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        ExpensesCourseManager expensesCourseManager = ServiceLocator.getExpensesCourseManager(request);
        ExpensesCourse expensesCourse = expensesCourseManager.getExpensesCourse(Integer.valueOf(Integer.parseInt(id)));
        if (expensesCourse == null)
            throw new ActionException("expensesCourse.notFound", id);
        return expensesCourse;
    }

    private void putEnumListToRequest(HttpServletRequest request) {
        request.setAttribute("X_ENABLEDDISABLEDLIST",
                PersistentEnum.getEnumList(EnabledDisabled.class));
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ExpensesCourse expensesCourse = getExpensesCourse(request);
        request.setAttribute("x_expensesCourse", expensesCourse);
        if (!isBack(request)) {
            BeanForm expensesCourseForm = (BeanForm)getForm("/updateExpensesCourse", request);
            expensesCourseForm.populate(expensesCourse, "to_form");
        }
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ExpensesCourse expensesCourse = getExpensesCourse(request);
        ExpensesCourseManager cm =
                ServiceLocator.getExpensesCourseManager(request);
        try {
            cm.deleteExpensesCourse(expensesCourse);
        } catch (Throwable t) {
            throw new ActionException("expensesCourse.delete.fail");
        }
        return mapping.findForward("success");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BeanForm expensesCourseForm = (BeanForm)form;
        ExpensesCourse expensesCourse = new ExpensesCourse();
        expensesCourseForm.populate(expensesCourse, "to_bean");
        ExpensesCourseManager expensesCourseManager = ServiceLocator.getExpensesCourseManager(request);
        request.setAttribute("X_OBJECT", expensesCourseManager.updateExpensesCourse(expensesCourse));
        request.setAttribute("X_ROWPAGE", "wmsbasic/expensesCourse/row.jsp");
        return mapping.findForward("success");
    }

    private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
        return getAndCheckSite("site_id", request);
    }

    private boolean hasSite(HttpServletRequest request) {
        String s = request.getParameter("site_id");
        return (s != null && !s.equals(""));
    }

    public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!isBack(request)) {
            ExpensesCourse expensesCourse = new ExpensesCourse();
            BeanForm expensesCourseForm = (BeanForm)getForm("/insertExpensesCourse", request);
            expensesCourseForm.populate(expensesCourse, "to_form");
        }
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ExpensesCourseManager cm = ServiceLocator.getExpensesCourseManager(request);
        BeanForm expensesCourseForm = (BeanForm)form;
        ExpensesCourse expensesCourse = new ExpensesCourse();
        expensesCourseForm.populate(expensesCourse, "to_bean");
        request.setAttribute("X_OBJECT", cm.insertExpensesCourse(expensesCourse));
        request.setAttribute("X_ROWPAGE", "wmsbasic/expensesCourse/row.jsp");
        return mapping.findForward("success");
    }
}

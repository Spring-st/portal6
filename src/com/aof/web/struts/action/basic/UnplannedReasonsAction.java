package com.aof.web.struts.action.basic;

import com.aof.model.admin.Site;
import com.aof.model.basic.UnplannedReasons;
import com.aof.model.basic.query.UnplannedReasonsQueryCondition;
import com.aof.model.basic.query.UnplannedReasonsQueryOrder;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.model.metadata.YesNo;
import com.aof.service.basic.UnplannedReasonsManager;
import com.aof.utils.SessionTempFile;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.BaseSessionQueryForm;
import com.aof.web.struts.form.basic.UnplannedReasonsQueryForm;
import com.shcnc.hibernate.PersistentEnum;
import com.shcnc.struts.action.ActionException;
import com.shcnc.struts.action.ActionUtils;
import com.shcnc.struts.form.BeanForm;
import com.shcnc.utils.BeanHelper;
import com.shcnc.utils.ExportUtil;
import com.shcnc.utils.Exportable;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.Date;
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

public class UnplannedReasonsAction extends BaseAction2 {
    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UnplannedReasonsQueryForm queryForm = (UnplannedReasonsQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(UnplannedReasonsQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        UnplannedReasonsManager fm = ServiceLocator.getUnplannedReasonsManager(request);
        Map conditions = constructQueryMap(queryForm);
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = fm.getUnplannedReasonsList(conditions, 0, -1, UnplannedReasonsQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "UnplannedReasons";
            String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable() {
                public void exportHead(List row, HttpServletRequest request) throws Exception {
                    MessageResources messages = UnplannedReasonsAction.this.getResources(request);
                    row.add(messages.getMessage(UnplannedReasonsAction.this.getLocale(request), "UnplannedReasons.instructions"));
                    row.add(messages.getMessage(UnplannedReasonsAction.this.getLocale(request), "UnplannedReasons.describe"));
                    row.add(messages.getMessage(UnplannedReasonsAction.this.getLocale(request), "UnplannedReasons.expenses_course"));
                    row.add(messages.getMessage(UnplannedReasonsAction.this.getLocale(request), "UnplannedReasons.department_cost"));
                    row.add(messages.getMessage(UnplannedReasonsAction.this.getLocale(request), "UnplannedReasons.date"));
                }

                public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                    row.add(BeanHelper.getBeanPropertyValue(data, "instructions"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "describe"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "expenses_course"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "department_cost"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "date"));
                }
            });
            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getUnplannedReasonsListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getUnplannedReasonsList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                UnplannedReasonsQueryOrder.ID, queryForm.isDescend());
        List<Site> siteList = getGrantedSiteDeparmentList(request);
        request.setAttribute("x_siteList", siteList);
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(80));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UnplannedReasonsQueryForm queryForm = (UnplannedReasonsQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(UnplannedReasonsQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        UnplannedReasonsManager fm = ServiceLocator.getUnplannedReasonsManager(request);
        Map conditions = constructQueryMap(queryForm);
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = fm.getUnplannedReasonsList(conditions, 0, -1, UnplannedReasonsQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "UnplannedReasons";
            String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable() {
                public void exportHead(List row, HttpServletRequest request) throws Exception {
                    MessageResources messages = UnplannedReasonsAction.this.getResources(request);
                    row.add(messages.getMessage(UnplannedReasonsAction.this.getLocale(request), "UnplannedReasons.id"));
                }

                public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                    row.add(BeanHelper.getBeanPropertyValue(data, "id"));
                }
            });
            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getUnplannedReasonsListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getUnplannedReasonsList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                UnplannedReasonsQueryOrder.ID, queryForm.isDescend());
        List<Site> siteList = getGrantedSiteDeparmentList(request);
        request.setAttribute("x_siteList", siteList);
        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    private Map constructQueryMap(UnplannedReasonsQueryForm queryForm) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        Integer id = ActionUtils.parseInt(queryForm.getId());
        if (id != null && !id.equals(""))
            conditions.put(UnplannedReasonsQueryCondition.ID_EQ, id);
        String type = queryForm.getType();
        if (type != null && !type.equals(""))
            conditions.put(UnplannedReasonsQueryCondition.TYPE_EQ, type);
        String status = queryForm.getStatus();
        if (status != null && !status.equals(""))
            conditions.put(UnplannedReasonsQueryCondition.ENABLED_EQ, status);
        return conditions;
    }

    private UnplannedReasons getUnplannedReasons(HttpServletRequest request) throws Exception {
        Integer id = ActionUtils.parseInt(request.getParameter("id"));
        UnplannedReasonsManager UnplannedReasonsManager = ServiceLocator.getUnplannedReasonsManager(request);
        UnplannedReasons UnplannedReasons = UnplannedReasonsManager.getUnplannedReasons(id);
        if (UnplannedReasons == null)
            throw new ActionException("UnplannedReasons.notFound", id);
        return UnplannedReasons;
    }

    private void putEnumListToRequest(HttpServletRequest request) {
        request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
        request.setAttribute("x_type", PersistentEnum.getEnumList(YesNo.class));
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UnplannedReasons reasons = getUnplannedReasons(request);
        request.setAttribute("x_unplanned", reasons);
        if (!isBack(request)) {
            BeanForm UnplannedReasonsForm = (BeanForm)getForm("/updateUnplannedReasons", request);
            UnplannedReasonsForm.populate(reasons, "to_form");
        }
        putEnumListToRequest(request);
        List<Site> siteList = getGrantedSiteDeparmentList(request);
        request.setAttribute("x_siteList", siteList);
        return mapping.findForward("page");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BeanForm UnplannedReasonsForm = (BeanForm)form;
        UnplannedReasons UnplannedReasons = new UnplannedReasons();
        UnplannedReasonsForm.populate(UnplannedReasons, "to_bean");
        UnplannedReasonsManager UnplannedReasonsManager = ServiceLocator.getUnplannedReasonsManager(request);
        UnplannedReasons.setDate(new Date());
        UnplannedReasons.setUser(getCurrentUser(request));
        UnplannedReasons = UnplannedReasonsManager.updateUnplannedReasons(UnplannedReasons);
        request.setAttribute("X_OBJECT", UnplannedReasons);
        request.setAttribute("X_ROWPAGE", "wmsbasic/unplannedReasons/row.jsp");
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
            UnplannedReasons UnplannedReasons = new UnplannedReasons();
            BeanForm UnplannedReasonsForm = (BeanForm)getForm("/insertUnplannedReasons", request);
            UnplannedReasonsForm.populate(UnplannedReasons, "to_form");
        }
        List<Site> siteList = getGrantedSiteDeparmentList(request);
        request.setAttribute("x_siteList", siteList);
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UnplannedReasonsManager cm = ServiceLocator.getUnplannedReasonsManager(request);
        BeanForm beanForm = (BeanForm)form;
        UnplannedReasons reasons = new UnplannedReasons();
        reasons.setUser(getCurrentUser(request));
        reasons.setDate(new Date());
        beanForm.populate(reasons, "to_bean");
        reasons = cm.insertUnplannedReasons(reasons);
        request.setAttribute("X_OBJECT", reasons);
        request.setAttribute("X_ROWPAGE", "wmsbasic/unplannedReasons/row.jsp");
        return mapping.findForward("success");
    }
}

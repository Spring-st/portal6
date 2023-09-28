package com.aof.web.struts.action.basic;

import com.aof.model.basic.query.ReportEntersSellsSavesQueryOrder;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.service.basic.ReportEntersSellsSavesManager;
import com.aof.utils.SessionTempFile;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.BaseSessionQueryForm;
import com.aof.web.struts.form.basic.ReportEntersSellsSavesQueryForm;
import com.shcnc.hibernate.PersistentEnum;
import com.shcnc.struts.action.ActionUtils;
import com.shcnc.utils.BeanHelper;
import com.shcnc.utils.ExportUtil;
import com.shcnc.utils.Exportable;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

public class ReportEntersSellsSavesAction extends BaseAction2 {
    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReportEntersSellsSavesQueryForm queryForm = (ReportEntersSellsSavesQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(ReportEntersSellsSavesQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        ReportEntersSellsSavesManager fm = ServiceLocator.getReportEntersSellsSavesManager(request);
        Map conditions = constructQueryMap(queryForm);
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = fm.getReportEntersSellsSavesList(conditions, 0, -1, ReportEntersSellsSavesQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "ReportEntersSellsSaves";
            String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable() {
                public void exportHead(List row, HttpServletRequest request) throws Exception {
                    MessageResources messages = ReportEntersSellsSavesAction.this.getResources(request);
                    row.add(messages.getMessage(ReportEntersSellsSavesAction.this.getLocale(request), "ReportEntersSellsSaves.part.id"));
                    row.add(messages.getMessage(ReportEntersSellsSavesAction.this.getLocale(request), "DPI"));
                    row.add(messages.getMessage(ReportEntersSellsSavesAction.this.getLocale(request), "part.describe1"));
                    row.add(messages.getMessage(ReportEntersSellsSavesAction.this.getLocale(request), "part.describe2"));
                    row.add(messages.getMessage(ReportEntersSellsSavesAction.this.getLocale(request), "ReportEntersSellsSaves.month"));
                    row.add(messages.getMessage(ReportEntersSellsSavesAction.this.getLocale(request), "ReportEntersSellsSaves.start_date"));
                    row.add(messages.getMessage(ReportEntersSellsSavesAction.this.getLocale(request), "ReportEntersSellsSaves.end_date"));
                    row.add(messages.getMessage(ReportEntersSellsSavesAction.this.getLocale(request), "ReportEntersSellsSaves.initial_qty"));
                    row.add(messages.getMessage(ReportEntersSellsSavesAction.this.getLocale(request), "ReportEntersSellsSaves.balance_qty"));
                }

                public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                    row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "part.dpiNo"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "part.describe2"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "month"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "start_date"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "end_date"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "initial_qty"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "balance_qty"));
                }
            });
            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getReportEntersSellsSavesListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getReportEntersSellsSavesList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                ReportEntersSellsSavesQueryOrder.ID, queryForm.isDescend());
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(81));
        return mapping.findForward("page");
    }

    private Map constructQueryMap(ReportEntersSellsSavesQueryForm queryForm) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        Integer id = ActionUtils.parseInt(queryForm.getId());
        return conditions;
    }

    private void putEnumListToRequest(HttpServletRequest request) {
        request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
    }

    public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mouth = request.getParameter("mouth");
        ReportEntersSellsSavesManager manager = ServiceLocator.getReportEntersSellsSavesManager(request);
        manager.insertReportEntersSellsSaves(mouth);
        return mapping.findForward("success");
    }

    public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Map<Object, Object>> list = new ArrayList();
        for (int i = 1; i <= 12; i++) {
            Map<Object, Object> map = new HashMap<Object, Object>();
            map.put("mouth", Integer.valueOf(i));
            list.add(map);
        }
        request.setAttribute("x_list", list);
        return mapping.findForward("page");
    }

    public ActionForward validateReportEntersSellsSaves(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");
//        response.setCharacterEncoding("UTF-8");
        JsonConfig cfg = new JsonConfig();
        ReportEntersSellsSavesManager manager = ServiceLocator.getReportEntersSellsSavesManager(request);
        JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
        response.getWriter().print(jo);
        return null;
    }
}

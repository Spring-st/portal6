package com.aof.web.struts.action.basic;

import com.aof.model.basic.query.WmsPartQueryCondition;
import com.aof.model.basic.query.WmsPartQueryOrder;
import com.aof.model.comprehensive.query.BomQueryOrder;
import com.aof.service.comprehensive.BomManager;
import com.aof.utils.SessionTempFile;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.BaseSessionQueryForm;
import com.aof.web.struts.form.basic.BomQueryForm;
import com.shcnc.utils.BeanHelper;
import com.shcnc.utils.ExportUtil;
import com.shcnc.utils.Exportable;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

public class BomAction extends BaseAction2 {
    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BomQueryForm queryForm = (BomQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(WmsPartQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        BomManager fm = ServiceLocator.getBomManager(request);
        Map conditions = constructQueryMap(queryForm);
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = fm.getBomList(conditions, 0, -1, BomQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "bom";
            String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable() {
                public void exportHead(List row, HttpServletRequest request) throws Exception {
                    MessageResources messages = BomAction.this.getResources(request);
                    row.add(messages.getMessage(BomAction.this.getLocale(request), "bom.assembly.no"));
                    row.add(messages.getMessage(BomAction.this.getLocale(request), "bom.assembly.describe"));
                    row.add(messages.getMessage(BomAction.this.getLocale(request), "bom.part.id"));
                    row.add(messages.getMessage(BomAction.this.getLocale(request), "bom.part.describe"));
                    row.add(messages.getMessage(BomAction.this.getLocale(request), "bom.quantity"));
                    row.add(messages.getMessage(BomAction.this.getLocale(request), "bom.date"));
                }

                public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                    row.add(BeanHelper.getBeanPropertyValue(data, "product_no.id"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "father_part.describe1"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "child_part.id"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "child_part.describe1"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "unit_qty"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "date"));
                }
            });
            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getBomListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getBomList(conditions, queryForm.getPageNoAsInt(),
                queryForm.getPageSizeAsInt(), BomQueryOrder.ID,
                queryForm.isDescend());
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(91));
        return mapping.findForward("page");
    }

    public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BomQueryForm queryForm = (BomQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(WmsPartQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        BomManager fm = ServiceLocator.getBomManager(request);
        Map conditions = constructQueryMap(queryForm);
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getBomListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getBomList(conditions, queryForm.getPageNoAsInt(),
                queryForm.getPageSizeAsInt(), BomQueryOrder.ID,
                queryForm.isDescend());
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(91));
        return mapping.findForward("page");
    }

    private Map constructQueryMap(BomQueryForm queryForm) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        String id = queryForm.getId();
        Date date = queryForm.getDate();
        if (id != null && !id.equals(""))
            conditions.put(WmsPartQueryCondition.ID_EQ, id);
        return conditions;
    }
}

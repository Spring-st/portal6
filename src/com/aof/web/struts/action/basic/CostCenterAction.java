package com.aof.web.struts.action.basic;

import com.aof.model.admin.Site;
import com.aof.model.basic.CostCenter;
import com.aof.model.basic.query.CostCenterQueryCondition;
import com.aof.model.basic.query.CostCenterQueryOrder;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.service.basic.CostCenterManager;
import com.aof.utils.SessionTempFile;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.basic.CostCenterQueryForm;
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

public class CostCenterAction extends BaseAction2 {
    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CostCenterQueryForm queryForm = (CostCenterQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(CostCenterQueryOrder.ID.getName());
            queryForm.setDescend(false);
            queryForm.setStatus("0");
        }
        CostCenterManager fm = ServiceLocator.getCostCenterManager(request);
        Map conditions = constructQueryMap(queryForm);
        getConditionAndOrder(queryForm, conditions, request);
        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = fm.getCostCenterList(conditions, 0, -1,
                    CostCenterQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "costCenter";
            String suffix = ExportUtil.export(
                    exportType,
                    data,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable() {
                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources messages = CostCenterAction.this.getResources(request);
                            row.add(messages.getMessage(CostCenterAction.this.getLocale(request), "costCenter.id"));
                            row.add(messages.getMessage(CostCenterAction.this.getLocale(request), "costCenter.code"));
                            row.add(messages.getMessage(CostCenterAction.this.getLocale(request), "costCenter.description"));
                            row.add(messages.getMessage(CostCenterAction.this.getLocale(request), "enabled.chnShortDescription"));
                        }

                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data, "id"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "code"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "description"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "enabled.chnShortDescription"));
                        }
                    });
            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getCostCenterListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getCostCenterList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                CostCenterQueryOrder.ID, queryForm.isDescend());
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("X_SITELIST", getAndCheckGrantedSiteList(request));
        request.setAttribute("x_selType", Integer.valueOf(83));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CostCenterQueryForm queryForm = (CostCenterQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(CostCenterQueryOrder.ID.getName());
            queryForm.setDescend(false);
            queryForm.setStatus("0");
        }
        CostCenterManager fm = ServiceLocator.getCostCenterManager(request);
        Map conditions = constructQueryMap(queryForm);
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getCostCenterListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getCostCenterList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                CostCenterQueryOrder.ID, queryForm.isDescend());
        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    private Map constructQueryMap(CostCenterQueryForm queryForm) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        Integer id = ActionUtils.parseInt(queryForm.getId());
        if (id != null && !id.equals(""))
            conditions.put(CostCenterQueryCondition.ID_EQ, id);
        return conditions;
    }

    private CostCenter getCostCenter(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        CostCenterManager costCenterManager = ServiceLocator.getCostCenterManager(request);
        CostCenter costCenter = costCenterManager.getCostCenter(Integer.valueOf(Integer.parseInt(id)));
        if (costCenter == null)
            throw new ActionException("costCenter.notFound", id);
        return costCenter;
    }

    private void putEnumListToRequest(HttpServletRequest request) {
        request.setAttribute("X_ENABLEDDISABLEDLIST",
                PersistentEnum.getEnumList(EnabledDisabled.class));
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CostCenter costCenter = getCostCenter(request);
        request.setAttribute("x_costCenter", costCenter);
        if (!isBack(request)) {
            BeanForm costCenterForm = (BeanForm)getForm("/updateCostCenter",
                    request);
            costCenterForm.populate(costCenter, "to_form");
        }
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CostCenter costCenter = getCostCenter(request);
        CostCenterManager cm = ServiceLocator.getCostCenterManager(request);
        try {
            cm.deleteCostCenter(costCenter);
        } catch (Throwable t) {
            throw new ActionException("costCenter.delete.fail");
        }
        return mapping.findForward("success");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BeanForm costCenterForm = (BeanForm)form;
        CostCenter costCenter = new CostCenter();
        costCenterForm.populate(costCenter, "to_bean");
        CostCenterManager costCenterManager = ServiceLocator.getCostCenterManager(request);
        request.setAttribute("X_OBJECT", costCenterManager.updateCostCenter(costCenter));
        request.setAttribute("X_ROWPAGE", "wmsbasic/costCenter/row.jsp");
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
            CostCenter costCenter = new CostCenter();
            BeanForm costCenterForm = (BeanForm)getForm("/insertCostCenter",
                    request);
            costCenterForm.populate(costCenter, "to_form");
        }
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CostCenterManager cm = ServiceLocator.getCostCenterManager(request);
        BeanForm costCenterForm = (BeanForm)form;
        CostCenter costCenter = new CostCenter();
        costCenterForm.populate(costCenter, "to_bean");
        request.setAttribute("X_OBJECT", cm.insertCostCenter(costCenter));
        request.setAttribute("X_ROWPAGE", "wmsbasic/costCenter/row.jsp");
        return mapping.findForward("success");
    }
}

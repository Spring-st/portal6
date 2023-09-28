package com.aof.web.struts.action.basic;

import com.aof.model.admin.Site;
import com.aof.model.basic.WmsTool;
import com.aof.model.basic.query.WmsToolQueryCondition;
import com.aof.model.basic.query.WmsToolQueryOrder;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.model.metadata.YesNo;
import com.aof.service.basic.WmsToolManager;
import com.aof.utils.SessionTempFile;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.BaseSessionQueryForm;
import com.aof.web.struts.form.basic.WmsToolQueryForm;
import com.shcnc.hibernate.PersistentEnum;
import com.shcnc.struts.action.ActionException;
import com.shcnc.struts.action.ActionUtils;
import com.shcnc.struts.form.BeanForm;
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
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

public class WmsToolAction extends BaseAction2 {
    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsToolQueryForm queryForm = (WmsToolQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(WmsToolQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        WmsToolManager fm = ServiceLocator.getWmsToolManager(request);
        Map conditions = constructQueryMap(queryForm);
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = fm.getWmsToolList(conditions, 0, -1, WmsToolQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "containerType";
            String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable() {
                public void exportHead(List row, HttpServletRequest request) throws Exception {
                    MessageResources messages = WmsToolAction.this.getResources(request);
                    row.add(messages.getMessage(WmsToolAction.this.getLocale(request), "containerType.code"));
                    row.add(messages.getMessage(WmsToolAction.this.getLocale(request), "containerType.capacity"));
                    row.add(messages.getMessage(WmsToolAction.this.getLocale(request), "containerType.date"));
                    row.add(messages.getMessage(WmsToolAction.this.getLocale(request), "containerType.enabled.chnShortDescription"));
                }

                public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                    row.add(BeanHelper.getBeanPropertyValue(data, "code"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "capacity"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "date"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "enabled.chnShortDescription"));
                }
            });
            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getWmsToolListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getWmsToolList(conditions, queryForm.getPageNoAsInt(),
                queryForm.getPageSizeAsInt(), WmsToolQueryOrder.ID,
                queryForm.isDescend());
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(14));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    public ActionForward listProductOffline(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsToolQueryForm queryForm = (WmsToolQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(WmsToolQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        WmsToolManager fm = ServiceLocator.getWmsToolManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(WmsToolQueryCondition.STATUS_EQ, Integer.valueOf(0));
        getConditionAndOrder(queryForm, conditions, request);
        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = fm.getWmsToolList(conditions, 0, -1, WmsToolQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "containerType";
            String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable() {
                public void exportHead(List row, HttpServletRequest request) throws Exception {
                    MessageResources messages = WmsToolAction.this.getResources(request);
                    row.add(messages.getMessage(WmsToolAction.this.getLocale(request), "containerType.id"));
                }

                public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                    row.add(BeanHelper.getBeanPropertyValue(data, "id"));
                }
            });
            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getWmsToolListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getWmsToolList(conditions, queryForm.getPageNoAsInt(),
                queryForm.getPageSizeAsInt(), WmsToolQueryOrder.ID,
                queryForm.isDescend());
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(4));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    public ActionForward selectTool(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsToolQueryForm queryForm = (WmsToolQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder()))
            queryForm.setOrder(WmsToolQueryOrder.ID.getName());
        WmsToolManager fm = ServiceLocator.getWmsToolManager(request);
        Map conditions = constructQueryMap(queryForm);
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getWmsToolListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getWmsToolList(conditions, queryForm.getPageNoAsInt(),
                queryForm.getPageSizeAsInt(), WmsToolQueryOrder.ID,
                queryForm.isDescend());
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
        return mapping.findForward("page");
    }

    private Map constructQueryMap(WmsToolQueryForm queryForm) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        Integer id = ActionUtils.parseInt(queryForm.getId());
        if (id != null && !id.equals(""))
            conditions.put(WmsToolQueryCondition.ID_EQ, id);
        String code = queryForm.getCode();
        if (code != null && !code.equals(""))
            conditions.put(WmsToolQueryCondition.CODE_EQ, code);
        String isPrint = queryForm.getIsPrint();
        if (isPrint != null && !isPrint.equals(""))
            conditions.put(WmsToolQueryCondition.ISPRINT_EQ, isPrint);
        return conditions;
    }

    private WmsTool getWmsTool(HttpServletRequest request) throws Exception {
        Integer id = ActionUtils.parseInt(request.getParameter("id"));
        WmsToolManager wmsToolManager = ServiceLocator.getWmsToolManager(request);
        WmsTool wmsTool = wmsToolManager.getWmsTool(id);
        if (wmsTool == null)
            throw new ActionException("wmsTool.notFound", id);
        return wmsTool;
    }

    private void putEnumListToRequest(HttpServletRequest request) {
        List siteList = getAndCheckGrantedSiteList(request);
        request.setAttribute("X_SITELIST", siteList);
        request.setAttribute("X_ISPRINT", PersistentEnum.getEnumList(YesNo.class));
        request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsTool wmsTool = getWmsTool(request);
        request.setAttribute("x_containerType", wmsTool);
        if (!isBack(request)) {
            BeanForm wmsToolForm = (BeanForm)getForm("/updateWmsTool", request);
            wmsToolForm.populate(wmsTool, "to_form");
        }
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsTool containerType = getWmsTool(request);
        WmsToolManager cm = ServiceLocator.getWmsToolManager(request);
        try {
            cm.deleteWmsTool(containerType);
        } catch (Throwable t) {
            throw new ActionException("containerType.delete.fail");
        }
        return mapping.findForward("success");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BeanForm containerTypeForm = (BeanForm)form;
        WmsTool containerType = new WmsTool();
        containerTypeForm.populate(containerType, "to_bean");
        WmsToolManager containerTypeManager = ServiceLocator.getWmsToolManager(request);
        request.setAttribute("X_OBJECT", containerTypeManager.updateWmsTool(containerType));
        request.setAttribute("X_ROWPAGE", "wmsbasic/wmsTool/row.jsp");
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
        Site site = null;
        if (!isBack(request)) {
            WmsTool containerType = new WmsTool();
            BeanForm containerTypeForm = (BeanForm)getForm("/insertWmsTool", request);
            containerTypeForm.populate(containerType, "to_form");
        }
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Site site = null;
        WmsToolManager cm = ServiceLocator.getWmsToolManager(request);
        BeanForm containerTypeForm = (BeanForm)form;
        WmsTool containerType = new WmsTool();
        containerTypeForm.populate(containerType, "to_bean");
        containerType.setStatus(YesNo.NO);
        request.setAttribute("X_OBJECT", cm.insertWmsTool(containerType));
        request.setAttribute("X_ROWPAGE", "wmsbasic/wmsTool/row.jsp");
        return mapping.findForward("success");
    }

    public ActionForward pirntCodeItemReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsToolManager porm = ServiceLocator.getWmsToolManager(request);
        String string = request.getParameter("str");
        String[] strings = string.split(",");
        List<WmsTool> boxlist = new ArrayList<WmsTool>();
        for (int i = 0; i < strings.length; i++) {
            Integer str = Integer.valueOf(Integer.parseInt(strings[i]));
            boxlist.add(porm.getWmsTool(str));
        }
        request.setAttribute("X_RESULTLIST", boxlist);
        putEnumListToRequest(request);
        request.setAttribute("path", request.getContextPath());
        return mapping.findForward("page");
    }

    public ActionForward updatePirntCodeItemReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsToolManager poip = ServiceLocator.getWmsToolManager(request);
        String ids = request.getParameter("ids");
        String[] idr = ids.split(";");
        String idInteger = null;
        byte b;
        int i;
        String[] arrayOfString1;
        for (i = (arrayOfString1 = idr).length, b = 0; b < i; ) {
            String id = arrayOfString1[b];
            WmsTool box = poip.getWmsTool(Integer.valueOf(Integer.parseInt(id)));
            poip.updateWmsTool(box);
            b++;
        }
        return new ActionForward("listWmsTool.do", true);
    }

    public ActionForward selectToolNext(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("x_arrays", request.getParameter("arrays"));
        return mapping.findForward("page");
    }
}

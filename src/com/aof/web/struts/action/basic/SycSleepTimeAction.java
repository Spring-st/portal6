package com.aof.web.struts.action.basic;

import com.aof.model.admin.Site;
import com.aof.model.basic.SycSleepTime;
import com.aof.model.basic.query.SycSleepTimeQueryCondition;
import com.aof.model.basic.query.SycSleepTimeQueryOrder;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.model.metadata.YesNo;
import com.aof.model.product.query.BasicCustomerQueryCondition;
import com.aof.service.basic.SycSleepTimeManager;
import com.aof.utils.SessionTempFile;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.BaseSessionQueryForm;
import com.aof.web.struts.form.basic.SycSleepTimeQueryForm;
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
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

public class SycSleepTimeAction extends BaseAction2 {
    public ActionForward listSycSleepTime(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SycSleepTimeQueryForm queryForm = (SycSleepTimeQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(SycSleepTimeQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        SycSleepTimeManager fm = ServiceLocator.getSycSleepTimeManager(request);
        Map conditions = constructQueryMap(queryForm);
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = fm.getSycSleepTimeList(conditions, 0, -1, SycSleepTimeQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "containerType";
            String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable() {
                public void exportHead(List row, HttpServletRequest request) throws Exception {
                    MessageResources messages = SycSleepTimeAction.this.getResources(request);
                    row.add(messages.getMessage(SycSleepTimeAction.this.getLocale(request), "containerType.code"));
                    row.add(messages.getMessage(SycSleepTimeAction.this.getLocale(request), "containerType.capacity"));
                    row.add(messages.getMessage(SycSleepTimeAction.this.getLocale(request), "containerType.date"));
                    row.add(messages.getMessage(SycSleepTimeAction.this.getLocale(request), "containerType.enabled.chnShortDescription"));
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
            queryForm.init(fm.getSycSleepTimeCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getSycSleepTimeList(conditions, queryForm.getPageNoAsInt(),
                queryForm.getPageSizeAsInt(), SycSleepTimeQueryOrder.ID,
                queryForm.isDescend());
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(180));
        return mapping.findForward("page");
    }

    private Map constructQueryMap(SycSleepTimeQueryForm queryForm) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        Integer id = ActionUtils.parseInt(queryForm.getId());
        if (id != null && !id.equals(""))
            conditions.put(SycSleepTimeQueryCondition.ID_EQ, id);
        String type = queryForm.getType();
        if (type != null && !type.equals(""))
            conditions.put(SycSleepTimeQueryCondition.TYPE_EQ, type);
        String sleepTime = queryForm.getSleepTime();
        if (sleepTime != null && !sleepTime.equals(""))
            conditions.put(SycSleepTimeQueryCondition.SLEEPTIME_EQ, sleepTime);
        return conditions;
    }

    private SycSleepTime getSycSleepTime(HttpServletRequest request) throws Exception {
        Integer id = ActionUtils.parseInt(request.getParameter("id"));
        SycSleepTimeManager SycSleepTimeManager = ServiceLocator.getSycSleepTimeManager(request);
        SycSleepTime SycSleepTime = SycSleepTimeManager.getSycSleepTime(id);
        if (SycSleepTime == null)
            throw new ActionException("SycSleepTime.notFound", id);
        return SycSleepTime;
    }

    private void putEnumListToRequest(HttpServletRequest request) {
        List siteList = getAndCheckGrantedSiteList(request);
        request.setAttribute("X_SITELIST", siteList);
        request.setAttribute("X_ISPRINT", PersistentEnum.getEnumList(YesNo.class));
        request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SycSleepTime SycSleepTime = getSycSleepTime(request);
        request.setAttribute("x_containerType", SycSleepTime);
        if (!isBack(request)) {
            BeanForm SycSleepTimeForm = (BeanForm)getForm("/updateSycSleepTimeMaintenanceAction", request);
            SycSleepTimeForm.populate(SycSleepTime, "to_form");
        }
        return mapping.findForward("page");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SycSleepTime containerType = getSycSleepTime(request);
        SycSleepTimeManager cm = ServiceLocator.getSycSleepTimeManager(request);
        try {
            cm.deleteSycSleepTime(containerType);
        } catch (Throwable t) {
            throw new ActionException("containerType.delete.fail");
        }
        return new ActionForward("listSycSleepTimeListAction.do", true);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BeanForm sycSleepTimeForm = (BeanForm)form;
        SycSleepTime sycSleepTime = new SycSleepTime();
        sycSleepTimeForm.populate(sycSleepTime, "to_bean");
        SycSleepTimeManager sycSleepTimeManager = ServiceLocator.getSycSleepTimeManager(request);
        sycSleepTime = sycSleepTimeManager.updateSycSleepTime(sycSleepTime);
        request.setAttribute("X_OBJECT", sycSleepTime);
        request.setAttribute("X_ROWPAGE", "wmsbasic/sycSleepTime/row.jsp");
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
            SycSleepTime containerType = new SycSleepTime();
            BeanForm containerTypeForm = (BeanForm)getForm("/insertSycSleepTimeMaintenanceAction", request);
            containerTypeForm.populate(containerType, "to_form");
        }
        return mapping.findForward("page");
    }

    public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Site site = null;
        SycSleepTimeManager cm = ServiceLocator.getSycSleepTimeManager(request);
        BeanForm containerTypeForm = (BeanForm)form;
        SycSleepTime containerType = new SycSleepTime();
        containerTypeForm.populate(containerType, "to_bean");
        request.setAttribute("X_OBJECT", cm.insertSycSleepTime(containerType));
        request.setAttribute("X_ROWPAGE", "wmsbasic/sycSleepTime/row.jsp");
        return mapping.findForward("success");
    }

    public ActionForward AJAXType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code = request.getParameter("type");
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        if (code != null && code.trim().length() > 0)
            conditions.put(SycSleepTimeQueryCondition.TYPE_EQ, code);
        SycSleepTimeManager cm = ServiceLocator.getSycSleepTimeManager(request);
        int result = cm.getSycSleepTimeCount(conditions);
        response.setContentType("text/html;charset=utf-8");
//        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(result);
        return null;
    }

    public ActionForward AJAXEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code = request.getParameter("type");
        Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        if (code != null && code.trim().length() > 0) {
            conditions.put(BasicCustomerQueryCondition.CODE_EQ, code);
            conditions.put(BasicCustomerQueryCondition.EDITID_NE, id);
        }
        SycSleepTimeManager cm = ServiceLocator.getSycSleepTimeManager(request);
        int result = cm.getSycSleepTimeCount(conditions);
        response.setContentType("text/html;charset=utf-8");
//        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(result);
        return null;
    }
}

package com.aof.web.struts.action.admin;

import com.aof.model.admin.Department;
import com.aof.model.admin.Site;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.service.admin.DepartmentManager;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.admin.DepartmentQueryForm;
import com.shcnc.hibernate.PersistentEnum;
import com.shcnc.struts.action.ActionException;
import com.shcnc.struts.action.ActionUtils;
import com.shcnc.struts.form.BeanForm;
import com.shcnc.struts.form.ComboBox;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DepartmentAction extends BaseAction2 {
    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DepartmentQueryForm queryForm = (DepartmentQueryForm)form;
        ComboBox comboSite = queryForm.getSite();
        comboSite.setList(getAndCheckGrantedSiteList(request));
        DepartmentManager dm = ServiceLocator.getDepartmentManager(request);
        request.setAttribute("X_DEPARTMENTXML", dm.getDepartmentXmlBySite(ActionUtils.parseInt(comboSite.getValue())));
        return mapping.findForward("page");
    }

    public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Site s = getAndCheckSite("siteId", request);
        if (!isBack(request)) {
            DepartmentManager dm = ServiceLocator.getDepartmentManager(request);
            Integer parentId = ActionUtils.parseInt(request.getParameter("parentId"));
            Department parent = null;
            if (parentId != null) {
                parent = dm.getDepartment(parentId);
                if (parent == null) throw new ActionException("department.notFound", parentId);
            }
            Department d = new Department();
            d.setSite(s);
            d.setParentDepartment(parent);
            BeanForm departmentForm = (BeanForm)getForm("/insertDepartment", request);
            departmentForm.populateToForm(d);
        }
        prepareData(s, request, (Department)null);
        return mapping.findForward("page");
    }

    public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DepartmentManager dm = ServiceLocator.getDepartmentManager(request);
        BeanForm departmentForm = (BeanForm)form;
        Department d = new Department();
        departmentForm.populateToBean(d);
        checkSite(d.getSite().getId(), request);
        request.setAttribute("X_OBJECT", dm.saveDepartment(d));

        return mapping.findForward("success");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = ActionUtils.parseInt(request.getParameter("id"));
        DepartmentManager dm = ServiceLocator.getDepartmentManager(request);
        Department d = dm.getDepartment(id);
        if (d == null) throw new ActionException("department.notFound", id);
        Site s = d.getSite();
        checkSite(s, request);
        if (!isBack(request)) {
            BeanForm departmentForm = (BeanForm)getForm("/updateDepartment", request);
            departmentForm.populate(d, "to_form");
        }
        prepareData(s, request, d);
        request.setAttribute("X_ENABLEDUSERLIST", ServiceLocator.getUserManager(request).getEnabledUserListOfDepartment(d));
        return mapping.findForward("page");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DepartmentManager dm = ServiceLocator.getDepartmentManager(request);
        BeanForm departmentForm = (BeanForm)form;
        Integer id = ActionUtils.parseInt((String)departmentForm.get("id"));
        Department d = dm.getDepartment(id);
        if (d == null) throw new ActionException("department.notFound", id);
        checkSite(d.getSite(), request);

        departmentForm.populateToBean(d, (HttpServletRequest) null, new String[] { "site.id" });
        request.setAttribute("X_OBJECT", dm.saveDepartment(d));
        return mapping.findForward("success");
    }

    private void prepareData(Site s, HttpServletRequest request, Department me) {
        DepartmentManager dm = ServiceLocator.getDepartmentManager(request);
        dm.fillDepartment(s, false);
        Set<Department> children = null;
        if (me != null) {
            children = new HashSet();
            children.add(me);
        }
        for (Iterator<Department> itor = s.getDepartments().iterator(); itor.hasNext(); ) {
            Department d = itor.next();
            if (me != null) {
                if (d.equals(me)) {
                    itor.remove();
                    continue;
                }
                Department pd = d.getParentDepartment();
                if (children.contains(pd)) {
                    itor.remove();
                    children.add(d);
                    continue;
                }
            }
            d.setIndentName("��" + d.getIndentName());
        }
        request.setAttribute("X_SITE", s);
        request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
    }
}
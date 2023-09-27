package com.aof.web.struts.action.admin;

import com.aof.model.admin.City;
import com.aof.model.admin.Function;
import com.aof.model.admin.Province;
import com.aof.model.admin.Site;
import com.aof.model.admin.User;
import com.aof.model.admin.query.CityQueryCondition;
import com.aof.model.admin.query.CityQueryOrder;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.service.admin.CityManager;
import com.aof.service.admin.EmailManager;
import com.aof.service.admin.FunctionManager;
import com.aof.service.admin.ProvinceManager;
import com.aof.service.admin.UserManager;
import com.aof.web.struts.action.BaseAction;
import com.aof.web.struts.action.ServiceLocator;
import com.shcnc.hibernate.PersistentEnum;
import com.shcnc.struts.action.ActionException;
import com.shcnc.struts.action.ActionUtils;
import com.shcnc.struts.action.BackToInputActionException;
import com.shcnc.struts.form.BeanForm;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CityAction extends BaseAction {
    public ActionForward promote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        City city = getCity(request);
        if (city.getSite() == null) {
            throw new ActionException("city.error.alreadyPromoted", city.getEngName());
        }

        if (request.getParameter("confirm") != null) {
            CityManager cm = ServiceLocator.getCityManager(request);
            cm.promoteCity(city);
            request.setAttribute("X_OBJECT", city);
            request.setAttribute("X_ROWPAGE", "city/row.jsp");

            return mapping.findForward("success");
        }

        String name = null;
        if (isEnglish(request)) {
            name = city.getEngName();
        } else {
            name = city.getChnName();
        }
        postGlobalMessage("city.promote.confirm", name, request);
        return mapping.findForward("confirm");
    }


    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Province province = getProvinceFromRequest(request);
        Map<Object, Object> conds = new HashMap<Object, Object>();
        conds.put(CityQueryCondition.PROVINCE_ID_EQ, province.getId());

        CityManager fm = ServiceLocator.getCityManager(request);
        List result = fm.getCityList(conds, 0, -1, isEnglish(request) ? CityQueryOrder.ENGNAME : CityQueryOrder.CHNNAME, false);
        request.setAttribute("X_RESULTLIST", result);
        if (!result.isEmpty()) {
            request.setAttribute("X_FIRST", result.get(0));
        }
        return mapping.findForward("page");
    }

    private Province getProvinceFromRequest(HttpServletRequest request) throws Exception {
        Integer province_id = ActionUtils.parseInt(request.getParameter("province_id"));
        ProvinceManager provinceManager = ServiceLocator.getProvinceManager(request);
        Province province = provinceManager.getProvince(province_id);
        if (province == null)
            throw new ActionException("province.notFound", province_id);
        return province;
    }

    private City getCity(HttpServletRequest request) throws Exception {
        Integer id = ActionUtils.parseInt(request.getParameter("id"));
        CityManager cityManager = ServiceLocator.getCityManager(request);
        City city = cityManager.getCity(id);
        if (city == null)
            throw new ActionException("city.notFound", id);
        return city;
    }

    private void putEnumListToRequest(HttpServletRequest request) {
        request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        City city = getCity(request);

        if (city.getSite() == null) {
            checkGlobalPower(request);
        } else {
            checkSite(city.getSite(), request);
        }
        request.setAttribute("x_city", city);
//        if (!isBack(request)) {
//            BeanForm cityForm = (BeanForm)getForm("/updateCity", request);
//            cityForm.populate(city, "to_form");
//        }
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        City city = getCity(request);
        if (city.getSite() == null) {
            checkGlobalPower(request);
        } else {
            checkSite(city.getSite(), request);
        }
        CityManager cm = ServiceLocator.getCityManager(request);
        try {
            cm.deleteCity(city);
        }
        catch (Throwable t) {

            throw new ActionException("city.delete.fail");
        }
        return mapping.findForward("success");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BeanForm cityForm = (BeanForm)form;
        City city = new City();
        cityForm.populate(city, "to_bean");

        if (city.getSite() == null) {
            checkGlobalPower(request);
        } else {
            city.setSite(getAndCheckSite(city.getSite().getId(), request));
        }
        CityManager cityManager = ServiceLocator.getCityManager(request);
        City oldCity = cityManager.getCityByChnName(city.getProvince(), city.getChnName());
        if (oldCity != null && !oldCity.equals(city)) {
            throw new BackToInputActionException("city.chnName.repeat");
        }
        oldCity = cityManager.getCityByEngName(city.getProvince(), city.getEngName());
        if (oldCity != null && !oldCity.equals(city)) {
            throw new BackToInputActionException("city.engName.repeat");
        }
        request.setAttribute("X_OBJECT", cityManager.updateCity(city));
        request.setAttribute("X_ROWPAGE", "city/row.jsp");

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
        if (hasSite(request)) {
            site = getSiteFromRequestAndCheckPower(request);
        } else {
            checkGlobalPower(request);
        }
        Province province = getProvinceFromRequest(request);
        request.setAttribute("x_province", province);
//        if (!isBack(request)) {
//            City city = new City();
//            city.setSite(site);
//            city.setProvince(province);
//            BeanForm cityForm = (BeanForm)getForm("/insertCity", request);
//            cityForm.populate(city, "to_form");
//        }
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Site site = null;
        if (hasSite(request)) {
            site = getSiteFromRequestAndCheckPower(request);
        } else {
            checkGlobalPower(request);
        }


        CityManager cm = ServiceLocator.getCityManager(request);
        BeanForm cityForm = (BeanForm)form;
        City city = new City();
        cityForm.populate(city, "to_bean");
        city.setSite(site);

        if (cm.getCityByChnName(city.getProvince(), city.getChnName()) != null) {
            throw new BackToInputActionException("city.chnName.repeat");
        }
        if (cm.getCityByEngName(city.getProvince(), city.getEngName()) != null) {
            throw new BackToInputActionException("city.engName.repeat");
        }
        request.setAttribute("X_OBJECT", cm.insertCity(city));
        request.setAttribute("X_ROWPAGE", "city/row.jsp");

        if (city.getSite() != null) {
            EmailManager em = ServiceLocator.getEmailManager(request);
            UserManager um = ServiceLocator.getUserManager(request);
            FunctionManager fm = ServiceLocator.getFunctionManager(request);
            Function function = fm.getFunction("countryProvinceCityMaintenance");
            List userList = um.getEnabledUserList(function);
            for (Iterator<User> itor = userList.iterator(); itor.hasNext(); ) {
                User user = itor.next();
                if (user.getEmail() != null) {
                    ProvinceManager pm = ServiceLocator.getProvinceManager(request);
                    Map<Object, Object> context = new HashMap<Object, Object>();
                    context.put("user", user);
                    context.put("city", city);
                    context.put("province", pm.getProvince(city.getProvince().getId()));
                    em.insertEmail(user.getPrimarySite(), user.getEmail(), "NewCity.vm", context);
                }
            }
        }

        return mapping.findForward("success");
    }
}
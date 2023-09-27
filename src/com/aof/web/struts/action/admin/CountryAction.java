package com.aof.web.struts.action.admin;

import com.aof.model.admin.Country;
import com.aof.model.admin.Function;
import com.aof.model.admin.Site;
import com.aof.model.admin.User;
import com.aof.model.admin.query.CountryQueryOrder;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.service.admin.CountryManager;
import com.aof.service.admin.EmailManager;
import com.aof.service.admin.FunctionManager;
import com.aof.service.admin.UserManager;
import com.aof.web.struts.action.ActionUtils;
import com.aof.web.struts.action.BaseAction;
import com.aof.web.struts.action.ServiceLocator;
import com.shcnc.hibernate.PersistentEnum;
import com.shcnc.struts.action.ActionException;
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

public class CountryAction
        extends BaseAction
{
    public ActionForward promote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Country country = getCountryFromRequest(request);
        if (country.getSite() == null) {
            throw new ActionException("country.error.alreadyPromoted", country.getEngName());
        }

        if (request.getParameter("confirm") != null) {
            CountryManager cm = ServiceLocator.getCountryManager(request);
            cm.promoteCountry(country);
            request.setAttribute("X_OBJECT", country);
            request.setAttribute("X_ROWPAGE", "country/row.jsp");
            return mapping.findForward("success");
        }

        String name = null;
        if (isEnglish(request)) {
            name = country.getEngName();
        } else {
            name = country.getChnName();
        }  postGlobalMessage("country.promote.confirm", name, request);

        return mapping.findForward("confirm");
    }

    public ActionForward listCountryProvinceCity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (isSite(request)) {
            request.setAttribute("x_siteList", getAndCheckGrantedSiteList(request));
        }

        if (hasGlobalPower(request)) {
            request.setAttribute("x_promote", Boolean.TRUE);
        }

        return mapping.findForward("page");
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CountryManager fm = ServiceLocator.getCountryManager(request);
        List result = fm.getCountryList(new HashMap<Object, Object>(), 0, -1, isEnglish(request) ? CountryQueryOrder.ENGNAME : CountryQueryOrder.CHNNAME, false);
        request.setAttribute("X_RESULTLIST", result);
        if (!result.isEmpty()) {
            request.setAttribute("X_FIRST", result.get(0));
        }

        return mapping.findForward("page");
    }

    private Country getCountryFromRequest(HttpServletRequest request) throws Exception {
        Integer id = Integer.parseInt(request.getParameter("id"));
        CountryManager countryManager = ServiceLocator.getCountryManager(request);
        Country country = countryManager.getCountry(id);
        if (country == null)
            throw new ActionException("country.notFound", id);
        return country;
    }

    private void putEnumListToRequest(HttpServletRequest request) {
        request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Country country = getCountryFromRequest(request);

        if (country.getSite() == null) {
            checkGlobalPower(request);
        } else {
            checkSite(country.getSite(), request);
        }
        request.setAttribute("x_country", country);
//        if (!isBack(request)) {
//            BeanForm countryForm = (BeanForm)getForm("/updateCountry", request);
//            countryForm.populate(country, "to_form");
//        }
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }


    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Country country = getCountryFromRequest(request);
        if (country.getSite() == null) {
            checkGlobalPower(request);
        } else {
            checkSite(country.getSite(), request);
        }
        CountryManager cm = ServiceLocator.getCountryManager(request);
        try {
            cm.deleteCountry(country);
        }
        catch (Throwable t) {

            throw new ActionException("country.delete.fail");
        }
        return mapping.findForward("success");
    }











    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BeanForm countryForm = (BeanForm)form;
        Country country = new Country();
        countryForm.populate(country, "to_bean");

        if (country.getSite() == null) {
            checkGlobalPower(request);
        } else {
            country.setSite(getAndCheckSite(country.getSite().getId(), request));
        }
        CountryManager countryManager = ServiceLocator.getCountryManager(request);

        Country oldCountry = countryManager.getCountryByChnName(country.getChnName());
        if (oldCountry != null && !oldCountry.equals(country))
        {
            throw new BackToInputActionException("country.chnName.repeat");
        }

        oldCountry = countryManager.getCountryByEngName(country.getEngName());
        if (oldCountry != null && !oldCountry.equals(country))
        {
            throw new BackToInputActionException("country.engName.repeat");
        }

        oldCountry = countryManager.getCountryByShortName(country.getShortName());
        if (oldCountry != null && !oldCountry.equals(country))
        {
            throw new BackToInputActionException("country.shortName.repeat");
        }

        request.setAttribute("X_OBJECT", countryManager.updateCountry(country));
        request.setAttribute("X_ROWPAGE", "country/row.jsp");

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


//        if (!isBack(request)) {
//            Country country = new Country();
//            country.setSite(site);
//            BeanForm countryForm = (BeanForm)getForm("/insertCountry", request);
//            countryForm.populate(country, "to_form");
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


        BeanForm countryForm = (BeanForm)form;
        Country country = new Country();
        countryForm.populate(country, "to_bean");
        country.setSite(site);

        CountryManager cm = ServiceLocator.getCountryManager(request);

        Country oldCountry = cm.getCountryByChnName(country.getChnName());
        if (oldCountry != null)
        {
            throw new BackToInputActionException("country.chnName.repeat");
        }

        oldCountry = cm.getCountryByEngName(country.getEngName());
        if (oldCountry != null)
        {
            throw new BackToInputActionException("country.engName.repeat");
        }

        oldCountry = cm.getCountryByShortName(country.getShortName());
        if (oldCountry != null)
        {
            throw new BackToInputActionException("country.shortName.repeat");
        }

        request.setAttribute("X_OBJECT", cm.insertCountry(country));
        request.setAttribute("X_ROWPAGE", "country/row.jsp");

        if (country.getSite() != null) {
            EmailManager em = ServiceLocator.getEmailManager(request);
            UserManager um = ServiceLocator.getUserManager(request);
            FunctionManager fm = ServiceLocator.getFunctionManager(request);
            Function function = fm.getFunction("countryProvinceCityMaintenance");
            List userList = um.getEnabledUserList(function);
            for (Iterator<User> itor = userList.iterator(); itor.hasNext(); ) {
                User user = itor.next();
                if (user.getEmail() != null) {
                    Map<Object, Object> context = new HashMap<Object, Object>();
                    context.put("user", user);
                    context.put("country", country);
                    em.insertEmail(user.getPrimarySite(), user.getEmail(), "NewCountry.vm", context);
                }
            }
        }
        return mapping.findForward("success");
    }
}
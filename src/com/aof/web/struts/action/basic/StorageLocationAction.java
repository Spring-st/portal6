package com.aof.web.struts.action.basic;

import com.aof.model.admin.Site;
import com.aof.model.basic.StorageLocation;
import com.aof.model.basic.query.StorageLocationQueryCondition;
import com.aof.model.basic.query.StorageLocationQueryOrder;
import com.aof.model.inventory.InventoryDetial;
import com.aof.model.inventory.query.InventoryQueryCondition;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.model.metadata.YesNo;
import com.aof.service.basic.StorageLocationManager;
import com.aof.service.basic.StoreRoomManager;
import com.aof.service.inventory.InventoryManager;
import com.aof.utils.SessionTempFile;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.BaseSessionQueryForm;
import com.aof.web.struts.form.basic.StorageLocationQueryForm;
import com.shcnc.hibernate.PersistentEnum;
import com.shcnc.struts.action.ActionException;
import com.shcnc.struts.action.ActionUtils;
import com.shcnc.struts.form.BeanForm;
import com.shcnc.utils.BeanHelper;
import com.shcnc.utils.ExportUtil;
import com.shcnc.utils.Exportable;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;


public class StorageLocationAction extends BaseAction2 {
    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StorageLocationQueryForm queryForm = (StorageLocationQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(StorageLocationQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        List grantedSiteList = getAndCheckGrantedSiteDeparmentList(request);
        request.setAttribute("X_SITELIST", grantedSiteList);

        StorageLocationManager fm = ServiceLocator.getStorageLocationManager(request);
        Map conditions = constructQueryMap(queryForm);

        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);

        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = fm.getStorageLocationList(conditions, 0, -1, StorageLocationQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());

            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "storageLocation";
            String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
            {
                public void exportHead(List row, HttpServletRequest request) throws Exception {
                    MessageResources messages = StorageLocationAction.this.getResources(request);
                    row.add(messages.getMessage(StorageLocationAction.this.getLocale(request), "storageLocation.codeid"));
                    row.add(messages.getMessage(StorageLocationAction.this.getLocale(request), "storageLocation.address"));
                    row.add(messages.getMessage(StorageLocationAction.this.getLocale(request), "storageLocation.max_inventory"));
                    row.add(messages.getMessage(StorageLocationAction.this.getLocale(request), "storageLocation.basic_storeroom_id.code"));
                    row.add(messages.getMessage(StorageLocationAction.this.getLocale(request), "storageLocation.basic_recommend_status"));
                    row.add(messages.getMessage(StorageLocationAction.this.getLocale(request), "storageLocation.freeae_status"));
                    row.add(messages.getMessage(StorageLocationAction.this.getLocale(request), "storageLocation.LOGIN_USER.locale"));
                    row.add(messages.getMessage(StorageLocationAction.this.getLocale(request), "storageLocation.f_in_f_out_status"));
                    row.add(messages.getMessage(StorageLocationAction.this.getLocale(request), "storageLocation.description"));
                }

                public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                    String codeid = BeanHelper.getBeanPropertyValue(data, "code").toString();
                    row.add("'" + codeid);
                    row.add(BeanHelper.getBeanPropertyValue(data, "address"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "max_inventory"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "basic_storeroom_id.code"));
                    String code = BeanHelper.getBeanPropertyValue(data, "recommend_status").toString();
                    if (code.equals("1")) {
                        row.add("不参与");
                    }
                    if (code.equals("0") || code == null || code.equals("")) {
                        row.add("参与");
                    }
                    String status = BeanHelper.getBeanPropertyValue(data, "freeae_status").toString();
                    if (status.equals("0")) {
                        row.add("已冻结");
                    }
                    if (status.equals("1") || status == null || status.equals("")) {
                        row.add("未冻结");
                    }
                    String locale = BeanHelper.getBeanPropertyValue(data, "enabled.engShortDescription").toString();
                    if (locale.equals("Enabled")) {
                        row.add("可用");
                    } else {
                        row.add("不可用");
                    }

                    String outstatus = BeanHelper.getBeanPropertyValue(data, "f_in_f_out_status").toString();
                    if (outstatus.equals("0")) {
                        row.add("强行限定");
                    }
                    if (outstatus.equals("") || outstatus.equals("1") || outstatus == null) {
                        row.add("不限定");
                    }


                    row.add(BeanHelper.getBeanPropertyValue(data, "description"));
                }
            });


            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getStorageLocationListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getStorageLocationList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                StorageLocationQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(9));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    private Map constructQueryMap(StorageLocationQueryForm queryForm) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        Integer id = ActionUtils.parseInt(queryForm.getId());
        if (id != null && !id.equals("")) {
            conditions.put(StorageLocationQueryCondition.ID_EQ, id);
        }
        String describe = queryForm.getDescribe();
        if (describe != null && !describe.equals("")) {
            conditions.put(StorageLocationQueryCondition.DESCRIBE_EQ, describe);
        }
        String stromType = queryForm.getStromType();
        if (stromType != null && !stromType.equals("")) {
            conditions.put(StorageLocationQueryCondition.STROMTYPE_EQ, stromType);
        }
        String notContain = queryForm.getNotContain();
        if (notContain != null && !notContain.equals("")) {
            conditions.put(StorageLocationQueryCondition.NOTCONTAIN_EQ, notContain);
        }
        String address = queryForm.getAddress();
        if (address != null && !address.equals("")) {
            conditions.put(StorageLocationQueryCondition.ADDRESS_EQ, address);
        }
        String storeroom = queryForm.getStoreroom_id();
        if (storeroom != null && !storeroom.equals("")) {
            conditions.put(StorageLocationQueryCondition.STOREROOM_ID_EQ, storeroom);
        }
        String status = queryForm.getStatus();
        if (status != null && !status.equals("")) {
            conditions.put(StorageLocationQueryCondition.ENABLED_EQ, status);
        }
        String site = queryForm.getSite();
        if (site != null && !site.equals("")) {
            conditions.put(StorageLocationQueryCondition.SITE_EQ, site);
        }
        String code = queryForm.getCode();
        if (code != null && !code.equals("")) {
            conditions.put(StorageLocationQueryCondition.CODE_EQ, code);
        }
        String codemany = queryForm.getCodemany();
        if (codemany != null && !codemany.equals("")) {
            String[] str = codemany.split(",");
            conditions.put(StorageLocationQueryCondition.CODEMANY_EQ, str);
        }
        return conditions;
    }

    private StorageLocation getStorageLocation(HttpServletRequest request) throws Exception {
        Integer id = ActionUtils.parseInt(request.getParameter("id"));
        StorageLocationManager storageLocationManager = ServiceLocator.getStorageLocationManager(request);
        StorageLocation storageLocation = storageLocationManager.getStorageLocation(id);
        if (storageLocation == null)
            throw new ActionException("storageLocation.notFound", id);
        return storageLocation;
    }

    private void putEnumListToRequest(HttpServletRequest request) {
        request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
        request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
        request.setAttribute("X_SITEID", ServiceLocator.getSiteManager(request).getAllEnabledSiteList());
        request.setAttribute("X_STOREROOMLIST", ServiceLocator.getStoreRoomManager(request).getStoreRoomListEnabledAll());
    }













    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StorageLocation storageLocation = getStorageLocation(request);

        request.setAttribute("x_storageLocation", storageLocation);
        if (!isBack(request)) {

            BeanForm storageLocationForm = (BeanForm)getForm("/updateStorageLocation", request);
            storageLocationForm.populate(storageLocation, "to_form");
        }
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }










    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StorageLocation storageLocation = getStorageLocation(request);

        StorageLocationManager cm = ServiceLocator.getStorageLocationManager(request);
        try {
            cm.deleteStorageLocation(storageLocation);
        }
        catch (Throwable t) {

            throw new ActionException("storageLocation.delete.fail");
        }
        return mapping.findForward("success");
    }













    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BeanForm storageLocationForm = (BeanForm)form;
        StorageLocation storageLocation = new StorageLocation();
        storageLocationForm.populate(storageLocation, "to_bean");
        StorageLocationManager storageLocationManager = ServiceLocator.getStorageLocationManager(request);
        storageLocationManager.updateStorageLocation(storageLocation);

        request.setAttribute("X_OBJECT", storageLocationManager.getStorageLocation(storageLocation.getId()));
        request.setAttribute("X_ROWPAGE", "wmsbasic/storageLocation/row.jsp");
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
            StorageLocation storageLocation = new StorageLocation();
            BeanForm storageLocationForm = (BeanForm)getForm("/insertStorageLocation", request);
            storageLocationForm.populate(storageLocation, "to_form");
        }
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

















    public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StorageLocationManager cm = ServiceLocator.getStorageLocationManager(request);
        BeanForm storageLocationForm = (BeanForm)form;
        StorageLocation storageLocation = new StorageLocation();
        storageLocationForm.populate(storageLocation, "to_bean");

        request.setAttribute("X_OBJECT", cm.insertStorageLocation(storageLocation));

        return mapping.findForward("success");
    }



    public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StorageLocationQueryForm queryForm = (StorageLocationQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(StorageLocationQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        StorageLocationManager fm = ServiceLocator.getStorageLocationManager(request);
        Map conditions = constructQueryMap(queryForm);

        int pageNo = queryForm.getPageNoAsInt();
        int pageSize = queryForm.getPageSizeAsInt();
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getStorageLocationListCount(conditions));
            queryForm.setCount((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString());
            if (Integer.parseInt((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString()) / Integer.parseInt((new StringBuilder(String.valueOf(pageSize))).toString()) <= pageNo) {
                pageNo = 0;
                queryForm.setPageNo("0");
            }
            queryForm.setPageSize((new StringBuilder(String.valueOf(pageSize))).toString());
        } else {
            queryForm.init();
        }
        List result = fm.getStorageLocationList(conditions, pageNo, pageSize, StorageLocationQueryOrder.ID, queryForm.isDescend());


        StoreRoomManager manager = ServiceLocator.getStoreRoomManager(request);

        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);

        String type = request.getParameter("type");
        if (type != null && type.equals("1")) {
            request.setAttribute("type", type);
            request.setAttribute("x_selType", Integer.valueOf(37));
        }


        return mapping.findForward("page");
    }




    public ActionForward selectLF(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StorageLocationQueryForm queryForm = (StorageLocationQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(StorageLocationQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        StorageLocationManager fm = ServiceLocator.getStorageLocationManager(request);
        Map<StorageLocationQueryCondition, String> conditions = constructQueryMap(queryForm);
        conditions.put(StorageLocationQueryCondition.STOREROOM_ID_EQ, "CK006");
        int pageNo = queryForm.getPageNoAsInt();
        int pageSize = queryForm.getPageSizeAsInt();
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getStorageLocationListCount(conditions));
            queryForm.setCount((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString());
            if (Integer.parseInt((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString()) / Integer.parseInt((new StringBuilder(String.valueOf(pageSize))).toString()) <= pageNo) {
                pageNo = 0;
                queryForm.setPageNo("0");
            }
            queryForm.setPageSize((new StringBuilder(String.valueOf(pageSize))).toString());
        } else {
            queryForm.init();
        }
        List result = fm.getStorageLocationList(conditions, pageNo, pageSize, StorageLocationQueryOrder.ID, queryForm.isDescend());

        String type = request.getParameter("type");
        if (type != null && type.equals("1")) {
            request.setAttribute("x_selType", Integer.valueOf(37));
        }

        StoreRoomManager manager = ServiceLocator.getStoreRoomManager(request);
        request.setAttribute("x_stromList", manager.getStoreRoomListEnabledAll());
        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward select1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StorageLocationQueryForm queryForm = (StorageLocationQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(StorageLocationQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        StorageLocationManager fm = ServiceLocator.getStorageLocationManager(request);
        Map<StorageLocationQueryCondition, Integer> conditions = constructQueryMap(queryForm);
        conditions.put(StorageLocationQueryCondition.STROMTYPE_EQ, Integer.valueOf(8));
        int pageNo = queryForm.getPageNoAsInt();
        int pageSize = queryForm.getPageSizeAsInt();
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getStorageLocationListCount(conditions));
            queryForm.setCount((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString());
            if (Integer.parseInt((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString()) / Integer.parseInt((new StringBuilder(String.valueOf(pageSize))).toString()) <= pageNo) {
                pageNo = 0;
                queryForm.setPageNo("0");
            }
            queryForm.setPageSize((new StringBuilder(String.valueOf(pageSize))).toString());
        } else {
            queryForm.init();
        }
        List result = fm.getStorageLocationList(conditions, pageNo, pageSize, StorageLocationQueryOrder.ID, queryForm.isDescend());
        StoreRoomManager manager = ServiceLocator.getStoreRoomManager(request);
        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }




    public ActionForward selectStorage1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StorageLocationQueryForm queryForm = (StorageLocationQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(StorageLocationQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        StorageLocationManager fm = ServiceLocator.getStorageLocationManager(request);
        Map conditions = constructQueryMap(queryForm);
        getConditionAndOrder(queryForm, conditions, request);
        conditions.put(StorageLocationQueryCondition.STROMTYPE_IN, new Object[] { Integer.valueOf(5), Integer.valueOf(8) });
        int pageNo = queryForm.getPageNoAsInt();
        int pageSize = queryForm.getPageSizeAsInt();
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getStorageLocationListCount(conditions));
            queryForm.setCount((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString());
            if (Integer.parseInt((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString()) / Integer.parseInt((new StringBuilder(String.valueOf(pageSize))).toString()) <= pageNo) {
                pageNo = 0;
                queryForm.setPageNo("0");
            }
            queryForm.setPageSize((new StringBuilder(String.valueOf(pageSize))).toString());
        } else {
            queryForm.init();
        }
        List result = fm.getStorageLocationList(conditions, pageNo, pageSize, StorageLocationQueryOrder.ID, queryForm.isDescend());
        StoreRoomManager manager = ServiceLocator.getStoreRoomManager(request);
        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }


    public ActionForward listPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StorageLocationManager fm = ServiceLocator.getStorageLocationManager(request);
        StorageLocationQueryForm queryForm = (StorageLocationQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(StorageLocationQueryOrder.ID.getName());
            queryForm.setDescend(false);
            queryForm.setStatus("0");
        }
        List<Site> grantedSiteList = getAndCheckGrantedSiteDeparmentList(request);
        request.setAttribute("X_SITELIST", grantedSiteList);
        if (hasGlobalPower(request)) {
            request.setAttribute("X_GLOBAL", Boolean.TRUE);
        } else {

            Integer siteId = ActionUtils.parseInt(queryForm.getSite());
            if (siteId == null) {
                siteId = ((Site)grantedSiteList.get(0)).getId();
                queryForm.setSite(siteId.toString());
            } else {
                checkSite(siteId, request);
            }
            request.setAttribute("X_GLOBAL", Boolean.FALSE);
        }

        Map conditions = constructQueryMap(queryForm);

        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getStorageLocationListCount(conditions));
        } else {
            queryForm.init();
        }
        int pageNo = queryForm.getPageNoAsInt();
        int pageSize = queryForm.getPageSizeAsInt();
        List result = fm.getStorageLocationList(conditions, -1, -1,
                StorageLocationQueryOrder.ID, queryForm.isDescend());

        String array = request.getParameter("array");
        if (!array.equals("")) {
            List<StorageLocation> list = new ArrayList();
            String[] arrays = array.split(","); byte b; int i; String[] arrayOfString1;
            for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
                StorageLocation location = fm.getStorageLocation(Integer.valueOf(Integer.parseInt(id)));
                list.add(location); b++; }

            request.setAttribute("X_RESULTLIST", list);
        } else {
            request.setAttribute("X_RESULTLIST", result);
        }

        putEnumListToRequest(request);
        request.setAttribute("path", request.getContextPath());
        return mapping.findForward("page");
    }



    public ActionForward checkLocationCodeAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");
//        response.setCharacterEncoding("UTF-8");
        JsonConfig cfg = new JsonConfig();
        String code = request.getParameter("code");
        boolean sign = false;
        StorageLocationManager locationManager = ServiceLocator.getStorageLocationManager(request);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put(StorageLocationQueryCondition.CODE_EQ, code);
        List list = locationManager.getStorageLocationList(map, -1, -1, null, true);
        if (list.size() == 0 || list == null) {
            sign = true;
        }

        JSONArray jo = JSONArray.fromObject(Boolean.valueOf(sign), cfg);
        response.getWriter().print(jo);
        return null;
    }



    public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StorageLocationManager fm = ServiceLocator.getStorageLocationManager(request);
        String array = request.getParameter("array");
        List<StorageLocation> list = new ArrayList();
        String[] arrays = array.split(","); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
            StorageLocation location = fm.getStorageLocation(Integer.valueOf(Integer.parseInt(id)));
            list.add(location);
            b++; }

        request.setAttribute("X_RESULTLIST", list);
        request.setAttribute("path", request.getContextPath());
        return mapping.findForward("page");
    }



    public ActionForward lookForLocationQtyByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");
//        response.setCharacterEncoding("UTF-8");
        JsonConfig cfg = new JsonConfig();

        String code = request.getParameter("str");
        StorageLocationManager manager = ServiceLocator.getStorageLocationManager(request);
        Map map = manager.lookForLocationQtyByAjax(code);

        JSONObject jo = JSONObject.fromObject(map, cfg);
        response.getWriter().print(jo);
        return null;
    }


    public ActionForward selectStorageLocationType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StorageLocationQueryForm queryForm = (StorageLocationQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(StorageLocationQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        StorageLocationManager fm = ServiceLocator.getStorageLocationManager(request);
        InventoryManager inventoryManager = ServiceLocator.getInventoryManager(request);
        Map conditions = constructQueryMap(queryForm);
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
        int pageNo = queryForm.getPageNoAsInt();
        int pageSize = queryForm.getPageSizeAsInt();
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getStorageLocationListCount(conditions));
            queryForm.setCount((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString());
            if (Integer.parseInt((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString()) / Integer.parseInt((new StringBuilder(String.valueOf(pageSize))).toString()) <= pageNo) {
                pageNo = 0;
                queryForm.setPageNo("0");
            }
            queryForm.setPageSize((new StringBuilder(String.valueOf(pageSize))).toString());
        } else {
            queryForm.init();
        }
        List<StorageLocation> result = fm.getStorageLocationList(conditions, pageNo, pageSize, StorageLocationQueryOrder.ID, queryForm.isDescend());

        Object[] parama = new Object[result.size()];
        for (int j0 = 0; j0 < result.size(); j0++) {
            StorageLocation location = result.get(j0);
            parama[j0] = location.getId();
        }
        conditions.clear();
        conditions.put(InventoryQueryCondition.LOCATION_ID_IN, parama);
        List<InventoryDetial> datialList = inventoryManager.getInventoryDetialList(conditions, 0, -1, null, false);
        for (StorageLocation location : result) {
            for (InventoryDetial inventoryDetial : datialList) {
                if (location.getCode().equals(inventoryDetial.getLocation().getCode())) {
                    BigDecimal count = location.getNumber().add(inventoryDetial.getNumber());
                    location.setNumber(count);
                }
            }
        }
        StoreRoomManager manager = ServiceLocator.getStoreRoomManager(request);
        request.setAttribute("x_stromList", manager.getStoreRoomListEnabledAll());
        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);

        String type = request.getParameter("type");
        if (type != null && type.equals("1")) {
            request.setAttribute("x_selType", Integer.valueOf(37));
        }
        request.setAttribute("x_selType", Integer.valueOf(37));
        request.setAttribute("x_selType", Integer.valueOf(37));
        return mapping.findForward("page");
    }
}

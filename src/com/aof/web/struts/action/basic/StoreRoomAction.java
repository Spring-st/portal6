package com.aof.web.struts.action.basic;

import com.aof.model.admin.Site;
import com.aof.model.basic.StoreRoom;
import com.aof.model.basic.query.StoreRoomQueryCondition;
import com.aof.model.basic.query.StoreRoomQueryOrder;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.model.metadata.StoreRoomType;
import com.aof.service.admin.SiteManager;
import com.aof.service.basic.StoreRoomManager;
import com.aof.utils.SessionTempFile;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.BaseSessionQueryForm;
import com.aof.web.struts.form.basic.StoreRoomQueryForm;
import com.shcnc.hibernate.PersistentEnum;
import com.shcnc.struts.action.ActionException;
import com.shcnc.struts.action.ActionUtils;
import com.shcnc.struts.form.BeanForm;
import com.shcnc.utils.BeanHelper;
import com.shcnc.utils.ExportUtil;
import com.shcnc.utils.Exportable;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

public class StoreRoomAction extends BaseAction2 {
    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StoreRoomQueryForm queryForm = (StoreRoomQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(StoreRoomQueryOrder.ID.getName());
            queryForm.setDescend(false);
            queryForm.setStatus("0");
        }
        StoreRoomManager fm = ServiceLocator.getStoreRoomManager(request);
        Map conditions = constructQueryMap(queryForm);
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = fm.getStoreRoomList(conditions, 0, -1, StoreRoomQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "storeRoom";
            String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable() {
                public void exportHead(List row, HttpServletRequest request) throws Exception {
                    MessageResources messages = StoreRoomAction.this.getResources(request);
                    row.add(messages.getMessage(StoreRoomAction.this.getLocale(request), "storeRoom.code"));
                    row.add(messages.getMessage(StoreRoomAction.this.getLocale(request), "storeRoom.kfname"));
                    row.add(messages.getMessage(StoreRoomAction.this.getLocale(request), "storeRoom.address"));
                    row.add(messages.getMessage(StoreRoomAction.this.getLocale(request), "storeRoom.type"));
                    row.add(messages.getMessage(StoreRoomAction.this.getLocale(request), "storeRoom.date"));
                    row.add(messages.getMessage(StoreRoomAction.this.getLocale(request), "storeRoom.largest_inventory"));
                    row.add(messages.getMessage(StoreRoomAction.this.getLocale(request), "storeRoom.status.color"));
                    row.add(messages.getMessage(StoreRoomAction.this.getLocale(request), "storeRoom.remark"));
                }

                public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                    row.add(BeanHelper.getBeanPropertyValue(data, "code"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "name"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "address"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "type"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "date"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "largest_inventory"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "status.chnShortDescription"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "remark"));
                }
            });
            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getStoreRoomListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getStoreRoomList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                StoreRoomQueryOrder.ID, queryForm.isDescend());
        List<Site> siteList = getGrantedSiteDeparmentList(request);
        request.setAttribute("x_siteList", siteList);
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(10));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    private Map constructQueryMap(StoreRoomQueryForm queryForm) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        Integer id = ActionUtils.parseInt(queryForm.getId());
        if (id != null && !id.equals(""))
            conditions.put(StoreRoomQueryCondition.ID_EQ, id);
        String address = queryForm.getAddress();
        if (address != null && !address.equals(""))
            conditions.put(StoreRoomQueryCondition.ADDRESS_EQ, address);
        String describe = queryForm.getDescribe();
        if (describe != null && !describe.equals(""))
            conditions.put(StoreRoomQueryCondition.DESCRIBE_EQ, describe);
        String type = queryForm.getType();
        if (type != null && !type.equals(""))
            conditions.put(StoreRoomQueryCondition.TYPE_EQ, type);
        String status = queryForm.getStatus();
        if (status != null && !status.equals(""))
            conditions.put(StoreRoomQueryCondition.ENABLED_EQ, status);
        String safetyInventory = queryForm.getSafetyInventory();
        if (safetyInventory != null && !safetyInventory.equals(""))
            conditions.put(StoreRoomQueryCondition.SAFETYINVENTORY_EQ, safetyInventory);
        String siteid = queryForm.getSiteId();
        if (siteid != null && !siteid.equals(""))
            conditions.put(StoreRoomQueryCondition.SITE_EQ, siteid);
        return conditions;
    }

    private StoreRoom getStoreRoom(HttpServletRequest request) throws Exception {
        Integer id = ActionUtils.parseInt(request.getParameter("id"));
        StoreRoomManager storeRoomManager = ServiceLocator.getStoreRoomManager(request);
        StoreRoom storeRoom = storeRoomManager.getStoreRoom(id);
        if (storeRoom == null)
            throw new ActionException("storeRoom.notFound", id);
        return storeRoom;
    }

    private void putEnumListToRequest(HttpServletRequest request) {
        request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
        request.setAttribute("x_STOREROOMTYPELIST", PersistentEnum.getEnumList(StoreRoomType.class));
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StoreRoom storeRoom = getStoreRoom(request);
        request.setAttribute("x_storeRoom", storeRoom);
        if (!isBack(request)) {
            BeanForm storeRoomForm = (BeanForm)getForm("/updateStoreRoom", request);
            storeRoomForm.populate(storeRoom, "to_form");
        }
        putEnumListToRequest(request);
        List<Site> siteList = getGrantedSiteDeparmentList(request);
        request.setAttribute("x_siteList", siteList);
        return mapping.findForward("page");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StoreRoom storeRoom = getStoreRoom(request);
        StoreRoomManager cm = ServiceLocator.getStoreRoomManager(request);
        try {
            cm.deleteStoreRoom(storeRoom);
        } catch (Throwable t) {
            throw new ActionException("storeRoom.delete.fail");
        }
        return mapping.findForward("success");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BeanForm storeRoomForm = (BeanForm)form;
        StoreRoom storeRoom = new StoreRoom();
        storeRoomForm.populate(storeRoom, "to_bean");
        StoreRoomManager storeRoomManager = ServiceLocator.getStoreRoomManager(request);
        storeRoom = storeRoomManager.updateStoreRoom(storeRoom);
        request.setAttribute("X_OBJECT", storeRoom);
        request.setAttribute("X_ROWPAGE", "wmsbasic/storeRoom/row.jsp");
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
            StoreRoom storeRoom = new StoreRoom();
            BeanForm storeRoomForm = (BeanForm)getForm("/insertStoreRoom", request);
            storeRoomForm.populate(storeRoom, "to_form");
        }
        List<Site> siteList = getGrantedSiteDeparmentList(request);
        request.setAttribute("x_siteList", siteList);
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
        StoreRoomManager cm = ServiceLocator.getStoreRoomManager(request);
        BeanForm storeRoomForm = (BeanForm)form;
        StoreRoom storeRoom = new StoreRoom();
        storeRoomForm.populate(storeRoom, "to_bean");
        storeRoom = cm.insertStoreRoom(storeRoom);
        request.setAttribute("X_OBJECT", storeRoom);
        request.setAttribute("X_ROWPAGE", "wmsbasic/storeRoom/row.jsp");
        return mapping.findForward("success");
    }

    public ActionForward getStoreRoomListBySite(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            List<Map> boxList = new ArrayList<Map>();
            response.setContentType("text/json");
//            response.setCharacterEncoding("UTF-8");
            JsonConfig cfg = new JsonConfig();
            PrintWriter out = response.getWriter();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM");
            SiteManager sm = ServiceLocator.getSiteManager(request);
            Site site = sm.getSite(Integer.valueOf(Integer.parseInt(request.getParameter("site"))));
            Map<Object, Object> condtions = new HashMap<Object, Object>();
            condtions.put(StoreRoomQueryCondition.SITE_EQ, site.getId());
            List<StoreRoom> srlist = ServiceLocator.getStoreRoomManager(request).getStoreRoomList(condtions, -1, -1, null, true);
            for (StoreRoom sr : srlist) {
                Map<Object, Object> map = new HashMap<Object, Object>();
                map.put("property", sr.getId());
                boxList.add(map);
            }
            if (srlist.size() == 0) {
                Map<Object, Object> map = new HashMap<Object, Object>();
                map.put("property", "");
                map.put("labelProperty", "null");
                boxList.add(map);
            }
            JSONArray jo = JSONArray.fromObject(boxList, cfg);
            response.getWriter().print(jo);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return null;
    }
}

package com.aof.web.struts.action.inventory;

import com.aof.model.admin.query.InventoryMovingQueryOrder;
import com.aof.model.basic.query.BadReasonsQueryCondition;
import com.aof.model.basic.query.BadReasonsQueryOrder;
import com.aof.model.basicDataView.query.LocationPartNumberQueryOrder;
import com.aof.model.inventory.query.InventoryQueryCondition;
import com.aof.model.inventory.query.InventoryQueryOrder;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.model.po.Box;
import com.aof.service.Product.ProductManager;
import com.aof.service.admin.InventoryMovingManager;
import com.aof.service.inventory.InventoryManager;
import com.aof.service.po.BoxManager;
import com.aof.utils.SessionTempFile;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.BaseSessionQueryForm;
import com.aof.web.struts.form.basic.InventoryOccupiedDetialQueryForm;
import com.aof.web.struts.form.basic.InventoryQueryForm;
import com.shcnc.hibernate.PersistentEnum;
import com.shcnc.struts.action.ActionUtils;
import com.shcnc.utils.BeanHelper;
import com.shcnc.utils.ExportUtil;
import com.shcnc.utils.Exportable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
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

public class InventoryAction extends BaseAction2 {
    private static final String[] Object = null;

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InventoryQueryForm queryForm = (InventoryQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BadReasonsQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        InventoryManager manager = ServiceLocator.getInventoryManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(InventoryQueryCondition.STATUS_EQ, Integer.valueOf(3));

        getConditionAndOrder(queryForm, conditions, request);

        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = manager.getInventoryList(conditions, 0, -1, InventoryQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());

            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "BadReasons";
            String suffix = ExportUtil.export(exportType, data, request,
                    new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources messages = InventoryAction.this.getResources(request);
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "BadReasons.id"));
                        }

                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data, "id"));
                        }
                    });


            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(manager.getInventoryListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = manager.getInventoryList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                InventoryQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(3));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }


    public ActionForward listInventoryLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InventoryQueryForm queryForm = (InventoryQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BadReasonsQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        InventoryManager manager = ServiceLocator.getInventoryManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(InventoryQueryCondition.STATUS_EQ, Integer.valueOf(3));

        getConditionAndOrder(queryForm, conditions, request);

        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = manager.getInventoryDetialList(conditions, 0, -1, InventoryQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());

            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "InventoryLocation";
            String suffix = ExportUtil.export(exportType, data, request,
                    new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources messages = InventoryAction.this.getResources(request);
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryLocation.location.code"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryLocation.location.basic_storeroom_id.name"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryLocation.number"));
                        }

                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data, "location.code"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "location.basic_storeroom_id.name"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "number"));
                        }
                    });


            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(manager.getInventoryDetialListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = manager.getInventoryDetialList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                InventoryQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);
        request.setAttribute("x_selType", Integer.valueOf(127));
        return mapping.findForward("page");
    }


    public ActionForward listInventoryPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InventoryQueryForm queryForm = (InventoryQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BadReasonsQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        InventoryManager manager = ServiceLocator.getInventoryManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(InventoryQueryCondition.STATUS_EQ, Integer.valueOf(3));

        getConditionAndOrder(queryForm, conditions, request);

        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = manager.getInventoryTotalList(conditions, 0, -1, InventoryQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());

            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "InventoryPart";
            String suffix = ExportUtil.export(exportType, data, request,
                    new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources messages = InventoryAction.this.getResources(request);
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryPart.part.id"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryPart.part.describe1"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryPart.part.describe1"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryPart.number"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryPart.part.unit"));
                        }

                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "number"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.unit"));
                        }
                    });


            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(manager.getInventoryTotalList(conditions));
        } else {
            queryForm.init();
        }
        List result = manager.getInventoryTotalList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                InventoryQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);
        request.setAttribute("x_selType", Integer.valueOf(126));
        return mapping.findForward("page");
    }


    public ActionForward report(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InventoryQueryForm queryForm = (InventoryQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BadReasonsQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        InventoryManager manager = ServiceLocator.getInventoryManager(request);
        Map conditions = constructQueryMap(queryForm);
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);

        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = manager.getInventoryDetialList(conditions, 0, -1, InventoryQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());

            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "InventoryDetial";
            String suffix = ExportUtil.export(exportType, data, request,
                    new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources messages = InventoryAction.this.getResources(request);
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.location.code"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part.id"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "DPI"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part.describe1"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part.describe2"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.number"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part_qty"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part.unit"));
                        }

                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data, "location.code"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.dpiNo"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.describe2"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "number"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part_qty"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.unit"));
                        }
                    });


            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(manager.getInventoryDetialListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = manager.getInventoryDetialList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                InventoryQueryOrder.PART_ID, queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(95));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }


    public ActionForward listProductOffline(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InventoryQueryForm queryForm = (InventoryQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BadReasonsQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        InventoryManager manager = ServiceLocator.getInventoryManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(InventoryQueryCondition.TYPE_EQ, Integer.valueOf(0));

        getConditionAndOrder(queryForm, conditions, request);

        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = manager.getInventoryList(conditions, 0, -1, InventoryQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());

            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "BadReasons";
            String suffix = ExportUtil.export(exportType, data, request,
                    new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources messages = InventoryAction.this.getResources(request);
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "BadReasons.id"));
                        }

                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data, "id"));
                        }
                    });

            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(manager.getInventoryListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = manager.getInventoryList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                InventoryQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(4));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    private Map constructQueryMap(InventoryQueryForm queryForm) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        Integer id = ActionUtils.parseInt(queryForm.getId());
        if (id != null && !id.equals("")) {
            conditions.put(BadReasonsQueryCondition.ID_EQ, id);
        }
        conditions.put(InventoryQueryCondition.NUMBER_NE, Integer.valueOf(0));
        return conditions;
    }

    private void putEnumListToRequest(HttpServletRequest request) {
        request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
    }

    public ActionForward getRecommendLot(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxManager manager = ServiceLocator.getBoxManager(request);
        String arrays = request.getParameter("arrays");
        List<Box> boxs = manager.getRecommendLot(arrays);

        request.setAttribute("x_box", boxs);
        return mapping.findForward("page");
    }


    public ActionForward checkLot(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");
//        response.setCharacterEncoding("UTF-8");
        BoxManager manager = ServiceLocator.getBoxManager(request);
        String arrays = request.getParameter("arrays");
        String message = manager.checkLot(arrays);
        JSONObject json = new JSONObject();
        json.put("message", message);
        response.getWriter().print(json);
        return null;
    }

    public ActionForward purchaseOrderOutbound(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InventoryManager manager = ServiceLocator.getInventoryManager(request);
        String arrays = request.getParameter("arrays");
        String[] array = arrays.split(","); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = array).length, b = 0; b < i; ) { String lot = arrayOfString1[b];
            manager.scanningPurchaseOrderOutbound(lot, getCurrentUser(request).getId().toString());
            b++; }

        return mapping.findForward("page");
    }

    public ActionForward listProductScanningOutbound(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InventoryQueryForm queryForm = (InventoryQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BadReasonsQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        InventoryManager manager = ServiceLocator.getInventoryManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(InventoryQueryCondition.STATUS_EQ, Integer.valueOf(3));


        getConditionAndOrder(queryForm, conditions, request);

        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = manager.getInventoryList(conditions, 0, -1, InventoryQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());

            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "BadReasons";
            String suffix = ExportUtil.export(exportType, data, request,
                    new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources messages = InventoryAction.this.getResources(request);
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "BadReasons.id"));
                        }

                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data, "id"));
                        }
                    });


            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(manager.getInventoryListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = manager.getInventoryList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                InventoryQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(5));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }


    public ActionForward productScanningOutbound(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");
//        response.setCharacterEncoding("UTF-8");
        JsonConfig cfg = new JsonConfig();

        ProductManager manager = ServiceLocator.getProductManager(request);
        String arrays = request.getParameter("ids");
        manager.scanningOutboundByProduct(arrays, getCurrentUser(request).getId().toString());

        JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
        response.getWriter().print(jo);
        return null;
    }


    public ActionForward inventoryMovingReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InventoryQueryForm queryForm = (InventoryQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BadReasonsQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        InventoryMovingManager manager = ServiceLocator.getInventoryMovingManager(request);
        Map conditions = constructQueryMap(queryForm);
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);

        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = manager.getInventoryMovingList(conditions, 0, -1,
                    InventoryMovingQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());

            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "InventoryMoving";
            String suffix = ExportUtil.export(exportType, data, request,
                    new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources messages = InventoryAction.this.getResources(request);
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.id"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.part.id"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "DPI"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.part.describe1"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.part.describe2"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.old_location.code"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.new_location.code"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.qty"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.part.unit"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.date"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.is_sync"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.is_sync_date"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.remark"));
                        }

                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data, "lotSer.id"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.dpiNo"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.describe2"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "old_location.code"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "new_location.code"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "qty"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.unit"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "date"));
                            if (InventoryAction.this.isEnglish(request)) {
                                row.add(BeanHelper.getBeanPropertyValue(data, "is_sync.engShortDescription"));
                            } else {
                                row.add(BeanHelper.getBeanPropertyValue(data, "is_sync.chnShortDescription"));
                            }  row.add(BeanHelper.getBeanPropertyValue(data, "is_sync_date"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "remark"));
                        }
                    });


            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(manager.getInventoryMovingListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = manager.getInventoryMovingList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                InventoryMovingQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(94));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    public ActionForward updateByBoxCout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
//        response.setCharacterEncoding("utf-8");
        InventoryManager manager = ServiceLocator.getInventoryManager(request);
        manager.updateInventoryDetialCount();
        String res = "";
        res = "{\"msg\":\"订单冻结成功\"}";
        response.getWriter().print(res);
        return null;
    }


    public ActionForward occupiedDetial(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InventoryOccupiedDetialQueryForm queryForm = (InventoryOccupiedDetialQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(LocationPartNumberQueryOrder.LOCATION_ID.getName());
            queryForm.setDescend(false);
        }

        InventoryManager manager = ServiceLocator.getInventoryManager(request);
        Map conditions = new HashMap();
        getConditionAndOrder(queryForm, conditions, request);

        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = manager.getLocationPartNumberList(conditions, 0, -1, LocationPartNumberQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());

            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "LocationPartNumber";
            String suffix = ExportUtil.export(exportType, data, request,
                    new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources messages = InventoryAction.this.getResources(request);
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.location.code"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part.id"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "DPI"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part.describe1"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part.describe2"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.number"));
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part_qty"));
                            row.add("占用数量");
                            row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part.unit"));
                        }

                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data, "location.code"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.dpiNo"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.describe2"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "number"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "partQty"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "occupiedNumber"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.unit"));
                        }
                    });


            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(manager.getLocationPartNumberListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = manager.getLocationPartNumberList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                LocationPartNumberQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(144));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }
}
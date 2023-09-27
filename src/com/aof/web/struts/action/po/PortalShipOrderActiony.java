package com.aof.web.struts.action.po;
import com.aof.model.admin.Site;
import com.aof.model.admin.Supplier;
import com.aof.model.admin.User;
import com.aof.model.admin.query.SiteQueryCondition;
import com.aof.model.basic.WmsPart;
import com.aof.model.basicDataView.JitShipPart;
import com.aof.model.basicDataView.query.JitShipPartQueryOrder;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.model.metadata.PortalShipOrderStatus;
import com.aof.model.metadata.PurchaseOrderStatus;
import com.aof.model.metadata.YesNo;
import com.aof.model.po.Box;
import com.aof.model.po.PortalShipOrder;
import com.aof.model.po.PortalShipOrderItem;
import com.aof.model.po.PurchaseOrderInspectionPendingItem;
import com.aof.model.po.query.PortalShipOrderQueryCondition;
import com.aof.model.po.query.PortalShipOrderQueryOrder;
import com.aof.model.po.query.PurchaseOrderInspectionPendingQueryCondition;
import com.aof.model.po.query.PurchaseOrderInspectionPendingQueryOrder;
import com.aof.model.query.BasicConditionModel;
import com.aof.model.query.BasicQueryCondition;
import com.aof.model.schedule.ProjectedInventory;
import com.aof.model.schedule.query.ProjectedInventoryQueryCondition;
import com.aof.service.admin.SiteManager;
import com.aof.service.admin.SupplierManager;
import com.aof.service.basic.WmsPartManager;
import com.aof.service.basicDataView.BasicDataViewManager;
import com.aof.service.po.BoxManager;
import com.aof.service.po.PortalShipOrderItemManager;
import com.aof.service.po.PortalShipOrderManager;
import com.aof.service.po.PurchaseOrderInspectionPendingManager;
import com.aof.service.po.PurchaseOrderManager;
import com.aof.service.po.PurchaseOrderReceiptsManager;
import com.aof.service.quartz.job.DeliverMinuteSyncJob;
import com.aof.service.schedule.ProjectedInventoryManager;
import com.aof.utils.SessionTempFile;
import com.aof.web.struts.action.ActionUtils2;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.BaseSessionQueryForm;
import com.aof.web.struts.form.admin.PortalShipOrderMainQueryForm;
import com.aof.web.struts.form.basicDataView.BasicDataViewQueryForm;
import com.aof.web.struts.form.po.PurchaseOrderInspectionPendingItemQueryForm;
import com.aof.web.struts.form.po.PurchaseOrderInspectionPendingQueryForm;
import com.shcnc.hibernate.PersistentEnum;
import com.shcnc.struts.action.ActionException;
import com.shcnc.utils.BeanUtils;
import com.shcnc.utils.ExportUtil;
import com.shcnc.utils.Exportable;
import com.shcnc.utils.MD5;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
public class PortalShipOrderActiony
        extends BaseAction2
{
    public ActionForward shippingOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
        PortalShipOrderManager fm =
                ServiceLocator.getPortalShipOrderManager(request);
        if (queryForm.getOrder() == "") {
            queryForm.setOrder("createDate");
            queryForm.setDescend(true);
        }

        Map conditions = new HashMap();
        getConditionsFrom(queryForm, conditions);
        getConditionAndOrder(queryForm, conditions, request);
        conditions.put(PortalShipOrderQueryCondition.CREATETYPE_EQ, "NJIT_PO");
        String exportType = queryForm.getExportType();
        if (exportType != null && exportType.length() > 0) {
            List data = fm.getPortalShipOrderList(conditions, 0, -1, null,
                    false);
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "PortalShipOrder";
            String suffix = ExportUtil.export(
                    exportType,
                    data,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources messages = PortalShipOrderActiony.this.getResources(request);
                            row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                    "SO.No1"));
                            row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                    "supplier1"));
                            row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                    "supplier.code1"));
                            row.add("创建时间");
                            row.add("发货时间");
                            row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                    "salesOrderItem.issync"));
                            row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                    "portalShipOrder.status"));
                        }

                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanUtils.getProperty(data, "code"));
                            row.add(
                                    BeanUtils.getProperty(data, "supplier.name"));
                            row.add(
                                    BeanUtils.getProperty(data, "supplier.code"));

                            row.add(BeanUtils.getProperty(data, "createDate"));
                            row.add(BeanUtils.getProperty(data, "arrivalDate"));
                            PortalShipOrder order = (PortalShipOrder)data;
                            if (order.getIsSync() != null) {
                                if (order.getIsSync().equals("0")) {
                                    row.add("未同步");
                                } else if (order.getIsSync().equals("1")) {
                                    row.add("已同步");
                                } else if (order.getIsSync().equals("")) {
                                    row.add("未同步");
                                }
                            } else {
                                row.add("未同步");
                            }
                            String locale = PortalShipOrderActiony.this.getCurrentUser(request).getLocale();
                            if ("en".equals(locale)) {
                                row.add(BeanUtils.getProperty(data,
                                        "status.engShortDescription"));
                            } else {
                                row.add(BeanUtils.getProperty(data,
                                        "status.chnShortDescription"));
                            }
                        }
                    });
            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getPortalShipOrderListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getPortalShipOrderList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                PortalShipOrderQueryOrder.getEnum(queryForm.getOrder()),
                queryForm.isDescend());
        request.setAttribute("X_ENABLEDDISABLEDLIST",
                PersistentEnum.getEnumList(EnabledDisabled.class));
        request.setAttribute("x_statusList",
                PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(159));
        return mapping.findForward("page");
    }

    public ActionForward listShipOrderReport1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
        PortalShipOrderManager fm =
                ServiceLocator.getPortalShipOrderManager(request);

        Map conditions = new HashMap();
        conditions.put(PortalShipOrderQueryCondition.SITE_EQ,
                getCurrentUser(request).getPrimarySite().getId());
        getConditionsFrom(queryForm, conditions);
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);

        String exportType = queryForm.getExportType();
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getPortalShipOrderListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getPortalShipOrderList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                PortalShipOrderQueryOrder.ID, queryForm.isDescend());
        request.setAttribute("X_ENABLEDDISABLEDLIST",
                PersistentEnum.getEnumList(EnabledDisabled.class));
        request.setAttribute("x_statusList",
                PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
        request.setAttribute("X_RESULTLIST", result);
        return mapping.findForward("page");
    }

    public ActionForward listShipOrderReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
        PortalShipOrderManager fm =
                ServiceLocator.getPortalShipOrderManager(request);

        Map conditions = new HashMap();

        getConditionsFrom(queryForm, conditions);
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);

        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getPortalShipOrderListCount(conditions));
        } else {
            queryForm.init();
        }

        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = fm.getPortalShipOrderList(conditions, 0, -1,
                    PortalShipOrderQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "PortalShip";
            String suffix = ExportUtil.export(
                    exportType,
                    data,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception
                        {
                            MessageResources messages = PortalShipOrderActiony.this.getResources(request);
                            row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                    "PortalShip.code"));
                            row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                    "PortalShip.createDate"));
                            row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                    "WmsUW.remark"));
                            row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                    "WmsUW.status.chnShortDescription"));
                        }

                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanUtils.getProperty(data, "code"));
                            row.add(BeanUtils.getProperty(data, "createDate"));
                            row.add(BeanUtils.getProperty(data, "remark"));
                            if (BeanUtils.getProperty(data, "status") == "0") {
                                row.add("成功");
                            } else {
                                row.add(BeanUtils.getProperty(data, "status"));
                            }
                        }
                    });

            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        List result = fm.getPortalShipOrderList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                PortalShipOrderQueryOrder.ID, queryForm.isDescend());
        request.setAttribute("X_ENABLEDDISABLEDLIST",
                PersistentEnum.getEnumList(EnabledDisabled.class));
        request.setAttribute("x_statusList",
                PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
        request.setAttribute("X_RESULTLIST", result);
        return mapping.findForward("page");
    }
    private void getConditionsFrom(PortalShipOrderMainQueryForm queryForm, Map<PortalShipOrderQueryCondition, String> conditions) {
        String code = queryForm.getCode();
        if (code != null && code.trim().length() != 0) {
            conditions.put(PortalShipOrderQueryCondition.CODE_EQ, code);
        }
        Integer enabled = ActionUtils2.parseInt(queryForm.getEnabled());
        if (enabled != null && enabled.intValue() != 0) {
            conditions.put(PortalShipOrderQueryCondition.ENABLED_EQ, String.valueOf(enabled));
        }
        String status = queryForm.getStatus();
        if (status != null && status.trim().length() != 0) {
            conditions.put(
                    PortalShipOrderQueryCondition.PORTALSHIPORDER_STATUS_EQ,
                    status);
        }
        String startDate1 = queryForm.getCreateDate1();
        String startDate2 = queryForm.getCreateDate2();
        if (startDate1 != null && startDate1.trim().length() != 0) {
            conditions.put(PortalShipOrderQueryCondition.CREATEDATE_GE,
                    startDate1);
        }
        if (startDate2 != null && startDate2.trim().length() != 0) {
            conditions.put(PortalShipOrderQueryCondition.CREATEDATE_LT,
                    startDate2);
        }
        String pocode = queryForm.getPocode();
        if (pocode != null && pocode.trim().length() != 0) {
            conditions.put(PortalShipOrderQueryCondition.PO_CODE_EQ, pocode);
        }
        String partCode = queryForm.getPart_code();
        if (partCode != null && partCode.trim().length() != 0) {
            conditions
                    .put(PortalShipOrderQueryCondition.PART_CODE_EQ, partCode);
        }
    }

    public ActionForward viewTwoReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PurchaseOrderInspectionPendingItemQueryForm formBean = (PurchaseOrderInspectionPendingItemQueryForm)form;
        PurchaseOrderInspectionPendingManager poipm =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        String shipOrderId = request.getParameter("id");
        PortalShipOrder portalShipOrder = portalShipOrderManager
                .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
        List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
                .getPortalShipOrderItemListByOrderId(
                        Integer.valueOf(Integer.parseInt(shipOrderId)));
        User user = getCurrentUser(request);
        request.setAttribute("x_portalShipOrder", portalShipOrder);
        request.setAttribute("x_portalShipOrderItemList",
                portalShipOrderItemList);
        request.getSession().setAttribute("path", request.getContextPath());

        request.setAttribute("X_ENABLEDDISABLEDLIST",
                PersistentEnum.getEnumList(EnabledDisabled.class));
        return mapping.findForward("page");
    }
    public ActionForward printPurchaseOrderActualReceipts(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PurchaseOrderInspectionPendingManager poipm =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        PortalShipOrderItemManager portalShipOrderItemManager =
                ServiceLocator.getPortalShipOrderItemManager(request);
        String shipOrderId = request.getParameter("id");
        String itemIds = request.getParameter("item");
        if (itemIds != "") {
            String[] arrays = itemIds.split(";"); byte b; int i; String[] arrayOfString1;
            for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String array = arrayOfString1[b];
                String[] item = array.split(",");
                String itemId = item[0];
                String actual = item[1];
                PortalShipOrderItem portalShipOrderItem = portalShipOrderItemManager
                        .getPortalShipOrderItem(Integer.valueOf(Integer.parseInt(itemId)));

                portalShipOrderItem.setActual_qty(new BigDecimal(actual));

                portalShipOrderItemManager
                        .updatePortalShipOrderItem(portalShipOrderItem); b++; }

        }
        PortalShipOrder portalShipOrder = portalShipOrderManager
                .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
        List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
                .getPortalShipOrderItemListByOrderId(
                        Integer.valueOf(Integer.parseInt(shipOrderId)));
        Supplier supplier = ((PortalShipOrderItem)portalShipOrderItemList.get(0)).getPoipItem()
                .getPoip_number().getSupplier();
        request.setAttribute("supplier", supplier);
        request.setAttribute("x_portalShipOrder", portalShipOrder);
        request.setAttribute("x_portalShipOrderItemList",
                portalShipOrderItemList);
        request.getSession().setAttribute("path", request.getContextPath());
        request.setAttribute("X_ENABLEDDISABLEDLIST",
                PersistentEnum.getEnumList(EnabledDisabled.class));
        return mapping.findForward("page");
    }

    private PortalShipOrder getPortalShipOrder(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        PortalShipOrder portalShipOrder = portalShipOrderManager
                .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
        if (portalShipOrder == null)
            throw new ActionException("portalShipOrder.notFound", id);
        return portalShipOrder;
    }
    public ActionForward updatePortalShipOrderItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalShipOrderItemManager itemsmanager =
                ServiceLocator.getPortalShipOrderItemManager(request);
        String ids = request.getParameter("id");
        String[] id = ids.split(";"); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = id).length, b = 0; b < i; ) { String str = arrayOfString1[b];
            PortalShipOrderItem item = itemsmanager
                    .getPortalShipOrderItem(Integer.valueOf(Integer.parseInt(str)));
            item.setPrintStatus(YesNo.YES);
            itemsmanager.updatePortalShipOrderItem(item);
            b++; }

        return new ActionForward("listPurchaseOrderActualReceipts.do", true);
    }

    public ActionForward viewTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalShipOrderMainQueryForm formBean = (PortalShipOrderMainQueryForm)form;
        PurchaseOrderInspectionPendingManager poipm =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        String shipOrderId = request.getParameter("id");
        PortalShipOrder portalShipOrder = portalShipOrderManager
                .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
        List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
                .getPortalShipOrderItemListByOrderId(
                        Integer.valueOf(Integer.parseInt(shipOrderId)));
        User user = getCurrentUser(request);
        String supplierCode = user.getLoginName();
        SupplierManager manager = ServiceLocator.getSupplierManager(request);
        Supplier supplier = manager.getSupplierByCode(supplierCode);
        request.setAttribute("supplier", supplier);
        List<Box> resultList = portalShipOrderManager
                .getBoxByShipOrderId(portalShipOrder.getId());

        int totalCount = resultList.size();

        int pageCount = 0;

        int endNum = formBean.getPageSizeAsInt();
        if (endNum == -1) {
            endNum = resultList.size();
        }

        int startNum = formBean.getPageNoAsInt() + 1;

        if (totalCount % endNum > 0) {

            pageCount = totalCount / endNum + 1;
        } else {

            pageCount = totalCount / endNum;
        }
        if (totalCount > 0) {
            if (startNum <= pageCount) {
                if (startNum == 1) {

                    if (totalCount > endNum)
                    {

                        resultList = resultList.subList(0, endNum);
                    }
                } else {

                    int fromIndex = (startNum - 1) * endNum;

                    int toIndex = startNum * endNum;

                    if ((totalCount - toIndex) % endNum >= 0) {
                        toIndex = startNum * endNum;
                    } else {
                        toIndex = (startNum - 1) * endNum +
                                totalCount % endNum;
                    }
                    if (totalCount >= toIndex) {
                        resultList = resultList.subList(fromIndex, toIndex);
                    }
                }
            } else {
                resultList = null;
            }
        }
        if (formBean.isFirstInit()) {
            formBean.init(totalCount);
        } else {
            formBean.init();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        request.setAttribute("X_DATE", format.format(new Date()));
        request.setAttribute("x_portalShipOrder", portalShipOrder);
        request.setAttribute("x_portalShipOrderItemList",
                portalShipOrderItemList);
        request.setAttribute("x_portalShopOrderBoxList", resultList);
        int size = resultList.size();
        for (Box poIpiBox : resultList) {
            if (poIpiBox.getSupplierBatch() != "" &&
                    poIpiBox.getSupplierBatch() != null) {
                size--;
                System.out.println(String.valueOf(size) + "---");
            }
        }
        request.getSession().setAttribute("size", Integer.valueOf(size));
        request.getSession().setAttribute("path", request.getContextPath());

        request.setAttribute("X_ENABLEDDISABLEDLIST",
                PersistentEnum.getEnumList(EnabledDisabled.class));
        return mapping.findForward("page");
    }
    public ActionForward printLotList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PurchaseOrderInspectionPendingItemQueryForm formBean = (PurchaseOrderInspectionPendingItemQueryForm)form;
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        String id = request.getParameter("id");
        PortalShipOrder portalShipOrder = portalShipOrderManager
                .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
        PurchaseOrderInspectionPendingManager poipm =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        List<Box> resultList = portalShipOrderManager
                .getBoxByShipOrderId(portalShipOrder.getId());
        BoxManager poipbm = ServiceLocator.getBoxManager(request);

        String str = request.getParameter("supplierBatchStr");
        String poipBoxIds = request.getParameter("poipBoxIds");

        if ((str != null || poipBoxIds != null) && (
                !str.equals("undefined") || !poipBoxIds.equals("undefined"))) {
            String[] supplierBatchList = str.split(",");
            String[] poipBoxIdList = poipBoxIds.split(",");
            if (supplierBatchList.length > 0) {
                for (int i = 0; i < supplierBatchList.length; i++) {
                    if (poipBoxIdList[i].length() > 0) {
                        Integer poipBoxId =
                                Integer.valueOf(Integer.parseInt(poipBoxIdList[i]));
                        String supplierBatch = supplierBatchList[i];
                        Box poIpiBox = poipbm.getBox(poipBoxId);
                        if (supplierBatch != null || supplierBatch != "") {
                            poIpiBox.setSupplierBatch(supplierBatch);

                            poipbm.updateBox(poIpiBox);
                        } else {
                            poIpiBox.setSupplierBatch("");
                            poipbm.updateBox(poIpiBox);
                        }
                    }
                }
            }
        }

        int totalCount = resultList.size();

        int pageCount = 0;

        int endNum = formBean.getPageSizeAsInt();
        if (endNum == -1) {
            endNum = resultList.size();
        }

        int startNum = formBean.getPageNoAsInt() + 1;

        if (totalCount % endNum > 0) {

            pageCount = totalCount / endNum + 1;
        } else {

            pageCount = totalCount / endNum;
        }
        if (totalCount > 0) {
            if (startNum <= pageCount) {
                if (startNum == 1) {

                    if (totalCount > endNum)
                    {

                        resultList = resultList.subList(0, endNum);
                    }
                } else {

                    int fromIndex = (startNum - 1) * endNum;

                    int toIndex = startNum * endNum;

                    if ((totalCount - toIndex) % endNum >= 0) {
                        toIndex = startNum * endNum;
                    } else {
                        toIndex = (startNum - 1) * endNum +
                                totalCount % endNum;
                    }
                    if (totalCount >= toIndex) {
                        resultList = resultList.subList(fromIndex, toIndex);
                    }
                }
            } else {
                resultList = null;
            }
        }
        if (formBean.isFirstInit()) {
            formBean.init(totalCount);
        } else {
            formBean.init();
        }

        request.setAttribute("x_supplierBatchStr", str);
        request.setAttribute("x_poipBoxIds", poipBoxIds);
        request.setAttribute("x_id", id);
        request.setAttribute("x_boxList", resultList);
        request.setAttribute("x_portalShipOrder", portalShipOrder);
        return mapping.findForward("page");
    }
    public ActionForward podeletePortalShipOrderTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        PurchaseOrderInspectionPendingManager poipManager =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        PurchaseOrderManager poManager =
                ServiceLocator.getPurchaseOrderManager(request);
        try {
            PortalShipOrder portalShipOrder = getPortalShipOrder(request);

            System.out.println(portalShipOrder.getId() + "----");
            List<PortalShipOrderItem> itemlist = portalShipOrderManager
                    .getPortalShipOrderItemListByOrderId(portalShipOrder
                            .getId());
            for (PortalShipOrderItem portalShipOrderItem : itemlist) {
                PurchaseOrderInspectionPendingItem poipItem = poipManager
                        .getPurchaseOrderInspectionPendingItem(portalShipOrderItem
                                .getPoipItem().getId());
                BigDecimal wBigDecimal = portalShipOrderItem
                        .getDeliveryNumber();

                poipItem.setQtyOpen(poipItem.getQtyOpen().add(wBigDecimal));

                poipManager.updatePurchaseOrderInspectionPendingItem2(poipItem);

                portalShipOrderManager
                        .deletePurchaseOrderBox(portalShipOrderItem.getId());
                portalShipOrderManager
                        .deletePortalShipOrderItem(portalShipOrderItem);

                Boolean isClose = poipManager
                        .isclosePurchaseOrderInspectionPendingByItem(poipItem
                                .getPoip_number());
                if (!isClose.booleanValue()) {
                    poipItem.getPoip_number().setStatus(
                            PurchaseOrderStatus.CLOSE);
                    poipManager.updatePurchaseOrderInspectionPending(poipItem
                            .getPoip_number());
                }
            }

            portalShipOrderManager.deletePortalShipOrder(portalShipOrder);
        } catch (Exception e) {
            e.printStackTrace();
            e.fillInStackTrace();
        }
        return new ActionForward("listShippingOrder.do", true);
    }

    public ActionForward podeletePortalShipOrderTwoItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        PurchaseOrderInspectionPendingManager poipManager =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        PurchaseOrderManager poManager =
                ServiceLocator.getPurchaseOrderManager(request);
        try {
            PortalShipOrderItem portalShipOrderItem = portalShipOrderManager
                    .getPortalShipOrderItem(Integer.valueOf(Integer.parseInt(request
                            .getParameter("id"))));
            PurchaseOrderInspectionPendingItem poipItem = poipManager
                    .getPurchaseOrderInspectionPendingItem(portalShipOrderItem
                            .getPoipItem().getId());
            BigDecimal wBigDecimal = portalShipOrderItem.getDeliveryNumber();

            poipItem.setQtyOpen(poipItem.getQtyOpen().add(wBigDecimal));

            poipManager.updatePurchaseOrderInspectionPendingItem2(poipItem);

            portalShipOrderManager.deletePurchaseOrderBox(portalShipOrderItem
                    .getId());

            portalShipOrderManager
                    .deletePortalShipOrderItem(portalShipOrderItem);
            poipItem.getPoip_number().setStatus(PurchaseOrderStatus.CLOSE);
            poipManager.updatePurchaseOrderInspectionPending(poipItem
                    .getPoip_number());
        }
        catch (Exception e) {
            e.printStackTrace();
            e.fillInStackTrace();
        }
        return new ActionForward("listShippingOrder.do", true);
    }

    public ActionForward withdrawPortalShipOrderTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        PurchaseOrderInspectionPendingManager poipManager =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        PurchaseOrderManager poManager =
                ServiceLocator.getPurchaseOrderManager(request);
        PortalShipOrder portalShipOrder = getPortalShipOrder(request);
        List<PortalShipOrderItem> portalShipOrderItemlist = null;

        List<Box> list = new ArrayList();
        List<Box> poipBoxList = null;
        try {
            if (!portalShipOrder.getStatus().equals(
                    PortalShipOrderStatus.RECEIVE)) {
                portalShipOrder.setStatus(PortalShipOrderStatus.DRAFT);
                portalShipOrderManager.updatePortalShipOrder(portalShipOrder);

                portalShipOrderItemlist = portalShipOrderManager
                        .getPortalShipOrderItemListByOrderId(portalShipOrder
                                .getId());
                for (PortalShipOrderItem portalShipOrderItem : portalShipOrderItemlist) {

                    poipBoxList = portalShipOrderManager
                            .getBoxList(portalShipOrderItem.getId());
                    list.addAll(poipBoxList);
                }
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            e.printStackTrace();
        }

        request.setAttribute("x_portalShipOrder", portalShipOrder);
        request.setAttribute("x_portalShipOrderItemList",
                portalShipOrderItemlist);
        request.setAttribute("x_portalShopOrderBoxList", list);
        int size = poipBoxList.size();
        for (Box poIpiBox : poipBoxList) {
            if (poIpiBox.getSupplierBatch() != "" &&
                    poIpiBox.getSupplierBatch() != null) {
                size--;
                System.out.println(String.valueOf(size) + "---");
            }
        }
        request.getSession().setAttribute("size", Integer.valueOf(size));

        return mapping.findForward("page");
    }
    public ActionForward createPortalShipOrderIpTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        String id = request.getParameter("id");
        PortalShipOrder portalShipOrder = getPortalShipOrder(request);
        portalShipOrderManager.createPortalShipOrderIP(portalShipOrder);

        return new ActionForward("viewPortalShipOrderTwo.do?id=" +
                portalShipOrder.getId(), true);
    }

    public static void main(String[] str) throws Exception {
        System.out.println(MD5.getDigestString("root"));
    }
    public ActionForward portalPirntCodeItemReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PurchaseOrderInspectionPendingManager poip =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        String string = request.getParameter("str");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        String[] strings = string.split(",");
        List<Box> boxlist = new ArrayList<Box>();
        PurchaseOrderInspectionPendingItem item = null; byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = strings).length, b = 0; b < i; ) { String str = arrayOfString1[b];
            Box box = poip.getBox(Integer.valueOf(Integer.parseInt(str)));

            String strNo = box.getPsoItem().getPortalShipOrder().getCode();
            String beathNo = strNo.substring(strNo.length() - 9, strNo.length());
            box.setHuCodeOrderNumber(beathNo);
            boxlist.add(box);
            b++; }

        request.setAttribute("x_item", item);
        request.setAttribute("X_RESULTLIST", boxlist);
        request.setAttribute("X_DATE", format.format(new Date()));

        request.setAttribute("path", request.getContextPath());
        return mapping.findForward("page");
    }
    public ActionForward updatePortalPirntCodeItems(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PurchaseOrderInspectionPendingManager poip =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        PurchaseOrderReceiptsManager pReceiptsManager =
                ServiceLocator.getPurchaseOrderReceiptsManager(request);
        BoxManager manager = ServiceLocator.getBoxManager(request);
        boolean bol = false;
        JSONObject object = new JSONObject();
        String ids = request.getParameter("ids");
        String[] idr = ids.split(";");
        Integer idInteger = null; byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = idr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
            Box box = manager.getBox(Integer.valueOf(Integer.parseInt(id)));
            box.setIsPrint(YesNo.YES);
            manager.updateBox(box);
            idInteger = box.getPsoItem().getPortalShipOrder().getId();
            b++; }

        bol = true;
        object.put("object", Boolean.valueOf(bol));
        object.put("id", idInteger);
        PrintWriter print = response.getWriter();
        print.print(object);
        return null;
    }
    public ActionForward updatePortalShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ids = request.getParameter("id");
        PortalShipOrderManager manager =
                ServiceLocator.getPortalShipOrderManager(request);

        PortalShipOrder order = manager.getPortalShipOrder(
                Integer.valueOf(Integer.parseInt(ids)));


        if (order.getPrintDate() == null) {
            order.setPrintDate(new Date());
        }
        order.setIsPrint(YesNo.YES);
        manager.updatePortalShipOrder(order);
        DeliverMinuteSyncJob ss =
                ServiceLocator.getDeliverMinuteSyncJobManager(request);
        ss.startSynOne(order);
        return new ActionForward("viewPortalShipOrderTwo.do?id=" +
                Integer.parseInt(ids), true);
    }
    public ActionForward updatePirntPortalShipOrderByJitPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject object = new JSONObject();
        boolean bol = false;
        String ids = request.getParameter("id");
        PortalShipOrderManager manager =
                ServiceLocator.getPortalShipOrderManager(request);
        PortalShipOrder order = manager.getPortalShipOrder(
                Integer.valueOf(Integer.parseInt(ids)));
        if (order.getPrintDate() == null) {
            order.setPrintDate(new Date());
        }
        order.setIsPrint(YesNo.YES);
        manager.updatePortalShipOrder(order);
        DeliverMinuteSyncJob ss =
                ServiceLocator.getDeliverMinuteSyncJobManager(request);
        ss.startSynOne(order);
        bol = true;
        PrintWriter print = response.getWriter();
        object.put("str", Boolean.valueOf(bol));
        print.print(object);
        return null;
    }

    public ActionForward updatePirntPortalShipOrderByNpoPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject object = new JSONObject();
        boolean bol = false;
        String ids = request.getParameter("id");
        PortalShipOrderManager manager =
                ServiceLocator.getPortalShipOrderManager(request);
        PortalShipOrder order = manager.getPortalShipOrder(
                Integer.valueOf(Integer.parseInt(ids)));
        if (order.getPrintDate() == null) {
            order.setPrintDate(new Date());
        }
        order.setIsPrint(YesNo.YES);
        manager.updatePortalShipOrder(order);
        DeliverMinuteSyncJob ss =
                ServiceLocator.getDeliverMinuteSyncJobManager(request);
        ss.startSynOne(order);
        bol = true;
        PrintWriter print = response.getWriter();
        object.put("str", Boolean.valueOf(bol));
        print.print(object);
        return null;
    }
    public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("page");
    }
    public ActionForward createPortalShipTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PurchaseOrderInspectionPendingManager poipm =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        PurchaseOrderInspectionPendingQueryForm queryForm = (PurchaseOrderInspectionPendingQueryForm)form;

        PortalShipOrder portalShipOrder = null;
        SupplierManager supplierManager =
                ServiceLocator.getSupplierManager(request);
        String supplierCode = request.getParameter("supplierCode");

        Supplier supplier = supplierManager.getSupplierByCode(supplierCode);
        if (supplier != null) {
            try {
                String[] poipItemIdsList = request
                        .getParameterValues("poipItemIds");
                String[] deliveryNumberList = request
                        .getParameterValues("deliveryNumbers");
                String createType = request.getParameter("createType");
                for (int i = 0; i < poipItemIdsList.length; i++) {
                    if (!deliveryNumberList[i].equals("")) {
                        if (portalShipOrder == null) {
                            portalShipOrder = new PortalShipOrder();
                            Site site = getCurrentUser(request)
                                    .getPrimarySite();
                            User requestor = getCurrentUser(request);
                            SimpleDateFormat sdf = new SimpleDateFormat(
                                    "yyyy/MM/dd");
                            String arrivalDate = queryForm.getReceivingDate();
                            Date arrDate = null;
                            if (arrivalDate != null &&
                                    arrivalDate.trim().length() != 0) {
                                arrDate = sdf.parse(arrivalDate);
                            }
                            portalShipOrder.setType(Integer.valueOf(1));
                            portalShipOrder.setCreateType(createType);
                            portalShipOrder.setSupplier(supplier);

                            portalShipOrderManager.insertPortalShipOrderSupplier(
                                    portalShipOrder, site, supplier, arrDate);
                        }
                        String poipItem = poipItemIdsList[i];
                        BigDecimal deliveryNumber = new BigDecimal(
                                deliveryNumberList[i]);

                        PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem1 = poipm
                                .getPurchaseOrderInspectionPendingItem(
                                        Integer.valueOf(Integer.parseInt(poipItem)));

                        BigDecimal wBigDecimal = purchaseOrderInspectionPendingItem1
                                .getQtyOpen();
                        purchaseOrderInspectionPendingItem1.setQtyOpen(wBigDecimal
                                .subtract(deliveryNumber));
                        poipm.updatePurchaseOrderInspectionPendingItem3(purchaseOrderInspectionPendingItem1);

                        Boolean isClose = poipm
                                .isclosePurchaseOrderInspectionPendingByItem(purchaseOrderInspectionPendingItem1
                                        .getPoip_number());
                        if (isClose.booleanValue()) {
                            purchaseOrderInspectionPendingItem1.getPoip_number()
                                    .setStatus(PurchaseOrderStatus.WAIT);
                            poipm.updatePurchaseOrderInspectionPending(purchaseOrderInspectionPendingItem1
                                    .getPoip_number());
                        }

                        PortalShipOrderItem portalShipOrderItem = new PortalShipOrderItem();

                        portalShipOrderItem.setPortalShipOrder(portalShipOrder);
                        portalShipOrderItem
                                .setPart(purchaseOrderInspectionPendingItem1
                                        .getItemNumber());
                        portalShipOrderItem
                                .setPoipItem(purchaseOrderInspectionPendingItem1);
                        portalShipOrderItem.setDeliveryNumber(deliveryNumber);
                        portalShipOrderItem
                                .setQty_std(purchaseOrderInspectionPendingItem1
                                        .getQty_std());
                        portalShipOrderManager
                                .insertPortalShipOrderItem(portalShipOrderItem);
                    }
                }
                PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem = poipm
                        .getPurchaseOrderInspectionPendingItem(
                                Integer.valueOf(Integer.parseInt(poipItemIdsList[0])));
                if (!"T".equals(purchaseOrderInspectionPendingItem
                        .getVd_promo())) {
                    portalShipOrderManager
                            .createPortalShipOrderIP(portalShipOrder);
                }
            } catch (Exception e) {
                e.fillInStackTrace();
            }
            return new ActionForward("/viewPortalShipOrderTwo.do?id=" +
                    portalShipOrder.getId(), true);
        }
        return new ActionForward("/listShippingOrder.do", true);
    }

    public ActionForward purchaseItemTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PurchaseOrderInspectionPendingItemQueryForm queryForm = (PurchaseOrderInspectionPendingItemQueryForm)form;
        PurchaseOrderInspectionPendingManager fm =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        String idString = queryForm.getSelectPoipItemId();
        String supplierCode = request.getParameter("supplierCode");
        String[] ids = idString.split(";");
        Map conditions = new HashMap();

        conditions.put(PurchaseOrderInspectionPendingQueryCondition.qtyOpen_DT,
                Integer.valueOf(0));
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
        String poNumber = queryForm.getPoip_number();
        if (poNumber != null && !poNumber.equals("")) {
            conditions.put(
                    PurchaseOrderInspectionPendingQueryCondition.PONUMBER_EQ,
                    poNumber);
        }

        String itemNumber = queryForm.getItemNumber();
        if (itemNumber != null && !itemNumber.equals("")) {
            conditions.put(
                    PurchaseOrderInspectionPendingQueryCondition.ITEMNUMBER_EQ,
                    itemNumber);
        }

        String endPoDate = queryForm.getDueDate();
        if (endPoDate != null && !endPoDate.equals("")) {
            conditions.put(
                    PurchaseOrderInspectionPendingQueryCondition.DUEDATE_EQ,
                    endPoDate);
        }
        String status = queryForm.getStatus();
        if (status != null && !status.equals("")) {
            conditions.put(
                    PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ,
                    status);
        } else {
            conditions.put(
                    PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ, Integer.valueOf(1));
        }
        SupplierManager manager = ServiceLocator.getSupplierManager(request);
        Supplier supplier = manager.getSupplierByCode(supplierCode);
        if (getCurrentUser(request).getPrimarySite() != null &&
                supplier != null)
        {
            conditions
                    .put(PurchaseOrderInspectionPendingQueryCondition.SUPPLIER_ID_EQ,
                            supplier.getId());
        }
        if (queryForm.isFirstInit()) {
            queryForm
                    .init(fm.getPurchaseOrderInspectionPendingItemListCount(conditions));
        } else {
            queryForm.init();
        }
        List<PurchaseOrderInspectionPendingItem> result = fm
                .getPurchaseOrderInspectionPendingItemList(conditions,
                        queryForm.getPageNoAsInt(),
                        queryForm.getPageSizeAsInt(),
                        PurchaseOrderInspectionPendingQueryOrder.ID,
                        queryForm.isDescend());
        if (ids.length > 0) {
            for (PurchaseOrderInspectionPendingItem pendingItem : result) {
                Integer itemId = pendingItem.getId();
                Boolean isClose = Boolean.valueOf(false);

                for (int i = 0; i < ids.length; i++) {
                    if (!ids[i].equals("") &&
                            itemId.intValue() == Integer.parseInt(ids[i])) {
                        isClose = Boolean.valueOf(true);
                    }
                }

                if (isClose.booleanValue()) {
                    pendingItem.setChecked("checked");
                }
            }
        }
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("X_YESNOLIST",
                PersistentEnum.getEnumList(YesNo.class));
        request.setAttribute("X_WmsPurchaseOrderStatusLIST",
                PersistentEnum.getEnumList(PurchaseOrderStatus.class));
        return mapping.findForward("page");
    }

    public ActionForward shippingOrderWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
            PortalShipOrderManager fm =
                    ServiceLocator.getPortalShipOrderManager(request);
            if (queryForm.getOrder() == "") {
                queryForm.setOrder("id");
                queryForm.setDescend(true);
            }
            Map conditions = new HashMap<>();
            getConditionAndOrder(queryForm, conditions, request);
            getConditionsFrom(queryForm, conditions);

            conditions.put(PortalShipOrderQueryCondition.CREATETYPE_EQ,
                    "NJIT_NPO");
            String exportType = queryForm.getExportType();
            if (exportType != null && exportType.length() > 0) {
                List data = fm.getPortalShipOrderList(conditions, 0, -1, null,
                        false);
                int index = SessionTempFile.createNewTempFile(request);
                String fileName = "PortalShipOrder";
                String suffix = ExportUtil.export(
                        exportType,
                        data,
                        request,
                        new FileOutputStream(SessionTempFile.getTempFile(index,
                                request)), new Exportable()
                        {
                            public void exportHead(List row, HttpServletRequest request) throws Exception
                            {
                                MessageResources messages = PortalShipOrderActiony.this.getResources(request);
                                row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                        "SO.No1"));
                                row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                        "supplier1"));
                                row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                        "supplier.code1"));
                                row.add("创建时间");
                                row.add("发货时间");
                                row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                        "salesOrderItem.isprintlabels"));
                                row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                        "salesOrderItem.issync"));
                                row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                        "portalShipOrder.status"));
                            }
                            public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                                row.add(BeanUtils.getProperty(data, "code"));
                                row.add(BeanUtils.getProperty(data,
                                        "site.supplier.name"));
                                row.add(BeanUtils.getProperty(data,
                                        "site.supplier.code"));

                                row.add(BeanUtils.getProperty(data,
                                        "createDate"));
                                row.add(BeanUtils.getProperty(data,
                                        "arrivalDate"));

                                PortalShipOrder order = (PortalShipOrder)data;
                                if (order.getIsPrint().equals(YesNo.YES)) {
                                    row.add("已打印");
                                } else if (order.getIsPrint().equals(YesNo.NO)) {
                                    row.add("未打印");
                                } else {
                                    row.add("未打印");
                                }
                                if (order.getIsSync() != null) {
                                    if (order.getIsSync().equals("0")) {
                                        row.add("未同步");
                                    } else if (order.getIsSync().equals("1")) {
                                        row.add("已同步");
                                    } else if (order.getIsSync().equals("")) {
                                        row.add("未同步");
                                    }
                                } else {
                                    row.add("未同步");
                                }
                                String locale = PortalShipOrderActiony.this.getCurrentUser(request)
                                        .getLocale();
                                if ("en".equals(locale)) {
                                    row.add(BeanUtils.getProperty(data,
                                            "status.engShortDescription"));
                                } else {
                                    row.add(BeanUtils.getProperty(data,
                                            "status.chnShortDescription"));
                                }
                            }
                        });

                return new ActionForward("download/" + index + "/" +
                        URLEncoder.encode(fileName, "UTF-8") + '.' + suffix,
                        true);
            }
            if (queryForm.isFirstInit()) {
                queryForm.init(fm.getPortalShipOrderListCount(conditions));
            } else {
                queryForm.init();
            }

            List result = fm.getPortalShipOrderList(conditions,
                    queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                    PortalShipOrderQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
            request.setAttribute("X_ENABLEDDISABLEDLIST",
                    PersistentEnum.getEnumList(EnabledDisabled.class));
            request.setAttribute("x_statusList",
                    PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
            request.setAttribute("X_RESULTLIST", result);
            request.setAttribute("x_selType", Integer.valueOf(169));
        } catch (Exception e) {

            e.printStackTrace();
        }
        return mapping.findForward("page");
    }

    public ActionForward shippingOrderWrongSupplier(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
            PortalShipOrderManager fm =
                    ServiceLocator.getPortalShipOrderManager(request);
            if (queryForm.getOrder() == "") {
                queryForm.setOrder("createDate");
                queryForm.setDescend(true);
            }

            Map conditions = new HashMap();
            getConditionAndOrder(queryForm, conditions, request);

            getConditionsFrom(queryForm, conditions);

            conditions.put(PortalShipOrderQueryCondition.CREATETYPE_EQ,
                    "NJIT_NPO");
            User user = getCurrentUser(request);
            SupplierManager supplierManager =
                    ServiceLocator.getSupplierManager(request);
            Supplier supplier = supplierManager.getSupplierByCode(user
                    .getLoginName());
            if (supplier != null) {
                conditions.put(PortalShipOrderQueryCondition.SUPPLIER_ID_EQ,
                        supplier.getId());
            } else {
                conditions.put(PortalShipOrderQueryCondition.SUPPLIER_ID_EQ, Integer.valueOf(0));
            }

            String exportType = queryForm.getExportType();
            if (exportType != null && exportType.length() > 0) {
                List data = fm.getPortalShipOrderList(conditions, 0, -1, null,
                        false);
                int index = SessionTempFile.createNewTempFile(request);
                String fileName = "PortalShipOrder";
                String suffix = ExportUtil.export(
                        exportType,
                        data,
                        request,
                        new FileOutputStream(SessionTempFile.getTempFile(index,
                                request)), new Exportable()
                        {
                            public void exportHead(List row, HttpServletRequest request) throws Exception
                            {
                                MessageResources messages = PortalShipOrderActiony.this.getResources(request);
                                row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                        "SO.No1"));
                                row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                        "supplier1"));
                                row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                        "supplier.code1"));
                                row.add("创建时间");
                                row.add("发货时间");
                                row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                        "portalShipOrder.status"));
                            }
                            public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                                row.add(BeanUtils.getProperty(data, "code"));
                                row.add(BeanUtils.getProperty(data,
                                        "site.supplier.name"));
                                row.add(BeanUtils.getProperty(data,
                                        "site.supplier.code"));
                                row.add("");
                                row.add(BeanUtils.getProperty(data,
                                        "createDate"));
                                String locale = PortalShipOrderActiony.this.getCurrentUser(request)
                                        .getLocale();
                                if ("en".equals(locale)) {
                                    row.add(BeanUtils.getProperty(data,
                                            "status.engShortDescription"));
                                } else {
                                    row.add(BeanUtils.getProperty(data,
                                            "status.chnShortDescription"));
                                }
                            }
                        });
                return new ActionForward("download/" + index + "/" +
                        URLEncoder.encode(fileName, "UTF-8") + '.' + suffix,
                        true);
            }
            if (queryForm.isFirstInit()) {
                queryForm.init(fm.getPortalShipOrderListCount(conditions));
            } else {
                queryForm.init();
            }
            List result = fm.getPortalShipOrderList(conditions,
                    queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                    PortalShipOrderQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());
            request.setAttribute("X_ENABLEDDISABLEDLIST",
                    PersistentEnum.getEnumList(EnabledDisabled.class));
            request.setAttribute("x_statusList",
                    PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
            request.setAttribute("X_RESULTLIST", result);
            request.setAttribute("x_selType", Integer.valueOf(177));
        } catch (Exception e) {

            e.printStackTrace();
        }
        return mapping.findForward("page");
    }
    public ActionForward viewTwoWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalShipOrderMainQueryForm formBean = (PortalShipOrderMainQueryForm)form;

        PurchaseOrderInspectionPendingManager poipm =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        String shipOrderId = request.getParameter("id");
        String GrantedSite = request.getParameter("GrantedSite");
        PortalShipOrder portalShipOrder = portalShipOrderManager
                .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
        List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
                .getPortalShipOrderItemListByOrderId(
                        Integer.valueOf(Integer.parseInt(shipOrderId)));
        User user = getCurrentUser(request);
        String supplierCode = user.getLoginName();
        SupplierManager manager = ServiceLocator.getSupplierManager(request);
        Supplier supplier = manager.getSupplierByCode(supplierCode);
        request.setAttribute("supplier", supplier);
        List<Box> resultList = portalShipOrderManager
                .getBoxByShipOrderId(portalShipOrder.getId());

        int totalCount = resultList.size();

        int pageCount = 0;

        int endNum = formBean.getPageSizeAsInt();
        if (endNum == -1) {
            endNum = resultList.size();
        }

        int startNum = formBean.getPageNoAsInt() + 1;

        if (totalCount % endNum > 0) {

            pageCount = totalCount / endNum + 1;
        } else {

            pageCount = totalCount / endNum;
        }
        if (totalCount > 0) {
            if (startNum <= pageCount) {
                if (startNum == 1) {

                    if (totalCount > endNum)
                    {

                        resultList = resultList.subList(0, endNum);
                    }
                } else {

                    int fromIndex = (startNum - 1) * endNum;

                    int toIndex = startNum * endNum;

                    if ((totalCount - toIndex) % endNum >= 0) {
                        toIndex = startNum * endNum;
                    } else {
                        toIndex = (startNum - 1) * endNum +
                                totalCount % endNum;
                    }
                    if (totalCount >= toIndex) {
                        resultList = resultList.subList(fromIndex, toIndex);
                    }
                }
            } else {
                resultList = null;
            }
        }
        String exportType = formBean.getExportType();
        if (exportType != null && exportType.length() > 0) {
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "PortalShipOrderBoxItem";
            String suffix = ExportUtil.export(
                    exportType,
                    resultList,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources messages = PortalShipOrderActiony.this.getResources(request);
                            row.add("箱单号");
                            row.add("供应商批次");
                            row.add("组件/物料编码");
                            row.add("品名");
                            row.add("包装箱容量");
                            row.add("批次数量");
                            row.add("发货数量");
                        }

                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanUtils.getProperty(data, "lot.id"));
                            row.add(
                                    BeanUtils.getProperty(data, "supplierBatch"));
                            row.add(BeanUtils.getProperty(data,
                                    "psoItem.poipItem.itemNumber.id"));
                            row.add(BeanUtils.getProperty(data,
                                    "psoItem.poipItem.itemNumber.dpiNo"));
                            row.add(BeanUtils.getProperty(data,
                                    "psoItem.qty_std"));
                            row.add("");
                            row.add(BeanUtils.getProperty(data, "number"));
                            row.add(BeanUtils.getProperty(data,
                                    "isPrint.chnShortDescription"));
                        }
                    });
            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (formBean.isFirstInit()) {
            formBean.init(totalCount);
        } else {
            formBean.init();
        }

        request.setAttribute("x_portalShipOrder", portalShipOrder);
        request.setAttribute("x_portalShipOrderItemList",
                portalShipOrderItemList);
        request.setAttribute("x_portalShopOrderBoxList", resultList);
        int size = resultList.size();
        for (Box poIpiBox : resultList) {
            if (poIpiBox.getSupplierBatch() != "" &&
                    poIpiBox.getSupplierBatch() != null) {
                size--;
                System.out.println(String.valueOf(size) + "---");
            }
        }
        request.getSession().setAttribute("size", Integer.valueOf(size));
        request.getSession().setAttribute("path", request.getContextPath());

        request.setAttribute("X_ENABLEDDISABLEDLIST",
                PersistentEnum.getEnumList(EnabledDisabled.class));
        request.setAttribute("X_GRANTEDSITE", GrantedSite);
        return mapping.findForward("page");
    }
    public ActionForward printPortalShipOrderViewWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalShipOrderMainQueryForm formBean = (PortalShipOrderMainQueryForm)form;
        PurchaseOrderInspectionPendingManager poipm =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        String shipOrderId = request.getParameter("id");
        PortalShipOrder portalShipOrder = portalShipOrderManager
                .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
        List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
                .getPortalShipOrderItemListByOrderId(
                        Integer.valueOf(Integer.parseInt(shipOrderId)));
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        request.setAttribute("X_DATE", format.format(new Date()));
        request.setAttribute("x_portalShipOrder", portalShipOrder);
        request.setAttribute("x_portalShipOrderItemList",
                portalShipOrderItemList);
        request.getSession().setAttribute("path", request.getContextPath());
        return mapping.findForward("page");
    }
    public ActionForward printPortalShipOrderViewProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalShipOrderMainQueryForm formBean = (PortalShipOrderMainQueryForm)form;
        PurchaseOrderInspectionPendingManager poipm =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        String shipOrderId = request.getParameter("id");
        PortalShipOrder portalShipOrder = portalShipOrderManager
                .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
        List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
                .getPortalShipOrderItemListByOrderId(
                        Integer.valueOf(Integer.parseInt(shipOrderId)));
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        request.setAttribute("X_DATE", format.format(new Date()));
        request.setAttribute("x_portalShipOrder", portalShipOrder);
        request.setAttribute("x_portalShipOrderItemList",
                portalShipOrderItemList);
        request.getSession().setAttribute("path", request.getContextPath());
        return mapping.findForward("page");
    }
    public ActionForward printLotListWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PurchaseOrderInspectionPendingItemQueryForm formBean = (PurchaseOrderInspectionPendingItemQueryForm)form;
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        String id = request.getParameter("id");
        PortalShipOrder portalShipOrder = portalShipOrderManager
                .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
        PurchaseOrderInspectionPendingManager poipm =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        List<Box> resultList = portalShipOrderManager
                .getBoxByShipOrderId(portalShipOrder.getId());
        BoxManager poipbm = ServiceLocator.getBoxManager(request);

        String str = request.getParameter("supplierBatchStr");
        String poipBoxIds = request.getParameter("poipBoxIds");

        if ((str != null || poipBoxIds != null) && (
                !str.equals("undefined") || !poipBoxIds.equals("undefined"))) {
            String[] supplierBatchList = str.split(",");
            String[] poipBoxIdList = poipBoxIds.split(",");
            if (supplierBatchList.length > 0) {
                for (int i = 0; i < supplierBatchList.length; i++) {
                    if (poipBoxIdList[i].length() > 0) {
                        Integer poipBoxId =
                                Integer.valueOf(Integer.parseInt(poipBoxIdList[i]));
                        String supplierBatch = supplierBatchList[i];
                        Box poIpiBox = poipbm.getBox(poipBoxId);
                        if (supplierBatch != null || supplierBatch != "") {
                            poIpiBox.setSupplierBatch(supplierBatch);

                            poipbm.updateBox(poIpiBox);
                        } else {
                            poIpiBox.setSupplierBatch("");
                            poipbm.updateBox(poIpiBox);
                        }
                    }
                }
            }
        }

        int totalCount = resultList.size();

        int pageCount = 0;

        int endNum = formBean.getPageSizeAsInt();
        if (endNum == -1) {
            endNum = resultList.size();
        }

        int startNum = formBean.getPageNoAsInt() + 1;

        if (totalCount % endNum > 0) {

            pageCount = totalCount / endNum + 1;
        } else {

            pageCount = totalCount / endNum;
        }
        if (totalCount > 0) {
            if (startNum <= pageCount) {
                if (startNum == 1) {

                    if (totalCount > endNum)
                    {

                        resultList = resultList.subList(0, endNum);
                    }
                } else {

                    int fromIndex = (startNum - 1) * endNum;

                    int toIndex = startNum * endNum;

                    if ((totalCount - toIndex) % endNum >= 0) {
                        toIndex = startNum * endNum;
                    } else {
                        toIndex = (startNum - 1) * endNum +
                                totalCount % endNum;
                    }
                    if (totalCount >= toIndex) {
                        resultList = resultList.subList(fromIndex, toIndex);
                    }
                }
            } else {
                resultList = null;
            }
        }
        if (formBean.isFirstInit()) {
            formBean.init(totalCount);
        } else {
            formBean.init();
        }

        request.setAttribute("x_supplierBatchStr", str);
        request.setAttribute("x_poipBoxIds", poipBoxIds);
        request.setAttribute("x_id", id);
        request.setAttribute("x_boxList", resultList);
        request.setAttribute("x_portalShipOrder", portalShipOrder);
        return mapping.findForward("page");
    }
    public ActionForward withdrawPortalShipOrderTwoWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        PurchaseOrderInspectionPendingManager poipManager =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        PurchaseOrderManager poManager =
                ServiceLocator.getPurchaseOrderManager(request);
        PortalShipOrder portalShipOrder = getPortalShipOrder(request);
        List<PortalShipOrderItem> portalShipOrderItemlist = null;

        List<Box> list = new ArrayList();
        List<Box> poipBoxList = null;
        try {
            if (!portalShipOrder.getStatus().equals(
                    PortalShipOrderStatus.RECEIVE)) {
                portalShipOrder.setStatus(PortalShipOrderStatus.DRAFT);
                portalShipOrderManager.updatePortalShipOrder(portalShipOrder);

                portalShipOrderItemlist = portalShipOrderManager
                        .getPortalShipOrderItemListByOrderId(portalShipOrder
                                .getId());
                for (PortalShipOrderItem portalShipOrderItem : portalShipOrderItemlist) {

                    poipBoxList = portalShipOrderManager
                            .getBoxList(portalShipOrderItem.getId());
                    list.addAll(poipBoxList);
                }
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            e.printStackTrace();
        }

        request.setAttribute("x_portalShipOrder", portalShipOrder);
        request.setAttribute("x_portalShipOrderItemList",
                portalShipOrderItemlist);
        request.setAttribute("x_portalShopOrderBoxList", list);
        int size = poipBoxList.size();
        for (Box poIpiBox : poipBoxList) {
            if (poIpiBox.getSupplierBatch() != "" &&
                    poIpiBox.getSupplierBatch() != null) {
                size--;
                System.out.println(String.valueOf(size) + "---");
            }
        }
        request.getSession().setAttribute("size", Integer.valueOf(size));

        return mapping.findForward("page");
    }
    public ActionForward createPortalShipOrderIpTwoWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        String id = request.getParameter("id");
        PortalShipOrder portalShipOrder = getPortalShipOrder(request);
        portalShipOrderManager.createPortalShipOrderIP(portalShipOrder);

        return new ActionForward("viewPortalShipOrderTwoWrong.do?id=" +
                portalShipOrder.getId(), true);
    }
    public ActionForward portalPirntCodeItemReportWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PurchaseOrderInspectionPendingManager poip =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        String string = request.getParameter("str");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        String[] strings = string.split(",");
        List<Box> boxlist = new ArrayList<Box>();
        PurchaseOrderInspectionPendingItem item = null; byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = strings).length, b = 0; b < i; ) { String str = arrayOfString1[b];
            Box box = poip.getBox(Integer.valueOf(Integer.parseInt(str)));

            String beatchNO = box.getPsoItem().getPortalShipOrder().getCode();
            if (beatchNO != null && !"".equals(beatchNO)) {
                String baetchNo = beatchNO.substring(beatchNO.length() - 9, beatchNO.length());
                box.setHuCodeOrderNumber(baetchNo);
            }
            boxlist.add(box); b++; }

        request.setAttribute("x_item", item);
        request.setAttribute("X_RESULTLIST", boxlist);
        request.setAttribute("X_DATE", format.format(new Date()));

        request.setAttribute("path", request.getContextPath());
        return mapping.findForward("page");
    }
    public ActionForward printQrCodeMin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PurchaseOrderInspectionPendingManager poip =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        String string = request.getParameter("str");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String[] strings = string.split(",");
        List<Box> boxlist = new ArrayList<Box>();
        PurchaseOrderInspectionPendingItem item = null; byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = strings).length, b = 0; b < i; ) { String str = arrayOfString1[b];
            Box box = poip.getBox(Integer.valueOf(Integer.parseInt(str)));

            String beatchNO = box.getPsoItem().getPortalShipOrder().getCode();
            if (beatchNO != null && !"".equals(beatchNO)) {
                String baetchNo = beatchNO.substring(beatchNO.length() - 9, beatchNO.length());
                box.setHuCodeOrderNumber(baetchNo);
            }
            boxlist.add(box); b++; }

        request.setAttribute("x_item", item);
        request.setAttribute("X_RESULTLIST", boxlist);
        request.setAttribute("X_DATE", format.format(new Date()));
        request.setAttribute("path", request.getContextPath());
        return mapping.findForward("page");
    }
    public ActionForward updatePortalPirntCodeItemsWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PurchaseOrderInspectionPendingManager poip =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        PurchaseOrderReceiptsManager pReceiptsManager =
                ServiceLocator.getPurchaseOrderReceiptsManager(request);
        JSONObject object = new JSONObject();
        boolean bol = false;
        BoxManager manager = ServiceLocator.getBoxManager(request);
        String ids = request.getParameter("ids");
        String[] idr = ids.split(";");
        Integer idInteger = null; byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = idr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
            Box box = manager.getBox(Integer.valueOf(Integer.parseInt(id)));
            box.setIsPrint(YesNo.YES);
            manager.updateBox(box);
            idInteger = box.getPsoItem().getPortalShipOrder().getId();
            b++; }

        bol = true;
        PrintWriter print = response.getWriter();
        object.put("str", Boolean.valueOf(bol));
        print.print(object);
        return null;
    }
    public ActionForward newObjectWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        PurchaseOrderInspectionPendingQueryForm pendingQueryForm = (PurchaseOrderInspectionPendingQueryForm)form;
        pendingQueryForm.setReceivingDate(simpleDateFormat.format(new Date()));

        return mapping.findForward("page");
    }
    public ActionForward createPortalShipTwoWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PurchaseOrderInspectionPendingManager poipm =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        PurchaseOrderInspectionPendingQueryForm queryForm = (PurchaseOrderInspectionPendingQueryForm)form;

        PortalShipOrder portalShipOrder = null;

        try {
            String[] poipItemIdsList = request
                    .getParameterValues("poipItemIds");
            String[] deliveryNumberList = request
                    .getParameterValues("deliveryNumbers");
            for (int i = 0; i < poipItemIdsList.length; i++) {
                if (!deliveryNumberList[i].equals("")) {
                    if (portalShipOrder == null) {
                        portalShipOrder = new PortalShipOrder();
                        Site site = getCurrentUser(request).getPrimarySite();
                        User requestor = getCurrentUser(request);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                        String arrivalDate = queryForm.getReceivingDate();
                        Date arrDate = null;
                        if (arrivalDate != null && arrivalDate.trim().length() != 0) {
                            arrDate = sdf.parse(arrivalDate);
                        }
                        portalShipOrder.setType(Integer.valueOf(2));
                        portalShipOrderManager.insertPortalShipOrder(
                                portalShipOrder, site, requestor, arrDate);
                    }

                    String poipItem = poipItemIdsList[i];
                    BigDecimal deliveryNumber = new BigDecimal(
                            deliveryNumberList[i]);

                    PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem1 = poipm
                            .getPurchaseOrderInspectionPendingItem(
                                    Integer.valueOf(Integer.parseInt(poipItem)));

                    BigDecimal wBigDecimal = purchaseOrderInspectionPendingItem1
                            .getQtyOpen();
                    purchaseOrderInspectionPendingItem1.setQtyOpen(wBigDecimal
                            .subtract(deliveryNumber));
                    poipm.updatePurchaseOrderInspectionPendingItem3(purchaseOrderInspectionPendingItem1);

                    Boolean isClose = poipm
                            .isclosePurchaseOrderInspectionPendingByItem(purchaseOrderInspectionPendingItem1
                                    .getPoip_number());
                    if (isClose.booleanValue()) {
                        purchaseOrderInspectionPendingItem1.getPoip_number()
                                .setStatus(PurchaseOrderStatus.WAIT);
                        poipm.updatePurchaseOrderInspectionPending(purchaseOrderInspectionPendingItem1
                                .getPoip_number());
                    }

                    PortalShipOrderItem portalShipOrderItem = new PortalShipOrderItem();

                    portalShipOrderItem.setPortalShipOrder(portalShipOrder);
                    portalShipOrderItem
                            .setPoipItem(purchaseOrderInspectionPendingItem1);
                    portalShipOrderItem.setDeliveryNumber(deliveryNumber);
                    portalShipOrderItem
                            .setQty_std(purchaseOrderInspectionPendingItem1
                                    .getQty_std());
                    portalShipOrderManager
                            .insertPortalShipOrderItem(portalShipOrderItem);
                }
            }

            PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem = poipm
                    .getPurchaseOrderInspectionPendingItem(
                            Integer.valueOf(Integer.parseInt(poipItemIdsList[0])));
            if (!"T".equals(purchaseOrderInspectionPendingItem.getVd_promo())) {
                portalShipOrderManager.createPortalShipOrderIP(portalShipOrder);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return new ActionForward("/viewPortalShipOrderTwoWrong.do?id=" +
                portalShipOrder.getId(), true);
    }

    public ActionForward purchaseItemTwoWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PurchaseOrderInspectionPendingItemQueryForm queryForm = (PurchaseOrderInspectionPendingItemQueryForm)form;
        PurchaseOrderInspectionPendingManager fm =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        String idString = queryForm.getSelectPoipItemId();
        String[] ids = idString.split(";");
        Map conditions = new HashMap();

        conditions.put(PurchaseOrderInspectionPendingQueryCondition.qtyOpen_DT,
                Integer.valueOf(0));
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
        String poNumber = queryForm.getPoip_number();
        if (poNumber != null && !poNumber.equals("")) {
            conditions.put(
                    PurchaseOrderInspectionPendingQueryCondition.PONUMBER_EQ,
                    poNumber);
        }

        String itemNumber = queryForm.getItemNumber();
        if (itemNumber != null && !itemNumber.equals("")) {
            conditions.put(
                    PurchaseOrderInspectionPendingQueryCondition.ITEMNUMBER_EQ,
                    itemNumber);
        }

        String endPoDate = queryForm.getDueDate();
        if (endPoDate != null && !endPoDate.equals("")) {
            conditions.put(
                    PurchaseOrderInspectionPendingQueryCondition.DUEDATE_EQ,
                    endPoDate);
        }
        String status = queryForm.getStatus();
        if (status != null && !status.equals("")) {
            conditions.put(
                    PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ,
                    status);
        } else {
            conditions.put(
                    PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ, Integer.valueOf(1));
        }
        SupplierManager manager = ServiceLocator.getSupplierManager(request);
        User user = getCurrentUser(request);
        Supplier supplier = manager.getSupplierByCode(user.getLoginName());
        if (getCurrentUser(request).getPrimarySite() != null &&
                supplier != null) {

            conditions
                    .put(PurchaseOrderInspectionPendingQueryCondition.SUPPLIER_ID_EQ,
                            supplier.getId());
        } else {
            conditions
                    .put(PurchaseOrderInspectionPendingQueryCondition.SUPPLIER_ID_EQ,
                            Integer.valueOf(0));
        }
        conditions.put(
                PurchaseOrderInspectionPendingQueryCondition.CREATETYPE_EQ, Integer.valueOf(3));
        if (queryForm.isFirstInit()) {
            queryForm
                    .init(fm.getPurchaseOrderInspectionPendingItemListCount(conditions));
        } else {
            queryForm.init();
        }
        List<PurchaseOrderInspectionPendingItem> result = fm
                .getPurchaseOrderInspectionPendingItemList(conditions,
                        queryForm.getPageNoAsInt(),
                        queryForm.getPageSizeAsInt(),
                        PurchaseOrderInspectionPendingQueryOrder.ID,
                        queryForm.isDescend());
        if (ids.length > 0) {
            for (PurchaseOrderInspectionPendingItem pendingItem : result) {
                Integer itemId = pendingItem.getId();
                Boolean isClose = Boolean.valueOf(false);

                for (int i = 0; i < ids.length; i++) {
                    if (!ids[i].equals("") &&
                            itemId.intValue() == Integer.parseInt(ids[i])) {
                        isClose = Boolean.valueOf(true);
                    }
                }

                if (isClose.booleanValue()) {
                    pendingItem.setChecked("checked");
                }
            }
        }
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("X_YESNOLIST",
                PersistentEnum.getEnumList(YesNo.class));
        request.setAttribute("X_WmsPurchaseOrderStatusLIST",
                PersistentEnum.getEnumList(PurchaseOrderStatus.class));
        return mapping.findForward("page");
    }

    public ActionForward shippingOrderProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
        PortalShipOrderManager fm =
                ServiceLocator.getPortalShipOrderManager(request);
        if (queryForm.getOrder() == "") {
            queryForm.setOrder("createDate");
        }

        Map conditions = new HashMap();
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
        getConditionsFrom(queryForm, conditions);

        conditions.put(PortalShipOrderQueryCondition.CREATETYPE_EQ, "JIT");
        User user = getCurrentUser(request);
        SupplierManager supplierManager =
                ServiceLocator.getSupplierManager(request);
        Supplier supplier = supplierManager.getSupplierByCode(user
                .getLoginName());
        if (!hasGlobalPower(request)) {
            conditions.put(PortalShipOrderQueryCondition.SITE_EQ,
                    getCurrentUser(request).getPrimarySite().getId());
            if (supplier != null) {
                conditions.put(PortalShipOrderQueryCondition.SUPPLIER_ID_EQ,
                        supplier.getId());
            } else {
                conditions.put(PortalShipOrderQueryCondition.SUPPLIER_ID_EQ, Integer.valueOf(0));
            }
        }

        String exportType = queryForm.getExportType();
        if (exportType != null && exportType.length() > 0) {
            List data = fm.getPortalShipOrderList(conditions, 0, -1, null,
                    false);
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "PortalShipOrder";
            String suffix = ExportUtil.export(
                    exportType,
                    data,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources messages = PortalShipOrderActiony.this.getResources(request);
                            row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                    "SO.No1"));
                            row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                    "supplier1"));
                            row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                    "supplier.code1"));
                            row.add("创建时间");
                            row.add("发货时间");
                            row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request),
                                    "portalShipOrder.status"));
                        }

                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanUtils.getProperty(data, "code"));
                            row.add(BeanUtils.getProperty(data,
                                    "site.supplier.name"));
                            row.add(BeanUtils.getProperty(data,
                                    "site.supplier.code"));
                            row.add("");
                            row.add(BeanUtils.getProperty(data, "createDate"));
                            String locale = PortalShipOrderActiony.this.getCurrentUser(request).getLocale();
                            if ("en".equals(locale)) {
                                row.add(BeanUtils.getProperty(data,
                                        "status.engShortDescription"));
                            } else {
                                row.add(BeanUtils.getProperty(data,
                                        "status.chnShortDescription"));
                            }
                        }
                    });
            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getPortalShipOrderListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getPortalShipOrderList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                PortalShipOrderQueryOrder.getEnum(queryForm.getOrder()),
                queryForm.isDescend());
        request.setAttribute("X_ENABLEDDISABLEDLIST",
                PersistentEnum.getEnumList(EnabledDisabled.class));
        request.setAttribute("x_statusList",
                PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(170));
        return mapping.findForward("page");
    }
    public ActionForward viewTwoProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalShipOrderMainQueryForm formBean = (PortalShipOrderMainQueryForm)form;
        PurchaseOrderInspectionPendingManager poipm =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        String shipOrderId = request.getParameter("id");
        PortalShipOrder portalShipOrder = portalShipOrderManager
                .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
        List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
                .getPortalShipOrderItemListByOrderId(
                        Integer.valueOf(Integer.parseInt(shipOrderId)));
        User user = getCurrentUser(request);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        request.setAttribute("X_DATE", format.format(new Date()));
        List<Box> resultList = portalShipOrderManager
                .getBoxByShipOrderId(portalShipOrder.getId());

        int totalCount = resultList.size();

        int pageCount = 0;

        int endNum = formBean.getPageSizeAsInt();
        if (endNum == -1) {
            endNum = resultList.size();
        }

        int startNum = formBean.getPageNoAsInt() + 1;

        if (totalCount % endNum > 0) {

            pageCount = totalCount / endNum + 1;
        } else {

            pageCount = totalCount / endNum;
        }
        if (totalCount > 0) {
            if (startNum <= pageCount) {
                if (startNum == 1) {

                    if (totalCount > endNum)
                    {

                        resultList = resultList.subList(0, endNum);
                    }
                } else {

                    int fromIndex = (startNum - 1) * endNum;

                    int toIndex = startNum * endNum;

                    if ((totalCount - toIndex) % endNum >= 0) {
                        toIndex = startNum * endNum;
                    } else {
                        toIndex = (startNum - 1) * endNum +
                                totalCount % endNum;
                    }
                    if (totalCount >= toIndex) {
                        resultList = resultList.subList(fromIndex, toIndex);
                    }
                }
            } else {
                resultList = null;
            }
        }
        String exportType = formBean.getExportType();
        if (exportType != null && exportType.length() > 0) {
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "PortalShipOrderBoxItem";
            String suffix = ExportUtil.export(
                    exportType,
                    resultList,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources messages = PortalShipOrderActiony.this.getResources(request);
                            row.add("箱单号");
                            row.add("供应商批次");
                            row.add("组件/物料编码");
                            row.add("品名");
                            row.add("包装箱容量");
                            row.add("批次数量");
                            row.add("发货数量");
                        }

                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanUtils.getProperty(data, "lot.id"));
                            row.add(
                                    BeanUtils.getProperty(data, "supplierBatch"));
                            row.add(BeanUtils.getProperty(data,
                                    "psoItem.poipItem.itemNumber.id"));
                            row.add(BeanUtils.getProperty(data,
                                    "psoItem.poipItem.itemNumber.dpiNo"));
                            row.add(BeanUtils.getProperty(data,
                                    "psoItem.qty_std"));
                            row.add("");
                            row.add(BeanUtils.getProperty(data, "number"));
                            row.add(BeanUtils.getProperty(data,
                                    "isPrint.chnShortDescription"));
                        }
                    });
            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (formBean.isFirstInit()) {
            formBean.init(totalCount);
        } else {
            formBean.init();
        }

        request.setAttribute("x_portalShipOrder", portalShipOrder);
        request.setAttribute("x_portalShipOrderItemList",
                portalShipOrderItemList);
        request.setAttribute("x_portalShopOrderBoxList", resultList);
        int size = resultList.size();
        for (Box poIpiBox : resultList) {
            if (poIpiBox.getSupplierBatch() != "" &&
                    poIpiBox.getSupplierBatch() != null) {
                size--;
                System.out.println(String.valueOf(size) + "---");
            }
        }
        request.getSession().setAttribute("size", Integer.valueOf(size));
        request.getSession().setAttribute("path", request.getContextPath());

        request.setAttribute("X_ENABLEDDISABLEDLIST",
                PersistentEnum.getEnumList(EnabledDisabled.class));
        return mapping.findForward("page");
    }
    public ActionForward printLotListProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PurchaseOrderInspectionPendingItemQueryForm formBean = (PurchaseOrderInspectionPendingItemQueryForm)form;
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        String id = request.getParameter("id");
        PortalShipOrder portalShipOrder = portalShipOrderManager
                .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
        PurchaseOrderInspectionPendingManager poipm =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        List<Box> resultList = portalShipOrderManager
                .getBoxByShipOrderId(portalShipOrder.getId());
        BoxManager poipbm = ServiceLocator.getBoxManager(request);

        String str = request.getParameter("supplierBatchStr");
        String poipBoxIds = request.getParameter("poipBoxIds");

        if ((str != null || poipBoxIds != null) && (
                !str.equals("undefined") || !poipBoxIds.equals("undefined"))) {
            String[] supplierBatchList = str.split(",");
            String[] poipBoxIdList = poipBoxIds.split(",");
            if (supplierBatchList.length > 0) {
                for (int i = 0; i < supplierBatchList.length; i++) {
                    if (poipBoxIdList[i].length() > 0) {
                        Integer poipBoxId =
                                Integer.valueOf(Integer.parseInt(poipBoxIdList[i]));
                        String supplierBatch = supplierBatchList[i];
                        Box poIpiBox = poipbm.getBox(poipBoxId);
                        if (supplierBatch != null || supplierBatch != "") {
                            poIpiBox.setSupplierBatch(supplierBatch);

                            poipbm.updateBox(poIpiBox);
                        } else {
                            poIpiBox.setSupplierBatch("");
                            poipbm.updateBox(poIpiBox);
                        }
                    }
                }
            }
        }

        int totalCount = resultList.size();

        int pageCount = 0;

        int endNum = formBean.getPageSizeAsInt();
        if (endNum == -1) {
            endNum = resultList.size();
        }

        int startNum = formBean.getPageNoAsInt() + 1;

        if (totalCount % endNum > 0) {

            pageCount = totalCount / endNum + 1;
        } else {

            pageCount = totalCount / endNum;
        }
        if (totalCount > 0) {
            if (startNum <= pageCount) {
                if (startNum == 1) {

                    if (totalCount > endNum)
                    {

                        resultList = resultList.subList(0, endNum);
                    }
                } else {

                    int fromIndex = (startNum - 1) * endNum;

                    int toIndex = startNum * endNum;

                    if ((totalCount - toIndex) % endNum >= 0) {
                        toIndex = startNum * endNum;
                    } else {
                        toIndex = (startNum - 1) * endNum +
                                totalCount % endNum;
                    }
                    if (totalCount >= toIndex) {
                        resultList = resultList.subList(fromIndex, toIndex);
                    }
                }
            } else {
                resultList = null;
            }
        }
        if (formBean.isFirstInit()) {
            formBean.init(totalCount);
        } else {
            formBean.init();
        }

        request.setAttribute("x_supplierBatchStr", str);
        request.setAttribute("x_poipBoxIds", poipBoxIds);
        request.setAttribute("x_id", id);
        request.setAttribute("x_boxList", resultList);
        request.setAttribute("x_portalShipOrder", portalShipOrder);
        return mapping.findForward("page");
    }
    public ActionForward withdrawPortalShipOrderTwoProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        PurchaseOrderInspectionPendingManager poipManager =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        PurchaseOrderManager poManager =
                ServiceLocator.getPurchaseOrderManager(request);
        PortalShipOrder portalShipOrder = getPortalShipOrder(request);
        List<PortalShipOrderItem> portalShipOrderItemlist = null;

        List<Box> list = new ArrayList();
        List<Box> poipBoxList = null;
        try {
            if (!portalShipOrder.getStatus().equals(
                    PortalShipOrderStatus.RECEIVE)) {
                portalShipOrder.setStatus(PortalShipOrderStatus.DRAFT);
                portalShipOrderManager.updatePortalShipOrder(portalShipOrder);

                portalShipOrderItemlist = portalShipOrderManager
                        .getPortalShipOrderItemListByOrderId(portalShipOrder
                                .getId());
                for (PortalShipOrderItem portalShipOrderItem : portalShipOrderItemlist) {

                    poipBoxList = portalShipOrderManager
                            .getBoxList(portalShipOrderItem.getId());
                    list.addAll(poipBoxList);
                }
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            e.printStackTrace();
        }

        request.setAttribute("x_portalShipOrder", portalShipOrder);
        request.setAttribute("x_portalShipOrderItemList",
                portalShipOrderItemlist);
        request.setAttribute("x_portalShopOrderBoxList", list);
        int size = poipBoxList.size();
        for (Box poIpiBox : poipBoxList) {
            if (poIpiBox.getSupplierBatch() != "" &&
                    poIpiBox.getSupplierBatch() != null) {
                size--;
                System.out.println(String.valueOf(size) + "---");
            }
        }
        request.getSession().setAttribute("size", Integer.valueOf(size));

        return mapping.findForward("page");
    }
    public ActionForward createPortalShipOrderIpTwoProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        String id = request.getParameter("id");
        PortalShipOrder portalShipOrder = getPortalShipOrder(request);
        portalShipOrderManager.createPortalShipOrderIP(portalShipOrder);

        return new ActionForward("viewPortalShipOrderTwoProduct.do?id=" +
                portalShipOrder.getId(), true);
    }
    public ActionForward portalPirntCodeItemReportProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PurchaseOrderInspectionPendingManager poip =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        String string = request.getParameter("str");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        String[] strings = string.split(",");
        List<Box> boxlist = new ArrayList<Box>();
        PurchaseOrderInspectionPendingItem item = null; byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = strings).length, b = 0; b < i; ) { String str = arrayOfString1[b];
            Box box = poip.getBox(Integer.valueOf(Integer.parseInt(str)));
            String batchStr = box.getPsoItem().getPortalShipOrder().getCode();
            String batchNo = batchStr.substring(batchStr.length() - 9, batchStr.length());
            box.setHuCodeOrderNumber(batchNo);
            boxlist.add(box);
            b++; }

        request.setAttribute("x_item", item);
        request.setAttribute("X_RESULTLIST", boxlist);
        request.setAttribute("X_DATE", format.format(new Date()));

        request.setAttribute("path", request.getContextPath());
        return mapping.findForward("page");
    }
    public ActionForward updatePortalPirntCodeItemsProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PurchaseOrderInspectionPendingManager poip =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        PurchaseOrderReceiptsManager pReceiptsManager =
                ServiceLocator.getPurchaseOrderReceiptsManager(request);
        JSONObject object = new JSONObject();
        boolean bol = false;
        BoxManager manager = ServiceLocator.getBoxManager(request);
        String ids = request.getParameter("ids");
        String[] idr = ids.split(";");
        Integer idInteger = null; byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = idr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
            Box box = manager.getBox(Integer.valueOf(Integer.parseInt(id)));
            box.setIsPrint(YesNo.YES);
            manager.updateBox(box);
            idInteger = box.getPsoItem().getPortalShipOrder().getId();
            b++; }

        bol = true;
        PrintWriter print = response.getWriter();
        object.put("str", Boolean.valueOf(bol));
        print.print(object);
        return null;
    }
    public ActionForward newObjectProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("page");
    }
    public ActionForward createPortalShipTwoProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PurchaseOrderInspectionPendingManager poipm =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        PurchaseOrderInspectionPendingQueryForm queryForm = (PurchaseOrderInspectionPendingQueryForm)form;

        PortalShipOrder portalShipOrder = null;

        try {
            String[] poipItemIdsList = request
                    .getParameterValues("poipItemIds");
            String[] deliveryNumberList = request
                    .getParameterValues("deliveryNumbers");
            for (int i = 0; i < poipItemIdsList.length; i++) {
                if (!deliveryNumberList[i].equals("")) {
                    if (portalShipOrder == null) {
                        portalShipOrder = new PortalShipOrder();
                        Site site = getCurrentUser(request).getPrimarySite();
                        User requestor = getCurrentUser(request);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                        String arrivalDate = queryForm.getReceivingDate();
                        Date arrDate = null;
                        if (arrivalDate != null && arrivalDate.trim().length() != 0) {
                            arrDate = sdf.parse(arrivalDate);
                        }
                        portalShipOrder.setType(Integer.valueOf(3));
                        portalShipOrderManager.insertPortalShipOrder(
                                portalShipOrder, site, requestor, arrDate);
                    }

                    String poipItem = poipItemIdsList[i];
                    BigDecimal deliveryNumber = new BigDecimal(
                            deliveryNumberList[i]);

                    PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem1 = poipm
                            .getPurchaseOrderInspectionPendingItem(
                                    Integer.valueOf(Integer.parseInt(poipItem)));

                    BigDecimal wBigDecimal = purchaseOrderInspectionPendingItem1
                            .getQtyOpen();
                    purchaseOrderInspectionPendingItem1.setQtyOpen(wBigDecimal
                            .subtract(deliveryNumber));
                    poipm.updatePurchaseOrderInspectionPendingItem3(purchaseOrderInspectionPendingItem1);

                    Boolean isClose = poipm
                            .isclosePurchaseOrderInspectionPendingByItem(purchaseOrderInspectionPendingItem1
                                    .getPoip_number());
                    if (isClose.booleanValue()) {
                        purchaseOrderInspectionPendingItem1.getPoip_number()
                                .setStatus(PurchaseOrderStatus.WAIT);
                        poipm.updatePurchaseOrderInspectionPending(purchaseOrderInspectionPendingItem1
                                .getPoip_number());
                    }

                    PortalShipOrderItem portalShipOrderItem = new PortalShipOrderItem();

                    portalShipOrderItem.setPortalShipOrder(portalShipOrder);
                    portalShipOrderItem
                            .setPoipItem(purchaseOrderInspectionPendingItem1);
                    portalShipOrderItem.setDeliveryNumber(deliveryNumber);
                    portalShipOrderItem
                            .setQty_std(purchaseOrderInspectionPendingItem1
                                    .getQty_std());
                    portalShipOrderManager
                            .insertPortalShipOrderItem(portalShipOrderItem);
                }
            }

            PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem = poipm
                    .getPurchaseOrderInspectionPendingItem(
                            Integer.valueOf(Integer.parseInt(poipItemIdsList[0])));
            if (!"T".equals(purchaseOrderInspectionPendingItem.getVd_promo())) {
                portalShipOrderManager.createPortalShipOrderIP(portalShipOrder);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return new ActionForward("/viewPortalShipOrderTwoProduct.do?id=" +
                portalShipOrder.getId(), true);
    }

    public ActionForward purchaseItemTwoProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PurchaseOrderInspectionPendingItemQueryForm queryForm = (PurchaseOrderInspectionPendingItemQueryForm)form;
        PurchaseOrderInspectionPendingManager fm =
                ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
        ProjectedInventoryManager projectedInventoryManager =
                ServiceLocator.getProjectedInventoryManager(request);
        String idString = queryForm.getSelectPoipItemId();
        String[] ids = idString.split(";");
        Map conditions = new HashMap();

        conditions.put(PurchaseOrderInspectionPendingQueryCondition.qtyOpen_DT,
                Integer.valueOf(0));
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
        String poNumber = queryForm.getPoip_number();
        if (poNumber != null && !poNumber.equals("")) {
            conditions.put(
                    PurchaseOrderInspectionPendingQueryCondition.PONUMBER_EQ,
                    poNumber);
        }

        String itemNumber = queryForm.getItemNumber();
        if (itemNumber != null && !itemNumber.equals("")) {
            conditions.put(
                    PurchaseOrderInspectionPendingQueryCondition.ITEMNUMBER_EQ,
                    itemNumber);
        }

        String endPoDate = queryForm.getDueDate();
        if (endPoDate != null && !endPoDate.equals("")) {
            conditions.put(
                    PurchaseOrderInspectionPendingQueryCondition.DUEDATE_EQ,
                    endPoDate);
        }
        String status = queryForm.getStatus();
        if (status != null && !status.equals("")) {
            conditions.put(
                    PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ,
                    status);
        } else {
            conditions.put(
                    PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ, Integer.valueOf(1));
        }
        SupplierManager manager = ServiceLocator.getSupplierManager(request);
        User user = getCurrentUser(request);
        Supplier supplier = manager.getSupplierByCode(user.getLoginName());
        if (getCurrentUser(request).getPrimarySite() != null &&
                supplier != null) {

            conditions
                    .put(PurchaseOrderInspectionPendingQueryCondition.SUPPLIER_ID_EQ,
                            supplier.getId());
        } else {
            conditions
                    .put(PurchaseOrderInspectionPendingQueryCondition.SUPPLIER_ID_EQ,
                            Integer.valueOf(0));
        }
        conditions.put(
                PurchaseOrderInspectionPendingQueryCondition.CREATETYPE_EQ, Integer.valueOf(1));
        if (queryForm.isFirstInit()) {
            queryForm
                    .init(fm.getPurchaseOrderInspectionPendingItemListCount(conditions));
        } else {
            queryForm.init();
        }
        List<PurchaseOrderInspectionPendingItem> result = fm
                .getPurchaseOrderInspectionPendingItemList(conditions,
                        queryForm.getPageNoAsInt(),
                        queryForm.getPageSizeAsInt(),
                        PurchaseOrderInspectionPendingQueryOrder.ID,
                        queryForm.isDescend());
        if (ids.length > 0) {
            for (PurchaseOrderInspectionPendingItem pendingItem : result) {
                Integer itemId = pendingItem.getId();
                Boolean isClose = Boolean.valueOf(false);

                for (int i = 0; i < ids.length; i++) {
                    if (!ids[i].equals("") &&
                            itemId.intValue() == Integer.parseInt(ids[i])) {
                        isClose = Boolean.valueOf(true);
                    }
                }

                if (isClose.booleanValue()) {
                    pendingItem.setChecked("checked");
                }
            }
        }
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("X_YESNOLIST",
                PersistentEnum.getEnumList(YesNo.class));
        request.setAttribute("X_WmsPurchaseOrderStatusLIST",
                PersistentEnum.getEnumList(PurchaseOrderStatus.class));
        return mapping.findForward("page");
    }
    public ActionForward createPortalShipJitPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SupplierManager supplierManager =
                ServiceLocator.getSupplierManager(request);
        WmsPartManager wmsPartManager =
                ServiceLocator.getWmsPartManager(request);
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        PortalShipOrderItemManager portalShipOrderItemManager =
                ServiceLocator.getPortalShipOrderItemManager(request);
        User user = getCurrentUser(request);
        Supplier supplier = supplierManager.getSupplierByCode(user
                .getLoginName());
        PortalShipOrder portalShipOrder = null;
        if (supplier != null) {
            try {
                String[] partIdsList = request.getParameterValues("ids");
                String[] deliveryNumberList = request
                        .getParameterValues("deliveryNumbers");
                String arrivalDate = request.getParameter("receivingDate");
                String createType = request.getParameter("createType");
                for (int i = 0; i < partIdsList.length; i++) {
                    if (!deliveryNumberList[i].equals("")) {
                        if (portalShipOrder == null) {
                            portalShipOrder = new PortalShipOrder();
                            Site site = getCurrentUser(request)
                                    .getPrimarySite();
                            User requestor = getCurrentUser(request);
                            portalShipOrder.setCreateType(createType);
                            portalShipOrder.setSupplier(supplier);
                            portalShipOrder.setCreateDate(new Date());
                            portalShipOrder.setIsPrint(YesNo.NO);
                            portalShipOrder.setType(Integer.valueOf(3));
                            portalShipOrder.setArrivalDate(new Date(arrivalDate));
                            portalShipOrderManager.insertPortalShipOrderByJitPart(
                                    portalShipOrder, site, requestor, new Date());
                        }
                        String partId = partIdsList[i];
                        BigDecimal deliveryNumber = new BigDecimal(
                                deliveryNumberList[i]);
                        WmsPart part = wmsPartManager.getWmsPart(partId);

                        PortalShipOrderItem portalShipOrderItem = new PortalShipOrderItem();
                        portalShipOrderItem.setPortalShipOrder(portalShipOrder);
                        portalShipOrderItem.setDeliveryNumber(deliveryNumber);
                        portalShipOrderItem.setPart(part);
                        portalShipOrderManager
                                .insertPortalShipOrderItem(portalShipOrderItem);
                    }
                }  portalShipOrderManager
                        .createPortalShipOrderByJitPartIP(portalShipOrder);
            } catch (Exception e) {
                e.fillInStackTrace();
            }
            return new ActionForward("/viewPortalShipOrderTwoProduct.do?id=" +
                    portalShipOrder.getId(), true);
        }
        return new ActionForward("/listShippingOrderProduct.do", true);
    }
    public ActionForward createPortalShipOrderTwoWrongByPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsPartManager wmsPartManager =
                ServiceLocator.getWmsPartManager(request);
        PortalShipOrderManager portalShipOrderManager =
                ServiceLocator.getPortalShipOrderManager(request);
        PortalShipOrderItemManager portalShipOrderItemManager =
                ServiceLocator.getPortalShipOrderItemManager(request);
        SupplierManager supplierManager =
                ServiceLocator.getSupplierManager(request);
        SiteManager siteManager = ServiceLocator.getSiteManager(request);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String supplierCode = request.getParameter("supplierCode");
        Supplier supplier = supplierManager.getSupplierByCode(supplierCode);
        Map<Object, Object> mapSite = new HashMap<Object, Object>();
        mapSite.put(SiteQueryCondition.NAME_EQ, supplierCode);
        List<Site> siteList = siteManager.getSiteList(mapSite, 0, -1, null,
                false);
        PortalShipOrder portalShipOrder = null;
        if (supplier != null) {
            try {
                Site site = siteList.get(0);
                String[] partIdsList = request.getParameterValues("ids");
                String[] deliveryNumberList = request
                        .getParameterValues("deliveryNumbers");
                String createType = request.getParameter("createType");
                String arrivalDate = request.getParameter("receivingDate");
                for (int i = 0; i < partIdsList.length; i++) {
                    if (!deliveryNumberList[i].equals("")) {

                        if (portalShipOrder == null) {
                            portalShipOrder = new PortalShipOrder();

                            User requestor = getCurrentUser(request);
                            portalShipOrder.setCreateType(createType);
                            portalShipOrder.setSupplier(supplier);
                            portalShipOrder.setCreateDate(new Date());
                            portalShipOrder.setArrivalDate(format
                                    .parse(arrivalDate));
                            portalShipOrder.setIsPrint(YesNo.NO);
                            portalShipOrder.setType(Integer.valueOf(2));
                            portalShipOrderManager
                                    .insertPortalShipOrderByNpoPartSupplier(
                                            portalShipOrder, site, supplier,
                                            new Date());
                        }
                        String partId = partIdsList[i];
                        String a = deliveryNumberList[i].replace(",", "");
                        BigDecimal deliveryNumber = new BigDecimal(
                                a);
                        WmsPart part = wmsPartManager.getWmsPart(partId);
                        PortalShipOrderItem portalShipOrderItem = new PortalShipOrderItem();
                        portalShipOrderItem.setPortalShipOrder(portalShipOrder);
                        portalShipOrderItem.setDeliveryNumber(deliveryNumber);
                        portalShipOrderItem.setPart(part);
                        portalShipOrderManager
                                .insertPortalShipOrderItem(portalShipOrderItem);
                    }
                }  portalShipOrderManager
                        .createPortalShipOrderByNpoPartIP(portalShipOrder);
            } catch (Exception e) {
                e.fillInStackTrace();
            }
            return new ActionForward("/viewPortalShipOrderTwoWrong.do?id=" +
                    portalShipOrder.getId(), true);
        }
        return new ActionForward("/listShippingOrderWrong.do", true);
    }

    public ActionForward updateJitCloseIsEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        try {
            PortalShipOrderManager shipOrderManager =
                    ServiceLocator.getPortalShipOrderManager(request);
            PortalShipOrder shipOrder = shipOrderManager
                    .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
            shipOrder.setEnabled(EnabledDisabled.DISABLED);
            shipOrderManager.updatePortalShipOrder(shipOrder);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return new ActionForward("/viewPortalShipOrderTwoProduct.do?id=" + id,
                true);
    }

    public ActionForward updateJitOpenIsEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        try {
            PortalShipOrderManager shipOrderManager =
                    ServiceLocator.getPortalShipOrderManager(request);
            PortalShipOrder shipOrder = shipOrderManager
                    .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
            shipOrder.setEnabled(EnabledDisabled.ENABLED);
            shipOrderManager.updatePortalShipOrder(shipOrder);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return new ActionForward("/viewPortalShipOrderTwoProduct.do?id=" + id,
                true);
    }

    public ActionForward updatePoCloseIsEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean bol = false;
        JSONObject object = new JSONObject();
        String id = request.getParameter("id");
        try {
            PortalShipOrderManager shipOrderManager =
                    ServiceLocator.getPortalShipOrderManager(request);
            PortalShipOrder shipOrder = shipOrderManager
                    .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
            shipOrder.setEnabled(EnabledDisabled.DISABLED);
            shipOrderManager.updatePortalShipOrder(shipOrder);
            bol = true;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return new ActionForward("/viewPortalShipOrderTwo.do?id=" + id, true);
    }

    public ActionForward updatePoOpenIsEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        try {
            PortalShipOrderManager shipOrderManager =
                    ServiceLocator.getPortalShipOrderManager(request);
            PortalShipOrder shipOrder = shipOrderManager
                    .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
            shipOrder.setEnabled(EnabledDisabled.ENABLED);
            shipOrderManager.updatePortalShipOrder(shipOrder);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return new ActionForward("/viewPortalShipOrderTwo.do?id=" + id, true);
    }

    public ActionForward updateNPoCloseIsEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        try {
            PortalShipOrderManager shipOrderManager =
                    ServiceLocator.getPortalShipOrderManager(request);
            PortalShipOrder shipOrder = shipOrderManager
                    .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
            shipOrder.setEnabled(EnabledDisabled.DISABLED);
            shipOrderManager.updatePortalShipOrder(shipOrder);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return new ActionForward("/viewPortalShipOrderTwoWrong.do?id=" + id +
                "&GrantedSite=1", true);
    }

    public ActionForward updateNPoOpenIsEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        try {
            PortalShipOrderManager shipOrderManager =
                    ServiceLocator.getPortalShipOrderManager(request);
            PortalShipOrder shipOrder = shipOrderManager
                    .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
            shipOrder.setEnabled(EnabledDisabled.ENABLED);
            shipOrderManager.updatePortalShipOrder(shipOrder);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return new ActionForward("/viewPortalShipOrderTwoWrong.do?id=" + id +
                "&GrantedSite=1", true);
    }

    public ActionForward deleteDeliveryPo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        PortalShipOrderManager manager =
                ServiceLocator.getPortalShipOrderManager(request);

        String id = request.getParameter("id");
        manager.deleteDeliveryPo(id);

        return new ActionForward("/listShippingOrder.do", true);
    }
    public ActionForward deleteDeliveryNPo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        PortalShipOrderManager manager =
                ServiceLocator.getPortalShipOrderManager(request);

        String id = request.getParameter("id");
        manager.deleteDeliveryNPo(id);
        return new ActionForward("/listShippingOrderWrong.do", true);
    }
    public ActionForward deleteDeliveryJit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        PortalShipOrderManager manager =
                ServiceLocator.getPortalShipOrderManager(request);

        String id = request.getParameter("id");
        manager.deleteDeliveryJit(id);
        return new ActionForward("/listShippingOrderProduct.do", true);
    }
    public ActionForward shippingOrderReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BasicDataViewQueryForm queryForm = (BasicDataViewQueryForm)form;
        BasicDataViewManager manager = ServiceLocator.getBasicDataViewManager(request);
        String idString = request.getParameter("portalShipJitPartId");
        if (StringUtils.isEmpty(queryForm.getOrder())) {

            queryForm.setOrder(JitShipPartQueryOrder.highQty.getName());
            queryForm.setOrder(JitShipPartQueryOrder.lowQty.getName());
            queryForm.setOrder(JitShipPartQueryOrder.securityQty.getName());
            queryForm.setOrder(JitShipPartQueryOrder.currentQty.getName());
            queryForm.setDescend(false);
        }
        Map conditions = new HashMap();
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
        if (queryForm.getHeight() != null && queryForm.getHeight() != "") {
            conditions.put("a", queryForm.getHeight());
        }
        if (queryForm.getLow() != null && queryForm.getLow() != "") {
            conditions.put("b", queryForm.getLow());
        }
        if (queryForm.getEq() != null && queryForm.getEq() != "") {
            conditions.put("e", queryForm.getEq());
        }
        SupplierManager managera = ServiceLocator.getSupplierManager(request);
        User user = getCurrentUser(request);
        Supplier supplier = managera.getSupplierByCode(user.getLoginName());
        if (queryForm.isFirstInit()) {
            queryForm.init(manager.getJitShipPartListCount(new HashMap<Object, Object>()));
        } else {
            queryForm.init();
        }
        int a = Integer.parseInt(queryForm.getPageNo());
        int b = Integer.parseInt(queryForm.getPageSize());
        List<JitShipPart> result = manager.getJitShipPartNumberList(conditions, Integer.parseInt(queryForm.getPageNo()), Integer.parseInt(queryForm.getPageSize()),
                JitShipPartQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());

        String exportType = queryForm.getExportType();
        if (exportType != null && exportType.length() > 0) {
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "PortalShipOrder";
            String suffix = ExportUtil.export(
                    exportType,
                    result,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources messages = PortalShipOrderActiony.this.getResources(request);
                            row.add(
                                    "物料 ");
                            row.add(
                                    "描述");
                            row.add("单位");
                            row.add("属性");
                            row.add("售后件/量产件");
                            row.add("车型 ");
                            row.add("供应商");
                            row.add("描述");
                            row.add("当前库存");
                            row.add("高储库存 ");
                            row.add("低储库存  ");
                            row.add("安全库存 ");
                        }
                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanUtils.getProperty(data, "part.id"));
                            row.add(
                                    BeanUtils.getProperty(data, "part.name"));
                            row.add(
                                    BeanUtils.getProperty(data, "part.unit"));

                            row.add("");
                            row.add(BeanUtils.getProperty(data, "part.drwgLoc"));
                            row.add(BeanUtils.getProperty(data, "part.productSpecifications"));

                            row.add(BeanUtils.getProperty(data, "part.vend"));
                            row.add("");
                            row.add(BeanUtils.getProperty(data, "currentQty"));
                            row.add(BeanUtils.getProperty(data, "part.highQty"));
                            row.add(BeanUtils.getProperty(data, "part.lowQty"));
                            row.add(BeanUtils.getProperty(data, "part.securityQty"));
                        }
                    });
            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }

        for (JitShipPart ship : result) {
            if (ship.getQty().compareTo(new BigDecimal(0)) == -1) {
                ship.setQty(new BigDecimal(0));
            }
        }

        request.setAttribute("portalShipJitPartId", idString);
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(184));
        return mapping.findForward("page");
    }

    public ActionForward getQrCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String msg = request.getParameter("msg");

        return new ActionForward("page");
    }
}
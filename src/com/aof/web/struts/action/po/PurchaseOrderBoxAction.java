package com.aof.web.struts.action.po;

import com.aof.model.admin.Site;
import com.aof.model.basic.BadReasons;
import com.aof.model.basic.StorageLocation;
import com.aof.model.basic.WmsPart;
import com.aof.model.basic.query.StorageLocationQueryCondition;
import com.aof.model.inventory.InventoryMoving;
import com.aof.model.metadata.BoxStatus;
import com.aof.model.metadata.BoxStatusRqc;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.model.metadata.StoreRoomType;
import com.aof.model.metadata.YesNo;
import com.aof.model.po.Box;
import com.aof.model.po.PurchaseOrderRqcUnqualified;
import com.aof.model.po.query.BoxQueryCondition;
import com.aof.model.po.query.BoxQueryOrder;
import com.aof.model.query.BasicConditionModel;
import com.aof.model.query.BasicQueryCondition;
import com.aof.service.admin.InventoryMovingManager;
import com.aof.service.admin.SupplierManager;
import com.aof.service.basic.StorageLocationManager;
import com.aof.service.basic.WmsToolManager;
import com.aof.service.inventory.InventoryManager;
import com.aof.service.po.BoxManager;
import com.aof.service.po.ProduceRejectedMaterialManager;
import com.aof.service.po.PurchaseOrderPutInStorageManager;
import com.aof.service.po.PurchaseOrderRQCManager;
import com.aof.service.po.PurchaseOrderReceiptsManager;
import com.aof.utils.SessionTempFile;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.action.product.ProductGolineAction;
import com.aof.web.struts.form.BaseSessionQueryForm;
import com.aof.web.struts.form.po.BoxQueryForm;
import com.shcnc.hibernate.PersistentEnum;
import com.shcnc.struts.action.ActionException;
import com.shcnc.struts.form.BeanForm;
import com.shcnc.utils.BeanHelper;
import com.shcnc.utils.BeanUtils;
import com.shcnc.utils.ExportUtil;
import com.shcnc.utils.Exportable;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

public class PurchaseOrderBoxAction
        extends BaseAction2
{
    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);

        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward listInventoryReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(BoxQueryCondition.STATUS_EQ, Integer.valueOf(4));
        conditions.put(BoxQueryCondition.ISENABLED_EQ, Integer.valueOf(1));
        conditions.put(BoxQueryCondition.NUMBER_GT, Integer.valueOf(0));
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = boxManager.getBoxList(conditions, 0, -1,
                    BoxQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());

            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "listInventoryReport";
            String suffix = ExportUtil.export(
                    exportType,
                    data,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception
                        {
                            MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.lot.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.part.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.part.describe1"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.po_supp"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.po_supp_name"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "DPI"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.location.code"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.in_date"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.status_rqc"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.status_freeze"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.status.chnShortDescription"));
                        }



                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "lot.id"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.id"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.describe1"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "po_supp"));
                            Object suppname = BeanHelper.getBeanPropertyValue(data,
                                    "po_supp_name");
                            if (suppname == null) {
                                row.add("");
                            } else {
                                row.add(suppname);
                            }
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.dpiNo"));

                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "number"));
                            Object locationCode =
                                    BeanHelper.getBeanPropertyValue(data, "location.code");
                            if (locationCode == null) {
                                row.add("");
                            } else {
                                row.add(locationCode);
                            }

                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "in_date"));
                            String statusrqc = BeanHelper.getBeanPropertyValue(
                                    data, "status_rqc").toString();
                            String statusshort =
                                    BeanHelper.getBeanPropertyValue(data,
                                                    "status_rqc.chnShortDescription")
                                            .toString();
                            if (statusrqc == null || statusrqc.equals("")) {
                                row.add("待检");
                            } else {
                                row.add(statusshort);
                            }
                            String statusfreeze =
                                    BeanHelper.getBeanPropertyValue(data, "status_freeze")
                                            .toString();
                            if (statusfreeze.equals("0")) {
                                row.add("已冻结");
                            } else {
                                row.add("未冻结");
                            }

                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "status.chnShortDescription"));
                        }
                    });

            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());
        request.setAttribute("x_selType", Integer.valueOf(106));
        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward listMoveLibrary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(BoxQueryCondition.STA_GT, Integer.valueOf(2));
        conditions.put(BoxQueryCondition.ISENABLED_NOT_EQ, EnabledDisabled.ENABLED);
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = boxManager.getBoxList(conditions, 0, -1,
                    BoxQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());

            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "containerType";
            String suffix = ExportUtil.export(
                    exportType,
                    data,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception
                        {
                            MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.lot.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.part.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.part.describe1"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "DPI"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.po_supp"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.po_supp_name"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.location.code"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.part.unit"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.in_date"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.status_rqc"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.status_freeze"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.status.chnShortDescription"));
                        }



                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "lot.id"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.id"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.describe1"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.dpiNo"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "po_supp"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "po_supp_name"));
                            String locationCode =
                                    BeanHelper.getBeanPropertyValue(data, "location.code")
                                            .toString();
                            row.add("'" + locationCode);
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "number"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.unit"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "in_date"));
                            String statusrqc = BeanHelper.getBeanPropertyValue(
                                    data, "status_rqc").toString();
                            String statusshort =
                                    BeanHelper.getBeanPropertyValue(data,
                                                    "status_rqc.chnShortDescription")
                                            .toString();
                            if (statusrqc == null || statusrqc.equals("")) {
                                row.add("待检");
                            } else {
                                row.add(statusshort);
                            }
                            String statusfreeze =
                                    BeanHelper.getBeanPropertyValue(data, "status_freeze")
                                            .toString();
                            if (statusfreeze.equals("0")) {
                                row.add("已冻结");
                            } else {
                                row.add("未冻结");
                            }

                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "status.chnShortDescription"));
                        }
                    });

            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(23));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward newMoveLibrary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String array = request.getParameter("array");

        request.setAttribute("x_array", array);
        return mapping.findForward("page");
    }


    public ActionForward insertMoveLibrary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxManager manager = ServiceLocator.getBoxManager(request);
        String array = request.getParameter("array");
        String location = request.getParameter("location");
        String str = "";
        String resu = manager.systemMoveLocation(array, location,
                getCurrentUser(request));

        if (!resu.equals("ok")) {
            str = String.valueOf(str) + resu + "    ";
        }
        if (!str.equals(""))
        {
            request.setAttribute("x_mfg", str);
        }
        request.setAttribute("x_array", array);
        return mapping.findForward("success");
    }


    public ActionForward insertDefeScanningQua(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxManager manager = ServiceLocator.getBoxManager(request);
        StorageLocationManager storageManager = ServiceLocator.getStorageLocationManager(request);
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        String array = request.getParameter("array");
        String location = request.getParameter("location");
        String[] lot = array.split(","); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = lot).length, b = 0; b < i; ) { String string = arrayOfString1[b];
            conditions.clear();
            conditions.put(BoxQueryCondition.LOTSER_EQ, string);
            List<Box> list = manager.getBoxList(conditions, 0, -1, null, false);
            Box box = list.get(0);
            box.setStatus_rqc(BoxStatusRqc.QUALIFIED);
            box.setStatus_freeze(YesNo.NO);

            manager.updateBox(box); b++; }

        request.setAttribute("x_array", array);
        return new ActionForward("listPurchaseOrderRQCUnqualified.do", true);
    }



    public ActionForward insertDefeScanningNOtQua(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxManager manager = ServiceLocator.getBoxManager(request);
        StorageLocationManager storageManager = ServiceLocator.getStorageLocationManager(request);
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        String array = request.getParameter("array");
        String location = request.getParameter("location");
        String[] lot = array.split(","); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = lot).length, b = 0; b < i; ) { String string = arrayOfString1[b];
            conditions.clear();
            conditions.put(BoxQueryCondition.LOTSER_EQ, string);
            List<Box> list = manager.getBoxList(conditions, 0, -1, null, false);
            Box box = list.get(0);
            box.setStatus_rqc(BoxStatusRqc.UNQUALIFIED);
            box.setStatus_freeze(YesNo.YES);

            manager.updateBox(box); b++; }

        request.setAttribute("x_array", array);
        return new ActionForward("listPurchaseOrderRQCUnqualified.do", true);
    }


    public ActionForward insertLFLibrary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxManager manager = ServiceLocator.getBoxManager(request);
        StorageLocationManager storageManager = ServiceLocator.getStorageLocationManager(request);
        InventoryManager inventoryManager = ServiceLocator.getInventoryManager(request);
        InventoryMovingManager inventoryMovingManager = ServiceLocator.getInventoryMovingManager(request);
        ProductGolineAction action = new ProductGolineAction();
        InventoryMoving moving = new InventoryMoving();
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        Map<Object, Object> map = new HashMap<Object, Object>();
        String array = request.getParameter("array");
        String location = request.getParameter("location");
        String[] lot = array.split(",");
        conditions.put(BoxQueryCondition.LOTSER_EQ, lot[0]);
        List<Box> list = manager.getBoxList(conditions, 0, -1, null, false);
        Box box = list.get(0);
        StorageLocation loca = box.getLocation();
        WmsPart part = box.getPart();
        String big = box.getNumber().toString();
        int ibig = big.indexOf(".");
        String strbig = big.substring(0, ibig);
        int b = Integer.parseInt(strbig);
        int a = -b;
        int c = b;
        BigDecimal dec = new BigDecimal(c);
        BigDecimal decJian = new BigDecimal(a);


        map.put(StorageLocationQueryCondition.STOREROOM_ID_EQ, "CK006");
        List<StorageLocation> storagelist = storageManager.getStorageLocationList(map, 0, -1, null, false);
        StorageLocation storage = storagelist.get(0);
        box.setLocation(storage);
        box.setStatus_freeze(YesNo.NO);
        manager.updateBox(box);
        moving.setOld_location(loca);
        moving.setPart(part);
        moving.setNew_location(box.getLocation());
        moving.setQty(box.getNumber());
        moving.setDate(new Date());
        moving.setIs_sync(YesNo.NO);
        moving.setRemark("不良品扫描：" + loca.getCode() + "-" + box.getLocation().getCode() + ",成品号：" + part.getId());
        inventoryMovingManager.insertInventoryMoving(moving);
        ProductGolineAction actiongoline = new ProductGolineAction();
        actiongoline.insertInventoryDetial(part, loca, inventoryManager, decJian);
        actiongoline.insertInventoryDetial(part, box.getLocation(), inventoryManager, dec);
        request.setAttribute("x_array", array);
        return mapping.findForward("success");
    }



    public ActionForward selectBox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);

        getConditionAndOrder(queryForm, conditions, request);
        String toGoOutId = request.getParameter("toGoOutId");
        if (toGoOutId != null && toGoOutId.trim().length() > 0) {
            request.setAttribute("toGoOutId", toGoOutId);
            conditions.put(BoxQueryCondition.PART_ID_IN, toGoOutId);
            conditions.put(BoxQueryCondition.STATUS_EQ, BoxStatus.HASBEENINTO);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_close", Boolean.valueOf(true));
        request.setAttribute("x_selType", Integer.valueOf(79));
        return mapping.findForward("page");
    }



    public ActionForward productOutbound(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);

        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward listProduceRejectedMaterialBoxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);

        conditions.put(BoxQueryCondition.STATUS_EQ, Integer.valueOf(2));
        conditions.put(BoxQueryCondition.LOCATION_STORE_ROOM_TYPE_EQ, Integer.valueOf(14));

        getConditionAndOrder(queryForm, conditions, request);

        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }

        String exportType = queryForm.getExportType();
        if (exportType != null && exportType.length() > 0) {
            List data = boxManager.getBoxList(conditions, 0, -1, BoxQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "box";
            String suffix = ExportUtil.export(exportType, data, request,
                    new FileOutputStream(SessionTempFile.getTempFile(index, request)),
                    new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.row"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.code"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "DPI"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.part.describe1"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.po_supp"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.po_supp_name"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.location.code"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.num"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "wmspart.pc"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "containerType.date"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_rqc"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_freeze"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status.chnShortDescription"));
                        }


                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanUtils.getProperty(data, "lot.id"));
                            row.add(BeanUtils.getProperty(data, "po_number"));
                            row.add(BeanUtils.getProperty(data, "po_line"));
                            row.add(BeanUtils.getProperty(data, "part.id"));
                            row.add(BeanUtils.getProperty(data, "part.dpiNo"));
                            row.add(BeanUtils.getProperty(data, "part.describe1"));
                            row.add(BeanUtils.getProperty(data, "po_supp"));
                            row.add(BeanUtils.getProperty(data, "po_supp_name"));
                            row.add(BeanUtils.getProperty(data, "location.code"));
                            row.add(BeanUtils.getProperty(data, "number"));
                            row.add(BeanUtils.getProperty(data, "part.unit"));
                            row.add(BeanUtils.getProperty(data, "po_date"));
                            if (BeanUtils.getProperty(data, "status_rqc") != null) {
                                row.add(BeanUtils.getProperty(data, "status_rqc.chnShortDescription"));
                            } else {
                                row.add("未质检");
                            }
                            if (BeanUtils.getProperty(data, "status_freeze").equals("0")) {
                                row.add("已冻结");
                            } else {
                                row.add("未冻结");
                            }
                            row.add(BeanUtils.getProperty(data, "status.chnShortDescription"));
                        }
                    });

            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }

        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("boxlist", result);
        request.setAttribute("x_selType", Integer.valueOf(18));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward purchaseOrderReceiptsBoxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(BoxQueryCondition.STATUS_EQ, Integer.valueOf(1));
        conditions.put(BoxQueryCondition.ISENABLED_NOT_EQ, EnabledDisabled.ENABLED);
        getConditionAndOrder(queryForm, conditions, request);


        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = boxManager.getBoxList(conditions, 0, -1,
                    BoxQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "purchaseOrderPutIn";
            String suffix = ExportUtil.export(
                    exportType,
                    data,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception
                        {
                            MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.row"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.part.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.code"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "DPI"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.po_supp"));



                            row.add(messages
                                    .getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                            "purchaseOrderPutInStorageBoxList.part.describe1"));

                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.num"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.po_date"));
                            row.add(messages
                                    .getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                            "purchaseOrderPutInStorageBoxList.chnShortDescription"));
                            row.add(messages
                                    .getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                            "purchaseOrderPutInStorageBoxList.status_freeze"));
                            row.add(messages
                                    .getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                            "Box.status.chnShortDescription"));
                        }


                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "lot.id"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "po_number"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "po_line"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.id"));
                            Object object1 = BeanHelper.getBeanPropertyValue(
                                    data, "psoItem.portalShipOrder.code");
                            Object object2 = BeanHelper.getBeanPropertyValue(
                                    data, "single.code");
                            if (object1 != null) {
                                row.add(object1);
                            }
                            if (object2 != null) {
                                row.add(object2);
                            }
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.dpiNo"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "po_supp"));


                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.describe1"));


                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "number"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "po_date"));
                            Object ob1 = BeanHelper.getBeanPropertyValue(data,
                                    "status_rqc");
                            Object ob2 = BeanHelper.getBeanPropertyValue(data,
                                    "status_rqc.chnShortDescription");
                            if (ob1 == null) {
                                row.add("未质检");
                            }
                            if (ob2 != null) {
                                row.add(ob2);
                            }
                            Object ob3 = BeanHelper.getBeanPropertyValue(data,
                                    "status_freeze");
                            if (ob3 == YesNo.YES) {
                                row.add("已冻结");
                            }
                            if (ob3 == YesNo.NO) {
                                row.add("未冻结");
                            }
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "status.chnShortDescription"));
                        }
                    });
            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }



        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }
        int pageSize = queryForm.getPageSizeAsInt();
        int pageNo = queryForm.getPageNoAsInt();
        List result = boxManager.getBoxList(conditions, pageNo, pageSize,
                BoxQueryOrder.ID, queryForm.isDescend());
        request.setAttribute("boxlist", result);
        request.setAttribute("x_selType", Integer.valueOf(2));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }


    public ActionForward poRqcBoxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(BoxQueryCondition.ISENABLED_EQ, Integer.valueOf(1));
        conditions.put(BoxQueryCondition.STATUS_NOT_EQ, Integer.valueOf(1));
        conditions.put(BoxQueryCondition.STATUS_RQC_EQ, Integer.valueOf(1));

        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);

        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }

        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = boxManager.getBoxList(conditions, 0, -1,
                    BoxQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "purchaseOrderPutIn";
            String suffix = ExportUtil.export(
                    exportType,
                    data,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception
                        {
                            MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.row"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.part.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.code"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "DPI"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.po_supp"));


                            row.add(messages
                                    .getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                            "purchaseOrderPutInStorageBoxList.part.describe1"));
                            row.add(messages
                                    .getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                            "purchaseOrderPutInStorageBoxList.location.code"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.num"));
                            row.add(messages
                                    .getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                            "Box.part.unit"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.po_date"));
                            row.add(messages
                                    .getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                            "purchaseOrderPutInStorageBoxList.chnShortDescription"));
                            row.add(messages
                                    .getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                            "purchaseOrderPutInStorageBoxList.status_freeze"));
                            row.add(messages
                                    .getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                            "status.chnShortDescription"));
                        }


                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "lot.id"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "po_number"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "po_line"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.id"));
                            Object object1 = BeanHelper.getBeanPropertyValue(
                                    data, "psoItem.portalShipOrder.code");
                            Object object2 = BeanHelper.getBeanPropertyValue(
                                    data, "single.code");
                            if (object1 != null) {
                                row.add(object1);
                            }
                            if (object2 != null) {
                                row.add(object2);
                            }
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.dpiNo"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "po_supp"));

                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.describe1"));

                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "location.code"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "number"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.unit"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "po_date"));
                            Object ob1 = BeanHelper.getBeanPropertyValue(data,
                                    "status_rqc");
                            Object ob2 = BeanHelper.getBeanPropertyValue(data,
                                    "status_rqc.chnShortDescription");
                            if (ob1 == null) {
                                row.add("未质检");
                            }
                            if (ob2 != null) {
                                row.add(ob2);
                            }
                            Object ob3 = BeanHelper.getBeanPropertyValue(data,
                                    "status_freeze");
                            if (ob3 == YesNo.YES) {
                                row.add("已冻结");
                            }
                            if (ob3 == YesNo.NO) {
                                row.add("未冻结");
                            }
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "status.chnShortDescription"));
                        }
                    });
            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("boxlist", result);
        request.setAttribute("x_selType", Integer.valueOf(25));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }


    public ActionForward poPutInStorageBoxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(BoxQueryCondition.STATUS_EQ, Integer.valueOf(2));
        conditions.put(BoxQueryCondition.STATUS_RQC_NOT_EQ, Integer.valueOf(3));
        conditions.put(BoxQueryCondition.ISENABLED_NOT_EQ, EnabledDisabled.ENABLED);
        getConditionAndOrder(queryForm, conditions, request);


        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = boxManager.getBoxList(conditions, 0, -1,
                    BoxQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "purchaseOrderPutIn";
            String suffix = ExportUtil.export(
                    exportType,
                    data,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception
                        {
                            MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.row"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.part.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.code"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "DPI"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.po_supp"));

                            row.add(messages
                                    .getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                            "purchaseOrderPutInStorageBoxList.po_supp_name"));
                            row.add(messages
                                    .getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                            "purchaseOrderPutInStorageBoxList.part.describe1"));
                            row.add(messages
                                    .getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                            "purchaseOrderPutInStorageBoxList.location.code"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.num"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "purchaseOrderPutInStorageBoxList.po_date"));
                            row.add(messages
                                    .getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                            "purchaseOrderPutInStorageBoxList.chnShortDescription"));
                            row.add(messages
                                    .getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                            "purchaseOrderPutInStorageBoxList.status_freeze"));
                        }


                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "lot.id"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "po_number"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "po_line"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.id"));
                            Object object1 = BeanHelper.getBeanPropertyValue(
                                    data, "psoItem.portalShipOrder.code");
                            Object object2 = BeanHelper.getBeanPropertyValue(
                                    data, "single.code");
                            if (object1 != null) {
                                row.add(object1);
                            }
                            if (object2 != null) {
                                row.add(object2);
                            }
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.dpiNo"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "po_supp"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "po_supp_name"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.describe1"));

                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "location.code"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "number"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "po_date"));
                            Object ob1 = BeanHelper.getBeanPropertyValue(data,
                                    "status_rqc");
                            Object ob2 = BeanHelper.getBeanPropertyValue(data,
                                    "status_rqc.chnShortDescription");
                            if (ob1 == null) {
                                row.add("未质检");
                            }
                            if (ob2 != null) {
                                row.add(ob2);
                            }
                            Object ob3 = BeanHelper.getBeanPropertyValue(data,
                                    "status_freeze");
                            if (ob3 == YesNo.YES) {
                                row.add("已冻结");
                            }
                            if (ob3 == YesNo.NO) {
                                row.add("未冻结");
                            }
                        }
                    });
            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }

        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("boxlist", result);
        request.setAttribute("x_selType", Integer.valueOf(26));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }


    public ActionForward listInventory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(BoxQueryCondition.STATUS_EQ, Integer.valueOf(3));

        conditions.put(BoxQueryCondition.NUMBER_GT, Integer.valueOf(0));
        conditions.put(BoxQueryCondition.ISENABLED_NOT_EQ, EnabledDisabled.ENABLED);
        conditions.put(BoxQueryCondition.FREEZE_EQ, YesNo.NO);
        getConditionAndOrder(queryForm, conditions, request);

        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("boxlist", result);
        request.setAttribute("x_selType", Integer.valueOf(3));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward purchaseOrderHighLineBoxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(BoxQueryCondition.LOCATION_EQ, Integer.valueOf(5));
        conditions.put(BoxQueryCondition.PART_TYPE_EQ, Integer.valueOf(2));
        conditions.put(BoxQueryCondition.ISENABLED_NOT_EQ, EnabledDisabled.ENABLED);
        getConditionAndOrder(queryForm, conditions, request);

        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("boxlist", result);
        request.setAttribute("x_selType", Integer.valueOf(28));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward purchaseOrderHighTwoLineBoxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(BoxQueryCondition.LOCATION_EQ, Integer.valueOf(5));
        conditions.put(BoxQueryCondition.PART_TYPE_EQ, Integer.valueOf(1));
        conditions.put(BoxQueryCondition.ISENABLED_NOT_EQ, EnabledDisabled.ENABLED);
        getConditionAndOrder(queryForm, conditions, request);

        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("boxlist", result);
        request.setAttribute("x_selType", Integer.valueOf(75));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward purchaseOrderBelowLineBoxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map<BoxQueryCondition, Integer> conditions = constructQueryMap(queryForm);
        conditions.put(BoxQueryCondition.LOCATION_EQ, Integer.valueOf(12));

//        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);

        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("boxlist", result);
        request.setAttribute("x_selType", Integer.valueOf(29));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward highLineBoxOneSelect(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxManager manager = ServiceLocator.getBoxManager(request);
        String array = request.getParameter("arrays");
        String[] arrays = array.split(",");
        List<Box> listBox = new ArrayList<Box>(); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String lot = arrayOfString1[b];

            List<Box> list = manager.gethighLineBoxOneCheck(lot);
            listBox.addAll(list);
            b++; }

        request.setAttribute("x_boxList", listBox);
        return mapping.findForward("page");
    }



    public ActionForward insertPurchaseOrderHighLineOne(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxManager manager = ServiceLocator.getBoxManager(request);
        String part = request.getParameter("part");
        String lot = request.getParameter("lots");
        String partname = request.getParameter("partname");
        String[] lots = lot.split(",");

        manager.systemPurchaseOrderHighLineOne(lots, part,
                getCurrentUser(request).getId().toString(), partname);
        return mapping.findForward("success");
    }



    public ActionForward insertPurchaseOrderHighLineTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxManager manager = ServiceLocator.getBoxManager(request);
        String array = request.getParameter("arrays");
        String[] lots = array.split(",");

        manager.systemPurchaseOrderHighLineTwo(lots,
                getCurrentUser(request).getId().toString());
        return new ActionForward("purchaseOrderHighTwoLineBoxList.do", true);
    }



    public ActionForward validateHighLineTwoByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");
//        response.setCharacterEncoding("UTF-8");
        JsonConfig cfg = new JsonConfig();

        BoxManager manager = ServiceLocator.getBoxManager(request);
        String array = request.getParameter("id");
        String[] arrays = array.split(",");

        boolean sign = true; byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String lot = arrayOfString1[b];
            List<Box> list = manager.gethighLineBoxOneCheck(lot);
            if (list.size() > 0) {
                String newlot = ((Box)list.get(0)).getLot().getId();
                if (!newlot.equals(lot)) {
                    sign = false;
                }
            }
            b++; }

        JSONArray jo = JSONArray.fromObject(Boolean.valueOf(sign), cfg);
        response.getWriter().print(jo);
        return null;
    }



    public ActionForward purchaseOrderHighLineBoxTwoSelect(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxManager manager = ServiceLocator.getBoxManager(request);
        String array = request.getParameter("arrays");
        String[] lots = array.split(",");
        List<Box> list = new ArrayList(); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = lots).length, b = 0; b < i; ) { String lot = arrayOfString1[b];
            List<Box> boxs = manager.gethighLineBoxOneCheck(lot);
            list.addAll(boxs);
            b++; }

        request.setAttribute("x_boxList", list);
        return mapping.findForward("page");
    }



    public ActionForward listBox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(BoxQueryCondition.ISENABLED_EQ, Integer.valueOf(1));

        getConditionAndOrder(queryForm, conditions, request);

        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }


        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = boxManager.getBoxList(conditions, 0, -1,
                    BoxQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());

            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "purchaseOrder";
            String suffix = ExportUtil.export(
                    exportType,
                    data,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception
                        {
                            MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.lot.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.po_number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.po_line"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "part.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "DPI"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "part.describe1"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "part.describe2"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.location.code"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.in_date"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.out_date"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.status_rqc"));
                        }


                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data, "lot.id"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "po_number"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "po_line"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.dpiNo"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "part.describe2"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "number"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "location.code"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "in_date"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "out_date"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "status.chnShortDescription"));
                        }
                    });
            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }


        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(15));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward listBoxReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);

        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);

        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }

        String exportType = queryForm.getExportType();
        if (exportType != null && exportType.length() > 0) {
            List data = boxManager.getBoxList(conditions, 0, -1, BoxQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "box";
            String suffix = ExportUtil.export(
                    exportType,
                    data,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {

                        public void exportHead(List row, HttpServletRequest request) throws Exception
                        {
                            MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.row"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.part.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.code"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "DPI"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.po_supp"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.part.describe1"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.location.code"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.num"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "wmspart.pc"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "containerType.date"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_rqc"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_freeze"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status.chnShortDescription"));
                        }


                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanUtils.getProperty(data, "lot.id"));
                            row.add(BeanUtils.getProperty(data, "po_number"));
                            row.add(BeanUtils.getProperty(data, "po_line"));
                            if (BeanUtils.getProperty(data, "psoItem") != null) {
                                row.add(BeanUtils.getProperty(data, "psoItem.portalShipOrder.code"));
                            } else {
                                row.add(BeanUtils.getProperty(data, "single.code"));
                            }
                            row.add(BeanUtils.getProperty(data, "part.id"));
                            row.add(BeanUtils.getProperty(data, "part.dpiNo"));
                            row.add(BeanUtils.getProperty(data, "po_supp"));
                            row.add(BeanUtils.getProperty(data, "part.describe1"));
                            row.add(BeanUtils.getProperty(data, "location.code"));
                            row.add(BeanUtils.getProperty(data, "number"));
                            row.add(BeanUtils.getProperty(data, "part.unit"));
                            row.add(BeanUtils.getProperty(data, "po_date"));
                            if (BeanUtils.getProperty(data, "status_rqc") != null) {
                                row.add(BeanUtils.getProperty(data, "status_rqc.chnShortDescription"));
                            } else {
                                row.add("待检");
                            }
                            row.add(BeanUtils.getProperty(data, "status_freeze.chnShortDescription"));
                            row.add(BeanUtils.getProperty(data, "status.chnShortDescription"));
                        }
                    });

            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }


        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        putEnumListToRequest(request);
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(89));
        return mapping.findForward("page");
    }



    public ActionForward listBoxAbolition(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);

        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);

        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }


        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = boxManager.getBoxList(conditions, 0, -1,
                    BoxQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());

            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "purchaseOrder";
            String suffix = ExportUtil.export(
                    exportType,
                    data,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception
                        {
                            MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.lot.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.po_number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.po_line"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.psoItem"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.part.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "DPI"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.po_supp"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.part.describe1"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "InventoryTransit.location.code"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "InventoryMoving.part.unit"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.po_date"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.status_rqc"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.status_freeze.chnShortDescription"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.status.chnShortDescription"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.isPrint.chnDescription"));
                        }



                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "lot.id"));
                            Object po_number = BeanHelper.getBeanPropertyValue(
                                    data, "po_number");
                            if (po_number != null) {
                                row.add(po_number);
                            } else {
                                row.add("");
                            }
                            Object po_line = BeanHelper.getBeanPropertyValue(
                                    data, "po_line");
                            if (po_line != null) {
                                row.add(po_line);
                            } else {
                                row.add("");
                            }
                            Object psoItem = BeanHelper.getBeanPropertyValue(
                                    data, "psoItem");
                            Object single = BeanHelper.getBeanPropertyValue(
                                    data, "single");
                            if (psoItem == null && single == null) {
                                row.add("");
                            } else {

                                if (psoItem != null)
                                {


                                    row.add(BeanHelper.getBeanPropertyValue(data,
                                            "psoItem.portalShipOrder.code"));
                                }

                                if (single != null)
                                {


                                    row.add(BeanHelper.getBeanPropertyValue(data,
                                            "single.code"));
                                }
                            }



                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.id"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.dpiNo"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "po_supp"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.describe1"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "location.code"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "number"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.unit"));
                            Object po_date = BeanHelper.getBeanPropertyValue(
                                    data, "po_date");
                            if (po_date != null) {
                                row.add(po_date);
                            } else {
                                row.add("");
                            }
                            Object status_rqc =
                                    BeanHelper.getBeanPropertyValue(data, "status_rqc");
                            if (status_rqc == null) {
                                row.add("未质检");
                            }
                            if (status_rqc != null) {
                                row.add(BeanHelper.getBeanPropertyValue(data,
                                        "status_rqc.chnShortDescription"));
                            }

                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "status_freeze.chnShortDescription"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "status.chnShortDescription"));
                            Object isPrint = BeanHelper.getBeanPropertyValue(
                                    data, "isPrint.chnDescription");
                            if (isPrint != null) {

                                if (isPrint == YesNo.NO) {
                                    row.add("未打印");
                                } else {
                                    row.add("已打印");
                                }
                            } else {
                                row.add("");
                            }
                        }
                    });

            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(27));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }


    public ActionForward boxAbolitionByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");
//        response.setCharacterEncoding("UTF-8");
        JsonConfig cfg = new JsonConfig();

        String array = request.getParameter("ids");
        BoxManager manager = ServiceLocator.getBoxManager(request);
        manager.boxAbolition(array);

        JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
        response.getWriter().print(jo);
        return null;
    }


    public ActionForward updateBoxByRemarkAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");
//        response.setCharacterEncoding("UTF-8");
        JsonConfig cfg = new JsonConfig();

        String id = request.getParameter("id");
        String remark = request.getParameter("remark");
        BoxManager manager = ServiceLocator.getBoxManager(request);
        Box box = manager.getBox(Integer.valueOf(Integer.parseInt(id)));
        box.setRemark(remark.trim());
        manager.updateBox(box);
        JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
        response.getWriter().print(jo);
        return null;
    }

    private Map constructQueryMap(BoxQueryForm queryForm) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        String lot = queryForm.getLotser();
        if (lot != null && !lot.equals("")) {
            conditions.put(BoxQueryCondition.LOTSER_EQ, lot);
        }

        return conditions;
    }

    private Box getBox(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        BoxManager purchaseOrderManager = ServiceLocator.getBoxManager(request);
        Box purchaseOrder = purchaseOrderManager.getBox(Integer.valueOf(Integer.parseInt(id)));
        if (purchaseOrder == null)
            throw new ActionException("purchaseOrder.notFound", id);
        return purchaseOrder;
    }

    private void putEnumListToRequest(HttpServletRequest request) {
        request.setAttribute("X_YESNOLIST",
                PersistentEnum.getEnumList(YesNo.class));
        request.setAttribute("X_SITELIST", getAndCheckGrantedSiteList(request));
    }



    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Box purchaseOrder = getBox(request);
        SupplierManager supplierManager =
                ServiceLocator.getSupplierManager(request);
        request.setAttribute("x_purchaseOrderBox", purchaseOrder);
        if (!isBack(request)) {

            BeanForm purchaseOrderForm = (BeanForm)getForm(
                    "/updatePurchaseOrderBox", request);
            purchaseOrderForm.populate(purchaseOrder, "to_form");
        }
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    private static List removeDuplicateWithOrder(List list) {
        Set<Object> set = new HashSet();
        List<Object> newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        return newList;
    }



    public ActionForward updateDevanningBox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BeanForm purchaseOrderForm = (BeanForm)form;
        Box purchaseOrder = new Box();
        purchaseOrderForm.populate(purchaseOrder, "to_bean");
        BoxManager purchaseOrderManager = ServiceLocator.getBoxManager(request);

        return mapping.findForward("success");
    }



    public ActionForward updateExchangeBox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BeanForm purchaseOrderForm = (BeanForm)form;
        Box purchaseOrder = new Box();
        purchaseOrderForm.populate(purchaseOrder, "to_bean");
        BoxManager purchaseOrderManager = ServiceLocator.getBoxManager(request);

        return mapping.findForward("success");
    }



    public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxManager BoxManager = ServiceLocator.getBoxManager(request);
        Site site = getCurrentUser(request).getPrimarySite();
        List<Box> list = BoxManager.getBoxList(null, 0, -1, null, false);
        if (!isBack(request)) {
            Box Box = new Box();
            BeanForm BoxForm = (BeanForm)getForm("/insertPurchaseOrderBox",
                    request);
            BoxForm.populate(Box, "to_form");
        }

        request.setAttribute("x_listDept", list);

        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward purchaseOrderRqcDetermine(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsToolManager manager = ServiceLocator.getWmsToolManager(request);
        List<BadReasons> reasons = manager.getBadReasons();
        StorageLocationManager locationManager =
                ServiceLocator.getStorageLocationManager(request);
        PurchaseOrderPutInStorageManager storageManager =
                ServiceLocator.getPurchaseOrderPutInStorageManager(request);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put(StorageLocationQueryCondition.STROMTYPE_EQ, Integer.valueOf(2));
        List list = locationManager.getStorageLocationList(map, -1, -1, null,
                true);

        String type = request.getParameter("type");
        String arrays = request.getParameter("arrays");
        String sign = "false";
        if (type.equals("4") && type != null) {
            List<StorageLocation> locations = storageManager
                    .getRecommendLocationList();
            request.setAttribute("x_partLocations", locations);
        }
        if (type.equals("5") && type != null) {
            map.clear();
            map.put(StorageLocationQueryCondition.STROMTYPE_EQ, Integer.valueOf(14));
            List listBlp = locationManager.getStorageLocationList(map, -1, -1, null,
                    true);
            request.setAttribute("x_blpLocations", listBlp);
        }

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        List<Box> boxList = new ArrayList<Box>();
        String[] str = arrays.split(",");
        BigDecimal sum = new BigDecimal(0); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = str).length, b = 0; b < i; ) { String lot = arrayOfString1[b];
            Box box = boxManager.getBoxBylotSer2(lot);
            boxList.add(box);
            sum = sum.add(box.getNumber());
            b++; }

        request.setAttribute("x_list", list);
        request.setAttribute("x_reasons", reasons);
        request.setAttribute("x_arrays", arrays);
        request.setAttribute("x_type", type);
        request.setAttribute("x_boxList", boxList);
        request.setAttribute("x_objsize", Integer.valueOf(boxList.size()));
        request.setAttribute("x_sum", sum);
        return mapping.findForward("page");
    }



    public ActionForward updatePurchaseOrderRqcDetermine(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String arrays = request.getParameter("arrays");
        String location = request.getParameter("location");
        String type = request.getParameter("type");
        String rqcType = request.getParameter("rqcType");
        String strList = request.getParameter("strList");

        PurchaseOrderReceiptsManager managerPor =
                ServiceLocator.getPurchaseOrderReceiptsManager(request);
        PurchaseOrderRQCManager managerRqc =
                ServiceLocator.getPurchaseOrderRQCManager(request);
        PurchaseOrderPutInStorageManager storageManager =
                ServiceLocator.getPurchaseOrderPutInStorageManager(request);

        String str = "";
        if (type.equals("2")) {
            str = managerPor.systemPurchaseOrderReceipts(arrays, location,
                    getCurrentUser(request));
            if (!str.equals("") && !str.equals("ok"))
            {
                request.setAttribute("x_mfg", str);
            }
        } else if (type.equals("3")) {
            String[] strs = arrays.split(","); byte b; int i; String[] arrayOfString1;
            for (i = (arrayOfString1 = strs).length, b = 0; b < i; ) { String array = arrayOfString1[b];
                str = managerRqc.systemPurchaseOrderRQC(array, rqcType, strList,
                        getCurrentUser(request));
                b++; }

        } else if (type.equals("5")) {
            String[] strs = arrays.split(","); byte b; int i; String[] arrayOfString1;
            for (i = (arrayOfString1 = strs).length, b = 0; b < i; ) { String array = arrayOfString1[b];
                String resu = storageManager.scanningPurchaseOrderBLPInStorage(array,
                        location, getCurrentUser(request).getId()
                                .toString());
                if (!resu.equals("ok") && !resu.equals(str))
                    str = String.valueOf(str) + resu;
                b++; }

            if (!str.equals(""))
            {
                request.setAttribute("x_mfg", str);
            }
        } else {

            String[] strs = arrays.split(","); byte b; int i; String[] arrayOfString1;
            for (i = (arrayOfString1 = strs).length, b = 0; b < i; ) { String array = arrayOfString1[b];
                String resu = storageManager.scanningPurchaseOrderPutInStorage(array,
                        location, getCurrentUser(request).getId()
                                .toString());
                if (!resu.equals("ok") && !resu.equals(str))
                    str = String.valueOf(str) + resu;
                b++; }

            if (!str.equals(""))
            {
                request.setAttribute("x_mfg", str);
            }
        }


        return mapping.findForward("success");
    }



    public ActionForward scanningProductOutbound(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String array = request.getParameter("array");
        BoxManager manager = ServiceLocator.getBoxManager(request);
        String[] arrays = array.split(","); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String lotSer = arrayOfString1[b];
            manager.scanningProductOutbound(lotSer,
                    getCurrentUser(request).getId().toString());
            b++; }

        return mapping.findForward("success");
    }



    public ActionForward listProductOffline(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(BoxQueryCondition.TYPE_EQ, Integer.valueOf(0));

        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);

        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(4));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }


    public ActionForward listPurchaseOrderLotPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(BoxQueryCondition.ENDTIME_EQ, Integer.valueOf(1));
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);

        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = boxManager.getBoxList(conditions, 0, -1,
                    BoxQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());

            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "purchaseOrder";
            String suffix = ExportUtil.export(
                    exportType,
                    data,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception
                        {
                            MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.lot.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.po_number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.po_line"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.psoItem"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.part.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "DPI"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.po_supp"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.part.describe1"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.po_date"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.status_rqc"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.status_freeze.chnShortDescription"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.status.chnShortDescription"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.isPrint.chnDescription"));
                        }



                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "lot.id"));
                            Object po_number = BeanHelper.getBeanPropertyValue(
                                    data, "po_number");
                            if (po_number != null) {
                                row.add(po_number);
                            } else {
                                row.add("");
                            }
                            Object po_line = BeanHelper.getBeanPropertyValue(
                                    data, "po_line");
                            if (po_line != null) {
                                row.add(po_line);
                            } else {
                                row.add("");
                            }
                            Object psoItem = BeanHelper.getBeanPropertyValue(
                                    data, "psoItem");
                            Object single = BeanHelper.getBeanPropertyValue(
                                    data, "single");
                            if (psoItem == null && single == null) {
                                row.add("");
                            } else {

                                if (psoItem != null)
                                {


                                    row.add(BeanHelper.getBeanPropertyValue(data,
                                            "psoItem.portalShipOrder.code"));
                                }
                                if (single != null)
                                {


                                    row.add(BeanHelper.getBeanPropertyValue(data,
                                            "single.code"));
                                }
                            }


                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.id"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.dpiNo"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "po_supp"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.describe1"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "number"));
                            Object po_date = BeanHelper.getBeanPropertyValue(
                                    data, "po_date");
                            if (po_date != null) {
                                row.add(po_date);
                            } else {
                                row.add("");
                            }
                            Object status_rqc =
                                    BeanHelper.getBeanPropertyValue(data, "status_rqc");
                            if (status_rqc == null) {
                                row.add("未质检");
                            }
                            if (status_rqc != null) {
                                row.add(BeanHelper.getBeanPropertyValue(data,
                                        "status_rqc.chnShortDescription"));
                            }

                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "status_freeze.chnShortDescription"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "status.chnShortDescription"));
                            Object isPrint = BeanHelper.getBeanPropertyValue(
                                    data, "isPrint.chnDescription");
                            if (isPrint != null) {

                                if (isPrint == YesNo.NO) {
                                    row.add("未打印");
                                } else {
                                    row.add("已打印");
                                }
                            } else {
                                row.add("");
                            }
                        }
                    });

            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }


        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(34));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward purchaseOrderLotPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String array = request.getParameter("arrays");
        String[] arrays = array.split(",");
        List<Box> list = new ArrayList<Box>();
        BoxManager manager = ServiceLocator.getBoxManager(request); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
            Box box = manager.getBoxBylotSer2(id);
            list.add(box);
            b++; }

        request.setAttribute("x_listMap", list);
        request.setAttribute("path", request.getContextPath());
        request.setAttribute("x_user", getCurrentUser(request));
        return mapping.findForward("page");
    }



    public ActionForward purchaseOrderBoxIdPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String array = request.getParameter("arrays");
        String[] arrays = array.split(",");
        List<Box> list = new ArrayList<Box>();
        BoxManager manager = ServiceLocator.getBoxManager(request); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
            Box box = manager.getBox(Integer.valueOf(Integer.parseInt(id)));
            list.add(box);
            b++; }

        request.setAttribute("x_listMap", list);
        request.setAttribute("path", request.getContextPath());
        request.setAttribute("x_user", getCurrentUser(request));
        return mapping.findForward("page");
    }



    public ActionForward updateBoxIsPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");
//        response.setCharacterEncoding("UTF-8");
        JsonConfig cfg = new JsonConfig();

        String ids = request.getParameter("ids");
        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        String[] arrays = ids.split(";"); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String lot = arrayOfString1[b];
            Integer id = Integer.valueOf(Integer.parseInt(lot));
            Box bo = boxManager.getBox(id);
            bo.setIsPrint(YesNo.YES);
            boxManager.updateBox(bo); b++; }

        JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
        response.getWriter().print(jo);
        return null;
    }


    public ActionForward listPurchaseOrderRQCUnqualified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);


        conditions.put(BoxQueryCondition.STATUS_NOT_EQ, Integer.valueOf(9));
        conditions.put(BoxQueryCondition.LOCATION_STORE_ROOM_TYPE_EQ, Integer.valueOf(6));



        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);

        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }

        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = boxManager.getBoxList(conditions, 0, -1,
                    BoxQueryOrder.getEnum(queryForm.getOrder()),
                    queryForm.isDescend());

            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "purchaseOrder";
            String suffix = ExportUtil.export(
                    exportType,
                    data,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception
                        {
                            MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.lot.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.po_number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.po_line"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.psoItem"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.part.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "DPI"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.po_supp"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.part.describe1"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "InventoryTransit.location.code"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.po_date"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.status_rqc"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.status_rqc.chnShortDescription"));

                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.status_freeze.chnShortDescription"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request),
                                    "Box.status.chnShortDescription"));
                        }



                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "lot.id"));
                            Object po_number = BeanHelper.getBeanPropertyValue(
                                    data, "po_number");
                            if (po_number != null) {
                                row.add(po_number);
                            } else {
                                row.add("");
                            }
                            Object po_line = BeanHelper.getBeanPropertyValue(
                                    data, "po_line");
                            if (po_line != null) {
                                row.add(po_line);
                            } else {
                                row.add("");
                            }
                            Object psoItem = BeanHelper.getBeanPropertyValue(
                                    data, "psoItem");
                            Object single = BeanHelper.getBeanPropertyValue(
                                    data, "single");
                            if (psoItem == null && single == null) {
                                row.add("");
                            } else {

                                if (psoItem != null)
                                {


                                    row.add(BeanHelper.getBeanPropertyValue(data,
                                            "psoItem.portalShipOrder.code"));
                                }
                                if (single != null)
                                {


                                    row.add(BeanHelper.getBeanPropertyValue(data,
                                            "single.code"));
                                }
                            }


                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.id"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.dpiNo"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "po_supp"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "part.describe1"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "location.code"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "number"));
                            Object po_date = BeanHelper.getBeanPropertyValue(
                                    data, "po_date");
                            if (po_date != null) {
                                row.add(po_date);
                            } else {
                                row.add("");
                            }
                            Object status_rqc =
                                    BeanHelper.getBeanPropertyValue(data, "status_rqc");
                            if (status_rqc == null) {
                                row.add("未质检");
                            }
                            if (status_rqc != null) {
                                row.add(BeanHelper.getBeanPropertyValue(data,
                                        "status_rqc.chnShortDescription"));
                            }
                            if (BeanHelper.getBeanPropertyValue(data, "status_rqc") != null) {
                                row.add(BeanHelper.getBeanPropertyValue(data, "status_rqc.chnShortDescription"));
                            } else {
                                row.add("待检");
                            }
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "status_freeze.chnShortDescription"));
                            row.add(BeanHelper.getBeanPropertyValue(data,
                                    "status.chnShortDescription"));
                        }
                    });



            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }



        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("boxlist", result);
        request.setAttribute("x_selType", Integer.valueOf(35));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward listProductScanningOutbound(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(BoxQueryCondition.STATUS_EQ, Integer.valueOf(3));

        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);

        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("boxlist", result);

        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward listPurchaseOrderBoxFreeze(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(BoxQueryCondition.FREEZE_EQ, Integer.valueOf(1));

        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);

        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }


        String exportType = queryForm.getExportType();
        if (exportType != null && exportType.length() > 0) {
            List data = boxManager.getBoxList(conditions, 0, -1, BoxQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "Box";
            String suffix = ExportUtil.export(
                    exportType,
                    data,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {

                        public void exportHead(List row, HttpServletRequest request) throws Exception
                        {
                            MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.lot.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.po_number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.po_line"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.psoItem"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.part.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "DPI"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.po_supp"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.part.describe1"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.po_date"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_rqc.chnShortDescription"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_freeze"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status.chnShortDescription"));
                        }



                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanUtils.getProperty(data, "lot.id"));
                            row.add(BeanUtils.getProperty(data, "po_number"));
                            row.add(BeanUtils.getProperty(data, "po_line"));
                            if (BeanUtils.getProperty(data, "psoItem") != null) {
                                row.add(BeanUtils.getProperty(data, "psoItem.portalShipOrder.code"));
                            } else {
                                row.add(BeanUtils.getProperty(data, "single.code"));
                            }
                            row.add(BeanUtils.getProperty(data, "part.id"));
                            row.add(BeanUtils.getProperty(data, "part.dpiNo"));
                            row.add(BeanUtils.getProperty(data, "po_supp"));
                            row.add(BeanUtils.getProperty(data, "part.describe1"));
                            row.add(BeanUtils.getProperty(data, "number"));
                            row.add(BeanUtils.getProperty(data, "po_date"));
                            row.add(BeanUtils.getProperty(data, "status_rqc.chnShortDescription"));
                            if (BeanUtils.getProperty(data, "status_freeze").equals("0")) {
                                row.add("已冻结");
                            } else {
                                row.add("未冻结");
                            }
                            row.add(BeanUtils.getProperty(data, "status.chnShortDescription"));
                        }
                    });

            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }

        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("boxlist", result);
        request.setAttribute("x_selType", Integer.valueOf(77));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward updatePurchaseOrderBoxFreeze(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");
//        response.setCharacterEncoding("UTF-8");
        JsonConfig cfg = new JsonConfig();

        BoxManager manager = ServiceLocator.getBoxManager(request);
        String array = request.getParameter("arrays");
        boolean sign = manager.updatePurchaseOrderBoxFreeze(array, Boolean.valueOf(true));

        JSONArray jo = JSONArray.fromObject(Boolean.valueOf(sign), cfg);
        response.getWriter().print(jo);
        return null;
    }



    public ActionForward listPurchaseOrderBoxFreezeOpen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(BoxQueryCondition.FREEZE_EQ, Integer.valueOf(0));

        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);

        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }


        String exportType = queryForm.getExportType();
        if (exportType != null && exportType.length() > 0) {
            List data = boxManager.getBoxList(conditions, 0, -1, BoxQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "Box";
            String suffix = ExportUtil.export(
                    exportType,
                    data,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {

                        public void exportHead(List row, HttpServletRequest request) throws Exception
                        {
                            MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.lot.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.po_number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.po_line"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.psoItem"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.part.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "DPI"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.po_supp"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.part.describe1"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.po_date"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_rqc.chnShortDescription"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_freeze"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status.chnShortDescription"));
                        }



                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanUtils.getProperty(data, "lot.id"));
                            row.add(BeanUtils.getProperty(data, "po_number"));
                            row.add(BeanUtils.getProperty(data, "po_line"));
                            if (BeanUtils.getProperty(data, "psoItem") != null) {
                                row.add(BeanUtils.getProperty(data, "psoItem.portalShipOrder.code"));
                            } else {
                                row.add(BeanUtils.getProperty(data, "single.code"));
                            }
                            row.add(BeanUtils.getProperty(data, "part.id"));
                            row.add(BeanUtils.getProperty(data, "part.dpiNo"));
                            row.add(BeanUtils.getProperty(data, "po_supp"));
                            row.add(BeanUtils.getProperty(data, "part.describe1"));
                            row.add(BeanUtils.getProperty(data, "number"));
                            row.add(BeanUtils.getProperty(data, "po_date"));
                            row.add(BeanUtils.getProperty(data, "status_rqc.chnShortDescription"));
                            if (BeanUtils.getProperty(data, "status_freeze").equals("0")) {
                                row.add("已冻结");
                            } else {
                                row.add("未冻结");
                            }
                            row.add(BeanUtils.getProperty(data, "status.chnShortDescription"));
                        }
                    });

            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }


        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("boxlist", result);
        request.setAttribute("x_selType", Integer.valueOf(78));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward updatePurchaseOrderBoxFreezeOpen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");
//        response.setCharacterEncoding("UTF-8");
        JsonConfig cfg = new JsonConfig();

        BoxManager manager = ServiceLocator.getBoxManager(request);
        String array = request.getParameter("arrays");
        boolean sign = manager.updatePurchaseOrderBoxFreeze(array, Boolean.valueOf(false));

        JSONArray jo = JSONArray.fromObject(Boolean.valueOf(sign), cfg);
        response.getWriter().print(jo);
        return null;
    }


    public ActionForward newDefeScanning(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String array = request.getParameter("array");

        request.setAttribute("x_array", array);
        return mapping.findForward("page");
    }



    public ActionForward poPutInStorageMaterialBoxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);

        conditions.put(BoxQueryCondition.STATUS_EQ, Integer.valueOf(3));
        conditions.put(BoxQueryCondition.LOCATION_STORE_ROOM_TYPE_EQ, Integer.valueOf(6));
        conditions.put(BoxQueryCondition.ISENABLED_NOT_EQ, EnabledDisabled.ENABLED);
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);

        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }


        String exportType = queryForm.getExportType();
        if (exportType != null && exportType.length() > 0) {
            List<Box> data = boxManager.getBoxList(conditions, 0, -1, BoxQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
            for (Box box : data) {
                List<PurchaseOrderRqcUnqualified> list = boxManager.getPurchaseOrderRqcUnqualifiedList(box.getId());
                box.setUnqualifiedList(list);
            }
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "box";
            String suffix = ExportUtil.export(
                    exportType,
                    data,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {

                        public void exportHead(List row, HttpServletRequest request) throws Exception
                        {
                            MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.row"));

                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.code"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.po_supp"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "supplierId.name"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.part.describe1"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.location.code"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.num"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "wmspart.pc"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "containerType.date"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_rqc"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "ProduceRejectedMaterial.returnReasons.describe"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_freeze"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status.chnShortDescription"));
                        }


                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            Box box = (Box)data;
                            row.add(BeanUtils.getProperty(data, "lot.id"));
                            row.add(BeanUtils.getProperty(data, "po_number"));
                            row.add(BeanUtils.getProperty(data, "po_line"));



                            row.add(BeanUtils.getProperty(data, "part.id"));
                            row.add(BeanUtils.getProperty(data, "po_supp"));
                            row.add(BeanUtils.getProperty(data, "po_supp_name"));
                            row.add(BeanUtils.getProperty(data, "part.describe1"));
                            row.add(BeanUtils.getProperty(data, "location.code"));
                            row.add(BeanUtils.getProperty(data, "number"));
                            row.add(BeanUtils.getProperty(data, "part.unit"));
                            row.add(BeanUtils.getProperty(data, "po_date"));
                            if (BeanUtils.getProperty(data, "status_rqc") != null) {
                                row.add(BeanUtils.getProperty(data, "status_rqc.chnShortDescription"));
                            } else {
                                row.add("待检");
                            }
                            String returnReasons = "";
                            List<PurchaseOrderRqcUnqualified> list = box.getUnqualifiedList();
                            for (int i = 0; i < list.size(); i++) {
                                PurchaseOrderRqcUnqualified purchaseOrderRqcUnqualified = list.get(i);
                                if (i == 0) {
                                    returnReasons = String.valueOf(returnReasons) + purchaseOrderRqcUnqualified.getReasons().getDescribe();
                                } else {
                                    returnReasons = String.valueOf(returnReasons) + "," + purchaseOrderRqcUnqualified.getReasons().getDescribe();
                                }
                            }
                            row.add(returnReasons);
                            row.add(BeanUtils.getProperty(data, "status_freeze.chnShortDescription"));
                            row.add(BeanUtils.getProperty(data, "status.chnShortDescription"));
                        }
                    });

            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }



        List<Box> result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());
        for (Box box : result) {
            List<PurchaseOrderRqcUnqualified> list = boxManager.getPurchaseOrderRqcUnqualifiedList(box.getId());
            box.setUnqualifiedList(list);
        }
        request.setAttribute("boxlist", result);
        request.setAttribute("x_selType", Integer.valueOf(112));
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward poPutInStorageMaterialBoxByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");
//        response.setCharacterEncoding("UTF-8");
        JsonConfig cfg = new JsonConfig();
        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        String array = request.getParameter("array");
        String str = boxManager.updateProduceInStorageMaterial(array, getCurrentUser(request));

        JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
        response.getWriter().print(jo);
        return null;
    }


    public ActionForward listBoxDetailReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxQueryForm queryForm = (BoxQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(BoxQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        Map conditions = constructQueryMap(queryForm);
        conditions.put(BoxQueryCondition.STATUS_EQ, BoxStatus.HASBEENINTO);
        conditions.put(BoxQueryCondition.LOCATION_STORE_ROOM_TYPE_EQ, StoreRoomType.RAWMATERIALSLIBRARY);
        conditions.put(BoxQueryCondition.ISENABLED_NOT_EQ, EnabledDisabled.ENABLED);
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);

        if (queryForm.isFirstInit()) {
            queryForm.init(boxManager.getBoxListCount(conditions));
        } else {
            queryForm.init();
        }

        String exportType = queryForm.getExportType();
        if (exportType != null && exportType.length() > 0) {
            List data = boxManager.getBoxList(conditions, 0, -1, BoxQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "BoxDetail";
            String suffix = ExportUtil.export(
                    exportType,
                    data,
                    request,
                    new FileOutputStream(SessionTempFile.getTempFile(index,
                            request)), new Exportable()
                    {

                        public void exportHead(List row, HttpServletRequest request) throws Exception
                        {
                            MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.number"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.row"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.part.id"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.code"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "DPI"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.po_supp"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.part.describe1"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.location.code"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.num"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "wmspart.pc"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "containerType.date"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_rqc"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_freeze"));
                            row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status.chnShortDescription"));
                        }


                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanUtils.getProperty(data, "lot.id"));
                            row.add(BeanUtils.getProperty(data, "po_number"));
                            row.add(BeanUtils.getProperty(data, "po_line"));
                            if (BeanUtils.getProperty(data, "psoItem") != null) {
                                row.add(BeanUtils.getProperty(data, "psoItem.portalShipOrder.code"));
                            } else {
                                row.add(BeanUtils.getProperty(data, "single.code"));
                            }
                            row.add(BeanUtils.getProperty(data, "part.id"));
                            row.add(BeanUtils.getProperty(data, "part.dpiNo"));
                            row.add(BeanUtils.getProperty(data, "po_supp"));
                            row.add(BeanUtils.getProperty(data, "part.describe1"));
                            row.add(BeanUtils.getProperty(data, "location.code"));
                            row.add(BeanUtils.getProperty(data, "number"));
                            row.add(BeanUtils.getProperty(data, "part.unit"));
                            row.add(BeanUtils.getProperty(data, "po_date"));
                            if (BeanUtils.getProperty(data, "status_rqc") != null) {
                                row.add(BeanUtils.getProperty(data, "status_rqc.chnShortDescription"));
                            } else {
                                row.add("待检");
                            }
                            row.add(BeanUtils.getProperty(data, "status_freeze.chnShortDescription"));
                            row.add(BeanUtils.getProperty(data, "status.chnShortDescription"));
                        }
                    });

            return new ActionForward("download/" + index + "/" +
                    URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }


        List result = boxManager.getBoxList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                BoxQueryOrder.ID, queryForm.isDescend());

        putEnumListToRequest(request);
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(135));
        return mapping.findForward("page");
    }


    public ActionForward produceRejectedMaterialBoxByReasons(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsToolManager manager = ServiceLocator.getWmsToolManager(request);
        List<BadReasons> reasons = manager.getBadReasons();
        StorageLocationManager locationManager =
                ServiceLocator.getStorageLocationManager(request);
        PurchaseOrderPutInStorageManager storageManager =
                ServiceLocator.getPurchaseOrderPutInStorageManager(request);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put(StorageLocationQueryCondition.STROMTYPE_EQ, Integer.valueOf(2));
        List list = locationManager.getStorageLocationList(map, -1, -1, null,
                true);

        String arrays = request.getParameter("arrays");
        String ids = request.getParameter("ids");
        String type = request.getParameter("type");

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        List<Box> boxList = new ArrayList<Box>();
        String[] str = arrays.split(",");
        BigDecimal sum = new BigDecimal(0); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = str).length, b = 0; b < i; ) { String lot = arrayOfString1[b];
            Box box = boxManager.getBoxBylotSer2(lot);
            boxList.add(box);
            sum = sum.add(box.getNumber()); b++; }

        request.setAttribute("x_ids", ids);
        request.setAttribute("x_type", type);
        request.setAttribute("x_list", list);
        request.setAttribute("x_reasons", reasons);
        request.setAttribute("x_arrays", arrays);
        request.setAttribute("x_boxList", boxList);
        request.setAttribute("x_objsize", Integer.valueOf(boxList.size()));
        request.setAttribute("x_sum", sum);
        return mapping.findForward("page");
    }



    public ActionForward updateProduceRejectedMaterialBoxByReasons(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProduceRejectedMaterialManager manager = ServiceLocator.getProduceRejectedMaterialManager(request);
        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        PurchaseOrderRQCManager managerRqc =
                ServiceLocator.getPurchaseOrderRQCManager(request);
        String arrays = request.getParameter("arrays");
        String ids = request.getParameter("ids");
        String rqcType = request.getParameter("rqcType");
        String strList = request.getParameter("strList");
        String type = request.getParameter("type");


        String str = "";
        String[] strs = arrays.split(","); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = strs).length, b = 0; b < i; ) { String array = arrayOfString1[b];
            str = managerRqc.purchaseOrderRejectedMaterialBoxRQC(array, rqcType, strList, getCurrentUser(request)); b++; }

        if (type.equals("1")) {
            String str1 = manager.updateProduceRejectedMaterial(ids, getCurrentUser(request));
            if (str1.equals("true")) {
                postGlobalMessage("The.return.of.success", request.getSession());
            }
        } else {
            String str1 = boxManager.updateProduceInStorageMaterial(ids, getCurrentUser(request));
            if (str1.equals("true")) {
                postGlobalMessage("The.return.of.success", request.getSession());
            }
        }
        return mapping.findForward("success");
    }


    public ActionForward updateProduceRejectedMaterialBox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        ProduceRejectedMaterialManager manager = ServiceLocator.getProduceRejectedMaterialManager(request);
        String ids = request.getParameter("ids");
        String type = request.getParameter("type");
        if (type.equals("1")) {
            String str = manager.updateProduceRejectedMaterial(ids, getCurrentUser(request));
            if (str.equals("true")) {
                postGlobalMessage("The.return.of.success", request.getSession());
            }
            return new ActionForward("listProduceRejectedMaterialBoxList.do", true);
        }
        String str1 = boxManager.updateProduceInStorageMaterial(ids, getCurrentUser(request));
        if (str1.equals("true")) {
            postGlobalMessage("The.return.of.success", request.getSession());
        }
        return new ActionForward("listpurchaseOrderPutInStorageMaterialBoxList.do", true);
    }


    public ActionForward purchaseReturnMaterialByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");
//        response.setCharacterEncoding("UTF-8");
        JsonConfig cfg = new JsonConfig();

        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        String array = request.getParameter("array");
        String str = boxManager.purchaseReturnMaterialByBox(array, getCurrentUser(request));

        JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
        response.getWriter().print(jo);
        return null;
    }
}
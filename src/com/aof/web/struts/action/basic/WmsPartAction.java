package com.aof.web.struts.action.basic;

import com.aof.model.admin.Site;
import com.aof.model.admin.Supplier;
import com.aof.model.admin.User;
import com.aof.model.basic.BasicPartPrice;
import com.aof.model.basic.WmsPart;
import com.aof.model.basic.query.BasicPartPriceQueryCondition;
import com.aof.model.basic.query.BasicPartPriceQueryOrder;
import com.aof.model.basic.query.WmsPartQueryCondition;
import com.aof.model.basic.query.WmsPartQueryOrder;
import com.aof.model.basicDataView.JitShipPart;
import com.aof.model.basicDataView.PartForecastNeedReport;
import com.aof.model.basicDataView.PoPartSumNumber;
import com.aof.model.basicDataView.SkPartSumNumber;
import com.aof.model.basicDataView.query.JitShipPartQueryCondition;
import com.aof.model.basicDataView.query.JitShipPartQueryOrder;
import com.aof.model.basicDataView.query.PartForecastNeedReportQueryCondition;
import com.aof.model.basicDataView.query.PartForecastNeedReportQueryOrder;
import com.aof.model.basicDataView.query.PoPartSumNumberQueryOrder;
import com.aof.model.basicDataView.query.SkPartSumNumberQueryOrder;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.model.metadata.WmsPartType;
import com.aof.model.metadata.YesNo;
import com.aof.model.product.BasicCustomer;
import com.aof.model.schedule.NjitNpoPlan;
import com.aof.service.Product.BasicCustomerManager;
import com.aof.service.admin.SupplierManager;
import com.aof.service.basic.BasicPartPriceManager;
import com.aof.service.basic.WmsPartManager;
import com.aof.service.basicDataView.BasicDataViewManager;
import com.aof.service.po.BoxManager;
import com.aof.service.po.PortalShipOrderManager;
import com.aof.service.schedule.NjitNpoPlanManager;
import com.aof.utils.SessionTempFile;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.BaseSessionQueryForm;
import com.aof.web.struts.form.basic.InventoryQueryForm;
import com.aof.web.struts.form.basic.WmsPartQueryForm;
import com.aof.web.struts.form.basicDataView.BasicDataViewQueryForm;
import com.shcnc.hibernate.PersistentEnum;
import com.shcnc.struts.action.ActionException;
import com.shcnc.struts.form.BeanForm;
import com.shcnc.utils.BeanHelper;
import com.shcnc.utils.ExportUtil;
import com.shcnc.utils.Exportable;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

public class WmsPartAction extends BaseAction2 {
    public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsPartQueryForm queryForm = (WmsPartQueryForm)form;
        WmsPartManager manager = ServiceLocator.getWmsPartManager(request);
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(WmsPartQueryOrder.ID.getName());
            queryForm.setDescend(false);
            queryForm.setStatus("0");
        }
        String type = request.getParameter("type");

        Map conditions = constructQueryMap(queryForm);

        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
        if (type != null &&
                type.equals("1")) {
            conditions.put(WmsPartQueryCondition.QUALITYPE, "S140");
            request.setAttribute("x_type", Integer.valueOf(1));
        }


        if (queryForm.isFirstInit()) {
            queryForm.init(manager.getWmsPartListCount(conditions));
        } else {
            queryForm.init();
        }
        List results = manager.getWmsPartList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                WmsPartQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", results);
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }


    public ActionForward selectDeliveryWmsPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsPartQueryForm queryForm = (WmsPartQueryForm)form;
        WmsPartManager manager = ServiceLocator.getWmsPartManager(request);
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(WmsPartQueryOrder.ID.getName());
            queryForm.setDescend(false);
            queryForm.setStatus("0");
        }

        Map conditions = constructQueryMap(queryForm);

        getConditionAndOrder(queryForm, conditions, request);
        conditions.put(WmsPartQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED);

        if (queryForm.isFirstInit()) {
            queryForm.init(manager.getWmsPartListCount(conditions));
        } else {
            queryForm.init();
        }
        List results = manager.getWmsPartList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                WmsPartQueryOrder.ID, queryForm.isDescend());
        request.setAttribute("x_selType", Integer.valueOf(126));
        request.setAttribute("X_RESULTLIST", results);
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    public ActionForward selectCustomerPlanDeliveryWmsPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InventoryQueryForm queryForm = (InventoryQueryForm)form;
        String idString = request.getParameter("partId");
        String customerId = request.getParameter("customerId");
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(PoPartSumNumberQueryOrder.PART_ID.getName());
            queryForm.setDescend(false);
        }
        BoxManager manager = ServiceLocator.getBoxManager(request);
        BasicPartPriceManager basicPartPriceManager = ServiceLocator.getBasicPartPriceManager(request);
        BasicCustomerManager basicCustomerManager = ServiceLocator.getBasicCustomerManager(request);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        BasicCustomer basicCustomer = basicCustomerManager.getById(Integer.valueOf(Integer.parseInt(customerId)));
        Map conditions = new HashMap();
        getConditionAndOrder(queryForm, conditions, request);

        if (queryForm.isFirstInit()) {
            queryForm.init(manager.getBoxItemListCount(conditions));
        } else {
            queryForm.init();
        }
        List<PoPartSumNumber> result = manager.getBoxItemList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                PoPartSumNumberQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
        if (basicCustomer != null) {
            for (PoPartSumNumber item : result) {
                conditions = new HashMap<Object, Object>();
                conditions.put(BasicPartPriceQueryCondition.PARTID_EQ, item.getPart().getId());
                conditions.put(BasicPartPriceQueryCondition.STARTDATE_LE, format.format(new Date()));
                conditions.put(BasicPartPriceQueryCondition.EXPIREDATE_GE, format.format(new Date()));

                conditions.put(BasicPartPriceQueryCondition.CUSTOMER_EQ, basicCustomer.getCode());
                List<BasicPartPrice> partPriceList = basicPartPriceManager.getList(conditions, 0, -1, BasicPartPriceQueryOrder.ID, true);
                if (partPriceList.size() > 0) {
                    BasicPartPrice basicPartPrice = partPriceList.get(0);
                    if (basicPartPrice.getAmt() != null) {
                        item.getPart().setPartPrice(basicPartPrice);
                    }
                }
            }
        }

        if (idString != null &&
                !idString.equals("")) {
            String[] ids = idString.split(",");
            if (ids.length > 0) {
                for (PoPartSumNumber item : result) {
                    String partId = item.getPart().getId();
                    Boolean isClose = Boolean.valueOf(false);

                    for (int i = 0; i < ids.length; i++) {
                        if (!ids[i].equals("") &&
                                partId.equals(ids[i])) {
                            isClose = Boolean.valueOf(true);
                        }
                    }


                    if (isClose.booleanValue()) {
                        item.getPart().setChecked("checked");
                    }
                }
            }
        }


        request.setAttribute("x_customerId", customerId);
        request.setAttribute("x_selType", Integer.valueOf(132));
        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsPartQueryForm queryForm = (WmsPartQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(WmsPartQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        WmsPartManager fm = ServiceLocator.getWmsPartManager(request);

        Map conditions = constructQueryMap(queryForm);

        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);

        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = fm.getWmsPartList(conditions, 0, -1, WmsPartQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());

            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "wmsPart";
            String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
            {
                public void exportHead(List row, HttpServletRequest request) throws Exception
                {
                    row.add("ASN号");
                    row.add("物料编号");

                    row.add("描述1");
                    row.add("描述2");
                    row.add("类型");
                    row.add("采购供应商编码");
                    row.add("产品类");
                    row.add("标准包装量");
                    row.add("高储库存");
                    row.add("低储库存");
                    row.add("安全库存");
                    row.add("单位");
                    row.add("是否冻结");
                    row.add("状态");
                }

                public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                    row.add(BeanHelper.getBeanPropertyValue(data, "productSpecifications"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "id"));

                    row.add(BeanHelper.getBeanPropertyValue(data, "describe1"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "describe2"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "drwgLoc"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "vend"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "productClass"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "ord_mult"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "highQty"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "lowQty"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "securityQty"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "unit"));
                    WmsPart part = (WmsPart)data;
                    if (part.getFreeze_status() == YesNo.NO) {
                        row.add("No");
                    } else if (part.getFreeze_status() == YesNo.YES) {
                        row.add("Yes");
                    } else {
                        row.add("");
                    }
                    if (part.getEnabled() == EnabledDisabled.DISABLED) {
                        row.add("不可用");
                    } else if (part.getEnabled() == EnabledDisabled.ENABLED) {
                        row.add("可用");
                    } else {
                        row.add("");
                    }
                }
            });































            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getWmsPartListCount(conditions));
        } else {
            queryForm.init();
        }
        int pageNo = queryForm.getPageNoAsInt();
        int pageSize = queryForm.getPageSizeAsInt();
        List result = fm.getWmsPartList(conditions, pageNo, pageSize,
                WmsPartQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(11));
        request.setAttribute("x_selType1", Integer.valueOf(102));
        putEnumListToRequestNoSite(request);
        return mapping.findForward("page");
    }


    public ActionForward listPartBySampling(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsPartQueryForm queryForm = (WmsPartQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(WmsPartQueryOrder.ID.getName());
            queryForm.setDescend(false);
            queryForm.setStatus("0");
        }
        WmsPartManager fm = ServiceLocator.getWmsPartManager(request);

        Map conditions = constructQueryMap(queryForm);
        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = fm.getWmsPartList(conditions, 0, -1, WmsPartQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());

            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "wmsPart";
            String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
            {
                public void exportHead(List row, HttpServletRequest request) throws Exception {
                    MessageResources messages = WmsPartAction.this.getResources(request);
                    row.add(messages.getMessage(WmsPartAction.this.getLocale(request), "wmsPart.id"));
                }

                public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                    row.add(BeanHelper.getBeanPropertyValue(data, "id"));
                }
            });

            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getWmsPartListCount(conditions));
        } else {
            queryForm.init();
        }
        int pageNo = queryForm.getPageNoAsInt();
        int pageSize = queryForm.getPageSizeAsInt();
        List result = fm.getWmsPartList(conditions, pageNo, pageSize, WmsPartQueryOrder.ID, queryForm.isDescend());
        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    public ActionForward listPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsPartQueryForm queryForm = (WmsPartQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(WmsPartQueryOrder.ID.getName());
            queryForm.setDescend(false);
            queryForm.setStatus("0");
        }
        WmsPartManager fm = ServiceLocator.getWmsPartManager(request);

        Map conditions = constructQueryMap(queryForm);
        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = fm.getWmsPartList(conditions, 0, -1, WmsPartQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());

            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "wmsPart";
            String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
            {
                public void exportHead(List row, HttpServletRequest request) throws Exception {
                    MessageResources messages = WmsPartAction.this.getResources(request);
                    row.add(messages.getMessage(WmsPartAction.this.getLocale(request), "wmsPart.id"));
                }

                public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                    row.add(BeanHelper.getBeanPropertyValue(data, "id"));
                }
            });

            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getWmsPartListCount(conditions));
        } else {
            queryForm.init();
        }
        int pageNo = queryForm.getPageNoAsInt();
        int pageSize = queryForm.getPageSizeAsInt();
        List result = fm.getWmsPartList(conditions, pageNo, pageSize, WmsPartQueryOrder.ID, queryForm.isDescend());
        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }

    private Map constructQueryMap(WmsPartQueryForm queryForm) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        String id = queryForm.getId();
        if (id != null && !id.equals("")) {
            conditions.put(WmsPartQueryCondition.ID_EQ, id);
        }
        String productClass = queryForm.getProductClass();
        if (productClass != null && !productClass.equals("")) {
            conditions.put(WmsPartQueryCondition.ID_EQ, productClass);
        }
        String qualityType = queryForm.getQualityType();
        if (qualityType != null && !qualityType.equals("")) {
            conditions.put(WmsPartQueryCondition.QUALITYPE, qualityType);
        }
        String group = queryForm.getGroup();
        if (group != null && !group.equals("")) {
            conditions.put(WmsPartQueryCondition.GROUP, group);
        }
        String name = queryForm.getName();
        if (name != null && !name.equals("")) {
            conditions.put(WmsPartQueryCondition.NAME_EQ, name);
        }
        String describe1 = queryForm.getDescribe1();
        if (describe1 != null && !describe1.equals("")) {
            conditions.put(WmsPartQueryCondition.DESCRIBE1_EQ, describe1);
        }
        String describe2 = queryForm.getDescribe2();
        if (describe2 != null && !describe2.equals("")) {
            conditions.put(WmsPartQueryCondition.DESCRIBE2_EQ, describe2);
        }
        String unit = queryForm.getUnit();
        if (unit != null && !unit.equals("")) {
            conditions.put(WmsPartQueryCondition.UNIT_EQ, unit);
        }
        String status = queryForm.getStatus();
        if (status != null && !status.equals("")) {
            conditions.put(WmsPartQueryCondition.ENABLED_EQ, status);
        }




        return conditions;
    }
    private WmsPart getWmsPart(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        WmsPartManager wmsPartManager = ServiceLocator.getWmsPartManager(request);
        WmsPart wmsPart = wmsPartManager.getWmsPart(id);
        if (wmsPart == null)
            throw new ActionException("wmsPart.notFound", id);
        return wmsPart;
    }

    private void putEnumListToRequest(HttpServletRequest request) {
        request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
        request.setAttribute("X_SITEID", ServiceLocator.getSiteManager(request).getAllEnabledSiteList());
        request.setAttribute("X_PartType", PersistentEnum.getEnumList(WmsPartType.class));
        request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
    }

    private void putEnumListToRequestNoSite(HttpServletRequest request) {
        request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
        request.setAttribute("X_PartType", PersistentEnum.getEnumList(WmsPartType.class));
        request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
    }













    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsPart wmsPart = getWmsPart(request);

        request.setAttribute("x_wmsPart", wmsPart);
        if (!isBack(request)) {

            BeanForm wmsPartForm = (BeanForm)getForm("/updateWmsPart", request);
            wmsPartForm.populate(wmsPart, "to_form");
        }
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }












    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsPart wmsPart = getWmsPart(request);
        WmsPartManager cm = ServiceLocator.getWmsPartManager(request);
        try {
            cm.deleteWmsPart(wmsPart);
        }
        catch (Throwable t) {

            throw new ActionException("wmsPart.delete.fail");
        }
        return mapping.findForward("success");
    }













    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BeanForm wmsPartForm = (BeanForm)form;
        WmsPart wmsPart = new WmsPart();
        wmsPartForm.populate(wmsPart, "to_bean");
        WmsPartManager wmsPartManager = ServiceLocator.getWmsPartManager(request);

        request.setAttribute("X_OBJECT", wmsPartManager.updateWmsPart(wmsPart));
        request.setAttribute("X_ROWPAGE", "wmsbasic/wmsPart/row.jsp");
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
            WmsPart wmsPart = new WmsPart();
            BeanForm wmsPartForm = (BeanForm)getForm("/insertWmsPart", request);
            wmsPartForm.populate(wmsPart, "to_form");
        }

        putEnumListToRequest(request);
        return mapping.findForward("page");
    }













    public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsPartManager cm = ServiceLocator.getWmsPartManager(request);
        BeanForm wmsPartForm = (BeanForm)form;
        WmsPart wmsPart = new WmsPart();
        wmsPartForm.populate(wmsPart, "to_bean");

        if (cm.getWmsPart(wmsPart.getId()) != null) {
            throw new ActionException("errors.partCode.existing");
        }

        request.setAttribute("X_OBJECT", cm.insertWmsPart(wmsPart));
        request.setAttribute("X_ROWPAGE", "wmsbasic/wmsPart/row.jsp");
        return mapping.findForward("success");
    }




    public ActionForward rqcTypelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsPartQueryForm queryForm = (WmsPartQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(WmsPartQueryOrder.ID.getName());
            queryForm.setDescend(false);
            queryForm.setStatus("0");
        }
        WmsPartManager fm = ServiceLocator.getWmsPartManager(request);
        Map conditions = constructQueryMap(queryForm);
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getWmsPartListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getWmsPartList(conditions, queryForm.getPageNoAsInt(),
                queryForm.getPageSizeAsInt(), WmsPartQueryOrder.ID,
                queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward rqcTypeEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsPart wmsPart = getWmsPart(request);
        request.setAttribute("x_wmsPart", wmsPart);
        if (!isBack(request)) {
            BeanForm wmsPartForm = (BeanForm)getForm("/updateWmsPart", request);
            wmsPartForm.populate(wmsPart, "to_form");
        }

        putEnumListToRequest(request);
        return mapping.findForward("page");
    }





    public ActionForward listFinishedMaterial(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsPartQueryForm queryForm = (WmsPartQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(WmsPartQueryOrder.ID.getName());
            queryForm.setDescend(false);
            queryForm.setStatus("0");
        }
        WmsPartManager fm = ServiceLocator.getWmsPartManager(request);
        Map conditions = constructQueryMap(queryForm);
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getWmsPartListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = fm.getWmsPartList(conditions, queryForm.getPageNoAsInt(),
                queryForm.getPageSizeAsInt(), WmsPartQueryOrder.ID,
                queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward editFinishedMaterial(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsPart wmsPart = getWmsPart(request);
        request.setAttribute("x_wmsPart", wmsPart);
        if (!isBack(request)) {
            BeanForm wmsPartForm = (BeanForm)getForm("/updateFinishedMaterial", request);
            wmsPartForm.populate(wmsPart, "to_form");
        }

        putEnumListToRequest(request);
        return mapping.findForward("page");
    }



    public ActionForward printBc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsPartManager manager = ServiceLocator.getWmsPartManager(request);
        String arrays = request.getParameter("ids");
        String[] str = arrays.split(",");
        List<WmsPart> parts = new ArrayList<WmsPart>(); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = str).length, b = 0; b < i; ) { String id = arrayOfString1[b];
            WmsPart part = manager.getWmsPart(id);
            parts.add(part);
            b++; }

        request.setAttribute("x_parts", parts);
        request.setAttribute("path", request.getContextPath());
        return mapping.findForward("page");
    }
    private Map constructQueryMaps(BasicDataViewQueryForm queryForm) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        return conditions;
    }


    public ActionForward selectWmsPartStocking(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BasicDataViewQueryForm queryForm = (BasicDataViewQueryForm)form;
        BasicDataViewManager manager = ServiceLocator.getBasicDataViewManager(request);

        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(SkPartSumNumberQueryOrder.PART_ID.getName());
            queryForm.setDescend(false);
        }
        Map conditions = constructQueryMaps(queryForm);


        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);


        if (queryForm.isFirstInit()) {
            queryForm.init(manager.getSkPartSumNumberListCount(conditions));
        } else {
            queryForm.init();
        }
        List<SkPartSumNumber> result = manager.getSkPartSumNumberList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                SkPartSumNumberQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);
        request.setAttribute("x_selType", Integer.valueOf(154));
        return mapping.findForward("page");
    }







    public ActionForward selectWmsPartShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        getConditionAndOrder(queryForm, conditions, request);
        SupplierManager managera = ServiceLocator.getSupplierManager(request);
        User user = getCurrentUser(request);
        Supplier supplier = managera.getSupplierByCode(user.getLoginName());
        if (supplier != null) {
            conditions.put(JitShipPartQueryCondition.PART_VEND_EQ, supplier.getCode());
        } else {
            conditions.put(JitShipPartQueryCondition.PART_VEND_EQ, "0");
        }
        conditions.put(JitShipPartQueryCondition.PART_ENABLED_EQ, Integer.valueOf(0));
        conditions.put(JitShipPartQueryCondition.PART_FREEZE_STATUS_EQ, Integer.valueOf(1));
        conditions.put(JitShipPartQueryCondition.PART_PRODUCTCLASS_EQ, "JIT");

        if (queryForm.isFirstInit()) {
            queryForm.init(manager.getJitShipPartListCount(conditions));
        } else {
            queryForm.init();
        }
        List<JitShipPart> result = manager.getJitShipPartNumberList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                JitShipPartQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
        for (JitShipPart ship : result) {
            if (ship.getQty().compareTo(new BigDecimal(0)) == -1) {
                ship.setQty(new BigDecimal(0));
            }
        }

        if (idString != null &&
                !idString.equals("")) {
            String[] ids = idString.split(",");
            if (ids.length > 0) {
                for (JitShipPart ship : result) {
                    String partId = ship.getPart().getId();
                    Boolean isClose = Boolean.valueOf(false);

                    for (int i = 0; i < ids.length; i++) {
                        if (!ids[i].equals("") &&
                                partId.equals(ids[i])) {
                            isClose = Boolean.valueOf(true);
                        }
                    }


                    if (isClose.booleanValue()) {
                        ship.getPart().setChecked("checked");
                    }
                }
            }
        }

        request.setAttribute("portalShipJitPartId", idString);
        request.setAttribute("X_RESULTLIST", result);
        return mapping.findForward("page");
    }

    private Map selectWmsPartByPortalShipOrderQueryMaps(Integer dayInt) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        if (dayInt.intValue() >= 0 && dayInt.intValue() <= 44) {
            if (dayInt.intValue() == 0) {
                conditions.put(PartForecastNeedReportQueryCondition.NO0NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 1) {
                conditions.put(PartForecastNeedReportQueryCondition.NO1NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 2) {
                conditions.put(PartForecastNeedReportQueryCondition.NO2NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 3) {
                conditions.put(PartForecastNeedReportQueryCondition.NO3NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 4) {
                conditions.put(PartForecastNeedReportQueryCondition.NO4NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 5) {
                conditions.put(PartForecastNeedReportQueryCondition.NO5NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 6) {
                conditions.put(PartForecastNeedReportQueryCondition.NO6NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 7) {
                conditions.put(PartForecastNeedReportQueryCondition.NO7NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 8) {
                conditions.put(PartForecastNeedReportQueryCondition.NO8NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 9) {
                conditions.put(PartForecastNeedReportQueryCondition.NO9NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 10) {
                conditions.put(PartForecastNeedReportQueryCondition.NO10NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 11) {
                conditions.put(PartForecastNeedReportQueryCondition.NO11NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 12) {
                conditions.put(PartForecastNeedReportQueryCondition.NO12NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 13) {
                conditions.put(PartForecastNeedReportQueryCondition.NO13NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 14) {
                conditions.put(PartForecastNeedReportQueryCondition.NO14NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 15) {
                conditions.put(PartForecastNeedReportQueryCondition.NO15NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 16) {
                conditions.put(PartForecastNeedReportQueryCondition.NO16NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 17) {
                conditions.put(PartForecastNeedReportQueryCondition.NO17NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 18) {
                conditions.put(PartForecastNeedReportQueryCondition.NO18NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 19) {
                conditions.put(PartForecastNeedReportQueryCondition.NO19NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 20) {
                conditions.put(PartForecastNeedReportQueryCondition.NO20NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 21) {
                conditions.put(PartForecastNeedReportQueryCondition.NO21NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 22) {
                conditions.put(PartForecastNeedReportQueryCondition.NO22NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 23) {
                conditions.put(PartForecastNeedReportQueryCondition.NO23NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 24) {
                conditions.put(PartForecastNeedReportQueryCondition.NO24NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 25) {
                conditions.put(PartForecastNeedReportQueryCondition.NO25NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 26) {
                conditions.put(PartForecastNeedReportQueryCondition.NO26NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 27) {
                conditions.put(PartForecastNeedReportQueryCondition.NO27NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 28) {
                conditions.put(PartForecastNeedReportQueryCondition.NO28NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 29) {
                conditions.put(PartForecastNeedReportQueryCondition.NO29NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 30) {
                conditions.put(PartForecastNeedReportQueryCondition.NO30NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 31) {
                conditions.put(PartForecastNeedReportQueryCondition.NO31NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 32) {
                conditions.put(PartForecastNeedReportQueryCondition.NO32NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 33) {
                conditions.put(PartForecastNeedReportQueryCondition.NO33NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 34) {
                conditions.put(PartForecastNeedReportQueryCondition.NO34NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 35) {
                conditions.put(PartForecastNeedReportQueryCondition.NO35NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 36) {
                conditions.put(PartForecastNeedReportQueryCondition.NO36NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 37) {
                conditions.put(PartForecastNeedReportQueryCondition.NO37NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 38) {
                conditions.put(PartForecastNeedReportQueryCondition.NO38NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 39) {
                conditions.put(PartForecastNeedReportQueryCondition.NO39NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 40) {
                conditions.put(PartForecastNeedReportQueryCondition.NO40NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 41) {
                conditions.put(PartForecastNeedReportQueryCondition.NO41NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 42) {
                conditions.put(PartForecastNeedReportQueryCondition.NO42NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 43) {
                conditions.put(PartForecastNeedReportQueryCondition.NO43NEEDQTY_GT, Integer.valueOf(0));
            } else if (dayInt.intValue() == 44) {
                conditions.put(PartForecastNeedReportQueryCondition.NO44NEEDQTY_GT, Integer.valueOf(0));
            }
        } else {
            conditions.put(PartForecastNeedReportQueryCondition.PART_ID_EQ, "0");
        }
        return conditions;
    }


    public ActionForward selectWmsPartByPortalShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WmsPartQueryForm queryForm = (WmsPartQueryForm)form;
        WmsPartManager manager = ServiceLocator.getWmsPartManager(request);
        BasicDataViewManager viewManager = ServiceLocator.getBasicDataViewManager(request);
        NjitNpoPlanManager njitNpoPlanManager = ServiceLocator.getNjitNpoPlanManager(request);
        PortalShipOrderManager shipOrderManager = ServiceLocator.getPortalShipOrderManager(request);
        String idString = request.getParameter("portalShipOrderByPartId");
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Map<Object, Object> map = new HashMap<Object, Object>();
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(WmsPartQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        String supplierCode = request.getParameter("supplierCode");

        String attivlDate = request.getParameter("receivingDate");
        String date = format.format(new Date());

        Calendar cal = Calendar.getInstance();
        cal.setTime(format.parse(date));
        long time1 = cal.getTimeInMillis();
        cal.setTime(format.parse(attivlDate));
        long time2 = cal.getTimeInMillis();
        long day = (time2 - time1) / 86400000L;

        String dayStr = String.valueOf(day);

        Map conditions = new HashMap();

        getConditionAndOrder(queryForm, conditions, request);

        conditions.put(PartForecastNeedReportQueryCondition.PART_ENABLED_EQ, Integer.valueOf(0));
        conditions.put(PartForecastNeedReportQueryCondition.PART_FREEZE_STATUS_EQ, Integer.valueOf(1));
        conditions.put(PartForecastNeedReportQueryCondition.PART_PRODUCTCLASS_EQ, "NJIT");
        SupplierManager managera = ServiceLocator.getSupplierManager(request);

        if (supplierCode != null && !supplierCode.trim().equals("")) {
            conditions.put(PartForecastNeedReportQueryCondition.PART_SUPPLIER_EQ, supplierCode);
        } else {
            conditions.put(PartForecastNeedReportQueryCondition.PART_SUPPLIER_EQ, "0");
        }
        List<PartForecastNeedReport> partForecas = viewManager.getPartForecastNeedReportList(conditions, 0, -1, PartForecastNeedReportQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
        List<PartForecastNeedReport> result = new ArrayList<PartForecastNeedReport>();
        for (PartForecastNeedReport partForecastNeedReport : partForecas) {

            Class<?> cla = partForecastNeedReport.getClass();
            Field[] field = partForecastNeedReport.getClass().getDeclaredFields();

            String[] modelName = new String[field.length];
            for (int i = 0; i < field.length; i++) {

                String pinjie = "projected" + dayStr + "Qty";

                String name = field[i].getName();
                if (name.equals(pinjie)) {
                    name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());
                    Method m = partForecastNeedReport.getClass().getMethod("get" + name, new Class[0]);

                    Method m1 = partForecastNeedReport.getClass().getMethod("getNo" + dayStr + "Needqty", new Class[0]);

                    Method m2 = partForecastNeedReport.getClass().getMethod("getNo" + dayStr + "Knotqty", new Class[0]);
                    BigDecimal value = (BigDecimal)m.invoke(partForecastNeedReport, new Object[0]);
                    BigDecimal xuQiu = (BigDecimal)m1.invoke(partForecastNeedReport, new Object[0]);
                    BigDecimal weiJie = (BigDecimal)m2.invoke(partForecastNeedReport, new Object[0]);



                    BigDecimal bg = new BigDecimal(0);

                    partForecastNeedReport.setProjectedQty(value);
                    if (xuQiu == null || xuQiu.equals(Integer.valueOf(0))) {
                        xuQiu = new BigDecimal("0.000000");
                        partForecastNeedReport.setNoNeedqty(xuQiu);
                    } else {
                        if (weiJie != null) {
                            xuQiu = xuQiu.subtract(weiJie);
                        }
                        if (xuQiu.compareTo(bg) == -1) {
                            partForecastNeedReport.setNoNeedqty(bg);
                        } else {
                            partForecastNeedReport.setNoNeedqty(xuQiu);
                        }
                    }


                    result.add(partForecastNeedReport);


















                    break;
                }
            }
        }

















        for (PartForecastNeedReport partForecastNeedReport : result) {
            NjitNpoPlan njitNpoPlan = njitNpoPlanManager.getNjitNpoPlanByPart(partForecastNeedReport.getPart().getId());
            if (njitNpoPlan != null) {
                partForecastNeedReport.part.setNeedQty(njitNpoPlan.getQty()); continue;
            }
            partForecastNeedReport.part.setNeedQty(new BigDecimal(0));
        }


        if (idString != null &&
                !idString.equals("")) {
            String[] ids = idString.split(",");
            if (ids.length > 0) {
                for (PartForecastNeedReport partForecastNeedReport : result) {
                    String partId = partForecastNeedReport.getPart().getId();
                    Boolean isClose = Boolean.valueOf(false);

                    for (int i = 0; i < ids.length; i++) {
                        if (!ids[i].equals("") &&
                                partId.equals(ids[i])) {
                            isClose = Boolean.valueOf(true);
                        }
                    }


                    if (isClose.booleanValue()) {
                        partForecastNeedReport.part.setChecked("checked");
                    }
                }
            }
        }


        int totalCount = result.size();

        int pageCount = 0;

        int endNum = queryForm.getPageSizeAsInt();
        if (endNum == -1) {
            endNum = result.size();
        }

        int startNum = queryForm.getPageNoAsInt() + 1;

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


                        result = result.subList(0, endNum);
                    }
                } else {

                    int fromIndex = (startNum - 1) * endNum;

                    int toIndex = startNum * endNum;

                    if ((totalCount - toIndex) % endNum >= 0) {
                        toIndex = startNum * endNum;
                    } else {
                        toIndex = (startNum - 1) * endNum + totalCount % endNum;
                    }
                    if (totalCount >= toIndex) {
                        result = result.subList(fromIndex, toIndex);
                    }
                }
            } else {
                result = null;
            }
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(totalCount);
        } else {
            queryForm.init();
        }
        request.setAttribute("portalShipOrderByPartId", idString);
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("receivingDate", attivlDate);
        request.setAttribute("supplierCode", supplierCode);
        return mapping.findForward("page");
    }
}
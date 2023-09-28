package com.aof.web.struts.action.basic;

import com.aof.model.admin.Site;
import com.aof.model.admin.User;
import com.aof.model.basic.CustomerReturnItem;
import com.aof.model.basic.Customerreturns;
import com.aof.model.basic.query.CustomerReturnItemQueryCondition;
import com.aof.model.basic.query.CustomerReturnItemQueryOrder;
import com.aof.model.basic.query.CustomerreturnsQueryCondition;
import com.aof.model.basic.query.CustomerreturnsQueryOrder;
import com.aof.model.basic.query.StorageLocationQueryCondition;
import com.aof.model.basic.query.StorageLocationQueryOrder;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.model.metadata.SalesPreshiporderBatchStatus;
import com.aof.model.metadata.YesNo;
import com.aof.model.product.BasicCustomer;
import com.aof.model.product.SalesWorkorder;
import com.aof.model.product.query.BasicCustomerQueryCondition;
import com.aof.model.product.query.BasicCustomerQueryOrder;
import com.aof.model.product.query.SalesWorkorderQueryCondition;
import com.aof.model.product.query.SalesWorkorderQueryOrder;
import com.aof.service.Product.BasicCustomerManager;
import com.aof.service.Product.SalesWorkorderManager;
import com.aof.service.admin.SiteManager;
import com.aof.service.basic.CustomerReturnItemManager;
import com.aof.service.basic.CustomerreturnsManager;
import com.aof.service.basic.StorageLocationManager;
import com.aof.service.basic.StoreRoomManager;
import com.aof.service.po.PurchaseOrderPutInStorageManager;
import com.aof.utils.SessionTempFile;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.basic.CustomerReturnItemQueryForm;
import com.aof.web.struts.form.basic.CustomerreturnsQueryForm;
import com.aof.web.struts.form.basic.StorageLocationQueryForm;
import com.aof.web.struts.form.product.BasicCustomerQueryForm;
import com.shcnc.hibernate.PersistentEnum;
import com.shcnc.struts.form.BeanForm;
import com.shcnc.struts.form.beanloader.BeanLoader;
import com.shcnc.utils.BeanUtils;
import com.shcnc.utils.ExportUtil;
import com.shcnc.utils.Exportable;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

public class CustomerreturnsAction extends BaseAction2 {
    private Map getConditions(CustomerreturnsQueryForm formBean) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        String returnNumber = formBean.getReturnNumber();
        if (returnNumber != null && returnNumber.trim().length() != 0)
            conditions.put(CustomerreturnsQueryCondition.RETURNNUMBER_EQ, returnNumber);
        conditions.put(CustomerreturnsQueryCondition.DELSTATUS_EQ, Integer.valueOf(1));
        return conditions;
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
        SiteManager siteManager = ServiceLocator.getSiteManager(request);
        CustomerreturnsQueryForm formBean = (CustomerreturnsQueryForm)form;
        Map conditions = getConditions(formBean);
        getConditionAndOrder(formBean, conditions, request);
        List<Site> siteList = getAndCheckGrantedSiteList(request);
        List<Site> sitesList = siteManager.getAllEnabledSiteList();
        if (sitesList.size() != siteList.size()) {
            Site site = siteList.get(0);
            conditions.put(CustomerreturnsQueryCondition.CUSTOMER_SITE_ID_EQ, site.getId());
        }
        String exportType = formBean.getExportType();
        if (exportType != null && exportType.length() > 0) {
            List datas = manager.getList(conditions, 0, -1, CustomerreturnsQueryOrder.getEnum(formBean.getOrder()),
                    formBean.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "Customerreturns";
            String suffix = ExportUtil.export(exportType, datas, request,
                    new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable() {
                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanUtils.getProperty(data, "id"));
                        }

                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources message = CustomerreturnsAction.this.getResources(request);
                            row.add(message.getMessage(CustomerreturnsAction.this.getLocale(request), "mapping.id"));
                        }
                    });
            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
        }
        if (formBean.isFirstInit()) {
            formBean.init(manager.getListCount(conditions).intValue());
        } else {
            formBean.init();
        }
        int pageNum = formBean.getPageNoAsInt();
        int pageSize = formBean.getPageSizeAsInt();
        List entityList = manager.getList(conditions, pageNum, pageSize, CustomerreturnsQueryOrder.getEnum(formBean.getOrder()),
                formBean.isDescend());
        request.setAttribute("x_selType", Integer.valueOf(136));
        putEnumListToRequest(request);
        request.setAttribute("X_RESULTLIST", entityList);
        return mapping.findForward("page");
    }

    public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            int num = 0;
            num++;
            String numberStr = "";
            Map<Object, Object> conditions = new HashMap<Object, Object>();
            StringBuffer buffer = new StringBuffer();
            SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
            Date date = new Date();
            BeanForm formBean = (BeanForm)form;
            Customerreturns entity = new Customerreturns();
            formBean.populateToBean(entity);
            CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
            BasicCustomerManager basicCustomerManager = ServiceLocator.getBasicCustomerManager(request);
            SalesWorkorderManager workorderManager = ServiceLocator.getSalesWorkorderManager(request);
            List<SalesWorkorder> workorderList = new ArrayList<SalesWorkorder>();
            CustomerReturnItemManager customerReturnItemManager = ServiceLocator.getCustomerReturnItemManager(request);
            CustomerReturnItem returnitem = new CustomerReturnItem();
            BasicCustomer basicCustomers = basicCustomerManager.getById(entity.getBasicCustomer().getId());
            entity.setDelStatus(Integer.valueOf(1));
            buffer.append(basicCustomers.getCode());
            buffer.append(format.format(date));
            conditions.put(CustomerreturnsQueryCondition.RETURNNUMBER_LIKE, buffer);
            List<Customerreturns> customerList = manager.getList(conditions, 0, 1, CustomerreturnsQueryOrder.ID, true);
            String str = String.valueOf(num);
            if (customerList == null || customerList.size() == 0) {
                for (int i = 0; i < 3 - str.length(); i++)
                    numberStr = String.valueOf(numberStr) + "0";
                buffer.append(numberStr);
                buffer.append(num);
            } else {
                String returnNumber = ((Customerreturns)customerList.get(0)).getReturnNumber();
                String LSH = returnNumber.substring(14, returnNumber.length());
                num = Integer.valueOf(LSH).intValue();
                num++;
                String strLSH = String.valueOf(num);
                for (int i = 0; i < 3 - strLSH.length(); i++)
                    numberStr = String.valueOf(numberStr) + "0";
                buffer.append(numberStr);
                buffer.append(num);
            }
            String chanpinCode = entity.getChanpinCode();
            Map<Object, Object> map = new HashMap<Object, Object>();
            String[] strCode = null;
            strCode = chanpinCode.split(",");
            map.put(SalesWorkorderQueryCondition.ID_IN, strCode);
            workorderList = workorderManager.getList(map, 0, -1, null, false);
            entity.setReturnNumber(buffer.toString());
            entity.setCustomerDescription(basicCustomers.getName2());
            request.setAttribute("X_OBJECT", manager.save(entity));
            for (SalesWorkorder order : workorderList) {
                returnitem.setCustomerreturns(entity);
                returnitem.setBatchNumber(order.getLotSer().getId());
                returnitem.setPart(order.getPart());
                returnitem.setMaterialDescription(order.getPart().getDescribe1());
                returnitem.setQty(Integer.valueOf(order.getCount().intValue()));
                returnitem.setReturnStorage(entity.getReturnStorage());
                returnitem.setSalesDeliveryDate(order.getOutDate());
                returnitem.setWorkorderId(order);
                customerReturnItemManager.save(returnitem);
                order.setStatus1(SalesPreshiporderBatchStatus.SALES_RETURN);
                workorderManager.update(order);
            }
            return new ActionForward("/listCustomerreturnsAction.do");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
        Customerreturns entity = new Customerreturns();
        putEnumListToRequest(request);
        request.setAttribute("X_OBJECT", entity);
        return mapping.findForward("page");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
        BeanForm formBean = (BeanForm)form;
        Customerreturns entity = new Customerreturns();
        formBean.setBeanLoader((BeanLoader)ServiceLocator.getBeanLoader(request));
        formBean.populateToBean(entity, request);
        request.setAttribute("X_OBJECT", manager.update(entity));
        request.setAttribute("X_ROWPAGE", "customerreturns/row.jsp");
        return mapping.findForward("success");
    }

    public ActionForward updateDelStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
            CustomerReturnItemManager customerReturnItemManager = ServiceLocator.getCustomerReturnItemManager(request);
            SalesWorkorderManager workorderManager = ServiceLocator.getSalesWorkorderManager(request);
            Customerreturns entity = new Customerreturns();
            String id = request.getParameter("id");
            entity = manager.getCustomerreturns(Integer.valueOf(Integer.parseInt(id)));
            entity.setDelStatus(Integer.valueOf(0));
            Map<Object, Object> conditions = new HashMap<Object, Object>();
            conditions.put(CustomerReturnItemQueryCondition.CUSTOMERRETURNS_ID_EQ, entity.getId());
            List<CustomerReturnItem> customerReturnItemList = customerReturnItemManager.getList(conditions, 0, -1, CustomerReturnItemQueryOrder.ID, false);
            for (CustomerReturnItem customerReturnItem : customerReturnItemList) {
                SalesWorkorder salesWorkorder = customerReturnItem.getWorkorderId();
                salesWorkorder.setStatus1(SalesPreshiporderBatchStatus.IN_DELIVERYBATCH);
                workorderManager.update(salesWorkorder);
            }
            request.setAttribute("X_OBJECT", manager.update(entity));
            return new ActionForward("/listCustomerreturnsAction.do");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
        CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
        Customerreturns customerreturns = manager.getCustomerreturns(id);
        manager.delete(customerreturns);
        return new ActionForward("listCustomerreturnsAction.do", true);
    }

    public ActionForward itemlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CustomerreturnsQueryForm customerqueryform = (CustomerreturnsQueryForm)form;
        SalesWorkorderManager workorderManager = ServiceLocator.getSalesWorkorderManager(request);
        List<SalesWorkorder> workorderList = new ArrayList<SalesWorkorder>();
        String chanpinId = request.getParameter("chanpinId");
        Map map = new HashMap();
        String[] str = null;
        str = chanpinId.split(",");
        map.put(SalesWorkorderQueryCondition.ID_IN, str);
        getConditionAndOrder(customerqueryform, map, request);
        if (customerqueryform.isFirstInit()) {
            customerqueryform.init(workorderManager.getListCount(map));
        } else {
            customerqueryform.init();
        }
        int pageNum = customerqueryform.getPageNoAsInt();
        int pageSize = customerqueryform.getPageSizeAsInt();
        workorderList = workorderManager.getList(map, pageNum, pageSize, null, false);
        putEnumListToRequest(request);
        request.setAttribute("workOrderList", workorderList);
        request.setAttribute("chanpin_id", chanpinId);
        return mapping.findForward("page");
    }

    public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            CustomerreturnsQueryForm customerqueryform = (CustomerreturnsQueryForm)form;
            SalesWorkorderManager workorderManager = ServiceLocator.getSalesWorkorderManager(request);
            List<SalesWorkorder> workorderList = new ArrayList<SalesWorkorder>();
            String chanpinId = request.getParameter("chanpinId");
            String[] str = null;
            Map<Object, Object> map = new HashMap<Object, Object>();
            if (chanpinId != null) {
                str = chanpinId.split(",");
                map.put(SalesWorkorderQueryCondition.ID_IN, str);
                workorderList = workorderManager.getList(map, customerqueryform.getPageNoAsInt(), customerqueryform.getPageSizeAsInt(), null, false);
                putEnumListToRequest(request);
                if (customerqueryform.isFirstInit()) {
                    customerqueryform.init(workorderList.size());
                } else {
                    customerqueryform.init();
                }
                request.setAttribute("workOrderList", workorderList);
                request.setAttribute("chanpin_id", chanpinId);
                return mapping.findForward("page");
            }
            customerqueryform.setPageSize("1");
            customerqueryform.init(2);
            System.out.println("123");
            putEnumListToRequest(request);
            request.setAttribute("workOrderList", workorderList);
            request.setAttribute("chanpin_id", chanpinId);
            return mapping.findForward("page");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ActionForward listCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BasicCustomerManager basicCustomerManager = ServiceLocator.getBasicCustomerManager(request);
        BasicCustomerQueryForm queryForm = (BasicCustomerQueryForm)form;
        Map conditions = getQueryConditions(queryForm);
        getConditionAndOrder(queryForm, conditions, request);
        List<Site> siteList = getAndCheckGrantedSiteList(request);
        Site site = siteList.get(0);
        if (site.getId().intValue() != 1)
            conditions.put(BasicCustomerQueryCondition.SITEID_ID_EQ, site.getId());
        String exportType = queryForm.getExportType();
        if (exportType != null && exportType.length() > 0) {
            List data = basicCustomerManager.getList(conditions, 0, -1, null, false);
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "BasicCustomer";
            String suffix = ExportUtil.export(exportType, data, request,
                    new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable() {
                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources messages = CustomerreturnsAction.this.getResources(request);
                            row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.code"));
                            row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.name1"));
                            row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.name2"));
                            row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.address"));
                            row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.address2"));
                            row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.address3"));
                            row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.country"));
                            row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.city"));
                            row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.fax"));
                            row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.post_id"));
                            row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.currency_type"));
                            row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.contacts"));
                            row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.phone"));
                            row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.email"));
                            row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.product"));
                            row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.site"));
                            row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.domain"));
                            row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.remarks"));
                            row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.enabled"));
                        }

                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanUtils.getProperty(data, "code"));
                            row.add(BeanUtils.getProperty(data, "name1"));
                            row.add(BeanUtils.getProperty(data, "name2"));
                            row.add(BeanUtils.getProperty(data, "address"));
                            row.add(BeanUtils.getProperty(data, "address2"));
                            row.add(BeanUtils.getProperty(data, "address3"));
                            row.add(BeanUtils.getProperty(data, "country"));
                            row.add(BeanUtils.getProperty(data, "city"));
                            row.add(BeanUtils.getProperty(data, "fax"));
                            row.add(BeanUtils.getProperty(data, "postId"));
                            row.add(BeanUtils.getProperty(data, "currencyType"));
                            row.add(BeanUtils.getProperty(data, "contacts"));
                            row.add(BeanUtils.getProperty(data, "phone"));
                            row.add(BeanUtils.getProperty(data, "email"));
                            row.add(BeanUtils.getProperty(data, "product"));
                            row.add(BeanUtils.getProperty(data, "site"));
                            row.add(BeanUtils.getProperty(data, "domain"));
                            row.add(BeanUtils.getProperty(data, "remarks"));
                            String locale = CustomerreturnsAction.this.getCurrentUser(request).getLocale();
                            if ("en".equals(locale)) {
                                row.add(BeanUtils.getProperty(data, "enabled.engShortDescription"));
                            } else {
                                row.add(BeanUtils.getProperty(data, "enabled.chnShortDescription"));
                            }
                        }
                    });
            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(basicCustomerManager.getListCount(conditions));
        } else {
            queryForm.init();
        }
        Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
        Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
        List<BasicCustomer> resultList = basicCustomerManager.getList(conditions, pageNo.intValue(), pageSize.intValue(), BasicCustomerQueryOrder.ID,
                queryForm.isDescend());
        putEnumListToRequest(request);
        request.setAttribute("X_RESULTLIST", resultList);
        return mapping.findForward("page");
    }

    public ActionForward listProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            SalesWorkorderManager workorderManager = ServiceLocator.getSalesWorkorderManager(request);
            CustomerreturnsQueryForm returns = (CustomerreturnsQueryForm)form;
            String customerCode = request.getParameter("id");
            Map conditions = new HashMap();
            getConditionAndOrder(returns, conditions, request);
            conditions.put(SalesWorkorderQueryCondition.CUSTOMERCODE_EQ, customerCode);
            conditions.put(SalesWorkorderQueryCondition.SHIPWORKORDER_STATUS_EQ, SalesPreshiporderBatchStatus.IN_DELIVERYBATCH);
            if (returns.isFirstInit()) {
                returns.init(workorderManager.getListCount(conditions));
            } else {
                returns.init();
            }
            List<SalesWorkorder> salesWorkorderList = workorderManager.getList(conditions, returns.getPageNoAsInt(), returns.getPageSizeAsInt(), SalesWorkorderQueryOrder.ID, false);
            request.setAttribute("customerCode", customerCode);
            request.setAttribute("x_salesWorkorderList", salesWorkorderList);
            return mapping.findForward("page");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Map getQueryConditions(BasicCustomerQueryForm queryForm) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        String queryStr = "";
        if (queryStr != null)
            queryStr.trim().length();
        return conditions;
    }

    private void putEnumListToRequest(HttpServletRequest request) {
        request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
    }

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
        CustomerReturnItemManager customerReturnItemManager = ServiceLocator.getCustomerReturnItemManager(request);
        CustomerReturnItemQueryForm queryForm = (CustomerReturnItemQueryForm)form;
        Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
        Customerreturns customerreturns = manager.getCustomerreturns(id);
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        conditions.put(CustomerReturnItemQueryCondition.CUSTOMERRETURNS_ID_EQ, customerreturns.getId());
        List<CustomerReturnItem> customerReturnItemList = customerReturnItemManager.getList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), CustomerReturnItemQueryOrder.ID, false);
        if (queryForm.isFirstInit()) {
            queryForm.init(customerReturnItemManager.getListCount(conditions).intValue());
        } else {
            queryForm.init();
        }
        request.setAttribute("x_customerreturns", customerreturns);
        request.setAttribute("x_customerReturnItemList",
                customerReturnItemList);
        request.setAttribute("x_Id", id);
        request.getSession().setAttribute("path", request.getContextPath());
        request.setAttribute("X_ENABLEDDISABLEDLIST",
                PersistentEnum.getEnumList(EnabledDisabled.class));
        return mapping.findForward("page");
    }

    public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
        CustomerReturnItemManager customerReturnItemManager = ServiceLocator.getCustomerReturnItemManager(request);
        Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
        Customerreturns customerreturns = manager.getCustomerreturns(id);
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        conditions.put(CustomerReturnItemQueryCondition.CUSTOMERRETURNS_ID_EQ, id);
        List<CustomerReturnItem> customerReturnItemList = customerReturnItemManager.getList(conditions, 0, -1, CustomerReturnItemQueryOrder.ID, false);
        Map<String, CustomerReturnItem> maps = new HashMap<String, CustomerReturnItem>();
        for (CustomerReturnItem customerReturnItem : customerReturnItemList) {
            if (maps.size() == 0) {
                maps.put(customerReturnItem.getPart().getId(), customerReturnItem);
                continue;
            }
            if (maps.containsKey(customerReturnItem.getPart().getId())) {
                CustomerReturnItem customerReturnItema = maps.get(customerReturnItem.getPart().getId());
                customerReturnItema.setQty(Integer.valueOf(customerReturnItema.getQty().intValue() + customerReturnItem.getQty().intValue()));
                maps.put(customerReturnItema.getPart().getId(), customerReturnItema);
                continue;
            }
            maps.put(customerReturnItem.getPart().getId(), customerReturnItem);
        }
        List<CustomerReturnItem> customerReturnByItemList = new ArrayList<CustomerReturnItem>();
        for (String partId : maps.keySet())
            customerReturnByItemList.add(maps.get(partId));
        request.setAttribute("x_customerreturns", customerreturns);
        request.setAttribute("x_customerReturnItemList",
                customerReturnByItemList);
        request.setAttribute("x_Id", id);
        request.getSession().setAttribute("path", request.getContextPath());
        request.setAttribute("X_ENABLEDDISABLEDLIST",
                PersistentEnum.getEnumList(EnabledDisabled.class));
        return mapping.findForward("page");
    }

    public ActionForward updateCustomerreturnsByItemPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
        CustomerReturnItemManager customerReturnItemManager = ServiceLocator.getCustomerReturnItemManager(request);
        PurchaseOrderPutInStorageManager inStorageManager = ServiceLocator.getPurchaseOrderPutInStorageManager(request);
        Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
        Customerreturns customerreturns = manager.getCustomerreturns(id);
        if (customerreturns.getPrintStatus() != YesNo.YES) {
            Map<Object, Object> conditions = new HashMap<Object, Object>();
            conditions.put(CustomerReturnItemQueryCondition.CUSTOMERRETURNS_ID_EQ, customerreturns.getId());
            List<CustomerReturnItem> customerReturnItemList = customerReturnItemManager.getList(conditions, 0, -1, CustomerReturnItemQueryOrder.ID, false);
            for (CustomerReturnItem customerReturnItem : customerReturnItemList)
                inStorageManager.scanningCustomerreturnInStorages(customerReturnItem.getBatchNumber(), customerReturnItem.getReturnStorage(), getCurrentUser(request).getId().toString());
            customerreturns.setPrintStatus(YesNo.YES);
            manager.update(customerreturns);
        }
        return new ActionForward("listCustomerreturnsAction.do", true);
    }

    public ActionForward selectCustomerReturnLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StorageLocationQueryForm queryForm = (StorageLocationQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(StorageLocationQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        User user = getCurrentUser(request);
        StorageLocationManager fm = ServiceLocator.getStorageLocationManager(request);
        Map conditions = new HashMap();
        getConditionAndOrder(queryForm, conditions, request);
        conditions.put(StorageLocationQueryCondition.SITE_EQ, user.getPrimarySite().getId());
        conditions.put(StorageLocationQueryCondition.STROMTYPE_EQ, Integer.valueOf(3));
        if (queryForm.isFirstInit()) {
            queryForm.init(fm.getStorageLocationListCount(conditions));
        } else {
            queryForm.init();
        }
        int pageNo = queryForm.getPageNoAsInt();
        int pageSize = queryForm.getPageSizeAsInt();
        List result = fm.getStorageLocationList(conditions, pageNo, pageSize, StorageLocationQueryOrder.ID, queryForm.isDescend());
        StoreRoomManager manager = ServiceLocator.getStoreRoomManager(request);
        request.setAttribute("x_stromList", manager.getStoreRoomListEnabledAll());
        request.setAttribute("X_RESULTLIST", result);
        putEnumListToRequest(request);
        return mapping.findForward("page");
    }
}

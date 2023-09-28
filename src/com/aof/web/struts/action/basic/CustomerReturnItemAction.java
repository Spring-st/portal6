package com.aof.web.struts.action.basic;

import com.aof.model.basic.CustomerReturnItem;
import com.aof.model.basic.query.CustomerReturnItemQueryOrder;
import com.aof.service.admin.SiteManager;
import com.aof.service.basic.CustomerReturnItemManager;
import com.aof.service.basic.CustomerreturnsManager;
import com.aof.utils.SessionTempFile;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.BaseSessionQueryForm;
import com.aof.web.struts.form.basic.CustomerReturnItemQueryForm;
import com.shcnc.utils.BeanUtils;
import com.shcnc.utils.ExportUtil;
import com.shcnc.utils.Exportable;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

public class CustomerReturnItemAction extends BaseAction2 {
    private Map getConditions(CustomerReturnItemQueryForm formBean) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        return conditions;
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward reportList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
        SiteManager siteManager = ServiceLocator.getSiteManager(request);
        CustomerReturnItemManager customerReturnItemManager = ServiceLocator.getCustomerReturnItemManager(request);
        CustomerReturnItemQueryForm formBean = (CustomerReturnItemQueryForm)form;
        if (formBean.getOrder() == "") {
            formBean.setDescend(true);
        }
        Map conditions = getConditions(formBean);
        getConditionAndOrder((BaseSessionQueryForm)formBean, conditions, request);






        String exportType = formBean.getExportType();
        if (exportType != null && exportType.length() > 0) {
            List datas = customerReturnItemManager.getList(conditions, 0, -1, CustomerReturnItemQueryOrder.getEnum(formBean.getOrder()), formBean.isDescend());
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "CustomerReturnReport";
            String suffix = ExportUtil.export(exportType, datas, request,
                    new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
                    {
                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanUtils.getProperty(data, "customerreturns.returnNumber"));
                            row.add(BeanUtils.getProperty(data, "batchNumber"));
                            row.add(BeanUtils.getProperty(data, "part.id"));
                            row.add(BeanUtils.getProperty(data, "part.describe1"));
                            row.add(BeanUtils.getProperty(data, "part.oldCode"));
                            row.add(BeanUtils.getProperty(data, "returnStorage"));
                            row.add(BeanUtils.getProperty(data, "qty"));
                            row.add(BeanUtils.getProperty(data, "salesDeliveryDate"));
                        }


                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources message = CustomerReturnItemAction.this.getResources(request);
                            row.add("退货单号");
                            row.add("条码编号");
                            row.add("物料编号");
                            row.add("物料描述");
                            row.add("原厂编号");
                            row.add("退货接收库位");
                            row.add("数量");
                            row.add("销售出库时间");
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
        List<CustomerReturnItem> customerReturnItemList = customerReturnItemManager.getList(conditions, pageNum, pageSize, CustomerReturnItemQueryOrder.ID, formBean.isDescend());


        request.setAttribute("x_selType", Integer.valueOf(152));
        request.setAttribute("X_RESULTLIST", customerReturnItemList);
        return mapping.findForward("page");
    }
}

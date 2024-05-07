package com.aof.web.struts.action.po;


import com.aof.model.metadata.EnabledDisabled;
import com.aof.model.metadata.PortalShipOrderStatus;
import com.aof.model.po.query.PortalShipOrderQueryCondition;
import com.aof.model.po.query.PortalShipOrderQueryOrder;
import com.aof.service.po.PortalShipOrderImportRecordManager;
import com.aof.service.po.PortalShipOrderManager;
import com.aof.web.struts.action.ActionUtils2;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.BaseSessionQueryForm;
import com.aof.web.struts.form.admin.PortalShipOrderMainQueryForm;
import com.shcnc.hibernate.PersistentEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by st137 on 2024-05-07.
 */
public class PortalShipOrderImportRecordAction extends BaseAction2 {

    public ActionForward shippingOrderWrong(ActionMapping mapping, ActionForm form,
                                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
            PortalShipOrderImportRecordManager fm =
                    ServiceLocator.portalShipOrderImportRecordManager(request);
            if (StringUtils.isEmpty(queryForm.getOrder())) {
                queryForm.setOrder("createDate");
                queryForm.setDescend(true);
            }
            queryForm.setOrder("createDate");

            Map conditions = new HashMap<Object, Object>();
            getConditionAndOrder(queryForm, conditions, request);
            getConditionsFrom(queryForm, conditions);
            //conditions.put(PortalShipOrderQueryCondition.CREATETYPE_EQ, "NJIT_NPO");

            if (queryForm.isFirstInit()) {
                queryForm.init(fm.getListCount(conditions));
            } else {
                queryForm.init();
            }

            List result = fm.getList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                    PortalShipOrderQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
            request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
            request.setAttribute("x_statusList", PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
            request.setAttribute("X_RESULTLIST", result);
            request.setAttribute("x_selType",    185);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("page");
    }

    private void getConditionsFrom(PortalShipOrderMainQueryForm queryForm, Map<PortalShipOrderQueryCondition, String> conditions) {
        String code = queryForm.getCode();
        if (code != null && code.trim().length() != 0) {
            conditions.put(PortalShipOrderQueryCondition.CODE_EQ, code);
        }
        Integer enabled = ActionUtils2.parseInt(queryForm.getEnabled());
        if (enabled != null && enabled != 0) {
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

}

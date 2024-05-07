package com.aof.web.struts.action.po;

import com.aof.model.admin.Supplier;
import com.aof.model.admin.User;
import com.aof.model.basic.WmsPart;
import com.aof.model.basic.query.WmsPartQueryCondition;
import com.aof.model.schedule.EdiProduction;
import com.aof.service.admin.SupplierManager;
import com.aof.service.basic.WmsPartManager;
import com.aof.service.schedule.EdiProductionManager;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.schedule.EdiProductionQueryForm;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import com.shcnc.struts.action.BaseAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by st137 on 2023-10-02.
 */
public class PortalShipOrderImportAction extends BaseAction {

    private User getCurrentUser(HttpServletRequest request) {
        User currentUser = (User)request.getSession().getAttribute("LOGIN_USER");
        return currentUser;
    }

    public ActionForward importPortalShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("----------------------导入开始------------------------");
        EdiProductionManager manager = ServiceLocator.getEdiProductionManager(request);
        WmsPartManager partManager = ServiceLocator.getWmsPartManager(request);
        ArrayList<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        EdiProductionQueryForm queryForm = (EdiProductionQueryForm)form;
        FormFile uploadfile = queryForm.getFileContent();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddhhmmss");
        String asnNo = format1.format(new Date());
        int fileSize = 0;
        String fileName = "";
        if (uploadfile != null)
            fileSize = uploadfile.getFileSize();
        HSSFWorkbook wb;
        Date date = new Date();

        User user = getCurrentUser(request);
        String supplierNo = user.getLoginName();
        SupplierManager sManager = ServiceLocator.getSupplierManager(request);
        Supplier supplier = sManager.getSupplierByCode(supplierNo);
        if (fileSize > 0) {
            if (uploadfile.getFileSize() > 0) {
                fileName = uploadfile.getFileName();
                String xls = fileName.substring(fileName.indexOf(".") + 1,
                        fileName.length());
                if (xls.indexOf("xls") == -1) {
                    return new ActionForward("listUnfinishPlanGroup.do", true);
                }
            }

            try {
                wb = new HSSFWorkbook(uploadfile.getInputStream());
            } catch (Exception ex) {
                wb = new HSSFWorkbook(uploadfile.getInputStream());
            }

            Map<String, Object> data = new HashMap<String, Object>();
            List<List<String>> allList = new ArrayList<List<String>>();
            HSSFSheet sheet11 = wb.getSheetAt(0);
            if (sheet11 == null) {
                System.out.println("SHEET不存在");
            }
            System.out.println(sheet11.getLastRowNum());
            for (int rowNum = 1; rowNum <= sheet11.getLastRowNum() + 1; rowNum++) {
                List<String> list = new ArrayList<String>();
                HSSFRow row = sheet11.getRow(rowNum);
                if (row != null) {
                    for (short cellNum = 0; cellNum <= row.getLastCellNum(); cellNum = (short)(cellNum + 1)) {
                        HSSFCell cell = row.getCell(cellNum);
                        if (cell != null) {
                            String de = getValue(sheet11.getRow(rowNum).getCell(
                                    cellNum));
                            list.add(de);
                        } else {
                            list.add("");
                        }
                    }

                    allList.add(list);
                }
            }

            List<EdiProduction> productions = new ArrayList<EdiProduction>();
            for (int j = 0; j < allList.size(); j++) {
                List<String> list = allList.get(j);
                Map<Object, Object> conditions = new HashMap<Object, Object>();
                EdiProduction ediProduction = new EdiProduction();
                conditions.put(WmsPartQueryCondition.ID_EQ, list.get(0));
                List<WmsPart> partList = partManager.getWmsPartList(conditions,
                        0, -1, null, false);

                ediProduction.setAsnNo(asnNo);


                if (supplier == null) {
                    ediProduction.setSupplier(null);
                } else {
                    ediProduction.setSupplier(supplier.getCode());
                }
                ediProduction.setUploader(user.getName());
                ediProduction.setUploadFileName(fileName);
                ediProduction.setUploadTime(date);
                ediProduction.setType(2);
                ediProduction.setStatus(0);
                int a = list.size();
                for (int k = 0; k <= list.size(); k++) {
                    if (k == 0) {
                        if ((list.get(k)).equals("")) {
                            data.put("key", "itemNumber.code");
                            data.put("num", "第" + (j + 2) + "行" + " " + "第" + (
                                    k + 1) + "列");
                            datas.add(data);
                            break;
                        }
                        if (partList.size() == 0) {
                            data.put("key", "itemNumber.codeNot");
                            data.put("num", "第" + (j + 2) + "行" + " " + "第" + (
                                    k + 1) + "列" + " " + list.get(k));
                            datas.add(data);
                            break;
                        }
                        ediProduction.setModels(list.get(k));
                    }
                    else if (k == 1) {
                        if ((list.get(k)).equals("")) {
                            data.put("key", "itemNumber.desc");
                            data.put("num", "第" + (j + 2) + "行" + " " + "第" + (
                                    k + 1) + "列");
                            datas.add(data);
                            break;
                        }
                        if (partList.size() == 0) {
                            data.put("key", "itemNumber.desc");
                            data.put("num", "第" + (j + 2) + "行" + " " + "第" + (
                                    k + 1) + "列" + " " + list.get(k));
                            datas.add(data);
                            break;
                        }
                        ediProduction.setDescribe(list.get(k));
                    }
                    else if (k == 2) {
                        if ((list.get(k)).equals("")) {
                            data.put("key", "ediProduction.qty");
                            data.put("num", "第" + (j + 2) + "行" + " " + "第" + (
                                    k + 1) + "列");
                            datas.add(data);
                            break;
                        }
                        ediProduction.setQty(Integer.valueOf(Integer.parseInt(list.get(k))));
                    }
                    else if (k == 3) {
                        if ((list.get(k)).equals("")) {
                            data.put("key", "ediProduction.date");
                            data.put("num", "第" + (j + 2) + "行" + " " + "第" + (
                                    k + 1) + "列");
                            datas.add(data);
                            break;
                        }
                        SimpleDateFormat format = new SimpleDateFormat(
                                "yyyyMMdd");
                        ediProduction
                                .setTaskDate(format.parse(list.get(k)));

                    }
                    else if (k == 4) {
                        if ((list.get(k)).equals("")) {
                            data.put("key", "ediProduction.time");
                            data.put("num", "第" + (j + 2) + "行" + " " + "第" + (
                                    k + 1) + "列");
                            datas.add(data);
                            break;
                        }
                        ediProduction.setTime(list.get(k));
                    }
                }


                if (datas.size() != 0) {
                    request.setAttribute("msg", datas);
                    return mapping.findForward("add");
                }
                productions.add(ediProduction);
            }

            if (datas.size() != 0) {
                request.setAttribute("msg", datas);
                return mapping.findForward("add");
            }
            for (int i = 0; i < productions.size(); i++) {
                EdiProduction ediProduction = productions.get(i);
                manager.save(ediProduction);
            }
        }
        System.out.println("----------------------导入结束------------------------");
        return new ActionForward("listUnfinishPlanGroup.do", true);
    }

    private String getValue(HSSFCell cell) {
        if (cell.getCellType() == 4)
            return (new BigDecimal(String.valueOf(cell.getNumericCellValue())))
                    .toPlainString();
        if (cell.getCellType() == 0)
            return (new BigDecimal(String.valueOf(cell.getNumericCellValue())))
                    .setScale(0, 4).toPlainString();
        if (cell.getCellType() == 2) {
            return String.valueOf(cell.getNumericCellValue());
        }
        return String.valueOf(cell.getStringCellValue().trim());
    }
}

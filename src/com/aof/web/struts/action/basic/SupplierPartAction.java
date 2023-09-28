package com.aof.web.struts.action.basic;

import com.aof.model.admin.Supplier;
import com.aof.model.admin.query.SupplierQueryCondition;
import com.aof.model.admin.query.SupplierQueryOrder;
import com.aof.model.basic.SupplierPart;
import com.aof.model.basic.WmsPart;
import com.aof.model.basic.query.SupplierPartQueryCondition;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.model.po.query.PurchaseOrderQueryOrder;
import com.aof.service.admin.SupplierManager;
import com.aof.service.basic.WmsPartManager;
import com.aof.utils.SessionTempFile;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.BaseSessionQueryForm;
import com.aof.web.struts.form.admin.SupplierPartQueryForm;
import com.aof.web.struts.form.admin.SupplierQueryForm;
import com.shcnc.hibernate.PersistentEnum;
import com.shcnc.struts.action.ActionException;
import com.shcnc.struts.action.ActionUtils;
import com.shcnc.struts.form.BeanForm;
import com.shcnc.struts.form.DateFormatter;
import com.shcnc.utils.BeanHelper;
import com.shcnc.utils.ExportUtil;
import com.shcnc.utils.Exportable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Sheet;
import jxl.Workbook;
import jxl.biff.DisplayFormat;
import jxl.biff.FontRecord;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;


public class SupplierPartAction extends BaseAction2
{
    private File file;
    private String filename;

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SupplierManager um = ServiceLocator.getSupplierManager(request);

        Integer id = ActionUtils.parseInt(request.getParameter("supplierId"));
        Supplier supplier = um.getSupplier(id);
        request.setAttribute("X_SUPPLIER", supplier);
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        conditions.put(SupplierPartQueryCondition.SUPPLIER_ID_EQ, id);

        List sublist = um.getEnabledSupplierPartList(conditions, 1, -1, null, false);
        request.setAttribute("X_SUBLIST", sublist);
        return mapping.findForward("page");
    }



    public ActionForward reportList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SupplierPartQueryForm queryForm = (SupplierPartQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setDescend(false);
        }

        SupplierManager manager = ServiceLocator.getSupplierManager(request);
        Map conditions = constructQueryMap(queryForm);
        if (queryForm.isFirstInit()) {
            queryForm.init(manager.getSupplierPartListCount(conditions));
        } else {
            queryForm.init();
        }

        List result = manager.getEnabledSupplierPartList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                SupplierQueryOrder.ID, queryForm.isDescend());

        List list = manager.updateSupplierProportion(result);

        request.setAttribute("X_RESULTLIST", list);
        request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
        return mapping.findForward("page");
    }

    private Map constructQueryMap(SupplierPartQueryForm queryForm) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        String id = queryForm.getId();

        if (id != null && !id.equals("")) {
            conditions.put(SupplierPartQueryCondition.ID_EQ, id);
        }

        return conditions;
    }

    public ActionForward supplierPartProportionByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");
//        response.setCharacterEncoding("UTF-8");
        JsonConfig cfg = new JsonConfig();

        String part = request.getParameter("part");
        String code = request.getParameter("code");
        SupplierManager manager = ServiceLocator.getSupplierManager(request);
        Supplier supplier = manager.getSupplierByCode(code);

        boolean returnValue = manager.validateSupplierPartProportion(supplier, part);


        if (!returnValue) {
            BigDecimal amount = manager.getSupplierPartProportion(supplier, part);
            JSONArray jo = JSONArray.fromObject(amount, cfg);
            response.getWriter().print(jo);
        } else {
            JSONArray jo = JSONArray.fromObject(Boolean.valueOf(returnValue), cfg);
            response.getWriter().print(jo);
        }

        return null;
    }












    public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SupplierManager um = ServiceLocator.getSupplierManager(request);
        if (!isBack(request)) {
            Integer id = ActionUtils.parseInt(request.getParameter("supplierId"));
            Supplier supplier = um.getSupplier(id);
            request.setAttribute("X_supplierList", supplier);
            SupplierPart ur = new SupplierPart();
            ur.setSupplierId(supplier);
            BeanForm supplierPartForm = (BeanForm)getForm("/insertSupplierPart", request);
            supplierPartForm.populate(ur, "to_form");
        }

        WmsPartManager wp = ServiceLocator.getWmsPartManager(request);
        List<WmsPart> wpList = wp.getWmsPartListEnabledAll();
        request.setAttribute("X_wpList", wpList);
        return mapping.findForward("page");
    }












    public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SupplierManager um = ServiceLocator.getSupplierManager(request);
        BeanForm supplierPartForm = (BeanForm)form;

        Integer id = ActionUtils.parseInt(request.getParameter("supplierId_id"));
        String partId = request.getParameter("partId_id");
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        conditions.put(SupplierPartQueryCondition.SUPPLIER_ID_EQ, id);
        List<SupplierPart> sublist = um.getEnabledSupplierPartList(conditions, 1, -1, null, false);

        for (SupplierPart supPart : sublist) {
            if (supPart.getPartId().getId().equals(partId)) {
                throw new ActionException("supplierPart.exist", partId);
            }
        }
        SupplierPart ur = new SupplierPart();
        supplierPartForm.populateToBean(ur);

        request.setAttribute("X_OBJECT", um.insertSupplierPart(ur));
        request.setAttribute("X_ROWPAGE", "wmsbasic/supplierPart/row.jsp");
        return mapping.findForward("success");
    }












    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SupplierManager um = ServiceLocator.getSupplierManager(request);
        if (!isBack(request)) {

            Integer id = ActionUtils.parseInt(request.getParameter("id"));
            SupplierPart ur = um.getSupplierPart(id);
            if (ur == null) throw new ActionException("userRole.notFound", id);
            request.setAttribute("X_supplierList", ur);

            BeanForm supplierPartForm = (BeanForm)getForm("/updateSupplierPart", request);
            supplierPartForm.populateToForm(ur);


            BigDecimal amount = um.getSupplierPartProportion(ur.getSupplierId(), ur.getPartId().getId());
            request.setAttribute("x_alreadyAmount", amount);
        }
        WmsPartManager rm = ServiceLocator.getWmsPartManager(request);
        List wpList = rm.getWmsPartListEnabledAll();

        request.setAttribute("X_wpList", wpList);
        return mapping.findForward("page");
    }












    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BeanForm craftForm = (BeanForm)form;
        SupplierManager um = ServiceLocator.getSupplierManager(request);
        String id = request.getParameter("id");
        SupplierPart supplierPart = um.getSupplierPart(Integer.valueOf(Integer.parseInt(id)));
        craftForm.populate(supplierPart, "to_bean");

        SupplierManager craftManager = ServiceLocator.getSupplierManager(request);

        request.setAttribute("X_OBJECT", craftManager.updateSupplierPart(supplierPart));
        request.setAttribute("X_ROWPAGE", "wmsbasic/supplierPart/row.jsp");
        return mapping.findForward("success");
    }












    public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SupplierManager um = ServiceLocator.getSupplierManager(request);
        Integer id = ActionUtils.parseInt(request.getParameter("id"));
        SupplierPart ur = um.getSupplierPart(id);
        if (ur == null) {
            throw new ActionException("userRole.notFound", id);
        }
        um.deleteSupplierPart(ur);
        return new ActionForward("listSupplierPart.do?supplierId=" + ur.getSupplierId().getId(), true);
    }



    public ActionForward listSupplierByPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String partId = request.getParameter("partId");
        SupplierManager manager = ServiceLocator.getSupplierManager(request);
        List list = manager.getSupplierPart(partId);

        request.setAttribute("x_result", list);
        return mapping.findForward("page");
    }

    public ActionForward selectSupplierByPortalShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SupplierQueryForm queryForm = (SupplierQueryForm)form;
        SupplierManager manager = ServiceLocator.getSupplierManager(request);
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(SupplierQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }
        Map conditions = new HashMap();
        getConditionAndOrder(queryForm, conditions, request);
        conditions.put(SupplierQueryCondition.CODE_LIKE, "LD");
        try {
            if (queryForm.isFirstInit()) {
                queryForm.init(manager.getSupplierListCount(conditions));
            } else {
                queryForm.init();
            }
            List<Supplier> supplierList = manager.getSupplierList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), SupplierQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
            request.setAttribute("X_RESULTLIST", supplierList);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return mapping.findForward("page");
    }



    public ActionForward exportsSupplierPartSamplingRatio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Character> chars = new ArrayList();
        int charsDouble = 0;
        for (char i = 'a'; i <= 'z'; i = (char)(i + 1)) {
            chars.add(Character.valueOf(i));
        }

        try {
            DateFormatter format = new DateFormatter(Date.class,
                    "HHmmssSS");
            String name = String.valueOf(format.format(new Date())) + ".xls";
            if (!(new File(String.valueOf(getSys_url()) + "temp/")).exists()) {
                (new File(String.valueOf(getSys_url()) + "temp/")).mkdir();
            }
            File f = new File(String.valueOf(getSys_url()) + "temp/partLocationImportTemplate" +
                    name);
            while (f.exists()) {
                Thread.sleep(100L);
                name = String.valueOf(format.format(new Date())) + ".xls";
                f = new File(String.valueOf(getSys_url()) + "temp/partLocationImportTemplate" +
                        name);
            }
            WritableWorkbook wwb = Workbook.createWorkbook(f);
            WritableSheet ws = wwb.createSheet("供应商物料质检比例", 0);

            Label lable = null;
            WritableSheet sheet = wwb.getSheet(0);
            sheet.setColumnView(0, 15);
            sheet.setColumnView(1, 20);
            NumberFormat nf = new NumberFormat("#.###");
            WritableFont host = new WritableFont(WritableFont.ARIAL,
                    15, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                    Colour.CORAL);
            WritableFont red = new WritableFont(WritableFont.ARIAL,
                    10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                    Colour.RED);

            WritableCellFormat hosts = new WritableCellFormat(host);
            hosts.setBorder(Border.ALL, BorderLineStyle.THIN);
            hosts.setWrap(true);
            hosts.setAlignment(Alignment.CENTRE);
            hosts.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableCellFormat title = new WritableCellFormat();
            title.setBorder(Border.ALL, BorderLineStyle.THIN);
            title.setBackground(Colour.ORANGE);
            title.setWrap(true);
            title.setAlignment(Alignment.CENTRE);
            title.setVerticalAlignment(VerticalAlignment.CENTRE);


            WritableCellFormat functions = new WritableCellFormat((DisplayFormat)nf);
            functions.setBorder(Border.ALL, BorderLineStyle.THIN);
            functions.setWrap(true);
            functions.setAlignment(Alignment.RIGHT);
            functions.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableCellFormat cellFormat = new WritableCellFormat();
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat.setWrap(true);
            cellFormat.setAlignment(Alignment.CENTRE);
            cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableCellFormat reds = new WritableCellFormat((DisplayFormat)nf);
            reds.setBorder(Border.ALL, BorderLineStyle.THIN);
            reds.setFont((FontRecord)red);
            reds.setWrap(true);
            reds.setAlignment(Alignment.CENTRE);
            reds.setVerticalAlignment(VerticalAlignment.CENTRE);

            ws = createheadtoImportNew(ws, lable, title, hosts, sheet);

            WritableCellFormat format3 = new WritableCellFormat((DisplayFormat)nf);
            format3.setWrap(true);
            format3.setAlignment(Alignment.RIGHT);
            format3.setVerticalAlignment(VerticalAlignment.CENTRE);
            format3.setBorder(Border.ALL, BorderLineStyle.THIN);

            wwb.write();
            wwb.close();
            response.sendRedirect("temp/partLocationImportTemplate" + name);
            return mapping.findForward("page");
        } catch (Exception e) {
            throw new ActionException(e.getMessage());
        }
    }




    public WritableSheet createheadtoImportNew(WritableSheet ws, Label lable, WritableCellFormat cellFormat, WritableCellFormat hosts, WritableSheet sheet) throws RowsExceededException, WriteException {
        List<String> arrhead = new ArrayList<String>();


        for (int s = 0; s < arrhead.size(); s++) {
            lable = new Label(s, 4, arrhead.get(s), (CellFormat)cellFormat);
            ws.addCell((WritableCell)lable);
        }

        WritableCellFormat mainBody = new WritableCellFormat();
        mainBody.setBorder(Border.ALL, BorderLineStyle.THIN);
        mainBody.setBackground(Colour.WHITE);
        mainBody.setWrap(true);
        mainBody.setAlignment(Alignment.RIGHT);
        mainBody.setVerticalAlignment(VerticalAlignment.CENTRE);

        lable = new Label(0, 0, "供应商编码", (CellFormat)cellFormat);
        ws.addCell((WritableCell)lable);
        lable = new Label(1, 0, "物料号", (CellFormat)cellFormat);
        ws.addCell((WritableCell)lable);
        lable = new Label(2, 0, "质检比例", (CellFormat)cellFormat);
        ws.addCell((WritableCell)lable);

        sheet.mergeCells(0, 0, 0, 0);
        return ws;
    }

    private String getSys_url() {
        String strClassName = getClass().getName();
        String strPackageName = "";
        if (getClass().getPackage() != null) {
            strPackageName = getClass().getPackage().getName();
        }
        String strClassFileName = "";
        if (!"".equals(strPackageName)) {
            strClassFileName = strClassName.substring(
                    strPackageName.length() + 1, strClassName.length());
        } else {
            strClassFileName = strClassName;
        }
        URL url = getClass().getResource(String.valueOf(strClassFileName) + ".class");
        String strURL = url.toString();
        strURL = strURL.substring(strURL.indexOf('/') + 1,
                strURL.lastIndexOf('/'));
        return "/" + strURL.substring(0, strURL.lastIndexOf("WEB-INF"));
    }




    public ActionForward showImportSupplierPartSamplingRatio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("page");
    }




    public ActionForward upLoadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        SupplierPartQueryForm uFrom = (SupplierPartQueryForm)form;
        String filenames = "";
        PrintWriter out = null;
        try {
            this.file = new File(this.servlet.getServletContext().getRealPath(
                    "/temp/importfile/"));
            if (!this.file.exists()) {
                this.file.mkdir();
            }
            request.setAttribute("filenames", String.valueOf(this.file.getPath()) + "\\" + uFrom.getMyFile().getFileName());
//            response.setCharacterEncoding("GBK");
            out = response.getWriter();
            saveFile(uFrom.getMyFile(), this.file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("page");
    }



    protected void saveFile(FormFile formFile, File file) throws Exception {
        try {
            String path = file.getPath();

            InputStream in = formFile.getInputStream();

            FileOutputStream fout = new FileOutputStream(String.valueOf(path) + "\\" + formFile.getFileName());
            this.filename = String.valueOf(path) + "\\" + formFile.getFileName();

            byte[] buffer = new byte[8192];
            int count = 0;

            while ((count = in.read(buffer)) > 0) {
                fout.write(buffer, 0, count);
            }
            fout.close();
            formFile.destroy();
        } catch (Exception e) {
            throw new ActionException("该文件" + this.filename + "无法上传");
        }
    }



    public ActionForward importeSupplierPartSamplingRatio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        SupplierManager manager = ServiceLocator.getSupplierManager(request);
        List<String> error = new ArrayList<String>();
        Workbook wb = null;
        String filenames = "";
        try {
            filenames = request.getAttribute("filenames").toString();
            wb = Workbook.getWorkbook(new File(filenames));
        } catch (Exception e) {
            throw new ActionException("该文件" + filenames + "不存在");
        }
        Sheet[] sheet = wb.getSheets();
        if (sheet != null && sheet.length > 0) {
            manager.insertSupplierPartSamplingRatio(sheet, getCurrentUser(request));
        }
        if (error.size() != 0) {
            request.setAttribute("showerror", Boolean.valueOf(true));
            request.setAttribute("error", error);
        } else {
            request.setAttribute("showerror", Boolean.valueOf(false));
        }
        if (error.size() != 0) {
            throw new ActionException("该文件" + filenames + "不存在" + (String)error.get(0));
        }

        return mapping.findForward("page");
    }



    public ActionForward listSamplingRatio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SupplierPartQueryForm queryForm = (SupplierPartQueryForm)form;
        if (StringUtils.isEmpty(queryForm.getOrder())) {
            queryForm.setOrder(PurchaseOrderQueryOrder.ID.getName());
            queryForm.setDescend(false);
        }

        SupplierManager manager = ServiceLocator.getSupplierManager(request);
        Map conditions = constructQueryMap(queryForm);
        getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);

        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List data = manager.getSupplierPartSamplingRatioList(conditions, 0, -1, SupplierQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());

            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "purchaseOrder";
            String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
            {
                public void exportHead(List row, HttpServletRequest request) throws Exception {
                    MessageResources messages = SupplierPartAction.this.getResources(request);
                    row.add(messages.getMessage(SupplierPartAction.this.getLocale(request), "supplierId.code"));
                    row.add(messages.getMessage(SupplierPartAction.this.getLocale(request), "supplierId.name"));
                    row.add(messages.getMessage(SupplierPartAction.this.getLocale(request), "part.id"));
                    row.add(messages.getMessage(SupplierPartAction.this.getLocale(request), "part.describe1"));
                    row.add(messages.getMessage(SupplierPartAction.this.getLocale(request), "part.describe2"));
                    row.add(messages.getMessage(SupplierPartAction.this.getLocale(request), "part.unit"));
                    row.add(messages.getMessage(SupplierPartAction.this.getLocale(request), "date"));
                    row.add(messages.getMessage(SupplierPartAction.this.getLocale(request), "qty"));
                    row.add(messages.getMessage(SupplierPartAction.this.getLocale(request), "enabled.chnShortDescription"));
                }

                public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                    row.add(BeanHelper.getBeanPropertyValue(data, "supplierId.code"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "supplierId.name"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "part.describe2"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "part.unit"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "date"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "qty"));
                    row.add(BeanHelper.getBeanPropertyValue(data, "enabled.chnShortDescription"));
                }
            });


            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(manager.getSupplierPartSamplingRatioCount(conditions));
        } else {
            queryForm.init();
        }
        List result = manager.getSupplierPartSamplingRatioList(conditions,
                queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(),
                SupplierQueryOrder.ID, queryForm.isDescend());

        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(30));
        return mapping.findForward("page");
    }
}

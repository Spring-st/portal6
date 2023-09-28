package com.aof.web.struts.action.product;

import com.aof.model.admin.FinishedSaiheRelation;
import com.aof.model.admin.FinishedToolPutnumber;
import com.aof.model.admin.query.FinishedSaiheRelationQueryCondition;
import com.aof.model.admin.query.FinishedToolPutnumberQueryCondition;
import com.aof.model.basic.StorageLocation;
import com.aof.model.basic.WmsPart;
import com.aof.model.comprehensive.Bom;
import com.aof.model.comprehensive.ProduceBuckleMaterial;
import com.aof.model.comprehensive.query.BomQueryCondition;
import com.aof.model.inventory.InventoryDetial;
import com.aof.model.inventory.InventoryMoving;
import com.aof.model.inventory.query.InventoryQueryCondition;
import com.aof.model.metadata.StoreRoomType;
import com.aof.model.metadata.YesNo;
import com.aof.model.po.Box;
import com.aof.model.po.query.BoxQueryCondition;
import com.aof.model.po.query.BoxQueryOrder;
import com.aof.model.product.ProductGoline;
import com.aof.model.product.query.ProductGolineQueryCondition;
import com.aof.model.product.query.ProductGolineQueryOrder;
import com.aof.model.sync.query.ProductOutGolineQueryCondition;
import com.aof.model.sync.query.ProductOutGolineQueryOrder;
import com.aof.service.Product.ProductGolineManager;
import com.aof.service.Product.ProductOutGolineManager;
import com.aof.service.admin.FinishedSaiheRelationManager;
import com.aof.service.admin.FinishedToolPutnumberManager;
import com.aof.service.admin.InventoryMovingManager;
import com.aof.service.basic.StorageLocationManager;
import com.aof.service.basic.WmsPartManager;
import com.aof.service.comprehensive.BomManager;
import com.aof.service.inventory.InventoryManager;
import com.aof.service.po.BoxManager;
import com.aof.service.po.ProduceBuckleMaterialManager;
import com.aof.service.quartz.job.RedMinuteSyncJob;
import com.aof.utils.SessionTempFile;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.BaseSessionQueryForm;
import com.aof.web.struts.form.po.ProductGoLineQueryForm;
import com.aof.web.struts.form.po.ProductOutGolineQueryForm;
import com.shcnc.struts.action.ActionUtils;
import com.shcnc.utils.BeanHelper;
import com.shcnc.utils.ExportUtil;
import com.shcnc.utils.Exportable;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

public class ProductGolineAction extends BaseAction2 {
    public ActionForward productGolineNotQAD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductOutGolineManager manager = ServiceLocator.getProductOutGolineManager(request);
        ProductOutGolineQueryForm queryForm = (ProductOutGolineQueryForm)form;
        Map conditions = constructQueryMap(queryForm);
        conditions.put(ProductOutGolineQueryCondition.XXSH_WORC_STATUS_EQ, Integer.valueOf(2));
        getConditionAndOrder(queryForm, conditions, request);
        if (queryForm.isFirstInit()) {
            queryForm.init(manager.getProductOutGolineListCount(conditions));
        } else {
            queryForm.init();
        }
        List result = manager.getProductOutGolineList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), ProductOutGolineQueryOrder.ID, queryForm.isDescend());
        request.setAttribute("X_RESULTLIST", result);
        request.setAttribute("x_selType", Integer.valueOf(111));
        return mapping.findForward("page");
    }
    private Map constructQueryMap(ProductOutGolineQueryForm queryForm) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        Integer id = ActionUtils.parseInt(queryForm.getId());
        if (id != null && !id.equals("")) {
            conditions.put(ProductOutGolineQueryCondition.ID_EQ, id);
        }
        String date1 = queryForm.getDate1();
        if (date1 != null && !date1.equals("")) {
            conditions.put(ProductOutGolineQueryCondition.DATE_GT, date1);
        }
        String date2 = queryForm.getDate2();
        if (date2 != null && !date2.equals("")) {
            conditions.put(ProductOutGolineQueryCondition.DATE_LT, date2);
        }
        String item = queryForm.getItem();
        if (item != null && !item.equals("")) {
            conditions.put(ProductOutGolineQueryCondition.XXSH_WORC_ITEM_LIKE, item);
        }
        return conditions;
    }


    public ActionForward productGolineQAD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RedMinuteSyncJob sss =
                ServiceLocator.getRedMinuteSyncJobManager(request);
        sss.startProductOutGolineSyn();

        return new ActionForward("listProductGoline.do", true);
    }


    public ActionForward insertProductGoline(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductGolineManager manager = ServiceLocator.getProductGolineManager(request);
        FinishedSaiheRelationManager fmanager = ServiceLocator.getFinishedSaiheRelationManager(request);
        BomManager bmanager = ServiceLocator.getBomManager(request);
        StorageLocationManager slManager = ServiceLocator.getStorageLocationManager(request);
        InventoryManager itManager = ServiceLocator.getInventoryManager(request);
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        ProductGoline proGoline = new ProductGoline();

        String shCode = request.getParameter("shCode");
        proGoline.setShCode(shCode);
        conditions.put(ProductGolineQueryCondition.SH_CODE_EQ, shCode);
        if (manager.getProductGolineListCount(conditions) > 0) {
            postGlobalMessage("productGoline.shCode.exists", shCode, request.getSession());
            return new ActionForward("listProductGoline.do", true);
        }

        String totalNumber = shCode.substring(23);
        proGoline.setTotalNumber(totalNumber);

        conditions.clear();
        conditions.put(FinishedSaiheRelationQueryCondition.SAIHECODE_EQ, totalNumber);
        List<FinishedSaiheRelation> list = fmanager.getFinishedSaiheRelationList(conditions, 0, -1, null, false);
        if (list.size() <= 0) {
            postGlobalMessage("productGoline.hncCode.notFound", totalNumber, request.getSession());
            return new ActionForward("listProductGoline.do", true);
        }
        String hncCode = ((FinishedSaiheRelation)list.get(0)).getFinishedCode();
        proGoline.setHncCode(hncCode);

        conditions.clear();
        conditions.put(BomQueryCondition.PRODUCT_NO_EQ, hncCode);
        List<Bom> bl = bmanager.getBomList(conditions, 0, -1, null, false);
        if (bl.size() <= 0) {
            postGlobalMessage("productGoline.partChild.notFound", hncCode, request.getSession());
            return new ActionForward("listProductGoline.do", true);
        }

        proGoline.setHncDesc(((Bom)bl.get(0)).getProduct_no().getDescribe1());
        for (Bom bom : bl) {
            if (bom.getChild_part().getId().indexOf("CL1") >= 0) {

                proGoline.setPartTireCode(bom.getChild_part().getId());
                proGoline.setTireDesc(bom.getChild_part().getDescribe1()); continue;
            }
            if (bom.getChild_part().getId().indexOf("CL3") >= 0) {

                proGoline.setPartHubCode(bom.getChild_part().getId());
                proGoline.setHubDesc(bom.getChild_part().getDescribe1()); continue;
            }
            if (bom.getChild_part().getId().indexOf("CL4") >= 0) {

                proGoline.setPartValvestemCode(bom.getChild_part().getId());
                proGoline.setValvestemDesc(bom.getChild_part().getDescribe1());
            }
        }


        if (proGoline.getPartTireCode() == null || proGoline.getPartTireCode().length() == 0) {
            postGlobalMessage("productGoline.partTireCode.notFound", hncCode, request.getSession());
            return new ActionForward("listProductGoline.do", true);
        }
        if (proGoline.getPartHubCode() == null || proGoline.getPartHubCode().length() == 0) {
            postGlobalMessage("productGoline.partHubCode.notFound", hncCode, request.getSession());
            return new ActionForward("listProductGoline.do", true);
        }
        if (proGoline.getPartValvestemCode() == null || proGoline.getPartValvestemCode().length() == 0) {
            postGlobalMessage("productGoline.partValvestemCode.notFound", hncCode, request.getSession());
            return new ActionForward("listProductGoline.do", true);
        }
        proGoline.setQty(Integer.valueOf(1));
        proGoline.setStatus(Integer.valueOf(1));
        proGoline.setStorageDate(new Date());
        StorageLocation sl = slManager.getStorageLocation("XBK001");
        proGoline.setLocationCode(sl);


        proGoline = manager.insertProductGoline(proGoline);

        return new ActionForward("listProductGoline.do", true);
    }



    public ActionForward listProductGoline(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductGolineManager manager = ServiceLocator.getProductGolineManager(request);
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        List<ProductGoline> resultList = new ArrayList();

        List totalNumberList = manager.getTotalNumberList();
        for (Iterator<String> iterator = totalNumberList.iterator(); iterator.hasNext(); ) {

            String totalNumber = iterator.next();
            conditions.put(ProductGolineQueryCondition.TOTAL_NUMBER_EQ, totalNumber);
            conditions.put(ProductGolineQueryCondition.STATUS_EQ, Integer.valueOf(1));
            List<ProductGoline> list = manager.getProductGolineList(conditions, 0, 1, null, false);
            if (list.size() > 0) {

                int qty = manager.getProductGolineListCount(conditions);
                ProductGoline pg = list.get(0);
                pg.setQty(Integer.valueOf(qty));
                resultList.add(pg);
            }
        }
        request.setAttribute("X_RESULTLIST", resultList);
        return mapping.findForward("page");
    }



    public ActionForward connectSys(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jo = new JSONObject();
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String refreshTime = sf.format(date);
        jo.put("refreshTime", refreshTime);
        response.getWriter().print(jo);
        return null;
    }



    public ActionForward materialReduction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductGolineManager manager = ServiceLocator.getProductGolineManager(request);
        BoxManager boxManager = ServiceLocator.getBoxManager(request);
        InventoryMovingManager movingManager = ServiceLocator.getInventoryMovingManager(request);
        ProduceBuckleMaterialManager pbmManager = ServiceLocator.getProduceBuckleMaterialManager(request);
        WmsPartManager wpManager = ServiceLocator.getWmsPartManager(request);
        StorageLocationManager slManager = ServiceLocator.getStorageLocationManager(request);
        InventoryManager itManager = ServiceLocator.getInventoryManager(request);
        BomManager bmanager = ServiceLocator.getBomManager(request);
        String[] ids = request.getParameterValues("cid");
        for (int i = 0; i < ids.length; i++) {

            Integer id = Integer.valueOf(Integer.parseInt(ids[i]));
            ProductGoline pg = manager.getProductGoline(id);
            Map<Object, Object> conditions = new HashMap<Object, Object>();
            conditions.put(BomQueryCondition.PRODUCT_NO_EQ, pg.getHncCode());
            List<Bom> bl = bmanager.getBomList(conditions, 0, -1, null, false);

            String totalNumber = pg.getTotalNumber();
            String hncCode = pg.getHncCode();

            conditions.clear();
            conditions.put(ProductGolineQueryCondition.TOTAL_NUMBER_EQ, totalNumber);
            conditions.put(ProductGolineQueryCondition.STATUS_EQ, Integer.valueOf(1));
            int pgListCount = manager.getProductGolineListCount(conditions);
            BigDecimal number = new BigDecimal(pgListCount);

            insertInventoryDetial(((Bom)bl.get(0)).getProduct_no(), slManager.getStorageLocation("XBK001"), itManager, number);

            String tireCode = pg.getPartTireCode();
            String hubCode = pg.getPartHubCode();
            String valuestemCode = pg.getPartValvestemCode();


            if (!isNotBiger(boxManager, tireCode, number)) {
                postGlobalMessage("productGoline.partCode.notEnough", tireCode, request.getSession());
                return new ActionForward("listProductGoline.do", true);
            }
            if (!isNotBiger(boxManager, hubCode, number)) {
                postGlobalMessage("productGoline.partCode.notEnough", hubCode, request.getSession());
                return new ActionForward("listProductGoline.do", true);
            }





            Integer nxgrowth = nextGrowth(request);

            buckleMaterial(nxgrowth, boxManager, tireCode, number, hncCode, pbmManager, request);
            buckleMaterial(nxgrowth, boxManager, hubCode, number, hncCode, pbmManager, request);



            BomManager bm = ServiceLocator.getBomManager(request);
            Map<Object, Object> conditionsbm = new HashMap<Object, Object>();
            conditionsbm.put(BomQueryCondition.PRODUCT_NO_EQ, hncCode);
            conditionsbm.put(BomQueryCondition.CHILD_PART_EQ, valuestemCode);
            Bom bom = (Bom) bm.getBomList(conditionsbm, 0, -1, null, false).get(0);

            ProduceBuckleMaterial pbm = new ProduceBuckleMaterial();
            pbm.setAssembly(wpManager.getWmsPart(hncCode));
            pbm.setPart(wpManager.getWmsPart(valuestemCode));
            pbm.setQty(number);
            pbm.setLocation(null);
            pbm.setSite(getCurrentUser(request).getPrimarySite());
            pbm.setDate(new Date());
            pbm.setGrowth(nxgrowth);
            pbm.setBom_id(bom);
            pbmManager.insertProduceBuckleMaterial(pbm);












            List<ProductGoline> pgList = manager.getProductGolineList(conditions, 0, -1, null, false);
            for (ProductGoline productGoline : pgList) {
                productGoline.setBuckleMaterialDate(new Date());
                productGoline.setStatus(Integer.valueOf(2));
                manager.updateProductGoline(productGoline);
            }
        }
        return new ActionForward("listProductGoline.do", true);
    }


    private boolean isNotBiger(BoxManager boxManager, String partCode, BigDecimal number) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        conditions.put(BoxQueryCondition.FREEZE_EQ, Integer.valueOf(1));
        conditions.put(BoxQueryCondition.STATUS_EQ, Integer.valueOf(4));
        conditions.put(BoxQueryCondition.PART_ID_EQ, partCode);


        conditions.put(BoxQueryCondition.LOCATION_EQ, Integer.valueOf(5));
        List<Box> list = boxManager.getBoxList(conditions, 0, -1, null, false);
        BigDecimal qty = new BigDecimal(0);
        for (Box box : list) {
            qty = qty.add(box.getNumber());
        }



        if (qty.compareTo(number) != -1) {
            return true;
        }
        return false;
    }
    private Integer nextGrowth(HttpServletRequest request) {
        ProduceBuckleMaterialManager pbmm = ServiceLocator.getProduceBuckleMaterialManager(request);
        Integer nxgrowth = pbmm.nextGrowth();
        if (nxgrowth == null || nxgrowth.intValue() == 0) {
            return Integer.valueOf(1);
        }
        return nxgrowth = Integer.valueOf(nxgrowth.intValue() + 1);
    }


    private void buckleMaterial(Integer nxgrowth, BoxManager boxManager, String partCode, BigDecimal number, String hncCode, ProduceBuckleMaterialManager pbmManager, HttpServletRequest request) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        BigDecimal count = number;
        conditions.put(BoxQueryCondition.FREEZE_EQ, Integer.valueOf(1));
        conditions.put(BoxQueryCondition.STATUS_EQ, Integer.valueOf(4));
        conditions.put(BoxQueryCondition.PART_ID_EQ, partCode);
        conditions.put(BoxQueryCondition.ISNOTZERO, null);
        conditions.put(BoxQueryCondition.LOCATION_NOTNULL, null);
        List<Box> list = boxManager.getBoxList(conditions, 0, -1, BoxQueryOrder.INDATE_LINE, false); Box box; Iterator<Box> iterator;
        for (iterator = list.iterator(), box = iterator.next(); iterator.hasNext() &&
                number.compareTo(new BigDecimal(0)) == 1; ) {


            number = number.subtract(box.getNumber());
            BigDecimal useQty = new BigDecimal(0);
            if (number.compareTo(new BigDecimal(0)) == 1) {

                useQty = box.getNumber();
                box.setNumber(new BigDecimal(0));
            } else {

                useQty = box.getNumber().add(number);
                BigDecimal bd = new BigDecimal(0);
                box.setNumber(bd.subtract(number));
            }

            boxManager.updateBox(box);



















            InventoryManager itManager = ServiceLocator.getInventoryManager(request);
            BigDecimal zero = new BigDecimal(0);
            useQty = zero.subtract(useQty);
            insertInventoryDetial(box.getPart(), box.getLocation(), itManager, useQty);
        }

        BomManager bm = ServiceLocator.getBomManager(request);
        Map<Object, Object> conditionsbm = new HashMap<Object, Object>();
        conditionsbm.put(BomQueryCondition.PRODUCT_NO_EQ, hncCode);
        conditionsbm.put(BomQueryCondition.CHILD_PART_EQ, partCode);
        Bom bom = (Bom) bm.getBomList(conditionsbm, 0, -1, null, false).get(0);

        ProduceBuckleMaterial pbm = new ProduceBuckleMaterial();
        WmsPartManager wpManager = ServiceLocator.getWmsPartManager(request);
        pbm.setAssembly(wpManager.getWmsPart(hncCode));
        pbm.setPart(((Box)list.get(0)).getPart());
        pbm.setQty(count);
        pbm.setLocation(((Box)list.get(0)).getLocation());
        pbm.setSite(getCurrentUser(request).getPrimarySite());
        pbm.setDate(new Date());
        pbm.setGrowth(nxgrowth);
        pbm.setBom_id(bom);
        pbmManager.insertProduceBuckleMaterial(pbm);
    }



    public void insertInventoryDetial(WmsPart part, StorageLocation sl, InventoryManager itManager, BigDecimal qty) {
        Map<Object, Object> conditions = new HashMap<Object, Object>();

        conditions.put(InventoryQueryCondition.DETAIL_PART_EQ, part.getId());

        List<InventoryDetial> partQtyList = itManager.getInventoryDetialList(conditions, 0, -1, null, false);
        BigDecimal oldQty = new BigDecimal(0);
        Iterator<InventoryDetial> iterator = partQtyList.iterator(); if (iterator.hasNext()) { InventoryDetial it = iterator.next();
            oldQty = oldQty.add(it.getPart_qty()); }


        conditions.put(InventoryQueryCondition.DETAIL_STORAGE_EQ, sl.getId());

        if (itManager.getInventoryDetialListCount(conditions) < 1) {
            InventoryDetial detial = new InventoryDetial();
            detial.setLocation(sl);
            detial.setNumber(new BigDecimal(0));
            detial.setPart(part);
            detial.setPart_qty(new BigDecimal(0));
            itManager.insertInventoryDetial(detial);
        }

        InventoryDetial indl = (InventoryDetial) itManager.getInventoryDetialList(conditions, 0, 1, null, false).get(0);

        indl.setNumber(indl.getNumber().add(qty));
        itManager.updateInventoryDetial(indl);
        conditions.clear();

        conditions.put(InventoryQueryCondition.DETAIL_PART_EQ, part.getId());
        List<InventoryDetial> detialList = itManager.getInventoryDetialList(conditions, 0, -1, null, false);

        for (InventoryDetial inventoryDetial : detialList) {
            inventoryDetial.setPart_qty(oldQty.add(qty));
            itManager.updateInventoryDetial(inventoryDetial);
        }
    }







    public ActionForward productDownlineList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductGolineManager manager = ServiceLocator.getProductGolineManager(request);
        ProductGoLineQueryForm queryForm = (ProductGoLineQueryForm)form;
        Map conditions = new HashMap();
        conditions.put(ProductGolineQueryCondition.STATUS_EQ, Integer.valueOf(2));
        getConditionAndOrder(queryForm, conditions, request);
        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List<ProductGoline> data = manager.getProductGolineList(conditions, 0, -1,
                    ProductGolineQueryOrder.HNC_CODE, false);
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "ProductDownlineExcel";
            String suffix = ExportUtil.export(exportType, data, request,
                    new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources messages = ProductGolineAction.this.getResources(request);
                            row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.hncCode"));
                            row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.saiHeCode"));
                            row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.hubCode"));
                            row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.hubdesc"));
                            row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.tireCode"));
                            row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.tireDesc"));
                            row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.qty"));
                        }

                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data, "hncCode"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "shCode"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "partTireCode"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "tireDesc"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "partHubCode"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "hubDesc"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "qty"));
                        }
                    });
            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix,
                    true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(manager.getProductGolineListCount(conditions));
        } else {
            queryForm.init();
        }
        List<ProductGoline> result = manager.getProductGolineList(conditions, queryForm.getPageNoAsInt(),
                queryForm.getPageSizeAsInt(), ProductGolineQueryOrder.HNC_CODE, false);
        request.setAttribute("x_selType", Integer.valueOf(107));
        request.setAttribute("X_RESULTLIST", result);
        return mapping.findForward("page");
    }



    public ActionForward productInObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductGolineManager manager = ServiceLocator.getProductGolineManager(request);
        FinishedToolPutnumberManager ftpm = ServiceLocator.getFinishedToolPutnumberManager(request);

        String str = request.getParameter("array");
        String[] arrays = str.split(",");

        List<ProductGoline> listPg = new ArrayList<ProductGoline>(); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
            ProductGoline productGoline = manager.getProductGoline(Integer.valueOf(Integer.parseInt(id)));
            if (productGoline != null) {
                listPg.add(productGoline);
            }
            b++; }

        ProductGoline pg = listPg.get(0);
        String code = pg.getHncCode();
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        conditions.put(FinishedToolPutnumberQueryCondition.FINISHEDCODE_EQ, code);

        List<FinishedToolPutnumber> finished = ftpm.getFinishedToolPutnumberList(conditions, 0, -1, null, false);
        FinishedToolPutnumber fin = null;
        if (finished != null) {
            fin = finished.get(0);
        }
        request.setAttribute("X_PRODUCTLIST", listPg);
        request.setAttribute("X_PRODUCTTOOL", fin);
        request.setAttribute("X_PRODUCTQTY", Integer.valueOf(listPg.size()));
        return mapping.findForward("page");
    }



    public ActionForward productDownInStorage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductGolineManager pgm = ServiceLocator.getProductGolineManager(request);
        StorageLocationManager slm = ServiceLocator.getStorageLocationManager(request);
        InventoryManager manager = ServiceLocator.getInventoryManager(request);
        FinishedToolPutnumberManager ftm = ServiceLocator.getFinishedToolPutnumberManager(request);
        InventoryMovingManager movingManager = ServiceLocator.getInventoryMovingManager(request);
        WmsPartManager partManager = ServiceLocator.getWmsPartManager(request);
        InventoryMoving moving = new InventoryMoving();
        String[] idsStrings = request.getParameterValues("ids");
        String toolCode = request.getParameter("tool");
        String location_code = request.getParameter("locationId");

        StorageLocation sl = slm.getStorageLocation(Integer.valueOf(Integer.parseInt(location_code)));

        FinishedToolPutnumber ftl = ftm.getFinishedToolPutnumber(Integer.valueOf(Integer.parseInt(toolCode)));

        List<ProductGoline> golineList = new ArrayList<ProductGoline>();

        int tqty = 0;
        for (int i = 0; i < idsStrings.length; i++) {
            Integer id = Integer.valueOf(Integer.parseInt(idsStrings[i]));
            ProductGoline pg = pgm.getProductGoline(id);
            pg.setLocationCode(sl);
            pg.setTool(ftl);
            pg.setStatus(Integer.valueOf(3));
            pgm.updateProductGoline(pg);
            tqty += pg.getQty().intValue();
            golineList.add(pg);
        }
        BigDecimal big = new BigDecimal(tqty);
        StorageLocation xbk = slm.getStorageLocation("XBK001");

        WmsPart part = partManager.getWmsPart(((ProductGoline)golineList.get(0)).getHncCode());

        BigDecimal qty = BigDecimal.valueOf(golineList.size());

        BigDecimal rqty = BigDecimal.valueOf(-tqty);
        moving.setOld_location(xbk);
        moving.setPart(part);
        moving.setNew_location(sl);
        moving.setQty(big);
        moving.setDate(new Date());
        moving.setIs_sync(YesNo.NO);
        moving.setRemark("成品入库：" + xbk.getCode() + "-" + sl.getCode() + ",成品号：" + part.getId());
        movingManager.insertInventoryMoving(moving);



        insertInventoryDetial(part, sl, manager, qty);


        insertInventoryDetial(part, xbk, manager, rqty);

        return mapping.findForward("success");
    }



    public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductGolineManager manager = ServiceLocator.getProductGolineManager(request);
        StorageLocationManager slManager = ServiceLocator.getStorageLocationManager(request);
        ProductGoLineQueryForm queryForm = (ProductGoLineQueryForm)form;
        Map conditions = new HashMap();

        StorageLocation xbk = slManager.getStorageLocation("XBK001");
        conditions.put(ProductGolineQueryCondition.LOCATION_CODE_EQ, xbk.getId());
        conditions.put(ProductGolineQueryCondition.STATUS_NE, new Object[] { Integer.valueOf(1), Integer.valueOf(4) });
        getConditionAndOrder(queryForm, conditions, request);

        List hnCode = manager.getHncCodeList();
        List<ProductGoline> resultList = new ArrayList();

        for (Iterator<String> iterator = hnCode.iterator(); iterator.hasNext(); ) {
            String hncCode = iterator.next();

            conditions.put(ProductGolineQueryCondition.HNC_CODE_EQ, hncCode);
            List<ProductGoline> list = manager.getProductGolineList(conditions, 0, -1, null, false);
            if (list.size() > 0) {

                int qty = manager.getProductGolineListCount(conditions);
                ProductGoline pg = list.get(0);
                pg.setQty(Integer.valueOf(qty));
                resultList.add(pg);
            }
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(resultList.size());
        } else {
            queryForm.init();
        }
        request.setAttribute("X_RESULTLIST", resultList);
        return mapping.findForward("page");
    }



    public ActionForward checkhncCodeByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");
//        response.setCharacterEncoding("UTF-8");
        JsonConfig cfg = new JsonConfig();
        FinishedToolPutnumberManager manager = ServiceLocator.getFinishedToolPutnumberManager(request);

        String hncCode = request.getParameter("hncCode");
        HashedMap hashedMap = new HashedMap();
        hashedMap.put(FinishedToolPutnumberQueryCondition.FINISHEDCODE_EQ, hncCode);

        int count = manager.getFinishedToolPutnumberListCount((Map)hashedMap);

        if (count > 0) {
            JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
            response.getWriter().print(jo);
        } else {
            JSONArray jo = JSONArray.fromObject(Boolean.valueOf(false), cfg);
            response.getWriter().print(jo);
        }
        return null;
    }



    public ActionForward checkhncCountByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");
//        response.setCharacterEncoding("UTF-8");
        JsonConfig cfg = new JsonConfig();
        ProductGolineManager manager = ServiceLocator.getProductGolineManager(request);
        InventoryManager iMnager = ServiceLocator.getInventoryManager(request);

        String str = request.getParameter("array");
        String[] arrays = str.split(",");

        List<ProductGoline> listPg = new ArrayList<ProductGoline>(); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
            ProductGoline productGoline = manager.getProductGoline(Integer.valueOf(Integer.parseInt(id)));
            if (productGoline != null) {
                listPg.add(productGoline);
            }
            b++; }

        ProductGoline pg = listPg.get(0);
        String code = pg.getHncCode();
        BigDecimal count = new BigDecimal(listPg.size());

        Map<Object, Object> conditions = new HashMap<Object, Object>();
        conditions.put(InventoryQueryCondition.DETAIL_STORAGE_ID_EQ, Integer.valueOf(5));
        conditions.put(InventoryQueryCondition.DETAIL_PART_EQ, code);
        List<InventoryDetial> list = iMnager.getInventoryDetialList(conditions, 0, -1, null, false);
        BigDecimal number = ((InventoryDetial)list.get(0)).getNumber();

        if (number.compareTo(count) != -1) {
            JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
            response.getWriter().print(jo);
        } else {
            JSONArray jo = JSONArray.fromObject(Boolean.valueOf(false), cfg);
            response.getWriter().print(jo);
        }
        return null;
    }



    public ActionForward TransferList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductGolineManager manager = ServiceLocator.getProductGolineManager(request);
        ProductGoLineQueryForm queryForm = (ProductGoLineQueryForm)form;

        Map conditions = new HashMap();
        conditions.put(ProductGolineQueryCondition.STATUS_EQ, Integer.valueOf(3));


        conditions.put(ProductGolineQueryCondition.STOREROOM_TYPE_NE, Integer.valueOf(5));

        getConditionAndOrder(queryForm, conditions, request);
        String exportType = queryForm.getExportType();
        if (StringUtils.isNotEmpty(exportType)) {
            List<ProductGoline> data = manager.getProductGolineList(conditions, 0, -1,
                    ProductGolineQueryOrder.HNC_CODE, false);
            int index = SessionTempFile.createNewTempFile(request);
            String fileName = "ProductDownlineExcel";
            String suffix = ExportUtil.export(exportType, data, request,
                    new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
                    {
                        public void exportHead(List row, HttpServletRequest request) throws Exception {
                            MessageResources messages = ProductGolineAction.this.getResources(request);
                            row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.hncCode"));
                            row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.saiHeCode"));
                            row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.hubCode"));
                            row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.hubdesc"));
                            row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.tireCode"));
                            row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.tireDesc"));
                            row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.qty"));
                        }

                        public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
                            row.add(BeanHelper.getBeanPropertyValue(data, "hncCode"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "shCode"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "partTireCode"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "tireDesc"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "partHubCode"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "hubDesc"));
                            row.add(BeanHelper.getBeanPropertyValue(data, "qty"));
                        }
                    });
            return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix,
                    true);
        }
        if (queryForm.isFirstInit()) {
            queryForm.init(manager.getProductGolineListCount(conditions));
        } else {
            queryForm.init();
        }
        List<ProductGoline> result = manager.getProductGolineList(conditions, queryForm.getPageNoAsInt(),
                queryForm.getPageSizeAsInt(), ProductGolineQueryOrder.HNC_CODE, false);
        request.setAttribute("x_selType", Integer.valueOf(110));
        request.setAttribute("X_RESULTLIST", result);
        return mapping.findForward("page");
    }



    public ActionForward TransferNew(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductGolineManager manager = ServiceLocator.getProductGolineManager(request);
        FinishedToolPutnumberManager ftpm = ServiceLocator.getFinishedToolPutnumberManager(request);

        String str = request.getParameter("array");
        String[] arrays = str.split(",");

        List<ProductGoline> listPg = new ArrayList<ProductGoline>(); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
            ProductGoline pg = manager.getProductGoline(Integer.valueOf(Integer.parseInt(id)));
            if (pg != null) {
                listPg.add(pg);
            }
            request.setAttribute("X_code", pg.getLocationCode().getCode());
            request.setAttribute("X_codeid", pg.getLocationCode().getId());
            b++; }

        request.setAttribute("X_PRODUCTLIST", listPg);
        return mapping.findForward("page");
    }


    public ActionForward productTransferUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductGolineManager pgm = ServiceLocator.getProductGolineManager(request);
        StorageLocationManager slm = ServiceLocator.getStorageLocationManager(request);
        InventoryManager manager = ServiceLocator.getInventoryManager(request);
        FinishedToolPutnumberManager ftm = ServiceLocator.getFinishedToolPutnumberManager(request);
        WmsPartManager partManager = ServiceLocator.getWmsPartManager(request);
        InventoryMovingManager movingManager = ServiceLocator.getInventoryMovingManager(request);
        String[] idsStrings = request.getParameterValues("ids");
        String location_code = request.getParameter("locationId");

        StorageLocation sl = slm.getStorageLocation(Integer.valueOf(Integer.parseInt(location_code)));

        List<ProductGoline> golineList = new ArrayList<ProductGoline>();
        ProductGoline pg = null;
        InventoryMoving moving = new InventoryMoving();
        StorageLocation oldLocation = null;
        BigDecimal qty = new BigDecimal(1);
        int count = 0;
        String[] code = new String[idsStrings.length];
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < idsStrings.length; i++) {
            Integer id = Integer.valueOf(Integer.parseInt(idsStrings[i]));
            pg = pgm.getProductGoline(id);
            if (oldLocation == null) {
                oldLocation = pg.getLocationCode();
            }
            code[i] = pg.getHncCode();
            if (sl.getBasic_storeroom_id().getType() == StoreRoomType.RAWMATERIALSLINE) {
                pg.setStatus(Integer.valueOf(2));
            }
            pg.setLocationCode(sl);
            pgm.updateProductGoline(pg);
            golineList.add(pg);
        }  byte b; int j;
        String[] arrayOfString1;
        for (j = (arrayOfString1 = code).length, b = 0; b < j; ) { String str = arrayOfString1[b];
            if (!list.contains(str))
                list.add(str);
            b++; }

        for (String str : list) {
            int num = 0; byte b1; int k; String[] arrayOfString;
            for (k = (arrayOfString = code).length, b1 = 0; b1 < k; ) { String s = arrayOfString[b1];
                if (s.equals(str))
                    num++;
                b1++; }

            WmsPart wmsPart = partManager.getWmsPart(str);
            moving.setOld_location(oldLocation);
            moving.setPart(wmsPart);
            moving.setNew_location(sl);
            moving.setQty(new BigDecimal(num));
            moving.setDate(new Date());
            moving.setIs_sync(YesNo.NO);
            moving.setRemark("成品移库：" + oldLocation.getCode() + "-" + sl.getCode() + ",成品号：" + wmsPart.getId());
            movingManager.insertInventoryMoving(moving);
            insertInventoryDetial(partManager.getWmsPart(str), oldLocation, manager, new BigDecimal(-num));
            insertInventoryDetial(partManager.getWmsPart(str), sl, manager, new BigDecimal(num));
        }

        WmsPart part = partManager.getWmsPart(((ProductGoline)golineList.get(0)).getHncCode());
        return mapping.findForward("success");
    }
}
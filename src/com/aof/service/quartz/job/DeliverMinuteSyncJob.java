package com.aof.service.quartz.job;

import com.aof.dao.admin.SynBaseDAO;
import com.aof.dao.sync.SyncDAO;
import com.aof.model.basic.CustomerReturnItem;
import com.aof.model.basic.StorageLocation;
import com.aof.model.basic.SyncLog;
import com.aof.model.basic.WmsPart;
import com.aof.model.comprehensive.ProduceBuckleMaterial;
import com.aof.model.comprehensive.StockingRecord;
import com.aof.model.inventory.InventoryMoving;
import com.aof.model.metadata.StoreRoomType;
import com.aof.model.metadata.YesNo;
import com.aof.model.plantWarehouse.WmsPlanToGoOutSub;
import com.aof.model.plantWarehouse.WmsUnplannedWarehousingItem;
import com.aof.model.po.Box;
import com.aof.model.po.PortalShipOrder;
import com.aof.model.po.PortalShipOrderItem;
import com.aof.model.po.PurchaseOrderPutInStorage;
import com.aof.model.po.WmsLot;
import com.aof.model.product.ProductOutStorage;
import com.aof.model.product.SalesOrderItem;
import com.aof.model.product.SalesWorkorder;
import com.aof.model.sync.shared.DepartmentReceiveParts;
import com.aof.model.sync.shared.MakeAnInventory;
import com.aof.model.sync.shared.PurchaseReceipt;
import com.aof.model.sync.shared.QADMessCtrl;
import com.aof.model.sync.shared.TransferStorage;
import com.aof.model.sync.shared.UnplanInAndOutStorage;
import com.aof.model.sync.shared.WorkOrderBackFlushInStroage;
import com.aof.model.sync.shared.WorkOrderBuckleMaterial;
import com.aof.model.sync.shared.XbipddDet;
import com.aof.model.sync.shared.XbipdmMstr;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.map.HashedMap;

















public class DeliverMinuteSyncJob
{
    private SynBaseDAO dao;
    private SyncDAO daoShared;
    private String siteDomainCode = "YA01";

    public void setDao(SynBaseDAO dao) {
        this.dao = dao;
    }

    public void setDaoShared(SyncDAO daoShared) {
        this.daoShared = daoShared;
    }



    public static void test() {}


    public void startSyn() {
        try {
            insertSystemLog("DailySyncJob", "startSync", "", "0");



            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy/MM/dd hh:mm:ss");
            System.out.println("insertPoIn-------------------------1" +
                    format.format(date));










































            shipOrder();
            System.out.println("shipOrder-------------------------9 " +
                    format.format(date));
        } catch (Exception e) {
            insertSystemLog("DailySyncJob", "main", e.getMessage(), "1");
        }
    }


    public void startSynOne(PortalShipOrder portalShipOrder) {
        try {
            insertSystemLog("DailySyncJob", "startSync", "", "0");



            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy/MM/dd hh:mm:ss");
            System.out.println("insertPoIn-------------------------1" +
                    format.format(date));

            shipOrderOne(portalShipOrder);
            System.out.println("shipOrder-------------------------9 " +
                    format.format(date));
        } catch (Exception e) {
            insertSystemLog("DailySyncJob", "main", e.getMessage(), "1");
        }
    }






    private Integer insertPoIn() {
        int num = 0;

        try {
            String str = getSeq();





            QADMessCtrl messCtrl =
                    insertQADMessCtrl("XXMES_PORC_DET", str);
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");


            List<PurchaseOrderPutInStorage> list = this.dao
                    .getObjectList("from PurchaseOrderPutInStorage pps where pps.is_sync = 1");
            Map<String, BigDecimal> hashedMap = new HashedMap();
            for (PurchaseOrderPutInStorage inStorage : list) {
                StorageLocation location = inStorage.getLocation();
                BigDecimal qty = inStorage.getQty();
                WmsPart part = inStorage.getPart();
                String po = inStorage.getPo_number();
                String line = inStorage.getLine();

                String roomCode = location.getCode();
                WmsLot lot = inStorage.getLotSer();
                String date = format2.format(inStorage.getDate());
                String sgin = String.valueOf(po) + "," + line + "," + part.getId() + "," +
                        location.getCode() + "," + roomCode + "," +
                        inStorage.getId() + "," + lot.getId() + "," + date +
                        ",";

                if (!hashedMap.containsKey(sgin)) {
                    hashedMap.put(sgin, qty);
                } else {
                    BigDecimal oldQty = hashedMap.get(sgin);
                    if (qty != null && !qty.equals("")) {
                        hashedMap.put(sgin, oldQty.add(qty));
                    } else {
                        hashedMap.put(sgin, oldQty);
                    }
                }
                inStorage.setIs_sync(YesNo.YES);
                inStorage.setIs_sync_date(new Date());
                this.dao.updateObject(inStorage);
            }

            Iterator<String> iterator = hashedMap.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                BigDecimal sumQty = (BigDecimal) hashedMap.get(key);

                if (key != null) {
                    String[] arrays = key.split(",");
                    String po = arrays[0];
                    String line = arrays[1];
                    String part = arrays[2];
                    String roomCode = arrays[4];
                    Integer storage_det_id = Integer.valueOf(Integer.parseInt(arrays[5]));
                    String lotSer = arrays[6];
                    String date1 = arrays[7];
                    Date date2 = format2.parse(date1);



















                    PurchaseReceipt instroage = new PurchaseReceipt();
                    instroage.setXxmes_porc_det_id(storage_det_id);
                    instroage.setXxmes_porc_ponbr(po);
                    instroage.setXxmes_porc_poline(Integer.valueOf(Integer.parseInt(line)));
                    instroage.setXxmes_porc_part(part);

                    instroage.setXxmes_porc_date(date2);
                    instroage.setXxmes_porc_qty_real(sumQty);
                    instroage.setXxmes_porc_loc(roomCode);
                    instroage.setXxmes_porc_boxcode(lotSer);
                    instroage.setXxmes_porc_potype("M");
                    instroage.setXxmes_porc_seq(str);
                    instroage.setXxmes_porc_qadread("0");
                    instroage.setXxmes_porc_mesread("1");
                    instroage.setXxmes_porc_portalread("0");

                    instroage.setXxmes_porc_site(this.siteDomainCode);
                    instroage.setXxmes_porc_domain(this.siteDomainCode);

                    this.daoShared.saveObject(instroage);
                    num++;
                }
            }

            if (num > 0) {
                messCtrl.setXxmes_table_qty(new BigDecimal(num));
                this.daoShared.saveObject(messCtrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
            insertSystemLog("DeliverMinuteSyncJob", "insertPoIn",
                    e.getMessage(), "1");
        }

        return Integer.valueOf(num);
    }

    private QADMessCtrl insertQADMessCtrl(String tablename, String str) {
        QADMessCtrl messCtrl = new QADMessCtrl();
        messCtrl.setXxmes_seq(str);
        messCtrl.setXxmes_table(tablename);
        messCtrl.setXxmes_qad("0");
        messCtrl.setXxmes_mes("1");
        messCtrl.setXxmes_portal("0");
        messCtrl.setXxmes_edi("0");

        messCtrl.setXxmes_site(this.siteDomainCode);
        messCtrl.setXxmes_domain(this.siteDomainCode);

        return messCtrl;
    }

    private String getStoreRoom(StorageLocation moving) {
        String oldlocation = null;
        StoreRoomType locationType = moving.getBasic_storeroom_id().getType();

        if (locationType.equals(StoreRoomType.RAWMATERIALSLIBRARY)) {
            oldlocation = "CK001";
        } else if (locationType.equals(StoreRoomType.RETURNOFTHELIBRARY)) {
            oldlocation = "CK005";
        } else if (locationType.equals(StoreRoomType.RAWMATERIALSLINE)) {
            oldlocation = "CK004";
        } else if (locationType
                .equals(StoreRoomType.FINISHEDPRODUCELINEALONGTHE)) {
            oldlocation = "CK006";
        } else if (locationType.equals(StoreRoomType.DETEMINETHELIBRARY)) {
            oldlocation = "CK008";
        } else if (locationType.equals(StoreRoomType.SHIPPINGDEPARTMENT)) {
            oldlocation = "CK008";
        } else if (locationType.equals(StoreRoomType.PRODUCELINE)) {
            oldlocation = "CK004";
        } else if (locationType.equals(StoreRoomType.RAWMATERIALSLINE)) {
            oldlocation = "CK004";
        }

        return oldlocation;
    }






    private Integer moveLocation() {
        int num = 0;

        try {
            String str = getSeq();

            QADMessCtrl messCtrl = insertQADMessCtrl("XXMES_IC_DET", str);
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");

            List<InventoryMoving> list = this.dao
                    .getObjectList("from InventoryMoving im where im.is_sync = 1");
            Map<Object, Object> map = new HashMap<Object, Object>();
            for (InventoryMoving moving : list) {




                String oldlocationCode = moving.getOld_location().getCode();
                String newlocationCode = moving.getNew_location().getCode();

                WmsPart part = moving.getPart();
                BigDecimal qty = moving.getQty();
                Date date = moving.getDate();
                Integer id = moving.getId();

                WmsLot lot = moving.getOldLotSer();
                WmsLot newlot = moving.getLotSer();

                String sgin = String.valueOf(oldlocationCode) + "," + newlocationCode + "," +
                        part.getId() + "," + date + "," + id + "," +
                        lot.getId() + "," + newlot.getId() + ",";

                if (map.get(sgin) == null) {
                    map.put(sgin, qty);
                } else {
                    BigDecimal amount = (BigDecimal)map.get(sgin);
                    if (amount != null && !amount.equals("")) {
                        map.put(sgin, amount.add(qty));
                    } else {
                        map.put(sgin, amount);
                    }
                }
                moving.setIs_sync(YesNo.YES);
                moving.setIs_sync_date(new Date());
                this.dao.updateObject(moving);
            }

            Iterator<Object> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                BigDecimal qty = (BigDecimal)map.get(key);

                String[] arrays = key.split(",");
                String oldlocation = arrays[0];
                String newlocation = arrays[1];
                String part = arrays[2];
                String newdate = arrays[3];
                Integer id = Integer.valueOf(Integer.parseInt(arrays[4]));
                String lotSer = arrays[5];
                String newLotSer = arrays[6];


                Date date = format2.parse(newdate);

                TransferStorage storage = new TransferStorage();
                storage.setXxmes_ic_seq(str);
                storage.setXxmes_ic_det_id(id);
                storage.setXxmes_ic_part(part);
                storage.setXxmes_ic_box_code(lotSer);
                storage.setXxmes_ic_qty(qty);
                storage.setXxmes_ic_loc_from(oldlocation);
                storage.setXxmes_ic_loc_to(newlocation);
                storage.setXxmes_ic_date(date);
                storage.setXxmes_ic_mesread("1");
                storage.setXxmes_ic_qadread("0");
                storage.setXxmes_ic_portalread("1");
                storage.setXxmes_ic_ediread("1");
                storage.setXxmes_ic_box_code_to(newLotSer);
                storage.setXxmes_ic_createdt(new Date());

                storage.setXxmes_ic_site(this.siteDomainCode);
                storage.setXxmes_ic_domain(this.siteDomainCode);











                this.daoShared.saveObject(storage);

                num++;
            }


            if (num > 0) {
                messCtrl.setXxmes_table_qty(new BigDecimal(num));
                this.daoShared.saveObject(messCtrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
            insertSystemLog("DeliverMinuteSyncJob", "moveLocation",
                    e.getMessage(), "1");
        }

        return Integer.valueOf(num);
    }






    public Integer productOutLocation() {
        int num = 0;

        try {
            String str = getSeq();

            QADMessCtrl messCtrl =
                    insertQADMessCtrl("XXMES_SHIP_DET", str);


            Map<Object, Object> map = new HashMap<Object, Object>();


            List<ProductOutStorage> list = this.dao
                    .getObjectList("from ProductOutStorage p where p.status=1 and p.issync=0");
            for (ProductOutStorage product : list) {

                String part = product.getHncCode();
                int qty = product.getQty().intValue();
                Date date = product.getOutDate();

                String CKcode = product.getLocation().getCode();
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat format2 = new SimpleDateFormat(
                        "yyyy-MM-dd hh:mm:ss");
                BigDecimal big = new BigDecimal(qty);

                String sign = String.valueOf(part) + "," + format1.format(date) + "," +
                        format2.format(date) + "," + CKcode;
                if (map.get(sign) == null) {
                    map.put(sign, big);
                } else {
                    BigDecimal sumqty = (BigDecimal)map.get(sign);
                    if (sumqty != null && !sumqty.equals("")) {
                        map.put(sign, sumqty.add(big));
                    } else {
                        map.put(sign, sumqty);
                    }
                }



                product.setIssync(1);

                this.dao.updateObject(product);
            }

            Iterator<Object> iterator2 = map.keySet().iterator();
            while (iterator2.hasNext()) {
                String key = (String) iterator2.next();
                BigDecimal qty = (BigDecimal)map.get(key);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat format2 = new SimpleDateFormat(
                        "yyyy-MM-dd hh:mm:ss");
                String[] arrays = key.split(",");
                String part = arrays[0];
                String date = arrays[1];
                String dateNew = arrays[2];
                String storeroomCode = arrays[3];
                Date datetimeRQ = format1.parse(date);
                Date datetimeSJ = format2.parse(dateNew);
                com.aof.model.sync.shared.ProductOutStorage productOutStorage = new com.aof.model.sync.shared.ProductOutStorage();
                productOutStorage.setXxmes_ship_seq(str);
                productOutStorage.setXxmes_ship_part(part);
                productOutStorage.setXxmes_ship_qty(qty);
                productOutStorage.setXxmes_ship_date(datetimeRQ);
                productOutStorage.setXxmes_ship_time(datetimeSJ);

                productOutStorage.setXxmes_ship_site(this.siteDomainCode);
                productOutStorage.setXxmes_ship_domain(this.siteDomainCode);
                productOutStorage.setXxmes_ship_loc("CK004");
                productOutStorage.setXxmes_ship_qadread("0");
                productOutStorage.setXxmes_ship_mesread("1");
                productOutStorage.setXxmes_ship_portalread("0");
                productOutStorage.setXxmes_ship_ediread("0");
                productOutStorage.setXxmes_ship_shipto(storeroomCode);


                this.daoShared.saveObject(productOutStorage);
                num++;
            }

            if (num > 0) {
                messCtrl.setXxmes_table_qty(new BigDecimal(num));
                this.daoShared.saveObject(messCtrl);
            }
        } catch (Exception e) {
            insertSystemLog("DeliverMinuteSyncJob", "productOutLocation",
                    e.getMessage(), "1");
            e.printStackTrace();
        }

        return Integer.valueOf(num);
    }

    private String getSeq() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String str = String.valueOf(format.format(new Date())) + "-";

        String sql = "select max(qc.xxmes_seq) from QADMessCtrl qc where qc.xxmes_seq like '" +
                str + "%' ";
        List listQADMessCtrl = this.daoShared.getObjectList(sql);
        if (listQADMessCtrl.size() == 0 || listQADMessCtrl.get(0) == null) {
            str = String.valueOf(str) + "1";
        } else {
            String array = listQADMessCtrl.get(0).toString();
            String[] arrays = array.split("-");
            String seq = arrays[1];
            Integer newSeq = Integer.valueOf(Integer.parseInt(seq) + 1);
            str = String.valueOf(str) + newSeq.toString();
        }

        return str;
    }






    private Integer unplannedIn() {
        int num = 0;

        try {
            String strH = getSeq();

            boolean mfg = true;



            QADMessCtrl messCtrlHave = insertQADMessCtrl(
                    "XXMES_UPISS_DET", strH);

            List<WmsUnplannedWarehousingItem> list1 = this.dao
                    .getObjectList("from WmsUnplannedWarehousingItem item where item.is_sync = 1 and item.unplanned_warehousing_id.reason_code is not null ");
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
            Map<Object, Object> map = new HashMap<Object, Object>();
            for (WmsUnplannedWarehousingItem item : list1) {

                String part = item.getBox_id().getPart().getId();

                BigDecimal qty = item.getQty();
                StorageLocation location = item.getBox_id().getLocation();
                String dept = item.getUnplanned_warehousing_id()
                        .getReason_code().getInstructions();
                String project = item.getUnplanned_warehousing_id()
                        .getReason_code().getExpenses_course();

                Date date = item.getUnplanned_warehousing_id().getDate();


                String code = location.getCode();

                String workCenter = item.getUnplanned_warehousing_id()
                        .getReason_code().getDepartment_cost();
                String userName = item.getUnplanned_warehousing_id()
                        .getReason_code().getUser().getName();


                String sign = String.valueOf(part) + "," + code + "," + dept + "," + date + "," +
                        project + "," + "," + item.getId() + "," + userName +
                        "," + workCenter;






                if (map.get(sign) == null) {
                    map.put(sign, qty);
                } else {
                    BigDecimal sumqty = (BigDecimal)map.get(sign);
                    if (sumqty != null && !sumqty.equals("")) {
                        map.put(sign, sumqty.add(qty));
                    } else {
                        map.put(sign, sumqty);
                    }
                }
                item.setIs_sync(YesNo.YES);
                item.setIs_sync_date(new Date());
                this.dao.updateObject(item);
            }

            Iterator<Object> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                BigDecimal sumQty = (BigDecimal)map.get(key);

                String[] arrays = key.split(",");
                String part = arrays[0];
                String code = arrays[1];
                String dept = arrays[2];
                String newdate = arrays[3];
                String project = arrays[4];
                Integer unid = Integer.valueOf(Integer.parseInt(arrays[6]));

                String users = arrays[7];
                String workcenter = arrays[8];

                Date date = format2.parse(newdate);























                UnplanInAndOutStorage unplanInAndOutStorage = new UnplanInAndOutStorage();

                unplanInAndOutStorage.setXxmes_upiss_createur(users);
                unplanInAndOutStorage.setXxmes_upiss_reason(dept);
                unplanInAndOutStorage.setXxmes_upiss_workcenter(workcenter);
                unplanInAndOutStorage.setXxmes_upiss_char1(project);
                unplanInAndOutStorage.setXxmes_upiss_seq(strH);
                unplanInAndOutStorage.setXxmes_upiss_part(part);
                unplanInAndOutStorage.setXxmes_upiss_qty(sumQty);
                unplanInAndOutStorage.setXxmes_upiss_loc(code);
                unplanInAndOutStorage.setXxmes_upiss_dir("2");
                unplanInAndOutStorage.setXxmes_upiss_date(date);
                unplanInAndOutStorage.setXxmes_upiss_qadread("0");
                unplanInAndOutStorage.setXxmes_upiss_site(this.siteDomainCode);
                unplanInAndOutStorage.setXxmes_upiss_domain(this.siteDomainCode);






                this.daoShared.saveObject(unplanInAndOutStorage);
                num++;
            }

            if (num > 0) {
                messCtrlHave.setXxmes_table_qty(new BigDecimal(num));
                this.daoShared.saveObject(messCtrlHave);
            }

            if (mfg) {
                String strN = getSeq();



                QADMessCtrl messCtrlNot = insertQADMessCtrl(
                        "XXMES_LY_DET", strN);

                num = 0;

                List<WmsUnplannedWarehousingItem> list2 = this.dao
                        .getObjectList("from WmsUnplannedWarehousingItem item where item.is_sync = 1 and item.unplanned_warehousing_id.reason_code is null ");

                Map<Object, Object> mapN = new HashMap<Object, Object>();
                for (WmsUnplannedWarehousingItem item : list2) {
                    String part = item.getBox_id().getPart().getId();

                    BigDecimal qty = item.getQty();
                    String code = item.getLocation().getCode();
                    Date date = item.getUnplanned_warehousing_id().getDate();
                    String remark = item.getUnplanned_warehousing_id()
                            .getRemark();


                    String sign = String.valueOf(part) + "," + code + "," + date + "," + "," +
                            item.getId() + ",";
                    if (mapN.get(sign) == null) {
                        mapN.put(sign, qty);
                    } else {
                        BigDecimal sumqty = (BigDecimal)mapN.get(sign);
                        if (sumqty != null && !sumqty.equals("")) {
                            mapN.put(sign, sumqty.add(qty));
                        } else {
                            mapN.put(sign, sumqty);
                        }
                    }
                    item.setIs_sync(YesNo.YES);
                    item.setIs_sync_date(new Date());
                    this.dao.updateObject(item);
                }

                Iterator<Object> iterator2 = mapN.keySet().iterator();
                while (iterator2.hasNext()) {
                    String key = (String) iterator2.next();
                    BigDecimal qty = (BigDecimal)map.get(key);

                    String[] arrays = key.split(",");
                    String part = arrays[0];
                    String code = arrays[1];
                    String dateNew = arrays[2];
                    Integer unid = Integer.valueOf(Integer.parseInt(arrays[3]));
                    Date date = format2.parse(dateNew);




















                    DepartmentReceiveParts departmentReceiveParts = new DepartmentReceiveParts();

                    departmentReceiveParts.setXxmes_ly_seq(strN);
                    departmentReceiveParts.setXxmes_ly_part(part);
                    departmentReceiveParts.setXxmes_ly_qty(qty);
                    departmentReceiveParts.setXxmes_ly_loc(code);


                    departmentReceiveParts.setXxmes_ly_date(date);
                    departmentReceiveParts.setXxmes_ly_qadread("0");






                    departmentReceiveParts.setXxmes_ly_site(this.siteDomainCode);
                    departmentReceiveParts.setXxmes_ly_domain(this.siteDomainCode);

                    this.daoShared.saveObject(departmentReceiveParts);
                    num++;
                }

                if (num > 0) {
                    messCtrlNot.setXxmes_table_qty(new BigDecimal(num));
                    this.daoShared.saveObject(messCtrlNot);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            insertSystemLog("DeliverMinuteSyncJob", "unplannedIn",
                    e.getMessage(), "1");
        }

        return Integer.valueOf(num);
    }






    private Integer unplannedOut() {
        int num = 0;

        try {
            String strH = getSeq();



            QADMessCtrl messCtrlHave = insertQADMessCtrl(
                    "XXMES_UPISS_DET", strH);



            List<WmsPlanToGoOutSub> list1 = this.dao
                    .getObjectList("from WmsPlanToGoOutSub sub where sub.is_sync <> 0  or sub.is_sync is null and sub.unplanned_outbound_detial_id.unplanned_outbound_id.reason_code is not null ");

            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
            Map<Object, Object> map = new HashMap<Object, Object>();
            for (WmsPlanToGoOutSub sub : list1) {
                String part = sub.getBox_id().getPart().getId();

                StorageLocation location = sub.getBox_id().getLocation();

                BigDecimal qty = sub.getBox_id().getNumber();
                Date date = sub.getUnplanned_outbound_detial_id()
                        .getUnplanned_outbound_id().getDate();
                String dept = sub.getUnplanned_outbound_detial_id()
                        .getUnplanned_outbound_id().getReason_code()
                        .getInstructions();
                String project = sub.getUnplanned_outbound_detial_id()
                        .getUnplanned_outbound_id().getReason_code()
                        .getExpenses_course();


                String code = location.getCode();
                String lotSer = sub.getBox_id().getLot().getId();
                String userName = sub.getUnplanned_outbound_detial_id()
                        .getUnplanned_outbound_id().getReason_code().getUser()
                        .getName();
                String workCenter = sub.getUnplanned_outbound_detial_id()
                        .getUnplanned_outbound_id().getReason_code()
                        .getDepartment_cost();

                String sign = String.valueOf(project) + "," + dept + "," + date + "," + code +
                        "," + part + "," + sub.getId() + "," + userName + "," +
                        workCenter + "," + lotSer;
                if (map.get(sign) == null) {
                    map.put(sign, qty);
                } else {
                    BigDecimal sumqty = (BigDecimal)map.get(sign);
                    if (sumqty != null && !sumqty.equals("")) {
                        map.put(sign, sumqty.add(qty));
                    } else {
                        map.put(sign, sumqty);
                    }
                }
                sub.setIs_sync(YesNo.YES);
                sub.setIs_sync_date(new Date());
                this.dao.updateObject(sub);
            }

            Iterator<Object> iterator2 = map.keySet().iterator();
            while (iterator2.hasNext()) {
                String key = (String) iterator2.next();
                BigDecimal qty = (BigDecimal)map.get(key);

                String[] arrays = key.split(",");
                String project = arrays[0];
                String dept = arrays[1];
                String dateNew = arrays[2];
                String code = arrays[3];
                String part = arrays[4];
                Integer subId = Integer.valueOf(Integer.parseInt(arrays[5]));
                Date date = format2.parse(dateNew);

                String users = arrays[6];
                String workcenter = arrays[7];
                String boxCode = arrays[8];


















                UnplanInAndOutStorage unplanInAndOutStorage = new UnplanInAndOutStorage();

                unplanInAndOutStorage.setXxmes_upiss_createur(users);
                unplanInAndOutStorage.setXxmes_upiss_reason(dept);
                unplanInAndOutStorage.setXxmes_upiss_workcenter(workcenter);
                unplanInAndOutStorage.setXxmes_upiss_char1(project);
                unplanInAndOutStorage.setXxmes_upiss_seq(strH);
                unplanInAndOutStorage.setXxmes_upiss_part(part);
                unplanInAndOutStorage.setXxmes_upiss_boxcode(boxCode);
                unplanInAndOutStorage.setXxmes_upiss_qty(qty);
                unplanInAndOutStorage.setXxmes_upiss_loc(code);
                unplanInAndOutStorage.setXxmes_upiss_dir("1");
                unplanInAndOutStorage.setXxmes_upiss_date(date);
                unplanInAndOutStorage.setXxmes_upiss_qadread("0");
                unplanInAndOutStorage.setXxmes_upiss_site(this.siteDomainCode);
                unplanInAndOutStorage.setXxmes_upiss_domain(this.siteDomainCode);






                this.daoShared.saveObject(unplanInAndOutStorage);
                num++;
            }

            if (num > 0) {
                messCtrlHave.setXxmes_table_qty(new BigDecimal(num));
                this.daoShared.saveObject(messCtrlHave);
            }


            String strN = getSeq();



            QADMessCtrl messCtrlNot = insertQADMessCtrl("XXMES_LY_DET",
                    strN);

            num = 0;



            List<WmsPlanToGoOutSub> list2 = this.dao
                    .getObjectList("from WmsPlanToGoOutSub sub where sub.is_sync = 1 and sub.unplanned_outbound_detial_id.unplanned_outbound_id.reason_code is null ");

            Map<Object, Object> mapN = new HashMap<Object, Object>();
            for (WmsPlanToGoOutSub sub : list2) {
                String part = sub.getBox_id().getPart().getId();
                StorageLocation location = sub.getLocation();
                BigDecimal qty = sub.getQty().multiply(new BigDecimal(-1));
                Date date = sub.getUnplanned_outbound_detial_id()
                        .getUnplanned_outbound_id().getDate();

                String code = getStoreRoom(location);
                String lotSer = sub.getBox_id().getLot().getId();
                String sign = date + "," + code + "," + part + "," +
                        sub.getId() + "," + lotSer;
                if (mapN.get(sign) == null) {
                    mapN.put(sign, qty);
                } else {
                    BigDecimal sumqty = (BigDecimal)mapN.get(sign);
                    if (sumqty != null && !sumqty.equals("")) {
                        mapN.put(sign, sumqty.add(qty));
                    } else {
                        mapN.put(sign, sumqty);
                    }
                }
                sub.setIs_sync(YesNo.YES);
                sub.setIs_sync_date(new Date());
                this.dao.updateObject(sub);
            }

            Iterator<Object> iterator3 = mapN.keySet().iterator();
            while (iterator3.hasNext()) {
                String key = (String) iterator3.next();
                BigDecimal qty = (BigDecimal)mapN.get(key);

                String[] arrays = key.split(",");
                String dateNew = arrays[0];
                String code = arrays[1];
                String part = arrays[2];
                Integer subid = Integer.valueOf(Integer.parseInt(arrays[3]));
                String boxCode = arrays[4];
                Date date = format2.parse(dateNew);
















                DepartmentReceiveParts departmentReceiveParts = new DepartmentReceiveParts();
                departmentReceiveParts.setXxmes_ly_det_id(subid);
                departmentReceiveParts.setXxmes_ly_seq(strN);
                departmentReceiveParts.setXxmes_ly_part(part);
                departmentReceiveParts.setXxmes_ly_qty(qty);
                departmentReceiveParts.setXxmes_ly_loc(code);
                departmentReceiveParts.setXxmes_ly_boxcode(boxCode);


                departmentReceiveParts.setXxmes_ly_date(date);
                departmentReceiveParts.setXxmes_ly_qadread("0");
                departmentReceiveParts.setXxmes_ly_mesread("1");
                departmentReceiveParts.setXxmes_ly_portalread("0");
                departmentReceiveParts.setXxmes_ly_ediread("0");
                departmentReceiveParts.setXxmes_ly_createdt(new Date());
                departmentReceiveParts.setXxmes_ly_updatedt(new Date());

                departmentReceiveParts.setXxmes_ly_site(this.siteDomainCode);
                departmentReceiveParts.setXxmes_ly_domain(this.siteDomainCode);

                this.daoShared.saveObject(departmentReceiveParts);
                num++;
            }

            if (num > 0) {
                messCtrlNot.setXxmes_table_qty(new BigDecimal(num));
                this.daoShared.saveObject(messCtrlNot);
            }
        } catch (Exception e) {
            insertSystemLog("DeliverMinuteSyncJob", "unplannedOut",
                    e.getMessage(), "1");
        }

        return Integer.valueOf(num);
    }






    private Integer inventory() {
        int num = 0;










        try {
            String str = getSeq();

            QADMessCtrl messCtrl = insertQADMessCtrl("XXMES_TAG_DET", str);


            Map<Object, Object> map = new HashMap<Object, Object>();
            List<StockingRecord> list = this.dao
                    .getObjectList("from StockingRecord sr where (sr.is_sync <> 0  or sr.is_sync is null) and sr.stocking_id.confirmStatus=0 and sr.type=2 and sr.status=1 ");


            for (StockingRecord record : list) {
                String part = record.getBox().getPart().getId();
                StorageLocation location = record.getLocation();
                String lot = record.getBox().getLot().getId();
                BigDecimal qty_cnt = record.getStocking_qty();
                BigDecimal differences = record.getDifferences();
                if (differences.compareTo(new BigDecimal(0)) == 1) {
                    location = record.getLocation();
                } else {
                    location = record.getBox().getLocation();
                }
                Date date = record.getCreateDate();


                String code = location.getCode();

                String sign = String.valueOf(code) + "," + part + "," + date + "," +
                        record.getId() + "," + lot;
                String strQty = "";
                if (map.get(sign) == null) {
                    strQty = String.valueOf(strQty) + qty_cnt + "," + differences + ",";
                    map.put(sign, strQty);
                } else {
                    String new_strQty = "";
                    String sumqtyStr = (String)map.get(sign);
                    if (sumqtyStr != null && !sumqtyStr.equals("")) {
                        String[] arrays = sumqtyStr.split(",");
                        String qty_cnt_new = arrays[0];
                        String differences_new = arrays[1];

                        new_strQty = String.valueOf(new_strQty) + (
                                new BigDecimal(qty_cnt_new)).add(qty_cnt) +
                                "," + (
                                new BigDecimal(differences_new))
                                .add(differences) + "," +
                                record.getId() + ",";

                        map.put(sign, new_strQty);
                    } else {
                        String strQty_add = qty_cnt + "," + differences + ",";

                        map.put(sign, strQty_add);
                    }
                }

                record.setIs_sync(YesNo.YES);
                record.setIs_sync_date(new Date());
                this.dao.updateObject(record);
            }

            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
            Iterator<Object> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String sumqyt_str = (String)map.get(key);

                String[] arrays = key.split(",");
                String code = arrays[0];
                String part = arrays[1];
                String datenew = arrays[2];
                Integer rid = Integer.valueOf(Integer.parseInt(arrays[3]));
                String lotSer = arrays[4];

                String[] array = sumqyt_str.split(",");
                String qty_cnt = array[0];
                String differences = array[1];

                Date date = format2.parse(datenew);

                MakeAnInventory makeAnInventory = new MakeAnInventory();
                makeAnInventory.setXxmes_tag_det_id(rid);
                makeAnInventory.setXxmes_tag_seq(str);
                makeAnInventory.setXxmes_tag_part(part);
                makeAnInventory.setXxmes_tag_loc(code);
                makeAnInventory.setXxmes_tag_qty_cnt(new BigDecimal(qty_cnt));
                makeAnInventory
                        .setXxmes_tag_qty_var(new BigDecimal(differences));
                makeAnInventory.setXxmes_tag_date(date);
                makeAnInventory.setXxmes_tag_qadread("0");
                makeAnInventory.setXxmes_tag_mesread("1");
                makeAnInventory.setXxmes_tag_createdt(new Date());

                makeAnInventory.setXxmes_tag_site(this.siteDomainCode);
                makeAnInventory.setXxmes_tag_domain(this.siteDomainCode);
                makeAnInventory.setXxmes_tag_boxcode(lotSer);

                this.daoShared.saveObject(makeAnInventory);

                num++;
            }

            if (num > 0) {
                messCtrl.setXxmes_table_qty(new BigDecimal(num));
                this.daoShared.saveObject(messCtrl);
            }
        } catch (Exception e) {
            insertSystemLog("DeliverMinuteSyncJob", "inventory",
                    e.getMessage(), "1");
        }

        return Integer.valueOf(num);
    }






    private Integer buckleMaterial() {
        int num = 0;

        try {
            String str = getSeq();

            QADMessCtrl messCtrl =
                    insertQADMessCtrl("SHMES_WOIS_DET", str);


            String str_seq = getSeq();

            QADMessCtrl messCtrl_buckleMaterial = insertQADMessCtrl(
                    "SHMES_WORC_DET", str_seq);








            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");



            List<ProduceBuckleMaterial> list = this.dao
                    .getObjectList("from ProduceBuckleMaterial pm where pm.is_sync is null or pm.is_sync<>0 order by pm.id ");
























            Map<Object, Object> map = new HashMap<Object, Object>();
            for (ProduceBuckleMaterial material : list) {
                Date date = material.getDate();
                String part = material.getPart().getId();
                StorageLocation location = material.getLocation();
                BigDecimal qty = material.getQty();
                String assembly = material.getAssembly().getId();
                Integer growth = material.getGrowth();
                String code = "";
                if (location == null) {
                    code = "";
                } else {

                    code = location.getCode();
                }

                String sign = String.valueOf(assembly) + "," + part + "," + code + "," +
                        format.format(date) + "," + material.getId() + "," +
                        growth + ",";
                if (map.get(sign) == null) {
                    map.put(sign, qty);
                } else {
                    BigDecimal sumqty = (BigDecimal)map.get(sign);
                    if (sumqty != null && !sumqty.equals("")) {
                        map.put(sign, sumqty.add(qty));
                    } else {
                        map.put(sign, sumqty);
                    }
                }
                material.setIs_sync(YesNo.YES);
                material.setIs_sync_date(new Date());
                this.dao.updateObject(material);
            }

            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
            Iterator<Object> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                BigDecimal sumqyt = (BigDecimal)map.get(key);

                String[] arrays = key.split(",");
                String assembly = arrays[0];
                String part = arrays[1];
                String code = arrays[2];
                String datenew = arrays[3];
                Integer mid = Integer.valueOf(Integer.parseInt(arrays[4]));
                Integer growth = Integer.valueOf(Integer.parseInt(arrays[5]));
                Date date = format2.parse(datenew);


                WorkOrderBackFlushInStroage stroage = new WorkOrderBackFlushInStroage();
                stroage.setShmes_worc_seq(str);
                stroage.setShmes_worc_item(assembly);
                stroage.setShmes_worc_det_id(growth);
                stroage.setShmes_rworc_id(mid.toString());
                stroage.setShmes_worc_loc("CK004");
                stroage.setShmes_worc_qty(sumqyt);
                stroage.setShmes_worc_date(date);
                stroage.setShmes_worc_qadread("0");
                stroage.setShmes_worc_mesread("1");
                stroage.setShmes_worc_portalread("0");
                stroage.setShmes_worc_ediread("0");
                stroage.setShmes_worc_createdt(new Date());

                stroage.setShmes_worc_site(this.siteDomainCode);
                stroage.setShmes_worc_domain(this.siteDomainCode);
                stroage.setShmes_rworc_note("1");
                if (code.trim().length() == 0) {
                    this.daoShared.saveObject(stroage);
                }

                WorkOrderBuckleMaterial buckleMaterial = new WorkOrderBuckleMaterial();
                buckleMaterial.setShmes_wois_det_id(mid);
                buckleMaterial.setShmes_wois_seq(str);
                buckleMaterial.setShmes_wois_item(assembly);
                buckleMaterial.setShmes_wois_part(part);
                buckleMaterial.setShmes_wois_loc(code);
                buckleMaterial.setShmes_wois_qty(sumqyt);
                buckleMaterial.setShmes_wois_date(date);
                buckleMaterial.setShmes_wois_qadread("0");
                buckleMaterial.setShmes_wois_mesread("1");
                buckleMaterial.setShmes_wois_portalread("0");
                buckleMaterial.setShmes_wois_ediread("0");
                buckleMaterial.setShmes_wois_createdt(new Date());
                buckleMaterial.setShmes_wois_fk(growth.toString());


                buckleMaterial.setShmes_worc_site(this.siteDomainCode);
                buckleMaterial.setShmes_worc_domain(this.siteDomainCode);
                this.daoShared.saveObject(buckleMaterial);
                num++;
            }

            if (num > 0) {
                messCtrl.setXxmes_table_qty(new BigDecimal(num));
                this.daoShared.saveObject(messCtrl);

                messCtrl_buckleMaterial.setXxmes_table_qty(new BigDecimal(num));
                this.daoShared.saveObject(messCtrl_buckleMaterial);


            }


        }
        catch (Exception e) {
            insertSystemLog("DeliverMinuteSyncJob", "buckleMaterial",
                    e.getMessage(), "1");
            e.printStackTrace();
        }

        return Integer.valueOf(num);
    }






    private Integer workOrder() {
        int num = 0;

        try {
            String str = getSeq();

            QADMessCtrl messCtrl =
                    insertQADMessCtrl("XXMES_SHIP_DET", str);


            Map<Object, Object> map = new HashMap<Object, Object>();


            List<SalesWorkorder> list = this.dao
                    .getObjectList("from SalesWorkorder sw where sw.isSync<>0 and sw.shipId.shPrint=0");
            for (SalesWorkorder salesWorkorder : list) {
                Integer orderType = salesWorkorder.getShipId().getType();
                if (orderType.intValue() == 1) {
                    SalesOrderItem salesOrderItem = salesWorkorder
                            .getShipItemId().getSoipitemId();
                    String str1 = salesWorkorder.getShipItemId()
                            .getSoipitemId().getSoId().getCustCode();
                    String str2 = (salesWorkorder.getShipItemId()
                            .getSoipitemId().getSoId().getCustAddress() == null) ? "" :
                            salesWorkorder.getShipItemId().getSoipitemId()
                                    .getSoId().getCustAddress();
                    String str3 = salesWorkorder.getShipItemId()
                            .getSoipitemId().getItemNumber().getId();
                    BigDecimal bigDecimal = salesWorkorder.getCount();
                    StorageLocation storageLocation = salesWorkorder.getLocation();
                    Date date1 = salesWorkorder.getOutDate();
                    String str4 = salesWorkorder.getShipId().getCode();
                    String str5 = salesWorkorder.getLotSer().getId();
                    String str6 = salesWorkorder.getShipItemId()
                            .getSoipitemId().getLine();
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(
                            "yyyy-MM-dd");
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
                            "yyyy-MM-dd hh:mm:ss");

                    String str7 = orderType + "," +
                            salesOrderItem.getSoipNumber() + "," +
                            str1 + "," + str2 + "," + str3 +
                            "," + storageLocation.getCode() + ',' +
                            simpleDateFormat1.format(date1) + "," + simpleDateFormat2.format(date1) +
                            "," + str4 + "," + str5 + "," + str6 +
                            ",";
                    if (map.get(str7) == null) {
                        map.put(str7, bigDecimal);
                    } else {
                        BigDecimal sumqty = (BigDecimal)map.get(str7);
                        if (sumqty != null && !sumqty.equals("")) {
                            map.put(str7, sumqty.add(bigDecimal));
                        } else {
                            map.put(str7, sumqty);
                        }
                    }  salesWorkorder.setIsSync(YesNo.YES);
                    this.dao.updateObject(salesWorkorder); continue;
                }
                String planNumber = salesWorkorder.getShipItemId()
                        .getCustomerPlanId().getPlanNumbers();
                String customerCode = salesWorkorder.getShipItemId()
                        .getCustomerPlanId().getCustomer().getCode();
                String customerAddress = (salesWorkorder.getShipItemId()
                        .getCustomerPlanId().getCustomerAddress() == null) ? "" :
                        salesWorkorder.getShipItemId()
                                .getCustomerPlanId().getCustomerAddress();
                String part = salesWorkorder.getShipItemId()
                        .getCustomerPlanId().getWmspart().getId();
                BigDecimal qty = salesWorkorder.getCount();
                StorageLocation location = salesWorkorder.getLocation();
                Date date = salesWorkorder.getOutDate();
                String workOrder = salesWorkorder.getShipId().getCode();
                String boxCode = salesWorkorder.getLotSer().getId();
                String line = salesWorkorder.getShipItemId()
                        .getCustomerPlanId().getLine();
                SimpleDateFormat format1 = new SimpleDateFormat(
                        "yyyy-MM-dd");
                SimpleDateFormat format2 = new SimpleDateFormat(
                        "yyyy-MM-dd hh:mm:ss");

                String sign = orderType + "," + planNumber + "," +
                        customerCode + "," + customerAddress + "," + part +
                        "," + location.getCode() + ',' +
                        format1.format(date) + "," + format2.format(date) +
                        "," + workOrder + "," + boxCode + "," + line +
                        ",";
                if (map.get(sign) == null) {
                    map.put(sign, qty);
                } else {
                    BigDecimal sumqty = (BigDecimal)map.get(sign);
                    if (sumqty != null && !sumqty.equals("")) {
                        map.put(sign, sumqty.add(qty));
                    } else {
                        map.put(sign, sumqty);
                    }
                }  salesWorkorder.setIsSync(YesNo.YES);
                this.dao.updateObject(salesWorkorder);
            }

            Iterator<Object> iterator2 = map.keySet().iterator();
            while (iterator2.hasNext()) {
                String key = (String) iterator2.next();
                BigDecimal qty = (BigDecimal)map.get(key);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat format2 = new SimpleDateFormat(
                        "yyyy-MM-dd hh:mm:ss");
                String[] arrays = key.split(",", -1);
                String orderType = arrays[0];
                String shipOrderId = arrays[1];
                String customerCode = arrays[2];
                String customerAddress = arrays[3];
                String part = arrays[4];
                String localtionCode = arrays[5];
                String date = arrays[6];
                String dateNew = arrays[7];
                String workOrder = arrays[8];
                String boxCode = arrays[9];
                String line = arrays[10];
                Date datetimeRQ = format1.parse(date);
                Date datetimeSJ = format2.parse(dateNew);

                com.aof.model.sync.shared.ProductOutStorage productOutStorage = new com.aof.model.sync.shared.ProductOutStorage();
                productOutStorage.setXxmes_ship_seq(str);
                productOutStorage.setXxmes_ship_nbr(shipOrderId);
                productOutStorage.setXxmes_ship_cust(customerCode);
                productOutStorage.setXxmes_ship_shipto(customerAddress);
                productOutStorage.setXxmes_ship_part(part);
                productOutStorage.setXxmes_ship_qty(qty);
                productOutStorage.setXxmes_ship_loc(localtionCode);
                productOutStorage.setXxmes_ship_date(datetimeRQ);
                productOutStorage.setXxmes_ship_time(datetimeSJ);

                productOutStorage.setXxmes_ship_boxcode(boxCode);
                productOutStorage.setXxmes_ship_line(Integer.valueOf(Integer.parseInt(line)));
                if (Integer.parseInt(orderType) == 2) {
                    productOutStorage.setXxmes_ship_type("S");
                }
                productOutStorage.setXxmes_ship_site(this.siteDomainCode);
                productOutStorage.setXxmes_ship_domain(this.siteDomainCode);
                productOutStorage.setXxmes_ship_qadread("0");
                productOutStorage.setXxmes_ship_mesread("1");
                productOutStorage.setXxmes_ship_portalread("0");
                productOutStorage.setXxmes_ship_ediread("0");
                this.daoShared.saveObject(productOutStorage);
                num++;
            }
            if (num > 0) {
                messCtrl.setXxmes_table_qty(new BigDecimal(num));
                this.daoShared.saveObject(messCtrl);
            }
        } catch (Exception e) {
            insertSystemLog("DeliverMinuteSyncJob", "workOrderOutLocation",
                    e.getMessage(), "1");
            e.printStackTrace();
        }

        return Integer.valueOf(num);
    }






    private Integer customerReturnsItem() {
        int num = 0;

        try {
            String str = getSeq();

            QADMessCtrl messCtrl =
                    insertQADMessCtrl("XXMES_SHIP_DET", str);
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format2 = new SimpleDateFormat(
                    "yyyy-MM-dd hh:mm:ss");

            Map<Object, Object> map = new HashMap<Object, Object>();
            List<CustomerReturnItem> list = this.dao
                    .getObjectList("from CustomerReturnItem cri where (cri.isSync<>0 or cri.isSync is null) and cri.customerreturns.printStatus=0");
            for (CustomerReturnItem customerReturnItem : list) {
                com.aof.model.sync.shared.ProductOutStorage productOutStorage = new com.aof.model.sync.shared.ProductOutStorage();
                productOutStorage.setXxmes_ship_seq(str);
                productOutStorage.setXxmes_ship_nbr(customerReturnItem
                        .getCustomerreturns().getReturnNumber());
                productOutStorage.setXxmes_ship_cust(customerReturnItem
                        .getCustomerreturns().getBasicCustomer().getCode());
                productOutStorage.setXxmes_ship_shipto(customerReturnItem
                        .getCustomerreturns().getBasicCustomer().getAddress());
                productOutStorage.setXxmes_ship_part(customerReturnItem
                        .getPart().getId());
                BigDecimal qty = new BigDecimal(customerReturnItem.getQty().intValue());
                BigDecimal qty1 = qty.multiply(new BigDecimal(-1));
                productOutStorage.setXxmes_ship_qty(qty1);
                productOutStorage.setXxmes_ship_loc(customerReturnItem
                        .getReturnStorage());
                productOutStorage.setXxmes_ship_date(format1.parse(format1
                        .format(customerReturnItem.getCustomerreturns()
                                .getReturnDate())));
                productOutStorage.setXxmes_ship_time(format2.parse(format2
                        .format(customerReturnItem.getCustomerreturns()
                                .getReturnDate())));

                productOutStorage.setXxmes_ship_boxcode(customerReturnItem
                        .getBatchNumber());


                productOutStorage.setXxmes_ship_type("S");

                productOutStorage.setXxmes_ship_site(this.siteDomainCode);
                productOutStorage.setXxmes_ship_domain(this.siteDomainCode);
                productOutStorage.setXxmes_ship_qadread("0");
                productOutStorage.setXxmes_ship_mesread("1");
                productOutStorage.setXxmes_ship_portalread("0");
                productOutStorage.setXxmes_ship_ediread("0");
                this.daoShared.saveObject(productOutStorage);

                customerReturnItem.setIsSync(YesNo.YES);
                this.dao.updateObject(customerReturnItem);
                num++;
            }
            if (num > 0) {
                messCtrl.setXxmes_table_qty(new BigDecimal(num));
                this.daoShared.saveObject(messCtrl);
            }

        } catch (Exception e) {
            insertSystemLog("DeliverMinuteSyncJob", " customer_returns_item",
                    e.getMessage(), "1");
            e.printStackTrace();
        }

        return Integer.valueOf(num);
    }


    private void insertSystemLog(String action, String content, String sync_describe, String syncResults) {
        SyncLog log = new SyncLog();
        log.setSync_date(new Date());
        log.setSync_content(content);
        log.setSync_describe(sync_describe);
        log.setSync_object(action);
        log.setSync_results(syncResults);
        this.dao.saveObject(log);
    }




    public void shipOrder() {
        try {
            int num = 1;

            List<PortalShipOrder> OrderList = this.dao
                    .getObjectList(" from PortalShipOrder shipOrder where shipOrder.isPrint=0 and (shipOrder.isSync !=1 or shipOrder.isSync is null)");
            if (OrderList.size() != 0) {
                for (PortalShipOrder portalShipOrder : OrderList) {

                    List<PortalShipOrderItem> orderItemList = this.dao
                            .getObjectList(" from PortalShipOrderItem item where item.portalShipOrder.id=" +
                                    portalShipOrder.getId() +
                                    " and (item.isSync!=1 or item.isSync is null) ");
                    if (orderItemList.size() != 0) {
                        for (PortalShipOrderItem portalShipOrderItem : orderItemList) {

                            List<Box> boxList = this.dao
                                    .getObjectList(" from Box box where box.psoItem.id=" +
                                            portalShipOrderItem.getId() +
                                            " and (box.isSync=1 or box.isSync is null)");
                            if (boxList.size() != 0) {
                                for (Box box : boxList) {

                                    XbipddDet det = new XbipddDet();

                                    if (portalShipOrder.getCreateType().equals(
                                            "NJIT_PO")) {
                                        det.setXbipdd_nbr(portalShipOrder
                                                .getCode());
                                        det.setXbipdd_line(Integer.valueOf(num));
                                        det.setXbipdd_ponbr(portalShipOrderItem
                                                .getPoipItem().getPoip_number()
                                                .getPoip_number());
                                        det.setXbipdd_poline(
                                                Integer.valueOf(Integer.parseInt(portalShipOrderItem
                                                        .getPoipItem()
                                                        .getLine())));
                                        det.setXbipdd_ctnbr(box.getLot()
                                                .getId());
                                        det.setXbipdd_part(portalShipOrderItem
                                                .getPart().getId());
                                        det.setXbipdd_lot(null);
                                        det.setXbipdd_qty(
                                                Integer.valueOf(box.getNumber().intValue()));
                                        det.setXbipdd_createdt(new Date());
                                        this.daoShared.saveObject(det);
                                    } else {

                                        det.setXbipdd_nbr(portalShipOrder
                                                .getCode());
                                        det.setXbipdd_line(Integer.valueOf(num));


                                        det.setXbipdd_ctnbr(box.getLot()
                                                .getId());
                                        det.setXbipdd_part(portalShipOrderItem
                                                .getPart().getId());
                                        det.setXbipdd_lot(null);
                                        det.setXbipdd_qty(
                                                Integer.valueOf(box.getNumber().intValue()));
                                        det.setXbipdd_createdt(new Date());
                                        this.daoShared.saveObject(det);
                                    }

                                    box.setIsSync(YesNo.YES);
                                    this.dao.updateObject(box);
                                }
                            }
                            num++;

                            portalShipOrderItem.setIsSync(Integer.valueOf(1));
                            this.dao.updateObject(portalShipOrderItem);
                        }
                    }

                    XbipdmMstr mstr = new XbipdmMstr();
                    mstr.setXbipdm_nbr(portalShipOrder.getCode());
                    mstr.setXbipdm_vend(portalShipOrder.getSupplier().getCode());
                    mstr.setXbipdm_date(portalShipOrder.getCreateDate());
                    mstr.setXbipdm_site("");
                    mstr.setXbipdm_uf1(null);
                    mstr.setXbipdm_uf2(null);
                    mstr.setXbipdm_uf3(null);
                    mstr.setXbipdm_createdt(new Date());
                    this.daoShared.saveObject(mstr);

                    portalShipOrder.setIsSync(Integer.valueOf(1));
                    this.dao.updateObject(portalShipOrder);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            insertSystemLog("PortalShipOrder", " SyncShipOrderError",
                    e.getMessage(), "1");
            System.out.println("同步送货单到中间表出错------------------------------>" + (
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                    .format(new Date()));
        }
    }








    public void shipOrderOne(PortalShipOrder portalShipOrder) {
        try {
            int num = 1;
            if (portalShipOrder != null)
            {
                List<PortalShipOrderItem> orderItemList = this.dao
                        .getObjectList(" from PortalShipOrderItem item where item.portalShipOrder.id=" +
                                portalShipOrder.getId() +
                                " and (item.isSync!=1 or item.isSync is null) ");
                if (orderItemList.size() != 0) {
                    for (PortalShipOrderItem portalShipOrderItem : orderItemList) {

                        List<Box> boxList = this.dao
                                .getObjectList(" from Box box where box.psoItem.id=" +
                                        portalShipOrderItem.getId() +
                                        " and (box.isSync=1 or box.isSync is null)");
                        if (boxList.size() != 0) {
                            for (Box box : boxList) {

                                XbipddDet det = new XbipddDet();

                                if (portalShipOrder.getCreateType().equals(
                                        "NJIT_PO")) {
                                    det.setXbipdd_nbr(portalShipOrder.getCode());
                                    det.setXbipdd_line(Integer.valueOf(num));
                                    det.setXbipdd_ponbr(portalShipOrderItem
                                            .getPoipItem().getPoip_number()
                                            .getPoip_number());
                                    det.setXbipdd_poline(
                                            Integer.valueOf(Integer.parseInt(portalShipOrderItem
                                                    .getPoipItem().getLine())));
                                    det.setXbipdd_ctnbr(box.getLot().getId());
                                    det.setXbipdd_part(portalShipOrderItem
                                            .getPart().getId());
                                    det.setXbipdd_lot(null);
                                    det.setXbipdd_qty(
                                            Integer.valueOf(box.getNumber().intValue()));
                                    det.setXbipdd_createdt(new Date());
                                    this.daoShared.saveObject(det);
                                } else {

                                    det.setXbipdd_nbr(portalShipOrder.getCode());
                                    det.setXbipdd_line(Integer.valueOf(num));


                                    det.setXbipdd_ctnbr(box.getLot().getId());
                                    det.setXbipdd_part(portalShipOrderItem
                                            .getPart().getId());
                                    det.setXbipdd_lot(null);
                                    det.setXbipdd_qty(
                                            Integer.valueOf(box.getNumber().intValue()));
                                    det.setXbipdd_createdt(new Date());
                                    this.daoShared.saveObject(det);
                                }

                                box.setIsSync(YesNo.YES);
                                this.dao.updateObject(box);
                            }
                        }
                        num++;

                        portalShipOrderItem.setIsSync(Integer.valueOf(1));
                        this.dao.updateObject(portalShipOrderItem);
                    }
                }

                XbipdmMstr mstr = new XbipdmMstr();
                mstr.setXbipdm_nbr(portalShipOrder.getCode());
                mstr.setXbipdm_vend(portalShipOrder.getSupplier().getCode());
                mstr.setXbipdm_date(portalShipOrder.getCreateDate());
                mstr.setXbipdm_site("");
                mstr.setXbipdm_uf1(null);
                mstr.setXbipdm_uf2(null);
                mstr.setXbipdm_uf3(null);
                mstr.setXbipdm_createdt(new Date());
                this.daoShared.saveObject(mstr);

                portalShipOrder.setIsSync(Integer.valueOf(1));
                this.dao.updateObject(portalShipOrder);
            }

        } catch (Exception e) {
            e.printStackTrace();
            insertSystemLog("PortalShipOrder", " SyncShipOrderError",
                    e.getMessage(), "1");
            System.out.println("同步送货单到中间表出错------------------------------>" + (
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                    .format(new Date()));
        }
    }
}    
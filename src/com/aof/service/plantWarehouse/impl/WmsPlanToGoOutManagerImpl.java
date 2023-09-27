package com.aof.service.plantWarehouse.impl;

import com.aof.dao.basic.ScanLogDAO;
import com.aof.dao.plantWarehouse.WmsPlanToGoOutDAO;
import com.aof.model.admin.Site;
import com.aof.model.admin.User;
import com.aof.model.basic.ScanLog;
import com.aof.model.basic.WmsPart;
import com.aof.model.metadata.BoxStatus;
import com.aof.model.metadata.InventoryType;
import com.aof.model.metadata.YesNo;
import com.aof.model.plantWarehouse.WmsPlanToGoOut;
import com.aof.model.plantWarehouse.WmsPlanToGoOutItem;
import com.aof.model.plantWarehouse.WmsPlanToGoOutSub;
import com.aof.model.plantWarehouse.query.WmsPlanToGoOutQueryOrder;
import com.aof.model.po.Box;
import com.aof.service.BaseManager;
import com.aof.service.Properties;
import com.aof.service.basic.WmsPartManager;
import com.aof.service.inventory.InventoryManager;
import com.aof.service.plantWarehouse.WmsPlanToGoOutManager;
import com.aof.service.po.BoxManager;
import com.aof.service.po.impl.BoxManagerImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.aof.web.struts.action.ActionUtils2;
import jxl.Cell;
import jxl.Sheet;
import org.apache.commons.lang.StringUtils;

public class WmsPlanToGoOutManagerImpl
        extends BaseManager
        implements WmsPlanToGoOutManager
{
    private WmsPlanToGoOutDAO wmsPlanToGoOutDAO;
    private BoxManager boxManager;
    private WmsPartManager wmsPartManager;
    private InventoryManager inventoryManager;
    private ScanLogDAO scanLogDAO;

    public void setBoxManager(BoxManager boxManager) {
        this.boxManager = boxManager;
    }

    public void setScanLogDAO(ScanLogDAO scanLogDAO) {
        this.scanLogDAO = scanLogDAO;
    }

    public void setInventoryManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public void setWmsPartManager(WmsPartManager wmsPartManager) {
        this.wmsPartManager = wmsPartManager;
    }

    public void setWmsPlanToGoOutDAO(WmsPlanToGoOutDAO wmsPlanToGoOutDAO) {
        this.wmsPlanToGoOutDAO = wmsPlanToGoOutDAO;
    }


    public WmsPlanToGoOut getWmsPlanToGoOut(Integer id) {
        return this.wmsPlanToGoOutDAO.getWmsPlanToGoOut(id);
    }


    public int getWmsPlanToGoOutListCount(Map conditions) {
        return this.wmsPlanToGoOutDAO.getWmsPlanToGoOutListCount(conditions);
    }



    public List getWmsPlanToGoOutList(Map conditions, int pageNo, int pageSize, WmsPlanToGoOutQueryOrder order, boolean descend) {
        return this.wmsPlanToGoOutDAO.getWmsPlanToGoOutList(conditions, pageNo,
                pageSize, order, descend);
    }

    public WmsPlanToGoOut insertWmsPlanToGoOut(WmsPlanToGoOut pwom) {
        pwom.setCode(getLastCode(pwom.getSite(), pwom.getDate()));
        return this.wmsPlanToGoOutDAO.insertWmsPlanToGoOut(pwom);
    }

    private String getLastCode(Site site, Date date) {
        StringBuffer sb = new StringBuffer("TG");
        sb.append(StringUtils.right(ActionUtils2.get8CharsFromDate(date), 6));
        String prefix = sb.toString();
        String maxId = this.wmsPlanToGoOutDAO.getMaxPoApplicationIdBeginWith(prefix);

        int serialNo = 1;
        if (maxId != null) {
            Integer maxSN = ActionUtils2.parseInt(StringUtils.right(maxId, 5));
            if (maxSN == null)
                throw new RuntimeException("max serial no. is not digit");
            serialNo = maxSN.intValue() + 1;
        }
        String sn = String.valueOf(serialNo);
        for (int i = 0; i < 5 - sn.length(); i++)
            sb.append('0');
        sb.append(sn);
        return sb.toString();
    }


    public WmsPlanToGoOut updateWmsPlanToGoOut(WmsPlanToGoOut pwom) {
        return this.wmsPlanToGoOutDAO.updateWmsPlanToGoOut(pwom);
    }


    public String getMaxPoApplicationIdBeginWith(String prefix) {
        return this.wmsPlanToGoOutDAO.getMaxPoApplicationIdBeginWith(prefix);
    }


    public WmsPlanToGoOutItem getWmsPlanToGoOutItem(int id) {
        return this.wmsPlanToGoOutDAO.getWmsPlanToGoOutItem(id);
    }


    public WmsPlanToGoOutItem insertWmsPlanToGoOutItem(WmsPlanToGoOutItem pwom) {
        return this.wmsPlanToGoOutDAO.insertWmsPlanToGoOutItem(pwom);
    }


    public WmsPlanToGoOutItem updateWmsPlanToGoOutItem(WmsPlanToGoOutItem pwom) {
        return this.wmsPlanToGoOutDAO.updateWmsPlanToGoOutItem(pwom);
    }


    public void deleteWmsPlanToGoOutItem(WmsPlanToGoOutItem item) {
        this.wmsPlanToGoOutDAO.deleteWmsPlanToGoOutItem(item);
    }




    public WmsPlanToGoOutItem updateWmsPlanToGoOutItemByList(String[] str, WmsPlanToGoOut toGoOut) {
        return null;
    }


    public List<WmsPlanToGoOutItem> getWmsPlanToGoOutItemByMain(Integer id) {
        return this.wmsPlanToGoOutDAO.getWmsPlanToGoOutItemByMain(id);
    }


    public String scanningUnplannedOutbound(String lotser, String planToGoOutId, String userId) {
        ScanLog scanLog = new ScanLog();
        scanLog.setDate(new Date());
        scanLog.setDescribe(lotser);
        scanLog.setType(Integer.valueOf(8));
        User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
        if (user != null) {
            scanLog.setUserId(user);
        }
        this.scanLogDAO.insertScanLog(scanLog);


        try {
            boolean sign = false;
            WmsPlanToGoOut goOut = this.wmsPlanToGoOutDAO.getWmsPlanToGoOutByCode(planToGoOutId);
            if (goOut == null) {
                return String.valueOf(Properties.getPropertiesValye("scan.sync.error.planToGoOutId.is.null")) + planToGoOutId;
            }

            List<WmsPlanToGoOutItem> goOutItems = this.wmsPlanToGoOutDAO.getWmsPlanToGoOutItemByMain(goOut.getId());
            if (goOutItems == null || goOutItems.size() == 0) {
                return String.valueOf(Properties.getPropertiesValye("scan.sync.error.planToGoOutId.item.is.null")) + planToGoOutId;
            }

            for (WmsPlanToGoOutItem item : goOutItems) {
                List<WmsPlanToGoOutSub> subList = this.wmsPlanToGoOutDAO.getWmsPlanToGoOutSubByItem(item.getId());
                for (WmsPlanToGoOutSub sub : subList) {

                    if (sub.getBox_id() != null) {
                        Box box = sub.getBox_id();
                        if (box.getStatus().equals(BoxStatus.HASBEENINTO)) {
                            if (box.getLot().getId().equals(lotser)) {
                                sub.setIs_sync(YesNo.NO);
                                sub.setLocation(box.getLocation());
                                sub.setQty(box.getNumber());
                                updateWmsPlanToGoOutItem(item);

                                this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.PLANTOGOOUT, Boolean.valueOf(true));
                                sign = true; continue;
                            }
                            return String.valueOf(lotser) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.item.is.null") + planToGoOutId;
                        }
                    }
                }
            }
            if (sign) {
                return "ok";
            }
            return String.valueOf(lotser) + ":" + Properties.getPropertiesValye("scan.sync.error.polotSer.not.is.plan") + lotser;
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }


    public String scanningWmsPlanToGoOut(String lotSet, Integer userId) {
        return "error";
    }


    public void deleteWmsPlanToGoOut(WmsPlanToGoOut goOut) {
        this.wmsPlanToGoOutDAO.deleteWmsPlanToGoOut(goOut);
    }


    public void insertWmsPlanToGoOutItemByMain(WmsPlanToGoOut toGoOut, String str) {
        String[] arrays = str.split(";");
        BigDecimal sum = new BigDecimal(0); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String array = arrayOfString1[b];
            String[] partandnumber = array.split(",");
            String part = partandnumber[0];
            String amount = partandnumber[1];
            WmsPart wmsPart = this.wmsPartManager.getWmsPart(part);

            WmsPlanToGoOutItem item = new WmsPlanToGoOutItem();
            item.setQty(new BigDecimal(amount));
            item.setPart(wmsPart);
            item.setActual_qty(new BigDecimal(0));
            item.setStatus(YesNo.NO);
            item.setUnplanned_outbound_id(toGoOut);
            insertWmsPlanToGoOutItem(item);

            sum = sum.add(new BigDecimal(amount));
            b++; }

        toGoOut.setQty(sum);
        this.wmsPlanToGoOutDAO.updateWmsPlanToGoOut(toGoOut);
    }


    public boolean checkWmsPlanToGoOutItemByAmount(String wmsGoOutid, String arrayList, String type) {
        boolean sign = false;
        if (type.equals("0")) {
            List<Box> boxList = new ArrayList<Box>();
            String[] array = arrayList.split(","); byte b; int i; String[] arrayOfString1;
            for (i = (arrayOfString1 = array).length, b = 0; b < i; ) { String boxId = arrayOfString1[b];
                Box box = this.boxManager.getBox(Integer.valueOf(Integer.parseInt(boxId)));
                boxList.add(box); b++; }

            Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
            for (Box box : boxList) {
                if (map.get(box.getPart().getId()) == null) {
                    map.put(box.getPart().getId(), box.getNumber()); continue;
                }
                map.put(box.getPart().getId(), ((BigDecimal)map.get(box.getPart().getId())).add(box.getNumber()));
            }

            List<WmsPlanToGoOutItem> itemList =
                    getWmsPlanToGoOutItemByMain(Integer.valueOf(Integer.parseInt(wmsGoOutid)));
            Set<String> set = map.keySet();
            for (WmsPlanToGoOutItem item : itemList) {
                int j = 0;
                for (String wmsPart : set) {
                    BigDecimal amount = map.get(wmsPart);
                    String part = item.getPart().getId();

                    if (wmsPart.equals(part) && amount.compareTo(item.getQty()) == 0) {
                        j++;
                        sign = true;
                    } else {
                        sign = false;
                    }  if (j > 0)
                        sign = true;
                }
            }
        } else {
            List<Box> boxList = new ArrayList<Box>();
            String[] array = arrayList.split(","); byte b; int i; String[] arrayOfString1;
            for (i = (arrayOfString1 = array).length, b = 0; b < i; ) { String boxId = arrayOfString1[b];
                Box box = this.boxManager.getBox(Integer.valueOf(Integer.parseInt(boxId)));
                boxList.add(box); b++; }

            Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
            for (Box box : boxList) {
                if (map.get(box.getPart().getId()) == null) {
                    map.put(box.getPart().getId(), box.getNumber()); continue;
                }
                map.put(box.getPart().getId(), (
                        (BigDecimal)map.get(box.getPart().getId())).add(
                        box.getNumber()));
            }

            List<WmsPlanToGoOutItem> itemList = getWmsPlanToGoOutItemByMain(Integer.valueOf(Integer.parseInt(wmsGoOutid)));
            Set<String> set = map.keySet();
            for (WmsPlanToGoOutItem item : itemList) {
                int j = 0;
                for (String wmsPart : set) {
                    BigDecimal amount = map.get(wmsPart);
                    String part = item.getPart().getId();

                    if (wmsPart.equals(part) && amount.compareTo(item.getQty()) == 0) {
                        j++;
                        sign = true;
                    } else {
                        sign = false;
                    }  if (j > 0) {
                        sign = true;
                    }
                }
            }
        }
        return sign;
    }


    public void updateWmsPlanToGoOutByBox(WmsPlanToGoOut planToGoOut, String[] arrayList) {
        List<Box> boxList = new ArrayList<Box>();
        List<Box> orderBoxList = new ArrayList<Box>();
        BoxManagerImpl manager = new BoxManagerImpl(); byte b; int i; String[] arrayOfString;
        for (i = (arrayOfString = arrayList).length, b = 0; b < i; ) { String boxId = arrayOfString[b];
            Box box = this.boxManager.getBox(Integer.valueOf(Integer.parseInt(boxId)));
            boxList.add(box);
            b++; }

        List<WmsPlanToGoOutItem> itemList = getWmsPlanToGoOutItemByMain(planToGoOut.getId());
        for (WmsPlanToGoOutItem item : itemList) {
            for (Box box : boxList) {
                if (item.getPart().getId().equals(box.getPart().getId())) {
                    WmsPlanToGoOutSub sub = new WmsPlanToGoOutSub();
                    sub.setUnplanned_outbound_detial_id(item);
                    sub.setBox_id(box);
                    insertWmsPlanToGoOutSub(sub);
                    try {
                        this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.PLANTOGOOUT, Boolean.valueOf(true));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public WmsPlanToGoOutSub insertWmsPlanToGoOutSub(WmsPlanToGoOutSub sub) {
        return this.wmsPlanToGoOutDAO.insertWmsPlanToGoOutSub(sub);
    }


    public List<WmsPlanToGoOutSub> getWmsPlanToGoOutSub(List<WmsPlanToGoOutItem> itemList) {
        List<WmsPlanToGoOutSub> subList = new ArrayList<WmsPlanToGoOutSub>();
        for (WmsPlanToGoOutItem item : itemList) {
            List<WmsPlanToGoOutSub> subs = this.wmsPlanToGoOutDAO.getWmsPlanToGoOutSubByItem(item.getId());
            subList.addAll(subs);
        }
        return subList;
    }

    public List<Map> getRecommendLotset(List<WmsPlanToGoOutItem> items) {
        List<Map> listMap = new ArrayList<Map>();
        Map<Object, Object> map = new HashMap<Object, Object>();

        for (WmsPlanToGoOutItem item : items) {
            BigDecimal amount = item.getQty();
            String sql = "from Box box where box.isInStorage = 0 and box.isOutStorage = 1  and box.enabled = 0 and box.wmsPart.id='" +

                    item.getPart().getId() +
                    "' " +
                    " and box.location.is_recommend <> 1 " +
                    " order by box.date ";

            List<Box> list = this.wmsPlanToGoOutDAO
                    .getObjectList(sql);
            for (Box orderBox : list) {
                if (amount.compareTo(orderBox.getNumber()) == 1 ||
                        amount.compareTo(orderBox.getNumber()) == 0) {
                    amount = amount.subtract(orderBox.getNumber());

                    map = new HashMap<Object, Object>();
                    map.put("boxId", orderBox.getId());
                    map.put("part", orderBox.getPart().getId());
                    map.put("describe1", orderBox.getPart().getDescribe1());
                    map.put("describe2", orderBox.getPart().getDescribe2());
                    map.put("lotset", orderBox.getLot().getId());
                    map.put("amount", orderBox.getNumber());
                    map.put("unit", orderBox.getPart().getUnit());
                    map.put("code", orderBox.getLocation().getCode());

                    if (orderBox.getLocation() != null) {
                        map.put("location", orderBox.getLocation().getCode());
                    } else {
                        listMap.clear();
                        map = new HashMap<Object, Object>();
                        map.put("sign", Boolean.valueOf(false));
                        map.put("part", item.getPart().getId());
                        listMap.add(map);

                        break;
                    }
                    listMap.add(map);
                    continue;
                }
                if (amount.compareTo(new BigDecimal(0)) == -1) {
                    listMap.clear();
                    map = new HashMap<Object, Object>();
                    map.put("sign", Boolean.valueOf(false));
                    map.put("part", item.getPart().getId());
                    listMap.add(map);
                }
            }

            if (amount.compareTo(new BigDecimal(0)) == 1) {
                listMap.clear();
                map = new HashMap<Object, Object>();
                map.put("sign", Boolean.valueOf(false));
                map.put("part", item.getPart().getId());
                listMap.add(map);
            }
        }

        return listMap;
    }

    public List<Map> getImportWmsPlanToGoOutList(Sheet[] sheet) {
        int rowNum = 0;
        List<Map> listMap = new ArrayList<Map>();
        Map<Object, Object> map = new HashMap<Object, Object>(); byte b; int i;
        Sheet[] arrayOfSheet;
        for (i = (arrayOfSheet = sheet).length, b = 0; b < i; ) { Sheet sh = arrayOfSheet[b];
            int k = 0;
            rowNum = sheet[k].getRows();
            for (int j = 1; j < rowNum; j++) {
                Cell[] cells = sh.getRow(j);
                String part = cells[0].getContents();
                String amount = cells[1].getContents();

                map = new HashMap<Object, Object>();
                map.put("part", part);
                listMap.add(map);
            }
            b++; }

        return listMap;
    }

    public Map updateWmsPlanToGoOutByManual(WmsPlanToGoOut plan) {
        List<WmsPlanToGoOutItem> itemList = this.wmsPlanToGoOutDAO.getObjectList("from WmsPlanToGoOutItem item where item.wmsGoOut.id = " + plan.getId() + " ");
        List<Map> listMap = getRecommendLotsetByBox(itemList);
        Map<Object, Object> mapvalue = new HashMap<Object, Object>();
        if (listMap.size() > 0) {
            Map map = listMap.get(0);
            String part = (String)map.get("part");
            if (part != null) {
                mapvalue = new HashMap<Object, Object>();
                mapvalue.put("sign", part);
                return mapvalue;
            }
            List<Box> listBox = (List<Box>)map.get("boxs");
            updateWmsPlanToGoOutByBox2(plan, listBox);
            mapvalue = new HashMap<Object, Object>();
            mapvalue.put("sign", "true");
        }


        this.wmsPlanToGoOutDAO.updateWmsPlanToGoOut(plan);
        return mapvalue;
    }


    public void updateWmsPlanToGoOutByBox2(WmsPlanToGoOut planToGoOut, List<Box> orderBoxList) {
        List<WmsPlanToGoOutItem> itemList = getWmsPlanToGoOutItemByMain(planToGoOut.getId());
        for (WmsPlanToGoOutItem item : itemList) {
            for (Box box : orderBoxList) {
                if (item.getPart().getId().equals(box.getPart().getId())) {
                    WmsPlanToGoOutSub sub = new WmsPlanToGoOutSub();
                    sub.setUnplanned_outbound_detial_id(item);
                    sub.setBox_id(box);
                    insertWmsPlanToGoOutSub(sub);
                }
            }
        }
    }

    public List<Map> getRecommendLotsetByBox(List<WmsPlanToGoOutItem> items) {
        List<Box> listBox = new ArrayList<Box>();
        List<Map> listMap = new ArrayList<Map>();
        Map<Object, Object> map = new HashMap<Object, Object>();

        for (WmsPlanToGoOutItem item : items) {
            BigDecimal amount = item.getQty();
            String part = item.getPart().getId();

            String sql2 = "select sum(box.count) from Box box where box.isInStorage = 0 and box.isOutStorage = 1  and box.enabled = 0 and box.wmsPart.id='" +

                    part +
                    "' and box.location.code not in ('SUPP','DYSUPP') ";

            List<Object> valiedate = this.wmsPlanToGoOutDAO.getObjectList(sql2);
            if (valiedate.size() > 0 && valiedate.get(0) != null) {
                BigDecimal valuedateAmount = (BigDecimal)valiedate.get(0);
                if (valuedateAmount.compareTo(amount) == -1) {
                    listBox.clear();
                    map = new HashMap<Object, Object>();
                    map.put("part", part);
                    listMap.add(map);
                    return listMap;
                }
            }

            String sql = "from Box box where box.isInStorage = 0 and box.isOutStorage = 1  and box.enabled = 0 and box.wmsPart.id='" +

                    item.getPart().getId() +
                    "' and box.location.code not in ('SUPP','DYSUPP') order by box.date ";

            List<Box> list = this.wmsPlanToGoOutDAO
                    .getObjectList(sql);
            for (Box orderBox : list) {

                if (amount.compareTo(orderBox.getNumber()) == 1 ||
                        amount.compareTo(orderBox.getNumber()) == 0) {
                    amount = amount.subtract(orderBox.getNumber());

                    listBox.add(orderBox);
                }
            }

            if (amount.compareTo(new BigDecimal(0)) == 1) {
                listBox.clear();
                map = new HashMap<Object, Object>();
                map.put("part", part);
                listMap.add(map);
                return listMap;
            }

            if (amount.compareTo(new BigDecimal(0)) == -1) {
                listBox.clear();
                map = new HashMap<Object, Object>();
                map.put("part", part);
                listMap.add(map);
                return listMap;
            }
        }
        map = new HashMap<Object, Object>();
        map.put("boxs", listBox);
        listMap.add(map);

        return listMap;
    }

    public void updateWmsPlanToGoOutByManualScannery(WmsPlanToGoOut plan) {
        String sql = "from WmsPlanToGoOutSub sub where sub.item.id in  (select item.id from WmsPlanToGoOutItem item where item.wmsGoOut.id = " +

                plan.getId() + ") ";

        List<WmsPlanToGoOutSub> list = this.wmsPlanToGoOutDAO.getObjectList(sql);
        for (WmsPlanToGoOutSub sub : list) {
            Box box = sub.getBox_id();

            scanningUnplannedOutbound(box.getLot().getId(), plan.getCode(), plan.getApplicant().getId().toString());
        }

        this.wmsPlanToGoOutDAO.updateWmsPlanToGoOut(plan);
    }


    public List<WmsPlanToGoOut> updateWmsPlanToGoOutByPlanNum(List<WmsPlanToGoOut> planToGoOut) {
        List<WmsPlanToGoOut> list = new ArrayList<WmsPlanToGoOut>();
        for (WmsPlanToGoOut goOut : planToGoOut) {

            String sql1 = "from WmsPlanToGoOutSub sub where sub.item.wmsGoOut.id = " +
                    goOut.getId() + " ";

            String sql2 = "from WmsPlanToGoOutSub sub where sub.item.wmsGoOut.id = " +
                    goOut.getId() + " and sub.box.isPickingOutboundFinish=0";
            String sql3 = "from WmsPlanToGoOutSub sub where sub.item.wmsGoOut.id = " +
                    goOut.getId() + " and sub.orderBox.isOutStorage=0";

            List list1 = this.wmsPlanToGoOutDAO.getObjectList(sql1);
            List list2 = this.wmsPlanToGoOutDAO.getObjectList(sql2);
            List list3 = this.wmsPlanToGoOutDAO.getObjectList(sql3);
            list.add(goOut);
        }

        return list;
    }

    public List<WmsPlanToGoOutSub> getWmsPlanToGoOutSubByMain(Integer id) {
        return this.wmsPlanToGoOutDAO.getWmsPlanToGoOutSubByMain(id);
    }
}


package com.aof.service.po.impl;

import com.aof.dao.po.PortalShipOrderDAO;
import com.aof.model.admin.Site;
import com.aof.model.admin.Supplier;
import com.aof.model.admin.User;
import com.aof.model.metadata.BoxStatus;
import com.aof.model.metadata.BoxStatusRqc;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.model.metadata.PortalShipOrderStatus;
import com.aof.model.metadata.YesNo;
import com.aof.model.po.Box;
import com.aof.model.po.PortalShipOrder;
import com.aof.model.po.PortalShipOrderItem;
import com.aof.model.po.PurchaseOrderInspectionPendingItem;
import com.aof.model.po.WmsLot;
import com.aof.model.po.query.PortalShipOrderQueryOrder;
import com.aof.service.BaseManager;
import com.aof.service.po.PortalShipOrderManager;
import com.aof.service.po.PurchaseOrderInspectionPendingManager;
import com.shcnc.utils.UUID;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PortalShipOrderManagerImpl extends BaseManager
        implements PortalShipOrderManager{
    private PortalShipOrderDAO dao;
    private PurchaseOrderInspectionPendingManager purchaseOrderInspectionPendingManager;

    public PurchaseOrderInspectionPendingManager getPurchaseOrderInspectionPendingManager() {
        return this.purchaseOrderInspectionPendingManager;
    }

    public void setPurchaseOrderInspectionPendingManager(PurchaseOrderInspectionPendingManager purchaseOrderInspectionPendingManager) {
        this.purchaseOrderInspectionPendingManager = purchaseOrderInspectionPendingManager;
    }

    public void setDao(PortalShipOrderDAO dao) {
        this.dao = dao;
    }


    public PortalShipOrder getPortalShipOrder(Integer id) {
        return this.dao.getPortalShipOrder(id);
    }


    public int getPortalShipOrderListCount(Map conditions) {
        return this.dao.getPortalShipOrderListCount(conditions);
    }



    public List getPortalShipOrderList(Map conditions, int pageNo, int pageSize, PortalShipOrderQueryOrder order, boolean descend) {
        return this.dao.getPortalShipOrderList(conditions, pageNo, pageSize, order, descend);
    }


    public PortalShipOrder insertPortalShipOrder(PortalShipOrder po) {
        return this.dao.insertPortalShipOrder(po);
    }


    public PortalShipOrder insertPortalShipOrder(PortalShipOrder ps, Site site, User requestor, Date arrDate) {
        ps.setCreateDate(new Date());
        ps.setSite(site);

        ps.setEnabled(EnabledDisabled.ENABLED);
        ps.setStatus(PortalShipOrderStatus.DRAFT);
        ps.setCode(getPortalShipOrderCode(new Date(), requestor));
        ps.setArrivalDate(arrDate);

        String uuuid = UUID.getUUID();

        return this.dao.insertPortalShipOrder(ps);
    }


    public PortalShipOrder insertPortalShipOrderSupplier(PortalShipOrder ps, Site site, Supplier supplier, Date arrDate) {
        ps.setCreateDate(new Date());
        ps.setSite(site);

        ps.setEnabled(EnabledDisabled.ENABLED);
        ps.setStatus(PortalShipOrderStatus.DRAFT);

        ps.setCode(getPortalShipOrderCodeSupplier(new Date(), supplier));

        ps.setArrivalDate(arrDate);

        String uuuid = UUID.getUUID();

        return this.dao.insertPortalShipOrder(ps);
    }


    public PortalShipOrder insertPortalShipOrderByJitPart(PortalShipOrder ps, Site site, User requestor, Date arrDate) {
        ps.setCreateDate(new Date());
        ps.setSite(site);

        ps.setEnabled(EnabledDisabled.ENABLED);
        ps.setStatus(PortalShipOrderStatus.DRAFT);
        ps.setCode(getPortalShipOrderCode(new Date(), requestor));


        String uuuid = UUID.getUUID();

        return this.dao.insertPortalShipOrder(ps);
    }



















    private String getPortalShipOrderCode(Date date, User requestor) {
        StringBuffer sb = new StringBuffer("S");
        String supplierCode = requestor.getLoginName();
        sb.append(supplierCode);
        String dateStr = (new SimpleDateFormat("yyMMdd")).format(new Date());
        sb.append(dateStr);
        List<String> PoMaxList = this.dao.getObjectList("select max(po.code) from PortalShipOrder po where po.code like 'S" +
                supplierCode + dateStr + "%'");
        Integer serialNumber = Integer.valueOf(0);
        if (PoMaxList != null && PoMaxList.size() != 0 && PoMaxList.get(0) != null && !((String)PoMaxList.get(0)).equals("null") && !((String)PoMaxList.get(0)).equals("NULL")) {
            String PoMax = PoMaxList.get(0);
            serialNumber = Integer.valueOf(Integer.parseInt(PoMax.substring(PoMax.length() - 3, PoMax.length())));
        }
        DecimalFormat df = new DecimalFormat("000");
        String serialNumbers = df.format((serialNumber.intValue() + 1));
        sb.append(serialNumbers);
        return sb.toString();
    }



















    private String getPortalShipOrderCodeSupplier(Date date, Supplier supplier) {
        StringBuffer sb = new StringBuffer("S");

        sb.append(supplier.getCode());
        String dateStr = (new SimpleDateFormat("yyMMdd")).format(new Date());
        sb.append(dateStr);
        List<String> PoMaxList = this.dao.getObjectList("select max(po.code) from PortalShipOrder po where po.code like 'S" +
                supplier.getCode() + dateStr + "%'");
        Integer serialNumber = Integer.valueOf(0);
        if (PoMaxList != null && PoMaxList.size() != 0 && PoMaxList.get(0) != null && !((String)PoMaxList.get(0)).equals("null") && !((String)PoMaxList.get(0)).equals("NULL")) {
            String PoMax = PoMaxList.get(0);
            serialNumber = Integer.valueOf(Integer.parseInt(PoMax.substring(PoMax.length() - 3, PoMax.length())));
        }
        DecimalFormat df = new DecimalFormat("000");
        String serialNumbers = df.format((serialNumber.intValue() + 1));
        sb.append(serialNumbers);
        return sb.toString();
    }

    public PortalShipOrder updatePortalShipOrder(PortalShipOrder po) {
        return this.dao.updatePortalShipOrder(po);
    }


    public List getEnabledPortalShipOrderList() {
        return this.dao.getEnabledPortalShipOrderList();
    }


    public List<PortalShipOrderItem> getPortalShipOrderItemListByOrderId(Integer id) {
        return this.dao.getPortalShipOrderItemListByOrderId(id);
    }

    public List<Box> getBoxByShipOrderId(Integer id) {
        return this.dao.getBoxByShipOrderId(id);
    }



    public List<PortalShipOrderItem> getAllShipOrderItem() {
        return this.dao.getAllShipOrderItem();
    }
    public void deletePortalShipOrder(PortalShipOrder portalShipOrder) {
        this.dao.deletePortalShipOrder(portalShipOrder);
    }

    public void deletePurchaseOrderBox(Integer id) {
        this.dao.deletePurchaseOrderBox(id);
    }


    public void deletePortalShipOrderItem(PortalShipOrderItem portalShipOrderItem) {
        this.dao.deletePortalShipOrderItem(portalShipOrderItem);
    }
    public PortalShipOrderItem getPortalShipOrderItem(Integer id) {
        return this.dao.getPortalShipOrderItem(id);
    }
    public List<Box> getBoxList(Integer id) {
        return this.dao.getObjectList("from Box box where box.psoItem.id='" + id + "'");
    }


    public void createPortalShipOrderIP(PortalShipOrder portalShipOrder) {
        List<PortalShipOrderItem> poilist = this.dao.getPortalShipOrderItemListByOrderId(portalShipOrder.getId());
        PortalShipOrder poip = null;
        for (PortalShipOrderItem portalShipOrderItem : poilist) {

            String supplierCode = portalShipOrderItem.getPoipItem().getPoip_number().getSupplier().getCode();

            String dateStr = (new SimpleDateFormat("yyMMdd")).format(new Date());
            String itemNumber = portalShipOrderItem.getPoipItem().getItemNumber().getId();
            BigDecimal deliveryNumber = portalShipOrderItem.getDeliveryNumber();
            BigDecimal capacity = new BigDecimal(1);


            capacity = portalShipOrderItem.getPoipItem().getQty_std();
            if (capacity == null || capacity.intValue() == 0)
            {
                capacity = portalShipOrderItem.getPart().getOrd_mult();
            }

            Double doubleDeliveryNumber = Double.valueOf(deliveryNumber.doubleValue());
            Double doubleCapacity = Double.valueOf(capacity.doubleValue());
            Integer boxCount = Integer.valueOf(0);
            BigDecimal halfATankCount = null;
            if (doubleDeliveryNumber.doubleValue() % doubleCapacity.doubleValue() == 0.0D) {
                Double number = Double.valueOf(doubleDeliveryNumber.doubleValue() / doubleCapacity.doubleValue());
                boxCount = Integer.valueOf(number.intValue());
            } else {
                halfATankCount = new BigDecimal(doubleDeliveryNumber.doubleValue() % doubleCapacity.doubleValue());
                Double number = Double.valueOf((doubleDeliveryNumber.doubleValue() - halfATankCount.doubleValue()) / doubleCapacity.doubleValue() + 1.0D);
                boxCount = Integer.valueOf(number.intValue());
            }

            for (int i = 1; i <= boxCount.intValue(); i++) {
                Box poipiBox = new Box();
                poipiBox.setPsoItem(portalShipOrderItem);
                poipiBox.setPart(portalShipOrderItem.getPoipItem().getItemNumber());
                poipiBox.setInStorageNumber(new BigDecimal(0));
                poipiBox.setReceivedNumber(new BigDecimal(0));
                poipiBox.setReturnsNumber(new BigDecimal(0));
                poipiBox.setVetoQCnumber(new BigDecimal(0));
                poipiBox.setVetoReceivedNumber(new BigDecimal(0));

                poipiBox.setIsReceipt(YesNo.NO);

                poipiBox.setIsPrint(YesNo.NO);
                poipiBox.setStatus_freeze(YesNo.NO);
                poipiBox.setPrint_number(Integer.valueOf(0));
                poipiBox.setStatus(BoxStatus.Wait);
                poipiBox.setStatus_rqc(BoxStatusRqc.NotTheQuality);
                poipiBox.setStatus_print(YesNo.NO);
                poipiBox.setEnabled(EnabledDisabled.ENABLED);
                poipiBox.setDate(portalShipOrder.getCreateDate());
                poipiBox.setType(YesNo.NO);

                poipiBox.setPo_number(portalShipOrderItem.getPoipItem().getPoip_number().getPo_number());
                poipiBox.setPo_supp(portalShipOrderItem.getPoipItem().getPoip_number().getSupplier().getCode());
                poipiBox.setPo_line(portalShipOrderItem.getPoipItem().getLine());
                poipiBox.setPo_date(portalShipOrderItem.getPoipItem().getDueDate());
                poipiBox.setPo_supp_name(portalShipOrderItem.getPoipItem().getPoip_number().getSupplier().getName());
                if (halfATankCount != null && i == boxCount.intValue()) {
                    poipiBox.setNumber(halfATankCount);
                } else {
                    poipiBox.setNumber(capacity);
                }

                List<String> lotMaxList = this.dao.getObjectList("select max(lot.id) from WmsLot lot where lot.id like '" +
                        itemNumber + "/" + dateStr + "/" + "%'");
                Integer serialNumber = Integer.valueOf(0);
                if (lotMaxList != null && lotMaxList.size() != 0 && lotMaxList.get(0) != null && !((String)lotMaxList.get(0)).equals("null") && !((String)lotMaxList.get(0)).equals("NULL")) {
                    String lotMax = lotMaxList.get(0);

                    serialNumber = Integer.valueOf(Integer.parseInt(lotMax.substring(lotMax.length() - 4, lotMax.length())));
                }
                DecimalFormat df = new DecimalFormat("0000");


                String lotSerId = String.valueOf(itemNumber) + "/" + dateStr + "/" + df.format((serialNumber.intValue() + 1));
                WmsLot lot = new WmsLot(lotSerId);
                lot.setEnabled(EnabledDisabled.ENABLED);
                try {
                    this.dao.saveObject(lot);
                    poipiBox.setLot(lot);
                } catch (Exception e) {

                    e.printStackTrace();
                }



                this.purchaseOrderInspectionPendingManager.insertBox(poipiBox);
            }
        }
    }




    public void insertPortalShipOrderItem(PortalShipOrderItem portalShipOrderItem) {
        this.dao.saveObject(portalShipOrderItem);
    }



    public void createPortalShipOrderByJitPartIP(PortalShipOrder portalShipOrder) {
        List<PortalShipOrderItem> poilist = this.dao.getPortalShipOrderItemListByOrderId(portalShipOrder.getId());
        for (PortalShipOrderItem portalShipOrderItem : poilist) {

            String supplierCode = portalShipOrderItem.getPortalShipOrder().getSupplier().getCode();

            String dateStr = (new SimpleDateFormat("yyMMdd")).format(new Date());
            String itemNumber = portalShipOrderItem.getPart().getId();
            BigDecimal deliveryNumber = portalShipOrderItem.getDeliveryNumber();
            BigDecimal capacity = new BigDecimal(1);


            capacity = portalShipOrderItem.getPart().getOrd_mult();

            Double doubleDeliveryNumber = Double.valueOf(deliveryNumber.doubleValue());
            Double doubleCapacity = Double.valueOf(capacity.doubleValue());
            Integer boxCount = Integer.valueOf(0);
            BigDecimal halfATankCount = null;
            if (doubleDeliveryNumber.doubleValue() % doubleCapacity.doubleValue() == 0.0D) {
                Double number = Double.valueOf(doubleDeliveryNumber.doubleValue() / doubleCapacity.doubleValue());
                boxCount = Integer.valueOf(number.intValue());
            } else {
                halfATankCount = new BigDecimal(doubleDeliveryNumber.doubleValue() % doubleCapacity.doubleValue());
                Double number = Double.valueOf((doubleDeliveryNumber.doubleValue() - halfATankCount.doubleValue()) / doubleCapacity.doubleValue() + 1.0D);
                boxCount = Integer.valueOf(number.intValue());
            }

            for (int i = 1; i <= boxCount.intValue(); i++) {
                Box poipiBox = new Box();
                poipiBox.setPsoItem(portalShipOrderItem);
                poipiBox.setPart(portalShipOrderItem.getPart());
                poipiBox.setInStorageNumber(new BigDecimal(0));
                poipiBox.setReceivedNumber(new BigDecimal(0));
                poipiBox.setReturnsNumber(new BigDecimal(0));
                poipiBox.setVetoQCnumber(new BigDecimal(0));
                poipiBox.setVetoReceivedNumber(new BigDecimal(0));

                poipiBox.setIsReceipt(YesNo.NO);

                poipiBox.setIsPrint(YesNo.NO);
                poipiBox.setStatus_freeze(YesNo.NO);
                poipiBox.setPrint_number(Integer.valueOf(0));
                poipiBox.setStatus(BoxStatus.Wait);
                poipiBox.setStatus_rqc(BoxStatusRqc.NotTheQuality);
                poipiBox.setStatus_print(YesNo.NO);
                poipiBox.setEnabled(EnabledDisabled.ENABLED);
                poipiBox.setDate(portalShipOrder.getCreateDate());
                poipiBox.setType(YesNo.NO);

                poipiBox.setPo_supp(portalShipOrderItem.getPortalShipOrder().getSupplier().getCode());
                poipiBox.setPo_date(new Date());
                poipiBox.setPo_supp_name(portalShipOrderItem.getPortalShipOrder().getSupplier().getName());
                if (halfATankCount != null && i == boxCount.intValue()) {
                    poipiBox.setNumber(halfATankCount);
                } else {
                    poipiBox.setNumber(capacity);
                }

                List<String> lotMaxList = this.dao.getObjectList("select max(lot.id) from WmsLot lot where lot.id like '" +
                        itemNumber + "/" + dateStr + "/" + "%'");
                Integer serialNumber = Integer.valueOf(0);
                if (lotMaxList != null && lotMaxList.size() != 0 && lotMaxList.get(0) != null && !((String)lotMaxList.get(0)).equals("null") && !((String)lotMaxList.get(0)).equals("NULL")) {
                    String lotMax = lotMaxList.get(0);

                    serialNumber = Integer.valueOf(Integer.parseInt(lotMax.substring(lotMax.length() - 4, lotMax.length())));
                }
                DecimalFormat df = new DecimalFormat("0000");


                String lotSerId = String.valueOf(itemNumber) + "/" + dateStr + "/" + df.format((serialNumber.intValue() + 1));
                WmsLot lot = new WmsLot(lotSerId);
                lot.setEnabled(EnabledDisabled.ENABLED);
                try {
                    this.dao.saveObject(lot);
                    poipiBox.setLot(lot);
                } catch (Exception e) {

                    e.printStackTrace();
                }
                this.purchaseOrderInspectionPendingManager.insertBox(poipiBox);
            }
        }
    }




    public PortalShipOrder insertPortalShipOrderByNpoPart(PortalShipOrder ps, Site site, User requestor, Date arrDate) {
        ps.setCreateDate(new Date());
        ps.setSite(site);

        ps.setEnabled(EnabledDisabled.ENABLED);
        ps.setStatus(PortalShipOrderStatus.DRAFT);
        ps.setCode(getPortalShipOrderCode(new Date(), requestor));


        String uuuid = UUID.getUUID();

        return this.dao.insertPortalShipOrder(ps);
    }

    public PortalShipOrder insertPortalShipOrderByNpoPartSupplier(PortalShipOrder ps, Site site, Supplier supplier, Date arrDate) {
        ps.setCreateDate(new Date());
        ps.setSite(site);

        ps.setEnabled(EnabledDisabled.ENABLED);
        ps.setStatus(PortalShipOrderStatus.DRAFT);
        ps.setCode(getPortalShipOrderCodeSupplier(new Date(), supplier));


        String uuuid = UUID.getUUID();

        return this.dao.insertPortalShipOrder(ps);
    }

    public void createPortalShipOrderByNpoPartIP(PortalShipOrder portalShipOrder) {
        List<PortalShipOrderItem> poilist = this.dao.getPortalShipOrderItemListByOrderId(portalShipOrder.getId());
        for (PortalShipOrderItem portalShipOrderItem : poilist) {

            String supplierCode = portalShipOrderItem.getPortalShipOrder().getSupplier().getCode();

            String dateStr = (new SimpleDateFormat("yyMMdd")).format(new Date());
            String itemNumber = portalShipOrderItem.getPart().getId();
            BigDecimal deliveryNumber = portalShipOrderItem.getDeliveryNumber();
            BigDecimal capacity = new BigDecimal(1);


            capacity = portalShipOrderItem.getPart().getOrd_mult();

            Double doubleDeliveryNumber = Double.valueOf(deliveryNumber.doubleValue());
            Double doubleCapacity = Double.valueOf(capacity.doubleValue());
            Integer boxCount = Integer.valueOf(0);
            BigDecimal halfATankCount = null;
            if (doubleDeliveryNumber.doubleValue() % doubleCapacity.doubleValue() == 0.0D) {
                Double number = Double.valueOf(doubleDeliveryNumber.doubleValue() / doubleCapacity.doubleValue());
                boxCount = Integer.valueOf(number.intValue());
            } else {
                halfATankCount = new BigDecimal(doubleDeliveryNumber.doubleValue() % doubleCapacity.doubleValue());
                Double number = Double.valueOf((doubleDeliveryNumber.doubleValue() - halfATankCount.doubleValue()) / doubleCapacity.doubleValue() + 1.0D);
                boxCount = Integer.valueOf(number.intValue());
            }

            for (int i = 1; i <= boxCount.intValue(); i++) {
                Box poipiBox = new Box();
                poipiBox.setPsoItem(portalShipOrderItem);
                poipiBox.setPart(portalShipOrderItem.getPart());
                poipiBox.setInStorageNumber(new BigDecimal(0));
                poipiBox.setReceivedNumber(new BigDecimal(0));
                poipiBox.setReturnsNumber(new BigDecimal(0));
                poipiBox.setVetoQCnumber(new BigDecimal(0));
                poipiBox.setVetoReceivedNumber(new BigDecimal(0));

                poipiBox.setIsReceipt(YesNo.NO);

                poipiBox.setIsPrint(YesNo.NO);
                poipiBox.setStatus_freeze(YesNo.NO);
                poipiBox.setPrint_number(Integer.valueOf(0));
                poipiBox.setStatus(BoxStatus.Wait);
                poipiBox.setStatus_rqc(BoxStatusRqc.NotTheQuality);
                poipiBox.setStatus_print(YesNo.NO);
                poipiBox.setEnabled(EnabledDisabled.ENABLED);
                poipiBox.setDate(portalShipOrder.getCreateDate());
                poipiBox.setType(YesNo.NO);

                poipiBox.setPo_supp(portalShipOrderItem.getPortalShipOrder().getSupplier().getCode());
                poipiBox.setPo_date(new Date());
                poipiBox.setPo_supp_name(portalShipOrderItem.getPortalShipOrder().getSupplier().getName());
                if (halfATankCount != null && i == boxCount.intValue()) {
                    poipiBox.setNumber(halfATankCount);
                } else {
                    poipiBox.setNumber(capacity);
                }

                List<String> lotMaxList = this.dao.getObjectList("select max(lot.id) from WmsLot lot where lot.id like '" +
                        itemNumber + "/" + dateStr + "/" + "%'");
                Integer serialNumber = Integer.valueOf(0);
                if (lotMaxList != null && lotMaxList.size() != 0 && lotMaxList.get(0) != null && !((String)lotMaxList.get(0)).equals("null") && !((String)lotMaxList.get(0)).equals("NULL")) {
                    String lotMax = lotMaxList.get(0);

                    serialNumber = Integer.valueOf(Integer.parseInt(lotMax.substring(lotMax.length() - 4, lotMax.length())));
                }
                DecimalFormat df = new DecimalFormat("0000");


                String lotSerId = String.valueOf(itemNumber) + "/" + dateStr + "/" + df.format((serialNumber.intValue() + 1));
                WmsLot lot = new WmsLot(lotSerId);
                lot.setEnabled(EnabledDisabled.ENABLED);
                try {
                    this.dao.saveObject(lot);
                    poipiBox.setLot(lot);
                } catch (Exception e) {

                    e.printStackTrace();
                }
                this.purchaseOrderInspectionPendingManager.insertBox(poipiBox);
            }
        }
    }



    public void deleteDeliveryPo(String id) {
        try {
            boolean bol = false;

            PortalShipOrder shipOrder = (PortalShipOrder)this.dao.getObject(PortalShipOrder.class, Integer.valueOf(Integer.parseInt(id)));

            List<PortalShipOrderItem> itemList = this.dao.getObjectList(" from PortalShipOrderItem item where item.portalShipOrder.id=" + id + " and (item.isSync!=1 or item.isSync is null)");
            if (itemList.size() != 0) {

                for (PortalShipOrderItem portalShipOrderItem : itemList) {
                    List<Box> boxList = this.dao.getObjectList(" from Box box where box.psoItem.id=" + portalShipOrderItem.getId() + " and (box.isSync!=1 or box.isSync is null) ");
                    for (Box box : boxList) {
                        this.dao.removeObject(box.getLot());
                        this.dao.removeObject(box);
                    }

                    BigDecimal deliveryNumber = portalShipOrderItem.getDeliveryNumber();
                    PurchaseOrderInspectionPendingItem pendingItem = portalShipOrderItem.getPoipItem();
                    pendingItem.setQtyOpen(pendingItem.getQtyOpen().add(deliveryNumber));
                    this.dao.updateObject(pendingItem);
                    this.dao.removeObject(portalShipOrderItem);
                }
                bol = true;
            }
            if (bol) {
                this.dao.removeObject(shipOrder);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public void deleteDeliveryNPo(String id) {
        try {
            boolean bol = false;

            PortalShipOrder shipOrder = (PortalShipOrder)this.dao.getObject(PortalShipOrder.class, Integer.valueOf(Integer.parseInt(id)));

            List<PortalShipOrderItem> itemList = this.dao.getObjectList(" from PortalShipOrderItem item where item.portalShipOrder.id=" + id + " and (item.isSync!=1 or item.isSync is null)");
            if (itemList.size() != 0) {

                for (PortalShipOrderItem portalShipOrderItem : itemList) {
                    List<Box> boxList = this.dao.getObjectList(" from Box box where box.psoItem.id=" + portalShipOrderItem.getId() + " and (box.isSync!=1 or box.isSync is null) ");
                    for (Box box : boxList) {
                        this.dao.removeObject(box.getLot());
                        this.dao.removeObject(box);
                    }
                    this.dao.removeObject(portalShipOrderItem);
                }
                bol = true;
            }
            if (bol) {
                this.dao.removeObject(shipOrder);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public void deleteDeliveryJit(String id) {
        try {
            boolean bol = false;

            PortalShipOrder shipOrder = (PortalShipOrder)this.dao.getObject(PortalShipOrder.class, Integer.valueOf(Integer.parseInt(id)));

            List<PortalShipOrderItem> itemList = this.dao.getObjectList(" from PortalShipOrderItem item where item.portalShipOrder.id=" + id + " and (item.isSync!=1 or item.isSync is null)");
            if (itemList.size() != 0) {

                for (PortalShipOrderItem portalShipOrderItem : itemList) {
                    List<Box> boxList = this.dao.getObjectList(" from Box box where box.psoItem.id=" + portalShipOrderItem.getId() + " and (box.isSync!=1 or box.isSync is null) ");
                    for (Box box : boxList) {
                        this.dao.removeObject(box.getLot());
                        this.dao.removeObject(box);
                    }
                    this.dao.removeObject(portalShipOrderItem);
                }
                bol = true;
            }
            if (bol) {
                this.dao.removeObject(shipOrder);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
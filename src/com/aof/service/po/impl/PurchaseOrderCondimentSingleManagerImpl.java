package com.aof.service.po.impl;

import com.aof.dao.DAO;
import com.aof.dao.po.PurchaseOrderCondimentSingleDAO;
import com.aof.model.basic.StorageLocation;
import com.aof.model.basic.StoreRoom;
import com.aof.model.inventory.InventoryTransit;
import com.aof.model.metadata.BoxStatus;
import com.aof.model.metadata.BoxStatusRqc;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.model.metadata.StoreRoomType;
import com.aof.model.metadata.YesNo;
import com.aof.model.po.Box;
import com.aof.model.po.PortalShipOrderItem;
import com.aof.model.po.PurchaseOrderCondimentSingle;
import com.aof.model.po.WmsLot;
import com.aof.model.po.query.PurchaseOrderCondimentSingleQueryOrder;
import com.aof.service.BaseManager;
import com.aof.service.InventoryTool;
import com.aof.service.inventory.InventoryManager;
import com.aof.service.inventory.InventoryTransitManager;
import com.aof.service.po.PortalShipOrderItemManager;
import com.aof.service.po.PurchaseOrderCondimentSingleManager;
import com.aof.web.struts.action.ActionUtils2;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

public class PurchaseOrderCondimentSingleManagerImpl
        extends BaseManager
        implements PurchaseOrderCondimentSingleManager
{
    private PurchaseOrderCondimentSingleDAO dao;
    private PortalShipOrderItemManager portalShipOrderItemManager;
    private InventoryManager inventoryManager;
    InventoryTransitManager inventoryTransitManager;

    public InventoryTransitManager getInventoryTransitManager() {
        return this.inventoryTransitManager;
    }

    public void setInventoryTransitManager(InventoryTransitManager inventoryTransitManager) {
        this.inventoryTransitManager = inventoryTransitManager;
    }

    public void setInventoryManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public void setPortalShipOrderItemManager(PortalShipOrderItemManager portalShipOrderItemManager) {
        this.portalShipOrderItemManager = portalShipOrderItemManager;
    }

    public void setDao(PurchaseOrderCondimentSingleDAO dao) {
        this.dao = dao;
    }

    public PurchaseOrderCondimentSingle getPurchaseOrderCondimentSingle(Integer id) {
        return this.dao.getPurchaseOrderCondimentSingle(id);
    }

    public int getPurchaseOrderCondimentSingleListCount(Map conditions) {
        return this.dao.getPurchaseOrderCondimentSingleListCount(conditions);
    }

    public List getPurchaseOrderCondimentSingleList(Map conditions, int pageNo, int pageSize, PurchaseOrderCondimentSingleQueryOrder order, boolean descend) {
        return this.dao.getPurchaseOrderCondimentSingleList(conditions, pageNo,
                pageSize, order, descend);
    }

    public PurchaseOrderCondimentSingle updatePurchaseOrderCondimentSingle(PurchaseOrderCondimentSingle single) {
        return this.dao.updatePurchaseOrderCondimentSingle(single);
    }

    public PurchaseOrderCondimentSingle insertPurchaseOrderCondimentSingle(PurchaseOrderCondimentSingle single) {
        return this.dao.insertPurchaseOrderCondimentSingle(single);
    }

    public PurchaseOrderCondimentSingle insertPurchaseOrderCondimentSingle(String arrays) {
        return null;
    }

    private String getLastCodeReceipts(Date date) {
        StringBuffer sb = new StringBuffer("CS");
        for (int i = 0; i < 3; i++)
            sb.append('0');
        sb.append(StringUtils.right(ActionUtils2.get8CharsFromDate(date), 6));
        String prefix = sb.toString();
        String maxId = this.dao.getMaxPoReceiptsBeginWith(prefix);
        int serialNo = 1;

        if (maxId != null) {
            Integer maxSN = ActionUtils2.parseInt(StringUtils.right(maxId, 3));
            if (maxSN == null)
                throw new RuntimeException("max serial no. is not digit");
            serialNo = maxSN.intValue() + 1;
        }

        String sn = String.valueOf(serialNo);





        for (int j = 0; j < 3 - sn.length(); j++)
            sb.append('0');
        sb.append(sn);
        return sb.toString();
    }



    public Box insertBox(PortalShipOrderItem item, BigDecimal amount, PurchaseOrderCondimentSingle single, boolean sign) {
        String supplierCode = item.getPoipItem().getPoip_number().getSupplier()
                .getCode();
        WmsLot wl = (new InventoryTool((DAO)this.dao))
                .insertWOBox(supplierCode, "yyyyMMdd");
        Box box = new Box();
        box.setLot(wl);
        box.setPsoItem(item);
        box.setDate(item.getPortalShipOrder().getCreateDate());
        box.setEnabled(EnabledDisabled.ENABLED);
        box.setPart(item.getPoipItem().getItemNumber());
        box.setNumber(amount);
        box.setPrint_number(Integer.valueOf(0));
        if (sign) {
            box.setStatus_rqc(BoxStatusRqc.EXEMPTION);
        } else {
            box.setStatus_rqc(BoxStatusRqc.NotTheQuality);
        }
        box.setStatus(BoxStatus.Wait);
        box.setStatus_freeze(YesNo.NO);
        box.setIsPrint(YesNo.NO);
        box.setSingle(single);
        box.setPo_number(item.getPoipItem().getPoip_number().getPo_number());
        box.setPo_line(item.getPoipItem().getLine());
        box.setPo_date(item.getPoipItem().getPoip_number().getCreateDate());
        box.setPo_supp(item.getPoipItem().getPoip_number().getSupplier()
                .getCode());
        box.setPo_supp_name(item.getPoipItem().getPoip_number().getSupplier().getName());
        System.out.println(box.getStatus_rqc());
        this.dao.saveObject(box);
        return box;
    }

    public void updatePurchaseOrderCondimentConfirm(String arrays, StoreRoomType s) {
        String[] array = arrays.split(";"); byte b; int i; String[] arrayOfString1;
        for (i = (arrayOfString1 = array).length, b = 0; b < i; ) { String str = arrayOfString1[b];
            String[] idAndQty = str.split(",");
            String id = idAndQty[0];
            String qty = idAndQty[1];
            PortalShipOrderItem item = this.portalShipOrderItemManager
                    .getPortalShipOrderItem(Integer.valueOf(Integer.parseInt(id)));

            List<StoreRoom> list1 = this.dao.getObjectList("from StoreRoom sr where code='ZZK'");
            if (list1 != null) {
                StoreRoom r = list1.get(0);

                List<StorageLocation> list = this.dao.getObjectList("from StorageLocation s where s.basic_storeroom_id = " + r.getId());
                if (list.size() > 0) {
                    StorageLocation location = list.get(0);

                    String part = item.getPoipItem().getItemNumber().getId();
                    List<InventoryTransit> listInventoryTransit = this.dao
                            .getObjectList("from InventoryTransit it where it.part.id='" +
                                    part +
                                    "' and it.location.code='" +
                                    location.getCode() + "' ");
                    if (listInventoryTransit.size() > 0) {
                        InventoryTransit transit = listInventoryTransit.get(0);
                        transit.setQty(transit.getQty().add(new BigDecimal(qty)));
                        this.dao.updateObject(transit);
                    } else {
                        InventoryTransit transit = new InventoryTransit();
                        transit.setLocation(location);
                        transit.setPart(item.getPoipItem().getItemNumber());
                        transit.setQty(new BigDecimal(qty));
                        this.dao.saveObject(transit);
                    }
                }
            }

            if (item.getReceived_qty() != null) {
                item.setReceived_qty(item.getReceived_qty().add(
                        new BigDecimal(qty)));
            } else {
                item.setReceived_qty(new BigDecimal(qty));
            }

            item.setStatus_confirm(YesNo.YES);

            this.portalShipOrderItemManager.updatePortalShipOrderItem(item);
            b++; }

    }
}

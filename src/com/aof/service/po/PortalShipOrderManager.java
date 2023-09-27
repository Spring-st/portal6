package com.aof.service.po;

import com.aof.model.admin.Site;
import com.aof.model.admin.Supplier;
import com.aof.model.admin.User;
import com.aof.model.po.Box;
import com.aof.model.po.PortalShipOrder;
import com.aof.model.po.PortalShipOrderItem;
import com.aof.model.po.query.PortalShipOrderQueryOrder;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PortalShipOrderManager {
  PortalShipOrder getPortalShipOrder(Integer paramInteger);
  
  int getPortalShipOrderListCount(Map paramMap);
  
  List getPortalShipOrderList(Map paramMap, int paramInt1, int paramInt2, PortalShipOrderQueryOrder paramPortalShipOrderQueryOrder, boolean paramBoolean);
  
  PortalShipOrder insertPortalShipOrder(PortalShipOrder paramPortalShipOrder);
  
  PortalShipOrder insertPortalShipOrder(PortalShipOrder paramPortalShipOrder, Site paramSite, User paramUser, Date paramDate);
  
  PortalShipOrder insertPortalShipOrderSupplier(PortalShipOrder paramPortalShipOrder, Site paramSite, Supplier paramSupplier, Date paramDate);
  
  PortalShipOrder updatePortalShipOrder(PortalShipOrder paramPortalShipOrder);
  
  List getEnabledPortalShipOrderList();
  
  List<PortalShipOrderItem> getPortalShipOrderItemListByOrderId(Integer paramInteger);
  
  List<Box> getBoxByShipOrderId(Integer paramInteger);
  
  List<PortalShipOrderItem> getAllShipOrderItem();
  
  void deletePortalShipOrder(PortalShipOrder paramPortalShipOrder);
  
  void deletePurchaseOrderBox(Integer paramInteger);
  
  void deletePortalShipOrderItem(PortalShipOrderItem paramPortalShipOrderItem);
  
  PortalShipOrderItem getPortalShipOrderItem(Integer paramInteger);
  
  List<Box> getBoxList(Integer paramInteger);
  
  void createPortalShipOrderIP(PortalShipOrder paramPortalShipOrder);
  
  void insertPortalShipOrderItem(PortalShipOrderItem paramPortalShipOrderItem);
  
  void createPortalShipOrderByJitPartIP(PortalShipOrder paramPortalShipOrder);
  
  void createPortalShipOrderByNpoPartIP(PortalShipOrder paramPortalShipOrder);
  
  PortalShipOrder insertPortalShipOrderByJitPart(PortalShipOrder paramPortalShipOrder, Site paramSite, User paramUser, Date paramDate);
  
  PortalShipOrder insertPortalShipOrderByNpoPart(PortalShipOrder paramPortalShipOrder, Site paramSite, User paramUser, Date paramDate);
  
  PortalShipOrder insertPortalShipOrderByNpoPartSupplier(PortalShipOrder paramPortalShipOrder, Site paramSite, Supplier paramSupplier, Date paramDate);
  
  void deleteDeliveryPo(String paramString);
  
  void deleteDeliveryNPo(String paramString);
  
  void deleteDeliveryJit(String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/PortalShipOrderManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
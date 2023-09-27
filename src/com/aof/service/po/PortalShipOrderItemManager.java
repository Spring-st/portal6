package com.aof.service.po;

import com.aof.model.po.PortalShipOrderItem;
import com.aof.model.po.query.PortalShipOrderQueryOrder;
import java.util.List;
import java.util.Map;

public interface PortalShipOrderItemManager {
  PortalShipOrderItem getPortalShipOrderItem(Integer paramInteger);
  
  int getPortalShipOrderItemListCount(Map paramMap);
  
  List getPortalShipOrderItemList(Map paramMap, int paramInt1, int paramInt2, PortalShipOrderQueryOrder paramPortalShipOrderQueryOrder, boolean paramBoolean);
  
  PortalShipOrderItem insertPortalShipOrderItem(PortalShipOrderItem paramPortalShipOrderItem);
  
  PortalShipOrderItem updatePortalShipOrderItem(PortalShipOrderItem paramPortalShipOrderItem);
  
  List getEnabledPortalShipOrderItemList();
  
  String updatePortalShipOrderWithdraw(String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/PortalShipOrderItemManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
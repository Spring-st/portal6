package com.aof.dao.po;

import com.aof.dao.DAO;
import com.aof.model.po.Box;
import com.aof.model.po.PortalShipOrder;
import com.aof.model.po.PortalShipOrderItem;
import com.aof.model.po.query.PortalShipOrderQueryOrder;
import java.util.List;
import java.util.Map;

public interface PortalShipOrderDAO extends DAO {
  PortalShipOrder getPortalShipOrder(Integer paramInteger);
  
  int getPortalShipOrderListCount(Map paramMap);
  
  List getPortalShipOrderList(Map paramMap, int paramInt1, int paramInt2, PortalShipOrderQueryOrder paramPortalShipOrderQueryOrder, boolean paramBoolean);
  
  PortalShipOrder insertPortalShipOrder(PortalShipOrder paramPortalShipOrder);
  
  PortalShipOrder updatePortalShipOrder(PortalShipOrder paramPortalShipOrder);
  
  List getEnabledPortalShipOrderList();
  
  List<PortalShipOrderItem> getPortalShipOrderItemListByOrderId(Integer paramInteger);
  
  List<Box> getBoxByShipOrderId(Integer paramInteger);
  
  List<PortalShipOrderItem> getAllShipOrderItem();
  
  void deletePortalShipOrder(PortalShipOrder paramPortalShipOrder);
  
  void deletePurchaseOrderBox(Integer paramInteger);
  
  void deletePortalShipOrderItem(PortalShipOrderItem paramPortalShipOrderItem);
  
  PortalShipOrderItem getPortalShipOrderItem(Integer paramInteger);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/PortalShipOrderDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
package com.aof.dao.admin;

import com.aof.model.admin.query.InventoryMovingQueryOrder;
import com.aof.model.inventory.InventoryMoving;
import java.util.List;
import java.util.Map;

public interface InventoryMovingDAO {
  InventoryMoving getInventoryMoving(Integer paramInteger);
  
  int getInventoryMovingListCount(Map paramMap);
  
  List getInventoryMovingList(Map paramMap, int paramInt1, int paramInt2, InventoryMovingQueryOrder paramInventoryMovingQueryOrder, boolean paramBoolean);
  
  InventoryMoving insertInventoryMoving(InventoryMoving paramInventoryMoving);
  
  InventoryMoving updateInventoryMoving(InventoryMoving paramInventoryMoving);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/InventoryMovingDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
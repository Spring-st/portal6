package com.aof.dao.inventory;

import com.aof.dao.DAO;
import com.aof.model.inventory.InventoryTransit;
import com.aof.model.inventory.query.InventoryTransitQueryOrder;
import java.util.List;
import java.util.Map;

public interface InventoryTransitDAO extends DAO {
  InventoryTransit getInventoryTransit(Integer paramInteger);
  
  int getInventoryTransitListCount(Map paramMap);
  
  List getInventoryTransitList(Map paramMap, int paramInt1, int paramInt2, InventoryTransitQueryOrder paramInventoryTransitQueryOrder, boolean paramBoolean);
  
  InventoryTransit insertInventoryTransit(InventoryTransit paramInventoryTransit);
  
  InventoryTransit updateInventoryTransit(InventoryTransit paramInventoryTransit);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/inventory/InventoryTransitDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
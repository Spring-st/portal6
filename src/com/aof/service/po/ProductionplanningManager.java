package com.aof.service.po;

import com.aof.model.po.Productionplanning;
import com.aof.model.po.query.ProductionplanningQueryOrder;
import java.util.List;
import java.util.Map;

public interface ProductionplanningManager {
  Productionplanning getProductionplanning(Integer paramInteger);
  
  List list(Map paramMap, int paramInt1, int paramInt2, ProductionplanningQueryOrder paramProductionplanningQueryOrder, boolean paramBoolean);
  
  Productionplanning insert(Productionplanning paramProductionplanning);
  
  void update(Productionplanning paramProductionplanning);
  
  void delete(Productionplanning paramProductionplanning);
  
  int listProductionplanningCount(Map paramMap);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/ProductionplanningManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
package com.aof.dao.po;

import com.aof.dao.DAO;
import com.aof.model.po.Productionplanning;
import com.aof.model.po.query.ProductionplanningQueryOrder;
import java.util.List;
import java.util.Map;

public interface ProductionplanningDao extends DAO {
  Productionplanning getProductionplanning(Integer paramInteger);
  
  List list(Map paramMap, int paramInt1, int paramInt2, ProductionplanningQueryOrder paramProductionplanningQueryOrder, boolean paramBoolean);
  
  int listProductionplanningCount(Map paramMap);
  
  Productionplanning insert(Productionplanning paramProductionplanning);
  
  void update(Productionplanning paramProductionplanning);
  
  void delete(Productionplanning paramProductionplanning);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/ProductionplanningDao.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
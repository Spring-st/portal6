package com.aof.dao.admin;

import com.aof.model.admin.Price;
import com.aof.model.admin.query.PriceQueryOrder;
import java.util.List;
import java.util.Map;

public interface PriceDAO {
  Price getPrice(Integer paramInteger);
  
  int getPriceListCount(Map paramMap);
  
  List getPriceList(Map paramMap, int paramInt1, int paramInt2, PriceQueryOrder paramPriceQueryOrder, boolean paramBoolean);
  
  Price insertPrice(Price paramPrice);
  
  Price updatePrice(Price paramPrice);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/PriceDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
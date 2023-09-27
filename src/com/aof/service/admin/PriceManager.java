package com.aof.service.admin;

import com.aof.model.admin.Price;
import com.aof.model.admin.query.PriceQueryOrder;
import java.util.List;
import java.util.Map;

public interface PriceManager {
  Price getPrice(Integer paramInteger);
  
  Price insertPrice(Price paramPrice);
  
  Price updatePrice(Price paramPrice);
  
  int getPriceListCount(Map paramMap);
  
  List getPriceList(Map paramMap, int paramInt1, int paramInt2, PriceQueryOrder paramPriceQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/PriceManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
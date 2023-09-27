package com.aof.service.basic;

import com.aof.model.basic.BasicPartPrice;
import com.aof.model.basic.query.BasicPartPriceQueryOrder;
import java.util.List;
import java.util.Map;

public interface BasicPartPriceManager {
  BasicPartPrice save(BasicPartPrice paramBasicPartPrice);
  
  BasicPartPrice getBasicPartPrice(Integer paramInteger);
  
  void delete(BasicPartPrice paramBasicPartPrice);
  
  BasicPartPrice update(BasicPartPrice paramBasicPartPrice);
  
  Integer getListCount(Map paramMap);
  
  List<BasicPartPrice> getList(Map paramMap, int paramInt1, int paramInt2, BasicPartPriceQueryOrder paramBasicPartPriceQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/BasicPartPriceManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
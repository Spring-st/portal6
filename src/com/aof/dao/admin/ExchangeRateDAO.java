package com.aof.dao.admin;

import com.aof.model.admin.Currency;
import com.aof.model.admin.ExchangeRate;
import com.aof.model.admin.Site;
import com.aof.model.admin.query.ExchangeRateQueryOrder;
import java.util.List;
import java.util.Map;

public interface ExchangeRateDAO {
  ExchangeRate getExchangeRate(Integer paramInteger);
  
  ExchangeRate getExchangeRate(Currency paramCurrency, Site paramSite);
  
  int getExchangeRateListCount(Map paramMap);
  
  List getExchangeRateList(Map paramMap, int paramInt1, int paramInt2, ExchangeRateQueryOrder paramExchangeRateQueryOrder, boolean paramBoolean);
  
  ExchangeRate saveExchangeRate(ExchangeRate paramExchangeRate);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/ExchangeRateDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
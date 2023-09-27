package com.aof.service.admin;

import com.aof.model.admin.Currency;
import com.aof.model.admin.ExchangeRate;
import com.aof.model.admin.Site;
import com.aof.model.admin.query.ExchangeRateQueryOrder;
import java.util.List;
import java.util.Map;

public interface ExchangeRateManager {
  ExchangeRate getExchangeRate(Integer paramInteger);
  
  ExchangeRate getExchangeRate(Currency paramCurrency, Site paramSite);
  
  ExchangeRate saveExchangeRate(ExchangeRate paramExchangeRate);
  
  int getExchangeRateListCount(Map paramMap);
  
  List getExchangeRateList(Map paramMap, int paramInt1, int paramInt2, ExchangeRateQueryOrder paramExchangeRateQueryOrder, boolean paramBoolean);
  
  List getExchangeRateListBySite(Site paramSite);
  
  List getEnabledExchangeRateListBySite(Site paramSite);
  
  List getEnabledExchangeRateListBySiteIncludeBaseCurrency(Site paramSite);
  
  void fillEnabledExchangeRateListBySiteListIncludeBaseCurrency(List paramList);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/ExchangeRateManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
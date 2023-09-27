package com.aof.service.admin;

import com.aof.model.admin.Currency;
import com.aof.model.admin.query.CurrencyQueryOrder;
import java.util.List;
import java.util.Map;

public interface CurrencyManager {
  Currency getCurrency(String paramString);
  
  int getCurrencyListCount(Map paramMap);
  
  List getCurrencyList(Map paramMap, int paramInt1, int paramInt2, CurrencyQueryOrder paramCurrencyQueryOrder, boolean paramBoolean);
  
  Currency insertCurrency(Currency paramCurrency);
  
  Currency updateCurrency(Currency paramCurrency);
  
  List getAllEnabledCurrencyList();
  
  List getAllEnabledCurrencyListAndIncludeThis(String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/CurrencyManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
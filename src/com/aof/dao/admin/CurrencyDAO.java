package com.aof.dao.admin;

import com.aof.dao.DAO;
import com.aof.model.admin.Currency;
import com.aof.model.admin.query.CurrencyQueryOrder;
import java.util.List;
import java.util.Map;

public interface CurrencyDAO extends DAO {
  Currency getCurrency(String paramString);
  
  int getCurrencyListCount(Map paramMap);
  
  List getCurrencyList(Map paramMap, int paramInt1, int paramInt2, CurrencyQueryOrder paramCurrencyQueryOrder, boolean paramBoolean);
  
  Currency insertCurrency(Currency paramCurrency);
  
  Currency updateCurrency(Currency paramCurrency);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/CurrencyDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
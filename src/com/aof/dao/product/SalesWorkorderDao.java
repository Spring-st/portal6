package com.aof.dao.product;

import com.aof.dao.DAO;
import com.aof.model.product.SalesPreshiporder;
import com.aof.model.product.SalesWorkorder;
import com.aof.model.product.query.SalesWorkorderQueryOrder;
import java.util.List;
import java.util.Map;

public interface SalesWorkorderDao extends DAO {
  SalesWorkorder getById(Integer paramInteger);
  
  SalesWorkorder insert(SalesWorkorder paramSalesWorkorder);
  
  void remove(SalesWorkorder paramSalesWorkorder);
  
  SalesWorkorder update(SalesWorkorder paramSalesWorkorder);
  
  int getListCount(Map paramMap);
  
  List getList(Map paramMap, int paramInt1, int paramInt2, SalesWorkorderQueryOrder paramSalesWorkorderQueryOrder, boolean paramBoolean);
  
  List<SalesWorkorder> getSalesWorkorder(String paramString);
  
  String getSalesWorkorderByLotStatus(String paramString1, String paramString2);
  
  SalesWorkorder getSalesWorkorder(String paramString1, String paramString2);
  
  SalesPreshiporder getSalesPreshiporder(String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/SalesWorkorderDao.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
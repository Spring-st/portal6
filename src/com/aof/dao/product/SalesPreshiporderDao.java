package com.aof.dao.product;

import com.aof.dao.DAO;
import com.aof.model.po.Box;
import com.aof.model.product.SalesPreshiporder;
import com.aof.model.product.SalesPreshiporderItem;
import com.aof.model.product.query.SalesPreshiporderQueryOrder;
import java.util.List;
import java.util.Map;

public interface SalesPreshiporderDao extends DAO {
  SalesPreshiporder getById(Integer paramInteger);
  
  SalesPreshiporder insert(SalesPreshiporder paramSalesPreshiporder);
  
  void remove(SalesPreshiporder paramSalesPreshiporder);
  
  SalesPreshiporder update(SalesPreshiporder paramSalesPreshiporder);
  
  int getListCount(Map paramMap);
  
  List getList(Map paramMap, int paramInt1, int paramInt2, SalesPreshiporderQueryOrder paramSalesPreshiporderQueryOrder, boolean paramBoolean);
  
  List<SalesPreshiporderItem> getSalesPreshiporderListByOrderId(Integer paramInteger);
  
  List<Box> getBoxByBatchList(String paramString);
  
  SalesPreshiporder getSalesPreshiporder(String paramString);
  
  String getSalesPreshiporderBacthByLotStatus(String paramString1, String paramString2);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/SalesPreshiporderDao.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
package com.aof.service.Product;

import com.aof.model.product.SalesPreshiporderBatch;
import com.aof.model.product.query.SalesPreshiporderBatchQueryOrder;
import java.util.List;
import java.util.Map;

public interface SalesPreshiporderBatchManager {
  SalesPreshiporderBatch getById(Integer paramInteger);
  
  SalesPreshiporderBatch insert(SalesPreshiporderBatch paramSalesPreshiporderBatch);
  
  void remove(SalesPreshiporderBatch paramSalesPreshiporderBatch);
  
  SalesPreshiporderBatch update(SalesPreshiporderBatch paramSalesPreshiporderBatch);
  
  int getListCount(Map paramMap);
  
  List getList(Map paramMap, int paramInt1, int paramInt2, SalesPreshiporderBatchQueryOrder paramSalesPreshiporderBatchQueryOrder, boolean paramBoolean);
  
  List getNotWorkList(Integer paramInteger);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/SalesPreshiporderBatchManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
package com.aof.dao.product;

import com.aof.model.product.WorkOrderBom;
import com.aof.model.product.query.WorkOrderBomQueryOrder;
import java.util.List;
import java.util.Map;

public interface WorkOrderBomDao {
  WorkOrderBom getById(Integer paramInteger);
  
  WorkOrderBom insert(WorkOrderBom paramWorkOrderBom);
  
  void remove(WorkOrderBom paramWorkOrderBom);
  
  WorkOrderBom update(WorkOrderBom paramWorkOrderBom);
  
  int getListCount(Map paramMap);
  
  List getList(Map paramMap, int paramInt1, int paramInt2, WorkOrderBomQueryOrder paramWorkOrderBomQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/WorkOrderBomDao.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
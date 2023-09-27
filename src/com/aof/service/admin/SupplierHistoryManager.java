package com.aof.service.admin;

import com.aof.model.admin.SupplierHistory;
import com.aof.model.admin.query.SupplierHistoryQueryOrder;
import java.util.List;
import java.util.Map;

public interface SupplierHistoryManager {
  SupplierHistory getSupplierHistory(Integer paramInteger);
  
  SupplierHistory insertSupplierHistory(SupplierHistory paramSupplierHistory);
  
  SupplierHistory updateSupplierHistory(SupplierHistory paramSupplierHistory);
  
  int getSupplierHistoryListCount(Map paramMap);
  
  List getSupplierHistoryList(Map paramMap, int paramInt1, int paramInt2, SupplierHistoryQueryOrder paramSupplierHistoryQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/SupplierHistoryManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
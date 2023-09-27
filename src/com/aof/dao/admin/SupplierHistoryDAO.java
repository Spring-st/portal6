package com.aof.dao.admin;

import com.aof.model.admin.Supplier;
import com.aof.model.admin.SupplierHistory;
import com.aof.model.admin.query.SupplierHistoryQueryOrder;
import java.util.List;
import java.util.Map;

public interface SupplierHistoryDAO {
  SupplierHistory getSupplierHistory(Integer paramInteger);
  
  int getSupplierHistoryListCount(Map paramMap);
  
  List getSupplierHistoryList(Map paramMap, int paramInt1, int paramInt2, SupplierHistoryQueryOrder paramSupplierHistoryQueryOrder, boolean paramBoolean);
  
  SupplierHistory insertSupplierHistory(SupplierHistory paramSupplierHistory);
  
  SupplierHistory updateSupplierHistory(SupplierHistory paramSupplierHistory);
  
  void copySupplier(Supplier paramSupplier);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/SupplierHistoryDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
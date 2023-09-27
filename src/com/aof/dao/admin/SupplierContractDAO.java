package com.aof.dao.admin;

import com.aof.model.admin.SupplierContract;
import com.aof.model.admin.query.SupplierContractQueryOrder;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface SupplierContractDAO {
  SupplierContract getSupplierContract(Integer paramInteger);
  
  int getSupplierContractListCount(Map paramMap);
  
  List getSupplierContractList(Map paramMap, int paramInt1, int paramInt2, SupplierContractQueryOrder paramSupplierContractQueryOrder, boolean paramBoolean);
  
  SupplierContract insertSupplierContract(SupplierContract paramSupplierContract);
  
  SupplierContract updateSupplierContract(SupplierContract paramSupplierContract);
  
  void saveSupplierContractContent(Integer paramInteger, InputStream paramInputStream);
  
  InputStream getSupplierContractContent(Integer paramInteger);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/SupplierContractDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
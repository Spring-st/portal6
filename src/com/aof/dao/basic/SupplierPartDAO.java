package com.aof.dao.basic;

import com.aof.dao.DAO;
import com.aof.model.basic.SupplierPart;
import com.aof.model.basic.query.SupplierPartQueryOrder;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface SupplierPartDAO extends DAO {
  SupplierPart getSupplierPart(Integer paramInteger);
  
  SupplierPart saveSupplierPart(SupplierPart paramSupplierPart);
  
  SupplierPart updateSupplierPart(SupplierPart paramSupplierPart);
  
  void removeSupplierPart(SupplierPart paramSupplierPart);
  
  int getSupplierPartListCount(Map paramMap);
  
  List getSupplierPartList(Map paramMap, int paramInt1, int paramInt2, SupplierPartQueryOrder paramSupplierPartQueryOrder, boolean paramBoolean);
  
  List supplierPart(Integer paramInteger);
  
  SupplierPart supplierPart(Integer paramInteger, String paramString);
  
  BigDecimal getSupplierPartReturnIsRqc(String paramString1, String paramString2);
  
  List<SupplierPart> getSupplierPart(String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/SupplierPartDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
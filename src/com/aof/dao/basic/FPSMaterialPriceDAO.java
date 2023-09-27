package com.aof.dao.basic;

import com.aof.dao.DAO;
import com.aof.model.basic.FPSMaterialPrice;
import com.aof.model.basic.query.FPSMaterialPriceQueryOrder;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface FPSMaterialPriceDAO extends DAO {
  FPSMaterialPrice getFPSMaterialPrice(Integer paramInteger);
  
  int getFPSMaterialPriceListCount(Map paramMap);
  
  List getFPSMaterialPriceList(Map paramMap, int paramInt1, int paramInt2, FPSMaterialPriceQueryOrder paramFPSMaterialPriceQueryOrder, boolean paramBoolean);
  
  FPSMaterialPrice insertFPSMaterialPrice(FPSMaterialPrice paramFPSMaterialPrice);
  
  FPSMaterialPrice updateFPSMaterialPrice(FPSMaterialPrice paramFPSMaterialPrice);
  
  List getEnabledFPSMaterialPriceList();
  
  void deleteFPSMaterialPrice(FPSMaterialPrice paramFPSMaterialPrice);
  
  void updateFPSMaterialPriceAllEanble();
  
  FPSMaterialPrice getFPSMaterialPriceByPart(String paramString);
  
  BigDecimal getFPSMaterialPriceBydescribe2(String paramString);
  
  FPSMaterialPrice getFPSMaterialPriceByPartAndSupp(String paramString, Integer paramInteger);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/FPSMaterialPriceDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
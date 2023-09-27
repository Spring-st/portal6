package com.aof.service.plantWarehouse;

import com.aof.model.plantWarehouse.WmsUnplannedWarehousing;
import com.aof.model.plantWarehouse.WmsUnplannedWarehousingItem;
import com.aof.model.plantWarehouse.query.WmsUnplannedWarehousingQueryOrder;
import com.aof.model.po.Box;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import jxl.Sheet;

public interface WmsUWManager {
  WmsUnplannedWarehousing getWmsUnplannedWarehousing(Integer paramInteger);
  
  int getWmsUnplannedWarehousingListCount(Map paramMap);
  
  List getWmsUnplannedWarehousingList(Map paramMap, int paramInt1, int paramInt2, WmsUnplannedWarehousingQueryOrder paramWmsUnplannedWarehousingQueryOrder, boolean paramBoolean);
  
  WmsUnplannedWarehousing insertWmsUnplannedWarehousing(WmsUnplannedWarehousing paramWmsUnplannedWarehousing);
  
  WmsUnplannedWarehousing updateWmsUnplannedWarehousing(WmsUnplannedWarehousing paramWmsUnplannedWarehousing);
  
  String getMaxPoApplicationIdBeginWith(String paramString);
  
  List<WmsUnplannedWarehousingItem> getWmsUnplannedWarehousingItemByMain(Integer paramInteger);
  
  WmsUnplannedWarehousingItem getWmsUnplannedWarehousingItem(int paramInt);
  
  List<WmsUnplannedWarehousingItem> insertWmsUnplannedWarehousingItem(Sheet[] paramArrayOfSheet, Integer paramInteger, String paramString);
  
  List<WmsUnplannedWarehousingItem> insertWmsUnplannedWarehousingItemByFinishedProduct(Sheet[] paramArrayOfSheet, Integer paramInteger);
  
  WmsUnplannedWarehousingItem updateWmsUnplannedWarehousingItem(WmsUnplannedWarehousingItem paramWmsUnplannedWarehousingItem);
  
  void deleteWmsUnplannedWarehousingItem(WmsUnplannedWarehousing paramWmsUnplannedWarehousing, Integer paramInteger);
  
  String scanningWmsUWPutInStorage(String paramString1, String paramString2, String paramString3, String paramString4);
  
  List<WmsUnplannedWarehousing> updateWmsUnplannedWarehousingByBox(List<WmsUnplannedWarehousing> paramList);
  
  WmsUnplannedWarehousing getWmsUnplannedWarehousingByWorkOrderBox(Integer paramInteger);
  
  List<WmsUnplannedWarehousingItem> getBoxByWmsUnplannedWarehousing(Integer paramInteger);
  
  Box insertBox(String paramString1, String paramString2, BigDecimal paramBigDecimal, Date paramDate);
  
  void updateWmsUnplannedWarehousingByStatusToConfirm(WmsUnplannedWarehousing paramWmsUnplannedWarehousing);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/plantWarehouse/WmsUWManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
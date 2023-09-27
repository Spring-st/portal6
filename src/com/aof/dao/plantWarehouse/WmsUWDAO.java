package com.aof.dao.plantWarehouse;

import com.aof.dao.DAO;
import com.aof.model.plantWarehouse.WmsUnplannedWarehousing;
import com.aof.model.plantWarehouse.WmsUnplannedWarehousingItem;
import com.aof.model.plantWarehouse.query.WmsUnplannedWarehousingQueryOrder;
import java.util.List;
import java.util.Map;

public interface WmsUWDAO extends DAO {
  WmsUnplannedWarehousing getWmsUnplannedWarehousing(Integer paramInteger);
  
  int getWmsUnplannedWarehousingListCount(Map paramMap);
  
  List getWmsUnplannedWarehousingList(Map paramMap, int paramInt1, int paramInt2, WmsUnplannedWarehousingQueryOrder paramWmsUnplannedWarehousingQueryOrder, boolean paramBoolean);
  
  WmsUnplannedWarehousing insertWmsUnplannedWarehousing(WmsUnplannedWarehousing paramWmsUnplannedWarehousing);
  
  WmsUnplannedWarehousing updateWmsUnplannedWarehousing(WmsUnplannedWarehousing paramWmsUnplannedWarehousing);
  
  String getMaxPoApplicationIdBeginWith(String paramString);
  
  Integer wmsUwIsPrintFinishByItem(Integer paramInteger);
  
  WmsUnplannedWarehousingItem getWmsUnplannedWarehousingItem(int paramInt);
  
  WmsUnplannedWarehousingItem insertWmsUnplannedWarehousingItem(WmsUnplannedWarehousingItem paramWmsUnplannedWarehousingItem);
  
  WmsUnplannedWarehousingItem updateWmsUnplannedWarehousingItem(WmsUnplannedWarehousingItem paramWmsUnplannedWarehousingItem);
  
  List<WmsUnplannedWarehousingItem> getWmsUnplannedWarehousingItemByMain(Integer paramInteger);
  
  void deleteWmsUWItem(WmsUnplannedWarehousingItem paramWmsUnplannedWarehousingItem);
  
  Integer getWmsUWItemIsPrintByMain(Integer paramInteger);
  
  Integer getWmsUnplannedWarehousingByBox(Integer paramInteger);
  
  Integer getWmsUnplannedWarehousingByBoxAll(Integer paramInteger);
  
  Integer getWmsUnplannedWarehousingByProduceWorkOrderBox(Integer paramInteger);
  
  Integer getWmsUnplannedWarehousingByProduceWorkOrderBoxAll(Integer paramInteger);
  
  WmsUnplannedWarehousing getWmsUnplannedWarehousingByWorkOrderBox(Integer paramInteger);
  
  void deleteWmsUW(WmsUnplannedWarehousing paramWmsUnplannedWarehousing);
  
  List<WmsUnplannedWarehousingItem> getBoxByWmsUnplannedWarehousing(Integer paramInteger);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/plantWarehouse/WmsUWDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
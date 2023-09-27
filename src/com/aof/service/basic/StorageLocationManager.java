package com.aof.service.basic;

import com.aof.model.basic.StorageLocation;
import com.aof.model.basic.query.StorageLocationQueryOrder;
import java.util.List;
import java.util.Map;

public interface StorageLocationManager {
  StorageLocation getStorageLocation(Integer paramInteger);
  
  int getStorageLocationListCount(Map paramMap);
  
  List getStorageLocationList(Map paramMap, int paramInt1, int paramInt2, StorageLocationQueryOrder paramStorageLocationQueryOrder, boolean paramBoolean);
  
  StorageLocation insertStorageLocation(StorageLocation paramStorageLocation);
  
  StorageLocation updateStorageLocation(StorageLocation paramStorageLocation);
  
  List getEnabledStorageLocationList();
  
  void deleteStorageLocation(StorageLocation paramStorageLocation);
  
  StorageLocation getStorageLocation(String paramString);
  
  StorageLocation getStorageLocationByDYK();
  
  List<StorageLocation> getStorageLocationLineLibrary();
  
  List<StorageLocation> getStorageLocationByWmsStockingItemLocation(Integer paramInteger);
  
  boolean validateIsScatteredLocation(String paramString);
  
  StorageLocation getProduceLineLocation();
  
  Map lookForLocationQtyByAjax(String paramString);
  
  StorageLocation getProduceLineLocationLine();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/StorageLocationManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
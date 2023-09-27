package com.aof.dao.basic;

import com.aof.dao.DAO;
import com.aof.model.basic.StorageLocation;
import com.aof.model.basic.query.StorageLocationQueryOrder;
import java.util.List;
import java.util.Map;

public interface StorageLocationDAO extends DAO {
  StorageLocation getStorageLocation(Integer paramInteger);
  
  int getStorageLocationListCount(Map paramMap);
  
  List getStorageLocationList(Map paramMap, int paramInt1, int paramInt2, StorageLocationQueryOrder paramStorageLocationQueryOrder, boolean paramBoolean);
  
  StorageLocation insertStorageLocation(StorageLocation paramStorageLocation);
  
  StorageLocation updateStorageLocation(StorageLocation paramStorageLocation);
  
  List getEnabledStorageLocationList();
  
  void deleteStorageLocation(StorageLocation paramStorageLocation);
  
  StorageLocation getStorageLocation(String paramString);
  
  List<StorageLocation> getStorageLocationLineLibrary();
  
  List<StorageLocation> getStorageLocationByWmsStockingItemLocation(Integer paramInteger);
  
  boolean validateIsScatteredLocation(String paramString);
  
  StorageLocation getStorageLocationByDYK();
  
  StorageLocation getProduceLineLocation();
  
  StorageLocation getProduceLineLocationLine();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/StorageLocationDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
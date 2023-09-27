package com.aof.service.comprehensive;

import com.aof.model.basic.StorageLocation;
import com.aof.model.comprehensive.Stocking;
import com.aof.model.comprehensive.StockingDetial;
import com.aof.model.comprehensive.StockingRecord;
import com.aof.model.comprehensive.StockingScanRecord;
import com.aof.model.comprehensive.StockingSweepRecord;
import com.aof.model.comprehensive.query.StockingDetialQueryOrder;
import com.aof.model.comprehensive.query.StockingQueryOrder;
import com.aof.model.comprehensive.query.StockingScanRecordQueryOrder;
import com.aof.model.po.Box;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface StockingManager {
  Stocking getStocking(Integer paramInteger);
  
  List<StockingDetial> getStockingByInventory(Integer paramInteger);
  
  int getStockingListCount(Map paramMap);
  
  List getStockingList(Map paramMap, int paramInt1, int paramInt2, StockingQueryOrder paramStockingQueryOrder, boolean paramBoolean);
  
  Stocking insertStocking(Stocking paramStocking);
  
  Stocking updateStocking(Stocking paramStocking);
  
  List getEnabledStockingList();
  
  void deleteStocking(Stocking paramStocking);
  
  void deleteStockingDetial(StockingDetial paramStockingDetial);
  
  String getMaxStockingIdBeginWith(String paramString);
  
  StockingDetial getStockingDetial(Integer paramInteger);
  
  int getStockingDetialListCount(Map paramMap);
  
  List getStockingDetialList(Map paramMap, int paramInt1, int paramInt2, StockingDetialQueryOrder paramStockingDetialQueryOrder, boolean paramBoolean);
  
  StockingDetial updateStockingDetial(StockingDetial paramStockingDetial);
  
  StockingSweepRecord insertStockingSweepRecord(StockingSweepRecord paramStockingSweepRecord);
  
  int getStockingSweepRecordByLotSer(String paramString1, String paramString2, String paramString3);
  
  List<StockingDetial> getStockingByStocking(String paramString);
  
  List<StockingDetial> getStockingByMain(Integer paramInteger);
  
  StockingDetial getStockingDetialByLocation(String paramString1, String paramString2);
  
  StockingDetial getStockingDetialByPart(String paramString1, String paramString2);
  
  List<StockingDetial> getStockingDetialByLocationList(String paramString1, String paramString2);
  
  StockingDetial getStockingDetialByLocationByPart(String paramString1, String paramString2, String paramString3);
  
  StockingSweepRecord getStockingSweepRecordByBox(Integer paramInteger);
  
  List<StockingSweepRecord> getStockingSweepRecordByItem(Integer paramInteger);
  
  List<Object[]> getPoIpiBoxByStockingReturnObject(Integer paramInteger);
  
  List<StockingSweepRecord> getStockingSweepRecordByMain(Integer paramInteger);
  
  List<StockingSweepRecord> getStockingByPoIpiBoxReturnObject(Integer paramInteger);
  
  List<Object[]> getStockingByInventoryLosses(Integer paramInteger);
  
  StockingRecord getStockingRecord(Integer paramInteger);
  
  StockingRecord updateStockingRecord(StockingRecord paramStockingRecord);
  
  StockingRecord insertStockingRecord(StockingRecord paramStockingRecord);
  
  List<StockingRecord> getStockingDifferenceByStockingRecord(Integer paramInteger);
  
  StockingSweepRecord getStockingSweepRecordByLotser(String paramString, Integer paramInteger);
  
  List getWmsInventoryCountReportBySite(Integer paramInteger);
  
  void insertWmsStockingItemList(String[] paramArrayOfString, Stocking paramStocking);
  
  List getWmsStockingDifference(Stocking paramStocking);
  
  List<StockingDetial> getWmsStockingByStocking(String paramString);
  
  int getStockingRecordListCount(Map paramMap);
  
  List getStockingRecordList(Map paramMap, int paramInt1, int paramInt2, StockingQueryOrder paramStockingQueryOrder, boolean paramBoolean);
  
  Stocking updateLocationByStocking(Stocking paramStocking);
  
  void insertWmsStockingRecordList(String[] paramArrayOfString, Stocking paramStocking);
  
  String scanningInventory(String paramString1, String paramString2, String paramString3, String paramString4);
  
  int updateStockingIsSync(Integer paramInteger);
  
  void insertWmsStockingItemAllList(Stocking paramStocking);
  
  void deleteStockingByAll(Stocking paramStocking);
  
  Integer getLotCountByLocationAndPartAll(String paramString1, String paramString2);
  
  List<Map> getStockingDetialByBox(StockingDetial paramStockingDetial);
  
  void insertWmsStockingRecordMap(Map paramMap, Stocking paramStocking);
  
  BigDecimal getInventoryDetialByPlanSum(Integer paramInteger);
  
  BigDecimal getInventoryDetialByActualSum(Integer paramInteger);
  
  BigDecimal getInventoryDetialByDifferencesSum(Integer paramInteger);
  
  Integer updateLocationFreeaeByStocking(Stocking paramStocking);
  
  Stocking getStockingByCode(String paramString);
  
  List<StockingScanRecord> getStockingScanRecordByList(String paramString);
  
  StockingSweepRecord getStockingSweepRecordBylotSer(String paramString1, String paramString2, String paramString3);
  
  StockingSweepRecord getStockingSweepRecordBylotSer(String paramString1, String paramString2);
  
  Object getObject(Class paramClass, Serializable paramSerializable);
  
  void saveObject(Object paramObject);
  
  void updateObject(Object paramObject);
  
  void removeObject(Object paramObject);
  
  List getObjectList(String paramString);
  
  List<Box> getObjectListBox(String paramString);
  
  int getStockingScanRecordListCount(Map paramMap);
  
  List getStockingScanRecordList(Map paramMap, int paramInt1, int paramInt2, StockingScanRecordQueryOrder paramStockingScanRecordQueryOrder, boolean paramBoolean);
  
  Integer updateStockingRecordStatus(StockingRecord paramStockingRecord, Integer paramInteger);
  
  StockingSweepRecord getStockingSweepRecordByBoxLotSer(String paramString1, String paramString2, String paramString3);
  
  Integer insertWmsStockingItemList(Stocking paramStocking, String[] paramArrayOfString);
  
  Integer insertWmsStockingItemListByPart(Stocking paramStocking, String[] paramArrayOfString);
  
  Integer insertWmsStockingItemNotBylocationList(Stocking paramStocking, List<StorageLocation> paramList);
  
  Integer insertWmsStockingItemNotBylocationList(Stocking paramStocking, String[] paramArrayOfString);
  
  StockingScanRecord getStockingScanRecordByLotser(String paramString, Integer paramInteger);
  
  String scanningInventoryByPart(String paramString1, String paramString2, String paramString3);
  
  List<Map> getStockingDetialByBoxByPart(StockingDetial paramStockingDetial);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/comprehensive/StockingManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
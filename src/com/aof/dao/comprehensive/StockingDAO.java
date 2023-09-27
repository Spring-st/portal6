package com.aof.dao.comprehensive;

import com.aof.dao.DAO;
import com.aof.model.basic.StorageLocation;
import com.aof.model.comprehensive.Stocking;
import com.aof.model.comprehensive.StockingDetial;
import com.aof.model.comprehensive.StockingRecord;
import com.aof.model.comprehensive.StockingScanRecord;
import com.aof.model.comprehensive.StockingSweepRecord;
import com.aof.model.comprehensive.query.StockingDetialQueryOrder;
import com.aof.model.comprehensive.query.StockingQueryOrder;
import com.aof.model.comprehensive.query.StockingScanRecordQueryOrder;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface StockingDAO extends DAO {
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
  
  List<StockingDetial> getStockingDetialByLocationList(String paramString1, String paramString2);
  
  StockingDetial getStockingDetialByLocationByPart(String paramString1, String paramString2, String paramString3);
  
  StockingSweepRecord getStockingSweepRecordByBox(Integer paramInteger);
  
  List<StockingSweepRecord> getStockingSweepRecordByItem(Integer paramInteger);
  
  List<Object[]> getPoIpiBoxByStockingReturnObject(Integer paramInteger);
  
  List<StockingSweepRecord> getStockingSweepRecordByMain(Integer paramInteger);
  
  List<StockingSweepRecord> getStockingByPoIpiBoxReturnObject(Integer paramInteger);
  
  List<Object[]> getStockingByInventoryLosses(Integer paramInteger);
  
  StockingRecord getStockingRecord(Integer paramInteger);
  
  StockingRecord insertStockingRecord(StockingRecord paramStockingRecord);
  
  List<StockingRecord> getStockingDifferenceByStockingRecord(Integer paramInteger);
  
  StockingSweepRecord getStockingSweepRecordByLotser(String paramString, Integer paramInteger);
  
  List<StockingDetial> getWmsStockingByStocking(String paramString);
  
  int getStockingRecordListCount(Map paramMap);
  
  List getStockingRecordList(Map paramMap, int paramInt1, int paramInt2, StockingQueryOrder paramStockingQueryOrder, boolean paramBoolean);
  
  StockingRecord getStockingRecordByPlan(String paramString1, String paramString2, String paramString3);
  
  Stocking getStockingByCode(String paramString);
  
  StockingSweepRecord getStockingSweepRecordBylotSer(String paramString1, String paramString2, String paramString3);
  
  StockingSweepRecord getStockingSweepRecordBylotSer(String paramString1, String paramString2);
  
  int updateStockingIsSync(Integer paramInteger);
  
  int deleteStockingSweepRecordByAll(Integer paramInteger);
  
  int deleteStockingDetialByAll(Integer paramInteger);
  
  int deleteLocationByAll(Integer paramInteger);
  
  BigDecimal getInventoryDetialByPlanSum(Integer paramInteger);
  
  BigDecimal getInventoryDetialByActualSum(Integer paramInteger);
  
  BigDecimal getInventoryDetialByDifferencesSum(Integer paramInteger);
  
  StockingRecord updateStockingRecord(StockingRecord paramStockingRecord);
  
  Integer insertWmsStockingItemAllList(Stocking paramStocking);
  
  Integer updateLocationFreeaeByStocking(Integer paramInteger);
  
  List<StockingScanRecord> getStockingScanRecordByList(String paramString);
  
  int getStockingScanRecordListCount(Map paramMap);
  
  List getStockingScanRecordList(Map paramMap, int paramInt1, int paramInt2, StockingScanRecordQueryOrder paramStockingScanRecordQueryOrder, boolean paramBoolean);
  
  Integer updateStockingRecordStatus(StockingRecord paramStockingRecord, Integer paramInteger);
  
  StockingSweepRecord getStockingSweepRecordByBoxLotSer(String paramString1, String paramString2, String paramString3);
  
  Integer insertWmsStockingItemList(Stocking paramStocking, String[] paramArrayOfString);
  
  Integer insertWmsStockingItemListByPart(Stocking paramStocking, String[] paramArrayOfString);
  
  Integer insertWmsStockingItemNotBylocationList(Stocking paramStocking, List<StorageLocation> paramList);
  
  Integer insertWmsStockingItemNotBylocationList(Stocking paramStocking, String[] paramArrayOfString);
  
  StockingScanRecord getStockingScanRecordByLotser(String paramString, Integer paramInteger);
  
  StockingDetial getStockingDetialByPart(String paramString1, String paramString2);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/comprehensive/StockingDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
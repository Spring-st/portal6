package com.aof.service.po;

import com.aof.model.admin.User;
import com.aof.model.basic.BadReasons;
import com.aof.model.basicDataView.query.PoPartSumNumberQueryOrder;
import com.aof.model.po.Box;
import com.aof.model.po.PurchaseOrderRqcUnqualified;
import com.aof.model.po.query.BoxQueryOrder;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BoxManager {
  Box getBox(Integer paramInteger);
  
  int getBoxListCount(Map paramMap);
  
  List getBoxList(Map paramMap, int paramInt1, int paramInt2, BoxQueryOrder paramBoxQueryOrder, boolean paramBoolean);
  
  Integer getBoxByPoipItem(int paramInt);
  
  List<Box> getBoxByChildItem(int paramInt);
  
  List<Box> getBoxBylotSer(String paramString);
  
  Box getBoxBylotSer2(String paramString);
  
  Box updateBox(Box paramBox);
  
  List<Box> getBoxByLocation(String paramString);
  
  List<Box> getBoxByLocationAndPart(String paramString1, String paramString2);
  
  List<Box> getBoxByPoritem(Integer paramInteger);
  
  List<Box> getBoxByReceiptsItem(Integer paramInteger);
  
  List<Box> getBoxByLocationAndIsPutIntStorage(Integer paramInteger);
  
  List exportBoxByDate(Date paramDate1, Date paramDate2);
  
  List exportBoxByendDate(Date paramDate);
  
  Integer getBoxListByReceiptIsAllPrint(String paramString);
  
  List<Object[]> getBoxLocationNumberByGroupBy();
  
  List<Map> getBoxLocationNumberByGroupByCountLocationType();
  
  List<Object[]> getBoxByLoation(String paramString);
  
  int getBoxItemListCount(Map paramMap);
  
  List getBoxItemList(Map paramMap, int paramInt1, int paramInt2, PoPartSumNumberQueryOrder paramPoPartSumNumberQueryOrder, boolean paramBoolean);
  
  List<Box> getBoxByRqcItemAndPart(Integer paramInteger, String paramString);
  
  Integer getPwoBoxByPurchaseOrderReceipts(Integer paramInteger);
  
  List<Box> getRecommendLot(String paramString);
  
  void insertBox(String paramString1, String paramString2, String paramString3, String paramString4, User paramUser);
  
  String scanningProductPacking(String paramString1, String paramString2, String paramString3, String paramString4);
  
  String scanningProductPackingBySystem(String paramString1, String paramString2, String paramString3, String paramString4);
  
  String scanningProductOutbound(String paramString1, String paramString2);
  
  List<Map> updateSelectBoxById(String paramString);
  
  List getBomByBox(String paramString);
  
  String systemMoveLocation(String paramString1, String paramString2, User paramUser);
  
  String scanningLocationChange(String paramString1, String paramString2, String paramString3);
  
  String scanningLocationChangeByLocation(String paramString1, String paramString2, String paramString3);
  
  String scanningLotInformation(String paramString);
  
  String boxAbolition(String paramString);
  
  String systemPurchaseOrderHighLineOne(String[] paramArrayOfString, String paramString1, String paramString2, String paramString3);
  
  String systemPurchaseOrderHighLineTwo(String[] paramArrayOfString, String paramString);
  
  String scanningPurchaseOrderHighLineOne(String paramString1, String paramString2, String paramString3);
  
  String scanningPurchaseOrderHighLineTwo(String paramString1, String paramString2);
  
  String checkLot(String paramString);
  
  String scanningPurchaseOrderOutboundCheck(String paramString);
  
  String systemProductPacking(String paramString1, String paramString2, String paramString3, String paramString4);
  
  List<Box> getBoxByLocation(String paramString1, String paramString2);
  
  List<Box> gethighLineBoxOneCheck(String paramString);
  
  String scanninghighLineBoxOneCheck(String paramString);
  
  List<Map> getProduceLineLocationAmunt();
  
  boolean updatePurchaseOrderBoxFreeze(String paramString, Boolean paramBoolean);
  
  String scanningLocationThransferSingle(String paramString1, String paramString2, String paramString3, String paramString4);
  
  String scanningLocationThransfer(String paramString1, String paramString2, String paramString3, String paramString4);
  
  String scanningProductDownline(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);
  
  String scanningProductDownlineVi(String paramString1, String paramString2);
  
  String systemMoveLocationThransfer(String paramString1, String paramString2, String paramString3);
  
  String hnCodeListBox(String paramString);
  
  String updateProduceInStorageMaterial(String paramString, User paramUser);
  
  List getBoxSumCount(Integer paramInteger, String paramString);
  
  BadReasons getBadReasons(String paramString);
  
  String scanningMaterialsOutbound(String paramString1, String paramString2);
  
  String scanningStockingInStorage(String paramString1, String paramString2, String paramString3);
  
  String purchaseReturnMaterialByBox(String paramString, User paramUser);
  
  List<PurchaseOrderRqcUnqualified> getPurchaseOrderRqcUnqualifiedList(Integer paramInteger);
  
  List<Box> getBoxByPart(String paramString);
  
  void deleteBoxByWmsUWItem(Box paramBox);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/BoxManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
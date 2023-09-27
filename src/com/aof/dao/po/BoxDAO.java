package com.aof.dao.po;

import com.aof.dao.DAO;
import com.aof.model.basic.BadReasons;
import com.aof.model.basicDataView.query.PoPartSumNumberQueryOrder;
import com.aof.model.po.Box;
import com.aof.model.po.PurchaseOrderRqcUnqualified;
import com.aof.model.po.query.BoxQueryOrder;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BoxDAO extends DAO {
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
  
  List getBomByBox(String paramString);
  
  List<Box> getBoxByLocation(String paramString1, String paramString2);
  
  List getBoxSumCount(Integer paramInteger, String paramString);
  
  BadReasons getBadReasons(String paramString);
  
  List<PurchaseOrderRqcUnqualified> getPurchaseOrderRqcUnqualifiedList(Integer paramInteger);
  
  List<Box> getBoxByPart(String paramString);
  
  void deleteBoxByWmsUWItem(Box paramBox);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/BoxDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
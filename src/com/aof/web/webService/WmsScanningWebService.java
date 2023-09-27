package com.aof.web.webService;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://impl.service.webservice.com/")
public interface WmsScanningWebService {
  @WebMethod(operationName = "scanningProductOutbound")
  @WebResult
  String scanningProductOutbound(String paramString1, String paramString2);
  
  @WebMethod(operationName = "scanningPurchaseOrderReceiptsByShipOrder")
  @WebResult
  String scanningPurchaseOrderReceiptsByShipOrder(String paramString1, String paramString2);
  
  @WebMethod(operationName = "scanningPurchaseOrderPutInStorage")
  @WebResult
  String scanningPurchaseOrderPutInStorage(String paramString1, String paramString2, String paramString3);
  
  @WebMethod(operationName = "scanningPurchaseOrderBatchStorage")
  @WebResult
  String scanningPurchaseOrderBatchStorage(String paramString1, String paramString2, String paramString3);
  
  @WebMethod(operationName = "scanningPurchaseOrderPutInStorages")
  @WebResult
  String scanningPurchaseOrderPutInStorages(String paramString1, String paramString2, String paramString3);
  
  @WebMethod(operationName = "scanningPurchaseOrderReceipts")
  @WebResult
  String scanningPurchaseOrderReceipts(String paramString1, String paramString2);
  
  @WebMethod(operationName = "scanningPurchaseOrderRQC")
  @WebResult
  String scanningPurchaseOrderRQC(String paramString1, String paramString2, String paramString3, String paramString4);
  
  @WebMethod(operationName = "scanningOutboundByProduct")
  @WebResult
  String scanningOutboundByProduct(String paramString1, String paramString2);
  
  @WebMethod(operationName = "scanningPurchaseOrderOutbound")
  @WebResult
  String scanningPurchaseOrderOutbound(String paramString1, String paramString2);
  
  @WebMethod(operationName = "scanningLocationChange")
  @WebResult
  String scanningLocationChange(String paramString1, String paramString2, String paramString3);
  
  @WebMethod(operationName = "scanningLocationChangeByLocation")
  @WebResult
  String scanningLocationChangeByLocation(String paramString1, String paramString2, String paramString3);
  
  @WebMethod(operationName = "scanningPurchaseOrderPutInStorageByRecommendLocation")
  @WebResult
  String scanningPurchaseOrderPutInStorageByRecommendLocation(String paramString);
  
  @WebMethod(operationName = "scanningPurchaseOrderPutInStorageByOrderRecommendLocation")
  @WebResult
  String scanningPurchaseOrderPutInStorageByOrderRecommendLocation(String paramString);
  
  @WebMethod(operationName = "scanningUnqualifiedReason")
  @WebResult
  String[] scanningUnqualifiedReason();
  
  @WebMethod(operationName = "scanningRqcProgress")
  @WebResult
  String[] scanningRqcProgress();
  
  @WebMethod(operationName = "scanningUnplannedOutbound")
  @WebResult
  String scanningUnplannedOutbound(String paramString1, String paramString2, String paramString3);
  
  @WebMethod(operationName = "scanningWmsUWPutInStorage")
  @WebResult
  String scanningWmsUWPutInStorage(String paramString1, String paramString2, String paramString3, String paramString4);
  
  @WebMethod(operationName = "scanningLotInformation")
  @WebResult
  String scanningLotInformation(String paramString);
  
  @WebMethod(operationName = "scanningLotBreakUp")
  @WebResult
  String scanningLotBreakUp(String paramString1, String paramString2, String paramString3);
  
  @WebMethod(operationName = "scanningLotMerge")
  @WebResult
  String scanningLotMerge(String paramString1, String paramString2);
  
  @WebMethod(operationName = "scanningProductOneProductionLine")
  @WebResult
  String scanningProductOneProductionLine(String paramString1, String paramString2, String paramString3);
  
  @WebMethod(operationName = "scanningProductTwolineProductionLine")
  @WebResult
  String scanningProductTwolineProductionLine(String paramString1, String paramString2);
  
  @WebMethod(operationName = "scanningPurchaseOrderOutboundCheck")
  @WebResult
  String scanningPurchaseOrderOutboundCheck(String paramString);
  
  @WebMethod(operationName = "scanningProductPacking")
  @WebResult
  String scanningProductPacking(String paramString1, String paramString2, String paramString3, String paramString4);
  
  @WebMethod(operationName = "scanninghighLineBoxOneCheck")
  @WebResult
  String scanninghighLineBoxOneCheck(String paramString);
  
  @WebMethod(operationName = "scanningLocationThransfer")
  @WebResult
  String scanningLocationThransfer(String paramString1, String paramString2, String paramString3, String paramString4);
  
  @WebMethod(operationName = "scanningLocationThransferSingle")
  @WebResult
  String scanningLocationThransferSingle(String paramString1, String paramString2, String paramString3, String paramString4);
  
  @WebMethod(operationName = "scanningProductDownline")
  @WebResult
  String scanningProductDownline(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);
  
  @WebMethod(operationName = "scanningProductDownlineVi")
  @WebResult
  String scanningProductDownlineVi(String paramString1, String paramString2);
  
  @WebMethod(operationName = "systemMoveLocationThransfer")
  @WebResult
  String systemMoveLocationThransfer(String paramString1, String paramString2, String paramString3);
  
  @WebMethod(operationName = "BoxAdjustmentBox")
  @WebResult
  String BoxAdjustmentBox(String paramString1, String paramString2, String paramString3, String paramString4);
  
  @WebMethod(operationName = "ListBoxInfoNumber")
  @WebResult
  String ListBoxInfoNumber(String paramString);
  
  @WebMethod(operationName = "hnCodeListBox")
  @WebResult
  String hnCodeListBox(String paramString);
  
  @WebMethod(operationName = "scanningSalesOutStock")
  @WebResult
  String scanningSalesOutStock(String paramString1, String paramString2, String paramString3);
  
  @WebMethod(operationName = "scanningSalesDelivery")
  @WebResult
  String scanningSalesDelivery(String paramString1, String paramString2, String paramString3);
  
  @WebMethod(operationName = "scanningInventory")
  @WebResult
  String scanningInventory(String paramString1, String paramString2, String paramString3, String paramString4);
  
  @WebMethod(operationName = "scanningInventoryByPart")
  @WebResult
  String scanningInventoryByPart(String paramString1, String paramString2, String paramString3);
  
  @WebMethod(operationName = "scanningSalesLoadingWithdraw")
  @WebResult
  String scanningSalesLoadingWithdraw(String paramString1, String paramString2, String paramString3);
  
  @WebMethod(operationName = "scanningProductInLocationNumber")
  @WebResult
  String scanningProductInLocationNumber(String paramString1, String paramString2);
  
  @WebMethod(operationName = "scanningSalesStockToWithdraw")
  @WebResult
  String scanningSalesStockToWithdraw(String paramString1, String paramString2, String paramString3, String paramString4);
  
  @WebMethod(operationName = "scanningSearchInventory")
  @WebResult
  String[] scanningSearchInventory(String paramString1, String paramString2);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/webService/WmsScanningWebService.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
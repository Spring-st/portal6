package com.aof.service.admin;

import com.aof.model.admin.DataTransferParameter;

public interface DataTransferManager {
  void startJob(DataTransferParameter paramDataTransferParameter);
  
  void resetJob(DataTransferParameter paramDataTransferParameter);
  
  void removeJob(DataTransferParameter paramDataTransferParameter);
  
  void updateJobParameter(DataTransferParameter paramDataTransferParameter);
  
  void exportPurchaseOrder(DataTransferParameter paramDataTransferParameter) throws Exception;
  
  void exportExpense(DataTransferParameter paramDataTransferParameter) throws Exception;
  
  void exportSupplier(DataTransferParameter paramDataTransferParameter) throws Exception;
  
  void exportDepartment(DataTransferParameter paramDataTransferParameter) throws Exception;
  
  void exportCountry(DataTransferParameter paramDataTransferParameter) throws Exception;
  
  void exportPurchaseOrderReceipt(DataTransferParameter paramDataTransferParameter) throws Exception;
  
  void importPurchaseType(DataTransferParameter paramDataTransferParameter) throws Exception;
  
  void importExpenseCategory(DataTransferParameter paramDataTransferParameter) throws Exception;
  
  void importCustomer(DataTransferParameter paramDataTransferParameter) throws Exception;
  
  void importExchangeRate(DataTransferParameter paramDataTransferParameter) throws Exception;
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/DataTransferManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
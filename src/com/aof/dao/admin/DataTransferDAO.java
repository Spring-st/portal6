package com.aof.dao.admin;

import com.aof.model.admin.Currency;
import com.aof.model.admin.Customer;
import com.aof.model.admin.ExchangeRate;
import com.aof.model.admin.ExpenseCategory;
import com.aof.model.admin.ExpenseSubCategory;
import com.aof.model.admin.PurchaseType;
import com.aof.model.admin.Site;
import com.aof.model.admin.Supplier;
import java.util.List;

public interface DataTransferDAO {
  List getDataTransferParameterList();
  
  List getPOList(Site paramSite);
  
  List getPOItemReceiptList(Site paramSite);
  
  List getExpenseList(Site paramSite);
  
  List getSupplierList(Site paramSite);
  
  List getDepartmentList(Site paramSite);
  
  List getCountryList(Site paramSite);
  
  List getPurchaseTypeCodeList(Site paramSite);
  
  List getCustomerList(Site paramSite);
  
  List getExchangeRateList(Site paramSite);
  
  List getExpenseCategoryList(Site paramSite);
  
  List getExpenseSubCategoryList(Site paramSite);
  
  Currency getCurrency(String paramString);
  
  ExpenseCategory getExpenseCategory(Site paramSite, String paramString);
  
  ExpenseSubCategory getExpenseSubCategory(ExpenseCategory paramExpenseCategory, String paramString);
  
  void updateSupplier(Supplier paramSupplier);
  
  void updatePurchaseType(PurchaseType paramPurchaseType, boolean paramBoolean);
  
  void updateExchangeRate(ExchangeRate paramExchangeRate, boolean paramBoolean);
  
  void updateExpenseCategory(ExpenseCategory paramExpenseCategory, boolean paramBoolean);
  
  void updateExpenseSubCategory(ExpenseSubCategory paramExpenseSubCategory, boolean paramBoolean);
  
  void updateCustomer(Customer paramCustomer, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/DataTransferDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
package com.aof.service.Product;

import com.aof.model.product.ProductOutStorage;
import com.aof.model.product.query.ProductOutStorageQueryOrder;
import java.util.List;
import java.util.Map;

public interface ProductOutStorageManager {
  ProductOutStorage getProductOutStorage(Integer paramInteger);
  
  ProductOutStorage updateProductOutStorage(ProductOutStorage paramProductOutStorage);
  
  ProductOutStorage insertProductOutStorage(ProductOutStorage paramProductOutStorage);
  
  void deleteProductOutStorage(ProductOutStorage paramProductOutStorage);
  
  int getProductOutStorageListCount(Map paramMap);
  
  List getProductOutStorageList(Map paramMap, int paramInt1, int paramInt2, ProductOutStorageQueryOrder paramProductOutStorageQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/ProductOutStorageManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
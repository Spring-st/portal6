package com.aof.service.Product;

import com.aof.model.product.ProductGoline;
import com.aof.model.product.query.ProductGolineQueryOrder;
import java.util.List;
import java.util.Map;

public interface ProductGolineManager {
  ProductGoline getProductGoline(Integer paramInteger);
  
  ProductGoline updateProductGoline(ProductGoline paramProductGoline);
  
  ProductGoline insertProductGoline(ProductGoline paramProductGoline);
  
  void deleteProductGoline(ProductGoline paramProductGoline);
  
  int getProductGolineListCount(Map paramMap);
  
  int getProductGolineListCountAjax(Map paramMap);
  
  List getProductGolineList(Map paramMap, int paramInt1, int paramInt2, ProductGolineQueryOrder paramProductGolineQueryOrder, boolean paramBoolean);
  
  List getTotalNumberList();
  
  List getHncCodeList();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/ProductGolineManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
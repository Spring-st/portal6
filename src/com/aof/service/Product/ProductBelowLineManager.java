package com.aof.service.Product;

import com.aof.model.product.ProductBelowLine;
import com.aof.model.product.query.ProductBelowLineQueryOrder;
import java.util.List;
import java.util.Map;

public interface ProductBelowLineManager {
  ProductBelowLine getProductBelowLine(Integer paramInteger);
  
  int getProductBelowLineListCount(Map paramMap);
  
  List getProductBelowLineList(Map paramMap, int paramInt1, int paramInt2, ProductBelowLineQueryOrder paramProductBelowLineQueryOrder, boolean paramBoolean);
  
  ProductBelowLine insertProductBelowLine(ProductBelowLine paramProductBelowLine);
  
  ProductBelowLine updateProductBelowLine(ProductBelowLine paramProductBelowLine);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/ProductBelowLineManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
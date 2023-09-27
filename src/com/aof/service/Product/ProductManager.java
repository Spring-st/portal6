package com.aof.service.Product;

import com.aof.model.product.Product;
import com.aof.model.product.ProductBelowLine;
import com.aof.model.product.query.ProductQueryOrder;
import java.util.List;
import java.util.Map;

public interface ProductManager {
  Product getProduct(Integer paramInteger);
  
  int getProductListCount(Map paramMap);
  
  List getProductList(Map paramMap, int paramInt1, int paramInt2, ProductQueryOrder paramProductQueryOrder, boolean paramBoolean);
  
  Product insertProduct(Product paramProduct);
  
  Product updateProduct(Product paramProduct);
  
  String scanningOutboundByProduct(String paramString1, String paramString2);
  
  List<ProductBelowLine> getAllProductBelowLines();
  
  void delete(Product paramProduct);
  
  List<ProductBelowLine> getBelowLineByStorage(String paramString);
  
  ProductBelowLine getProductBelowLineById(Integer paramInteger);
  
  void updateProductBelowLine(ProductBelowLine paramProductBelowLine);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/ProductManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
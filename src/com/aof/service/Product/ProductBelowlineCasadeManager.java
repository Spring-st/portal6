package com.aof.service.Product;

import com.aof.model.product.ProductBelowlineCasade;

public interface ProductBelowlineCasadeManager {
  void insert(ProductBelowlineCasade paramProductBelowlineCasade);
  
  void delete(ProductBelowlineCasade paramProductBelowlineCasade);
  
  void update(ProductBelowlineCasade paramProductBelowlineCasade);
  
  ProductBelowlineCasade getProductBelowlineCasadeByProduct(Integer paramInteger);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/ProductBelowlineCasadeManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
package com.aof.dao.product;

import com.aof.model.sync.query.ProductOutGolineQueryOrder;
import java.util.List;
import java.util.Map;

public interface ProductOutGolineDAO {
  int getProductOutGolineListCount(Map paramMap);
  
  List getProductOutGolineList(Map paramMap, int paramInt1, int paramInt2, ProductOutGolineQueryOrder paramProductOutGolineQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/ProductOutGolineDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
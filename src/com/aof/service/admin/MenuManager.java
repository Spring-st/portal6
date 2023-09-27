package com.aof.service.admin;

import com.aof.model.admin.Menu;
import com.aof.model.admin.query.MenuQueryOrder;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface MenuManager {
  Menu getMenu(Integer paramInteger);
  
  Menu saveMenu(Menu paramMenu);
  
  void removeMenu(Integer paramInteger);
  
  int getMenuListCount(Map paramMap);
  
  List getMenuList(Map paramMap, int paramInt1, int paramInt2, MenuQueryOrder paramMenuQueryOrder, boolean paramBoolean);
  
  String getMenuXml(Locale paramLocale);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/MenuManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
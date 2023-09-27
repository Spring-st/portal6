package com.aof.dao.admin;

import com.aof.dao.DAO;
import com.aof.model.admin.UserSite;
import com.aof.model.admin.query.UserSiteQueryOrder;
import java.util.List;
import java.util.Map;

public interface UserSiteDAO extends DAO {
  UserSite getUserSite(Integer paramInteger1, Integer paramInteger2);
  
  UserSite saveUserSite(UserSite paramUserSite);
  
  UserSite updateUserSite(UserSite paramUserSite);
  
  void removeUserSite(UserSite paramUserSite);
  
  int getUserSiteListCount(Map paramMap);
  
  List getUserSiteList(Map paramMap, int paramInt1, int paramInt2, UserSiteQueryOrder paramUserSiteQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/UserSiteDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
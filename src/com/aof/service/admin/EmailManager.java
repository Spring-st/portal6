package com.aof.service.admin;

import com.aof.model.admin.Email;
import com.aof.model.admin.EmailBatch;
import com.aof.model.admin.Site;
import com.aof.model.admin.User;
import com.aof.model.admin.query.EmailQueryOrder;
import java.util.List;
import java.util.Map;

public interface EmailManager {
  Email getEmail(Integer paramInteger);
  
  Email insertEmail(Email paramEmail, String paramString);
  
  EmailBatch insertEmailBatch(EmailBatch paramEmailBatch, String paramString);
  
  Email updateEmail(Email paramEmail);
  
  int getEmailListCount(Map paramMap);
  
  List getEmailList(Map paramMap, int paramInt1, int paramInt2, EmailQueryOrder paramEmailQueryOrder, boolean paramBoolean);
  
  void sendEmail();
  
  void insertEmail(Site paramSite, String paramString1, String paramString2, String paramString3, String paramString4);
  
  void insertEmailBatch(Site paramSite, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, User paramUser);
  
  void insertEmail(Site paramSite, String paramString1, String paramString2, Map paramMap);
  
  void insertEmailBatch(Site paramSite, String paramString1, String paramString2, String paramString3, Map paramMap, String paramString4);
  
  void deleteEmailBatch(String paramString);
  
  void mailReminder();
  
  void sendBatchEmail();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/EmailManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
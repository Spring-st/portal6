package com.aof.dao.admin;

import com.aof.model.admin.Email;
import com.aof.model.admin.EmailBatch;
import com.aof.model.admin.query.EmailQueryOrder;
import java.util.List;
import java.util.Map;

public interface EmailDAO {
  Email getEmail(Integer paramInteger);
  
  EmailBatch getEmailBatch(Integer paramInteger);
  
  String getEmailBody(Integer paramInteger);
  
  List getAllUnSendEmailBatch();
  
  String getEmailBatchBody(Integer paramInteger);
  
  int getEmailListCount(Map paramMap);
  
  List getEmailList(Map paramMap, int paramInt1, int paramInt2, EmailQueryOrder paramEmailQueryOrder, boolean paramBoolean);
  
  Email insertEmail(Email paramEmail);
  
  EmailBatch insertEmailBatch(EmailBatch paramEmailBatch);
  
  void saveEmailBody(Integer paramInteger, String paramString);
  
  void saveEmailBatchBody(Integer paramInteger, String paramString);
  
  Email updateEmail(Email paramEmail);
  
  EmailBatch updateEmailBatch(EmailBatch paramEmailBatch);
  
  List getWaitToSendEmailList();
  
  void deleteEmailBatch(String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/EmailDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
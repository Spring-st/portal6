package com.aof.dao.business.rule;

import com.aof.dao.DAO;
import com.aof.model.business.rule.Flow;
import com.aof.model.business.rule.FlowRule;
import com.aof.model.business.rule.query.FlowQueryOrder;
import java.util.List;
import java.util.Map;

public interface FlowDAO extends DAO {
  Flow getFlow(Integer paramInteger, boolean paramBoolean);
  
  Flow saveFlow(Flow paramFlow);
  
  void removeFlow(Integer paramInteger);
  
  int getFlowListCount(Map paramMap);
  
  List getFlowList(Map paramMap, int paramInt1, int paramInt2, FlowQueryOrder paramFlowQueryOrder, boolean paramBoolean);
  
  List getRulesForFlow(Integer paramInteger);
  
  void saveFlowRule(FlowRule paramFlowRule);
  
  void removeFlowRule(FlowRule paramFlowRule);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/business/rule/FlowDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
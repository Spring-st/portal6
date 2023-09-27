package com.aof.service.business.rule;

import com.aof.model.business.Approvable;
import com.aof.model.business.Controllable;
import com.aof.model.business.Notifiable;
import com.aof.model.business.Purchasable;
import com.aof.model.business.rule.Flow;
import com.aof.model.business.rule.FlowRule;
import com.aof.model.business.rule.query.FlowQueryOrder;
import com.aof.model.metadata.RuleType;
import com.aof.ruleengine.EngineRule;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface FlowManager {
  Flow getFlow(Integer paramInteger);
  
  Flow getFlow(Integer paramInteger, boolean paramBoolean);
  
  Flow[] saveFlow(Flow paramFlow);
  
  Flow[] saveFlow(Flow paramFlow, Set paramSet);
  
  void removeFlow(Integer paramInteger);
  
  int getFlowListCount(Map paramMap);
  
  List getFlowList(Map paramMap, int paramInt1, int paramInt2, FlowQueryOrder paramFlowQueryOrder, boolean paramBoolean);
  
  Flow getSiteEnabledFlow(Integer paramInteger, RuleType paramRuleType);
  
  int validateRules(Collection paramCollection);
  
  EngineRule createEngineRuleForFlowRule(FlowRule paramFlowRule);
  
  void constructEngineFlow(Integer paramInteger, RuleType paramRuleType);
  
  List executeApproveFlow(Approvable paramApprovable) throws ExecuteFlowEmptyResultException, NoAvailableFlowToExecuteException;
  
  List executePurchaseFlow(Purchasable paramPurchasable) throws ExecuteFlowEmptyResultException, NoAvailableFlowToExecuteException;
  
  boolean executeControlFlow(Controllable paramControllable) throws NoAvailableFlowToExecuteException;
  
  void executeNotifyFlow(Notifiable paramNotifiable);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/business/rule/FlowManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
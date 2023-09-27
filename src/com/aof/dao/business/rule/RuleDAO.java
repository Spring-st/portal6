package com.aof.dao.business.rule;

import com.aof.dao.DAO;
import com.aof.model.business.rule.Rule;
import com.aof.model.business.rule.RuleCondition;
import com.aof.model.business.rule.RuleConsequence;
import com.aof.model.business.rule.query.RuleQueryOrder;
import com.aof.model.metadata.ConditionType;
import java.util.List;
import java.util.Map;

public interface RuleDAO extends DAO {
  Rule getRule(Integer paramInteger, boolean paramBoolean);
  
  Rule saveRule(Rule paramRule);
  
  void removeRule(Integer paramInteger);
  
  int getRuleListCount(Map paramMap);
  
  List getRuleList(Map paramMap, int paramInt1, int paramInt2, RuleQueryOrder paramRuleQueryOrder, boolean paramBoolean);
  
  boolean isRuleInUse(Integer paramInteger);
  
  RuleCondition getRuleCondition(Integer paramInteger, ConditionType paramConditionType);
  
  RuleCondition saveRuleCondition(RuleCondition paramRuleCondition);
  
  RuleCondition updateRuleCondition(RuleCondition paramRuleCondition);
  
  void removeRuleCondition(RuleCondition paramRuleCondition);
  
  RuleConsequence getRuleConsequence(Integer paramInteger1, Integer paramInteger2);
  
  RuleConsequence saveRuleConsequence(RuleConsequence paramRuleConsequence);
  
  RuleConsequence updateRuleConsequence(RuleConsequence paramRuleConsequence);
  
  void removeRuleConsequence(RuleConsequence paramRuleConsequence);
  
  Integer getMaxConsequenceSeqNoForRuleId(Integer paramInteger);
  
  List getConditionsForRuleId(Integer paramInteger);
  
  List getConsequencesForRuleId(Integer paramInteger);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/business/rule/RuleDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
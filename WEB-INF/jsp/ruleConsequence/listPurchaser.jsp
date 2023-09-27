<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<script type="text/javascript">
	function addPurchaser() {
		var v = window.showModalDialog(
			'showDialog.do?title=ruleConsequence.newPurchaser.title&new${X_RULE.type.prefixUrl}RuleConsequence.do?rule_id=${X_RULE.id}', 
			null, 'dialogWidth:650px;dialogHeight:570px;status:no;help:no;scroll:no');
		if (v) {
			var table = document.all('datatablePurchaser');
			appendRow(table, v);
		    applyRowStyle(table);
		};
	}
	
	function deletePurchaser(id) {
		var v = window.showModalDialog(
		    'showDialog.do?title=ruleConsequence.deletePurchaser.title&confirmOperationDialog.do?message=ruleConsequence.deletePurchaser.message&delete${X_RULE.type.prefixUrl}RuleConsequence.do?rule_id=${X_RULE.id}&user_id=' + id, 
		    null, 'dialogWidth:300px;dialogHeight:143px;status:no;help:no;scroll:no');
		if (v) {
			deleteRow(document.all('datatablePurchaser').all('r' + id));
		}
	}
//-->
</script>
<table width="100%">
  <tbody>
    <tr>
      <td><h3 class="formtitle"><bean:message key="ruleConsequence.purchaser"/></h3></td>
    </tr>
    <tr>
      <td align="left"><input type="button" value="<bean:message key="ruleConsequence.button.addpurchaser"/>" onclick="addPurchaser();"/></td>
    </tr>
 </tbody>
</table>
<table class="data">
  <thead>
    <tr bgcolor="#9999ff">
      <th width="90%"><bean:message key="ruleConsequence.purchaser"/></th>
      <th width="10%"></th>
    </tr>
  </thead>
  <tbody id="datatablePurchaser">
    <bean:define id="X_OBJECTS" toScope="request" name="X_RULE" property="consequences"/>
    <jsp:include page="purchaserRows.jsp"/>
  </tbody>
</table>
<script type="text/javascript">
  applyRowStyle(document.all('datatablePurchaser'));
</script>

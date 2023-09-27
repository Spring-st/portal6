<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">
<!--
	function addCondition() {
		var v = window.showModalDialog(
			'showDialog.do?title=ruleCondition.new.title&new${X_RULE.type.prefixUrl}RuleCondition.do?rule_id=${X_RULE.id}', 
			null, 'dialogWidth:700px;dialogHeight:200px;status:no;help:no;scroll:no');
		if (v) {
			var table = document.all('datatable');
			appendRow(table, v);
		    applyRowStyle(table);
		};
	}

	function editCondition(id) {
		var v = window.showModalDialog(
			'showDialog.do?title=ruleCondition.edit.title&edit${X_RULE.type.prefixUrl}RuleCondition.do?rule_id=${X_RULE.id}&type=' + id, 
			null, 'dialogWidth:700px;dialogHeight:200px;status:no;help:no;scroll:no');
		if (v) {
			updateRow(document.all('datatable').all('r' + id), v);
		};
	}

	function deleteCondition(id) {
		var v = window.showModalDialog(
		    'showDialog.do?title=ruleCondition.delete.title&confirmOperationDialog.do?message=ruleCondition.delete.message&delete${X_RULE.type.prefixUrl}RuleCondition.do?rule_id=${X_RULE.id}&type=' + id, 
		    null, 'dialogWidth:300px;dialogHeight:143px;status:no;help:no;scroll:no');
		if (v) {
			deleteRow(document.all('datatable').all('r' + id));
		}
	}

//-->
</script>
<table width="100%">
  <tbody>
    <tr>
      <td><h3 class="formtitle"><bean:message key="rule.condition"/></h3></td>
    </tr>
    <tr>
      <td>
    <input type="button" value="<bean:message key="ruleCondition.button.add"/>" onclick="javascript:addCondition();"/></td>
    </tr>
  </tbody>
</table>
<table class="data">
  <thead>
    <tr bgcolor="#9999ff">
        <th width="80%"><bean:message key="rule.condition"/></th>
        <th width="10%"></th>
        <th width="10%"></th>
    </tr>
  </thead>
  <tbody id="datatable">
    <logic:iterate id="X_OBJECT" name="X_RULE" property="conditions">
      <bean:define id="X_OBJECT" toScope="request" name="X_OBJECT"/>
      <jsp:include page="conditionRow.jsp"/>
    </logic:iterate>
  </tbody>
</table>
<script type="text/javascript">
  applyRowStyle(document.all('datatable'));
</script>
<hr width="100%"/>
<jsp:include page="../ruleConsequence/list${X_RULE.type.consequenceType.prefixUrl}.jsp"/>

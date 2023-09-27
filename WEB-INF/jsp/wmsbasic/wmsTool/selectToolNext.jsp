<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
  	 function selectTool() {
		v = window.showModalDialog(
			'showDialog.do?title=assembly2 expense.selectTA.title&selectTool.do', 
			null, 'dialogWidth:840px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) {
			document.getElementById("toolId").value=v['id'];
			document.getElementById("code").innerHTML=v['code'];
		};
	}
	 
//-->
</script>
<html:form action="/scanningPurchaseOrderReceipts.do">
	<input type="hidden" value="${x_arrays}" name="arrays"/>
	<input type="hidden"  name="tool" id="toolId"/>
	
	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext" >请选择器具号:</td>
			<td>
			  <span id="code"></span>
        	  <a href="javascript:selectTool();"><img src="images/select.gif" border="0"/></a>
			</td>
		</tr>
	</table>
	<hr />
	<div style="margin: 10px 10px;" align="center">
	<input type="submit" value="确认收货"/>
	<input type="button" value="取消" onclick="window.close();" />
	</div>
</html:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

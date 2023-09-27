<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script type="text/javascript" src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
function printProduce(){
	
}	 	      
</script>
<html:form action="/listProductOutbound.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="produce.box.psoItem.poipItem.poip_number.po_number" desc="采购单号"/>
    <input type="hidden" name="fields" value="produce.box.lot.id" desc="批次号"/>
	<input type="hidden" name="fields" value="produce.box.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="produce.box.part.describe1" desc="描述一"/>
	<input type="hidden" name="fields" value="produce.box.part.describe2" desc="描述二"/>
	<input type="hidden" name="fields" value="produce.date" desc="时间"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listProductOutbound.do" method="post">
	<table class="data">
		<thead>
		<tr bgcolor="#9999ff" align="center">
		    <th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
			<th width="15%">批次号</th>	
			<th>采购单号</th>
			<th>行号</th>
			<th>物料号</th>	
			<th>描述一</th>
			<th>描述二</th>
			<th>数量</th>
			<th>单位</th>
			<th>时间</th>
			<th>冻结状态</th>
			<th>打印状态</th>
			<th>批次状态</th>
		</tr>
	</thead>
	<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="row.jsp" />
			</logic:iterate>
		</tbody>
		</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

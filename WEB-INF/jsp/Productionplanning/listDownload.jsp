<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
 <style type="text/css">
        td
        {
            white-space: nowrap;
        }
         th
        {
            white-space: nowrap;
        }
    </style>

<script type="text/javascript">

function view(id) {
	var url = "viewBySupplierPurchaseOrderItem.do?id=" + id;
	window.location.href = url;
}

 	
 	function printExcel(){
		window.location.href = "listPurchase.do?isExcel=true";
	}
 	
</script>
<html:form action="/listProductionplanningDownload" >
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="productionPlanning.productionPlanningNumber" desc="生产计划编号"/>
	<input type="hidden" name="fields" value="productionPlanning.uploadDate" desc="上传日期"/>
	<input type="hidden" name="fields" value="productionPlanning.uploadUser.loginName" desc="上传用户"/>
	            
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<% int num=1;%>
<page:form action="listProductionplanningDownload.do" method="post">
	<table class="data" >
		<thead>
			<tr  bgcolor="#9999ff" align="center">
			<th width="5%">序号</th>
			<th><bean:message key="productionPlanning.number"/></th>
			<th><bean:message key="productionPlanning.uploadDate"/></th>
			<th><bean:message key="productionPlanning.uploadUser"/></th>
			<th>操作</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="rowDownload.jsp"></jsp:include>
			</logic:iterate>
		</tbody>
	</table>

</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

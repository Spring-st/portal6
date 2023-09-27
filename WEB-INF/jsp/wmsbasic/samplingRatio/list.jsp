<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script type="text/javascript">
<!--
	 function addSupplierPartSamplingRatio() {
		v = window.showModalDialog(
			'showDialog.do?title=WmsPart.new.title&newPartLocation.do' , 
			null, 'dialogWidth:700px;dialogHeight:500px;status:no;help:no;scroll:no');
		if (v) {
			var table = document.all('datatable');
			appendRow(table, v);
		    applyRowStyle(table);
		    window.location.href = 'listPartLocation.do';
		};
	}

	function edit(id) {
		v = window.showModalDialog(
			'showDialog.do?title=WmsPart.edit.title&editPartLocation.do?id=' + id , 
			null, 'dialogWidth:600px;dialogHeight:350px;status:no;help:no;scroll:no');
		if (v) {
			updateRow(document.all('r' + id), v);
			var url="listPartLocation.do";
	   		window.location.href = url;
		};
	}
	
	function download(){
	 	var url="exportsSupplierPartSamplingRatio.do";
	    window.location.href = url;
	}
 
	function showImportSupplierPartSamplingRatio(){
		v = window.showModalDialog(
			'showDialog.do?title=StorageLocation.new.title&showImportSupplierPartSamplingRatio.do' , 
			null, 'dialogWidth:400px;dialogHeight:200px;status:no;help:no;scroll:no');
		if (v) {
			 var url="listPartLocation.do";
	   		 window.location.href = url;
		};
	}
	 
//-->
</script>
<html:form action="/listSupplierPartSamplingRatio.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="srt.id" desc="供应商编号"/>
	<input type="hidden" name="fields" value="srt.name" desc="供应商名称"/>
	<input type="hidden" name="fields" value="srt.describe1" desc="物料名称"/>
	<input type="hidden" name="fields" value="srt.describe1" desc="单位"/>
	<input type="hidden" name="fields" value="srt.describe1" desc="产品类型 "/>
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listSupplierPartSamplingRatio.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
			<th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
					<th>供应商编号 </th>
					<th>供应商名称</th>
					<th>物料名称 </th>
					<th><bean:message key="wmsPart.describe1" /> </th>
					<th><bean:message key="wmsPart.describe2" /> </th>
					<th>单位 </th>
					<th>产品类型 </th>
					<th>时间</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<%--<jsp:include page="row.jsp" />
			--%></logic:iterate>
		</tbody>
	</table>
</page:form>
 
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

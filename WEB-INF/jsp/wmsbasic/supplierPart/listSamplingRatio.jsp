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
	 function download(){
	 	var url="exportsSupplierPartSamplingRatio.do";
	    window.location.href = url;
	}
 
	function showImportSupplierPartSamplingRatio(){
		v = window.showModalDialog(
			'showDialog.do?title=StorageLocation.new.title&showImportSupplierPartSamplingRatio.do' , 
			null, 'dialogWidth:400px;dialogHeight:200px;status:no;help:no;scroll:no');
		if (v) {
			 var url="listSupplierPartSamplingRatio.do";
	   		 window.location.href = url;
		};
	}
//-->
</script>
<html:form action="/listSupplierPartSamplingRatio.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="sr.supplierId.code" desc="供应商编号"/>
	<input type="hidden" name="fields" value="sr.supplierId.name" desc="供应商名称"/>
	<input type="hidden" name="fields" value="sr.part.id" desc="物料编号"/>
	<input type="hidden" name="fields" value="sr.part.describe1" desc="描述一"/>
	<input type="hidden" name="fields" value="sr.part.describe2" desc="描述二"/>
	<input type="hidden" name="fields" value="sr.part.unit" desc="单位"/>
	<input type="hidden" name="fields" value="sr.date" desc="时间"/>
	<input type="hidden" name="fields" value="sr.qty" desc="抽检比例 "/>
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
					<th>供应商编号</th>
					<th>供应商名称 </th>
					<th>物料号 </th>
					<th>描述一 </th>
					<th>描述二</th>
					<th>单位</th>
					<th>时间</th>
					<th>抽检比例</th>
					<th>状态</th>
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


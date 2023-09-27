<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
	function listView(id) {
		window.location.href="listUnfinishPlan.do?productionId="+id;
	}
	function validateForm(){
		var myFile = document.getElementsByName('fileContent')[0].value;
		if(myFile==""){
			alert("请填写导入文件路径!");
			return false;
		} 
		return true;
} 
	function dcexcel(){
	//alert("1");
		window.location.href = "drexcelmoban.do";
	}
//-->
</script>
<html:form action="/listUnfinishPlanGroup.do">
	 <input type="hidden" name="fields" value="" desc="请选择"/>
	 <input type="hidden" name="fields" value="entity.asnNo" desc="ASN号"/>
	<input type="hidden" name="fields" value="entity.supplier" desc="供应商名称"/>
	<input type="hidden" name="fields" value="entity.uploadFileName" desc="上传文件名称"/>
	<input type="hidden" name="fields" value="entity.uploadTime" desc="上传时间"/>
	<input type="hidden" name="fields" value="entity.uploader" desc="上传者"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<html:form action="/importeUnfinishPlan.do" enctype="multipart/form-data" onsubmit="return validateForm()">
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td width="5%"></td>
	  		<td width="40%"><html:file property="fileContent" size="40"></html:file></td>
	  		<td width="5%" align="left"><html:submit>导入</html:submit></td>
	  		<td align="left"><input type="button" value="<bean:message key="template.import"/>"
					onClick="dcexcel()" /></td>
	  	</tr>
	</table>
</html:form>
<hr />
<page:form action="/listUnfinishPlanGroup.do" method="post">
	<table class="data" style="table-layout: fixed;">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="10%" ><page:order order="id" style="text-decoration:none">
					<bean:message key="ediproduction.shiduan" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th><bean:message key="ediproduction.supplier" /></th>
				<th><bean:message key="ediproduction.uploadFileName" /></th>
				<th><bean:message key="ediproduction.uploadTime" /></th>
				<th><bean:message key="ediproduction.uploader" /></th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="rowGroup.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>


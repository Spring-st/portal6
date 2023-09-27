<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
	function addStoreRoom() {
		v = window.showModalDialog(
			'showDialog.do?title=StoreRoom.new.title&newStoreRoom.do' , 
			null, 'dialogWidth:500px;dialogHeight:400px;status:no;help:no;scroll:no');
		if (v) {
			var table = document.all('datatable');
			appendRow(table, v);
		    applyRowStyle(table);
		};
	}

	function edit(id) {
		v = window.showModalDialog(
			'showDialog.do?title=StoreRoom.edit.title&editStoreRoom.do?id=' + id , 
			null, 'dialogWidth:500px;dialogHeight:400px;status:no;help:no;scroll:no');
		if (v) {
			updateRow(document.all('r' + id), v);
		};
	}
	
//-->
</script>
<html:form action="/listStoreRoom.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="sr.code" desc="库区编码"/>
	<input type="hidden" name="fields" value="sr.name" desc="库房名称"/>
	<input type="hidden" name="fields" value="sr.address" desc="地址"/>
	<input type="hidden" name="fields" value="sr.type" desc="类型"/>
	<input type="hidden" name="fields" value="sr.date" desc="时间"/>
	<input type="hidden" name="fields" value="sr.largest_inventory" desc="最大存储量"/>
	<input type="hidden" name="fields" value="sr.remark" desc="备注"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listStoreRoom.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="10%"><page:order order="id" style="text-decoration:none">
					库房编码
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>

				<th width="20%"><page:order order="name"
					style="text-decoration:none">
					库房名称
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>地址</th>
				<th>类型</th>
				<th>时间</th>
				<th>最大存储量</th>
				<th>状态</th>
				<th>备注</th>
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


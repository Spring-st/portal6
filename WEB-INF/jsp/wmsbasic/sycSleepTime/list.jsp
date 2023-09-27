<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
	function addSycSleepTime() {
		v = window.showModalDialog(
			'showDialog.do?title=tool WorkCenter.new.title&newSycSleepTimeMaintenanceAction.do' , 
			null, 'dialogWidth:400px;dialogHeight:380px;status:no;help:no;scroll:no');
		if (v) {
			var table = window.location.href = "listSycSleepTimeListAction.do";
			appendRow(table, v);
		    applyRowStyle(table);
		};
	}

	function edit(id) {
		v = window.showModalDialog(
			'showDialog.do?title=tool WorkCenter.edit.title&editSycSleepTimeMaintenanceAction.do?id=' + id , 
			null, 'dialogWidth:400px;dialogHeight:380px;status:no;help:no;scroll:no');
		if (v) {
			 window.location.href = "listSycSleepTimeListAction.do";
		};
	}
	
	function remove(id) {
		if(confirm("确定要删除吗？")){
			window.location.href="deleteSycSleepTimeMaintenanceAction.do?id="+id;
		}
	}
 	 
</script>
<html:form action="/listSycSleepTimeListAction.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<%--<input type="hidden" name="fields" value="sst.code" desc="器具编码"/>
	<input type="hidden" name="fields" value="sst.capacity" desc="容量"/>
	<input type="hidden" name="fields" value="sst.date" desc="日期"/>
	
	--%><html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listSycSleepTimeListAction.do" method="post">
	<html:hidden property="id" />
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th align="left">序号</th>
				<th align="left">类别</th>
				<th align="left">时间（分钟）</th>
				<th align="left"><bean:message key="all.operate" /></th>
			</tr>
		</thead>

		<tbody id="datatable">
			<c:forEach items="${X_RESULTLIST }" var="X_OBJECT"
				varStatus="indexStatus">
				<tr id="r${X_OBJECT.id}">
					<td align="center"> <a href='javascript:edit("${X_OBJECT.id}")'>${indexStatus.index+1}</a></td>
					<td align="center"> ${X_OBJECT.type} </td>
					<td align="center">${X_OBJECT.sleepTime} </td>
					<td align="center">
						<a href='javascript:remove("${X_OBJECT.id}")'><bean:message key="all.delete" /></a>
					</td>
				</tr>
			</c:forEach>
			<!--<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="row.jsp" />
			</logic:iterate>
		--></tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>


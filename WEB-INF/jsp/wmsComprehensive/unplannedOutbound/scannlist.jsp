<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<script language="javascript" src="includes/table.js"></script>
<script language="javascript" src="includes/basicJS.js"></script>
<script src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
		 function edit(id){
			 v = window.showModalDialog(
			'showDialog.do?title=purchaseOrderRqcDetermine.title&getUnplannedWarehousingByBox.do?array='+id, 
			null, 'dialogWidth:600px;dialogHeight:450px;status:no;help:no;scroll:no');
			 if (v) {
			 location = location;
			};
		 }
</script>
<html:form action="/listWmsUnplannedWarehousingScann.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="pwom.code" desc="非计划出库代码"/>
	<input type="hidden" name="fields" value="pwom.date" desc="申请日期"/>
	<input type="hidden" name="fields" value="pwom.remark" desc="备注"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listWmsUnplannedWarehousingScann.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th><page:order order="id" style="text-decoration:none">
					非计划入库代码
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>操作用户</th>
				<th>申请日期</th>
				<th>计划数量</th>
				<th>已入数量</th>
				<th>原因代码</th>
				<th>费用科目</th>
				<th>费用部门</th>
				<th>状态</th>
				<th>备注</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
					<tr id="r${X_OBJECT.id}" align="center"> 
					<td> <a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.code}</a> </td>
					<td>${X_OBJECT.applicant.name}</td>
					<td><fmt:formatDate value="${X_OBJECT.date}" pattern="yyyy-MM-dd"/></td>
					<td>${X_OBJECT.qty}</td>
					<td>${X_OBJECT.actual_qty}</td>
					
					<td>${X_OBJECT.reason_code.instructions}</td>
					<td>${X_OBJECT.reason_code.expenses_course}</td>
					<td>${X_OBJECT.reason_code.department_cost}</td>
					
					<td>${X_OBJECT.status.chnShortDescription}</td>
					<td>${X_OBJECT.remark}</td>
				</tr>
			</logic:iterate>
		</tbody>
	</table>
</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

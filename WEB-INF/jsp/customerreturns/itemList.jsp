<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script language="javascript" src="includes/table.js"></script>
 <html:form action="itemListCustomerreturnsAction" >
	<input type="hidden" name="chanpinId" value="${chanpin_id}"/>
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="salesWorkorder.lotSer.id" desc="批次号"/>
	<input type="hidden" name="fields" value="salesWorkorder.part.id" desc="物料编号"/>
	<input type="hidden" name="fields" value="salesWorkorder.part.describe1" desc="物料描述"/>
	<input type="hidden" name="fields" value="salesWorkorder.part.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="salesWorkorder.part.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="salesWorkorder.location.code" desc="出库库位"/>
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/itemListCustomerreturnsAction.do">	
<input type="hidden" name="chanpinId" id="chanpinId" value="${chanpin_id}"/>
	<table class="data" width="100%" style="display: inline;">
		<thead>
			<tr bgcolor="#9999ff">
				<th>批次号</th>
				<th>物料编号</th>
				<th>物料描述</th>
				<th>DPI</th>
				<th>原厂编号</th>
				<th>出库库位</th>
				<th>数量</th>
				<th>出库时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="datatable3">
			<c:forEach items="${workOrderList}" var="X_OBJECT">
			<c:set var="X_OBJECT"  scope="request" value="X_OBJECT"/>
				<tr align="center">
					<td>
						${X_OBJECT.lotSer.id}
						<input type="hidden" name="workOrderId" value="${X_OBJECT.id}"/>
					</td>
					<td>${X_OBJECT.part.id}</td>
					<td>${X_OBJECT.part.describe1}</td>
					<td>${X_OBJECT.part.dpiNo}</td>
					<td>${X_OBJECT.part.oldCode}</td>
					<td>${X_OBJECT.location.code}</td>
					<td><fmt:formatNumber value="${X_OBJECT.count}" maxFractionDigits="0"/>
						<input type="hidden" id="${X_OBJECT.id}count" value="${X_OBJECT.count}"/>
					</td>
					<td>${X_OBJECT.outDate}</td>
					<td><a href="javascript:void(0)" onclick="detailDeleteRow(this.parentNode.parentNode);">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</page:form>
 <script type="text/javascript">
 function detailDeleteRow(row){
		var table = row.parentNode;
		table.deleteRow(row.sectionRowIndex);
		applyRowStyle(table);
		this.getchanpinId();
	}
 function getchanpinId(){
	 var str="";
	 var qty=0;
	 var workOrderIds=document.getElementsByName("workOrderId");
	 	for(var i=0;i<workOrderIds.length;i++){
	 		var count=document.getElementById(workOrderIds[i].value+"count").value;
	 		qty=qty+parseInt(count);
	 		str = str + workOrderIds[i].value+",";
	 	}
	 	parent.setchanpin(str,qty);
	 	//document.getElementById("chanpin").value=str;
	 	return str;
 }
 //document.getElementById('chanpinId').setExpression("value", "getchanpinId()");
    applyRowStyle(document.all('datatable3'));
</script>

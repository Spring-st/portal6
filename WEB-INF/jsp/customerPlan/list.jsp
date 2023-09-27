<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>

<script type="text/javascript">
<!--
	 function addCustomerPlan() {
		//v = window.showModalDialog(
		//	'showDialog.do?title=Price.new.title&newCustomerPlan.do' , 
		//	null, 'dialogWidth:900px;dialogHeight:600px;status:no;help:no;scroll:no');
		//if (v) {
		//	var table = document.all('datatable');
		//	appendRow(table, v);
		 //   applyRowStyle(table);
		//};
		var url = "newCustomerPlan.do";
		window.location.href = url;
	}

	//function edit(id) {
	//	v = window.showModalDialog(
	//		'showDialog.do?title=Price.edit.title&editCustomerPlan.do?id=' + id , 
	//		null, 'dialogWidth:400px;dialogHeight:450px;status:no;help:no;scroll:no');
	//	if (v) {
	//		updateRow(document.all('r' + id), v);
	//	};
	 function view(id) {
		var url = "viewCustomerPlan.do?id="+id;
		window.location.href = url;
	}
//-->
</script>
<html:form action="listCustomerPlan" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="customerPlan.planNumbers" desc="单号"/>
	<input type="hidden" name="fields" value="customerPlan.line" desc="行号"/>
	<input type="hidden" name="fields" value="customerPlan.customer.code" desc="客户编号"/>
	<input type="hidden" name="fields" value="customerPlan.customerAddress" desc="客户地址"/>
	<input type="hidden" name="fields" value="customerPlan.wmspart.id" desc="物料编号"/>
	<input type="hidden" name="fields" value="customerPlan.wmspart.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="customerPlan.wmspart.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="customerPlan.wmspart.describe1" desc="物料描述"/>
	<input type="hidden" name="fields" value="customerPlan.um" desc="单位" />
	<input type="hidden" name="fields" value="customerPlan.entryDate" desc="要货日期"/>
	<input type="hidden" name="fields" value="customerPlan.qty" desc="要求发货数量"/>
	<input type="hidden" name="fields" value="customerPlan.receiptQty" desc="已发货数量"/>
	<input type="hidden" name="fields" value="customerPlan.qtyOpen" desc="未发货数量"/>
	<%--<input type="hidden" name="fields" value="customerPlan.returnQtySum" desc="退货数量"/>--%>
	<input type="hidden" name="fields" value="customerPlan.operation" desc="业务员"/>
	<input type="hidden" name="fields" value="customerPlan.shipmentDate" desc="录入日期" />
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	<input type="hidden" id="statusCheck" name="statusCheck"/>

	<table width=100% border="0" cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="listCustomerPlan" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				    <th width="10%"><page:order order="id" style="text-decoration:none">
					           单号
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
					</page:order></th>
				    <th>行号</th>
				    <th>客户编号</th>
				    <th>客户地址</th>
				    <th>物料编号</th>
				    <th>DPI</th>
				    <th>原厂编号</th>
				    <th>物料描述</th>
				    <th>单位</th>
				   
				    <th>要货日期</th>
				    <th>要求发货数量</th>
				    <th>已发货数量</th>
				    <th>未发货数量</th>
				    <%--<th>退货数量</th>--%>
				    <th>业务员</th>
				    <th>录入日期</th>
				    <%--<th>打印状态</th>
			--%></tr>
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


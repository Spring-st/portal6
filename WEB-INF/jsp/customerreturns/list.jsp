<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">

function view(id) {
		var url = "viewCustomerreturnsByItem.do?id=" + id;
		window.location.href = url;
	}


function printExcel(){
	window.location.href = "listShippingOrder.do?isExcel=true";
}
function del(id){
	window.location.href = "updateDelStatusAction.do?id="+ id;
}
function add(){
	<%-- returnValue = window.showModalDialog(
				'showDialog.do?title=customer.new.title&newCustomerreturnsAction.do?type=1', 
				null, 'dialogWidth:840px;dialogHeight:620px;status:no;help:no;scroll:no');--%>
	window.location.href="newListCustomerreturnsAction.do";
}
</script>
<html:form action="listCustomerreturnsAction" >
<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="entity.returnDate" desc="发货时间"/>
	<input type="hidden" name="fields" value="entity.returnNumber" desc="发货单号"/>
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
	
</html:form>
<page:form action="listCustomerreturnsAction.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th>
					<bean:message key="customer.returnNumber"/>
				</th>
				<th>
					<bean:message key="customer.customerCode"/>
				</th>
				<th>
					<bean:message key="customer.customerDescription"/> 
				</th>
				<%--<th>
					<bean:message key="customer.materialCode"/> 
				</th>
				<th>
					<bean:message key="customer.materialName"/> 
				</th>
				--%><th>
					<bean:message key="customer.location_code"/> 
				</th>
				<th>
					<bean:message key="customer.returnQuantity"/> 
				</th>
				<th>
					<bean:message key="customer.returnLocation"/> 
				</th>
				<th>
					<bean:message key="customer.returnDate"/> 
				</th>
				<th>
					<bean:message key="customer.returnPerson"/> 
				</th>
				<th>
					<bean:message key="customer.returnPersonContact"/> 
				</th>
				<th>
					是否打印
				</th>
				<th >
					操作 
				</th>
				
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<tr align="center">
						<td width="15%" align="center">
								<a href='javascript:view("${X_OBJECT.id}")'>${X_OBJECT.returnNumber}</a>
							<%-- 	${X_OBJECT.returnNumber}--%>
						</td>
						<td>
							${X_OBJECT.basicCustomer.code}
						</td>
						<td>
							${X_OBJECT.customerDescription}
						</td>
						<%--<td>
							${X_OBJECT.materialCode}
						</td>
						<td>
							${X_OBJECT.materialName}
						</td>
						--%><td>
							${X_OBJECT.returnStorage}
						</td>
						<td>
							${X_OBJECT.returnQuantity}
						</td>
						<td>
							${X_OBJECT.returnLocation}
						</td>
						<td>
							<c:if test="${X_OBJECT.returnDate!= null }">
								<bean:write name="X_OBJECT" property="returnDate" format="yyyy-MM-dd" />
							</c:if>
						</td>
						<td>
							${X_OBJECT.returnPerson}
						</td>
						<td>
							${X_OBJECT.returnPersonContact}
						</td>
						<td align="center""> 
				 			<c:if test="${X_OBJECT.printStatus=='0'}">
				 				<span style="color: red;">已打印</span>
				 			</c:if>
				 			<c:if test="${X_OBJECT.printStatus!='0'}">
				 				未打印
				 			</c:if>
			 			</td>
						<td>
							<c:if test="${X_OBJECT.printStatus!='0'}">
								<a href='javascript:del("${X_OBJECT.id}")'>删除</a>
							</c:if>
							<c:if test="${X_OBJECT.printStatus=='0'}">
				 				<a style="display: none;" href='javascript:void(0)'>删除</a>
				 			</c:if>
						</td>
				</tr>
			</logic:iterate>
		</tbody>
	</table>
</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>


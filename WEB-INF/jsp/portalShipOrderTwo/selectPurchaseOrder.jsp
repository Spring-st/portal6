<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">

function view(id) {
	v = window.showModalDialog(
			'showDialog.do?title=site.purchase.title&viewBySupplierPurchaseOrder.do?id='+id, 
			null, 'dialogWidth:660px;dialogHeight:540px;status:no;help:no;scroll:no');
}

 	function generate(){
 		var check = document.getElementsByName('ids');
		var list = new Array();
		var j=0;
		for(i=0;i<check.length;i++){
			if(check[i].checked == true){
				list[j]=check[i].value;
				j++;
			}
		}
		if(j==0){
				alert("请选择采购单!");
		  		return false;
		}
		var result = [];
		result['list'] = list;
		//result['poOrder'] = poOrder;
		window.parent.returnValue = result;
		window.parent.close();
		 
	 }

 	function printExcel(){
		window.location.href = "listPurchase.do?isExcel=true";
	}
</script>
<html:form action="listPurchaseTwo" >

	<html:hidden property="order" />
	<html:hidden property="descend" />

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	<tr>
		<td class="bluetext">
			<bean:message key="supplier"/>:
		</td>
		<td>
			<html:text property="supplier" size="15" />
		</td>
		<td class="bluetext">
			<bean:message key="Status"/>:
		</td>
		<td>
			<html:select property="status">
			<html:option value=""><bean:message key="all.all"/></html:option>
			<c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><html:options collection = "X_WmsPurchaseOrderStatusLIST" property = "enumCode" labelProperty = "engShortDescription"/></c:if>
			<c:if test="${sessionScope.LOGIN_USER.locale!='en'}"><html:options collection = "X_WmsPurchaseOrderStatusLIST" property = "enumCode" labelProperty = "chnShortDescription"/></c:if>
			</html:select>
			</td>
		</tr>
										
		<tr>
			<td class="bluetext">
					<bean:message key="order.number"/>:
			</td>
			<td colspan="3">
				<html:text property="poNumber" size="15"/>
			</td>
			<!--  <td class="bluetext"><bean:message key="whether.the.generated.invoices"/>:</td>
			<td>
			<html:select property="isPrint">
												
			<c:if test="${sessionScope.LOGIN_USER.locale=='en'}">
			<html:options collection="X_YESNOLIST"
				property="enumCode" labelProperty="engShortDescription" />
			</c:if>
			<c:if test="${sessionScope.LOGIN_USER.locale!='en'}">
			<html:options collection="X_YESNOLIST" property="enumCode" labelProperty="chnShortDescription" />
			</c:if>
			</html:select>
			</td>-->
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="start.time"/>:
			</td>
			<td>
				<html:text property="poDate" size="15" />
				<a onclick="event.cancelBubble=true;"
				href="javascript:showCalendar('dimg1',false,'poDate',null,null,'portalPurchaseOrderInspectionPendingQueryForm')"><img
				align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" /> </a>
			</td>
			<td class="bluetext">
				<bean:message key="end.time"/>:
			</td> 
			<td>
			<html:text property="dueDate" size="15" />
				<a onclick="event.cancelBubble=true;"
				href="javascript:showCalendar('dimg2',false,'dueDate',null,null,'portalPurchaseOrderInspectionPendingQueryForm')"><img
				align="absmiddle" border="0" id="dimg2"
			src="images/datebtn.gif" /> </a>
			</td>
											
		</tr>
		<tr>
			<td colspan="2"></td>
			<td align="right">
			<input type="submit" value="<bean:message key="all.query"/>" />
			<%--<input type="button" onclick="printExcel();" value="<bean:message key="page.export.excel"/>" />
			--%></td>
			<td align="center">
				<%--<input type="button" onclick="generate()" value="<bean:message key="to.generate.the.invoice"/>" />
			--%></td>
		</tr>
		<tr><td colspan="4"><hr/></td></tr>
	</table>

</html:form>
<page:form action="listPurchaseTwo.do" method="post">

	<jsp:include page="../pageHead.jsp"/>
	<table class="data">
		<thead>
			<tr  bgcolor="#9999ff">
					<th width="4%">
						<%--<input type="checkbox" />
					--%></th>
					<th width="10%">
						<bean:message key="PO.No"/>
					</th>
					<th width="8%">
						<bean:message key="supplier"/>
					</th>
					<th width="8%">
						<bean:message key="supplier.code"/>
					</th>
					<th width="8%">
						<bean:message key="order.date"/>
					</th>
					<th width="6%">
						<bean:message key="Status"/>
					</th>
					<th width="6%">
						<bean:message key="Remark"/> 
					</th>
					<th width="8%">
						<bean:message key="whether.the.generated.invoices"/>
					</th>
				</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<tr>
					<td width="4%">
						<c:if test="${X_OBJECT.isPrint == '0'}">
						<input type="checkbox" id="ids" value="${X_OBJECT.id}" style="display: none"/>
						</c:if>
						<c:if test="${X_OBJECT.isPrint == '1'}">
						<input type="checkbox" id="ids" value="${X_OBJECT.id}"/>
						</c:if>
					</td>
															
					
					<td width="10%">
						<a href='javascript:view("${X_OBJECT.id}")'>${X_OBJECT.po_number.poOrder}</a>
					</td>
					<td width="8%">
						${X_OBJECT.supplier.name}
					</td>
					<td width="8%">
						${X_OBJECT.supplier.code}
					</td>
					<td width="8%">
						${X_OBJECT.po_number.poDate}
					</td>
					<td width="6%">
						${X_OBJECT.status.chnShortDescription}
					</td>
					<td width="6%">
						${X_OBJECT.remark}
					</td>
					<td width="8%">
						<c:if test="${X_OBJECT.isPrint == '0'}">
							Yes
						</c:if>
						<c:if test="${X_OBJECT.isPrint == '1'}">
							No
						</c:if>
					</td>
				</tr>
			</logic:iterate>
		</tbody>
	</table>
	<table>
		<tr>
			<td align="right">
				<input type="button" onclick="generate()" value="<bean:message key="select.purchaseOrder"/>" />
			</td>
		</tr>
	</table>
</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>




<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--

//-->
</script>
<c:set var="x_action" value="listWmsStockingInventory"/>

<html:form action="${x_action}">
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext">
				<bean:message key="travelApplication.site" />
				&nbsp;
			</td>
				<td>
				${x_sitename}
				</td>
		</tr>
		<tr>
			<td width="15%" class="bluetext"><bean:message key="inventory.storeRoom.address" />:</td>
			<td width="30%">
 				${x_storeroomname}
            </td>
            
            <td width="15%" class="bluetext">零件号:</td>
			<td width="30%">
                <html:text property="partCode"></html:text>
            </td>
		</tr>
		<tr>
			 <td align="left" colspan="4">
				<html:submit><bean:message key="all.query"/></html:submit>
			</td>
		</tr>
	</table>
</html:form>
<hr />
<page:form action="${x_action}" method="post">
	<jsp:include page="../pageHead.jsp"/>
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
								<th>
					<bean:message key="inventory.partCode" />
					</th>
										<th>
					<bean:message key="wmsPart.describe1" />
					</th>
					<th>
					<bean:message key="wmsPart.describe2" />
					</th>
					<th>
					<bean:message key="inventory.storeRoom.address" />
					</th>

					<th>
					<bean:message key="inventory.storeRoom.number" />
					</th>
					<th>
					<bean:message key="inventory.initialNumber" />
					</th>
				 	<th></th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="newItemViewListRow.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>


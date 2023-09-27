<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ page import="com.aof.model.metadata.SupplierPromoteStatus"%>

<script type="text/javascript">

function select(code) {
		var result = [];
		result['code'] = code;
		window.parent.returnValue = result;
		window.parent.close();
	}



</script>
<html:form action="listSupplier${x_version}">
	<html:hidden property="order" />
	<html:hidden property="descend" />
	

	
	
</html:form>
<hr />
<page:form action="listSupplier${x_version}.do" method="post">
	
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="10%"><page:order order="code" style="text-decoration:none">
					<bean:message key="supplier.code" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>

				<th width="20%"><page:order order="name"
					style="text-decoration:none">
					<bean:message key="supplier.name" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th width="10%"><bean:message key="supplier.telephone" /></th>
				<th width="10%"><bean:message key="supplier.fax" /></th>
				<th width="7%"><bean:message key="supplier.post" /></th>
				<th width="12%"><bean:message key="supplier.contactor" /></th>
				<th width="10%"><bean:message key="supplier.contractStatus" /></th>
				<th width="10%">
				<page:order order="promoteStatus"
					style="text-decoration:none">
					<bean:message key="supplier.promoteStatus" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th width="5%">
				<page:order order="enabled"
					style="text-decoration:none">
					<bean:message key="supplier.status" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th><bean:message key="supplier.operate" /></th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="x_result">
			
				<tr id="r${X_OBJECT.supplierId.code}">
				  <td>
					 ${X_OBJECT.supplierId.code}
				  </td>
				  <td>${X_OBJECT.supplierId.name}</td>
				  <td>${X_OBJECT.supplierId.telephone1}</td>
				  <td>${X_OBJECT.supplierId.fax1}</td>
				  <td>${X_OBJECT.supplierId.post}</td>
				  <td>${X_OBJECT.supplierId.contact}</td>
				  <td>
				  	<span style="color:${X_OBJECT.supplierId.contractStatus.color}">
				      <c:if test="${sessionScope.LOGIN_USER.locale=='en'}">${X_OBJECT.supplierId.contractStatus.engShortDescription}</c:if>
				      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}">${X_OBJECT.supplierId.contractStatus.chnShortDescription}</c:if>
				    </span>  
				  </td>
				  <td>
				  	<span style="color:${X_OBJECT.supplierId.promoteStatus.color}">
				      <c:if test="${sessionScope.LOGIN_USER.locale=='en'}">${X_OBJECT.supplierId.promoteStatus.engShortDescription}</c:if>
				      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}">${X_OBJECT.supplierId.promoteStatus.chnShortDescription}</c:if>
				    </span>  
				  </td>
				  <td>
					<span style="color:${X_OBJECT.supplierId.enabled.color}">
				      <c:if test="${sessionScope.LOGIN_USER.locale=='en'}">${X_OBJECT.supplierId.enabled.engShortDescription}</c:if>
				      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}">${X_OBJECT.supplierId.enabled.chnShortDescription}</c:if>
				    </span> 
				  </td>
				   <td><a href="javascript:select('${X_OBJECT.supplierId.code}')">选择</a></td>
				</tr>
			</logic:iterate>
		</tbody>
	</table>

</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>


<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>

<script type="text/javascript">
function select(id,code, name,address) {
	    var list = new Array();
		var result = [];
		result['id'] = id;
		result['code'] = code;
		result['name'] = name;
		result['address'] = address;
		window.parent.returnValue = result;
		window.parent.close();
	}
</script>
<html:form action="selectBasicCustomer" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="basicCustomer.code" desc="客户编码"/>
	<input type="hidden" name="fields" value="basicCustomer.name2" desc="客户全称"/>
	<input type="hidden" name="fields" value="basicCustomer.address" desc="客户地址"/>
	<input type="hidden" name="fields" value="basicCustomer.country" desc="国家"/>
	<input type="hidden" name="fields" value="basicCustomer.city" desc="城市"/>
	<input type="hidden" name="fields" value="basicCustomer.site" desc="工厂	"/>
	<input type="hidden" name="fields" value="basicCustomer.domain" desc="域"/>
	
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="statusCheck" name="statusCheck"/>

	<table width=100% border="0" cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="selectBasicCustomer" method="post">
		<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th>
					<page:order order="id" style="text-decoration:none">
						<bean:message key="basic_customer.code" />
						<page:asc><img src="images/up.gif" border="0" /></page:asc>
						<page:desc><img src="images/down.gif" border="0" /></page:desc>
					</page:order>
				</th>
				<th>
					<page:order order="id" style="text-decoration:none">
						<bean:message key="basic_customer.name1" />
						<page:asc><img src="images/up.gif" border="0" /></page:asc>
						<page:desc><img src="images/down.gif" border="0" /></page:desc>
					</page:order>
				</th>
				<th><bean:message key="basic_customer.address" /></th>
				<th>国家</th>
				<th>城市</th>
				<th>工厂</th>
				<th>域</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="selectRow.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

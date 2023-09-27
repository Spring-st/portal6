<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript" src="includes/selection.js"></script>
<script type="text/javascript">
<!--
	function select(id,code, name1,name2,address,address2,address3,country,city,fax,postId,currencyType,
	                contacts,phone,email,product,site,domain,remarks,chnShortDescription) {
		var result = [];
		result['id'] = id;
		result['code']=code
		result['name1'] = name1;
		result['name2'] = name2;
		result['address'] = address;
		result['address2'] = address2;
		result['address3'] = address3;
		result['country'] = city;
		result['fax'] = fax;
		result['postId'] = postId;
		result['currencyType'] = currencyType;
		result['contacts'] = contacts;
		result['phone'] = phone;
		result['email'] = email;
		result['product'] = product;
		result['site'] = site;
		result['domain'] = domain;
		result['remarks'] = remarks;
		result['chnShortDescription'] = chnShortDescription;
		window.parent.returnValue = result;
		window.parent.close();
	}
//-->
</script>
<html:form action="/listCustomerReturn.do">
<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="basicCustomer.code" desc="客户编码"/>
	<input type="hidden" name="fields" value="basicCustomer.name1" desc="客户简称"/>
	<input type="hidden" name="fields" value="basicCustomer.name2" desc="客户全称"/>
	<input type="hidden" name="fields" value="basicCustomer.address" desc="客户地址"/>
	<input type="hidden" name="fields" value="basicCustomer.address2" desc="地址2"/>
	<input type="hidden" name="fields" value="basicCustomer.address3" desc="地址3"/>
	<input type="hidden" name="fields" value="basicCustomer.country" desc="国家"/>
	<input type="hidden" name="fields" value="basicCustomer.city" desc="城市"/>
	<input type="hidden" name="fields" value="basicCustomer.fax" desc="传真"/>
	<input type="hidden" name="fields" value="basicCustomer.postId" desc="邮编"/>
	<input type="hidden" name="fields" value="basicCustomer.currencyType" desc="货币"/>
	<input type="hidden" name="fields" value="basicCustomer.contacts" desc="联系人"/>
	<input type="hidden" name="fields" value="basicCustomer.phone" desc="联系电话"/>
	<input type="hidden" name="fields" value="basicCustomer.email" desc="客户邮箱"/>
	<input type="hidden" name="fields" value="basicCustomer.product" desc="供货产品"/>
	<input type="hidden" name="fields" value="basicCustomer.site" desc="工厂	"/>
	<input type="hidden" name="fields" value="basicCustomer.domain" desc="域"/>
	<input type="hidden" name="fields" value="basicCustomer.remarks" desc="备注"/>
	<input type="hidden" name="fields" value="basicCustomer.enabled" desc="是否可用"/>
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
<page:form action="selectWmsPart" method="post">			
  <table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th>
					<page:order order="name" style="text-decoration:none">
						<bean:message key="basic_customer.code" />
						<page:desc><img src="images/down.gif" border="0" /></page:desc>
						<page:asc><img src="images/up.gif" border="0" /></page:asc>
					</page:order>
				</th>
				<th>
					<page:order order="name" style="text-decoration:none">
						<bean:message key="basic_customer.name1" />
						<page:desc><img src="images/down.gif" border="0" /></page:desc>
						<page:asc><img src="images/up.gif" border="0" /></page:asc>
					</page:order>
				</th>
				<th><bean:message key="basic_customer.name2" /></th>
				<th><bean:message key="basic_customer.address" /></th>
				<th>地址2</th>
				<th>地址3</th>
				<th>国家</th>
				<th>城市</th>
				<th>传真</th>
				<th>邮编</th>
				<th>货币</th>
				<th><bean:message key="basic_customer.contacts" /></th>
				<th><bean:message key="basic_customer.phone" /></th>
				<th><bean:message key="basic_customer.email" /></th>
				<th><bean:message key="basic_customer.product" /></th>
				<th>工厂</th>
				<th>域</th>
				<th>备注</th>
				<th><bean:message key="basic_customer.enabled" /></th>
				<th></th>
			</tr>
		</thead>
		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="listCustomerRow.jsp" />
			</logic:iterate>
		</tbody>
	</table>
  <script type="text/javascript">
    applyRowStyle(document.all('datatable'));
  </script>
</page:form>		      

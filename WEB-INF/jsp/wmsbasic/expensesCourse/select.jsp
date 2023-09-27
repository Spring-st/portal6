<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
	 function select(id, code, description) {
		var result = [];
		result['id'] = id;
		result['code'] = code;
		result['description'] = description;
		
		window.parent.returnValue = result;
		window.parent.close();
	}
//-->
</script>
<html:form action="/selectExpensesCourse.do">
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<!-- id -->
			<td class="bluetext">账户:</td>
			<td><html:text property="code" /></td>
			<td class="bluetext">描述:</td>
			<td><html:text property="describe" /></td></tr>
			<tr>
			<td class="bluetext"><bean:message key="expensesCourse.status" />:</td>
 		<td>
	    <html:select property="status">
	    	<html:option value=""><bean:message key="all.all"/></html:option>
	      <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><html:options collection = "X_ENABLEDDISABLEDLIST" property = "enumCode" labelProperty = "engShortDescription"/></c:if>
	      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}"><html:options collection = "X_ENABLEDDISABLEDLIST" property = "enumCode" labelProperty = "chnShortDescription"/></c:if>
	    </html:select>
	  </td>
	  <td class="bluetext">类型:</td>
	  <td>
	   <html:select property="type">
	   <html:option value="">请选择</html:option>
			 		<html:option value="A">A</html:option>
			 		<html:option value="E">E</html:option>
			 		<html:option value="I">I</html:option>
			 		<html:option value="L">L</html:option>
                </html:select>
	  </td>
    </tr>
		<tr>
			<td class="bluetext">货币:</td>
			<td>
			  <html:select property="currency" >
			  	 <html:option value="">请选择</html:option>
			     <html:option value="CNY">CNY</html:option>
			     <html:option value="JPY">JPY</html:option>
			     <html:option value="USD">USD</html:option>
			  </html:select>
			</td>
			<td align="left" colspan="2">
				<html:submit><bean:message key="all.query"/></html:submit>
				<input type="button" value="关闭"
					onClick="window.close();" />
			</td>
		</tr>
	</table>
</html:form>
<hr />
<page:form action="/selectExpensesCourse.do" method="post">
	<jsp:include page="../../pageHead.jsp"/>
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th><page:order order="id" style="text-decoration:none">
					<bean:message key="expensesCourse.id" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>账户</th>
				<th><page:order order="name"
					style="text-decoration:none">
					费用科目名称
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>类型</th>
				<th>货币</th>
				<th><bean:message key="expensesCourse.status"/></th>
				<th>请选择11</th>
			</tr>
		</thead>

		<tbody id="datatable">
		 <logic:iterate id="X_OBJECT" name="X_RESULTLIST">
		 <tr id="r${X_OBJECT.id}">
			<td><a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.id}</a></td>
			<td>${X_OBJECT.code}</td>
			<td>${X_OBJECT.description}</td>
			<td>${X_OBJECT.type}</td>
			<td>${X_OBJECT.currency}</td>
			<td>
			<span style="color:${X_OBJECT.enabled.color}">
   		    <c:if test="${sessionScope.LOGIN_USER.locale=='en'}">${X_OBJECT.enabled.engShortDescription}</c:if>
   		    <c:if test="${sessionScope.LOGIN_USER.locale!='en'}">${X_OBJECT.enabled.chnShortDescription}</c:if>
    		</span>
  			</td>
  			<td><a href='javascript:select("${X_OBJECT.id}", "${X_OBJECT.code}", "${X_OBJECT.description}");'><bean:message key="all.select"/></a></td>
		</tr>
		</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

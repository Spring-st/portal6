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
	function select(id,code, name, address, describe2) {
	    var list = new Array();
		var result = [];
		result['id'] = id;
		result['str'] = id;
		result['code'] = code;
		result['name'] = name;
		result['address'] = address;
		result['describe'] = describe2;
		window.parent.returnValue = result;
		window.parent.close();
	}
//-->
</script>
<html:form action="/selectStorageLocationRadio.do">
<html:hidden property="order"/>
<html:hidden property="descend"/>
<table width="100%" border="0" cellpadding="4" cellspacing="0">
  <tbody>
    <tr>
<!-- id -->
			<td class="bluetext" width="20%"><bean:message key="warehouseInventoryRepor.code" />(模糊查询):</td>
			<td><html:text property="code" size="8"/></td>
<%--			<td class="bluetext"><bean:message key="storageLocation.isEnable" />:</td>--%>
<%--<td>--%>
<%--	    <html:select property="status">--%>
<%--	    	<html:option value=""><bean:message key="all.all"/></html:option>--%>
<%--	      <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><html:options collection = "X_ENABLEDDISABLEDLIST" property = "enumCode" labelProperty = "engShortDescription"/></c:if>--%>
<%--	      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}"><html:options collection = "X_ENABLEDDISABLEDLIST" property = "enumCode" labelProperty = "chnShortDescription"/></c:if>--%>
<%--	    </html:select></td>--%>
    </tr>
    <tr>
    	<td colspan="2" class="bluetext">请输入%进行匹配。如A%</td>
    </tr>
    <tr>
    <td class="bluetext"><bean:message key="warehouseInventoryRepor.code" />(多次查询):</td>
	<td><html:text property="codemany" size="8"/></td>
    </tr>
	<tr><td class="bluetext"><bean:message key="storageLocation.describe" />:</td>
			<td><html:text property="describe" size="8"/></td>
		</tr>
     <tr>
    	<td>
    		每页显示条数
    	</td>
    	<td>
    		<html:text property="pageSize"></html:text>
    	</td>
    </tr>

    <tr>
     <td align="left">
        <html:submit><bean:message key="all.query"/></html:submit>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" onclick="window.close()" value="关闭"/>
      </td>
    </tr>
  </tbody>
</table>
</html:form>
<hr/>
<page:form action="/selectStorageLocationRadio.do" method="post">			
  <jsp:include page="../../pageHead.jsp"/>
  <table class="data">
    <thead>
      <tr bgcolor="#9999ff">
        <th>
        <page:order order="id" style="text-decoration:none">
					<bean:message key="storageLocation.id" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				    <th>
					<bean:message key="storageLocation.describe" />
					</th>
					<th>
					<bean:message key="storageLocation.address" />
					</th>
					<th>
					<bean:message key="storageLocation.max_inventory" />
					</th>
					<th>
					<bean:message key="storageLocation.storeroom_id" />
					</th>
					<th>
					<bean:message key="storageLocation.enabled" />
					</th>
				    <th>
	  </th>
      </tr>
    </thead>
    <tbody id="datatable">
      <logic:iterate id="X_OBJECT" name="X_RESULTLIST">
        <bean:define id="X_OBJECT" toScope="request" name="X_OBJECT"/>
        <jsp:include page="selectRowRadio.jsp"/>
      </logic:iterate>
    </tbody>
  </table>
  <script type="text/javascript">
    applyRowStyle(document.all('datatable'));
  </script>
</page:form>		      

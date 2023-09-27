<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">
<!--
	function doSelect(id) {
		var result = [];
		result['id'] = id;
		window.parent.returnValue = result;
		window.parent.close();
	}
	
//-->
</script>

<table width=100% border=1 cellpadding=4 cellspacing=0 bgcolor=ffffff>
	<tr>
		<td bgcolor=f0f0f0 width=100% colspan=2 valign=top>    
			<table width=100% cellpadding=0 cellspacing=0>
	      <tr>
	        <td bgcolor=f0f0f0 width=80% valign=top>
	        	<h3 class="formtitle"><bean:message key="PurchaseOrder.selectPurchaseOrder.title" /> </h3>
	        </td>
	       	<td valign=top align ="right"></td>
	      </tr>
	    </table>
	  </td>
	</tr>
</table>
<html:form action="selectPurchaseOrder">
	<html:hidden property="order" />
	<html:hidden property="descend" />

	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<!-- id -->
			<td class="bluetext"><bean:message key="purchaseOrder.id" />:</td>
			<td><html:text property="id" size="8"/></td>
			
		</tr>
		
		<tr>
			<td align="right" colspan="4">
				<html:hidden property="includeDisabled" value="false"/> 
				<html:submit><bean:message key="all.query"/></html:submit>
			</td>
		</tr>
	</table>
</html:form>
<hr />
<page:form action="selectPurchaseOrder.do" method="post">
	<jsp:include page="../../pageHead.jsp"/>
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="15%"><page:order order="code" style="text-decoration:none">
					<bean:message key="purchaseOrder.id" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th width="5%">&nbsp;</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<tr id="r${X_OBJECT.id}">
				  <td>
					  ${X_OBJECT.id}
				  </td>
				  <td><a href='javascript:doSelect("${X_OBJECT.id}")'><bean:message key="all.select"/></a></td>
				</tr>
			</logic:iterate>
		</tbody>
	</table>
</page:form>
<table width="100%">
<tr><td align="right"><input type="button" value="<bean:message key="all.close" />" onclick="window.parent.close();" /></td></tr>
</table>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
   <%-- var mapping=new Map();
	mapping.put("country_id","country");
	mapping.put("city_id","city");
    initCascadeSelect("config","data","purchaseOrderQueryForm",mapping,true);--%>
</script>


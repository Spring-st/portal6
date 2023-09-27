<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
    function devanning(id){
	var title="wmsProductPlandetailFlightNum.edit.title";
		var url="listDevanningBox.do?id="+id;
		var v=dialogAction(url,title,1024,768);
		if (v) {
			alert("更换库位成功！");
		};
	}
    function exchange(){
    	var title="proplan.proPlanNum.new";
		var url="listExchangeBox.do";
		var v=dialogAction(url,title,600,400);
		if (v) {
			alert("移库成功！");
		};
	}
      function together(){
		window.location.href = "listKnockTogetherBox.do";			
	}
//-->
</script>
<html:form action="listPurchaseOrderBox" styleId="formId">
<html:hidden property="order" />
<html:hidden property="descend" />
<table width=100% border=0 cellpadding=4 cellspacing=0>
	  <tr>
			<!-- id -->
			<td width="20%"  class="bluetext"><bean:message key="storageLocation.site" />:</td>
			<td width="30%"><html:select property="site">
			<html:options collection="X_SITELIST" property="id" labelProperty="name"/>
			</html:select></td>
			<td class="bluetext"><bean:message key="warehouseInventoryRepor.wmsPart" />:</td>
		    <td><html:text property="wmsPart" /></td>
		</tr>
		<tr>
		<td class="bluetext"><bean:message key="warehouseInventoryRepor.code" />:</td>
		<td><html:text property="code" /></td>
		<td class="bluetext"><bean:message key="warehouseInventoryRepor.blanket" />:</td>
		    <td><html:text property="blanket" /></td>
		</tr>
		<tr>
		<td class="bluetext"><bean:message key="warehouseInventoryRepor.lotset" />:</td>
		<td><html:text property="lotSet" /></td>
		<td class="bluetext"><bean:message key="warehouseInventoryRepor.specifications" />:</td>
		    <td><html:text property="specifications" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="warehouseInventoryRepor.date" />:</td>
			<td ><html:text property="datetime" size="6" /> 
				<a onclick="event.cancelBubble=true;"
				href="javascript:showCalendar('dimg1',false,'datetime',null,null,'poIpiBoxQueryForm')"><img
				align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" /></a>
			&nbsp;~&nbsp; <html:text property="endtime" size="6" /> 
				<a onclick="event.cancelBubble=true;"
				href="javascript:showCalendar('dimg2',false,'endtime',null,null,'poIpiBoxQueryForm')"><img
				align="absmiddle" border="0" id="dimg2" src="images/datebtn.gif" /></a>
			</td>
			</tr>
</table>
<div align="left">
<%--<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="拼箱"  onclick="together();"/></c:if>--%>
<%--<c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="Knock Together Box"  onclick="together();"/></c:if>--%>
<html:submit><bean:message key="all.query"/></html:submit>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="更换库位"  onclick="devanning(null);"/></c:if>
<c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="Devanning Box"  onclick="devanning();"/></c:if>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
<%--<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="移库"  onclick="exchange();"/></c:if>--%>
<%--<c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="Exchange Box"  onclick="exchange();"/></c:if>--%>
</div>
</html:form>
<hr />
<page:form action="listPurchaseOrderBox.do" method="post">
	<jsp:include page="../../pageHead.jsp"/>
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="10%"><page:order order="id" style="text-decoration:none">
					<bean:message key="warehouseInventoryRepor.code" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				    </page:order>
				 </th>
				 <th width="15%">
					<bean:message key="warehouseInventoryRepor.lotset" />
					</th>
					<th width="15%">
					<bean:message key="warehouseInventoryRepor.partCode" />
					</th>
					<th width="10%">
					<bean:message key="warehouseInventoryRepor.blanket" />
					</th>
					<th width="15%">
					<bean:message key="warehouseInventoryRepor.specifications" />
					</th>
					<th width="10%">
					<bean:message key="storageLocation.supplierCode" />
					</th>
					<th width="10%">
					<bean:message key="warehouseInventoryRepor.count" />
					</th>
					<th width="5%">
					<bean:message key="warehouseInventoryRepor.nuit" />
					</th>
			</tr>
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


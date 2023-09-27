<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
	function view(id) {
	  window.location.href = "viewWmsStockingReport.do?id="+id;
	}

	function lookOver(id){
		v = window.showModalDialog(
			'showDialog.do?title=Stockingreport.view.title&wmsStockingReportLookOver.do?id='+id , 
			null, 'dialogWidth:1024px;dialogHeight:700px;status:no;help:no;scroll:no');
		if (v) {
			var table = document.all('datatable');
			appendRow(table, v);
		    applyRowStyle(table);
		};
	}
//-->
</script>
<html:form action="/listWmsStockingReport.do">
	<html:hidden property="order" />
	<html:hidden property="descend" />

	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext">
				<bean:message key="travelApplication.site" />
				&nbsp;
			</td>
				<td colspan="3">
				 <html:select property="siteId"><html:options collection="X_SITELIST" property="id" labelProperty="name"/></html:select>
			</td>
		</tr>
		<tr>
			<!-- id -->
			<td class="bluetext"><bean:message key="wmsStocking.id" />:</td>
			<td><html:text property="code" /></td>
			<td class="bluetext"><bean:message key="wmsStocking.name" />:</td>
			<td><html:text property="name"  /></td></tr>
			<tr>
			<td class="bluetext"><bean:message key="wmsStocking.createDate" />:</td>
			<td>
			<html:text property="startdate" size="8" /> 
			<a onclick="event.cancelBubble=true;"
				href="javascript:showCalendar('dimg1',false,'startdate',null,null,'wmsStockingQueryForm')"><img
				align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" /></a>
			&nbsp;~&nbsp; 
					<html:text property="enddate" size="8" /> 
				<a onclick="event.cancelBubble=true;"
				href="javascript:showCalendar('dimg2',false,'enddate',null,null,'wmsStockingQueryForm')"><img
				align="absmiddle" border="0" id="dimg2" src="images/datebtn.gif" /></a>
			</td>
			<td class="bluetext"><bean:message key="wmsStocking.enabled" />:</td>
			<td>
	    	<html:select property="status">
	    	<html:option value=""><bean:message key="all.all"/></html:option>
	      	<c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><html:options collection = "X_ENABLEDDISABLEDLIST" property = "enumCode" labelProperty = "engShortDescription"/></c:if>
	     	<c:if test="${sessionScope.LOGIN_USER.locale!='en'}"><html:options collection = "X_ENABLEDDISABLEDLIST" property = "enumCode" labelProperty = "chnShortDescription"/></c:if>
	   	 	</html:select>
	 	 	</td></tr>
	  		<tr>
			<td align="left">
				<html:submit><bean:message key="all.query"/></html:submit>
			</td>
		</tr>
	</table>
</html:form>
<hr />
<page:form action="/listWmsStockingReport.do" method="post">
	<jsp:include page="../pageHead.jsp"/>
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="15%"><page:order order="id" style="text-decoration:none">
					<bean:message key="wmsStocking.id" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>
					<bean:message key="wmsStocking.name" />
					</th>
					<th>
					<bean:message key="wmsStocking.createDate" />
					</th>
					<th>
					<bean:message key="wmsStocking.createUser" />
					</th>
					<th>
					<bean:message key="wmsStocking.count" />
					</th>
					<th> 状态 </th>
					<th width="10%">操作</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="rowReport.jsp" />
			</logic:iterate>
		</tbody>
	</table>
	<jsp:include page="../pageTail.jsp"/>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>


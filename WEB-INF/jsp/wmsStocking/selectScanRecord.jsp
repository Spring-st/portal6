<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script language="javascript" src="includes/basicJS.js"></script>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>

<script type="text/javascript">

</script>
<html:form action="/selectScanRecord.do">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="ssr.location" desc="库位"/>
	<input type="hidden" name="fields" value="ssr.lotSer" desc="条码编号"/>
	
	<input type="hidden" name="id" value="${x_wmsStocking.id}" />
	<html:hidden property="order" />
	<html:hidden property="descend" />
		<table width="90%">
		<tr>
			<td>
				<html:errors />
			</td>
		</tr>
		<tr>
			<td>
				<div class="message">
					<html:messages id="x_message" message="true">
						${x_message}<br>
					</html:messages>
				</div>
			</td>
		</tr>
	</table>
    <table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="selectScanRecord.do" method="post">
<input type="hidden" name="id" value="${x_wmsStocking.id}" />
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
					<th>库位</th>
					<th>条码编号</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="selectScanRecordRow.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>
<table border="0" width="100%">
	<tr>
		<td width="20%"><input type="button" value="返回" onclick="history.go(-1);"/>
			<%--<input type="button" value="返回" onclick="goTo();"/>--%>
		</td>
	</tr>
</table>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>


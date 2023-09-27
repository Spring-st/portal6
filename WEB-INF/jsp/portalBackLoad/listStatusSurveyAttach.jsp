<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
	function add() {
		var url="newPortalStatusSurveyAttach.do";
		
		window.location.href=url;
	}
	
	function edit(id) {
		var url = "editPortalStatusSurveyAttach.do?id=" + id;
		window.location.href = url;
	}

	function view(id) {
		var url = "viewPortalStatusSurveyAttach.do?id=" + id;
		window.location.href = url;
	}
</script>
<%--<html:form action="insertPortalStatusSurveyAttach.do" enctype="multipart/form-data">
	
	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
				<td class="bluetext" width="25%">
					<bean:message key="portalStatusSurvey.Asn.statusSurveyAttach" />:
				</td>
				<td>
					<html:file property="fileContent"></html:file>
				</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input type="submit" value="<bean:message key="Upload" />" />
			</td>
		</tr>
		<tr>
			<Td colspan="2"><hr/></Td>
		</tr>
	</table>

</html:form>
--%><html:form action="listPortalStatusSurveyAttach">
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	<tr>
		<td class="bluetext">
			<bean:message key="start.time" />:
		</td>
		<td class="bluetext">
			<html:text property="createDate1" size="15" />
			<a onclick="event.cancelBubble=true;"
			href="javascript:showCalendar('dimg1',false,'createDate1',null,null,'portalStatusSurveyAttachQueryForm')"><img
			align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" />
			</a>
	</td>
	<td class="bluetext">
			<bean:message key="end.time" />:
	</td>
	<td class="bluetext">
		<html:text property="createDate2" size="15" />
		<a onclick="event.cancelBubble=true;"
		href="javascript:showCalendar('dimg2',false,'createDate2',null,null,'portalStatusSurveyAttachQueryForm')"><img
		align="absmiddle" border="0" id="dimg2"
		src="images/datebtn.gif" />
		</a>
		</td>
	</tr>
<tr>
	<td colspan="2"></td>
	<td align="right">
		<input type="submit" value="<bean:message key="all.query"/>" />
	</td>
	<td>
		<input type="button" value="<bean:message key="all.new"/>" onclick="add()"/>
	</td>
	</tr>
	<tr>
		<td colspan="4"><hr/></td>
	</tr>
	</table>
</html:form>
<page:form action="listPortalStatusSurveyAttach.do" method="post">

	<jsp:include page="../pageHead.jsp"/>
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="30%">
					<bean:message key="psoa.fileName" />
				</th>
				<th width="25%">
					<bean:message key="psoa.creater" />
				</th>
				<th width="25%">
					<bean:message key="psoa.uploadDate" />
				</th>
				<th width="25%">
					Status
				</th>
			</tr>
		</thead>

		<tbody id="datatable">
								<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
									<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT"/>
									<jsp:include page="statusSurveyAttachRow.jsp" />
								</logic:iterate>
							</tbody>
	</table>

</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>




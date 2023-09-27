<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>

<html:form action="/updatePortalStatusSurveyAttach" method="post">
<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td  class="bluetext" width="20%">
					<bean:message key="supplier.code" />
				</td>
				<td width="20%">
					${X_SUPPLIER.code}
				</td>
				<td class="bluetext" width="20%">
					<bean:message key="supplier.name" />
				</td>
				<td width="20%">
					${X_SUPPLIER.name}
				</td></tr><tr>
				<td class="bluetext" width="20%">
					<bean:message key="supplier.date" />
				</td>
				<td width="20%">
					<c:if test="${X_DATE != null }">
						<bean:write name="X_OBJECT" property="uploadDate" format="yyyy/MM/dd" />
						</c:if>
				</td></tr>
				<tr>
				<td class="bluetext">
					<bean:message key="supplier.mark" />
				</td>
				<td>
				<c:if test="${X_OBJECT.mark != null }">
				<td colspan=4><html:textarea property="mark" rows="3" cols="60" value="${X_OBJECT.mark}"></html:textarea></td>
				
				</c:if>
				<c:if test="${X_OBJECT.mark == null }">				
				<td colspan=4><html:textarea property="mark" rows="3" cols="60" value=""></html:textarea></td>
				</c:if>
				</td>
				
				 
		</tr>
	</table>
	<table width=90% border=0 cellpadding=4 cellspacing=0>

		<tr>
			<td><input type="button" value="<bean:message key="portalStatusSurvey.Asn.statusSurveyAttach" />" onclick="addAttachment();"/>
			</td> 
		</tr>
	</table>
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
				<%--<th width="25%">
					<bean:message key="all.operate" />
				</th>
			--%></tr>
		</thead>

		<tbody id="datatable">
								<%--<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
									<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT"/>
									<jsp:include page="statusSurveyAttachRow.jsp" />
								</logic:iterate>
		--%></tbody>
	</table>
	<input type="hidden" name="draft" />
	<script type="text/javascript">
function addAttachment() {
		var url='newStatusSurveyAttach.do';
		window.location.href = url;
	}

</script>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
	<hr />
	<table>


		<tr>

			<td align="right">
				<%--<input type="button"
					value="<bean:message key="StatusSurveyAttach.saveAsDraft"/>"
					onclick="saveAsDraft()" />
				<input type="button" value="<bean:message key="all.submit" />"
					onclick="saveAsSubmit()" />
				--%><input type="button" value="<bean:message key="all.back"/>"
					onclick="history.go(-1);">
			</td>
		</tr>
	</table>
</html:form>
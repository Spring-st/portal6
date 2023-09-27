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
				</td>
				<td class="bluetext"  width="20%">
					<bean:message key="supplier.mark" />
				</td>
				<td width="20%">
				<c:if test="${X_OBJECT.mark != null }">
				<input type="text" name="mark" value="${X_OBJECT.mark}"/>
				
				
				</c:if>
				<c:if test="${X_OBJECT.mark == null }">				
				<input type="text" name="mark" value=""/>
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
				<th width="20%">
					<bean:message key="psoa.fileName" />
				</th>
				<th width="20%">
					<bean:message key="psoa.creater" />
				</th>
				<th width="20%">
					<bean:message key="psoa.uploadDate" />
				</th>
				<th width="20%">
					<bean:message key="psoa.fileSize" />
				</th>
				<th width="20%">
					<bean:message key="all.operate" />
				</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<tr id="r${X_OBJECT.id}">
				<td width="20%" align="center">
						${X_OBJECT.fileName}
				</td>
					<td width="20%">
						${X_OBJECT.createUser.name}
					</td>
					<td width="20%">
						<c:if test="${X_OBJECT.uploadDate!= null }">
						<bean:write name="X_OBJECT" property="uploadDate" format="yyyy/MM/dd" />
						</c:if>
					</td>
					<td width="20%">
						${X_OBJECT.fileSize}
					</td>
					<td width="20%">
						<c:if test="${X_OBJECT.status.enumCode==0}"></c:if>
						<a href="deletePortalStatusSurveyAttach.do?id=${X_OBJECT.id}">删除</a>
					</td>
			</tr>
		</tbody>
	</table>
	<input type="hidden" name="draft" />
	<input type="hidden" name="site_id"  value="${X_OBJECT.site.id}"/>
	<input type="hidden" name="id" value="${X_OBJECT.id}"/>
	<script type="text/javascript">


function saveAsDraft(id) {
	document.getElementById("draft").value="true";
	portalStatusSurveyAttachForm.submit();
}

function saveAsSubmit(id) {
	document.getElementById("draft").value="false";
	portalStatusSurveyAttachForm.submit();

}
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
				<input type="button"
					value="<bean:message key="StatusSurveyAttach.saveAsDraft"/>"
					onclick="saveAsDraft(${X_OBJECT.id})" />
				<input type="button" value="<bean:message key="all.submit" />"
					onclick="saveAsSubmit(${X_OBJECT.id})" />
				<input type="button" value="<bean:message key="all.back"/>"
					onclick="history.go(-1);">
			</td>
		</tr>
	</table>
</html:form>
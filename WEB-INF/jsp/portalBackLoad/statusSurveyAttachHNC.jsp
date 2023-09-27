<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">

</script>

<html:form action="listPortalStatusSurveyAttachHNC">
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
		<td class="bluetext">
			<bean:message key="psoa.Site" />:
		</td>
		<td>
			<html:select property="site">
	    		<html:option value=""><bean:message key="all.all"/></html:option>
		        <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><html:options collection = "x_siteList" property = "id" labelProperty="name"/></c:if>
		        <c:if test="${sessionScope.LOGIN_USER.locale!='en'}"><html:options collection = "x_siteList" property = "id" labelProperty="name"/></c:if>
		    </html:select>
		</td>
	</tr>
<tr>
	<td></td>
	<td align="right">
		<input type="submit" value="<bean:message key="all.query"/>" />
	</td>
											
		<td colspan="2"></td>
	</tr>
	<tr>
		<td colspan="4"><hr/></td>
	</tr>
	</table>
</html:form>
<page:form action="listPortalStatusSurveyAttachHNC.do" method="post">

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
									<jsp:include page="statusSurveyAttachHNCRow.jsp" />
								</logic:iterate>
							</tbody>
	</table>

</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>




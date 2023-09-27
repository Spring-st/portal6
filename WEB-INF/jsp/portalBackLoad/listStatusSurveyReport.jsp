<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
</script>
<html:form action="listPortalStatusSurveyReport">
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	<tr>
		<td class="bluetext">
			YEAR:
		</td>
		<td class="bluetext" colspan="3">
			<html:select property="year" >
				<html:option value="2014" >2014</html:option>
				<html:option value="2015" >2015</html:option>
				<html:option value="2016" >2016</html:option>
			</html:select>
		</td>
	</tr>
<tr>
	<td colspan="2"></td>
	<td align="right">
		<input type="submit" value="<bean:message key="all.query"/>" />
	</td>
	<td>
		
	</td>
	</tr>
	<tr>
		<td colspan="4"><hr/></td>
	</tr>
	</table>
</html:form>
<page:form action="listPortalStatusSurveyReport.do" method="post">

	<jsp:include page="../pageHead.jsp"/>
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="10%">
					<bean:message key="pssr.id" />
				</th>
				<th width="25%">
					<bean:message key="pssr.supplierName" />
				</th>
				<th>
					1<bean:message key="pssr.month" />
				</th>
				<th>
					2<bean:message key="pssr.month" />
				</th>
				<th>
					3<bean:message key="pssr.month" />
				</th>
				<th>
					4<bean:message key="pssr.month" />
				</th>
				<th>
					5<bean:message key="pssr.month" />
				</th>
				<th>
					6<bean:message key="pssr.month" />
				</th>
				<th>
					7<bean:message key="pssr.month" />
				</th>
				<th>
					8<bean:message key="pssr.month" />
				</th>
				<th>
					9<bean:message key="pssr.month" />
				</th>
				<th>
					10<bean:message key="pssr.month" />
				</th>
				<th>
					11<bean:message key="pssr.month" />
				</th>
				<th>
					12<bean:message key="pssr.month" />
				</th>
			</tr>
		</thead>

		<tbody id="datatable">
								<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
									<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT"/>
									<jsp:include page="statusSurveyReportRow.jsp" />
								</logic:iterate>
							</tbody>
	</table>

</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>




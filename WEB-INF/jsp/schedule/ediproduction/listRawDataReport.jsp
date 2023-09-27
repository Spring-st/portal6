<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<html:form action="/listEdiProductionRawDataReport.do">
<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="epr.asn_no" desc="ALC"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<hr />
<page:form action="/listEdiProductionRawDataReport.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th>序号</th>
				<th	><page:order order="id"
						style="text-decoration:none">
						<bean:message key="ediproduction.asnno" />
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order></th>
				<th>任务日期</th>
				<th>汇总</th>
				<th>06~07</th>
				<th>08~09</th>
				<th>10~11</th>
				<th>12~13</th>
				<th>14~15</th>
				<th>16~17</th>
				<th>18~19</th>
				<th>20~21</th>
				<th>22~23</th>
				<th>次日00~01</th>
				<th>次日02~03</th>
				<th>次日04~05</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<c:forEach items="${X_RESULTLIST }" var="X_OBJECT"
				varStatus="indexAt">
				<tr align="center">
					<td>${indexAt.index+1 }</td>
					<td>
						${X_OBJECT.asn_no}
					</td>
					<td>${X_OBJECT.task_date }</td>
					<td>${X_OBJECT.quty }</td>
					<td>${X_OBJECT.a }</td>
					<td>${X_OBJECT.b }</td>
					<td>${X_OBJECT.c }</td>
					<td>${X_OBJECT.d }</td>
					<td>${X_OBJECT.e }</td>
					<td>${X_OBJECT.f }</td>
					<td>${X_OBJECT.g }</td>
					<td>${X_OBJECT.h }</td>
					<td>${X_OBJECT.i }</td>
					<td>${X_OBJECT.j }</td>
					<td>${X_OBJECT.k }</td>
					<td>${X_OBJECT.l }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
	applyRowStyle(document.all('datatable'));
</script>


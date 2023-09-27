<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js">
</script>
<script type="text/javascript">
<!--
 
//-->
</script>
<html:form action="/productGolineNotQAD.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择" />
	<input type="hidden" name="fields" value="pg.xxsh_worc_item" desc="赛赫条码" />
	<input type="hidden" name="fields" value="pg.xxsh_worc_cr_date" desc="创建日期" />
	<input type="hidden" name="fields" value="pg.xxsh_worc_up_date" desc="同步时间" />

	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType" />

	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td colspan="4"><jsp:include page="../../simQuery.jsp" /></td>
		</tr>
	</table>
</html:form>
<page:form action="/productGolineNotQAD.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="25%">
					赛赫条码
				</th>
				<th width="10%">
					数量
				</th>
				<th width="20%">
					创建日期
				</th>
				<th width="20%">
					同步时间
				</th>
				<th width="20%">
					备注
				</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="row_ProductGolineNotQAD.jsp" />
			</logic:iterate>
		</tbody>
	</table>
	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td align="right"">
				<input type="button" value="<bean:message key="all.back"/>" onclick="window.location.href='listProductGoline.do'">
			</td>
		</tr>
	</table>
</page:form>
<script type="text/javascript">
applyRowStyle(document.all('datatable'));
</script>


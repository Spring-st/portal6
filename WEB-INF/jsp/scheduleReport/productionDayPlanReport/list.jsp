<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
	
</script>
<html:form action="/listProductionDayPlan.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择" />
	<input type="hidden" name="fields" value="entity.carkind" desc="车种" />
	<input type="hidden" name="fields" value="entity.asnNo" desc="值" />


	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType" />

	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td colspan="4"><jsp:include page="../../simQuery.jsp" /></td>
		</tr>
	</table>
	<div id="yellow" style="background:#FFFF00;width: 20%;display:none" ><font size="3" id="iii">版本号:${X_RESULTLIST[0].version}</font></div>
	<div id="wu" style="width: 20%;display:none" ><font id="uuu" size="3">版本号:${X_RESULTLIST[0].version}</font></div>
	
</html:form>
<page:form action="/listProductionDayPlan.do" method="post">
	<div style="width: 2000px;">
		<!--表头-->
		<div id="mytable" style="width: 100%; padding-right: 17px;">
			<table class="data" width="2000px"  word-break="keep-all;">
				<thead>
					<tr bgcolor="#9999ff">
						<th nowrap="nowrap"><page:order order="partId"
								style="text-decoration:none">
					车种
					<page:desc>
									<img src="images/down.gif" border="0" />
								</page:desc>
								<page:asc>
									<img src="images/up.gif" border="0" />
								</page:asc>
							</page:order></th>
						<th nowrap="nowrap" width="29px">值</th>
						<th nowrap="nowrap" width="34px">M-1</th>
						<th nowrap="nowrap" width="35px">当月</th>
						<th nowrap="nowrap" width="26px">D-1</th>
						<th nowrap="nowrap">PBS</th>
						<th nowrap="nowrap">PRJ</th>
						<th nowrap="nowrap">WBS</th>
						<th nowrap="nowrap">${X_DD}SUM</th>
						<c:forEach var="dateStr" items="${X_DATELIST}">
							<th nowrap="nowrap" width="50px">${dateStr}</th>
						</c:forEach>
						<th nowrap="nowrap">REM</th>
						<th nowrap="nowrap">合计查询</th>
						<th nowrap="nowrap">未投</th>
						<th nowrap="nowrap">LC(O)</th>
						<th nowrap="nowrap">LC(X)</th>
						<th nowrap="nowrap">总计</th>
					</tr>

				</thead>
			</table>
		</div>
		<div id="container"
			style="width: 2000px; overflow-y: scroll; height: 390px;">
			<table width="2850px">
				<tbody id="datatable">
					<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
						<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
						<jsp:include page="row.jsp" />
					</logic:iterate>
				</tbody>
			</table>
		</div>
	</div>
</page:form>

<script type="text/javascript">
	applyRowStyle(document.all('datatable'));
</script>
<script type="text/javascript">
	var now = new Date();
    var currentdate =now.getYear()+""+((now.getMonth()+1)<10?"0":"")+(now.getMonth()+1)+""+(now.getDate()<10?"0":"")+now.getDate()
	if(currentdate==${fn:substring(X_RESULTLIST[0].version,0,fn:length(X_RESULTLIST[0].version)-5)}){
		document.write(currentdate);
		var element = document.getElementById("yellow").style.display="";
 		element.style.display ="";	
	}else{
		var element = document.getElementById("wu").style.display="";
 		element.style.display ="";
	}
	
</script>
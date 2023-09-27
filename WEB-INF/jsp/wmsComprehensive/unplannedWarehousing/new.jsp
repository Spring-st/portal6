<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
	 function selectBadReasons() {
		v = window.showModalDialog(
			'showDialog.do?title=expense.selectTA.title&listUnplannedReasonsSelect.do', 
			null, 'dialogWidth:840px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) {
			 document.getElementById("reasoncode_id").innerHTML=v['code'];
			document.getElementById("reason_code").value=v['id'];
			
			document.getElementById("expenses_course").innerHTML=v['expenses_course'];
			document.getElementById("department_cost").innerHTML=v['department_cost'];
		};
			
	}
</script>
<html:form action="/insertWmsUnplannedWarehousing.do" >
<div align="center"><span style="font-size: 20px; color: blue; padding: 15px 0px;"><strong>新增非计划入库单</strong></span></div>
    	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext">计划入库时间:</td>
			<td>
				<html:text property="date" size="8"></html:text>
				<a onclick="event.cancelBubble=true;"
					href="javascript:showCalendar('dimg1',false,'date',null,null,'wmsUnplannedWarehousingForm')"><img
						align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" />
					</a>
			</td>
		</tr>
		<tr>
			<td class="bluetext">原因代码:
			</td>
			<td>
				<span id="reasoncode_id"></span>
				<a href="javascript:selectBadReasons();"><img src="images/select.gif" border="0"/></a>
				<input type="hidden" id="reason_code" name="reason_code"/>
			</td>
		</tr> 
		<tr>
			<td class="bluetext">费用科目:</td>
			<td id="expenses_course"></td>
		</tr> 
		<tr>
			<td class="bluetext">费用部门:</td>
			<td id="department_cost"></td>
		</tr> 
		<tr>
			<td class="bluetext">入库原因:
			</td>
			<td>
			    <html:textarea property="remark" cols="40" rows="5"/>  
			</td>
		</tr> 
</table>
<hr />
		<table border="0">
			<tr align="center">
				<td colspan="3">
				<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="submit"" value="保存" /></c:if>&nbsp;
                <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="submit" value="保存" /></c:if>&nbsp;
                <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="取消" onclick="window.close();"/></c:if>&nbsp;
                <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="取消" onclick="window.close();"/></c:if>&nbsp;
				</td>
			</tr>
		</table>
</html:form>
<%--<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
--%>
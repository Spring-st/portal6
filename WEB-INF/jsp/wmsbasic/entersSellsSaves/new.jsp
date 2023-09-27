<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">
function selectMouth(val){
	document.getElementById("mouthId").value = val;
}
function save(){
	var mouth = document.getElementById("mouthId").value;
	document.getElementById("saveId").disabled = true;
	window.location.href = "insertReportEntersSellsSaves.do?mouth="+mouth;
}
</script>
<input type="hidden" id="mouthId" />
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext">选择月份:</td>
			<td><select onchange="selectMouth(this.value)">
					<option value="">请选择</option>
				<c:forEach var="x_obj" items="${x_list}">
					<option value="${x_obj.mouth}">${x_obj.mouth}</option>
				</c:forEach>
			</select>
			<span class="required">*</span></td>
		</tr>
	</table>
	
	<hr />
	<div style="margin: 10px 10px;" align="center">
		<input type="button" value="保存" onclick="save()" id="saveId"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="关闭" onclick="window.close();"/>
	</div>

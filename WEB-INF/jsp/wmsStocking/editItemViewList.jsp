<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<div align="center"><span style="font-size: 20px;"><strong>盘库扫描信息</strong></span></div>
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
					<th>#</th>
					<th>条码编号</th>
					<th>库位代码</th>
					<th>物料号</th>
					<th>规格</th>
					<th>数量</th>
					<th>是否扫描</th>
			</tr>
		</thead>
		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="x_boxs" indexId="indexid">
			<bean:define id="index" toScope="request" name="indexid" />
				 <tr>
				 <td>${cF11OrderQueryForm.pageNo*cF11OrderQueryForm.pageSize+index+1 }</td>
				   <td>${X_OBJECT.lot.id}</td>
				   <td>${X_OBJECT.location.code}</td>
				   <td>${X_OBJECT.part.id}</td>
				   <td>${X_OBJECT.part.describe2}</td>
				   <td>${X_OBJECT.number}</td>
				   <td>
				   <c:if test="${X_OBJECT.is_scan=='0'}">
				   <span style="color: green;">YES</span>
				   </c:if>
				    <c:if test="${X_OBJECT.is_scan=='1'}">
				   <span style="color: red;">NO</span>
				   </c:if>
				   </td>
				 </tr>
			</logic:iterate>
		</tbody>
	</table>
<div align="center">
<input type="button" value="<bean:message key="all.close" />" onclick="window.close();"/> 
</div>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>


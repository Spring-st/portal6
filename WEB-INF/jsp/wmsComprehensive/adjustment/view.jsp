<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
<!--

//-->
</script>
	<div style="text-align: center;"><h3 style="color:blue"></h3></div>
	<table class="data" width="100%">
		<thead>
			<tr bgcolor="#9999ff">
				<th>条码编号</th>
				<th>物料号</th>
				<th>描述一</th>
				<th>描述二</th>
				<th>数量</th>
			</tr>
		</thead>
		<tbody id="datatable" >
			<c:forEach var="x_obj" items="${x_boxs}">
				<tr>
					<td>${x_obj.box_id.lot.id}</td>
					<td>${x_obj.box_id.part.id}</td>
					<td>${x_obj.box_id.part.describe1}</td>
					<td>${x_obj.box_id.part.describe2}</td>
					<td>${x_obj.box_id.number}</td>
				</tr>
			</c:forEach>	
		</tbody>
	</table>
	
	<div align="center"> 
   	<input type="button" value="关闭" onclick="window.close();"/>
    </div>  

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">
<!--
	function itemview(srid) {
		var site="${x_wmsStocking.site.id}"
		v = window.showModalDialog(
			'showDialog.do?title=WmsStocking.view.title&listWmsStockingItem.do?site='+site+"&storeroomId="+srid+"&stocking=${x_wmsStocking.id }", 
			null, 'dialogWidth:700px;dialogHeight:680px;status:no;help:no;scroll:no');
	}
    function confirmEnd(){
    	if(confirm('<bean:message key="wmsStocking.confirmEnd" />')){
			var form = document.wmsStockingForm;
			    form.action = "updateWmsStocking.do";
			    form.submit();
		}
    }
    function exportEXCELByPart(){
	  var url = "exportWmsStockingReportByPartEXCEL.do?id=${x_stocking.id}";
   	  window.location.href = url;
   }
    function exportEXCELByLocation(){
	  var url = "exportWmsStockingReportByLocationEXCEL.do?id=${x_stocking.id}";
   	  window.location.href = url;
   }
//-->
</script> 
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext">
				<bean:message key="travelApplication.site" />&nbsp;
			</td>
			<td>${x_stocking.site.name}</td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="wmsStocking.createDate" />:</td>
			<td>${x_stocking.createDate}</td>
		</tr>
		
		<tr>
			<td class="bluetext"><bean:message key="wmsStocking.createUser" />:</td>
			<td>${x_stocking.createUser.name}</td>
		</tr>
	</table>
	<hr />
	<h3 style="color:blue">物料差异汇总列表</h3> 
	<table width="100%" class="data">
				<thead>
					<tr bgcolor="#9999ff">
						<th>物料代码</th>
						<th>描述</th>
						<th>规格</th>
						<th>计划数量</th>
						<th>实际数量</th>
						<th>差异</th>
					</tr>
				</thead>
				<tbody id="datatable">
					<c:forEach var="x_object" items="${x_datePartList}">
					<tr>
					   <td>${x_object.wmsPart.id}</td>
					   <td>${x_object.wmsPart.describe1}</td>
					   <td>${x_object.wmsPart.describe2}</td>
					   <td>${x_object.amount}</td>
					   <td>${x_object.stockingAmount}</td>
					   <td>${x_object.difference}</td>
					</tr>
					</c:forEach>
				</tbody>	
	</table>
	<span class="bluetext">导出物料差异汇总列表</span>：<img src="images/excel.gif" border=0/>&nbsp;<a href="#" onclick="exportEXCELByPart();">EXCEL</a>
	<h3 style="color:blue">库位差异汇总列表</h3> 
	<table width="100%" class="data">
				<thead>
					<tr bgcolor="#9999ff">
						<th>库位代码</th>
						<th>物料代码</th>
						<th>描述</th>
						<th>规格</th>
						<th>计划数量</th>
						<th>实际数量</th>
						<th>差异</th>
					</tr>
				</thead>
				<tbody id="datatable1">
					<c:forEach var="x_object" items="${x_dataLocationList}">
					<tr>
					   <td>${x_object.location}</td>
					   <td>${x_object.wmsPart.id}</td>
					   <td>${x_object.wmsPart.describe1}</td>
					   <td>${x_object.wmsPart.describe2}</td>
					   <td>${x_object.amount}</td>
					   <td>${x_object.stockingAmount}</td>
					   <td>${x_object.difference}</td>
					</tr>
					</c:forEach>
				</tbody>	
	</table>
	<span class="bluetext">导出库位差异汇总列表</span>：<img src="images/excel.gif" border=0/>&nbsp;<a href="#" onclick="exportEXCELByLocation();">EXCEL</a>
	<div align="center">
	<input type="button" value="关闭" onclick="window.close();"/> 
	</div> 
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
<script type="text/javascript">
    applyRowStyle(document.all('datatable1'));
</script>
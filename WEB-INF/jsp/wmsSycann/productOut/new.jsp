<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script type="text/javascript" src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
function select(){
	var val = document.getElementById("sel").value;
	if(val ==0){
		 v = window.showModalDialog(
					'showDialog.do?title=purchaseOrderRqcDetermine.title&listProductBelowLine.do?type=0', 
					null, 'dialogWidth:700px;dialogHeight:600px;status:no;help:no;scroll:no');
				if (v) {
					var trString = "<tr class='aboveRow'>";   
			        trString += "<td id='partId'>"+v['partId']+"</td>";
			        trString += "<td>"+v['desc1']+"</td>";
			        trString += "<td>"+v['desc2']+"</td>";
	                trString += "<td id='location'>"+v['location']+"</td>";
	                trString += "<td id='qty'>"+v['qty']+"</td>"; 
	                trString+="<td>"+v['user']+"</td>";
	                trString+="<td id='date'>"+v['date']+"</td>";
	                trString +="<td style='display: none;'><input type='hidden' value='"+v['id']+"' id='lineId'/></td>";
	                trString +="<td style='display: none;'.;/><input type='hidden' value='"+v['type']+"' id='type'/></td>";
	                trString+="</tr>"
	                $("#datatable").append(trString).show();
	                 appStyle();
			};
		
	}else if(val ==1){
		 v = window.showModalDialog(
					'showDialog.do?title=purchaseOrderRqcDetermine.title&listProductBelowLine.do?type=1', 
					null, 'dialogWidth:700px;dialogHeight:600px;status:no;help:no;scroll:no');
				if (v) {
					var trString = "<tr class='aboveRow'>";   
			        trString += "<td id='partId'>"+v['partId']+"</td>";
			        trString += "<td>"+v['desc1']+"</td>";
			        trString += "<td>"+v['desc2']+"</td>";
	                trString += "<td id='location'>"+v['location']+"</td>";
	                trString += "<td id='qty'>"+v['qty']+"</td>"; 
	                trString+="<td>"+v['user']+"</td>";
	                trString+="<td id='date'>"+v['date']+"</td>";
	                trString +="<td style='display: none;'><input type='hidden' value='"+v['id']+"' id='lineId'/></td>"
	                trString +="<td style='display: none;'><input type='hidden' value='"+v['type']+"' id='type'/></td>"
	                trString+="</tr>"
	                
	                $("#datatable").append(trString).show();
	                 appStyle();
			};
		}
}

function createProductOut(){
	var partId = document.getElementById("partId").innerHTML;
	var location = document.getElementById("location").innerHTML;
	var date1 = document.getElementById("date").innerHTML;
	var qty = document.getElementById("qty").innerHTML;
	var lineId = document.getElementById("lineId").value;
	var type =document.getElementById("type").value;
	
	var str = partId+","+location+","+date1+","+qty+","+lineId;
	window.location.href="createProductOut.do?str="+str+"&type="+type;
}
function appStyle(){
	applyRowStyle(document.all('datatable'));
}
</script>
<html:form action="listProductOffline.do">
	<table>
		<tr>
			<td>请选择出库库位：</td>
			<td>
				<select onchange="select()" id ="sel">
					<option value="">请选择</option>
					<option value="0">CCP-00A</option>
					<option value="1">其他</option>
				</select>
			</td>		
		</tr>
	</table>

</html:form>

<page:form action="/listProductOffline.do" method="post">
	<table class="data">
		<thead>
		<tr bgcolor="#9999ff" align="center">
			<th>物料号</th>	
			<th>描述一</th>
			<th>描述二</th>
			<th>库位</th>
			<th>出库数量</th>
			<th>操作人</th>
			<th>时间</th>
		</tr>
		</thead>
		<tbody id="datatable">
			
		</tbody>
	 </table>
		
		<table>
			<tr>
				<td><input type ="button" onclick="createProductOut()" value="确认出库"></td>
			</tr>
		</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

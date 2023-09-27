<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script src="includes/jquery-1.3.2.js"></script>
<script src="includes/basicJS.js"></script>
<script type="text/javascript">
<!--
    function itemview(srid) {
		v = window.showModalDialog(
			'showDialog.do?title=WmsStocking.new.title&listWmsStockingInventory.do?storeroomId='+srid, 
			null, 'dialogWidth:700px;dialogHeight:680px;status:no;help:no;scroll:no');
	}
	function startStockingAll(id){//开始盘库
	    var r = confirm('<bean:message key="wmsStocking.prompt" />');
	    if(r){
			window.location.href = "startStockingAll.do?id="+id;
		}
	}
	function selectLocation() {
		v = window.showModalDialog(
			'showDialog.do?title=WmsStocking.view.title&selectStorageLocationType.do?type=1', 
			null, 'dialogWidth:740px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) {
			//var resultList = v; 
			//for(i = 0;i<resultList.length; i++){
			//	var v=resultList[i];
					if(v['str']!=null){
						$.ajax({         
               			type:"POST", //请求方式        
                		url:"selectLocationById.do", //请求路径        
                		cache: false,           
                		data:"ids="+v['str'],  //传参        
                		dataType: 'json',   //返回值类型        
                		success:function(date){
                		for(i=0;i<date.length;i++){
						var code = date[i].code;
						var id = date[i].id;
						var address = date[i].address;
						var describe = date[i].describe;
						var name = date[i].name;
						var i ;
						var s=v[i];
						var trString = "<tr class='aboveRow'>";   
						trString += "<td style='display: none;'><input type=\"checkbox\" checked=\"checked\" value="+(id)+" name=\"ids\" onclick='productbox_click()'/></td>";
		        		trString += "<td align='center'>"+(code)+"</td>";
		        		trString += "<td align='center'>"+(address)+"</td>";
		        		trString += "<td align='center'>"+(name)+"</td>";
		        		trString += "<td align='center'>"+(describe)+"</td>";
		        
		        		trString += "<td><a href=\"javascript:void(0)\" onclick='detailDeleteRow(this)'>删除</a></td>"
		        		trString += "</tr>"; 
		        		$("#datatable").append(trString).show();
		         		reloadDatatable();
				  		}
			   		} 
            		})
					}else{
						var trString = "<tr class='aboveRow'>";   
						trString += "<td style='display: none;'><input type=\"checkbox\" checked=\"checked\" value="+(v['code'])+" name=\"ids\" onclick='productbox_click()'/></td>";
		        		trString += "<td align='center'>"+(v['code'])+"</td>";
		        		trString += "<td align='center'>"+(v['address'])+"</td>";
		        		trString += "<td align='center'>"+(v['name'])+"</td>";
		        		trString += "<td align='center'>"+(v['describe'])+"</td>";
		        
		        		trString += "<td><a href=\"javascript:void(0)\" onclick='detailDeleteRow(this)'>删除</a></td>"
		        		trString += "</tr>"; 
		        		$("#datatable").append(trString).show();
		        		reloadDatatable();
				}
			//}
		};
	}
	function selectLocationAll(){
		$.ajax({         
        	type:"POST", //请求方式        
            url:"selectLocationByIdAll.do", //请求路径        
            cache: false,           
            //data:"ids="+v['str'],  //传参        
            dataType: 'json',   //返回值类型        
            success:function(date){
            for(i=0;i<date.length;i++){
            	//alert(date.length);
				var code = date[i].code;
				var id = date[i].id;
				var address = date[i].address;
				var describe = date[i].describe;
				var name = date[i].name;
				var trString = "<tr class='aboveRow'>";   
				trString += "<td style='display: none;'><input type=\"checkbox\" checked=\"checked\" value="+(id)+" name=\"ids\" onclick='productbox_click()'/></td>";
		   		trString += "<td align='center'>"+(code)+"</td>";
		    	trString += "<td align='center'>"+(address)+"</td>";
		    	trString += "<td align='center'>"+(name)+"</td>";
		    	trString += "<td align='center'>"+(describe)+"</td>";
		        
		     	trString += "<td><a href=\"javascript:void(0)\" onclick='detailDeleteRow(this)'>删除</a></td>"
		     	trString += "</tr>"; 
		     	$("#datatable").append(trString).show();
		     	reloadDatatable();
				}
			} 
		})
	}
	function detailByDelete(stockingId,locationId){
		$.ajax({         
        	type:"POST", //请求方式        
            url:"detailDeleteLocation.do", //请求路径        
            cache: false,           
            data:"stockingId="+stockingId+"&locationId="+locationId,  //传参        
            dataType: 'json',   //返回值类型        
            success:function(date){
            	window.location.href="editWmsStockingAll.do?id="+stockingId;
			} 
		})
//		var oldRow=obj.parentNode.parentNode;
//		var ttbody=document.all('datatable');
//		ttbody.deleteRow(oldRow.sectionRowIndex);
	}
	
	function reloadDatatable(){
	 applyRowStyle(document.all('datatable'));
	}
	function windowclose(){
		window.location.href = "listWmsStocking.do";
	}
//-->
</script>
<html:form action="/editWmsStockingAll.do" >
<input type="hidden" id="locationId" name="locationId" />
	<input type="hidden" name="id" value="${x_wmsStocking.id}"/>
	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext" width="20%">库房盘存编号:</td>
			<td>${x_wmsStocking.code}</td>
			<td width="25%"></td>
			<td width="25%"></td>
		</tr>
		<tr>
			<td class="bluetext" width="20%">备注:</td>
			<td>${x_wmsStocking.name}</td>
			<td width="25%"></td>
			<td width="25%"></td>
		</tr>
	</table>
	
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="skd.location.code" desc="库位代码"/>
	<input type="hidden" name="fields" value="skd.location.address" desc="地址"/>
	<input type="hidden" name="fields" value="skd.location.basic_storeroom_id.code" desc="所属库房"/>
	<input type="hidden" name="fields" value="skd.location.description" desc="描述"/>
	
	<input type="hidden" id="ExportType" name="exportType"/>
    <table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/editWmsStockingAll.do">
	<%--<jsp:include page="../pageHead.jsp"/>
		--%><table class="data" width="100%" style="display: inline;">
			<thead>
				<tr bgcolor="#9999ff">
					<th>库位代码</th>
					<th>地址</th>
					<th>所属库房</th>
					<th>描述</th>
					<th>操作</th>
				</tr>
			</thead>	
			<tbody id="datatable">
				<c:forEach items="${X_RESULTLIST}" var="X_OBJECT">
					<tr align="center" >
						<td>${X_OBJECT.location.code}</td>
						<td>${X_OBJECT.location.address}</td>
						<td>${X_OBJECT.location.basic_storeroom_id.code}</td>
						<td>${X_OBJECT.location.description}</td>
						<td><a href="javascript:void(0)" onclick="detailByDelete(${x_wmsStocking.id},${X_OBJECT.location.id});">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	<div align="center">
			<input type="button" value="返回" onclick="windowclose();"  />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="<bean:message key="wmsStocking.start" />" onclick="startStockingAll('${x_wmsStocking.id}')"/>
	</div>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
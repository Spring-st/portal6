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
	var type=0;
    function itemview(srid) {
		v = window.showModalDialog(
			'showDialog.do?title=WmsStocking.new.title&listWmsStockingInventory.do?storeroomId='+srid, 
			null, 'dialogWidth:700px;dialogHeight:680px;status:no;help:no;scroll:no');
	}
	function startStocking(){//开始盘库
	
		var str = "";
	    var idArr = document.getElementsByName('ids');
	    var r = confirm('<bean:message key="wmsStocking.prompt" />');
	    if(r){
	    	// for(var i = 0; i<idArr.length; i++){
	    	//	if(idArr[i].checked == true){
	    	//		str = str+idArr[i].value+",";
	    	//	}
	   	 //	}
	  	  //	$('#locationId').attr("value", str);
	  	  $('#type').attr("value", type);
			document.wmsStockingForm.submit();
		}
	}
	function selectLocation() {
		type=1;
		$("#datatable2").empty()
		document.getElementById("data2").style.display='none';
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
		        
		        		trString += "<td align='center'><a href=\"javascript:void(0)\" onclick='detailDeleteRow(this)'>删除</a></td>"
		        		trString += "</tr>"; 
		        		document.getElementById("data1").style.display='block';
		        		$("#datatable1").append(trString).show();
		         		//reloadDatatable();
		         		applyRowStyle(document.all('datatable1'));
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
		        
		        		trString += "<td align='center'><a href=\"javascript:void(0)\" onclick='detailDeleteRow(this)'>删除</a></td>"
		        		trString += "</tr>"; 
		        		document.getElementById("data1").style.display='block';
		        		$("#datatable1").append(trString).show();
		        		//reloadDatatable();
		        		applyRowStyle(document.all('datatable1'));
				}
			//}
		};
	}
	function selectLocationAll(){
		var str="";
		$.ajax({         
        	type:"POST", //请求方式        
            url:"selectLocationByFreeae.do", //请求路径        
            cache: false,           
           // data:"ids="+v['str'],  //传参        
            dataType: 'json',   //返回值类型        
            success:function(date){
            	//alert(date.length);
            	for(i=0;i<date.length;i++){
            		var code=date[i].code;
            		var freeae=date[i].freeae;
            		str+=code+":"+freeae+";"
            	}
            	//alert(str);
            	if(str!=""){
            			//v = window.showModalDialog(
						//	'', 
						//	null, 'dialogWidth:740px;dialogHeight:580px;status:no;help:no;scroll:no');
					var r = confirm(str);
					if(r){
						var name=document.getElementById('name').value;
						window.location.href = "selectLocationByIdAll.do?reamk="+name;
					}else{
						return false;
					}
				}else{
					var name=document.getElementById('name').value;
					window.location.href = "selectLocationByIdAll.do?reamk="+name;
				}
			} 
		})
	}
	function detailDeleteRow(obj){
		var oldRow=obj.parentNode.parentNode;
		//var ttbody=document.all('datatable');
		var ttbody=obj.parentNode.parentNode.parentNode;
		ttbody.deleteRow(oldRow.sectionRowIndex);
	}
	
	function reloadDatatable(){
	 applyRowStyle(document.all('datatable'));
	}
	function windowclose(){
		window.location.href = "listWmsStocking.do";
	}
	function selectWmsPart(){
		type=2;
		$("#datatable1").empty()
		document.getElementById("data1").style.display='none';
		var partStr=""; 
		var part=document.getElementsByName("ids");
		for(var i=0;i<part.length;i++){
			partStr +=part[i].value+",";
		}
		var r = window.showModalDialog(
			'showDialog.do?title=expense.selectTA.title&selectWmsPartStocking.do?partId='+partStr, 
			null, 'dialogWidth:1000px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (r) { 
			var resultList = r; 
			for(i = 0;i<resultList.length; i++){
				var v=resultList[i];
				var trString = "<tr class='aboveRow'>";   
		        trString += "<td align='center'>"+"<input type=\"hidden\" name=\"ids\" value=\""+(v['id'])+"\" />"+v['id']+"</td>";
		        trString += "<td align='center'>"+v['oldCode']+"</td>";
		        trString += "<td align='center'>"+v['name']+"</td>";
		        trString += "<td align='center'>"+v['describe1']+"</td>";
		        trString += "<td align='center'>"+v['describe2']+"</td>";
		        trString += "<td align='center'>"+v['unit']+"</td>";
                trString += "<td align='center'><a href=\"javascript:void(0)\" onclick='detailDeleteRow(this)'><bean:message key='all.delete' /></a></td>";                 
		      trString += "</tr>"; 
		     // alert(trString);
		     document.getElementById("data2").style.display='block';
		     $("#datatable2").append(trString).show();
    		applyRowStyle(document.all('datatable2'));
          }
		};
	}
//-->
</script>
<html:form action="/insertWmsStocking.do" >
<input type="hidden" id="locationId" name="locationId" />
<input type="hidden" id="type" name="type" />
	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext">库房盘存编号:</td>
			<td>(<bean:message key="common.id.generateBySystem" />)</td>
		</tr>
		<tr>
			<td class="bluetext" width="20%">请选择盘库库位:</td>
			<td>
			部分盘库：
			<span id="locationCode"></span><a href="javascript:selectLocation();"><img src="images/select.gif" border="0"/></a>
			<input name="userId" type="hidden" id="userId"/>
			<span style="color: red;">或</span>
			<input type="button" value="全部盘库" onclick="selectLocationAll()"/>
			<span style="color: red;">或</span>
			物料盘点
			<a href="javascript:selectWmsPart();"><img src="images/select.gif" border="0"/></a>
			</td>
			
		</tr>
		<tr>
			<td class="bluetext">备注:</td>
			<td><html:textarea property="name" cols="50" rows="3"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<table width="100%" class="data1" id="data1" style="display: none;">
				<thead>
					<tr bgcolor="#9999ff">
						<th>库位代码</th>
						<th>地址</th>
						<th>所属库房</th>
						<th>描述</th>
						<th>操作</th>
					</tr>
				</thead>	
					<tbody id="datatable1">
					 	
					</tbody>
				</table>
				<table width="100%" class="data" id="data2" style="display: none;">
				<thead>
					<tr bgcolor="#9999ff">
						<th>物料编号</th>
						<th>原厂编号</th>
						<th>物料名称</th>
						<th>描述一</th>
						<th>描述二</th>
						<th><bean:message key="wmsPart.unit" /></th>
						<th>操作</th>
					</tr>
				</thead>	
					<tbody id="datatable2">
					 	
					</tbody>
				</table>
			</td>
		</tr>
	</table>
	
	<div align="center">
			<input type="button" value="关闭" onclick="windowclose();"  />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="重置" onclick="location=location"  />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="<bean:message key="wmsStocking.start" />" onclick="startStocking()"/>
	</div>
</html:form>

<script type="text/javascript"><%--
    applyRowStyle(document.all('datatable1'));
    applyRowStyle(document.all('datatable2'));
--%></script>
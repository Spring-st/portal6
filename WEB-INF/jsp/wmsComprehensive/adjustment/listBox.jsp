<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script language="javascript" src="includes/basicJS.js"></script>
<script type="text/javascript">
<!--
    function breakUp(id){
		  var el = document.getElementsByName('ids');
		  var result = []; 
  		  var str="";
  		  var count=0;
  		  for(var i=0; i<el.length; i++){   
  			 if((el[i].type=="checkbox") && (el[i].checked== true)){
				 if(el[i].id!="checkAll"){
				    str = str + el[i].value+",";
					count++;
				}
  			}   
  		} 
  		  if(count==0){
  			  alert("请选择拆分批次！");
  			  return false;
  		  }  
  		  if(count>1){
			 alert("请单个批次进行拆分！");  			  
  			 return false; 
  		  }
	    var title="proplan.proPlanNum.new";
		var url="newBatchAdjustment.do?array="+str+"&type=0";
		var v=dialogAction(url,title,600,400);
		if (v) {
			alert("拆分成功！");
			window.location.href = 'listBatchAdjustmentByBox.do'
		};
	}

    function merge(){
		var el = document.getElementsByName('ids');
		var result = []; 
  		var str="";
  		var partids="";
  		var locations="";
  		var strs="";
  		var count=0;
  		for(var i=0; i<el.length; i++){   
  			if((el[i].type=="checkbox") && (el[i].checked== true)){
				if(el[i].id!="checkAll"){
					var partid =document.getElementById("partids"+el[i].value).value;
					if(partids==""){
						partids = partid;
					}else{
						if(partids!=partid){
							strs="请选择相同物料的批次!";
						}
					}
					var location =document.getElementById("locationids"+el[i].value).value;
					if(locations==""){
						locations = location;
					}else{
						if(locations!=location){
							if(strs==""){
								strs="请选择相同库位的批次!";
							}else{
								strs="请选择相同物料和相同库位的批次!";
							}
						}
					}
					if(strs!=""){
						alert(strs);
						return false;
					}
				   str = str + el[i].value+",";
					count++;
				}
  			}   
  		} 
  		  if(count==0){
  			  alert("请选择合并批次！");
  			  return false;
  		  }  
  		  if(count == 1){
			 alert("请选择两个以上批次进行合并！");  			  
  			 return false; 
  		  }
    	var title="proplan.proPlanNum.new";
		var url="newBatchAdjustment.do?array="+str+"&type=1";
		var v=dialogAction(url,title,600,400);
		if (v) {
			alert("合并成功！");
			window.location.href = 'listBatchAdjustmentByBox.do'
		};
	}
//-->
</script>
<html:form action="/listBatchAdjustmentByBox.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
 	<input type="hidden" name="fields" value="pb.lot.id" desc="条码编号"/>
	<input type="hidden" name="fields" value="pb.psoItem.poipItem.poip_number.po_number" desc="采购单号"/>
	<input type="hidden" name="fields" value="pb.po_line" desc="行号"/>
	<input type="hidden" name="fields" value="pb.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="pb.part.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="pb.part.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="pb.part.describe1" desc="描述一"/>
	<input type="hidden" name="fields" value="pb.part.describe2" desc="描述二"/>
	<input type="hidden" name="fields" value="pb.number" desc="数量"/>
	<input type="hidden" name="fields" value="pb.location.code" desc="库位"/>
	<input type="hidden" name="fields" value="pb.in_date" desc="入库时间"/>
	<input type="hidden" name="fields" value="pb.out_date" desc="出库时间"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listBatchAdjustmentByBox.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="2" align="center"><input type="checkbox" id="checkAll" onclick="selectAll();"/></th>
				<th>条码编号</th>
				<th>采购单号</th>
				<th>行号</th>
				<th>物料号</th>
				<th>DPI</th>
				<th>原厂编号</th>
				<th>描述一</th>
				<th>描述二</th>
				<th>数量</th>
				<th>库位</th>
				<th>入库时间</th>
				<th>出库时间</th>
				<th>状态</th>
			</tr> 
		</thead>
		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="rowBox.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>


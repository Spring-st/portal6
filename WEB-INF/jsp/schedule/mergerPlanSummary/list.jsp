<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script type="text/javascript">
function combinePlan(){ 
	var r = false;
	var el = document.getElementsByName('ids');
	var len = el.length;   
	var str="";
	var count=0;
	for(var i=0; i<len; i++){   
		if((el[i].type=="checkbox") && (el[i].checked== true)){
			if(el[i].id!="checkAll"){
				str = str + el[i].value+",";
				count++;
			}
		}   
	}
	if(count==0){
		alert('选勾选要合并的计划！');
		return false;
	}
	if(confirm("确认合并吗？")){
		window.location.href = "combineJitProductionPlanAction.do?arrays="+str;
	}	
}
function automaticCombinePlanAll(){ 
	window.location.href = "automaticCombinePlanAllAction.do";
}
function computeCombinePlan(){ 
	var el = document.getElementsByName('ids');
	var len = el.length;   
	var str="";
	for(var i=0; i<len; i++){   
		if((el[i].type=="checkbox") && (el[i].checked== true)){
			if(el[i].id!="checkAll"){
				if(str==""){
					str = str + el[i].value;
				}else{
					str = str +","+ el[i].value;
				}
			}
		}   
	}
	if(str!=""){
		$("#data").html("");
		$.ajax({         
			type:"POST", //请求方式        
			url:"computeCombinePlanByAjax.do", //请求路径        
			cache: false,           
			data:"arrays="+str,  //传参        
			dataType: 'json',   //返回值类型        poipItemList
			success:function(json){
				//alert(json.length);
				for(var i=0;i<json.length;i++){
					var trString = "<tr align='center'>";   
					trString += "<td>"+"<bean:message key="jitproductionplan.childpart.id" />"+":"+"</td>";
					trString += "<td>"+json[i].part+"</td>";
					trString += "<td>"+"<bean:message key="all.sumNumber" />"+":"+"</td>";
					trString += "<td>"+json[i].qty+"</td>";
					trString += "<td>"+"<bean:message key="projectedinventory.currentqty" />"+":"+"</td>";
					trString += "<td>"+json[i].currentQty+"</td>";
					trString += "<td>"+"<bean:message key="wmspart.highQty" />"+":"+"</td>";
					trString += "<td>"+json[i].highQty+"</td>";
					trString += "<td>"+"<bean:message key="wmspart.lowQty" />"+":"+"</td>";
					trString += "<td>"+json[i].lowQty+"</td>";
					trString += "<td>"+"提示"+":"+"</td>";
					if(json[i].remark==""){
						trString += "<td>"+json[i].remark+"</td>";
					}else{
						trString += "<td style=\"\color: red;font-weight: 500;\"\>"+json[i].remark+"</td>";
					}
					trString += "</tr>"; 
		          	$("#data").append(trString).show();
				}
			} 
		})
	}else{
		$("#data").html("");
	}
}
</script>
<html:form action="/listMergerPlanSummary.do">
	 <input type="hidden" name="fields" value="" desc="请选择"/><%--
	 <input type="hidden" name="fields" value="entity.productionId.asnNo" desc="ASN号"/>
	 --%><input type="hidden" name="fields" value="entity.childPart.id" desc="组件物料编码"/>
	<input type="hidden" name="fields" value="entity.childPart.describe1" desc="物料描述"/>
	<input type="hidden" name="fields" value="entity.qty" desc="数量"/>
	<input type="hidden" name="fields" value="entity.childPart.unit" desc="单位"/>
	<input type="hidden" name="fields" value="entity.childPart.productClass" desc="产品类"/>
	<input type="hidden" name="fields" value="entity.date" desc="任务日期"/>
	<!-- <input type="hidden" name="fields" value="entity.hour8DemandQty" desc="06-07"/>
	<input type="hidden" name="fields" value="entity.hour10DemandQty" desc="08-09"/>
	<input type="hidden" name="fields" value="entity.hour12DemandQty" desc="10-11"/>
	<input type="hidden" name="fields" value="entity.hour14DemandQty" desc="12-13"/>
	<input type="hidden" name="fields" value="entity.hour16DemandQty" desc="14-15"/>
	<input type="hidden" name="fields" value="entity.hour18DemandQty" desc="16-17"/>
	<input type="hidden" name="fields" value="entity.hour20DemandQty" desc="18-19"/>
	<input type="hidden" name="fields" value="entity.hour22DemandQty" desc="20-21"/>
	<input type="hidden" name="fields" value="entity.hour24DemandQty" desc="22-23"/>
	<input type="hidden" name="fields" value="entity.hour2DemandQty" desc="次日00-01"/>
	<input type="hidden" name="fields" value="entity.hour4DemandQty" desc="次日02-03"/>
	<input type="hidden" name="fields" value="entity.hour6DemandQty" desc="次日04-05"/> -->
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<hr />
<table id="data">
</table>

<page:form action="/listMergerPlanSummary.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="30px"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th><%--
				<th>
					<page:order order="id" style="text-decoration:none">
						<bean:message key="ediproduction.asnno" />
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				--%><th><bean:message key="jitproductionplan.childpart.id" /></th>
				<th><bean:message key="jitproductionplan.childpart.describe1" /></th>
				<%--<th><bean:message key="ediproduction.qty" /></th>
				--%><th><bean:message key="ediproduction.unit" /></th>
				<th>产品类</th>
				<th>
					<page:order order="currentQty" style="text-decoration:none">
						<bean:message key="projectedinventory.currentqty" />
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				<th>任务日期</th>
				<th>
					<page:order order="hour8DemandQty" style="text-decoration:none">
						06-07
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				<th>
					<page:order order="hour10DemandQty" style="text-decoration:none">
						08-09
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				<th>
					<page:order order="hour12DemandQty" style="text-decoration:none">
						10-11
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				<th>
					<page:order order="hour14DemandQty" style="text-decoration:none">
						12-13
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				<th>
					<page:order order="hour16DemandQty" style="text-decoration:none">
						14-15
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				<th>
					<page:order order="hour18DemandQty" style="text-decoration:none">
						16-18
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				<th>
					<page:order order="hour20DemandQty" style="text-decoration:none">
						18-19
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				<th>
					<page:order order="hour22DemandQty" style="text-decoration:none">
						20-21
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				
				<th>
					<page:order order="hour24DemandQty" style="text-decoration:none">
						22-23
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				<th>
					<page:order order="hour2DemandQty" style="text-decoration:none">
						次日00-01
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				<th>
					<page:order order="hour4DemandQty" style="text-decoration:none">
						次日02-03
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				<th>
					<page:order order="hour6DemandQty" style="text-decoration:none">
						次日04-05
						<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="row.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>


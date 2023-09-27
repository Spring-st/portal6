<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<script src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
//该类需要其他页面完成对应对应关系才可生效.例如<input type="hidden" name="fields" value="fpsContainerPlan.c_code" desc="出荷编号"/>
	
var sign = true;
function showSimQuery(){
		var sq=document.getElementById("simQuery").style.display;
		if(sq=='none'){
			document.getElementById("simQuery").style.display="";
		}else{
			document.getElementById("simQuery").style.display="none";
		}
	}
	var id=20;
	<c:forEach items="${simQueryList}" var="sq">
								<c:if test="${sq.isCustom==true}">
									id++;
								</c:if>
							</c:forEach>
	function addQuery(field,condition,value,index){
		sign = false;
		if(index!='' && index != null){
			id=index;
		}
		var fields=document.getElementsByName("fields");
		if(fields=='undefined'){
			alert("查询条件未定义");
		}else{
			var trString="<tr id='q_"+(id)+"' align='center'>";
			 //字段
			// trString+="<td><select name='field["+id+"]' onchange='changeCheck(this.value,"+id+");'>";
			trString+="<td><select name='field["+id+"]' >";
			 for(var i=0; i<fields.length; i++){
				 if(field != '' && field == fields[i].value){
					 trString+="<option selected='selected' value='"+fields[i].value+"'>"+fields[i].attributes.desc.nodeValue+"</option>";
				 }else{
					 trString+="<option value='"+fields[i].value+"'>"+fields[i].attributes.desc.nodeValue+"</option>";
				 }
			 }
			 
			trString+="</select></td>";
			//查询类型
			trString+="<td><select name='fieldCondition["+id+"]'>";
			trString+=addCondition('eq','等于',condition)
			trString+=addCondition('not_eq','不等于',condition)
			trString+=addCondition('begin_like','开始于',condition)
			trString+=addCondition('end_like','结束于',condition)
			trString+=addCondition('include_like','包含',condition)
			trString+=addCondition('not_include_like','不包含',condition)
			//trString+=addCondition('in','在',condition)
			//trString+=addCondition('not_in','不在',condition)
			trString+=addCondition('gt','大于',condition)
			trString+=addCondition('lt','小于',condition)
			trString+=addCondition('gte','大于等于',condition)
			trString+=addCondition('lte','小于等于',condition)
			trString+="</select></td>";
			
			if(value==null){
				trString+="<td><input type='text' name='fieldValue["+id+"]' id='fieldValue_"+id+"'></td>";
				
			}
			else{
				trString+="<td><input type='text' name='fieldValue["+id+"]' id='fieldValues_"+id+"' value='"+value+"' ></td>";
			
			}
			 		
			 trString+="<td><select id='checkStatus_"+id+"' onchange='changeCheckStatus(this.value,"+id+");' style='display: none;'>";
			 	trString+="<option value=''>请选择</option>"
			 	<c:forEach var="x_obj" items="${x_status}">
 				 	trString+="<option value='${x_obj.enumCode }'>${x_obj.chnShortDescription}</option>"	
 				</c:forEach>
			 trString+="</select></td>";
			 
			 trString+="<td><a href='#' onclick='detailDeleteRow(this,"+id+")'><img src='images/cha.png' width='20px;'></a></td>"
			 trString+="<td><a href='#' onclick='addQuery()'><img src='images/dui.jpg' width='20px'></a></td>"
			 
			 trString+="</tr>";
			$("#queryContent").append(trString).show(); 
			 
			 id++;
		}
		
		
	}
	 
	function detailDeleteRow(obj,idVal){
		var object = document.getElementById("fieldValues_"+idVal);
		id--;
		 
		if(object==null)
			$('#subId').click();
		else{
			object.value = ""
			$('#subId').click();
		}
	}
	
	function addCondition(key,value,initkey){
		if(initkey!=""&&initkey==key){
			return "<option selected='selected' value='"+key+"'>"+value+"</option>";
			
		}else{
			return "<option value='"+key+"'>"+value+"</option>";
		}
		
	}
	window.onload = function (){
		if(sign)
			addQuery('','','','');	
	}
	
	function clearAll(){
		var tb = document.getElementById("queryContent");
		var rowNum=tb.rows.length;
		 
		 for(i = 20; i < id; i++){
			var object = document.getElementById("fieldValues_"+i);
			if(object==null)
				$('#subId').click();
			else 
				object.value = "";
		} 
   	 	
   	 	$('#subId').click();
	}
	 
</script>
<div style="margin: 0; padding: 0;">
<jsp:include page="pageHead.jsp"/>
</div>
<div>
<fieldset>
<legend>搜索</legend>
<table width="60%" border="0"  style="float: left;" >
	<tr>
		<td width="10%">
			<a href="javascript:showSimQuery()">
				<img align="absmiddle" border="0" id="dimg1" src="images/search.gif" />
			</a>
		</td>
		<td>
			<table id="simQuery" border="0">
				<tr>
					<td>
						<table id="queryContent" border="0" width="100%">
						<c:if test="${simQueryList != null}">		
								<script type="text/javascript">
									<c:forEach items="${simQueryList}" var="sq">
										addQuery('${sq.field}','${sq.type.name}','${sq.value}',${sq.index});
									</c:forEach>
								</script>
						</c:if>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<table>
<tr>
	<td style="padding: 0px 15px;"><input type="submit" value="查询" id="subId"/></td>
	<td><input type="button" value="全部清空" onclick="clearAll();"/></td>
	<td><c:if test="${x_close}"><input type="button" value="关闭" onclick="window.close();"/></c:if></td>
</tr>
</table>
</fieldset>
</div>

<%--<hr style="color: #ccc; width: 100%; height: 3px;"/>--%>


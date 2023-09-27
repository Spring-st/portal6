<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script type="text/javascript">
function guanbi(){
	this.close();
}
function save(){
  		 var el = document.getElementsByName('ids');
		  var result = []; 
  		  var str="";
  		  var count=0;
  		  var qty=0;
  		  var location_code="";
  		  var id="";
  		  var num=0;
  		  for(var i=0; i<el.length; i++){   
  			 if((el[i].type=="checkbox") && (el[i].checked== true)){
				 if(el[i].id!="checkAll"){
					location_code=document.getElementById("location_code").innerHTML;
					qty=Number(document.getElementById("qty").innerHTML);
					num=num+qty;
				    str = str + el[i].value+",";
					count++;
				}
  			}   
  		}
  		  if(count==0){
  			  alert("请勾选数据!");
  			  return;
  		  }
  		result['id'] = str;
		result['location_code']=location_code;
		result['qty']=num;
		window.location.href="itemListCustomerreturnsAction.do?chanpinId="+str;
		window.parent.returnValue = result;
		window.parent.close();
		
}
</script>
	
<html:form action="listProductCode" >
	<input type="hidden" name="id" value="${customerCode}"/>
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="salesWorkorder.lotSer.id" desc="批次号"/>
	<input type="hidden" name="fields" value="salesWorkorder.part.id" desc="物料编号"/>
	<input type="hidden" name="fields" value="salesWorkorder.part.describe1" desc="物料描述"/>
	<input type="hidden" name="fields" value="salesWorkorder.part.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="salesWorkorder.part.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="salesWorkorder.location.code" desc="出库库位"/>
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listProductCode">	
<input type="hidden" name="id" value="${customerCode}"/>	
	<table class="data" width="100%" style="display: inline;">
					<thead>
						<tr bgcolor="#9999ff">
							<th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
							<th>批次号</th>
							<th>物料编号</th>
							<th>物料描述</th>
							<th>DPI</th>
							<th>原厂编号</th>
							<th>出库库位</th>
							<th>数量</th>
							<th>出库时间</th>
						</tr>
					</thead>
				<tbody id="datatable3">
				<c:forEach items="${x_salesWorkorderList}" var="X_OBJECT">
				<c:set var="X_OBJECT"  scope="request" value="X_OBJECT"/>
					<tr align="center">
						<td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();"/></td>
						<td id="lotSer${X_OBJECT.id}">${X_OBJECT.lotSer.id}</td>
						<td id="part_id${X_OBJECT.id}">${X_OBJECT.part.id}</td>
						<td id="part_describe${X_OBJECT.id}">${X_OBJECT.part.describe1}</td>
						<td id="part_dpiNo${X_OBJECT.id}">${X_OBJECT.part.dpiNo}</td>
						<td id="part_oldCode${X_OBJECT.id}">${X_OBJECT.part.oldCode}</td>
						<td id="location_code">${X_OBJECT.location.code}</td>
						<td id="qty"><fmt:formatNumber value="${X_OBJECT.count}" maxFractionDigits="0" /></td>
						<td id="outDate${X_OBJECT.id}">
							${X_OBJECT.outDate}
						</td>
					</tr>
				</c:forEach>
				</tbody>
	</table>
	<table width="100%">
		<tr>
		<td align="right"><input type="button" onclick="save()" value="选择" /><input " type="button" value="返回" onclick="guanbi()"/></td>
			
		</tr>
	</table>
	 <script type="text/javascript">
    applyRowStyle(document.all('datatable3'));
  </script>
</page:form>
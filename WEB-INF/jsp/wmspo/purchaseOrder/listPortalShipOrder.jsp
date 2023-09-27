<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<script language="javascript" src="includes/table.js">
</script>
<script type="text/javascript" src="includes/basicJS.js">
</script>
<script type="text/javascript" src="includes/jquery-1.3.2.js">
</script>
<script type="text/javascript">
<!--
	function addCondiment() {
		  var el = document.getElementsByName('ids');
		  var len = el.length; 
  		  var str="";
  		  var count=0;
  		  for(var i=0; i<len; i++){   
  			 if((el[i].type=="checkbox") && (el[i].checked== true)){
				 if(el[i].id!="checkAll"){
					var alreadySeasonTxt = document.getElementById("already_season_"+el[i].value).value;//本次调料量
					var poipItemQtyTxt = document.getElementById("poipItemQty_"+el[i].value).value;//采购量
					var qtyTxt = document.getElementById("qty_"+el[i].value).value;//已调料量
					var qtyTxt = document.getElementById("qty_"+el[i].value).value;//已调料量
					var a = document.getElementById("a"+el[i].value).value;//采购单量
					var b=document.getElementById("poipItemCapacity_"+el[i].value).value;//包装量
					var d=document.getElementById("singlecode_"+el[i].value).value;//物料号
					var already_season_ = document.getElementById("already_season_"+el[i].value).value;//本次调料量
					var already_season = document.getElementById("already_season"+el[i].value).value;//隐藏本次调料量
					
					if(parseFloat(b)<=0){
						alert('请先维护采购单的包装量！');
						return false;
					}
					
					var r = confirm("确认是否创建物料号为"+d+",采购包装量为"+b+"/箱,数量为"+alreadySeasonTxt+"个的调料单吗 ?");
					if(r){
					
					if(parseFloat(already_season)-parseFloat(already_season_)<0){
						alert("调料单已超出！");
						window.location.href="listPortalShipOrder.do";
						return false;
					}
					if(parseFloat(alreadySeasonTxt)+parseFloat(qtyTxt) > parseFloat(poipItemQtyTxt)){
						alert("调料量不能大于订单量！");
						return false;
					}
					
				    str=str+el[i].value+"," + alreadySeasonTxt + ";";
					count++;
					 if(count==0){
  			    	alert('选勾选发货单！');
  			    	return false;
  			    }
  		        
  		        $.ajax({         
                type:"POST", //请求方式        
                url:"insertPurchaseOrderCondimentByAjax.do", //请求路径        
                cache: false,           
                data:"ids="+str,  //传参        
                dataType: 'json',   //返回值类型        
                success:function(date){
                	window.location.href="listPortalShipOrder.do";
			   } 
            })
			}
  			}   
  		} 
		
	}
  		  }
	function withdraw(){
		var r = confirm("确定要撤回吗？");
		if(r){
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
  			    	alert('选勾选发货单！');
  			    	return false;
  			    }
  		   window.location.href = "updatePortalShipOrderWithdraw.do?array="+str;     
  	}
}
	function checkBoxs(obj){    
		var checks = document.getElementsByName("ids");    
		if(obj.checked){
			for(var i=0;i<checks.length;i++){
				checks[i].checked = false;        
			}       
			obj.checked = true;
		}else{
			for(var i=0;i<checks.length;i++){    
				checks[i].checked = false;
			}   
		}
	}
//-->
</script>
<html:form action="/listPortalShipOrder.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择" />
	<input type="hidden" name="fields" value="psoi.portalShipOrder.code"
		desc="发货单" />
	<input type="hidden" name="fields"
		value="psoi.poipItem.poip_number.po_number" desc="采购单" />
	<input type="hidden" name="fields" value="psoi.poipItem.line" desc="行号" />
	<input type="hidden" name="fields" value="psoi.poipItem.itemNumber.id"
		desc="物料号" />
	<input type="hidden" name="fields"
		value="psoi.poipItem.poip_number.supplier.code" desc="供应商编号" />
	<input type="hidden" name="fields"
		value="psoi.portalShipOrder.createDate" desc="时间" />

	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType" />
	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td colspan="4"><jsp:include page="../../simQuery.jsp" /></td>
		</tr>
	</table>
</html:form>
<page:form action="/listPortalShipOrder.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="2%">
					<%--<input type="checkbox" value="0" id="checkAll"
						onclick="selectAll();" />
				--%>
				</th>
				<th>
					<page:order order="id" style="text-decoration:none">
					发货单
					<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				<th>
					采购单
				</th>
				<th>
					行号
				</th>
				<th>
					物料号
				</th>
				<th>
					描述一
				</th>
				<th>
					供应商代码
				</th>
				<th>
					发货时间
				</th>
				<th>
					包装箱容量
				</th>
				<th>
					采购单量
				</th>
				<th>
					已调料量
				</th>
				<th>
					本次调料量
				</th>
				<th>
					状态
				</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="portalShipOrderRow.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
applyRowStyle(document.all('datatable'));
</script>


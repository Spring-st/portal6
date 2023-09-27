<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script type="text/javascript" src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
<!--
	function condimentConfirm() {
		var r = confirm("确认发货单入中转库吗？");
		if(r){
		  var el = document.getElementsByName('ids');
		  var len = el.length;   
  		  var str="";
  		  var count=0;
  		  for(var i=0; i<len; i++){   
  			 if((el[i].type=="checkbox") && (el[i].checked== true)){
				 if(el[i].id!="checkAll"){
					var id = el[i].value;
					var receivedQty = document.getElementById("received_"+id).value;
				    str = str + id + "," + receivedQty + ";";
					count++;
				}
  			}   
  		}
  		        if(count==0){
  			    	alert('选勾选发货单！');
  			    	return false;
  			    }
  		        
  		        $.ajax({         
                type:"POST", //请求方式        
                url:"updatePurchaseOrderCondimentConfirmByAjax.do", //请求路径        
                cache: false,           
                data:"ids="+str,  //传参        
                dataType: 'json',   //返回值类型        
                success:function(date){
                	window.location.href="listPortalShipOrderConfirm.do";
			   } 
            })
		}
	}
//-->
</script>
<html:form action="/listPortalShipOrderConfirm.do" styleId="formId" >
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="psoi.portalShipOrder.code" desc="发货单"/>
	<input type="hidden" name="fields" value="psoi.poipItem.poip_number.po_number" desc="采购单"/>
	<input type="hidden" name="fields" value="psoi.poipItem.line" desc="行号"/>
	<input type="hidden" name="fields" value="psoi.poipItem.itemNumber.id" desc="物料号"/>
	<input type="hidden" name="fields" value="psoi.poipItem.poip_number.supplier.code" desc="供应商编号"/>
	<input type="hidden" name="fields" value="psoi.portalShipOrder.createDate" desc="时间"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listPortalShipOrderConfirm.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
			    <th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
				<th><page:order order="id" style="text-decoration:none">
					发货单
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
					</page:order></th>
 				    <th>采购单</th>
					<th>行号</th>
				    <th>物料号</th>
				    <th>描述一</th>
				    <th>描述二</th>
				    <th>供应商代码</th>
				    <th>发货时间</th>
				    <th>采购单量</th>
				    <th>已收数量</th>
				    <th>到货数量</th>
				    <th>状态</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<tr id="r${X_OBJECT.id}">
					<td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();"/></td>
					<td>
						<a href='javascript:view("${X_OBJECT.id}")'>${X_OBJECT.portalShipOrder.code}</a>
					</td>
					<td>
					${X_OBJECT.poipItem.poip_number.po_number }
					</td>
					<td>
					${X_OBJECT.poipItem.line }
					</td>
					<td>
					${X_OBJECT.poipItem.itemNumber.id }
					</td>
					<td>
					${X_OBJECT.poipItem.itemNumber.describe1 }
					</td>
					<td>
					${X_OBJECT.poipItem.itemNumber.describe2 }
					</td>
					<td>
					${X_OBJECT.poipItem.poip_number.supplier.code}
					</td>
					<td>
					${X_OBJECT.portalShipOrder.createDate}
					</td>
					<td>
						<fmt:formatNumber value="${X_OBJECT.poipItem.qty}" maxFractionDigits="2" minFractionDigits="2"/>
					</td>
					<td>
						<c:if test="${X_OBJECT.received_qty == null}">
							0.00
						</c:if>
						<c:if test="${X_OBJECT.received_qty != null}">
							<fmt:formatNumber value="${X_OBJECT.received_qty}" maxFractionDigits="2" minFractionDigits="2"/> 
						</c:if>
					</td>
					<td>
						<input type="text" value="${X_OBJECT.deliveryNumber-X_OBJECT.received_qty}" id="received_${X_OBJECT.id}" size="8"/>
					</td>
					<td>${X_OBJECT.portalShipOrder.status.chnShortDescription}</td>
				</tr>
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>


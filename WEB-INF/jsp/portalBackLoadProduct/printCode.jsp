<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<object id="factory" style="display: none"
	classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814"
	codebase="${path}/includes/smsx/smsx.cab#Version=6,5,439,12">
</object>
<style>

v\:* {
	behavior: url(#default#VML);
}

o\:* {
	behavior: url(#default#VML);
}

x\:* {
	behavior: url(#default#VML);
}

.shape {
	behavior: url(#default#VML);
}
.xy{
    filter: Alpha(Opacity=10);
}
</style>

<%
	Date date = new Date();
	request.setAttribute("date", date);
%>
<script language="JavaScript" src="includes/jquery-1.3.2.js">
</script>
<script language="javascript">
function window.onload()    {  
		factory.printing.header = "";  //页尾        
		factory.printing.footer = "";  //页首        
		factory.printing.portrait = false;   //portrait是指打印方向，设置为true就是纵向，false就是横向。         
		factory.printing.leftMargin = 0.0;         
		factory.printing.topMargin = 0.0;         
		factory.printing.rightMargin = 0.0;        
		factory.printing.bottomMargin = 0.0;   
		
		//factory.DoPrint(true); //设置为false，直接打印     }    
	}
  	function printTure() //打印函数 
	{ 
	  //	document.all("viewpanel").style.display="none";//隐藏按钮 
		document.all("viewpane2").style.display="none";//隐藏按钮 
		factory.printing.Print(false); //调用控件打印 
		//document.all("viewpanel").style.display="";//显示
		document.all("viewpane2").style.display="";//隐藏按钮
		var ids="<c:forEach items="${X_RESULTLIST}" var="po" >${po.id};</c:forEach>";
		//var url="updatePirntCodePurchaseOrderItemProduct.do?ids="+ids;
		//window.location=url;
			$.ajax({         
                type:"POST", //请求方式        
                url:"updatePirntCodePurchaseOrderItemProduct.do", //请求路径        
                cache: false,           
              	data:{"ids":ids},//传参        
                dataType: 'json',   //返回值类型        
                async:false,
                success:function(json){
              		//if(json.object==true){
              		//	window.location.href="viewPortalShipOrderTwo.do?id="+json.id;
              		//}
               }
            });
	}
	
	function Print(frame) { 
		factory.printing.Print(true, frame) // print with prompt 
		
	}
	function printView(){
		document.all("viewpane2").style.display="none";//隐藏按钮 
		
		factory.printing.Preview();
		document.all("viewpane2").style.display="";//隐藏按钮 
		var ids="<c:forEach items="${X_RESULTLIST}" var="po" >${po.id};</c:forEach>";
		//var url="updatePirntCodePurchaseOrderItemProduct.do?ids="+ids;
		//window.location=url;
		$.ajax({         
                type:"POST", //请求方式        
                url:"updatePirntCodePurchaseOrderItemProduct.do", //请求路径        
                cache: false,           
              	data:{"ids":ids},//传参        
                dataType: 'json',   //返回值类型        
                async:false,
                success:function(json){
              		//if(json.object==true){
              		//	window.location.href="viewPortalShipOrderTwo.do?id="+json.id;
              		//}
               }
            });
	}
	 
	function partBarCodes(){
		var partBarCodes=$(".partBarCode");
		for(var i = 0; i < partBarCodes.length; i++){
			if(partBarCodes[i].style.display=='none'){
				partBarCodes[i].style.display="";
			}else{
				partBarCodes[i].style.display="none";
			}
		}
	}
	
	function  addState(){
	   alert("状态已修改!");
	}
</script>
<style type="text/css" id="style1">

.tab {
	width: 310px;
	height: 215px;
	border: solid #FFFFFF 2px; <%--边框 白色   #FFFFFF --%> 
	padding: 0px;
	margin-top:3px;
}

 .tab td {
	font-size: 10px;padding-left: 0px;
}
.tabspan{
	font-size: 18px;
}
.transparent_class { 
/* http://www.sharejs.com/codes/ */ 
filter:alpha(opacity=50); 
-moz-opacity:0.5; 
-khtml-opacity: 0.5; 
opacity: 0.5; 
} 
.td1{ 
BORDER-RIGHT: #FFFFFF 1px solid; //显示右边框为1px，如果不想显示就为0px 
} 

.td2{ 
BORDER-LEFT: #FFFFFF 1px solid;//显示左边框为1px，如果不想显示就为0px 
} 
</style>

<div id="viewpane2" align="center">
	<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}">
		<input name="bequery" type="button" value="打印" style="cursor: hand;"
			onclick="printTure();" />
	</c:if>
	<c:if test="${sessionScope.LOGIN_USER.locale=='en'}">
		<input name="bequery" type="button" value="Print"
			style="cursor: hand;" onclick="printTure();">
	</c:if>
	<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}">
		<input name="bequery" type="button" value="打印预览" style="cursor: hand;"
			onclick="printView();" />
	</c:if>
	<c:if test="${sessionScope.LOGIN_USER.locale=='en'}">
		<input name="bequery" type="button" value="Print preview"
			style="cursor: hand;" onclick="printView();" />
	</c:if>
	<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}">
		<input name="bequery" type="button" value="返回"
			onclick="history.go(-1)" />
	</c:if>
	<c:if test="${sessionScope.LOGIN_USER.locale=='en'}">
		<input name="bequery" type="button" value="Return"
			onclick="history.go(-1);" />
	</c:if>
	<input name="bequery" type="button" value="刷新"
		onclick="location=location" />
</div>
		<%--<table class="tab" align="center" border="1" cellspacing="0" cellpadding="0" >
		<tr>
			<td style="line-height:40px;" align="right">
				<font style="font-family: Arial; font-weight: bold; font-size: 22px;">${box.part.id}</font>
			</td>
		</tr>
		<tr height="40px;">
			<td style="border-top: 2px solid #000;padding-left: 8px;" >
				<table>
					<tr>
						<td style="font-family: Arial; padding-left: 0px; font-size: 13px;">适用车型:</td>
						<td style="font-family: Arial; font-size: 13px;padding-left: 0px;">
							${box.part.carBrand1}&nbsp;${box.part.domesticCar1}&nbsp;
							<c:if test="${box.part.yearFrom1 ne null && box.part.yearFrom1 ne ''}">'</c:if>${box.part.yearFrom1}<c:if test="${(box.part.yearFrom1 ne null && box.part.yearFrom1 ne '') or (box.part.yearTo1 ne null && box.part.yearTo1 ne '')}">-'</c:if>${box.part.yearTo1}
						</td>
					</tr>
					<tr>
						<td></td>
						<td style="font-family: Arial; font-size: 13px;padding-left: 0px;">
							${box.part.carBrand2}&nbsp;${box.part.domesticCar2}&nbsp;
							<c:if test="${box.part.yearFrom2 ne null && box.part.yearFrom2 ne ''}">'</c:if>${box.part.yearFrom2}<c:if test="${(box.part.yearFrom2 ne null && box.part.yearFrom2 ne '') or (box.part.yearTo2 ne null && box.part.yearTo2 ne '')}">-'</c:if>${box.part.yearTo2}
						</td>
					</tr>
					<tr>
						<td></td>
						<td style="font-family: Arial; font-size: 13px;padding-left: 0px;">
							${box.part.carBrand3}&nbsp;${box.part.domesticCar3}&nbsp;
							<c:if test="${box.part.yearFrom3 ne null && box.part.yearFrom3 ne ''}">'</c:if>${box.part.yearFrom3}<c:if test="${(box.part.yearFrom3 ne null && box.part.yearFrom3 ne '') or (box.part.yearTo3 ne null && box.part.yearTo3 ne '')}">-'</c:if>${box.part.yearTo3}
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr height="12px">
			<td style="font-family: Arial; padding-left: 10px; font-size: 13px;">
				${box.part.describe1}&nbsp;${box.part.describe2}&nbsp;${box.part.vend}
			${box.part.describe1}&nbsp;${box.part.vend}
			</td>
		</tr>
		<tr height="10px">
			<td style="font-family: Arial; padding-left: 10px; font-size: 13px; word-wrap:break-word; word-break:break-all;">
				${box.part.productSpecifications}
			</td>
		</tr>
		<tr>
			<td>
				<table border="0px;">
					<tr>
						<td  style="padding-left: 0px; padding-top: 2px;font-size: 13px; padding-bottom: 0px;">
							<img style="width: 220px; height: 55px;"
								src='${path}/CreateBarCode?msg=${box.lot.id}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=9&width=500&moduleWidth=500'>
						</td>
						<td valign="bottom" style="padding-bottom: 5px;font-size: 13px;">
							<fmt:formatNumber pattern="###" value="${box.number}" var="number" />QTY:${number}PC	
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
	
	--%>
	<%--<table width="60%" height="450px"  align="center"  border="1" cellspacing="0" cellpadding="0" >
		<tr height="15%" >
			<td width="50%" colspan="2"><img width=160  src='images/Lear_log_proc.jpg'><img width=160  src='images/Dymos_log_proc.jpg'></td>
			<td  colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;收货方 Recipient<br>&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px;font-weight: bold;">LEAR</span></td>
		</tr>
		<tr valign="top" height="15%">
			<td width="25%" >订单编号<br>Order Number<br><span class="tabspan">${box.po_number}</span></td>
			<td >供应商编号<br>Supplier Number<br><span class="tabspan">${box.po_supp}</span></td>
			<td width="25%" >供应商名称<br>Supplier Name<br><span class="tabspan">${box.po_supp_name}</span></td>
			<td >打印日期<br>Arrival Date<br><span class="tabspan">${X_DATE}</span></td>
		</tr>
		<tr  height="15%" >
			<td width="50%" valign="top" align="left" colspan="2" style="padding-top: 5px">
					  &nbsp;&nbsp;零件编号   Material Number<br> 
					<span style="font-size: 22px; font-weight: bold;">&nbsp;&nbsp;${box.part.id}</span>
			</td>
			<td  colspan="2" valign="top" align="left" colspan="2" style="padding-top: 5px">&nbsp;&nbsp;零件名称  Material Description <br>
					<span style="font-size: 18px;">&nbsp;&nbsp;${box.part.name}</span>
			</td>
		</tr>
		<tr height="15%">
			<td  rowspan="2"  colspan="3" width="70%">
			<div style="width: 100%;height: 20%;">&nbsp;&nbsp;HU编号 Handing Unit Number</div>
			<div style="width: 100%;height: 50%; text-align: center;"><img  style="width: 450px; height: 70px;" src='${path}/CreateBarCode?msg=${box.lot.id}&barType=Code93&checkCharacter=n&hrp=none&checkCharacterInText=n&height=9&width=160&moduleWidth=300'></div>
			<div style="width: 100%;height: 10px;  "><span  style="font-size: 20px;font-weight: bold;">　　${box.huCodeOrderNumber}</span></div>
			</td>
			<td> 数量  Quantity <br><span style="font-size: 20px">${box.number}</span></td>
		</tr>
		<tr valign="top" height="15%">
			<td> ZGS/Q-Level <br><span style="font-size: 20px"></span></td>
		</tr>
		<tr align="center" height="15%">
			<td> 质量状态</td>
			<td colspan="2"> 合格&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;不合格</td>
			<td align="left"> &nbsp;&nbsp;备料 <br>&nbsp;&nbsp;确认</td>
		</tr>
	</table>
--%>
	<%--<table width="453px" height="265px" align="center"  border="0" cellspacing="0" cellpadding="0" style="background-image: url('images/tlcbig.png');">
		<tr height="21%" >
			<td width="21%"><img width="80" height="44"  src='images/Lear_log_proc.jpg'></td>
			<td width="62%" colspan="2" align="center" style="font-weight: bold;">北京李尔现代坦迪斯汽车系统有限公司<br/>部品识别表</td>
			<td width="17%"><img width="68" height="44"  src='images/Dymos_log_proc.jpg'></td>
		</tr>
		<tr height="10%" align="center">
			<td width="21%"	 rowspan="2">零件号</td>
			<td width="45%"  rowspan="2" style="font-weight: bold; font-size: 15px;">${box.part.id}</td>
			<td width="17%">车型</td>
			<td width="17%" style="font-weight: bold;">TLC</td>
		</tr>
		<tr height="11%" align="center">
			<td width="17%">数量</td>
			<td width="17%" style="font-weight: bold;"><fmt:formatNumber>${box.number}</fmt:formatNumber>${box.part.unit}</td>
		</tr>
		<tr height="14%" align="center">
			<td width="21%">零件名称</td>
			<td width="45%" colspan="3" style="font-weight: bold;">${box.part.name}</td>
		</tr>
		<tr height="14%" align="center">
			<td width="21%">供应商</td>
			<td width="45%" style="font-weight: bold;" >${box.po_supp_name}</td>
			<td width="17%">批次号</td>
			<td width="17%">&nbsp;</td>
		</tr>
		<tr height="11%" align="center">
			<td width="21%">打印日期</td>
			<td width="45%" >${X_DATE}</td>
			<td width="17%">校验</td>
			<td width="17%">&nbsp;</td>
		</tr>
		<tr height="17%">
			<td width="21%">条形码</td>
			<td width="45%" colspan="3"><img  style="width: 356px; height: 34px;" src='${path}/CreateBarCode?msg=${box.lot.id}&barType=Code93&checkCharacter=n&hrp=none&checkCharacterInText=n&height=9&width=160&moduleWidth=300'></td>
		</tr>
	</table>
--%>
<table style="width:1100px;height:752px;"align="center"  >
<tr style="width:100% ;height: 50%;" align="center"  >
<c:forEach var="box" items="${X_RESULTLIST}" varStatus="status" >
	<td style="width:50% ;height: 50%;"  >
		<table width="514px" height="347px;"  border="1" cellspacing="0" cellpadding="0"  >
			<tr height="21%"  >
				<%--<td width="21%"><img width="80" height="44"  src='images/Lear_log_proc.jpg'></td>--%>
				<td class="td1" width="10%">&nbsp;</td>
				<td class="td1 td2" width="62%" colspan="2" align="center" style="font-weight: bold;font-size: 17px;">北京李尔现代坦迪斯汽车系统有限公司<br/>部品识别表</td>
				<%--<td width="17%"><img width="68" height="44"  src='images/Dymos_log_proc.jpg'></td>--%>
				<td class="td2" width="17%">&nbsp;</td>
			</tr>
			<tr height="10%" align="center">
				<td width="10%"	 rowspan="2">零&nbsp;&nbsp;件&nbsp;&nbsp;号</td>
				<td width="45%"  rowspan="2" style="font-weight: bold; font-size: 25px;">${box.part.id}</td>
				<td width="8%">车&nbsp;&nbsp;&nbsp;型</td>
				<td width="17%" style="font-weight: bold;font-size: 20px;">${box.part.productSpecifications}</td>
			</tr>
			<tr height="11%" align="center">
				<td width="8%">数&nbsp;&nbsp;&nbsp;量</td>
				<td width="17%" style="font-weight: bold; font-size: 20px;"><fmt:formatNumber>${box.number}</fmt:formatNumber>${box.part.unit}</td>
			</tr>
			<tr height="12%" align="center">
				<td width="10%">零件名称</td>
				<td width="45%" colspan="3" style="font-weight: bold;font-size: 15px;">${box.part.name}</td>
			</tr>
			<tr height="11%" align="center">
				<td width="10%">供&nbsp;&nbsp;应&nbsp;&nbsp;商</td>
				<td width="50%" style="font-weight: bold;" >${box.po_supp_name}</td>
				<td width="8%">批次号</td>
				<td width="17%">${box.huCodeOrderNumber}</td>
			</tr>
			<tr height="11%" align="center">
				<td width="10%">打印日期</td>
				<td width="45%" >${X_DATE}</td>
				<td width="8%">检&nbsp;&nbsp;&nbsp;验</td>
				<td width="17%">&nbsp;</td>
			</tr>
			<tr height="17%" align="center">
				<td width="10%">条&nbsp;&nbsp;形&nbsp;&nbsp;码</td>
				<td width="45%" colspan="3"><img  style="width: 430px; height: 70px;" src='${path}/CreateBarCode?msg=${box.lot.id}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=12&width=300&moduleWidth=300'></td>
			</tr>
		</table>
		</td>
		<c:choose>
		    <c:when test="${fn:length(X_RESULTLIST)==1}">
		    	<td height="50%" width="50%">&nbsp;</td>
		    	</tr>
		    	<tr style="width:100% ;height: 50%" align="center"  >
			    	<td height="50%" width="50%">&nbsp;</td>
			    	<td height="50%" width="50%">&nbsp;</td>
				</tr>
			</c:when>
			<c:when test="${fn:length(X_RESULTLIST)==2}">
				<c:if test="${status.count%2==0}">
					</tr>
					<tr style="width:100% ;height: 50%" align="center"  >
				    	<td height="50%" width="50%">&nbsp;</td>
				    	<td height="50%" width="50%">&nbsp;</td>
					</tr>
				</c:if>
				
			</c:when>
			<c:otherwise>
				<c:if test="${status.count%2==0}">
					</tr>
					<tr style="width:100% ;height: 50%" align="center"  >
				</c:if>
				<c:if test="${status.count%4==0}">
					</tr>
					</table>
					<table style="width:1100px;height:752px;" align="center"   >
					<tr style="width:100% ;height: 50%" align="center"  >
				</c:if>
				<c:if test="${status.last}">
					<c:if test="${status.count%2!=0}">
						<td height="50%" width="50%">&nbsp;</td>
					</c:if>
					</tr>
					<c:if test="${status.count%4!=0}">
						<tr style="width:100% ;height: 50%" align="center"  >
						<td height="50%" width="50%">&nbsp;</td>
						<td height="50%" width="50%">&nbsp;</td>
						</tr>
					</c:if>
					</table>
				</c:if>
			</c:otherwise>
		</c:choose>
</c:forEach>
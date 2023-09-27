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
		var ids="<c:forEach items="${X_CODELIST}" var="code" >${code.id};</c:forEach>";
			$.ajax({         
                type:"POST", //请求方式        
                url:"updatePurchaseManualCreateBarcode.do", //请求路径        
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
		var ids="<c:forEach items="${X_CODELIST}" var="code" >${code.id};</c:forEach>";
			$.ajax({         
                type:"POST", //请求方式        
                url:"updatePurchaseManualCreateBarcode.do", //请求路径        
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
<%--<table style="width:1050px;height:762px;"align="center"  >
<tr style="width:100% ;height: 50%;" align="center"  >
<c:forEach var="code" items="${X_CODELIST}" varStatus="status" >
	<td style="width:50% ;height: 50%;"  >
	<table width="514px" height="345px" align="center"  border="1" cellspacing="0" cellpadding="0" >
		<tr height="21%" >
			<td class="td1" width="12%">&nbsp;</td>
			<td class="td1 td2" width="62%" colspan="5" align="center" >北京李尔现代坦迪斯汽车系统有限公司<br/>部品识别表</td>
			<td class="td2" width="17%">&nbsp;</td>
		</tr>
		<tr height="10%" align="center">
			<td width="12%"	 rowspan="2" >零&nbsp;&nbsp;件&nbsp;&nbsp;号</td>
			<td width="45%"  rowspan="2"  colspan="4" style="font-weight: bold; font-size: 15px;">${code.part}</td>
			<td width="12%">车&nbsp;&nbsp;&nbsp;型</td>
			<td width="17%" style="font-weight: bold;">${code.model}</td>
		</tr>
		<tr  align="center">
			<td width="12%">数&nbsp;&nbsp;&nbsp;量</td>
			<td width="17%" style="font-weight: bold;"><fmt:formatNumber>${code.qty}</fmt:formatNumber>${box.part.unit}</td>
		</tr>
		<tr  align="center">
			<td width="12%">零件名称</td>
			<td width="45%" colspan="6" style="font-weight: bold;">${code.partName}</td>
		</tr>
		<tr height="14%" align="center">
			<td width="12%">供&nbsp;&nbsp;应&nbsp;&nbsp;商</td>
			<td width="50%" style="font-weight: bold;"colspan="5" >${code.supplierName}</td>
			<td rowspan="3" valign="middle">
				<img  style=" width: 75px;height: 76px; " src='${path}/CreateQrCode?msg=${code.part}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=9&width=80&moduleWidth=80'> 
			</td>
		</tr>
		<tr height="11%" align="center">
			<td width="12%">批次号</td>
			<td width="45%" colspan="5">&nbsp;</td>
		</tr>
		<tr height="17%" align="center">
			<td>检&nbsp;&nbsp;&nbsp;验</td>
			<td width="14%" colspan="2">&nbsp;</td>
			<td width="12%" colspan="2">流水号</td>
			<td width="12%">${code.serialNumber}</td>
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
					<table style="width:1050px;height:762px;" align="center"   >
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

--%>
<table style="width: 1100px; height: 760px;" align="center" >
	<tr style="width: 100%; height: 50%;" align="center">
		<c:forEach var="code" items="${X_CODELIST}" varStatus="status">
			<td style="width: 50%; height: 50%;">
				
				<table height="20">
	<tr><td>&nbsp;</td></tr>
</table>
				
				<table width="514px" height="345px" align="center" border="1"
					cellspacing="0" cellpadding="0">
					<tr height="21%">
						<td class="td1" width="12%">
							&nbsp;
						</td>
						<td class="td1 td2" width="62%" colspan="5" align="center"
							style="font-weight: bold;font-size: 17px;">
							北京李尔现代坦迪斯汽车系统有限公司
							<br />
							部品识别表
						</td>
						<td class="td2" width="17%">
							&nbsp;
						</td>
					</tr>
					<tr height="10%" align="center">
						<td width="12%" rowspan="2">
							零&nbsp;&nbsp;件&nbsp;&nbsp;号
						</td>
						<td width="45%" rowspan="2" colspan="4"
							style="font-weight: bold; font-size: 25px;">
							${code.part}
						</td>
						<td width="12%">
							车&nbsp;&nbsp;&nbsp;型
						</td>
						<td width="17%" style="font-weight: bold;font-size: 20px;">
							${code.model}
						</td>
					</tr>
					<tr height="11%" align="center">
						<td width="12%">
							数&nbsp;&nbsp;&nbsp;量
						</td>
						<td width="17%" style="font-weight: bold;font-size: 20px;">
							<fmt:formatNumber>${code.qty}</fmt:formatNumber>
							${code.partUnit}
						</td>
					</tr>
					<tr height="14%" align="center">
						<td width="12%">
							零件名称
						</td>
						<td width="45%" colspan="6" style="font-weight: bold;font-size: 20px;">
							${code.partName}
						</td>
					</tr>
					<tr height="14%" align="center">
						<td width="12%">
							供&nbsp;&nbsp;应&nbsp;&nbsp;商
						</td>
						<td width="50%" style="font-weight: bold;font-size: 20px;" colspan="5">
							${code.supplierName}
						</td>
						<td rowspan="3">
							<img style="width: 130px; height: 130px;"
								src='${path}/CreateQrCode?msg=${code.part}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=9&width=80&moduleWidth=80'>
						</td>
					</tr>
					<tr height="11%" align="center">
						<td width="12%">
							批次号
						</td>
						<td width="45%" colspan="5">
							&nbsp;
						</td>
					</tr>
					<tr height="17%" align="center">
						<td>
							检&nbsp;&nbsp;&nbsp;验
						</td>
						<td width="14%" colspan="2">
							&nbsp;
						</td>
						<td width="12%" colspan="2">
							流水号
						</td>
						<td width="12%" style="font-weight: bold;font-size: 15px;">
							${code.serialNumber}
						</td>
					</tr>
				</table>
			</td>
			<c:choose>
				<c:when test="${fn:length(X_RESULTLIST)==1}">
					<td height="50%" width="50%">
						&nbsp;
					</td>
	</tr>
	<tr style="width: 100%; height: 50%" align="center">
		<td height="50%" width="50%">
			&nbsp;
		</td>
		<td height="50%" width="50%">
			&nbsp;
		</td>
	</tr>
	</c:when>
	<c:when test="${fn:length(X_RESULTLIST)==2}">
		<c:if test="${status.count%2==0}">
			</tr>
			<tr style="width: 100%; height: 50%" align="center">
				<td height="50%" width="50%">
					&nbsp;
				</td>
				<td height="50%" width="50%">
					&nbsp;
				</td>
			</tr>
		</c:if>

	</c:when>
	<c:otherwise>
		<c:if test="${status.count%2==0}">
			</tr>
			<tr style="width: 100%; height: 50%" align="center">
		</c:if>
		<c:if test="${status.count%4==0}">
			</tr>
</table>
<table style="width: 1100px; height: 760px;" align="center">
	<tr style="width: 100%; height: 50%" align="center">
		</c:if>
		<c:if test="${status.last}">
			<c:if test="${status.count%2!=0}">
				<td height="50%" width="50%">
					&nbsp;
				</td>
			</c:if>
	</tr>
	<c:if test="${status.count%4!=0}">
		<tr style="width: 100%; height: 50%" align="center">
			<td height="50%" width="50%">
				&nbsp;
			</td>
			<td height="50%" width="50%">
				&nbsp;
			</td>
		</tr>
	</c:if>
</table>
</c:if>
</c:otherwise>
</c:choose>
</c:forEach>


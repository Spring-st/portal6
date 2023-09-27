<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<script language='javascript'>
	var list=new Array();
	var i=0;
	<c:forEach items="${X_RESULTLIST}" var="result">
		var list2=new Array();
		list2[0]="${result.supplierCode}";
		list2[1]="${result.supplierName}";
		list2[2]="${result.wmsPart}";
		list2[3]="${result.enterTime}";
		list2[4]="${result.blanket}";
		list2[5]="${result.amount}";
		list2[6]="${result.unit}";
		list2[7]="${result.lotSet}";
		list2[8]="${result.unplanned.id}";
		list[i]=list2;
		i++;
	</c:forEach>
    window.parent.returnValue = list;
    window.parent.close();
</script>










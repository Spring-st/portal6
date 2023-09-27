<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<script language='javascript'>
	var list=new Array();
	var i=0;
	<c:forEach items="${X_RESULTLIST}" var="result">
		var list2 = new Array();
		list2[0]="${result.part}";
		list2[1]="${result.amount}";
		list2[2]="${result.it_amount}";
		list[i]=list2;
		i++;
	</c:forEach>
	
    window.parent.returnValue = list;
    window.parent.close();
</script>










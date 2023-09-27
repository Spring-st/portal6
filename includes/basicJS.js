 function selectAll(){
		var ctlBox = document.getElementById("checkAll");
		var idArr = document.getElementsByName('ids');
		for(var i=0;i<idArr.length;i++){
			idArr.item(i).checked = ctlBox.checked;
		}
	}
	
	function getCheckedNum(){
	var count = 0;
	var proEles = document.getElementsByName('ids');
	for(var i = 0;i<proEles.length;i++){
		if(proEles.item(i).checked){
			count++;
		}
	}
	return count;
}

function productbox_click(){
	var proEles = document.getElementsByName('ids');
	var ctlbox  = document.getElementById("checkAll");
	if(proEles.length == getCheckedNum()){
		ctlbox.checked = true;
	} else {
		ctlbox.checked = false;
	}
}

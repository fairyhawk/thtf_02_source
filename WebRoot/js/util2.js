//根据id取得元素
//author: llw

function getIDElement(id) {
	return document.getElementById(id);
}

//类型验证

//数字: num
//非空: notNull
//
function checkType(type, content, descrption) {
	
	var regExp;
	var alertMessage;

	//数字
	if (type == "num") {
		regExp = /^[0-9]{1,20}$/;	
		alertText = "\u53ea\u80fd\u586b\u6570\u5b57！";	
	}
	//notNull
	if (type == "notNull") {
		regExp = "^[ ]+$";
		alertText = "不能为空！";
	}
	
	//结果的验证	
	
	if (regExp.test(content) == false) {	 	
	 	alert(descrption + alertText);
	 	return false;
	} 
	
	return true;	

}

//获取选中的radio
//author: llw
function getCheckedRadioByName(name) {
	var elements = document.getElementsByName(name);

	for (var i = 0; i < elements.length; i++) {
		if (elements[i].checked == true && elements[i].type.toUpperCase() == "RADIO") {
			return elements[i];
		}//if
	}//for
}


//全选-checkBox

	function selectAllCheckBoxByName(name, checkBox) {
		var all = document.getElementsByName(name);
		
		for(var i = 0; i < all.length; i++) {
			if(all[i].type.toUpperCase() == "CHECKBOX") {
				if(checkBox.checked == true) {
					all[i].checked = true;
				} else {
					all[i].checked = false;
				}
			}
		}
		
	
	}

//获取父页面id的元素
//author: llw
	function getParentElementById(id) {
		return  window.opener.document.getElementById(id);
	}

//清除元素的全部子节点
//author: llw
function removeAllChild(parent) {
	
	var childNodes = parent.childNodes;
	
	for (var i = childNodes.length-1; i >=0 ; i--) {
		parent.removeChild(childNodes[i]);
	}
	
	return parent;
	
}

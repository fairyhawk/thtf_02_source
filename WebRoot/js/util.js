//非空验证
function checkFormNotNull(formId, checknNames, descriptions, checkNulls) {
	var form = document.forms[formId];
	//var form = document.getElementById(formId);
	var elements = form.elements;
	for (i = 0; i < checknNames.length; i = i + 1) {
		var element = elements(checknNames[i]);
		var description = descriptions[i];
		var checkType = checkNulls[i];
		if (checkType == "notnull") {
			if (element.value == "") {
				//if(description==""){
				alert("\u5fc5\u586b\u9879\u4e0d\u80fd\u4e3a\u7a7a！");
				return false;
				//}else{
				//	alert(description + "不能为空");
				//	return false;
				//}
			}
			try{ 
				if(element.value.indexOf(" ")==0||element.value.lastIndexOf(" ")==(element.value.length-1)
				||element.value.indexOf("　")==0||element.value.lastIndexOf("　")==(element.value.length-1)) {
					alert("\u5fc5\u586b\u9879\u4e0d\u80fd\u4ee5\u7a7a\u683c\u5f00\u5934\u6216\u7ed3\u5c3e！");
					return false;
				}
			} 
			catch(e){ 
			} 
						
		} else if (checkType == "notspace") {
			if (element.value != "") {
				if (element.value.indexOf(" ")==0||element.value.lastIndexOf(" ")==(element.value.length-1)
				||element.value.indexOf("　")==0||element.value.lastIndexOf("　")==(element.value.length-1)) {
					alert(description + "\u4e0d\u80fd\u4ee5\u7a7a\u683c\u5f00\u5934\u6216\u7ed3\u5c3e！");
					return false;
				}
			}
		}
	}
	return true;
}


// 长度验证
function checkFormLength(formId, checknNames, descriptions, checkNulls,checkLengths) {
	var form = document.forms[formId];
	var elements = form.elements;
	for (i = 0; i < checknNames.length; i = i + 1) {
		var element = elements(checknNames[i]);
		var description = descriptions[i];
		var checkNull = checkNulls[i];
		var checkLength = checkLengths[i];
		var regExp = /^(0|[1-9][0-9]*)$/;
		var regExp_x = /^(x|[1-9][0-9]*)$/;
		if(checkLength!=""){
			if (element.type == "text" || element.type == "password") {
				//if (checkNull == "" || checkNull == "notnull" ||checkNull == "null") {
					var lengths = getInputLengths(element);
					if (regExp.test(checkLength) == false) {
						checkLength=checkLength.substring(1);
						if(lengths< checkLength && lengths != ""){
							alert(description + "长度不能少于" + checkLength + "个字符！");
							return false;
						}
					}
					
					else if (regExp.test(checkLength) == false) {
						
						if(lengths != checkLength && lengths != ""){
							checkLength=checkLength.substring(1);
							alert(description + "长度必须等于" + checkLength + "个字符！");
							return false;
						}
						
					}
					
					else if (lengths > checkLength) {
						alert(description + "长度不能超过" + checkLength + "个字符！");
						return false;
					}
				//}
			}
		}
	}
	return true;
}

// 类型验证
function checkFormType(formId, checknNames, descriptions, checkTypes) {
	var form = document.forms[formId];
	var elements = form.elements;
	for (i = 0; i < checknNames.length; i = i + 1) {
		var element = elements(checknNames[i]);
		var description = descriptions[i];
		var checkType = checkTypes[i];
		if (element.value != "") {
			if (element.type == "text" || element.type == "password") {
				if (checkType == "email") {
					var alertText = "\u683c\u5f0f\u4e0d\u6b63\u786e！";
					var regExp = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
					if (regExp.test(element.value) == false) {
						alert(description + alertText);
						return false;
					}
				}
				
				
				if (checkType == "num") {
					var alertText = "\u53ea\u80fd\u586b\u6570\u5b57！";
					var regExp = /^[0-9]*$/;
					if (regExp.test(element.value) == false) {
						alert(description + alertText);
						return false;
					}
				}
				if (checkType == "abc") {
					var alertText = "\u53ea\u80fd\u586b\u5b57\u6bcd！";
					var regExp = /^[A-Za-z]+$/;
					if (regExp.test(element.value) == false) {
						alert(description + alertText);
						return false;
					}
				}
				if (checkType == "abcnum") {
					var alertText = "\u683c\u5f0f\u4e0d\u6b63\u786e！";
					var regExp = /^[0-9A-Za-z]*$/;
					if (regExp.test(element.value) == false) {
						alert(description + alertText);
						return false;
					}
				}
				if (checkType == "0num") {
					var alertText = "\u662f\u4e0d\u80fd\u4ee50\u5f00\u5934\u7684\u6570\u5b57！";
					var regExp = /^(0|[1-9][0-9]*)$/;
					if (regExp.test(element.value) == false) {
						alert(description + alertText);
						return false;
					}
				}
				if(checkType=="num."){
					var alertText = "格式不正确！";
					var regExp = /^[0-9]+\d*[\.\d]?\d{0,2}$/;
					if (regExp.test(element.value) == false) {
						alert(description + alertText);
						return false;
					}
				}				
				if (checkType == "zip") {
					var alertText = "\u683c\u5f0f\u4e0d\u6b63\u786e！";
					var regExp = /^\d{6}$/;
					if (regExp.test(element.value) == false) {
						alert(description + alertText);
						return false;
					}
				}
				if (checkType == "loginname") {
					var alertText = "\u683c\u5f0f\u4e0d\u6b63\u786e！";
					var regExp = /^\w+([-+.]\w+)*$/;
					if (regExp.test(element.value) == false) {
						alert(description + alertText);
						return false;
					}
				}	
				//电话和传真 可以输入  数字+"-"+"/"+"," 的组合，但符号不允许连续在一起，别骂我。。。
				if (checkType == "phone") {
					var alertText = "\u683c\u5f0f\u4e0d\u6b63\u786e！";
					var regExp = /^\d+([-,\/]\d+)*$/;
					if (regExp.test(element.value) == false) {
						alert(description + alertText);
						return false;
					}
				}
			}
		}
	}
	return true;
}
function getInputLengths(obj) {
	var vname = obj.value;
	var count = 0;
	for (j = 0; j < vname.length; j = j + 1) {
		if (vname.charCodeAt(i) > 256) {
			count = count + 2;
		} else {
			count = count + 1;
		}
	}
	return count;
}
function checkForm() {
	if (!checkFormNotNull(formId, checknNames, descriptions, checkNulls)) {
		
		return false;
	}
	if (!checkFormLength(formId, checknNames, descriptions, checkNulls, checkLengths)) {
		
		return false;
	}
	if (!checkFormType(formId, checknNames, descriptions, checkTypes)) {
		
		return false;
	}
}

function yanzhengjinedaichangdu(ccclimit, ccclimit_length){
	var pd =9999999999.99;
	var alertText = "请输入正确格式！";
	var regExp = /^[0-9]+\d*[\.\d]?\d{0,2}$/;
	if(ccclimit_length==8){
		pd = 99999999.99;
	}else if(ccclimit_length==9){
		pd = 999999999.99;
	}else if(ccclimit_length==10){
		pd = 9999999999.99;
	}
	if(ccclimit>pd){
		alert(alertText);
		return false;
	}
	if (regExp.test(ccclimit) == false) {
		alert(alertText);
		return false;
	}
}
//Eagle  20100101

//格式化金额
//param num eg 123456.36
//return    eg 123,456.36
function formatCurrency(num) {
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
    num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    cents = num%100;
    num = Math.floor(num/100).toString();
    if(cents<10)
    cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
    num = num.substring(0,num.length-(4*i+3))+','+
    num.substring(num.length-(4*i+3));
    return (((sign)?'':'-') + num + '.' + cents);
}

//替换textArea内的回车符和空格
//插入数据库后保留原有格式
function setTextArea(){
	$("textArea").each(function(){
		$(this).text();
		while(this.value.indexOf("\r\n")>=0){
			this.value=this.value.replace("\r\n","<br>");//替换回车符
		}
		while(this.value.indexOf(" ")>=0){ 
			this.value=this.value.replace(" ","&nbsp;");//替换空格
		}			
	});
}
//检查textArea控件字符长度
function checkTextAreaLen(len){
	var flag = false;
	if((/^(\+|-)?\d+$/.test( len ))|| len<0){ //正整数判断  

	}else{
		alert("程序错误：参数类型应为正整数");
		return flag;  
	}

	$("textArea").each(function(){//检查textArea字符长度
		if($(this).text().length>len){
			alert("输入字符过长！最大长度为"+len+"个字符");
			$(this).select();
			return flag = false;
		}else{
			flag = true;
		}
	});
	return flag;
} 
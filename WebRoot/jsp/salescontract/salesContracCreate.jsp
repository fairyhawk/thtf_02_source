<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" import="cn.com.thtf.egov.cms.constant.Constants" %>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新建销售合同</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/common/contractUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/math.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<style type="text/css">
<!--
.treven {
	background-color: #f3fbff;
}
.over {
	background-color: #ECECEC;
}
-->
</style>
<script language="JavaScript"> 
var win1;
var win3;
			$(document).ready(function(){
				var msg = "${msg}";  //获取服务端信息
				if(msg!=""){
				    alert(msg);
				}
			hideAddText();
				if($("#table")){
					$("#table tr:even").addClass("treven");
					$("#table tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
				if($("#table1")){
					$("#table1 tr:odd").addClass("treven");
					$("#table1 tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
				$("#addBUtton").click(function(){
				$("#addressAddOrSelect").val("0");//0表示添加  1表示选择 
					showAddText();
				});  
			});
		function showAddText(){
				if(win3!=undefined){
					win3.close();
				}
				 $("#trUp1").hide();
				 $("#trUp2").hide();
				 $("#trUp3").hide();

				$("#addreveivename").val("");
				$("#addaddressname").val("");
				$("#addreveivelinkman").val("");
				$("#addpostcode").val("");
				$("#addreceivetel").val("");
				$("#addreceivemobile").val("");

				$("#customerAddressId").val("0"); 
				$("#reveivename").text("");
				$("#addressname").text("");
				$("#reveivelinkman").text("");
				$("#postcode").text("");
				$("#receivetel").text("");
				$("#receivemobile").text("");
					


				 $("#trFt1").show();
				 $("#trFt2").show();
				 $("#trFt3").show();
			}
function hideAddText(){
				 $("#trUp1").show();
				 $("#trUp2").show();
				 $("#trUp3").show();

				 $("#trFt1").hide();
				 $("#trFt2").hide();
				 $("#trFt3").hide();
				$("#customerAddressId").val(""); 
				$("#addreveivename").val("");
				$("#addaddressname").val("");
				$("#addreveivelinkman").val("");
				$("#addpostcode").val("");
				$("#addreceivetel").val("");
				$("#addreceivemobile").val("");
			}

</script>

<script type="text/javascript">
    //关闭所有打开的子窗口
	var arrSubWin = new Array(); 
	function closeSubWin(){
		for(var i=0; i<arrSubWin.length; i++){
			if(arrSubWin[i] && arrSubWin[i].open) 
			{ 
				arrSubWin[i].close(); 
			} 
		}
		if(arrSubWin.length>0){
			arrSubWin = new Array();
		}
	}

	function showFile(){//文件名显示
		$("#contractFile").val($("#file").val());
		$("#file").attr("class","");
		$("#fileNameError").text("");
	}

	//选择模板的操作
	function checkradio(){
		var elm=document.getElementsByName('templateTypes');
		var type;
		for(ii=0;ii<elm.length;ii++){
			if(elm[ii].checked){
				type=elm[ii].value;
			}
		}
		// 标准模板
		if(type == 0){
			$("tr[name='trTemplate0']").show();
			$("tr[name='trTemplate1']").hide();
			$("tr[name='trTemplate2']").hide();

			//从配置文件中取得
			$("#place").val("<%=Constants.SELL_PLACE%>"); //签订地点
			$("#lawsuit").val("<%=Constants.SELL_LAWSUIT%>"); //诉讼地
			$("#protectLimit").val("<%=Constants.SELL_PROTECT%>"); //保价期限
			$("#checkLimit").val("<%=Constants.SELL_CHECK%>"); //验收期限
			$("#breach").val("<%=Constants.SELL_BREAK%>"); //违约金标准	
			$("#place").removeClass("invalid");
			$("#lawsuit").removeClass("invalid");
			$("#protectLimit").removeClass("invalid");
			$("#checkLimit").removeClass("invalid");
			$("#breach").removeClass("invalid");

			$("tr[name='trTemplate0'] input").attr("disabled",true);
			$("#btnPreview").show();
			var contractProName=$("#contractProName").clone();
			$("#tdOfcontractProNameBy2").html("");
			$("#tdOfcontractProNameBy1").html(contractProName);
			$("#contractProName").attr("disabled",false);
		}if(type == 1){// 自定义模板
			$("tr[name='trTemplate0']").show();
			$("tr[name='trTemplate1']").show();
			$("tr[name='trTemplate2']").hide();
			$("tr[name='trTemplate0'] input").attr("disabled",false);
			$("tr[name='trTemplate1'] input").attr("disabled",false);
			$("#btnPreview").show();
			var contractProName=$("#contractProName").clone();
			$("#tdOfcontractProNameBy2").html("");
			$("#tdOfcontractProNameBy1").html(contractProName);
		} if(type == 2){ // 非模板
			$("tr[name='trTemplate2']").show();
			$("tr[name='trTemplate1']").hide();
			$("tr[name='trTemplate0']").hide();
			$("tr[name='trTemplate2'] input").attr("disabled",false);
			var contractProName=$("#contractProName").clone();
			$("#tdOfcontractProNameBy1").html("");
			$("#tdOfcontractProNameBy2").html(contractProName);
			$("#btnPreview").hide();
		}
		$("#template_type_value").attr("value",type);
	};

	//清空付款信息
	function clearPayMent(){
		$("#salesCon\\.paymentWay").attr("value","");
		$("#payType").attr("value","");
		$("#salesPaymentWay").empty();
		$("#salesCon\\.cashMoney").attr("value","");
		$("#salesCashMoney").empty();
		$("#salesCon\\.batchWay").attr("value","");
		$("#salesBatchWay").empty();
		$("#salesCon\\.batchMaxMoney").attr("value","");
		$("#salesBatchMaxMoney").empty();

		$("#salesCon\\.creditTypeId").attr("value","");
		$("#salesCon\\.projectName").attr("value","");
		$("#salesCon\\.arterm").attr("value","");
		$("#salesCon\\.climit").attr("value","");
		$("#freeLimit").attr("value","");

		$("#salesCreTypeName").empty();
		$("#salesProductName").empty();
		$("#salesArterm").empty();
		$("#salesClimit").empty();
		$("#salesFree").empty();
	}
    //----------------------------------------客户信息------------------------------------------------
   	// 根据客户Id获取联系人和客户的相关信息
   	function getLinkManByCustomerId(){
		var customerId = $("#customerId option:selected").val();
		$("#telname").empty();
		$("#faxname").empty();
		$("#customerLinkmanId").empty();
		$("#provincename").empty();
		$("#cityname").empty();
		$("#invoicebankname").empty();
		$("#invoicebankaccount").empty();
		$("#taxnumbername").empty();
		$("#taxname").empty();
        clearPayMent();//客户改变，清空付款信息
		closeSubWin();//关闭弹出的窗口
		if (customerId != ""){
			$.getJSON("${ctx}/getLinkManByCustomerId.do?customerId=" + customerId+"&date="+Date(), function(linkMan){
				
				$("#customerLinkmanId").empty();
				var  options = "<option value=''>--请选择--</option>";
				$.each(linkMan,function(i,entry){
					options = options + "<option value='"+ entry['id'] + "'>" + entry['name'] + "</option>";
					if(i==0){//客户信息
						$("#customername").empty();
						$("#customername").attr("value",$("#customerId option:selected").get(0).innerHTML);						
						$("#provincename").append("<span>" + entry['province'] + "</span>");
						$("#cityname").empty();
						$("#cityname").append("<span>" + entry['city'] + "</span>");
						$("#invoicebankname").empty();
						$("#invoicebankname").append("<span>" + entry['bankName'] + "</span>");
						$("#invoicebankaccount").empty();
						$("#invoicebankaccount").append("<span>" + entry['bankAccount'] + "</span>");
						$("#taxnumbername").empty();
						$("#taxnumbername").append("<span>" + entry['taxNumber'] + "</span>");
						$("#taxname").empty();
						$("#taxname").append("<span>" + entry['invoiceType'] + "</span>");
					}				
				});
				$("#customerLinkmanId").append(options);
			});
		}else{	
		    $("#customerLinkmanId").empty();
			$("#provincename").empty();
			$("#cityname").empty();
			$("#invoicebankname").empty();
			$("#invoicebankaccount").empty();
			$("#taxnumbername").empty();
			$("#taxname").empty();
			$("#customerLinkmanId").append("<option value=''>--请选择--</option>");
		}
	};
		
	// 根据联系人Id获取联系人的相关信息
	function getLinkMsgByLinkManId() {
		var customerLinkmanId = $("#customerLinkmanId option:selected").val();
		$("#telname").empty();
		$("#faxname").empty();
		if (customerLinkmanId != ""){
			$.getJSON("${ctx}/getLinkMsgByLinkManId.do?customerLinkmanId=" + customerLinkmanId, function(LinkManMsg){
				$("#telname").empty();
				$("#telname").append("<span>" + LinkManMsg[0].tel + "</span>");
				$("#faxname").empty();
				$("#faxname").append("<span>" + LinkManMsg[0].fax + "</span>");
			});
		}else{
			$("#telname").empty();
			$("#faxname").empty();
		}
	};
	

    //----------------------------------------产品信息------------------------------------------------

	function selProdType(Obj){
		var id=Obj.selectedIndex;
		var prodTypeId=$("#hideProdTypeId").val();
		var productAll = document.getElementsByName("productCheckbox");
		
		if(productAll.length>0){//如果当前已存在产品信息
			if(prodTypeId!=""){
				if(confirm("改变产品分类将会删除以下所有产品，确定要改变产品分类吗？")){
					var allCheck = document.getElementsByName("productCheckbox");
		       			 for(i=0;i<allCheck.length;i++){
		            		allCheck[i].checked=true;
		            		}
					removeProduct();
					clearPayMent();//客户改变，清空付款信息
					closeSubWin();//关闭弹出的窗口
					Obj.options[id].selected="selected";
				}else{
					$("#productTypeId option[value="+prodTypeId+"]").attr("selected","selected");
					return;
				}
			}
		}
		var s=Obj.options[id].value;
		$("#productTypeId").attr("class","");$("#productTypeIdError").css("display","none");
		var seldeptname=document.getElementsByName("prodDeptNames")[0];
		var deptname="";
		for(var i=0;i<seldeptname.length;i++){
			if(seldeptname.options[i].value==s){
				deptname=seldeptname.options[i].text;
			}
		}

		document.getElementById("prodDeptName").innerText=deptname;
		$("#hideProdTypeId").val(Obj.options[id].value);
	}

	//进入选择产品页面
	function getProdByProdType(){	
		if($("#productTypeId").val()==""){
				$("#productTypeId").focus().attr("class","invalid");
				$("#productTypeIdError").html("<font style='font-size:12px' color='red'>请选择产品分类名称!</font>").css("display","");
				return;
		}else{$("#productTypeId").attr("class","");$("#productTypeIdError").css("display","none")}
        win1 = window.open("${ctx}/getProduct.do?prodTypeId="+$("#productTypeId").val()+"&prodTypeName="+encodeURI($("#productTypeId").find("option:selected").text(),"UTF-8"),'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=400, width=900,height=500');
		arrSubWin.push(win1);
	}
	//去除重复选择产品的项
	function newWindow(myArrayProduct,typeid){
		var prodtypeid=$("#productTypeId").val();
		if(prodtypeid!=typeid){
			alert("产品分类以改变,请重新选择");
			return;
		}
		var productids = document.getElementsByName("productids");
		for(var i=0;i<productids.length;i++){
			for(var j=0;j<myArrayProduct.length;j++){
				var id = myArrayProduct[j];
				if(productids[i].value==id[0]){
					myArrayProduct.splice(j,1);
				}
			}
		}
		insertCell(myArrayProduct);
	};
	
	//从产品小页面返回的信息,显示
	var countRow=1;
	var lastDeleteIndex=0;
	var indexRow=1;
	function insertCell(myArray){
		var averagePrice = 0;
		for(var i=0;i<myArray.length;i++){
			var arr=myArray[i];
			var table = document.getElementById("table");
			var looktable = document.getElementById("looktable");
			//添加一行
	        var newTr = table.insertRow();
	       
	        countRow++;
	        indexRow++;
	        
	        //添加十列
	        var newTd0 = newTr.insertCell();
	        var newTd1 = newTr.insertCell();
	        var newTd2 = newTr.insertCell();
	        var newTd3 = newTr.insertCell();
	        var newTd4 = newTr.insertCell();
	        var newTd5 = newTr.insertCell();
	        var newTd6 = newTr.insertCell();
	        var newTd7 = newTr.insertCell();
	        var newTd8 = newTr.insertCell();
	        var newTd9 = newTr.insertCell();
	        var newTd10 = newTr.insertCell();
	        //设置列内容和属性

	        newTd0.innerHTML = '<input type="checkbox" name="productCheckbox" id="choose'+countRow+'" value="'+arr[0]+'">';
	        newTd1.innerHTML = '';
	        newTd2.innerText = ''+arr[1];
	        newTd3.innerText = ''+arr[2];
	        newTd4.innerText = ''+arr[3];
	        newTd5.innerText = ''+arr[4];
	        newTd6.innerHTML = '<input type="text" name="count" style="width:86px;text-align:right; " id="count'+countRow+'" onclick="range(this)" onblur="autoSalesNumber(this)" value="0"><input type="hidden" name="productNumber" id="productNumber'+countRow+'" value="'+arr[5]+'"><input type="hidden" name="averagePrice" id="avagprice'+countRow+'" value="'+arr[6]+'">';
	        newTd7.innerHTML = '<input type="text" name="price" style="width:86px;text-align:right; " id="price'+countRow+'" onclick="range(this)" onblur="autoSalesPrice(this)" value="0.00"><input type="hidden" name="productsellcount" value="'+arr[7]+'" ><input type="hidden" name="totleprice" id="arth'+indexRow+'"><input type="hidden" name="productids" value="'+arr[0]+'"><input type="hidden" name="canuse" value="'+arr[5]+'">';

			newTd8.style.textAlign="right";
			newTd8.style.paddingRight="5px";
			newTd9.style.textAlign="right";
			newTd9.style.paddingRight="5px";
			newTd10.style.textAlign="right";
			newTd10.style.paddingRight="5px";
			
	        newTd8.innerHTML = ' 0.00';
	        newTd9.innerHTML = ''+arr[5];

	        newTd8.innerText = ''+'0.00';
	        newTd9.innerText = ''+arr[5];
			newTd10.innerText= ''+arr[6];
		}
		changeIndex();
		$("#tableLength").attr("value", countRow);
		if($("#table")){
					$("#table tr:even").addClass("treven");
					$("#table tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
	};
	//选中文本框内所有
	function range(obj){
	  obj.select();
	 }
	
	
	//计算销售合同总额
	function salesTotalMoney(obj,flag){
		var totalMoney = obj.value;
		var tbobj = obj.parentNode.parentNode.parentNode;
		var row = obj.parentNode.parentNode.rowIndex-1;
		var cell = obj.parentNode.cellIndex;
		if(flag=="count"){
			var scount = tbobj.rows[row].cells[cell+1].childNodes[0].value;
			tbobj.rows[row].cells[cell+2].innerText = fmoney(accMul(totalMoney,scount),2);
		}else if(flag=="price"){
			var sprice = tbobj.rows[row].cells[cell-1].childNodes[0].value;
			tbobj.rows[row].cells[cell+1].innerText = fmoney(accMul(totalMoney,sprice),2);
		}
	};
	
	// 刪除時的全选
	function checkAll(record){
   		var allCheck = document.getElementsByName("productCheckbox");
    		 if(record.checked==true){
       			 for(i=0;i<allCheck.length;i++){
            		allCheck[i].checked=true;
       		 }
   			 }else if(record.checked==false){
        		for(i=0;i<allCheck.length;i++){
            		allCheck[i].checked=false;
        		}
   	 		 }
	};
	
	// 刪除所选择的产品的相关操作
	function removeProduct(){
		judgeRemovePro();
		// 自动添加索引
		changeIndex();
		// 销售合同金额计算
		totleMoneyMethod(0);
		$("#tableLength").attr("value", countRow);
	}
	
	// 刪除所选择的产品
	function judgeRemovePro(){
		var productAll = document.getElementsByName("productCheckbox");

		if(productAll.length==0){
			alert("没有删除的产品");
		}

		var table = document.getElementById("table");
		//删除行
		var oldCount = countRow+1;
		var bol=false;
		for(var i=countRow;i>0;i--){
			var checkId = document.getElementById("choose"+i);
			if(checkId.checked==true){
				bol=true;
				table.deleteRow(i);
				countRow--;
				lastDeleteIndex=i-1;
				indexRow--;

			}
		}
		if(bol){
		//重新设置checkbox的自增ID
		for(i=lastDeleteIndex+2;i<oldCount;i++){
			var box = document.getElementById("choose"+i);
			if(box!=null){
				box.id="choose"+(++lastDeleteIndex);
			}
		}
		if(countRow==0){
		// 删除所有产品的同时删除产品名称,部门名称,传真和帐号
		    $("#productTypeId").attr("value","");
			$("#productTypeName").html("");
			$("#productDeptName").html("");
			$("#productDeptAccount").html("");
			$("#productDeptFax").html("");
			document.getElementById("prodDeptName").innerHTML="&nbsp;";
					
   			$("#chckbxSelectAllProduct").attr("checked", false);
		}
		}
	};
	//金额的格式化s为要格式化的参数（浮点型），n为小数点后保留的位数	
	function fmoney(s,n){
		n = n>0 && n<=20 ? n : 2;
		s = parseFloat((s+"").replace(/[^\d\.-]/g,"")).toFixed(n)+"";
		var l = s.split(".")[0].split("").reverse(), 
		r = s.split(".")[1]; 
		t = "";
		for(i = 0;i<l.length;i++){
			t+=l[i]+((i+1)%3==0 && (i+1) != l.length ? "," : ""); 
		}
		return t.split("").reverse().join("")+"."+r;
	}

	 //自动添加序号
    function changeIndex(){
    	var table =$("#table").get(0);
    	var tlength = table.rows.length;
    	for(var i=1;i<tlength;i++){
    		table.rows[i].cells[1].innerHTML = i;
    	}
    }	
		
	//  自动计算销售数量
	function autoSalesNumber(obj){
		var salesCount = obj.value;
		var flag = "count";
		 
		if(/^[1-9]\d{0,10}$/.test(salesCount) || salesCount==0){
			salesTotalMoney(obj,flag);
		 	totleMoneyMethod(0);
		}else{
			alert("销售数量必须是正整数");
			obj.focus();
			return;
		}
	}

	// 自动计算销售单价
	function autoSalesPrice(obj){
		var salesPrice = obj.value;
		var flag = "price";
		if($.trim(salesPrice)==""){
			alert("销售单价不能为空");
			obj.focus();
			return;
		}
		if(/^[1-9](\d{1,7})?(\.\d{1,2})?$/.test(salesPrice) || /^0{1}\.\d{1,2}$/.test(salesPrice) || salesPrice==0){
			salesTotalMoney(obj,flag);
		 	totleMoneyMethod(0);
		}else{
			alert("销售单价必须是数值型且保留两位小数");
			obj.focus();
			return;
		}
	}
	
	//还原金额   
	function rmoney(s){ 
		return parseFloat(s.replace(/[^\d\.-]/g,""));  
	} 
	
	//销售金额合计
	function totleMoneyMethod(flg){
		var tableObj = document.getElementById("table");
		var tlength = tableObj.rows.length;
		var vtotle = 0;
		var arr = new Array();
		for(var i=1;i<tlength;i++){
		    var countid = "count"+i;
		    var priceid = "price"+i;
		    var num = $("#"+countid).val(); //销售数量
		    var price = $("#"+priceid).val(); //销售单价
		    var unitPrice = rmoney(tableObj.rows[i].cells[7].innerText);
			var money = rmoney(tableObj.rows[i].cells[8].innerText);
			vtotle = accAdd(vtotle,money);
			$("#"+countid).removeClass("invalid");
			$("#"+priceid).removeClass("invalid");
			if(flg==1)
			{
				if(num==0)
				{
					alert("销售数量不能为零");
					$("#"+countid).addClass("invalid");
					return false;
				}
				//if(price==0)
				//{
				//	alert("销售单价不能为零");
				//	$("#"+priceid).addClass("invalid");
				//	return false;
			//	}
			}
		}
		
		$("#totalmoney").html(fmoney(vtotle,2));
		$("#totalmoneyTO").val(vtotle);
		
		var paymentWay = $("#salesCon\\.paymentWay").val();
			
		
		if(paymentWay !="" && paymentWay == 0) {
			$("#salesCashMoney").html(fmoney(vtotle,2) + " 元");
		}
	}		
    //----------------------------------------付款信息------------------------------------------------
	//进入选择支付方式页面
	function selectPaymentType(){
		if(check(1)==false){
			return false;
		}

	    var tmoney = $("#totalmoneyTO").val()-0;//合同金额
		//if(tmoney<=0){
		  //  alert("请选择产品");
		//	return false;
		//}
		var typename=$("#productTypeId option:selected").text().trim();
		var typeid= $("#productTypeId").val();
		var cusname = $("#customerId option:selected").text();
		var customerId=$("#customerId").val();
		var totalmoney=$("#totalmoneyTO").val();

		var checkResult = true;
		$("#table1 :checkbox").each(function(){
			
			if (checkResult == true) {
				
				var pid = this.value;
				
				var count = $("#count"+pid).val()-0;
				
				removeInvalidClass("count" + pid);
				if (count == 0) {
					alert("销售数量不能为零");
					checkResult = false;
					addInvalidClass("count" + pid);
					return ;
				}
				
				var price = $("#price"+pid).val()-0;
				
				removeInvalidClass("price" + pid);
			//	if (price == 0) {
				//	alert("销售单价不能为零");
				//	checkResult = false;
			//		addInvalidClass("price" + pid);
			//		return ;
			//	}
			}
		});	

        if(checkResult){
			var win2 = window.open('${ctx}/getPaymentWay.do?typename='+encodeURI(typename,"UTF-8")+'&customername='+encodeURI(cusname,"UTF-8")+'&totalmoney='+fmoney(totalmoney,2)+'&prodTypeId='+typeid+'&customerId='+customerId,'newwindow', popWindows(400));
			arrSubWin.push(win2);
		}
	}
	
	var countRow=0;//新增的行数,也是checkbox的id值
	var lastDeleteIndex=-1;
	var indexRow=0;//序号
	
    //----------------------------------------发货信息------------------------------------------------				
	//进入发货地址页面
	function selectAddress(){
		if(check(2)==false){
			return false;
		}
		hideAddText();
		$("#addressAddOrSelect").val("1");
        var customerId=$("#customerId").val(); 
		 win3 = window.open('${ctx}/getSendGoodsAddress.do?customerId='+customerId,'newwindow', popWindows(250));
		arrSubWin.push(win3);
	}
	

	//获取当前日期转化问题
	function getNowFormatDate(){
		var dateObj = new Date();
		var Year = 0;
		var Month = 0;
		var Day = 0;
		var CurrentDate = "";
		var Year = dateObj.getYear();
		var Month = dateObj.getMonth()+1;
		var Day = dateObj.getDate();
		CurrentDate += Year+"-";
		if(Month>=10){
			CurrentDate =+Month+"-";
		}else{
			CurrentDate += "0"+Month+"-";
		}
		if(Day>=10){
			CurrentDate += Day;
		}else{
			CurrentDate += "0"+Day;
		}
		return CurrentDate;
	}
	var formId = 0;
		//控件名
	var checknNames = [ "addreveivename", "addaddressname", "addreveivelinkman","addreceivetel","addpostcode","addreceivemobile"];
	//提示语
	var descriptions = [ "货物接收单位名称", "发货地址", "联系人", "电话","邮编","手机"];
	//是否非空验证,如果非空验证填写notnull,如果不需要非空验证传空参
	var checkNulls = [ "notnull", "notnull", "notnull", "notnull" ,"",""];
	//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”
	var checkTypes = [ "", "", "", "phone","num","num"];
	//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
	var checkLengths = [ "40", "80", "8", "20" ,"06","12"];
	// 保存提交
	function subSales(method){
	
		if(check(0)==false){
			return false;
		}
		if(checkTextAreaLen(200) == false){
			return ;
		}
		if (method ==1) {
			$("#method").attr("value",1);
		}
		if (method ==2) {
			$("#method").attr("value",2);
		}
		if (method ==3) {
			$("#method").attr("value",3);				
		}
		 if($("#addressAddOrSelect").val() !=1 ){
			if(checkForm()==false){
				return ;
			}
		}
        //setTextArea();
		$("#salesConForm").submit();
	}
	//对控件进行Check. flg说明：0为提交时调用;1付款方式;2发货地址
	function check(flg){
		if($("#contractProName").val()==""){
			$("#contractProName").focus().attr("class","invalid");
			if($("#contractProName").next().attr("class")!="invalid"){
				$("#contractProName").after("<span class='invalid'>必填字段</span>");
			}
			return false;
		}else{
			$("#contractProName").attr("class","");
			$("#contractProName").next().remove();
		}
		 
		if($("#customerId").val() == null || $("#customerId").val()==""){
			$("#customerId").focus().attr("class","invalid");
			if($("#customerId").next().attr("class")!="invalid"){
				$("#customerId").after("<span class='invalid'>必填项</span>");
			}
			return false;
		}else{
			$("#customerId").attr("class","");
			$("#customerId").next().remove();
		}
		 if($("#customerLinkmanId").val() == null || $("#customerLinkmanId").val()==""){
			 $("#customerLinkmanId").focus().attr("class","invalid");
				if($("#customerLinkmanId").next().attr("class")!="invalid"){
					$("#customerLinkmanId").after("<span class='invalid'>必填字段</span>");
				}
				return false;
			}else{
				$("#customerLinkmanId").attr("class","");
				$("#customerLinkmanId").next().remove();
				
			}
	    var templateType = $(":radio:checked").val();
		
		if (templateType == "2") {
			//非模板，检查合同文件
			$("#contractFile").removeClass("invalid");
			if ($("#contractFile").val() == "") {
				alert("请添加合同模板文件");
				$("#contractFile").addClass("invalid");
				return false;
			}
		}
	    
		if (templateType == "1" || templateType == "0") {
			//自定义模板，检查保价期限，验收期限
			var reg = /^[1-9]\d{0,10}$/;
			var protectLimit = $("#protectLimit").val();
			var place = $("#place").val();
            var breach = $("#breach").val();
            var lawsuit = $("#lawsuit").val();

            $("#place").removeClass("invalid");
			$("#breach").removeClass("invalid");
			$("#lawsuit").removeClass("invalid");
			$("#protectLimit").removeClass("invalid");

            if(place==""){
			    alert("签订地址不能为空");
				$("#place").addClass("invalid");
				return false;
			}
			if(breach==""){
			    alert("违约金标准不能为空");
				$("#breach").addClass("invalid");
				return false;
			}
			if(lawsuit==""){
			    alert("诉讼地不能为空");
				$("#lawsuit").addClass("invalid");
				return false;
			}

			if(reg.test(protectLimit) == false || protectLimit==0){			
				alert("保价期限必须为正整数");				
				$("#protectLimit").addClass("invalid");
				return false;
			}
			
			var checkLimit = $("#checkLimit").val();
			$("#checkLimit").removeClass("invalid");
			if(reg.test(checkLimit) == false || checkLimit==0){		
				alert("验收期限必须为正整数");	
				$("#checkLimit").addClass("invalid");			
				return false;
			}
		}
	    if (templateType == "2"){
			var uploadFileName=$("#contractFile").val();
            uploadFileName=uploadFileName.substring(uploadFileName.lastIndexOf(".")+1,uploadFileName.length).toLowerCase();
			var prec=["txt","pdf","xml","rtf","doc","docx","xls","xlsx"];
			var j=0;
			for(var i=0;i<prec.length;i++){
				if(uploadFileName==prec[i]){
					j=1;
					break;
				}
			}
			if(j==0){
				$("#file").focus().attr("class","invalid");
				$("#fileNameError").text("格式只能为txt、pdf、xml、rtf、doc、docx、xls、xlsx类型！");
				return false;
			}	
		}
	    
	    var pid = $("#productTypeId").val();
	    if(pid==""){
	    	alert("请选择产品分类");
	    	return false;
	    }
	    if($("#table tr").length==1){
	    	alert("请选择产品");
	    	return false;
	    }
		if(totleMoneyMethod(1)==false)
		{
			return false;
		}
	    if(flg!=1){
			var pt=$("#payType").val();  
			if(pt==""){
				alert("请选择付款方式");
				return false;
			}
		}
		if(flg==0){
			if(checkMoney()==false){
			    return false;
			}
			var address = $("#customerAddressId").val();
			if(address==""){
				alert("请选择发货地址");
				return false;
			}

			// 判断是否选要求发货日期
			var sendsDate = $("#salesCon\\.sendsDate").val();
			var today = getNowFormatDate();
			var nowDate = new Date(today.replace("-",",")).getTime();
			var requestSendDate = new Date(sendsDate.replace("-",",")).getTime();
			$("#salesCon\\.sendsDate").removeClass("invalid");
			if(nowDate>requestSendDate || sendsDate==""){
				alert("发货日期必须大于或等于当前日期");
				//$("#salesCon\\.sendsDate").focus();
				$("#salesCon\\.sendsDate").addClass("invalid");
				return false;
			}
		}		  
	}

	//将货币格式还原成数值型
	function revertNumber(obj){
		var tmp = obj.split(",");
		var num = tmp.join("");
		return parseFloat(num);
	}

	//提交时对销售金额与付款金额进行check
	function checkMoney(){		
		
		var paymentWay = revertNumber($("#salesCon\\.paymentWay").val()); 
		var tmoney     = revertNumber($("#totalmoneyTO").val());//合同金额
		var cmoney     = revertNumber($("#salesCon\\.cashMoney").val());//现结金额
		//var limit      = revertNumber($("#freeLimit").val());//可用信用 Del By ChenHJ @2010-07-29
		var limit      = revertNumber($("#salesCon\\.climit").val());//信用额度 Add By ChenHJ @2010-07-29
		var bmoney     = revertNumber($("#salesCon\\.batchMaxMoney").val());//分批最大金额		
	
		if(paymentWay==0){//全部现结
		    $("#salesCon\\.cashMoney").attr("value",tmoney);
		}
			
		if(paymentWay==1){//全部信用
			//不分批 信用金额大于等于合同金额  分批，分批金额小于等于信用金额
			if(((bmoney<=0||isNaN(bmoney))&&(tmoney > limit))||(bmoney>limit)){
				alert("信用额度不足,请重新选择信用类型");
				return false;
			}
			//分批最大金额必须小于合同金额
			if(bmoney>(tmoney)){
				alert("分批最大金额必须小于合同金额");
				return false;
			}				
		}
		if(paymentWay==2){//部分现结部分信用
			//如果合同金额不为0 则销售金额必须大于现结金额 如果合同金额为0 则现结金额不能大于0 
			if(((cmoney)>=(tmoney)&&tmoney!=0)||(cmoney>0&&tmoney==0)){
				alert("现结金额必须小于销售金额");
				return false;
			}
			//如果不分批 信用金额必须大等于于合同金额减现结金额  分批，最大分批金额等于小于信用金额
			if(((bmoney<=0||isNaN(bmoney))&&(limit<tmoney-cmoney))||(bmoney>0&&(limit<bmoney))){
				alert("信用额度不足");
				return false;	
			}
			//分批金额小于合同金额
			if(bmoney>=(tmoney)){
				alert("分批最大金额必须小于合同金额");
				return false;
			}
			//分批金额加现结金额小于等于合同金额
			if(tmoney<cmoney+bmoney){
				alert("现结金额与分批金额之和不能大于合同金额");
				return false;
			}
			
		}	
	}
   	//删除 invalid css
   	function removeInvalidClass(elementId) {
   		$("#"+elementId).removeClass("invalid");
   		
   	}
</script>
</head>
<body>
<div id="newbulid">
  <html:form action="/addSalesContract"  method="post" styleId="salesConForm" enctype="multipart/form-data">
    <input type="hidden" id="tableLength" value="0"/>
    <input type="hidden" id="method" value="" name = "method"/>
    <input type="hidden" name="money" id="totalmoneyTO" value="0.00">
    <input type="hidden" name="addressAddOrSelect" id="addressAddOrSelect" value="1">
    <input type="hidden" id="customerAddressId" name="customerAddressId" value="0"/>
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td class="ye_header_left">&nbsp;</td>
        <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" /> &nbsp;当前位置： 销售管理 &gt;&gt; 销售合同管理 &gt;&gt; 新建销售合同 </td>
        <td class="ye_header_right">&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td valign="middle">
        <br />
        <div class="div_tiao"> &nbsp;&nbsp;合同信息 </div>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" width="20%"> 盖章类型 </td>
            <td class="td_02" width="30%">合同章
              <input type="hidden" name="stampType" id="stampType" value = "1">
            </td>
            <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;模版类型 </td>
            <td class="td_02" width="30%"><input type="hidden" name="templateType" id="template_type_value" value = "0">
              <input name="templateTypes" type="radio" value="0" checked="checked"
										onclick="checkradio();" />
              标准模版
              <input type="radio" name="templateTypes" value="1"
										onclick="checkradio();" />
              自定义模版
              <input type="radio" name="templateTypes" value="2"
										onclick="checkradio();" />
              非模版 </td>
          </tr>
          <tr id="placeProtect" name="trTemplate0">
            <td class="td_01"> 签订地点 </td>
            <td class="td_02"><input name="place" type="text" id="place"
										style="width:120px;" value="${sellContract.place}"
										disabled="disabled" maxlength="10"/>
            </td>
            <td class="td_01"> 保价期限 </td>
            <td class="td_02"><input name="protectLimit"   type="text" id="protectLimit" style="width:120px;" value="${sellContract.protectLimit}" disabled="disabled" maxlength="3" />
              日 </td>
          </tr>
          <tr id="checkBreach" name="trTemplate0">
            <td class="td_01"> 验收期限 </td>
            <td class="td_02"><input name="checkLimit" type="text" id="checkLimit"
										style="width:120px;" value="${sellContract.checkLimit}"
										disabled="disabled" maxlength="3"/>
              日 </td>
            <td class="td_01"> 违约金标准 </td>
            <td class="td_02"><input name="breach" type="text" id="breaks"
										style="width:120px;" value="${sellContract.breach}"
										disabled="disabled" maxlength="10"/>
            </td>
          </tr>
          <tr id="lawsuitJuge" name="trTemplate0">
            <td class="td_01"> 诉讼地 </td>
            <td class="td_02"><input name="lawsuit" type="text" id="lawsuit"
										style="width:240px;" value="${sellContract.lawsuit}"
										disabled="disabled" maxlength="20"/>
            </td>
            <td class="td_01" ><span style="color:#FF0000">*</span>&nbsp;项目名称</td>
            <td class="td_02" id="tdOfcontractProNameBy1"><input name="contractProName" type="text" id="contractProName" maxlength="20"/></td>
          </tr>
          <tr style="display:none" id="clause" name="trTemplate1">
            <td class="td_01"> 合同附加条款 </td>
            <td colspan="3" class="td_02"><textarea name="clause" id="textarea2" cols="100"
										rows="4" maxlength="200"></textarea>
            </td>
          </tr>
          <tr style="display:none" id="filename" name="trTemplate2">
            <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;选择文件 </td>
            <td class="td_02">
			  <input type="file"  id="contractFile" name="contractFile" onchange="showFile();" style="width:240px;" />
              <SPAN id="fileNameError" style="font-size:12px;color:#F00"></SPAN>
            </td>
            <td class="td_01" ><span style="color:#FF0000">*</span>&nbsp;项目名称</td>
            <td class="td_02" id="tdOfcontractProNameBy2"></td>
          </tr>
        </table>
        <br />
        <div class="div_tiao"> &nbsp;&nbsp;客户信息 </div>
        <table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
          <tr>
            <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;客户名称
              <input type="hidden" id="customername" name= "customerName"/>
            </td>
            <td class="td_02" width="30%"><select name="customerId" id="customerId"
										style=" width:286px" onchange="getLinkManByCustomerId();">
                <option value=""> --请选择-- </option>
                <logic:present name="customerList">
                  <logic:iterate id="customer" name="customerList">
                    <option value="${customer.customerId}"> ${customer.customerName} </option>
                  </logic:iterate>
                </logic:present>
              </select>
            </td>
            <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;联系人 </td>
            <td class="td_02" width="30%"><select name="customerLinkmanId" id="customerLinkmanId"
										style=" width:126px" onchange="getLinkMsgByLinkManId();">
                <option value=""> --请选择-- </option>
              </select>
            </td>
          </tr>
          <tr>
            <td class="td_01" height="18px"> 省份 </td>
            <td class="td_02" id="provincename">&nbsp;</td>
            <td class="td_01"> 电话 </td>
            <td class="td_02" id="telname">&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px"> 城市 </td>
            <td class="td_02" id="cityname">&nbsp;</td>
            <td class="td_01"> 传真 </td>
            <td class="td_02" id="faxname">&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px"> 开户行 </td>
            <td class="td_02" id="invoicebankname">&nbsp;</td>
            <td class="td_01"> 帐号 </td>
            <td class="td_02" id="invoicebankaccount">&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px"> 税号 </td>
            <td class="td_02" id="taxnumbername">&nbsp;</td>
            <td class="td_01"> 发票类型 </td>
            <td class="td_02" id="taxname">&nbsp;</td>
          </tr>
        </table>
        <br />
        <div class="div_tiao">&nbsp;&nbsp;产品信息</div>
        <input type="hidden" id="hideProdTypeId" value=""/>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" width="20%" height="18px">产品分类名称</td>
            <td class="td_02" width="30%" ><html:select property="productTypeId" styleId="productTypeId" onchange="selProdType(this)" style="width:126px">
                <html:option value="">--请选择--</html:option>
                <html:options collection="productList" property="prodId" labelProperty="prodName"/>
              </html:select>
              <span id="productTypeIdError"></span>
              <html:select property="prodDeptNames" value="" style="display:none" >
                <html:options collection="productList" property="prodId" labelProperty="prodDeptName"/>
              </html:select>
            </td>
            <td class="td_01" width="20%">产品部门名称</td>
            <td class="td_02" width="30%" id="prodDeptName">&nbsp;${productDeptName}</td>
          </tr>
        </table>
        <div style="width:100%; padding:5px 0px 1px 0px;">
        <div style="float:left;">&nbsp;&nbsp;<a href="#" onclick="getProdByProdType()"><img src="${ctx}/images/btnCPXZ.gif" width="70" height="20" /></a></div>
        <div style=" float:right; padding-top:10px;">单位：元</div>
        </div>
        <table id="table1" width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" >
          <tr id="firstTr">
            <th nowrap="nowrap" width="30"> 选择 </th>
            <th nowrap="nowrap" width="40"> 序号 </th>
            <th nowrap="nowrap" name="productId" id="productId"> 产品编码 </th>
            <th nowrap="nowrap"> 产品名称 </th>
            <th nowrap="nowrap"> 规格型号 </th>
            <th nowrap="nowrap" id="productUnit"> 单位 </th>
            <th nowrap="nowrap" width="108"> 销售数量 </th>
            <th nowrap="nowrap" width="108"> 销售单价 </th>
            <th nowrap="nowrap" id="xiaoshoujine" width="93"> 销售金额 </th>
            <th nowrap="nowrap" width="80px"> 销售可用数 </th>
            <th nowrap="nowrap" width="80px">限价</th>
          </tr>
          <tbody id="table">
            <tr></tr>
          </tbody>
          <tr id="lastTr" style="display:none"></tr>
        </table>
        <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
              <tr>
                <td width="30">
                  &nbsp;<input type="checkbox" name="checkbox3" id="chckbxSelectAllProduct" onclick="checkAll(this);"/>
                </td>
                <td width="40"> 全选 </td>
                <td width="100" align="center"><a href="javascript:removeProduct();"><img src="${ctx}/images/btnDelete.gif" /></a> </td>
                <td align="right"> 销售金额合计 </td>
                <td align="right"  id="totalmoney"  width="93">0.00&nbsp;</td>
              <td align="left" height="25" width="82">&nbsp;元&nbsp; </td>
              </tr>
            </table>
        <br />
        <div class="div_tiao"> &nbsp;&nbsp;付款信息 </div>
          <a href="#" onclick="selectPaymentType();"><img src="${ctx}/images/btnFKFS.gif" width="99" height="20" /></a>
        <input type="hidden" name="paymentWay" id="salesCon.paymentWay"/>
        <input type="hidden" id="payType" value=""/>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
							<tr>
								<td class="td_01" width="20%" height="18px">
									付款方式
								</td>
								
								<td class="td_02" id="salesPaymentWay"  width="30%">&nbsp;
									
								</td>
								<td class="td_01" width="20%">
									现结金额(元)
								</td>
								<input type="hidden" name="cashMoney" id="salesCon.cashMoney" value=""/>
								<td class="td_02" id="salesCashMoney" width="30%">&nbsp;
								</td>
							</tr>
							<tr id="td_fenpi">
								<td class="td_01" height="18px">
									分批方式
								</td>
								<input type="hidden" name="batchWay" id="salesCon.batchWay"/>
								<td class="td_02" id="salesBatchWay">&nbsp;
									
								</td>
								<td class="td_01">
									单批最大金额(元)
								</td>
								<input type="hidden" name="batchMaxMoney" id="salesCon.batchMaxMoney"/>
								<td class="td_02" id="salesBatchMaxMoney">&nbsp;
									
								</td>
							</tr>
						</table>
						<br />
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table1">
							<input type="hidden" name="customerCreditId" id="salesCon.cusCreditId"/>
							<input type="hidden" name="creditTypeId" id="salesCon.creditTypeId"/>
							<input type="hidden" name="projectName" id="salesCon.projectName"/>
							<input type="hidden" name="arterm" id="salesCon.arterm"/>
							<input type="hidden" name="climit" id="salesCon.climit"/>
							<input type="hidden" name="freeLimit" id="freeLimit"/>
							<tr>
								<th nowrap="nowrap">
									信用类型
								</th>
								<th nowrap="nowrap">
									项目名称
								</th>
								<th nowrap="nowrap">
									帐期(天)
								</th>
								<th nowrap="nowrap">
									信用额度(元)
								</th>
								<th nowrap="nowrap">
									可用额度(元)
								</th>
							</tr>
							<tr>
								<td nowrap="nowrap" id="salesCreTypeName" height="18px">&nbsp;
									
								</td>
								<td nowrap="nowrap" id="salesProductName">&nbsp;
									
								</td>
								<td nowrap="nowrap" style="text-align:right" id="salesArterm">&nbsp;
								
								</td>
								<td nowrap="nowrap" style="text-align:right" id="salesClimit">&nbsp;
								
								</td>
								<td nowrap="nowrap" style="text-align:right" id="salesFree">&nbsp;
								
								</td>
							</tr>
						</table>

						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;发货信息
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="60%">
									&nbsp;&nbsp;<a href="#" onclick="selectAddress();"><img
											src="${ctx}/images/btnFHDZ.gif" width="99" height="20" /></a>&nbsp;&nbsp;<img id="addBUtton" src="${ctx}/images/btnFHDZTJ1.gif" width="99" height="20" />
								</td>
							</tr>
						</table>
						<input type="hidden" id="customerAddressId" name="customerAddressId" />
        <table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
          <tr id="trUp1">
            <td class="td_01" height="18px"> 货物接收单位名称 </td>
            <td class="td_02" id="reveivename">&nbsp;</td>
            <td class="td_01" height="18px"> 发货地址 </td>
            <td colspan="3" class="td_02" id="addressname">&nbsp;</td>
          </tr>
          <tr id="trUp2">
            <td class="td_01" height="18px" width="20%"> 联系人 </td>
            <td class="td_02" id="reveivelinkman" width="30%">&nbsp;</td>
            <td class="td_01" width="20%"> 邮编 </td>
            <td class="td_02" id="postcode" width="30%">&nbsp;</td>
          </tr>
          <tr id="trUp3">
            <td class="td_01" height="18px"> 电话 </td>
            <td class="td_02" id="receivetel">&nbsp;</td>
            <td class="td_01"> 手机 </td>
            <td class="td_02" id="receivemobile">&nbsp;</td>
          
          
          
          <tr id="trFt1">
            <td class="td_01" height="18px"> <span id="x4" style="color:#FF0000">*</span>&nbsp;货物接收单位名称 </td>
            <td class="td_02" ><input type="text" id="addreveivename" name="customerAddressName" style="width:240px;" maxlength="20">&nbsp;</td>
             <td class="td_01" height="18px"><span id="x4" style="color:#FF0000">*</span>&nbsp; 发货地址 </td>
            <td colspan="3" class="td_02" ><input type="text" id="addaddressname" name="customerAddressAddress"  style="width:280px;"  maxlength="40">&nbsp;</td>

          </tr>
          <tr id="trFt2">
            <td class="td_01" height="18px" width="20%"> <span id="x4" style="color:#FF0000">*</span>&nbsp;联系人 </td>
            <td class="td_02" width="30%"><input type="text" id="addreveivelinkman" name="customerAddressLinkman" style="width:120px;" maxlength="4">&nbsp;</td>
            <td class="td_01" width="20%"> 邮编 </td>
            <td class="td_02" width="30%"><input type="text" id="addpostcode" name="customerAddressPostcode" style="width:120px;" maxlength="6">&nbsp;</td>
          </tr>
          <tr id="trFt3">
            <td class="td_01" height="18px"><span id="x4" style="color:#FF0000">*</span>&nbsp; 电话 </td>
            <td class="td_02" ><input type="text" id="addreceivetel" name="customerAddressTel" style="width:120px;" maxlength="20">&nbsp;</td>
            <td class="td_01">手机 </td>
            <td class="td_02" ><input type="text" id="addreceivemobile" name="customerAddressMobile" style="width:120px;" maxlength="12">&nbsp;</td>
          </tr>
          
           <tr>
            
            <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;要求发货日期&nbsp; </td>
            <td class="td_02"><input type="text" name="requestSendDate"
										id="salesCon.sendsDate" style="width:120px;"  onfocus="calendarMinToday()"
										readonly="readonly" />
            </td>
            <td class="td_01" height="18px">&nbsp; </td>
            <td class="td_02" >&nbsp;</td>
          </tr>
        </table>
        <br />
        <div class="div_tiao"> &nbsp;&nbsp;供方信息 </div>
        <table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
          <input type="hidden" name="sellcontract.user_id" value="leizhao" id="user_id"/>
          <input type="hidden" name="sellcontract.user_name" value="${sellinfo.user_name}"/>
          <input type="hidden" name="sellcontract.user_area_id" value="110"/>
          <input type="hidden" name="sellcontract.user_area_name" value="${sellinfo.user_area_name }"/>
          <input type="hidden" name="sellcontract.tel" value="${sellinfo.tel }"/>
          <tr>
            <td class="td_01" width="20%" height="18px"> 人员名称 </td>
            <td class="td_02" width="30%"> ${sellContract.userName} </td>
            <td class="td_01" width="20%"> 人员区域 </td>
            <td class="td_02" width="30%"> ${sellContract.userAreaName} </td>
          </tr>
          <tr>
            <td class="td_01" height="18px"> 电话 </td>
            <td class="td_02"> ${sellContract.userTel} </td>
            <td class="td_01"> 传真 </td>
            <td class="td_02" id="productDeptFax">&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px"> 帐号 </td>
            <td class="td_02" id="productDeptAccount">&nbsp;</td>
            <td class="td_01">&nbsp;</td>
            <td class="td_02">&nbsp;</td>
          </tr>
        </table>
        <br />
        <div class="div_tiao"> &nbsp;&nbsp;特殊说明 </div>
        <table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
          <tr>
            <td class="td_03" width="20%"> 特殊说明 </td>
            <td class="td_04"><textarea name="text" id="text" cols="100" rows="4"></textarea>
            </td>
          </tr>
        </table>
        </td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td></td>
        <td height="50px" align="center" valign="bottom"><a href="#" onclick="subSales(1);">
        <img src="${ctx}/images/btnSave.gif" width="65" height="20" /></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="#" onclick="subSales(2);"> <img src="${ctx}/images/btnSubmit.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="${ctx}/getSalesContractsList.do?init=true"><img src="${ctx}/images/btnBack.gif" /></a>
        </td>
        <td></td>
      </tr>
    </table>
    <br />
  </html:form>
</div>
</body>
</html>

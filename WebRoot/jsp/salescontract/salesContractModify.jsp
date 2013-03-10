<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" import="cn.com.thtf.egov.cms.constant.Constants" %>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>销售合同修改</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
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
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/contractUtil.js"></script>
		<script type="text/javascript" src="${ctx}/js/math.js"></script>
		<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>		
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
	<script type="text/javascript">
	var win1;
var win3;
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
	$(document).ready(function(){
		var type = $(":radio:checked[name='templateType']").val();
		var contractProName=$("#contractProName").clone();
		if(type==2){
			$("#tdOfcontractProNameBy1").html("");
			$("#tdOfcontractProNameBy2").html(contractProName);
		}else{
			$("#tdOfcontractProNameBy2").html("");
			$("#tdOfcontractProNameBy1").html(contractProName);
		}
		
		hideAddText();
	
				if($("#tableContractDetail")){
					$("#tableContractDetail tr:even").addClass("treven");
					$("#tableContractDetail tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
				if($("#table1")){
					$("#table1 tr:even").addClass("treven");
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


				$("#customerAddressId").val("0"); 

					


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
				var addressid= document.getElementById("_customerAddressId").value ; 
				var reveivename = document.getElementById("reveivename").innerHTML; 
				var addressname = document.getElementById("addressname").innerHTML;
				var postcode =document.getElementById("postcode").innerHTML;
				var reveivelinkman =document.getElementById("reveivelinkman").innerHTML;
				var receivetel = document.getElementById("receivetel").innerHTML;
				var receivemobile =document.getElementById("receivemobile").innerHTML;
				
				$("#customerAddressId").val(addressid); 
				$("#addreveivename").attr("value",reveivename);
				$("#addaddressname").attr("value",addressname);
				$("#addreveivelinkman").attr("value",reveivelinkman);
				$("#addpostcode").attr("value",postcode);
				$("#addreceivetel").attr("value",receivetel);
				$("#addreceivemobile").attr("value",receivemobile);

			}
	//页面载入时的动作
	$(function() {
		
		var templateType = "${contract.templateType}";
		$("#tableAssessIdea :checkbox").attr("disabled", true);
		selectTemplateType();		
		reCheckContractProduct();

		
		var command = "${param.command}";
		
		if (command == "preview") {
			previewContract('${ctx}',"${param.contractId}");			
		}
				
		//服务器端消息
		var msg = "${msg}";
		
		if (msg != "") {
			alert(msg);
		}
	
	});
	
		function showFile(){//文件名显示
			$("#contractFile").val($("#file").val());
			$("#file").attr("class","");
			$("#fileNameError").text("");
		}	

		//清空付款信息
		function clearPayMent(){
			$("#salesCon\\.cusCreditId").attr("value","");
			$("#salesCon\\.creditTypeId").attr("value","");
			$("#salesCon\\.projectName").attr("value","");
			$("#salesCon\\.arterm").attr("value","");
			$("#salesCon\\.climit").attr("value","");

			$("#salesCon\\.paymentWay").attr("value","");
			$("#salesCon\\.batchWay").attr("value","");
			$("#salesCon\\.batchMaxMoney").attr("value","");
			$("#salesCon\\.cashMoney").attr("value","");
			$("#freeLimit").attr("value","");
            
			$("#salesPaymentWay").empty();
			$("#salesCashMoney").empty();
			$("#salesBatchWay").empty();
			$("#salesBatchMaxMoney").empty();
            
			$("#salesCreTypeName").empty();
			$("#salesProductName").empty();
			$("#salesArterm").empty();
			$("#salesClimit").empty();
			$("#salesFree").empty();
		}

	   	// 根据客户Id获取联系人和客户的相关信息
	   	function getLinkManByCustomerId() {
			
			var customerId = $("#customerId option:selected").val();
			clearPayMent();//客户改变，清空付款信息
			closeSubWin();//关闭弹出的窗口
			if (customerId != "")
			{
				$("#telname").empty();
				$("#faxname").empty();
				
				$.getJSON("${ctx}/getLinkManByCustomerId.do?customerId=" + customerId + "&date=" + Date(), function(linkMan){
					$("#customerLinkmanId").empty();
					var  options = "<option value=''>--请选择--</option>";
					$.each(linkMan,function(i,entry){
						options = options + "<option value='"+ entry['id'] + "'>" + entry['name'] + "</option>";
						
						if(i==0){
							$("#customername").empty();
							$("#customername").attr("value",$("#customerId option:selected").get(0).innerHTML) ;
							$("#provincename").empty();
							$("#provincename").append(entry['province']);
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
			}
			else
			{	$("#customerLinkmanId").empty();
				$("#provincename").empty();
				$("#cityname").empty();
				$("#invoicebankname").empty();
				$("#invoicebankaccount").empty();
				$("#taxnumbername").empty();
				$("#telname").empty();
				$("#faxname").empty();
				$("#taxname").empty();
				$("#customerLinkmanId").append("<option value=''>--请选择--</option>");
			}
		};
		
		// 根据联系人Id获取联系人的相关信息
		function getLinkMsgByLinkManId() {
			var customerLinkmanId = $("#customerLinkmanId option:selected").val();
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
			
		//进入发货地址页面
		function selectAddress(){
			var customerId = $("#customerId option:selected").val();
			
			if(customerId == ""){
				alert("客户名称不能为空！");
				return false;
			}		
		hideAddText();
		$("#addressAddOrSelect").val("1");
			win3=window.open('${ctx}/getSendGoodsAddress.do?customerId='+customerId,'newwindow', popWindows(250));
		};
	
		
		//金额的格式化s为要格式化的参数（浮点型），n为小数点后保留的位数	
		function formatMoney(s,n){
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
		
	

		function insertPaymentWayRecords(myArrayPayTypeSelect){
		
			for(var i=0;i<myArrayPayTypeSelect.length;i++){
				$("#salesCreTypeName").html(myArrayPayTypeSelect[0]);
				$("#salesProductName").html(myArrayPayTypeSelect[1]);
				$("#salesArterm").html(myArrayPayTypeSelect[2]);
				$("#salesClimit").html(myArrayPayTypeSelect[3]);
				$("#salesFree").html(myArrayPayTypeSelect[4]);
				
				//设置隐藏域的值
				$("#projectName").val(myArrayPayTypeSelect[1]);
				$("#arterm").val(myArrayPayTypeSelect[2]);
				$("#climit").val(myArrayPayTypeSelect[3]);
			}	
		}
		
		 //自动添加序号
	    function changeIndex(){
	    	var contractDetail = $("#tableContractDetail").get(0);
	    	var detailCount = contractDetail.rows.length;
	    	for(var i=1; i<detailCount; i++){
	    		contractDetail.rows[i].cells[1].innerHTML = i;
	    	}
	    }	
		
	
		//进入选择支付方式页面
		function selectPaymentType(){
	
			//客户id,name
			var customerId = $("#customerId option:selected").val();
			var customerName = $("#customerId option:selected").html();	
			if(customerId == ""){
				alert("请先选择客户");
				return false;
			}
			
			var customerLinkmanId = $("#customerLinkmanId option:selected").val();		
			if(customerLinkmanId == ""){
				alert("请选择联系人");
				return false;
			}
			//产品数量
			var productCount = $("#tableContractDetail tr[name='productTR']").length;
			if(productCount == 0){
				alert("请选择产品");
				return false;
			}
			
			//产品名，产品id 
			var productTypeName = $("#productTypeName").html();		
			var productTypeId = $("#productTypeId").val();
			//合同金额
			var totalMoney = $("#totalmoney").html();
            
			var checkResult = true;
			$("#tableContractDetail :checkbox").each(function(){
				
				if (checkResult == true) {
					
					var pid = this.value;
					
					var count = $("#detailCount"+pid).val()-0;
					
					removeInvalidClass("detailCount" + pid);
					if (count == 0) {
						alert("销售数量不能为零");
						checkResult = false;
						addInvalidClass("detailCount" + pid);
						return ;
					}
					
					var price = $("#detailPrice"+pid).val()-0;
					
					removeInvalidClass("detailPrice" + pid);
					//if (price == 0) {
						//alert("销售单价不能为零");
						//checkResult = false;
						//addInvalidClass("detailPrice" + pid);
						//return ;
					//}
				}
			});	
            
			if(checkResult){
				 win1 = 
				window.open('${ctx}/getPaymentWay.do?typename='+encodeURI(productTypeName,"UTF-8")
				+ '&customername='+ encodeURI(customerName,"UTF-8")
				+ '&totalmoney='  + totalMoney 
				+ '&prodTypeId='  + productTypeId 
				+ '&customerId='  + customerId ,'newwindow', popWindows(300));
				arrSubWin.push(win1);
			}
		};


		var countRow=0;//新增的行数,也是checkbox的id值
		var lastDeleteIndex=-1;
		var indexRow=0;//序号
		
		//去除重复选择产品的项
		function newWindow(myArrayProduct,typeid){
			var prodtypeid=$("#productTypeId").val();
			if(prodtypeid!=typeid){
				alert("产品分类已改变,请重新选择！");
				return;
			}
			var productids = document.getElementsByName("productId");
			
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
		function insertCell(myArray){
			var averagePrice = 0;
			for(var i=0;i<myArray.length;i++){
				var arr=myArray[i];
				var table = $("#tableContractDetail").get(0);
				//添加一行
		        var newTr = table.insertRow();
		        newTr.id="trProduct"+arr[0];
		        newTr.name='productTR';
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

		        newTd0.innerHTML = "&nbsp;<input type='hidden'  name='proDetailId' value='" + arr[0] + "'/><input type='checkbox' name='productId' id='choose"+countRow+"' value='" + arr[0] +"'/>";
		        newTd1.innerHTML = '';
		        newTd2.innerText = ''+arr[1];
		        newTd3.innerText = ''+arr[2];
		        newTd4.innerText = ''+arr[3];
		        newTd5.innerText = ''+arr[4];
		        newTd6.innerHTML = '<input type="text" name="detailCount' + arr[0] +'" style="width:86px; text-align:right; " id="detailCount' + arr[0] + '" onclick="range(this)" onblur="checkSellCount(this)" value="0"><input type="hidden" name="productNumber" id="productNumber'+countRow+'" value="'+arr[5]+'"><input type="hidden" name="averagePrice" id="avagprice'+countRow+'" value="'+arr[6]+'">';
		        newTd7.innerHTML = '<input type="text" name="detailPrice' + arr[0] +'" style="width:86px; text-align:right; " id="detailPrice' + arr[0] + '" onclick="range(this)" onblur="autoSalesPrice(this)" value="0.00"><input type="hidden" name="productsellcount"+arr[0] +" value="'+arr[7]+'" ><input type="hidden" name="totleprice" id="arth'+indexRow+'"><input type="hidden" name="productids" value="'+arr[0]+'"><input type="hidden" name="productUsableCount'+ arr[0] +'" value="'+arr[5]+'">';

				newTd8.style.textAlign="right";
				newTd8.style.paddingRight="5px";
	            newTd9.style.textAlign="right";
                newTd9.style.paddingRight="5px";
				newTd10.style.textAlign="right";
                newTd10.style.paddingRight="5px";
                
		        //newTd8.innerText = ''+0.00;
		        //newTd9.innerText = ''+arr[5];

				newTd8.innerHTML = ' 0.00';
				newTd9.innerHTML = ''+arr[5];

				newTd8.innerText = ''+'0.00';
				newTd9.innerText = ''+arr[5];
				newTd10.innerHTML= ''+arr[6]+'&nbsp;';
			}
			
			changeIndex();
			$("#tableLength").attr("value", countRow);
			if($("#tableContractDetail")){
								$("#tableContractDetail tr:even").addClass("treven");
								$("#tableContractDetail tr").each(function(i){
									$(this).mouseover(function(){
										$(this).addClass("over");
									});
									$(this).mouseout(function(){
										$(this).removeClass("over");
									});
								});
							}
	};
	

	
	//检查销售数量
	function checkSellCount(productCountText){
		var productCount = productCountText.value;
		var flag = "count";
		 
		if(/^[1-9]\d{0,10}$/.test(productCount) || productCount==0){
			salesTotalMoney(productCountText,flag);
		    totalMoneyMethod();		
		}else{
			alert("销售数量必须是正整数");
			productCountText.focus();
			return;		
		}
		

	}
	// 检查销售单价
	function autoSalesPrice(priceText){
		var salesPrice = priceText.value;
		var flag = "price";
		if($.trim(salesPrice)==""){
			alert("销售单价不能为空");
			priceText.focus();
			return;
		}
		if(/^[1-9](\d{1,7})?(\.\d{1,2})?$/.test(salesPrice) || /^0{1}\.\d{1,2}$/.test(salesPrice) || salesPrice==0){
			salesTotalMoney(priceText,flag);
		 	totalMoneyMethod();
		}else{
			alert("销售单价必须是数值型且保留两位小数");
			priceText.focus();
			return;
		}
	}
	//计算销售合同总额
	function salesTotalMoney(obj,flag){
		var totalMoney = obj.value;
		var tbobj = obj.parentNode.parentNode.parentNode;
		var row = obj.parentNode.parentNode.rowIndex;
		var cell = obj.parentNode.cellIndex;
		if(flag=="count"){
			var scount = tbobj.rows[row].cells[cell+1].childNodes[0].value;
			tbobj.rows[row].cells[cell+2].innerText = formatMoney(accMul(totalMoney,scount),2);
		}else if(flag=="price"){
			var sprice = tbobj.rows[row].cells[cell-1].childNodes[0].value;
			tbobj.rows[row].cells[cell+1].innerText = formatMoney(accMul(totalMoney,sprice),2);
		}
	};
	

		//进入选择产品页面
		function selectProduct(){
            var win2 = window.open("${ctx}/getProduct.do?prodTypeId="+$("#productTypeId").val()+"&prodTypeName="+encodeURI("${contract.productTypeName}","UTF-8"),'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=900,height=500');
			arrSubWin.push(win2);
		};
		
		
		//选择模板的操作
		function selectTemplateType(){
		
			var type = $(":radio:checked[name='templateType']").val();
				// 标准模板
				if(type == "0"){
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
				}
				if(type == "1"){// 自定义模板
					$("tr[name='trTemplate0']").show();
					$("tr[name='trTemplate1']").show();
					$("tr[name='trTemplate2']").hide();
					$("tr[name='trTemplate0'] input").attr("disabled",false);
					$("tr[name='trTemplate1'] input").attr("disabled",false);
					$("#btnPreview").show();
					var contractProName=$("#contractProName").clone();
					$("#tdOfcontractProNameBy2").html("");
					$("#tdOfcontractProNameBy1").html(contractProName);
				} 
				
				if(type == "2"){ // 非模板
					$("tr[name='trTemplate2']").show();
					$("tr[name='trTemplate1']").hide();
					$("tr[name='trTemplate0']").hide();
					$("tr[name='trTemplate2'] input").attr("disabled",false);	
					var contractProName=$("#contractProName").clone();
					$("#tdOfcontractProNameBy1").html("");
					$("#tdOfcontractProNameBy2").html(contractProName);
					$("#btnPreview").hide();
				}				
			
		};
		//还原金额   
		function rmoney(s){ 
			return parseFloat(s.replace(/[^\d\.-]/g,""));  
		} 
	   	//销售金额合计
   		function totalMoneyMethod(){
   		
   		var detailTable = $("#tableContractDetail").get(0);
		var detailCount = detailTable.rows.length;
		var totalMoney = 0;
		var arr = new Array();
    	for(var i=1; i<detailCount; i++){
    		totalMoney = accAdd(totalMoney,rmoney(detailTable.rows[i].cells[8].innerText));
    	}
		$("#totalmoney").html(formatMoney(totalMoney,2));
		$("#totalMoney").val(totalMoney);
		
		var paymentWay = $("#salesCon\\.paymentWay").val();
		
		if(paymentWay == 0) {
			$("#salesCashMoney").html(formatMoney(totalMoney,2));
		}
    	
   	}
	
	//全选<修改产品选择用>		
	function selectAllProduct(selectAllbutton) {
		
   		$("#tableContractDetail :checkbox").attr("checked", selectAllbutton.checked);
	};
	
	//删除选中的产品	
	function removeSelectedProduct() {
   		$("#tableContractDetail tr:has(:checkbox:checked)").remove();
        changeIndex();
   		//重新计算合同金额
   		totalMoneyMethod();
   		reCheckContractProduct();
 
	};
	
	function reCheckContractProduct() {
	  	//产品全被删除后，去掉全选按钮的选中状态
   		if ($("#tableContractDetail :checkbox").length == 0) {
   			$("#chckbxSelectAllProduct").attr("checked", false);
   			//$("#productDeptName").empty();
   			//$("#productTypeName").empty();
   			$("#productDeptFax").empty();
   			$("#productDeptAccount").empty();
   			//$("#productTypeId").attr("value", "");
   		}
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
		
	//提交前的检查
	function submitContractForm(method) {		
		
		var checkResult = submitCheck();
		
		if(checkResult==false) {
			return false;
		}

		if(checkTextAreaLen(200)==false){
		    return;
		}

		var checkMoneyResult = checkMoney();
	
		if (checkResult == true && checkMoneyResult == true) {
		 if($("#addressAddOrSelect").val() !=1 ){
			 if(checkForm()==false){
				return ;
			}
			 }
			$("#method").attr("value", method);
			$("#salesConForm").submit();
		}
	
	};
	
	
	//提交时的检查
	function submitCheck() {
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
			if ($("#contractFile").val() == "" && $("#contractFileName").val() == "") {
				alert("请添加合同模板文件");
				$("#contractFile").addClass("invalid");
				return false;
			}

			var fl1 = $("#contractFile").val();
			var fl2 = $("#contractFileName").val();
			var uploadFileName;
			if(fl1.length !=0) {
			    uploadFileName = fl1;
			}else{
			    uploadFileName = fl2;
			}
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

		var pid = $("#productTypeId").val();
	    if(pid==""){
	    	alert("请选择产品");
	    	return false;
	    }
		
					
		var customerCreditId = $("#salesCon.cusCreditId").val();
		
		if (customerCreditId == "") {
			alert("请选择付款方式");
			return false;
		}
		
		var requestSendDate = $("#requestSendDate").val();
		removeInvalidClass("requestSendDate");
		if (requestSendDate == "") {
			alert("请选择发货日期");
			addInvalidClass("requestSendDate");
			return false;
		} 
		
		 requestSendDate = requestSendDate.split("-");
	    var sendDate = new Date();
			    
		if(0 == requestSendDate[1]) {
			 requestSendDate[1] = 1;
		}
		
		sendDate.setFullYear(requestSendDate[0]-0, requestSendDate[1]-1, requestSendDate[2]-0);
		var today = new Date();
										
		removeInvalidClass("requestSendDate");		
		if (sendDate <  today) {
					
			alert("发货日期必须大于或等于当前日期");
			$("#requestDendDate").attr("value", "");
			addInvalidClass("requestSendDate");					
			return false;				
		} 
		
		var customerAddressId = $("#customerAddressId").val();
		
		if (customerAddressId == "") {
			alert("请选择发货地址");
			return false;
		}	
		
		var checkResult = true;
		
		$("#tableContractDetail :checkbox").each(function(){
			
			if (checkResult == true) {
				
				var pid = this.value;
				
				var count = $("#detailCount"+pid).val()-0;
				
				removeInvalidClass("detailCount" + pid);
				if (count == 0) {
					alert("销售数量不能为零");
					checkResult = false;
					addInvalidClass("detailCount" + pid);
					return ;
				}
				
				var price = $("#detailPrice"+pid).val()-0;
				
				removeInvalidClass("detailPrice" + pid);
				//if (price == 0) {
				//	alert("销售单价不能为零");
				//	checkResult = false;
				//	addInvalidClass("detailPrice" + pid);
				//	return ;
				//}
			}
		});		
		
		return checkResult;
	}
   	
   	//增加 invalid css
   	function addInvalidClass(elementId) {
   		$("#"+elementId).addClass("invalid");
   		
   	}
   	
   	//删除 invalid css
   	function removeInvalidClass(elementId) {
   		$("#"+elementId).removeClass("invalid");
   		
   	}
	//将货币格式还原成数值型
	function revertNumber(obj){
		var tmp = obj.split(",");
		var num = tmp.join("");
		return parseFloat(num);
	}
	
   	//提交时对销售金额与付款金额进行check
	function checkMoney(){
	    var id = $("#salesCon\\.creditTypeId").val();
        if(id==null || id==""){
		    alert("请选择付款方式");
			return false;
		}
		var paymentWay = revertNumber($("#salesCon\\.paymentWay").val()); 
		var tmoney     = revertNumber($("#totalMoney").val());//合同金额
		var cmoney     = revertNumber($("#salesCon\\.cashMoney").val());//现结金额
		//var limit      = revertNumber($("#freeLimit").val());//可用信用 Del By ChenHJ @2010-07-29
		var limit      = revertNumber($("#salesCon\\.climit").val());//信用额度 Add By ChenHJ @2010-07-29
		var bmoney     = revertNumber($("#salesCon\\.batchMaxMoney").val());//分批最大金额
        
		if(paymentWay==0){//全部现结
		    $("#salesCon\\.cashMoney").attr("value",tmoney);
		}
		
		if(paymentWay==1){//全部信用		
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
			if(((cmoney)>=(tmoney)&&tmoney!=0)||(cmoney>0&&tmoney==0)){
				alert("现结金额必须小于销售金额");
				return false;
			}

            if(bmoney==0||isNaN(bmoney)){//不分批
			//合同金额-现结金额<=信用金额
			    if(tmoney-cmoney>limit){
					alert("信用额度不足");
					return false;					
				}
			}else if(limit<bmoney){
				alert("信用额度不足");
				return false;		
			}
			
			if(bmoney>tmoney){
				alert("分批最大金额必须小于合同金额");
				return false;
			}
			
			//分批金额加现结金额小于等于合同金额
			if(tmoney<cmoney+bmoney){
				alert("现结金额与分批金额之和不能大于合同金额");
				return false;
			}
		}	
		
		return true;
	}
	
	//选中文本框内所有
	function range(obj){
	  obj.select();
	 }
</script>
</head>
	<body>
		<div id="newbulid">
		<html:form action="dealwithSalesContract.do"  method="post" styleId="salesConForm" enctype="multipart/form-data">
			<input type="hidden" id="tableLength" value="0"/>
			<input type="hidden" id="method" value="" name = "method"/>
			<input type="hidden" id="method" value="${contract.id}" name="id"/>
			<input type="hidden" name="addressAddOrSelect" id="addressAddOrSelect" value="<c:if test="${contract.customerAddressId==0}">0</c:if><c:if test="${contract.customerAddressId!=0}">1</c:if>">
   			<input type="hidden" id="customerAddressId" name="customerAddressId" value="${contract.customerAddressId}"/>	
   			<input type="hidden" id="_customerAddressId" name="_customerAddressId" value="${contract.customerAddressId}"/>					
			<input type="hidden" name="contractFileName" id="contractFileName" value="${contract.fileName}">
			<input type="hidden" name="money"  id="totalMoney" value="${contract.money}" />
			
			<!--产品类型id-->
			<input type="hidden" id="productTypeId" type="hidden" name="productTypeId" value="${contract.productTypeId}"/>
			<!--付款方式隐藏域-->					
		    <input  type="hidden" name="customerCreditId" id="salesCon.cusCreditId"   value="${contract.customerCreditId}"/>
		    <input  type="hidden" name="creditTypeId"     id="salesCon.creditTypeId"  value="${contract.creditTypeId}"/>
		    <input  type="hidden" name="projectName"      id="salesCon.projectName"   value="${contract.projectName}"/>
		    <input  type="hidden" name="arterm"           id="salesCon.arterm"        value="${contract.arterm}"/>
		    <input  type="hidden" name="climit"           id="salesCon.climit" 		value="${contract.climit}"/>
		    <input  type="hidden" name="freeLimit"        id="freeLimit"    		value="${contract.freeLimit}"/>
		    
		    <input  type="hidden" name="paymentWay"       id="salesCon.paymentWay"    value="${contract.paymentWay}"/>
		    <input  type="hidden" name="batchWay"         id="salesCon.batchWay"      value="${contract.batchWay}"/>
		    <input  type="hidden" name="batchMaxMoney"    id="salesCon.batchMaxMoney" value="${contract.batchMaxMoney}"/>
		    <input  type="hidden" name="cashMoney"        id="salesCon.cashMoney"     value="${contract.cashMoney}"/>
		
		 
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="ye_header_left">&nbsp;
						
					</td>
					<td class="ye_header_center">
						<img src="${ctx}/images/main_jt.jpg" />
						&nbsp;当前位置： 销售管理 &gt;&gt; 销售合同管理 &gt;&gt;  销售合同修改
					</td>
					<td class="ye_header_right">&nbsp;
						
					</td>
				</tr>
				<tr>
					<td>&nbsp;
						
					</td>
					<td valign="middle">
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;合同信息
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td class="td_01" width="20%">
									&nbsp;盖章类型
								</td>
								<td class="td_02" width="30%">合同章
								<input type="hidden" name="stampType" id="stampType" value = "1">
								</td>
								<td class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;模版类型
								</td>
								<td class="td_02">
									
									<input name="templateType" type="radio" value="0" 
									<c:if test="${contract.templateType == 0 }">
										checked="checked"
									</c:if>	
										onclick="selectTemplateType();" />
									标准模版
									<input type="radio" name="templateType" value="1"
									<c:if test="${contract.templateType == 1 }">
										checked="checked"
								    </c:if> 
										onclick="selectTemplateType();" />
									自定义模版
									<input type="radio" name="templateType" value="2"
									<c:if test="${contract.templateType == 2 }">
										checked="checked"
									</c:if>
										onclick="selectTemplateType();" />
									非模版
								</td>
							</tr>
							<tr id="placeProtect" name='trTemplate0'>
								<td class="td_01">
									签订地点
								</td>
								<td class="td_02">
									<input name="place" id="place" type="text" 
										style="width:120px;" value="${contract.place}" disabled="disabled" maxlength="10" />
								</td>
								<td class="td_01">
									保价期限
								</td>
								<td class="td_02">
									<input name="protectLimit" id="protectLimit" type="text" id="protect"
										style="width:120px;" value="${contract.protectLimit}"
										disabled="disabled" maxlength="3"/>
									日
								</td>
							</tr>
							<tr id="checkBreach" name="trTemplate0">
								<td class="td_01">
									验收期限
								</td>
								<td class="td_02">
									<input name="checkLimit" id="checkLimit" type="text" id="check"
										style="width:120px;" value="${contract.checkLimit}"
										disabled="disabled" maxlength="3"/>
									日
								</td>
								<td class="td_01">
									违约金标准
								</td>
								<td class="td_02">
									<input  id="breach" name="breach" type="text"
										style="width:120px;" value="${contract.breach}"
										disabled="disabled" maxlength="10"/>
								</td>
							</tr>
							<tr id="lawsuitJuge" name="trTemplate0">
								<td class="td_01">
									诉讼地
								</td>
								<td class="td_02">
									<input id="lawsuit" name="lawsuit" type="text" 
										style="width:240px;" value="${contract.lawsuit}"
										disabled="disabled" maxlength="20"/>
								</td>
								<td class="td_01" ><span style="color:#FF0000">*</span>&nbsp;项目名称</td>
            <td class="td_02" id="tdOfcontractProNameBy1"><input name="contractProName" type="text" id="contractProName" maxlength="20" value="${contract.contractProName}"/></td>
							</tr>
							<tr style="display:none"  name="trTemplate1">
								<td class="td_01">
									合同附加条款
								</td>
								<td colspan="3" class="td_02">
									<textarea name="clause" id="clause" cols="100"
										rows="4">${contract.clause}</textarea>
								</td>
							</tr>
								<tr style="display:none" id="filename" name="trTemplate2">
								<td class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;选择文件
								</td>
								<td  class="td_02">
									<input type="file" id="contractFile"  name="contractFile"
										style="width:240px;" value="${contract.fileName}"  onchange="showFile();"/>
                                    <SPAN id="fileNameError" style="font-size:12px;color:#F00"></SPAN>
								</td>
								<td class="td_01" ><span style="color:#FF0000">*</span>&nbsp;项目名称</td>
            <td class="td_02" id="tdOfcontractProNameBy2"></td>
							</tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;客户信息
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td class="td_01" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;客户名称
									<input type="hidden" id="customerName" name= "customerName" value="${customerName}"/>
								</td>
								<td class="td_02" width="30%">
									<select name="customerId" id="customerId"
										style=" width:286px" onchange="getLinkManByCustomerId();">
										<option value="">
											--请选择--
										</option>
										<logic:present name="customerList">
											<logic:iterate id="customer" name="customerList">
												<option value="${customer.customerId}"
													<c:if test="${contract.customerId == customer.customerId}">selected="selected"</c:if>>
											${customer.customerName}
												</option>
											</logic:iterate>
										</logic:present>
									</select>
								</td>
								<td class="td_01" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;联系人
								</td>
								<td class="td_02" width="30%">
									<select name="customerLinkmanId" id="customerLinkmanId"
										style=" width:126px" onchange="getLinkMsgByLinkManId();">
											<option value="">
												--请选择--
											</option>
											<c:forEach var="customerLinkman" items="${customerLinkmanList}">
											<option value="${customerLinkman.cusLinkId}" 
											<c:if test="${customerLinkman.cusLinkId == contract.customerLinkmanId}">selected="selected"</c:if>>
											${customerLinkman.cusLinkName}
										</option>
									</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td class="td_01" height="18px">
									省份
								</td>
								<td class="td_02"  id="provincename">
								${contract.customerProvince} 
								</td>
								<td class="td_01">
									电话
								</td>
								<td class="td_02" id="telname">
								${contract.customerLinkmanTel}
								</td>
							</tr>
							<tr>
								<td class="td_01" height="18px">
									城市
								</td>
								<td class="td_02" id="cityname">
								${contract.customerCity}
								</td>
								<td class="td_01">
									传真
								</td>
								<td class="td_02" id="faxname">
								${contract.customerLinkmanFax}
								</td>
							</tr>
							<tr>
								<td class="td_01" height="18px">
									开户行
								</td>
								<td class="td_02" id="invoicebankname">
								${contract.customerInvoiceBankName}
								</td>
								<td class="td_01">
									帐号
								</td>
								<td class="td_02" id="invoicebankaccount">
								${contract.customerInvoiceBankAccount}
								</td>
							</tr>
							<tr>
								<td class="td_01" height="18px">
									税号
								</td>
								<td class="td_02" id="taxnumbername">
								${contract.customerTaxNumber}
								</td>
								<td class="td_01">
									发票类型
								</td>
								<td class="td_02" id="taxname">
								<c:if test="${contract.customerInvoiceType == 0}">
									<c:set var="invoiceTypeView" value="普通"/>
								</c:if>
								<c:if test="${contract.customerInvoiceType == 1}">
									<c:set var="invoiceTypeView" value="增值税"/>
								</c:if>
								${invoiceTypeView}
								</td>
							</tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;产品信息
						</div>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3" >
							<tr>
								<td class="td_01" height="18px" width="20%">
									产品分类名称 
								</td>
								<td class="td_02" id="productTypeName" width="30%">
								${contract.productTypeName}
								</td>
								<td class="td_01" width="20%">
									产品部门名称
								</td>
								<td class="td_02" id="productDeptName" width="30%">
								${contract.productDeptName}
								</td>
							</tr>
						</table>
						<div style="width:100%; padding:5px 0px 1px 0px;">
                        <div style="float:left;">&nbsp;&nbsp;<a href="#" onclick="selectProduct();"><img src="${ctx}/images/btnCPXZ.gif" width="70" height="20" /> </a></div>
      <div style=" float:right; padding-top:10px;">单位：元</div>
      </div>
			<table id="tableContractDetail"  width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" >
							<tr>
								<th nowrap="nowrap" width="40">
									选择
								</th>
								<th nowrap="nowrap" width="40">
									序号
								</th>
								<th nowrap="nowrap">
									产品编码
								</th>
								<th nowrap="nowrap">
									产品名称
								</th>
								<th nowrap="nowrap">
									规格型号
								</th>
								<th nowrap="nowrap">
									单位
								</th>
								<th nowrap="nowrap">
									销售数量
								</th>
								<th nowrap="nowrap" >
									销售单价
								</th>
								<th nowrap="nowrap" width="93px">
									销售金额
								</th>
								<th nowrap="nowrap" width="80px">
									销售可用数
								</th>
								<th nowrap="nowrap" width="80px">
									限价
								</th>
							</tr>
								
					<c:forEach var="productDetail" varStatus="detailAmount" items="${contractProductDetailList}">		
					
						<tr name="productTR">
							<input type="hidden"  name="proDetailId" value="${productDetail.productId}"/>						
							<td nowrap="nowrap">
								&nbsp;<input type="checkbox"  name="productId" value="${productDetail.productId}"/>
							</td>
							<td nowrap="nowrap">
								${detailAmount.count}
							</td>
							<td nowrap="nowrap">
								${productDetail.productCode}
							</td>
							<td nowrap="nowrap">
								${productDetail.productName}
							</td>
							<td nowrap="nowrap">
								${productDetail.productType}
							</td>
							<td nowrap="nowrap">
								${productDetail.productUnit}
							</td>
							<td nowrap="nowrap" width="100px">
								<input type="text" id="detailCount${productDetail.productId}" name="detailCount${productDetail.productId}" value="${productDetail.detailCount}" style="width:86px;text-align:right; padding-right:5px;" onblur="checkSellCount(this);" onclick="range(this)"/>
							</td>
							<td nowrap="nowrap" width="100px">
								<input type="text"  id="detailPrice${productDetail.productId}" name="detailPrice${productDetail.productId}" value="${productDetail.detailPrice}" style="width:86px; text-align:right; padding-right:5px;" onblur="autoSalesPrice(this);" onclick="range(this)"/>
							</td>
							<td  nowrap="nowrap" style=" text-align:right; padding-right:5px;">
								<fmt:formatNumber value="${productDetail.detailCount * productDetail.detailPrice}" pattern="#,##0.00"/>
								
							</td>
							<td nowrap="nowrap" style="text-align:right; padding-right:5px;">
								<input type="hidden" name="productUsableCount${productDetail.productId}"  value="${productDetail.productUsableCount}" style="text-align:right; padding-right:5px;"/>							
								${productDetail.productUsableCount}
							</td>
							<td nowrap="nowrap" style="text-align:right; padding-right:5px;">
								<fmt:formatNumber value="${productDetail.limitPrice}" pattern="#,##0.00"/>&nbsp;
							</td>
						</tr>
					</c:forEach>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							id="ec_table">
							<tr>
								<td width="40">
								&nbsp;&nbsp;<input type="checkbox" name="checkbox3" id="chckbxSelectAllProduct" onclick="selectAllProduct(this);"/>
								</td>
								<td width="40">
									全选
								</td>
								<td width="100" align="center">
									<a href="#"><img src="${ctx}/images/btnDelete.gif" onclick="removeSelectedProduct();"/></a>
								</td>
								<td align="right">
									销售金额合计
								</td>
								<td align="right" id="totalmoney" width="93px" style="padding-right:5px">
								<fmt:formatNumber value="${contract.money}" pattern="#,##0.00"/>	
								</td>
								
								<td align="left" height="25" width="80px">
									元
								</td>
							</tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;付款信息
						</div>
						&nbsp;&nbsp;<a href="#" onclick="selectPaymentType();"><img src="${ctx}/images/btnFKFS.gif" width="99" height="20" /> </a>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td class="td_01" width="20%" height="18px">
									付款方式
								</td>
								<td class="td_02" width="30%" id="salesPaymentWay">
								<c:if test="${contract.paymentWay == 0}">
								全部现结 &nbsp;
								</c:if>
								<c:if test="${contract.paymentWay == 1}">
								全部信用
								</c:if>
								<c:if test="${contract.paymentWay == 2}">
								部分现结部分信用
								</c:if>
								</td>
								<td class="td_01" width="20%">现结金额(元)</td>
								<td class="td_02" width="30%" id="salesCashMoney">
								<fmt:formatNumber value="${contract.cashMoney}" pattern="#,##0.00"/>
								&nbsp;</td>
							</tr>
							
							<tr id="td_fenpi" <c:if test="${contract.paymentWay == 0}">  style="display:none"</c:if>>
								<td class="td_01" height="18px">
									分批方式
								</td>
								<td class="td_02" id="salesBatchWay">
								<c:if test="${contract.batchWay == 0}">
									不分批
								</c:if>
								<c:if test="${contract.batchWay == 1}">
									分批
								</c:if>&nbsp;
								</td>
								<td class="td_01">
									单批最大金额(元)
								</td>
								<td class="td_02" id="salesBatchMaxMoney">
									
									<fmt:formatNumber value="${contract.batchMaxMoney}" pattern="#,##0.00"/>&nbsp;
								</td>
							</tr>
							
						</table>
						<br />
				
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table1">
								
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
								<td nowrap="nowrap" id="salesCreTypeName" height="18px">
										${contract.creditTypeName}&nbsp;
								</td>
								<td nowrap="nowrap" id="salesProductName">
										${contract.projectName}&nbsp;
								</td>
								<td nowrap="nowrap" id="salesArterm" style="text-align:right">
										${contract.arterm}&nbsp;
								</td>
								<td nowrap="nowrap" id="salesClimit" style="text-align:right">
										<fmt:formatNumber value="${contract.climit}" pattern="#,##0.00"/>&nbsp;
								</td>
								<td nowrap="nowrap" id="salesFree" style="text-align:right">
										<fmt:formatNumber value="${contract.freeLimit}" pattern="#,##0.00"/>&nbsp;
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
								<input type="hidden" name="addressnameHidden" id="addressnameHidden">
								</td>
							</tr>
						</table>
						<input type="hidden" id="customerAddressId"	name="customerAddressId" value="${contract.customerAddressId}"/>
						
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							 <tr id="trUp1">
            <td class="td_01" height="18px"> 货物接收单位名称 </td>
            <td class="td_02" id="reveivename">${contract.customerAddressName}</td>
            <td class="td_01" height="18px"> 发货地址 </td>
            <td colspan="3" class="td_02" id="addressname">${contract.customerAddressAddress}</td>
          </tr>
          <tr id="trUp2">
            <td class="td_01" height="18px" width="20%"> 联系人 </td>
            <td class="td_02" id="reveivelinkman" width="30%">${contract.customerAddressLinkman}</td>
            <td class="td_01" width="20%"> 邮编 </td>
            <td class="td_02" id="postcode" width="30%">${contract.customerAddressPostcode}</td>
          </tr>
          <tr id="trUp3">
            <td class="td_01" height="18px"> 电话 </td>
            <td class="td_02" id="receivetel">${contract.customerAddressTel}</td>
            <td class="td_01"> 手机 </td>
            <td class="td_02" id="receivemobile">${contract.customerAddressMobile}</td>
          
          
          
          <tr id="trFt1">
            <td class="td_01" height="18px"> <span id="x4" style="color:#FF0000">*</span>&nbsp;货物接收单位名称 </td>
            <td class="td_02" ><input type="text" id="addreveivename" name="customerAddressName" value="${contract.customerAddressName}" style="width:240px;" maxlength="20">&nbsp;</td>
             <td class="td_01" height="18px"><span id="x4" style="color:#FF0000">*</span>&nbsp; 发货地址 </td>
            <td colspan="3" class="td_02" ><input type="text" id="addaddressname" name="customerAddressAddress" value="${contract.customerAddressAddress}"  style="width:280px;" maxlength="40">&nbsp;</td>

          </tr>
          <tr id="trFt2">
            <td class="td_01" height="18px" width="20%"> <span id="x4" style="color:#FF0000">*</span>&nbsp;联系人 </td>
            <td class="td_02" width="30%"><input type="text" id="addreveivelinkman" name="customerAddressLinkman" value="${contract.customerAddressLinkman}" style="width:120px;" maxlength="4">&nbsp;</td>
            <td class="td_01" width="20%"> 邮编 </td>
            <td class="td_02" width="30%"><input type="text" id="addpostcode" name="customerAddressPostcode" value="${contract.customerAddressPostcode}" style="width:120px;" maxlength="6">&nbsp;</td>
          </tr>
          <tr id="trFt3">
            <td class="td_01" height="18px"><span id="x4" style="color:#FF0000">*</span>&nbsp; 电话 </td>
            <td class="td_02" ><input type="text" id="addreceivetel" name="customerAddressTel" value="${contract.customerAddressTel}" style="width:120px;" maxlength="20">&nbsp;</td>
            <td class="td_01"> 手机 </td>
            <td class="td_02" ><input type="text" id="addreceivemobile" name="customerAddressMobile" value="${contract.customerAddressMobile}" style="width:120px;" maxlength="12">&nbsp;</td>
          </tr>
          
           <tr>
            
            <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;要求发货日期&nbsp; </td>
            <td class="td_02"><input type="text" name="requestSendDate"
										id="requestSendDate" value="${contract.requestSendDate}" style="width:120px;" onfocus="calendarMinToday()"
										readonly="readonly" />
            </td>
            <td class="td_01" height="18px">&nbsp; </td>
            <td class="td_02" >&nbsp;</td>
          </tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;供方信息
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<input type="hidden" name="sellcontract.user_id" value="leizhao" id="user_id"/>
							<input type="hidden" name="sellcontract.user_name" value="${sellinfo.user_name}"/>
							<input type="hidden" name="sellcontract.user_area_id" value="110"/>
							<input type="hidden" name="sellcontract.user_area_name" value="${sellinfo.user_area_name }"/>
							<input type="hidden" name="sellcontract.tel" value="${sellinfo.tel }"/>
							<tr>
								<td class="td_01" width="20%" height="18px">
									人员名称
								</td>
								<td class="td_02" width="30%">
										${contract.userName}
								</td>
								<td class="td_01" width="20%">
									人员区域
								</td>
								<td class="td_02" width="30%">
									${contract.userAreaName}
								</td>
							</tr>
							<tr>
								<td class="td_01" height="18px">
									电话
								</td>
								<td class="td_02">
									${contract.userTel}
								</td>
								<td class="td_01">
									传真
								</td>
								<td class="td_02" id="productDeptFax">
								${contract.productDeptFax}
								</td>
							</tr>
							<tr>
								<td class="td_01" height="18px">
									帐号
								</td>
								<td class="td_02" id="productDeptAccount">
									${contract.productDeptAccount}
								</td>
								<td class="td_01">&nbsp;
									
								</td>
								<td class="td_02">&nbsp;
									
								</td>
							</tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;特殊说明
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td class="td_03" width="20%">
									特殊说明
								</td>
								<td class="td_04">
									<textarea name="text" id="text" cols="100" rows="4">${contract.text}</textarea>
								</td>
							</tr>
						</table>
						<br>
		<div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
		
   <table  id="tableAssessIdea" border="0" cellpadding="0" cellspacing="0" align="center"	width="460">
				<c:if test="${not empty contract.proMajId}">		
						
						<tr>
							<td height="20px" colspan="2">
								产品总监评审意见：
							</td>
						</tr>
						<tr name="trProMaj">
							<td width="320" nowrap="nowrap">
								1.产品要求是否符合（数量、规格、技术、质量）
							</td>
							<td height="20px" width="140" nowrap="nowrap">
								<input type="checkbox"  name="proMajIdea1" id="radio" value="1"
									<c:if test='${contract.proMajIdea1 == "1"}'>checked="checked"</c:if> 
									/>
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="proMajIdea1" id="radio" value="0"
									<c:if test='${contract.proMajIdea1 == "0"}'>checked="checked"</c:if> 
									/>
								不符合
							</td>
						</tr>
						<tr	name="trProMaj">
							<td>
								2.交付日期是否符合
							</td>
							<td height="20px">
								<input type="checkbox"  name="proMajIdea2" id="radio2" value="1"
									<c:if test='${contract.proMajIdea2 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="proMajIdea2" id="radio2" value="0"
									<c:if test='${contract.proMajIdea2 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr name="trProMaj">
							<td>
								3.包装要求是否符合
							</td>
							<td height="20px">
								<input type="checkbox"  name="proMajIdea3" id="radio3" value="1"
									<c:if test='${contract.proMajIdea3 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="proMajIdea3" id="radio3" value="0"
									<c:if test='${contract.proMajIdea3 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr name="trProMaj">
							<td colspan="2" valign="top">
								<table width="98%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>
											补充说明：
										</td>
										<td style="padding:5px 0 5px 0;" name="tdProMaj" width="330px">
											${tf:replaceBr(contract.proMajText)}
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="20px">
								签字：&nbsp; ${contract.proMajName}
							</td>
							<td>
								日期：&nbsp; ${contract.proMajDate}
							</td>
						</tr>
						<tr>
							<td height="20px" colspan="2">&nbsp;
								
							</td>
						</tr>
				</c:if>
				
				<c:if test="${not empty contract.legalId}">		
						<tr	>
							<td height="20px" colspan="2">
								法务专员评审意见：
							</td>
						</tr>
						<tr name="trLegal"	>
							<td>
								法律法规是否符合
							</td>
							<td height="20px">
								<input type="checkbox"  name="legalIdea" id="radio4" value="1"
									<c:if test='${contract.legalIdea == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="legalIdea" id="radio4" value="0"
									<c:if test='${contract.legalIdea == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr>
							<td colspan="2" valign="top">
								<table width="98%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>
											补充说明：
										</td>
										<td style="padding:5px 0 5px 0;" name="tdLegal" width="330px">
											${tf:replaceBr(contract.legalText)}
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="20px">
								签字：&nbsp; ${contract.legalName}
							</td>
							<td>
								日期：&nbsp; ${contract.legalDate}
							</td>
						</tr>
						<tr>
							<td height="20px" colspan="2">&nbsp;
								
							</td>
						</tr>
				</c:if>	
					<tr name="trAreaMaj">
						<td height="20px" colspan="2">
								区域总监评审意见：
						</td>
					</tr>
					<tr>
								<td>
									1.毛利率是否符合
								</td>
								<td height="20px" width="150">
									<input type="checkbox" name="contract.areaMajIdea1"
										id="checkbox" disabled="disabled"
										<c:if test="${contract.areaMajIdea1 == 1}">checked="checked"</c:if> />
									符合 &nbsp;&nbsp;
									<input type="checkbox" name="contract.areaMajIdea1"
										id="checkbox" disabled="disabled"
										<c:if test="${contract.areaMajIdea1 == 0}">checked="checked"</c:if> />
									不符合
								</td>
							</tr>
							<tr>
								<td>
									2.付款方式是否符合
								</td>
								<td height="20px">
									<input type="checkbox" name="contract.areaMajIdea2"
										id="checkbox2" disabled="disabled"
										<c:if test="${contract.areaMajIdea2 == 1}">checked="checked"</c:if> />
									符合 &nbsp;&nbsp;
									<input type="checkbox" name="contract.areaMajIdea2"
										id="checkbox2" disabled="disabled"
										<c:if test="${contract.areaMajIdea2 == 0}">checked="checked"</c:if> />
									不符合
								</td>
							</tr>
							<tr>
								<td>
									3.运输方式是否符合
								</td>
								<td height="20px">
									<input type="checkbox" name="contract.areaMajIdea3"
										id="checkbox5" disabled="disabled"
										<c:if test="${contract.areaMajIdea3 == 1}">checked="checked"</c:if> />
									符合 &nbsp;&nbsp;
									<input type="checkbox" name="contract.areaMajIdea3"
										id="checkbox5" disabled="disabled"
										<c:if test="${contract.areaMajIdea3 == 0}">checked="checked"</c:if> />
									不符合
								</td>
							</tr>
							<tr>
								<td>
									4.售后服务是否符合（含售前、售中）
								</td>
								<td height="20px">
									<input type="checkbox" name="contract.areaMajIdea4"
										id="checkbox3" disabled="disabled"
										<c:if test="${contract.areaMajIdea4 == 1}">checked="checked"</c:if> />
									符合 &nbsp;&nbsp;
									<input type="checkbox" name="contract.areaMajIdea4"
										id="checkbox3" disabled="disabled"
										<c:if test="${contract.areaMajIdea4 == 0}">checked="checked"</c:if> />
									不符合
								</td>
							</tr>
						<tr>
							<td colspan="2" valign="top">
								<table width="98%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>
											补充说明：
										</td>
										<td style="padding:5px 0 5px 0;" name="tdLegal" width="330px">
											${tf:replaceBr(contract.areaMajText)}
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="20px">
								签字：&nbsp; ${contract.areaMajName}
							</td>
							<td>
								日期：&nbsp; ${contract.areaMajDate}
							</td>
						</tr>
						<tr>
							<td height="20px" colspan="2">&nbsp;
								
							</td>
						</tr>	
						<tr name="trSellMaj">
							<td height="20px" colspan="2">
								销售总监评审意见：
							</td>
						</tr>
						<tr name="trSellMaj">
							<td>
								1.毛利率是否符合
							</td>
							<td height="20px" width="150">
								<input type="checkbox"  name="sellMajIdea1" id="radio" value="1"
									<c:if test='${contract.sellMajIdea1 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="sellMajIdea1" id="radio" value="0"
									<c:if test='${contract.sellMajIdea1 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr name="trSellMaj">
							<td>
								2.付款方式是否符合
							</td>
							<td height="20px">
								<input type="checkbox"  name="sellMajIdea2" id="radio2" value="1"
									<c:if test='${contract.sellMajIdea2 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="sellMajIdea2" id="radio2" value="0"
									<c:if test='${contract.sellMajIdea2 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr name="trSellMaj">
							<td>
								3.运输方式是否符合
							</td>
							<td height="20px"> 
								<input type="checkbox"  name="sellMajIdea3" id="radio5" value="1"
									<c:if test='${contract.sellMajIdea3 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="sellMajIdea3" id="radio5" value="0"
									<c:if test='${contract.sellMajIdea3 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr name="trSellMaj">
							<td>
								4.售后服务是否符合（含售前、售中）
							</td>
							<td height="20px">
								<input type="checkbox"  name="sellMajIdea4" id="radio3" value="1"
									<c:if test='${contract.sellMajIdea4 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="sellMajIdea4" id="radio3" value="0"
									<c:if test='${contract.sellMajIdea4 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr>
							<td colspan="2" valign="top">
								<table width="98%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>
											补充说明：
										</td>
										<td style="padding:5px 0 5px 0;" name="tdSellMaj" width="330px">
											${tf:replaceBr(contract.sellMajText)}
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="20px">
								签字：&nbsp; ${contract.sellMajName}
							</td>
							<td>
								日期：&nbsp; ${contract.sellMajDate}
							</td>
						</tr>
						<tr>
							<td height="20px" colspan="2">&nbsp;
								
							</td>
						</tr>
						<tr>
							<td height="20px" colspan="2">
								运营总监评审意见：
							</td>
						</tr>
						<tr name="trOpeMaj">
							<td>&nbsp;
								
							</td>
							<td height="20px">
								<input type="checkbox"  name="opeMajIdea" id="radio4" value="1"
									<c:if test='${contract.opeMajIdea == "1"}'>checked="checked"</c:if> />
								同意 &nbsp;&nbsp;
								<input type="checkbox"  name="opeMajIdea" id="radio4" value="0"
									<c:if test='${contract.opeMajIdea == "0"}'>checked="checked"</c:if> />
								不同意
							</td>
						</tr>
						<tr>
							<td colspan="2" valign="top">
								<table width="98%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>
											补充说明：
										</td>
										<td style="padding:5px 0 5px 0;" name="tdOpeMaj" width="330px">
											${tf:replaceBr(contract.opeMajText)}
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="20px">
								签字： &nbsp; ${contract.opeMajName} 
							</td>
							<td>
								日期： &nbsp; ${contract.opeMajDate} 
							</td>
						</tr>
					</table>
				
					</td>
					<td>&nbsp;
						
					</td>
				</tr>
				<tr>
					<td></td>
					<td height="50px" align="center" valign="bottom">
						<a href=#>
						<img src="${ctx}/images/btnSave.gif" width="65" height="20" onclick="submitContractForm('save');"/></a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href=#>
						<img src="${ctx}/images/btnSubmit.gif" width="65" height="20" onclick="submitContractForm('submit');"/></a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${ctx}/getSalesContractsList.do?init=true"><img src="${ctx}/images/btnBack.gif" /></a>
					</td>
					<td></td>
				</tr>
			</table>
		</html:form>
		
		</div>
		<br/>
	</body>
	
</html>

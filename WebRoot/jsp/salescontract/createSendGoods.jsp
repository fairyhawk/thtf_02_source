<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
		<title>发货单新建修改</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" /> 
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
		<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
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

		<script type="text/javascript">
			
			var subwin;

			$(function(){ 

				hideAddText(); 
				
				window.onbeforeunload=function()
			    { 
			        if(subwin!=undefined){
			            subwin.close();
			        }
			    }
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
				 //隐藏姓名和身份证号码 
				 if($("#transportWaySelect").val() !=1 ){
					$("#nameAndId").hide(); 
				 } 

				 if($("#customerAddressId").val()=="0"){
					$("#customerAddressId").val("");
					$("#caType").val("1"); 
				 }
				 

				 //选择自提时 显示姓名和身份证号码
				 $("#transportWaySelect").change(function(){  
					$('option:selected', this).each(function(){ 
						if(this.value == 1){
							$("#nameAndId").show();

							$("#x1").hide();
							$("#x2").hide();
							$("#x3").hide();
							$("#x4").hide();
						}else{ 
							$("#nameAndId").hide();
							$("#takeName").val("");
							$("#takeNumber").val("");

							$("#x1").show();
							$("#x2").show();
							$("#x3").show();
							$("#x4").show();
						}
					}); 
				 });  

				 //换库房
				 $("#stockroomSelect").change(function(){  
					 if($("#productTable td.productCode").length==0){  
						$("#stockroomSelectId").val($(this).val()); 
					 }else{ 
                        if( $("#stockroomSelectId").val() != $("#stockroomSelect").val()){
                            alert("已选择产品后不能改变库房");
                            //document.getElementById('stockroomSelect').selectedIndex = 2;
                            //$("#stockroomSelect").get(0).selectedIndex = 2;
                            $("#stockroomSelect").attr('value',$("#stockroomSelectId").val());
                        }
					 }
				 });

				 //全选
				 $("#allchecked").click(function(){ 
					if($(this).attr("checked")){
						$("#productTable :checkbox").attr("checked", true);
					}else{
						$("#productTable :checkbox").attr("checked", false);
					} 
				 });

				 //删除
				 $("#deleteProduct").click(function(){  

					 if($("#productTable input:checked").length==0){
						alert("请先选择要删除的产品");
					 }else{  
						var i = 1;
						$("#productTable :checkbox").each(function(){  
							  if($(this).attr("checked")){
								$("#productTable tr:eq("+(i--)+")").remove();
							  }
							  i++;
						}); 

						changeCount();
						$("#allchecked").attr("checked", false);

						if($("#productTable")){
							$("#productTable tr").removeClass("treven");
							$("#productTable tr:even").addClass("treven"); 
						}
					 }
				 }); 

				 //打开选择产品页面
				 $("#addProduct").click(function(){   
					 var stockroomId = $("#stockroomSelect").val(); 
					 
					 if(stockroomId!=""){
						 var sellContractId = $("#sellContractId").val();  

						 subwin = window.open('${ctx}/getProductByStockroomId.do?stockroomId='+stockroomId+'&sellContractId='+sellContractId,'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=250');
					 }else{
						 alert("请先选择一个库房！");
					 }
				 }); 

				 //保存
				 $("#btnSave").click(function(){   
					 $("#submitType").val("1"); 

					 checkSubmit();
					
				 });
				 //提交
				 $("#btnSubmit").click(function(){   
					 $("#submitType").val("2"); 
					 
					 checkSubmit();
				 });

				 
				 $("#addForm").validate({
					rules: { 
						"addPara.countArr": {
							digits: true,
							min: 1
						},
						"addPara.text" : {
							maxlength: 200
						}
					},
					messages: {
						"addPara.countArr": "发货数必须为自然数" ,
						"addPara.text": "特殊说明长度不能超过200" 
							} ,
					errorClass: "invalid",
					onchange: true
				 }); 

				if("${errorMsg}" != ""){
					alert("${errorMsg}");
				}

				 
				if($("#productTable")){
					$("#productTable tr:even").addClass("treven");
					$("#productTable tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});

					});
				}

				 //打开发货地址选择页面 
				$("#selectAddr").click(function(){ 
					//$("#selectAddr")[0].src="${ctx}/images/btnFHDZ.gif";
					//<input id="selectRadio" name="addAddrType" type="radio" height="20" checked/>选择发货地址<input id="addRadio" name="addAddrType" height="20" type="radio"/>添加发货地址
					hideAddText();
 
					var customerId = $("#customerId").val(); 
					 subwin = window.open('${ctx}/getSendGoodsAddress.do?customerId='+customerId,'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=250');
				});

				$("#addBUtton").click(function(){ 
					showAddText();
				});  
	
			});

			function showAddText(){
				
                 //$("#selectAddr").hide();
				if(subwin!=undefined){
					subwin.close();
				}
	
				$("#caType").val("1"); 

				$("#customerAddressId").val("");

				 $("#trUp1").hide();
				 $("#trUp2").hide();
				 $("#trUp3").hide();
				 $("#trUp4").hide();


				//$("#addreveivename").val("");
				//$("#addaddressname").val("");
				//$("#addreveivelinkman").val("");
				//$("#addpostcode").val("");
				//$("#addreceivetel").val("");
				//$("#addreceivemobile").val("");

				//$("#customerAddressId").val(""); 
				//$("#reveivename").text("");
				//$("#addressname").text("");
				//$("#reveivelinkman").text("");
				//$("#postcode").text("");
				//$("#receivetel").text("");
				//$("#receivemobile").text("");
					


				 $("#trFt1").show();
				 $("#trFt2").show();
				 $("#trFt3").show();
				 $("#trFt4").show();

				 //$("#addreveivename").show();
				 //$("#addaddressname").show();
				 //$("#addreveivelinkman").show();
				 //$("#addpostcode").show();
				 //$("#addreceivetel").show();

				 //$("#addreceivemobile").show(); 
			}

			function hideAddText(){
				 
				 //$("#selectAddr").show();
				
				$("#caType").val("0");

				 $("#trUp1").show();
				 $("#trUp2").show();
				 $("#trUp3").show();
				 $("#trUp4").show(); 

				 $("#trFt1").hide();
				 $("#trFt2").hide();
				 $("#trFt3").hide();
				 $("#trFt4").hide();

				//$("#addreveivename").val("");
				//$("#addaddressname").val("");
				//$("#addreveivelinkman").val("");
				//$("#addpostcode").val("");
				//$("#addreceivetel").val("");
				//$("#addreceivemobile").val("");

				 //$("#addreveivename").hide();
				 //$("#addaddressname").hide();
				 //$("#addreveivelinkman").hide();
				 //$("#addpostcode").hide();
				 //$("#addreceivetel").hide();

				 //$("#addreceivemobile").hide(); 
			}

			//金额的验证
			function checkPrice(){  
				var result = true;
			    $("#productTable tr:gt(0)").each(function(i){  
					//销售数-已发货数-备货数（本合同其他库房）+本合同退货数(退货入库成功)
					var xsNum = $(this).children("td:eq(6)").text()*1;
					var yfhNum = $(this).children("td:eq(9)").text()*1;
					var bhNum = $(this).children("td:eq(10)").text()*1;
					var thsNum = $(this).children("td:eq(14)").text()*1; 

					var num = xsNum - yfhNum - bhNum + thsNum;  
					
					var countObj = $(this).children("td:eq(12)").find("input");
					var count = countObj.val();
					 
					if(count==""){
					    alert("发货数不能为空"); 
						result = false;
						countObj.addClass("invalid");
						countObj.focus();
						return false;
					}else if(count*1<=0){
					    alert("请输入发货数"); 
						result = false;
						countObj.addClass("invalid");
						countObj.focus();
						return false;
					}else if(count*1 > $(this).children("td:eq(15)").text()*1){
					    alert("发货数必须小于等于发货可用数,请重新输入"); 	
						result = false;
						countObj.addClass("invalid");
						countObj.focus();
						return false;
					}else if(count*1> num){
					    alert("发货数必须小于等于:销售数-已发货数-备货数（本合同其他库房）+本合同退货数(退货入库成功),请重新输入"); 
						result = false; 
						countObj.addClass("invalid");
						countObj.focus();
						return false;
					}else{
						countObj.removeClass("invalid");
					} 	
					
				}); 
				
				return result;

			}

			//提交验证
			function checkSubmit(){  

				 if($("#productTable tr").length>1){ 
				      if(!checkPrice()){
				 	    return false;
				 	  }
				 }  

				 if($("#productTable tr").length==1){
					alert("请先添加产品");
					return false; 
				 } 
 
				 if($("#transportWaySelect").val() !=1 ){
					 
					 if($("#caType").val()=="1"){
						 if($("#addreveivename").val()==""){
							alert("请输入货物接收单位名称");
							return false;
						 }
						 if($("#addaddressname").val()==""){
							alert("请输入发货地址");
							return false;
						 }
						 if($("#addreveivelinkman").val()==""){
							alert("请输入联系人");
							return false;
						 }
						 if($("#addreceivetel").val()==""){
							alert("请输入电话");
							return false;
						 }
					 }else{	 
						 if($("#customerAddressId").val()==""){
							alert("请选择发货地址");
							return false;
						 }
					 }
				 }

				 if($("#requestDate").val()==""){
					 alert("请选择要求发货日期");
					 return false; 
				 }
				
				 if($("#requestDate").val()!=""){
					var date = $("#requestDate").val().split("-");  
 
					if(date[0] < new Date().getFullYear()){
						alert("要求发货日期必须大于等于今天");
						return false;
					} 

					if(date[0] == new Date().getFullYear() 
						&& date[1] < new Date().getMonth()+1 
						){
						alert("要求发货日期必须大于等于今天");
						return false;
					}

					if(date[0] == new Date().getFullYear() 
						&& date[1] == new Date().getMonth()+1 
						&& date[2] < new Date().getDate()){ 
						 
						alert("要求发货日期必须大于等于今天");
						return false;
					} else {
						
					}
				 } 
				 
				 if(!$("#nameAndId").is(":hidden")){ 

				      if($("#takeName").val()==""){
						 alert("请填写提货人姓名");
						 return false;
					  } 
					  if($("#takeNumber").val()==""){
					     alert("请填写身份证号码"); 
						 return false;
					  } 
				 }
				 //alert("submit");
				 var stockroomId = $("#stockroomSelectId").val();
                 var stockroomtype;
				 var i;
				 var strArr = $("#stockroomType").val().split(",");
				 for(i=0;i<strArr.length;i++){ 
				     var id = strArr[i].split(":")[0]; 
					 var stockroomtype = strArr[i].split(":")[1];
					 if(id == stockroomId && stockroomtype == 0){
						break;
					 }
				 }

				 if(stockroomtype==0){
					 if(confirm("警告：是否确认从虚拟库发货，此库房所有产品为空入空出，库房并不实际发货，如属误操作也不允许退货，请再次确认！")){
						$("#addForm").submit(); 
					 }
				 }else{ 
					 $("#addForm").submit();
				 }
				
			} 


			//判断小页面传过来的产品是否存在
			function checkProduct(prod){
				if($("#productTable td.productCode").length==0){
					return false;
				}else{ 
					var result = false;
					$("#productTable td.productCode").each(function(){  
						 if($(this).text()==prod.split("&")[1]){
						 	result = true;
						 }
						 
					}); 
					return result;
				}
			}

			var i =0;
			if("${productListSize}" != ""){
			    i = "${productListSize}"*1;
			}

		    //添加产品
			function addProduct(prodArr) { 
				$.each(prodArr,function(k,product){  
					var tr = $("<tr></tr>").appendTo("#productTable");
					tr.append("<td><input type='checkbox' id='prodCh'"+i+"/></td>").append("<td>"+(i+1)+"</td>"); 
				 
					$.each(product.split("&"),function(j,td){  
						if(td==""){
							td=0;
						}

						if(j==0){ 
							tr.append("<input type='hidden' id='productIdArr' name='addPara.productIdArr' value='"+td+"'/>"); 
						}else if(j==1){
							tr.append("<td class='productCode'>"+td+"</td>"); 
						}else if(j==6){ 
							tr.append("<td style='text-align:right; padding-right:5px'><div id='price"+i+"'>"+td+"</div></td>");  
							tr.append("<input type='hidden' name='addPara.moneyArr' value='"+td+"'/>"); 
						}else if(j==7){
							tr.append("<td style='text-align:right; padding-right:5px'>"+td+"</td>"); 
						}else if(j==11){
							tr.append("<td><input onkeyup='changeCount();' onclick='range(this)' id='count"+i+"' name='addPara.countArr' type='text' size='10' style='text-align:right; padding-right:5px' value='0' /></td>");
								
							tr.append("<td style='text-align:right; padding-right:5px'><div id='priceSum"+i+"'>0.00</div></td>");

							tr.append("<td style='text-align:right; padding-right:5px'>"+td+"</td>");  
						}else{
							tr.append("<td style='text-align:right; padding-right:5px'>"+td+"</td>"); 
						}
						
					});  

					i++;
					 
				});
				if($("#productTable")){
					$("#productTable tr").removeClass("treven"); 
					$("#productTable tr:even").addClass("treven");
					$("#productTable tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});

					});
				} 
				 
			} 

			//增加金额
			function changeCount(){ 
				
				//if(checkPrice()){ 
					  
					var sumMoney = 0; 

					$("#productTable tr:gt(0)").each(function(){  
						var price = rmoney($(this).children("td:eq(7)").text());
						var count = $(this).children("td:eq(12)").find("input").val();
						var sum = FloatMul(price,count);
						 
						$(this).children("td:eq(13)").text(formatMoney(sum)); 
						
						sumMoney = FloatAdd(sumMoney,sum); 
					}); 

					$("#sumMoney").text(formatMoney(sumMoney,2));
					$("#sendGoodsMoney").val(sumMoney); 
				  
				//}
			} 
		//还原金额   
		function rmoney(s){ 
			return parseFloat(s.replace(/[^\d\.-]/g,""));  
		} 

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
		
	//选中文本框内所有
	function range(obj){
	  obj.select();
	 }
	 
		</script>
	</head>
<body>
<html:form method="post" action="addCreateSendGoods" styleId="addForm">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td class="ye_header_left">&nbsp;</td>
		<td class="ye_header_center">
		<img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt 发货管理 &gt;&gt; <c:if test="${isUpdate!=true}">新建</c:if>发货单<c:if test="${isUpdate==true}">修改</c:if></td>
		<td class="ye_header_right">&nbsp;</td>
	</tr>
	<tr>
										
		<td >&nbsp;</td>
		<td><br /> 
			<div class="div_tiao"> &nbsp;&nbsp;销售合同信息 </div> 
			<input type="hidden" id="sellContractId" name="addPara.sellContractId" value="${sendGoodInfo.sendSellContractId}${sellContractId}"/> 
			<input type="hidden" name="addPara.id" value="${sendGoodInfo.id}"/>
			<input type="hidden" id="customerId" value="${sendGoodInfo.customerId}${salesContract.customerId}"/> 
			<input type="hidden" id="customerAddressId" name="addPara.customerAddressId" value="${sendGoodInfo.sendCustomerAddressId}"/>
			<input type="hidden" id="submitType" name="addPara.submitType"/>
			<input type="hidden" id="stockroomSelectId" value="${sendGoodInfo.sroomId}"/>
			<input type="hidden" id="sendGoodsMoney" name="addPara.sendGoodsMoney" value="${sendGoodInfo.money}"/>
			<input type="hidden" id="stockroomType" value="${stockroomTypeList}"/>

			<input type="hidden" id="caType" name="addPara.caType" value="0"/>

			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01" width="20%" height="18px">产品合同号</td>
					<td class="td_02" width="30%">${sendGoodInfo.productContractCode}${salesContract.productContractCode}&nbsp;</td>
					<td class="td_01" width="20%">公司合同号</td>
					<td class="td_02" width="30%">${sendGoodInfo.companyContarctCode}${salesContract.companyContractCode}&nbsp;</td>
				</tr>
				<tr>
					<td class="td_01" height="18px">客户名称</td>
					<td class="td_02">${sendGoodInfo.customerName}${salesContract.customerName}&nbsp;</td>
					<td class="td_01">要求发货日期</td>
					<td class="td_02">${sendGoodInfo.requestSendDate}${salesContract.requestSendDate}&nbsp;</td>
				</tr>
				<tr>
					<td class="td_01" height="18px">人员名称</td>
					<td class="td_02">${sendGoodInfo.userName}${salesContract.userName}&nbsp;</td>
					<td class="td_01">人员区域</td>
					<td class="td_02">${sendGoodInfo.areaname}${salesContract.userAreaName}&nbsp;</td>
				</tr>
				<tr>
					<td class="td_01" height="18px">项目名称</td>
					<td class="td_02">${salesContract.contractProName}${sendGoodInfo.contractProName}&nbsp;</td>
					<td class="td_01">&nbsp;</td>
					<td class="td_02">&nbsp;</td>
				</tr>
			</table>
			<br />
			<div class="div_tiao"> &nbsp;&nbsp;付款信息 </div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01" width="20%" height="18px">付款方式</td>
					<td class="td_02" width="30%">
						<c:if test="${isUpdate!=true}">
							<c:if test="${salesContract.paymentWay==0}">全部现结</c:if>
							<c:if test="${salesContract.paymentWay==1}">全部信用</c:if>
							<c:if test="${salesContract.paymentWay==2}">部分现结部分信用</c:if> 
						</c:if> 
						<c:if test="${isUpdate==true}">
							<c:if test="${sendGoodInfo.paymentWay==0}">全部现结</c:if>
							<c:if test="${sendGoodInfo.paymentWay==1}">全部信用</c:if>
							<c:if test="${sendGoodInfo.paymentWay==2}">部分现结部分信用</c:if> 
						</c:if>
					</td>
						<c:if test="${isUpdate!=true}">
							<c:if test="${salesContract.paymentWay==2}">
								<td class="td_01" width="20%" height="18px">现结金额</td>
								<td class="td_02" width="30%"><fmt:formatNumber value="${salesContract.cashMoney}" pattern="#,##0.00#"/>&nbsp;元</td>
							</c:if>
							<c:if test="${salesContract.paymentWay!=2}">
								<td class="td_01" width="20%" height="18px"></td>
								<td class="td_02" width="30%"></td>
							</c:if>
						</c:if> 
						<c:if test="${isUpdate==true}">
							<c:if test="${sendGoodInfo.paymentWay==2}">
								<td class="td_01" width="20%" height="18px">现结金额</td>
								<td class="td_02" width="30%"><fmt:formatNumber value="${sendGoodInfo.cashMoney}" pattern="#,##0.00#"/>&nbsp;元</td>
							</c:if>
							<c:if test="${sendGoodInfo.paymentWay!=2}">
								<td class="td_01"></td>
								<td class="td_02"></td>
							</c:if>
						</c:if> 
				</tr>
				<tr>  
					<c:if test="${isUpdate!=true}">
						 <c:if test="${salesContract.paymentWay==1 || salesContract.paymentWay==2}"> 
							<td class="td_01" height="18px">分批方式</td>
							<td  class="td_02" >
								<c:if test="${salesContract.batchWay==0}">不分批
									</td><td  class="td_01"></td><td class="td_02">
								</c:if>
								<c:if test="${salesContract.batchWay==1}">分批
									</td>
									<td class="td_01">单批最大金额</td>
									<td  class="td_02"><fmt:formatNumber value="${salesContract.batchMaxMoney}" pattern="#,##0.00#"/>&nbsp;元</td>
								</c:if>
							</td> 
						</c:if>
					</c:if> 
					<c:if test="${isUpdate==true}">
						 <c:if test="${sendGoodInfo.paymentWay==1||sendGoodInfo.paymentWay==2}"> 
							<td class="td_01" height="18px">分批方式</td>
							<td  class="td_02" >
								<c:if test="${sendGoodInfo.batchWay==0}">不分批
									</td><td  class="td_01"></td>
									<td class="td_02">
								</c:if>
								<c:if test="${sendGoodInfo.batchWay==1}">分批
									</td>
									<td class="td_01">单批最大金额</td>
									<td  class="td_02"><fmt:formatNumber value="${salesContract.batchMaxMoney}" pattern="#,##0.00#"/>&nbsp;元</td>
								</c:if>
							</td> 
						</c:if>
					</c:if> 
				</tr>
			</table>
			<div style="width:100%; text-align:right">单位：元</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
				<tr>
					<th>信用类型名称</th>
					<th>项目名称</th>
					<th>帐期(天)</th>
					<th>信用额度</th>
					<th>可用额度</th>
					<th>回款预收金额</th>
					<th>在途预收金额</th>
				</tr>
				
				<tr>
					<td height="18px">${sendGoodInfo.creditname}${salesContract.creditTypeName}&nbsp;</td>
					<td>${sendGoodInfo.sendProjectName}${salesContract.projectName}&nbsp;</td>
					<td style="text-align:right;">${sendGoodInfo.arterm}${salesContract.arterm}&nbsp;</td>
					<td style="text-align:right; padding-right:5px"><fmt:formatNumber value="${sendGoodInfo.climit}" pattern="#,##0.00#"/><fmt:formatNumber value="${salesContract.climit}" pattern="#,##0.00#"/></td>
					<td style="text-align:right; padding-right:5px"><fmt:formatNumber value="${sendGoodInfo.free}" pattern="#,##0.00#"/><fmt:formatNumber value="${salesContract.freeLimit}" pattern="#,##0.00#"/></td>
					<td style="text-align:right; padding-right:5px"><fmt:formatNumber value="${sendGoodInfo.sendPrepayMoney}" pattern="#,##0.00#"/><fmt:formatNumber value="${salesContract.prepayMoney}" pattern="#,##0.00#"/></td>
					<td style="text-align:right; padding-right:5px"><fmt:formatNumber value="${sendGoodInfo.sendTransitMoney}" pattern="#,##0.00#"/><fmt:formatNumber value="${salesContract.transitMoney}" pattern="#,##0.00#"/></td>
				</tr>

			</table> 
			<br/>
			<div class="div_tiao"> &nbsp;&nbsp;产品明细 </div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01" height="18px" width="20%"><span style="color:#FF0000">*</span>&nbsp;库房名称</td>
					<td class="td_02" width="30%"> 
						<html:select styleId="stockroomSelect" property="addPara.stockroomId" value="${sendGoodInfo.sroomId}" style=" width:264px">
							<html:option value="">--请选择--</html:option> 
							<html:options collection="stockroomList" property="id" labelProperty="name"/>
						</html:select>
					</td>
					<td class="td_01" width="20%">产品分类名称</td>
					<td class="td_02" width="30%">${sendGoodInfo.ptName}${salesContract.productTypeName}</td>
				</tr>
			</table>
			<div style="width:100%; text-align:right">单位：元</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="productTable" >
				<tr>
					<th nowrap="nowrap" width="30">选择</th>
					<th nowrap="nowrap" width="30">序号</th>
					<th nowrap="nowrap" width="80">产品编码</th>
					<th nowrap="nowrap" width="100">产品名称</th>
					<th nowrap="nowrap" width="60">规格型号</th>

					<th nowrap="nowrap" width="30">单位</th>
					<th nowrap="nowrap" width="90">销售数</th>
					<th nowrap="nowrap" width="60">销售单价</th>
					<th nowrap="nowrap" width="60">销售金额</th>
					<th nowrap="nowrap" width="90">已发货数</th>

					<th nowrap="nowrap" width="110">其它库房备货数</th>
					<th nowrap="nowrap" width="90">本库房备货数</th>
					<th nowrap="nowrap" width="60">发货数</th> 
					<th nowrap="nowrap" width="80">发货金额</th>
					<th nowrap="nowrap" width="50">退货数</th>

					<th nowrap="nowrap" width="80">发货可用数</th>
				</tr>
				<c:if test="${productList!=null}">
				<logic:iterate id="product" name="productList" indexId="index">
					<tr>
						<td height="18px">
							<input type='checkbox' id='prodCh${index}'/>
							<input type='hidden' id='productIdArr' name='addPara.productIdArr' value='${product.productId}'/>
						</td> 
						<td nowrap="nowrap" >${index+1}</td>
						<td nowrap="nowrap" class='productCode'>${product.productcode}</td>
						<td nowrap="nowrap" >${product.productname}</td>
						<td nowrap="nowrap" >${product.producttype}</td>
						<td nowrap="nowrap" >${product.productunit}</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${product.count}</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><div id="price${index}"><fmt:formatNumber value="${product.price}" pattern="#,##0.00#"/></div><input type='hidden' name='addPara.moneyArr' value='${product.price}'/>
						</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${product.money}" pattern="#,##0.00#"/></td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">
							<c:if test="${product.yfhnum==null}">0</c:if>
							<c:if test="${product.yfhnum!=null}">${product.yfhnum}</c:if>
						</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">
							<c:if test="${product.qtkfnum==null}">0</c:if>
							<c:if test="${product.qtkfnum!=null}">${product.qtkfnum}</c:if>
						</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">
							<c:if test="${product.bkfnum==null}">0</c:if>
							<c:if test="${product.bkfnum!=null}">${product.bkfnum}</c:if>
						</td> 
						<td nowrap="nowrap" > 
							<input onkeyup='changeCount();' onclick="range(this)" value='${product.ffcount}' id='count${index}' name='addPara.countArr' size='10' type='text' style=" text-align:right; padding-right:5px;" /> 
						</td>
						<td nowrap="nowrap" style="text-align:right; padding-right:5px">
							<div id='priceSum${index}'><fmt:formatNumber value="${product.ffmoney}" pattern="#,##0.00#"/></div>
						</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">
							<c:if test="${product.thsnum==null}">0</c:if>
							<c:if test="${product.thsnum!=null}">${product.htthsnum}</c:if>
						</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">
							<c:if test="${product.fhkynum==null}">0</c:if>
							<c:if test="${product.fhkynum!=null}">${product.fhkynum}</c:if>
						</td>
					</tr>
				</logic:iterate>
				</c:if>
			</table>
			<table width="1083" border="0" cellpadding="0" cellspacing="0" style="padding-top:5px">
				<tr>
					<td colspan="2" width="40px">
					&nbsp;<input type="checkbox" name="checkbox3" id="allchecked" />
					</td>
					<td colspan="2" width="40px" >全选</td> 
					<td>
						<a href='#'><img id="deleteProduct" src="${ctx}/images/btnDelete.gif" width="65" height="20"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href='#'><img id="addProduct" src="${ctx}/images/btnAdd.gif" width="65" height="20"/></a>
					</td> 
					<td colspan="3" width="360" nowrap="nowrap" style="text-align:right; padding-right:5px;"> 发货金额合计 </td>
					<td colspan="4" width="60" nowrap="nowrap" style="text-align:right;padding-right:5px; ">
						<div id="sumMoney" style="text-align:right;">
							<c:if test="${isUpdate==true}"><fmt:formatNumber value="${sendGoodInfo.money}" pattern="#,##0.00#"/></c:if>
							<c:if test="${isUpdate!=true}">0.00</c:if>
						</div>
					</td>
					<td nowrap="nowrap" >
						<span style=" text-align:right;">元</span>
					</td>
				</tr>
			</table>

			<br />
			<div class="div_tiao"> &nbsp;&nbsp;货运信息 </div>
			<img id="selectAddr" src="${ctx}/images/btnFHDZ.gif" width="99" height="20" />&nbsp;&nbsp;<img id="addBUtton" src="${ctx}/images/btnFHDZTJ1.gif" width="99" height="20" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr id="trUp1">
					<td width="20%" class="td_01" height="18px">货物接收单位名称</td>
					<td colspan="3" class="td_02" id="reveivename">${sendGoodInfo.custname}&nbsp;</td>
				</tr>
				<tr id="trUp2">
					<td class="td_01" height="18px">发货地址</td>
					<td colspan="3" class="td_02" id="addressname">${sendGoodInfo.address}&nbsp;</td>
				</tr>
				<tr id="trUp3">
					<td class="td_01" height="18px" width="20%">联系人</td>
					<td class="td_02" id="reveivelinkman" width="30%">${sendGoodInfo.linkman}&nbsp;</td>
					<td class="td_01" width="20%">邮编</td>
					<td class="td_02" id="postcode" width="30%">${sendGoodInfo.postcode}&nbsp;</td>
				</tr>
				<tr id="trUp4">
					<td class="td_01" height="18px">电话</td>
					<td class="td_02" id="receivetel">${sendGoodInfo.tel}&nbsp;</td>
					<td class="td_01">手机</td>
					<td class="td_02" id="receivemobile">${sendGoodInfo.mobile}&nbsp;</td>
				</tr>

				<tr id="trFt1">
					<td width="20%" class="td_01" height="18px"><span id="x1" style="color:#FF0000">*</span>&nbsp;货物接收单位名称</td>
					<td colspan="3" class="td_02"><input type="text" id="addreveivename" name="addPara.caName" value="${sendGoodInfo.custname}" style="width:240px;" maxlength="40">&nbsp;</td>
				</tr>
				<tr id="trFt2">
					<td class="td_01" height="18px"><span id="x2" style="color:#FF0000">*</span>&nbsp;发货地址</td>
					<td colspan="3" class="td_02"><input type="text" id="addaddressname" name="addPara.caAddress" value="${sendGoodInfo.address}" style="width:480px;" maxlength="40">&nbsp;</td>
				</tr>
				<tr id="trFt3">
					<td class="td_01" height="18px" width="20%"><span id="x3" style="color:#FF0000">*</span>&nbsp;联系人</td>
					<td class="td_02" width="30%"><input type="text" id="addreveivelinkman" name="addPara.caLinkman" value="${sendGoodInfo.linkman}" style="width:120px;" maxlength="4">&nbsp;</td>
					<td class="td_01" width="20%">邮编</td>
					<td class="td_02" width="30%"><input type="text" id="addpostcode" name="addPara.caPostcode" value="${sendGoodInfo.postcode}" style="width:120px;" maxlength="6">&nbsp;</td>
				</tr>
				<tr id="trFt4">
					<td class="td_01" height="18px"><span id="x4" style="color:#FF0000">*</span>&nbsp;电话</td>
					<td class="td_02"><input type="text" id="addreceivetel" name="addPara.caTel" value="${sendGoodInfo.tel}" style="width:120px;" maxlength="20">&nbsp;</td>
					<td class="td_01">手机</td>
					<td class="td_02"><input type="text" id="addreceivemobile" name="addPara.caMobile" value="${sendGoodInfo.mobile}" style="width:120px;" maxlength="12">&nbsp;</td>
				</tr>

				<tr>
					<td class="td_01" ><span style="color:#FF0000">*</span>&nbsp;要求发货日期</td>
					<td class="td_02" >
						<input type="text" id="requestDate" name="addPara.requestDate" maxlength="12"
												style="width:120px;" onfocus="calendar()"
												readonly="readonly" value="${sendGoodInfo.sendRequestDate}"/>&nbsp;
					</td>
					<td class="td_01" ><span style="color:#FF0000">*</span>&nbsp;运货方式 </td>
					<td class="td_02" >
						<html:select styleId="transportWaySelect" property="addPara.transportWay" value="${sendGoodInfo.sendTransportWay}" style="width:126px">
							<html:option value="0">无指定</html:option>
							<html:option value="1">自提</html:option> 
							<html:option value="2">快递</html:option>
							<html:option value="3">汽运</html:option>  
							<html:option value="4">铁运</html:option> 
							<html:option value="5">航空</html:option> 
							<html:option value="6">海运</html:option>
							<html:option value="7">中铁</html:option> 
							<html:option value="8">市内送货</html:option> 
							<html:option value="9">市内快递</html:option>
						</html:select> 
					</td>
				</tr> 
				<tr id="nameAndId">
					<td class="td_01" ><span style="color:#FF0000">*</span> 提货人姓名</td>
					<td class="td_02" >
						<input type="text" name="addPara.takeName" id="takeName" maxlength="4" value="${sendGoodInfo.sendTakeName}" style="width:120px;" />
					</td>
					<td class="td_01" ><span style="color:#FF0000">*</span> 身份证号码</td>
					<td class="td_02">
						<input type="text" name="addPara.takeNumber" id="takeNumber" maxlength="20" value="${sendGoodInfo.sendTakeNumber}" style="width:120px;" />
					</td>
				</tr>
				</div>
			</table>
			<br/>
			<div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="20%">特殊说明</td>
					<td class="td_04" ><textarea name="addPara.text" id="textarea" cols="100" rows="4">${sendGoodInfo.sendtext}</textarea>
					</td>
				</tr>
			</table></td>
		<td >&nbsp;</td>
	</tr>
	<tr>
		<td></td>
		<td height="50px" align="center" valign="bottom">
			<a href='#'><img id="btnSave" src="${ctx}/images/btnSave.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href='#'><img id="btnSubmit" src="${ctx}/images/btnSubmit.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<c:if test="${isUpdate!=true}">
				<a href="${ctx}/getSalesContractsList.do?init=true"><img src="${ctx}/images/btnBack.gif" /></a>	 
			</c:if> 
			<c:if test="${isUpdate==true}">
				<a href="${ctx}/getSendGoodsList.do"><img src="${ctx}/images/btnBack.gif" /></a>
			</c:if>
		</td>
		<td></td>
	</tr>
</table>
<br />
</html:form>
</body>
</html>

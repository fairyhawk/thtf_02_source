<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="tf" uri="localhost" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>回款指定</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
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
		<script language="JavaScript"> 
			var subwin;
			var subproduct;
			window.onbeforeunload=function()
		    { 
		        if(subwin!=undefined){
		            subwin.close();
		        }
		        if(subproduct!=undefined){
		        	subproduct.close();
		        }
		    }
			$(document).ready(function(){
				//隔行换色
				changeColor(); 

				//计算金额
				changePrice();

				//验证小数点后最多两位
				$.validator.addMethod("checkPoint", function(value, element) {
					var result = false; 
					var rule=/^[0-9]+([.]{1}[0-9]{1,2})?$/;
					
					if(rule.test(value)){ 
						result = true;
					}  

					return this.optional(element) || result;
				}, "指定金额小数点后最多有两位");

				//验证发货金额
				$.validator.addMethod("checkDetilePrice", function(value, element) {
					var result = false;
				 
					var tr = $(element).parent().parent();
					if(parseFloat(value) <= FloatSub(FloatSub(rmoney(tr.children("td:eq(9)").text()),rmoney(tr.children("td:eq(10)").text())), rmoney(tr.children("td:eq(12)").text()))){ 
						result = true;
					} 

					if(result){
						changePrice();
					} 
					return this.optional(element) || result;
				}, "指定金额必须小于等于发货金额-已指定金额-退货金额");


				//验证合同金额
				$.validator.addMethod("checkSellPrice", function(value, element) {
					var result = false;
				 
					var tr = $(element).parent().parent();
					if(parseFloat(value) <= FloatAdd(FloatSub(FloatSub(rmoney(tr.children("td:eq(3)").text()),rmoney(tr.children("td:eq(4)").text())) , rmoney(tr.children("td:eq(7)").text())),rmoney(tr.children("td:eq(10)").text()))){ 
						result = true;
					} 
 
					if(result){
						changePrice();
					} 
					return this.optional(element) || result;
				}, "指定金额必须小于等于合同金额-发货金额-已预收金额+退货金额");

				//form验证
				$("#appointForm").validate({
					rules: { 
						"addPara.sendGoodsPriceArr": {
							min: 0,
							checkPoint: true,
							checkDetilePrice: true
						},
						"addPara.sellPriceArr": { 
							min: 0,
							checkPoint: true,
							checkSellPrice: true
						}
						
					},
					messages: {
						"addPara.sendGoodsPriceArr":{
							min: "指定金额必须大于等于0" 
						},
					
						"addPara.sellPriceArr": {
							min: "指定金额必须大于等于0" 
						}
					} ,
					errorClass: "invalid",
					onchange: true 
				}); 

				 //提交
				 $("#butSub").click(function(){  
					 checkSubmit();
				 }); 

				 //全选 产品
				 $("#sendgoodsAll").click(function(){ 
					if($(this).attr("checked")){
						$("#sendgoodsTable :checkbox").attr("checked", true);
					}else{
						$("#sendgoodsTable :checkbox").attr("checked", false);
					} 
				 });

				 //全选 合同
				 $("#sellAll").click(function(){ 
					if($(this).attr("checked")){
						$("#sellTable :checkbox").attr("checked", true);
					}else{
						$("#sellTable :checkbox").attr("checked", false);
					} 
				 });

				 //删除 产品
				 $("#delSendgoods").click(function(){  

					 if($("#sendgoodsTable input:checked").length==0){
						alert("请先选择要删除的发货明细");
					 }else{  
						var i = 0;
						$("#sendgoodsTable :checkbox").each(function(){  
							  if($(this).attr("checked")){
								$("#sendgoodsTable tr:eq("+(i--)+")").remove();
							  }
							  i++;
						}); 

						changePrice();
						$("#sendgoodsAll").attr("checked", false);
					 }
				 });

				 //删除 产品
				 $("#delSell").click(function(){

					 if($("#sellTable input:checked").length==0){
						alert("请先选择要删除的合同");
					 }else{  
						var i = 0;
						$("#sellTable :checkbox").each(function(){  
							  if($(this).attr("checked")){
								$("#sellTable tr:eq("+(i--)+")").remove();
							  }
							  i++;
						}); 

						changePrice();
						$("#sellAll").attr("checked", false);
					 }
				 });

				 if("${errorMsg}" != ""){
					alert("${errorMsg}");
				 }

			});

			//隔行换色
			function changeColor(){
				if($("#table1")){
					$("#table1 tr").removeClass("treven");
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
				if($("#table2")){
					$("#table2 tr").removeClass("treven");
					$("#table2 tr:even").addClass("treven");
					$("#table2 tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
			}
			
			//计算指定金额
			function changePrice(){

				//计算指定明细金额
				var detileMoney = 0;
				$("#sendgoodsTable tr").each(function(i){   
					var detilePrice = $(this).children("td:eq(13)").find("input").val();
					detileMoney = FloatAdd(detileMoney,detilePrice);
				}); 
				$("#detileMoney").text(formatMoney(detileMoney,2));
				
				//计算指定合同金额
				var sellMoney = 0;
				$("#sellTable tr").each(function(j){ 
					var sellPrice = $(this).children("td:eq(11)").find("input").val();
					sellMoney = FloatAdd(sellMoney,sellPrice);
				}); 
				$("#sellMoney").text(formatMoney(sellMoney,2));

				//计算剩余额度 =回款金额-退款金额(已退)-指定明细金额-指定合同金额
				var mreturnMoney = rmoney($("#mreturnMoney").text());
				var mreturnBackMoney = rmoney($("#mreturnBackMoney").text());
				 
				var surplus = FloatSub(FloatSub(FloatSub(mreturnMoney,mreturnBackMoney),detileMoney),sellMoney);

				$("#surplus").text(formatMoney(surplus,2));

			}
			function checkMoney(){
				//计算指定明细金额
				var pd1 = true;
				var pd2 = true;
 				var detileMoney = 0;
				$("#sendgoodsTable tr").each(function(i){   
					var detilePrice = $(this).children("td:eq(13)").find("input").val();
					if(detilePrice == 0){
						pd1 = false;
						return false;
					}
				}); 
				if(pd1 == false){
					alert("指定金额不可为0");
					return false;
				}
				//计算指定合同金额
				var sellMoney = 0;
				$("#sellTable tr").each(function(j){ 
					var sellPrice = $(this).children("td:eq(11)").find("input").val();
					if(sellPrice == 0){
						pd2 = false;
						return false;
					}
				}); 
				if(pd2 == false){
					alert("预收金额不可为0");
					return false;
				}
				return true;
			}

			//提交验证
			function checkSubmit(){ 
				if(checkMoney()==false){
					return ;
				} 
				//剩余额度大于等于0
				if(rmoney($("#surplus").text()) < 0){ 
					alert("剩余额度为负,请重新分配");
				}else{
					$("#appointForm").submit();
				}
			}

			//判断小页面传过来的发货单是否存在
			function checkSendGoods(sendgoods){ 
				 
				if($("#sendgoodsTable tr").length==0){
					return false;
				}else{ 
					var result = false;
					$("#sendgoodsTable tr").each(function(){  
						 if($(this).find(".productIdArr").val()==sendgoods.split("&")[0] && $(this).find(".sendgoodsIdArr").val()==sendgoods.split("&")[1]){
							result = true;
						 }
						 
					}); 
					return result;
				}
			} 

			var sendgoodsNum =0;
			if("${sendGoodsListSize}" != ""){
			    i = "${sendGoodsListSize}"*1;
			}

		    //添加发货单
			function addSendGoods(sendGoodsArr) { 
				 
				$.each(sendGoodsArr,function(k,sendgoods){  
					var tr = $("<tr></tr>").appendTo("#sendgoodsTable");
					tr.append("<td style='border-left:1px solid #c2c2c2;'><input type='checkbox' id='sendgoodsCh'"+sendgoodsNum+"/><input type='hidden' name='addPara.sendGoodsMrDetileIdArr' value='-1'/></td>"); 
					 
					$.each(sendgoods.split("&"),function(j,td){ 
						 
						if(j==0){
							tr.append("<input type='hidden' class='productIdArr' name='addPara.productIdArr' value='"+td+"'/>"); 
						}else if(j==1){ 
							tr.append("<input type='hidden' class='sendgoodsIdArr' name='addPara.sendGoodsIdArr' value='"+td+"'/>");
							tr.append("<td>"+td+"</td>"); 
						}else if(j==13){
							tr.append("<td width='96px'><input id='send"+sendgoodsNum+"'  value='0' name='addPara.sendGoodsPriceArr' type='text' style='width:86px; text-align:right'/></td>"); 

							tr.append("<td>"+td+"</td>");  
						}else{
							if(j>7 && j<13){
								tr.append("<td style='text-align:right; padding-right:5px'>"+formatMoney(td,2)+"</td>"); 
							}else{
								tr.append("<td>"+td+"</td>"); 
							} 
						}
						
					});  

					sendgoodsNum++;
					 
				}); 

				//隔行换色
				changeColor();
				
			} 
			
			//判断小页面传过来的合同是否存在
			function checkSell(sell){ 
				 
				if($("#sellTable tr").length==0){
					return false;
				}else{ 
					var result = false;
					$("#sellTable tr").each(function(){  
						 
						 if($(this).find(".sellIdClass").val()==sell.split("&")[0]){
						 	result = true;
						 }
						 
					}); 
					return result;
				}
			} 

			var sellNum =0;
			if("${sellListSize}" != ""){
			    i = "${sellListSize}"*1;
			}

		    //添加合同
			function addSell(sellArr) { 
				 
				$.each(sellArr,function(k,sell){  
					var tr = $("<tr></tr>").appendTo("#sellTable");
					tr.append("<td style='border-left:1px solid #c2c2c2;'><input type='checkbox' id='sellCh'"+sellNum+"/><input type='hidden' name='addPara.sellMrDetileIdArr' value='-1'/></td>"); 
					
					$.each(sell.split("&"),function(j,td){ 
						if(j==0){
							tr.append("<input type='hidden' class='sellIdClass' name='addPara.sellContractIdArr' value='"+td+"'/>"); 
						}else if(j==11){
							tr.append("<td width='96' style=' text-align:right; '><input id='sell"+sellNum+"' name='addPara.sellPriceArr' value='0' type='text'style='width:86px; text-align:right'/></td>"); 

							tr.append("<td>"+td+"</td>");  
						}else{
							if(j>2 && j<11){
								tr.append("<td style='text-align:right; padding-right:5px'>"+formatMoney(td,2)+"</td>"); 
							}else{
								tr.append("<td>"+td+"</td>"); 
							} 
						}
						
					});  

					sellNum++;
					 
				}); 

				//隔行换色
				changeColor();
				
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
		</script>
		 
	</head>
<body>
<html:form method="post" action="addAppoint" styleId="appointForm">
<input type="hidden" name="addPara.mreturnId" value="${mreturn.id}" />
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 回款管理 &gt;&gt; 回款指定</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;回款信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">回款类型</td>
          <td class="td_02" width="30%" >
				<c:if test="${mreturn.returnType==0}">回款</c:if>
				<c:if test="${mreturn.returnType==1}">在途</c:if>
				<c:if test="${mreturn.returnType==2}">到帐</c:if> 
		  </td>
          <td class="td_01" width="20%">回款编号</td>
          <td class="td_02" width="30%" >${mreturn.no}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">回款方式</td>
          <td class="td_02" width="30%" >
				<c:if test="${mreturn.returnWay==1}">现金</c:if>
				<c:if test="${mreturn.returnWay==2}">支票</c:if>
				<c:if test="${mreturn.returnWay==3}">网银</c:if>
				<c:if test="${mreturn.returnWay==4}">电汇</c:if>
				<c:if test="${mreturn.returnWay==5}">银行承兑</c:if>
				<c:if test="${mreturn.returnWay==6}">承诺函</c:if>
				<c:if test="${mreturn.returnWay==7}">其它</c:if> 
		  </td>
          <td class="td_01" width="20%">凭证号</td>
          <td class="td_02" width="30%" >${mreturn.number}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">产品分类名称</td>
          <td class="td_02" width="30%" >${mreturn.productName}</td>
          <td class="td_01" width="20%">客户名称</td>
          <td class="td_02" width="30%" >${mreturn.customerName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">录入日期</td>
          <td class="td_02" width="30%">${mreturn.date}</td>
          <td class="td_01" width="20%">回款日期</td>
          <td class="td_02" width="30%">${mreturn.returnDate}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">回款金额</td>
          <td class="td_02" width="30%"><div id="mreturnMoney" style="float:left"><fmt:formatNumber value="${mreturn.money}" pattern="#,##0.00#"/></div>&nbsp;元</td>
          <td class="td_01" width="20%">退款金额</td>
          <td class="td_02" width="30%"><div id="mreturnBackMoney" style="float:left"><fmt:formatNumber value="${mreturn.backMoney}" pattern="#,##0.00#"/></div>&nbsp;元</td>
        </tr>
      </table>
      <BR />
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
      <div style=" padding:4px 0px 0px 10px "><a href="#" onclick="javascript:subwin = window.open('${ctx}/getAppointSendGoods.do?mreturnPara.mreturnId=${mreturn.id}','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=1400,height=700');"><img src="${ctx}/images/btnFHMX.gif" width="99" height="20" align="absmiddle" /></a></div>
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table1" style="border-left:1px solid #FFFFFF;">
        <thead>
          <th nowrap="nowrap" style="border-left:1px solid #c2c2c2;">选择</th>
          <th nowrap="nowrap" >发货单号</th>
          <th nowrap="nowrap">产品合同号</th>
          <th nowrap="nowrap">公司合同号</th>
          <th nowrap="nowrap">产品编码</th>

          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap">销售单价</th>
          <th nowrap="nowrap">发货金额</th>

          <th nowrap="nowrap">已指定金额</th>
          <th nowrap="nowrap">开票金额</th>
          <th nowrap="nowrap">退货金额</th> 
          <th nowrap="nowrap">指定金额</th> 
          <th nowrap="nowrap">人员名称</th>
        </thead>
        <tbody id="sendgoodsTable">
		<logic:iterate id="sendgoods" name="sendGoodsList" indexId="index">
		<tr>
		  <td width="30" nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18px">
			<c:if test="${sendgoods.status==2||sendgoods.status==3}">
				&nbsp;
			</c:if>
			<c:if test="${sendgoods.status==5}">
				<input type="checkbox" name="checkbox2" id="sendgoodsCh${index}"/>
			</c:if>
			<input type="hidden" class="productIdArr" name="addPara.productIdArr" value="${sendgoods.productId}"/>
			<input type="hidden" class="sendgoodsIdArr" name="addPara.sendGoodsIdArr" value="${sendgoods.sendGoodsId}"/>
			<input type="hidden" name="addPara.sendGoodsMrDetileIdArr" value="${sendgoods.mreturnDetileId}"/> 
			 
		  </td>
		  <td nowrap="nowrap">${sendgoods.sendGoodsId}&nbsp;</td>
		  <td nowrap="nowrap" >${sendgoods.productContractCode}&nbsp;</td>
		  <td nowrap="nowrap" >${sendgoods.companyContractCode}&nbsp;</td>
		  <td nowrap="nowrap" >${sendgoods.productCode}&nbsp;</td>

		  <td nowrap="nowrap" >${sendgoods.productName}&nbsp;</td>
		  <td nowrap="nowrap" >${sendgoods.productType}&nbsp;</td>
		  <td nowrap="nowrap" >${sendgoods.productUnit}&nbsp;</td>
		  <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${sendgoods.sellContractDetailPrice}" pattern="#,##0.00#"/>&nbsp;</td>
		  <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${sendgoods.fhMoney}" pattern="#,##0.00#"/>&nbsp;</td>

		  <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${sendgoods.sendGoodsMoney-sendgoods.appointMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		  <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${sendgoods.kpMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		  <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${sendgoods.thMoney}" pattern="#,##0.00#"/>&nbsp;</td> 
		  <td nowrap="nowrap" width="96px">
			<c:if test="${sendgoods.status==2||sendgoods.status==3}">
				${sendgoods.appointMoney}<input type="hidden" id="send${index}" name='addPara.sendGoodsPriceArr' value="${sendgoods.appointMoney}" style="width:86px; text-align:right"/> 
			</c:if>
			<c:if test="${sendgoods.status==5}">
				<input type="text" id="send${index}" name='addPara.sendGoodsPriceArr' value="${sendgoods.appointMoney}" style="width:86px; text-align:right"/> 
			</c:if> 
		  </td>
		   
		  <td nowrap="nowrap" >${sendgoods.userName}&nbsp;</td>
		</tr> 
		</logic:iterate>
        </tbody>
        <tr>
          <td nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">
            <input type="checkbox" name="checkbox3" id="sendgoodsAll" />
          </td>
          <td nowrap="nowrap" colspan="2" style="border:1px solid #FFFFFF; background-color:#FFFFFF" height="18">全选&nbsp;&nbsp;&nbsp;&nbsp;
          <img id="delSendgoods" src="${ctx}/images/btnDelete.gif" width="65" height="20" /></td>
          <td nowrap="nowrap" colspan="9" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">指定金额合计</td>
          <td id="detileMoney" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">0</td>
          <td nowrap="nowrap" style=" border:1px solid #FFFFFF; background-color:#FFFFFF">元</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;销售合同信息</div>
        <a href="#" onclick="javascript:subproduct = window.open('${ctx}/getAppointSellContract.do?mreturnPara.mreturnId=${mreturn.id}','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=1400,height=700');"><img src="${ctx}/images/btnXSHT.gif" /></a>
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table2" style="border-left:1px solid #FFFFFF;">
        <thead>
          <th nowrap="nowrap" style="border-left:1px solid #c2c2c2;">选择</th>
          <th nowrap="nowrap" >产品合同号</th>
          <th nowrap="nowrap">公司合同号</th>

          <th nowrap="nowrap">合同金额</th>
          <th nowrap="nowrap">发货金额</th>
          <th nowrap="nowrap">备货金额</th>

          <th nowrap="nowrap">指定金额</th>
          <th nowrap="nowrap">已预收金额</th>
          <th nowrap="nowrap">开票金额</th>
          <th nowrap="nowrap">退货合同金额</th>
          <th nowrap="nowrap">退货金额</th> 

          <th nowrap="nowrap">预收金额</th>

          <th nowrap="nowrap">人员名称</th>
        </thead>
		<tbody id="sellTable">
		<logic:iterate id="sellContract" name="sellList" indexId="index">
		<tr>
		  <td width="30" nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18px">
			<input type="checkbox" name="checkbox" id="sellCh${index}" />
			<input type="hidden" id="sellIdArr" name="addPara.sellContractIdArr" value="${sellContract.id}"/>
			<input type="hidden" name="addPara.sellMrDetileIdArr" value="${sellContract.mreturnDetileId}"/>
			
		  </td>
		  <td nowrap="nowrap">${sellContract.productContractCode}&nbsp;</td>
		  <td nowrap="nowrap" >${sellContract.companyContractCode}&nbsp;</td>
		  <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${sellContract.money}" pattern="#,##0.00#"/>&nbsp;</td>
		  <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${sellContract.fhMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		  <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${sellContract.bhMoney}" pattern="#,##0.00#"/>&nbsp;</td>

		  <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${sellContract.sendGoodsMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		  <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${sellContract.sellMoney-sellContract.appointMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		  <td nowrap="nowrap"  width="96" style=" text-align:right; "><fmt:formatNumber value="${sellContract.kpMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		  <td nowrap="nowrap"  width="96" style=" text-align:right; "><fmt:formatNumber value="${sellContract.sbMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		  <td nowrap="nowrap"  width="96" style=" text-align:right; "><fmt:formatNumber value="${sellContract.thMoney}" pattern="#,##0.00#"/>&nbsp;</td> 

		  <td nowrap="nowrap"  width="96" style=" text-align:right; ">
			<input type="text" name="addPara.sellPriceArr" id="sell${index}" style="width:86px; text-align:right" value="${sellContract.appointMoney}"/>&nbsp;
		  </td>

		  <td nowrap="nowrap" >${sellContract.userName}&nbsp;</td> 
		</tr> 
		</logic:iterate>
        </tbody> 
        <tr>
          <td nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">
				<input type="checkbox" name="checkbox4" id="sellAll" />
		  </td>
          <td nowrap="nowrap" colspan="9" valign="middle" style="border:1px solid #FFFFFF; background-color:#FFFFFF">全选&nbsp;&nbsp;&nbsp;&nbsp;
			<img id="delSell" src="${ctx}/images/btnDelete.gif" width="65" height="20" />
		  </td>
          <td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">预收金额合计</td>
          <td id="sellMoney" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">0</td>
          <td nowrap="nowrap" style=" text-align:left; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">元</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;产品分类信息</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="50%" height="18px">剩余额度</td>
          <td class="td_04"><div id="surplus" style="float:left"></div>&nbsp;元</td>
        </tr>
      </table>
      <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
            <td class="td_03" width="20%">特殊说明</td>
            <td class="td_04"  style="padding:5px 100px 5px 5px">${tf:replaceBr(mreturn.text)}</td>
        </tr>
    </table>
      </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom"> 
		<img id="butSub" src="${ctx}/images/btnZD.gif" width="65" height="20" /> 
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		<a href="${ctx}/mreturn.do"><img src="${ctx}/images/btnBack.gif" /></a>
	</td>
    <td></td>
  </tr>
</table>
<br />
</html:form>
</body>
</html>

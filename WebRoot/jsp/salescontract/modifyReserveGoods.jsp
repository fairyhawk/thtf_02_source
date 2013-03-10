<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="tf" uri="localhost" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>备货单修改</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" /> 
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
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
		<!--
			var subwin;
			$(document).ready(function(){
				
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
				if('${ProMajIder[0]}'=='1'){
		        	$('#ProMajIder1').attr("checked",'true');
		        }
		        if('${ProMajIder[0]}'=='0'){
		        	$('#ProMajIder0').attr("checked",'true');
		        }
		        if('${AreaMajId[0]}'=='1'){
		        	$('#AreaMajId1').attr("checked",'true');
		        }
		        if('${AreaMajId[0]}'=='0'){
		        	$('#AreaMajId0').attr("checked",'true');
		        }
			});
		//-->
		</script>
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
		<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
<script type="text/javascript">
$(function(){	
				//初始化金额
				changeCount();
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
					 if($("#productTable tr").length==1){
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
					 }
					 $("#allchecked").attr("checked",false);
				 });



				 //打开选择产品页面
				 $("#addProduct").click(function(){   
					 var stockroomId = $("#stockroomSelect").val(); 
					 if(stockroomId!=""){
						 var sellContractId = $("#sellContractId").val();  

						 subwin = window.open('${ctx}/getReserveProductByStockroomId.do?stockroomId='+stockroomId+'&sellContractId='+sellContractId,'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=250');
					 }
				 });
				 
				 //提交
				 $('#addReserveGoods').click(function(){
				 	$("#submitType").val("2");
				 	checkSubmit();
				 }); 
				 //保存
				 $('#saveReserveGoods').click(function(){
				 	$("#submitType").val("1");
				 	checkSubmit();
				});
				 $('#backSendGoodList').click(function(){
				 	window.location.href='getSendGoodsList.do';
				});
	
				if($('#err').val()!=''){
					alert($('#err').val());
				}
			});
			
			
			//判断小页面传过来的产品是否存在
			function checkProduct(prod){
				if($("#productTable td.productIdArr").length==0){
					return false;
				}else{
					var result;
					$("#productTable td.productIdArr").each(function(){  
						 if($(this).text()==prod.split("&")[1]){
						 	result = true;
						 }
					});
					return result;
				} 
			}

		    //添加产品
			function addProduct(prodArr) { 
				$.each(prodArr,function(i,product){ 
					var tr = $("<tr></tr>").appendTo("#productTable");
					tr.append("<td><input type='checkbox' id='prodCh'"+i+"'/></td>").append("<td>"+(i+1)+"</td>"); 
					
					$.each(product.split("&"),function(j,td){
						if(td==""){
							td=0;
						}
						if(j==10){
							tr.append("<td><input onChange='changeCount();' id='count"+i+"' name='reserveGoodsNum' id='reserveGoodsNum' type='text' value='0' style='text-align:right;padding-right:5px;width:80px' /></td>");
								
							tr.append("<td style='text-align:right;padding-right:5px;'><input type='hidden' id='priceSum"+i+"' name='reserveGoodsMoney' value='' style='border:0 none;width:90px;text-align:right;padding-right:6px;' readonly />"+td+"</td>");

							tr.append("<td style='text-align:right;padding-right:5px;'>"+td+"</td>");  
						}else if(j==0){
							tr.append("<input type='hidden' name='productId' id='productId' value='"+td+"'/>"); 
						}else if(j==6){
							tr.append("<td style='text-align:right;padding-right:5px;'><input type='hidden' id='price' name='price' value='"+td+"' style='border:0 none;width:80px' readonly />"+formatMoney(td,2)+"</td>");
						}else if(j==7){
							tr.append("<td style='text-align:right;padding-right:5px;'>"+formatMoney(td,2)+"</td>");
						}else if(j>4){
							tr.append("<td style='text-align:right;padding-right:5px;'>"+td+"</td>");
						}else if(j==1){
							tr.append("<td class='productIdArr'>"+td+"</td>"); 
						}
						else{
							tr.append("<td>"+td+"</td>"); 
						}
						
					}); 
					 
				});
				 changeCount();
			}
			
			//金额的验证
			function checkPrice(){ 
			    $("#productTable tr").each(function(i){  
			    	if(i!=0){
					//销售数-已发货数-备货数（本合同其他库房）+本合同退货数(退货入库成功)
					var xsNum = $(this).children("td:eq(6)").text()*1;
					var yfhNum = $(this).children("td:eq(9)").text()*1;
					var bhNum = $(this).children("td:eq(10)").text()*1;
					var thsNum = $(this).children("td:eq(13)").text()*1;

					var num = (xsNum - yfhNum - bhNum + thsNum)*1; 
					
					var count = $(this).children("td:eq(11)").find("input").val()*1;  
					
					//alert("销售数："+xsNum
					//		+"_发货数："+yfhNum
					//		+"_其他备货数："+bhNum
					//		+"_退货数："+thsNum
					//		+"_可用备货数："+num
					//		+"_备货数："+count);
					if(count==""){
					    alert("备货数不能为空");
					    votetext = true;
						return false;
					}
					
					var regExp = /^[1-9]\d*/;
					if (regExp.test(count) == false) {
						alert('备货数只能输入自然数！');
						votetext = true;
						return false;
					}
					
					if(count > $(this).children("td:eq(14)").text()*1){
					    alert("备货数<=备货可用数");
					    votetext = true;
						return false;
					}  
					if(count> num){
					    alert("备货数<=销售数-发货数-备货数（本合同其他库房）+本合同退货数(退货入库成功)");
					    votetext = true;
						return false;
					}
					}
				});
			}
			
			//提交验证
			var votetext= false;
			function checkSubmit(){  
			 	votetext= false;
			 	if($("#productTable tr").length==1){
					alert("请先添加产品");
					return false; 
				 }
				 if($("#reserveText").text().length>200){
					alert("特殊说明长度不能超过200");
					return false;
				 }
				 
				 if($("#productTable tr").length>1){
			 		checkPrice();
			 	} 
			 	//alert(votetext);
				if(votetext){
					return false;
				}
				
			 	if($("#willSendGoodsDate").val()==""){
					 alert("请选择预计发货日期");
					 return false; 
				 }
			 	if($("#willSendGoodsDate").val()!=""){
					var date = $("#willSendGoodsDate").val().split("-");  
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
				 
				 document.forms[0].submit(); 
			} 
			
			//计算备货金额
			function changeCount(){
				//var text = $("#productTable input[type='text']");
				var reserveGoodsNum;
				var price;
				var reserveGoodsMoneyE=0;
				var sumMoney = 0;
				$("#productTable tr").each(function(i){
					if(i!='0'){
						reserveGoodsNum = $(this).children("td:eq(11)").find('input').val();
						price = $(this).children("td:eq(7)").find('input').val();
						sumMoney = FloatMul(reserveGoodsNum,price);
						$(this).children("td:eq(12)").text(formatMoney(sumMoney,2));
						reserveGoodsMoneyE=FloatAdd(reserveGoodsMoneyE,sumMoney);
						$(this).children("td:eq(1)").text(i);
					}
				}); 

				$("#reserveGoodsMoneyV").val(formatMoney(reserveGoodsMoneyE,2));
				$("#reserveGoodsMoneyE").val(reserveGoodsMoneyE);
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
<html:form method="post" action="addCreateReserveGoods.do" >
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td class="ye_header_left">&nbsp;</td>
		<td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt; 发货管理 &gt;&gt; 备货单修改</td>
		<td class="ye_header_right">&nbsp;</td>
	</tr>
	<tr>
		<td >&nbsp;</td>
		<td><br />
			<div class="div_tiao"> &nbsp;&nbsp;备货单信息 </div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01"><span style="color:#FF0000">*</span>&nbsp;预计发货日期</td>
					<td class="td_02" >
							<input type="text" name="willSendGoodsDate" id="willSendGoodsDate" maxlength="12"
									style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${sge.sendRequestDate}" />
						</td>
					<td class="td_01"></td>
					<td class="td_02"></td>
				</tr>
			</table>
			<br />
			<div class="div_tiao"> &nbsp;&nbsp;销售合同信息 </div>
			<input type="hidden" id="sellContractId" name="sellContractId" value="${sge.sellid}"/>
			<input type="hidden" id="sendGoodId" name="sendGoodId" value="${sge.id}"/>
			<input type="hidden" id="customerId" name="customerId" value="${salesContract.customerId}"/>
			<input type="hidden" id="addressid" />
			<input type="hidden" id="stockroomSelectId value="${sge.sendStockRoomId}"/>
			<input type="hidden" id="submitType" name="submitType"/>
			<input type="hidden" id="err" name="err" value="${err}"/>
			<input type="hidden" id="reserveGoodsMoneyE" name="reserveGoodsMoneyE" value=""/>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01" width="20%" height="18px">产品合同号</td>
					<td class="td_02" width="30%">${sge.productContractCode}&nbsp;</td>
					<td class="td_01" width="20%">公司合同号</td>
					<td class="td_02" width="30%">${sge.companyContarctCode}&nbsp;</td>
				</tr>
				<tr>
					<td class="td_01" height="18px">客户名称</td>
					<td class="td_02">${sge.customerName}&nbsp;</td>
					<td class="td_01">要求发货日期</td>
					<td class="td_02">${sge.requestSendDate}&nbsp;</td>
				</tr>
				<tr>
					<td class="td_01" height="18px">人员名称</td>
					<td class="td_02">${sge.userName}&nbsp;</td>
					<td class="td_01">人员区域</td>
					<td class="td_02">${sge.areaname}&nbsp;</td>
				</tr>
				<tr>
					<td class="td_01" height="18px">项目名称</td>
					<td class="td_02">${sge.contractProName}&nbsp;</td>
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
						<c:if test="${sge.paymentWay==0}">全部现结</c:if>
						<c:if test="${sge.paymentWay==1}">全部信用</c:if>
						<c:if test="${sge.paymentWay==2}">部分现结部分信用</c:if>
						</td>
					<td class="td_01" width="20%">&nbsp;<c:if test="${sge.paymentWay==1}">现结金额</c:if></td>
					<td class="td_02" width="30%"><c:if test="${sge.paymentWay==1}"><fmt:formatNumber value="${sge.cashMoney}" pattern="#,##0.00#"/>&nbsp;元</c:if>&nbsp;</td>
				</tr>
				<c:if test="${sge.batchWay!=1||sge.batchWay!=0}">
			<tr>
				<td class="td_01" height="18px">分批方式</td>
				<td  class="td_02" >
					<c:if test="${sge.batchWay==0}">
						不分批&nbsp;
						&nbsp;
					</c:if>
					<c:if test="${sge.batchWay==1}">
						分批&nbsp;
					</c:if>
					
				</td>
				<c:if test="${sge.batchWay!=1}">
					<td class="td_01">&nbsp;</td>
					<td class="td_02">&nbsp;</td>
				</c:if>
				<c:if test="${sge.batchWay==1}">
					<td class="td_01">单批最大金额</td>
					<td  class="td_02" ><fmt:formatNumber value="${sge.batchMaxMoney}" pattern="#,##0.00#"/>&nbsp;</td>
				</c:if>
			</tr>
			</c:if>
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
					<td height="18px">${sge.creditname}&nbsp;</td>
					<td>${sge.sendProjectName}&nbsp;</td>
					<td style="text-align:right">${sge.arterm}&nbsp;</td>
					<td style="text-align:right"><fmt:formatNumber value="${sge.climit}" pattern="#,##0.00#"/>&nbsp;</td>
					<td style="text-align:right"><fmt:formatNumber value="${sge.free}" pattern="#,##0.00#"/>&nbsp;</td>
					<td style="text-align:right"><fmt:formatNumber value="${sge.sendPrepayMoney}" pattern="#,##0.00#"/>&nbsp;</td>
					<td style="text-align:right"><fmt:formatNumber value="${sge.sendTransitMoney}" pattern="#,##0.00#"/>&nbsp;</td>
				</tr>
			</table>
			<br/>
			<div class="div_tiao"> &nbsp;&nbsp;产品明细 </div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01" width="20%" height="18px">&nbsp;库房名称</td>
					<td class="td_02" width="30%"> 
					<input type="hidden" id="stockroomSelect" name="stockroomSelect" value="${sge.sendStockRoomId}"/>
	              			${sge.sroomName}&nbsp;
					</td>
					<td class="td_01" width="20%">产品分类名称</td>
					<td class="td_02" width="30%">${sge.ptName}&nbsp;</td>
				</tr>
			</table>
			<div style="width:100%; text-align:right">单位：元</div>
			<table width="100%" id="productTable" border="0" cellpadding="0" cellspacing="0" class="biao1">
				<tr>
					<th nowrap="nowrap" width="40px">选择</th>
					<th nowrap="nowrap">序号</th>
					<th nowrap="nowrap">产品编码</th>
					<th nowrap="nowrap">产品名称</th>
					<th nowrap="nowrap">规格型号</th>
					<th nowrap="nowrap">单位</th>
					<th nowrap="nowrap">销售数</th>
					<th nowrap="nowrap">销售单价</th>
					<th nowrap="nowrap">销售金额</th>
					<th nowrap="nowrap">发货数</th>
					<th nowrap="nowrap">其它库房备货数</th>
					<th nowrap="nowrap">本库房备货数</th>
					<th nowrap="nowrap" width="90px">备货金额</th>
					<th nowrap="nowrap" width="70px">&nbsp;&nbsp;退货数&nbsp;&nbsp;</th>
					<th nowrap="nowrap" width="80px">&nbsp;备货可用数&nbsp;</th>
				</tr>
				<logic:present name="list">
				<logic:iterate id="ssp" name="list" indexId="index">
					<tr>
						<td nowrap="nowrap"><input type="checkbox" id="prodCh" />
							<input type="hidden" name="productId" id="productId" value="${ssp.productId}"/>
						</td>
						<td nowrap="nowrap">${index+1}</td>
						<td nowrap="nowrap" class="productIdArr">${ssp.productcode}</td>
						<td nowrap="nowrap" >${ssp.productname}</td>
						<td nowrap="nowrap" >${ssp.producttype}</td>
						<td nowrap="nowrap" >${ssp.productunit}</td>
						<td nowrap="nowrap" style="text-align:right; padding-right:5px;">${ssp.count}</td>
						<td nowrap="nowrap" style="text-align:right; padding-right:5px;"><input type='hidden' id="price" name="price" value="${ssp.price}" style="border:0 none;width:80px" readonly /><fmt:formatNumber value="${ssp.price}" pattern="#,##0.00#"/></td>
						<td nowrap="nowrap" style="text-align:right; padding-right:5px;"><fmt:formatNumber value="${ssp.money}" pattern="#,##0.00#"/></td>
						<td  nowrap="nowrap" style="text-align:right; padding-right:5px;">
							<c:if test="${ssp.yfhnum==null}">0</c:if>
							<c:if test="${ssp.yfhnum!=null}">${ssp.yfhnum}</c:if>
						</td>
						<td  nowrap="nowrap" style="text-align:right; padding-right:5px;">
							<c:if test="${ssp.qtkfnum==null}">0</c:if>
							<c:if test="${ssp.qtkfnum!=null}">${ssp.qtkfnum}</c:if>
						</td>
						<td nowrap="nowrap">
							<c:if test="${ssp.bkfnum==null}"><input onChange="changeCount();" onclick="range(this)" id="count" name="reserveGoodsNum" id="reserveGoodsNum" type="text" value="0" style="text-align:right;width:80px; padding-right:5px;" /></c:if>
							<c:if test="${ssp.bkfnum!=null}"><input onChange="changeCount();" onclick="range(this)" id="count" name="reserveGoodsNum" id="reserveGoodsNum" type="text" value="${ssp.bkfnum}" style="text-align:right;width:80px; padding-right:5px;" /></c:if>
						</td>
						<td nowrap="nowrap" width="90px" style="text-align:right; padding-right:5px;">
							<c:if test="${ssp.bfmoney==null}"><input type='hidden' id="priceSum" name="reserveGoodsMoney" value="0.00" style="border:0 none; width:89px;text-align:right;padding-right:6px;" readonly />0</c:if>
							<c:if test="${ssp.bfmoney!=null}"><input type='hidden' id="priceSum" name="reserveGoodsMoney" value="${ssp.bfmoney}" style="border:0 none; width:89px;text-align:right;padding-right:6px;" readonly /><fmt:formatNumber value="${ssp.bfmoney}" pattern="#,##0.00#"/></c:if>
						</td>
						<td nowrap="nowrap" style="text-align:right; padding-right:5px;">
							<c:if test="${ssp.thsnum==null}">0</c:if>
							<c:if test="${ssp.thsnum!=null}">${ssp.htthsnum}</c:if>
						</td>
						<td nowrap="nowrap" style="text-align:right; padding-right:5px;">
							<c:if test="${ssp.fhkynum==null}">0</c:if>
							<c:if test="${ssp.fhkynum!=null}">${ssp.fhkynum}</c:if>
						</td>
					</tr>
				</logic:iterate>
	   		 </logic:present>
	   		 </table>
				<table width="1072" border="0" cellpadding="0" cellspacing="0" style="padding-top:5px"> 
					<tr>
						<td colspan="2" width="40px">&nbsp;<input type="checkbox" name="checkbox3" id="allchecked" /></td>
						<td colspan="2" width="40px" nowrap="nowrap">全选</td>
						<td nowrap="nowrap">
							<a href='#'><img id="deleteProduct" src="${ctx}/images/btnDelete.gif" width="65px" height="20" /></a>&nbsp;&nbsp;
							<a href='#'><img id="addProduct" src="${ctx}/images/btnAdd.gif" width="65px" height="20"/></a>
						</td>
						</td>
						<td colspan="3" nowrap="nowrap" width="400"  style="text-align:right;"> 备货金额合计 </td>
						<td colspan="4" nowrap="nowrap" width="60" style="text-align:right;"><input id="reserveGoodsMoneyV" name="reserveGoodsMoneyV" value="0.00" style="border:0 none; width:80px;text-align:right;padding-right:7px;" readonly /></td>
						<td nowrap="nowrap" >
						<span style=" text-align:right;">元</span>
						</td>
					</tr>
				</table>
			<br />
			<div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="20%">特殊说明</td>
					<td class="td_04" ><textarea name="reserveText" id="reserveText" cols="100" rows="4" >${sge.sendtext}</textarea>
					</td>
				</tr>
			</table>
			<div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
            <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
             <tr>
                <td height="20px" colspan="2" >区域总监评审意见：</td>
              </tr>
              <tr>
                <td width="320" nowrap="nowrap">&nbsp;</td>
                <td height="20px" width="140px" nowrap="nowrap" algin="right">
					<input type="checkbox" name="AreaMajId1" id="AreaMajId1" disabled="disabled" />同意&nbsp;&nbsp;
					<input type="checkbox" name="AreaMajId0" id="AreaMajId0" disabled="disabled" />不同意
                </td>
              </tr>
              <tr>
                <td colspan="2">
					<table width="438px" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td valign="top" width="65px" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                      <td style="padding:5px 0 5px 0;" >${tf:replaceBr(sge.sendAreaMajText)}&nbsp;</td>
                    </tr>
                </table></td>
              </tr>
              <tr>
                <td width="320" height="20px">签字：${sge.sendAreaMajName}</td>
				<td width="140">日期：${sge.sendAreaMajDate}</td>
             </tr>
             <tr>
                <td height="20px" colspan="2" >销售总监评审意见：</td>
              </tr>
              <tr>
                <td width="320" nowrap="nowrap">&nbsp;</td>
                <td height="20px" width="140px" nowrap="nowrap" algin="right">
					<input type="checkbox" name="ProMajIder1" id="ProMajIder1" disabled="disabled" />同意&nbsp;&nbsp;
					<input type="checkbox" name="ProMajIder0" id="ProMajIder0" disabled="disabled" />不同意
                </td>
              </tr>
              <tr>
                <td colspan="2">
					<table width="438px" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td valign="top" width="65px" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                      <td style="padding:5px 0 5px 0;" >${tf:replaceBr(sge.sendProMajText)}&nbsp;</td>
                    </tr>
                </table></td>
              </tr>
              <tr>
                <td width="320" height="20px">签字：${sge.sendProMajName}</td>
				<td width="140">日期：${sge.sendProMajDate}</td>
             </tr>
            </table>
		</td>
		<td >&nbsp;</td>
	</tr>
	<tr>
		<td></td>
		<td height="50px" align="center" valign="bottom">
		<a id='saveReserveGoods' href='#'><img src="${ctx}/images/btnSave.gif" href="" width="65" height="20" /></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		<a id='addReserveGoods' href='#'><img src="${ctx}/images/btnSubmit.gif" width="65" height="20" /></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		<a id="backSendGoodList" href='#'><img src="${ctx}/images/btnBack.gif" /></a></td>
    <td></td>
  </tr>
</table>
<br />
</html:form>
</body>
</html>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>开票申请</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
</head>
<body>
<style>
#div_title{
	font-size:12px;
}
#div_idea table{
	font-size:14px;
}
.treven {
	background-color: #f3fbff;
}
.over {
	background-color: #ECECEC;
</style>
<script type="text/javascript">
	function getOpenerVal(val){//客户信息
		if(val.id!=$("#cid").val()){//如果客户改变
			$("#makeInvoiceSum").text("0");//清0
			$("#money").val("0");
			var length=$("#table1 tr").length;
			$("#table1 tr").each(function(i){
				
				if(i!=0 && i!=length-1){
					$(this).remove();
					$("#productTypeId").val("");
				}
			});	
		}
		$("#invoiceBankTel").html(val.invoiceBankTel+"&nbsp;");
		$("#taxNumber").html(val.taxNumber+"&nbsp;");
		$("#invoiceBankAccount").html(val.invoiceBankAccount+"&nbsp;");
		$("#invoiceBankAddress").html(val.invoiceBankAddress+"&nbsp;");
		$("#invoiceBankName").html(val.invoiceBankName+"&nbsp;");
		$("#invoiceBankTel").html(val.invoiceBankTel+"&nbsp;");
		$("#invoiceTypeName").html(val.invoiceTypeName+"&nbsp;");
		$("#cname").val(val.name);
		$("#cid").val(val.id);
		$("#tdCustomerId").html(val.name+"&nbsp;");
		$("#invoiceType").val(val.invoiceType);
		
		
	}
	function openSendGoodsWindow(){
		if($("#cid").val()==""){
			alert("请先选择客户");
			return;
		}
		window.open(jsCtx+"/getSendGoodsParticularList.do?cid="+$("#cid").val()+"&pTypeId="+$("#productTypeId").val()+"","newwindow", "toolbar=no,scrollbars=yes,resizable=no,top=200,left=230, width=1050,height=550");
	}
	function getOpenerValOfProduct(html,id,name,productType){
		var trLength=$("#table1 tr").length-1;//获得tr的个数（序号）
		if($("input[id="+id+"]").attr("id")==null){//判断是否有重复id标示
			$("#lastTr").before("<tr id=tr"+trLength+">"+html+"</tr>");//插入到最后一行前
			$("#first").after("<td style='padding-right:5px;text-align:right'>"+trLength+"</td>");//加入序号列 first 为第一列的id;序号为第二列
			$("#first").attr("id","discard");//取消第一列id 变成废弃的
			$("#id").attr("id",id+name)//给checkbox加唯一标识
			$("#getDate").css("display","none").remove();
			//$("#getDate").attr("id","discard");
			$("#tdProductTypeName").css("display","none");//tdProductTypeName列隐藏（无用）
			$("#tdProductTypeName").attr("id","discard");//变成废弃的
			$("#tdInvoiceCount").after("<td style='text-align:right;padding-right:5px'><input type='text' name='makeInvoiceSum"+trLength+"' id='makeInvoiceSum"+trLength+"' value='0' size=8 onblur='makeInvoiceSumVerdict(this)'/></td>");//添加开票数框
			$("#tdInvoiceCount").attr("id","discard");//变成废弃的
			$("#productTypeId").val(productType.id);
			$("#productTypeName").text(productType.name);
			$("#tdInvoiceMoney").css("display","");
			var makeInvoiceSum=FloatAdd($("#money").val(),$("#tdInvoiceMoney").text());//求和
			$("#tdInvoiceMoney").attr("id","discard");//变成废弃的
			$("#makeInvoiceSum").text(formatMoney(makeInvoiceSum.toFixed(2),2));//重新取和
			$("#money").val(makeInvoiceSum.toFixed(2));
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
	}
	$(document).ready(function(){
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
	$("#invoiceSave").click(function(){//点击保存
		if($("#cid").val()==""){alert("请选择客户");return;}
		if($("#table1 tr").length-1==1){alert("请选择发货明细");return;}
		if(!getJSONValue()){return};
		if($("#text").text().length>200){
			alert("特殊说明字符不能超过200字");
			return;
		}
		document.getElementById("invoiceForm").action=jsCtx+"/addInvoice.do?type=save";
		document.forms[0].submit();
	});
	$("#invoiceSubmit").click(function(){//点击提交
		if($("#cid").val()==""){alert("请选择客户");return;}
		if($("#table1 tr").length-1==1){alert("请选择发货明细");return;}
		if(!getJSONValue()){return};
		if($("#text").text().length>200){
			alert("特殊说明字符不能超过200字");
			return;
		}
		document.getElementById("invoiceForm").action=jsCtx+"/addInvoice.do?type=submit";
		document.forms[0].submit();
	});
	$("#invoiceSaveOfModify").click(function(){//点击保存  修改
		if($("#cid").val()==""){alert("请选择客户");return;}
		if($("#table1 tr").length-1==1){alert("请选择发货明细");return;}
		if(!getJSONValue()){return};
		if($("#text").text().length>200){
				alert("特殊说明字符不能超过200字");
				return;
		}
		document.getElementById("invoiceForm").action=jsCtx+"/modifyInvoiceOfSubmit.do?type=save";
		document.forms[0].submit();
	});
	$("#invoiceSubmitOfModify").click(function(){//点击提交  修改
		if($("#cid").val()==""){alert("请选择客户");return;}
		if($("#table1 tr").length-1==1){alert("请选择发货明细");return;}
		if(!getJSONValue()){return};
		if($("#text").text().length>200){
			alert("特殊说明字符不能超过200字");
			return;
		}
		document.getElementById("invoiceForm").action=jsCtx+"/modifyInvoiceOfSubmit.do?type=submit";
		document.forms[0].submit();
	});	
	function getJSONValue(){//制造json格式数据  付给returnValueToServer隐藏对象  
		var sumId=$("#table1 tr").length-2;
		var returnVlaue="{resultSum:"+sumId+",rows:[";
		for(var i=1;i<=sumId;i++){
			var sendGoodsId=$("#tr"+i+" td:nth-child(3)").text();//发货单号
			var productId=$("#tr"+i+" td:nth-child(20)").text();//产品编号 有个隐藏列
			var makeInvoice1=$("#tr"+i+" td:nth-child(14)").text();//已经开票
			var returnGoodsCount=$("#tr"+i+" td:nth-child(23)").text();//退货数
			var makeInvoice2=$("#makeInvoiceSum"+i).val();//开票数
			var makeSum=parseInt(makeInvoice2)+ parseInt(makeInvoice1);//开票数 有个隐藏列
			if(makeSum>parseInt($("#tr"+i+" td:nth-child(12)").text())){alert("开票数不能高于发货数-已申请开票数，请重新填写");$("#makeInvoiceSum"+i).select().focus();return false;}
			if(makeSum>parseInt($("#tr"+i+" td:nth-child(12)").text())-returnGoodsCount*1){alert("此发货单有 "+returnGoodsCount+" 个产品正申请退货，请重新填写开票数");$("#makeInvoiceSum"+i).select().focus();return false;}
			var makeMoney=$.trim($("#tr"+i+" td:nth-child(22)").text());//$("#tr"+i+" td:nth-child(16)").text();//开票金额
			if(makeMoney*1=="0"){alert("开票金额不能为0");$("#makeInvoiceSum"+i).select().focus();return;}
			returnVlaue+="{sendGoodsId:"+sendGoodsId+",productId:"+productId+",makeSum:'"+makeInvoice2+"',makeMoney:'"+makeMoney+"'},"
		}
		returnVlaue=returnVlaue.substring(0,returnVlaue.lastIndexOf(","));
		returnVlaue+="]}";
		$("#returnValueToServer").val(returnVlaue); 
		return true;
	}
	$("#del").click(function(){//点击删除
		if($("#table1 tr").length-2==0){alert("请选择产品信息！");return;}
		$("#table1 input:checked[name!='checkAll']").each(function(){
			$(this).parent().parent().remove();//查找<tr>里的数据并删除input[name!='checkAll'][checked]
		});
		var indexSum=$("#table1 tr").length-1;//取和
		var makeInvoiceSum=0;
		$("#table1 tr").each(function(i){
			if(i!=0 && i!=indexSum){//判断是否第一个和最后一个tr
				$("#"+$(this).attr("id")+" td:nth-child(2)").text(i);//重新排序
				makeInvoiceSum=FloatAdd($("#"+$(this).attr("id")+" td:nth-child(22)").text(),makeInvoiceSum);
				var makeInvoiceSumId= $("#"+$(this).attr("id")+" input[type='text']").attr("id");//获取开票数的id
				$(this).attr("id","tr"+i);//改变trid
				$("#"+makeInvoiceSumId).attr("id","makeInvoiceSum"+i);//改变开票数的id
				if(i%2!=0){$("#"+$(this).attr("id")).removeClass("treven");}else{$("#"+$(this).attr("id")).addClass("treven");}
				}
			
		});
				$("#makeInvoiceSum").text(formatMoney(makeInvoiceSum.toFixed(2),2));//重新取和
				$("#money").val(makeInvoiceSum.toFixed(2));
				if(indexSum==1){$("#productTypeName").text(" ");
					$("#productTypeId").val("");
				}

	});
	});
	function makeInvoiceSumVerdict(obj){
		if(obj.value!=""){
			if(!CheckIfNum(obj.value)){alert("只能填写数字");obj.select();obj.focus();} 
			else{
			    var trId=$(obj).parent().parent().attr("id");//tr的id
				var sellPrice=$("#"+trId+" td:nth-child(21)").text();//销售单价
				var makeMoney=FloatMul(sellPrice,obj.value);//开票金额
				$("#"+trId+" td:nth-child(16)").text(formatMoney(makeMoney.toFixed(2),2));//申请开票金额
				$("#"+trId+" td:nth-child(22)").text(makeMoney.toFixed(2));
				var makeInvoiceSum=0;
				var indexSum=$("#table1 tr").length-1;//取和
				$("#table1 tr").each(function(i){
					if(i!=0 && i!=indexSum){//判断是否第一个和最后一个tr
					makeInvoiceSum=FloatAdd($("#"+$(this).attr("id")+" td:nth-child(22)").text(),makeInvoiceSum);
					}
				});
				$("#makeInvoiceSum").text(formatMoney(makeInvoiceSum.toFixed(2),2));//重新取和
				$("#money").val(makeInvoiceSum.toFixed(2));
			}
			}else{alert("请填写开票数");obj.select();obj.focus();}
		
	}
function formatMoney(s, n)
{
   n = n > 0 && n <= 20 ? n : 2;
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
   var l = s.split(".")[0].split("").reverse(),
   r = s.split(".")[1];
   t = "";
   for(i = 0; i < l.length; i ++ )
   {
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
   }
   return t.split("").reverse().join("") + "." + r;
}

function CheckIfNum(String){//判断是否数字
		
			    var Letters = "1234567890";
			     var i;
			     var c;
			     for( i = 0; i < String.length; i ++ )
			     {
			          c = String.charAt( i );
				  if (Letters.indexOf( c ) < 0)
				     return false;
			     }
			     return true;
			}
function CheckAll()//判断全选
  {
     for (var i=0;i<document.forms[0].elements.length;i++)
     {
       document.forms[0].elements[i].checked=document.forms[0].checkAll.checked;
     }
  }
</script>
 	 <c:choose>
 		<c:when test="${param.type=='look'}">
 			<c:set var="title" value="开票查看"></c:set>
 		</c:when>  
 		<c:when test="${param.type=='add'}">
 			<c:set var="title" value="开票申请"></c:set>
 		</c:when>
 		<c:when test="${param.type=='modify'}">
			<c:set var="title" value="开票修改"></c:set>
 		</c:when>    
 	</c:choose>
<c:if test="${param.statusError}">
	<script>alert("执行失败，此状态不能操作");</script>
</c:if>
<html:form action="getSendGoodsParticularList" styleId="invoiceForm" method="post">
<input type="hidden" value="${param.id }" name="invoiceId">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center" style="font-size:12px"><img src="${ctx}/images/main_jt.jpg" />
    	 &nbsp;当前位置： 销售管理 &gt;&gt 开票管理 &gt;&gt; <c:out value="${title}"></c:out>
    </td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao" id="div_title"> &nbsp;&nbsp;开票申请信息</div>
      <c:if test="${param.type=='add'}">
        <a href="#" onclick="javascript:window.open('${ctx}/getCustomerList.do','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=500');"><img src="${ctx}/images/btnKHXZ.gif" /></a>
      </c:if>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" width="20%" height="18px">客户名称</td>
            <td class="td_02" id="tdCustomerId" width="30%">${tf:replaceHTML(customerInfo.customerName)}&nbsp;</td>
            <td class="td_01" width="20%">&nbsp;</td>
            <td class="td_02" width="30%">&nbsp;&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">开户行</td>
            <td class="td_02" id="invoiceBankName">${tf:replaceHTML(customerInfo.invoiceBankName)}&nbsp;</td>
            <td class="td_01">税号</td>
            <td class="td_02" id="taxNumber">${tf:replaceHTML(customerInfo.taxNumber)}&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">地址</td>
            <td class="td_02" id="invoiceBankAddress">${tf:replaceHTML(customerInfo.invoiceBankAddress)}&nbsp;</td>
            <td class="td_01">帐号</td>
            <td class="td_02" id="invoiceBankAccount">${tf:replaceHTML(customerInfo.invoiceBankAccount)}&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">发票类型</td>
            <td class="td_02" id="invoiceTypeName">
            	<c:if test="${customerInfo.invoiceType==0 }">普通</c:if>
            	<c:if test="${customerInfo.invoiceType==1 }">增值税</c:if>
            &nbsp;</td>
            <td class="td_01">电话</td>
            <td class="td_02" id="invoiceBankTel">${tf:replaceHTML(customerInfo.invoiceBankTel)}&nbsp;</td>
          </tr>
          <c:if test="${param.type=='look' || param.type=='sellIdea' || param.type=='opeIdea'}">
	          <tr>
	            <td class="td_01" height="18px">申请人</td>
	            <td class="td_02" >${tf:replaceHTML(customerInfo.userName)}&nbsp;</td>
	            <td class="td_01">申请日期</td>
	            <td class="td_02">${customerInfo.date}</td>
	          </tr>
          </c:if>
      </table>
      <html:hidden property="makeInvoiceAddDto.customerId" styleId="cid" value="${customerInfo.customerId}"/>
      <html:hidden property="makeInvoiceAddDto.customerName" styleId="cname" value="${customerInfo.customerName}"/>
      <html:hidden property="makeInvoiceAddDto.productTypeId" styleId="productTypeId" value="${customerInfo.productTypeId}"/>
      <html:hidden property="makeInvoiceAddDto.invoiceType" styleId="invoiceType" value="${customerInfo.invoiceType}"/>
      <html:hidden property="makeInvoiceAddDto.money" styleId="money" value="${customerInfo.money}"/>
   <br/>
	 <div class="div_tiao" id="div_title"> &nbsp;&nbsp;产品信息 </div>
	 <c:if test="${param.type=='add' || param.type=='modify'}">
	 &nbsp;&nbsp;<a href="#" onclick="openSendGoodsWindow()"><img src="${ctx}/images/btnFHMX.gif" width="99" height="20" /></a>
	 </c:if>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
              	<td class="td_03" width="50%" height="18px">产品分类</td>
                <td class="td_04" id="productTypeName">
                	${customerInfo.productName}&nbsp;
                </td>
                
            </tr>
   	  </table>
   	  <font color="red" style="font-size:14px">
   	  	<c:if test="${param.addInvoiceError }">请选择客户名称!</c:if>
   	  	<c:if test="${param.addInvoiceErrorOfType }">请选择发货明细!</c:if>
   	  	<c:if test="${param.passError }">你无此权限操作！</c:if>
   	  	<c:if test="${param.addError }">添加失败！</c:if>
   	  </font>
      <div style="width:100%; text-align:right;font-size:12px">单位：元</div>
   	  <table id="table1" width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
			<tr>
			<c:if test="${param.type!='look'}">
				<th nowrap="nowrap" width="40" style="border-left:1px solid #c2c2c2;border-right:0px solid #ffffff;">选择</th>
			</c:if>	
				<th nowrap="nowrap" style="border-left:1px solid #c2c2c2;">序号</th>
				<th nowrap="nowrap">发货单号</th>
				<th nowrap="nowrap">产品合同号</th>
				<th nowrap="nowrap">公司合同号</th>
				<th nowrap="nowrap">产品编码</th>
				<th nowrap="nowrap">产品名称</th>
				<th nowrap="nowrap">规格型号</th>
				<th nowrap="nowrap">单位</th>
				<th nowrap="nowrap">销售单价</th>
				<th nowrap="nowrap">发货数</th>
				<th nowrap="nowrap">指定金额</th>
				<th nowrap="nowrap">已申请开票数</th><!-- 后加 已开票票数去掉-->
				<th nowrap="nowrap">申请开票数</th><!-- 后加 -->
				<th nowrap="nowrap">申请开票金额</th><!-- 后加 -->
				<th nowrap="nowrap">开票数</th>
				<th nowrap="nowrap">开票金额</th>
				<th nowrap="nowrap">退货金额</th>
				<c:if test="${param.type=='look'}">
				<th nowrap="nowrap">状态</th>
				</c:if>
			</tr>
			<logic:present name="sendGoodsList">
				<logic:iterate id="sendGoodsList" name="sendGoodsList" indexId="index">
				<tr id="tr<c:out value="${index+1}"></c:out>">
				<c:if test="${param.type!='look'}">
					<td nowrap="nowrap" width="40" style="border-left:1px solid #c2c2c2;border-right:0px solid #ffffff;" ><html:checkbox property="id" name="sendGoodsList" styleId="${sendGoodsList.id }${sendGoodsList.productId }"></html:checkbox></td>
				</c:if>	
					<td nowrap="nowrap" style="border-left:1px solid #c2c2c2;text-align:right;padding-right:5px" height="18px"><c:out value="${index+1}"></c:out></td>
					<td nowrap="nowrap">${sendGoodsList.id }</td>
					<td style="display:none">&nbsp;</td>
					<td nowrap="nowrap">${sendGoodsList.productContractNum }&nbsp;</td>
					<td nowrap="nowrap">${sendGoodsList.companyContractNum }&nbsp;</td>
					<td nowrap="nowrap">${tf:replaceHTML(sendGoodsList.productCode)}&nbsp;</td>
					<td nowrap="nowrap">${tf:replaceHTML(sendGoodsList.productName)}</td>
					<td nowrap="nowrap">${tf:replaceHTML(sendGoodsList.specModel)}</td>
					<td nowrap="nowrap">${tf:replaceHTML(sendGoodsList.unit)}</td>
					<td nowrap="nowrap" style="text-align:right;"><fmt:formatNumber value="${sendGoodsList.sellPrice} " type="number" pattern="#,##0.00" />&nbsp;</td>
					<td nowrap="nowrap" style="text-align:right;padding-right:5px">${sendGoodsList.sendCount }</td>
					<td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${sendGoodsList.money} " type="number" pattern="#,##0.00" />&nbsp;</td><!-- 后加 -->
					<td nowrap="nowrap" style="text-align:right;padding-right:5px">
						${sendGoodsList.appointCount }
					</td><!-- 已申请开票数 -->
					<td nowrap="nowrap" style=" text-align:right;padding-right:5px">
						<c:choose>
							<c:when test="${param.type=='look'}">
								${sendGoodsList.makeInvoiceCount}
							</c:when>
							<c:otherwise>
								<input type="text" name="makeInvoiceSum<c:out value="${index+1}"></c:out>" id="makeInvoiceSum<c:out value="${index+1}"></c:out>" value="${sendGoodsList.makeInvoiceCount}" size="8" onblur="makeInvoiceSumVerdict(this)"/>
							</c:otherwise>
						</c:choose>
					</td>
					<td nowrap="nowrap" style=" text-align:right;padding-right:5px"><fmt:formatNumber value="${sendGoodsList.invoiceMoney}" type="number" pattern="#,##0.00" /></td>
					<td nowrap="nowrap" style="text-align:right;padding-right:5px">${sendGoodsList.invoiceCount}</td>
					<td nowrap="nowrap" style=" text-align:right;" ><fmt:formatNumber value="${sendGoodsList.makeInvoiceMoney}" type="number" pattern="#,##0.00" />&nbsp;</td>
					<td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${sendGoodsList.returnMoney}" type="number" pattern="#,##0.00" />&nbsp;</td>
					<td style="display:none">${sendGoodsList.productId}</td>
					<td style="display:none">${sendGoodsList.sellPrice}</td>
					<td style="display:none">${sendGoodsList.invoiceMoney}</td>
					<td style="display:none">${sendGoodsList.sellBackGoodsCount }</td>
					<c:if test="${param.type=='look'}">
					<td nowrap="nowrap" >
						<c:choose>
							<c:when test="${sendGoodsList.flag==1 }">已退</c:when>
							<c:otherwise>正常</c:otherwise>
						</c:choose>
					</td>
					</c:if>
				</tr>
				</logic:iterate>
			</logic:present>
			<c:choose>
			<c:when test="${param.type=='add' || param.type=='modify'}">
			<tr id="lastTr">
				<td style=" border:1px solid #FFFFFF; background-color:#FFFFFF"><input type="checkbox" name="checkAll" id="checkAll" onclick="CheckAll()"/>				</td>
				<td style=" border:1px solid #FFFFFF; background-color:#FFFFFF">全选</td>
				<td colspan="2" style=" border:1px solid #FFFFFF; background-color:#FFFFFF">
					<a href="#" id="del"><img src="${ctx}/images/btnDelete.gif" width="65" height="20" /></a></td>
				<td colspan="10" nowrap="nowrap" style="text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"> 申请开票金额合计 </td>
				<td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF" id="makeInvoiceSum">
					<logic:present name="customerInfo"><fmt:formatNumber value="${customerInfo.money}" type="number" pattern="#,##0.00" /></logic:present>
					<logic:notPresent name="customerInfo">0</logic:notPresent>
				</td>
				<td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF">元</td>
			</tr>
			</c:when>
			<c:otherwise>
			<tr id="lastTr">
				<td style=" border:1px solid #FFFFFF; background-color:#FFFFFF"></td>
				<td style=" border:1px solid #FFFFFF; background-color:#FFFFFF"></td>
				<td colspan="11" nowrap="nowrap" style="text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"> 申请开票金额合计 </td>
				<td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF" id="makeInvoiceSum">
					<logic:present name="customerInfo"><logic:present name="customerInfo"><fmt:formatNumber value="${customerInfo.money}" type="number" pattern="#,##0.00" /></logic:present></logic:present>
					<logic:notPresent name="customerInfo">0</logic:notPresent>
				</td>
				<td colspan="3" nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF"><span style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">元</span></td>
			</tr>
			</c:otherwise>
			</c:choose>
		</table>
			<div class="div_tiao" id="div_title"> &nbsp;&nbsp;特殊说明</div>
			<input type="hidden" id="returnValueToServer" name="returnValueToServer" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="20%">特殊说明</td>
					<td class="td_04" style="padding:5px 100px 5px 5px">
					<c:choose>
						<c:when test="${param.type=='look'}">
							${tf:replaceBr(customerInfo.text)}&nbsp;
						</c:when>
						<c:otherwise>
							<textarea rows="3" cols="100" name="text" id="text">${customerInfo.text}</textarea>
						</c:otherwise>
					</c:choose>					
					</td>
				</tr>
			</table>
			
			<c:if test="${param.type=='look'}"> 
			<div class="div_tiao"> &nbsp;&nbsp;补充说明</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="20%">开票专员补充说明</td>
					<td class="td_04" >
					${tf:replaceBr(customerInfo.text_k3)} 
					</td>
				</tr>
			</table>
			</c:if>
						
			
			
			
			
			
			
			
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    	<c:choose>
    		<c:when test="${param.type=='look'}">
    		</c:when>  
    		<c:when test="${param.type=='add'}">
    			<a href="#" id="invoiceSave"><img src="${ctx}/images/btnSave.gif" width="65" height="20" /></a>
   				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			<a href="#" id="invoiceSubmit"><img src="${ctx}/images/btnSubmit.gif" width="65" height="20" /></a>
    		</c:when>
    		<c:when test="${param.type=='modify'}">
    			<a href="#" id="invoiceSaveOfModify"><img src="${ctx}/images/btnSave.gif" width="65" height="20" /></a>
   				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			<a href="#" id="invoiceSubmitOfModify"><img src="${ctx}/images/btnSubmit.gif" width="65" height="20" /></a>
    		</c:when>    
    			
    	</c:choose>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
    		<html:link action="getInvoiceList.do"><img src="${ctx}/images/btnBack.gif" /></html:link>
    </td>
    <td></td>
  </tr>
</table>

</html:form>
</body>
</html>
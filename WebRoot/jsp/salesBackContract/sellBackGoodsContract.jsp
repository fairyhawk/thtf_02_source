<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="tf" uri="localhost" %>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新建销售退货合同</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/util.js"></script>
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
	function getOpenerVal(val){//收货地址选择
		//if(val.stockRoomId!=$("#storeRoomId").val()){//如果改变
		$("#name").text(val.name);
		$("#address").text(val.address);
		$("#Linkman").text(val.Linkman);
		$("#Postcode").text(val.Postcode);
		$("#Tel").text(val.Tel);
		$("#Mobile").text(val.Mobile);
		$("#storeRoomId").val(val.stockRoomId);
		$("#stockroomAddressId").val(val.id);

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
function getOpenerValOfGoodsInfo(html,id){
		var trLength=$("#table1 tr").length-1;//获得tr的个数（序号）
		if($("input[id="+id+"]").attr("id")==null){//判断是否有重复id标示
			$("#lastTr").before("<tr id=tr"+trLength+">"+html+"</tr>");//插入到最后一行前
			$("#first").after("<td>"+trLength+"</td>");//加入序号列 first 为第一列的id;序号为第二列
			$("#first").attr("id","discard");//取消第一列id 变成废弃的
			$("#tdReturnCount").after("<td><input style='text-align:right;padding-right:5px;' type='text' name='backCount"+trLength+"' id='backCount"+trLength+"' value='0' size=8 onblur='makeInvoiceSumVerdict(this)' onclick='range(this)'/>&nbsp;</td>");//添加数框
			$("#tdReturnCount").attr("id","discard");//变成废弃的
			$("#tdBackContractGoodsMoney").css("display","");//退货合同金额
			$("#tdBackContractGoodsMoney").text("0");//退货合同金额
			$("#tdBackContractGoodsMoney").attr("id","discard");//变成废弃的
			var makeInvoiceSum=parseFloat($("#makeInvoiceSum").text())+parseFloat($("#tdInvoiceMoney").text());//求和
			$("#tdInvoiceMoney").attr("id","discard");//变成废弃的
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
	function showFile(){//文件名显示
		$("#fileName").val($("#file").val());
		$("#fileName").attr("class","");
		$("#fileNameError").text("");
	}
	$(document).ready(function(){//改变下拉框后
	$("#storeRoomId").change(function(){
		$("#name").text(" ");
		$("#address").text(" ");
		$("#Linkman").text(" ");
		$("#Postcode").text(" ");
		$("#Tel").text(" ");
		$("#Mobile").text(" ");
		$("#id").val(" ");
		$("#stockroomAddressId").val("");
	});
	$("#submit").click(function(){//提交
		if(!validateOfForm($("#submit").attr("id"))){
			return;
		}
		getJsonOfProductInfo();
		document.getElementById("saleBackForm").action=jsCtx+"/addSalesBackContractOfTransact.do?type=submit";
		document.forms[0].submit();
	});
	$("#save").click(function(){//保存
		if(!validateOfForm($("#save").attr("id"))){
			return;
		}
		getJsonOfProductInfo();
		document.getElementById("saleBackForm").action=jsCtx+"/addSalesBackContractOfTransact.do?type=save";
		 $("#saleBackForm").submit();
	});
	$("#look").click(function(){//预览
		if(!validateOfForm($("#look").attr("id"))){
			return;
		}
		getJsonOfProductInfo();
		document.getElementById("saleBackForm").action=jsCtx+"/addSalesBackContractOfTransact.do?type=save&additive=look";
		document.forms[0].submit();
	});
	$("#modifySave").click(function(){//修改保存
		if(!validateOfForm($("#modifySave").attr("id"))){
			return;
		}
		getJsonOfProductInfo();
		document.getElementById("saleBackForm").action=jsCtx+"/modifySalesBackContractOfTransact.do?type=save";
		 $("#saleBackForm").submit();
	});
		$("#modifySubmit").click(function(){//修改提交
		if(!validateOfForm($("#modifySubmit").attr("id"))){
			return;
		}
		getJsonOfProductInfo();
		document.getElementById("saleBackForm").action=jsCtx+"/modifySalesBackContractOfTransact.do?type=submit";
		 $("#saleBackForm").submit();
	});
		$("#modifyLook").click(function(){//修改预览
		if(!validateOfForm($("#modifyLook").attr("id"))){
			return;
		}
		getJsonOfProductInfo();
		document.getElementById("saleBackForm").action=jsCtx+"/modifySalesBackContractOfTransact.do?type=save&additive=look";
		window.open('${ctx}/salesBackContractPreview.do?backContractId='+$("#salesBackContractId").val()+'','newwindow', "toolbar=no,scrollbars=yes,resizable=yes,top=0,left=170, width=750,height=680");
		 $("#saleBackForm").submit();
	});
	$("#addFile").click(function(){//添加文件
		$("#file").click();
	});
	$("#add").click(function(){
		window.open(jsCtx+"/getSalesBackContractOfGoodsInfo.do?id="+$("#sellContractId").val()+"","newwindow", "toolbar=no,scrollbars=yes,resizable=no,top=150,left=250, width=999,height=550");
	});
	$("#templateType1").click(function(){//选择非模板
	$("#tdChangeFileLabel").css("display","");
	$("#tdChangeFile").css("display","");
	});
	$("#templateType0").click(function(){//选择模板
		//$("#file").after($("#file").clone().val("")); 
		//$("#file").remove();     
		$("#tdChangeFileLabel").css("display","none");
		$("#tdChangeFile").css("display","none");
		
		
	});
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
	$("#del").click(function(){//删除
		if($("#table1 tr").length-2==0){alert("请选择产品信息！");return;}
		$("#table1 input:checked[name!='checkAll']").each(function(){
			$(this).parent().parent().remove();//查找<tr>里的数据并删除input[name!='checkAll'][checked]
		});
		var indexSum=$("#table1 tr").length-1;//取和
		var makeInvoiceSum=0;
		$("#table1 tr").each(function(i){
			if(i!=0 && i!=indexSum){//判断是否第一个和最后一个tr
				$("#"+$(this).attr("id")+" td:nth-child(2)").text(i);//重新排序
				
				makeInvoiceSum=FloatAdd($("#"+$(this).attr("id")+" td:nth-child(19)").text(),makeInvoiceSum);
				var makeInvoiceSumId= $("#"+$(this).attr("id")+" input[type='text']").attr("id");//获取退票合同数的id
				
				$(this).attr("id","tr"+i);//改变trid
				$("#"+makeInvoiceSumId).attr("id","backCount"+i);//改变退票合同数的id
				if(i%2!=0){$("#"+$(this).attr("id")).removeClass("treven");}else{$("#"+$(this).attr("id")).addClass("treven");}
				}
		});
				$("#makeInvoiceSum").text(formatMoney(makeInvoiceSum.toFixed(2),2)+"元");//重新取和
				$("#money").val(makeInvoiceSum.toFixed(2))

		
	});
	$("#changeReceiveAddress").click(function(){//收货地址选择
		if($("#storeRoomId").val()==""){
			$("#storeRoomId").focus().attr("class","invalid");
			$("#storeRoomIdError").text("请选择库房名称！");
			return;
			}
			javascript:window.open(jsCtx+'/getStoreRoomAddress.do?id='+$("#storeRoomId").val()+'&name='+encodeURI($("#storeRoomId option:selected").text())+'','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=340, width=900,height=450');	
	});
	$("#storeRoomId").blur(function(){//收货地址选择
			if($("#storeRoomId").val()==""){
			$("#storeRoomId").attr("class","invalid");
			$("#storeRoomIdError").text("请选择库房名称！");
			}else{$("#storeRoomIdError").text("");$("#storeRoomId").attr("class","");}
	});

	});
function makeInvoiceSumVerdict(obj){
		if(obj.value!=""){
			if(!CheckIfNum(obj.value)){alert("只能填写数字");obj.select();} 
			else{
			    var trId=$(obj).parent().parent().attr("id");//tr的id
				var sellPrice=$("#"+trId+" td:nth-child(18)").text();//销售单价
				if($.trim(sellPrice)==""){alert("销售单价无数据！");return;}
				var sellCount=$("#"+trId+" td:nth-child(7)").text();//销售数
				var alreadyReturnCount=$("#"+trId+" td:nth-child(14)").text();//已退货合同数
				//if(obj.value>(parseInt(sellCount)-parseInt(alreadyReturnCount))){alert("退货合同数不能高于销售数-已退货合同数！");obj.select();obj.focus();return;}
				var makeMoney=FloatMul(sellPrice,obj.value);//退货合同金额
				$("#"+trId+" td:nth-child(17)").text(formatMoney(makeMoney.toFixed(2),2));//退货合同金额
				$("#"+trId+" td:nth-child(19)").text(makeMoney.toFixed(2));//退货合同金额
				var makeInvoiceSum=0;
				var indexSum=$("#table1 tr").length-1;//取和
				$("#table1 tr").each(function(i){
					if(i!=0 && i!=indexSum){//判断是否第一个和最后一个tr
					makeInvoiceSum=FloatAdd(makeInvoiceSum,$("#"+$(this).attr("id")+" td:nth-child(19)").text());
					}
				});
				$("#makeInvoiceSum").text(formatMoney(makeInvoiceSum.toFixed(2),2)+"元");//重新取和
				$("#money").val(makeInvoiceSum.toFixed(2));
			}
			}else{alert("请填写退货数");obj.focus();}
		
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
function CheckAll()//判断全选
  {
     for (var i=0;i<document.forms[0].elements.length;i++)
     {
       document.forms[0].elements[i].checked=document.forms[0].checkAll.checked;
     }
  }
  function getNowFormatDate()
{
   var day = new Date();

   var Year = 0;
   var Month = 0;
   var Day = 0;
   var CurrentDate = "";
   //初始化时间   
   Year       = day.getFullYear();
   Month      = day.getMonth()+1;
   Day        = day.getDate();
  
   CurrentDate += Year + "-";
  
   if (Month >= 10 )
   {
    CurrentDate += Month + "-";
   }
   else
   {
    CurrentDate += "0" + Month + "-";
   }
   if (Day >= 10 )
   {
    CurrentDate += Day ;
   }
   else
   {
    CurrentDate += "0" + Day ;
   }
   return CurrentDate;
} 
  function validateOfForm(id){//提交的 <a> 的 id
  var success=true;
  		if($("#backDate").val()==""){//时间
			$("#backDate").attr("class","invalid");
			$("#dateError").text("请选择预计退货时间！");
			$("#"+id).attr("href","#divreturnContractInfo");
			return false;
		}else if(new Date($("#backDate").val().replace("-",",")).getTime()<new Date(getNowFormatDate().replace("-",",")).getTime()){
			$("#backDate").attr("class","invalid");
			$("#dateError").text("退货时间必须是今日以后的时间！");
			$("#"+id).attr("href","#divreturnContractInfo");
		return false;}else{
			$("#backDate").attr("class","");
			$("#dateError").text("");
		}
		if($("#templateType1").attr("checked")){//非模板
			if($("#fileName").val()==""){
				$("#file").focus().attr("class","invalid");
				$("#fileNameError").text("请选择文件！");
				$("#"+id).attr("href","#divreturnContractInfo");
				return false;}
			var uploadFileName=$("#fileName").val();
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
				$("#"+id).attr("href","#divreturnContractInfo");
				return false;
				
				}	
		}
		if($("#table1 tr").length==2){alert("请选择产品信息！");return false;}//产品信息
		$("#table1 tr").each(function(i){//产品信息 退货合同数
			if(i!=0 && i!=$("#table1 tr").length-1){//判断是否第一个和最后一个tr		
				var makeInvoiceSumId= $("#"+$(this).attr("id")+" input[type='text']").attr("id");//获取退票合同数的id
				var sellCount=$("#"+$(this).attr("id")+" td:nth-child(7)").text();//销售数
				var alreadyReturnCount=$("#"+$(this).attr("id")+" td:nth-child(14)").text();//已退货合同数
				if($("#"+makeInvoiceSumId).val()*1>(parseInt(sellCount)-parseInt(alreadyReturnCount))){alert("退货合同数不能高于销售数-已退货合同数！");$("#"+makeInvoiceSumId).select();$("#"+makeInvoiceSumId).focus();$("#"+id).attr("href","#divProductInfo");success=false;return false;}
				if($("#"+makeInvoiceSumId).val()==0){alert("退货合同数不能为0！");$("#"+makeInvoiceSumId).focus().select();$("#"+id).attr("href","#divProductInfo");success=false;return false;}
				}
		});
		if($("input:checked[id='backWay']").length==0){//退货退款信息 
			alert("请选择退货退款方式！");
			$("#"+id).attr("href","#divreturnInfo");
			return false;
		}
		if($("#storeRoomId").val()==""){//库房名称
			$("#storeRoomId").focus().attr("class","invalid");
			$("#storeRoomIdError").text("请选择库房名称！");
			$("#"+id).attr("href","#divreceiveInfo");
			return false;
			}
		if($("#stockroomAddressId").val()==""){//收货地址
			alert("请选择收货地址！");
			$("#"+id).attr("href","#divreceiveInfo");
			return false;
		}
		if($.trim($("#text").text())==""){//退货原因说明
			alert("请写退货原因说明！");
			$("#text").focus()
			$("#"+id).attr("href","#divBecauseText");
			return false;
		}
		if(checkTextAreaLen(200) == false){
			return false;
		}
		return success;
  }
 function getJsonOfProductInfo(){
 var sumId=$("#table1 tr").length-2;
 	var returnVlaue="{resultSum:"+sumId+",rows:[";
 	$("#table1 tr").each(function(i){//产品信息 退货合同数
		if(i!=0 && i!=$("#table1 tr").length-1){//判断是否第一个和最后一个tr		
			var makeInvoiceSumId= $("#"+$(this).attr("id")+" input[type='text']").attr("id");//获取退票合同数的id
			var backContractCount=$.trim($("#"+makeInvoiceSumId).val());
			var productId=$("#"+$(this).attr("id")+" td:nth-child(1)").children().attr("id");
			var sellpriceId=$("#"+$(this).attr("id")+" input[type='hidden']").attr("id");
			var sellprice=$.trim($("#"+sellpriceId).val());
			returnVlaue+="{productId:"+productId+",backContractCount:'"+backContractCount+"',sellprice:'"+sellprice+"'},"
			}
	});
	returnVlaue=returnVlaue.substring(0,returnVlaue.lastIndexOf(","));
	returnVlaue+="]}";
	$("#returnValueToServer").val(returnVlaue); 
  }
  
  //选中文本框内所有
	function range(obj){
	  obj.select();
	 }
</script>
</head>
<c:if test="${param.passError}">
  <script>alert("你无此权限！");</script>
</c:if>
<c:if test="${param.templateTypeError}">
  <script>alert("请选择模板！");</script>
</c:if>
<c:if test="${param.fileError}">
  <script>alert("请选择文件！");</script>
</c:if>
<c:if test="${param.fileMaxError}">
  <script>alert("${param.msg}");</script>
</c:if>
<c:if test="${param.fileUplaodError}">
  <script>alert("上传失败！格式只能为txt、pdf、xml、rtf、doc、docx、xls、xlsx类型");</script>
</c:if>

<c:if test="${param.dateError}">
  <script>alert("请选择时间！");</script>
</c:if>
<c:if test="${param.productError}">
  <script>alert("请选择产品信息！");</script>
</c:if>
<c:if test="${param.backWayError}">
  <script>alert("请选择退货退款！");</script>
</c:if>
<c:if test="${param.stockroomAddressError}">
  <script>alert("请选择信息！");</script>
</c:if>
<c:if test="${param.textError}">
  <script>alert("请填写原因！");</script>
</c:if>
<c:if test="${param.addError}">
  <script>alert("添加失败！");</script>
</c:if>
<c:if test="${param.noUserIdError}">
  <script>alert("添加失败，无负责此区域或产品的销售总监！");</script>
</c:if>
<c:if test="${param.statusError}">
	<script>alert("执行失败，此状态不能操作");</script>
</c:if>
<c:if test="${param.preview}">
  <script>window.open('${ctx}/salesBackContractPreview.do?backContractId=${param.id}','newwindow', "toolbar=no,scrollbars=yes,resizable=yes,top=0,left=170, width=750,height=680");</script>
</c:if>
<body>
<html:form action="addSalesBackContractOfTransact" method="post" styleId="saleBackForm" enctype="multipart/form-data">
  <c:if test="${param.type=='modify'}">
	<c:set var="title" value="销售退货合同修改"></c:set>
  </c:if>
  <c:if test="${param.type=='add'}">
	<c:set var="title" value="新建销售退货合同"></c:set>
  </c:if>
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td class="ye_header_left">&nbsp;</td>
      <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt; 销售退货合同管理 &gt;&gt; <c:out value="${title}"></c:out></td>
      <td class="ye_header_right">&nbsp;</td>
    </tr>
    <tr>
      <td >&nbsp;</td>
      <td><br />
        <div class="div_tiao"> &nbsp;&nbsp;销售合同信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" height="18px">产品合同号</td>
          <td class="td_02">${salesBackContractOfShowDto.productContractCode}&nbsp;</td>
          <td class="td_01">公司合同号</td>
          <td class="td_02">${salesBackContractOfShowDto.companyContractCode}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px" width="20%">签订日期</td>
          <td class="td_02" width="30%">${salesBackContractOfShowDto.date}&nbsp;</td>
          <td class="td_01" width="20%">产品分类名称</td>
          <td class="td_02" width="30%">${salesBackContractOfShowDto.productTypeName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">合同金额</td>
          <td class="td_02"><fmt:formatNumber value="${salesBackContractOfShowDto.money}" type="number" pattern="#,##0.00" />&nbsp;元</td>
          <td class="td_01">预收金额</td>
          <td class="td_02"><fmt:formatNumber value="${salesBackContractOfShowDto.preMoney}" type="number" pattern="#,##0.00" />&nbsp;元</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;客户信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" height="18px" width="20%">客户名称</td>
          <td class="td_02" width="30%">${salesBackContractOfShowDto.customerName}&nbsp;</td>
          <td class="td_01" width="20%">联系人</td>
          <td class="td_02" width="30%">${salesBackContractOfShowDto.customerLinkmanName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">省份</td>
          <td class="td_02">${salesBackContractOfShowDto.customerProvince}&nbsp;</td>
          <td class="td_01">电话</td>
          <td class="td_02">${salesBackContractOfShowDto.customerLinkmanTel}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">城市</td>
          <td class="td_02">${salesBackContractOfShowDto.customerCity}&nbsp;</td>
          <td class="td_01">传真</td>
          <td class="td_02">${salesBackContractOfShowDto.customerLinkmanFax}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">开户行</td>
          <td class="td_02">${salesBackContractOfShowDto.customerInvoiceBankName}&nbsp;</td>
          <td class="td_01">帐号</td>
          <td class="td_02">${salesBackContractOfShowDto.customerInvoiceBankAccount}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">税号</td>
          <td class="td_02">${salesBackContractOfShowDto.customerTaxNumber}&nbsp;</td>
          <td class="td_01">发票类型</td>
          <td class="td_02"><logic:equal value="0" property="customerInvoiceType"
										name="salesBackContractOfShowDto">普通</logic:equal>
			 <logic:equal value="1" property="customerInvoiceType"
										name="salesBackContractOfShowDto">增值税</logic:equal>
          </td>
        </tr>
      </table>
      <br />
      <div class="div_tiao" id="divreturnContractInfo"> &nbsp;&nbsp;退货合同信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;模版类型</td>
          <td class="td_02">
          <html:radio property="saleBackContractOfAddDto.templateType" value="0" styleId="templateType0">模版</html:radio>&nbsp;&nbsp;
          <html:radio property="saleBackContractOfAddDto.templateType" value="1" styleId="templateType1">非模版</html:radio>&nbsp;&nbsp;
           </td>

          	<c:choose>
          		<c:when test="${requestScope.saleBackContractOfAddForm.saleBackContractOfAddDto.templateType=='1'}">
          			<td class="td_01" ><span id="tdChangeFileLabel" style=""><span style="color:#FF0000">*</span>&nbsp;选择文件</span>&nbsp;</td>
         				 <td class="td_02">
          			<span id="tdChangeFile" style="">
		          	<INPUT type="text" value="${salesBackContractOfShowDto.file}" name="fileName" id="fileName" readonly="readonly" style="display:none"/><input style="display:none" type="button" value="浏览..." id="addFile"/>
		          	
		          	<html:file property="file" onchange="showFile()"  styleId="file" value="${salesBackContractOfShowDto.file}"></html:file><SPAN id="fileNameError" style="font-size:12px;color:#F00">
		          	</SPAN></span>
		          	&nbsp;
          				</td>
          		</c:when>
     		    <c:when test="${requestScope.saleBackContractOfAddForm.saleBackContractOfAddDto.templateType=='0'}">
          			<td class="td_01" ><span id="tdChangeFileLabel" style="display:none"><span style="color:#FF0000">*</span>&nbsp;选择文件</span>&nbsp;</td>
          				<td class="td_02">
          			<span id="tdChangeFile" style="display:none">
		          	<INPUT type="text" value="${salesBackContractOfShowDto.file}" name="fileName" id="fileName" readonly="readonly" style="display:none"/><input style="display:none" type="button" value="浏览..." id="addFile"/>
		          	
		          	<html:file property="file" onchange="showFile()"  styleId="file" value="${salesBackContractOfShowDto.file}"></html:file><SPAN id="fileNameError" style="font-size:12px;color:#F00">
		          	</SPAN></span>&nbsp;
         				 </td>
          		</c:when>   
          	</c:choose>
          	
        </tr>
        <tr>
          <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;预计退货时间</td>
          <td class="td_02"><html:text property="saleBackContractOfAddDto.backDate" styleId="backDate" style="width: 120px;" onfocus="calendarMinTomorrow()" readonly="readonly" value="${salesBackContractOfShowDto.backDate}" ></html:text><span id="dateError" style="font-size:12px;color:#F00"></span></td>
          <td class="td_01">签订地点</td>
          <td class="td_02">${salesBackContractOfShowDto.place}&nbsp;</td>
        </tr>
      </table>
      <br/>
      <div class="div_tiao" id="divProductInfo"> &nbsp;&nbsp;产品信息 </div>
      <div style="width:100%; text-align:right">&nbsp;单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table1" name="table1" style="border-left:1px solid #FFFFFF;">
        <tr>
          <th nowrap="nowrap" width="35" style="border-left:1px solid #c2c2c2;">选择</th>
          <th nowrap="nowrap" width="35" >序号</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap">销售数</th>
          <th nowrap="nowrap">销售单价</th>
          <th nowrap="nowrap">销售金额</th>
          <th nowrap="nowrap">发货金额</th>
          <th nowrap="nowrap">备货金额</th>
          <th nowrap="nowrap">指定金额</th>
          <th nowrap="nowrap">开票金额</th>
          <th nowrap="nowrap">已退货合同数</th>
          <th nowrap="nowrap">退货数</th>
          <th nowrap="nowrap">退货合同数</th>
          <th nowrap="nowrap">退货合同金额</th>
        </tr>
        <logic:present name="goodsInfo">
        <logic:iterate id="goodsInfo" name="goodsInfo" indexId="index">
        <tr id="tr<c:out value="${index+1}"></c:out>">
       	  <td nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18px">
       	  	<html:checkbox property="productId" name="goodsInfo" styleId="${goodsInfo.productId }"></html:checkbox>
       	  </td>
          <td nowrap="nowrap"  height="18px"><c:out value="${index+1}"></c:out></td>
          <td nowrap="nowrap" >${goodsInfo.productCode }&nbsp;</td>
          <td nowrap="nowrap" >${goodsInfo.productName }&nbsp;</td>
          <td nowrap="nowrap" >${goodsInfo.specModel }&nbsp;</td>
          <td nowrap="nowrap" >${goodsInfo.unit }&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${goodsInfo.sellCount }&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><input type="hidden" name="sellPrice" id="sellPrice${goodsInfo.productId }" value="${goodsInfo.sellPrice }"/><fmt:formatNumber value="${goodsInfo.sellPrice }" type="number" pattern="#,##0.00" />&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${goodsInfo.sellMoney }" type="number" pattern="#,##0.00" />&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${goodsInfo.sendGoodsMoney }" type="number" pattern="#,##0.00" />&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${goodsInfo.preGoodsMoney }" type="number" pattern="#,##0.00" />&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${goodsInfo.appointMoney }" type="number" pattern="#,##0.00" />&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${goodsInfo.makeInvoiceMoney }" type="number" pattern="#,##0.00" />&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${goodsInfo.alreadyReturnCount }&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${goodsInfo.returnCount }&nbsp;</td>
          <td nowrap="nowrap" >
          				<c:choose>
							<c:when test="${param.type=='look'}">
								${goodsInfo.backContractGoodsCount}
							</c:when>
							<c:when test="${param.type=='modify' || param.type=='add'}">
								<input style='text-align:right;padding-right:5px;' type="text" name="backCount<c:out value="${index+1}"></c:out>" id="backCount<c:out value="${index+1}"></c:out>" value="${goodsInfo.backContractGoodsCount}" size="8" onblur="makeInvoiceSumVerdict(this)" onclick="range(this)"/>
							</c:when>
							<c:when test="${param.type=='add'}">
								<input style='text-align:right;padding-right:5px;' type="text" name="backCount<c:out value="${index+1}"></c:out>" id="backCount<c:out value="${index+1}"></c:out>" value="0" size="8" onblur="makeInvoiceSumVerdict(this)" onclick="range(this)"/>
							</c:when>
							<c:otherwise>
								
							</c:otherwise>
						</c:choose>
          </td>
          <td nowrap="nowrap" style=" text-align:right; padding-right:12px;"><fmt:formatNumber value="${goodsInfo.backContractGoodsMoney }" type="number" pattern="#,##0.00" /></td>
          <td nowrap="nowrap" style="display:none">${goodsInfo.sellPrice }</td>
          <td nowrap="nowrap" style="display:none">${goodsInfo.backContractGoodsMoney }</td>
        </tr>
        </logic:iterate>
        </logic:present>
			<c:choose>
			<c:when test="${param.type=='add' || param.type=='modify'}">
			<tr id="lastTr">
				<td style=" border:1px solid #FFFFFF; background-color:#FFFFFF"><input type="checkbox" name="checkAll" id="checkAll" onclick="CheckAll()"/>				</td>
				<td style=" border:1px solid #FFFFFF; background-color:#FFFFFF">全选</td>
				<td colspan="2" style=" border:1px solid #FFFFFF; background-color:#FFFFFF">
					<a href="#table1" id="del"><img src="${ctx}/images/btnDelete.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
					
					<a href="#table1" id="add"><img src="${ctx}/images/btnAdd.gif" width="65" height="20" /></a></td>
				<td colspan="12" nowrap="nowrap" style="text-align:right; border:1px solid #FFFFFF; background-color:#FFFFFF"> 退货合同金额合计 </td>
				<td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; background-color:#FFFFFF" id="makeInvoiceSum">
					<logic:present name="salesBackContractOfShowDto"><fmt:formatNumber value="${salesBackContractOfShowDto.backMoney}" type="number" pattern="#,##0.00" /></logic:present>
					<logic:notPresent name="salesBackContractOfShowDto">0</logic:notPresent>元</td>
			</tr>
			</c:when>
			<c:otherwise>
			<tr id="lastTr">
				<td style=" border:1px solid #FFFFFF; background-color:#FFFFFF"></td>
				<td style=" border:1px solid #FFFFFF; background-color:#FFFFFF"></td>
				<td colspan="2" style=" border:1px solid #FFFFFF; background-color:#FFFFFF">
					</td>
				<td colspan="10" nowrap="nowrap" style="text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"> 退货金额合计 </td>
				<td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF" id="makeInvoiceSum">
					<logic:present name="salesBackContractOfShowDto">${salesBackContractOfShowDto.backMoney}</logic:present>
					<logic:notPresent name="salesBackContractOfShowDto">0</logic:notPresent>
				</td>
				<td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF"><span style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">元</span></td>
			</tr>
			</c:otherwise>
			</c:choose>
      </table>
      <br />
      <div class="div_tiao" id="divreturnInfo"> &nbsp;&nbsp;退货退款信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td rowspan="3" class="td_03"  width="20%" ><span style="color:#FF0000">*</span>&nbsp;退货退款方式选择</td>
          <td class="td_04">&nbsp;&nbsp;&nbsp;已供货已付款&nbsp;
          <html:radio property="saleBackContractOfAddDto.backWay" value="1" styleId="backWay">先退货后退款</html:radio>
          <html:radio property="saleBackContractOfAddDto.backWay" value="2" styleId="backWay">先退款后退货</html:radio>
          </td>
        </tr>
        <tr>
          <td class="td_04">
          	<html:radio property="saleBackContractOfAddDto.backWay" value="3" styleId="backWay">已供货未付款</html:radio>
            </td>
        </tr>
        <tr>
          <td class="td_04">
          	<html:radio property="saleBackContractOfAddDto.backWay" value="4" styleId="backWay">未供货</html:radio>
            </td>
        </tr>
      </table>
      <br/>
      <div class="div_tiao" id="divreceiveInfo"> &nbsp;&nbsp;收货信息 </div>
        <a href="#tdname" id="changeReceiveAddress"><img src="${ctx}/images/btnSHDZ.gif" /></a>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" height="18px" name="tdname">库房名称</td>
          <td colspan="3" class="td_02" >
				<html:select property="saleBackContractOfAddDto.stockroomId" value="${salesBackContractOfShowDto.stockRoomId}" style="width:264px" styleId="storeRoomId">
					<html:option value="">--请选择--</html:option>
					<html:options collection="stockRoomList" property="id"  labelProperty="name" />
				</html:select><span id="storeRoomIdError" style="font-size:12px;color:#F00"></span>
          </td>
        </tr>
        <tr>
          <td class="td_01" height="18px">货物接收单位名称</td>
          <td colspan="3" class="td_02" id="name">${salesBackContractOfShowDto.goodsName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">收货地址</td>
          <td colspan="3" class="td_02" id="address">${salesBackContractOfShowDto.goodsAddress}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">联系人</td>
          <td class="td_02" id="Linkman">${salesBackContractOfShowDto.linkman}&nbsp;</td>
          <td class="td_01">邮编</td>
          <td class="td_02" id="Postcode">${salesBackContractOfShowDto.postcode}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px" width="20%">电话</td>
          <td class="td_02" width="30%" id="Tel">${salesBackContractOfShowDto.tel}&nbsp;</td>
          <td class="td_01" width="20%">手机</td>
          <td class="td_02" width="30%" id="Mobile">${salesBackContractOfShowDto.mobile}&nbsp;</td>
        </tr>
      </table>
      <br/>
      <div class="div_tiao" id="divBecauseText"> &nbsp;&nbsp;退货原因说明</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%"><span style="color:#FF0000">*</span>&nbsp;退货原因说明</td>
          <td class="td_04" ><textarea name="saleBackContractOfAddDto.text" id="text" cols="100" rows="4">${salesBackContractOfShowDto.text}</textarea>
          </td>
        </tr>
      </table></td>
    <td >&nbsp;</td>
  </tr>
  <c:if test="${param.type=='modify'}">
  <tr>
  	<td></td>
  	<td>
  		      <div class="div_tiao" style="font-size:12px" id="div_title"> &nbsp;&nbsp;评审意见 </div>
    <div id="div_idea" style="padding-top:0px">
      <table border="0" cellpadding="0" cellspacing="0" align="center"	width="460">
        <c:if test="${salesBackContractOfShowDto.templateType==1}">
          <tr>
            <td height="20px">法务专员评审意见：&nbsp;</td>
            <td height="20px"><c:if test="${salesBackContractOfShowDto.legalIdea==0}">
                <c:set value="checked" var="legalUnpass" scope="page"></c:set>
              </c:if>
              <c:if test="${salesBackContractOfShowDto.legalIdea==1}">
                <c:set value="checked" var="legalPass" scope="page"></c:set>
              </c:if>
              <c:choose>
                <c:when test="${requestScope.type=='legal' && salesBackContractOfShowDto.status==2  && !param.preview}">
                  <html:checkbox property="saleBackContractOfAddDto.legalIdea" value="1" styleId="legalIdea1">符合</html:checkbox>
                  &nbsp;&nbsp;
                  <html:checkbox property="saleBackContractOfAddDto.legalIdea" value="0" styleId="legalIdea0">不符合</html:checkbox>
                </c:when>
                <c:otherwise> <input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" 
                  <c:out value="${legalPass}"></c:out>
                  />符合
                  &nbsp;&nbsp; <input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" 
                  <c:out value="${legalUnpass}"></c:out>
                  />不符合 </c:otherwise>
              </c:choose>
            </td>
          </tr>
          <tr>
            <td colspan="2"><c:choose>
                <c:when test="${requestScope.type=='legal' && salesBackContractOfShowDto.status==2  && !param.preview}">
                  <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="60px" valign="top" style="padding:5px 0 0 0; text-align:left" nowrap>补充说明：</td>
                      <td style="padding:5px 0 5px 0;"><html:textarea styleId="legalText" property="saleBackContractOfAddDto.legalText" rows="3" cols="60"></html:textarea>
                      </td>
                    </tr>
                  </table>
                </c:when>
                <c:otherwise>
                  <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="60px" valign="top" style="padding:5px 0 0px 0;" nowrap>补充说明：</td>
                      <td style="padding:5px 0 5px 0;">${tf:replaceBr(salesBackContractOfShowDto.legalText)}</td>
                    </tr>
                  </table>
                </c:otherwise>
              </c:choose>
            </td>
          </tr>
          <tr>
            <td height="20px">签字：${salesBackContractOfShowDto.legalName }</td>
            <td>日期：${salesBackContractOfShowDto.legalDate }</td>
          </tr>
        <tr>
          <td height="20pxs" colspan="2">&nbsp;</td>
        </tr>
        </c:if>
        <tr >
							<td height="20px" colspan="2">
								区域总监评审意见：
							</td>
						</tr>
						<tr >
							<td>
								1.退货数量是否符合
							</td>
							<td height="20px" width="150">
								<input type="checkbox"   id="radio" value="1" disabled="disabled" 
									<c:if test='${salesBackContractOfShowDto.areaMajIdea1 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"   id="radio" value="0" disabled="disabled" 
									<c:if test='${salesBackContractOfShowDto.areaMajIdea1 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr >
							<td>
								2.退货退款方式是否符合
							</td>
							<td height="20px">
								<input type="checkbox"   id="radio2" value="1" disabled="disabled" 
									<c:if test='${salesBackContractOfShowDto.areaMajIdea2 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"   id="radio2" value="0" disabled="disabled" 
									<c:if test='${salesBackContractOfShowDto.areaMajIdea2 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr >
							<td>
								3.退货地址是否符合
							</td>
							<td height="20px"> 
								<input type="checkbox"   id="radio5" value="1" disabled="disabled" 
									<c:if test='${salesBackContractOfShowDto.areaMajIdea3 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"   id="radio5" value="0" disabled="disabled" 
									<c:if test='${salesBackContractOfShowDto.areaMajIdea3 == "0"}'>checked="checked"</c:if> />
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
										<td style="padding:5px 0 5px 0;" name="tdSellMaj" width="339px">
											${tf:replaceBr(salesBackContractOfShowDto.areaMajText)}
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="20px">
								签字：&nbsp; ${salesBackContractOfShowDto.areaMajName}
							</td>
							<td>
								日期：&nbsp; ${salesBackContractOfShowDto.areaMajDate}
							</td>
						</tr>
						<tr>
							<td height="20px" colspan="2">
								&nbsp;
							</td>
						</tr>
        <tr>
          <td height="20px" colspan="2">销售总监评审意见：</td>
        </tr>
        <c:if test="${salesBackContractOfShowDto.sellMajIdea!=null && fn:substring(salesBackContractOfShowDto.sellMajIdea,0,1)==0}">
          <c:set value="checked" var="sellCountUnpass" scope="page"></c:set>
        </c:if>
        <c:if test="${salesBackContractOfShowDto.sellMajIdea!=null && fn:substring(salesBackContractOfShowDto.sellMajIdea,0,1)==1}">
          <c:set value="checked" var="sellCountPass" scope="page"></c:set>
        </c:if>
        <c:if test="${salesBackContractOfShowDto.sellMajIdea!=null && fn:substring(salesBackContractOfShowDto.sellMajIdea,1,2)==0}">
          <c:set value="checked" var="sellQuomodoUnpass" scope="page"></c:set>
        </c:if>
        <c:if test="${salesBackContractOfShowDto.sellMajIdea!=null && fn:substring(salesBackContractOfShowDto.sellMajIdea,1,2)==1}">
          <c:set value="checked" var="sellQuomodoPass" scope="page"></c:set>
        </c:if>
        <c:if test="${salesBackContractOfShowDto.sellMajIdea!=null && fn:substring(salesBackContractOfShowDto.sellMajIdea,2,3)==0}">
          <c:set value="checked" var="sellAddressUnpass" scope="page"></c:set>
        </c:if>
        <c:if test="${salesBackContractOfShowDto.sellMajIdea!=null && fn:substring(salesBackContractOfShowDto.sellMajIdea,2,3)==1}">
          <c:set value="checked" var="sellAddressPass" scope="page"></c:set>
        </c:if>
        <tr>
          <td height="20px">1.退货数量是否符合 &nbsp;</td>
          <td height="20px"><c:choose>
              <c:when test="${requestScope.type=='sell' && salesBackContractOfShowDto.status==4 && !param.preview}">
                <input type="checkbox" name="sellMajCountIdea" value="1" id="sellMajCountIdea1"/>
                符合&nbsp;&nbsp;
                <input type="checkbox" name="sellMajCountIdea" value="0" id="sellMajCountIdea0"/>
                不符合 </c:when>
              <c:otherwise> <input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" 
                <c:out value="${sellCountPass}"></c:out>
                />符合
                &nbsp;&nbsp; <input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" 
                <c:out value="${sellCountUnpass}"></c:out>
                />不符合 </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td height="20px">2.退货退款方式是否符合 &nbsp;</td>
          <td height="20px"><c:choose>
              <c:when test="${requestScope.type=='sell' && salesBackContractOfShowDto.status==4 && !param.preview}">
                <input type="checkbox" name="sellMajQuomodoIdea" value="1" id="sellMajQuomodoIdea1"/>
                符合&nbsp;&nbsp;
                <input type="checkbox" name="sellMajQuomodoIdea" value="0" id="sellMajQuomodoIdea0"/>
                不符合 </c:when>
              <c:otherwise> <input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" 
                <c:out value="${sellQuomodoPass}"></c:out>
                />符合
                &nbsp;&nbsp; <input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" 
                <c:out value="${sellQuomodoUnpass}"></c:out>
                />不符合 </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td height="20px">3.退货地址是否符合 &nbsp;</td>
          <td height="20px"><c:choose>
              <c:when test="${requestScope.type=='sell' && salesBackContractOfShowDto.status==4 && !param.preview}">
                <input type="checkbox" name="sellMajAddressIdea" value="1" id="sellMajAddressIdea1"/>
                符合&nbsp;&nbsp;
                <input type="checkbox" name="sellMajAddressIdea" value="0" id="sellMajAddressIdea0"/>
                不符合 </c:when>
              <c:otherwise> <input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" 
                <c:out value="${sellAddressPass}"></c:out>
                />符合
                &nbsp;&nbsp; <input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" 
                <c:out value="${sellAddressUnpass}"></c:out>
                />不符合 </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td colspan="2"><c:choose>
              <c:when test="${requestScope.type=='sell' && salesBackContractOfShowDto.status==4 && !param.preview}">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="60px" valign="top" style="padding-top:5px" nowrap>补充说明：</td>
                    <td style="padding:5px 0 5px 0;"><html:textarea styleId="sellMajText" property="saleBackContractOfAddDto.sellMajText" rows="3" cols="60"></html:textarea>
                    </td>
                  </tr>
                </table>
              </c:when>
              <c:otherwise>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="60px" valign="top" style="padding-top:5px" nowrap>补充说明：</td>
                    <td style="padding:5px 0 5px 0;">${tf:replaceBr(salesBackContractOfShowDto.sellMajText)}</td>
                  </tr>
                </table>
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td width="320" height="20px">签字：${salesBackContractOfShowDto.sellMajName }</td>
          <td width="140">日期：${salesBackContractOfShowDto.sellMajDate }</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">运营总监评审意见：</td>
        </tr>
        <tr>
          <td height="20px">&nbsp;</td>
          <td height="20px"><c:if test="${salesBackContractOfShowDto.opeMajIdea==1}">
              <c:set value="checked" var="opjPass" scope="page"></c:set>
            </c:if>
            <c:if test="${salesBackContractOfShowDto.opeMajIdea==0}">
              <c:set value="checked" var="opjUnpass" scope="page"></c:set>
            </c:if>
            <c:choose>
              <c:when test="${requestScope.type=='ope' && salesBackContractOfShowDto.status==6 && !param.preview}">
                <html:checkbox property="saleBackContractOfAddDto.opeMajIdea" value="1" styleId="opeMajIdea1">同意</html:checkbox>
                &nbsp;&nbsp;
                <html:checkbox property="saleBackContractOfAddDto.opeMajIdea" value="0" styleId="opeMajIdea0">不同意</html:checkbox>
              </c:when>
              <c:otherwise> <input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" 
                <c:out value="${opjPass}"></c:out>
                />同意
                &nbsp;&nbsp; <input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" 
                <c:out value="${opjUnpass}"></c:out>
                />不同意 </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td colspan="2"><c:choose>
              <c:when test="${requestScope.type=='ope' && salesBackContractOfShowDto.status==6  && !param.preview}">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td valign="top" style="padding-top:5px" nowrap="nowrap">补充说明：</td>
                    <td style="padding:5px 0 5px 0;"><html:textarea styleId="opeMajText" property="saleBackContractOfAddDto.opeMajText" rows="4" cols="60"></html:textarea>
                    </td>
                  </tr>
                </table>
              </c:when>
              <c:otherwise>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td valign="top" style="padding-top:5px" nowrap="nowrap" width="60px">补充说明：</td>
                    <td style="padding:5px 0 5px 0;">${tf:replaceBr(salesBackContractOfShowDto.opeMajText)}</td>
                  </tr>
                </table>
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td height="20px">签字：${salesBackContractOfShowDto.opeMajName }</td>
          <td>日期：${salesBackContractOfShowDto.opeMajDate }</td>
        </tr>
      </table>
    </div>
  	</td>
  </tr>
  </c:if>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    <c:if test="${param.type=='add'}">
    <!-- <a href="#" id="look"><img src="${ctx}/images/btnYuLan.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
	    
	    <a href="#" id="save"><img src="${ctx}/images/btnSave.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    <a href="#" id="submit"><img src="${ctx}/images/btnSubmit.gif" width="65" height="20" /></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    </c:if>
    <c:if test="${param.type=='modify'}">
	    <a href="#" id="modifySave"><img src="${ctx}/images/btnSave.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    <a href="#" id="modifySubmit"><img src="${ctx}/images/btnSubmit.gif" width="65" height="20" /></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    </c:if>
    <c:if test="${param.type=='modify'}">
    	<a href="${ctx}/salesBackContractManage.do?init=true" ><img src="${ctx}/images/btnBack.gif" /></a>
    </c:if>
     <c:if test="${param.type=='add'}">
    	<a href="${ctx}/getSalesContractsList.do?init=true" ><img src="${ctx}/images/btnBack.gif" /></a>	
    </c:if>   
    </td>
    <td></td>
  </tr>
</table>
<br />
<input type="hidden" name="saleBackContractOfAddDto.sellContractId" id="sellContractId" value="${salesBackContractOfShowDto.salesContractId}"/>
<input type="hidden" name="saleBackContractOfAddDto.productContractCode" id="productContractCode" value="${salesBackContractOfShowDto.productContractCode}"/>
<input type="hidden" name="saleBackContractOfAddDto.companyContractCode" id="companyContractCode" value="${salesBackContractOfShowDto.companyContractCode}"/>
<input type="hidden" name="saleBackContractOfAddDto.place" id="place" value="${salesBackContractOfShowDto.place}"/>
<input type="hidden" name="saleBackContractOfAddDto.money" id="money" value="${salesBackContractOfShowDto.backMoney}"/>
<input type="hidden" name="saleBackContractOfAddDto.stockroomAddressId" id="stockroomAddressId" value="${salesBackContractOfShowDto.stockRoomAddressId}"/>
<input type="hidden" name="returnValueToServer" id="returnValueToServer" value=""/>
<input type="hidden" name="productTypeId" id="productTypeId" value="${salesBackContractOfShowDto.productTypeId}"/>
<input type="hidden" name="oldFile" id="oldFile" value="${salesBackContractOfShowDto.file}"/>
<input type="hidden" name="salesBackContractId" id="salesBackContractId" value="${salesBackContractOfShowDto.salesBackContractId}"/>
<input type="hidden" name="saleBackContractOfAddDto.date" id="date" value="${salesBackContractOfShowDto.date}"/>
</html:form>
</body>
</html>
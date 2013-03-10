<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>评审</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
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
#div_title {
	font-size:12px;
}
#div_idea {
}
#div_idea table {
	margin:5px;
	font-size:12px;
}
-->
</style>
<script type="text/javascript">
$(document).ready(function(){
		$("#preview").click(function(){//预览
			window.open('${ctx}/salesBackContractPreview.do?print=true&backContractId=${salesBackContractOfShowDto.salesBackContractId}','newwindow', "toolbar=no,scrollbars=yes,resizable=yes,top=0,left=170, width=750,height=680");
		});
		
		$("#down").click(function(){//下载
			wb.execwb(6,6);
		});
		$("#decide").click(function(){//生效
			if($.trim($("#companyContractCode").val())==""){
				$("#companyContractCodeError").css("color","red").text("请输入公司合同号！");
				$("#companyContractCode").focus().attr("class","invalid");
				return;
			}else{
			if(confirm("警告：公司合同号填写后不可更改!请再次确认！")){
				$("#commentSaleBackForm").attr("action","decideSaleBackContract.do?decideType=true");
				$("#commentSaleBackForm").submit();
				}
			}
		});
		$("#cancel").click(function(){//取消合同
				$("#commentSaleBackForm").attr("action","decideSaleBackContract.do?decideType=false");
				$("#commentSaleBackForm").submit();
		});
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
	$("#sellMajCountIdea0").click(function(){//销售退货数量
		if($("#sellMajCountIdea0").attr("checked")){
			$("#sellMajCountIdea1").attr("checked","");
		}else{
			$("#sellMajCountIdea1").attr("checked","true");
		}
	});
	$("#areaMajIdea1").click(function(){//销售退货数量
		if($("#areaMajIdea1").attr("checked")){
			$("#areaMajIdea10").attr("checked","");
		}else{
			$("#areaMajIdea10").attr("checked","true");
		}
	});
	$("#sellMajCountIdea1").click(function(){//销售退货数量
		if($("#sellMajCountIdea1").attr("checked")){
			$("#sellMajCountIdea0").attr("checked","");
		}else{
			$("#sellMajCountIdea0").attr("checked","true");
		}
	});
	$("#areaMajIdea10").click(function(){//销售退货数量
		if($("#areaMajIdea10").attr("checked")){
			$("#areaMajIdea1").attr("checked","");
		}else{
			$("#areaMajIdea1").attr("checked","true");
		}
	});
	$("#sellMajQuomodoIdea0").click(function(){//销售退货方式
		if($("#sellMajQuomodoIdea0").attr("checked")){
			$("#sellMajQuomodoIdea1").attr("checked","");
		}else{
			$("#sellMajQuomodoIdea1").attr("checked","true");
		}
	});
	$("#areaMajIdea2").click(function(){//销售退货数量
		if($("#areaMajIdea2").attr("checked")){
			$("#areaMajIdea20").attr("checked","");
		}else{
			$("#areaMajIdea20").attr("checked","true");
		}
	});
	$("#sellMajQuomodoIdea1").click(function(){//销售退货方式
		if($("#sellMajQuomodoIdea1").attr("checked")){
			$("#sellMajQuomodoIdea0").attr("checked","");
		}else{
			$("#sellMajQuomodoIdea0").attr("checked","true");
		}
	});
	$("#areaMajIdea20").click(function(){//销售退货数量
		if($("#areaMajIdea20").attr("checked")){
			$("#areaMajIdea2").attr("checked","");
		}else{
			$("#areaMajIdea2").attr("checked","true");
		}
	});
	$("#sellMajAddressIdea0").click(function(){//销售退货地址
		if($("#sellMajAddressIdea0").attr("checked")){
			$("#sellMajAddressIdea1").attr("checked","");
		}else{
			$("#sellMajAddressIdea1").attr("checked","true");
		}
	});
	$("#areaMajIdea3").click(function(){//销售退货数量
		if($("#areaMajIdea3").attr("checked")){
			$("#areaMajIdea30").attr("checked","");
		}else{
			$("#areaMajIdea30").attr("checked","true");
		}
	});
	$("#sellMajAddressIdea1").click(function(){//销售退货地址
		if($("#sellMajAddressIdea1").attr("checked")){
			$("#sellMajAddressIdea0").attr("checked","");
		}else{
			$("#sellMajAddressIdea0").attr("checked","true");
		}
	});
		$("#areaMajIdea30").click(function(){//销售退货数量
		if($("#areaMajIdea30").attr("checked")){
			$("#areaMajIdea3").attr("checked","");
		}else{
			$("#areaMajIdea3").attr("checked","true");
		}
	});
	$("#opeMajIdea0").click(function(){
		if($("#opeMajIdea0").attr("checked")){
			$("#opeMajIdea1").attr("checked","");
		}else{
			$("#opeMajIdea1").attr("checked","true");
		}
	});
	$("#opeMajIdea1").click(function(){
		if($("#opeMajIdea1").attr("checked")){
			$("#opeMajIdea0").attr("checked","");
		}else{
			$("#opeMajIdea0").attr("checked","true");
		}
	});
	$("#legalIdea1").click(function(){
		if($("#legalIdea1").attr("checked")){
			$("#legalIdea0").attr("checked","");
		}else{
			$("#legalIdea0").attr("checked","true");
		}
	});	
	$("#legalIdea0").click(function(){
		if($("#legalIdea0").attr("checked")){
			$("#legalIdea1").attr("checked","");
		}else{
			$("#legalIdea1").attr("checked","true");
		}
	});
	$("#UnpassSubmit").click(function(){
		var type=$("#type").val();
		$("#submitType").val("unpass");
		if(type=='sell'){
			if(!$("#sellMajCountIdea0").attr("checked") && !$("#sellMajCountIdea1").attr("checked")){
				alert("请选择退货数量");
				$("#UnpassSubmit").attr("href","#sellMajCountIdea0");
				return;
			}else if(!$("#sellMajQuomodoIdea0").attr("checked") && !$("#sellMajQuomodoIdea1").attr("checked")){
				alert("请选择退货退款方式");
				$("#UnpassSubmit").attr("href","#sellMajQuomodoIdea0");
				return;
			}else if(!$("#sellMajAddressIdea0").attr("checked") && !$("#sellMajAddressIdea1").attr("checked")){
				alert("请选择退货地址");
				$("#UnpassSubmit").attr("href","#sellMajAddressIdea0");
				return;
			}else if($.trim($("#sellMajText").val())==""){
				alert("请填写补充说明");
				$("#UnpassSubmit").attr("href","#sellMajText");
				return;
				}
		}
		if(type=='area'){
			if(!$("#areaMajIdea1").attr("checked") && !$("#areaMajIdea10").attr("checked")){
				alert("请选择退货数量");
				$("#UnpassSubmit").attr("href","#areaMajIdea1");
				return;
			}else if(!$("#areaMajIdea2").attr("checked") && !$("#areaMajIdea20").attr("checked")){
				alert("请选择退货退款方式");
				$("#UnpassSubmit").attr("href","#sellMajQuomodoIdea0");
				return;
			}else if(!$("#areaMajIdea3").attr("checked") && !$("#areaMajIdea30").attr("checked")){
				alert("请选择退货地址");
				$("#UnpassSubmit").attr("href","#sellMajAddressIdea0");
				return;
			}else if($.trim($("#areaMajText").val())==""){
				alert("请填写补充说明");
				$("#UnpassSubmit").attr("href","#areaMajText");
				return;
				}
		}
		if(type=='ope'){
			if(!$("#opeMajIdea0").attr("checked") && !$("#opeMajIdea1").attr("checked")){
				alert("请选择评审意见");
				$("#UnpassSubmit").attr("href","#opeMajIdea0");
				return;
			}else if($.trim($("#opeMajText").val())==""){
				alert("请填写补充说明");
				$("#UnpassSubmit").attr("href","#opeMajText");
				return;
				}
		}
		if(type=='legal'){
			if(!$("#legalIdea0").attr("checked") && !$("#legalIdea1").attr("checked")){
				alert("请选择评审意见");
				$("#UnpassSubmit").attr("href","#legalIdea0");
				return;
			}else if($.trim($("#legalText").val())==""){
				alert("请填写补充说明");
				$("#UnpassSubmit").attr("href","#legalText");
				return;
				}		
		}
		if(checkTextAreaLen(200) == false){
				return;
			}
		$("#commentSaleBackForm").submit();
	});	
	$("#PassSubmit").click(function(){
		var type=$("#type").val();
		$("#submitType").val("pass");
		if(type=='sell'){
			if(!$("#sellMajCountIdea1").attr("checked")){
				alert("请选择符合");
				$("#PassSubmit").attr("href","#sellMajCountIdea1");
				return;
			}else if(!$("#sellMajQuomodoIdea1").attr("checked")){
				alert("请选择符合");
				$("#PassSubmit").attr("href","#sellMajQuomodoIdea1");
				return;
			}else if(!$("#sellMajAddressIdea1").attr("checked")){
				alert("请选择符合");
				$("#PassSubmit").attr("href","#sellMajAddressIdea1");
				return;
			}
		}
		if(type=='area'){
			if(!$("#areaMajIdea1").attr("checked")){
				alert("请选择符合");
				$("#PassSubmit").attr("href","#areaMajIdea1");
				return;
			}else if(!$("#areaMajIdea2").attr("checked")){
				alert("请选择符合");
				$("#PassSubmit").attr("href","#areaMajIdea2");
				return;
			}else if(!$("#areaMajIdea3").attr("checked")){
				alert("请选择符合");
				$("#PassSubmit").attr("href","#areaMajIdea3");
				return;
			}
		}
		if(type=='ope'){
			if(!$("#opeMajIdea1").attr("checked")){
				alert("请选择符合");
				$("#PassSubmit").attr("href","#opeMajIdea1");
				return;
			}
		}
		if(type=='legal'){
			if(!$("#legalIdea1").attr("checked")){
				alert("请选择符合");
				$("#PassSubmit").attr("href","#legalIdea1");
				return;
			}		
		}	
		if(checkTextAreaLen(200) == false){
				return;
			}
		$("#commentSaleBackForm").submit();	
	});	
})
</script>
</head>
<body>
<html:form action="commentSalesBackContractOfTransact" method="post" styleId="commentSaleBackForm">
  <c:if test="${requestScope.type=='legal'}">
    <c:set var="title" value="法务专员评审"></c:set>
  </c:if>
  <c:if test="${requestScope.type=='sell'}">
    <c:set var="title" value="销售总监评审"></c:set>
  </c:if>
  <c:if test="${requestScope.type=='ope'}">
    <c:set var="title" value="运营总监评审"></c:set>
  </c:if>
  <c:if test="${param.preview}">
    <c:set var="title" value="销售退货合同查看"></c:set>
  </c:if>
  <c:if test="${param.decide}">
    <c:set var="title" value="销售退货合同确认"></c:set>
  </c:if>
  <c:if test="${param.roleError}">
    <script>alert("越权操作！");</script>
  </c:if>
  <c:if test="${param.commentError}">
    <script>alert("请选择退货！");</script>
  </c:if>
  <c:if test="${param.decideError}">
    <script>alert("确认失败！");</script>
  </c:if>
  <c:if test="${param.codeError}">
    <script>alert("请填写公司合同号！");</script>
  </c:if>
  <c:if test="${param.codeExistError}">	
    <script>alert("此公司合同号已经存在！");</script>
  </c:if>
<c:if test="${param.statusError}">
	<script>alert("执行失败，此状态不能操作");</script>
</c:if>

<c:if test="${param.commentError}">
	<script>alert("评审失败");</script>
</c:if>
<c:if test="${param.noExistError}">
	<script>alert("评审失败，销售助理不存在");</script>
</c:if>
<c:if test="${param.fileError}">
	<script>alert("销售退货合同文件不存在");</script>
</c:if>
<c:if test="${param.fileExistError}">
	<script>alert("下载文件失败");</script>
</c:if>
<c:if test="${param.isTokenValid}">
	<script>alert("不能重复提交！");
		location="${ctx}/salesBackContractManage.do?init=true";
	</script>
	
</c:if>

  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td class="ye_header_left">&nbsp;</td>
      <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt; 销售退货合同管理 &gt;&gt;
        <c:out value="${title}"></c:out></td>
      <td class="ye_header_right">&nbsp;</td>
    </tr>
    <tr>
    
    <td >&nbsp;</td>
    <td>
    <br />
    <div class="div_tiao"> &nbsp;&nbsp;销售合同信息 </div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
      <tr>
        <td class="td_01" height="18px">产品合同号</td>
        <td class="td_02"> ${salesBackContractOfShowDto.productContractCode}&nbsp; </td>
        <td class="td_01">公司合同号</td>
        <td class="td_02"> ${salesBackContractOfShowDto.companyContractCode}
          &nbsp; </td>
      </tr>
      <tr>
        <td class="td_01" height="18px" width="20%">签订日期</td>
        <td class="td_02" width="30%">${salesBackContractOfShowDto.date}&nbsp;</td>
        <td class="td_01" width="20%">产品分类名称</td>
        <td class="td_02" width="30%">${salesBackContractOfShowDto.productTypeName}&nbsp;</td>
      </tr>
      <tr>
        <td class="td_01" height="18px">合同金额</td>
        <td class="td_02"><fmt:formatNumber value="${salesBackContractOfShowDto.money }" type="number" pattern="#,##0.00" />&nbsp;元</td>
        <td class="td_01">预收金额</td>
        <td class="td_02"><fmt:formatNumber value="${salesBackContractOfShowDto.preMoney }" type="number" pattern="#,##0.00" />&nbsp;元</td>
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
										name="salesBackContractOfShowDto"> 普通 </logic:equal>
          <logic:equal value="1" property="customerInvoiceType"
										name="salesBackContractOfShowDto"> 增值税 </logic:equal>
        </td>
      </tr>
    </table>
    <br />
    <input name="submitType" id="submitType" type="hidden"/>
    <div class="div_tiao" id="divreturnContractInfo"> &nbsp;&nbsp;退货合同信息 </div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">

      <tr>
        <td class="td_01" width="20%" height="18">&nbsp;产品合同号</td>
        <td class="td_02" width="30%">${salesBackContractOfShowDto.backProductContractCode}&nbsp;</td>
        <c:choose>
        	<c:when test="${param.decide}">
        		<td class="td_01" >&nbsp;公司合同号</td>
         		<td class="td_02"><input type="text" name="companyContractCode" id="companyContractCode" maxLength="20"/>
            <span id="companyContractCodeError"></span></td>
        	</c:when>
        	<c:otherwise>
        		<td class="td_01" width="20%">公司合同号</td>
        		<td class="td_02" width="30%">${salesBackContractOfShowDto.backCompanyContractCode}&nbsp;</td>
        	</c:otherwise>
        </c:choose>
        
      </tr>      
      <tr>
        <td class="td_01" width="20%" height="18">&nbsp;预计退货时间</td>
        <td class="td_02" width="30%">${salesBackContractOfShowDto.backDate}&nbsp;</td>
        <td class="td_01" width="20%">签订地点</td>
        <td class="td_02" width="30%">${salesBackContractOfShowDto.place}&nbsp;</td>
      </tr>
            <tr>
        <td class="td_01"  height="18">&nbsp;模版类型</td>
        <td class="td_02" 
        <c:if test="${!param.decide}">colspan="3"</c:if>
        >
        <logic:equal value="0" property="templateType"
										name="salesBackContractOfShowDto"> 模版 </logic:equal>
        <logic:equal value="1" property="templateType"
										name="salesBackContractOfShowDto"> 非模版
        </logic:equal>
      </td>
      </tr>
    </table>
    <br/>
    <div class="div_tiao" id="divProductInfo"> &nbsp;&nbsp;产品信息 </div>
    <div style="width:100%; text-align:right">单位：元</div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table1" name="table1" style="border-left:1px solid #FFFFFF;">
      <tr>
        <th nowrap="nowrap" width="35" style="border-left:1px solid #c2c2c2;">序号</th>
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
          <tr>
            <td nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18px"><c:out value="${index+1}"></c:out></td>
            <td nowrap="nowrap" >${goodsInfo.productCode }&nbsp;</td>
            <td nowrap="nowrap" >${goodsInfo.productName }&nbsp;</td>
            <td nowrap="nowrap" >${goodsInfo.specModel }&nbsp;</td>
            <td nowrap="nowrap" >${goodsInfo.unit }&nbsp;</td>
            <td nowrap="nowrap" style=" text-align:right;">${goodsInfo.sellCount }&nbsp;</td>
            <td nowrap="nowrap" style=" text-align:right;" ><fmt:formatNumber value="${goodsInfo.sellPrice }" type="number" pattern="#,##0.00" />&nbsp;</td>
            <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${goodsInfo.sellMoney }" type="number" pattern="#,##0.00" />&nbsp;</td>
            <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${goodsInfo.sendGoodsMoney }" type="number" pattern="#,##0.00" />&nbsp;</td>
            <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${goodsInfo.preGoodsMoney }" type="number" pattern="#,##0.00" />&nbsp;</td>
            <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${goodsInfo.appointMoney }" type="number" pattern="#,##0.00" />&nbsp;</td>
            <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${goodsInfo.makeInvoiceMoney }" type="number" pattern="#,##0.00" />&nbsp;</td>
            
            <td nowrap="nowrap" style=" text-align:right; ">
            <c:choose>
            	<c:when test="${salesBackContractOfShowDto.status==2 || salesBackContractOfShowDto.status==4 || salesBackContractOfShowDto.status==6 || salesBackContractOfShowDto.status==8 || salesBackContractOfShowDto.status==9 || salesBackContractOfShowDto.status==11}">${goodsInfo.alreadyReturnCount - goodsInfo.backContractGoodsCount }</c:when>
            	<c:otherwise>${goodsInfo.alreadyReturnCount}</c:otherwise>
            </c:choose>
            &nbsp;</td>
            <td nowrap="nowrap" style=" text-align:right; ">${goodsInfo.returnCount }&nbsp;</td>
            <td nowrap="nowrap" style=" text-align:right; "> ${goodsInfo.backContractGoodsCount} &nbsp;</td>
            <td nowrap="nowrap" style=" text-align:right; padding-right:6px;"><fmt:formatNumber value="${goodsInfo.backContractGoodsMoney }" type="number" pattern="#,##0.00" />&nbsp;</td>
          </tr>
        </logic:iterate>
      </logic:present>
      <tr id="lastTr">
        <td style=" border:1px solid #FFFFFF; background-color:#FFFFFF"></td>
        <td style=" border:1px solid #FFFFFF; background-color:#FFFFFF"></td>
        <td colspan="2" style=" border:1px solid #FFFFFF; background-color:#FFFFFF"></td>
        <td colspan="11" nowrap="nowrap" style="text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">退货金额合计 </td>
        <td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; background-color:#FFFFFF" id="makeInvoiceSum"><logic:present name="salesBackContractOfShowDto"><fmt:formatNumber value="${salesBackContractOfShowDto.backMoney }" type="number" pattern="#,##0.00" />元</logic:present>
          <logic:notPresent name="salesBackContractOfShowDto">0</logic:notPresent>
        </td>
      </tr>
    </table>
    <br />
    <div class="div_tiao" id="divreturnInfo"> &nbsp;&nbsp;退货退款信息 </div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
      <tr>
        <td  class="td_03" width="50%">&nbsp;退货退款方式 &nbsp;</td>
        <td class="td_04" style="padding:5px 100px 5px 5px"><c:if test="${salesBackContractOfShowDto.backWay=='1'}">先退货后退款</c:if>
          <c:if test="${salesBackContractOfShowDto.backWay=='2'}">先退款后退货</c:if>
          <c:if test="${salesBackContractOfShowDto.backWay=='3'}">已供货未付款</c:if>
          <c:if test="${salesBackContractOfShowDto.backWay=='4'}">未供货</c:if>
        </td>
      </tr>
    </table>
    <br/>
    <div class="div_tiao" id="divreceiveInfo"> &nbsp;&nbsp;收货信息 </div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
      <tr>
        <td class="td_01" height="18px" name="tdname">库房名称</td>
        <td colspan="3" class="td_02" > ${salesBackContractOfShowDto.stockRoomName} </td>
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
        <td class="td_03" width="30%"><span style="color:#FF0000">*</span>&nbsp;退货原因说明</td>
        <td class="td_04" style="padding:5px 100px 5px 5px">${tf:replaceBr(salesBackContractOfShowDto.text)} </td>
      </tr>
    </table>
    </td>
    <td >&nbsp;</td>
    </tr>
    <tr>
    	<td></td>
    	<td><br />
  <input type="hidden" name="saleBackContractOfAddDto.id" id="salesBackContractId" value="${salesBackContractOfShowDto.salesBackContractId}"/>
  <input type="hidden" name="type" id="type" value="${requestScope.type}"/>
  <input type="hidden" name="saleBackContractOfAddDto.productContractCode" id="productContractCode" value="${salesBackContractOfShowDto.productContractCode}"/>
  <input type="hidden" name="saleBackContractOfAddDto.productTypeId" id="productTypeId" value="${salesBackContractOfShowDto.productTypeId}"/>
  <c:if test="${requestScope.type=='area' || requestScope.type=='legal' || requestScope.type=='sell' || requestScope.type=='ope' || param.preview || param.decide}">
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
        <tr>
          <td height="20px" colspan="2">区域总监评审意见：</td>
        </tr>
        <tr>
          <td height="20px">1.退货数量是否符合 &nbsp;</td>
          <td height="20px"><c:choose>
              <c:when test="${requestScope.type=='area' && salesBackContractOfShowDto.status==12 && !param.preview}">
                <input type="checkbox" name="saleBackContractOfAddDto.areaMajIdea1" value="1" id="areaMajIdea1"/>
                符合&nbsp;&nbsp;
                <input type="checkbox" name="saleBackContractOfAddDto.areaMajIdea1" value="0" id="areaMajIdea10"/>
                不符合 </c:when>
              <c:otherwise> <input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" 
                <c:if test="${salesBackContractOfShowDto.areaMajIdea1==1}">checked="checked"</c:if>
                />符合
                &nbsp;&nbsp; <input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" 
                <c:if test="${salesBackContractOfShowDto.areaMajIdea1==0}">checked="checked"</c:if>
                />不符合 </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td height="20px">2.退货退款方式是否符合 &nbsp;</td>
          <td height="20px"><c:choose>
              <c:when test="${requestScope.type=='area' && salesBackContractOfShowDto.status==12 && !param.preview}">
                <input type="checkbox" name="saleBackContractOfAddDto.areaMajIdea2" value="1" id="areaMajIdea2"/>
                符合&nbsp;&nbsp;
                <input type="checkbox" name="saleBackContractOfAddDto.areaMajIdea2" value="0" id="areaMajIdea20"/>
                不符合 </c:when>
              <c:otherwise> <input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" 
                <c:if test="${salesBackContractOfShowDto.areaMajIdea2==1}">checked="checked"</c:if>
                />符合
                &nbsp;&nbsp; <input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" 
                <c:if test="${salesBackContractOfShowDto.areaMajIdea2==0}">checked="checked"</c:if>
                />不符合 </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td height="20px">3.退货地址是否符合 &nbsp;</td>
          <td height="20px"><c:choose>
              <c:when test="${requestScope.type=='area' && salesBackContractOfShowDto.status==12 && !param.preview}">
                <input type="checkbox" name="saleBackContractOfAddDto.areaMajIdea3" value="1" id="areaMajIdea3"/>
                符合&nbsp;&nbsp;
                <input type="checkbox" name="saleBackContractOfAddDto.areaMajIdea3" value="0" id="areaMajIdea30"/>
                不符合 </c:when>
              <c:otherwise> <input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" 
                <c:if test="${salesBackContractOfShowDto.areaMajIdea3==1}">checked="checked"</c:if>
                />符合
                &nbsp;&nbsp; <input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" 
                <c:if test="${salesBackContractOfShowDto.areaMajIdea3==0}">checked="checked"</c:if>
                />不符合 </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td colspan="2"><c:choose>
              <c:when test="${requestScope.type=='area' && salesBackContractOfShowDto.status==12 && !param.preview}">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="60px" valign="top" style="padding-top:5px" nowrap>补充说明：</td>
                    <td style="padding:5px 0 5px 0;"><textarea name="saleBackContractOfAddDto.areaMajText" id="areaMajText" rows="3" cols="60"></textarea>
                    </td>
                  </tr>
                </table>
              </c:when>
              <c:otherwise>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="60px" valign="top" style="padding-top:5px" nowrap>补充说明：</td>
                    <td style="padding:5px 0 5px 0;">${tf:replaceBr(salesBackContractOfShowDto.areaMajText)}</td>
                  </tr>
                </table>
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td width="320" height="20px">签字：${salesBackContractOfShowDto.areaMajName }</td>
          <td width="140">日期：${salesBackContractOfShowDto.areaMajDate }</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">&nbsp;</td>
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
  </c:if>
  <br/>
  <br/>
  <c:if test="${(requestScope.type=='area' ||requestScope.type=='legal' || requestScope.type=='sell' || requestScope.type=='ope') && !param.preview}">
    <div style="text-align:center;"> <a href="#" id="PassSubmit"><img src="${ctx}/images/btnTG.gif" width="65" height="20" /></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" id="UnpassSubmit"><img src="${ctx}/images/btnWTG.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="${ctx}/salesBackContractManage.do?init=true" ><img src="${ctx}/images/btnBack.gif" /></a> </div>
  </c:if>
  <c:if test="${param.preview}">
    <div style="text-align:center;">
      <c:choose>
      	<c:when test="${salesBackContractOfShowDto.templateType==1}">
      		<html:link styleId="down" action="downloadSalesBackContractFile.do?fileName=${salesBackContractOfShowDto.file}" paramId="salesBackContractId" paramName="salesBackContractOfShowDto" paramProperty="salesBackContractId"><img src="${ctx}/images/btnXiaZai.gif" width="65" height="20" /></html:link>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      	</c:when>
        <c:otherwise>
          <a id="preview" href="#"><img src="${ctx}/images/btnYuLan.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
         </c:otherwise>
      </c:choose>
      <a href="${ctx}/salesBackContractManage.do?init=true" ><img src="${ctx}/images/btnBack.gif" /></a> </div>
  </c:if>
  <c:if test="${param.decide}">
    <div style="text-align:center;"> <a href="#" id="decide"><img src="${ctx}/images/btnSX.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" id="cancel"><img src="${ctx}/images/btnQXHT.gif" width="88" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="${ctx}/salesBackContractManage.do?init=true"><img src="${ctx}/images/btnBack.gif" /></a> </div>
  </c:if>
    	</td>
    	<td></td>
    </tr>
  </table>
  <input type="hidden" value="${salesBackContractOfShowDto.userAreaId}" name="userAreaId"/>
</html:form>
</body>
</html>

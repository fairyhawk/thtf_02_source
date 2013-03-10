<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;
charset=utf-8"%> 
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>退票查看</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
</head>
<script type="text/javascript" >
$(function(){


		$("#totalMoney").html(fmoney(${totalMakeInvoicePrice},2)+'元');
	
	});

//数字每隔三位加上逗号
function   formatNum(num)
{   
    if(!/^(\+|-)?(\d+)(\.\d+)?$/.test(num)){alert("金额格式化错误!");   return   num;}   
    var   a   =   RegExp.$1,   b   =   RegExp.$2,   c   =   RegExp.$3;   
    var   re   =   new   RegExp().compile("(\\d)(\\d{3})(,|$)");   
    while(re.test(b))   b   =   b.replace(re,   "$1,$2$3");   
    return   a   +""+   b   +""+   c;   
}

//金额合计的格式化s为要格式化的参数（浮点型），n为小数点后保留的位数	
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

</script>
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
			$(document).ready(function(){
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
			});
		//-->
		</script>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt 销售发票管理 &gt;&gt; 发票查看 </td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;发票信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
				<td class="td_01" width="20%" height="18px">开票日期</td>
          		<td class="td_02" width="30%">${sellInvoiceDto.date}&nbsp;</td>
          		<td class="td_01" width="20%">发票号</td>
          		<td class="td_02" width="30%">${sellInvoiceDto.number}&nbsp;</td>
          </tr>
          <tr>
				<td class="td_01" height="18px">发票金额</td>
          		<td class="td_02"><fmt:formatNumber value="${sellInvoiceDto.money}" pattern="#,##0.00"/>&nbsp;</td>
          		<td class="td_01">&nbsp;</td>
          		<td class="td_02">&nbsp;</td>
          </tr>
      </table>
   <br/>
	 <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
              <td height="18" class="td_03" width="50%">产品分类名称</td>
                <td class="td_04">${sellInvoiceDto.productTypeName}&nbsp;</td>
            </tr>
   	  </table>
   	  <br />
      <div style="width:100%; text-align:right">单位：元</div>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
			<tr>
				<th nowrap="nowrap" width="35" style="border-left:1px solid #c2c2c2;">序号</th>
				<th nowrap="nowrap">发货单号</th>
				<th nowrap="nowrap">产品合同号</th>
				<th nowrap="nowrap">公司合同号</th>
				<th nowrap="nowrap">产品编码</th>
				<th nowrap="nowrap">产品名称</th>
				<th nowrap="nowrap">规格型号</th>
				<th nowrap="nowrap">单位</th>
				<th nowrap="nowrap">销售单价</th>
				<th nowrap="nowrap">开票数</th>
				<th nowrap="nowrap">开票金额</th>
				
			</tr>
			<logic:present name="sellInvoiceDetailList">
				<logic:iterate id="sellInvoiceDetail" name="sellInvoiceDetailList" indexId="indexId">
			<tr>
				<td nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18px">&nbsp;${indexId+1}</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.sendGoodsId}&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.productContractCode}&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.companyContractCode}&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.productCode}&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.productName}&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.productType}&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.productUnit}&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; width:93px;"><fmt:formatNumber value="${sellInvoiceDetail.price}" pattern="#,##0.00"/>&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; width:93px;" >${sellInvoiceDetail.count}&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; padding-right:12px;width:93px"><fmt:formatNumber value="${sellInvoiceDetail.makeInvoicePrice}" pattern="#,##0.00"/></td>				
			</tr>		
				</logic:iterate>
			</logic:present>
			</table>
		<table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
			<tr>
			<td align="center" >&nbsp;</td>
         	<td style=" text-align:right;width:96px;padding-right:12px;">退票金额合计</td>
        	<td style=" text-align:right;width:100px;padding-top:5px;" id="totalMoney"></td>
			</tr>
		</table>
			<div class="div_tiao"> &nbsp;&nbsp;备注信息</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="20%">备注信息</td>
					<td class="td_04" style="padding:5px 100px 5px 5px">${tf:replaceBr(sellInvoiceDto.remark)}&nbsp;</td>
				</tr>
			</table>
			<br/>
			<div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="20%">特殊说明</td>
					<td class="td_04" style="padding:5px 100px 5px 5px">${tf:replaceBr(sellInvoiceDto.text)}&nbsp;</td>
				</tr>
			</table>
			<br/>
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
            <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
              <tr>
			  	<td height="20px" colspan="2">销售总监评审意见：</td>
			  	</tr>
			  <tr>
			  	<td height="20px">&nbsp;</td>
			  	<td height="20px">
			  		<input type="checkbox" name="sellInvoiceDto.sellMajIdea" id="checkbox" disabled="disabled"
					<c:if test="${sellInvoiceDto.sellMajIdea == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
					<input type="checkbox" name="sellInvoiceDto.sellMajIdea"	id="checkbox" disabled="disabled"
					<c:if test="${sellInvoiceDto.sellMajIdea == 0}">checked="checked"</c:if> />
										不符合
			  	</td>			  	
			  </tr>
			  <tr>
			  	<td height="20px" colspan="2">
			  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="60" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                          <td width="395" align="left" style="padding:5px 0 5px 0;">${tf:replaceBr(sellInvoiceDto.sellMajText)}&nbsp;
                          </td>
                        </tr>
                	</table>
					</td>
			  	</tr>
              <tr>
                <td width="320" height="20px">签字：&nbsp;${sellInvoiceDto.sellMajName}</td>
                <td width="140">日期：&nbsp;${sellInvoiceDto.sellMajDate}</td>
              </tr>
			  <tr>
			  	<td height="20px" colspan="2">&nbsp;</td>
			  	</tr>
			  <tr>
			  	<td height="20px" colspan="2">运营总监评审意见：</td>
			  	</tr>
			  <tr>
			  	<td height="20px">&nbsp;</td>
			  	<td height="20px">
			  		<input type="checkbox" name="sellInvoiceDto.opeMajIdea"	id="checkbox" disabled="disabled"
					<c:if test="${sellInvoiceDto.opeMajIdea == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
					<input type="checkbox" name="sellInvoiceDto.opeMajIdea"	id="checkbox" disabled="disabled"
					<c:if test="${sellInvoiceDto.opeMajIdea == 0}">checked="checked"</c:if> />
										不符合
			  </tr>
			  <tr>
			  	<td height="20px" colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="60" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                          <td width="395" align="left" style="padding:5px 0 5px 0;">${tf:replaceBr(sellInvoiceDto.opeMajText)}&nbsp;</td>
                        </tr>
                	</table>				
                </td>
			  </tr>
			  <tr>
					<td height="20px">签字：&nbsp;${sellInvoiceDto.opeMajName}</td>
			  	<td>日期：&nbsp;${sellInvoiceDto.opeMajDate}</td>
		  	  </tr>
      </table>
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom"> 
        <img src="${ctx}/images/btnBack.gif" 
        <c:if test="${back == true}">onclick="javascript:window.history.back()"</c:if>  
        <c:if test="${back != true}">onclick="javascript:window.location='${ctx}/getBackInvoiceByUserId.do'"</c:if> 
         
        />
    </td>
    <td></td>
  </tr>
</table>
</body>
</html>

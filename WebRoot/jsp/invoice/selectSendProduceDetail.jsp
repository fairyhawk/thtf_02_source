<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>发货明细选择</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<style>
.treven {
	background-color: #f3fbff;
}
.over {
	background-color: #ECECEC;}
#fenye a {text-decoration: underline}
</style>
</head>
<script type="text/javascript">

$(document).ready(function(){
if($("#valueList")){
			$("#valueList tr:even").addClass("treven");
			$("#valueList tr").each(function(i){
				$(this).mouseover(function(){
					$(this).addClass("over");
				});
				$(this).mouseout(function(){
					$(this).removeClass("over");
				});
			});
		}
$("#changeCheckdValue").click(function(){//点击
if($("#valueList input:checked[name='id']").length==0){alert("请选择发货明细！");}
if($("#cid",window.opener.document).val()!=$("#cid").val()){
	alert("客户已改变，请重新选择");
	window.close();
	return;
}else 
if($("#productTypeId",window.opener.document).val()!="" && $("#productTypeId",window.opener.document).val()!=$("#productTypeId").val()){
	alert("产品分类已改变，请重新选择");
	window.close();
	return;
}
$("#valueList input:checked[name='id']").each(function(){//循环 chenkbox选中 
var productType={name:$("#tr"+$(this).attr("id")+" td:nth-child(4)").text(),id:$("#productTypeId").val()};
opener.getOpenerValOfProduct($("#tr"+$(this).attr("id")).html(),$(this).attr("id"),$("#tr"+$(this).attr("id")+" td:nth-child(7)"),productType);//调用父窗口方法 参数：tr里面的信息，id，name,productType
window.close();
});
	
});
$("#findSendGoods").click(function(){
	if($("#productTypeName").val()==""){alert("请选择产品分类名称");$("#productTypeName").focus();return;}
	document.forms[0].submit();
});
});


</script>
<body>
<html:form action="findSendGoodsParticularList.do" method="get">
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center">
   <tr>
    <td align="center"><br />
    	<div class="mo_wp">
          <div style="display: ; " class="mo_con" >
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
             <tr>
                <td class="td_01" width="20%">产品分类名称</td>
                <td class="td_02" width="30%">
                <html:select styleId="productTypeName" property="sendGoodsParticularListDto.productTypeName" value="${sendGoods.sendGoodsParticularListDto.productTypeName}" style="width:126px;" disabled="${requestScope.isTrue}">
					<html:option value="">--请选择--</html:option>
					<html:options collection="productType" property="id"  labelProperty="name" />
				</html:select>
                </td>
                 <td class="td_01" width="20%">发货单号</td>
                <td class="td_02" width="30%">
                	<html:text property="sendGoodsParticularListDto.id" style="width:120px;"></html:text>
                </td>
              </tr>
              <tr>

                 <td class="td_01" width="20%">产品合同号</td>
                 <td class="td_02" width="30%">
                 	<html:text property="sendGoodsParticularListDto.productContractNum" style="width:120px;"></html:text>
                 </td>
                 <td class="td_01">公司合同号</td>
               	<td class="td_02">
               	<html:text property="sendGoodsParticularListDto.companyContractNum" style="width:120px;"></html:text>
               	</td>
              </tr>
               <tr>

               	<td class="td_01">产品编码</td>
               	<td class="td_02">
               	<html:text property="sendGoodsParticularListDto.productCode" style="width:120px;"></html:text>
               	</td>
                	<td class="td_01">产品名称</td>
               	<td class="td_02">
               	<html:text property="sendGoodsParticularListDto.productName" style="width:120px;"></html:text>
               	</td>
               	</tr>
               <tr>

               	<td class="td_01">到账起始日期</td>
               	<td class="td_02">
               	<html:text property="sendGoodsParticularListDto.beginTime" style="width:120px;" onfocus="calendar()" readonly="readonly"></html:text>
               	</td>
                	<td class="td_01">到账终止日期</td>
               	<td class="td_02">
               	<html:text property="sendGoodsParticularListDto.endTime" style="width:120px;" onfocus="calendar()" readonly="readonly"></html:text>
               	</td>
               	</tr>
               <tr>
               	<td class="td_01">规格型号</td>
               	<td class="td_02">
               	<html:text property="sendGoodsParticularListDto.specModel" style="width:120px;"></html:text>
               	</td>
            	<td class="td_01">&nbsp;</td>
               	<td class="td_02">&nbsp;
               	</td>              	
               	</tr>
              <tr>
                <td colspan="4" align="center" style="height:30px;">
                 <a href="#" id="findSendGoods"><img  src="${ctx}/images/btn_JianSuo.gif" /></a>
                 
                 </td>
              </tr>
            </table>
            <c:choose>
            	<c:when test="${sendGoods.sendGoodsParticularListDto.customerId==null}">
            		<html:hidden property="sendGoodsParticularListDto.customerId" value="${param.cid}" styleId="cid" />
            	</c:when>
            	<c:otherwise>
            		<html:hidden property="sendGoodsParticularListDto.customerId" value="${sendGoods.sendGoodsParticularListDto.customerId }" styleId="cid"/>
            	</c:otherwise>
            </c:choose>
          </div>
          <div class="mo_title" onclick="fMainListToggle(this)">
              <div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
          </div>
        </div>    </td>
  </tr>
  <tr>
    <td align="center">
    <br/>
        <logic:present name="sendGoodsList">
    <div style="width:100%; text-align:right;font-size:12px">单位：元</div>

    <table id="valueList" width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" style="border-left:1px solid #FFFFFF;">
		<tr>
			<th nowrap="nowrap" width="40" style="border-left:1px solid #c2c2c2;border-right:0px solid #ffffff;">选择</th>
			<th nowrap="nowrap" style="border-left:1px solid #c2c2c2;">发货单号</th>
			<th nowrap="nowrap">要求到账日期</th>
			<th nowrap="nowrap">产品分类名称</th>
			<th nowrap="nowrap">产品合同号</th>
			<th nowrap="nowrap">公司合同号</th>
			<th nowrap="nowrap">产品编码</th>
			<th nowrap="nowrap">产品名称</th>
			<th nowrap="nowrap">规格型号</th>
			<th nowrap="nowrap">单位</th>
			<th nowrap="nowrap">销售单价</th>
			<th nowrap="nowrap">发货数</th>
			<th nowrap="nowrap">指定金额</th>
			<th nowrap="nowrap">申请开票数</th>
			<th nowrap="nowrap">开票数</th>
			<th nowrap="nowrap">开票金额</th>
			<th nowrap="nowrap">退货金额</th>
			
		</tr>
		
		<logic:iterate id="sendGoodsList" name="sendGoodsList">
		<tr id="tr${sendGoodsList.id }${sendGoodsList.productId}">
			<td nowrap="nowrap" id="first" style="border-left:1px solid #c2c2c2;border-right:1px solid #c2c2c2">
			<html:checkbox property="id" name="sendGoodsList" styleId="${sendGoodsList.id }${sendGoodsList.productId}"></html:checkbox>
			</td>
			<td nowrap="nowrap" style="">${sendGoodsList.id }</td>
			<td nowrap="nowrap" id="getDate">${sendGoodsList.getDate }&nbsp;</td>
			<td nowrap="nowrap" id="tdProductTypeName">${tf:replaceHTML(sendGoodsList.productTypeName)}&nbsp;</td>
			<td nowrap="nowrap" >${sendGoodsList.productContractNum }&nbsp;</td>
			<td nowrap="nowrap" >${sendGoodsList.companyContractNum }&nbsp;</td>
			<td nowrap="nowrap" >${tf:replaceHTML(sendGoodsList.productCode)}&nbsp;</td>
			<td nowrap="nowrap" >${tf:replaceHTML(sendGoodsList.productName)}&nbsp;</td>
			<td nowrap="nowrap" >${tf:replaceHTML(sendGoodsList.specModel)}&nbsp;</td>
			<td nowrap="nowrap" >${tf:replaceHTML(sendGoodsList.unit)}&nbsp;</td>
			<td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${sendGoodsList.sellPrice }" type="number" pattern="#,##0.00" />&nbsp;</td>
			<td nowrap="nowrap" style=" text-align:right;padding-right:5px">${sendGoodsList.sendCount }</td>
			<td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${sendGoodsList.money }" type="number" pattern="#,##0.00" />&nbsp;</td>
			<td nowrap="nowrap" id="tdInvoiceCount" style=" text-align:RIGHT;padding-right:5px"><logic:empty name="sendGoodsList" property="appointCount">0</logic:empty><logic:notEmpty name="sendGoodsList" property="appointCount">${sendGoodsList.appointCount }</logic:notEmpty></td>
			<td nowrap="nowrap" id="tdInvoiceMoney" style="display:none;text-align:right;padding-right:5px">0.00</td>
			<td nowrap="nowrap" style=" text-align:right;padding-right:5px">${sendGoodsList.invoiceCount}</td>
			<td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${sendGoodsList.invoiceMoney }" type="number" pattern="#,##0.00" />&nbsp;</td>
			<td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${sendGoodsList.returnMoney}" type="number" pattern="#,##0.00" />&nbsp;</td>
			<td style="display:none">${sendGoodsList.productId}</td>
			<td style="display:none">${sendGoodsList.sellPrice }</td>
			<td style="display:none">0</td>
			<td style="display:none">${sendGoodsList.sellBackGoodsCount }</td>
			<html:hidden property="productTypeId" styleId="productTypeId" value="${sendGoodsList.productTypeId }"/>
		</tr>
		</logic:iterate>
		
	</table>
	
    <table align="center" width="100%" cellpadding="0" cellspacing="0">
			<tr>
              <td  align="right" valign="bottom" id="fenye" height="20px">
              <%@ include file="/jsp/common/newPage.jsp"%></td>
            </tr>
            <tr>
              <td height="45px"  align="center" valign="bottom">
              <a href="#" id="changeCheckdValue"><img src="${ctx}/images/btnChoice.gif" width="65" height="20" /></a></td>
            </tr>
        </table>   
        </logic:present> 
    </td>
  </tr>
</table>

<c:if test="${requestScope.isTrue }">
<html:hidden property="sendGoodsParticularListDto.productTypeName" value="${sendGoods.sendGoodsParticularListDto.productTypeName}"/>
</c:if>
<input type="hidden" value="${requestScope.isTrue }" name="isTrue"/>
</html:form>
</body>
</html>
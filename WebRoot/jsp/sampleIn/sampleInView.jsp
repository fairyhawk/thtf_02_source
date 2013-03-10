<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%><html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="tf" uri="localhost" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>样品归还查看</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<style type="text/css">
<!--
.treven {
	background-color: #f3fbff;
}
.over {
	background-color: #ECECEC;
}
.STYLE1 {
	color: #FF0000
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

        $(function(){
            //采购主管评审意见
	        if('${assessDto.buyManIdea}'=='1'){
	        	$('#buyManIdea1').attr("checked",'true');
	        }
	        if('${assessDto.buyManIdea}'=='0'){
	        	$('#buyManIdea0').attr("checked",'true');
	        }
       });
  
  //隐藏table的一列
//$(document).ready(function(){
//	if("${USERLOGIN.roleId}"==4 ||"${USERLOGIN.roleId}"==9){
//        $("td[name='priceTaxTd']").hide();
//  	  $("td[name='moneyTd']").hide();
//  	  $("#priceth").hide();
//  	  $("#moneyth").hide();
//  	  $("td[name='inpriceTd']").hide();
//  	  $("#inpriceth").hide();
//  	  $("#totalmoneytr").hide();
//  	}
//});
  
              

</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
</head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 样品归还管理 &gt;&gt; 样品归还单查看</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
	 <div class="div_tiao"> &nbsp;&nbsp;借出单信息 </div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" width="20%" height="18px">借出单号</td>
            <td class="td_02" width="30%">${assessDto.sampleOutId}&nbsp;</td>
			<td class="td_01" width="20%">借出库房名称</td>
            <td class="td_02" width="30%">${assessDto.outStockroomName}&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">借出单类型</td>
            <td  class="td_02" >
                    <logic:equal value="0" property="type" name="assessDto">
            		          不归还
            	    </logic:equal>
                    <logic:equal value="1" property="type" name="assessDto">
            		          归还
            	    </logic:equal>
            &nbsp;</td>
			<td class="td_01">预计归还日期</td>
            <td class="td_02" >${assessDto.inDate}&nbsp;</td>
          </tr>
          <tr>
          	<td class="td_01" height="18px">借出单位类型</td>
          	<td class="td_02" >
          	        <logic:equal value="1" property="companyType" name="assessDto">
            		          公司
            	    </logic:equal>
                    <logic:equal value="2" property="companyType" name="assessDto">
            		          客户
            	    </logic:equal>
                    <logic:equal value="3" property="companyType" name="assessDto">
            		         供货商
            	    </logic:equal> 
          	&nbsp;</td>
          	<td class="td_01">发货日期</td>
          	<td class="td_02" >${assessDto.sendDate}&nbsp;</td>
          	</tr>
          <tr>
            <td class="td_01" height="18px">借出单位</td>
            <td class="td_02" >${assessDto.companyName}&nbsp;</td>
            <td class="td_01">保管人</td>
            <td class="td_02" >${assessDto.custosName}&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">产品分类名称</td>
            <td class="td_02" >${assessDto.productTypeName}&nbsp;</td>
            <td class="td_01">&nbsp;</td>
            <td class="td_02" >&nbsp;</td>
          </tr>
        </table>
	<br />
    <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
		<tr>
				<td class="td_03" width="20%" height="18">库房名称</td>
				<td class="td_04" >${assessDto.inStockroomName}&nbsp;</td>
		</tr>
	</table>
	<div style="text-align:right; width:100%">单位：元</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
		<tr>
			<th nowrap="nowrap">序号</th>
			<th nowrap="nowrap">产品编码</th>
			<th nowrap="nowrap">产品名称</th>
			<th nowrap="nowrap">规格型号</th>
			<th nowrap="nowrap">单位</th>
			<!-- <th nowrap="nowrap" id="priceth">库存单价</th> -->
			<c:if test="${USERLOGIN.roleId!=4&&USERLOGIN.roleId!=9}"><th nowrap="nowrap" id="priceth">库存单价</th></c:if>			
			<th nowrap="nowrap">借出数</th>
			<!-- <th nowrap="nowrap" id="moneyth">借出金额</th> -->
			<c:if test="${USERLOGIN.roleId!=4&&USERLOGIN.roleId!=9}"><th nowrap="nowrap" id="moneyth">借出金额</th></c:if>			
			
			<th nowrap="nowrap">已归还数</th>
			<th nowrap="nowrap">归还数</th>
			<!-- <th nowrap="nowrap" id="inpriceth">&nbsp;&nbsp;&nbsp;归还金额&nbsp;&nbsp;&nbsp;</th> -->
			<c:if test="${USERLOGIN.roleId!=4&&USERLOGIN.roleId!=9}"><th nowrap="nowrap" id="inpriceth">&nbsp;&nbsp;&nbsp;归还金额&nbsp;&nbsp;&nbsp;</th></c:if>			
			
		</tr>
		
		<logic:iterate id="sampleInProductInfoDto" name="list" indexId="indexId">
		<tr>
			<td width="40" nowrap="nowrap" height="18" style="text-align:center;">${indexId+1}</td>
			<td nowrap="nowrap">${sampleInProductInfoDto.code}&nbsp;</td>
			<td nowrap="nowrap">${sampleInProductInfoDto.productName}&nbsp;</td>
			<td nowrap="nowrap" >${sampleInProductInfoDto.type}&nbsp;</td>
			<td nowrap="nowrap" >${sampleInProductInfoDto.unit}&nbsp;</td>
			<!-- <td nowrap="nowrap"  style="text-align:right;" name="priceTaxTd"><fmt:formatNumber value="${sampleInProductInfoDto.priceTax}" pattern="#,##0.00"/>&nbsp;</td> -->
			<c:if test="${USERLOGIN.roleId!=4&&USERLOGIN.roleId!=9}"><td nowrap="nowrap"  style="text-align:right;" name="priceTaxTd"><fmt:formatNumber value="${sampleInProductInfoDto.priceTax}" pattern="#,##0.00"/>&nbsp;</td></c:if>			
			
			<td nowrap="nowrap"  style="text-align:right;">${sampleInProductInfoDto.count}&nbsp;</td>
			<!-- <td nowrap="nowrap" style="text-align:right;" name="moneyTd"><fmt:formatNumber value="${sampleInProductInfoDto.moneyTax}" pattern="#,##0.00"/>&nbsp;</td> -->
			<c:if test="${USERLOGIN.roleId!=4&&USERLOGIN.roleId!=9}"><td nowrap="nowrap" style="text-align:right;" name="moneyTd"><fmt:formatNumber value="${sampleInProductInfoDto.moneyTax}" pattern="#,##0.00"/>&nbsp;</td></c:if>			
			
			<td nowrap="nowrap"  style="text-align:right;">${sampleInProductInfoDto.alreadyReCount}&nbsp;</td>
			<td nowrap="nowrap" width="92"  style="text-align:right;">${sampleInProductInfoDto.reCount}&nbsp;</td>
			<!-- <td nowrap="nowrap" width="93" style="text-align:right;" name="inpriceTd"><fmt:formatNumber value="${sampleInProductInfoDto.returnMoneyTax}" pattern="#,##0.00"/>&nbsp;&nbsp;</td> -->
			<c:if test="${USERLOGIN.roleId!=4&&USERLOGIN.roleId!=9}"><td nowrap="nowrap" style="text-align:right;" name="moneyTd"><fmt:formatNumber value="${sampleInProductInfoDto.returnMoneyTax}" pattern="#,##0.00"/>&nbsp;</td></c:if>			
		
		</tr>
		</logic:iterate>
	</table>
   
  	<table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
  	 <c:if test="${USERLOGIN.roleId!=4&&USERLOGIN.roleId!=9}">	  
		<tr id="totalmoneytr">
			<td height="25"  align="center">&nbsp;</td>
			<td  align="center" width="100px">归还金额合计</td>
		    <td  style=" text-align:right;width:93px"><fmt:formatNumber value="${assessDto.moneyTotalTax}" pattern="#,##0.00"/>元</td>
		</tr>
	 </c:if>
	</table>
  	<div class="div_tiao"> &nbsp;&nbsp;收货信息</div>
	&nbsp;&nbsp;
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" height="18px">货物接收单位名称</td>
            <td colspan="3" class="td_02" >${assessDto.goodsReceiveUnitName}&nbsp;</td>
		</tr>
          <tr>
            <td class="td_01" height="18px">收货地址</td>
            <td colspan="3"  class="td_02" >${assessDto.address}&nbsp;</td>
		</tr>
          <tr>
            <td class="td_01" width="20%" height="18px">联系人</td>
            <td class="td_02" width="30%">${assessDto.linkman}&nbsp;</td>
            <td class="td_01" width="20%">邮编</td>
            <td class="td_02" width="30%">${assessDto.postcode}&nbsp;</td>
          </tr>
		  <tr>
		  	<td class="td_01" height="18px">电话</td>
		  	<td class="td_02" >${assessDto.tel}&nbsp;</td>
		  	<td class="td_01">手机</td>
		  	<td class="td_02" >${assessDto.mobile}&nbsp;</td>
	  	</tr>
      </table>
		 <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="20%">特殊说明</td>
				  <td class="td_04" style="padding:5px 100px 5px 5px"> ${tf:replaceBr(assessDto.text)}&nbsp;</td>
				</tr>
			</table>
            <br />
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
      <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
        <tr>
          <td height="20px" colspan="2" >采购主管评审意见：</td>
        </tr>
        <tr>
          <td width="320" nowrap="nowrap">库房名称是否符合</td>
          <td height="20px" width="150" nowrap="nowrap">
          <input type="checkbox" name="buyManIdea" id="buyManIdea1" value="1" disabled="disabled"
                    <c:if test='${assessDto.buyManIdea == "1"}'>checked="checked"</c:if>  
          />符合
            &nbsp;&nbsp;
            <input type="checkbox" name="buyManIdea" id="buyManIdea0" value="0" disabled="disabled"
                    <c:if test='${assessDto.buyManIdea == "0"}'>checked="checked"</c:if> 
            />不符合
        </td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                 ${tf:replaceBr(assessDto.buyManText)}
                </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${assessDto.buyManName}&nbsp;</td>
          <td>日期：${assessDto.buyManDate}&nbsp;</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">&nbsp;</td>
        </tr>
        
             <c:set var="disabled" value="disabled" scope="page"></c:set>
		     <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,0,1)==0}">
             <c:set value="checked" var="productSpecUnpass" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,0,1)==1}">
             <c:set value="checked" var="productSpecPass" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,1,2)==0}">
             <c:set value="checked" var="productQualityUnpass" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,1,2)==1}">
             <c:set value="checked" var="productQualityPass" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,2,3)==0}">
             <c:set value="checked" var="productSumUnpass" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,2,3)==1}">
             <c:set value="checked" var="productSumPass" scope="page"></c:set>
             </c:if>       
             
        
        <tr>
          <td height="20px" colspan="2">库房管理员核对意见：</td>
        </tr>
        <tr>
          <td>1.产品规格是否符合</td>
          <td height="20px">
          <input type="checkbox" id="productSpec1" value="1" name="productSpec" ${disabled } ${productSpecPass}/>符合&nbsp;&nbsp;
          <input type="checkbox" id="productSpec0" value="0" ${disabled } name="productSpec" ${productSpecUnpass}/>不符合
          </td>
        </tr>
        <tr>
          <td>2.产品质量是否符合</td>
          <td height="20px">
          <input type="checkbox" ${disabled } name="productQuality" id="productQuality1" value="1" ${productQualityPass}/>符合&nbsp;&nbsp;
          <input type="checkbox" name="productQuality" id="productQuality0" value="0" ${disabled } ${productQualityUnpass}/>不符合
          </td>
        </tr>
        <tr>
          <td>3.产品数量是否符合</td>
          <td height="20px">
          <input type="checkbox" ${disabled } value="1" id="productSum1" name="productSum" ${productSumPass}/>符合&nbsp;&nbsp;
          <input type="checkbox" ${disabled } value="0" id="productSum0" name="productSum" ${productSumUnpass}/>不符合
          </td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="62px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
               		 ${tf:replaceBr(assessDto.stkAdmText)}&nbsp;
                </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${assessDto.stkAdmName}&nbsp;</td>
          <td>日期：${assessDto.stkAdmDate}&nbsp;</td>
        </tr>
      </table>
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom"><a href="${ctx}/getSampleInList.do"><img src="${ctx}/images/btnBack.gif" /></a> </td>
    <td></td>
  </tr>
</table>
<br />
</body>
</html>
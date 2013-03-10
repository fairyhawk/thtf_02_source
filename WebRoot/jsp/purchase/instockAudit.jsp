<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="tf" uri="localhost" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>入库单评审</title>
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
		-->
		<!--
		.STYLE1 {color: #FF0000}
		-->
	</style>
	<script language="JavaScript">
	 
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

			//控制单选
			$('#pro_maj1').click(function(){
				$('#pro_maj2').attr('checked',false);
			});
			$('#pro_maj2').click(function(){
				$('#pro_maj1').attr('checked',false);
			});

			//提交
			$('#btnTG').click(function(){ 
				checkSubmit(1);
			});
			$('#btnWTG').click(function(){ 
				checkSubmit(0);
			}); 

			if("${errorMsg}" != ""){
				alert("${errorMsg}");
			}
			
		});

		//提交验证
		function checkSubmit(type){  
			var textarea = $.trim($("#textarea").val().replace(/[\u3000]/g," "));
			$("#textarea").val(textarea); 
			if($('#pro_maj1').attr('checked')==false && $('#pro_maj2').attr('checked')==false){
				alert("请选择评审意见！");
			}else{
				if(type==1 && $('#pro_maj1').attr('checked')==false){
					alert("评审意见必须为符合！");
					return;
				}else if(type==1 && $('#pro_maj1').attr('checked')==true){
					$('#proMajIdea').val("1");
				}else{
					$('#proMajIdea').val("0");
					if(textarea==""||textarea==null){
						alert("补充说明不可以为空！");
						return false;
					}
				}
				$("#auditkForm").submit();  
			} 
			
		}
	 
	</script> 
</head>

<body>
<html:form method="post" action="instockAudit" styleId="auditkForm">
<input type="hidden" name="auditPara.id" value="${inStockInfo.id}" />
<input type="hidden" name="addPara.id" value="${inStockInfo.id}" />
<input type="hidden" name="isAudit" value="true" />
<input type="hidden" id="proMajIdea" name="auditPara.proMajIdea"/>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" /> &nbsp;当前位置： 采购管理 &gt;&gt; 入库管理 &gt;&gt; 入库单评审</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;采购合同信息 </div>
          	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
                <tr>
				  <td class="td_01" width="20%" height="18px">产品合同号</td>
				  <td class="td_02" width="30%">${buyContract.productContractCode}&nbsp;</td>
				  <td class="td_01" width="20%">公司合同号</td>
				  <td class="td_02" width="30%">${buyContract.companyContractCode}&nbsp;</td>
				</tr>
				<tr>
				  <td class="td_01" height="18px">供货商名称</td>
				  <td class="td_02">${buyContract.supplierName}&nbsp;</td>
				  <td class="td_01">合同签订日期</td>
				  <td class="td_02">${buyContract.date}&nbsp;</td>
				</tr>
				<tr>
				  <td class="td_01" height="18px">发票类型</td>
				  <td class="td_02">
					<c:if test="${buyContract.invoiceType==0}">普通&nbsp;</c:if>
					<c:if test="${buyContract.invoiceType==1}">增值税&nbsp;</c:if> 
				  </td>
				  <td class="td_01">增值税税率</td>
				  <td class="td_02">${buyContract.taxRate}%</td>
				</tr>
            </table>
          	<br />
      <div class="div_tiao"> &nbsp;&nbsp;付款信息 </div>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
           <tr>
			  <td class="td_01" width="20%" height="18px">&nbsp;付款方式</td>
			  <td class="td_02">
				<c:if test="${buyContract.paymentWay==2}">支票&nbsp;</c:if>
				<c:if test="${buyContract.paymentWay==3}">网银&nbsp;</c:if>
				<c:if test="${buyContract.paymentWay==4}">电汇&nbsp;</c:if>
				<c:if test="${buyContract.paymentWay==5}">银行承兑&nbsp;</c:if>
				<c:if test="${buyContract.paymentWay==6}">信用证&nbsp;</c:if>
				<c:if test="${buyContract.paymentWay==7}">其它&nbsp;</c:if>
			  </td>
			  <td class="td_01" width="20%" height="18px">
				<c:if test="${buyContract.paymentWay==5}">账期</c:if>
			  </td>
			  <td class="td_02" width="30%">
				${buyContract.arterm}<c:if test="${buyContract.paymentWay==5}">天</c:if>&nbsp;
			  </td>
			</tr>
			<tr>
			  <td class="td_01" height="18px">&nbsp;付款类型</td>
			  <td colspan="3" class="td_02" >
				<c:if test="${buyContract.paymentType==0}">全部预付&nbsp;</c:if>
				<c:if test="${buyContract.paymentType==1}">部分预付&nbsp;</c:if>
				<c:if test="${buyContract.paymentType==2}">货到付款&nbsp;</c:if> 
			  </td>
			</tr>
			<c:if test="${buyContract.paymentType==0}">
				<tr>
				  <td class="td_01" height="18px">合同签订后</td>
				  <td colspan="3" class="td_02" >${buyContract.contarctPaymentTime}个工作日内</td>
				</tr>
			</c:if>
			<c:if test="${buyContract.paymentType==1}">
				<tr>
				  <td class="td_01" height="18px">合同签订后</td>
				  <td class="td_02" >${buyContract.contarctPaymentTime}个工作日内</td>
				  <td class="td_01">预付金额</td>
				  <td class="td_02" >${buyContract.prepayMoney}元</td>
				</tr> 
				<tr>
				  <td class="td_01" height="18px">验收合格后</td>
				  <td colspan="3" class="td_02" >${buyContract.sendPaymentTime}个工作日内</td>
				</tr> 
			</c:if>
			<c:if test="${buyContract.paymentType==2}">
				<tr>
				  <td class="td_01" height="18px">验收合格后</td>
				  <td class="td_02" >${buyContract.sendPaymentTime}个工作日内</td>
				  <td class="td_01">&nbsp;</td>
				  <td class="td_02" height="18px">&nbsp;</td>
				</tr>
			</c:if>
			<tr>
			  <td class="td_01" height="18px">厂家发货日期</td>
			  <td class="td_02" >${inStockInfo.factorySendDate}</td>
			  <td class="td_01">要求付款日期</td>
			  <td class="td_02" >${inStockInfo.requestAccountDate}</td>
			</tr>
        </table>
      <br/>
		<div class="div_tiao"> &nbsp;&nbsp;收货信息 </div>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
			<tr>
			  <td class="td_01" width="20%" height="18px">货物接收单位名称</td>
			  <td colspan="3" id="receiveName" class="td_02">${receiveGoodsDetail.name}&nbsp;</td>
			</tr>
			<tr>
			  <td class="td_01" height="18px">收货地址</td>
			  <td colspan="3" id="receiveAddress" class="td_02" >${receiveGoodsDetail.address}&nbsp;</td>
			</tr>
			<tr>
			  <td class="td_01" height="18px">联系人</td>
			  <td width="30%" id="receiveLinkman" class="td_02" >${receiveGoodsDetail.linkman}&nbsp;</td>
			  <td width="20%" class="td_01">邮编</td>
			  <td width="30%" id="receivePostcode" class="td_02" >${receiveGoodsDetail.postcode}&nbsp;</td>
			</tr>
			<tr>
			  <td class="td_01" height="18px">电话</td>
			  <td class="td_02" id="receiveTel" >${receiveGoodsDetail.tel}&nbsp;</td>
			  <td class="td_01">手机</td>
			  <td class="td_02" id="receiveMobile" >${receiveGoodsDetail.mobile}&nbsp;</td>
			</tr>
        </table>
        <br/>
      <div class="div_tiao"> &nbsp;&nbsp;产品明细 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" height="18px">库房名称</td>
          <td class="td_02" id="receiveStockroomName">${receiveGoodsDetail.stockroomName}&nbsp;</td>
          <td class="td_01">产品分类名称</td>
          <td class="td_02">${buyContract.productTypeName}&nbsp;</td>
        </tr>
      </table>
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
        <tr>
          <th width="40" nowrap="nowrap" style="border-left:1px solid #c2c2c2;">序号</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>

          <th nowrap="nowrap">应入库数</th>
          <th nowrap="nowrap">采购单价</th>
          <th nowrap="nowrap">采购金额</th>
          <th nowrap="nowrap">已入库数</th>
          <th nowrap="nowrap">入库数</th>

          <th nowrap="nowrap">入库金额</th> 
          <th nowrap="nowrap">返厂数</th>
        </tr>
        <logic:iterate id="product" name="productList" indexId="index">
		<tr>
		  <input type='hidden' class='productIdArr' name='addPara.productIdArr' value='${product.productId}'/> 
		  <input type='hidden' name='addPara.productPriceArr' value='${product.buyContractDetailPrice}'/>

          <td nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18">
			${index+1}
		  </td>
          <td nowrap="nowrap" >${product.productCode}&nbsp;</td>
          <td nowrap="nowrap" >${product.productName}&nbsp;</td>
          <td nowrap="nowrap" >${product.productType}&nbsp;</td>
          <td nowrap="nowrap" >${product.productUnit}&nbsp;</td>

          <td nowrap="nowrap" style=" text-align:right; ">${product.receiveGoodsDetailCount}&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${product.buyContractDetailPrice}" pattern="#,##0.00000#"/>&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${product.money}" pattern="#,##0.00000#"/>&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; ">${product.inStockCount}&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; ">${product.count}&nbsp;</td>

          <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${product.count*product.buyContractDetailPrice}" pattern="#,##0.00000#"/>&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; ">${product.buyBackGoodsCount}&nbsp;</td>
        </tr>
		</logic:iterate>
        <tr>
          <td colspan="3" nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
          <td colspan="7" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"> 入库金额合计 </td>
          <td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"><fmt:formatNumber value="${inStockInfo.money}" pattern="#,##0.00000#"/></td>
          <td nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF">元</td>
        </tr>
      </table>
      <br/>
    <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%">特殊说明</td>
          <td class="td_04" style="padding:5px 100px 5px 0">${tf:replaceBr(inStockInfo.text)}&nbsp;</td>
        </tr>
    </table>
    <br />
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
            <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
              <tr>
                <td height="20px" colspan="2" >产品总监评审意见：</td>
              </tr>
              <tr>
                <td width="320" nowrap="nowrap">&nbsp;</td>
                <td height="20px" width="150" nowrap="nowrap">
					<input type="checkbox" name="checkbox" id="pro_maj1" />符合&nbsp;&nbsp;
                    <input type="checkbox" name="checkbox" id="pro_maj2" />不符合
				</td>
              </tr>
              <tr>
                <td colspan="2" valign="top">
                	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>补充说明：</td>
                          <td style="padding:5px 0 5px 0;">
							<textarea name="auditPara.proMajText" id="textarea" cols="60" rows="3"></textarea>
						  </td>
                        </tr>
                	</table>
				</td>
              </tr>
              <tr>
                <td height="20px">签字：</td>
                <td>日期：</td>
              </tr>
              <tr>
                <td height="20px" colspan="2">&nbsp;</td>
              </tr>
              <tr>
                <td height="20px" colspan="2">库房管理员核对意见：</td>
              </tr>
              <tr>
                <td>1.产品规格是否符合</td>
                <td height="20px">
				  <input type="checkbox" disabled="true"/>符合&nbsp;&nbsp;
                  <input type="checkbox" name="checkbox7" disabled="true"/>不符合
				</td>
              </tr>
              <tr>
                <td>2.产品质量是否符合</td>
                <td height="20px">
				  <input type="checkbox" disabled="true"/>符合&nbsp;&nbsp;
                  <input type="checkbox" name="checkbox6" disabled="true"/>不符合
				</td>
              </tr>
              <tr>
                <td>3.产品数量是否符合</td>
                <td height="20px">
				  <input type="checkbox" disabled="true"/>符合&nbsp;&nbsp;
                  <input type="checkbox" disabled="true"/>不符合
				</td>
              </tr>
              <tr>
                <td colspan="2" valign="top">
				<table width="98%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="60px" valign="top" style="padding:5px 0 5px 0;">补充说明：</td>
                      <td style="padding:5px 0 5px 0;">${tf:replaceBr(inStockInfo.stkAdmText)}</td>
                    </tr>
                </table>
				</td>
              </tr>
              <tr>
                <td height="20px">签字：</td>
                <td>日期：</td>
              </tr>
            </table>
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
     <td height="50px" align="center" valign="bottom">
    	<img src="${ctx}/images/btnTG.gif" width="65" height="20" id="btnTG"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		<img src="${ctx}/images/btnWTG.gif" width="65" height="20" id="btnWTG"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<a href="${ctx}/instockList.do?queryPara.init=true"><img src="${ctx}/images/btnBack.gif" /></a>
    </td>
    <td></td>
  </tr>
</table>
<br />
</html:form>
</body>
</html>

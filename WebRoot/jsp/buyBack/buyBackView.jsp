<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购退款查看</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.STYLE1 {
	color: #FF0000
}
-->
</style>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 采购管理 &gt;&gt; 采购退款管理 &gt;&gt; 采购退款查看</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center"><br/>
      <div class="div_tiao"> &nbsp;&nbsp;付款信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18">付款单号</td>
          <td class="td_02" width="30%">${paymentInfoDto.id}&nbsp;</td>
          <td class="td_01" width="20%">付款金额</td>
          <td class="td_02" width="30%"><fmt:formatNumber value="${paymentInfoDto.paymentMoney}" pattern="#,##0.00"/>&nbsp;元</td>
        </tr>
        <tr>
          <td class="td_01" height="18">产品分类名称</td>
          <td class="td_02">${paymentInfoDto.productTypeName}&nbsp;</td>
          <td class="td_01">供货商名称</td>
          <td class="td_02">${paymentInfoDto.supplierName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18">已指定金额</td>
          <td class="td_02"><fmt:formatNumber value="${paymentInfoDto.alreadyAppointMoney}" pattern="#,##0.00"/>&nbsp;元</td>
          <td class="td_01">已退款金额</td>
          <td class="td_02"><fmt:formatNumber value="${paymentInfoDto.alreadyBackMoney}" pattern="#,##0.00"/>&nbsp;元</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;退款信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18">退款日期</td>
          <td class="td_02" width="30%">${buyBackViewDto.backDate}&nbsp;</td>
          <td class="td_01" width="20%">退款编号</td>
          <td class="td_02" width="30%">${buyBackViewDto.no}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18">退款方式</td>
          <td class="td_02">
                    <logic:equal value="1" property="backWay" name="buyBackViewDto">
            		            现金
            	    </logic:equal>
				    <logic:equal value="2" property="backWay" name="buyBackViewDto">
            		            支票
            	    </logic:equal>
            	    <logic:equal value="3" property="backWay" name="buyBackViewDto">
            		           网银
            	    </logic:equal>
            	    <logic:equal value="4" property="backWay" name="buyBackViewDto">
            		           电汇
            	    </logic:equal>
            	    <logic:equal value="5" property="backWay" name="buyBackViewDto">
            		           银行承兑
            	    </logic:equal> 
            	    <logic:equal value="7" property="backWay" name="buyBackViewDto">
            		           其它
            	    </logic:equal>
          &nbsp;</td>
          <td class="td_01">凭证号</td>
          <td class="td_02">${buyBackViewDto.number}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18">可退款金额</td>
          <td class="td_02"><fmt:formatNumber value="${paymentInfoDto.canBackMoney}" pattern="#,##0.00"/>&nbsp;元</td>
          <td class="td_01">退款金额</td>
          <td class="td_02"><fmt:formatNumber value="${buyBackViewDto.money}" pattern="#,##0.00"/>&nbsp;元</td>
        </tr>
        <tr>
          <td class="td_01" height="18">特殊说明</td>
          <td colspan="3" class="td_02">
          ${tf:replaceBr(buyBackViewDto.text)}&nbsp;
          </td>
        </tr>
      </table></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td height="50px" align="center" valign="bottom">
    <a href="${ctx}/getBuyBackList.do">
    <img src="${ctx}/images/btnBack.gif" width="65" height="20" />
    </a>
    </td>
    <td >&nbsp;</td>
  </tr>
</table>
<br />
</body>
</html>

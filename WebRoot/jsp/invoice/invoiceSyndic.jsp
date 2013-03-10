<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>开票评审</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt 开票管理 &gt;&gt; 开票评审</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;开票申请信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01">客户名称</td>
            <td class="td_02" >&nbsp;</td>
            <td class="td_01">税号</td>
            <td class="td_02" >&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01">开票银行名称</td>
            <td class="td_02">&nbsp;</td>
            <td class="td_01">开票银行帐号</td>
            <td class="td_02">&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01">开票银行地址</td>
            <td class="td_02">&nbsp;</td>
            <td class="td_01">开票银行电话</td>
            <td class="td_02">&nbsp;</td>
          </tr>
          <tr>
          	<td class="td_01">发票类型</td>
          	<td class="td_02">&nbsp;</td>
          	<td class="td_01">申请日期</td>
          	<td class="td_02">&nbsp;</td>
          	</tr>
          <tr>
            <td class="td_01">申请人</td>
            <td class="td_02">&nbsp;</td>
            <td class="td_01">&nbsp;</td>
            <td class="td_02">&nbsp;</td>
          </tr>
      </table>
   <br/>
	 <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
              <td height="23" class="td_03" width="50%">产品分类名称</td>
                <td class="td_04">xcv</td>
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
				<th nowrap="nowrap">发货数</th>
				<th nowrap="nowrap">备货数</th>
				<th nowrap="nowrap">指定金额</th>
				<th nowrap="nowrap">未开票数</th>
				<th nowrap="nowrap">未开票金额</th>
				<th nowrap="nowrap">开票数</th>
				<th nowrap="nowrap">开票金额</th>
				<th nowrap="nowrap">退货金额</th>
			</tr>
			<tr>
				<td nowrap="nowrap" style="border-left:1px solid #c2c2c2;">1</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">&nbsp;</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">000.000.000.00</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">000.000.000.00</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">000.000.000.00</td>
				<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">000.000.000.00</td>
			</tr>
			<tr>
				<td nowrap="nowrap" style="border-left:1px solid #c2c2c2;">2</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">&nbsp;</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">000.000.000.00</td>
				<td nowrap="nowrap" >&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">000.000.000.00</td>
				<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">000.000.000.00</td>
				<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">000.000.000.00</td>
			</tr>
			<tr>
				<td colspan="3" style=" border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
				<td colspan="12" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"> 开票金额合计 </td>
				<td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">123650</td>
				<td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF"><span style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">元</span></td>
			</tr>
		</table>
   	  <br/>
			<div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="20%">特殊说明</td>
					<td class="td_04" ><textarea name="textarea" id="textarea" cols="100" rows="4"></textarea>
					</td>
				</tr>
			</table>
    <br />
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
            <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
                <tr>
                  <td height="20px" colspan="2" >销售总监评审意见：</td>
                </tr>
                <tr>
                  <td width="320">&nbsp;</td>
                <td height="20px" width="150"><input type="checkbox" name="checkbox" id="checkbox" />
符合
                    &nbsp;&nbsp;
                    <input type="checkbox" name="checkbox" id="checkbox" />
                  不符合 </td></tr>
                <tr>
                  <td colspan="2" valign="top">
                  	<table width="98%" border="0" cellpadding="0" cellspacing="0">
                    	<tr>
                       	  <td width="60px" valign="top" style="padding:5px 0 5px 0;">补充说明：</td>
                        	<td style="padding:5px 0 5px 0;"><textarea name="textarea2" id="textarea2" cols="60" rows="3"></textarea></td>
                        </tr>
                    </table>                  </td>
                </tr>
                <tr>
                  <td height="20px">签字：</td>
                  <td>日期：</td>
                </tr>
                <tr>
                  <td height="20px" colspan="2">&nbsp;</td>
                </tr>
                <tr>
                  <td height="20px" colspan="2">运营总监评审意见：</td>
              </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td height="20px"><input type="checkbox" name="checkbox4" id="checkbox4" />
符合
                    &nbsp;&nbsp;
                    <input type="checkbox" name="checkbox4" id="checkbox4" />
不符合</td>
              	</tr>
                  <tr>
                      <td colspan="2" valign="top">
                        <table width="98%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="60px" valign="top" style="padding:5px 0 5px 0;">补充说明：</td>
                                <td style="padding:5px 0 5px 0;"></td>
                            </tr>
                        </table>                  </td>
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
    <img src="${ctx}/images/btnTG.gif" width="65" height="20" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="${ctx}/images/btnWTG.gif" width="65" height="20" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="${ctx}/images/btnBack.gif" /></td>
    <td></td>
  </tr>
</table>
</body>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购退款管理</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script> 
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<style type="text/css">
<!--
.treven {
	background-color: #f3fbff;
}
.over {
	background-color: #ECECEC;
}
.rowselected {
  			background-color: #868686;
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
						//点击换色
						$(this).click( function(){
			              if( $(this).hasClass("rowselected") ){
			                $(this).removeClass("rowselected");
			              }else{
			                $(this).addClass("rowselected");
			              }
			            });
						//点击换色
					});
				}
			});
		//-->
		</script>
<script type="text/javascript">

    $(document).ready(function(){
	    var msg = "${msg}";  //获取服务端信息
		if(msg!=""){
			alert(msg);
		}
	 });
	
	function confimDel(){
	  if(confirm('是否删除该采购退款？')==false)return false;
	  return true;  
	 }
	 
	function form0submit() {
	   $("#searchBuyBack").submit();
	}
	
</script>
</head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 采购管理 &gt;&gt; 采购退款管理</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    	<div class="mo_wp">
          <div style="display: ; " class="mo_con" >
          
          <form action="searchBuyBack.do" id="searchBuyBack" >
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                <td class="td_01" width="20%">退款单号</td>
                <td class="td_02" width="30%"><input type="text" name="searchBuyBack.id" id="searchBuyBack.id" style="width:120px;" value="${buyBackSearchDto.id}"/></td>
                <td class="td_01" width="20%">付款单号</td>
                <td class="td_02" width="30%"><input type="text" name="searchBuyBack.paymentId" id="searchBuyBack.paymentId" style="width:120px;" value="${buyBackSearchDto.paymentId}"/></td>
              </tr>
              <tr>
                <td class="td_01">退款编号</td>
                <td class="td_02"><input type="text" name="searchBuyBack.no" id="searchBuyBack.no" style="width:120px;" value="${buyBackSearchDto.no}"/></td>
                 <td class="td_01">产品分类名称</td>
             	 <td class="td_02">
              	<html:select property="searchBuyBack.productTypeId" value="${buyBackSearchDto.productTypeId}" style=" width:126px">
							<html:option value="">--请选择--</html:option>
							<logic:iterate id="productEntity" name="productTypeList" >
							   <html:option value="${productEntity.id}"> ${productEntity.name}</html:option> 
							</logic:iterate>
						 
				</html:select>
                
              </td>
              </tr>
              <tr>
                <td class="td_01">供货商名称</td>
                <td class="td_02"><input type="text" name="searchBuyBack.supplierName" id="searchBuyBack.supplierName" style="width:120px;" value="${buyBackSearchDto.supplierName}"/>
                	</td>
                <td class="td_01">退款金额</td>
                <td class="td_02"><input type="text" name="searchBuyBack.money" id="searchBuyBack.money" style="width:120px;" value="${buyBackSearchDto.money}"/>
                	元</td>
              </tr>
              <tr>
              	<td class="td_01">录入起始日期</td>
              	<td class="td_02"><input type="text" onfocus="calendar()" name="searchBuyBack.dateStart" id="searchBuyBack.dateStart" style="width:120px;" value="${buyBackSearchDto.dateStart}"/></td>
              	<td class="td_01">录入终止日期</td>
              	<td class="td_02"><input type="text" onfocus="calendar()" name="searchBuyBack.dateEnd" id="searchBuyBack.dateEnd" style="width:120px;" value="${buyBackSearchDto.dateEnd}"/></td>
              	</tr>
              <tr>
              	<td class="td_01">退款起始日期</td>
              	<td class="td_02"><input type="text" onfocus="calendar()" name="searchBuyBack.backDateStart" id="searchBuyBack.backDateStart" style="width:120px;" value="${buyBackSearchDto.backDateStart}"/></td>
              	<td class="td_01">退款终止日期</td>
              	<td class="td_02"><input type="text" onfocus="calendar()" name="searchBuyBack.backDateEnd" id="searchBuyBack.backDateEnd" style="width:120px;" value="${buyBackSearchDto.backDateEnd}"/></td>
              	</tr>
              <tr>
              	<td class="td_01">退款方式</td>
              	<td class="td_02">
              	<html:select property="searchBuyBack.backWay" style=" width:126px" value="${buyBackSearchDto.backWay}" >
							<html:option value="">--请选择--</html:option> 							
							<html:option value="1">现金</html:option>
							<html:option value="2">支票</html:option>
							<html:option value="3">网银</html:option>
							<html:option value="4">电汇</html:option>
							<html:option value="5">银行承兑</html:option> 
							<html:option value="7">其它</html:option>
				</html:select> 
              	
              	</td>
              	<td class="td_01">凭证号</td>
              	<td class="td_02"><input type="text" name="searchBuyBack.number" id="searchBuyBack.number" style="width:120px;" value="${buyBackSearchDto.number}"/></td>
              	</tr>
              <tr>
                <td class="td_01">录入人</td>
                <td class="td_02"><input type="text" name="searchBuyBack.userName" id="searchBuyBack.userName" style="width:120px;" value="${buyBackSearchDto.userName}"/> </td>
                <td class="td_01">&nbsp;</td>
                <td class="td_02">&nbsp;</td>
              </tr>
              <tr>
                <td colspan="4" align="center" style="height:30px;"><img src="${ctx}/images/btn_JianSuo.gif" onclick="form0submit()"/></td>
              </tr>
            </table>
            </form>
          </div>
          <div class="mo_title" onclick="fMainListToggle(this)">
              <div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
          </div>
        </div>
    </td>
    <td>&nbsp;</td>
  </tr>

  <tr>
    <td >&nbsp;&nbsp;</td>
    <td align="center">
    <div style="width:100%; text-align:right">单位：元</div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
      <tr>
        <th nowrap="nowrap">退款单号</th>
        <th nowrap="nowrap">付款单号</th>
        <th nowrap="nowrap">退款编号</th>
        <th nowrap="nowrap">产品分类名称</th>
        <th nowrap="nowrap">供货商名称 </th>
        <th nowrap="nowrap">录入日期 </th>
        <th nowrap="nowrap">退款日期</th>
        <th nowrap="nowrap" >&nbsp;&nbsp;&nbsp;退款金额&nbsp;&nbsp;&nbsp;</th>
        <th nowrap="nowrap" >退款方式</th>
        <th nowrap="nowrap" >凭证号</th>
        <th nowrap="nowrap" >录入人</th>
        <th nowrap="nowrap">&nbsp;操作&nbsp;</th>
      </tr>
       <logic:iterate id="buyBackDto" name="buyBackList" >
			<tr>
				<td nowrap="nowrap" width="100px" height="18">${buyBackDto.id}&nbsp;</td>
				<td width="120px" nowrap="nowrap">${buyBackDto.paymentId}&nbsp;</td>
				<td width="120px" nowrap="nowrap" >${buyBackDto.no}&nbsp;</td>
				<td width="84px" nowrap="nowrap">${buyBackDto.productTypeName}&nbsp;</td>				
				<td width="73px" nowrap="nowrap"  title="${buyBackDto.supplierName}"><div class="ellipsis_div" style="width:240px;">${buyBackDto.supplierName}&nbsp;</div></td>
				<td width="72px" nowrap="nowrap" >${buyBackDto.date}&nbsp;</td>
				<td width="73px" nowrap="nowrap" >${buyBackDto.backDate}&nbsp;</td>
				<td width="73px" nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${buyBackDto.money}" pattern="#,##0.00"/>&nbsp;</td>
				<td width="60px" nowrap="nowrap" >  				
				  <logic:equal value="1" name="buyBackDto" property="backWay">现金</logic:equal> 
				  <logic:equal value="2" name="buyBackDto" property="backWay">支票</logic:equal>
				  <logic:equal value="3" name="buyBackDto" property="backWay">网银</logic:equal>
				  <logic:equal value="4" name="buyBackDto" property="backWay">电汇</logic:equal>
				  <logic:equal value="5" name="buyBackDto" property="backWay">银行承兑</logic:equal>
				  <logic:equal value="7" name="buyBackDto" property="backWay">其它</logic:equal>
				  &nbsp;
				</td>
				<td width="120px" nowrap="nowrap"><div style="width:120px;">${buyBackDto.number}&nbsp;</div></td>
				<td width="72px" nowrap="nowrap" >${buyBackDto.userName}&nbsp;</td>
				<td nowrap="nowrap">
					<a href="getBuyBackView.do?buyBackId=${buyBackDto.id}">查看</a>							  
					<!-- 采购专员  -->
					<c:if test="${USERLOGIN.roleId==8}">				
						 	 <a href="getPaymentInfoForUpdate.do?id=${buyBackDto.id}">修改</a>												
						 	 <a href="removeBuyBack.do?buyBackId=${buyBackDto.id}" onclick="return confimDel()">删除</a>									
					</c:if>											
				</td>
			</tr>
		</logic:iterate>
    </table>
  <br />
        <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        	<tr>
            	<td align="right" ><%@ include file="/jsp/common/newPage.jsp"%></td>
          </tr>
        </table>
    </td>
    <td >&nbsp;</td>
  </tr>
</table>
 
</body>
</html>

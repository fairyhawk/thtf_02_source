<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售退款管理</title>
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
	 	 
	 function confimSubmit(){
	  if(confirm('是否确认该销售退款？')==false)return false;
	  return true;  
	 }
	 function confimDel(){
	  if(confirm('是否删除该销售退款？')==false)return false;
	  return true;  
	 }
	
	 
	function form0submit() {
	   $("#searchSellBack").submit();
	}
	
</script>
</head>
<body>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt 销售退款管理</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
      <div class="mo_wp">
        <div style="display: ; " class="mo_con" >
        
       <form action="searchSellBack.do" id="searchSellBack" >
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
              <td class="td_01" width="20%">退款申请单号</td>
              <td class="td_02" width="30%"><input type="text" name="searchSellBack.id" id="name7" style="width:120px;" value="${sellBackSearchDto.id}" /></td>
              <td class="td_01" width="20%">回款流水号</td>
              <td class="td_02" width="30%"><input type="text" name="searchSellBack.returnId" id="name6" style="width:120px;" value="${sellBackSearchDto.returnId}"/></td>
            </tr>
            <tr>
              <td class="td_01">产品分类名称</td>
              <td class="td_02">
              	<html:select property="searchSellBack.productTypeId" value="${sellBackSearchDto.productTypeId}" style=" width:126px">
							<html:option value="">--请选择--</html:option>
							<logic:iterate id="productEntity" name="productEntitieList" >
							   <html:option value="${productEntity.id}"> ${productEntity.name}</html:option> 
							</logic:iterate>
						 
				</html:select>
                
              </td>
              <td class="td_01">客户名称</td>
              <td class="td_02"><input type="text" name="searchSellBack.customerName" id="name8" style="width:120px;" value="${sellBackSearchDto.customerName}"/></td>
            </tr>
            <tr>
              <td class="td_01">申请起始日期</td>
              <td class="td_02"><input type="text" onfocus="calendar()" name="searchSellBack.dateStart" id="name4" style="width:120px;" value="${sellBackSearchDto.dateStart}" /></td>
              <td class="td_01">申请终止日期</td>
              <td class="td_02"><input type="text" onfocus="calendar()"  name="searchSellBack.dateEnd" id="name6" style="width:120px;" value="${sellBackSearchDto.dateEnd}" /></td>
            </tr>
            <tr>
              <td class="td_01">退款起始日期</td>
              <td class="td_02"><input type="text" onfocus="calendar()" name="searchSellBack.backDateStart" id="name9" style="width:120px;" value="${sellBackSearchDto.backDateStart}" /></td>
              <td class="td_01">退款终止日期</td>
              <td class="td_02"><input type="text" onfocus="calendar()" name="searchSellBack.backDateEnd" id="name3" style="width:120px;" value="${sellBackSearchDto.backDateEnd}" /></td>
            </tr>
            <tr>
              <td class="td_01">退款方式</td>
              <td class="td_02">
              	<html:select property="searchSellBack.backWay" style=" width:126px" value="${sellBackSearchDto.backWay}" >
							<html:option value="">--请选择--</html:option> 							
							
			            	<html:option value="2">支票</html:option>
						    <html:option value="3">网银</html:option>
						    <html:option value="4">电汇</html:option>
						    <html:option value="5">银行承兑</html:option>
						    <html:option value="7">其它</html:option>
						</html:select> 
                
                </td>
              <td class="td_01">凭证号码</td>
              <td class="td_02"><input type="text" name="searchSellBack.number" id="name" style="width:120px;" value="${sellBackSearchDto.number}"/></td>
            </tr>
            <tr>
              <td class="td_01">申请人</td>
              <td class="td_02"><input type="text" name="searchSellBack.userName" id="name10" style="width:120px;" value="${sellBackSearchDto.userName}"/></td>
              <td class="td_01">确认人</td>
              <td class="td_02"><input type="text" name="searchSellBack.confirmName" id="name5" style="width:120px;" value="${sellBackSearchDto.confirmName}"/></td>
            </tr>
            <tr>
              <td class="td_01">退款状态</td>
              <td class="td_02">
              	<html:select property="searchSellBack.status" style=" width:126px" value="${sellBackSearchDto.status}" >
							<html:option value="">--请选择--</html:option> 							
							<html:option value="1">未提交</html:option>
							<html:option value="2">销售总监待评审</html:option>
							<html:option value="3">销售总监未通过</html:option>
							<html:option value="4">运营总监待评审</html:option>
							<html:option value="5">运营总监未通过</html:option>
							<html:option value="6">待打印</html:option>
							<html:option value="7">待确认</html:option>
							<html:option value="8">退款成功</html:option>
						</html:select> 
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
      </div></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center">
      <div style="width:100%; text-align:right; padding-top:0px">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
        <tr>
          <th nowrap="nowrap">退款申请单号</th>
          <th nowrap="nowrap">回款流水号</th>
          <th nowrap="nowrap">&nbsp;产品分类名称&nbsp;</th>
          <th nowrap="nowrap" >客户名称</th>
          <th nowrap="nowrap">申请日期</th>
          <th nowrap="nowrap">退款日期</th>
          <th nowrap="nowrap">退款金额</th>
          <th nowrap="nowrap">&nbsp;退款方式&nbsp;</th>
          <th nowrap="nowrap">凭证号码</th>
          <th nowrap="nowrap">申请人</th>
          <th nowrap="nowrap">确认人</th>
          <th nowrap="nowrap">退款状态</th>
          <th nowrap="nowrap" >&nbsp;操作&nbsp;</th>
        </tr>
        <logic:iterate id="sellBackDto" name="sellBackList" >
			<tr>
				<td nowrap="nowrap" width="100px" height="18">${sellBackDto.id}&nbsp;</td>
				<td width="120px" nowrap="nowrap">${sellBackDto.returnId}&nbsp;</td>
				<td width="84px" nowrap="nowrap">${sellBackDto.productTypeName}&nbsp;</td>
				<td width="120px" nowrap="nowrap" title="${sellBackDto.customerName}"><div class=ellipsis_div style="width:120px;">${sellBackDto.customerName}&nbsp;</div></td>
				<td width="73px" nowrap="nowrap" >${sellBackDto.date}&nbsp;</td>
				<td width="73px" nowrap="nowrap" >${sellBackDto.backDate}&nbsp;</td>
				<td width="90px" nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${sellBackDto.money}" pattern="#,##0.00"/>&nbsp;</td>
				<td width="60px" nowrap="nowrap" >  				
				  <logic:equal value="7" name="sellBackDto" property="backWay">其他</logic:equal> 
				  <logic:equal value="2" name="sellBackDto" property="backWay">支票</logic:equal>
				  <logic:equal value="3" name="sellBackDto" property="backWay">网银</logic:equal>
				  <logic:equal value="4" name="sellBackDto" property="backWay">电汇</logic:equal>
				  <logic:equal value="5" name="sellBackDto" property="backWay">银行承兑</logic:equal>
				  
				  &nbsp;
				</td>
				<td width="120px" nowrap="nowrap">${sellBackDto.number}&nbsp;</td>
				<td width="72px" nowrap="nowrap" >${sellBackDto.userName}&nbsp;</td>
				<td width="72px" nowrap="nowrap" >${sellBackDto.confirmName}&nbsp;</td>
				<td width="100px" nowrap="nowrap" ><div class=ellipsis_div style="width:95px;">
				  <c:if test="${sellBackDto.status==1}">未提交</c:if>
				  <c:if test="${sellBackDto.status==2}">销售总监待评审</c:if>
				  <c:if test="${sellBackDto.status==3}">销售总监未通过</c:if>
				  <c:if test="${sellBackDto.status==4}">运营总监待评审</c:if>
				  <c:if test="${sellBackDto.status==5}">运营总监未通过</c:if>
				  <c:if test="${sellBackDto.status==6}">待打印</c:if>
				  <c:if test="${sellBackDto.status==7}">待确认</c:if>
				  <c:if test="${sellBackDto.status==8}">退款成功</c:if></div>
				 </td>
				<td nowrap="nowrap">
					<a href="getInfoBySellBackId.do?sellBackId=${sellBackDto.id}&type=view">查看</a>					
					<!-- 销售总监  -->
					<c:if test="${USERLOGIN.roleId==5}">  
						<c:if test="${sellBackDto.status==2}">
						  <a href="getShowSellBackAssess.do?sellBackId=${sellBackDto.id}">评审</a>
						</c:if>
						<c:if test="${sellBackDto.status!=2}">
						  评审
						</c:if> 
					 </c:if> 
					<!-- 运营总监  -->
					<c:if test="${USERLOGIN.roleId==17}">  
					    <c:if test="${sellBackDto.status==4}">
						  <a href="getShowSellBackAssess.do?sellBackId=${sellBackDto.id}">评审</a>
						</c:if>
						<c:if test="${sellBackDto.status!=4}">
						    评审
						</c:if> 
					</c:if>				  
					<!-- 销售助理  -->
					<c:if test="${USERLOGIN.roleId==3}">
					    <c:if test="${sellBackDto.status==7}">
						  <a href="confirmSellBack.do?sellBackId=${sellBackDto.id}" onclick=" return confimSubmit()">确认</a>
						</c:if>
						<c:if test="${sellBackDto.status!=7}">
						    确认
						</c:if> 
						<c:choose>
							<c:when test="${sellBackDto.status==1||sellBackDto.status==3||sellBackDto.status==5}">
						 	 <a href="getInfoBySellBackId.do?sellBackId=${sellBackDto.id}&type=modify">修改</a>
							</c:when>
							<c:otherwise>
						   		 修改
							</c:otherwise> 
						</c:choose>	
						<c:choose>
							<c:when test="${sellBackDto.status==1||sellBackDto.status==3||sellBackDto.status==5}">
						 	 <a href="deleteSellBack.do?sellBackId=${sellBackDto.id}" onclick=" return confimDel()">删除</a>
							</c:when>
							<c:otherwise>
						   		 删除
							</c:otherwise> 
						</c:choose>	
						<c:choose>
							<c:when test="${sellBackDto.status==6||sellBackDto.status==7||sellBackDto.status==8}">
						 	 <a href="javascript:void(0)" onclick="javascript:window.open('getPrintInfo.do?sellBackId=${sellBackDto.id}','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=400')">打印</a>
							</c:when>
							<c:otherwise>
						   		打印
							</c:otherwise> 
						</c:choose>						
					</c:if>	
					
					<!-- 其他权限只能查看  --> 
										
				</td>
			</tr>
		</logic:iterate>
      </table>
      <br />
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        <tr>
            	<td align="right" ><%@ include file="/jsp/common/newPage.jsp"%></td>
          </tr>
      </table></td>
    <td >&nbsp;</td>
  </tr>
</table>
</body>
</html>

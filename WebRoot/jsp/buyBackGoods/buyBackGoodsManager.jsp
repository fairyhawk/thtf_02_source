<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购返厂管理</title>
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
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript">

	  $(document).ready(function(){
	      var msg = "${msg}";  //获取服务端信息
		if(msg!=""){
		alert(msg);
		}
	 });
	 
	function form0submit() {
	   $("#searchBuyBackGoods").submit();
	}
		function confimDel(){
	  if(confirm('确认删除该条返厂信息？')==false)return false;
	  return true;  
	 }
	
</script>
</head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 采购管理 &gt;&gt; 采购返厂管理</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    	<div class="mo_wp">
          <div style="display: ; " class="mo_con" >
          
          <form action="searchBuyBackGoods.do" id="searchBuyBackGoods" >
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                <td class="td_01" width="20%">返厂单号</td>
                <td class="td_02" width="30%"><input type="text" name="searchBuyBackGoods.id" value="${buyBackGoodsSearchDto.id}"  id="name" style="width:120px;" /></td>
                <td class="td_01" width="20%">库房名称</td>
                <td class="td_02" width="30%">
                <html:select property="searchBuyBackGoods.stockroomId" value="${buyBackGoodsSearchDto.stockroomId}" >
							<html:option value="">--请选择--</html:option>
							<logic:iterate id="stockroomEntity" name="stockroomEntitiesList" >
							   <html:option value="${stockroomEntity.id}"> ${stockroomEntity.name}</html:option> 
							</logic:iterate>						 
				</html:select>  
                </td>
              </tr>
              <tr>
                <td class="td_01">产品合同号</td>
                <td class="td_02"><input type="text" name="searchBuyBackGoods.productContractCode" value="${buyBackGoodsSearchDto.productContractCode}" id="name2" style="width:120px;" /></td>
                <td class="td_01">公司合同号</td>
                <td class="td_02"><input type="text" name="searchBuyBackGoods.companyContractCode" id="name7" value="${buyBackGoodsSearchDto.companyContractCode}" style="width:120px;" /></td>
              </tr>
              <tr>
                <td class="td_01">产品分类名称</td>
                <td class="td_02">
                <html:select property="searchBuyBackGoods.productTypeId" value="${buyBackGoodsSearchDto.productTypeId}" style=" width:126px">
							<html:option value="">--请选择--</html:option>
							<logic:iterate id="productEntity" name="productEntitiesList" >
							   <html:option value="${productEntity.id}"> ${productEntity.name}</html:option> 
							</logic:iterate>						 
				</html:select>                
                </td>
                <td class="td_01">供货商名称</td>
                <td class="td_02"><input type="text" name="searchBuyBackGoods.supplierName" id="name3" style="width:120px;" value="${buyBackGoodsSearchDto.supplierName}"/></td>
              </tr>
              <tr>
                <td class="td_01">人员名称</td>
                <td class="td_02"><input type="text" name="searchBuyBackGoods.userName" id="name4" style="width:120px;" value="${buyBackGoodsSearchDto.userName}"/></td>
                <td class="td_01">返厂单状态</td>
                <td class="td_02">
                
                <html:select property="searchBuyBackGoods.status"  value="${buyBackGoodsSearchDto.status}"   style=" width:126px">
                  <html:option value="">--请选择--</html:option>
                   <html:option value="1">未提交</html:option> 
                   <html:option value="2">产品总监待评审</html:option> 
                   <html:option value="3">产品总监未通过</html:option> 
                   <html:option value="4">采购主管待评审</html:option> 
                   <html:option value="5">采购主管未通过</html:option> 
                   <html:option value="6">运营总监待评审</html:option> 
                   <html:option value="7">运营总监未通过</html:option> 
                   <html:option value="8">待发货</html:option> 
                   <html:option value="9">发货中</html:option> 
                   <html:option value="10">发货异常</html:option> 
                   <html:option value="11">发货成功</html:option> 
                </html:select></td>
              </tr>
              <tr>
                <td colspan="4" align="center" style="height:30px;"><img src="${ctx}/images/btn_JianSuo.gif"  onclick="form0submit()"/></td>
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
        <th nowrap="nowrap">返厂单号</th>
        <th nowrap="nowrap">库房名称</th>
        <th nowrap="nowrap">产品合同号</th>
        <th nowrap="nowrap">公司合同号</th>
        <th nowrap="nowrap">产品分类名称 </th>
        <th nowrap="nowrap">供货商名称 </th>
        <th nowrap="nowrap">返厂金额</th>
        <th nowrap="nowrap" >人员名称</th>
        <th nowrap="nowrap" >申请日期</th>
        <th nowrap="nowrap" >返厂日期</th>
        <th nowrap="nowrap" >返厂单状态</th>
        <th nowrap="nowrap">&nbsp;操作&nbsp;</th>
      </tr>
   
      <logic:iterate id="buyBackGoodsDto" name="buyBackGoodsList" >
			<tr>
				<td nowrap="nowrap" width="100px" height="18">${buyBackGoodsDto.id}&nbsp;</td>
				<td  nowrap="nowrap" title="${buyBackGoodsDto.stockroomName}"><div class="ellipsis_div" style="width:120px;">${buyBackGoodsDto.stockroomName}&nbsp;</div></td>
				<td width="112px" nowrap="nowrap">${buyBackGoodsDto.productContractCode}&nbsp;</td>
				<td width="120px" nowrap="nowrap">${buyBackGoodsDto.companyContractCode}&nbsp;</td>
				<td width="84px" nowrap="nowrap">${buyBackGoodsDto.productTypeName}&nbsp;</td>
				<td  nowrap="nowrap" title="${buyBackGoodsDto.supplierName}"><div class="ellipsis_div" style="width:180px;">${buyBackGoodsDto.supplierName}&nbsp;</div></td>
				<td width="90px" nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${buyBackGoodsDto.money}" pattern="#,##0.00000"/>&nbsp;</td>
				<td width="73px" nowrap="nowrap" >${buyBackGoodsDto.userName}&nbsp;</td>
				<td width="73px" nowrap="nowrap" >${buyBackGoodsDto.date}&nbsp;</td>
				<td width="73px" nowrap="nowrap" >${buyBackGoodsDto.sendDate}&nbsp;</td>
				<td width="100px" nowrap="nowrap" ><div class=ellipsis_div style="width:95px;">
				  <c:if test="${buyBackGoodsDto.status==1}">未提交</c:if>
				  <c:if test="${buyBackGoodsDto.status==2}">产品总监待评审</c:if>
				  <c:if test="${buyBackGoodsDto.status==3}">产品总监未通过</c:if>
				  <c:if test="${buyBackGoodsDto.status==4}">采购主管待评审</c:if>
				  <c:if test="${buyBackGoodsDto.status==5}">采购主管未通过</c:if>
				  <c:if test="${buyBackGoodsDto.status==6}">运营总监待评审</c:if>
				  <c:if test="${buyBackGoodsDto.status==7}">运营总监未通过</c:if>
				  <c:if test="${buyBackGoodsDto.status==8}">待发货</c:if>
				  <c:if test="${buyBackGoodsDto.status==9}">发货中</c:if>
				  <c:if test="${buyBackGoodsDto.status==10}">发货异常</c:if>
				  <c:if test="${buyBackGoodsDto.status==11}">发货成功</c:if></div>
				 </td>
				<td  nowrap="nowrap">
					<a href="getShowBuyBackAssess.do?buyBackGoodsId=${buyBackGoodsDto.id}&type=view">查看</a>		
					<!-- 采购专员-->
					<c:if test="${USERLOGIN.roleId==8}">  
						<c:choose>
							<c:when test="${buyBackGoodsDto.status==1||buyBackGoodsDto.status==3||buyBackGoodsDto.status==5||buyBackGoodsDto.status==7||buyBackGoodsDto.status==10}">
						 	 <a href="getBuyBackGoodsModify.do?buyBackGoodsId=${buyBackGoodsDto.id}">修改</a>
						 	   <a href="deleteBuyBackGoods.do?buyBackGoodsId=${buyBackGoodsDto.id}"  onclick=" return confimDel()" >删除</a>
							</c:when>
							<c:otherwise>
						   		 修改  删除
							</c:otherwise> 
						</c:choose>	
					 </c:if> 					
								
					<!-- 产品总监  -->
					<c:if test="${USERLOGIN.roleId==10}">  
						<c:if test="${buyBackGoodsDto.status==2}">
						  <a href="getShowBuyBackAssess.do?buyBackGoodsId=${buyBackGoodsDto.id}&type=showAssess">评审</a>
						</c:if>
						<c:if test="${buyBackGoodsDto.status!=2}">
						  评审
						</c:if> 
					 </c:if> 
					 
					<!-- 采购主管  -->
					<c:if test="${USERLOGIN.roleId==11}">  
					    <c:if test="${buyBackGoodsDto.status==4}">
						  <a href="getShowBuyBackAssess.do?buyBackGoodsId=${buyBackGoodsDto.id}&type=showAssess">评审</a>
						</c:if>
						<c:if test="${buyBackGoodsDto.status!=4}">
						    评审
						</c:if> 
					</c:if>		
							  
					<!-- 运营总监  -->
					<c:if test="${USERLOGIN.roleId==17}">  
					    <c:if test="${buyBackGoodsDto.status==6}">
						  <a href="getShowBuyBackAssess.do?buyBackGoodsId=${buyBackGoodsDto.id}&type=showAssess">评审</a>
						</c:if>
						<c:if test="${buyBackGoodsDto.status!=6}">
						    评审
						</c:if> 
					</c:if>&nbsp;</td>
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

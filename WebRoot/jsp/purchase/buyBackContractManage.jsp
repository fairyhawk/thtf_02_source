<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<META HTTP-EQUIV="Expires" CONTENT="Wed,   26   Feb   1997   08:21:57   GMT"/>

<title>采购退货合同管理</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
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
						$(this).click( function(){
						  if( $(this).hasClass("rowselected") ){
							$(this).removeClass("rowselected");
						  }else{
							$(this).addClass("rowselected");
						  }
						});
					});
				}
			});
			//采购退货合同删除
			function delBuyBackContractById(buyBackId){
				if(confirm("确定要删除？")){
					if(buyBackId==null||buyBackId==""){//判断采购退货合同编号是否存在
						alert("删除失败，退货合同号不存在！");
					}else{
					location="${ctx}/delBuyBackContract.do?buyBackContractId="+buyBackId;
					}
				}
			}
			//打开评审表界面
			function selReview(buyBackContractId){
			 	window.open("${ctx}/selBuyBackContractReview.do?buyBackContractId="+buyBackContractId+"&print="+$("#print").val(),"newwindow", "toolbar=no,scrollbars=yes,resizable=yes,top=0,left=170, width=750,height=680");		
			}
		</script>
</head>
<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 采购管理 &gt;&gt; 采购退货合同管理</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
      <div class="mo_wp">
        <div style="display: ; " class="mo_con" >
        <html:form action="buyBackContractList" method="get" styleId="buyContractForm">
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
              <td class="td_01" width="20%">产品合同号</td>
              <td class="td_02" width="30%">
              	<input type="text" name="productContractCode" id="productContractCode" value="${searchForm.productContractCode}" style="width:120px;" />
              </td>
              <td class="td_01">公司合同号</td>
              <td class="td_02">
              	<input type="text" name="companyContractCode" id="companyContractCode"  value="${searchForm.companyContractCode}" style="width:120px;" />
              </td>
            </tr>
			<tr>
              <td class="td_01" width="20%">产品分类名称</td>
              <td class="td_02" width="30%">
				<html:select property="productTypeId" value="${searchForm.productTypeId}" style=" width:126px">
					<html:option value="">--请选择--</html:option> 
					<html:options collection="productTypeList" property="id" labelProperty="name"/>
				</html:select>
              </td>
              <td class="td_01">供货商名称</td>
              <td class="td_02">
              	<input type="text" name="supplierName" id="supplierName" value="${searchForm.supplierName}" style="width:240px;" />
              	</td>
            </tr>
            <tr>
              <td class="td_01">合同状态</td>
              <td class="td_02">
				<html:select property="status" value="${searchForm.status}" style=" width:126px">
					<html:option value="">--请选择--</html:option>  
					<html:option value="1">未提交</html:option>
					<html:option value="2">法务专员待评审</html:option> 
					<html:option value="3">法务专员未通过</html:option> 
					<html:option value="4">采购主管待评审</html:option>
					<html:option value="5">采购主管未通过</html:option> 
					<html:option value="6">运营总监待评审</html:option> 
					<html:option value="7">运营总监未通过</html:option> 
					<html:option value="8">待打印</html:option>
					<html:option value="9">待确认</html:option> 
					<html:option value="10">合同取消</html:option> 
					<html:option value="11">合同生效</html:option> 
				</html:select> 
              </td>
              <td class="td_01">模版类型</td>
              <td class="td_02">
				<html:select property="templateType" value="${searchForm.templateType}" style=" width:126px">
					<html:option value="">--请选择--</html:option> 
					<html:option value="0">标准模板</html:option> 
					<html:option value="1">非模板</html:option>
				</html:select>
              </td>
            </tr>
			<tr>
              <td class="td_01">合同签订起始日期</td>
              <td class="td_02"><input type="text" name="startTime" id="startTime" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${searchForm.startTime}"/></td>
              <td class="td_01">合同签订终止日期</td>
              <td class="td_02"><input type="text" name="endTime" id="endTime" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${searchForm.endTime}"/></td>
            </tr>
            <tr>
              <td class="td_01">人员名称</td>
              <td class="td_02"><input type="text" name="userName" id="userName" style="width:120px;" value="${searchForm.userName}" /></td>
              <td class="td_01">&nbsp;</td>
              <td class="td_02">&nbsp;</td>
            </tr>
            <tr>
              <td colspan="4" align="center" style="height:30px;">
              	<img src="${ctx}/images/btn_JianSuo.gif" onclick="$('#buyContractForm').submit();"/>
              </td>
            </tr>
          </table>
          </html:form>
        </div>
        <div class="mo_title" onclick="fMainListToggle(this)">
          <div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
        </div>
      </div></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;&nbsp;</td>
    <td align="center">
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
        <tr>
          <th nowrap="nowrap">合同流水号</th>
          <th nowrap="nowrap">产品分类名称</th>
          <th nowrap="nowrap">产品合同号</th>
          <th nowrap="nowrap">公司合同号</th>
          <th nowrap="nowrap" >供货商名称</th>
          <th nowrap="nowrap">退货合同金额</th>
          <th nowrap="nowrap">&nbsp;&nbsp;合同类型&nbsp;&nbsp;</th>
          <th nowrap="nowrap">&nbsp;模版类型&nbsp;</th>
          <th nowrap="nowrap">签订日期</th>
          <th nowrap="nowrap">人员名称</th>
          <th nowrap="nowrap">合同状态</th>
		  <th nowrap="nowrap">&nbsp;操作&nbsp;</th>
        </tr>

       <c:forEach var="backContract" items="${buyBackContractList}">
        <tr>
          <td nowrap="nowrap" height="18px" width="120">${backContract.buyBackContractId}&nbsp;</td>
          <td nowrap="nowrap" width="84">${backContract.prodTypeName}&nbsp;</td>
          <td nowrap="nowrap" width="120">${backContract.prodContractCode}&nbsp;</td>
          <td nowrap="nowrap" title="${backContract.compContractCode}" width="120"><div class="ellipsis_div" style="width:120px;">${backContract.compContractCode}&nbsp;</div></td>
          <td nowrap="nowrap" title="${backContract.supplierName}"><div class="ellipsis_div" style="width:120px;">${backContract.supplierName}&nbsp;</div></td>
		  <td nowrap="nowrap" style=" text-align:right;" width="93">
          	<fmt:formatNumber value="${backContract.backContractMoney}" pattern="#,##0.00000"/>&nbsp;
       	  </td>
          <td nowrap="nowrap">
          	<c:if test="${backContract.contractType==0}">国内</c:if>
			<c:if test="${backContract.contractType==1}">国外</c:if>&nbsp;
          </td>
		  <td nowrap="nowrap">
          	<c:if test="${backContract.templateType==0}">标准模板</c:if>
			<c:if test="${backContract.templateType==1}">非模板</c:if>&nbsp;
          </td>
          <td nowrap="nowrap" width="73">${backContract.signDate}&nbsp;</td>
          <td nowrap="nowrap" width="72">${backContract.userName}&nbsp;</td>
          <td nowrap="nowrap" width="108">
            <c:if test="${backContract.status==1}">未提交</c:if>
			<c:if test="${backContract.status==2}">法务专员待评审</c:if>
			<c:if test="${backContract.status==3}">法务专员未通过</c:if>
			<c:if test="${backContract.status==4}">采购主管待评审</c:if>
			<c:if test="${backContract.status==5}">采购主管未通过</c:if>
			<c:if test="${backContract.status==6}">运营总监待评审</c:if>
			<c:if test="${backContract.status==7}">运营总监未通过</c:if>
			<c:if test="${backContract.status==8}">待打印</c:if>
			<c:if test="${backContract.status==9}">待确认</c:if>
			<c:if test="${backContract.status==10}">合同取消</c:if>
			<c:if test="${backContract.status==11}">合同生效</c:if> 
          &nbsp;
          </td>
          <td>
			<a href="${ctx}/returnBackContractModifyPage.do?buyBackContractId=${backContract.buyBackContractId}&targetPage=view">查看</a>
			<input type="hidden" id="print" value="true"/><a href="javascript:selReview('${backContract.buyBackContractId}')">查看评审表</a>
			<c:if test="${userAction == 'printE'}">
				<c:choose>
					<c:when test="${backContract.status ==9}">
						<a href="${ctx}/returnBackContractModifyPage.do?buyBackContractId=${backContract.buyBackContractId}&targetPage=confirm">确认</a>
					</c:when>
					<c:otherwise>
						确认
					</c:otherwise>
				</c:choose>
			</c:if>

			<c:if test="${userAction == 'queryUDB'}">
				<c:choose>
					<c:when	test="${backContract.status==1|| backContract.status==3||backContract.status==5||backContract.status==7||backContract.status==10}">
						<a href="${ctx}/returnBackContractModifyPage.do?buyBackContractId=${backContract.buyBackContractId}&targetPage=modify">修改</a>											
						<a href="javascript:delBuyBackContractById('${backContract.buyBackContractId}')">删除</a>
					</c:when>
					<c:otherwise>
						修改
						删除
					</c:otherwise>
				</c:choose>
			</c:if>
               
			<c:if test="${userAction == 'queryA'}">
				<c:choose>
					<c:when	test="${(user.roleId==15 && backContract.status==2) || (user.roleId==11 && backContract.status==4) || (user.roleId==17 && backContract.status==6) }">
						<a href="${ctx}/returnBackContractModifyPage.do?buyBackContractId=${backContract.buyBackContractId}&targetPage=review">评审</a>&nbsp;														
					</c:when>
					<c:otherwise>
						评审&nbsp;
					</c:otherwise>
				</c:choose>
			</c:if>
          </td>
        </tr>
       </c:forEach>
      </table>
      <br/>
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        <tr>
          <td align="right" height="25px"><%@ include file="/jsp/common/newPage.jsp"%></td>
        </tr>
      </table></td>
    <td >&nbsp;</td>
  </tr>
</table>
</body>
</html>

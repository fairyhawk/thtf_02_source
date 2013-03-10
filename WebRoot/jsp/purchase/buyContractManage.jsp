<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购合同管理</title>
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
		function lookCommentClick(id,istrue){
			window.open('${ctx}/lookBuyContractCommentTable.do?print='+istrue+'&buyContractId='+id+'','newwindow', "toolbar=no,scrollbars=yes,resizable=yes,top=0,left=170, width=900,height=680");
		}
	function delBuyContract(){
		if(confirm('确认删除该条信息及此信息的所有明细和地址？')){return true;}else{return false;}
	}
		</script>
</head>
<body>
<c:if test="${param.deleteError}">
	<script>alert("权限错误！");</script>
</c:if>
<c:if test="${param.delError}">
	<script>alert("删除失败！");</script>
</c:if>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 采购管理 &gt;&gt; 采购合同管理</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
      <div class="mo_wp">
        <div style="display: ; " class="mo_con" >
        <html:form action="buyContractList" method="get" styleId="buyContractForm">
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
					<html:option value="6">运营助理待评审</html:option> 
					<html:option value="7">运营助理未通过</html:option>
					<html:option value="8">运营总监待评审</html:option> 
					<html:option value="9">运营总监未通过</html:option> 
					<html:option value="10">待打印</html:option>
					<html:option value="11">待确认</html:option> 
					<html:option value="12">合同取消</html:option> 
					<html:option value="13">合同生效</html:option> 
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
              	<a href="#" onclick="$('#buyContractForm').submit();"><img src="${ctx}/images/btn_JianSuo.gif" /></a>
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
          <th nowrap="nowrap">&nbsp;合同流水号&nbsp;</th>
          <th nowrap="nowrap">&nbsp;产品分类名称&nbsp;</th>
          <th nowrap="nowrap">&nbsp;产品合同号&nbsp;</th>
          <th nowrap="nowrap">&nbsp;公司合同号&nbsp;</th>
          <th nowrap="nowrap" >&nbsp;供货商名称&nbsp;</th>
          <th nowrap="nowrap">&nbsp;模版类型&nbsp;</th>
          <th nowrap="nowrap">&nbsp;付款方式&nbsp;</th>
          <th nowrap="nowrap">&nbsp;帐期(天)&nbsp;</th>
          <th nowrap="nowrap">&nbsp;签订日期&nbsp;</th>
          <th nowrap="nowrap">&nbsp;人员名称&nbsp;</th>
          <th nowrap="nowrap">&nbsp;合同状态&nbsp;</th>
          <th nowrap="nowrap">&nbsp;&nbsp;合同金额&nbsp;&nbsp;</th>
          <th nowrap="nowrap">&nbsp;&nbsp;入库金额&nbsp;&nbsp;</th>
          <th nowrap="nowrap">&nbsp;&nbsp;指定金额&nbsp;&nbsp;</th>
          <th nowrap="nowrap">&nbsp;&nbsp;预付金额&nbsp;&nbsp;</th>
          <th nowrap="nowrap">&nbsp;&nbsp;收票金额&nbsp;&nbsp;</th>
          <th nowrap="nowrap">&nbsp;退货合同金额&nbsp;</th>
          <th nowrap="nowrap">&nbsp;&nbsp;返厂金额&nbsp;&nbsp;</th>
		  <th nowrap="nowrap" >&nbsp;操作&nbsp;</th>
        </tr>

       <c:forEach var="buyContract" items="${buyContractList}">
        <tr>
          <td nowrap="nowrap" height="18px">${buyContract.id}&nbsp;</td>
          <td nowrap="nowrap">${buyContract.productTypeName}&nbsp;</td>
          <td nowrap="nowrap">${buyContract.productContractCode}&nbsp;</td>
          <td nowrap="nowrap">${buyContract.companyContractCode}&nbsp;</td>
          <td nowrap="nowrap" title="${buyContract.supplierName}"><div class="ellipsis_div" style="width:80px;">${buyContract.supplierName}&nbsp;</td>
          <td nowrap="nowrap">
          	<c:if test="${buyContract.templateType==0}">标准模板</c:if>
			<c:if test="${buyContract.templateType==1}">非模板</c:if>&nbsp;&nbsp;
          </td>
          <td nowrap="nowrap">
            <c:if test="${buyContract.paymentWay==2}">支票</c:if>
			<c:if test="${buyContract.paymentWay==3}">网银</c:if>
			<c:if test="${buyContract.paymentWay==4}">电汇</c:if>
			<c:if test="${buyContract.paymentWay==5}">银行承兑</c:if>
			<c:if test="${buyContract.paymentWay==6}">信用证</c:if>
			<c:if test="${buyContract.paymentWay==7}">其它</c:if>
		  &nbsp;
		  </td>
          <td nowrap="nowrap" style=" text-align:right;">${buyContract.arterm}&nbsp;</td>
          <td nowrap="nowrap" width="73px">${buyContract.signDate}&nbsp;</td>
          <td nowrap="nowrap">${buyContract.userName}&nbsp;</td>
          <td nowrap="nowrap">
            <c:if test="${buyContract.status==1}">未提交</c:if>
			<c:if test="${buyContract.status==2}">法务专员待评审</c:if>
			<c:if test="${buyContract.status==3}">法务专员未通过</c:if>
			<c:if test="${buyContract.status==4}">采购主管待评审</c:if>
			<c:if test="${buyContract.status==5}">采购主管未通过</c:if>
			<c:if test="${buyContract.status==6}">运营助理待评审</c:if>
			<c:if test="${buyContract.status==7}">运营助理未通过</c:if>
			<c:if test="${buyContract.status==8}">运营总监待评审</c:if>
			<c:if test="${buyContract.status==9}">运营总监未通过</c:if>
			<c:if test="${buyContract.status==10}">待打印</c:if>
			<c:if test="${buyContract.status==11}">待确认</c:if>
			<c:if test="${buyContract.status==12}">合同取消</c:if>
			<c:if test="${buyContract.status==13}">合同生效</c:if> 
          &nbsp;
          </td>
		  <td nowrap="nowrap" style=" text-align:right;">
          	<fmt:formatNumber value="${buyContract.contractMoney}" pattern="#,##0.00000"/>&nbsp;
       	  </td>		  
		  <td nowrap="nowrap" style=" text-align:right;">
          	<fmt:formatNumber value="${buyContract.instockMoney}" pattern="#,##0.00000"/>&nbsp;
       	  </td>		
		  <td nowrap="nowrap" style=" text-align:right;;">
          	<fmt:formatNumber value="${buyContract.appointMoney}" pattern="#,##0.00000"/>&nbsp;
       	  </td>		
		  <td nowrap="nowrap" style=" text-align:right;">
          	<fmt:formatNumber value="${buyContract.advanceMoney}" pattern="#,##0.00000"/>&nbsp;
       	  </td>				  
		  <td nowrap="nowrap" style=" text-align:right;">
          	<fmt:formatNumber value="${buyContract.invoiceMoney}" pattern="#,##0.00000"/>&nbsp;
       	  </td>		
		  <td nowrap="nowrap" style=" text-align:right;">
          	<fmt:formatNumber value="${buyContract.backContractMoney}" pattern="#,##0.00000"/>&nbsp;
       	  </td>		
		  <td nowrap="nowrap" style=" text-align:right;">
          	<fmt:formatNumber value="${buyContract.backGoodsMoney}" pattern="#,##0.00000"/>&nbsp;
       	  </td>		
          <td>
			<a href="${ctx}/getBuyContract.do?downLoad=true&buyContractId=${buyContract.id}">查看</a>
			<c:if test="${userAction == 'printE'}"><a href="#" onclick="lookCommentClick('${buyContract.id}',true)">查看评审表</a>
				<c:choose>
					<c:when test="${buyContract.status ==11}">
						<a href="${ctx}/getBuyContractDecideOfShow.do?buyContractId=${buyContract.id}">确认</a>
					</c:when>
					<c:otherwise>
						确认
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${buyContract.status ==13 && (buyContract.contractMoney-buyContract.instockMoney+buyContract.backGoodsMoney>0)}">
						<a href="${ctx}/addItockInit.do?addPara.buyContractId=${buyContract.id}">入库</a>
					</c:when>
					<c:otherwise>
						入库
					</c:otherwise>
				</c:choose>
			</c:if>
			<c:if test="${userAction == 'queryUDB'}">
				<c:choose>
					<c:when	test="${buyContract.status==1 || buyContract.status==3 || buyContract.status==5 || buyContract.status==7 || buyContract.status==9 || buyContract.status==12}">
						<a href="${ctx}/modifyBuyContractOfShow.do?id=${buyContract.id}">修改</a>			
						<a href="${ctx}/deleteBuyContract.do?id=${buyContract.id}" onclick="return delBuyContract()">删除</a>
						退货合同
					</c:when>
					<c:when	test="${buyContract.status==13}">
					    <c:if test="${buyContract.contractMoney>buyContract.backContractMoney}">
						    修改
							删除
							<a href="${ctx}/goBuyBackContract.do?buyContractId=${buyContract.id}&prodTypeId=${buyContract.productTypeId}">退货合同</a>

						</c:if>
					    <c:if test="${buyContract.contractMoney<=buyContract.backContractMoney}">
						     修改
							 删除
						    退货合同
						</c:if>
					</c:when>	
					<c:otherwise>
						修改
						删除
						退货合同
					</c:otherwise>
				</c:choose>
			</c:if>
               
			<c:if test="${userAction == 'queryA'}">
			<a href="#" onclick="lookCommentClick('${buyContract.id}',false)">查看评审表</a>
				<c:choose>
					<c:when	test="${(user.roleId==15 && buyContract.status==2) || (user.roleId==11 && buyContract.status==4) || (user.roleId==16 && buyContract.status==6) || (user.roleId==17 && buyContract.status==8) }">
						<a href="${ctx}/getBuyContract.do?buyContractId=${buyContract.id}&review=t">评审</a>&nbsp;														
					</c:when>
					<c:otherwise>
						评审
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
          <td width="120px" align="left">
          <c:if test="${requestScope.addBuyContract}">
          	<a href="${ctx}/addBuyContract.do?"><img src="${ctx}/images/btnXGHT.gif" width="112" height="20" /></a>
		  </c:if>
		  </td>

          <td align="right" height="25px"><%@ include file="/jsp/common/newPage.jsp"%></td>
        </tr>
      </table></td>
    <td >&nbsp;</td>
  </tr>
</table>
</body>
</html>

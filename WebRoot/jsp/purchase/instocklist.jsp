<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>入库管理</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
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
				$('#btnJS').click(function(){
					trimText();
					$('#frmConList').submit();
				});
				
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

				if("${errorMsg}" != ""){
					alert("${errorMsg}");
				}

				
			}); 

			function deleteInstock(id){
				if(confirm("是否确认删除？")){
					window.location = 'instockDel.do?addPara.id='+id;
				}
			}
		function trimText(){
	        var fom = document.forms[0];
	        for(var i=0;i<fom.elements.length;i++){
	            if(fom.elements[i].type == 'text'){
	               fom.elements[i].value = $.trim(fom.elements[i].value);
	            }
	        }
	    }
		</script> 
	</head> 
	<body>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td class="ye_header_left">&nbsp;</td>
			<td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 采购管理 &gt;&gt; 入库管理</td>
			<td class="ye_header_right">&nbsp;</td>
		</tr>
		<tr>
		<td>&nbsp;</td>
		<td align="center"><br />
			<div class="mo_wp">
			<div style="display: ; " class="mo_con" >
			<form action="instockList.do" id="frmConList">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01" width="20%">入库单号</td>
					<td class="td_02" width="30%">
						<input type="text" name="queryPara.instockId" value="${queryPara.instockId}" style="width:120px;" />
					</td>
					<td class="td_01" width="20%">库房名称</td>
					<td class="td_02" width="30%">
						<html:select property="queryPara.stockroomId" value="${queryPara.stockroomId}" style=" width:126px">
							<html:option value="">--请选择--</html:option> 
							<html:options collection="stockroomList" property="id" labelProperty="name"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td class="td_01">产品分类名称</td>
					<td class="td_02">
						<html:select property="queryPara.productTypeId" value="${queryPara.productTypeId}" style=" width:126px">
							<html:option value="">--请选择--</html:option> 
							<html:options collection="productTypeList" property="id" labelProperty="name"/>
						</html:select>
					</td>
					<td class="td_01">产品合同号</td>
					<td class="td_02"><input type="text" name="queryPara.productContractCode" id="name7" style="width:120px;" value="${queryPara.productContractCode}"/></td>
				</tr>
				<tr>
					<td class="td_01">公司合同号</td>
					<td class="td_02">
						<input type="text" name="queryPara.companyContarctCode" id="name4" style="width:120px;" value="${queryPara.companyContarctCode}"/>
					</td>
					<td class="td_01">供货商名称</td>
					<td class="td_02">
						<input type="text" name="queryPara.supplierName" id="name8" style="width:240px;" value="${queryPara.supplierName}"/>
					</td>
				</tr>
				<tr>
					<td class="td_01">申请起始日期</td>
					<td class="td_02">
						<input type="text" name="queryPara.starttime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.starttime}"/>
					</td>
					<td class="td_01">申请终止日期</td>
					<td class="td_02">
						<input type="text" name="queryPara.endtime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.endtime}"/>
					</td>
				</tr>
				<tr>
					<td class="td_01">入库起始日期</td>
					<td class="td_02">
						<input type="text" name="queryPara.stkStarttime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.stkStarttime}"/>
					</td>
					<td class="td_01">入库终止日期</td>
					<td class="td_02">
						<input type="text" name="queryPara.stkEndtime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.stkEndtime}"/>
					</td>
				</tr>
				<tr>
					<td class="td_01">人员名称</td>
					<td class="td_02">
						<input type="text" name="queryPara.userName" id="name8" style="width:240px;" value="${queryPara.userName}"/>
					</td>
					<td class="td_01">入库单状态</td>
					<td class="td_02">
						<html:select property="queryPara.status" value="${queryPara.status}" style=" width:126px">
							<html:option value="">--请选择--</html:option> 
							<html:option value="1">未提交</html:option> 
							<html:option value="2">产品总监待评审</html:option>
							<html:option value="3">产品总监未通过</html:option>
							<html:option value="4">待核对</html:option> 
							<html:option value="5">核对未通过</html:option>
							<html:option value="6">入库成功</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td class="td_01">要求付款起始日期</td>
					<td class="td_02">
						<input type="text" name="queryPara.requestAccountStarttime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.requestAccountStarttime}"/>
					</td>
					<td class="td_01">要求付款终止日期</td>
					<td class="td_02">
						<input type="text" name="queryPara.requestAccountEndtime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.requestAccountEndtime}"/>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center" style="height:30px;">
						<img id="btnJS" src="${ctx}/images/btn_JianSuo.gif" />
					</td>
				</tr>
			</table>
			</form>
			</div>
				<div class="mo_title" onclick="fMainListToggle(this)">
				 <div style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
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
			<th nowrap="nowrap">&nbsp;入库单号&nbsp;</th>
			<th nowrap="nowrap">&nbsp;库房名称&nbsp;</th>
			<th nowrap="nowrap">&nbsp;产品分类&nbsp;</th>
			<th nowrap="nowrap">&nbsp;产品合同号&nbsp;</th>
			<th nowrap="nowrap">&nbsp;公司合同号&nbsp;</th>

			<th nowrap="nowrap">供货商名称</th>
			<th nowrap="nowrap" >&nbsp;&nbsp;&nbsp;入库金额&nbsp;&nbsp;&nbsp;</th>
			<th nowrap="nowrap" >&nbsp;&nbsp;&nbsp;返厂金额&nbsp;&nbsp;&nbsp;</th>
			<th nowrap="nowrap" >&nbsp;申请日期&nbsp;</th>
			<th nowrap="nowrap" >&nbsp;入库日期&nbsp;</th>

			<th nowrap="nowrap" >人员名称</th> 
			<th nowrap="nowrap" >&nbsp;入库单状态&nbsp;</th>

			<th nowrap="nowrap" >&nbsp;操作&nbsp;</th>
		  </tr>
		  <logic:iterate id="instock" name="instockList">
		  <tr>
			<td nowrap="nowrap" width="78" height="18">${instock.inStockId}&nbsp;</td>
			<td nowrap="nowrap" width="120">${instock.stockroomName}&nbsp;</td>
			<td nowrap="nowrap" width="74">${instock.productTypeName}&nbsp;</td>
			<td nowrap="nowrap" width="120" ><div class="ellipsis_div" style="width:120px;">${instock.productContractCode}&nbsp;</td>
			<td nowrap="nowrap" width="120" ><div class="ellipsis_div" style="width:120px;">${instock.companyContractCode}&nbsp;</td>

			<td nowrap="nowrap" width="120" title="${instock.inStockSupplierName}"><div class="ellipsis_div" style="width:120px;">${instock.inStockSupplierName}&nbsp;</td>
			<td nowrap="nowrap" width="86" style="text-align:right; " >${instock.inStockMoney}&nbsp;</td>
			<td nowrap="nowrap" width="86" style="text-align:right; ">${instock.buyBackGoodsMoney}&nbsp;</td>
			<td nowrap="nowrap" width="73" >${instock.inStockDate}&nbsp;</td>
			<td nowrap="nowrap" width="73" >${instock.inStockStkAdmDate}&nbsp;</td>

			<td nowrap="nowrap" width="59" >${instock.inStockUserName}&nbsp;</td> 
			<td nowrap="nowrap" width="80" > 
				<c:if test="${instock.inStockStatus==1}">未提交&nbsp;</c:if>
				<c:if test="${instock.inStockStatus==2}">产品总监待评审&nbsp;</c:if>
				<c:if test="${instock.inStockStatus==3}">产品总监未通过&nbsp;</c:if>
				<c:if test="${instock.inStockStatus==4}">待核对&nbsp;</c:if>
				<c:if test="${instock.inStockStatus==5}">核对未通过&nbsp;</c:if>
				<c:if test="${instock.inStockStatus==6}">入库成功&nbsp;</c:if> 
			</td>

			<td nowrap="nowrap">
			<c:if test="${roleId==8||roleId==10||roleId==11||roleId==14||roleId==15||roleId==16||roleId==17||roleId==18}"> 
				<a href="${ctx}/instockView.do?addPara.id=${instock.inStockId}">查看</a>
			</c:if>
			<c:if test="${roleId==8}">
				<c:choose>
					<c:when test="${instock.inStockStatus==1||instock.inStockStatus==3||instock.inStockStatus==5}"> 
						<a href="${ctx}/addItockInit.do?addPara.id=${instock.inStockId}">修改</a>
						<a href="javascript:deleteInstock('${instock.inStockId}');">删除</a>
					</c:when>
					<c:otherwise>
						修改
						删除
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${instock.inStockStatus==6}"> 
						<a href="${ctx}/getBuyBackGoogsCreate.do?id=${instock.inStockId}">返厂</a>
					</c:when>
					<c:otherwise>
						返厂
					</c:otherwise>
				</c:choose>
			</c:if>
			<c:if test="${roleId==10}">
				<c:choose>
					<c:when test="${instock.inStockStatus==2}"> 
						<a href="${ctx}/instockView.do?addPara.id=${instock.inStockId}&isAudit=true">评审</a>
					</c:when>
					<c:otherwise>
						评审
					</c:otherwise> 
				</c:choose>
			</c:if>&nbsp;
			</td>
		  </tr>
		  </logic:iterate>
		</table>
	<br />
			<table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">

				<tr>
				  <td align="left">&nbsp;</td>
				  <td align="right" ><%@ include file="/jsp/common/newPage.jsp"%></td>
			  </tr>
			</table>
		</td>
		<td >&nbsp;</td>
	  </tr>
	</table> 
	</body>
</html>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>移库管理</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
		<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
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
<script type="text/javascript" >
	function form0submit() {
	   $("#searchMoveStockDto").submit();
	}
	
	$(document).ready(function(){
	    var msg = "${msg}";  //获取服务端信息
		if(msg!=""){
			alert(msg);
		}
	 });
 function  confirmDel(){
 
  if(confirm('确认删除该移库单？')==false)return false;
	  return true;  
 }

</script>
</head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt; 移库管理</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    	<div class="mo_wp">
          <div style="display: ; " class="mo_con" >
          
          <form action="searchMoveStock.do" id="searchMoveStockDto" >
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                    <td class="td_01">移库单号</td>
                    <td class="td_02" ><input type="text" name="searchMoveStockDto.id" value="${moveStockSearchDto.id}" style="width:120px;" /></td>
                    <td class="td_01">产品分类名称</td>
                    <td class="td_02" >
                    <html:select property="searchMoveStockDto.productTypeId" value="${moveStockSearchDto.productTypeId}" style="width:126px">
							<html:option value="">--请选择--</html:option>
							<logic:iterate id="productTypeEntity" name="productTypeEntitiesList" >
							   <html:option value="${productTypeEntity.id}"> ${productTypeEntity.name}</html:option> 
							</logic:iterate>	
					</html:select>  
                    </td>
                  </tr>
                  <tr>
                    <td class="td_01">移出库房名称 </td>
                    <td class="td_02">
                    <html:select property="searchMoveStockDto.outStockroomId" value="${moveStockSearchDto.outStockroomId}" style=" width:264px">
							<html:option value="">--请选择--</html:option>
							<logic:iterate id="stockroomEntity" name="stockroomEntitiesList" >
							   <html:option value="${stockroomEntity.id}"> ${stockroomEntity.name}</html:option> 
							</logic:iterate>
					</html:select> 
                    </td>
                    <td class="td_01">移入库房名称</td>
                    <td class="td_02">
                    <html:select property="searchMoveStockDto.inStockroomId" value="${moveStockSearchDto.inStockroomId}" style=" width:264px">
							<html:option value="">--请选择--</html:option>
							<logic:iterate id="stockroomEntity" name="stockroomEntitiesList" >
							   <html:option value="${stockroomEntity.id}">${stockroomEntity.name}</html:option> 
							</logic:iterate> 
					</html:select> 
                    </td>
                  </tr>
                  <tr>
                  	<td class="td_01">货物接收单位名称</td>
                  	<td class="td_02"><input type="text" name="searchMoveStockDto.stockroomAddressName" value="${moveStockSearchDto.stockroomAddressName}"  style="width:120px;" /></td>
                  	<td class="td_01">货运方式</td>
                  	<td class="td_02">                  	                  	
					 <html:select property="searchMoveStockDto.transportWay"  value="${moveStockSearchDto.transportWay}"   style=" width:126px">
                  		  <html:option value="">--请选择--</html:option>
                  		  <html:option value="0">未指定</html:option>
                	 	  <html:option value="1">自提</html:option> 
                  		  <html:option value="2">快递</html:option> 
                 		  <html:option value="3">汽运</html:option> 
                   		  <html:option value="4">铁运</html:option> 
                  		  <html:option value="5">航空</html:option> 
                  		  <html:option value="6">海运</html:option> 
                  		  <html:option value="7">中铁</html:option> 
                 		  <html:option value="8">市内送货</html:option>    
                 		  <html:option value="9">市内快递</html:option>                  		    		   
                     </html:select>
					</td>
                  	</tr>
                  <tr>
                  	<td class="td_01">申请起始日期</td>
                  	<td class="td_02"><input type="text" name="searchMoveStockDto.dateStart" value="${moveStockSearchDto.dateStart}" style="width:120px;" onfocus="calendar()"/></td>
                  	<td class="td_01">申请终止日期</td>
                  	<td class="td_02"><input type="text" name="searchMoveStockDto.dateEnd"  value="${moveStockSearchDto.dateEnd}"  style=" width:120px" onfocus="calendar()"/></td>
                  </tr>
                  <tr>
                  	<td class="td_01">要求发货起始日期</td>
                  	<td class="td_02"><input type="text" name="searchMoveStockDto.requestDateStart" value="${moveStockSearchDto.requestDateStart}" style="width:120px;" onfocus="calendar()"/></td>
                  	<td class="td_01">要求发货终止日期</td>
                  	<td class="td_02"><input type="text" name="searchMoveStockDto.requestDateEnd" value="${moveStockSearchDto.requestDateEnd}" style="width:120px;" onfocus="calendar()"/></td>
                  	</tr>
                  <tr>
                  	<td class="td_01">发货起始日期</td>
                  	<td class="td_02"><input type="text" name="searchMoveStockDto.sendDateStart" value="${moveStockSearchDto.sendDateStart}" style="width:120px;" onfocus="calendar()"/></td>
                  	<td class="td_01">发货终止日期 </td>
                  	<td class="td_02"><input type="text" name="searchMoveStockDto.sendDateEnd" value="${moveStockSearchDto.sendDateEnd}" style="width:120px;" onfocus="calendar()"/></td>
                  	</tr>
                  <tr>
                  	<td class="td_01">入库起始日期</td>
                  	<td class="td_02"><input type="text" name="searchMoveStockDto.inAdmDateStart" value="${moveStockSearchDto.inAdmDateStart}" style="width:120px;" onfocus="calendar()"/></td>
                  	<td class="td_01">入库终止日期</td>
                  	<td class="td_02"><input type="text" name="searchMoveStockDto.inAdmDateEnd" value="${moveStockSearchDto.inAdmDateEnd}" style="width:120px;" onfocus="calendar()"/></td>
                  	</tr>
                  <tr>
                  	<td class="td_01">人员名称</td>
                  	<td class="td_02"><input type="text" name="searchMoveStockDto.userName"  value="${moveStockSearchDto.userName}" style="width:120px;" /></td>
                  	<td class="td_01">移库单状态</td>
                  	<td class="td_02">
                  	<html:select property="searchMoveStockDto.status"  value="${moveStockSearchDto.status}"   style=" width:126px">
                  		  <html:option value="">--请选择--</html:option>
                	 	  <html:option value="1">未提交</html:option> 
                  		  <html:option value="2">采购主管待评审</html:option> 
                 		  <html:option value="3">采购主管未通过</html:option> 
                   		  <html:option value="4">待发货</html:option> 
                  		  <html:option value="5">发货中</html:option> 
                  		  <html:option value="6">发货异常</html:option> 
                  		  <html:option value="7">待核对</html:option> 
                 		  <html:option value="8">入库成功</html:option>                  		    		   
                     </html:select>					
					</td>
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
    <br/>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
      <tr>
        <th nowrap="nowrap">移库单号</th>
        <th nowrap="nowrap">移出库房名称</th>
        <th nowrap="nowrap">移入库房名称</th>
        <th nowrap="nowrap">&nbsp;产品分类名称&nbsp;</th>
        <th nowrap="nowrap">货物接收单位名称</th>
        <th nowrap="nowrap">货运方式</th>
        <th nowrap="nowrap">申请日期</th>
        <th nowrap="nowrap">&nbsp;要求发货日期&nbsp;</th>
        <th nowrap="nowrap">发货日期</th>
        <th nowrap="nowrap">入库日期</th>
        <th nowrap="nowrap">人员名称</th>
        <th nowrap="nowrap">移库单状态</th>
        <th nowrap="nowrap">&nbsp;操作&nbsp;</th>
      </tr>
      
      <logic:iterate id="moveStockListDto" name="moveStockList" >
			<tr>
				<td nowrap="nowrap" width="100px" height="18">${moveStockListDto.id}&nbsp;</td>
				<td width="100px" nowrap="nowrap"   title="${moveStockListDto.outStockroomName}">
				<div class="ellipsis_div" width="100px">
					${moveStockListDto.outStockroomName}&nbsp;
				</div>
				</td>
				<td width="100px" nowrap="nowrap" title="${moveStockListDto.inStockroomName}" ><div class="ellipsis_div" style="width:100px;">${moveStockListDto.inStockroomName}&nbsp;</div></td>
				<td width="120px" nowrap="nowrap">${moveStockListDto.productTypeName}&nbsp;</td>
				<td nowrap="nowrap" title="${moveStockListDto.stockroomAddressName}"><div class="ellipsis_div" style="width:120px;">${moveStockListDto.stockroomAddressName}&nbsp;</div></td>
				<td  nowrap="nowrap" >
				<div class="ellipsis_div" style="width:60px;">		
				  <c:if test="${moveStockListDto.transportWay==0}">未指定</c:if>
				  <c:if test="${moveStockListDto.transportWay==1}">自提</c:if>
				  <c:if test="${moveStockListDto.transportWay==2}">快递</c:if>
				  <c:if test="${moveStockListDto.transportWay==3}">汽运</c:if>
				  <c:if test="${moveStockListDto.transportWay==4}">铁运</c:if>
				  <c:if test="${moveStockListDto.transportWay==5}">航空</c:if>
				  <c:if test="${moveStockListDto.transportWay==6}">海运</c:if>
				  <c:if test="${moveStockListDto.transportWay==7}">中铁</c:if>
				  <c:if test="${moveStockListDto.transportWay==8}">市内送货</c:if>	
				  <c:if test="${moveStockListDto.transportWay==9}">市内快递</c:if>	
				</div></td>
				<td width="72px" nowrap="nowrap" style=" text-align:right;">${moveStockListDto.date}&nbsp;</td>
				<td width="73px" nowrap="nowrap" >${moveStockListDto.requestDate}&nbsp;</td>
				<td width="73px" nowrap="nowrap" >${moveStockListDto.sendDate}&nbsp;</td>
				<td width="73px" nowrap="nowrap" >${moveStockListDto.inAdmDate}&nbsp;</td>
				<td width="73px" nowrap="nowrap" >${moveStockListDto.userName}&nbsp;</td>
				<td width="100px" nowrap="nowrap" ><div class=ellipsis_div style="width:95px;">
				  <c:if test="${moveStockListDto.status==1}">未提交</c:if>
				  <c:if test="${moveStockListDto.status==2}">采购主管待评审</c:if>
				  <c:if test="${moveStockListDto.status==3}">采购主管未通过</c:if>
				  <c:if test="${moveStockListDto.status==4}">待发货</c:if>
				  <c:if test="${moveStockListDto.status==5}">发货中</c:if>
				  <c:if test="${moveStockListDto.status==6}">发货异常</c:if>
				  <c:if test="${moveStockListDto.status==7}">待核对</c:if>
				  <c:if test="${moveStockListDto.status==8}">入库成功</c:if>				
				 </td>
				<td  nowrap="nowrap">
					<a href="getShowMoveStockAssess.do?moveStockId=${moveStockListDto.id}&type=view">查看</a>		
					<!-- 采购专员-->
					<c:if test="${USERLOGIN.roleId==8}">  
						<c:choose>
							<c:when test="${moveStockListDto.status==1||moveStockListDto.status==3||moveStockListDto.status==6}">
						 	 <a href="getMoveStockModifyInfo.do?moveStockId=${moveStockListDto.id}">修改</a>
						 	   <a href="deleteMoveStock.do?moveStockId=${moveStockListDto.id}"  onclick=" return confirmDel()" >删除</a>
							</c:when>
							<c:otherwise>
						   		 修改  删除
							</c:otherwise> 
						</c:choose>	
					 </c:if> 					
								
					<!-- 产品总监  -->
					<c:if test="${USERLOGIN.roleId==10}">  
						<c:choose>
							<c:when test="${moveStockListDto.status==1||moveStockListDto.status==3||moveStockListDto.status==6}">
						 	 <a href="getMoveStockModifyInfo.do?moveStockId=${moveStockListDto.id}">修改</a>
						 	   <a href="deleteMoveStock.do?moveStockId=${moveStockListDto.id}"  onclick=" return confirmDel()" >删除</a>
							</c:when>
							<c:otherwise>
						   		 修改  删除
							</c:otherwise> 
						</c:choose>	
					 </c:if> 
					 
					<!-- 采购主管  -->
					<c:if test="${USERLOGIN.roleId==11}">  
					    <c:if test="${moveStockListDto.status==2}">
						  <a href="getShowMoveStockAssess.do?moveStockId=${moveStockListDto.id}&type=showAssess">评审</a>
						</c:if>
						<c:if test="${moveStockListDto.status!=2}">
						    评审
						</c:if> 
					</c:if>&nbsp;					
				</td>
			</tr>
		</logic:iterate>
     
     
    </table>
    <br/>
        <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        	<tr>
        	<c:if test="${USERLOGIN.roleId==8||USERLOGIN.roleId==10}">  
				<td align="left" width="150px"><a href="createMoveStockInfo.do"><img src="${ctx}/images/btnYK.jpg" width="102" height="20" /></a></td>
			</c:if>
           	    <td align="right"><%@ include file="/jsp/common/newPage.jsp"%></td>
            </tr>
        </table>   
	 </td>
    <td >&nbsp;</td>
  </tr>
</table>
 
</body>
</html>

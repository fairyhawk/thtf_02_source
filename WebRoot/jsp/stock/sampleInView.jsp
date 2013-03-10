<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<%@ page language="java" pageEncoding="UTF-8"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title id="title">样品归还单</title>
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
.STYLE1 {
	color: #FF0000
}
-->
</style>
<script language="JavaScript">
function getJsonData(){
				var sumId=$("#tableProduct tr").length-1;
			 	var returnVlaue="{resultSum:"+sumId+",rows:[";
			 	$("#tableProduct tr").each(function(i){
					if(i!=0){
						var productId=$("#"+$(this).attr("id")+" td:nth-child(1)").text();
						var  incomeMoney=$("#"+$(this).attr("id")+" td:nth-child(7)").text();//入库sum  入库金额
						var  incomeSum=$("#"+$(this).attr("id")+" td:nth-child(13)").text();//
						returnVlaue+="{productId:"+productId+",incomeSum:"+$.trim(incomeSum)+",incomeMoney:"+$.trim(incomeMoney)+"},"
						}
				});
				returnVlaue=returnVlaue.substring(0,returnVlaue.lastIndexOf(","));
				returnVlaue+="]}";
				$("#jsonData").val(returnVlaue);
			}
$(document).ready(function(){
			$("#prient").click(function(){
				window.open("${ctx}/jsp/stock/printOfIncome.jsp",'newwindow', "toolbar=no,scrollbars=yes,resizable=yes,top=100,left=280, width=750,height=480");
			});
				$("#productSpec1").click(function(){
					if($("#productSpec1").attr("checked")){
					$("#productSpec0").attr("checked","");
					}else{
						$("#productSpec0").attr("checked","true");
						}
					});
				$("#productSpec0").click(function(){
					if($("#productSpec0").attr("checked")){
					$("#productSpec1").attr("checked","");
					}else{
						$("#productSpec1").attr("checked","true");
						}
					});					
				$("#productQuality0").click(function(){
					if($("#productQuality0").attr("checked")){
					$("#productQuality1").attr("checked","");
					}else{
						$("#productQuality1").attr("checked","true");
						}
					});
				$("#productQuality1").click(function(){
					if($("#productQuality1").attr("checked")){
					$("#productQuality0").attr("checked","");
					}else{
						$("#productQuality0").attr("checked","true");
						}
					});
				$("#productSum0").click(function(){
					if($("#productSum0").attr("checked")){
					$("#productSum1").attr("checked","");
					}else{
						$("#productSum1").attr("checked","true");
						}
					});
				$("#productSum1").click(function(){
					if($("#productSum1").attr("checked")){
					$("#productSum0").attr("checked","");
					}else{
						$("#productSum0").attr("checked","true");
						}
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
					});
				}
					$("#UnpassSubmit").click(function(){  
			if(!$("#productSpec0").attr("checked") && !$("#productSpec1").attr("checked")){
				alert("请选产品规格");
				$("#UnpassSubmit").attr("href","#productSpec0");
				return;
			}else if(!$("#productQuality0").attr("checked") && !$("#productQuality1").attr("checked")){
				alert("请选择产品质量");
				$("#UnpassSubmit").attr("href","#productQuality0");
				return;
			}else if(!$("#productSum0").attr("checked") && !$("#productSum1").attr("checked")){
				alert("请选择产品数量");
				$("#UnpassSubmit").attr("href","#productSum0");
				return;
			}else if($.trim($("#textarea").val())==""){
				alert("请填写补充说明");
				$("#UnpassSubmit").attr("href","#textarea");
				return;
				}
			$("#submitType").val("unpass");
		$("#InStockOfCommentForm").submit();
	});	
	$("#PassSubmit").click(function(){
			if(!$("#productSpec1").attr("checked")){
				alert("请选择符合");
				$("#PassSubmit").attr("href","#productSpec1");
				return;
			}else if(!$("#productQuality1").attr("checked")){
				alert("请选择符合");
				$("#PassSubmit").attr("href","#productQuality1");
				return;
			}else if(!$("#productSum1").attr("checked")){
				alert("请选择符合");
				$("#PassSubmit").attr("href","#productSum1");
				return;
			}	
		$("#submitType").val("pass");
		$("#InStockOfCommentForm").submit();	
	});
			});

        $(function(){
            //采购主管评审意见
	        if('${assessDto.buyManIdea}'=='1'){
	        	$('#buyManIdea1').attr("checked",'true');
	        }
	        if('${assessDto.buyManIdea}'=='0'){
	        	$('#buyManIdea0').attr("checked",'true');
	        }
       });
              

</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
</head>

<body>
<c:choose>
	<c:when test="${requestScope.comment}"><c:set var ="title" value="样品归还单核对"></c:set></c:when>
	<c:otherwise><c:set var ="title" value="样品归还单查看"></c:set></c:otherwise>
</c:choose>
<c:if test="${param.stkAdmIdeaError}">
	<script>alert("请选择库房管理员核对意见");</script>
</c:if>
<c:if test="${param.commentError}">
	<script>alert("评审失败");</script>
</c:if>
<html:form action="modifyInStockOfincomeStoreComment" styleId="InStockOfCommentForm" method="post">
<input type="hidden" name="id" value="${assessDto.id}" />
<input type="hidden" id="submitType" name="type" />
<input type="hidden" name="billType" value="4"/>
<input type="hidden" name="stockroomId" value="${assessDto.inStockroomId}"/>
<input type="hidden" name="jsonData" value="" id="jsonData"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 入库管理 &gt;&gt; ${title }</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
	 <div class="div_tiao"> &nbsp;&nbsp;借出单信息 </div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" width="20%" height="18px">借出单号</td>
            <td class="td_02" width="30%">${assessDto.sampleOutId}&nbsp;</td>
			<td class="td_01" width="20%">借出库房名称</td>
            <td class="td_02" width="30%">${assessDto.outStockroomName}&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">借出单类型</td>
            <td  class="td_02" >
                    <logic:equal value="0" property="type" name="assessDto">
            		          不归还
            	    </logic:equal>
                    <logic:equal value="1" property="type" name="assessDto">
            		          归还
            	    </logic:equal>
            &nbsp;</td>
			<td class="td_01">预计归还日期</td>
            <td class="td_02" >${assessDto.inDate}&nbsp;</td>
          </tr>
          <tr>
          	<td class="td_01" height="18px">借出单位类型</td>
          	<td class="td_02" >
          	        <logic:equal value="1" property="companyType" name="assessDto">
            		          公司
            	    </logic:equal>
                    <logic:equal value="2" property="companyType" name="assessDto">
            		          客户
            	    </logic:equal>
                    <logic:equal value="3" property="companyType" name="assessDto">
            		         供货商
            	    </logic:equal> 
          	&nbsp;</td>
          	<td class="td_01">发货日期</td>
          	<td class="td_02" >${assessDto.sendDate}&nbsp;</td>
          	</tr>
          <tr>
            <td class="td_01" height="18px">借出单位</td>
            <td class="td_02" >${assessDto.companyName}&nbsp;</td>
            <td class="td_01">保管人</td>
            <td class="td_02" >${assessDto.custosName}&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">产品分类名称</td>
            <td class="td_02" >${assessDto.productTypeName}&nbsp;</td>
            <td class="td_01">&nbsp;</td>
            <td class="td_02" >&nbsp;</td>
          </tr>
        </table>
	<br />
    <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
    <div id="printOfDiv">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
		<div id="showPrint" style="display:none;font-size:20px;font-weight:700;text-align:center">入库单</div>
        <tr id="showPrint" style="display:none">
          <td class="td_01" height="18px">厂家</td>
          <td class="td_02" colspan="3">${assessDto.companyName}&nbsp;</td>
        </tr>
        <tr id="showPrint" style="display:none">
          <td class="td_01" height="18px">样品归还单号</td>
          <td class="td_02">${assessDto.id}&nbsp;</td>
          <td class="td_01">日期</td>
          <td class="td_02">${assessDto.stkAdmDate}&nbsp;</td>
        </tr>
		<tr>
				<td class="td_03" width="20%" height="18">库房名称</td>
				<td class="td_04" colspan="3">${assessDto.inStockroomName}&nbsp;</td>
		</tr>
	</table>
	<div style="text-align:right; width:100%">&nbsp;</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="tableProduct">
		<tr>
			<th nowrap="nowrap">序号</th>
			<th nowrap="nowrap">产品编码</th>
			<th nowrap="nowrap">产品名称</th>
			<th nowrap="nowrap">规格型号</th>
			<th nowrap="nowrap">单位</th>
			<th nowrap="nowrap">借出数</th>
			<th nowrap="nowrap">已归还数</th>
			<th nowrap="nowrap">归还数</th>
		</tr>
		
		<logic:iterate id="sampleInProductInfoDto" name="list" indexId="indexId">
		<tr id="tr${sampleInProductInfoDto.id}">
			<td nowrap="nowrap" style="display:none">${sampleInProductInfoDto.id}</td>
			<td width="40" nowrap="nowrap" height="18" style="padding-right:5px;text-align:right">${indexId+1}</td>
			<td nowrap="nowrap">&nbsp;${sampleInProductInfoDto.code}</td>
			<td nowrap="nowrap">&nbsp;${sampleInProductInfoDto.productName}</td>
			<td nowrap="nowrap" >&nbsp;${sampleInProductInfoDto.type}</td>
			<td nowrap="nowrap" >&nbsp;${sampleInProductInfoDto.unit}</td>
			<td nowrap="nowrap" style="padding-right:5px;text-align:right">${sampleInProductInfoDto.count}</td>
			<td nowrap="nowrap" style="padding-right:5px;text-align:right">${sampleInProductInfoDto.alreadyReCount}</td>
			<td nowrap="nowrap" width="92" style="padding-right:5px;text-align:right">${sampleInProductInfoDto.reCount}</td>
		</tr>
		</logic:iterate>
	</table>
   </div>
<br/>
  	<div class="div_tiao"> &nbsp;&nbsp;收货信息</div>
	&nbsp;&nbsp;
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" height="18px">货物接收单位名称</td>
            <td colspan="3" class="td_02" >${assessDto.goodsReceiveUnitName}&nbsp;</td>
		</tr>
          <tr>
            <td class="td_01" height="18px">收货地址</td>
            <td colspan="3"  class="td_02" >${assessDto.address}&nbsp;</td>
		</tr>
          <tr>
            <td class="td_01" width="20%" height="18px">联系人</td>
            <td class="td_02" width="30%">${assessDto.linkman}&nbsp;</td>
            <td class="td_01" width="20%">邮编</td>
            <td class="td_02" width="30%">${assessDto.postcode}&nbsp;</td>
          </tr>
		  <tr>
		  	<td class="td_01" height="18px">电话</td>
		  	<td class="td_02" >${assessDto.tel}&nbsp;</td>
		  	<td class="td_01">手机</td>
		  	<td class="td_02" >${assessDto.mobile}&nbsp;</td>
	  	</tr>
      </table>
		 <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="20%">特殊说明</td>
				  <td class="td_04" style="padding:5px 100px 5px 5px">${tf:replaceBr(assessDto.text)}&nbsp;</td>
				</tr>
			</table>
            <br />
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
      <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
        <tr>
          <td height="20px" colspan="2" >采购主管评审意见：</td>
        </tr>
        <tr>
          <td width="320" nowrap="nowrap">库房名称是否符合</td>
          <td height="20px" width="150" nowrap="nowrap">
          <input type="checkbox" name="buyManIdea" id="buyManIdea1" value="1" disabled="disabled"
                    <c:if test='${assessDto.buyManIdea == "1"}'>checked="checked"</c:if>  
          /> 符合
            &nbsp;&nbsp;
            <input type="checkbox" name="buyManIdea" id="buyManIdea0" value="0" disabled="disabled"
                    <c:if test='${assessDto.buyManIdea == "0"}'>checked="checked"</c:if> 
            />不符合
        </td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                ${tf:replaceBr(assessDto.buyManText)}&nbsp;
                </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${assessDto.buyManName}</td>
          <td>日期：${assessDto.buyManDate}</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">&nbsp;</td>
        </tr>
<c:choose>
	<c:when test="${!requestScope.comment}">       
             <c:set var="disabled" value="disabled" scope="page"></c:set>
		     <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,0,1)==0}">
             <c:set value="checked" var="productSpecUnpass" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,0,1)==1}">
             <c:set value="checked" var="productSpecPass" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,1,2)==0}">
             <c:set value="checked" var="productQualityUnpass" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,1,2)==1}">
             <c:set value="checked" var="productQualityPass" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,2,3)==0}">
             <c:set value="checked" var="productSumUnpass" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,2,3)==1}">
             <c:set value="checked" var="productSumPass" scope="page"></c:set>
             </c:if>       
       </c:when>
</c:choose>  
        <tr>
          <td height="20px" colspan="2">库房管理员核对意见：</td>
        </tr>
        <tr>
          <td>1.产品规格是否符合</td>
          <td height="20px">
          <input type="checkbox" id="productSpec1" value="1" name="productSpec" ${disabled } ${productSpecPass}/>符合&nbsp;&nbsp;
          <input type="checkbox" id="productSpec0" value="0" ${disabled } name="productSpec" ${productSpecUnpass}/>不符合
          </td>
        </tr>
        <tr>
          <td>2.产品质量是否符合</td>
          <td height="20px">
          <input type="checkbox" ${disabled } name="productQuality" id="productQuality1" value="1" ${productQualityPass}/>符合&nbsp;&nbsp;
          <input type="checkbox" name="productQuality" id="productQuality0" value="0" ${disabled } ${productQualityUnpass}/>不符合
          </td>
        </tr>
        <tr>
          <td>3.产品数量是否符合</td>
          <td height="20px">
          <input type="checkbox" ${disabled } value="1" id="productSum1" name="productSum" ${productSumPass}/>符合&nbsp;&nbsp;
          <input type="checkbox" ${disabled } value="0" id="productSum0" name="productSum" ${productSumUnpass}/>不符合
          </td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="62px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                <c:choose>
                  <c:when test="${requestScope.comment}">
          	          <textarea rows="4" cols="60" name="text" id="textarea"></textarea>
                  </c:when>
                  <c:otherwise>${tf:replaceBr(assessDto.stkAdmText)}&nbsp;</c:otherwise>
               </c:choose>
                &nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${assessDto.stkAdmName}</td>
          <td>日期：${assessDto.stkAdmDate}</td>
        </tr>
      </table>
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    <div style="text-align:center;"> 
    	<c:choose>
    	<c:when test="${requestScope.comment}">
			<a href="#" id="PassSubmit"><img src="${ctx}/images/btnTG.gif" width="65" height="20" /></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" id="UnpassSubmit"><img src="${ctx}/images/btnWTG.gif" width="65" height="20" /></a>	    	
    	</c:when>
    	<c:otherwise>
    		<a href="#" id="prient"><img src="${ctx}/images/btnPrint.gif" width="65" height="20" /></a> 
    	</c:otherwise>
    </c:choose>
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="${ctx}/getIncomeStockList.do" ><img src="${ctx}/images/btnBack.gif" /></a>
    	</div>
    </td>
    <td></td>
  </tr>
</table>
<br />
</html:form>
</body>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title id="title">移库单</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
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
					if(i!=0){//判断是否第一个
						var productId=$("#"+$(this).attr("id")+" td:nth-child(8)").text();
						var  incomeMoney=$("#"+$(this).attr("id")+" td:nth-child(9)").text();//入库sum  入库金额
						var  incomeSum=$("#"+$(this).attr("id")+" td:nth-child(10)").text();//
						returnVlaue+="{productId:"+productId+",incomeSum:"+incomeSum+",incomeMoney:"+incomeMoney+"},"
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
		getJsonData();
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
	<c:when test="${requestScope.comment}"><c:set var ="title" value="移库单核对"></c:set></c:when>
	<c:otherwise><c:set var ="title" value="移库单查看"></c:set></c:otherwise>
</c:choose>
<html:form action="modifyInStockOfincomeStoreComment" styleId="InStockOfCommentForm" method="post">
<input type="hidden" name="id" value="${assessDto.id}" />
<input type="hidden" id="submitType" name="type" />
<input type="hidden" name="billType" value="3"/>
<input type="hidden" name="stockroomId" value="${assessDto.inStockroomId}"/>
<input type="hidden" name="jsonData" value="" id="jsonData"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt; 入库管理 &gt;&gt; ${title }</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;移出库房信息 </div>
      <div id="printOfDiv">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <div id="showPrint" style="display:none;font-size:20px;font-weight:700;text-align:center">入库单</div>
        <tr id="showPrint" style="display:none">
          <td class="td_01" height="18px">厂家</td>
          <td class="td_02" colspan="3">${assessDto.moveOutName}&nbsp;</td>
        </tr>
        <tr id="showPrint" style="display:none">
          <td class="td_01" height="18px">移库单号</td>
          <td class="td_02">${assessDto.id}&nbsp;</td>
          <td class="td_01">日期</td>
          <td class="td_02">${assessDto.inAdmDate}&nbsp;</td>
        </tr>
        <tr id="showPrint" style="display:none">
          <td class="td_01" width="20%" height="18px">移入库房名称</td>
          <td class="td_02" width="30%">${assessDto.moveInName}&nbsp;</td>
          <td class="td_01" width="20%">产品分类名称</td>
          <td class="td_02" width="30%">${assessDto.productTypeName}&nbsp;</td>
        </tr>
        <tr id="showPrintNone">
          <td class="td_01" width="20%" height="18px">移出库房名称</td>
          <td class="td_02" width="30%">${assessDto.moveOutName}&nbsp;</td>
          <td class="td_01" width="20%">产品分类名称</td>
          <td class="td_02" width="30%">${assessDto.productTypeName}&nbsp;</td>
        </tr>
      </table>
      <div style="width:100%; text-align:right"></div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="tableProduct">
        <tr>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap">移库数</th>
          <th nowrap="nowrap">移库可用数</th>
        </tr>
        <logic:iterate id="moveOutStockDto" name="list" >
        <tr id="tr${moveOutStockDto.id}">
          <td nowrap="nowrap" height="18px">&nbsp;${moveOutStockDto.code}</td>
          <td nowrap="nowrap">&nbsp;${moveOutStockDto.productName}</td>
          <td nowrap="nowrap" >&nbsp;${moveOutStockDto.type}</td>
          <td nowrap="nowrap" >&nbsp;${moveOutStockDto.unit}</td>
          <td nowrap="nowrap" width="100" style="padding-right:5px;text-align:right">${moveOutStockDto.count}</td>
          <td nowrap="nowrap" width="100" style="padding-right:5px;text-align:right">${moveOutStockDto.useCount}</td>
          
          <td nowrap="nowrap" style="display:none">${moveOutStockDto.id}</td>
          <td nowrap="nowrap" style="display:none">${moveOutStockDto.count}</td>
        </tr>
        </logic:iterate>
      </table>
      </div>
      <br/>
      <div class="div_tiao"> &nbsp;&nbsp;移入库房信息</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%" height="18px">移入库房名称</td>
          <td class="td_04" >&nbsp;${assessDto.moveInName}</td>
        </tr>
      </table>
      <div style="padding:3px 0px 2px 0px"></div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">货物接收单位名称</td>
          <td class="td_02" width="30%">${assessDto.goodsReceiveUnitName}&nbsp;</td>
          <td class="td_01" width="20%">联系人</td>
          <td class="td_02" width="30%">${assessDto.linkman}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">发货地址</td>
          <td  class="td_02" >${assessDto.address}&nbsp;</td>
          <td class="td_01">邮编</td>
          <td class="td_02" >${assessDto.postcode}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">手机</td>
          <td class="td_02" >${assessDto.mobile}&nbsp;</td>
          <td class="td_01">电话</td>
          <td class="td_02" >${assessDto.tel}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">要求发货日期</td>
          <td class="td_02" >${assessDto.requestDate}&nbsp;</td>
          <td class="td_01">货运方式</td>
          <td class="td_02" >
                    <logic:equal value="0" property="transportWay" name="assessDto">
            		          无指定
            	    </logic:equal>
                    <logic:equal value="1" property="transportWay" name="assessDto">
            		          自提
            	    </logic:equal>
				    <logic:equal value="2" property="transportWay" name="assessDto">
            		           快递
            	    </logic:equal>
            	    <logic:equal value="3" property="transportWay" name="assessDto">
            		           汽运
            	    </logic:equal>
            	    <logic:equal value="4" property="transportWay" name="assessDto">
            		           铁运
            	    </logic:equal>
            	    <logic:equal value="5" property="transportWay" name="assessDto">
            		           航空
            	    </logic:equal>
                    <logic:equal value="6" property="transportWay" name="assessDto">
            		           海运
            	    </logic:equal>
            	    <logic:equal value="7" property="transportWay" name="assessDto">
            		           中铁
            	    </logic:equal>
            	    <logic:equal value="8" property="transportWay" name="assessDto">
            		           市内送货
            	    </logic:equal>
            	    <logic:equal value="9" property="transportWay" name="assessDto">
            		           市内快递
            	    </logic:equal>
          &nbsp;</td>
        </tr>
      </table><br/>
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
                <td width="320" nowrap="nowrap">&nbsp;</td>
                <td height="20px" width="150" nowrap="nowrap"><input type="checkbox" name="moveStockAssess.buyManIdea" id="buyManIdea1" value="1" disabled="disabled"
                                 <c:if test='${assessDto.buyManIdea == "1"}'>checked="checked"</c:if> 
                />符合
                  &nbsp;&nbsp;
                          <input type="checkbox" name="moveStockAssess.buyManIdea" id="buyManIdea0" value="0" disabled="disabled"
                          <c:if test='${assessDto.buyManIdea == "0"}'>checked="checked"</c:if> 
                        />不符合 
                      </td>
              </tr>
              <tr>
                <td colspan="2" valign="top">
                	<table width="98%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                          <td style="padding:5px 0 5px 0;">${tf:replaceBr(assessDto.buyManText)}
                          </td>
                        </tr>
                	</table>                    </td>
              </tr>
              <tr>
                <td height="20px">签字：${assessDto.buyManName}</td>
                <input type="hidden" name="moveStockAssess.buyManName"  value="${assessDto.buyManName}"/>
                <td>日期：${assessDto.buyManDate}</td>
                <input type="hidden" name="moveStockAssess.buyManDate"  value="${assessDto.buyManDate}"/>
              </tr>
              <tr>
                <td height="20px" colspan="2">&nbsp;</td>
              </tr>
		     <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,0,1)==0}">
             	<c:set value="checked" var="productSpecUnpassOfMove" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,0,1)==1}">
             	<c:set value="checked" var="productSpecPassOfMove" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,1,2)==0}">
             	<c:set value="checked" var="productQualityUnpassOfMove" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,1,2)==1}">
             	<c:set value="checked" var="productQualityPassOfMove" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,2,3)==0}">
             	<c:set value="checked" var="productSumUnpassOfMove" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,2,3)==1}">
             	<c:set value="checked" var="productSumPassOfMove" scope="page"></c:set>
             </c:if>              
             <tr>
                <td height="20px" colspan="2">移出库房管理员意见：</td>
              </tr>
              <tr>
                <td>1.产品规格是否符合</td>
                <td height="20px">
                  <input type="checkbox" disabled="true" ${productSpecPassOfMove}/>符合&nbsp;&nbsp;
                  <input type="checkbox" disabled="true" ${productSpecUnpassOfMove}/>不符合
               </td>
              </tr>
              <tr>
                <td>2.产品质量是否符合</td>
                <td height="20px">
                  <input type="checkbox" disabled="true" ${productQualityPassOfMove}/>符合&nbsp;&nbsp;
                  <input type="checkbox" disabled="true" ${productQualityUnpassOfMove}/>不符合
               </td>
              </tr>
              <tr>
                <td>3.产品数量是否符合</td>
                <td height="20px">
                  <input type="checkbox" disabled="true" ${productSumPassOfMove}/>符合&nbsp;&nbsp;
                  <input type="checkbox"  disabled="true" ${productSumUnpassOfMove}/>不符合
               </td>
              </tr>
              <tr>
                <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="62px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                      <td style="padding:5px 0 5px 0;">${assessDto.stkAdmText }&nbsp;</td>
                    </tr>
                </table></td>
              </tr>
              <tr>
                <td height="20px">签字：${assessDto.stkAdmName}</td>
                <td>日期：${assessDto.stkAdmDate}</td>
              </tr>
              <tr>
                <td height="20px" colspan="2">&nbsp;</td>
              </tr>
 <c:choose>
	<c:when test="${!requestScope.comment}">
		<c:set var="disabled" value="disabled" scope="page"></c:set>
		<c:if test="${assessDto.inAdmIdea!=null && fn:substring(assessDto.inAdmIdea,0,1)==0}">
          <c:set value="checked" var="productSpecUnpass" scope="page"></c:set>
        </c:if>
        <c:if test="${assessDto.inAdmIdea!=null && fn:substring(assessDto.inAdmIdea,0,1)==1}">
          <c:set value="checked" var="productSpecPass" scope="page"></c:set>
        </c:if>
        <c:if test="${assessDto.inAdmIdea!=null && fn:substring(assessDto.inAdmIdea,1,2)==0}">
          <c:set value="checked" var="productQualityUnpass" scope="page"></c:set>
        </c:if>
        <c:if test="${assessDto.inAdmIdea!=null && fn:substring(assessDto.inAdmIdea,1,2)==1}">
          <c:set value="checked" var="productQualityPass" scope="page"></c:set>
        </c:if>
        <c:if test="${assessDto.inAdmIdea!=null && fn:substring(assessDto.inAdmIdea,2,3)==0}">
          <c:set value="checked" var="productSumUnpass" scope="page"></c:set>
        </c:if>
        <c:if test="${assessDto.inAdmIdea!=null && fn:substring(assessDto.inAdmIdea,2,3)==1}">
          <c:set value="checked" var="productSumPass" scope="page"></c:set>
        </c:if>
	</c:when>
</c:choose>             
               <tr>
                <td height="20px" colspan="2">移入库房管理员意见：</td>
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
                <td colspan="2" valign="top">
				<table width="98%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="60px" valign="top" style="padding:5px 0 5px 0;">补充说明：</td>
                      <td style="padding:5px 0 5px 0;">
                      	<c:choose>
                      		<c:when test="${requestScope.comment}">
                      			<textarea rows="4" cols="60" name="text"></textarea>
                      		</c:when>
                      		<c:otherwise>${tf:replaceBr(assessDto.inAdmText)}&nbsp;</c:otherwise>
                      	</c:choose>
                      </td>
                    </tr>
                </table>
				</td>
              </tr>
              <tr>
                <td height="20px">签字：${assessDto.inAdmName}</td>
                <td>日期：${assessDto.inAdmDate}</td>
              </tr>
      </table>
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    <c:choose>
    	<c:when test="${requestScope.comment}">
			<a href="#" id="PassSubmit"><img src="${ctx}/images/btnTG.gif" width="65" height="20" /></a> 
    	</c:when>
    	<c:otherwise>
    		<a href="#" id="prient"><img src="${ctx}/images/btnPrint.gif" width="65" height="20" /></a> 
    	</c:otherwise>
    </c:choose>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/getIncomeStockList.do"><img src="${ctx}/images/btnBack.gif" /></a> </td>
    <td></td>
  </tr>
</table>
<br />
</html:form>
</body>
</html>		

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="tf" uri="localhost" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>移库单预览</title>
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
@media   print   {   
  			.buttonNoPrint {display:none;}   
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
					});
				}
			});
//-->

       $(function(){
            //采购主管评审意见
	        if('${assessDto.buyManIdea}'=='1'){
	        	$('#buyManIdea1').attr("checked",'true');
	        }
	        if('${assessDto.buyManIdea}'=='0'){
	        	$('#buyManIdea0').attr("checked",'true');
	        }
       });

		function printPreview(){   
			// 打印页面预览    
			wb.execwb(7,1);
		}    
		function printSetup(){    
			// 打印页面设置    
			wb.execwb(8,1);    
		}
		function  printIt(){ 
			wb.execwb(6,6);
		}
		function closeWindow(){
			window.close();
		}

</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
</head>
<body>
<OBJECT id="wb" height=0 width=0 classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 name="wb"></OBJECT>
<html:form action="getMoveStockAssess.do" styleId="moveStockAssess" >
<input type="hidden" id="assessResult" value="" name = "assessResult"/>
<input type="hidden" id="moveStockAssess.id" value="${assessDto.id}" name="moveStockAssess.id"/>
<input type="hidden" id="moveStockAssess.proMajId" value="${assessDto.proMajId}" name="moveStockAssess.proMajId"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td >&nbsp;</td>
    <td>
      <div class="div_tiao"> &nbsp;&nbsp;移出库房信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">移出库房名称</td>
          <td class="td_02" width="30%">${assessDto.moveOutName}&nbsp;</td>
          <td class="td_01" width="20%">产品分类名称</td>
          <td class="td_02" width="30%">${assessDto.productTypeName}&nbsp;</td>
        </tr>
      </table>
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
        <tr>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
		  <c:if test="${roleId!=12&&roleId!=13}">
          <th nowrap="nowrap">库存单价</th>
		  </c:if>
          <th nowrap="nowrap">移库数</th>
          <th nowrap="nowrap">移库可用数</th>
        </tr>
        <logic:iterate id="moveOutStockDto" name="list" >
        <tr>
          <td nowrap="nowrap" height="18px">&nbsp;${moveOutStockDto.code}</td>
          <td nowrap="nowrap">${moveOutStockDto.productName}&nbsp;</td>
          <td nowrap="nowrap" >${moveOutStockDto.type}&nbsp;</td>
          <td nowrap="nowrap" >${moveOutStockDto.unit}&nbsp;</td>
		   <c:if test="${roleId!=12&&roleId!=13}">
          <td nowrap="nowrap" style=" text-align:right;">${moveOutStockDto.price}&nbsp;</td>
		  </c:if>
          <td nowrap="nowrap" width="100" style=" text-align:right;">${moveOutStockDto.count}&nbsp;</td>
          <td nowrap="nowrap" width="100" style=" text-align:right;">${moveOutStockDto.useCount}&nbsp;</td>
        </tr>
        </logic:iterate>
      </table>
      <br/>
      <div class="div_tiao"> &nbsp;&nbsp;移入库房信息</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%" height="18px">移入库房名称</td>
          <td class="td_04" >${assessDto.moveInName}&nbsp;</td>
        </tr>
      </table><!-- &nbsp;&nbsp;<a href="#" onclick="javascript:window.open('../42采购合同管理/stockroom_address_select.html','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=900,height=400');"><img src=""${ctx}/images/btnSHDZ.gif" width="99" height="20" /></a> -->
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
                />同意
                  &nbsp;&nbsp;
                          <input type="checkbox" name="moveStockAssess.buyManIdea" id="buyManIdea0" value="0" disabled="disabled"
                          <c:if test='${assessDto.buyManIdea == "0"}'>checked="checked"</c:if> 
                        />不同意 
                      </td>
              </tr>
              <tr>
                <td colspan="2" valign="top">
                	<table width="98%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                          <td style="padding:5px 0 5px 0;">
                          ${tf:replaceBr(assessDto.buyManText)}&nbsp;
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
   
              <tr>
                <td height="20px" colspan="2">移出库房管理员意见：</td>
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
                    ${tf:replaceBr(assessDto.stkAdmText)}&nbsp;
                      </td>
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
                      ${tf:replaceBr(assessDto.inAdmText)}&nbsp;
                      </td>
                    </tr>
                </table>
				</td>
              </tr>
              <tr>
                <td height="20px">签字：${assessDto.inAdmName}</td>
                <td>日期：${assessDto.inAdmIdea}</td>
              </tr>
      </table>
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    <img id="printBtn" src="/cms/images/btnPrint.gif" width="65" height="20" class="buttonNoPrint" onClick="javascript:printIt();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<img src="/cms/images/btnPrintSZ.gif" width="88" height="20" class="buttonNoPrint" onClick="javascript:printSetup();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<img src="/cms/images/btnPrintYL.gif" class="buttonNoPrint" onClick="javascript:printPreview();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </td>
    <td></td>
  </tr>
</table>
<br />
</html:form>
</body>
</html>

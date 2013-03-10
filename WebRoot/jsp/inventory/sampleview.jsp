<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%><html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="tf" uri="localhost" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>样品借出单查看</title>
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
		  //隐藏姓名和身份证号码 
		 if(${assessDto.transportWay== 1} && ${assessDto.companyType !=1}){ 
						  $("#nameAndId").show();  
					}else{
						$("#nameAndId").hide(); 
		} 
		 
	 });
	 $(function(){
			//打印
			$('#YuLanBtn').click(function(){
				window.open('getStockSamplePrint.do?id=${id}','newwindow');
			});
		});
</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt; 发货管理 &gt;&gt; 样品借出单查看</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;借出单信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">借出单类型</td>
          <td class="td_02" width="30%">
          	<logic:equal value="0" property="type" name="assessDto">
            		          不归还
            	    </logic:equal>
                    <logic:equal value="1" property="type" name="assessDto">
            		          归还
           </logic:equal>
            	    &nbsp;</td>
          <td class="td_01" width="20%">预计归还日期</td>
          <td class="td_02" width="30%">${assessDto.inDate}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">借出单位类型</td>
          <td  class="td_02" >
           <logic:equal value="1" property="companyType" name="assessDto">
            		          公司
            	    </logic:equal>
                    <logic:equal value="2" property="companyType" name="assessDto">
            		          客户
            	    </logic:equal>
                    <logic:equal value="3" property="companyType" name="assessDto">
            		         供货商
            	    </logic:equal>&nbsp;</td>
          <td class="td_01">保管人</td>
          <td class="td_02" >${assessDto.custosName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">借出单位</td>
          <td class="td_02" >${assessDto.companyName}&nbsp;</td>
          <td class="td_01">产品分类名称</td>
          <td class="td_02" >${assessDto.productTypeName}&nbsp;</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%" height="18px">库房名称</td>
          <td class="td_04" >${assessDto.stockRoomName}&nbsp;</td>
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
          <th width="98" nowrap="nowrap">借出数</th>
		   <c:if test="${roleId!=12&&roleId!=13}">
          <th nowrap="nowrap" width="96">&nbsp;&nbsp;&nbsp;借出金额&nbsp;&nbsp;&nbsp;</th>
		  </c:if>
          <th nowrap="nowrap" width="82">&nbsp;借出可用数&nbsp;</th>
        </tr>
        <logic:iterate id="sampleOutProductInfoDto" name="list" >
        <tr>
          <td nowrap="nowrap" height="18px">${sampleOutProductInfoDto.code}&nbsp;</td>
          <td nowrap="nowrap">${sampleOutProductInfoDto.productName}&nbsp;</td>
          <td nowrap="nowrap" >${sampleOutProductInfoDto.type}&nbsp;</td>
          <td nowrap="nowrap" >${sampleOutProductInfoDto.unit}&nbsp;</td>
		   <c:if test="${roleId!=12&&roleId!=13}">
          <td nowrap="nowrap" style="text-align:right;">${sampleOutProductInfoDto.price}&nbsp;</td>
		  </c:if>
          <td  width="98" nowrap="nowrap" style="text-align:right;">${sampleOutProductInfoDto.count}&nbsp;</td>
		   <c:if test="${roleId!=12&&roleId!=13}">
          <td nowrap="nowrap" style="text-align:right;width:96px">${sampleOutProductInfoDto.money}&nbsp;</td>
		  </c:if>
          <td nowrap="nowrap" style="text-align:right;width:82px">${sampleOutProductInfoDto.outUseCount}&nbsp;</td>
        </tr>
         </logic:iterate>
      </table>
	   <c:if test="${roleId!=12&&roleId!=13}">
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        <tr>
          <td height="25">&nbsp;</td>
          <td style=" text-align:right;">借出金额合计</td>
          <td style=" text-align:right;width:96px">${assessDto.moneyTotal}&nbsp;&nbsp;</td>
          <td style="width:82px">元</td>
        </tr>
      </table>
	  </c:if>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;发货信息</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" height="18px">货物接收单位名称</td>
          <td colspan="3" class="td_02" >${addressDto.goodsReceiveUnitName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">发货地址</td>
          <td colspan="3"  class="td_02" >${addressDto.address}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">联系人</td>
          <td class="td_02" >${addressDto.linkman}&nbsp;</td>
          <td class="td_01">邮编</td>
          <td class="td_02" >${addressDto.postcode}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">电话</td>
          <td class="td_02" width="30%">${addressDto.tel}&nbsp;</td>
          <td class="td_01" width="20%">手机</td>
          <td class="td_02" width="30%">${addressDto.mobile}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">要求发货日期</td>
          <td class="td_02" >${assessDto.requestDate}&nbsp;</td>
          <td class="td_01">货运方式</td>
          <td class="td_02" >
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
            	    </logic:equal>&nbsp;</td>
        </tr>
        <tr id="nameAndId">
          <td class="td_01" height="18px">提货人姓名</td>
          <td class="td_02" >${assessDto.takeName}&nbsp;</td>
          <td class="td_01">身份证号码</td>
          <td class="td_02" >${assessDto.takeNumber}&nbsp;</td>
        </tr>
      </table>
      <br />
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
      <c:if test="${tempRoleId == 8 }">
        <tr>
          <td height="20px" colspan="2" >采购主管评审意见：</td>
        </tr>
        <tr>
          <td width="320" nowrap="nowrap">&nbsp;</td>
          <td height="20px" width="150" nowrap="nowrap"><input type="checkbox" name="checkbox" id="checkbox" 
          disabled="disabled"
          <c:if test="${assessDto.buyManIdea==1}"> checked="checked" </c:if>
          />
            符合
            &nbsp;&nbsp;
            <input type="checkbox" name="checkbox" id="checkbox"   disabled="disabled"
            <c:if test="${assessDto.buyManIdea==0}"> checked="checked" </c:if>
             />
            不符合 </td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">${tf:replaceBr(assessDto.buyManText)}&nbsp;</td>
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
        
        </c:if>
        <c:if test="${tempRoleId == 4 }">
         <tr>
          <td height="20px" colspan="2" >销售总监评审意见：</td>
        </tr>
        <tr>
          <td width="320" nowrap="nowrap">&nbsp;</td>
          <td height="20px" width="150" nowrap="nowrap"><input type="checkbox" name="checkbox" id="checkbox" 
           disabled="disabled"
          <c:if test="${assessDto.sellMajIdea==1}">checked="checked" </c:if>
          />
            符合
            &nbsp;&nbsp;
            <input type="checkbox" name="checkbox" id="checkbox"  disabled="disabled"
            <c:if test="${assessDto.sellMajIdea==0}">checked="checked" </c:if>/>
            不符合 </td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">${tf:replaceBr(assessDto.sellMajText)}&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：&nbsp;${assessDto.sellMajName}</td>
          <td>日期：&nbsp;${assessDto.sellMajDate}</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">&nbsp;</td>
        </tr>
        </c:if>
        
         
         
        <tr>
          <td height="20px" colspan="2">库房管理员核对意见：</td>
        </tr>
        <tr>
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
             
             
          <td>1.产品规格是否符合</td>
          <td height="20px"><input type="checkbox" name="checkbox7" id="checkbox7" disabled="disabled"   ${productSpecPass}/>
            符合
            &nbsp;&nbsp;
            <input type="checkbox" name="checkbox7" id="checkbox7" disabled="disabled"  ${productSpecUnpass}/>
            不符合</td>
        </tr>
        <tr>
          <td>2.产品质量是否符合</td>
          <td height="20px"><input type="checkbox" name="checkbox6" id="checkbox6" disabled="disabled" ${productQualityPass}/>
            符合
            &nbsp;&nbsp;
            <input type="checkbox" name="checkbox6" id="checkbox6" disabled="disabled"  ${productQualityUnpass}/>
            不符合</td>
        </tr>
        <tr>
          <td>3.产品数量是否符合</td>
          <td height="20px"><input type="checkbox" name="checkbox4" id="checkbox4" disabled="disabled"  ${productSumPass}/>
            符合
            &nbsp;&nbsp;
            <input type="checkbox" name="checkbox4" id="checkbox4" disabled="disabled"   ${productSumUnpass}/>
            不符合</td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="62px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">${tf:replaceBr(assessDto.stkAdmText)}&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${assessDto.stkAdmName}</td>
          <td>日期：${assessDto.stkAdmDate}</td>
        </tr>
    </table></td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    <img id="YuLanBtn" src="${ctx}/images/btnYuLan.gif" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${ctx}/stockOrder.do?queryPara.init=true"><img src="${ctx}/images/btnBack.gif" /></a>
    </td>
    <td></td>
  </tr>
</table>
<br />
</body>
</html>

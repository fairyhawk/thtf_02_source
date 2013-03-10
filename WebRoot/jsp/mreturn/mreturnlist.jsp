<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
	$(function(){
		$.ajaxSetup({ 
	  		async: false 
	  	});
	});
	function deleteMReturn(mrid){
		if(confirm("是否确认删除？")){
			$.post('deldtemreturn.do?id='+mrid ,waitResult,'html');
			//window.location = '${ctx}/deldtemreturn.do?id='+mrid;
		}
	}
	function waitResult(result){
		if(result == '1'){
			window.location = '${ctx}/mreturn.do';
		}else if(result == '0'){
			alert("回款删除失败！");
		}else{
			alert("存在明细的回款不可删除！");
		}
	}
</script>
<html>
	<head>
		<title>回款管理</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/math.js"></script>
		<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
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
			/*各行换色*/
			$(document).ready(
				function(){
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
				$("#querybtn").click(
					function(){
						trimText();
						document.forms[0].submit();
					}
				);
				
				$("#btnHklr").click(
					function(){
						window.location.href='mreturnCreateInit.do';
					}
				);
			});
		//-->
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
    <td class="ye_header_left" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 回款管理 &gt;&gt; 回款管理</td>
   <td class="ye_header_right" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
      <div class="mo_wp">
        <div style="display: ; " class="mo_con" >
        <form action="mreturn.do" name="mreturnform" > 
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
              <td class="td_01" width="20%">回款流水号</td>
              <td class="td_02" width="30%"><input type="text" name="mReturnListQueryDto.id" id="id" style="width:120px;" value="${mReturnListForm.mReturnListQueryDto.id}" /></td>
              <td class="td_01" width="20%">回款编号</td>
              <td class="td_02" width="30%"><input type="text" name="mReturnListQueryDto.no" id="no" style="width:120px;" value="${mReturnListForm.mReturnListQueryDto.no}"  /></td>
            </tr>
            <tr>
              <td class="td_01" width="20%">产品分类名称</td>
        	  <td class="td_02" width="30%">
				<html:select property="mReturnListQueryDto.ptoductTypeId" value="${mReturnListForm.mReturnListQueryDto.ptoductTypeId}" style=" width:126px">
					<html:option value="">--请选择--</html:option> 
					<html:options collection="producttypelist" property="id" labelProperty="name"/>
				</html:select>
			  </td>
              <td class="td_01" width="20%">客户名称</td>
              <td class="td_02" width="30%"><input type="text" name="mReturnListQueryDto.customerName" id="customerName" style="width:240px;" value="${mReturnListForm.mReturnListQueryDto.customerName}" /></td>
            </tr>
            <tr>
              <td class="td_01" width="20%">回款金额</td>
              <td class="td_02" width="30%"><input type="text" name="mReturnListQueryDto.money" id="money" style="width:120px;" value="${mReturnListForm.mReturnListQueryDto.money}" /></td>
              <td class="td_01" width="20%">回款方式</td>
              <td class="td_02" width="30%">
                <html:select property="mReturnListQueryDto.returnWay" value="${mReturnListForm.mReturnListQueryDto.returnWay}" style="width:126px">
				  <html:option value="">--请选择--</html:option> 
				  <html:option value="1">现金</html:option>
                  <html:option value="2">支票</html:option>
                  <html:option value="3">网银</html:option>
                  <html:option value="4">电汇</html:option>
                  <html:option value="5">银行承兑</html:option>
                  <html:option value="6">承诺函</html:option>
                  <html:option value="7">其他</html:option>
				</html:select>
                </td>
            </tr>
            <tr>
              <td class="td_01" width="20%">录入起始日期</td>
              <td class="td_02" width="30%"><input type="text" name="mReturnListQueryDto.startDate" id="mReturnListQueryDto.startDate" style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${mReturnListForm.mReturnListQueryDto.startDate}" /></td>
              <td class="td_01" width="20%">录入终止日期</td>
              <td class="td_02" width="30%"><input type="text" name="mReturnListQueryDto.endDate" id="mReturnListQueryDto.endDate" style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${mReturnListForm.mReturnListQueryDto.endDate}" /></td>
            </tr>
            <tr>
              <td class="td_01" width="20%">回款起始日期</td>
              <td class="td_02" width="30%"><input type="text" name="mReturnListQueryDto.startReturnDate" id="mReturnListQueryDto.startReturnDate" style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${mReturnListForm.mReturnListQueryDto.startReturnDate}" /></td>
              <td class="td_01" width="20%">回款终止日期</td>
              <td class="td_02" width="30%"><input type="text" name="mReturnListQueryDto.endReturnDate" id="mReturnListQueryDto.endReturnDate" style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${mReturnListForm.mReturnListQueryDto.endReturnDate}" /></td>
            </tr>
            <tr>
              <td class="td_01" width="20%">产品合同号</td>
              <td class="td_02" width="30%"><input type="text" name="mReturnListQueryDto.productContractCode" id="mReturnListQueryDto.productContractCode" style="width:120px;" value="${mReturnListForm.mReturnListQueryDto.productContractCode}" /></td>
              <td class="td_01" width="20%">发货单号</td>
              <td class="td_02" width="30%"><input type="text" name="mReturnListQueryDto.sendGoodId" id="mReturnListQueryDto.sendGoodId" style="width:120px;" value="${mReturnListForm.mReturnListQueryDto.sendGoodId}" /></td>
            </tr>
            <tr>
              <td class="td_01" width="20%">回款类型</td>
              <td colspan="3" class="td_02" width="30%">
              <c:if test="${mReturnListForm.mReturnListQueryDto.returnType0!='0'}">
              	<input type="checkbox" name="mReturnListQueryDto.returnType0" value="0">
                回款 &nbsp;&nbsp;
                </input>
              </c:if>
              <c:if test="${mReturnListForm.mReturnListQueryDto.returnType0=='0'}">
              	<input type="checkbox" name="mReturnListQueryDto.returnType0" value="0" checked>
                回款 &nbsp;&nbsp;
                </input>
              </c:if>
            <c:if test="${mReturnListForm.mReturnListQueryDto.returnType1!='1'}">
              	<input type="checkbox" name="mReturnListQueryDto.returnType1" value="1">
                在途款 &nbsp;&nbsp;
                </input>
              </c:if>
              <c:if test="${mReturnListForm.mReturnListQueryDto.returnType1=='1'}">
              	<input type="checkbox" name="mReturnListQueryDto.returnType1" value="1" checked>
                在途款 &nbsp;&nbsp;
                </input>
              </c:if>
              <c:if test="${mReturnListForm.mReturnListQueryDto.returnType2!='2'}">
              	<input type="checkbox" name="mReturnListQueryDto.returnType2" value="2">
                在途款到账 &nbsp;&nbsp;
                </input>
              </c:if>
              <c:if test="${mReturnListForm.mReturnListQueryDto.returnType2=='2'}">
              	<input type="checkbox" name="mReturnListQueryDto.returnType2" value="2" checked>
                在途款到账 &nbsp;&nbsp;
                </input>
              </c:if>
            </tr>
            <tr>
              <td colspan="4" align="center" style="height:30px;"><a id="querybtn"><img  src="${ctx}/images/btn_JianSuo.gif" /></a></td>
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
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
        <tr>
          <th nowrap="nowrap">回款流水号</th>
          <th nowrap="nowrap">回款编号</th>
          <th nowrap="nowrap">产品分类</th>
          <th nowrap="nowrap">客户名称</th>
          <th nowrap="nowrap">回款总额 </th>
          <th nowrap="nowrap">指定金额</th>
          <th nowrap="nowrap" >合同预收金额</th>
          <th nowrap="nowrap" >产品分类预收金额</th>
          <th nowrap="nowrap" >退款金额</th>
          <th nowrap="nowrap" >回款类型</th>
          <th nowrap="nowrap" >回款方式</th>
          <th nowrap="nowrap" >录入日期</th>
          <th nowrap="nowrap" >回款日期</th>
          <th nowrap="nowrap" >凭证号</th>
          <th nowrap="nowrap">录入人</th>
          <th nowrap="nowrap">&nbsp;操作&nbsp;</th>
        </tr>
		<logic:present name="list">
			<logic:iterate id="mr" name="list" >
		        <tr>
		          <td nowrap="nowrap" width="120px" height="18px">${mr.id}&nbsp;</td>
		          <td nowrap="nowrap" width="120px">${mr.no}&nbsp;</td>
		          <td nowrap="nowrap" width="60px">${mr.productTyepName}&nbsp;</td>
		          <td nowrap="nowrap" width="64px" title="${mr.customerName}"><div class="ellipsis_div" style="width:60px;">${mr.customerName}</div></td>
		          <td nowrap="nowrap" width="84px" style="text-align:right;"  title="<fmt:formatNumber value="${mr.money}" pattern="#,##0.00#"/>"><fmt:formatNumber value="${mr.money}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap" width="84px" style="text-align:right;" title="<fmt:formatNumber value="${mr.pointMoney}" pattern="#,##0.00#"/>"><fmt:formatNumber value="${mr.pointMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap" width="84px" style="text-align:right;" title="<fmt:formatNumber value="${mr.pointContractMoney}" pattern="#,##0.00#"/>"><fmt:formatNumber value="${mr.pointContractMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap" width="84px" style="text-align:right;" title="<fmt:formatNumber value="${mr.pointProductTypeMoney}" pattern="#,##0.00#"/>"><fmt:formatNumber value="${mr.pointProductTypeMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap" width="84px" style="text-align:right;" title="<fmt:formatNumber value="${mr.backMoney}" pattern="#,##0.00#"/>"><fmt:formatNumber value="${mr.backMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap" width="51px" >
		          <c:if test="${mr.retrunType==0}">
		          	回款
		          </c:if>
		          <c:if test="${mr.retrunType==1}">
		          	在途款
		          </c:if>
		          <c:if test="${mr.retrunType==2}">
		          	到账
		          </c:if>
		          &nbsp;</td>
		          <td nowrap="nowrap" width="51px" >
	           	  <c:if test="${mr.returnWay==1}">
	           	  现金
		          </c:if>
		          <c:if test="${mr.returnWay==2}">
		          支票
		          </c:if>
		          <c:if test="${mr.returnWay==3}">
		          网银
		          </c:if>
		          <c:if test="${mr.returnWay==4}">
		          电汇
		          </c:if>
		          <c:if test="${mr.returnWay==5}">
		          银行承兑
		          </c:if>
		          <c:if test="${mr.returnWay==6}">
		          承诺函
		          </c:if>
		          <c:if test="${mr.returnWay==7}">
		          其它
		          </c:if>
		          &nbsp;</td>
		          <td nowrap="nowrap" width="73px" >${mr.date}&nbsp;</td>
		          <td nowrap="nowrap" width="73px" >${mr.returnDate}&nbsp;</td>
		          <td nowrap="nowrap" width="120px" >${mr.number}&nbsp;</td>
		          <td nowrap="nowrap" width="60px">${mr.userName}&nbsp;</td>
		          <td nowrap="nowrap">
		            <a href="javascript:window.location ='${ctx}/getmreturn.do?id=${mr.id}'">查看</a>
		            <c:if test="${roleId==3}"> 
			            <c:if test="${mr.status==2||mr.status==4||mr.status==6||mr.status==7}">
			            	删除
							指定
		            	</c:if>
		            	<c:if test="${mr.status!=2&&mr.status!=4&&mr.status!=6&&mr.status!=7}">
							<a href="javascript:deleteMReturn('${mr.id}');">删除</a>
			            	<a href="${ctx}/appoint.do?addPara.mreturnId=${mr.id}">指定</a> 
		            	</c:if>
			            <c:if test="${mr.retrunType==1}">
				          	<a href="javascript:window.location ='${ctx}/arriveinitialization.do?id=${mr.id}'">到账</a>
				        </c:if>
				        <c:if test="${mr.retrunType!=1}">
				          	到账
				        </c:if>
				        <logic:greaterThan name="mr" property="pointProductTypeMoney" value= "0">
				        	<c:if test="${mr.retrunType==0||mr.retrunType==2}">
				        		<a href="${ctx}/getInfoByMreturnId.do?id=${mr.id}">退款</a>
				        	</c:if>
				        	<c:if test="${mr.retrunType!=0&&mr.retrunType!=2}">
				        		退款
				        	</c:if>
				        </logic:greaterThan>
				        <logic:lessEqual name="mr" property="pointProductTypeMoney" value= "0"> 
				        	退款
				        </logic:lessEqual>
  				  	</c:if>&nbsp;
  				  </td>
		        </tr>
			</logic:iterate>
		</logic:present>
      </table>
      <br />
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        	<tr>
        		<c:if test="${roleId==3}">
  		      		<td align="left"><a id="btnHklr" href="#"><img src="${ctx}/images/btnHKLR.gif" width="88" height="20" /></a></td>
        		</c:if>
       	  	 	<td align="right" ><%@ include file="/jsp/common/newPage.jsp"%></td>
            </tr>
    </table></td>
    <td >&nbsp;</td>
  </tr>
</table>
</body>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head onload="checks();">
		<title>发货管理</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
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
                        $(this).click( function(){
                          if( $(this).hasClass("rowselected") ){
                            $(this).removeClass("rowselected");
                          }else{
                            $(this).addClass("rowselected");
                          }
                        }); 
					});
				}
				
				$('#btnJS').click(function(){
					trimText();
					$('#init').val('false');
					document.getElementsByTagName('Form')[0].submit();
				});;

				if("${errorMsg}" != ""){
					alert("${errorMsg}");
				}
			});
		//-->
		function checks(){
			var err = document.getElementById("err").value;
			if(err==null||err==""){
				
			} 
			else if(err!= null){
				alert(err);
				return false;
			}
		}
		function deleteSend(sendid,sgstatus){
			var alerttext = "是否确认删除？";
			if(sgstatus=='6'||sgstatus=='7'||sgstatus=='8'){
				alerttext = "是否取消备货?";
			}
			if(confirm(alerttext)){
				window.location = '${ctx}/removeSendGoods.do?id='+sendid+'&sgstatus='+sgstatus;
			}
		}
		
		function returnBox(sendGoodId){
			var url = 'getBoxIdBySendGoodId.do?sendGoodId="'+sendGoodId+'"';
			$.post(url,sendGoodId,waitResult,'html');
		}
		function waitResult(date){
			if(date == ''){
				alert('查看运单失败！');
			}else{
				var url = 'wayBillView.do?boxId='+date+'&back=true';
				document.location.href = url;
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
<input type="hidden" name="err" value="${err}" id="err"/>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tr>
    <td class="ye_header_left" width="16px" ><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt; 发货管理</td>
    <td class="ye_header_right">&nbsp;&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    	<div class="mo_wp">
    	<form action="getSendGoodsList.do" name="sendgoodsform" > 
          <div style="display: ; " class="mo_con" >
          		<input type="hidden" name="sdlqd.init" id="init" value="sendgoodsform.sdlqd.init">
	          	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
					<tr>
						<td class="td_01" width="20%">发货单号</td>
						<td class="td_02" width="30%"><input type="text" name="sdlqd.sgid" value="${sendgoodsform.sdlqd.sgid}" style="width:120px;" />
						</td>
						<td class="td_01" width="20%">库房名称</td>
						<td class="td_02" width="30%">
							<html:select property="sdlqd.stockroomid" value="${sendgoodsform.sdlqd.stockroomid}" style=" width:264px">
								<html:option value="">--请选择--</html:option> 
								<html:options collection="stockroomlist" property="id" labelProperty="name"/>
							</html:select>
					</tr>
					<tr>
						<td class="td_01" width="20%">产品分类名称</td>
						<td class="td_02" width="30%">
							<html:select property="sdlqd.producttypeid" value="${sendgoodsform.sdlqd.producttypeid}" style=" width:126px">
								<html:option value="">--请选择--</html:option> 
								<html:options collection="producttypelist" property="id" labelProperty="name"/>
							</html:select>
						</td>
						<td class="td_01" width="20%">产品合同号</td>
						<td class="td_02" width="30%"><input type="text" name="sdlqd.pcontractcode" value="${sendgoodsform.sdlqd.pcontractcode}" style="width:120px;" /></td>
					</tr>
					<tr>
						<td class="td_01">公司合同号</td>
						<td class="td_02"><input type="text" name="sdlqd.ccontractcode" value="${sendgoodsform.sdlqd.ccontractcode}" style="width:120px;" /></td>
						<td class="td_01">客户名称</td>
						<td class="td_02"><input type="text" name="sdlqd.customername" value="${sendgoodsform.sdlqd.customername}" style="width:240px;" /></td>
					</tr>
					<tr>
						<td class="td_01">申请起始日期</td>
						<td class="td_02">
							<input type="text" name="sdlqd.startapplydate" maxlength="12"
									style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${sendgoodsform.sdlqd.startapplydate}" />
						</td>
						<td class="td_01">申请终止日期</td>
						<td class="td_02">
							<input type="text" name="sdlqd.endapplydate" maxlength="12"
									style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${sendgoodsform.sdlqd.endapplydate}" />
						</td>
					</tr>
					<tr>
						<td class="td_01">发货起始日期</td>
						<td class="td_02">
							<input type="text" name="sdlqd.startsendgoodsdate" maxlength="12"
									style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${sendgoodsform.sdlqd.startsendgoodsdate}"/>
						</td>
						<td class="td_01">发货终止日期</td>
						<td class="td_02">
							<input type="text" name="sdlqd.endsendgoodsdate" maxlength="12"
									style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${sendgoodsform.sdlqd.endsendgoodsdate}"/>
						</td>
					</tr>
					<tr>
						<td class="td_01">要求到帐起始日期</td>
						<td class="td_02">
							<input type="text" name="sdlqd.startrequestdate" maxlength="12"
									style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${sendgoodsform.sdlqd.startrequestdate}"/>
						</td>
						<td class="td_01">要求到帐终止日期</td>
						<td class="td_02">
							<input type="text" name="sdlqd.endrequestdate" maxlength="12"
									style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${sendgoodsform.sdlqd.endrequestdate}"/>
						</td>
					</tr>
					<tr>
						<td class="td_01">人员名称</td>
						<td class="td_02"><input type="text" name="sdlqd.sgusername" value="${sendgoodsform.sdlqd.sgusername}" style="width:120px;" /></td>
						<td class="td_01">发货单类型</td>
						<td class="td_02">
							<html:select property="sdlqd.sgsgtype" value="${sendgoodsform.sdlqd.sgsgtype}" style=" width:126px">
								<html:option value="">--请选择--</html:option> 
								<html:option value="0">备货</html:option> 
								<html:option value="1">发货</html:option> 
							</html:select>
						</td>
					</tr>
					<tr>
						<td class="td_01" width="20%">发货单状态</td>
						<td class="td_02" width="30%">
						<html:select property="sdlqd.sgstatus" value="${sendgoodsform.sdlqd.sgstatus}" style=" width:126px">
							<html:option value="">--请选择--</html:option>
							<html:option value="1">未提交</html:option>
							<html:option value="2">待发货</html:option>
							<html:option value="3">发货中</html:option>
							<html:option value="4">发货异常</html:option>
							<html:option value="5">发货成功</html:option>
							<html:option value="10">区域总监待评审</html:option>
							<html:option value="11">区域总监未通过</html:option>
							<html:option value="6">销售总监待评审</html:option>
							<html:option value="7">销售总监未通过</html:option>
							<html:option value="8">备货成功</html:option>
							<html:option value="9">备货超期</html:option>
						</html:select>
						</td>
						<td class="td_01">项目名称</td>
						<td class="td_02"><input type="text" name="sdlqd.contractProName" value="${sendgoodsform.sdlqd.contractProName}" style="width:120px;" /></td>
					<tr>
						<td colspan="4" align="center" style="height:30px;"><a id="btnJS" href=#><img src="${ctx}/images/btn_JianSuo.gif" /></a></td>
					</tr>
				</table>
          </div>
          <div class="mo_title" onclick="fMainListToggle(this)">
			<div style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
		  </div>
		  </form>
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
      	<th nowrap="nowrap">发货单号</th>
      	<th nowrap="nowrap">库房名称</th>
      	<th nowrap="nowrap">产品分类名称</th>
      	<th nowrap="nowrap">产品合同号</th>
      	<th nowrap="nowrap">公司合同号</th>
      	<th nowrap="nowrap">客户名称</th>
		<th nowrap="nowrap">项目名称</th>
      	<th nowrap="nowrap">发货金额</th>
      	<th nowrap="nowrap">备货金额</th>
      	<th nowrap="nowrap">退货金额</th>
      	<th nowrap="nowrap">申请日期</th>
      	<th nowrap="nowrap">发货日期</th>
      	<th nowrap="nowrap">&nbsp;要求到帐日期&nbsp;</th>
      	<th nowrap="nowrap">&nbsp;人员名称&nbsp;</th>
      	<th nowrap="nowrap">&nbsp;发货单类型&nbsp;</th>
      	<th nowrap="nowrap">&nbsp;发货单状态&nbsp;</th>
      	<th nowrap="nowrap">&nbsp;操作&nbsp;</th>
      </tr>
        <logic:present name="sendGoodsList">
			<logic:iterate id="sendgoods" name="sendGoodsList" >
      <tr>
      	<td nowrap="nowrap" width="76px"  height="18px">${sendgoods.sgid}&nbsp;</td>
      	<td nowrap="nowrap" width="100px" title="${sendgoods.sgskname}"><div class=ellipsis_div style="width:90px;">${sendgoods.sgskname}&nbsp;</div></td>
      	<td nowrap="nowrap" width="72px" >${sendgoods.sgptname}&nbsp;</td>
      	<td nowrap="nowrap" title="${sendgoods.sgpcontractcode}"><div class=ellipsis_div style="width:73px;">${sendgoods.sgpcontractcode}&nbsp;</div></td>
      	<td nowrap="nowrap" title="${sendgoods.sgccontractcode}"><div class=ellipsis_div style="width:96px;">${sendgoods.sgccontractcode}&nbsp;</div></td>
      	<td nowrap="nowrap" width="75" title="${sendgoods.sgcustomername}"><div class=ellipsis_div style="width:70px;">${sendgoods.sgcustomername}&nbsp;</div></td>
		<td nowrap="nowrap" width="75" title="${sendgoods.contractProName}">
			<div class=ellipsis_div style="width:70px;">${sendgoods.contractProName}&nbsp;</div>
		</td>
      	<td nowrap="nowrap" style=" text-align:right;" title="<fmt:formatNumber value="${sendgoods.sendgoodsmoney}" pattern="#,##0.00#"/>"><div class=ellipsis_div style="width:86px;"><fmt:formatNumber value="${sendgoods.sendgoodsmoney}" pattern="#,##0.00#"/>&nbsp;</div></td>
      	<td nowrap="nowrap" style=" text-align:right;" title="<fmt:formatNumber value="${sendgoods.stockgoodsmoney}" pattern="#,##0.00#"/>"><div class=ellipsis_div style="width:86px;"><fmt:formatNumber value="${sendgoods.stockgoodsmoney}" pattern="#,##0.00#"/>&nbsp;</div></td>
      	<td nowrap="nowrap" style=" text-align:right;" title="<fmt:formatNumber value="${sendgoods.sgbackmoney}" pattern="#,##0.00#"/>"><div class=ellipsis_div style="width:86px;"><fmt:formatNumber value="${sendgoods.sgbackmoney}" pattern="#,##0.00#"/>&nbsp;</div></td>
      	<td nowrap="nowrap" width="73px"  >${sendgoods.sgdate}&nbsp;</td>
      	<td nowrap="nowrap" width="73px" >${sendgoods.sgsenddate}&nbsp;</td>
      	<td nowrap="nowrap" width="84px" >${sendgoods.sgrqartermdate}&nbsp;</td>
      	<td nowrap="nowrap" width="84px"  >${sendgoods.sgusername}&nbsp;</td>
      	<td  nowrap="nowrap">${sendgoods.sgsgtypestr}&nbsp;</td>
      	<td  nowrap="nowrap">${sendgoods.sgstatusstr}&nbsp;</td>
      	<td nowrap="nowrap" >
      	<!--发货查看-->
      	<c:if test="${sendgoods.sgsgtype==1}">
      		<c:if test="${roleId!=12&&roleId!=13}">
	      		<a href="javascript:window.location = '${ctx}/getSendGoodsView.do?id=${sendgoods.sgid}';">查看</a>
	      	</c:if>   
      		<c:if test="${roleId==12||roleId==13}">
	      		<c:if test="${sendgoods.sgstatus==2||sendgoods.sgstatus==3||sendgoods.sgstatus==4||sendgoods.sgstatus==5}">
		      		<a href="javascript:window.location = '${ctx}/getSendGoodsView.do?id=${sendgoods.sgid}';">查看</a>
		      	</c:if>
		      	<c:if test="${sendgoods.sgstatus!=2&&sendgoods.sgstatus!=3&&sendgoods.sgstatus!=4&&sendgoods.sgstatus!=5}">
		      		查看&nbsp;
		      	</c:if>
	      	</c:if>	  		
      	</c:if>
      	
      	<!--备货查看-->
      	<c:if test="${sendgoods.sgsgtype==0}">
	      	<c:if test="${roleId!=12&&roleId!=13}">
	      		<a href="javascript:window.location = '${ctx}/getSendgoosViewPrepare.do?id=${sendgoods.sgid}';">查看</a>
	      	</c:if>
	      	<c:if test="${roleId==12||roleId==13}">
	      		<c:if test="${sendgoods.sgstatus==2||sendgoods.sgstatus==3||sendgoods.sgstatus==4||sendgoods.sgstatus==5}">
		      		<a href="javascript:window.location = '${ctx}/getSendgoosViewPrepare.do?id=${sendgoods.sgid}';">查看</a>
		      	</c:if>
		      	<c:if test="${sendgoods.sgstatus!=2&&sendgoods.sgstatus!=3&&sendgoods.sgstatus!=4&&sendgoods.sgstatus!=5}">
		      		查看
		      	</c:if>
	      	</c:if>
      	</c:if>
      	
      	<!--修改 、 删除、 查看运单 、退货-->
      	<c:if test="${roleId==4||roleId==7||roleId==5||roleId==9||roleId==19||roleId==20}">
      		<c:if test="${roleId==4}">
      			<!--修改-->
      			<c:if test="${sendgoods.sgstatus==1||sendgoods.sgstatus==7||sendgoods.sgstatus==4||sendgoods.sgstatus==9||sendgoods.sgstatus==11}">
      				<c:if test="${sendgoods.sgsgtype==0}">
      					<a href="javascript:window.location = '${ctx}/modifyReserveGoods.do?id=${sendgoods.sgid}'">修改</a>
      				</c:if>
      				<c:if test="${sendgoods.sgsgtype==1}">
      					<a href="javascript:window.location = '${ctx}/modifySendGoods.do?id=${sendgoods.sgid}'">修改</a>
      				</c:if>
      			</c:if>
      			<c:if test="${sendgoods.sgstatus!=1&&sendgoods.sgstatus!=7&&sendgoods.sgstatus!=4&&sendgoods.sgstatus!=9&&sendgoods.sgstatus!=11}">
      				修改
      			</c:if>
      			
      			<!--删除-->
      			<c:if test="${sendgoods.sgstatus==1||sendgoods.sgstatus==7||sendgoods.sgstatus==4||sendgoods.sgstatus==8||sendgoods.sgstatus==9||sendgoods.sgstatus==11}">
		      		<a href="javascript:deleteSend('${sendgoods.sgid}','${sendgoods.sgstatus}','${sendgoods.sgstatus}');">删除</a>
		      	</c:if>
		      	<c:if test="${sendgoods.sgstatus!=1&&sendgoods.sgstatus!=7&&sendgoods.sgstatus!=4&&sendgoods.sgstatus!=8&&sendgoods.sgstatus!=9&&sendgoods.sgstatus!=11}">
		      		删除
		      	</c:if>
		      	
		      	<!--退货-->
		      	<c:if test="${sendgoods.sgstatus==5&&sendgoods.sgsgtype==1}">
      				<a href="javascript:window.location = '${ctx}/getAddSaleReturnsInit.do?id=${sendgoods.sgid}'">退货</a>
      			</c:if>
      			<c:if test="${sendgoods.sgstatus!=5||sendgoods.sgsgtype!=1}">
      				退货
      			</c:if>
      		</c:if>
      		
      		<!--评审-->
	      	<c:if test="${sendgoods.sgsgtype==0}">
	     	 	<c:if test="${(sendgoods.sgstatus==6&&roleId==5)||(sendgoods.sgstatus==10&&roleId==9)}">
	     	 		<a href="javascript:window.location = '${ctx}/sendStockingAssessment.do?id=${sendgoods.sgid}'">评审</a>
	      		</c:if>
	      		<c:if test="${(sendgoods.sgstatus!=6&&roleId==5)||(sendgoods.sgstatus!=10&&roleId==9)}">
	     	 		评审
	      		</c:if>
	      	</c:if>
	      	<!--查看运单-->
	      	<c:if test="${(roleId==4||roleId==9||roleId==19||roleId==20)&&sendgoods.sgstatus==5}">
	     	 	<a href="#" onclick="javascript:returnBox('${sendgoods.sgid}');" >查看运单</a>
	      	</c:if>
	      	<c:if test="${(roleId==4||roleId==9||roleId==19||roleId==20)&&sendgoods.sgstatus!=5}">
	     	 	查看运单
	      	</c:if>
	      	
	    </td> 	
	    </c:if>       	
    </tr>
    	</logic:iterate>
    </logic:present>
    </table>
<br />
        <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        	<tr>
       	  	 	<td align="right"><%@ include file="/jsp/common/newPage.jsp"%></td>
            </tr>
    </table>    </td>
    <td >&nbsp;</td>
  </tr>
</table>
</html>
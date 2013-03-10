<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>库存信息管理</title>
	<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
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
			$('#btnJianSuo').click(function(){
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
			
			$("#productTypeSelect").change(function(){  
				changeProduct(); 
			});
			
			changeProduct();
		}); 

		function changeProduct(){
			var selectValue = $("#productTypeSelect").val();
			 
			var options = "<option value=''>--请选择--</option>";
			$("#productSerieSelect").empty();
			 
			if(selectValue!=""){
				$.getJSON("${ctx}/getProductSerie.do?queryPara.productTypeId=" + selectValue +"&date="+Date(),  
					function(result){ 
						$.each(result,function(i,entry){
							var opt; 
							if('${queryPara.productSerieId}'!=""){
								if(entry['id']=='${queryPara.productSerieId}'){
									opt = '<option value="'+ entry['id'] + '" selected>' + entry["name"] + '</option>'
								}else{
									opt = "<option value='"+ entry['id'] + "'>" + entry['name'] + "</option>"
								}
							}else{
								opt = "<option value='"+ entry['id'] + "'>" + entry['name'] + "</option>"
							}
							options = options + opt; 
						});
						$("#productSerieSelect").append(options);
					} 
				);
			}else{
				$("#productSerieSelect").append(options);
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
    <td class="ye_header_left" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt; 库存信息管理</td>
    <td class="ye_header_right" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    	<div class="mo_wp">
          <div style="display: ; " class="mo_con" >
		  <html:form method="post" action="stock" styleId="frmConList">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                    <td class="td_01" width="20%">产品分类名称</td>
                    <td class="td_02" width="30%">
						<html:select styleId="productTypeSelect" property="queryPara.productTypeId" value="${queryPara.productTypeId}" style=" width:126px">
							<html:option value="">--请选择--</html:option> 
							<html:options collection="productTypeList" property="id" labelProperty="name"/>
						</html:select>
					</td>
                    <td class="td_01" width="20%">产品系列名称</td>
                    <td class="td_02" width="30%">
						<select name="queryPara.productSerieId" id="productSerieSelect"  style=" width:126px">
							<option value="">--请选择--</option>
						</select>
					</td>
                  </tr>
                  <tr>
                    <td class="td_01">产品编码 </td>
                    <td class="td_02"><input type="text" name="queryPara.productCode" value="${queryPara.productCode}" style="width:120px;" /></td>
                    <td class="td_01">产品名称</td>
                    <td class="td_02"><input type="text" name="queryPara.productName" value="${queryPara.productName}" style="width:120px;" /></td>
                  </tr>
                  <tr>
                    <td class="td_01">规格型号</td>
                    <td class="td_02"><input type="text" name="queryPara.productType" value="${queryPara.productType}" style="width:240px;" /></td>
                    <td class="td_01">备货分类</td>
                    <td class="td_02">
						<html:select property="queryPara.sendgoodsType" value="${queryPara.sendgoodsType}" style=" width:126px">
							<html:option value="">--请选择--</html:option>  
							<html:option value="1">有备货</html:option>
							<html:option value="2">无备货</html:option>
						</html:select>
					</td>
                  </tr>
                  <tr>
                    <td class="td_01">库房类型</td>
                    <td class="td_02">
						<html:select property="queryPara.stockroomType" value="${queryPara.stockroomType}" style=" width:126px">
							<html:option value="">--请选择--</html:option> 
							<html:option value="0">虚拟库</html:option> 
							<html:option value="1">正常库</html:option>
							<html:option value="2">索赔库</html:option>
						</html:select>
					</td>
                    <td class="td_01">库房名称</td>
                    <td class="td_02">
						<html:select property="queryPara.stockroomId" value="${queryPara.stockroomId}" style=" width:264px">
							<html:option value="">--请选择--</html:option> 
							<html:options collection="stockroomList" property="id" labelProperty="name"/>
						</html:select>
					</td>
                  </tr>
              <tr>
                <td colspan="4" align="center" style="height:30px;">
					<img id="btnJianSuo" src="${ctx}/images/btn_JianSuo.gif" />
				</td>
              </tr>
            </table>
			</html:form>
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
        <th nowrap="nowrap">&nbsp;产品分类名称&nbsp;</th>
        <th nowrap="nowrap">&nbsp;产品系列名称&nbsp;</th>
        <th nowrap="nowrap">&nbsp;产品编码&nbsp;</th>
        <th nowrap="nowrap">&nbsp;产品名称&nbsp;</th>
        <th nowrap="nowrap">&nbsp;规格型号&nbsp;</th>
		
        <th nowrap="nowrap">&nbsp;单位&nbsp;</th>
		<c:if test="${roleId==5||roleId==8||roleId==10||roleId==11||roleId==16||roleId==17||roleId==18}"> 
			 <th nowrap="nowrap">&nbsp;库存单价&nbsp;</th>
		</c:if>  

        <th nowrap="nowrap">&nbsp;库存总数&nbsp;</th>
        <th nowrap="nowrap">&nbsp;采购合同数&nbsp;</th>
        <th nowrap="nowrap">&nbsp;销售合同数&nbsp;</th>

        <th nowrap="nowrap">&nbsp;销售可用数&nbsp;</th>
        <th nowrap="nowrap">&nbsp;库存数&nbsp;</th>
        <th nowrap="nowrap">&nbsp;发货冻结数&nbsp;</th>
        <th nowrap="nowrap">&nbsp;备货数&nbsp;</th>
        <th nowrap="nowrap">&nbsp;发货可用数&nbsp;</th>

        <th nowrap="nowrap">&nbsp;操作&nbsp;</th>
      </tr>
	  <logic:iterate id="stock" name="stockList">
      <tr>
        <td nowrap="nowrap" width="81px" height="18">${stock.productTypeName}&nbsp;</td>
        <td nowrap="nowrap" width="87px">${stock.productSerieName}&nbsp;</td>
        <td nowrap="nowrap" width="96px">${stock.productCode}&nbsp;</td>
        <td nowrap="nowrap" title="${stock.productName}"><div class="ellipsis_div" style="width:80px;">${stock.productName}&nbsp;</div></td>
        <td nowrap="nowrap" title="${stock.productType}"><div class="ellipsis_div" style="width:80px;">${stock.productType}&nbsp;</div></td>

        <td nowrap="nowrap" width="50px" >${stock.productUnit}&nbsp;</td> 
		<c:if test="${roleId==5||roleId==8||roleId==10||roleId==11||roleId==16||roleId==17||roleId==18}"> 
			<td nowrap="nowrap" style="text-align:right;">${stock.stockPrice}&nbsp;</td>
		</c:if>
		<td nowrap="nowrap" width="84px" style="text-align:right;">${stock.stockNum}&nbsp;</td>

        <td nowrap="nowrap" width="74px" style="text-align:right;">${stock.buyContractCount}&nbsp;</td>
        <td nowrap="nowrap" width="64px" style="text-align:right;">${stock.sellContractCount}&nbsp;</td>
		
        <td nowrap="nowrap" width="64px" style="text-align:right;">${stock.sellUseCount}&nbsp;</td>

		<td nowrap="nowrap" width="72px" style="text-align:right;">${stock.stockroomNum}&nbsp;</td>

        <td nowrap="nowrap" width="64px" style="text-align:right;">${stock.stockSendLock}&nbsp;</td>
        <td nowrap="nowrap" width="64px" style="text-align:right;">${stock.stockPrepared}&nbsp;</td>
        <td nowrap="nowrap" width="80px" style="text-align:right;">${stock.sendGoodsUseCount}&nbsp;</td>
        

        <td nowrap="nowrap" width="40px">
			<a href="#" onclick="javascript:window.open('${ctx}/stockView.do?queryPara.productId=${stock.productId}&queryPara.stockroomType=${queryPara.stockroomType}&queryPara.stockroomId=${queryPara.stockroomId}','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=1000,height=400');">明细</a>
		</td>
      </tr> 
      </logic:iterate>
    </table><br />
  <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        	<tr>
           	  <td align="right" ><%@ include file="/jsp/common/newPage.jsp"%></td>
        </tr>
        </table>    </td>
    <td >&nbsp;</td>
  </tr>
</table>
 
</body>
</html>

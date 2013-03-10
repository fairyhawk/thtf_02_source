<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>供货商选择</title>
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
		-->
		</style>
		<script language="JavaScript"> 
		    $(document).ready(function(){
				if($("#xxxlist")){
					$("#xxxlist tr:odd").addClass("treven");
					$("#xxxlist tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
			});

			var arrAddress = new Array();
			//获取选中记录的信息
			function getOption(obj){
				var obj=obj.value;
				arrAddress = obj.split("#");
			};

			//检索供应商名称
			function searchSupplier(){
				var supplierName = $.trim($("#supplierName").val());
				//if(supplierName==null || supplierName==""){
				//	alert("请输入供应商名称");
					//return;
				//}

				document.forms[0].action = "getSuppliersList.do?supplierName="+encodeURI(supplierName,"UTF-8");
				$("#frmSupplier").submit();
			}

			//单击选择按钮时，将选中的信息传到父页面
			function toParent(){
				if(arrAddress==null || arrAddress==""){
				    alert("请选择供应商");
					return;
				}

				var supplierId       = arrAddress[0];
				var supplierName     = arrAddress[1];

				$("#supplierId",window.opener.document).attr("value",supplierId);
				$("#supplierName",window.opener.document).attr("value",supplierName);
                $("#td_supplierName",window.opener.document).html(supplierName);
				window.close();
			}

		</script>
</head>

<body>
<form action="" method="post" id ="frmSupplier">
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center">
   <tr>
    <td align="center"><br />
    	<div class="mo_wp">
          <div style="display: ; " class="mo_con" >
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                <td class="td_03" width="50%">供货商名称</td>
                <td class="td_04">
					<span class="td_02">
					  <input name="supplierName" type="text" id="supplierName" style="width:240px;"/>
					</span>
				</td>
              </tr>
              <tr>
                <td colspan="4" align="center" style="height:30px;"><a href="#" onClick="searchSupplier()"/><img src="${ctx}/images/btn_JianSuo.gif"/></td>
              </tr>
            </table>
          </div>
          <div class="mo_title" onclick="fMainListToggle(this)">
              <div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
          </div>
        </div>
	</td>
  </tr>
  <tr>
    <td align="center">
    <br/>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="xxxlist">
      <tr>
        <th nowrap="nowrap" width="30px">选择</th>
        <th nowrap="nowrap">供应商名称</th>
      </tr>
      <tr>
		<logic:present name="supplierList">
			<logic:iterate id="supplierList" name="supplierList">
				<tr>
					<td>
						<input type="radio" name="supplierRadio" id="radio"
							value="${supplierList.id}#${supplierList.name}" onclick="getOption(this);" />
					</td>
					<td width="150px">
						${supplierList.name}
					</td>
				</tr>
			</logic:iterate>
		</logic:present>
      </tr>
    </table>
    <br />
	<table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
		<tr>
			<td align="right" ><%@ include file="/jsp/common/newPage.jsp"%></td>
		</tr>
	</table>
	<table align="center">
		<tr>
		  <td height="45px" colspan="2" align="center" valign="bottom"><a href="javascript:toParent();"><img src="${ctx}/images/btnChoice.gif" width="65"  height="20" /></td>
		</tr>
	</table>    
    </td>
  </tr>
</table>
</form>
</body>
</html>

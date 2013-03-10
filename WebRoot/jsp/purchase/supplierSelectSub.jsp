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
					$("#xxxlist tr:even").addClass("treven");
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
				var supplierName = $("#supplierName").val();
				document.forms[0].action = "supplierList.do?supplierName="+encodeURI(supplierName,"UTF-8");
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
				var province         = arrAddress[2];
				var city             = arrAddress[3];
				var remitBankName    = arrAddress[4];
				var remitBankAccount = arrAddress[5];
				var invoiceType      = arrAddress[6];
				var taxRate          = arrAddress[7];
				var invoiceTypeOfHidden=invoiceType;
                if(invoiceType==0){
				    invoiceType = "普通";
				}else{
				    invoiceType = "增值税";
				}
				$("#supplierId",window.opener.document).val(supplierId);
				$("#supplierName",window.opener.document).html(supplierName);
				$("#supplierNameOfHidden",window.opener.document).val(supplierName);
				$("#province",window.opener.document).html(province);
				$("#city",window.opener.document).html(city);
				$("#remitBankName",window.opener.document).html(remitBankName);
				$("#remitBankAccount",window.opener.document).html(remitBankAccount);		
				$("#invoiceType",window.opener.document).html(invoiceType);	
				$("#taxRate",window.opener.document).html(taxRate+"%");
				$("#invoiceTypeOfHidden",window.opener.document).val(invoiceTypeOfHidden);	
				$("#taxRateOfHidden",window.opener.document).val(taxRate);	
				window.close();
				window.opener.getLinkMan(supplierId);
				
				if (supplierId != "" && 1==2){
				$.getJSON("${ctx}/getLinkmanBySuppplierId.do?supplierId="+supplierId, function(linkMan){
						$("#linkman",window.opener.document).empty();
						var  options = "<option value=''>--请选择--</option>";
					$.each(linkMan,function(i,entry){
						options = options + "<option value='"+ entry['id'] + "'>" + entry['name'] + "</option>";			
					});
					$("#tel",window.opener.document).text("");
					$("#fax",window.opener.document).text("");
					$("#linkman",window.opener.document).append(options);
					window.close();
					});
				}else{	
			  	  	$("#tel",window.opener.document).text("");
					$("#fax",window.opener.document).text("");
					$("#linkMan",window.opener.document).append("<option value=''>--请选择--</option>");
					
				}
				
			}

		// 根据供应商Id获取联系人
		function getLinkmanBySupplierId(){

			$.getJSON("${ctx}/getLinkmanBySuppplierId.do?supplierId=9&date="+Date(), function(linkMan){
				$("#testId").empty();
				var  options = "<option value=''>--请选择--</option>";
				$.each(linkMan,function(i,entry){
					options = options + "<option value='"+ entry['id'] + "'>" + entry['name'] + "</option>";
				});
				$("#testId").append(options);
			});
		}

		//根据联系人ID获取联系人信息
		function getLinkmanInfoById(){
			$.getJSON("${ctx}/getLinkmanById.do?id=7&date="+Date(), function(linkManInfo){
				alert(linkManInfo['tel']+" || "+ linkManInfo['fax']);
			});	
		}
		</script>
</head>

<body>
<form action="supplierList.do" method="post" id ="frmSupplier">
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
							value="${supplierList.id}#${supplierList.name}#${supplierList.province}#${supplierList.city}#${supplierList.remitBankName}#${supplierList.remitBankAccount}#${supplierList.invoiceType}#${supplierList.taxRate}" onclick="getOption(this);" />
					</td>
					<td>
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

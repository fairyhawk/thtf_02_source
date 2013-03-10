<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物流公司添加</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
<script type="text/javascript" src="${ctx}/js/ajax.js"></script>
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript" language="javascript">
	var currentSort;
	
		//要验证的表单是第n个表单
		var formId = 0;
		//控件名
		var checknNames = [  "names",		"dates",		"provinces",		"citys",		"addresss",				"linkmans",		"roles",		"tel",			"faxs",		"mobiles",		"mails",		"qqs",		"msns",		"remitBankNames",		"remitBankAccounts"];
		//提示语
		var descriptions = [ "物流公司名称",	"合作开始时间",	"省",				"市",			"通信地址",					"联系人姓名",		"职务",			"电话",		"传真",		"手机",			"Email",		"QQ",		"MSN",		"汇款银行名称",			"汇款银行帐号"];
		//是否非空验证,如果非空验证填写notnull,如果只验证 开头和结尾的空格填写 notspace，如果不需要非空验证传空参
		var checkNulls =   [ "notnull",		"notnull",		"notnull",			"notnull",		"notnull",					"notnull",		"notnull",		"notnull",		"",			"",				"",				"",			"",			"notnull",				"notnull"];
		//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,只能填字母和数字  “abcnum”，    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”,
		var checkTypes = 	[ "",			"",				"",					"",				"",								"",				"",				"phone",		"phone",	"num",			"email",		"num",		"email",	"",						""];
		//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
		var checkLengths = [ "40",			"",				"",					"",				"80",			 				"8",			"20",			"",				"",			"12",			"",				"12",		"",			"40",			"20"];
		
//下拉列表项改变时的操作
	function proChange(objVal){
	 createXMLHttpRequest();
	 document.getElementById("city").length=1;
	 XMLHttpReq.onreadystatechange=getAjaxStatus;
	 var url = "${ctx}/logistics.do?method=getCitysByProvinceId&provinceId="+encodeURI(objVal,"UTF-8");
	 XMLHttpReq.open("GET",url,true);
	 XMLHttpReq.send(null);
	}
//解析XML信息，添加城市
	function parseXML(xmlDoc){
		var cityName = xmlDoc.getElementsByTagName("cityName");
		var areaName = "";
		var citySel = document.getElementById("city");
		for(var i=0;i<cityName.length;i++){
			var opt = document.createElement("OPTION");
			opt.text = xmlDoc.getElementsByTagName("cityName")[i].firstChild.data;
			opt.value=xmlDoc.getElementsByTagName("cityName")[i].firstChild.data;
			citySel.add(opt);
			areaName = xmlDoc.getElementsByTagName("areaName")[0].firstChild.data;
		}
		document.getElementById("areaName").innerHTML=areaName;
	}
	function check(){
		if(checkForm()==false){
			return ;
		}
		var lname = document.getElementById("lname").value;
		var provinceId = document.getElementById("provinceId").value;
		var city = document.getElementById("city").value;
		var address = document.getElementById("address").value;
		 
		var linkname = document.getElementById("linkname").value;
		var role = document.getElementById("role").value;
		var tel = document.getElementById("tel").value;
		var bankname = document.getElementById("bankname").value;
		var account = document.getElementById("account").value;
		if(lname == "" ||provinceId == "" ||city == "" ||address == "" ||linkname == "" ||role == "" ||tel == "" ||bankname == "" ||account == "" ){
			alert("带*的不可以为空！");
		}else{
				document.forms[0].submit(); 
		}
	};
	function checks(){
			var err = document.getElementById("err").value;
			if(err==null||err==""){
				
			} 
			else if(err!= null){
				alert("添加失败！");
				}
		}
</script>
</head>
<body onload="checks();">
<html:form action="/logistics" method="post">
  <input type="hidden" name="method" value="addLogistics" />
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td class="ye_header_left">&nbsp;</td>
      <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" /> &nbsp;当前位置： 基础信息管理 &gt;&gt; 物流公司信息管理 &gt;&gt; 物流公司添加 </td>
      <td class="ye_header_right">&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><br />
        <table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
          <tr>
            <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;物流公司名称 </td>
            <td class="td_02" width="30%"><input type="text" name="names" maxlength="20" id="lname"
										style="width:240px;" />
              <input type="hidden" name="err" value="${err}" id="err" />
            </td>
            <td class="td_01" height="18px"> 区域 </td>
            <td class="td_02" id="areaName"></td>
          </tr>
          <tr>
            <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;省 </td>
            <td class="td_02"><label>
              <select name="provinces" id="provinceId"
											onchange="proChange(this.value);" style=" width:126px">
                <option value="" selected="selected"> --请选择-- </option>
                <logic:present name="provinceList">
                  <logic:iterate id="province" name="provinceList">
                    <option value="${province.name}"> ${province.name} </option>
                  </logic:iterate>
                </logic:present>
              </select>
              </label>
            </td>
            <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;市 </td>
            <td class="td_02"><label>
              <select name="citys" id="city" style=" width:126px">
                <option value="" selected="selected"> --请选择-- </option>
              </select>
              </label>
            </td>
          </tr>
          <tr>
            <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;通信地址 </td>
            <td class="td_02" width="30%"><input type="text" name="addresss" id="address" maxlength="40"
										style="width:240px;" />
            </td>
            <td class="td_01" width="20%">邮编 </td>
            <td class="td_02" width="30%"><input type="text" name="postcodes" id="postcode" maxlength="6"
										style="width:120px;" />
            </td>
          </tr>
          <tr>
            <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;合作开始时间 </td>
            <td class="td_02" width="30%"><input type="text" name="dates" maxlength="12"
										style="width:120px;" onfocus="calendar()" readonly="readonly" />
            </td>
            <td class="td_01">&nbsp;</td>
            <td class="td_02">&nbsp;</td>
          </tr>
        </table>
        <div class="div_tiao"> &nbsp;&nbsp;联系人信息 </div>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;联系人姓名 </td>
            <td class="td_02" width="30%"><input type="text" name="linkmans" id="linkname" maxlength="4"
										style="width:120px;" />
            </td>
            <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;职务 </td>
            <td class="td_02" width="30%"><input type="text" name="roles" id="role" maxlength="10"
										style="width:120px;" />
            </td>
          </tr>
          <tr>
            <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;电话 </td>
            <td class="td_02"><input type="text" name="tels" id="tel" maxlength="20"
											style="width:120px;" />
            </td>
            <td class="td_01"> 传真 </td>
            <td class="td_02"><input type="text" name="faxs" maxlength="20"
											style="width:120px;" />
            </td>
          </tr>
          <tr>
            <td class="td_01"> 手机 </td>
            <td class="td_02"><input type="text" name="mobiles" maxlength="12"
											style="width:120px;" />
            </td>
            <td class="td_01"> Email </td>
            <td class="td_02"><input type="text" name="mails" maxlength="30"
											style="width:240px;" />
            </td>
          </tr>
          <tr>
            <td class="td_01"> QQ </td>
            <td class="td_02"><input type="text" name="qqs" maxlength="12"
											style="width:120px;" />
            </td>
            <td class="td_01"> MSN </td>
            <td class="td_02"><input type="text" name="msns" maxlength="30"
											style="width:240px;" />
            </td>
          </tr>
        </table>
        <br />
        <div class="div_tiao"> &nbsp;&nbsp;汇款信息 </div>
        <table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="biao3">
          <tr>
            <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;开户行 </td>
            <td class="td_02" width="30%"><input type="text" name="remitBankNames" id="bankname"
											maxlength="40" style="width:240px;" />
            </td>
            <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;帐号 </td>
            <td class="td_02" width="30%"><input type="text" name="remitBankAccounts" id="account"
											maxlength="20" style="width:120px;" />
            </td>
          </tr>
        </table>
      <td>&nbsp;</td>
    <tr>
      <td></td>
      <td align="center" valign="bottom" height="50PX"><a href="javascript:check();"><img src="${ctx}/images/btnAdd.gif" /></a>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
      <a href="javascript:window.location = '${ctx}/logistics.do?method=logisticsAll';"><img src="${ctx}/images/btnBack.gif" width="65" height="20" /></a></td>
      <td>&nbsp;</td>
    </tr>
  </table>
</html:form>
</body>
</html>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>

<title>库房收货地址选择</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<style>
.treven {background-color: #f3fbff;}
.over {background-color: #ECECEC;}
</style>
</head>
<script type="text/javascript">
$(document).ready(function(){
if($("#table")){
			$("#table tr:odd").addClass("treven");
			$("#table tr").each(function(i){
				$(this).mouseover(function(){
					$(this).addClass("over");
				});
				$(this).mouseout(function(){
					$(this).removeClass("over");
				});
			});
		}
	$("#changeValue").click(function(){
		if($("input:checked").val()==null){alert("请选择地址！");return;}//提示没有选择提示信息
		var sname='${requestScope.name }';
		if(sname!=$("#storeRoomId option:selected",window.opener.document).text()){
			alert("库房已经更换，请重新添加产品信息");
			window.close();
			return;
		}
		var addressInfo=eval('('+$("#JsonData").text()+')');//转换成对象模式
		for(var i=0;i<addressInfo.resultCount;i++){//循环信息
			if($("input:checked").val()==addressInfo.rows[i].id){
				window.close();//关闭子窗口
				opener.getOpenerVal(addressInfo.rows[i]);//调父窗口的类，参数所选人的信息 类型为Object
			}
		}
	});
});

</script>
<body>
<br />
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center">
    <tr>
      <td align="center">
        <div class="mo_wp">
          <div style="display: ; " class="mo_con" >
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                <td class="td_03" width="50%" height="20px">库房名称</td>
                <td class="td_04" >${requestScope.name }
                </td>
              </tr>
            </table>
          </div>
          <div class="mo_title" onClick="fMainListToggle(this)">
            <div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
          </div>
        </div></td>
    </tr>
  </table><br/>
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center" class="biao1" id="table">
	<tr>
		<th width="30">
			选择
		</th>
		<th>
			货物接收单位名称
		</th>
		<th>
			发货地址
		</th>
		<th>
			邮编
		</th>
		<th>
			联系人
		</th>
		<th>
			电话
		</th>
		<th>
			手机
		</th>
	</tr>
	<tr>
		<logic:present name="storeRoomAddress">
			<logic:iterate id="storeRoomAddress" name="storeRoomAddress">
				<tr>
					<td>
						<input type="radio" name="cusAddessRadio" id="radio"
							value="${storeRoomAddress.stockRoomAddressId}" />
					</td>
					<td width="150px" title="${storeRoomAddress.goodsName}">
						${storeRoomAddress.goodsName}
					</td>
					<td title="${storeRoomAddress.goodsAddress}">
						${storeRoomAddress.goodsAddress}
					</td>
					<td width="40">
						${storeRoomAddress.postcode}
					</td>
					<td width="48px">
						${storeRoomAddress.linkman}
					</td>
					<td width="120px">
						${storeRoomAddress.tel}
					</td>
					<td width="72">
						${storeRoomAddress.mobile}
					</td>
				</tr>
			</logic:iterate>
		</logic:present>
	</tr>
</table>
  <br><br>
  <div style="text-align:center">
  	<a href="#" id="changeValue"><img src="${ctx}/images/btnChoice.gif" width="65" height="20" /></a>
  </div>
  <div id="JsonData" style="display:none">${requestScope.JsonData} </div>
</body>
</html>
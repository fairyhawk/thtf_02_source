<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>收货明细选择</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/contractUtil.js"></script>
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
		var disable =true;
		function getJsonDate(){
			var buyContractId=$("#buyContractId").val();//合同流水号
			var companyType=$("#typeOfAddress").val();//单位类型
			var companyId=$("#companyId").val();//单位编号
			var addressId=$("#idOfAddress").val();//地址编号
			var returnVlaue="{resultSum:"+$("#table1 input:[type='text'][value!=0]").length+",buyContractId:"+buyContractId+",companyType:"+companyType+",companyId:"+companyId+",addressId:"+addressId+",rows:[";
			$("#table1 input:[type='text'][value!=0]").each(function(){//循环
				var productId=$.trim($("#"+$(this).parent().parent().attr("id")+" td:nth-child(10)").text());//产品编号
				var selectCount=$(this).val();//选择数 收货数
				returnVlaue+="{selectCount:'"+selectCount+"',productId:"+productId+"},"	
				});
			returnVlaue=returnVlaue.substring(0,returnVlaue.lastIndexOf(","));
			returnVlaue+="]}";
			return returnVlaue;
		} 
			$(document).ready(function(){
				$("#selectReceive").click(function(){//点击添加
				if($("#typeOfAddress").val()==''){alert("请选择收货地址!");return;}
				if($("#table1 input:[type='text'][value!=0]").length==0){alert("选择数不能都为空！");return;}
				if($("#singlePorkerFace",window.opener.document).val()!='bingo'){alert("添加失败,收货信息页面已关闭,请重新打开!");window.close();return;}
				if(!disable){
					return;
				}
				disable=false;
				var returnVlaue=getJsonDate();
				$.ajax({//同步提交
					type:"post",
					async:false,
					url:"${ctx}/addReceiveOfBuyContract.do",
					data:"returnVlaue="+returnVlaue,
					success:function(html){
						disable=true;
						if(html=='true'){
							var newTableId="receiveAddressOfTable"+$("#typeOfAddress").val()+$("#idOfAddress").val()+$("#companyId").val();//指定唯一table
							//列表
						 	var returnVlaue="";
						 	$("#table1 input:[type='text'][value!=0]").each(function(i){
						 	var trList=$(this).parent().parent().wrapInner("<tr id='"+$(this).parent().parent().attr("id")+"'></tr>");//增加一个tr
							returnVlaue+=trList.html();
								});
							opener.addReceiveInfo($("#receiveAddressOfTable").html(),newTableId,returnVlaue);//调用父窗口方法
							window.close();
						}else{
							alert("添加失败，此地址已经存在");return;
						}
					}
				});
				});
				if($("#table1")){
					$("#table1 tr:even").addClass("treven");
					$("#table1 tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
				
			});

		//进入库房收货地址选择页面
		function selectStockroom(){
			window.open('${ctx}/getStockroomAddressList.do?','newwindow', popWindows(400));
		};

		//进入公司收货地址选择页面
		function selectCompany(){
			window.open('${ctx}/getCompanyAddressList.do?','newwindow', popWindows(410));
		};

		//进入客户收货地址选择页面
		function selectCustomer(){
			window.open('${ctx}/getCustomerAddressList.do?', 'newwindow',popWindows(450));
		};
function selectSumVerdict(obj){
	if(!CheckIfNum(obj.value,null)){
		alert("只能填写数字");obj.select();
		$("#"+obj.id).focus().attr("class","invalid");
		return;
		}
	if(obj.value=='' || obj.value*1==0){
			$("#"+obj.id).val("0");
			return;
		}
	var trid=$("#"+obj.id).parent().parent().attr("id");
	if($("#"+trid+" td:nth-child(6)").text()*1-$("#"+trid+" td:nth-child(7)").text()*1<obj.value){
		alert("数据不正确，选择数只能小于采购数-已选择数");obj.select();
		$("#"+obj.id).focus().attr("class","invalid");
		return;
	}
	$("#"+obj.id).attr("class","");
			
	}
function CheckIfNum(String,dot){//判断是否数字
		
			    var Letters = "1234567890"+dot;
			     var i;
			     var c;
			     var n=0;
			     for( i = 0; i < String.length; i ++ )
			     {
			          c = String.charAt( i );
				  if (Letters.indexOf( c ) < 0){
				     return false;
				     }
				     if(c=='.'){n+=1;}
				     if(dot!=null && n>1){
				     	return false;
				     }
			     }
			     return true;
			}
		</script>

</head>

<body>
<br />
<table border="0" cellpadding="0" cellspacing="0" width="96%" align="center">
	<tr>
      <td  align="center"><a href="#" onclick="javascript:selectStockroom();"><img src="${ctx}/images/btnKFSHDZ.gif" /></a></td>
      <td  align="center"><a href="#" onclick="javascript:selectCompany();"><img src="${ctx}/images/btnGSSHDZ.gif" /></a></td>
      <td  align="center"><a href="#" onclick="javascript:selectCustomer();"><img src="${ctx}/images/btnKHSHDZ.gif" /></a></td>
    </tr>
</table>
<br />
<table width="96%" border="0" cellpadding="0" cellspacing="0" class="biao3" align="center" id="receiveAddressOfTable">
  <tr>
    <td class="td_01">名称</td>
    <td colspan="3" class="td_02" id="receiveName"  name="receiveName" >&nbsp;</td>
  </tr>
  <tr>
    <td class="td_01">货物接收单位名称</td>
    <td colspan="3" class="td_02" id="goodsName"  name="goodsName">&nbsp;</td>
  </tr>
  <tr>
    <td class="td_01">发货地址</td>
    <td colspan="3" class="td_02" id="address" name="address">&nbsp;</td>
  </tr>
  <tr>
    <td class="td_01" >联系人</td>
    <td class="td_02" id="linkman" name="linkman">&nbsp;</td>
    <td class="td_01">邮编</td>
    <td class="td_02" id="postcode" name="postcode">&nbsp;</td>
  </tr>
  <tr>
    <td class="td_01">电话</td>
    <td class="td_02" id="tel" name="tel">&nbsp;</td>
    <td class="td_01">手机</td>
    <td class="td_02" id="mobile" name="mobile">&nbsp;</td>
  </tr>
</table>
  <input type="hidden" value="" name="typeOfAddress" id="typeOfAddress"/>
  <input type="hidden" value="" name="idOfAddress" id="idOfAddress"/>
  <input type="hidden" value="" name="companyId" id="companyId"/>
   <input type="hidden" value="${param.buyContractId }" name="buyContractId" id="buyContractId"/>	
  
<br />
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center" class="biao1" id="table1">
	<tr>
	  <th width="30">序号</th>
	  <th>产品编码</th>
	  <th>产品名称</th>
	  <th>规格型号</th>
	  <th>单位</th>
	  <th>采购数</th>
	  <th>已选择数</th>
	  <th>选择数</th>
  </tr>
  <logic:iterate id="companyAddressList" name="companyAddressList" indexId="index">
	<tr id="tr${companyAddressList.id}">
	  <td id="num" style='padding-right:5px;text-align:right'>${index+1}</td>
	  <td>${companyAddressList.prodCode}&nbsp;</td>
	  <td>${companyAddressList.prodName}&nbsp;</td>
	  <td>${companyAddressList.prodType}&nbsp;</td>
	  <td>${companyAddressList.prodUnit}&nbsp;</td>
	  <td style='padding-right:5px;text-align:right'>${companyAddressList.buyCount}&nbsp;</td>
	  <td id="receiveTotalCount" style='padding-right:5px;text-align:right'>${companyAddressList.receiveTotalCount}&nbsp;</td>
	  <td width="96px" style='padding-right:5px;text-align:right'>
	    <input name="receiveCount${companyAddressList.id}" type="text" value="0" id="receiveCount${companyAddressList.id}" style="width:86px;" onblur="selectSumVerdict(this)"/>
	  </td>
	  <td style="display:none">${companyAddressList.id}</td>
	  <td style="display:none">${companyAddressList.prodId}</td>
  	</tr>
  </logic:iterate>
</table>
<table align="center" width="96%">
    <tr>
      	<td height="45px" colspan="2" align="center" valign="bottom">
      		<a id="selectReceive" href="#"><img src="${ctx }/images/btnChoice.gif" width="65" height="20" /></a>
      	</td>
    </tr>
</table>
</body>
</html>

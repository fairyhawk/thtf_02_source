<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%> 
<html>
<head>
<title>用户登录7.0</title>
<style type="text/css">
<!--
html,body{
     margin:0px;
	 height:100%;
	 font-size:12px;
	 font-family:"宋体";
}
#main{
     position:absolute;
     width:546px;
	 height:245px;
	 left:50%;
     top:30%; 
	 margin-left:-273px;   
     margin-top:-122px;
	 
}
.nav {
	border:1px solid #83ace7;
}
.userText {
	font-size: 12px;
	font-weight: 600;
	color: #2A7FFF;
}
.titleStyle {
	font-family: "楷体_GB2312", "宋体", Arial, Helvetica, sans-serif;
	font-size: 20px;
	font-style: normal;
	font-weight: bold;
	color: #FFFFFF;
	height: 40px;
	background-image: url(images/sbgup.gif);
	background-repeat: repeat-x;
	background-color: #007FFF;
}
input.data {
   background-color: #FFFFFF;
   height: 21px;
   width: 180px;
   border: 1px solid #83ace7;
   font-family: "Arial";
   font-size: 12px;
   padding-top: 2px;
   padding-left: 3px;
}
.button {
	font-size: 12px;
	font-weight: bold;
	padding-right: 0px;
	padding-bottom: 3px;
	padding-left: 0px;
	margin: 1px;
	background-image:url(images/DL.gif);
	width:65px;
	height:24px;
	color: #ffffff;
	border:0px;
}
img{border:0px;}
-->
</style> 
</head>

<body> 
<div id="main">
<table cellpadding="0" cellspacing="0" class="nav" width="100%" height="100%">
<tr>
<td height="40px" align="center" class="titleStyle">同方政务系统科技公司信用管理【高级】系统 7.0<img id="codeimg" src="${ctx}/images/confirmimg" align="absmiddle"/></td>
</tr>
<tr>
<td class="loginBG" style=" background-image:url(images/loginBackground.jpg); background-repeat:no-repeat;" height="320">
<table cellpadding="0" cellspacing="0" width="500px"  align="center"> 
<tr>
<td><a href="${ctx}/login.do?method=login&username=luffy1&password=123456">luffy1 - 高级区域总监</a></td>
<td><a href="${ctx}/login.do?method=login&username=luffy2&password=123456">luffy2 - 大区经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
 <td><a href="${ctx}/login.do?method=login&username=sunxue&password=123456">孙雪 - 信息管理员</a></td>
<td><a href="${ctx}/login.do?method=login&username=wangzheng&password=123456">王峥 - 销售助理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=sunxinxin&password=123456">孙欣欣 - 销售助理</a></td>
<td><a href="${ctx}/login.do?method=login&username=liying&password=123456">李颖 - 销售助理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=zhaoguiju2&password=123456">赵桂菊 - 销售助理</a></td>
<td><a href="${ctx}/login.do?method=login&username=sunxue3&password=123456">孙雪 - 销售助理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=panlei&password=123456">潘蕾 - 销售助理</a></td>
<td><a href="${ctx}/login.do?method=login&username=houyingwei&password=123456">侯英伟 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=xuzhiyang&password=123456">徐志扬 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=shihaiying1&password=123456">史海鹰 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=liuwenbin&password=123456">刘文斌 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=gaoyeyu&password=123456">郜业玉 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=zoujianhui&password=123456">邹建辉 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=wentao&password=123456">文涛 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=qiuchunxia&password=123456">邱春霞 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=liufang&password=123456">刘芳 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=dongxianglian&password=123456">董香莲 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=zhujiaming&password=123456">朱家明 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=wangxin&password=123456">王欣 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=panqian&password=123456">潘倩 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=lishimei&password=123456">李实美 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=chenliang&password=123456">陈亮 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=zhenqi&password=123456">甄琦 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=wangwenzhuang&password=123456">王文壮 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=majialin&password=123456">马加林 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=leijianxun&password=123456">雷建勋 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=chenhuan&password=123456">陈欢 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=zhangxiaoxing&password=123456">张效星 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=wangjinrong&password=123456">王金荣 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=luomin&password=123456">罗敏 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=huchenli&password=123456">胡晨莉 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=yinzhongxiang&password=123456">殷中祥 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=tanlei1&password=123456">谭蕾 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=liyan&password=123456">李嫣 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=guogang&password=123456">郭刚 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=xiumiao&password=123456">徐淼 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=shengyizu&password=123456">盛忆祖 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=liuguo&password=123456">刘帼 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=gaochao&password=123456">高超 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=zouhong&password=123456">邹宏 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=wangyanhong&password=123456">王艳红 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=qianjianchuan&password=123456">钱建川 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=lishuhua&password=123456">李淑华 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=dingyunpeng&password=123456">丁云鹏 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=zhouweiping&password=123456">周伟萍 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=wangxiaogang&password=123456">王晓刚 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=panghua&password=123456">庞华 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=lilirong&password=123456">李立容 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=chenkaibing&password=123456">陈开兵 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=zhaoguiju1&password=123456">赵桂菊 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=wangrui&password=123456">王锐 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=luxuming&password=123456">陆徐明 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=kongdapeng&password=123456">孔大鹏 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=bailin&password=123456">白琳 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=yuhua&password=123456">余华 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=wangbing&password=123456">王兵 - 销售经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=lizhonghui&password=123456">李忠慧 - 销售经理</a></td>
<td><a href="${ctx}/login.do?method=login&username=wanghongquan&password=123456">王红权 - 销售总监</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=wuzhanchun&password=123456">吴战春 - 销售总监</a></td>
<td><a href="${ctx}/login.do?method=login&username=wangyuanzheng&password=123456">王远征 - 销售总监</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=liutao&password=123456">刘涛 - 销售总监</a></td>
<td><a href="${ctx}/login.do?method=login&username=wujianjun&password=123456">吴建军 - 销售总监</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=heyanyan&password=123456">和艳艳 - 信用专员</a></td>
<td><a href="${ctx}/login.do?method=login&username=yuyan1&password=123456">于燕 - 信用主管</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=zhaoguiju&password=123456">赵桂菊 - 采购专员</a></td>
<td><a href="${ctx}/login.do?method=login&username=sunxue2&password=123456">孙雪 - 采购专员</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=yangling&password=123456">杨玲 - 采购专员</a></td>
<td><a href="${ctx}/login.do?method=login&username=caijing&password=123456">蔡静 - 采购专员</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=lishuhua1&password=123456">李淑华 - 区域总监</a></td>
<td><a href="${ctx}/login.do?method=login&username=yinzhongxiang1&password=123456">殷中祥 - 区域总监</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=shihaiying&password=123456">史海鹰 - 区域总监</a></td>
<td><a href="${ctx}/login.do?method=login&username=qiuchunxia1&password=123456">邱春霞 - 区域总监</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=lishuhua2&password=123456">李淑华 - 区域总监</a></td>
<td><a href="${ctx}/login.do?method=login&username=bailin1&password=123456">白琳 - 区域总监</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=tanlei&password=123456">谭蕾 - 区域总监</a></td>
<td><a href="${ctx}/login.do?method=login&username=qiuchunxia2&password=123456">邱春霞 - 区域总监</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=liuhong&password=123456">刘洪 - 区域总监</a></td>
<td><a href="${ctx}/login.do?method=login&username=wanghongquan1&password=123456">王红权 - 产品总监</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=shiyan&password=123456">石岩 - 产品总监</a></td>
<td><a href="${ctx}/login.do?method=login&username=wangyuanzheng1&password=123456">王远征 - 产品总监</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=sunxue4&password=123456">孙雪 - 产品总监</a></td>
<td><a href="${ctx}/login.do?method=login&username=liutao1&password=123456">刘涛 - 产品总监</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=wujianjun1&password=123456">吴建军 - 产品总监</a></td>
<td><a href="${ctx}/login.do?method=login&username=huyitian&password=123456">胡怡甜 - 采购主管</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=sunxue1&password=123456">孙雪 - 采购主管</a></td>
<td><a href="${ctx}/login.do?method=login&username=wangzhiguo&password=123456">王治国 - 库房管理员</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=taoping&password=123456">陶平 - 库房管理员</a></td>
<td><a href="${ctx}/login.do?method=login&username=liuxiaoqi&password=123456">刘晓岐 - 库房管理员</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=wangfeineng&password=123456">汪飞能 - 库房管理员</a></td>
<td><a href="${ctx}/login.do?method=login&username=liuxueyin&password=123456">刘学银 - 库房管理员</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=wangyingwu&password=123456">王英武 - 库房主管</a></td>
<td><a href="${ctx}/login.do?method=login&username=shxh&password=123456">上海信辉 - 物流管理员</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=shsx&password=123456">上海思讯 - 物流管理员</a></td>
<td><a href="${ctx}/login.do?method=login&username=shjj&password=123456">上海家佳 - 物流管理员</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=bjsf&password=123456">北京顺丰 - 物流管理员</a></td>
<td><a href="${ctx}/login.do?method=login&username=shwe&password=123456">上海惟尔 - 物流管理员</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=shsf&password=123456">上海顺丰 - 物流管理员</a></td>
<td><a href="${ctx}/login.do?method=login&username=gzst&password=123456">广州速腾 - 物流管理员</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=bjjj&password=123456">北京捷杰 - 物流管理员</a></td>
<td><a href="${ctx}/login.do?method=login&username=mashuhua&password=123456">马淑华 - 法务专员</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=yuyan&password=123456">于燕 - 运营总监助理</a></td>
<td><a href="${ctx}/login.do?method=login&username=dengyu&password=123456">邓宇 - 运营总监</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
<td><a href="${ctx}/login.do?method=login&username=zhouxia&password=123456">周侠 - 总经理</a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr>
</table>
</div>  
</body>
</html>

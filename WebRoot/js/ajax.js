
var XMLHttpReq = false;
	//创建XMLHttpRequest对象 
function createXMLHttpRequest() {
	if (window.XMLHttpRequest) { //Mozilla 浏览器
		XMLHttpReq = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			}
			catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				}
				catch (e) {
					window.alert("\u521b\u5efaXMLHttpRequest\u5bf9\u8c61\u51fa\u9519" + e);
				}
			}
		}
	}
	if (!(XMLHttpReq)) {
		window.alert("\u521b\u5efaXMLHttpRequest\u5bf9\u8c61\u5f02\u5e38\uff01");
	}
}
//获取返回状态
function getAjaxStatus() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			parseXML(XMLHttpReq.responseXML);
		}
	}
}
//获取返回状态1(为了处理一个页面用到两次Ajax的情况)
function getAjaxStatus1() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			parseXML1(XMLHttpReq.responseXML);
		}
	}
}


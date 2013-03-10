package cn.com.thtf.egov.cms.util;

public class Function {
	public static String replaceHTML(String str){
		if(str==null)return str;
    	str=str.replaceAll("\"", "&quot;");
    	str=str.replaceAll("<", "&lt;");
    	str=str.replaceAll(">", "&gt;");
    	return str;
    }
	public static String replaceBr(String str){
		if(str==null)return str;
		str = str.replaceAll("\r\n", "<br>");
		str = str.replaceAll("\r", "<br>");
        str = str.replaceAll("\n", "<br>");
		str = str.replaceAll(" ", "&nbsp;");
    	return str;
    }
	public static String replaceNull(String str){
	    if(str==null||"".equals(str)){
	        return "/";
	    }
	    return str;
	}
	public static boolean isEmptyOrNull(String str){
	    if(str==null||"".equals(str)){
	        return true;
	    }
	    return false;
	}
}

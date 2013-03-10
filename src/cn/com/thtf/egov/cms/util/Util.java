/**
 * ClassName  Util
 *
 * History
 * Create User: Lubo
 * Create Date: 2009-6-1
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Util
 * 
 * @author Lubo
 * 
 */
public class Util {

    /** log */
    private static Logger log = LoggerFactory.getLogger(Util.class);
    // SimpleDateFormat 线程不安全
    // /** dateTimeFormat 2010-03-31 13:31:17 */
    // private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat(
    // "yyyy-MM-dd HH:mm:ss");
    //    
    // /** dateTimeFormat 2010-03-31 */
    // private static SimpleDateFormat dateFormat = new
    // SimpleDateFormat("yyyy-MM-dd");
    // /** formatShortDate 2010-03-31 */
    // private static SimpleDateFormat shortDateFormat = new
    // SimpleDateFormat("yyMMdd");

    private static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

    private static final String FORMAT_DATE = "yyyy-MM-dd";

    private static final String FORMAT_SHORTDATE = "yyMMdd";

    /**
     * 获取指定字符格式的当前日期
     * 
     * @return
     */
    public static String getDate() {
        return new SimpleDateFormat(FORMAT_DATE).format(new Date());
    }

    /**
     * 获取指定字符格式的当前时间
     * 
     * @return
     */
    public static String getDateTime() {
        return new SimpleDateFormat(FORMAT_DATETIME).format(new Date());
    }

    /**
     * 日期格式化
     * 
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return new SimpleDateFormat(FORMAT_DATE).format(date);
    }

    /**
     * 日期格式化
     * 
     * @param date
     * @return
     */
    public static String formatShortDate(Date date) {
        return new SimpleDateFormat(FORMAT_SHORTDATE).format(date);
    }

    /**
     * formatDate
     * 
     * @param date
     * @param str
     * @return
     */
    public static String formatDate(Date date, String str) {
        return new SimpleDateFormat(str).format(date);
    }

    /**
     * MD5校验
     * 
     * @param str
     *            未加密的数据
     * @param md5Str
     *            加密过的数据
     * @return
     */
    public static boolean isAuthorized(String str, String md5Str) {
        String md5Result = null;
        try {
            md5Result = CryptUtil.MD5(str);
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException", e);
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException", e);
        }
        return StringUtils.equals(md5Result, md5Str);
    }

    /**
     * encodeUrl
     * 
     * @param str
     * @return
     */
    public static String encodeUrl(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("encodeUrl", e);
            return null;
        }
    }

    /**
     * unEncodeUrl
     * 
     * @param str
     * @return
     */
    public static String decodeUrl(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("decodeUrl", e);
            return null;
        }
    }

    /**
     * 判断字符串是否为空
     * 
     * @author llw
     * 
     * @param s
     * @return true: 当字符串为空 false: 当字符串不为空
     */
    public static boolean isNullString1(String s) {

        return !(s != null ? s.trim().length() > 0 : false);
    }

    /**
     * dateAnd
     * 
     * @param adate
     *            时间
     * @param anddate
     *            要加多少天
     * @return 加完的天数
     */
    public static String dateAnd(String adate, Integer anddate) {
        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        DateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String returnDate = "";
        try {
            if (StringUtils.isNotEmpty(adate)) {
                date = df.parse(adate);
                calendar.setTime(date);
                calendar.add(Calendar.DATE, anddate);
                // date.setDate(date.getDate() + anddate);
                returnDate = datef.format(calendar.getTime());
                log.debug(returnDate);
            }
        } catch (Exception e) {
            log.error("date compute error!", e);
        }
        return returnDate;
    }

    /**
     * 拆分评审意见
     * 
     * @param ideaStr
     * @return
     */
    public Integer[] splitIdea(String ideaStr) {
        Integer[] str = new Integer[ideaStr.length()];
        try {
            for (int i = 0; i < ideaStr.length(); i++) {
                str[i] = Integer.parseInt(ideaStr.substring(i, i + 1).toString());
            }
        } catch (Exception e) {
            log.error("split id", e);
        }
        return str;
    }

    /**
     * 将字符串中的 '（单引号） 替换为\\ ’以防止sql注入, 同时要将 ibatis中的 like '%$XXX$%' 改为
     * concat('%',#customerName#,'%')
     * 
     * @param str
     *            原字符串
     * @return 合法的字符串
     */
    public static String replaceSQLInjectionCharacter(String str) {

        String correctString;

        if (StringUtils.isNotBlank(str)) {
            correctString = str.replaceAll("'", "\\\\'");
            correctString = correctString.replaceAll("%", "\\\\%");
            correctString = correctString.replaceAll("_", "\\\\_");
            return correctString;

        } else {
            return str;
        }
    }

    /**
     * 利率计算
     * 
     * @param sellUnitPrice
     *            销售单价
     * 
     *@param buyUnitPrice
     *            预计采购价
     * 
     *@param sellCount
     *            销售数量:如果计算毛利率将sellCount设为0，计算总利率将sellCount设为实际数。
     * 
     * @return 销售毛利率、销售总利率
     **/
    public static String getInterestRate(BigDecimal sellUnitPrice,
            BigDecimal buyUnitPrice, int sellCount) {

        // 销售毛利率
        BigDecimal grossRate = BigDecimal.ZERO;
        // 销售总利率
        // BigDecimal totalRate = BigDecimal.ZERO;
        if (BigDecimal.ZERO.compareTo(sellUnitPrice) == 0) {
            log.info("利率计算失败：分母(销售单价)为零");
            return null;
        }
        // 销售毛利率
        // if (sellCount == 0) {
        if (BigDecimal.ZERO.compareTo(sellUnitPrice) == 0) {
            grossRate = BigDecimal.ZERO;
        } else {
            sellUnitPrice = sellUnitPrice.setScale(8);
            grossRate = (sellUnitPrice.subtract(buyUnitPrice).divide(sellUnitPrice, 4,
                    BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal("100"));

            if (grossRate.compareTo(new BigDecimal("9999.99")) == 1) {
                grossRate = new BigDecimal("9999.99");
                log.warn("警告：销售毛利率大于9999.99");
            }
            if (grossRate.compareTo(new BigDecimal("-9999.99")) == -1) {
                grossRate = new BigDecimal("-9999.99");
                log.warn("警告：销售毛利率小于-9999.99");
            }
        }
        log.info("计算销售毛利率");
        return (String) numFormat(grossRate);
        /*
         * } else {// 总利率 if (BigDecimal.ZERO.equals(sellUnitPrice)) { totalRate
         * = BigDecimal.ZERO; } else { totalRate = (sellUnitPrice.multiply(new
         * BigDecimal(sellCount)) .subtract(buyUnitPrice.multiply(new
         * BigDecimal(sellCount)))) .divide(sellUnitPrice.multiply(new
         * BigDecimal(sellCount)), 4, BigDecimal.ROUND_HALF_UP).multiply(new
         * BigDecimal("100")); if (totalRate.compareTo(new
         * BigDecimal("9999.99")) == 1) { totalRate = new BigDecimal("9999.99");
         * log.warn("警告：销售总利率大于9999.99"); } if (totalRate.compareTo(new
         * BigDecimal("-9999.99")) == -1) { totalRate = new
         * BigDecimal("-9999.99"); log.warn("警告：销售总利率小于-9999.99"); } }
         * log.info("计算销售总利率"); return (String) numFormat(totalRate); }
         */
    }

    /**
     * numFormat
     * 
     * @param obj
     * @return
     */
    public static Object numFormat(Object obj) {
        return new DecimalFormat("0.00").format(obj);
    }

    /**
     * 将小写金额转成大写金额
     * 
     * @param value
     * @return
     */
    public static String changeConst(String value) {
        if (StringUtils.isBlank(value))
            return "零";
        int flag = 0;
        if (value.substring(0, 1).equals("-")) {
            flag = 1;
            value = value.substring(1);
        }
        @SuppressWarnings("unused")
        String strCheck, strArr, strFen, strDW, strNum, strBig, strNow;
        double d = 0;
        try {
            d = Double.parseDouble(value);
        } catch (Exception e) {
            return "数据" + value + "非法！";
        }

        strCheck = value + ".";
        int dot = strCheck.indexOf(".");
        if (dot > 13) {
            return "数据" + value + "过大，无法处理！";
        }

        try {
            int i = 0;
            strBig = "";
            strDW = "";
            strNum = "";
            long intFen = (long) (d * 100);
            strFen = String.valueOf(intFen);
            int lenIntFen = strFen.length();
            while (lenIntFen != 0) {
                i++;
                switch (i) {
                case 1:
                    strDW = "分";
                    break;
                case 2:
                    strDW = "角";
                    break;
                case 3:
                    strDW = "元";
                    break;
                case 4:
                    strDW = "拾";
                    break;
                case 5:
                    strDW = "佰";
                    break;
                case 6:
                    strDW = "仟";
                    break;
                case 7:
                    strDW = "万";
                    break;
                case 8:
                    strDW = "拾";
                    break;
                case 9:
                    strDW = "佰";
                    break;
                case 10:
                    strDW = "仟";
                    break;
                case 11:
                    strDW = "亿";
                    break;
                case 12:
                    strDW = "拾";
                    break;
                case 13:
                    strDW = "佰";
                    break;
                case 14:
                    strDW = "仟";
                    break;
                case 15:
                    strDW = "万";
                    break;
                }
                switch (strFen.charAt(lenIntFen - 1)) // 选择数字
                {
                case '1':
                    strNum = "壹";
                    break;
                case '2':
                    strNum = "贰";
                    break;
                case '3':
                    strNum = "叁";
                    break;
                case '4':
                    strNum = "肆";
                    break;
                case '5':
                    strNum = "伍";
                    break;
                case '6':
                    strNum = "陆";
                    break;
                case '7':
                    strNum = "柒";
                    break;
                case '8':
                    strNum = "捌";
                    break;
                case '9':
                    strNum = "玖";
                    break;
                case '0':
                    strNum = "零";
                    break;
                }
                // 处理特殊情况
                strNow = strBig;
                // 分为零时的情况
                if ((i == 1) && (strFen.charAt(lenIntFen - 1) == '0'))
                    strBig = "整";
                // 角为零时的情况
                else if ((i == 2) && (strFen.charAt(lenIntFen - 1) == '0')) { // 角分同时为零时的情况
                    if (!strBig.equals("整"))
                        strBig = "零" + strBig;
                }
                // 元为零的情况
                else if ((i == 3) && (strFen.charAt(lenIntFen - 1) == '0'))
                    strBig = "元" + strBig;
                // 拾－仟中一位为零且其前一位（元以上）不为零的情况时补零
                else if ((i < 7) && (i > 3) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) != '零') && (strNow.charAt(0) != '元'))
                    strBig = "零" + strBig;
                // 拾－仟中一位为零且其前一位（元以上）也为零的情况时跨过
                else if ((i < 7) && (i > 3) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) == '零')) {
                }
                // 拾－仟中一位为零且其前一位是元且为零的情况时跨过
                else if ((i < 7) && (i > 3) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) == '元')) {
                }
                // 当万为零时必须补上万字
                else if ((i == 7) && (strFen.charAt(lenIntFen - 1) == '0'))
                    strBig = "万" + strBig;
                // 拾万－仟万中一位为零且其前一位（万以上）不为零的情况时补零
                else if ((i < 11) && (i > 7) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) != '零') && (strNow.charAt(0) != '万'))
                    strBig = "零" + strBig;
                // 拾万－仟万中一位为零且其前一位（万以上）也为零的情况时跨过
                else if ((i < 11) && (i > 7) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) == '万')) {
                }
                // 拾万－仟万中一位为零且其前一位为万位且为零的情况时跨过
                else if ((i < 11) && (i > 7) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) == '零')) {
                }
                // 万位为零且存在仟位和十万以上时，在万仟间补零
                else if ((i < 11) && (i > 8) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) == '万') && (strNow.charAt(2) == '仟'))
                    strBig = strNum + strDW + "万零" + strBig.substring(1, strBig.length());
                // 单独处理亿位
                else if (i == 11) {
                    // 亿位为零且万全为零存在仟位时，去掉万补为零
                    if ((strFen.charAt(lenIntFen - 1) == '0')
                            && (strNow.charAt(0) == '万') && (strNow.charAt(2) == '仟'))
                        strBig = "亿" + "零" + strBig.substring(1, strBig.length());
                    // 亿位为零且万全为零不存在仟位时，去掉万
                    else if ((strFen.charAt(lenIntFen - 1) == '0')
                            && (strNow.charAt(0) == '万') && (strNow.charAt(2) != '仟'))
                        strBig = "亿" + strBig.substring(1, strBig.length());
                    // 亿位不为零且万全为零存在仟位时，去掉万补为零
                    else if ((strNow.charAt(0) == '万') && (strNow.charAt(2) == '仟'))
                        strBig = strNum + strDW + "零"
                                + strBig.substring(1, strBig.length());
                    // 亿位不为零且万全为零不存在仟位时，去掉万
                    else if ((strNow.charAt(0) == '万') && (strNow.charAt(2) != '仟'))
                        strBig = strNum + strDW + strBig.substring(1, strBig.length());
                    // 其他正常情况
                    else
                        strBig = strNum + strDW + strBig;
                }
                // 拾亿－仟亿中一位为零且其前一位（亿以上）不为零的情况时补零
                else if ((i < 15) && (i > 11) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) != '零') && (strNow.charAt(0) != '亿'))
                    strBig = "零" + strBig;
                // 拾亿－仟亿中一位为零且其前一位（亿以上）也为零的情况时跨过
                else if ((i < 15) && (i > 11) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) == '亿')) {
                }
                // 拾亿－仟亿中一位为零且其前一位为亿位且为零的情况时跨过
                else if ((i < 15) && (i > 11) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) == '零')) {
                }
                // 亿位为零且不存在仟万位和十亿以上时去掉上次写入的零
                else if ((i < 15) && (i > 11) && (strFen.charAt(lenIntFen - 1) != '0')
                        && (strNow.charAt(0) == '零') && (strNow.charAt(1) == '亿')
                        && (strNow.charAt(3) != '仟'))
                    strBig = strNum + strDW + strBig.substring(1, strBig.length());
                // 亿位为零且存在仟万位和十亿以上时，在亿仟万间补零
                else if ((i < 15) && (i > 11) && (strFen.charAt(lenIntFen - 1) != '0')
                        && (strNow.charAt(0) == '零') && (strNow.charAt(1) == '亿')
                        && (strNow.charAt(3) == '仟'))
                    strBig = strNum + strDW + "亿零" + strBig.substring(2, strBig.length());
                else
                    strBig = strNum + strDW + strBig;
                strFen = strFen.substring(0, lenIntFen - 1);
                lenIntFen--;
            }
            if (strBig.equals("整")) {
                strBig = "零元整";
            }
            if (flag == 1) {
                strBig = "负" + strBig;
            }
            return strBig;
        } catch (Exception e) {
            log.error("错误:", e);
            return "";
        }
    }

    /**
     * getFormatCurrency
     * 
     * @param money
     * @return
     */
    public static String getFormatCurrency(String money) {
        DecimalFormat fmt = new DecimalFormat("##,###,###,###,##0.00");
        String formattedMoney = null;
        double doubleMoney;
        try {
            doubleMoney = Double.parseDouble(money);
            formattedMoney = fmt.format(doubleMoney);
        } catch (Exception e) {
        }
        return formattedMoney;
    }

    /**
     * 替换textArea中的\r\n和空格
     * 
     * @param str
     *            textArea内容
     * @return string
     */
    public static String showTextAreaContents(String str) {

        if (StringUtils.isBlank(str)) {
            return "";
        }

        while (str.indexOf("\r\n") >= 0) {
            str = str.replace("\r\n", "<br>");// 替换回车符
        }

        while (str.indexOf(" ") >= 0) {
            str = str.replace(" ", "&nbsp;");// 替换空格
        }
        return str;
    }
}

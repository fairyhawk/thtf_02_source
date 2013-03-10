/**
 * ClassName  Constants
 *
 * History
 * Create User: Lubo
 * Create Date: 2009-11-18
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.constant;

import java.util.Date;

import cn.com.thtf.egov.cms.dto.ContractIdDto;

/**
 * 常量类
 * 
 * @author Lubo
 * 
 */
public abstract class Constants {

    /** 管理员 */
    public static final int ROLE_ADMINISTRATOR = 1;
    /** 信息管理员 */
    public static final int ROLE_INFO_MGR = 2;
    /** 销售助理 */
    public static final int ROLE_SALES_ASSISTANT = 3;
    /** 销售经理 */
    public static final int ROLE_SALES_MANAGER = 4;
    /** 销售总监 */
    public static final int ROLE_SALES_DIRECTOR = 5;
    /** 信用专员 */
    public static final int ROLE_CREDIT_COMMISSIONER = 6;
    /** 信用主管 */
    public static final int ROLE_CREDIT_CHARGE = 7;
    /** 采购专员 */
    public static final int ROLE_PROCUREMENT_COMMISSIONER = 8;
    /** 区域总监 */
    public static final int ROLE_REGIONAL_DIRECTOR = 9;
    /** 产品总监 */
    public static final int ROLE_PRODUCT_DIRECTOR = 10;
    /** 采购主管 */
    public static final int ROLE_PROCUREMENT_OFFICER = 11;
    /** 库房管理员 */
    public static final int ROLE_TREASURY_MANAGER = 12;
    /** 库房主管 */
    public static final int ROLE_TREASURY_HEAD = 13;
    /** 物流管理员 */
    public static final int ROLE_LOGISTICS_MANAGER = 14;
    /** 法务专员 */
    public static final int ROLE_COMPLIANCE_OFFICER = 15;
    /** 运营总监助理 */
    public static final int ROLE_AST_DIRECTOR_OF_OPERATIONS = 16;
    /** 运营总监 */
    public static final int ROLE_DIRECTOR_OF_OPERATIONS = 17;
    /** 总经理 */
    public static final int ROLE_GENERAL_MANAGER = 18;
    /** 区域经理 */
    public static final int ROLE_AREA_MANAGER = 19;
    /** 大区经理 */
    public static final int ROLE_BIGAREA_MANAGER = 20;

    /** 登陆错误提示 */
    public static final String ERR_LOGIN_TIPS = "用户名或密码错误！";
    /** 验证码错误提示 */
    public static final String ERR_LOGIN_CODE = "验证码错误！";

    /** 当前用户在职 */
    public static final String USER_ENABLE_ON = "1";

    /** 当前用户离职 */
    public static final String USER_ENABLE_OFF = "0";

    /** 当前用户离线 */
    public static final String USER_ONLINE_OFF = "0";

    /** 当前用户在线 */
    public static final String USER_ONLINE_ON = "1";
    /** 当前用户在线 */
    public static final Integer USER_ONLINE_ON_NEW = 1;

    /** 当前用户信息 */
    public static final String USERLOGIN = "USERLOGIN";

    /** SESSION KEY */
    public final static String USERINFO = "USERINFO";

    /** WORKPATH */
    public final static String WORKPATH = "/cms";

    /** REQUEST_PAGE_OFFSET */
    public final static String REQUEST_PAGE_OFFSET = "pager.offset";

    /** REQUEST_PAGE_SIZE */
    public final static String REQUEST_PAGE_SIZE = "pageSize";

    /** DEFAULT_PAGE_SIZE */
    public static String DEFAULT_PAGE_SIZE = null;

    /** 上传文件的格式 */
    public final static String FILETYPE[] = { ".txt", ".pdf", ".xml", ".rtf", ".doc",
            ".docx", "xls", "xlsx" };

    /**
     * 信用类型常量定义开始
     */
    public final static int CREDIT_TYPE1 = 1;// 非信用

    public final static int CREDIT_TYPE2 = 2;// 小额临时

    public final static int CREDIT_TYPE13 = 3;// 临时

    public final static int CREDIT_TYPE4 = 4;// 分销

    public final static int CREDIT_TYPE5 = 5;// 项目

    /**
     * 信用类型常量定义结束
     */

    // 销售合同静态变量定义开始
    /**
     * <pre>
     * 销售合同签订地点
     * 通过config.xml设定
     * </pre>
     */
    public static String SELL_PLACE = null;

    /**
     * <pre>
     * 销售合同保价期限
     * 通过config.xml设定
     * </pre>
     */
    public static Integer SELL_PROTECT = null;
    /**
     * <pre>
     * 销售合同验收期限
     * 通过config.xml设定
     * </pre>
     */
    public static Integer SELL_CHECK = null;
    /**
     * <pre>
     * 销售合同违约金标准
     * 通过config.xml设定
     * </pre>
     */
    public static String SELL_BREAK = null;
    /**
     * <pre>
     * 销售合同诉讼地
     * 通过config.xml设定
     * </pre>
     */
    public static String SELL_LAWSUIT = null;

    /**
     * 销售合同静态变量定义结束
     */

    /**
     * <pre>
     * 销售总监 和 销售总监助理权限评审条件 
     * 100万以上，销售总监评审；100万以下，销售总监助理评审
     * 通过config.xml设定
     * </pre>
     */
    public static String CONTRACT_MONEY = null;// 合同总额（100万以上，销售总监评审；100万以下，销售总监助理评审）

    /**
     * <pre>
     * 销售总监 和 销售总监助理权限评审条件 
     * 低于5%，销售总监评审；高于5%，销售总监助理评审
     * 通过config.xml设定
     * </pre>
     */
    public static String CONTRACT_RATE = null;// 销售合同总利率（低于5%，销售总监评审；高于5%，销售总监助理评审）

    /**
     * <pre>
     * 运营总监 和 运营总监助理权限评审条件 
     * 低于50万，运营总监助理评审；高于50万，运营总监评审
     * 通过config.xml设定
     * </pre>
     */
    public static String BUY_CONTRACT_MONEY = null; // 采购合同金额

    /**
     * <pre>
     * 运营总监 和 运营总监助理权限评审条件 
     * 低于10%，运营总监助理评审；高于10%，运营总监评审
     * 通过config.xml设定
     * </pre>
     */
    public static String PROD_GROWTH_RATE = null; // 产品增长率

    /**
     * 销售合同状态
     */
    // 未提交
    public final static Integer SELLSTATUS_NOSUBMIT = 1;

    // 产品总监待评审
    public final static Integer SELLSTATUS_PROMAJWAITCOM = 2;

    // 产品总监未通过
    public final static Integer SELLSTATUS_PROMAJNOPASS = 3;

    // 法务专员待评审
    public final static Integer SELLSTATUS_LEGALWAITCOM = 4;

    // 法务专员未通过
    public final static Integer SELLSTATUS_LEGALNOPASS = 5;

    // 区域总监待评审
    public final static Integer SELLSTATUS_AREAMAJWAITCOM = 16;

    // 区域总监未通过
    public final static Integer SELLSTATUS_AREAMAJNOPASS = 17;

    // 销售总监待评审
    public final static Integer SELLSTATUS_SELLMAJWAITCOM = 6;

    // 销售总监未通过
    public final static Integer SELLSTATUS_DELLMAJNOPASS = 7;

    // 运营助理待评审
    public final static Integer SELLSTATUS_OPEASSWAITCOM = 8;

    // 运营助理未通过
    public final static Integer SELLSTATUS_OPEASSNOPASS = 9;

    // 运营总监待评审
    public final static Integer SELLSTATUS_OPEMAJWAITCOM = 10;

    // 运营总监未通过
    public final static Integer SELLSTATUS_OPEMAJNOPASS = 11;

    // 待打印
    public final static Integer SELLSTATUS_WAITPRINT = 12;

    // 待确认
    public final static Integer SELLSTATUS_WAITCONFIRM = 13;

    // 合同生效
    public final static Integer SELLSTATUS_EFFECT = 14;

    /**
     * 销售退货合同状态
     */
    // 未提交
    public final static Integer BACKCONTRACT_STATUS_NOSUBMIT = 1;

    // 法务专员待评审
    public final static Integer BACKCONTRACT_STATUS_LEGALWAITCOM = 2;

    // 法务专员未通过
    public final static Integer BACKCONTRACT_STATUS_LEGALNOPASS = 3;
    
    // 区域总监待评审
    public final static Integer BACKCONTRACT_STATUS_AREAMAJWAITCOM = 12;

    // 区域总监未通过
    public final static Integer BACKCONTRACT_STATUS_AREAMAJNOPASS = 13;

    // 销售总监待评审
    public final static Integer BACKCONTRACT_STATUS_SELLMAJWAITCOM = 4;

    // 销售总监未通过
    public final static Integer BACKCONTRACT_STATUS_DELLMAJNOPASS = 5;

    // 运营总监待评审
    public final static Integer BACKCONTRACT_STATUS_OPEMAJWAITCOM = 6;

    // 运营总监未通过
    public final static Integer BACKCONTRACT_STATUS_OPEMAJNOPASS = 7;

    // 待打印
    public final static Integer BACKCONTRACT_STATUS_WAITPRINT = 8;

    // 待确认
    public final static Integer BACKCONTRACT_STATUS_WAITCONFIRM = 9;

    // 合同取消
    public final static Integer BACKCONTRACT_STATUS_CANCEL = 10;

    // 合同生效
    public final static Integer BACKCONTRACT_STATUS_EFFECT = 11;

    /**
     * 采购合同状态
     */
    // 未提交
    public final static Integer BUYCONTRACT_STATUS_NOSUBMIT = 1;

    // 法务专员待评审
    public final static Integer BUYCONTRACT_STATUS_LEGALWAITCOM = 2;

    // 法务专员未通过
    public final static Integer BUYCONTRACT_STATUS_LEGALNOPASS = 3;

    // 采购主管待评审
    public final static Integer BUYCONTRACT_STATUS_PROCUREMENTWAITCOM = 4;

    // 采购主管未通过
    public final static Integer BUYCONTRACT_STATUS_PROCUREMENTNOPASS = 5;

    // 运营助理待评审
    public final static Integer BUYCONTRACT_STATUS_OPEASSWAITCOM = 6;

    // 运营助理未通过
    public final static Integer BUYCONTRACT_STATUS_OPEASSNOPASS = 7;

    // 运营总监待评审
    public final static Integer BUYCONTRACT_STATUS_OPEMAJWAITCOM = 8;

    // 运营总监未通过
    public final static Integer BUYCONTRACT_STATUS_OPEMAJNOPASS = 9;

    // 待打印
    public final static Integer BUYCONTRACT_STATUS_WAITPRINT = 10;

    // 待确认
    public final static Integer BUYCONTRACT_STATUS_WAITCONFIRM = 11;

    // 合同取消
    public final static Integer BUYCONTRACT_STATUS_CANCEL = 12;

    // 合同生效
    public final static Integer BUYCONTRACT_STATUS_EFFECT = 13;

    /**
     * 事务编号开始
     */
    // 销售合同待评审
    public final static Integer SELL1 = 1;

    // 销售合同待打印
    public final static Integer SELL2 = 2;

    // 发货异常
    public final static Integer SELL3 = 3;

    // 备货待评审
    public final static Integer SELL4 = 4;

    // 开票待评审
    public final static Integer SELL5 = 5;

    // 开票待通知
    public final static Integer SELL6 = 6;

    // 退票待评审
    public final static Integer SELL7 = 7;

    // 销售退货合同待评审
    public final static Integer SELL8 = 8;

    // 销售退货合同待打印
    public final static Integer SELL9 = 9;

    // 退款待评审
    public final static Integer SELL10 = 10;

    // 退款待打印
    public final static Integer SELL11 = 11;

    // 退货待评审
    public final static Integer SELL12 = 12;

    // 采购合同待评审
    public final static Integer SELL13 = 13;

    // 采购合同待打印
    public final static Integer SELL14 = 14;

    // 入库单待评审
    public final static Integer SELL15 = 15;

    // 付款待评审
    public final static Integer SELL16 = 16;

    // 付款待打印
    public final static Integer SELL17 = 17;

    // 承兑付款提醒
    public final static Integer SELL18 = 18;

    // 采购退货合同待评审
    public final static Integer SELL19 = 19;

    // 采购退货合同待打印
    public final static Integer SELL20 = 20;

    // 返厂单待评审
    public final static Integer SELL21 = 21;

    // 移库单待评审
    public final static Integer SELL22 = 22;

    // 借出单待评审
    public final static Integer SELL23 = 23;

    // 归还单待评审
    public final static Integer SELL24 = 24;

    /**
     * 事务编号结束
     */
    /**
     * 发货单状态 常量定义开始
     */
    public final static Integer SEND1 = 1;// 未提交

    public final static Integer SEND2 = 2;// 待发货

    public final static Integer SEND3 = 3;// 发货中

    public final static Integer SEND4 = 4;// 发货异常

    public final static Integer SEND5 = 5;// 发货成功

    public final static Integer SEND6 = 6;// 备货待评审

    public final static Integer SEND7 = 7;// 备货未通过

    public final static Integer SEND8 = 8;// 备货成功

    /**
     * 发货单状态 常量定义结束
     */

    public final static String CONTRACT_TEMPLATE_DIR = "test"; // 销售合同模板文件路径

    /**
     * 图片验证的Session变量名
     */
    public static final String SESSION_CONFIRM_STR = "ConfirmWords";

    /**  */
    public static final String NEW_THISPAGE = "new_ThisPage";

    /** 操作成功时使用 */
    public static final String SUCCESS = "success";

    /** 操作失败时使用 */
    public static final String FAIL = "fail";

    /** 查看时使用 */
    public static final String VIEW = "view";

    /** 付款方式 现结 */
    public static final Integer PAYMENT_WAY_CURRENT = 0;
    /** 付款方式 信用 */
    public static final Integer PAYMENT_WAY_CREDIT = 1;
    /** 付款方式 信用现结 */
    public static final Integer PAYMENT_WAY_CURRENT_ANDCREDIT = 2;

    /** 回款指定类型 指定到合同 */
    public static final String RETURN_DETAIL_TOCONTRACT = "2";
    /** 回款指定类型 指定到明细 */
    public static final String RETURN_DETAIL_TODETAIL = "4";

    /** 上传、下载文件目录名 */
    public static String DIR_NAME = null;

    /** 销售合同 下载文件名前缀 */
    public static String DOWNLOAD_FILE_NAME_PREFIX_SELL = null;

    /** 销售退货合同 下载文件名前缀 */
    public static String DOWNLOAD_FILE_NAME_PREFIX_SELL_BACK = null;

    /** 采购合同 下载文件名前缀 */
    public static String DOWNLOAD_FILE_NAME_PREFIX_BUY = null;

    /** 采购退货合同 下载文件名前缀 */
    public static String DOWNLOAD_FILE_NAME_PREFIX_BUY_BACK = null;

    /** 发送邮件服务器的IP或URL */
    public static String MAIL_SERVER_HOST = null;

    /** 发送邮件服务器的端口 */
    public static String MAIL_SERVER_PORT = null;

    /** 简单协议 */
    public static String MAIL_SMTP_HOST = null;

    /** 发送授权认证 */
    public static String MAIL_SMTP_AUTH = null;

    /** 发送协议 */
    public static String MAIL_SEND_CONFER = null;

    /** 是否需要身份验证 */
    public static String AUTH_VALIDATE = null;

    /** 邮件发送者的地址 */
    public static String FROM_ADDRESS = null;

    /** 邮件接收者的地址 点对点发送 */
    // public static String TO_ADDRESS = null;

    /** 登陆邮件发送服务器的用户名 */
    public static String USER_NAME = null;

    /** 登陆邮件发送服务器的密码 */
    public static String PASSWORD = null;

    /** 邮件主题 */
    public static String SUBJECT = null;

    /** 发件人昵称 */
    public static String NICKNAME = null;

    /** 邮件格式 */
    public static String MAIL_TYPE = null;

    /** 编码方式 */
    public static String MAIL_TYPE_CODING = null;

    /** 宽限天数 */
    public static Integer EXTEND_EXCEED_DAYS = null;

    /** FreeMarker 模板路径 在StartUp中初始化 */
    public static String FM_TEMPLATEDIR = "template";
    /** FreeMarker 编码 */
    public static String FM_TEMPLATE_ENCODING = "utf-8";
    /** 财务邮件模版 */
    public static String FINA_TEMPLATE = null;

    /** K3发货单模版 */
    public static String K3_SENDGOODS_TEMPLATE = null;
    /** K3收件地址 */
    public static String K3_TO_ADDRESS = null;
    /** K3收件人名 */
    public static String K3_TO_NAME = null;
    /** K3入库单模版 */
    public static String K3_INCOMESTOCK_TEMPLATE = null;
    /** 发货异常邮件模版路径及文件名 */
    public static String ERR_SENDGOODS_TEMPLATE = null;
    /** 发货异常收件人姓名 */
    // public static String ERR_TO_NAME = null;

    /** 回款邮件模版路径及文件名 */
    public static String RETURN_TEMPLATE = null;
    /** 资金管理员收件地址 */
    public static String RETURN_TO_ADDRESS = null;
    /** 资金管理员收件人姓名 */
    public static String RETURN_NAME = null;

    /** 事物接收者权限ID */
    public static enum WORK_ROLEID {
        /** 库房管理员 */
        WORK_ROLEID_TREASURY_MANAGER,
        /** 销售助理 */
        WORK_ROLEID_SALES_ASSISTANT,
        /** 产品总监 */
        WORK_ROLEID_PRODUCT_DIRECTOR,
        /** 销售经理 */
        WORK_ROLEID_SALES_MANAGER,
        /** 销售总监 */
        WORK_ROLEID_SALES_DIRECTOR,

        /** 采购专员 */
        WORK_ROLEID_PROCUREMENT_COMMISSIONER,
        /** 采购主管 */
        WORK_ROLEID_PROCUREMENT_OFFICER,
        /** 法务专员 */
        WORK_ROLEID_COMPLIANCE_OFFICER,
        /** 运营总监助理 */
        WORK_ROLEID_AST_DIRECTOR_OF_OPERATIONS,
        /** 运营总监 */
        WORK_ROLEID_DIRECTOR_OF_OPERATIONS
    };

    /** 采购付款界定值 */
    public static String PURCHASE_MONEY = null;

    /** 合同及出、入库单最大ID */
    public static ContractIdDto CONTRACT_MAX_ID_DTO = null;

    public static Date DATE = null;

    /** 上传文件大小 */
    public static Integer MAX_POST_SIZE = null;

    /** 上传文件过大导致失败的错误提示 */
    public static String MAX_POST_SIZE_ERR_MSG = null;
}

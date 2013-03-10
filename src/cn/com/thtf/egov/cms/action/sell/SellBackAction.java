/**
 * ClassName  SellBackAction
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-5-31
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.sell;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.CustomerProInfoListDto;
import cn.com.thtf.egov.cms.dto.SellBackAddDto;
import cn.com.thtf.egov.cms.dto.SellBackAssessDto;
import cn.com.thtf.egov.cms.dto.SellBackForMreturnDto;
import cn.com.thtf.egov.cms.dto.SellBackListDto;
import cn.com.thtf.egov.cms.dto.SellBackSearchDto;
import cn.com.thtf.egov.cms.entity.CustomerLinkmanEntity;
import cn.com.thtf.egov.cms.entity.MreturnEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.sell.IBackInvoiceService;
import cn.com.thtf.egov.cms.iservice.sell.ISalesContractManagementService;
import cn.com.thtf.egov.cms.iservice.sell.ISellBackService;

/**
 * 销售退款管理
 * 
 * @author liuqg
 */

public class SellBackAction extends NewBaseAction {
	/** log */
	private static Logger log = LoggerFactory.getLogger(SellBackAction.class);

	/* ISellBackService */
	private ISellBackService sellBackService = null;

	/**
	 * 初始化检索
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zzx
	 */
	public ActionForward getSellBackList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("进入退款管理初始列表");

		sellBackService = (ISellBackService) getBean("sellBackServiceImpl");

		// 获取用户ID
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);
		// 分页
		NewPage newPage = getNewPage(request);
		newPage.setUrl("getSellBackList.do");

		List<SellBackListDto> sellBackListDtos = null;// 退票信息
		List<ProductTypeEntity> productTypeEntities = null;// 产品分类

		// 获取数据
		try {
			sellBackListDtos = sellBackService.getSellBackList(newPage, user);
			productTypeEntities = sellBackService.getProductType();
		} catch (Exception e) {
			log.error("获取退款一览信息错误：", e);
		}
		// 将取得的退票list放在request中
		request.setAttribute("sellBackList", sellBackListDtos);
		// 产品分类
		request.setAttribute("productEntitieList", productTypeEntities);
		request.setAttribute("NewPage", newPage);

		return mapping.findForward("success");

	}

	/**
	 * 根据查询条件检索
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zzx
	 */
	public ActionForward searchSellBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("检索退票一览");

		ISellBackService sellBackService = null;

		// 检索条件放到request中,保存画面检索条件
		DynaActionForm dform = (DynaActionForm) form;

		SellBackSearchDto sellBackSearchDto = (SellBackSearchDto) dform
				.get("searchSellBack");

		// 获取用户ID
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);
		// 将当前登录用户的ID和权限放在检索用DTO中
		sellBackSearchDto.setLoginId(user.getId());
		sellBackSearchDto.setRoleId(String.valueOf(user.getRoleId()));
		sellBackSearchDto.setUserAreaId(String.valueOf(user.getUserAreaId()));

		request.setAttribute("sellBackSearchDto", sellBackSearchDto);

		// 分页
		NewPage newPage = getNewPage(request);
		newPage.setUrl("searchSellBack.do");

		// 页面检索条件保存
		setNewPagePara(newPage, sellBackSearchDto);
		// 实现类
		sellBackService = (ISellBackService) getBean("sellBackServiceImpl");

		List<SellBackListDto> sellBackListDtos = null;// 退票信息

		List<ProductTypeEntity> productTypeEntities = null;// 产品分类

		// 获取数据
		try {
			sellBackListDtos = sellBackService.getSellBackListByCondition(
					newPage, sellBackSearchDto);
			productTypeEntities = sellBackService.getProductType();
		} catch (Exception e) {
			request.setAttribute("msg", "检索错误!");
			log.error("检索退票一览信息错误：", e);
		}
		// 将取得的list放在request中
		request.setAttribute("sellBackList", sellBackListDtos);
		// 产品分类
		request.setAttribute("productEntitieList", productTypeEntities);
		request.setAttribute("NewPage", newPage);

		return mapping.findForward("success");
	}

	/**
	 * 分页条件设置
	 * 
	 * @author zzx
	 * @param newPage
	 * @param sellBackSearchDto
	 * @return {@link Void}
	 */

	private void setNewPagePara(NewPage newPage,
			SellBackSearchDto sellBackSearchDto) {
		// 退款申请单号
		if (StringUtils.isNotEmpty(sellBackSearchDto.getId())) {
			newPage.setQuery("searchSellBack.id", sellBackSearchDto.getId());
		}
		// 回款流水号
		if (StringUtils.isNotEmpty(sellBackSearchDto.getReturnId())) {
			newPage.setQuery("searchSellBack.returnId", sellBackSearchDto
					.getReturnId());
		}
		// 客户名称
		if (StringUtils.isNotEmpty(sellBackSearchDto.getCustomerName())) {
			newPage.setQuery("searchSellBack.customerName", sellBackSearchDto
					.getCustomerName());
		}
		// 产品分类编号

		if (StringUtils.isNotEmpty(sellBackSearchDto.getProductTypeId())) {
			newPage.setQuery("searchSellBack.productTypeId", sellBackSearchDto
					.getProductTypeId());
		}
		// 凭证号
		if (StringUtils.isNotEmpty(sellBackSearchDto.getNumber())) {
			newPage.setQuery("searchSellBack.number", String
					.valueOf(sellBackSearchDto.getNumber()));
		}

		// 申请人名称
		if (StringUtils.isNotEmpty(sellBackSearchDto.getUserName())) {
			newPage.setQuery("searchSellBack.userName", sellBackSearchDto
					.getUserName());
		}
		// 确认人名称
		if (StringUtils.isNotEmpty(sellBackSearchDto.getConfirmName())) {
			newPage.setQuery("searchSellBack.confirmName", sellBackSearchDto
					.getConfirmName());
		}
		// 退款状态
		if (StringUtils.isNotEmpty(sellBackSearchDto.getStatus())) {
			newPage.setQuery("searchSellBack.status", sellBackSearchDto
					.getStatus());
		}
		// 退款方式
		if (StringUtils.isNotEmpty(sellBackSearchDto.getBackWay())) {
			newPage.setQuery("searchSellBack.backWay", sellBackSearchDto
					.getBackWay());
		}
		// 申请起始日期
		if (StringUtils.isNotEmpty(sellBackSearchDto.getDateStart())) {
			newPage.setQuery("searchSellBack.dateStart", sellBackSearchDto
					.getDateStart());
		}
		// 申请终止日期
		if (StringUtils.isNotEmpty(sellBackSearchDto.getDateEnd())) {
			newPage.setQuery("searchBackBack.dateEnd", sellBackSearchDto
					.getDateEnd());
		}

		// 退款起始日期
		if (StringUtils.isNotEmpty(sellBackSearchDto.getBackDateStart())) {
			newPage.setQuery("searchSellBack.backDateStart", sellBackSearchDto
					.getBackDateStart());
		}
		// 退款终止日期
		if (StringUtils.isNotEmpty(sellBackSearchDto.getBackDateEnd())) {
			newPage.setQuery("searchSellBack.backDateEnd", sellBackSearchDto
					.getBackDateEnd());
		}

	}

	/**
	 * 退款确认
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zzx
	 */
	public ActionForward confirmSellBack(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("进入退款确认action");

		sellBackService = (ISellBackService) getBean("sellBackServiceImpl");
		// 获取用户ID
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);
		String id = request.getParameter("sellBackId");
		// 销售退款dto
		SellBackListDto sellBack = new SellBackListDto();
		sellBack.setId(id);
		sellBack.setConfirmName(user.getName());
		sellBack.setStatus(8);
		boolean isSuccess = sellBackService.confirmSellBack(sellBack);
		if (isSuccess) {
			log.info("User:{},{} 确认退款成功", user.getId(), user.getName());
		} else {
			log.info("User:{},{} 确认退款失败", user.getId(), user.getName());
		}
		if (!isSuccess) {
			request.setAttribute("msg", "确认退款失败");
		}
		return mapping.findForward(Constants.SUCCESS);
	}

	/**
	 * 退款打印
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zzx
	 */
	public ActionForward printSellBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("进入退款打印action");
		
		sellBackService = (ISellBackService) getBean("sellBackServiceImpl");
		// 获取用户ID
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);
		String sellBackId = request.getParameter("sellBackId");
		
		ICommonService commonService = (ICommonService) this.getApplicationContext()
        .getBean("commonServiceImpl");

		String getStatus = commonService.getStatusById("sell_back", sellBackId);
		
		request.setAttribute("sellBackId", sellBackId);
		if ("6".equals(getStatus)) {
			WorkEntity work = new WorkEntity();
			work.setCount(-1);
			work.setWorkId(11);
			work.setUserId(user.getId());
			// 打印修改退款状态、给自己减少一个待办事务
			boolean isSuccess = sellBackService.printSellBack(sellBackId, work);
			if (isSuccess) {
				log.info("User:{},{} 打印退款成功", user.getId(), user.getName());
			} else {
				log.info("User:{},{} 打印退款失败", user.getId(), user.getName());
			}
			if (!isSuccess) {
				request.setAttribute("msg", "打印退款失败");
			}
		}
		return mapping.findForward(Constants.SUCCESS);
	}

  /**
     * 显示退款评审
     * 
     * @author 蒋名星
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward getShowSellBackAssess(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("进入显示退款评审");

        String sellBackId = (String) (request.getParameter("sellBackId") == null ? request
                .getAttribute("sellBackId")
                : request.getParameter("sellBackId"));

        // 获得spring的bean容器
        sellBackService = (ISellBackService) this.getApplicationContext().getBean(
                "sellBackServiceImpl");

        // 根据id获得退款评审信息
        SellBackAssessDto assessDto = sellBackService.getSellBackById(sellBackId.trim());
        if (assessDto == null) {
            request.setAttribute("msg", "退款评审显示失败!");
            return mapping.findForward("failure");
        }

        request.setAttribute("assessDto", assessDto); // 第一个Dto

        /* 显示回款信息 */
        SellBackForMreturnDto forMreturnDto = sellBackService
                .selectInfoByMreturnId(assessDto.getReturnId());
        if (forMreturnDto == null) {
            request.setAttribute("msg", "显示退款信息错误！");
            return mapping.findForward("failure");
        }
        /* 产品分类信用信息 */
        MreturnEntity mreturnEntity = new MreturnEntity();
        mreturnEntity.setCustomerId(assessDto.getCustomerId());
        mreturnEntity.setProductTypeId(assessDto.getProductTypeId());

        /* 产品分类预收金额合计 */
        forMreturnDto.setProdMoneySum(sellBackService
                .selectProdMoneySumByMreturnId(mreturnEntity));
        /* 产品分类可退款金额 */
        forMreturnDto.setProCanBackMoney(sellBackService
                .selectProdCanBackMoneyByMreturnId(mreturnEntity));

        request.setAttribute("sellBackDto", forMreturnDto); // 第二个Dto

        /* 产品分类信用信息 列表 */
        List<CustomerProInfoListDto> customerProInfoListDtos = sellBackService
                .selectCustomerProInfoList(mreturnEntity);

        request.setAttribute("customerProInfoListDtos", customerProInfoListDtos); // 第三个dto(list中)画面产品分类表格的显示

        /* 客户产品信用信息 */
        List<SellBackForMreturnDto> customerCreditList = sellBackService
                .selectCustomerCreditList(String.valueOf(assessDto.getCustomerId()));
        request.setAttribute("customerCreditList", customerCreditList);

        /* 客服可退款额度 */
        BigDecimal customerCanBackMoney = sellBackService.selectCustomerBackMoney(String
                .valueOf(assessDto.getCustomerId()));

        // getCustomerCanBackMoney(customerCreditList);

        request.setAttribute("customerCanBackMoney", customerCanBackMoney);

        request.getAttribute("sellBackAssessDto");

        /* 防止重复提交 */
        saveToken(request);

        return mapping.findForward("success");
    }

    /**
     * 退款评审
     * 
     * @author 蒋名星
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward getSellBackAssess(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("正在评审");
        /* 防止重复提交 */
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            request.setAttribute("msg", "请勿重复评审!");
            request.setAttribute("sellBackId", request.getParameter("sellBackAssess.id"));
            return mapping.findForward("failureToList");
        }

        // 获得用户id
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);

        String assessResult = request.getParameter("assessResult");

        String sellBackId = request.getParameter("sellBackAssess.id");
        request.setAttribute("sellBackId", sellBackId);

        DynaActionForm dform = (DynaActionForm) form;
        SellBackAssessDto sellBackAssessDto = (SellBackAssessDto) dform
                .get("sellBackAssess");

        sellBackService = (ISellBackService) this.getApplicationContext().getBean(
                "sellBackServiceImpl");

        ICommonService commonService = (ICommonService) this.getApplicationContext()
                .getBean("commonServiceImpl");

        String getStatus = commonService.getStatusById("sell_back", sellBackAssessDto
                .getId());

        int status = 0;
        boolean isSuccess = false;

        String roleId = String.valueOf(user.getRoleId());
        sellBackAssessDto.setRoleId(roleId);
        if ("5".equals(roleId)) {
            sellBackAssessDto.setSellMajName(user.getName());
            // 销售总监意见0/1
            // 防止重复提交评审
            if ("4".equals(getStatus) || "3".equals(getStatus)) {
                request.setAttribute("msg", "请勿重复提交评审!");
                return mapping.findForward("failureToList");
            }
            if ("pass".equals(assessResult)) {
                status = 4;
            } else if ("nopass".equals(assessResult)) {
                status = 3;
            }

            sellBackAssessDto.setStatus(status);
            sellBackAssessDto.setSellMajDate(new SimpleDateFormat("yyyy-MM-dd")
                    .format(new Date()));

            // 如果同意 修改状态并且给运营总监发待办事务
            if (status == 4) {
                // 给运营总监发待办事务
                WorkEntity work = new WorkEntity();
                work.setCount(1);
                work.setUserId(sellBackAssessDto.getOpeMajId());
                work.setWorkId(10);

                // 把销售总监的待办事务减一
                WorkEntity workself = new WorkEntity();
                workself.setCount(-1);
                workself.setUserId(user.getId());
                workself.setWorkId(10);

                isSuccess = sellBackService.updateSellBackAssess(sellBackAssessDto, work,
                        workself);
                if (isSuccess) {
                    log.info("User:{},{} 销售总监退款评审通过成功", user.getId(), user.getName());
                } else {
                    log.info("User:{},{} 销售总监退款评审通过失败", user.getId(), user.getName());
                }

            } else {
                WorkEntity workself = new WorkEntity();
                workself.setCount(-1);
                workself.setUserId(user.getId());
                workself.setWorkId(10);
                // 不同意只修改状态
                isSuccess = sellBackService.updateAssessNoWork(sellBackAssessDto,
                        workself);
                if (isSuccess) {
                    log.info("User:{},{} 销售总监退款评审未通过成功", user.getId(), user.getName());
                } else {
                    log.info("User:{},{} 销售总监退款评审未通过失败", user.getId(), user.getName());
                }
            }
        } else if ("17".equals(roleId)) {
            sellBackAssessDto.setOpeMajName(user.getName());
            // 运营总监意见0/1
            String logmsg = "";
            // 防止重复提交评审
            if ("6".equals(getStatus) || "5".equals(getStatus)) {
                request.setAttribute("msg", "请勿重复提交评审!");
                return mapping.findForward("failureToList");
            }
            if ("pass".equals(assessResult)) {
                status = 6;
                logmsg = "通过";
            } else if ("nopass".equals(assessResult)) {
                status = 5;
                logmsg = "未通过";
            }

            sellBackAssessDto.setStatus(status);
            sellBackAssessDto.setOpeMajDate(new SimpleDateFormat("yyyy-MM-dd")
                    .format(new Date()));

            // 如果同意 修改状态并且给销售助理发待办事务
            if (status == 6) {
                // 给销售助理发待办事务
                WorkEntity work = new WorkEntity();
                work.setCount(1);
                work.setUserId(sellBackAssessDto.getConfirmId());
                work.setWorkId(11);

                // 把运营总监的待办事务减一
                WorkEntity workself = new WorkEntity();
                workself.setCount(-1);
                workself.setUserId(user.getId());
                workself.setWorkId(10);

                isSuccess = sellBackService.updateSellBackAssess(sellBackAssessDto, work,
                        workself);
                if (isSuccess) {
                    log.info("User:{},{} 运营总监退款评审通过" + logmsg + "成功", user.getId(), user
                            .getName());
                } else {
                    log.info("User:{},{} 运营总监退款评审通过" + logmsg + "失败", user.getId(), user
                            .getName());
                }

            } else {
                WorkEntity workself = new WorkEntity();
                workself.setCount(-1);
                workself.setUserId(user.getId());
                workself.setWorkId(10);
                // 不同意只修改状态
                isSuccess = sellBackService.updateAssessNoWork(sellBackAssessDto,
                        workself);
                if (isSuccess) {
                    log.info("User:{},{} 运营总监退款评审未通过" + logmsg + "成功", user.getId(), user
                            .getName());
                } else {
                    log.info("User:{},{} 运营总监退款评审未通过" + logmsg + "失败", user.getId(), user
                            .getName());

                }
            }

        }
        request.setAttribute("sellBackAssessDto", sellBackAssessDto);
        if (!isSuccess) {
            request.setAttribute("msg", "评审失败");
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

	/**
	 * 销售退款删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 蒋名星
	 */
	public ActionForward deleteSellBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 获得用户id
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);

		// 退款号
		String sellBackId = request.getParameter("sellBackId");

		// 获得bean容器
		sellBackService = (ISellBackService) this.getApplicationContext()
				.getBean("sellBackServiceImpl");

		// 删除退款
		boolean isSuccess = sellBackService.removeSellBack(sellBackId);

		if (isSuccess) {
			log.info("User:{},{} 删除销售退款成功", user.getId(), user.getName());
			return mapping.findForward("success");
		} else {
			log.info("User:{},{} 删除销售退款失败", user.getId(), user.getName());
			request.setAttribute("msg", "删除销售退款失败");
			return mapping.findForward("failure");
		}

	}

	/**
	 * 退款申请时信息显示(根据回款ID)
	 * 
	 * @author liuqg
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward getInfoByMreturnId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("退款申请显示");
		/* 获得回款ID */
		String mreturnId = request.getParameter("id");
		if (mreturnId==null){
			mreturnId=(String)  request.getAttribute("id") ;
		}
		sellBackService = (ISellBackService) getBean("sellBackServiceImpl");
		/* 回款单信息获得 */
		MreturnEntity mreturnEntity = new MreturnEntity();
		mreturnEntity = sellBackService.selectMreturnById(mreturnId);

		if (mreturnEntity == null) {
			request.setAttribute("err", "获取回款信息失败");
			return mapping.findForward("failure");
		}

		/* 获得客户联系人信息 */
		List<CustomerLinkmanEntity> customerLinkmanList = sellBackService
				.selectCustomerInfoByMId(mreturnId);
		request.setAttribute("customerLinkmanList", customerLinkmanList);
		/* 取得回款信息和客户信息 */
		SellBackForMreturnDto forMreturnDto = sellBackService
				.selectInfoByMreturnId(mreturnId);

		if (forMreturnDto == null) {
			request.setAttribute("err", "显示退款申请信息错误！");
			return mapping.findForward("failure");
		}
		/* 产品分类信用信息 */

		/* 产品分类预收金额合计 */
		forMreturnDto.setProdMoneySum(sellBackService
				.selectProdMoneySumByMreturnId(mreturnEntity));
		/* 产品分类可退款金额 */
		forMreturnDto.setProCanBackMoney(sellBackService
				.selectProdCanBackMoneyByMreturnId(mreturnEntity));

		/* 产品分类信用信息 列表 */
		List<CustomerProInfoListDto> customerProInfoListDtos = sellBackService
				.selectCustomerProInfoList(mreturnEntity);
		request
				.setAttribute("customerProInfoListDtos",
						customerProInfoListDtos);
		request.setAttribute("sellBackDto", forMreturnDto);

		/* 防止重复提交 */
		saveToken(request);

		request.setAttribute("mreturnId", mreturnId);
		return mapping.findForward("success");
	}

	/**
	 * 保存提交退款申请
	 * 
	 * @author liuqg
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @returnActionForward
	 * @throws Exception
	 */
	public ActionForward addSellBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("退款申请");
		String subFlag = request.getParameter("subFlag");
		// 回款单号
		String mreturnId = request.getParameter("mreturnId");
		
		request.setAttribute("id",mreturnId);
		SellBackAddDto backAddDto = new SellBackAddDto();

		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			if ("save".equals(subFlag)) {
				request.setAttribute("msg", "请勿重复保存 !");
			} else {
				request.setAttribute("msg", "请勿重复提交!");
			}
			return mapping.findForward(Constants.SUCCESS);
		}
		// 获取画面值
		String customerLinkmanId = request.getParameter("customerLinkmanId");// 客户联系人编号

		String backWay = request.getParameter("backWay");// 退款方式
		String backMoney = request.getParameter("backMoney"); // 退款金额

		String text = request.getParameter("textarea"); // 特殊说明

		// 销售退款主键生成
		ICommonService commonService = (ICommonService) getBean("commonServiceImpl");
		backAddDto.setId(commonService.getSerialNumber("THK", "sell_back"));

		// 画面值赋值
		backAddDto.setCustomerLinkmanId(customerLinkmanId);
		backAddDto.setBackWay(backWay);
		backAddDto.setMoney(backMoney);
		backAddDto.setText(text);
		// 回款单信息获得
		MreturnEntity mreturnEntity = new MreturnEntity();

		sellBackService = (ISellBackService) getBean("sellBackServiceImpl");
		mreturnEntity = sellBackService.selectMreturnById(mreturnId);

		if (mreturnEntity == null) {
			request.setAttribute("err", "获取回款信息失败");
			return mapping.findForward("failure");
		}

		backAddDto.setReturnId(mreturnEntity.getId());// 回款流水号
		backAddDto.setProductTypeId(mreturnEntity.getProductTypeId());// 产品分类编号
		backAddDto.setCustomerId(mreturnEntity.getCustomerId());// 客户编号
		backAddDto.setCustomerName(mreturnEntity.getCustomerName());// 客户名称
		backAddDto.setNumber(mreturnEntity.getNumber());// 凭证号

		// 获得用户id
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);

		backAddDto.setUserId(user.getId());// 申请人
		backAddDto.setUserName(user.getName());// 申请人姓名
		// backAddDto.setUserAreaId(mreturnEntity.getUserAreaId());//人员区域编号
		backAddDto.setConfirmId(user.getId());// 确认人,销售助理自己确认。
		// 获得bean
		IBackInvoiceService backInvoiceService = (IBackInvoiceService) this
				.getBean("backInvoiceServiceImpl");
		// 销售总监登录名
		String sellMajId = backInvoiceService.selectSellMajId(String
				.valueOf(mreturnEntity.getProductTypeId()));

		backAddDto.setSell_majId(sellMajId);
		// 运营总监登录名
		backAddDto.setOpe_majId(backInvoiceService.selectOpeMajId(null));

		if ("save".equals(subFlag)) {
			backAddDto.setStatus("1");// 未提交
			if (sellBackService.insertSellBackSave(backAddDto)) {
				log.info("User:{},{} 退款保存成功", user.getId(), user.getName());
				return mapping.findForward("success");
			} else {
				log.info("User:{},{} 退款保存失败", user.getId(), user.getName());
				request.setAttribute("err", "退款保存失败！");
				return mapping.findForward("failure");
			}
		} else if ("submit".equals(subFlag)) {
			backAddDto.setStatus("2");// 销售总监待评审要发待办事务
			WorkEntity work = new WorkEntity();
			work.setUserId(sellMajId);
			work.setWorkId(10);// 退款待评审
			work.setCount(1);

			if (sellBackService.insertSellBackSubmit(backAddDto, work)) {
				log.info("User:{},{} 退款提交成功", user.getId(), user.getName());
				return mapping.findForward("success");
			} else {
				log.info("User:{},{} 退款提交失败", user.getId(), user.getName());
				request.setAttribute("err", "退款提交失败！");
				return mapping.findForward("failure");
			}

		} else {
			request.setAttribute("err", "非法提交");
			return mapping.findForward("failure");
		}

	}

	/**
	 * 根据联系人Id获取相应的联系人信息
	 * 
	 * @author liuqg
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward getLinkMsgByLinkManId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 取得画面选择的联系人ID
		String linkmanId = request.getParameter("customerLinkmanId");
		if (StringUtils.isNotEmpty(linkmanId)) {
			// 获取spring的service

			sellBackService = (ISellBackService) getBean("sellBackServiceImpl");

			ISalesContractManagementService salesContractService = (ISalesContractManagementService) getBean("salesContractManagementServiceImp");
			CustomerLinkmanEntity customerLinkman = null;
			try {
				customerLinkman = salesContractService
						.getLinkMsgByLinkManId(linkmanId);
				JSONArray array = new JSONArray();
				// 将 查到的所有类型转换成json格式
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("fax", customerLinkman.getFax());
				jsonObject.put("tel", customerLinkman.getTel());
				array.put(jsonObject);
				// 将json串写回客户端
				response.setCharacterEncoding("UTF-8");
				PrintWriter writer = response.getWriter();
				writer.write(array.toString());
				writer.close();

			} catch (Exception e) {

				log.error("查看联系人的传真和电话失败", e);
			}
		}
		return null;
	}

	/**
	 * 退款修改/查看时信息显示(根据退款ID)
	 * 
	 * @author liuqg
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward getInfoBySellBackId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("退款修改/查看时信息显示");

		/* 获得区分信息 modify 跳转到申请画面 view 跳转到查看画面 print 打印画面 */
		String type = request.getParameter("type");
		if (!(("modify".equals(type)) || ("view".equals(type)) || ("print"
				.equals(type)))) {
			request.setAttribute("msg", "非法提交");
			return mapping.findForward("failure");
		}
		// 下面是共同的显示的地方，显示时用到三个 dto
		/* 获得退款ID */
		String sellBackId = (request.getParameter("sellBackId"));
		if (sellBackId==null){
			sellBackId =(String) (request.getAttribute("sellBackId"));
		}
		request.setAttribute("sellBackId", sellBackId);

		/* 显示退款表信息 (客户信息/退货退款信息/特殊说明) */
		sellBackService = (ISellBackService) getBean("sellBackServiceImpl");
		SellBackAssessDto assessDto = sellBackService
				.getSellBackCustomerInfo(sellBackId);

		if (assessDto == null) {
			request.setAttribute("msg", "显示退款信息错误！");
			return mapping.findForward("failure");
		}
		request.setAttribute("assessDto", assessDto);// 第一个dto主要显示退款表中信息

		/* 显示回款信息 */
		SellBackForMreturnDto forMreturnDto = sellBackService
				.selectInfoByMreturnId(assessDto.getReturnId());
		if (forMreturnDto == null) {
			request.setAttribute("msg", "显示退款信息错误！");
			return mapping.findForward("failure");
		}
		/* 产品分类信用信息 */
		MreturnEntity mreturnEntity = new MreturnEntity();
		mreturnEntity.setCustomerId(assessDto.getCustomerId());
		mreturnEntity.setProductTypeId(assessDto.getProductTypeId());

		/* 产品分类预收金额合计 */
		forMreturnDto.setProdMoneySum(sellBackService
				.selectProdMoneySumByMreturnId(mreturnEntity));
		/* 产品分类可退款金额 */
		forMreturnDto.setProCanBackMoney(sellBackService
				.selectProdCanBackMoneyByMreturnId(mreturnEntity));

		request.setAttribute("sellBackDto", forMreturnDto);// 第二个dto主要显示跟回款有关的信息

		/* 产品分类信用信息 列表 */
		List<CustomerProInfoListDto> customerProInfoListDtos = sellBackService
				.selectCustomerProInfoList(mreturnEntity);
		request
				.setAttribute("customerProInfoListDtos",
						customerProInfoListDtos);// 第三个dto(list中)画面产品分类表格的显示

		if ("modify".equals(type)) { // 如果是修改时要获得客户联系人列表
			/* 获得客户联系人 */
			List<CustomerLinkmanEntity> customerLinkmanList = sellBackService
					.selectCustomerInfoByMId(assessDto.getReturnId());
			request.setAttribute("customerLinkmanList", customerLinkmanList);
		}

		/* 防止重复提交 */
		saveToken(request);

		return mapping.findForward(type);

	}

	/**
	 * 打印显示
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zzx
	 */
	public ActionForward getPrintInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("打印时信息显示");
		// 下面是共同的显示的地方，显示时用到三个 dto
		/* 获得退款ID */
		String sellBackId = (String) (request.getParameter("sellBackId") != null ? request
				.getParameter("sellBackId")
				: request.getAttribute("sellBackId"));
		request.setAttribute("sellBackId", sellBackId);

		/* 显示退款表信息 (客户信息/退货退款信息/特殊说明) */
		sellBackService = (ISellBackService) getBean("sellBackServiceImpl");
		SellBackAssessDto assessDto = sellBackService
				.getSellBackCustomerInfo(sellBackId);

		if (assessDto == null) {
			request.setAttribute("msg", "显示退款信息错误！");
			return mapping.findForward("failure");
		}
		request.setAttribute("assessDto", assessDto);// 第一个dto主要显示退款表中信息

		/* 显示回款信息 */
		SellBackForMreturnDto forMreturnDto = sellBackService
				.selectInfoByMreturnId(assessDto.getReturnId());
		if (forMreturnDto == null) {
			request.setAttribute("msg", "显示退款信息错误！");
			return mapping.findForward("failure");
		}
		/* 产品分类信用信息 */
		MreturnEntity mreturnEntity = new MreturnEntity();
		mreturnEntity.setCustomerId(assessDto.getCustomerId());
		mreturnEntity.setProductTypeId(assessDto.getProductTypeId());

		/* 产品分类预收金额合计 */
		forMreturnDto.setProdMoneySum(sellBackService
				.selectProdMoneySumByMreturnId(mreturnEntity));
		/* 产品分类可退款金额 */
		forMreturnDto.setProCanBackMoney(sellBackService
				.selectProdCanBackMoneyByMreturnId(mreturnEntity));

		request.setAttribute("sellBackDto", forMreturnDto);// 第二个dto主要显示跟回款有关的信息

		/* 获得产品部门名称 */
		String productDeptName = sellBackService.getProductDeptName(String
				.valueOf(assessDto.getProductTypeId()));

		request.setAttribute("productDeptName", productDeptName);
		
		return mapping.findForward(Constants.SUCCESS);
	}

	/**
	 * 修改退款(保存提交)
	 * 
	 * @author liuqg
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */

	public ActionForward modifySellBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("销售退款修改");
		String subFlag = request.getParameter("subFlag");

		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			if ("save".equals(subFlag)) {
				request.setAttribute("msg", "请勿重复保存 !");
			} else {
				request.setAttribute("msg", "请勿重复提交!");
			}
			return mapping.findForward("success");
		}

		DynaActionForm dform = (DynaActionForm) form;
		SellBackAssessDto assessDto = (SellBackAssessDto) dform
				.get("modifySellBackForm");
		String sellBackId= assessDto.getId(); // 退款申请单号
		request.setAttribute("sellBackId",sellBackId );
		/* 实现类 */
		sellBackService = (ISellBackService) getBean("sellBackServiceImpl");

		// 获取用户
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);

		/* 更新数据库 */

		if ("save".equals(subFlag)) {
			/* 保存 */
			if (sellBackService.modifySellBackSave(assessDto)) {
				log.info("User:{},{} 销售退款修改时，保存成功", user.getId(), user.getName());
				return mapping.findForward("success");
			} else {
				log.info("User:{},{} 销售退款修改时，保存失败", user.getId(), user.getName());
				request.setAttribute("err", "销售退款保存失败");
				return mapping.findForward("failure");
			}

		} else if ("submit".equals(subFlag)) {
			/* 修改并 发待办事务 */
			WorkEntity workEntity = new WorkEntity();
			workEntity.setCount(1);
			workEntity.setUserId(assessDto.getSellMajId());
			workEntity.setWorkId(10);
			if (sellBackService.modifySellBackSubmit(assessDto, workEntity)) {
				log.info("User:{},{} 销售退款修改时，提交成功", user.getId(), user.getName());
				return mapping.findForward("success");
			} else {
				log.info("User:{},{} 销售退款修改时，提交失败", user.getId(), user.getName());
				request.setAttribute("err", "销售退款提交失败");
				return mapping.findForward("failure");
			}

		} else {
			request.setAttribute("msg", "非法提交!");
			return mapping.findForward("success");
		}

	}


}

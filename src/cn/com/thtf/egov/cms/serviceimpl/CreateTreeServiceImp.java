/**
 * ClassName  CreateTreeServiceImp
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-7
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.dto.RoleNodeDto;
import cn.com.thtf.egov.cms.iservice.ICreateTreeService;

/**
 * CreateTreeServiceImp
 * 
 * @author Lubo
 */

public class CreateTreeServiceImp implements ICreateTreeService {

	/** NewIDao */
	private NewIDao dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ICreateTreeService#getLeftTree(java.lang
	 * .Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getLeftTree(Integer roleId) throws Exception {
		StringBuffer tree = new StringBuffer();
		boolean isTitleWrited = false;
		List<RoleNodeDto> result = dao.queryForlist("base_sqlMap.createLeftTree",
				roleId);

		for (RoleNodeDto nodeDto : result) {
			if (StringUtils.equals(nodeDto.getParentNodeId(), "0")) {
				if (isTitleWrited) {
					tree.append("</ul></div>");
				}
				tree.append("<h3><a href=\"#\">").append(nodeDto.getName())
						.append("</a></h3>");
				tree.append("<div><ul>");

				isTitleWrited = true;
			} else {
				tree.append("<li><a href=\"").append(nodeDto.getUrl()).append(
						"\" target='body'>").append(nodeDto.getName()).append(
						"</a></li>");
			}
		}

		if (isTitleWrited) {
			tree.append("</ul></div>");
		}
		return tree.toString();
	}

	/**
	 * @return the dao
	 */
	public NewIDao getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(NewIDao dao) {
		this.dao = dao;
	}

}

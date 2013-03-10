/**
 * ClassName  NewBaseAction
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-3-31
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.application;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.actions.MappingDispatchAction;
import org.apache.struts.upload.FormFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.util.Container;

/**
 * 2010-04-01后使用的Action基类
 * 
 * @author Shiy
 */

public abstract class NewBaseAction extends MappingDispatchAction {

    private static Logger log = LoggerFactory.getLogger(NewBaseAction.class);

    /**
     * 获取当前登录的用户信息
     * 
     * @param request
     * @return
     */
    public UserEntity getLoginUserInfo(HttpServletRequest request) {
        return (UserEntity) request.getSession().getAttribute(Constants.USERLOGIN);
    }

    /**
     * 取得ApplicationContext，如仅仅需要取bean直接使用getBean
     * 
     * @return
     */
    public ApplicationContext getApplicationContext() {
        return Container.getApplicationContext();
    }

    /**
     * 依据Spring配置文件中的name，取得相应的bean
     * 
     * @param name
     * @return
     */
    public Object getBean(String name) {
        return Container.getBean(name);
    }

    /**
     * getNewPage
     * 
     * @param request
     * @param para
     * @return
     */
    protected NewPage getNewPage(HttpServletRequest request) {
        return getNewPage(request, null);
    }

    /**
     * getNewPage
     * 
     * @param request
     * @param para
     * @param psize
     * @return
     */
    protected NewPage getNewPage(HttpServletRequest request, String psize) {
        String thisPage = request.getParameter(Constants.NEW_THISPAGE);
        String pageSize = null;

        if (psize != null) {
            pageSize = psize;
        } else {
            pageSize = Constants.DEFAULT_PAGE_SIZE;
        }

        if (StringUtils.isEmpty(thisPage)) {
            thisPage = "1";
        }

        NewPage page = new NewPage();
        page.setThisPage(Integer.parseInt(thisPage));
        page.setPageSize(Integer.parseInt(pageSize));
        return page;
    }

    /**
     * 上传文件的方法
     * 
     * @param file
     *            文件对象
     * @param dirname
     *            文件夹的名字
     * @param request
     * @return 成功返回新文件名，失败返回null
     */
    protected String loadFile(FormFile file, String dirname, HttpServletRequest request) {

        OutputStream bos = null;
        InputStream stream = null;
        String fname = null;// 新文件名
        try {
            log.info("开始上传文件");
            boolean isType = false;
            String filetype[] = Constants.FILETYPE;// 上传文件的类型
            for (int i = 0; i < filetype.length; i++) {
                if (file.getFileName().toLowerCase().endsWith(filetype[i])) {
                    isType = true;
                    break;
                }
            }

            if (isType == true) {

                /* 上传文件重命名 Begin */
                fname = file.getFileName();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                UserEntity user = (UserEntity) request.getSession().getAttribute(
                        Constants.USERLOGIN);
                String formatDate = user.getId() + "_" + sdf.format(new Date());
                fname = formatDate + fname.substring(fname.lastIndexOf("."));
                /* 上传文件重命名 End */

                stream = file.getInputStream();// 把文件读入
                String filePath = request.getSession().getServletContext().getRealPath(
                        "/WEB-INF/");
                String newdir = filePath + "/" + dirname + "/";
                File myFilePath = new File(newdir);
                if (!myFilePath.exists()) {
                    boolean isSuccess = myFilePath.mkdir();
                    if (!isSuccess) {
                        return null;
                    }
                }

                bos = new FileOutputStream(newdir + fname);// 建立一个上传文件的输出流//
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                    bos.write(buffer, 0, bytesRead);// 将文件写入服务器
                }
            }
        } catch (IOException e) {
            log.error("上传文件失败", e);
            fname = null;
        } finally {
            try {
                if (bos != null)
                    bos.close();
                if (stream != null)
                    stream.close();
            } catch (IOException e1) {
                log.error("上传文件失败", e1);
                fname = null;
            }
        }
        log.info("上传文件结束");
        return fname;
    }

    /**
     * 实现文件的下载
     * 
     * @param file
     * @param dirname
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    protected String downFile(String filename, String dirname, String prefixName,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.info("开始下载文件");
        String path = request.getSession().getServletContext().getRealPath(
                "/WEB-INF/" + dirname + "/" + filename);

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        OutputStream fos = null;
        InputStream fis = null;

        String filepath = path;
        File uploadFile = new File(filepath);
        fis = new FileInputStream(uploadFile);

        bis = new BufferedInputStream(fis);
        fos = response.getOutputStream();
        bos = new BufferedOutputStream(fos);

        // 为下载的文件加前缀，进行重新命名
        String newName = prefixName + filename;

        // 这个就就是弹出下载对话框的关键代码
        response.setHeader("Content-disposition", "attachment;filename="
                + URLEncoder.encode(newName, "utf-8"));
        int bytesRead = 0;
        // 缓冲输入输出流
        byte[] buffer = new byte[8192];
        while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }
        bos.flush();
        fis.close();
        bis.close();
        fos.close();
        bos.close();
        log.info("文件下载结束");
        return null;
    }
}

package com.cetccity.operationcenter.webframework.core.tools;

import com.cetccity.operationcenter.webframework.core.frame.CetcCloudStaticResource;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * PackageName:   com.cetccity.operationcenter.webframework.core.tools
 * projectName:   31project-Apr4
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2018/11/7 19:47
 * Updater:     by luolinjie
 * Update_Date: 2018/11/7
 * Update_Description: luolinjie 补充
 **/
public class ImageFileTool {

    /**
     * 获取建筑平面图URL
     *
     * @param srcImgURL
     * @return
     */
    public static HttpResponseModel<String> getBuildImageFileUrl(String srcImgURL) {
        String fileRelativePath;
        String suffix = null;

        if (srcImgURL != null && !srcImgURL.equals("undefined")) {
            fileRelativePath = srcImgURL.substring(25);
            String[] split = srcImgURL.split("\\.");
            suffix = split[split.length - 1];

            if (suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("png")) {
                return new HttpResponseModel<String>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, CetcCloudStaticResource.ROOT_PATH_BUILD_IMAGE + fileRelativePath);
            } else {
                return new HttpResponseModel<String>(SysCode.UNKNOWN_ERROR_CODE, "error file type", null);
            }
        } else {
            return new HttpResponseModel<String>(-1, "error file type", null);
        }
    }

    /**
     * 获取城市部件数据
     *
     * @param srcImgURL
     * @return
     */
    public static HttpResponseModel<String> getBjImageFileUrl(String srcImgURL) {
        String fileRelativePath;
        String suffix = null;

        if (srcImgURL != null && srcImgURL.contains("Z:\\photo\\")) {
            String[] split = srcImgURL.split("Z:\\\\photo\\\\");
            fileRelativePath = split[1];
            if (fileRelativePath.contains("JPG") || fileRelativePath.contains("PNG")) {
                fileRelativePath = fileRelativePath.replaceAll("\\\\", "/");
                return new HttpResponseModel<String>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, CetcCloudStaticResource.ROOT_PATH_BJ_IMAGE + fileRelativePath);
            } else {
                return new HttpResponseModel<String>(SysCode.UNKNOWN_ERROR_CODE, "error file type", null);
            }
        } else {
            return new HttpResponseModel<String>(-1, "error file url", null);
        }
    }

    /**
     * 获取城市部件数据
     *
     * @param srcImgURL
     * @return
     */
    public static HttpResponseModel<String> getSanxiaoImageFileUrl(String srcImgURL) {
        if (srcImgURL != null) {
            String[] split = srcImgURL.split(",");
            String processed = Arrays.stream(split)
                    .map(str -> CetcCloudStaticResource.ROOT_PATH_SANXIAO_IMAGE + str)
                    .collect(Collectors.joining("、"));
            return new HttpResponseModel<>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, processed);
        } else {
            return new HttpResponseModel<>(SysCode.UNKNOWN_ERROR_CODE, "error file type", null);
        }
    }

    @Test
    public void testGetURL() {
        String url = "http://58.60.185.51:5750/xfyhpc-web/content/upload/2017-05-18/84c5507d-40c7-47e0-9f0a-1a02e96ee920.jpg";
        HttpResponseModel<String> imageFileUrl = ImageFileTool.getBuildImageFileUrl(url);
        System.out.println(imageFileUrl.getData());
    }

    @Test
    public void testGetBjURL() {
        String url = "Z:\\photo\\BJ\\44030406723录入部件照片\\440304067232126001872.JPG";
        HttpResponseModel<String> imageFileUrl = ImageFileTool.getBjImageFileUrl(url);
        System.out.println(imageFileUrl.getData());
    }
}

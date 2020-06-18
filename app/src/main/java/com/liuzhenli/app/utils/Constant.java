package com.liuzhenli.app.utils;

import javax.annotation.Resource;
import javax.annotation.Resources;

/**
 * @author Liuzhenli
 * @since 2019-07-06 19:05
 */
public class Constant {
    public static final String API_BASE_URL = "https://www.wanandroid.com";

    public interface ApiPath {
        String ARTICLE_LIST = "/article/list/{page}/json";
        /**
         * 置顶文章
         */
        String ARTICLE_TOP = "/article/top/json";
        String ARTICLE_LOGIN = "/user/login";
        /**
         * 广场
         */
        String ARTICLE_USER_ARTICLE = "/user_article/list/{page}/json";
        /**
         * 按照作者昵称搜索文章
         */
        String ARTICLE_USER_ARTICLE_LIST = "/article/list/{page}/json";
        /**
         * 问答
         */
        String ARTICLE_WEN_DA = "/wenda/list/{page}/json ";
        /***获取公众号列表**/
        String ARTICLE_WXARTICLE_CHAPTERS = "/wxarticle/chapters/json ";
        /***查看某个公众号历史数据**/
        String ARTICLE_WXARTICLE_LIST = "/wxarticle/list/{id}/{page}/json";
        /***导航*/
        String NAVIGATION = "/navi/json";
        /***项目树*/
        String PROJECT_TREE = "/project/tree/json";
        /***项目列表**/
        String PROJECT_LIST = "/project/list/{page}/json";
    }

    public static final int TOKEN_EXPRIED = 10104;
    public static final String SP_TOKEN = "token";
    public static final String COMMON_CONFIG = "common_config";
    public static final String REFER = "http://wan.baidu.com";

    public static String PATH_DATA = FileUtils.createRootPath(AppUtils.getAppContext()) + "/data";
    public static String BASE_PATH = FileUtils.createRootPath(AppUtils.getAppContext()) + "/book/";
    public static String FONT_PATH = FileUtils.createRootPath(AppUtils.getAppContext()) + "/font/";
    public static String IMG_PATH = FileUtils.createRootPath(AppUtils.getAppContext()) + "/img/";
    public static String UPDATE_PATH = FileUtils.createRootPath(AppUtils.getAppContext()) + "/update/";
    public static String LOG_PATH = FileUtils.createRootPath(AppUtils.getAppContext()) + "/log/";
    public static String TTS_PATH = FileUtils.createRootPath(AppUtils.getAppContext()) + "/tts/";
    public static String WIFI_BOOK_PATH = FileUtils.createWifiBookPath(AppUtils.getAppContext()) + "/wifiTransfer/";
    public static String POST_CACHE_PATH = FileUtils.createRootPath(AppUtils.getAppContext()) + "/tiezi/";


    public static final String PERCENTL_STR = "%";
    public static final String CHARSET_NAME = "UTF-8";
}

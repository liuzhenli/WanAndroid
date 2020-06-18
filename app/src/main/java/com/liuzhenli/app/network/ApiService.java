package com.liuzhenli.app.network;

import com.liuzhenli.app.bean.ArticleChapters;
import com.liuzhenli.app.bean.ArticleListBean;
import com.liuzhenli.app.bean.NavigationData;
import com.liuzhenli.app.bean.ProjectTreeBean;
import com.liuzhenli.app.bean.TopArticleList;
import com.liuzhenli.app.bean.UserInfo;
import com.liuzhenli.app.utils.Constant;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Liuzhenli
 * @since 2019-07-06 19:37
 */
public interface ApiService {
    @FormUrlEncoded
    @POST(Constant.ApiPath.ARTICLE_LOGIN)
    Observable<UserInfo> getLoginData(@FieldMap Map<String, String> body);

    /**
     * 首页文章列表
     *
     * @param page 页数
     * @return Observable
     */
    @GET(Constant.ApiPath.ARTICLE_LIST)
    Observable<ArticleListBean> getHomePageArticleList(@Path("page") int page);

    /**
     * 置顶文章
     *
     * @return 置顶文章
     */
    @GET(Constant.ApiPath.ARTICLE_TOP)
    Observable<TopArticleList> getTopArticle();

    /**
     * @return 获取公众号列表
     */
    @GET(Constant.ApiPath.ARTICLE_WXARTICLE_CHAPTERS)
    Observable<ArticleChapters> getArticleChapters();

    /**
     * @return 查看某个公众号历史数据
     */
    @GET(Constant.ApiPath.ARTICLE_WXARTICLE_LIST)
    Observable<ArticleListBean> getArticleChaptersList(@Path("id") String id, @Path("page") int page);

    /**
     * 导航
     *
     * @return 导航数据
     */
    @GET(Constant.ApiPath.NAVIGATION)
    Observable<NavigationData> getNavigation();

    /**
     * 广场
     *
     * @return 广场用户分享的文章
     */
    @GET(Constant.ApiPath.ARTICLE_USER_ARTICLE)
    Observable<ArticleListBean> getUserArticles(@Path("page") String page);

    /**
     * @return 根据用户获取其分享的文章列表
     */
    @GET(Constant.ApiPath.ARTICLE_USER_ARTICLE_LIST)
    Observable<ArticleListBean> getUserArticleList(@Path("page") String page, @Query("author") String userName);

    /**
     * @return 问答
     */
    @GET(Constant.ApiPath.ARTICLE_WEN_DA)
    Observable<ArticleListBean> getDailyQuestion(@Path("page") String page);

    /**
     * @return 项目分类
     */
    @GET(Constant.ApiPath.PROJECT_TREE)
    Observable<ProjectTreeBean> getProjectTree();

    /**
     * @return 项目列表
     */
    @GET(Constant.ApiPath.PROJECT_LIST)
    Observable<ArticleListBean> getProjects(@Path("page") int page, @Query("cid") String cid);
}

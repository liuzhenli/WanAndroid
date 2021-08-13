package com.liuzhenli.app.network;

import com.liuzhenli.app.bean.ArticleChapters;
import com.liuzhenli.app.bean.ArticleListBean;
import com.liuzhenli.app.bean.NavigationData;
import com.liuzhenli.app.bean.ProjectTreeBean;
import com.liuzhenli.app.bean.TopArticleList;
import com.liuzhenli.app.bean.UserInfo;
import com.liuzhenli.app.gson.CustomGsonConverterFactory;
import com.liuzhenli.app.utils.Constant;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author Liuzhenli
 * @since 2019-07-06 19:37
 */
public class Api {
    public static Api instance;

    private ApiService service;

    public Api(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URL)
                // 添加Rx适配器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                // 添加Gson转换器
                .addConverterFactory(CustomGsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        service = retrofit.create(ApiService.class);
    }

    public static Api getInstance(OkHttpClient okHttpClient) {
        if (instance == null) {
            instance = new Api(okHttpClient);
        }
        return instance;
    }

    public Observable<UserInfo> getLoginData(Map<String, String> params) {
        return service.getLoginData(params);
    }

    /***首页文章列表**/
    public Observable<ArticleListBean> getHomePageArticleList(int page) {
        return service.getHomePageArticleList(page);
    }

    /***置顶文章**/
    public Observable<TopArticleList> getTopArticle() {
        return service.getTopArticle();
    }

    /***公众号列表**/
    public Observable<ArticleChapters> getArticleChapters() {
        return service.getArticleChapters();
    }

    /*****/
    public Observable<ArticleListBean> getArticleChaptersList(String id, int page) {
        return service.getArticleChaptersList(id, page);
    }

    /***navigation**/
    public Observable<NavigationData> getNavigation() {
        return service.getNavigation();
    }

    public Observable<ArticleListBean> getUserArticles(String page) {
        return service.getUserArticles(page);
    }

    public Observable<ArticleListBean> getUserArticleList(String userName, String page) {
        return service.getUserArticleList(page, userName);
    }

    public Observable<ArticleListBean> getDailyQuestion(String page) {
        return service.getDailyQuestion(page);
    }

    public Observable<ProjectTreeBean> getProjectTree() {
        return service.getProjectTree();
    }
    public Observable<ArticleListBean> getProjects(int page,String cid) {
        return service.getProjects(page,cid);
    }

    public Observable<UserInfo> register(Map<String,String> body){
        return service.register(body);
    }
}

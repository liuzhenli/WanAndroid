package com.liuzhenli.app.network;


import com.liuzhenli.app.module.ApiModule;
import com.liuzhenli.app.module.AppModule;
import com.liuzhenli.app.ui.activity.LoginActivity;
import com.liuzhenli.app.ui.activity.RegisterActivity;
import com.liuzhenli.app.ui.fragment.ArticleContainerFragment;
import com.liuzhenli.app.ui.fragment.ArticleFragment;
import com.liuzhenli.app.ui.fragment.DailyQuestionFragment;
import com.liuzhenli.app.ui.fragment.HomeFragment;
import com.liuzhenli.app.ui.fragment.NavigationFragment;
import com.liuzhenli.app.ui.fragment.ProjectFragment;
import com.liuzhenli.app.ui.fragment.ProjectTreeFragment;
import com.liuzhenli.app.ui.fragment.UserArticleFragment;
import com.liuzhenli.app.ui.fragment.UserArticleListFragment;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Liuzhenli
 * @since 19/7/6
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    //void inject(SplashActivity splashActivity);

    void inject(LoginActivity loginActivity);

    void inject(HomeFragment homeFragment);

    void inject(ArticleContainerFragment articleContainerFragment);

    void inject(ArticleFragment articleFragment);

    void inject(NavigationFragment navigationFragment);

    void inject(UserArticleFragment userArticleFragment);

    void inject(UserArticleListFragment userArticleListFragment);

    void inject(DailyQuestionFragment dailyQuestionFragment);

    void inject(ProjectTreeFragment projectTreeFragment);

    void inject(ProjectFragment projectFragment);

    void inject(@NotNull RegisterActivity registerActivity);
}

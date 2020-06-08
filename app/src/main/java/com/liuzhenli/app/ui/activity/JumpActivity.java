package com.liuzhenli.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.liuzhenli.app.R;
import com.liuzhenli.app.base.rxlife.RxAppCompatActivity;
import com.liuzhenli.app.ui.fragment.UserArticleListFragment;
import com.liuzhenli.app.ui.fragment.WebExplorerFragment;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.arch.annotation.DefaultFirstFragment;
import com.qmuiteam.qmui.arch.annotation.FirstFragments;
import com.qmuiteam.qmui.arch.annotation.LatestVisitRecord;

import static com.liuzhenli.app.ui.fragment.UserArticleListFragment.EXTRA_NAME;
import static com.liuzhenli.app.ui.fragment.WebExplorerFragment.EXTRA_TITLE;
import static com.liuzhenli.app.ui.fragment.WebExplorerFragment.EXTRA_URL;

/**
 * describe:
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/5/25 8:19 AM
 */
@FirstFragments(
        value = {
                WebExplorerFragment.class,
                UserArticleListFragment.class
        })
@DefaultFirstFragment(WebExplorerFragment.class)
@LatestVisitRecord
public class JumpActivity extends RxAppCompatActivity {

    @Override
    public int getContextViewId() {
        return R.id.activity_text_view_container;
    }

    public static Intent createWebExplorerIntent(Context context, String url, String title) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_URL, url);
        bundle.putString(EXTRA_TITLE, title);
        return of(context, WebExplorerFragment.class, bundle);
    }

    public static Intent createUserArticleListIntent(Context context, String userName, String title) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_NAME, userName);
        bundle.putString(EXTRA_TITLE, title);
        return of(context, UserArticleListFragment.class, bundle);
    }

    public static Intent of(@NonNull Context context,
                            @NonNull Class<? extends QMUIFragment> firstFragment) {
        return QMUIFragmentActivity.intentOf(context, JumpActivity.class, firstFragment);
    }

    public static Intent of(@NonNull Context context,
                            @NonNull Class<? extends QMUIFragment> firstFragment,
                            @Nullable Bundle fragmentArgs) {
        return QMUIFragmentActivity.intentOf(context, JumpActivity.class, firstFragment, fragmentArgs);
    }
}

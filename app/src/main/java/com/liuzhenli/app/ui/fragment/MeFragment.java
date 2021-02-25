package com.liuzhenli.app.ui.fragment;

import android.widget.TextView;

import com.liuzhenli.app.R;
import com.liuzhenli.app.base.BaseFragment;
import com.liuzhenli.app.events.LoginSuccessEvent;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.ui.activity.LoginActivity;
import com.liuzhenli.app.ui.activity.SettingActivity;
import com.liuzhenli.app.utils.AccountManager;
import com.liuzhenli.app.utils.ClickUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * describe:
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/5/25 10:45 PM
 */
public class MeFragment extends BaseFragment {

    public static MeFragment getInstance() {
        return new MeFragment();
    }

    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_me_setting)
    TextView mTvSetting;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {
        ClickUtils.click(mTvUserName, o -> {
            if (!AccountManager.getInstance().isLogin()) {
                LoginActivity.start(mContext);
            }
        });

        ClickUtils.click(mTvSetting, o -> SettingActivity.start(mContext));
        if (AccountManager.getInstance().isLogin()) {
            mTvUserName.setText(String.format("%s ,欢迎您!", AccountManager.getInstance().getUserName()));
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginSuccessEvent event) {
        if (AccountManager.getInstance().isLogin()) {
            mTvUserName.setText(String.format("%s ,欢迎您!", AccountManager.getInstance().getUserName()));
        }
    }
}

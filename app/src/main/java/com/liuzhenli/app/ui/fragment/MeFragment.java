package com.liuzhenli.app.ui.fragment;

import android.view.LayoutInflater;

import com.liuzhenli.app.base.BaseContract;
import com.liuzhenli.app.base.BaseFragment;
import com.liuzhenli.app.databinding.FragmentMeBinding;
import com.liuzhenli.app.events.LoginOutEvent;
import com.liuzhenli.app.events.LoginSuccessEvent;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.ui.activity.LoginActivity;
import com.liuzhenli.app.ui.activity.SettingActivity;
import com.liuzhenli.app.utils.AccountManager;
import com.liuzhenli.app.utils.ClickUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * describe:
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/5/25 10:45 PM
 */
public class MeFragment extends BaseFragment<BaseContract.BasePresenter, FragmentMeBinding> {


    public static MeFragment getInstance() {
        return new MeFragment();
    }

    @Override
    public FragmentMeBinding inflateView(LayoutInflater inflater) {
        return FragmentMeBinding.inflate(inflater);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {
        ClickUtils.click(binding.tvUserName, o -> {
            if (!AccountManager.getInstance().isLogin()) {
                LoginActivity.start(mContext);
            }
        });

        ClickUtils.click(binding.tvMeSetting, o -> SettingActivity.start(mContext));
        if (AccountManager.getInstance().isLogin()) {
            binding.tvUserName.setText(String.format("%s ,欢迎您!", AccountManager.getInstance().getUserName()));
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginSuccessEvent event) {
        if (AccountManager.getInstance().isLogin()) {
            binding.tvUserName.setText(String.format("%s ,欢迎您!", AccountManager.getInstance().getUserName()));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginOutEvent event) {
        binding.tvUserName.setText("游客");
    }
}

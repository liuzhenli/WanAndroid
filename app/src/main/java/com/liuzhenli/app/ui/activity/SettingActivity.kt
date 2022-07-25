package com.liuzhenli.app.ui.activity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import com.liuzhenli.app.R
import com.liuzhenli.app.base.BaseActivity
import com.liuzhenli.app.databinding.ActSettingBinding
import com.liuzhenli.app.events.LoginOutEvent
import com.liuzhenli.app.network.AppComponent
import com.liuzhenli.app.ui.contract.SettingContract
import com.liuzhenli.app.ui.presenter.SettingPresenter
import com.liuzhenli.app.utils.AccountManager
import com.liuzhenli.app.utils.ClickUtils
import org.greenrobot.eventbus.EventBus


/**
 * Description:
 *
 * @author liuzhenli 2021/2/25
 * Email: 848808263@qq.com
 */
class SettingActivity : BaseActivity<SettingPresenter?, ActSettingBinding>(), SettingContract.View {

    override fun getContextViewId(): Int {
        return R.layout.act_setting
    }

    override fun setupActivityComponent(appComponent: AppComponent) {}
    override fun initToolBar() {
        mTvTitle.text = "设置"
    }

    override fun initData() {}
    override fun configViews() {
        ClickUtils.click(binding?.btnLogout) { o: Any? ->
            AccountManager.getInstance().logout()
            EventBus.getDefault().post(LoginOutEvent())
            finish()
        }
    }

    override fun showError(e: Exception) {}
    override fun complete() {}

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, SettingActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun inflateView(inflater: LayoutInflater?): ActSettingBinding {
        return ActSettingBinding.inflate(inflater!!)
    }
}
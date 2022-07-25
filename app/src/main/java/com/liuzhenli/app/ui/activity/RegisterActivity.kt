package com.liuzhenli.app.ui.activity

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.EditText
import com.liuzhenli.app.R
import com.liuzhenli.app.base.BaseActivity
import com.liuzhenli.app.bean.UserInfo
import com.liuzhenli.app.databinding.ActivityRegisterBinding
import com.liuzhenli.app.network.AppComponent
import com.liuzhenli.app.ui.contract.RegisterContract
import com.liuzhenli.app.ui.presenter.RegisterPresenter

/**
 * Description:register activity
 *
 * @author  liuzhenli 2021/8/12
 * Email: 848808263@qq.com
 */
class RegisterActivity : BaseActivity<RegisterPresenter, ActivityRegisterBinding>(),
    RegisterContract.View {

    fun start(context: Context) {
        context.startActivity(Intent(context, RegisterActivity::class.java));
    }

    override fun getContextViewId(): Int {
        return R.layout.activity_register
    }

    override fun setupActivityComponent(appComponent: AppComponent?) {
        appComponent?.inject(this)
    }

    override fun initToolBar() {
        mTvTitle.text = "账户注册"
    }

    override fun initData() {
    }

    override fun configViews() {
        var etUserName = findViewById<EditText>(R.id.etUserName)
        etUserName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun showRegisterResult(userInfo: UserInfo) {
    }

    override fun showError(e: Exception?) {
    }

    override fun complete() {
    }

    override fun inflateView(inflater: LayoutInflater?): ActivityRegisterBinding {
        return ActivityRegisterBinding.inflate(inflater!!)
    }
}
package com.liuzhenli.app.ui.presenter

import android.util.ArrayMap
import com.liuzhenli.app.base.RxPresenter
import com.liuzhenli.app.bean.UserInfo
import com.liuzhenli.app.network.Api
import com.liuzhenli.app.observer.SampleProgressObserver
import com.liuzhenli.app.ui.contract.RegisterContract
import com.liuzhenli.app.utils.RxUtil
import javax.inject.Inject

/**
 * Description:
 *
 * @author liuzhenli 2021/8/12
 * Email: 848808263@qq.com
 */
class RegisterPresenter @Inject constructor(private val api: Api) :
    RxPresenter<RegisterContract.View?>(), RegisterContract.Presenter<RegisterContract.View?> {
    override fun register(userName: String, userPassword: String, rePassword: String) {
        val body = ArrayMap<String, String>()
        //username,password,repassword
        body["username"] = userName
        body["password"] = userPassword
        body["repassword"] = rePassword

        addSubscribe(
            RxUtil.subscribe(api.register(body),
                object : SampleProgressObserver<UserInfo>() {
                    override fun onNext(t: UserInfo) {
                        mView!!.showRegisterResult(t)
                    }
                })
        )
    }
}
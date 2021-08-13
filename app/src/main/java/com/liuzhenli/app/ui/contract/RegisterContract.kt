package com.liuzhenli.app.ui.contract

import com.liuzhenli.app.base.BaseContract
import com.liuzhenli.app.bean.UserInfo

/**
 * Description:user login contract
 *
 * @author  liuzhenli 2021/8/12
 * Email: 848808263@qq.com
 */
class RegisterContract {
    interface View : BaseContract.BaseView {
        fun showRegisterResult(userInfo: UserInfo)
    }

    interface Presenter<T> : BaseContract.BasePresenter<T> {
        fun register(userName: String, userPassword: String, rePassword: String)
    }
}
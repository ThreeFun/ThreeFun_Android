package com.example.retrofit.viewModel

import androidx.lifecycle.ViewModel
import com.example.retrofit.src.data.models.login.LoginRequestModel
import com.example.retrofit.src.data.models.sign.SignRequestModel

class LoginViewModel : ViewModel() {
    var id: String = ""
    var password: String = ""


    fun makeLoginModel(): LoginRequestModel? {
        //region은 일단 납둔다.
        return if (id.isNotBlank() && password.isNotBlank()) {
            LoginRequestModel(id, password)
        } else {
            //오류처리
            null
        }
    }
}
package com.example.retrofit.viewModel

import androidx.lifecycle.ViewModel
import com.example.retrofit.src.data.models.sign.SignRequestModel

class SignUpViewModel : ViewModel() {
    var email: String = ""
    var nickname: String = ""
    var password: String = ""
    var region: Int = 1

    fun makeSignUpModel(): SignRequestModel?{
        //region은 일단 납둔다.
        if(email.isNotBlank() && nickname.isNotBlank() && password.isNotBlank()){
            return SignRequestModel(nickname, email, password, region)
        }
        else{
            //오류처리
            return null
        }
    }
}
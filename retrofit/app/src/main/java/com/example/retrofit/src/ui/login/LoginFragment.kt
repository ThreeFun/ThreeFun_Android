package com.example.retrofit.src.ui.login

import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.core.view.isNotEmpty
import androidx.fragment.app.viewModels
import com.example.retrofit.R
import com.example.retrofit.application.CommonFragment
import com.example.retrofit.databinding.FragmentLoginBinding
import com.example.retrofit.viewModel.LoginViewModel

class LoginFragment : CommonFragment<FragmentLoginBinding>(R.layout.fragment_login),
    View.OnClickListener {

    val loginViewModel : LoginViewModel by viewModels()

    //전체적인 텍스트 변화
    private val checkValidation = {
        //비밀번호 확인
        binding.loginPassword.isError != binding.loginPassword.keyword.length < 4
        //비밀번호 개수 확인
        binding.loginId.isError = binding.loginId.keyword.isNotBlank()
        //버튼의 사용가능 여부 확인
        binding.btnLogin.isEnabled = isValidation()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginId.apply {
            setTitle(resources.getString(R.string.title_id))
            textChangeEvent = checkValidation
            setInputType(InputType.TYPE_CLASS_TEXT)
        }
        binding.loginPassword.apply {
            setTitle(resources.getString(R.string.title_password))
            setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT)
            textChangeEvent = checkValidation
            maxLength = 20
        }
        binding.tvDoJoin.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnLogin -> {
                //로그인
            }
            binding.tvDoJoin -> {
                navigate(LoginFragmentDirections.actionLoginFragmentToFragmentSign())
            }
        }
    }

    //모든 정규식을 통과하면 버튼 enabled
    private fun isValidation(): Boolean {
        return binding.loginId.keyword.isNotBlank() && !binding.loginPassword.isError
    }
}
package com.example.retrofit.src.ui.sign.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.retrofit.R
import com.example.retrofit.application.CommonFragment
import com.example.retrofit.databinding.FragmentLoginBinding
import com.example.retrofit.legacy.SubActivity
import com.example.retrofit.src.data.models.ErrorResponse
import com.example.retrofit.src.ui.main.MainActivity
import com.example.retrofit.util.TokenManager
import com.example.retrofit.util.network.SignNetworkUtil
import com.example.retrofit.viewModel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class LoginFragment : CommonFragment<FragmentLoginBinding>(R.layout.fragment_login),
    View.OnClickListener {

    val loginViewModel: LoginViewModel by viewModels()

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
                if (isValidEmail(binding.loginId.keyword)) {
                    loginViewModel.id = binding.loginId.keyword
                    loginViewModel.password = binding.loginPassword.keyword
                    CoroutineScope(Dispatchers.IO).launch {
                        if (loginViewModel.makeLoginModel() == null) {
                            //오류 처리
                            Log.d("login", "null 값")
                        } else {
                            val result = SignNetworkUtil.api.login(
                                loginViewModel.makeLoginModel()!!
                            ).execute()
                            //여기가 성공
                            if (result.code() == 200) {
                                if (result.body()?.code == 200) {
                                    //바로 홈화면으로
                                    val token = result.body()?.result?.jwt
                                    val idx = result.body()?.result?.userIdx
                                    if (token != null) {
                                        TokenManager(requireContext()).setToken(token)
                                        TokenManager(requireContext()).setIdx(idx!!)
                                        startActivity(Intent(requireContext(), SubActivity::class.java))
                                    }
                                } else {
                                    Log.d("login", "200 실패${result.body()?.message}")
                                }
                            } else { //여기가 실패
                                Log.d("login", "실패${result.body()?.code}")
                            }
                        }
                    }
                }
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

    fun isValidEmail(email: String?): Boolean {
        if (email == null) return false
        val regex = """[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,64}""".toRegex()
        return regex.matches(email)
    }
}
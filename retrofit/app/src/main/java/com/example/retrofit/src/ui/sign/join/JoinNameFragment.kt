package com.example.retrofit.src.ui.sign.join

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.retrofit.R
import com.example.retrofit.databinding.FragmentJoinNameBinding
import com.example.retrofit.util.network.SignNetworkUtil
import com.example.retrofit.application.CommonFragment
import com.example.retrofit.util.TokenManager
import com.example.retrofit.viewModel.SignUpViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JoinNameFragment : CommonFragment<FragmentJoinNameBinding>(R.layout.fragment_join_name),
    View.OnClickListener {

    //앞서 사용한 viewModel을 가져옴, 싱글톤 방식
    private val signUpViewModel: SignUpViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val checkBtn = {
            binding.btnJoinNext.isEnabled = checkName()
        }

        binding.joinName.apply {
            setTitle(resources.getString(R.string.title_name))
            maxLength = 16
            setHint(resources.getString(R.string.content_hint_name))
            setInputType(InputType.TYPE_CLASS_TEXT)
            textChangeEvent = checkBtn
        }
        binding.btnJoinNext.setOnClickListener(this)
        binding.btnJoinBack.setOnClickListener(this)
    }

    private fun checkName(): Boolean {
        return binding.joinName.keyword.isNotBlank()
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnJoinBack -> {
                navPopStack()
            }
            binding.btnJoinNext -> {
                signUpViewModel.nickname = binding.joinName.keyword
                //일단 여기까지 sign, 다음 액티비티로 넘어감
                CoroutineScope(Dispatchers.IO).launch {
                    if (signUpViewModel.makeSignUpModel() == null) {
                        //오류 처리
                    } else {
                        val result = SignNetworkUtil.api.signUp(
                            signUpViewModel.makeSignUpModel()!!
                        ).execute()
                        //여기가 성공
                        if (result.code() == 200) {
                            val token = result.body()?.result?.jwt
                            if (token != null) {
                                TokenManager(requireContext()).setToken(token)
                                startActivity(Intent())
                            }
                        } else { //여기가 실패
                            Log.d("join","아에 실패${result.body()?.code}")
                        }
                    }
                }
            }
        }
    }
}
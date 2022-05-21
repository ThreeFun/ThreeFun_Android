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
import com.example.retrofit.src.ui.main.MainActivity
import com.example.retrofit.util.TokenManager
import com.example.retrofit.viewModel.SignUpViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

enum class RegionCategory(index : Int, region : String) {
    Seoul(1, "서울"),
    Gyeonggi(2, "경기"),
    Incheon(3, "인천"),
    Gangwon(4, "강원"),
    Daejeon(5,"대전"),
    Chungbuk(6,"충북"),
    Chungnam(7, "충남"),
    Deagu(8, "대구"),
    Gyeongnam(9, "경남"),
    Gyeongbuk(10,"경북"),
    Busan(11, "부산"),
    Ulsan(12,"울산"),
    Gwangju(13,"광주"),
    Jeonnam(14,"전남"),
    Jeonbuk(15, "전북"),
    Sejong(16, "세종")
}

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
                        if (result.code() == 200) {
                            val token = result.body()?.result?.jwt
                            val idx = result.body()?.result?.userIdx
                            if (token != null) {
                                TokenManager(requireContext()).setToken(token)
                                TokenManager(requireContext()).setIdx(idx!!)
                                startActivity(Intent(requireContext(), MainActivity::class.java))
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
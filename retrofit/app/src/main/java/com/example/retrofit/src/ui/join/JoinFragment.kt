package com.example.retrofit.src.ui.join

import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.retrofit.R
import com.example.retrofit.databinding.FragmentJoinBinding
import com.example.retrofit.application.CommonFragment
import com.example.retrofit.viewModel.SignUpViewModel

class JoinFragment : CommonFragment<FragmentJoinBinding>(R.layout.fragment_join),
    View.OnClickListener {

    //액티비티 라이프사이클에 종속
    private val signUpViewModel : SignUpViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //전체적인 텍스트 변화
        val checkValidation = {
            //비밀번호 확인
            binding.joinMatchPassword.isError =
                binding.joinPassword.keyword != binding.joinMatchPassword.keyword
            //비밀번호 개수 확인
            binding.joinPassword.isError = binding.joinPassword.keyword.length < 4
            //버튼의 사용가능 여부 확인
            binding.btnJoinNext.isEnabled = isValidation()
        }

        binding.joinId.apply {
            isError = false
            maxLength = 100
            setTitle(resources.getString(R.string.title_id))
            setInputType(InputType.TYPE_CLASS_TEXT)
            setHint(resources.getString(R.string.content_id_hint))
        }

        binding.joinPassword.apply {
            setErrorMessage(resources.getString(R.string.content_password_error))
            textChangeEvent = checkValidation
            maxLength = 20
            setTitle(resources.getString(R.string.title_password))
            setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT)
            setHint(resources.getString(R.string.content_password_hint))
        }

        binding.joinMatchPassword.apply {
            setErrorMessage(resources.getString(R.string.content_no_match_password))
            textChangeEvent = checkValidation
            maxLength = 20
            setTitle(resources.getString(R.string.title_match_password))
            setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT)
            setHint(resources.getString(R.string.content_match_password_hint))
        }
        binding.btnJoinNext.setOnClickListener(this)
    }

    //모든 정규식을 통과하면 버튼 enabled
    private fun isValidation(): Boolean {
        return binding.joinId.keyword.isNotBlank() && !binding.joinPassword.isError && !binding.joinMatchPassword.isError
    }

    override fun onClick(button: View?) {
        when (button) {
            binding.btnJoinNext -> {
                //FragmentSign to JoinNameFragment 로 넘어감, 앞선 매개변수는 JoinFragmentDirections, 현재 프래그먼트
                signUpViewModel.email = binding.joinId.keyword
                signUpViewModel.password = binding.joinPassword.keyword
                navigate(JoinFragmentDirections.actionFragmentSignToJoinNameFragment())
            }
        }
    }
}
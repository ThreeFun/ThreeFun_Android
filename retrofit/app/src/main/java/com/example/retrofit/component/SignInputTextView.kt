package com.example.retrofit.component

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.retrofit.R
import com.example.retrofit.databinding.ItemSignInputTextViewBinding

class SignInputTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), TextWatcher,
    TextView.OnEditorActionListener  {

    private var _binding: ItemSignInputTextViewBinding? = null
    private val binding : ItemSignInputTextViewBinding get() = _binding!!
    var textChangeEvent : () -> Unit = {}
    var maxLength = 100
    set(value) {
        val filters = mutableListOf<InputFilter>()
        filters.add(InputFilter.LengthFilter(value))
        binding.viewInputText.filters = filters.toTypedArray()
        field = value
    }
    var keyword
        get() = binding.viewInputText.text.toString()
        set(value) = binding.viewInputText.setText(value)
    var isError : Boolean = false
        set(value) {
            if(value) {
                binding.tvErrorMessage.visibility = View.VISIBLE
            } else {
                binding.tvErrorMessage.visibility = View.GONE
            }
            field = value
        }

    init {
        _binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_sign_input_text_view,
            this,
            false
        )
        addView(binding.root)
        binding.viewInputText.addTextChangedListener(this)
    }

    fun setInputType(inputType: Int) {
        binding.viewInputText.inputType = inputType
    }

    fun setHint(hint : String) {
        binding.viewInputText.hint = hint
    }

    //에러 문구
    fun setErrorMessage(message : String) {
        binding.tvErrorMessage.text = message
    }

    //타이틀 문구
    fun setTitle(title : String) {
        binding.tvTitle.text = title
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        textChangeEvent()
    }

    override fun afterTextChanged(s: Editable?) { }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        return true
    }
}
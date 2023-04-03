package com.maxwellxin.password_input

import android.content.Context
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.maxwellxin.password_input.databinding.SamplePasswordInputBinding

class PasswordInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs) {

    private var binding: SamplePasswordInputBinding = SamplePasswordInputBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    private var editText: EditText = binding.editText
    private var btnViewPassword: ImageButton = binding.viewPassword
    private var visible: Boolean = false

    init {
        btnViewPassword.setOnClickListener {
            if (visible) {
                visible = false
                btnViewPassword.setImageResource(R.drawable.icon_close_eyes)
                editText.transformationMethod = PasswordTransformationMethod()
            } else {
                visible = true
                btnViewPassword.setImageResource(R.drawable.icon_eyes)
                editText.transformationMethod = null
            }
        }
    }

    val text: String
        get() = editText.text.toString().trim()

    fun setError(error: String?) {
        editText.error = error
    }

    fun reset() {
        editText.setText("")
    }

}
package com.maxwellxin.password_input

import android.content.Context
import android.graphics.Typeface
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.content.res.ResourcesCompat
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

        attrs?.let {
            applyCustomProperty(it)
        }
    }

    private fun applyCustomProperty(attrs: AttributeSet) {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.PasswordInput, 0, 0)

        a.getString(R.styleable.PasswordInput_pi_hint)?.let {
            binding.editText.hint = it
        }

        a.getResourceId(R.styleable.PasswordInput_pi_fontFamily, 0).let {
            if (it > 0) {
                val typeFace: Typeface? = ResourcesCompat.getFont(context, it)
                binding.editText.typeface = typeFace
            }
        }

        a.getDrawable(R.styleable.PasswordInput_pi_background)?.let {
            binding.editText.background = it
        }

        a.getDimension(R.styleable.PasswordInput_pi_textSize, resources.getDimension(R.dimen.text_medium)).let {
            binding.editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, it)
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
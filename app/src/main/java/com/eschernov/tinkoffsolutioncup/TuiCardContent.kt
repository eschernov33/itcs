package com.eschernov.tinkoffsolutioncup

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.eschernov.tinkoffsolutioncup.databinding.TuiCardContentBinding

class TuiCardContent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = R.style.Theme_TinkoffSolutionCup,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: TuiCardContentBinding

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.tui_card_content, this, true)
        binding = TuiCardContentBinding.bind(this)
        initAttr(attrs, defStyleAttr, defStyleRes)
    }

    private fun initAttr(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.TuiCardContent, defStyleAttr, defStyleRes
        )

        val title = typedArray.getString(R.styleable.TuiCardContent_contentTitle)
        setTitle(title)

        val description = typedArray.getString(R.styleable.TuiCardContent_contentDescription)
        setDescription(description)

        val accessory =
            typedArray.getResourceId(
                R.styleable.TuiCardContent_contentAccessory,
                R.drawable.tui_avatar
            )
        setAccessoryIcon(accessory)

        typedArray.recycle()
    }

    private fun setDescription(description: String?) {
        binding.description.text = description ?: ""
    }

    private fun setTitle(title: String?) {
        binding.title.text = title ?: ""
    }

    private fun setAccessoryIcon(accessory: Int) {
        binding.imgAccessory.setImageResource(accessory)
    }
}

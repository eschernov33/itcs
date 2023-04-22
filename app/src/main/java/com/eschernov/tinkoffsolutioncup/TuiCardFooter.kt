package com.eschernov.tinkoffsolutioncup

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.eschernov.tinkoffsolutioncup.databinding.TuiCardFooterBinding

class TuiCardFooter @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = R.style.Theme_TinkoffSolutionCup,
) : CardView(context, attrs, defStyleAttr) {

    private val binding: TuiCardFooterBinding

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.tui_card_footer, this, true)
        binding = TuiCardFooterBinding.bind(this)
        initAttr(attrs, defStyleAttr, defStyleRes)
    }

    private fun initAttr(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.TuiCardFooter, defStyleAttr, defStyleRes
        )

        val buttonText = typedArray.getString(R.styleable.TuiCardFooter_footerButtonText) ?: ""
        setButtonText(buttonText)

        typedArray.recycle()
    }

    private fun setButtonText(buttonText: String) {
        binding.actionButton.text = buttonText
    }
}

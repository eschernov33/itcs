package com.eschernov.tinkoffsolutioncup

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.eschernov.tinkoffsolutioncup.databinding.TuiCardHeaderBinding

class TuiCardHeader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = R.style.Theme_TinkoffSolutionCup,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: TuiCardHeaderBinding

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.tui_card_header, this, true)
        binding = TuiCardHeaderBinding.bind(this)
        initAttr(attrs, defStyleAttr, defStyleRes)
    }

    private fun initAttr(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.TuiCardHeader, defStyleAttr, defStyleRes
        )

        val title = typedArray.getString(R.styleable.TuiCardHeader_title)
        setTitle(title)

        val subTitle = typedArray.getString(R.styleable.TuiCardHeader_subtitle)
        setSubtitle(subTitle)

        val accessoryIcon =
            typedArray.getResourceId(R.styleable.TuiCardHeader_accessoryIcon, R.drawable.tui_avatar)
        setAccessoryIcon(accessoryIcon)

        val accessoryType = AccessoryType.values().getOrNull(
            typedArray.getInt(
                R.styleable.TuiCardHeader_accessoryType,
                AccessoryType.Icon.value
            )
        )
        setAccessoryType(accessoryType)

        typedArray.getString(R.styleable.TuiCardHeader_accessoryText)?.let(::setAccessoryText)
        typedArray.recycle()
    }

    private fun setAccessoryText(text: String) {
        binding.textAccessory.text = text
    }

    private fun setAccessoryType(accessoryType: AccessoryType?) {
        binding.textAccessory.setVisible(accessoryType == AccessoryType.Button)
        binding.imgAccessory.setVisible(accessoryType != AccessoryType.Button)
    }

    private fun setAccessoryIcon(accessory: Int) {
        binding.imgAccessory.setImageResource(accessory)
    }

    private fun setSubtitle(subTitle: String?) {
        binding.subTitle.setTextOrHide(subTitle)
    }

    private fun setTitle(title: String?) {
        binding.title.setTextOrHide(title)
    }

    private enum class AccessoryType(val value: Int) {
        Button(0), Icon(1)
    }
}

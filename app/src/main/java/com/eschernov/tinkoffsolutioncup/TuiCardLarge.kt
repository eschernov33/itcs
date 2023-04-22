package com.eschernov.tinkoffsolutioncup

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import androidx.core.view.children
import androidx.core.view.marginRight

class TuiCardLarge @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CardView(context, attrs, defStyleAttr) {

    private val marginBetweenBlocks = 20.px()
    private val defaultPadding = 20.px()

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val header = children.asIterable().firstOrNull { it is TuiCardHeader }
        val contentList = children.asIterable().filterIsInstance<TuiCardContent>()
        val footer = children.asIterable().firstOrNull { it is TuiCardFooter }

        var currentTopPosition = marginBetweenBlocks

        header?.let {
            it.layout(
                defaultPadding ,
                currentTopPosition + paddingTop,
                it.measuredWidth -(paddingLeft + it.paddingRight + it.marginRight),
                currentTopPosition + paddingBottom + it.measuredHeight,
            )
            currentTopPosition = paddingTop + paddingBottom + it.measuredHeight
        }
        currentTopPosition += marginBetweenBlocks
        contentList.forEachIndexed { index, view ->
            view.layout(
                defaultPadding + paddingLeft,
                paddingTop + currentTopPosition + (view.measuredHeight * index),
                paddingRight + view.measuredWidth,
                paddingBottom + view.measuredHeight + (view.measuredHeight * index) + currentTopPosition,
            )
            currentTopPosition += paddingTop + view.measuredHeight + (view.measuredHeight * index) + paddingBottom + (view.measuredHeight * index + 1)
        }
        currentTopPosition += marginBetweenBlocks
        footer?.layout(
            defaultPadding + paddingLeft,
            paddingTop + currentTopPosition,
            paddingRight + footer.measuredWidth - paddingRight,
            paddingBottom + currentTopPosition + footer.measuredHeight
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec) - paddingStart - paddingEnd
        var height = 0
        children.asIterable().map { view ->

            view.measure(widthMeasureSpec, heightMeasureSpec)
            height += view.measuredHeight
        }
        height += marginBetweenBlocks * 4

        setMeasuredDimension(
            width + paddingLeft + paddingRight,
            height + paddingLeft + paddingRight
        )
    }

}

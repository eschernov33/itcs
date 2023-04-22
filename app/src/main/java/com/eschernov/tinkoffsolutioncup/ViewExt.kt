package com.eschernov.tinkoffsolutioncup

import android.content.res.Resources
import android.view.View
import android.widget.TextView

fun TextView.setTextOrHide(text: String?) {
    this.setVisible(text != null)
    text?.let { this.text = text }
}

fun View.setVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun Int.px(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}

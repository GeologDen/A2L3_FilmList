package com.geekbrains.weatherwithmvvm.model

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(
        text: String,
        actionText: String,
        action: ((View) -> Unit)? = null,
        lenght: Int = Snackbar.LENGTH_INDEFINITE
) {
    val ourSnackBar = Snackbar.make(this, text, lenght)
    action?.let {
        ourSnackBar.setAction(actionText, it)
    }
    ourSnackBar.show()
}
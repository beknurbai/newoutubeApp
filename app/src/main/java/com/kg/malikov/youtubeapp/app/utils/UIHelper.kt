package com.kg.malikov.youtubeapp.app.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.kg.malikov.youtubeapp.app.R
import java.util.*

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun ImageView.loadImage(url: String) {
    Glide
        .with(this.context)
        .load(url)
        .centerCrop()
        .into(this)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
fun Activity.changeLanguage() {
    val listItems = arrayOf("English","Кыргызский","Русский")
    val mBuilder = AlertDialog.Builder(this)
    mBuilder.setTitle("Выберите язык")
    mBuilder.setSingleChoiceItems(listItems, -1) { dialog, which ->
        when (which) {
            0 -> {
                setLocale("en",this)
            }
            1 -> {
                setLocale("ky",this)
            }
            2 -> {
                setLocale("ru", this)
            }
        }
        this.recreate()
        dialog.dismiss()
    }
    val mDialog = mBuilder.create()
    mDialog.show()
}

private fun setLocale(s: String, context:Context) {
    val locale = Locale(s)
    Locale.setDefault(locale)
    val config = Configuration()
    config.locale = locale
    context.resources.updateConfiguration(
        config,
        context.resources.displayMetrics)
    LanguagePreference.getInstance(context)?.saveLanguage(s)

    //TODO: this in ViewModel
}
fun loadLocale(context: Context) {
    var language: String? = LanguagePreference.getInstance(context)?.getLanguage
    if (language != null) {
        setLocale(language,context)
    }

}

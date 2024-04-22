package com.app.shuru.views.extensions

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.app.shuru.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadUrl(logoUrl : String) {
    val context = this.context
    val placeHolder = ContextCompat.getDrawable(context, R.drawable.ic_user_placeholder)
    Glide.with(context)
        .load(logoUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .optionalFitCenter()
        .apply(RequestOptions.circleCropTransform())
        .dontAnimate()
        .into(this)

}

fun ImageView.loadUrlWthoutCache(logoUrl : String) {
    val context = this.context
    val placeHolder = ContextCompat.getDrawable(context, R.drawable.ic_user_placeholder)
    Glide.with(context)
        .load(logoUrl)
//        .diskCacheStrategy(DiskCacheStrategy.NONE)
//        .signature(ObjectKey(System.currentTimeMillis()))
        .skipMemoryCache(true)
        .placeholder(placeHolder)
        .optionalFitCenter()
        .apply(RequestOptions.circleCropTransform())
        .dontAnimate()
        .into(this)

}

fun ImageView.loadImage(logoUrl : String) {
    val context = this.context
    val placeHolder = ContextCompat.getDrawable(context, R.drawable.ic_user_placeholder)
    Glide.with(context)
        .load(logoUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .optionalFitCenter()
        .dontAnimate()
        .into(this)
}

fun ImageView.loadImageWithouCache(logoUrl : String) {
    val context = this.context
    val placeHolder = ContextCompat.getDrawable(context, R.drawable.ic_user_placeholder)
    Glide.with(context)
        .load(logoUrl)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .optionalFitCenter()
        .dontAnimate()
        .into(this)
}
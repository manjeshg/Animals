package mamdevsolutions.com.au.animals.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.palette.graphics.Palette
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import mamdevsolutions.com.au.animals.R

fun getProgressDrawable(context: Context) :CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

// adding a function to the ImageView
fun ImageView.loadImage(uri :String?, progressDrawable: CircularProgressDrawable) {
    val options :RequestOptions = RequestOptions()
            .placeholder(progressDrawable)
            .error(R.mipmap.ic_launcher_round)
    Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(uri)
            .into(this)

}

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, url: String?) {
    view.loadImage(url, getProgressDrawable(view.context))
}

package ua.nure.havrysh.kindergarten.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import ua.nure.havrysh.kindergarten.rest.ImageService


/**
 * Created by Oleg on 19.10.2016.
 */

object Utils {
    @BindingAdapter("bind:imageBitmap")
    fun loadImage(iv: ImageView, icon: String) {
        ImageService.getInstance().getBitmap(icon) { bmp -> iv.post { iv.setImageBitmap(bmp) } }
    }

}

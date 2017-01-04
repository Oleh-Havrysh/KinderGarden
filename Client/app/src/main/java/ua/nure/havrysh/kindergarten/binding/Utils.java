package ua.nure.havrysh.kindergarten.binding;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import ua.nure.havrysh.kindergarten.rest.ImageService;


/**
 * Created by Oleg on 19.10.2016.
 */

public class Utils {
    @BindingAdapter("bind:imageBitmap")
    public static void loadImage(final ImageView iv, String icon) {

        ImageService.getInstance().getBitmap(icon, new ImageService.BitmapCallback() {
            @Override
            public void consumeBitmap(final Bitmap bmp) {
                iv.post(new Runnable() {
                    @Override
                    public void run() {
                        iv.setImageBitmap(bmp);
                    }
                });
            }
        });


    }

}

package com.liuzhenli.app.utils.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.Target;
import com.liuzhenli.app.observer.SampleProgressObserver;
import com.liuzhenli.app.utils.RxUtil;

import java.io.File;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

/**
 * @author Liuzhenli
 * @since 2019-07-06 10:26
 */
public class ImageUtil {
    /**
     * @param context activity fragment的contextFile file = Glide.with(mContext)
     *                .load(url)
     *                .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
     *                .get();
     *                return file.getAbsolutePath();
     * @param url     图片地址
     * @param view    imageView
     */
    public static void setImage(Context context, String url, ImageView view) {
        Glide.with(context).load(url).into(view);
    }

    public static void setImage(Context context, String url, ImageView view, int placeHolder) {
        Glide.with(context).load(url).placeholder(placeHolder).error(placeHolder).into(view);
    }

    /**
     * Glide 加载图片  圆形图片
     * placeHolderResId 加载失败填充图资源id
     */
    public static void setRoundImage(Context cxt, String url, ImageView imageView, int placeHolderResId) {
        Glide.with(cxt).load(url).error(placeHolderResId).placeholder(placeHolderResId).transform(new GlideCircleTransform()).into(imageView);
    }

    /**
     * Glide 加载图片
     * placeHolderResId 加载失败填充图资源id
     */
    public static void setRoundedCornerImage(Context cxt, String url, int placeHolderResId, ImageView imageView) {
        Glide.with(cxt).load(url).placeholder(placeHolderResId).transform(new GlideRoundTransform()).into(imageView);
    }

    public static void setRoundedCornerImage(Context cxt, String url, ImageView imageView) {
        Glide.with(cxt).load(url).dontAnimate().placeholder(imageView.getDrawable()).transform(new GlideRoundTransform()).into(imageView);
    }

    /**
     * Glide 加载图片
     * placeHolderResId 加载失败填充图资源id
     *
     * @param radius dp
     */
    public static void setRoundedCornerImage(Context cxt, String url, int placeHolderResId, ImageView imageView, int radius) {
        Glide.with(cxt).load(url).dontAnimate().placeholder(placeHolderResId).transform(new GlideRoundTransform(radius)).into(imageView);
    }

    public static void setGifImage(Context context, int url, ImageView view) {
        Glide.with(context).asGif().load(url).into(view);
    }

    /**
     * 下载图片
     */
    public void downloadImage(Context context, String url, SampleProgressObserver<String> observer) {
        RxUtil.subscribe(Observable.fromCallable(() -> {
            File file = Glide.with(context).load(url).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
            return file.getAbsolutePath();
        }), observer);
    }
}

package com.liuzhenli.app.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * describe:切换时候取消动画效果
 *
 * @author Liuzhenli on 2020-06-09 11:23
 */
public class NoAnimViewPager extends ViewPager {
    public NoAnimViewPager(@NonNull Context context) {
        super(context);
    }

    public NoAnimViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        //去除页面切换时的滑动翻页效果
        super.setCurrentItem(item, false);
    }

}

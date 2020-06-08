package com.liuzhenli.app.view.loading;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RotateDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liuzhenli.app.R;


/***
 * loading 对话框
 * @author liuzhenli 848808263@qq.com
 */
public class CustomProgressDialog extends Dialog {

    private TextView mProgress;

    public CustomProgressDialog(Context context) {
        this(context, R.style.loading_dialog);
    }

    public CustomProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public CustomProgressDialog instance(Activity activity) {
        ViewGroup v = (ViewGroup) View.inflate(activity, R.layout.common_progress_view, null);
        mProgress = v.findViewById(R.id.tv_progress);

        ProgressBar pb = v.findViewById(R.id.common_progress);
        if (pb != null) {
            RotateDrawable rd = (RotateDrawable) pb.getIndeterminateDrawable();
            GradientDrawable gd = (GradientDrawable) rd.getDrawable();
            if (gd != null) {
                gd.setColors(new int[]{Color.WHITE, pb.getContext().getResources().getColor(R.color.main)});
                gd.setShape(GradientDrawable.RING);
            }
        }
        setContentView(v,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
        );
        return this;
    }


    public void setProgress(String progress) {
        mProgress.setText(progress);
    }
}
package com.liuzhenli.app.base.rxlife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.liuzhenli.app.ui.fragment.WebExplorerFragment;
import com.liuzhenli.app.view.loading.CustomProgressDialog;
import com.qmuiteam.qmui.arch.QMUIActivity;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import io.reactivex.subjects.BehaviorSubject;

import static com.liuzhenli.app.ui.fragment.WebExplorerFragment.EXTRA_TITLE;
import static com.liuzhenli.app.ui.fragment.WebExplorerFragment.EXTRA_URL;


/**
 * @author Liuzhenli
 * @since 2019-07-06 16:51
 */
public abstract class RxAppCompatActivity extends QMUIFragmentActivity {
    public final String TAG = RxAppCompatActivity.this.getClass().getSimpleName();
    protected final BehaviorSubject<LifeEvent> lifeSubject = BehaviorSubject.create();
    /**
     * 进度条
     */
    private CustomProgressDialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifeSubject.onNext(LifeEvent.CREATE);
    }

    public CustomProgressDialog getDialog() {
        if (dialog == null) {
            dialog = new CustomProgressDialog(this).instance(this);
            dialog.setCancelable(true);
        }
        return dialog;
    }

    public void setProgress(String progress) {
        if (dialog == null) {
            showDialog();
        }
        if (dialog != null) {
            dialog.setProgress(progress);
        }
    }

    public void hideDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public void showDialog() {
        getDialog().show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        lifeSubject.onNext(LifeEvent.CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifeSubject.onNext(LifeEvent.RESUME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifeSubject.onNext(LifeEvent.PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        lifeSubject.onNext(LifeEvent.STOP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifeSubject.onNext(LifeEvent.DESTROY);
    }

}

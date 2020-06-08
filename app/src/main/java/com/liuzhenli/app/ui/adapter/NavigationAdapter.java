package com.liuzhenli.app.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.liuzhenli.app.R;
import com.liuzhenli.app.bean.NavigationData;
import com.liuzhenli.app.view.recyclerview.adapter.BaseViewHolder;
import com.liuzhenli.app.view.recyclerview.adapter.RecyclerArrayAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * describe:
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/6/4 8:41 PM
 */
public class NavigationAdapter extends RecyclerArrayAdapter<NavigationData.DataBean> {


    public NavigationAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new NavigationViewHolder(parent, R.layout.item_navigation);
    }


    class NavigationViewHolder extends BaseViewHolder<NavigationData.DataBean> {
        @BindView(R.id.tv_navigation_title)
        TextView mTvTitle;

        public NavigationViewHolder(ViewGroup parent, int res) {
            super(parent, res);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(NavigationData.DataBean item) {
            super.setData(item);
            mTvTitle.setText(item.name);
            if (item.isSelected) {
                mTvTitle.setBackgroundResource(R.drawable.selector_bg_gray_radius);
            } else {
                mTvTitle.setBackgroundColor(mContext.getResources().getColor(R.color.qmui_config_color_transparent));
            }
        }
    }
}

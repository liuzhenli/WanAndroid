package com.liuzhenli.app.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.liuzhenli.app.R;
import com.liuzhenli.app.bean.NavigationData;
import com.liuzhenli.app.databinding.ItemNavigationBinding;
import com.liuzhenli.app.view.recyclerview.adapter.BaseViewHolder;
import com.liuzhenli.app.view.recyclerview.adapter.RecyclerArrayAdapter;


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
        ItemNavigationBinding binding;
        public NavigationViewHolder(ViewGroup parent, int res) {
            super(parent, res);
            binding = ItemNavigationBinding.bind(itemView);
        }

        @Override
        public void setData(NavigationData.DataBean item) {
            super.setData(item);
            binding.tvNavigationTitle.setText(item.name);
            if (item.isSelected) {
                binding.tvNavigationTitle.setBackgroundResource(R.drawable.selector_bg_gray_radius);
            } else {
                binding.tvNavigationTitle.setBackgroundColor(mContext.getResources().getColor(R.color.qmui_config_color_transparent));
            }
        }
    }
}

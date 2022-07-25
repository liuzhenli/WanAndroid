package com.liuzhenli.app.ui.adapter;

import android.content.Context;
import android.text.Html;
import android.view.ViewGroup;

import com.liuzhenli.app.R;
import com.liuzhenli.app.bean.ArticleBean;
import com.liuzhenli.app.databinding.ItemPorjectBinding;
import com.liuzhenli.app.utils.image.ImageUtil;
import com.liuzhenli.app.view.recyclerview.adapter.BaseViewHolder;
import com.liuzhenli.app.view.recyclerview.adapter.RecyclerArrayAdapter;


/**
 * describe:
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/6/14 4:33 PM
 */
public class ProjectAdapter extends RecyclerArrayAdapter<ArticleBean> {

    public ProjectAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProjectViewHolder(parent, R.layout.item_porject);
    }

    public class ProjectViewHolder extends BaseViewHolder<ArticleBean> {


        ItemPorjectBinding binding;

        public ProjectViewHolder(ViewGroup parent, int res) {
            super(parent, res);
            binding = ItemPorjectBinding.bind(itemView);
        }

        @Override
        public void setData(ArticleBean item) {
            super.setData(item);
            ImageUtil.setImage(mContext, item.envelopePic, binding.ivProjectCover);
            binding.tvProjectName.setText(Html.fromHtml(item.title));
            binding.tvProjectDes.setText(Html.fromHtml(item.desc));
            binding.tvProjectShareTime.setText(item.niceShareDate);
        }
    }
}

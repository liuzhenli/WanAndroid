package com.liuzhenli.app.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuzhenli.app.R;
import com.liuzhenli.app.bean.ArticleBean;
import com.liuzhenli.app.utils.image.ImageUtil;
import com.liuzhenli.app.view.recyclerview.adapter.BaseViewHolder;
import com.liuzhenli.app.view.recyclerview.adapter.RecyclerArrayAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        @BindView(R.id.iv_project_cover)
        ImageView mIvProjectCover;
        @BindView(R.id.tv_project_name)
        TextView mTvProjectName;
        @BindView(R.id.tv_project_des)
        TextView mTvProjectDes;
        @BindView(R.id.tv_project_share_time)
        TextView mTvProjectShareTime;

        public ProjectViewHolder(ViewGroup parent, int res) {
            super(parent, res);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(ArticleBean item) {
            super.setData(item);
            ImageUtil.setImage(mContext, item.envelopePic, mIvProjectCover);
            mTvProjectName.setText(item.title);
            mTvProjectDes.setText(item.desc);
            mTvProjectShareTime.setText(item.niceShareDate);
        }
    }
}

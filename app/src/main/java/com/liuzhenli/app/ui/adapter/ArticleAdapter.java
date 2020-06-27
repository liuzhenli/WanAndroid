package com.liuzhenli.app.ui.adapter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuzhenli.app.R;
import com.liuzhenli.app.bean.ArticleBean;
import com.liuzhenli.app.bean.ArticleListBean;
import com.liuzhenli.app.ui.activity.JumpActivity;
import com.liuzhenli.app.utils.ClickUtils;
import com.liuzhenli.app.view.recyclerview.adapter.BaseViewHolder;
import com.liuzhenli.app.view.recyclerview.adapter.RecyclerArrayAdapter;

import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * describe:
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/5/24 11:07 AM
 */
public class ArticleAdapter extends RecyclerArrayAdapter<ArticleBean> {

    public ArticleAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, R.layout.item_home_page_article);
    }

    class ViewHolder extends BaseViewHolder<ArticleBean> {
        @BindView(R.id.tv_article_title)
        TextView mTvTitle;
        @BindView(R.id.tv_article_author)
        TextView mTvAuthor;
        @BindView(R.id.iv_article_source)
        TextView mIvArticleSource;
        @BindView(R.id.tv_article_time)
        TextView mViewArticleTime;
        @BindView(R.id.tv_article_type)
        TextView mViewArticleType;

        public ViewHolder(ViewGroup parent, int res) {
            super(parent, res);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(ArticleBean item) {
            super.setData(item);
            if (item.itemType == 1) {
                mTvTitle.setText(MessageFormat.format("【顶】 {0}", Html.fromHtml(item.title)));
            } else {
                mTvTitle.setText(Html.fromHtml(item.title));
            }
            if (TextUtils.isEmpty(item.author)) {
                mIvArticleSource.setText("分享人:");
                mTvAuthor.setText(item.shareUser);
                ClickUtils.click(mTvAuthor, o -> mContext.startActivity(JumpActivity.createUserArticleListIntent(mContext, item.shareUser, item.title)));
            } else {
                mIvArticleSource.setText("作者:");
                mTvAuthor.setText(item.author);
                ClickUtils.click(mTvAuthor, o -> mContext.startActivity(JumpActivity.createUserArticleListIntent(mContext, item.author, item.title)));
            }
            mViewArticleType.setText(String.format("分类:%s/%s", item.superChapterName, item.chapterName));
            mViewArticleTime.setText(item.niceDate);
        }
    }
}

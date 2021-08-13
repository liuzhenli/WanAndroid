package com.liuzhenli.app.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.liuzhenli.app.R;
import com.liuzhenli.app.base.BaseFragment;
import com.liuzhenli.app.bean.NavigationData;
import com.liuzhenli.app.databinding.FragmentNavigationBinding;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.ui.activity.JumpActivity;
import com.liuzhenli.app.ui.adapter.NavigationAdapter;
import com.liuzhenli.app.ui.contract.NavigationContract;
import com.liuzhenli.app.ui.presenter.NavigationPresenter;
import com.liuzhenli.app.utils.DensityUtil;
import com.liuzhenli.app.view.recyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;


/**
 * describe:
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/6/2 9:01 PM
 */
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View {


    private NavigationAdapter leftAdapter;
    private FragmentNavigationBinding binding;

    public static NavigationFragment getInstance() {
        NavigationFragment instance = new NavigationFragment();
        return instance;
    }

    @Override
    public View bindContentView(LayoutInflater inflater, ViewGroup container, boolean attachParent) {
        binding = FragmentNavigationBinding.inflate(inflater, container, attachParent);
        return binding.getRoot();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {
        binding.rvHomeNavigation.setLayoutManager(new LinearLayoutManager(mContext));
        leftAdapter = new NavigationAdapter(mContext);
        binding.rvHomeNavigation.setAdapter(leftAdapter);
        leftAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeData(position);
            }
        });
        showDialog();
    }

    @Override
    public void showNavigation(NavigationData data) {
        hideDialog();
        leftAdapter.clear();
        leftAdapter.addAll(data.data);
        changeData(0);
    }

    @Override
    public void showError(Exception e) {
        hideDialog();
    }

    @Override
    public void complete() {
        hideDialog();
    }

    private void changeData(int position) {
        List<NavigationData.DataBean> data = leftAdapter.getAllData();
        binding.fgNavigationContainer.removeAllViews();
        for (NavigationData.DataBean.ArticlesBean item : data.get(position).articles) {
            TextView tv = new TextView(mContext);
            tv.setText(item.title);
            tv.setTextColor(getResources().getColor(R.color.text_color_99));
            tv.setPadding(DensityUtil.dip2px(mContext, 12), DensityUtil.dip2px(mContext, 6), DensityUtil.dip2px(mContext, 12), DensityUtil.dip2px(mContext, 6));
            tv.setBackgroundResource(R.drawable.selector_bg_gray_radius);
            tv.setOnClickListener(v -> startActivity(JumpActivity.createWebExplorerIntent(mContext, item.link, item.title)));
            binding.fgNavigationContainer.addView(tv);
        }
        for (int i = 0; i < leftAdapter.getAllData().size(); i++) {
            if (position == i) {
                leftAdapter.getRealAllData().get(i).isSelected = true;
            } else {
                leftAdapter.getRealAllData().get(i).isSelected = false;
            }
        }
        leftAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {
            if (leftAdapter.getCount() == 0) {
                mPresenter.getNavigation();
            }
        }
    }
}

package com.yuanpeiyu.fangdai;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.yuanpeiyu.mdm.BR;
import com.example.yuanpeiyu.mdm.R;

import java.util.List;

/**
 * Created by Yuan Peiyu on 2018/3/13.
 */

public class TestAdapter extends RecyclerView.Adapter {

    Context ctx;
    LayoutInflater inflater;
    List<DataBindTestBean> data;
    public TestAdapter(Context context, List<DataBindTestBean> data) {
        this.ctx = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void setData(List<DataBindTestBean> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.list_item, parent, false);
        return new BaseViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewDataBinding binding = ((BaseViewHolder)holder).getBinding();
        binding.setVariable(BR.user, data.get(position));
        binding.executePendingBindings();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class BaseViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
        /**
         * ViewDataBinding
         */
        private B mBinding;


        public BaseViewHolder(B binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        /**
         * @return viewDataBinding
         */
        public B getBinding() {
            return mBinding;
        }

    }
}

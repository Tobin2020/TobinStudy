package com.tobin.top.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tobin.top.R;
import com.tobin.top.ui.OnItemClickListener;
import com.tobin.top.utils.LogUtil;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder>{
    private OnItemClickListener onItemClickListener;
    private List<DashboardBean> mFuncList;
    private RecyclerView recyclerView;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView funcImage;
        TextView funcName;

        public ViewHolder(View view) {
            super(view);
            funcImage = view.findViewById(R.id.iv_dashboard_kinds);
            funcName = view.findViewById(R.id.tv_dashboard_content);
        }

    }

    public DashboardAdapter() {
    }

    public void setData(List<DashboardBean> funcList){
        this.mFuncList =funcList;

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(v -> {
            onItemClickListener.onItemClick(view,holder.getLayoutPosition(),mFuncList.get(holder.getLayoutPosition()));
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DashboardBean fruit = mFuncList.get(position);
        holder.funcImage.setImageResource(fruit.getImageId());
        holder.funcName.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        if (mFuncList == null) return 0;
        LogUtil.d("DashboardAdapter getItemCount: " + mFuncList.size());
        return mFuncList.size();
    }

    /**
     *   将RecycleView附加到Adapter上
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    /**
     *   将RecycleView从Adapter解除
     */
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }
}

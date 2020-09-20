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

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> implements View.OnClickListener {
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

    public DashboardAdapter(List<DashboardBean> funcList) {
        if (funcList != null){
            mFuncList = funcList;
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener == null || recyclerView == null) {
            LogUtil.d("DashboardAdapter onItemClickListener or recyclerView is null");
            return;
        }
        int position = recyclerView.getChildAdapterPosition(view);
        onItemClickListener.onItemClick(recyclerView,view,position,mFuncList.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DashboardBean fruit = mFuncList.get(position);
        holder.funcImage.setImageResource(fruit.getImageId());
        holder.funcImage.setOnClickListener(this);
        holder.funcName.setText(fruit.getName());
        holder.funcName.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
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

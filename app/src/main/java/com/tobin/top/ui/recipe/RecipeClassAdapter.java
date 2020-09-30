package com.tobin.top.ui.recipe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tobin.top.R;
import com.tobin.top.bean.RecipesClassBean;
import com.tobin.top.ui.OnItemClickListener;
import com.tobin.top.utils.LogUtil;

import androidx.recyclerview.widget.RecyclerView;

public class RecipeClassAdapter extends RecyclerView.Adapter<RecipeClassAdapter.ViewHolder> implements View.OnClickListener {
    private OnItemClickListener onItemClickListener;
    private RecipesClassBean mRecipesClass;
    private RecyclerView recyclerView;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView className;

        public ViewHolder(View view) {
            super(view);
            className = view.findViewById(R.id.tv_recipe_class);
        }

    }

    public RecipeClassAdapter() {}

    public void setData(RecipesClassBean recipesClassBean){
        LogUtil.d("RecipeClassAdapter setData: " + recipesClassBean.toString());
        mRecipesClass = recipesClassBean;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener == null || recyclerView == null) {
            LogUtil.d("RecipeClassAdapter onItemClickListener or recyclerView is null");
            return;
        }
        int position = recyclerView.getChildAdapterPosition(view);
        onItemClickListener.onItemClick(recyclerView,view,position, mRecipesClass.getResult().get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_class, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecipesClassBean.ResultBean recipesClass = mRecipesClass.getResult().get(position);
        holder.className.setText(recipesClass.getName());

    }

    @Override
    public int getItemCount() {
        if (mRecipesClass == null || mRecipesClass.getResult() == null) return 0;
        LogUtil.d("RecipeClassAdapter getItemCount: " + mRecipesClass.getResult().size());
        return mRecipesClass.getResult().size();
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

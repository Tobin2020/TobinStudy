package com.tobin.top.ui.recipe;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.tobin.top.R;
import com.tobin.top.base.BaseApplication;
import com.tobin.top.utils.LogUtil;

import org.jetbrains.annotations.NotNull;


public class RecipeAdapter extends BaseQuickAdapter<RecipesBean.ResultBean.ListBean, BaseViewHolder> {

    public RecipeAdapter() {
        super(R.layout.item_recipe);
    }

    /**
     * 在此方法中设置item数据
     */
    @Override
    protected void convert(@NotNull BaseViewHolder helper, @NotNull RecipesBean.ResultBean.ListBean listBean) {

        helper.setText(R.id.tv_recipe_title, listBean.getName());
        helper.setText(R.id.tv_recipe_content, listBean.getTag());
        LogUtil.d("getPic: " + listBean.getPic());

        //设置图片大小
        RoundedCorners roundedCorners = new RoundedCorners(6);
        //通过RequestOptions扩展功能,
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners)
                .placeholder(new ColorDrawable(Color.GRAY))//设置占位图
                .error(R.mipmap.ic_launcher)//设置错误图片
                .fallback(new ColorDrawable(Color.RED))
                .override(300, 300);//采样率

        Glide.with(BaseApplication.getInstance())
                .load(listBean.getPic())
                .apply(options)
                .into((ImageView) helper.getView(R.id.iv_recipe_icon));
    }


}
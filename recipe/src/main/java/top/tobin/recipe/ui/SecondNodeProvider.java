package top.tobin.recipe.ui;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import top.tobin.basic.base.BaseApplication;
import top.tobin.basic.utils.LogUtil;
import top.tobin.recipe.R;

public class SecondNodeProvider extends BaseNodeProvider  {

    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_node_second_recipe_class;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, @NotNull BaseNode data) {
        // 数据类型需要自己强转
        SecondNode entity = (SecondNode) data;
        helper.setText(R.id.tv_second_item, entity.getData().getName());
    }

    @Override
    public void onChildClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        super.onChildClick(helper, view, data, position);
        LogUtil.d("SecondNodeProvider onChildClick position: " + position + "\nview: " + view.getId());
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        LogUtil.d("SecondNodeProvider onClick position: " + position + "\nview: " + view.getId());
        SecondNode secondNode = (SecondNode) data;
        Intent intent = new Intent(BaseApplication.getInstance(), RecipeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(RecipeActivity.INTENT_DATA, secondNode.getData());
        BaseApplication.getInstance().startActivity(intent);
    }
}

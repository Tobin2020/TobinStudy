package top.tobin.top.ui.recipe;

import android.view.View;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.tobin.top.R;

import org.jetbrains.annotations.NotNull;

public class FirstNodeProvider extends BaseNodeProvider {

    @Override
    public int getItemViewType() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_node_first_recipe_class;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, @NotNull BaseNode data) {
        // 数据类型需要自己强转
        FirstNode entity = (FirstNode) data;
        helper.setText(R.id.tv_recipe_class, entity.getTitle());

        if (entity.isExpanded()) {
            helper.setBackgroundResource(R.id.iv_first_node_arrow, R.drawable.ic_arrow_down);
        } else {
            helper.setBackgroundResource(R.id.iv_first_node_arrow, R.drawable.ic_arrow_up);
        }
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        getAdapter().expandOrCollapse(position);
    }
}

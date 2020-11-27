package top.tobin.top.ui.recipe;

import android.os.Bundle;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.gyf.immersionbar.ImmersionBar;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import top.tobin.basic.base.BaseActivity;
import top.tobin.basic.widgets.CustomToast;
import top.tobin.basic.widgets.RecycleViewDivider;

import com.tobin.top.R;

/**
 * @author lijunbin
 * @date 2020/9/17
 * @email 616041023@qq.com
 * @description 做菜
 */
public class RecipeClassActivity extends BaseActivity<RecipeClassViewModel> {
    private Toolbar toolbar;

    private RecyclerView recyclerView;
    RecipeClassNodeAdapter adapter;
    private RecipesClassBean recipesClassBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int onCreate() {
        return R.layout.activity_recipe_class;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.rcv_recipe_class);
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("菜谱");
        setSupportActionBar(toolbar);  //加载Toolbar控件
        ImmersionBar.with(this).titleBar(R.id.tool_bar).statusBarDarkFont(true).init();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, 12, getResources().getColor(R.color.colorWhite)));
        adapter = new RecipeClassNodeAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        viewModel.getRecipesClassLiveData().observe(this, recipesClassBean -> {
            if (recipesClassBean != null){
                this.recipesClassBean = recipesClassBean;
                adapter.setList(getEntity(recipesClassBean));
            }

        });
    }

    private List<BaseNode> getEntity(RecipesClassBean recipesClassBean) {
        List<BaseNode> list = new ArrayList<>();
        for (RecipesClassBean.ResultBean resultBean: recipesClassBean.getResult()) {

            List<BaseNode> secondNodeList = new ArrayList<>();
            for (RecipesClassBean.ResultBean.ListBean listBean: resultBean.getList()) {
                SecondNode seNode = new SecondNode(listBean);
                secondNodeList.add(seNode);
            }
            FirstNode entity = new FirstNode(secondNodeList, resultBean.getName());

            // 模拟 默认第0个是展开的
            entity.setExpanded("1".equalsIgnoreCase(resultBean.getClassid()));

            list.add(entity);
        }
        return list;
    }

    @Override
    protected RecipeClassViewModel initViewModel() {
        return new ViewModelProvider(this).get(RecipeClassViewModel.class);
    }

    @Override
    protected void showError(Object obj) {
        CustomToast.makeText(this, obj == null ? "" : obj.toString(), CustomToast.LENGTH_SHORT);
    }
}
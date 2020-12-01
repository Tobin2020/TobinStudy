package top.tobin.study.ui.expandable.recyclerview;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public interface ViewProducer {
    int VIEW_TYPE_EMPTY = 1 << 30;
    int VIEW_TYPE_HEADER = VIEW_TYPE_EMPTY >> 1;

    /**
     * equivalent to RecyclerView.Adapter#onCreateViewHolder(ViewGroup, int)
     *
     * @param parent ViewGroup
     */
    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

    /**
     * equivalent to RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int)
     *
     * @param holder RecyclerView.ViewHolder
     */
    void onBindViewHolder(RecyclerView.ViewHolder holder);

    public static class DefaultEmptyViewHolder extends RecyclerView.ViewHolder {
        public DefaultEmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}

package top.tobin.top.ui.searchview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tobin.top.R;

import java.util.List;


public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchViewHolder> {

    private OnItemClickListener onItemClickListener;
    private int                 historyIcon;
    private int                 historyTextColor;
    private List<String>        list;

    SearchRecyclerViewAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sv_view_rv_item, parent, false);
        final SearchViewHolder viewHolder = new SearchViewHolder(view);
        if (onItemClickListener != null) {
            view.setOnClickListener(v ->
                    onItemClickListener.onItemClick(SearchRecyclerViewAdapter.this, v, viewHolder.getLayoutPosition()));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.historyTextView.setText(list.get(position));
        holder.historyTextView.setTextColor(historyTextColor);
        holder.iconImageView.setImageResource(historyIcon);
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    List<String> getData() {
        return this.list;
    }

    void setNewData(List<String> newData) {
        this.list = newData;
        notifyDataSetChanged();
    }

    void addData(@NonNull String data) {
        this.list.add(data);
        notifyItemInserted(this.list.size());
    }

    void addData(@NonNull List<String> data) {
        int oldSize = this.list.size();
        this.list.addAll(data);
        notifyItemRangeInserted(oldSize - data.size(), data.size());
    }

    void setHistoryIcon(int historyIcon) {
        this.historyIcon = historyIcon;
    }

    void setHistoryTextColor(int historyTextColor) {
        this.historyTextColor = historyTextColor;
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    static class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView  historyTextView;
        ImageView iconImageView;

        private SearchViewHolder(View itemView) {
            super(itemView);
            historyTextView = itemView.findViewById(R.id.tv_history_item);
            iconImageView = itemView.findViewById(R.id.left_icon);
        }
    }


    interface OnItemClickListener {
        void onItemClick(SearchRecyclerViewAdapter adapter, View view, int position);
    }
}

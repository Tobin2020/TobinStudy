package top.tobin.top.ui;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public interface OnItemClickListener {
    void onItemClick(View view, int position, Object data);
}

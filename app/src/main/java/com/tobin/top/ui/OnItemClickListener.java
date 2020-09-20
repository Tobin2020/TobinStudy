package com.tobin.top.ui;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public interface OnItemClickListener {
    void onItemClick(RecyclerView recyclerView, View view, int position, Object data);
}

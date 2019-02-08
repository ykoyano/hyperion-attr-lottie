package com.github.ykoyano.hyperion.plugin.attr.lottie;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public abstract class DataViewHolder<T> extends RecyclerView.ViewHolder {

    private T data;

    public DataViewHolder(View itemView) {
        super(itemView);
    }

    public T getData() {
        return this.data;
    }

    public void bind(T data) {
        this.data = data;
        onDataChanged(data);
    }

    public void onDataChanged(T data) {

    }
}

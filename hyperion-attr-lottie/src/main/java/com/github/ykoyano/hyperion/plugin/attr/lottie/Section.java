package com.github.ykoyano.hyperion.plugin.attr.lottie;

import androidx.annotation.NonNull;

import java.util.List;

public class Section<T> implements Comparable<Section<?>> {

    private Class<?> type;
    private List<T> list;

    public Section(Class<?> type, List<T> list) {
        this.type = type;
        this.list = list;
    }

    public String getName() {
        return this.type.getSimpleName();
    }

    public List<T> getList() {
        return this.list;
    }

    @Override
    public int compareTo(@NonNull Section<?> o) {
        if (type.isAssignableFrom(o.type)) {
            return 1;
        }
        if (o.type.isAssignableFrom(type)) {
            return -1;
        }
        return 0;
    }
}

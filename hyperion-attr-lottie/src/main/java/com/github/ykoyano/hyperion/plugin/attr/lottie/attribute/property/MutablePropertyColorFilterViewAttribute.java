package com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property;

import android.graphics.ColorFilter;

import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.MutableViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.property.PropertyListView;

import androidx.annotation.NonNull;

public abstract class MutablePropertyColorFilterViewAttribute extends MutableViewAttribute<ColorFilter> {

    public MutablePropertyColorFilterViewAttribute(String key, @NonNull ColorFilter value) {
        super(key, value);
    }

    @Override
    public int getViewType() {
        return PropertyListView.ITEM_FLOAT;
    }
}

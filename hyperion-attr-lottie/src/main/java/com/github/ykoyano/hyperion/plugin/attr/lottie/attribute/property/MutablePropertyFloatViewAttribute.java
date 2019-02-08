package com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property;

import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.MutableViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.property.PropertyListView;

import androidx.annotation.NonNull;

public abstract class MutablePropertyFloatViewAttribute extends MutableViewAttribute<Float> {

    public MutablePropertyFloatViewAttribute(String key, @NonNull Float value) {
        super(key, value);
    }

    @Override
    public int getViewType() {
        return PropertyListView.ITEM_FLOAT;
    }
}

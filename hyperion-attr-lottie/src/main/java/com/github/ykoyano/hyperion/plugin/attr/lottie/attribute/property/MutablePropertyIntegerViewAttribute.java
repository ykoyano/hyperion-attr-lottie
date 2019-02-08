package com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property;

import com.github.ykoyano.hyperion.plugin.attr.lottie.property.PropertyListView;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.MutableViewAttribute;

import androidx.annotation.NonNull;

public abstract class MutablePropertyIntegerViewAttribute extends MutableViewAttribute<Integer> {

    public MutablePropertyIntegerViewAttribute(String key, @NonNull Integer value) {
        super(key, value);
    }

    @Override
    public int getViewType() {
        return PropertyListView.ITEM_INTEGER;
    }
}

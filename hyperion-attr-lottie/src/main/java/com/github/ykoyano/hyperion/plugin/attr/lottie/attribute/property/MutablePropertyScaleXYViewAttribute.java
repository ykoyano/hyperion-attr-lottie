package com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property;

import com.airbnb.lottie.value.ScaleXY;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.MutableViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.property.PropertyListView;

import androidx.annotation.NonNull;

public abstract class MutablePropertyScaleXYViewAttribute extends MutableViewAttribute<ScaleXY> {

    public MutablePropertyScaleXYViewAttribute(String key, @NonNull ScaleXY value) {
        super(key, value);
    }

    @Override
    public int getViewType() {
        return PropertyListView.ITEM_SCALE_XY;
    }
}

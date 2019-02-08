package com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.detail;

import com.github.ykoyano.hyperion.plugin.attr.lottie.AttributeDetailView;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.MutableViewAttribute;

import androidx.annotation.NonNull;

public abstract class MutableFloatViewAttribute extends MutableViewAttribute<Float> {

    public MutableFloatViewAttribute(String key, @NonNull Float value) {
        super(key, value);
    }

    @Override
    public int getViewType() {
        return AttributeDetailView.ITEM_MUTABLE_FLOAT_ATTRIBUTE;
    }
}

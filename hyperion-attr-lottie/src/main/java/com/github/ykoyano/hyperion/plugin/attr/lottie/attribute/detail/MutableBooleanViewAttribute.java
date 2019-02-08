package com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.detail;

import com.github.ykoyano.hyperion.plugin.attr.lottie.AttributeDetailView;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.MutableViewAttribute;

import androidx.annotation.NonNull;

public abstract class MutableBooleanViewAttribute extends MutableViewAttribute<Boolean> {
    public MutableBooleanViewAttribute(String key, @NonNull Boolean value) {
        super(key, value);
    }

    public boolean getBoolean() {
        Boolean bool = value;
        return bool == null ? false : bool;
    }

    @Override
    public int getViewType() {
        return AttributeDetailView.ITEM_MUTABLE_BOOLEAN_ATTRIBUTE;
    }
}
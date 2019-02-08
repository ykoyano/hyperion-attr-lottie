package com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.detail;

import android.graphics.Color;

import com.github.ykoyano.hyperion.plugin.attr.lottie.AttributeDetailView;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.MutableViewAttribute;

import androidx.annotation.NonNull;

public abstract class MutableColorViewAttribute extends MutableViewAttribute<Color> {
    MutableColorViewAttribute(String key, @NonNull Color value) {
        super(key, value);
    }

    @Override
    public int getViewType() {
        return AttributeDetailView.ITEM_MUTABLE_COLOR_ATTRIBUTE;
    }
}

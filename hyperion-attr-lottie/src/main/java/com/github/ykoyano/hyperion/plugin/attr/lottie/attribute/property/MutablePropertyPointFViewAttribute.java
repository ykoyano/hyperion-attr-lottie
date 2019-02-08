package com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property;

import android.graphics.PointF;

import com.github.ykoyano.hyperion.plugin.attr.lottie.property.PropertyListView;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.MutableViewAttribute;

import androidx.annotation.NonNull;

public abstract class MutablePropertyPointFViewAttribute extends MutableViewAttribute<PointF> {

    public MutablePropertyPointFViewAttribute(String key, @NonNull PointF value) {
        super(key, value);
    }

    @Override
    public int getViewType() {
        return PropertyListView.ITEM_POINT_F;
    }
}

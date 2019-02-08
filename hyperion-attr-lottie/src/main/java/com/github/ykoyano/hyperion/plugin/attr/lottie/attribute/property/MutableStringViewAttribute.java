package com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property;

import com.github.ykoyano.hyperion.plugin.attr.lottie.AttributeDetailView;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.MutableViewAttribute;

import androidx.annotation.NonNull;

public abstract class MutableStringViewAttribute extends MutableViewAttribute<CharSequence> {

    public MutableStringViewAttribute(String key, @NonNull CharSequence value) {
        super(key, value);
    }

    @Override
    public int getViewType() {
        return AttributeDetailView.ITEM_MUTABLE_STRING_ATTRIBUTE;
    }
}

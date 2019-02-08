package com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.detail;

import com.airbnb.lottie.model.KeyPath;
import com.github.ykoyano.hyperion.plugin.attr.lottie.AttributeDetailView;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.MutableViewAttribute;

import androidx.annotation.NonNull;

public abstract class KeyPathViewAttribute extends MutableViewAttribute<KeyPath> {

    public KeyPathViewAttribute(String key, @NonNull KeyPath value) {
        super(key, value);
    }

    @NonNull
    public CharSequence getDisplayValue() {
        if (value == null) {
            return "";
        }

        return this.value.keysToString();
    }

    @Override
    public int getViewType() {
        return AttributeDetailView.ITEM_MUTABLE_KYE_PATH_ATTRIBUTE;
    }
}

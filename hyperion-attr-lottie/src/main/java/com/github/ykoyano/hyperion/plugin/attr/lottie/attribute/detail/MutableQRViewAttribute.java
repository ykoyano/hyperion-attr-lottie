package com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.detail;

import com.github.ykoyano.hyperion.plugin.attr.lottie.AttributeDetailView;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.MutableViewAttribute;

import androidx.annotation.NonNull;

public abstract class MutableQRViewAttribute extends MutableViewAttribute<CharSequence> {

    public MutableQRViewAttribute(String key, @NonNull CharSequence value) {
        super(key, value);
    }

    @Override
    public int getViewType() {
        return AttributeDetailView.ITEM_QR;
    }
}

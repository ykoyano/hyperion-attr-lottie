package com.github.ykoyano.hyperion.plugin.attr.lottie.collectors.element;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.KeyPathElement;
import com.github.ykoyano.hyperion.plugin.attr.lottie.ViewAttribute;
import com.willowtreeapps.hyperion.plugin.v1.AttributeTranslator;

import java.util.List;

public abstract class KeyPathAttributeCollector<T extends KeyPathElement> {

    private final Class<T> type;

    public KeyPathAttributeCollector(Class<T> type) {
        this.type = type;
    }

    public final boolean acceptsType(Class<?> type) {
        return this.type.isAssignableFrom(type);
    }

    public final Class<T> getType() {
        return type;
    }

    @NonNull
    public abstract List<ViewAttribute> collect(
            LottieAnimationView view,
            AttributeTranslator attributeTranslator,
            KeyPath keyPath
    );

    @NonNull
    protected CharSequence nonNull(@Nullable CharSequence s) {
        return s == null ? "" : s;
    }
}

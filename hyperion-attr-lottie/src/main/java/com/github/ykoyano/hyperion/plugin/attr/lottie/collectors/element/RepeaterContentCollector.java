package com.github.ykoyano.hyperion.plugin.attr.lottie.collectors.element;

import android.annotation.SuppressLint;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.content.RepeaterContent;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieRelativeFloatValueCallback;
import com.github.ykoyano.hyperion.plugin.attr.lottie.ViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property.MutablePropertyFloatViewAttribute;
import com.google.auto.service.AutoService;
import com.willowtreeapps.hyperion.plugin.v1.AttributeTranslator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

@AutoService(KeyPathAttributeCollector.class)
@SuppressWarnings("unused")
public class RepeaterContentCollector extends KeyPathAttributeCollector<RepeaterContent> {

    public RepeaterContentCollector() {
        super(RepeaterContent.class);
    }

    @SuppressLint("RestrictedApi")
    @NonNull
    @Override
    public List<ViewAttribute> collect(
            final LottieAnimationView view,
            AttributeTranslator attributeTranslator,
            final KeyPath keyPath
    ) {
        List<ViewAttribute> attributes = new ArrayList<>();

        attributes.add(new MutablePropertyFloatViewAttribute("REPEATER_COPIES", 0f) {
            @Override
            protected void mutate(Float value) {
                view.addValueCallback(
                        keyPath,
                        LottieProperty.REPEATER_COPIES,
                        new LottieRelativeFloatValueCallback(value)
                );
                view.resumeAnimation();
            }
        });

        attributes.add(new MutablePropertyFloatViewAttribute("REPEATER_OFFSET", 0f) {
            @Override
            protected void mutate(Float value) {
                view.addValueCallback(
                        keyPath,
                        LottieProperty.REPEATER_OFFSET,
                        new LottieRelativeFloatValueCallback(value)
                );
                view.resumeAnimation();
            }
        });

        return attributes;
    }
}

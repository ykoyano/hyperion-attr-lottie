package com.github.ykoyano.hyperion.plugin.attr.lottie.collectors.element;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.content.GradientStrokeContent;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieRelativeFloatValueCallback;
import com.airbnb.lottie.value.LottieValueCallback;
import com.github.ykoyano.hyperion.plugin.attr.lottie.ViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property.MutablePropertyIntegerViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property.MutablePropertyFloatViewAttribute;
import com.google.auto.service.AutoService;
import com.willowtreeapps.hyperion.plugin.v1.AttributeTranslator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

@AutoService(KeyPathAttributeCollector.class)
@SuppressWarnings("unused")
public class GradientStrokeContentCollector extends KeyPathAttributeCollector<GradientStrokeContent> {

    public GradientStrokeContentCollector() {
        super(GradientStrokeContent.class);
    }

    @NonNull
    @Override
    public List<ViewAttribute> collect(
            final LottieAnimationView view,
            AttributeTranslator attributeTranslator,
            final KeyPath keyPath
    ) {
        List<ViewAttribute> attributes = new ArrayList<>();

        // TODO("implement")
        // LottieProperty.COLOR_FILTER

        attributes.add(new MutablePropertyIntegerViewAttribute("OPACITY", 0) {
            @Override
            protected void mutate(Integer value) {
                view.addValueCallback(
                        keyPath,
                        LottieProperty.OPACITY,
                        new LottieValueCallback<>(value)
                );
                view.resumeAnimation();
            }
        });

        attributes.add(new MutablePropertyFloatViewAttribute("STROKE_WIDTH", 0f) {
            @Override
            protected void mutate(Float value) {
                view.addValueCallback(
                        keyPath,
                        LottieProperty.STROKE_WIDTH,
                        new LottieRelativeFloatValueCallback(value)
                );
                view.resumeAnimation();
            }
        });

        return attributes;
    }
}

package com.github.ykoyano.hyperion.plugin.attr.lottie.collectors.element;

import android.graphics.PointF;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.content.PolystarContent;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieRelativeFloatValueCallback;
import com.airbnb.lottie.value.LottieRelativePointValueCallback;
import com.github.ykoyano.hyperion.plugin.attr.lottie.ViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property.MutablePropertyPointFViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property.MutablePropertyFloatViewAttribute;
import com.google.auto.service.AutoService;
import com.willowtreeapps.hyperion.plugin.v1.AttributeTranslator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

@AutoService(KeyPathAttributeCollector.class)
@SuppressWarnings("unused")
public class PolystarContentCollector extends KeyPathAttributeCollector<PolystarContent> {

    public PolystarContentCollector() {
        super(PolystarContent.class);
    }

    @NonNull
    @Override
    public List<ViewAttribute> collect(
            final LottieAnimationView view,
            AttributeTranslator attributeTranslator,
            final KeyPath keyPath
    ) {
        List<ViewAttribute> attributes = new ArrayList<>();

        attributes.add(new MutablePropertyPointFViewAttribute("POSITION", new PointF(0f, 0f)) {
            @Override
            protected void mutate(PointF value) {
                view.addValueCallback(
                        keyPath,
                        LottieProperty.POSITION,
                        new LottieRelativePointValueCallback(value)
                );
                view.resumeAnimation();
            }
        });

        attributes.add(new MutablePropertyFloatViewAttribute("POLYSTAR_POINTS", 0f) {
            @Override
            protected void mutate(Float value) {
                view.addValueCallback(
                        keyPath,
                        LottieProperty.POLYSTAR_POINTS,
                        new LottieRelativeFloatValueCallback(value)
                );
                view.resumeAnimation();
            }
        });

        attributes.add(new MutablePropertyFloatViewAttribute("POLYSTAR_ROTATION", 0f) {
            @Override
            protected void mutate(Float value) {
                view.addValueCallback(
                        keyPath,
                        LottieProperty.POLYSTAR_ROTATION,
                        new LottieRelativeFloatValueCallback(value)
                );
                view.resumeAnimation();
            }
        });

        attributes.add(new MutablePropertyFloatViewAttribute("POLYSTAR_INNER_RADIUS", 0f) {
            @Override
            protected void mutate(Float value) {
                view.addValueCallback(
                        keyPath,
                        LottieProperty.POLYSTAR_INNER_RADIUS,
                        new LottieRelativeFloatValueCallback(value)
                );
                view.resumeAnimation();
            }
        });

        attributes.add(new MutablePropertyFloatViewAttribute("POLYSTAR_OUTER_RADIUS", 0f) {
            @Override
            protected void mutate(Float value) {
                view.addValueCallback(
                        keyPath,
                        LottieProperty.POLYSTAR_OUTER_RADIUS,
                        new LottieRelativeFloatValueCallback(value)
                );
                view.resumeAnimation();
            }
        });

        attributes.add(new MutablePropertyFloatViewAttribute("POLYSTAR_INNER_ROUNDEDNESS", 0f) {
            @Override
            protected void mutate(Float value) {
                view.addValueCallback(
                        keyPath,
                        LottieProperty.POLYSTAR_INNER_ROUNDEDNESS,
                        new LottieRelativeFloatValueCallback(value)
                );
                view.resumeAnimation();
            }
        });

        attributes.add(new MutablePropertyFloatViewAttribute("POLYSTAR_OUTER_ROUNDEDNESS", 0f) {
            @Override
            protected void mutate(Float value) {
                view.addValueCallback(
                        keyPath,
                        LottieProperty.POLYSTAR_OUTER_ROUNDEDNESS,
                        new LottieRelativeFloatValueCallback(value)
                );
                view.resumeAnimation();
            }
        });

        return attributes;
    }
}

package com.github.ykoyano.hyperion.plugin.attr.lottie.collectors.element;

import android.graphics.PointF;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.content.EllipseContent;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieRelativePointValueCallback;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property.MutablePropertyPointFViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.ViewAttribute;
import com.google.auto.service.AutoService;
import com.willowtreeapps.hyperion.plugin.v1.AttributeTranslator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

@AutoService(KeyPathAttributeCollector.class)
@SuppressWarnings("unused")
public class EllipseContentCollector extends KeyPathAttributeCollector<EllipseContent> {

    public EllipseContentCollector() {
        super(EllipseContent.class);
    }

    @NonNull
    @Override
    public List<ViewAttribute> collect(
            final LottieAnimationView view,
            AttributeTranslator attributeTranslator,
            final KeyPath keyPath
    ) {
        List<ViewAttribute> attributes = new ArrayList<>();

        attributes.add(new MutablePropertyPointFViewAttribute("ELLIPSE_SIZE", new PointF(0f, 0f)) {
            @Override
            protected void mutate(PointF value) {
                view.addValueCallback(
                        keyPath,
                        LottieProperty.ELLIPSE_SIZE,
                        new LottieRelativePointValueCallback(value)
                );
                view.resumeAnimation();
            }
        });

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

        return attributes;
    }
}

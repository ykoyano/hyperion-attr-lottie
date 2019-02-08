package com.github.ykoyano.hyperion.plugin.attr.lottie.collectors.element;

import android.graphics.PointF;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.layer.ShapeLayer;
import com.airbnb.lottie.value.LottieRelativeFloatValueCallback;
import com.airbnb.lottie.value.LottieRelativePointValueCallback;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.ScaleXY;
import com.github.ykoyano.hyperion.plugin.attr.lottie.ViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property.MutablePropertyIntegerViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property.MutablePropertyPointFViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property.MutablePropertyFloatViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property.MutablePropertyScaleXYViewAttribute;
import com.google.auto.service.AutoService;
import com.willowtreeapps.hyperion.plugin.v1.AttributeTranslator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

@AutoService(KeyPathAttributeCollector.class)
@SuppressWarnings("unused")
public class ShapeLayerCollector extends KeyPathAttributeCollector<ShapeLayer> {

    public ShapeLayerCollector() {
        super(ShapeLayer.class);
    }

    @NonNull
    @Override
    public List<ViewAttribute> collect(
            final LottieAnimationView view,
            AttributeTranslator attributeTranslator,
            final KeyPath keyPath
    ) {
        List<ViewAttribute> attributes = new ArrayList<>();

        attributes.add(new MutablePropertyScaleXYViewAttribute("TRANSFORM_SCALE", new ScaleXY(0f, 0f)) {
            @Override
            protected void mutate(ScaleXY value) {
                view.addValueCallback(
                        keyPath,
                        LottieProperty.TRANSFORM_SCALE,
                        new LottieValueCallback<>(value)
                );
                view.resumeAnimation();
            }
        });

        attributes.add(new MutablePropertyPointFViewAttribute("TRANSFORM_ANCHOR_POINT", new PointF(0f, 0f)) {
            @Override
            protected void mutate(PointF value) {
                view.addValueCallback(
                        keyPath,
                        LottieProperty.TRANSFORM_ANCHOR_POINT,
                        new LottieRelativePointValueCallback(value)
                );
                view.resumeAnimation();
            }
        });

        attributes.add(new MutablePropertyPointFViewAttribute("TRANSFORM_POSITION", new PointF(0f, 0f)) {
            @Override
            protected void mutate(PointF value) {
                view.addValueCallback(
                        keyPath,
                        LottieProperty.TRANSFORM_POSITION,
                        new LottieRelativePointValueCallback(value)
                );
                view.resumeAnimation();
            }
        });

        attributes.add(new MutablePropertyFloatViewAttribute("TRANSFORM_ROTATION", 0f) {
            @Override
            protected void mutate(Float value) {
                view.addValueCallback(
                        keyPath,
                        LottieProperty.TRANSFORM_ROTATION,
                        new LottieRelativeFloatValueCallback(value)
                );
                view.resumeAnimation();
            }
        });

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

        attributes.add(new MutablePropertyFloatViewAttribute("TRANSFORM_START_OPACITY", 0f) {
            @Override
            protected void mutate(Float value) {
                view.addValueCallback(
                        keyPath,
                        LottieProperty.TRANSFORM_START_OPACITY,
                        new LottieRelativeFloatValueCallback(value)
                );
                view.resumeAnimation();
            }
        });

        attributes.add(new MutablePropertyFloatViewAttribute("TRANSFORM_END_OPACITY", 0f) {
            @Override
            protected void mutate(Float value) {
                view.addValueCallback(
                        keyPath,
                        LottieProperty.TRANSFORM_END_OPACITY,
                        new LottieRelativeFloatValueCallback(value)
                );
                view.resumeAnimation();
            }
        });

        return attributes;
    }
}

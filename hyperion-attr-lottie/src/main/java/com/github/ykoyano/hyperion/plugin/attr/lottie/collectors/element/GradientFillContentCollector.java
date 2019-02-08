package com.github.ykoyano.hyperion.plugin.attr.lottie.collectors.element;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.animation.content.GradientFillContent;
import com.airbnb.lottie.model.KeyPath;
import com.github.ykoyano.hyperion.plugin.attr.lottie.ViewAttribute;
import com.google.auto.service.AutoService;
import com.willowtreeapps.hyperion.plugin.v1.AttributeTranslator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

@AutoService(KeyPathAttributeCollector.class)
@SuppressWarnings("unused")
public class GradientFillContentCollector extends KeyPathAttributeCollector<GradientFillContent> {

    public GradientFillContentCollector() {
        super(GradientFillContent.class);
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

        return attributes;
    }
}

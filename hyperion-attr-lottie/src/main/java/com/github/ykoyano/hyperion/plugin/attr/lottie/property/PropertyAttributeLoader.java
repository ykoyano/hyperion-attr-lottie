package com.github.ykoyano.hyperion.plugin.attr.lottie.property;

import android.annotation.SuppressLint;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.model.KeyPath;
import com.github.ykoyano.hyperion.plugin.attr.lottie.Section;
import com.github.ykoyano.hyperion.plugin.attr.lottie.ViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.collectors.element.KeyPathAttributeCollector;
import com.willowtreeapps.hyperion.plugin.v1.AttributeTranslator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;

class PropertyAttributeLoader {

    private final AttributeTranslator attributeTranslator;
    private final List<KeyPathAttributeCollector> typedCollectors;
    @Nullable
    private final KeyPath keyPath = null;

    @Inject
    PropertyAttributeLoader(AttributeTranslator attributeTranslator) {
        this.attributeTranslator = attributeTranslator;
        this.typedCollectors = PropertyAttributeCollectors.get();
    }

    @SuppressLint("RestrictedApi")
    @SuppressWarnings("unchecked")
    List<Section<ViewAttribute>> getAttributesForView(LottieAnimationView view, KeyPath keyPath) {
        List<Section<ViewAttribute>> sections = new ArrayList<>(12);
        for (KeyPathAttributeCollector aggregator : typedCollectors) {
            if (aggregator.acceptsType(keyPath.getResolvedElement().getClass())) {
                List<ViewAttribute> attributes = aggregator.collect(view, attributeTranslator, keyPath);
                Section<ViewAttribute> section = new Section<>(aggregator.getType(), attributes);
                sections.add(section);
            }
        }
        Collections.sort(sections);
        return sections;
    }

}
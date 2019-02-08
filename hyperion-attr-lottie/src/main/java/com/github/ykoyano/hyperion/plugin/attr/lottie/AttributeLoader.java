package com.github.ykoyano.hyperion.plugin.attr.lottie;

import android.view.View;

import com.github.ykoyano.hyperion.plugin.attr.lottie.collectors.LottieTypedAttributeCollector;
import com.willowtreeapps.hyperion.plugin.v1.AttributeTranslator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

class AttributeLoader {

    private final AttributeTranslator attributeTranslator;
    private final List<LottieTypedAttributeCollector> typedCollectors;

    @Inject
    AttributeLoader(AttributeTranslator attributeTranslator) {
        this.attributeTranslator = attributeTranslator;
        this.typedCollectors = AttributeCollectors.get();
    }

    @SuppressWarnings("unchecked")
    List<Section<ViewAttribute>> getAttributesForView(View view) {
        List<Section<ViewAttribute>> sections = new ArrayList<>(12);
        for (LottieTypedAttributeCollector aggregator : typedCollectors) {
             if (aggregator.acceptsType(view.getClass())) {
                List<ViewAttribute> attributes = aggregator.collect(view, attributeTranslator);
                Section<ViewAttribute> section = new Section<>(aggregator.getType(), attributes);
                sections.add(section);
            }
        }
        Collections.sort(sections);
        return sections;
    }
}
package com.github.ykoyano.hyperion.plugin.attr.lottie;

import com.github.ykoyano.hyperion.plugin.attr.lottie.collectors.LottieTypedAttributeCollector;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

final class AttributeCollectors {

    private static List<LottieTypedAttributeCollector> collectors;

    static List<LottieTypedAttributeCollector> get() {
        synchronized (AttributeCollectors.class) {
            if (collectors == null) {
                synchronized (AttributeCollectors.class) {
                    collectors = load();
                }
            }
        }
        return collectors;
    }

    private static List<LottieTypedAttributeCollector> load() {
        ServiceLoader<LottieTypedAttributeCollector> loader =
                ServiceLoader.load(LottieTypedAttributeCollector.class);
        List<LottieTypedAttributeCollector> collectors = new ArrayList<>(10);
        for (LottieTypedAttributeCollector collector : loader) {
            collectors.add(collector);
        }
        return collectors;
    }

    AttributeCollectors() {
        throw new AssertionError("No instances.");
    }

}

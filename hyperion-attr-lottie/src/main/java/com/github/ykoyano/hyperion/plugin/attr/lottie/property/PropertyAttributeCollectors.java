package com.github.ykoyano.hyperion.plugin.attr.lottie.property;

import com.github.ykoyano.hyperion.plugin.attr.lottie.collectors.element.KeyPathAttributeCollector;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

final class PropertyAttributeCollectors {

    private static List<KeyPathAttributeCollector> collectors;

    static List<KeyPathAttributeCollector> get() {
        synchronized (PropertyAttributeCollectors.class) {
            if (collectors == null) {
                synchronized (PropertyAttributeCollectors.class) {
                    collectors = load();
                }
            }
        }
        return collectors;
    }

    private static List<KeyPathAttributeCollector> load() {
        ServiceLoader<KeyPathAttributeCollector> loader =
                ServiceLoader.load(KeyPathAttributeCollector.class);
        List<KeyPathAttributeCollector> collectors = new ArrayList<>(10);
        for (KeyPathAttributeCollector collector : loader) {
            collectors.add(collector);
        }
        return collectors;
    }

    PropertyAttributeCollectors() {
        throw new AssertionError("No instances.");
    }

}

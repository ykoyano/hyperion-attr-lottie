package com.github.ykoyano.hyperion.plugin.attr.lottie;

import com.google.auto.service.AutoService;
import com.willowtreeapps.hyperion.plugin.v1.Plugin;
import com.willowtreeapps.hyperion.plugin.v1.PluginModule;

@AutoService(Plugin.class)
@SuppressWarnings("unused")
public class LottieAttributeInspectorPlugin extends Plugin {

    @Override
    public PluginModule createPluginModule() {
        return new LottieAttributeInspectorModule();
    }

}

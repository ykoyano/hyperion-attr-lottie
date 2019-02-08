package com.github.ykoyano.hyperion.plugin.attr.lottie.attribute;

import androidx.annotation.NonNull;

import android.util.Log;

import com.github.ykoyano.hyperion.plugin.attr.lottie.ViewAttribute;

public abstract class MutableViewAttribute<T> extends ViewAttribute<T> {

    private boolean activated;

    protected MutableViewAttribute(String key, @NonNull T value) {
        super(key, value);
    }

    public void setValue(T value) {
        try {
            mutate(value);
            this.value = value;
        } catch (Exception e) {
            Log.e("Hyperion", "Error mutating view", e);
        }
    }

    protected abstract void mutate(T value) throws Exception;

    public boolean isActivated() {
        return this.activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}

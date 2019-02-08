package com.github.ykoyano.hyperion.plugin.attr.lottie;


public class Header implements AttributeDetailItem {

    private final String text;

    public Header(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public int getViewType() {
        return AttributeDetailView.ITEM_HEADER;
    }

}
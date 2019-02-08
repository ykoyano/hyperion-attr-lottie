package com.github.ykoyano.hyperion.plugin.attr.lottie.collectors;

import android.content.Context;
import android.graphics.PointF;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.widget.PopupWindow;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieListener;
import com.airbnb.lottie.model.KeyPath;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.detail.KeyPathViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.detail.MutableBooleanViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.detail.MutableFloatViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.detail.MutableQRViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property.MutableStringViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.ViewAttribute;
import com.google.auto.service.AutoService;
import com.willowtreeapps.hyperion.plugin.v1.AttributeTranslator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

@AutoService(LottieTypedAttributeCollector.class)
@SuppressWarnings("unused")
public class LottieAnimationViewAttributeCollector extends LottieTypedAttributeCollector<LottieAnimationView> {

    public LottieAnimationViewAttributeCollector() {
        super(LottieAnimationView.class);
    }

    @NonNull
    @Override
    public List<ViewAttribute> collect(final LottieAnimationView view, AttributeTranslator attributeTranslator) {
        List<ViewAttribute> attributes = new ArrayList<>();

        attributes.add(new MutableStringViewAttribute("URL", "") {
            @Override
            protected void mutate(CharSequence value) {
                view.pauseAnimation();
                LottieCompositionFactory.fromUrl(view.getContext(), value.toString())
                        .addListener(new LottieListener<LottieComposition>() {
                            @Override
                            public void onResult(LottieComposition result) {
                                view.setComposition(result);
                                view.playAnimation();
                            }
                        })
                        .addFailureListener(new LottieListener<Throwable>() {
                            @Override
                            public void onResult(Throwable error) {
                                view.playAnimation();
                            }
                        });

            }
        });

        attributes.add(new MutableQRViewAttribute("QR", "") {
            @Override
            protected void mutate(CharSequence value) {
                final Context context = view.getContext();

                QRCodeReaderView qrCodeReaderView = new QRCodeReaderView(context);
                qrCodeReaderView.setQRDecodingEnabled(true);
                qrCodeReaderView.setAutofocusInterval(2000L);
                qrCodeReaderView.setBackCamera();

                final PopupWindow popupWindow = new PopupWindow(qrCodeReaderView, 2000, 2000);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);

                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }

                qrCodeReaderView.setOnQRCodeReadListener(new QRCodeReaderView.OnQRCodeReadListener() {
                    @Override
                    public void onQRCodeRead(String text, PointF[] points) {
                        popupWindow.dismiss();
                        view.pauseAnimation();
                        LottieCompositionFactory.fromUrl(context, text)
                                .addListener(new LottieListener<LottieComposition>() {
                                    @Override
                                    public void onResult(LottieComposition result) {
                                        view.setComposition(result);
                                        view.playAnimation();
                                    }
                                })
                                .addFailureListener(new LottieListener<Throwable>() {
                                    @Override
                                    public void onResult(Throwable error) {
                                        Log.d("HOGE", error.toString());
                                        view.playAnimation();
                                    }
                                });
                    }
                });

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    popupWindow.showAsDropDown(view.getRootView(), 0, 0, Gravity.CENTER_HORIZONTAL);
                }
            }
        });

        attributes.add(new MutableFloatViewAttribute("Speed", view.getSpeed()) {
            @Override
            protected void mutate(Float value) {
                try {
                    view.setSpeed(value);
                    view.resumeAnimation();
                } catch (NumberFormatException ignored) {
                }
            }
        });

        attributes.add(new MutableStringViewAttribute("Repeat Mode", String.valueOf(view.getRepeatMode())) {
            @Override
            protected void mutate(CharSequence value) {
                try {
                    int integerValue = Integer.valueOf(value.toString().trim());
                    view.setRepeatMode(integerValue);
                    view.resumeAnimation();
                } catch (NumberFormatException ignored) {
                }

            }
        });

        attributes.add(new MutableFloatViewAttribute("Scale", view.getScale()) {
            @Override
            protected void mutate(Float value) {
                try {
                    view.setScale(value);
                    view.resumeAnimation();
                } catch (NumberFormatException ignored) {
                }

            }
        });

        attributes.add(new MutableFloatViewAttribute("Alpha", view.getAlpha()) {
            @Override
            protected void mutate(Float value) {
                try {
                    view.setAlpha(value);
                    view.resumeAnimation();
                } catch (NumberFormatException ignored) {
                }
            }
        });

        attributes.add(new MutableStringViewAttribute("Min Frame", String.valueOf(view.getMinFrame())) {
            @Override
            protected void mutate(CharSequence value) {
                try {
                    int integerValue = Integer.valueOf(value.toString().trim());
                    view.setMinFrame(integerValue);
                    view.resumeAnimation();
                } catch (NumberFormatException ignored) {
                }

            }
        });

        attributes.add(new MutableStringViewAttribute("Max Frame", String.valueOf(view.getMaxFrame())) {
            @Override
            protected void mutate(CharSequence value) {
                try {
                    int integerValue = Integer.valueOf(value.toString().trim());
                    view.setMaxFrame(integerValue);
                    view.resumeAnimation();
                } catch (NumberFormatException ignored) {
                }

            }
        });

        for (KeyPath keyPath : view.resolveKeyPath(new KeyPath("**"))) {
            attributes.add(new KeyPathViewAttribute("Key Path", keyPath) {
                @Override
                protected void mutate(KeyPath value) {
                    try {
                    } catch (NumberFormatException ignored) {
                    }
                }
            });
        }

        return attributes;
    }
}

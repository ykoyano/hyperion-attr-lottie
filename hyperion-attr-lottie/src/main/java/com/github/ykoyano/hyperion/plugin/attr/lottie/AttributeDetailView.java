package com.github.ykoyano.hyperion.plugin.attr.lottie;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.detail.KeyPathViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.detail.MutableBooleanViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.detail.MutableFloatViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.detail.MutableQRViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property.MutableStringViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.property.PropertyListView;
import com.willowtreeapps.hyperion.plugin.v1.ExtensionProvider;
import com.willowtreeapps.hyperion.plugin.v1.PluginExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ykoyano.github.com.hyperion_attr_lottie.R;

public class AttributeDetailView extends RecyclerView {

    public static final int ITEM_HEADER = 1;
    public static final int ITEM_ATTRIBUTE = 2;
    public static final int ITEM_MUTABLE_COLOR_ATTRIBUTE = 3;
    public static final int ITEM_MUTABLE_STRING_ATTRIBUTE = 4;
    public static final int ITEM_MUTABLE_FLOAT_ATTRIBUTE = 5;
    public static final int ITEM_MUTABLE_BOOLEAN_ATTRIBUTE = 6;
    public static final int ITEM_MUTABLE_KYE_PATH_ATTRIBUTE = 7;
    public static final int ITEM_QR = 8;

    private final AttributeAdapter adapter;
    private final AttributeLoader attributeLoader;

    public AttributeDetailView(@NonNull Context context) {
        this(context, null);
    }

    public AttributeDetailView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AttributeDetailView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        PluginExtension extension = ExtensionProvider.get(context);
        adapter = new AttributeAdapter();
        attributeLoader = new AttributeLoader(extension.getAttributeTranslator());

        setHasFixedSize(true);
        setLayoutManager(new LinearLayoutManager(context));
        setAdapter(adapter);
        DividerItemDecoration decoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        //noinspection ConstantConditions
        decoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.detail_divider));
        addItemDecoration(decoration);


    }

    public void setTarget(View target) {
        if (target != null) {
            List<Section<ViewAttribute>> sections = attributeLoader.getAttributesForView(target);
            List<AttributeDetailItem> items = toItems(sections);

            if (target instanceof LottieAnimationView) {
                adapter.setLottieAnimationView((LottieAnimationView) target);
            } else {
                adapter.setLottieAnimationView(null);
            }
            adapter.setItems(items);
        } else {
            adapter.setItems(Collections.<AttributeDetailItem>emptyList());
        }
    }

    private List<AttributeDetailItem> toItems(List<Section<ViewAttribute>> sections) {
        List<AttributeDetailItem> items = new ArrayList<>(18);
        for (Section<ViewAttribute> section : sections) {
            items.add(new Header(section.getName()));
            items.addAll(section.getList());
        }
        return items;
    }

    private static class AttributeAdapter extends RecyclerView.Adapter<DataViewHolder> {

        private List<AttributeDetailItem> items;
        @Nullable
        private LottieAnimationView animationView;

        private void setItems(List<AttributeDetailItem> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        private void setLottieAnimationView(LottieAnimationView animationView) {
            this.animationView = animationView;
        }

        @Override
        @NonNull
        public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView;
            switch (viewType) {
                case ITEM_HEADER:
                    itemView = inflater.inflate(R.layout.item_header, parent, false);
                    return new HeaderViewHolder(itemView);
                case ITEM_ATTRIBUTE:
                    itemView = inflater.inflate(R.layout.item_attribute, parent, false);
                    return new AttributeViewHolder(itemView);
                case ITEM_MUTABLE_STRING_ATTRIBUTE:
                    itemView = inflater.inflate(R.layout.item_mutable_string_attribute, parent, false);
                    return new MutableStringAttributeViewHolder(itemView);
                case ITEM_MUTABLE_FLOAT_ATTRIBUTE:
                    itemView = inflater.inflate(R.layout.item_mutable_float_attribute, parent, false);
                    return new MutableFloatAttributeViewHolder(itemView);
                case ITEM_MUTABLE_BOOLEAN_ATTRIBUTE:
                    itemView = inflater.inflate(R.layout.item_mutable_boolean_attribute, parent, false);
                    return new MutableBooleanAttributeViewHolder(itemView);
                case ITEM_MUTABLE_KYE_PATH_ATTRIBUTE:
                    itemView = inflater.inflate(R.layout.item_mutable_key_path_attribute, parent, false);
                    return new KeyPathViewAttributeViewHolder(itemView, animationView);
                case ITEM_QR:
                    itemView = inflater.inflate(R.layout.item_qr, parent, false);
                    return new QRViewHolder(itemView);
                default:
                    throw new IllegalStateException("Did not recognize view type: " + viewType);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
            //noinspection unchecked
            holder.bind(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items == null ? 0 : items.size();
        }

        @Override
        public int getItemViewType(int position) {
            return items.get(position).getViewType();
        }
    }

    private static class HeaderViewHolder extends DataViewHolder<Header> {

        private final TextView text;

        private HeaderViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }

        @Override
        public void onDataChanged(Header data) {
            text.setText(data.getText());
        }
    }

    private static class AttributeViewHolder<T extends ViewAttribute> extends DataViewHolder<T> {

        private final TextView keyText;
        final TextView valueText;
        private final ImageView image;

        private AttributeViewHolder(View itemView) {
            super(itemView);
            keyText = itemView.findViewById(R.id.key_text);
            valueText = itemView.findViewById(R.id.value_text);
            image = itemView.findViewById(R.id.image);
        }

        @Override
        public void onDataChanged(ViewAttribute data) {
            keyText.setText(data.getKey());

            valueText.setText(data.getDisplayValue());

            Drawable drawable = data.getDrawable();
            if (drawable != null) {
                image.setImageDrawable(drawable);
                image.setVisibility(View.VISIBLE);
            } else {
                image.setVisibility(View.GONE);
            }
        }
    }

    private static class MutableStringAttributeViewHolder
            extends AttributeViewHolder<MutableStringViewAttribute>
            implements View.OnClickListener, TextWatcher {

        private final ExpandableLayout detail;
        private final EditText editText;

        private MutableStringAttributeViewHolder(View itemView) {
            super(itemView);
            detail = itemView.findViewById(R.id.detail);
            editText = itemView.findViewById(R.id.edit_text);
            editText.setLongClickable(true);
            editText.setTextIsSelectable(true);
            editText.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return true;
                }
            });
            detail.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return true;
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return true;
                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final MutableStringViewAttribute attribute = getData();
            boolean activated = !attribute.isActivated();
            attribute.setActivated(activated);
            itemView.setActivated(activated);
            detail.setExpanded(activated);
        }

        @Override
        public void onDataChanged(MutableStringViewAttribute data) {
            super.onDataChanged(data);
            boolean activated = data.isActivated();
            itemView.setActivated(activated);
            detail.setExpanded(activated, false);
            editText.removeTextChangedListener(this);
            editText.setText(data.getDisplayValue());
            editText.addTextChangedListener(this);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            final MutableStringViewAttribute attribute = getData();
            final String value = s.toString();
            attribute.setValue(value);
            valueText.setText(value);
            valueText.setVisibility(View.VISIBLE);
        }
    }

    private static class MutableFloatAttributeViewHolder
            extends AttributeViewHolder<MutableFloatViewAttribute>
            implements View.OnClickListener, TextWatcher {

        private final ExpandableLayout detail;
        private final EditText editText;

        private MutableFloatAttributeViewHolder(View itemView) {
            super(itemView);
            detail = itemView.findViewById(R.id.detail);
            editText = itemView.findViewById(R.id.edit_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final MutableFloatViewAttribute attribute = getData();
            boolean activated = !attribute.isActivated();
            attribute.setActivated(activated);
            itemView.setActivated(activated);
            detail.setExpanded(activated);
        }

        @Override
        public void onDataChanged(MutableFloatViewAttribute data) {
            super.onDataChanged(data);
            boolean activated = data.isActivated();
            itemView.setActivated(activated);
            detail.setExpanded(activated, false);
            editText.removeTextChangedListener(this);
            editText.setText(data.getDisplayValue());
            editText.addTextChangedListener(this);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            final MutableFloatViewAttribute attribute = getData();
            final String value = s.toString();
            if (value.isEmpty()) return;
            try {
                final float floatValue = Float.valueOf(s.toString().trim());
                attribute.setValue(floatValue);
                valueText.setText(value);
                valueText.setVisibility(View.VISIBLE);
            } catch (NumberFormatException ignored) {
            }
        }
    }

    private static class MutableBooleanAttributeViewHolder
            extends DataViewHolder<MutableBooleanViewAttribute> implements View.OnClickListener {

        private final TextView keyText;
        private final Switch booleanSwitch;

        private MutableBooleanAttributeViewHolder(View itemView) {
            super(itemView);
            keyText = itemView.findViewById(R.id.key_text);
            booleanSwitch = itemView.findViewById(R.id.boolean_switch);
            booleanSwitch.setOnClickListener(this);
        }

        @Override
        public void onDataChanged(MutableBooleanViewAttribute data) {
            keyText.setText(data.getKey());
            booleanSwitch.setChecked(data.getBoolean());
        }

        @Override
        public void onClick(View v) {
            final MutableBooleanViewAttribute attribute = getData();
            attribute.setValue(booleanSwitch.isChecked());
        }
    }

    private static class KeyPathViewAttributeViewHolder
            extends AttributeViewHolder<KeyPathViewAttribute>
            implements View.OnClickListener {

        private final ExpandableLayout detail;
        private final PropertyListView list;
        private final LottieAnimationView animationView;

        private KeyPathViewAttributeViewHolder(View itemView, LottieAnimationView animationView) {
            super(itemView);
            detail = itemView.findViewById(R.id.detail);
            this.animationView = animationView;
            itemView.setOnClickListener(this);
            list = itemView.findViewById(R.id.list);
        }

        @Override
        public void onDataChanged(KeyPathViewAttribute data) {
            super.onDataChanged(data);
            list.setTarget(animationView, data.value);
        }

        @Override
        public void onClick(View v) {
            if (list.isEmpty()) return;

            final KeyPathViewAttribute attribute = getData();
            boolean activated = !attribute.isActivated();
            attribute.setActivated(activated);
            itemView.setActivated(activated);
            detail.setExpanded(activated);
        }
    }

    private static class QRViewHolder extends AttributeViewHolder<MutableQRViewAttribute> implements View.OnClickListener {

        private final ExpandableLayout detail;
        private final TextView text;

        private QRViewHolder(View itemView) {
            super(itemView);
            detail = itemView.findViewById(R.id.detail);
            text = itemView.findViewById(R.id.text);
            itemView.setOnClickListener(this);
            text.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    final MutableQRViewAttribute attribute = getData();
                    attribute.setValue("");
                }
            });
        }

        @Override
        public void onClick(View v) {
            final MutableQRViewAttribute attribute = getData();
            boolean activated = !attribute.isActivated();
            attribute.setActivated(activated);
            itemView.setActivated(activated);
            detail.setExpanded(activated);
        }
    }

}
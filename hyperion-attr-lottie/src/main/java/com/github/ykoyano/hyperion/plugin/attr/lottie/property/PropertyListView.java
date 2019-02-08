package com.github.ykoyano.hyperion.plugin.attr.lottie.property;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.ScaleXY;
import com.github.ykoyano.hyperion.plugin.attr.lottie.AttributeDetailItem;
import com.github.ykoyano.hyperion.plugin.attr.lottie.DataViewHolder;
import com.github.ykoyano.hyperion.plugin.attr.lottie.Header;
import com.github.ykoyano.hyperion.plugin.attr.lottie.Section;
import com.github.ykoyano.hyperion.plugin.attr.lottie.ViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property.MutablePropertyIntegerViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property.MutablePropertyPointFViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property.MutablePropertyFloatViewAttribute;
import com.github.ykoyano.hyperion.plugin.attr.lottie.attribute.property.MutablePropertyScaleXYViewAttribute;
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

public class PropertyListView extends RecyclerView {

    public static final int ITEM_HEADER = 1;
    public static final int ITEM_FLOAT = 2;
    public static final int ITEM_POINT_F = 3;
    public static final int ITEM_INTEGER = 4;
    public static final int ITEM_COLOR_FILTER = 5;
    public static final int ITEM_SCALE_XY = 6;

    private final AttributeAdapter adapter;
    private final PropertyAttributeLoader attributeLoader;

    public PropertyListView(@NonNull Context context) {
        this(context, null);
    }

    public PropertyListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PropertyListView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        PluginExtension extension = ExtensionProvider.get(context);
        adapter = new AttributeAdapter();

        attributeLoader = new PropertyAttributeLoader(extension.getAttributeTranslator());

        setHasFixedSize(true);
        setLayoutManager(new LinearLayoutManager(context));
        setAdapter(adapter);
        DividerItemDecoration decoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        //noinspection ConstantConditions
        decoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.detail_divider));
        addItemDecoration(decoration);
    }

    public void setTarget(LottieAnimationView target, KeyPath keyPath) {
        if (target != null) {
            List<Section<ViewAttribute>> sections = attributeLoader.getAttributesForView(target, keyPath);
            List<AttributeDetailItem> items = toItems(sections);
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

    public Boolean isEmpty() {
        return adapter.items.isEmpty();
    }

    private static class AttributeAdapter extends Adapter<DataViewHolder> {

        private List<AttributeDetailItem> items;

        private void setItems(List<AttributeDetailItem> items) {
            this.items = items;
            notifyDataSetChanged();
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
                case ITEM_FLOAT:
                    itemView = inflater.inflate(R.layout.item_mutable_property_integer_attribute, parent, false);
                    return new MutablePropertyFloatViewHolder(itemView);
                case ITEM_POINT_F:
                    itemView = inflater.inflate(R.layout.item_mutable_property_point_f_attribute, parent, false);
                    return new MutablePropertyPointFAttributeViewHolder(itemView);
                case ITEM_INTEGER:
                    itemView = inflater.inflate(R.layout.item_mutable_property_float_attribute, parent, false);
                    return new MutablePropertyIntegerAttributeViewHolder(itemView);
                case ITEM_SCALE_XY:
                    itemView = inflater.inflate(R.layout.item_mutable_property_scale_xy_attribute, parent, false);
                    return new MutablePropertyScaleXYViewAttributeViewHolder(itemView);
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

    private static class MutablePropertyIntegerAttributeViewHolder
            extends AttributeViewHolder<MutablePropertyIntegerViewAttribute>
            implements View.OnClickListener, TextWatcher {

        private final PropertyExpandableLayout detail;
        private final EditText editText;

        private MutablePropertyIntegerAttributeViewHolder(View itemView) {
            super(itemView);
            detail = itemView.findViewById(R.id.detail);
            editText = itemView.findViewById(R.id.edit_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final MutablePropertyIntegerViewAttribute attribute = getData();
            boolean activated = !attribute.isActivated();
            attribute.setActivated(activated);
            itemView.setActivated(activated);
            detail.setExpanded(activated);
        }

        @Override
        public void onDataChanged(MutablePropertyIntegerViewAttribute data) {
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
            final MutablePropertyIntegerViewAttribute attribute = getData();
            final String value = s.toString();
            if (value.isEmpty()) return;
            try {
                final int intValue = Integer.valueOf(s.toString().trim());
                attribute.setValue(intValue);
                valueText.setText(value);
                valueText.setVisibility(View.VISIBLE);
            } catch (NumberFormatException ignored) {
            }
        }
    }

    private static class MutablePropertyFloatViewHolder
            extends AttributeViewHolder<MutablePropertyFloatViewAttribute>
            implements View.OnClickListener, TextWatcher {

        private final PropertyExpandableLayout detail;
        private final EditText editText;

        private MutablePropertyFloatViewHolder(View itemView) {
            super(itemView);
            detail = itemView.findViewById(R.id.detail);
            editText = itemView.findViewById(R.id.edit_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final MutablePropertyFloatViewAttribute attribute = getData();
            boolean activated = !attribute.isActivated();
            attribute.setActivated(activated);
            itemView.setActivated(activated);
            detail.setExpanded(activated);
        }

        @Override
        public void onDataChanged(MutablePropertyFloatViewAttribute data) {
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
            final MutablePropertyFloatViewAttribute attribute = getData();
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

    private static class MutablePropertyPointFAttributeViewHolder
            extends AttributeViewHolder<MutablePropertyPointFViewAttribute>
            implements OnClickListener {

        private final PropertyExpandableLayout detail;
        private final EditText firstEditText;
        private final EditText secondEditText;

        private MutablePropertyPointFAttributeViewHolder(View itemView) {
            super(itemView);
            detail = itemView.findViewById(R.id.detail);
            firstEditText = itemView.findViewById(R.id.first_edit_text);
            secondEditText = itemView.findViewById(R.id.second_edit_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final MutablePropertyPointFViewAttribute attribute = getData();
            boolean activated = !attribute.isActivated();
            attribute.setActivated(activated);
            itemView.setActivated(activated);
            detail.setExpanded(activated);
        }

        @Override
        public void onDataChanged(MutablePropertyPointFViewAttribute data) {
            super.onDataChanged(data);
            boolean activated = data.isActivated();
            itemView.setActivated(activated);
            detail.setExpanded(activated, false);

            firstEditText.removeTextChangedListener(firstTextWatcher);
            firstEditText.setText(String.valueOf(data.getValue().x));
            firstEditText.addTextChangedListener(firstTextWatcher);

            secondEditText.removeTextChangedListener(secondTextWatcher);
            secondEditText.setText(String.valueOf(data.getValue().y));
            secondEditText.addTextChangedListener(secondTextWatcher);
        }

        private TextWatcher firstTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                final MutablePropertyPointFViewAttribute attribute = getData();
                try {
                    final float x = Float.valueOf(s.toString().trim());
                    final float y = Float.valueOf(secondEditText.getText().toString().trim());
                    final PointF pointF = new PointF(x, y);
                    attribute.setValue(pointF);
                    valueText.setText(pointF.toString());
                    valueText.setVisibility(View.VISIBLE);
                } catch (NumberFormatException ignored) {
                }
            }
        };

        private TextWatcher secondTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    final MutablePropertyPointFViewAttribute attribute = getData();
                    final float x = Float.valueOf(firstEditText.getText().toString().trim());
                    final float y = Float.valueOf(s.toString().trim());
                    final PointF pointF = new PointF(x, y);
                    attribute.setValue(pointF);
                    valueText.setText(pointF.toString());
                    valueText.setVisibility(View.VISIBLE);
                } catch (NumberFormatException ignored) {
                }
            }
        };
    }

    private static class MutablePropertyScaleXYViewAttributeViewHolder
            extends AttributeViewHolder<MutablePropertyScaleXYViewAttribute>
            implements OnClickListener {

        private final PropertyExpandableLayout detail;
        private final EditText firstEditText;
        private final EditText secondEditText;

        private MutablePropertyScaleXYViewAttributeViewHolder(View itemView) {
            super(itemView);
            detail = itemView.findViewById(R.id.detail);
            firstEditText = itemView.findViewById(R.id.first_edit_text);
            secondEditText = itemView.findViewById(R.id.second_edit_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final MutablePropertyScaleXYViewAttribute attribute = getData();
            boolean activated = !attribute.isActivated();
            attribute.setActivated(activated);
            itemView.setActivated(activated);
            detail.setExpanded(activated);
        }

        @Override
        public void onDataChanged(MutablePropertyScaleXYViewAttribute data) {
            super.onDataChanged(data);
            boolean activated = data.isActivated();
            itemView.setActivated(activated);
            detail.setExpanded(activated, false);

            firstEditText.removeTextChangedListener(firstTextWatcher);
            firstEditText.setText(String.valueOf(data.getValue().getScaleX()));
            firstEditText.addTextChangedListener(firstTextWatcher);

            secondEditText.removeTextChangedListener(secondTextWatcher);
            secondEditText.setText(String.valueOf(data.getValue().getScaleY()));
            secondEditText.addTextChangedListener(secondTextWatcher);
        }

        private TextWatcher firstTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                final MutablePropertyScaleXYViewAttribute attribute = getData();
                try {
                    final float x = Float.valueOf(s.toString().trim());
                    final float y = Float.valueOf(secondEditText.getText().toString().trim());
                    final ScaleXY pointF = new ScaleXY(x, y);
                    attribute.setValue(pointF);
                    valueText.setText(pointF.toString());
                    valueText.setVisibility(View.VISIBLE);
                } catch (NumberFormatException ignored) {
                }
            }
        };

        private TextWatcher secondTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    final MutablePropertyScaleXYViewAttribute attribute = getData();
                    final float x = Float.valueOf(firstEditText.getText().toString().trim());
                    final float y = Float.valueOf(s.toString().trim());
                    final ScaleXY pointF = new ScaleXY(x, y);
                    attribute.setValue(pointF);
                    valueText.setText(pointF.toString());
                    valueText.setVisibility(View.VISIBLE);
                } catch (NumberFormatException ignored) {
                }
            }
        };
    }
}
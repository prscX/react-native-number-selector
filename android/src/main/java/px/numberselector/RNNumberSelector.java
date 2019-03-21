
package px.numberselector;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactFontManager;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class RNNumberSelector extends ViewGroupManager<ViewGroup> {

    public static final String REACT_CLASS = "RNNumberSelector";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected FrameLayout createViewInstance(final ThemedReactContext reactContext) {

        final FrameLayout frameLayout = new FrameLayout(reactContext.getCurrentActivity());
        NumberPicker numberPicker = new NumberPicker(reactContext.getCurrentActivity());
        frameLayout.addView(numberPicker);

// Set divider color
//        numberPicker.setDividerColor(ContextCompat.getColor(reactContext, R.color.catalyst_redbox_background));
//        numberPicker.setDividerColorResource(R.color.catalyst_redbox_background);
//
//// Set formatter
//        numberPicker.setFormatter(getString(R.string.number_picker_formatter));
//        numberPicker.setFormatter(R.string.number_picker_formatter);
//
//// Set selected text color
//        numberPicker.setSelectedTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
//        numberPicker.setSelectedTextColorResource(R.color.colorPrimary);
//
//// Set selected text size
//        numberPicker.setSelectedTextSize(getResources().getDimension(R.dimen.selected_text_size));
//        numberPicker.setSelectedTextSize(R.dimen.selected_text_size);
//
//// Set text color
//        numberPicker.setTextColor(ContextCompat.getColor(this, R.color.dark_grey));
//        numberPicker.setTextColorResource(R.color.dark_grey);
//
//// Set text size
//        numberPicker.setTextSize(getResources().getDimension(R.dimen.text_size));
//        numberPicker.setTextSize(R.dimen.text_size);
//
//// Set typeface
//        numberPicker.setTypeface(Typeface.create(getString(R.string.roboto_light), Typeface.NORMAL));
//        numberPicker.setTypeface(getString(R.string.roboto_light), Typeface.NORMAL);
//        numberPicker.setTypeface(getString(R.string.roboto_light));
//        numberPicker.setTypeface(R.string.roboto_light, Typeface.NORMAL);
//        numberPicker.setTypeface(R.string.roboto_light);

// Set value
        numberPicker.setMaxValue(59);
        numberPicker.setMinValue(0);
        numberPicker.setValue(3);
        numberPicker.setOrientation(LinearLayout.HORIZONTAL);
        numberPicker.setWheelItemCount(5);

// Using string values
// IMPORTANT! setMinValue to 1 and call setDisplayedValues after setMinValue and setMaxValue
        String[] data = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(data.length);
        numberPicker.setDisplayedValues(data);
        numberPicker.setValue(7);

// Set fading edge enabled
        numberPicker.setFadingEdgeEnabled(true);

// Set scroller enabled
        numberPicker.setScrollerEnabled(true);

// Set wrap selector wheel
        numberPicker.setWrapSelectorWheel(true);

// OnClickListener
        numberPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("", "Click on current value");
            }
        });

// OnValueChangeListener
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.d("", String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal));
            }
        });

        return frameLayout;
    }
}